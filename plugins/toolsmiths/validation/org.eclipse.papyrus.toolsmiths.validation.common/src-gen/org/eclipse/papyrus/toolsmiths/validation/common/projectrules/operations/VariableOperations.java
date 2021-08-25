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

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable;

/**
 * <!-- begin-user-doc -->
 * A static utility class that provides operations related to '<em><b>Variable</b></em>' model objects.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following operations are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable#stringValue() <em>String Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariableOperations extends ValueOperations {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected VariableOperations() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public static String stringValue(Variable variable) {
		if (variable.getName() == null) {
			return "{}"; //$NON-NLS-1$
		}
		if (variable.eIsSet(ProjectRulesPackage.Literals.VARIABLE__OPTION)) {
			return String.format("{%s|%s}", variable.getName(), String.join("|", variable.getOptions())); //$NON-NLS-1$//$NON-NLS-2$
		}
		return String.format("{%s}", variable.getName()); //$NON-NLS-1$
	}

} // VariableOperations
