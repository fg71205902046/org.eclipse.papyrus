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
package org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.operations.ExtensionAttributeOperations;
import org.eclipse.uml2.common.util.CacheAdapter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extension Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionAttributeImpl#getValue <em>Value</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionAttributeImpl#getParsedValue <em>Parsed Value</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionAttributeImpl#getElement <em>Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExtensionAttributeImpl extends ExtensionModelNodeImpl implements ExtensionAttribute {
	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected Value value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ExtensionAttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProjectRulesPackage.Literals.EXTENSION_ATTRIBUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Value getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public NotificationChain basicSetValue(Value newValue, NotificationChain msgs) {
		Value oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProjectRulesPackage.EXTENSION_ATTRIBUTE__VALUE, oldValue, newValue);
			if (msgs == null) {
				msgs = notification;
			} else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setValue(Value newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null) {
				msgs = ((InternalEObject) value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProjectRulesPackage.EXTENSION_ATTRIBUTE__VALUE, null, msgs);
			}
			if (newValue != null) {
				msgs = ((InternalEObject) newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProjectRulesPackage.EXTENSION_ATTRIBUTE__VALUE, null, msgs);
			}
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectRulesPackage.EXTENSION_ATTRIBUTE__VALUE, newValue, newValue));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Value createValue(EClass eClass) {
		Value newValue = (Value) create(eClass);
		setValue(newValue);
		return newValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Value getParsedValue() {
		Value parsedValue = basicGetParsedValue();
		return parsedValue != null && parsedValue.eIsProxy() ? (Value) eResolveProxy((InternalEObject) parsedValue) : parsedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public Value basicGetParsedValue() {
		CacheAdapter cache = getCacheAdapter();
		if (cache != null) {
			cache.adapt(this);
			Value result = (Value) cache.get(eResource(), this, ProjectRulesPackage.Literals.EXTENSION_ATTRIBUTE___GET_PARSED_VALUE);
			if (result == null) {
				cache.put(eResource(), this, ProjectRulesPackage.Literals.EXTENSION_ATTRIBUTE___GET_PARSED_VALUE, result = ExtensionAttributeOperations.getParsedValue(this));
			}
			return result;
		}
		return ExtensionAttributeOperations.getParsedValue(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ExtensionElement getElement() {
		if (eContainerFeatureID() != ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT) {
			return null;
		}
		return (ExtensionElement) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public NotificationChain basicSetElement(ExtensionElement newElement, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newElement, ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setElement(ExtensionElement newElement) {
		if (newElement != eInternalContainer() || (eContainerFeatureID() != ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT && newElement != null)) {
			if (EcoreUtil.isAncestor(this, newElement)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newElement != null) {
				msgs = ((InternalEObject) newElement).eInverseAdd(this, ProjectRulesPackage.EXTENSION_ELEMENT__ATTRIBUTE, ExtensionElement.class, msgs);
			}
			msgs = basicSetElement(newElement, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT, newElement, newElement));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetElement((ExtensionElement) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__VALUE:
			return basicSetValue(null, msgs);
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT:
			return basicSetElement(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT:
			return eInternalContainer().eInverseRemove(this, ProjectRulesPackage.EXTENSION_ELEMENT__ATTRIBUTE, ExtensionElement.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__VALUE:
			return getValue();
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__PARSED_VALUE:
			if (resolve) {
				return getParsedValue();
			}
			return basicGetParsedValue();
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT:
			return getElement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__VALUE:
			setValue((Value) newValue);
			return;
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT:
			setElement((ExtensionElement) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__VALUE:
			setValue((Value) null);
			return;
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT:
			setElement((ExtensionElement) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__VALUE:
			return value != null;
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__PARSED_VALUE:
			return basicGetParsedValue() != null;
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT:
			return getElement() != null;
		}
		return super.eIsSet(featureID);
	}

} // ExtensionAttributeImpl
