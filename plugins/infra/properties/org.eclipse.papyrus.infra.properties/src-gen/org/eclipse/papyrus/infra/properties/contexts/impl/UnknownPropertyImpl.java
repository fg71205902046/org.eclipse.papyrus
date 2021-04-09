/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.properties.contexts.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.papyrus.infra.properties.contexts.ContextsPackage;
import org.eclipse.papyrus.infra.properties.contexts.UnknownProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unknown Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class UnknownPropertyImpl extends PropertyImpl implements UnknownProperty {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnknownPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ContextsPackage.Literals.UNKNOWN_PROPERTY;
	}

} // UnknownPropertyImpl
