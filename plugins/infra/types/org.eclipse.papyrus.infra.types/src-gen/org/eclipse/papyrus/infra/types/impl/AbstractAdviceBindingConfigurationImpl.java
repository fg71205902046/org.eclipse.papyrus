/**
 * Copyright (c) 2014 CEA LIST.
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
 */
package org.eclipse.papyrus.infra.types.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration;
import org.eclipse.papyrus.infra.types.AbstractMatcherConfiguration;
import org.eclipse.papyrus.infra.types.ContainerConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypeConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage;
import org.eclipse.papyrus.infra.types.IdentifiedConfiguration;
import org.eclipse.papyrus.infra.types.InheritanceKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Advice Binding Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.AbstractAdviceBindingConfigurationImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.AbstractAdviceBindingConfigurationImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.AbstractAdviceBindingConfigurationImpl#getContainerConfiguration <em>Container Configuration</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.AbstractAdviceBindingConfigurationImpl#getMatcherConfiguration <em>Matcher Configuration</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.AbstractAdviceBindingConfigurationImpl#getInheritance <em>Inheritance</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.AbstractAdviceBindingConfigurationImpl#isApplyToAllTypes <em>Apply To All Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractAdviceBindingConfigurationImpl extends AdviceConfigurationImpl implements AbstractAdviceBindingConfiguration {
	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String identifier = IDENTIFIER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected ElementTypeConfiguration target;

	/**
	 * The cached value of the '{@link #getContainerConfiguration() <em>Container Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainerConfiguration()
	 * @generated
	 * @ordered
	 */
	protected ContainerConfiguration containerConfiguration;

	/**
	 * The cached value of the '{@link #getMatcherConfiguration() <em>Matcher Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatcherConfiguration()
	 * @generated
	 * @ordered
	 */
	protected AbstractMatcherConfiguration matcherConfiguration;

	/**
	 * The default value of the '{@link #getInheritance() <em>Inheritance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInheritance()
	 * @generated
	 * @ordered
	 */
	protected static final InheritanceKind INHERITANCE_EDEFAULT = InheritanceKind.NONE;

	/**
	 * The cached value of the '{@link #getInheritance() <em>Inheritance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInheritance()
	 * @generated
	 * @ordered
	 */
	protected InheritanceKind inheritance = INHERITANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #isApplyToAllTypes() <em>Apply To All Types</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isApplyToAllTypes()
	 * @generated
	 * @ordered
	 */
	protected static final boolean APPLY_TO_ALL_TYPES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isApplyToAllTypes() <em>Apply To All Types</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isApplyToAllTypes()
	 * @generated
	 * @ordered
	 */
	protected boolean applyToAllTypes = APPLY_TO_ALL_TYPES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractAdviceBindingConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIdentifier(String newIdentifier) {
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__IDENTIFIER, oldIdentifier, identifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ElementTypeConfiguration getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (ElementTypeConfiguration)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementTypeConfiguration basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(ElementTypeConfiguration newTarget) {
		ElementTypeConfiguration oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContainerConfiguration getContainerConfiguration() {
		return containerConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainerConfiguration(ContainerConfiguration newContainerConfiguration, NotificationChain msgs) {
		ContainerConfiguration oldContainerConfiguration = containerConfiguration;
		containerConfiguration = newContainerConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION, oldContainerConfiguration, newContainerConfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainerConfiguration(ContainerConfiguration newContainerConfiguration) {
		if (newContainerConfiguration != containerConfiguration) {
			NotificationChain msgs = null;
			if (containerConfiguration != null)
				msgs = ((InternalEObject)containerConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION, null, msgs);
			if (newContainerConfiguration != null)
				msgs = ((InternalEObject)newContainerConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION, null, msgs);
			msgs = basicSetContainerConfiguration(newContainerConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION, newContainerConfiguration, newContainerConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractMatcherConfiguration getMatcherConfiguration() {
		return matcherConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMatcherConfiguration(AbstractMatcherConfiguration newMatcherConfiguration, NotificationChain msgs) {
		AbstractMatcherConfiguration oldMatcherConfiguration = matcherConfiguration;
		matcherConfiguration = newMatcherConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION, oldMatcherConfiguration, newMatcherConfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMatcherConfiguration(AbstractMatcherConfiguration newMatcherConfiguration) {
		if (newMatcherConfiguration != matcherConfiguration) {
			NotificationChain msgs = null;
			if (matcherConfiguration != null)
				msgs = ((InternalEObject)matcherConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION, null, msgs);
			if (newMatcherConfiguration != null)
				msgs = ((InternalEObject)newMatcherConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION, null, msgs);
			msgs = basicSetMatcherConfiguration(newMatcherConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION, newMatcherConfiguration, newMatcherConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InheritanceKind getInheritance() {
		return inheritance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInheritance(InheritanceKind newInheritance) {
		InheritanceKind oldInheritance = inheritance;
		inheritance = newInheritance == null ? INHERITANCE_EDEFAULT : newInheritance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__INHERITANCE, oldInheritance, inheritance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isApplyToAllTypes() {
		return applyToAllTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setApplyToAllTypes(boolean newApplyToAllTypes) {
		boolean oldApplyToAllTypes = applyToAllTypes;
		applyToAllTypes = newApplyToAllTypes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__APPLY_TO_ALL_TYPES, oldApplyToAllTypes, applyToAllTypes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION:
				return basicSetContainerConfiguration(null, msgs);
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION:
				return basicSetMatcherConfiguration(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__IDENTIFIER:
				return getIdentifier();
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION:
				return getContainerConfiguration();
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION:
				return getMatcherConfiguration();
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__INHERITANCE:
				return getInheritance();
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__APPLY_TO_ALL_TYPES:
				return isApplyToAllTypes();
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
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__IDENTIFIER:
				setIdentifier((String)newValue);
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET:
				setTarget((ElementTypeConfiguration)newValue);
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION:
				setContainerConfiguration((ContainerConfiguration)newValue);
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION:
				setMatcherConfiguration((AbstractMatcherConfiguration)newValue);
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__INHERITANCE:
				setInheritance((InheritanceKind)newValue);
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__APPLY_TO_ALL_TYPES:
				setApplyToAllTypes((Boolean)newValue);
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
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__IDENTIFIER:
				setIdentifier(IDENTIFIER_EDEFAULT);
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET:
				setTarget((ElementTypeConfiguration)null);
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION:
				setContainerConfiguration((ContainerConfiguration)null);
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION:
				setMatcherConfiguration((AbstractMatcherConfiguration)null);
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__INHERITANCE:
				setInheritance(INHERITANCE_EDEFAULT);
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__APPLY_TO_ALL_TYPES:
				setApplyToAllTypes(APPLY_TO_ALL_TYPES_EDEFAULT);
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
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__IDENTIFIER:
				return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET:
				return target != null;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION:
				return containerConfiguration != null;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION:
				return matcherConfiguration != null;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__INHERITANCE:
				return inheritance != INHERITANCE_EDEFAULT;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__APPLY_TO_ALL_TYPES:
				return applyToAllTypes != APPLY_TO_ALL_TYPES_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IdentifiedConfiguration.class) {
			switch (derivedFeatureID) {
				case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__IDENTIFIER: return ElementTypesConfigurationsPackage.IDENTIFIED_CONFIGURATION__IDENTIFIER;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IdentifiedConfiguration.class) {
			switch (baseFeatureID) {
				case ElementTypesConfigurationsPackage.IDENTIFIED_CONFIGURATION__IDENTIFIER: return ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__IDENTIFIER;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (identifier: ");
		result.append(identifier);
		result.append(", inheritance: ");
		result.append(inheritance);
		result.append(", applyToAllTypes: ");
		result.append(applyToAllTypes);
		result.append(')');
		return result.toString();
	}

} //AbstractAdviceBindingConfigurationImpl
