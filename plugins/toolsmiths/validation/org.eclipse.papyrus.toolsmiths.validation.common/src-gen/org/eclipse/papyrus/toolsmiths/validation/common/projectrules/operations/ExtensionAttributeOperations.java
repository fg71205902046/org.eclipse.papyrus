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

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;

/**
 * <!-- begin-user-doc -->
 * A static utility class that provides operations related to '<em><b>Extension Attribute</b></em>' model objects.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following operations are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getParsedValue() <em>Get Parsed Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExtensionAttributeOperations extends ExtensionModelNodeOperations {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ExtensionAttributeOperations() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public static Value getParsedValue(ExtensionAttribute extensionAttribute) {
		Value result = extensionAttribute.getValue();

		if (result instanceof OpaqueValue) {
			result = ((OpaqueValue) result).parse();
		}

		return result;
	}

} // ExtensionAttributeOperations
