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
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getExtensionPoint <em>Extension Point</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getElements <em>Element</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getResourceAttribute <em>Resource Attribute</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getArchitectureReference <em>Architecture Reference</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getProject <em>Project</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtension()
 * @model
 * @generated
 */
public interface Extension extends EObject {
	/**
	 * Returns the value of the '<em><b>Extension Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension Point</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Extension Point</em>' attribute.
	 * @see #setExtensionPoint(String)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtension_ExtensionPoint()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getExtensionPoint();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getExtensionPoint <em>Extension Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Extension Point</em>' attribute.
	 * @see #getExtensionPoint()
	 * @generated
	 */
	void setExtensionPoint(String value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Element</em>' containment reference list.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtension_Element()
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getExtension
	 * @model opposite="extension" containment="true" required="true" ordered="false"
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
	 * Returns the value of the '<em><b>Resource Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The attribute in an extension element that references the model resource to be registered. Some element in the extension description must have such an attribute in order for the extension to be matched to a tooling model and validated.
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Resource Attribute</em>' reference.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtension_ResourceAttribute()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	ExtensionAttribute getResourceAttribute();

	/**
	 * Returns the value of the '<em><b>Architecture Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Architecture Reference</em>' reference.
	 * @see #setArchitectureReference(EReference)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtension_ArchitectureReference()
	 * @model ordered="false"
	 * @generated
	 */
	EReference getArchitectureReference();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getArchitectureReference <em>Architecture Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Architecture Reference</em>' reference.
	 * @see #getArchitectureReference()
	 * @generated
	 */
	void setArchitectureReference(EReference value);

	/**
	 * Returns the value of the '<em><b>Project</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getExtensions <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Project</em>' container reference.
	 * @see #setProject(ProjectDescription)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtension_Project()
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getExtensions
	 * @model opposite="extension" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ProjectDescription getProject();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getProject <em>Project</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Project</em>' container reference.
	 * @see #getProject()
	 * @generated
	 */
	void setProject(ProjectDescription value);

} // Extension
