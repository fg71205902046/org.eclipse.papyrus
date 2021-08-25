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

import java.util.function.Predicate;

import org.eclipse.papyrus.infra.tools.util.Iterators2;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.util.Evaluator;

/**
 * <!-- begin-user-doc -->
 * A static utility class that provides operations related to '<em><b>Extension</b></em>' model objects.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following operations are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getResourceAttribute() <em>Get Resource Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExtensionOperations {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ExtensionOperations() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public static ExtensionAttribute getResourceAttribute(Extension extension) {
		Predicate<ExtensionAttribute> isResource = attribute -> {
			Value value = attribute.getParsedValue();
			return value instanceof Variable && Evaluator.RESOURCE.equals(((Variable) value).getName());
		};

		ExtensionAttribute result = Iterators2.stream(Iterators2.filter(extension.eAllContents(), ExtensionAttribute.class))
				.filter(isResource)
				.findAny()
				.orElse(null);

		return result;
	}

} // ExtensionOperations
