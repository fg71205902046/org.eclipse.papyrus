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

import org.eclipse.emf.ecore.EObject;

import org.osgi.framework.Version;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getKind <em>Kind</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#isRequired <em>Required</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getLowerBound <em>Lower Bound</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getDependency()
 * @model
 * @generated
 */
public interface Dependency extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getDependency_Name()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The default value is <code>"requireBundle"</code>.
	 * The literals are from the enumeration {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.DependencyKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.DependencyKind
	 * @see #setKind(DependencyKind)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getDependency_Kind()
	 * @model default="requireBundle" required="true" ordered="false"
	 * @generated
	 */
	DependencyKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.DependencyKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(DependencyKind value);

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #setRequired(boolean)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getDependency_Required()
	 * @model dataType="org.eclipse.uml2.types.Boolean" required="true" transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Required</em>' attribute.
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(Version)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getDependency_LowerBound()
	 * @model dataType="org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Version" ordered="false"
	 * @generated
	 */
	Version getLowerBound();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getLowerBound <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(Version value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #isSetUpperBound()
	 * @see #unsetUpperBound()
	 * @see #setUpperBound(Version)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getDependency_UpperBound()
	 * @model unsettable="true" dataType="org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Version" ordered="false"
	 * @generated
	 */
	Version getUpperBound();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #isSetUpperBound()
	 * @see #unsetUpperBound()
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(Version value);

	/**
	 * Unsets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #isSetUpperBound()
	 * @see #getUpperBound()
	 * @see #setUpperBound(Version)
	 * @generated
	 */
	void unsetUpperBound();

	/**
	 * Returns whether the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getUpperBound <em>Upper Bound</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return whether the value of the '<em>Upper Bound</em>' attribute is set.
	 * @see #unsetUpperBound()
	 * @see #getUpperBound()
	 * @see #setUpperBound(Version)
	 * @generated
	 */
	boolean isSetUpperBound();

} // Dependency
