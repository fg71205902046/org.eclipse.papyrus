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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Opaque Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An opaque value primarily used for user input of complex expressions as text. At run-time, opaque values are parsed to generate structured expressions for evaluation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#getText <em>Text</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getOpaqueValue()
 * @model annotation="duplicates"
 * @generated
 */
public interface OpaqueValue extends Value {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getOpaqueValue_Text()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *                  the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Parse my opaque text as a structured value expression.
	 * <!-- end-model-doc -->
	 *
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Value parse();

} // OpaqueValue
