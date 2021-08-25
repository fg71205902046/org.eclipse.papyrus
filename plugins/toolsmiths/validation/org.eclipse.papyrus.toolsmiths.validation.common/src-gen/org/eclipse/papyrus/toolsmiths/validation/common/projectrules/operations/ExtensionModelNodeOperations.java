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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionModelNode;

/**
 * <!-- begin-user-doc -->
 * A static utility class that provides operations related to '<em><b>Extension Model Node</b></em>' model objects.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following operations are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionModelNode#containingExtension() <em>Containing Extension</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExtensionModelNodeOperations {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ExtensionModelNodeOperations() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public static Extension containingExtension(ExtensionModelNode extensionModelNode) {
		Extension result = null;

		for (EObject object = extensionModelNode; result == null && object != null; object = object.eContainer()) {
			if (object instanceof Extension) {
				result = (Extension) object;
			}
		}

		return result;
	}

} // ExtensionModelNodeOperations