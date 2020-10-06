/*****************************************************************************
 * Copyright (c) 2020 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.emf.validation;

import java.util.Collection;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.papyrus.emf.helpers.BundleResourceURIHelper;
import org.eclipse.papyrus.emf.helpers.ProjectDependencyHelper;


/**
 * Abstract validator for dependencies of an Ecore model
 */
public abstract class AbstractEObjectDependencyValidator extends EObjectValidator {

	/**
	 * helper used to get bundle name from a {@link Resource}
	 */
	protected static final BundleResourceURIHelper RESOURCE_URI_HELPER = BundleResourceURIHelper.INSTANCE;

	/**
	 * helper used to know if a project is dependant of another one
	 */
	protected static final ProjectDependencyHelper PROJECT_DEPENDENCY_HELPER = ProjectDependencyHelper.INSTANCE;

	/**
	 *
	 * @param eobjectInError
	 *            the eobject in error
	 * @param feature
	 *            the tested feature
	 * @param missingDependencies
	 *            the list of missing dependencies
	 * @return
	 *         the created diagnostic
	 */
	protected final Diagnostic createMissingDependenciesDiagnostic(final EObject eobjectInError, final EStructuralFeature feature, final Collection<String> missingDependencies) {
		return DependencyValidationUtils.createMissingDependenciesDiagnostic(eobjectInError, feature, missingDependencies);
	}

}
