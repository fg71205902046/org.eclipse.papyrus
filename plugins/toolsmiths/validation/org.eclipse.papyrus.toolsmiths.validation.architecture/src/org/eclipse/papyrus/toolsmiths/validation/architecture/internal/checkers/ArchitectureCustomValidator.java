/*****************************************************************************
 * Copyright (c) 2021 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.architecture.internal.checkers;

import static org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.ProjectManagementUtils.getExtensionElements;
import static org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.ProjectManagementUtils.hasAttribute;
import static org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.ProjectManagementUtils.resourcePathIs;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.papyrus.infra.core.architecture.ADElement;
import org.eclipse.papyrus.infra.core.architecture.ArchitectureContext;
import org.eclipse.papyrus.infra.core.architecture.RepresentationKind;
import org.eclipse.papyrus.infra.types.ElementTypeSetConfiguration;
import org.eclipse.papyrus.infra.types.core.extensionpoints.IElementTypeSetExtensionPoint;
import org.eclipse.papyrus.toolsmiths.validation.architecture.internal.messages.Messages;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CustomModelChecker;
import org.eclipse.pde.core.plugin.IPluginElement;

/**
 * Custom validation rules for <em>Architecture Domain</em> models.
 */
public class ArchitectureCustomValidator extends CustomModelChecker.SwitchValidator {

	private static final String ELEM_ELEMENT_TYPE_SET = "elementTypeSet"; //$NON-NLS-1$

	public ArchitectureCustomValidator(String nsURI) {
		super(nsURI);
	}

	public void validate(ADElement element, DiagnosticChain diagnostics, Map<Object, Object> context) {
		validateIcon(element, element.getIcon(), diagnostics, context);
	}

	public void validate(RepresentationKind representation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		validateIcon(representation, representation.getGrayedIcon(), diagnostics, context);
	}

	private void validateIcon(EObject owner, String iconURI, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (iconURI == null) {
			// Okay
			return;
		}

		URI uri = null;

		try {
			uri = URI.createURI(iconURI);
		} catch (Exception e) {
			diagnostics.add(createDiagnostic(Diagnostic.ERROR, owner, format(Messages.ArchitectureCustomValidator_0, context, owner, iconURI)));
		}

		if (uri != null) {
			ResourceSet rset = owner.eResource().getResourceSet();
			if (!rset.getURIConverter().exists(uri, null)) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, owner, format(Messages.ArchitectureCustomValidator_1, context, owner, uri.lastSegment())));
			}
		}
	}

	public void validate(ArchitectureContext architecture, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (architecture.getId() != null) { // Missing ID is reported separately
			architecture.getElementTypes().forEach(typeSet -> validateElementTypesContextID(architecture, typeSet, diagnostics, context));
		}
	}

	private void validateElementTypesContextID(ArchitectureContext architecture, ElementTypeSetConfiguration typeSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		Collection<IPluginElement> registrations = getExtensionElements(IElementTypeSetExtensionPoint.EXTENSION_POINT_ID, ELEM_ELEMENT_TYPE_SET)
				.filter(resourcePathIs(IElementTypeSetExtensionPoint.PATH, typeSet.eResource().getURI()))
				.collect(Collectors.toList());

		if (!registrations.isEmpty() && registrations.stream().noneMatch(hasAttribute(IElementTypeSetExtensionPoint.CLIENT_CONTEXT_ID, architecture.getId()::equals))) {
			diagnostics.add(createDiagnostic(Diagnostic.WARNING, architecture, format(Messages.ArchitectureCustomValidator_2, context, typeSet)));
		}
	}

}
