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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.infra.types.rulebased.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RuleBasedFactoryImpl extends EFactoryImpl implements RuleBasedFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RuleBasedFactory init() {
		try {
			RuleBasedFactory theRuleBasedFactory = (RuleBasedFactory)EPackage.Registry.INSTANCE.getEFactory(RuleBasedPackage.eNS_URI);
			if (theRuleBasedFactory != null) {
				return theRuleBasedFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RuleBasedFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleBasedFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RuleBasedPackage.RULE_BASED_TYPE_CONFIGURATION: return createRuleBasedTypeConfiguration();
			case RuleBasedPackage.NOT_RULE_CONFIGURATION: return createNotRuleConfiguration();
			case RuleBasedPackage.AND_RULE_CONFIGURATION: return createAndRuleConfiguration();
			case RuleBasedPackage.OR_RULE_CONFIGURATION: return createOrRuleConfiguration();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleBasedTypeConfiguration createRuleBasedTypeConfiguration() {
		RuleBasedTypeConfigurationImpl ruleBasedTypeConfiguration = new RuleBasedTypeConfigurationImpl();
		return ruleBasedTypeConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotRuleConfiguration createNotRuleConfiguration() {
		NotRuleConfigurationImpl notRuleConfiguration = new NotRuleConfigurationImpl();
		return notRuleConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AndRuleConfiguration createAndRuleConfiguration() {
		AndRuleConfigurationImpl andRuleConfiguration = new AndRuleConfigurationImpl();
		return andRuleConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrRuleConfiguration createOrRuleConfiguration() {
		OrRuleConfigurationImpl orRuleConfiguration = new OrRuleConfigurationImpl();
		return orRuleConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuleBasedPackage getRuleBasedPackage() {
		return (RuleBasedPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RuleBasedPackage getPackage() {
		return RuleBasedPackage.eINSTANCE;
	}

} //RuleBasedFactoryImpl
