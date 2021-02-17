/**
 * Copyright (c) 2015 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   CEA LIST - Initial API and implementation
 * 
 */
package org.eclipse.papyrus.gmf.codegen.genextension.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.papyrus.gmf.codegen.genextension.CommentedElement;
import org.eclipse.papyrus.gmf.codegen.genextension.PapyrusExtensionRootNode;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Papyrus Extension Root Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.PapyrusExtensionRootNodeImpl#getExtensionNodes <em>Extension Nodes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PapyrusExtensionRootNodeImpl extends CommentedElementImpl implements PapyrusExtensionRootNode {
	/**
	 * The cached value of the '{@link #getExtensionNodes() <em>Extension Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensionNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<CommentedElement> extensionNodes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PapyrusExtensionRootNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CommentedElement> getExtensionNodes() {
		if (extensionNodes == null) {
			extensionNodes = new EObjectContainmentEList<CommentedElement>(CommentedElement.class, this, GenExtensionPackage.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES);
		}
		return extensionNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GenExtensionPackage.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES:
				return ((InternalEList<?>)getExtensionNodes()).basicRemove(otherEnd, msgs);
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
			case GenExtensionPackage.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES:
				return getExtensionNodes();
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
			case GenExtensionPackage.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES:
				getExtensionNodes().clear();
				getExtensionNodes().addAll((Collection<? extends CommentedElement>)newValue);
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
			case GenExtensionPackage.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES:
				getExtensionNodes().clear();
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
			case GenExtensionPackage.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES:
				return extensionNodes != null && !extensionNodes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PapyrusExtensionRootNodeImpl
