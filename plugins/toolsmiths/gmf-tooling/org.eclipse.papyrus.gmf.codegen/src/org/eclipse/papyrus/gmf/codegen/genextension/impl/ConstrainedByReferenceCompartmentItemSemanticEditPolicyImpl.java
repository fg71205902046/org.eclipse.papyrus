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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.papyrus.gmf.codegen.genextension.ConstrainedByReferenceCompartmentItemSemanticEditPolicy;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constrained By Reference Compartment Item Semantic Edit Policy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.ConstrainedByReferenceCompartmentItemSemanticEditPolicyImpl#getGenView <em>Gen View</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConstrainedByReferenceCompartmentItemSemanticEditPolicyImpl extends CommentedElementImpl implements ConstrainedByReferenceCompartmentItemSemanticEditPolicy {
	/**
	 * The cached value of the '{@link #getGenView() <em>Gen View</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenView()
	 * @generated
	 * @ordered
	 */
	protected EList<GenCommonBase> genView;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstrainedByReferenceCompartmentItemSemanticEditPolicyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenExtensionPackage.Literals.CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<GenCommonBase> getGenView() {
		if (genView == null) {
			genView = new EObjectResolvingEList<GenCommonBase>(GenCommonBase.class, this, GenExtensionPackage.CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY__GEN_VIEW);
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
			case GenExtensionPackage.CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY__GEN_VIEW:
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
			case GenExtensionPackage.CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY__GEN_VIEW:
				getGenView().clear();
				getGenView().addAll((Collection<? extends GenCommonBase>)newValue);
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
			case GenExtensionPackage.CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY__GEN_VIEW:
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
			case GenExtensionPackage.CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY__GEN_VIEW:
				return genView != null && !genView.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ConstrainedByReferenceCompartmentItemSemanticEditPolicyImpl
