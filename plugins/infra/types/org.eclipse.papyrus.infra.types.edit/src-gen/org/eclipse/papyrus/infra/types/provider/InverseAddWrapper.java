/*****************************************************************************
 * Copyright (c) 2020 Christian W. Damus, CEA LIST, and others.
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
 *****************************************************************************/

package org.eclipse.papyrus.infra.types.provider;

/**
 * A wrapper around an object being set into a container reference to indicate that
 * it is being set to invert an add on the containment end.
 */
final class InverseAddWrapper {

	private final Object value;

	InverseAddWrapper(Object value) {
		super();

		this.value = value;
	}

	static boolean isInverseAdd(Object value) {
		return value instanceof InverseAddWrapper;
	}

	static Object unwrap(Object value) {
		return isInverseAdd(value) ? ((InverseAddWrapper) value).value : value;
	}

}
