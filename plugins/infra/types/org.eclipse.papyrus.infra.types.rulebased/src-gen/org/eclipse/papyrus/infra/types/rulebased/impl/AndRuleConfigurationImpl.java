/**
 * Copyright (c) 2014 CEA LIST.
 * 
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  CEA LIST - Initial API and implementation
 */
package org.eclipse.papyrus.infra.types.rulebased.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.papyrus.infra.types.rulebased.AndRuleConfiguration;
import org.eclipse.papyrus.infra.types.rulebased.RuleBasedPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>And Rule Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class AndRuleConfigurationImpl extends CompositeRuleConfigurationImpl implements AndRuleConfiguration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AndRuleConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RuleBasedPackage.Literals.AND_RULE_CONFIGURATION;
	}

} //AndRuleConfigurationImpl
