/*****************************************************************************
 * Copyright (c) 2020 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Christian W. Damus - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.types.core.matchers.stereotype;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;
import org.eclipse.papyrus.infra.services.edit.utils.RequestParameterConstants;
import org.eclipse.papyrus.uml.tools.utils.NamedElementUtil;
import org.eclipse.papyrus.uml.types.core.Activator;
import org.eclipse.papyrus.uml.types.core.requests.ApplyStereotypeRequest;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;

/**
 * An advice that applies the stereotype(s) specified by a stereotype application matcher.
 */
public class StereotypeMatcherEditHelperAdvice extends AbstractEditHelperAdvice {


	protected StereotypeApplicationMatcherConfiguration configuration;


	public StereotypeMatcherEditHelperAdvice(StereotypeApplicationMatcherConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public boolean approveRequest(IEditCommandRequest request) {
		if (request instanceof CreateElementRequest) {
			CreateElementRequest createElement = (CreateElementRequest) request;

			if (configuration == null) {
				return false;
			}

			if (!(createElement.getContainer() instanceof Element)) {
				return false;
			}

			Element container = (Element) createElement.getContainer();
			Package nearestPackage = container.getNearestPackage();
			if (nearestPackage == null) {
				return false;
			}

			String profileURI = configuration.getProfileUri();
			if (profileURI == null || !StereotypeApplicationMatcher.isProfileApplied(container, profileURI)) {
				// If the profile is not applied, then we cannot apply the stereotypes
				return false;
			}
		}

		return true;
	}

	@Override
	public void configureRequest(final IEditCommandRequest request) {
		if (!(request instanceof ConfigureRequest)) {
			return;
		}

		// Use the last stereotype for the name to set, similar to the ApplyStereotypeAdvice
		List<String> stereotypeQNames = configuration.getStereotypesQualifiedNames();
		if (!stereotypeQNames.isEmpty()) {
			request.setParameter(RequestParameterConstants.BASE_NAME_TO_SET,
					NamedElementUtil.getNameFromQualifiedName(stereotypeQNames.get(stereotypeQNames.size() - 1)));
		}
	}

	@Override
	protected ICommand getAfterConfigureCommand(ConfigureRequest request) {
		ICommand result = null;

		if (configuration == null) {
			return result;
		}

		EObject elementToConfigure = request.getElementToConfigure();
		if (!(elementToConfigure instanceof Element)) {
			return result;
		}

		TransactionalEditingDomain editingDomain = request.getEditingDomain();
		if (editingDomain == null) {
			return result;
		}

		IElementEditService service = ElementEditServiceUtils.getCommandProvider(elementToConfigure);
		if (service == null) {
			Activator.log.error(NLS.bind("Cannot get edit service from element: {0}.", elementToConfigure), null); //$NON-NLS-1$
			return result;
		}

		for (String next : configuration.getStereotypesQualifiedNames()) {
			Stereotype stereotype = ((Element) elementToConfigure).getApplicableStereotype(next);
			if (stereotype != null) {
				ICommand applyStereotypeCommand = service.getEditCommand(new ApplyStereotypeRequest((Element) elementToConfigure, stereotype, editingDomain));

				if (result == null) {
					result = applyStereotypeCommand;
				} else {
					result = result.compose(applyStereotypeCommand);
				}
			}

		}

		if (result != null) {
			result = result.reduce();
		}

		return result;
	}

}
