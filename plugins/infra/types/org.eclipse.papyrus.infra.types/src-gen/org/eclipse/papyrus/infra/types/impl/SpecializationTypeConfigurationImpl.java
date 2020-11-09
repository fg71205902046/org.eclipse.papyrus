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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.papyrus.infra.types.AbstractEditHelperAdviceConfiguration;
import org.eclipse.papyrus.infra.types.AbstractMatcherConfiguration;
import org.eclipse.papyrus.infra.types.ContainerConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypeConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage;
import org.eclipse.papyrus.infra.types.SpecializationTypeConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specialization Type Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.SpecializationTypeConfigurationImpl#getEditHelperAdviceConfiguration <em>Edit Helper Advice Configuration</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.SpecializationTypeConfigurationImpl#getContainerConfiguration <em>Container Configuration</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.SpecializationTypeConfigurationImpl#getMatcherConfiguration <em>Matcher Configuration</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.impl.SpecializationTypeConfigurationImpl#getSpecializedTypes <em>Specialized Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SpecializationTypeConfigurationImpl extends ElementTypeConfigurationImpl implements SpecializationTypeConfiguration {
	/**
	 * The cached value of the '{@link #getEditHelperAdviceConfiguration() <em>Edit Helper Advice Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditHelperAdviceConfiguration()
	 * @generated
	 * @ordered
	 */
	protected AbstractEditHelperAdviceConfiguration editHelperAdviceConfiguration;

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
	 * The cached value of the '{@link #getSpecializedTypes() <em>Specialized Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecializedTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementTypeConfiguration> specializedTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecializationTypeConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ElementTypesConfigurationsPackage.Literals.SPECIALIZATION_TYPE_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractEditHelperAdviceConfiguration getEditHelperAdviceConfiguration() {
		return editHelperAdviceConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditHelperAdviceConfiguration(AbstractEditHelperAdviceConfiguration newEditHelperAdviceConfiguration, NotificationChain msgs) {
		AbstractEditHelperAdviceConfiguration oldEditHelperAdviceConfiguration = editHelperAdviceConfiguration;
		editHelperAdviceConfiguration = newEditHelperAdviceConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION, oldEditHelperAdviceConfiguration, newEditHelperAdviceConfiguration);
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
	public void setEditHelperAdviceConfiguration(AbstractEditHelperAdviceConfiguration newEditHelperAdviceConfiguration) {
		if (newEditHelperAdviceConfiguration != editHelperAdviceConfiguration) {
			NotificationChain msgs = null;
			if (editHelperAdviceConfiguration != null)
				msgs = ((InternalEObject)editHelperAdviceConfiguration).eInverseRemove(this, ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET, AbstractEditHelperAdviceConfiguration.class, msgs);
			if (newEditHelperAdviceConfiguration != null)
				msgs = ((InternalEObject)newEditHelperAdviceConfiguration).eInverseAdd(this, ElementTypesConfigurationsPackage.ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET, AbstractEditHelperAdviceConfiguration.class, msgs);
			msgs = basicSetEditHelperAdviceConfiguration(newEditHelperAdviceConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION, newEditHelperAdviceConfiguration, newEditHelperAdviceConfiguration));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__CONTAINER_CONFIGURATION, oldContainerConfiguration, newContainerConfiguration);
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
				msgs = ((InternalEObject)containerConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__CONTAINER_CONFIGURATION, null, msgs);
			if (newContainerConfiguration != null)
				msgs = ((InternalEObject)newContainerConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__CONTAINER_CONFIGURATION, null, msgs);
			msgs = basicSetContainerConfiguration(newContainerConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__CONTAINER_CONFIGURATION, newContainerConfiguration, newContainerConfiguration));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__MATCHER_CONFIGURATION, oldMatcherConfiguration, newMatcherConfiguration);
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
				msgs = ((InternalEObject)matcherConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__MATCHER_CONFIGURATION, null, msgs);
			if (newMatcherConfiguration != null)
				msgs = ((InternalEObject)newMatcherConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__MATCHER_CONFIGURATION, null, msgs);
			msgs = basicSetMatcherConfiguration(newMatcherConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__MATCHER_CONFIGURATION, newMatcherConfiguration, newMatcherConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ElementTypeConfiguration> getSpecializedTypes() {
		if (specializedTypes == null) {
			specializedTypes = new EObjectResolvingEList<ElementTypeConfiguration>(ElementTypeConfiguration.class, this, ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__SPECIALIZED_TYPES);
		}
		return specializedTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION:
				if (editHelperAdviceConfiguration != null)
					msgs = ((InternalEObject)editHelperAdviceConfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION, null, msgs);
				return basicSetEditHelperAdviceConfiguration((AbstractEditHelperAdviceConfiguration)otherEnd, msgs);
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
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION:
				return basicSetEditHelperAdviceConfiguration(null, msgs);
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__CONTAINER_CONFIGURATION:
				return basicSetContainerConfiguration(null, msgs);
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__MATCHER_CONFIGURATION:
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
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION:
				return getEditHelperAdviceConfiguration();
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__CONTAINER_CONFIGURATION:
				return getContainerConfiguration();
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__MATCHER_CONFIGURATION:
				return getMatcherConfiguration();
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__SPECIALIZED_TYPES:
				return getSpecializedTypes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION:
				setEditHelperAdviceConfiguration((AbstractEditHelperAdviceConfiguration)newValue);
				return;
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__CONTAINER_CONFIGURATION:
				setContainerConfiguration((ContainerConfiguration)newValue);
				return;
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__MATCHER_CONFIGURATION:
				setMatcherConfiguration((AbstractMatcherConfiguration)newValue);
				return;
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__SPECIALIZED_TYPES:
				getSpecializedTypes().clear();
				getSpecializedTypes().addAll((Collection<? extends ElementTypeConfiguration>)newValue);
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
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION:
				setEditHelperAdviceConfiguration((AbstractEditHelperAdviceConfiguration)null);
				return;
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__CONTAINER_CONFIGURATION:
				setContainerConfiguration((ContainerConfiguration)null);
				return;
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__MATCHER_CONFIGURATION:
				setMatcherConfiguration((AbstractMatcherConfiguration)null);
				return;
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__SPECIALIZED_TYPES:
				getSpecializedTypes().clear();
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
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION:
				return editHelperAdviceConfiguration != null;
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__CONTAINER_CONFIGURATION:
				return containerConfiguration != null;
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__MATCHER_CONFIGURATION:
				return matcherConfiguration != null;
			case ElementTypesConfigurationsPackage.SPECIALIZATION_TYPE_CONFIGURATION__SPECIALIZED_TYPES:
				return specializedTypes != null && !specializedTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SpecializationTypeConfigurationImpl
