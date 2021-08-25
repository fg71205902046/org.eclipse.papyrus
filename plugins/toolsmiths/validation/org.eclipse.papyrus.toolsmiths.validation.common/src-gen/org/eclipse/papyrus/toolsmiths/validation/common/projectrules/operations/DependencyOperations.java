/**
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
 */
package org.eclipse.papyrus.toolsmiths.validation.common.projectrules.operations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;

/**
 * <!-- begin-user-doc -->
 * A static utility class that provides operations related to '<em><b>Dependency</b></em>' model objects.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following operations are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#isRequired() <em>Is Required</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#setRequired(boolean) <em>Set Required</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DependencyOperations {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected DependencyOperations() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public static boolean isRequired(Dependency dependency) {
		EObject owner = dependency.eContainer();
		return owner instanceof ProjectDescription && ((ProjectDescription) owner).getRequiredDependencies().contains(dependency);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public static void setRequired(Dependency dependency, boolean newRequired) {
		EObject owner = dependency.eContainer();
		if (owner instanceof ProjectDescription && newRequired != dependency.isRequired()) {
			if (newRequired) {
				((ProjectDescription) owner).getRequiredDependencies().add(dependency);
			} else {
				((ProjectDescription) owner).getRequiredDependencies().remove(dependency);
			}
		}
	}

} // DependencyOperations
