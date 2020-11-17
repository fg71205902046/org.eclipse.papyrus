/**
 * Copyright (c) 2014, 2020 CEA LIST, Christian W. Damus, and others.
 * 
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
 *  Christian W. Damus - bug 568853
 */
package org.eclipse.papyrus.infra.types.rulebased.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.papyrus.infra.types.rulebased.NotRuleConfiguration;
import org.eclipse.papyrus.infra.types.rulebased.RuleBasedPackage;
import org.eclipse.papyrus.infra.types.rulebased.RuleConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Not Rule Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.types.rulebased.impl.NotRuleConfigurationImpl#getComposedRule <em>Composed Rule</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NotRuleConfigurationImpl extends RuleConfigurationImpl implements NotRuleConfiguration {
	/**
	 * The cached value of the '{@link #getComposedRule() <em>Composed Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComposedRule()
	 * @generated
	 * @ordered
	 */
	protected RuleConfiguration composedRule;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NotRuleConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RuleBasedPackage.Literals.NOT_RULE_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RuleConfiguration getComposedRule() {
		if (composedRule != null && composedRule.eIsProxy()) {
			InternalEObject oldComposedRule = (InternalEObject)composedRule;
			composedRule = (RuleConfiguration)eResolveProxy(oldComposedRule);
			if (composedRule != oldComposedRule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuleBasedPackage.NOT_RULE_CONFIGURATION__COMPOSED_RULE, oldComposedRule, composedRule));
			}
		}
		return composedRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleConfiguration basicGetComposedRule() {
		return composedRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setComposedRule(RuleConfiguration newComposedRule) {
		RuleConfiguration oldComposedRule = composedRule;
		composedRule = newComposedRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RuleBasedPackage.NOT_RULE_CONFIGURATION__COMPOSED_RULE, oldComposedRule, composedRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RuleBasedPackage.NOT_RULE_CONFIGURATION__COMPOSED_RULE:
				if (resolve) return getComposedRule();
				return basicGetComposedRule();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RuleBasedPackage.NOT_RULE_CONFIGURATION__COMPOSED_RULE:
				setComposedRule((RuleConfiguration)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RuleBasedPackage.NOT_RULE_CONFIGURATION__COMPOSED_RULE:
				setComposedRule((RuleConfiguration)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RuleBasedPackage.NOT_RULE_CONFIGURATION__COMPOSED_RULE:
				return composedRule != null;
		}
		return super.eIsSet(featureID);
	}

} //NotRuleConfigurationImpl
