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

import java.util.stream.Collectors;

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Expression;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;

/**
 * <!-- begin-user-doc -->
 * A static utility class that provides operations related to '<em><b>Expression</b></em>' model objects.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following operations are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Expression#stringValue() <em>String Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExpressionOperations extends ValueOperations {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ExpressionOperations() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public static String stringValue(Expression expression) {
		return "=" + expression.getValues().stream().map(Value::stringValue).collect(Collectors.joining()); //$NON-NLS-1$
	}

} // ExpressionOperations
