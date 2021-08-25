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
package org.eclipse.papyrus.toolsmiths.validation.common.projectrules;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Project Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getDependencies <em>Dependency</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getRequiredDependencies <em>Required Dependency</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getExtensions <em>Extension</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getProjectDescription()
 * @model
 * @generated
 */
public interface ProjectDescription extends EObject {
	/**
	 * Returns the value of the '<em><b>Dependency</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependency</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Dependency</em>' containment reference list.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getProjectDescription_Dependency()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Dependency> getDependencies();

	/**
	 * Creates a new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency}, with the specified '<em><b>Name</b></em>', and appends it to the '<em><b>Dependency</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                 The '<em><b>Name</b></em>' for the new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency}, or <code>null</code>.
	 * @return The new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency}.
	 * @see #getDependencies()
	 * @generated
	 */
	Dependency createDependency(String name);

	/**
	 * Retrieves the first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} with the specified '<em><b>Name</b></em>' from the '<em><b>Dependency</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                 The '<em><b>Name</b></em>' of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} to retrieve, or <code>null</code>.
	 * @return The first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getDependencies()
	 * @generated
	 */
	Dependency getDependency(String name);

	/**
	 * Retrieves the first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} with the specified '<em><b>Name</b></em>' from the '<em><b>Dependency</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                           The '<em><b>Name</b></em>' of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} to retrieve, or <code>null</code>.
	 * @param ignoreCase
	 *                           Whether to ignore case in {@link java.lang.String} comparisons.
	 * @param createOnDemand
	 *                           Whether to create a {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} on demand if not found.
	 * @return The first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getDependencies()
	 * @generated
	 */
	Dependency getDependency(String name, boolean ignoreCase, boolean createOnDemand);

	/**
	 * Returns the value of the '<em><b>Required Dependency</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency}.
	 * <p>
	 * This feature subsets the following features:
	 * </p>
	 * <ul>
	 * <li>'{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getDependencies() <em>Dependency</em>}'</li>
	 * </ul>
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Dependency</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Required Dependency</em>' reference list.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getProjectDescription_RequiredDependency()
	 * @model ordered="false"
	 *        annotation="subsets"
	 * @generated
	 */
	EList<Dependency> getRequiredDependencies();

	/**
	 * Creates a new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency}, with the specified '<em><b>Name</b></em>', and appends it to the '<em><b>Required Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                 The '<em><b>Name</b></em>' for the new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency}, or <code>null</code>.
	 * @return The new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency}.
	 * @see #getRequiredDependencies()
	 * @generated
	 */
	Dependency createRequiredDependency(String name);

	/**
	 * Retrieves the first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} with the specified '<em><b>Name</b></em>' from the '<em><b>Required Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                 The '<em><b>Name</b></em>' of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} to retrieve, or <code>null</code>.
	 * @return The first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getRequiredDependencies()
	 * @generated
	 */
	Dependency getRequiredDependency(String name);

	/**
	 * Retrieves the first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} with the specified '<em><b>Name</b></em>' from the '<em><b>Required Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                           The '<em><b>Name</b></em>' of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} to retrieve, or <code>null</code>.
	 * @param ignoreCase
	 *                           Whether to ignore case in {@link java.lang.String} comparisons.
	 * @param createOnDemand
	 *                           Whether to create a {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} on demand if not found.
	 * @return The first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getRequiredDependencies()
	 * @generated
	 */
	Dependency getRequiredDependency(String name, boolean ignoreCase, boolean createOnDemand);

	/**
	 * Returns the value of the '<em><b>Extension</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Extension</em>' containment reference list.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getProjectDescription_Extension()
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getProject
	 * @model opposite="project" containment="true" ordered="false"
	 * @generated
	 */
	EList<Extension> getExtensions();

	/**
	 * Creates a new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension}, with the specified '<em><b>Extension Point</b></em>', and appends it to the '<em><b>Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param extensionPoint
	 *                           The '<em><b>Extension Point</b></em>' for the new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension}, or <code>null</code>.
	 * @return The new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension}.
	 * @see #getExtensions()
	 * @generated
	 */
	Extension createExtension(String extensionPoint);

	/**
	 * Retrieves the first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension} with the specified '<em><b>Extension Point</b></em>' from the '<em><b>Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param extensionPoint
	 *                           The '<em><b>Extension Point</b></em>' of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension} to retrieve, or <code>null</code>.
	 * @return The first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension} with the specified '<em><b>Extension Point</b></em>', or <code>null</code>.
	 * @see #getExtensions()
	 * @generated
	 */
	Extension getExtension(String extensionPoint);

	/**
	 * Retrieves the first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension} with the specified '<em><b>Extension Point</b></em>' from the '<em><b>Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param extensionPoint
	 *                           The '<em><b>Extension Point</b></em>' of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension} to retrieve, or <code>null</code>.
	 * @param ignoreCase
	 *                           Whether to ignore case in {@link java.lang.String} comparisons.
	 * @param createOnDemand
	 *                           Whether to create a {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension} on demand if not found.
	 * @return The first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension} with the specified '<em><b>Extension Point</b></em>', or <code>null</code>.
	 * @see #getExtensions()
	 * @generated
	 */
	Extension getExtension(String extensionPoint, boolean ignoreCase, boolean createOnDemand);

} // ProjectDescription
