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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;
import org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Visual ID Override</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.VisualIDOverrideImpl#getGenView <em>Gen View</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.VisualIDOverrideImpl#getVisualID <em>Visual ID</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.VisualIDOverrideImpl#getChild <em>Child</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VisualIDOverrideImpl extends EObjectImpl implements VisualIDOverride {
	/**
	 * The cached value of the '{@link #getGenView() <em>Gen View</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenView()
	 * @generated
	 * @ordered
	 */
	protected GenCommonBase genView;

	/**
	 * The default value of the '{@link #getVisualID() <em>Visual ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisualID()
	 * @generated
	 * @ordered
	 */
	protected static final String VISUAL_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVisualID() <em>Visual ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisualID()
	 * @generated
	 * @ordered
	 */
	protected String visualID = VISUAL_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getChild() <em>Child</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChild()
	 * @generated
	 * @ordered
	 */
	protected EList<VisualIDOverride> child;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VisualIDOverrideImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenExtensionPackage.Literals.VISUAL_ID_OVERRIDE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GenCommonBase getGenView() {
		if (genView != null && genView.eIsProxy()) {
			InternalEObject oldGenView = (InternalEObject)genView;
			genView = (GenCommonBase)eResolveProxy(oldGenView);
			if (genView != oldGenView) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenExtensionPackage.VISUAL_ID_OVERRIDE__GEN_VIEW, oldGenView, genView));
			}
		}
		return genView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenCommonBase basicGetGenView() {
		return genView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGenView(GenCommonBase newGenView) {
		GenCommonBase oldGenView = genView;
		genView = newGenView;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenExtensionPackage.VISUAL_ID_OVERRIDE__GEN_VIEW, oldGenView, genView));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVisualID() {
		return visualID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVisualID(String newVisualID) {
		String oldVisualID = visualID;
		visualID = newVisualID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenExtensionPackage.VISUAL_ID_OVERRIDE__VISUAL_ID, oldVisualID, visualID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<VisualIDOverride> getChild() {
		if (child == null) {
			child = new EObjectContainmentEList<VisualIDOverride>(VisualIDOverride.class, this, GenExtensionPackage.VISUAL_ID_OVERRIDE__CHILD);
		}
		return child;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__CHILD:
				return ((InternalEList<?>)getChild()).basicRemove(otherEnd, msgs);
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
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__GEN_VIEW:
				if (resolve) return getGenView();
				return basicGetGenView();
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__VISUAL_ID:
				return getVisualID();
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__CHILD:
				return getChild();
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
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__GEN_VIEW:
				setGenView((GenCommonBase)newValue);
				return;
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__VISUAL_ID:
				setVisualID((String)newValue);
				return;
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__CHILD:
				getChild().clear();
				getChild().addAll((Collection<? extends VisualIDOverride>)newValue);
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
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__GEN_VIEW:
				setGenView((GenCommonBase)null);
				return;
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__VISUAL_ID:
				setVisualID(VISUAL_ID_EDEFAULT);
				return;
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__CHILD:
				getChild().clear();
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
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__GEN_VIEW:
				return genView != null;
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__VISUAL_ID:
				return VISUAL_ID_EDEFAULT == null ? visualID != null : !VISUAL_ID_EDEFAULT.equals(visualID);
			case GenExtensionPackage.VISUAL_ID_OVERRIDE__CHILD:
				return child != null && !child.isEmpty();
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
		result.append(" (visualID: ");
		result.append(visualID);
		result.append(')');
		return result.toString();
	}

} //VisualIDOverrideImpl
