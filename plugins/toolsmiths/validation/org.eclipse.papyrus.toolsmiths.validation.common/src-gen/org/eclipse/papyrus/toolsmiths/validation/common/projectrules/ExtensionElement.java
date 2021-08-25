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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getParent <em>Parent</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getElements <em>Element</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getAttributes <em>Attribute</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getExtension <em>Extension</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtensionElement()
 * @model
 * @generated
 */
public interface ExtensionElement extends ExtensionModelNode {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getElements <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(ExtensionElement)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtensionElement_Parent()
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getElements
	 * @model opposite="element" transient="false" ordered="false"
	 * @generated
	 */
	ExtensionElement getParent();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(ExtensionElement value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Element</em>' containment reference list.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtensionElement_Element()
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getParent
	 * @model opposite="parent" containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<ExtensionElement> getElements();

	/**
	 * Creates a new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement}, with the specified '<em><b>Name</b></em>', and appends it to the '<em><b>Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                 The '<em><b>Name</b></em>' for the new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement}, or <code>null</code>.
	 * @return The new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement}.
	 * @see #getElements()
	 * @generated
	 */
	ExtensionElement createElement(String name);

	/**
	 * Retrieves the first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement} with the specified '<em><b>Name</b></em>' from the '<em><b>Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                 The '<em><b>Name</b></em>' of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement} to retrieve, or <code>null</code>.
	 * @return The first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getElements()
	 * @generated
	 */
	ExtensionElement getElement(String name);

	/**
	 * Retrieves the first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement} with the specified '<em><b>Name</b></em>' from the '<em><b>Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                           The '<em><b>Name</b></em>' of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement} to retrieve, or <code>null</code>.
	 * @param ignoreCase
	 *                           Whether to ignore case in {@link java.lang.String} comparisons.
	 * @param createOnDemand
	 *                           Whether to create a {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement} on demand if not found.
	 * @return The first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getElements()
	 * @generated
	 */
	ExtensionElement getElement(String name, boolean ignoreCase, boolean createOnDemand);

	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Attribute</em>' containment reference list.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtensionElement_Attribute()
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getElement
	 * @model opposite="element" containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<ExtensionAttribute> getAttributes();

	/**
	 * Creates a new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute}, with the specified '<em><b>Name</b></em>', and appends it to the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                 The '<em><b>Name</b></em>' for the new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute}, or <code>null</code>.
	 * @return The new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute}.
	 * @see #getAttributes()
	 * @generated
	 */
	ExtensionAttribute createAttribute(String name);

	/**
	 * Retrieves the first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute} with the specified '<em><b>Name</b></em>' from the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                 The '<em><b>Name</b></em>' of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute} to retrieve, or <code>null</code>.
	 * @return The first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getAttributes()
	 * @generated
	 */
	ExtensionAttribute getAttribute(String name);

	/**
	 * Retrieves the first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute} with the specified '<em><b>Name</b></em>' from the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param name
	 *                           The '<em><b>Name</b></em>' of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute} to retrieve, or <code>null</code>.
	 * @param ignoreCase
	 *                           Whether to ignore case in {@link java.lang.String} comparisons.
	 * @param createOnDemand
	 *                           Whether to create a {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute} on demand if not found.
	 * @return The first {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getAttributes()
	 * @generated
	 */
	ExtensionAttribute getAttribute(String name, boolean ignoreCase, boolean createOnDemand);

	/**
	 * Returns the value of the '<em><b>Extension</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getElements <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Extension</em>' container reference.
	 * @see #setExtension(Extension)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtensionElement_Extension()
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getElements
	 * @model opposite="element" transient="false" ordered="false"
	 * @generated
	 */
	Extension getExtension();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getExtension <em>Extension</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Extension</em>' container reference.
	 * @see #getExtension()
	 * @generated
	 */
	void setExtension(Extension value);

} // ExtensionElement
