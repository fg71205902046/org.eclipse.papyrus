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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extension Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionElementImpl#getParent <em>Parent</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionElementImpl#getElements <em>Element</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionElementImpl#getAttributes <em>Attribute</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionElementImpl#getExtension <em>Extension</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExtensionElementImpl extends ExtensionModelNodeImpl implements ExtensionElement {
	/**
	 * The cached value of the '{@link #getElements() <em>Element</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtensionElement> elements;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attribute</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtensionAttribute> attributes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ExtensionElementImpl() {
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
		return ProjectRulesPackage.Literals.EXTENSION_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ExtensionElement getParent() {
		if (eContainerFeatureID() != ProjectRulesPackage.EXTENSION_ELEMENT__PARENT) {
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
	public NotificationChain basicSetParent(ExtensionElement newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newParent, ProjectRulesPackage.EXTENSION_ELEMENT__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setParent(ExtensionElement newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != ProjectRulesPackage.EXTENSION_ELEMENT__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newParent != null) {
				msgs = ((InternalEObject) newParent).eInverseAdd(this, ProjectRulesPackage.EXTENSION_ELEMENT__ELEMENT, ExtensionElement.class, msgs);
			}
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectRulesPackage.EXTENSION_ELEMENT__PARENT, newParent, newParent));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EList<ExtensionElement> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentWithInverseEList<>(ExtensionElement.class, this, ProjectRulesPackage.EXTENSION_ELEMENT__ELEMENT, ProjectRulesPackage.EXTENSION_ELEMENT__PARENT);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ExtensionElement createElement(String name) {
		ExtensionElement newElement = (ExtensionElement) create(ProjectRulesPackage.Literals.EXTENSION_ELEMENT);
		getElements().add(newElement);
		if (name != null) {
			newElement.setName(name);
		}
		return newElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ExtensionElement getElement(String name) {
		return getElement(name, false, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ExtensionElement getElement(String name, boolean ignoreCase, boolean createOnDemand) {
		elementLoop: for (ExtensionElement element : getElements()) {
			if (name != null && !(ignoreCase ? name.equalsIgnoreCase(element.getName()) : name.equals(element.getName()))) {
				continue elementLoop;
			}
			return element;
		}
		return createOnDemand ? createElement(name) : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EList<ExtensionAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentWithInverseEList<>(ExtensionAttribute.class, this, ProjectRulesPackage.EXTENSION_ELEMENT__ATTRIBUTE, ProjectRulesPackage.EXTENSION_ATTRIBUTE__ELEMENT);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ExtensionAttribute createAttribute(String name) {
		ExtensionAttribute newAttribute = (ExtensionAttribute) create(ProjectRulesPackage.Literals.EXTENSION_ATTRIBUTE);
		getAttributes().add(newAttribute);
		if (name != null) {
			newAttribute.setName(name);
		}
		return newAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ExtensionAttribute getAttribute(String name) {
		return getAttribute(name, false, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ExtensionAttribute getAttribute(String name, boolean ignoreCase, boolean createOnDemand) {
		attributeLoop: for (ExtensionAttribute attribute : getAttributes()) {
			if (name != null && !(ignoreCase ? name.equalsIgnoreCase(attribute.getName()) : name.equals(attribute.getName()))) {
				continue attributeLoop;
			}
			return attribute;
		}
		return createOnDemand ? createAttribute(name) : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Extension getExtension() {
		if (eContainerFeatureID() != ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION) {
			return null;
		}
		return (Extension) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public NotificationChain basicSetExtension(Extension newExtension, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newExtension, ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setExtension(Extension newExtension) {
		if (newExtension != eInternalContainer() || (eContainerFeatureID() != ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION && newExtension != null)) {
			if (EcoreUtil.isAncestor(this, newExtension)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newExtension != null) {
				msgs = ((InternalEObject) newExtension).eInverseAdd(this, ProjectRulesPackage.EXTENSION__ELEMENT, Extension.class, msgs);
			}
			msgs = basicSetExtension(newExtension, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION, newExtension, newExtension));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ProjectRulesPackage.EXTENSION_ELEMENT__PARENT:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetParent((ExtensionElement) otherEnd, msgs);
		case ProjectRulesPackage.EXTENSION_ELEMENT__ELEMENT:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getElements()).basicAdd(otherEnd, msgs);
		case ProjectRulesPackage.EXTENSION_ELEMENT__ATTRIBUTE:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getAttributes()).basicAdd(otherEnd, msgs);
		case ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetExtension((Extension) otherEnd, msgs);
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
		case ProjectRulesPackage.EXTENSION_ELEMENT__PARENT:
			return basicSetParent(null, msgs);
		case ProjectRulesPackage.EXTENSION_ELEMENT__ELEMENT:
			return ((InternalEList<?>) getElements()).basicRemove(otherEnd, msgs);
		case ProjectRulesPackage.EXTENSION_ELEMENT__ATTRIBUTE:
			return ((InternalEList<?>) getAttributes()).basicRemove(otherEnd, msgs);
		case ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION:
			return basicSetExtension(null, msgs);
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
		case ProjectRulesPackage.EXTENSION_ELEMENT__PARENT:
			return eInternalContainer().eInverseRemove(this, ProjectRulesPackage.EXTENSION_ELEMENT__ELEMENT, ExtensionElement.class, msgs);
		case ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION:
			return eInternalContainer().eInverseRemove(this, ProjectRulesPackage.EXTENSION__ELEMENT, Extension.class, msgs);
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
		case ProjectRulesPackage.EXTENSION_ELEMENT__PARENT:
			return getParent();
		case ProjectRulesPackage.EXTENSION_ELEMENT__ELEMENT:
			return getElements();
		case ProjectRulesPackage.EXTENSION_ELEMENT__ATTRIBUTE:
			return getAttributes();
		case ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION:
			return getExtension();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ProjectRulesPackage.EXTENSION_ELEMENT__PARENT:
			setParent((ExtensionElement) newValue);
			return;
		case ProjectRulesPackage.EXTENSION_ELEMENT__ELEMENT:
			getElements().clear();
			getElements().addAll((Collection<? extends ExtensionElement>) newValue);
			return;
		case ProjectRulesPackage.EXTENSION_ELEMENT__ATTRIBUTE:
			getAttributes().clear();
			getAttributes().addAll((Collection<? extends ExtensionAttribute>) newValue);
			return;
		case ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION:
			setExtension((Extension) newValue);
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
		case ProjectRulesPackage.EXTENSION_ELEMENT__PARENT:
			setParent((ExtensionElement) null);
			return;
		case ProjectRulesPackage.EXTENSION_ELEMENT__ELEMENT:
			getElements().clear();
			return;
		case ProjectRulesPackage.EXTENSION_ELEMENT__ATTRIBUTE:
			getAttributes().clear();
			return;
		case ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION:
			setExtension((Extension) null);
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
		case ProjectRulesPackage.EXTENSION_ELEMENT__PARENT:
			return getParent() != null;
		case ProjectRulesPackage.EXTENSION_ELEMENT__ELEMENT:
			return elements != null && !elements.isEmpty();
		case ProjectRulesPackage.EXTENSION_ELEMENT__ATTRIBUTE:
			return attributes != null && !attributes.isEmpty();
		case ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION:
			return getExtension() != null;
		}
		return super.eIsSet(featureID);
	}

} // ExtensionElementImpl
