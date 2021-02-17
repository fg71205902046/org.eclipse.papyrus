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
import org.eclipse.papyrus.gmf.codegen.genextension.AdditionalEditPartCandies;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Additional Edit Part Candies</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AdditionalEditPartCandiesImpl#getBaseEditHelperPackage <em>Base Edit Helper Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdditionalEditPartCandiesImpl extends CommentedElementImpl implements AdditionalEditPartCandies {
	/**
	 * The default value of the '{@link #getBaseEditHelperPackage() <em>Base Edit Helper Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseEditHelperPackage()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_EDIT_HELPER_PACKAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaseEditHelperPackage() <em>Base Edit Helper Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseEditHelperPackage()
	 * @generated
	 * @ordered
	 */
	protected String baseEditHelperPackage = BASE_EDIT_HELPER_PACKAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdditionalEditPartCandiesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenExtensionPackage.Literals.ADDITIONAL_EDIT_PART_CANDIES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBaseEditHelperPackage() {
		return baseEditHelperPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBaseEditHelperPackage(String newBaseEditHelperPackage) {
		String oldBaseEditHelperPackage = baseEditHelperPackage;
		baseEditHelperPackage = newBaseEditHelperPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenExtensionPackage.ADDITIONAL_EDIT_PART_CANDIES__BASE_EDIT_HELPER_PACKAGE, oldBaseEditHelperPackage, baseEditHelperPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GenExtensionPackage.ADDITIONAL_EDIT_PART_CANDIES__BASE_EDIT_HELPER_PACKAGE:
				return getBaseEditHelperPackage();
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
			case GenExtensionPackage.ADDITIONAL_EDIT_PART_CANDIES__BASE_EDIT_HELPER_PACKAGE:
				setBaseEditHelperPackage((String)newValue);
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
			case GenExtensionPackage.ADDITIONAL_EDIT_PART_CANDIES__BASE_EDIT_HELPER_PACKAGE:
				setBaseEditHelperPackage(BASE_EDIT_HELPER_PACKAGE_EDEFAULT);
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
			case GenExtensionPackage.ADDITIONAL_EDIT_PART_CANDIES__BASE_EDIT_HELPER_PACKAGE:
				return BASE_EDIT_HELPER_PACKAGE_EDEFAULT == null ? baseEditHelperPackage != null : !BASE_EDIT_HELPER_PACKAGE_EDEFAULT.equals(baseEditHelperPackage);
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
		result.append(" (baseEditHelperPackage: ");
		result.append(baseEditHelperPackage);
		result.append(')');
		return result.toString();
	}

} //AdditionalEditPartCandiesImpl
