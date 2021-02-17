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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenConstraint;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Node Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.GenNodeConstraintImpl#getGenNode <em>Gen Node</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.GenNodeConstraintImpl#getGenConstraint <em>Gen Constraint</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenNodeConstraintImpl extends CommentedElementImpl implements GenNodeConstraint {
	/**
	 * The cached value of the '{@link #getGenNode() <em>Gen Node</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenNode()
	 * @generated
	 * @ordered
	 */
	protected EList<GenNode> genNode;

	/**
	 * The cached value of the '{@link #getGenConstraint() <em>Gen Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenConstraint()
	 * @generated
	 * @ordered
	 */
	protected GenConstraint genConstraint;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenNodeConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenExtensionPackage.Literals.GEN_NODE_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<GenNode> getGenNode() {
		if (genNode == null) {
			genNode = new EObjectResolvingEList<GenNode>(GenNode.class, this, GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_NODE);
		}
		return genNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GenConstraint getGenConstraint() {
		if (genConstraint != null && genConstraint.eIsProxy()) {
			InternalEObject oldGenConstraint = (InternalEObject)genConstraint;
			genConstraint = (GenConstraint)eResolveProxy(oldGenConstraint);
			if (genConstraint != oldGenConstraint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_CONSTRAINT, oldGenConstraint, genConstraint));
			}
		}
		return genConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenConstraint basicGetGenConstraint() {
		return genConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGenConstraint(GenConstraint newGenConstraint) {
		GenConstraint oldGenConstraint = genConstraint;
		genConstraint = newGenConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_CONSTRAINT, oldGenConstraint, genConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_NODE:
				return getGenNode();
			case GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_CONSTRAINT:
				if (resolve) return getGenConstraint();
				return basicGetGenConstraint();
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
			case GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_NODE:
				getGenNode().clear();
				getGenNode().addAll((Collection<? extends GenNode>)newValue);
				return;
			case GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_CONSTRAINT:
				setGenConstraint((GenConstraint)newValue);
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
			case GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_NODE:
				getGenNode().clear();
				return;
			case GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_CONSTRAINT:
				setGenConstraint((GenConstraint)null);
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
			case GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_NODE:
				return genNode != null && !genNode.isEmpty();
			case GenExtensionPackage.GEN_NODE_CONSTRAINT__GEN_CONSTRAINT:
				return genConstraint != null;
		}
		return super.eIsSet(featureID);
	}

} //GenNodeConstraintImpl
