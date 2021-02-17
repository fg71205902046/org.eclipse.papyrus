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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingReorientService;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edit Part Using Reorient Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.EditPartUsingReorientServiceImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.EditPartUsingReorientServiceImpl#getGenView <em>Gen View</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EditPartUsingReorientServiceImpl extends CommentedElementImpl implements EditPartUsingReorientService {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGenView() <em>Gen View</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenView()
	 * @generated
	 * @ordered
	 */
	protected EList<GenLink> genView;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EditPartUsingReorientServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenExtensionPackage.Literals.EDIT_PART_USING_REORIENT_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<GenLink> getGenView() {
		if (genView == null) {
			genView = new EObjectResolvingEList<GenLink>(GenLink.class, this, GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE__GEN_VIEW);
		}
		return genView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE__NAME:
				return getName();
			case GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE__GEN_VIEW:
				return getGenView();
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
			case GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE__NAME:
				setName((String)newValue);
				return;
			case GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE__GEN_VIEW:
				getGenView().clear();
				getGenView().addAll((Collection<? extends GenLink>)newValue);
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
			case GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE__GEN_VIEW:
				getGenView().clear();
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
			case GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE__GEN_VIEW:
				return genView != null && !genView.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //EditPartUsingReorientServiceImpl
