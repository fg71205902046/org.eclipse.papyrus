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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.operations.ExtensionOperations;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.util.ProjectRulesCache;
import org.eclipse.uml2.common.util.CacheAdapter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionImpl#getExtensionPoint <em>Extension Point</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionImpl#getElements <em>Element</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionImpl#getResourceAttribute <em>Resource Attribute</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionImpl#getArchitectureReference <em>Architecture Reference</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionImpl#getProject <em>Project</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExtensionImpl extends MinimalEObjectImpl.Container implements Extension {
	/**
	 * The default value of the '{@link #getExtensionPoint() <em>Extension Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getExtensionPoint()
	 * @generated
	 * @ordered
	 */
	protected static final String EXTENSION_POINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExtensionPoint() <em>Extension Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getExtensionPoint()
	 * @generated
	 * @ordered
	 */
	protected String extensionPoint = EXTENSION_POINT_EDEFAULT;

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
	 * The cached value of the '{@link #getArchitectureReference() <em>Architecture Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getArchitectureReference()
	 * @generated
	 * @ordered
	 */
	protected EReference architectureReference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ExtensionImpl() {
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
		return ProjectRulesPackage.Literals.EXTENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public String getExtensionPoint() {
		return extensionPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setExtensionPoint(String newExtensionPoint) {
		String oldExtensionPoint = extensionPoint;
		extensionPoint = newExtensionPoint;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectRulesPackage.EXTENSION__EXTENSION_POINT, oldExtensionPoint, extensionPoint));
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
			elements = new EObjectContainmentWithInverseEList<>(ExtensionElement.class, this, ProjectRulesPackage.EXTENSION__ELEMENT, ProjectRulesPackage.EXTENSION_ELEMENT__EXTENSION);
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
	public ExtensionAttribute getResourceAttribute() {
		ExtensionAttribute resourceAttribute = basicGetResourceAttribute();
		return resourceAttribute != null && resourceAttribute.eIsProxy() ? (ExtensionAttribute) eResolveProxy((InternalEObject) resourceAttribute) : resourceAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	public ExtensionAttribute basicGetResourceAttribute() {
		CacheAdapter cache = getCacheAdapter();
		if (cache != null) {
			ExtensionAttribute result = (ExtensionAttribute) cache.get(eResource(), this, ProjectRulesPackage.Literals.EXTENSION___GET_RESOURCE_ATTRIBUTE);
			if (result == null) {
				cache.put(eResource(), this, ProjectRulesPackage.Literals.EXTENSION___GET_RESOURCE_ATTRIBUTE, result = ExtensionOperations.getResourceAttribute(this));
			}
			return result;
		}
		return ExtensionOperations.getResourceAttribute(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EReference getArchitectureReference() {
		if (architectureReference != null && architectureReference.eIsProxy()) {
			InternalEObject oldArchitectureReference = (InternalEObject) architectureReference;
			architectureReference = (EReference) eResolveProxy(oldArchitectureReference);
			if (architectureReference != oldArchitectureReference) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProjectRulesPackage.EXTENSION__ARCHITECTURE_REFERENCE, oldArchitectureReference, architectureReference));
				}
			}
		}
		return architectureReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public EReference basicGetArchitectureReference() {
		return architectureReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setArchitectureReference(EReference newArchitectureReference) {
		EReference oldArchitectureReference = architectureReference;
		architectureReference = newArchitectureReference;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectRulesPackage.EXTENSION__ARCHITECTURE_REFERENCE, oldArchitectureReference, architectureReference));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ProjectDescription getProject() {
		if (eContainerFeatureID() != ProjectRulesPackage.EXTENSION__PROJECT) {
			return null;
		}
		return (ProjectDescription) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public NotificationChain basicSetProject(ProjectDescription newProject, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newProject, ProjectRulesPackage.EXTENSION__PROJECT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setProject(ProjectDescription newProject) {
		if (newProject != eInternalContainer() || (eContainerFeatureID() != ProjectRulesPackage.EXTENSION__PROJECT && newProject != null)) {
			if (EcoreUtil.isAncestor(this, newProject)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newProject != null) {
				msgs = ((InternalEObject) newProject).eInverseAdd(this, ProjectRulesPackage.PROJECT_DESCRIPTION__EXTENSION, ProjectDescription.class, msgs);
			}
			msgs = basicSetProject(newProject, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ProjectRulesPackage.EXTENSION__PROJECT, newProject, newProject));
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
		case ProjectRulesPackage.EXTENSION__ELEMENT:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getElements()).basicAdd(otherEnd, msgs);
		case ProjectRulesPackage.EXTENSION__PROJECT:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetProject((ProjectDescription) otherEnd, msgs);
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
		case ProjectRulesPackage.EXTENSION__ELEMENT:
			return ((InternalEList<?>) getElements()).basicRemove(otherEnd, msgs);
		case ProjectRulesPackage.EXTENSION__PROJECT:
			return basicSetProject(null, msgs);
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
		case ProjectRulesPackage.EXTENSION__PROJECT:
			return eInternalContainer().eInverseRemove(this, ProjectRulesPackage.PROJECT_DESCRIPTION__EXTENSION, ProjectDescription.class, msgs);
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
		case ProjectRulesPackage.EXTENSION__EXTENSION_POINT:
			return getExtensionPoint();
		case ProjectRulesPackage.EXTENSION__ELEMENT:
			return getElements();
		case ProjectRulesPackage.EXTENSION__RESOURCE_ATTRIBUTE:
			if (resolve) {
				return getResourceAttribute();
			}
			return basicGetResourceAttribute();
		case ProjectRulesPackage.EXTENSION__ARCHITECTURE_REFERENCE:
			if (resolve) {
				return getArchitectureReference();
			}
			return basicGetArchitectureReference();
		case ProjectRulesPackage.EXTENSION__PROJECT:
			return getProject();
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
		case ProjectRulesPackage.EXTENSION__EXTENSION_POINT:
			setExtensionPoint((String) newValue);
			return;
		case ProjectRulesPackage.EXTENSION__ELEMENT:
			getElements().clear();
			getElements().addAll((Collection<? extends ExtensionElement>) newValue);
			return;
		case ProjectRulesPackage.EXTENSION__ARCHITECTURE_REFERENCE:
			setArchitectureReference((EReference) newValue);
			return;
		case ProjectRulesPackage.EXTENSION__PROJECT:
			setProject((ProjectDescription) newValue);
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
		case ProjectRulesPackage.EXTENSION__EXTENSION_POINT:
			setExtensionPoint(EXTENSION_POINT_EDEFAULT);
			return;
		case ProjectRulesPackage.EXTENSION__ELEMENT:
			getElements().clear();
			return;
		case ProjectRulesPackage.EXTENSION__ARCHITECTURE_REFERENCE:
			setArchitectureReference((EReference) null);
			return;
		case ProjectRulesPackage.EXTENSION__PROJECT:
			setProject((ProjectDescription) null);
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
		case ProjectRulesPackage.EXTENSION__EXTENSION_POINT:
			return EXTENSION_POINT_EDEFAULT == null ? extensionPoint != null : !EXTENSION_POINT_EDEFAULT.equals(extensionPoint);
		case ProjectRulesPackage.EXTENSION__ELEMENT:
			return elements != null && !elements.isEmpty();
		case ProjectRulesPackage.EXTENSION__RESOURCE_ATTRIBUTE:
			return basicGetResourceAttribute() != null;
		case ProjectRulesPackage.EXTENSION__ARCHITECTURE_REFERENCE:
			return architectureReference != null;
		case ProjectRulesPackage.EXTENSION__PROJECT:
			return getProject() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (extensionPoint: ");
		result.append(extensionPoint);
		result.append(')');
		return result.toString();
	}

	/**
	 * Creates a new instance of the specified Ecore class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param eClass
	 *                   The Ecore class of the instance to create.
	 * @return The new instance.
	 * @generated
	 */
	protected EObject create(EClass eClass) {
		return EcoreUtil.create(eClass);
	}

	/**
	 * Retrieves the cache adapter for this '<em><b>Extension</b></em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return The cache adapter for this '<em><b>Extension</b></em>'.
	 * @generated
	 */
	protected ProjectRulesCache getCacheAdapter() {
		return ProjectRulesCache.getCacheAdapter(this);
	}

} // ExtensionImpl
