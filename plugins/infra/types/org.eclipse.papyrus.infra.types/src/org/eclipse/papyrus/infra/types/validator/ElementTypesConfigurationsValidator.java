/*****************************************************************************
 * Copyright (c) 2020 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   Christian W. Damus - bug 568782
 *
 *****************************************************************************/

package org.eclipse.papyrus.infra.types.validator;

import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.emf.validation.AbstractEObjectDependencyValidator;
import org.eclipse.papyrus.infra.types.ElementTypeSetConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage;
import org.eclipse.papyrus.infra.types.util.ElementTypesConfigurationsSwitch;

/**
 * validator for elements provided by {@link ElementTypesConfigurationsPackage}
 *
 */
public class ElementTypesConfigurationsValidator extends AbstractEObjectDependencyValidator {

	/**
	 * instance of this validator
	 */
	public static final ElementTypesConfigurationsValidator eINSTANCE = new ElementTypesConfigurationsValidator();

	private final ValidationSwitch validationSwitch = new ValidationSwitch();

	// private to prevent multiple instantiation
	private ElementTypesConfigurationsValidator() {
		super(ElementTypesConfigurationsPackage.eINSTANCE);
	}

	@Override
	protected boolean validate(int classifierID, Object object, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = super.validate(classifierID, object, diagnostics, context);

		// This is delegated by attribute value validation as well as object validation
		if ((result || diagnostics != null) && (object instanceof EObject)) {
			result = validationSwitch.doSwitch((EObject) object, diagnostics, context) && result;
		}

		return result;
	}

	/**
	 * @see org.eclipse.emf.ecore.util.EObjectValidator#getEPackage()
	 *
	 * @return
	 */
	@Override
	protected EPackage getEPackage() {
		return ElementTypesConfigurationsPackage.eINSTANCE;
	}

	/**
	 *
	 * @param elementTypeConf
	 * @param diagnostics
	 * @param context
	 * @return
	 */
	private boolean validateElementTypeSetConfiguration(final ElementTypeSetConfiguration elementTypeConf, DiagnosticChain diagnostics, Map<Object, Object> context) {
		final String metamodelURI = elementTypeConf.getMetamodelNsURI();
		if (metamodelURI != null) {
			final Resource resource = elementTypeConf.eResource();
			final String bundleName = RESOURCE_URI_HELPER.getBundleNameFromResource(resource);
			final String dependencyName = RESOURCE_URI_HELPER.getBundleNameFromNS_URI(metamodelURI);

			boolean isOK = PROJECT_DEPENDENCY_HELPER.hasDependency(bundleName, dependencyName);
			if (!isOK) {
				Diagnostic newDiag = createMissingDependenciesDiagnostic(elementTypeConf, ElementTypesConfigurationsPackage.eINSTANCE.getElementTypeSetConfiguration_MetamodelNsURI(), Collections.singletonList(dependencyName));
				diagnostics.add(newDiag);
			}
			return isOK;
		}
		return false;
	}

	//
	// Nested types
	//

	private final class ValidationSwitch extends ElementTypesConfigurationsSwitch<Boolean> {

		private DiagnosticChain diagnostics;
		private Map<Object, Object> context;

		@Override
		public Boolean caseElementTypeSetConfiguration(ElementTypeSetConfiguration object) {
			return validateElementTypeSetConfiguration(object, diagnostics, context);
		}

		@Override
		public Boolean defaultCase(EObject object) {
			return true;
		}

		boolean doSwitch(EObject object, DiagnosticChain diagnostics, Map<Object, Object> context) {
			this.diagnostics = diagnostics;
			this.context = context;

			try {
				return doSwitch(object);
			} finally {
				this.diagnostics = null;
				this.context = null;
			}
		}

	}
}
