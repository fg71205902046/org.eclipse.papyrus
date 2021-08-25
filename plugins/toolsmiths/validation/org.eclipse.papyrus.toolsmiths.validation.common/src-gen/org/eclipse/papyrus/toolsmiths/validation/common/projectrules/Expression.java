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

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A complex value that combines literal and variable text together.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Expression#getValues <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExpression()
 * @model annotation="duplicates"
 * @generated
 */
public interface Expression extends Value {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Value</em>' containment reference list.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage#getExpression_Value()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Value> getValues();

	/**
	 * Creates a new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value} and appends it to the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param eClass
	 *                   The Ecore class of the {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value} to create.
	 * @return The new {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value}.
	 * @see #getValues()
	 * @generated
	 */
	Value createValue(EClass eClass);

} // Expression
