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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.papyrus.gmf.codegen.genextension.CustomDiagramUpdaterSingleton;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Diagram Updater Singleton</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.CustomDiagramUpdaterSingletonImpl#getSingletonPath <em>Singleton Path</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomDiagramUpdaterSingletonImpl extends CommentedElementImpl implements CustomDiagramUpdaterSingleton {
	/**
	 * The default value of the '{@link #getSingletonPath() <em>Singleton Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSingletonPath()
	 * @generated
	 * @ordered
	 */
	protected static final String SINGLETON_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSingletonPath() <em>Singleton Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSingletonPath()
	 * @generated
	 * @ordered
	 */
	protected String singletonPath = SINGLETON_PATH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomDiagramUpdaterSingletonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenExtensionPackage.Literals.CUSTOM_DIAGRAM_UPDATER_SINGLETON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSingletonPath() {
		return singletonPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSingletonPath(String newSingletonPath) {
		String oldSingletonPath = singletonPath;
		singletonPath = newSingletonPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenExtensionPackage.CUSTOM_DIAGRAM_UPDATER_SINGLETON__SINGLETON_PATH, oldSingletonPath, singletonPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GenExtensionPackage.CUSTOM_DIAGRAM_UPDATER_SINGLETON__SINGLETON_PATH:
				return getSingletonPath();
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
			case GenExtensionPackage.CUSTOM_DIAGRAM_UPDATER_SINGLETON__SINGLETON_PATH:
				setSingletonPath((String)newValue);
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
			case GenExtensionPackage.CUSTOM_DIAGRAM_UPDATER_SINGLETON__SINGLETON_PATH:
				setSingletonPath(SINGLETON_PATH_EDEFAULT);
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
			case GenExtensionPackage.CUSTOM_DIAGRAM_UPDATER_SINGLETON__SINGLETON_PATH:
				return SINGLETON_PATH_EDEFAULT == null ? singletonPath != null : !SINGLETON_PATH_EDEFAULT.equals(singletonPath);
		}
		return super.eIsSet(featureID);
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
		result.append(" (singletonPath: ");
		result.append(singletonPath);
		result.append(')');
		return result.toString();
	}

} //CustomDiagramUpdaterSingletonImpl
