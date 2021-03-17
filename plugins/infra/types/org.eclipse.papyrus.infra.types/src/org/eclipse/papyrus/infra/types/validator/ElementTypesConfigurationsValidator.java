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

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.papyrus.emf.validation.AbstractEObjectDependencyValidator;
import org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypeSetConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage;

/**
 * validator for elements provided by {@link ElementTypesConfigurationsPackage}
 *
 */
public class ElementTypesConfigurationsValidator extends AbstractEObjectDependencyValidator {

	/**
	 * instance of this validator
	 */
	public static final ElementTypesConfigurationsValidator eINSTANCE = new ElementTypesConfigurationsValidator();

	// private to prevent multiple instantiation
	private ElementTypesConfigurationsValidator() {
		super(ElementTypesConfigurationsPackage.eINSTANCE);
	}

	/**
	 * @see org.eclipse.emf.ecore.util.EObjectValidator#validate(int, java.lang.Object, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 *
	 * @param classifierID
	 * @param object
	 * @param diagnostics
	 * @param context
	 * @return
	 */
	@Override
	protected boolean validate(int classifierID, Object object, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = super.validate(classifierID, object, diagnostics, context);
		switch (classifierID) {
		case ElementTypesConfigurationsPackage.ELEMENT_TYPE_SET_CONFIGURATION:
			result = result && validateElementTypeSetConfiguration((ElementTypeSetConfiguration) object, diagnostics, context);
			break;
		case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION:
			result = result & validateTypeReference((AbstractAdviceBindingConfiguration) object, diagnostics, context);
			break;
		default:
			// nothing to do
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
	 * @param object
	 * @param diagnostics
	 * @param context
	 * @return
	 */
	protected boolean validateTypeReference(AbstractAdviceBindingConfiguration object, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (object.isApplyToAllTypes() && object.getTarget() != null) {
			// Target will be ignored and should be null
			String message = "applyToAllTypes is true, but the AdviceConfiguration has an ElementType target";
			BasicDiagnostic diag = new BasicDiagnostic(Diagnostic.ERROR, EObjectValidator.DIAGNOSTIC_SOURCE, 2, message,
					new Object[] { object });
			diagnostics.add(diag);
			return false;
		} else if (!object.isApplyToAllTypes() && object.getTarget() == null) {
			// Target was accidentally unset
			String message = "applyToAllTypes is false, but the AdviceConfiguration doesn't have an ElementType target.";
			BasicDiagnostic diag = new BasicDiagnostic(Diagnostic.ERROR, EObjectValidator.DIAGNOSTIC_SOURCE, 3, message,
					new Object[] { object });
			diagnostics.add(diag);
			return false;
		}
		return true;
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

	/**
	 * @see org.eclipse.emf.ecore.util.EObjectValidator#validate(org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EObject, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 *
	 * @param eClass
	 * @param eObject
	 * @param diagnostics
	 * @param context
	 * @return
	 */
	@Override
	public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = super.validate(eClass, eObject, diagnostics, context);
		if (eObject instanceof ElementTypeSetConfiguration) {
			result = result && validateElementTypeSetConfiguration((ElementTypeSetConfiguration) eObject, diagnostics, context);
		}
		return result;
	}

}
