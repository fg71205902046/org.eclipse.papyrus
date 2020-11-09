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

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.papyrus.infra.types.AbstractEditHelperAdviceConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage;
import org.eclipse.papyrus.infra.types.SpecializationTypeConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Edit Helper Advice Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.AbstractEditHelperAdviceConfigurationImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractEditHelperAdviceConfigurationImpl extends AdviceConfigurationImpl implements AbstractEditHelperAdviceConfiguration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractEditHelperAdviceConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ElementTypesConfigurationsPackage.Literals.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpecializationTypeConfiguration getTarget() {
		if (eContainerFeatureID() != ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET) return null;
		return (SpecializationTypeConfiguration)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(SpecializationTypeConfiguration newTarget, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTarget, ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(SpecializationTypeConfiguration newTarget) {
		if (newTarget != eInternalContainer() || (eContainerFeatureID() != ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET && newTarget != null)) {
			if (EcoreUtil.isAncestor(this, newTarget))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION, SpecializationTypeConfiguration.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTarget((SpecializationTypeConfiguration)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET:
				return basicSetTarget(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET:
				return eInternalContainer().eInverseRemove(this, ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION, SpecializationTypeConfiguration.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET:
				return getTarget();
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
			case ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET:
				setTarget((SpecializationTypeConfiguration)newValue);
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
			case ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET:
				setTarget((SpecializationTypeConfiguration)null);
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
			case ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET:
				return getTarget() != null;
		}
		return super.eIsSet(featureID);
	}

} //AbstractEditHelperAdviceConfigurationImpl
