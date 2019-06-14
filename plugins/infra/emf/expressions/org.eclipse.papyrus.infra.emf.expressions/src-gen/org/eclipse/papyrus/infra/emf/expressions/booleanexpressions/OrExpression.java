/**
 * Copyright (c) 2017 CEA LIST.
 * 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 * 
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 */
package org.eclipse.papyrus.infra.emf.expressions.booleanexpressions;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Or Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Make an OR between all owned and referenced IBooleanExpressions.
 * If ownedExpressions.size()==0 and referencedExpressions.size()==0, we return FALSE
 * otherwize we return the OR between all owned and referenced expressions
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.emf.expressions.booleanexpressions.OrExpression#getOwnedExpressions <em>Owned Expressions</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.emf.expressions.booleanexpressions.OrExpression#getReferencedExpressions <em>Referenced Expressions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.infra.emf.expressions.booleanexpressions.BooleanExpressionsPackage#getOrExpression()
 * @model
 * @generated
 */
public interface OrExpression extends IBooleanEObjectExpression {
	/**
	 * Returns the value of the '<em><b>Owned Expressions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.infra.emf.expressions.booleanexpressions.IBooleanEObjectExpression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Expressions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Expressions</em>' containment reference list.
	 * @see org.eclipse.papyrus.infra.emf.expressions.booleanexpressions.BooleanExpressionsPackage#getOrExpression_OwnedExpressions()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<IBooleanEObjectExpression> getOwnedExpressions();

	/**
	 * Returns the value of the '<em><b>Referenced Expressions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.infra.emf.expressions.booleanexpressions.IBooleanEObjectExpression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Expressions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Expressions</em>' reference list.
	 * @see org.eclipse.papyrus.infra.emf.expressions.booleanexpressions.BooleanExpressionsPackage#getOrExpression_ReferencedExpressions()
	 * @model ordered="false"
	 * @generated
	 */
	EList<IBooleanEObjectExpression> getReferencedExpressions();

} // OrExpression
