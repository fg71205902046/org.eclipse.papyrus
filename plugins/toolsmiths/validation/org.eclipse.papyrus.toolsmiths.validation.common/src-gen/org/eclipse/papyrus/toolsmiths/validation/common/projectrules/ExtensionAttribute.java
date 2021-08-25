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

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getValue <em>Value</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getParsedValue <em>Parsed Value</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getElement <em>Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtensionAttribute()
 * @model
 * @generated
 */
public interface ExtensionAttribute extends ExtensionModelNode {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(Value)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtensionAttribute_Value()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Value getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Value value);

	/**
	 * Creates a new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value} and sets the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param eClass
	 *                   The Ecore class of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value} to create.
	 * @return The new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value}.
	 * @see #getValue()
	 * @generated
	 */
	Value createValue(EClass eClass);

	/**
	 * Returns the value of the '<em><b>Parsed Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * My {@linkplain #getValue() value} parsed as an expression if it is modeled as an {@link OpaqueValue}, otherwise just my {@code value} as it is.
	 * <!-- end-model-doc -->
	 *
	 * @return the value of the '<em>Parsed Value</em>' reference.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtensionAttribute_ParsedValue()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	Value getParsedValue();

	/**
	 * Returns the value of the '<em><b>Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getAttributes <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Element</em>' container reference.
	 * @see #setElement(ExtensionElement)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExtensionAttribute_Element()
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getAttributes
	 * @model opposite="attribute" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ExtensionElement getElement();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getElement <em>Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Element</em>' container reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(ExtensionElement value);

} // ExtensionAttribute
