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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLink;
import org.eclipse.papyrus.gmf.codegen.gmfgen.TypeModelFacet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Alternate Gen Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateGenLinkImpl#getGenLinkNode <em>Gen Link Node</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateGenLinkImpl#getTypeModelFacet <em>Type Model Facet</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AlternateGenLinkImpl extends CommentedElementImpl implements AlternateGenLink {
	/**
	 * The cached value of the '{@link #getGenLinkNode() <em>Gen Link Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenLinkNode()
	 * @generated
	 * @ordered
	 */
	protected GenLink genLinkNode;

	/**
	 * The cached value of the '{@link #getTypeModelFacet() <em>Type Model Facet</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeModelFacet()
	 * @generated
	 * @ordered
	 */
	protected TypeModelFacet typeModelFacet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AlternateGenLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GenExtensionPackage.Literals.ALTERNATE_GEN_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GenLink getGenLinkNode() {
		if (genLinkNode != null && genLinkNode.eIsProxy()) {
			InternalEObject oldGenLinkNode = (InternalEObject)genLinkNode;
			genLinkNode = (GenLink)eResolveProxy(oldGenLinkNode);
			if (genLinkNode != oldGenLinkNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenExtensionPackage.ALTERNATE_GEN_LINK__GEN_LINK_NODE, oldGenLinkNode, genLinkNode));
			}
		}
		return genLinkNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenLink basicGetGenLinkNode() {
		return genLinkNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGenLinkNode(GenLink newGenLinkNode) {
		GenLink oldGenLinkNode = genLinkNode;
		genLinkNode = newGenLinkNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenExtensionPackage.ALTERNATE_GEN_LINK__GEN_LINK_NODE, oldGenLinkNode, genLinkNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypeModelFacet getTypeModelFacet() {
		return typeModelFacet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeModelFacet(TypeModelFacet newTypeModelFacet, NotificationChain msgs) {
		TypeModelFacet oldTypeModelFacet = typeModelFacet;
		typeModelFacet = newTypeModelFacet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GenExtensionPackage.ALTERNATE_GEN_LINK__TYPE_MODEL_FACET, oldTypeModelFacet, newTypeModelFacet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTypeModelFacet(TypeModelFacet newTypeModelFacet) {
		if (newTypeModelFacet != typeModelFacet) {
			NotificationChain msgs = null;
			if (typeModelFacet != null)
				msgs = ((InternalEObject)typeModelFacet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GenExtensionPackage.ALTERNATE_GEN_LINK__TYPE_MODEL_FACET, null, msgs);
			if (newTypeModelFacet != null)
				msgs = ((InternalEObject)newTypeModelFacet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GenExtensionPackage.ALTERNATE_GEN_LINK__TYPE_MODEL_FACET, null, msgs);
			msgs = basicSetTypeModelFacet(newTypeModelFacet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GenExtensionPackage.ALTERNATE_GEN_LINK__TYPE_MODEL_FACET, newTypeModelFacet, newTypeModelFacet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GenExtensionPackage.ALTERNATE_GEN_LINK__TYPE_MODEL_FACET:
				return basicSetTypeModelFacet(null, msgs);
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
			case GenExtensionPackage.ALTERNATE_GEN_LINK__GEN_LINK_NODE:
				if (resolve) return getGenLinkNode();
				return basicGetGenLinkNode();
			case GenExtensionPackage.ALTERNATE_GEN_LINK__TYPE_MODEL_FACET:
				return getTypeModelFacet();
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
			case GenExtensionPackage.ALTERNATE_GEN_LINK__GEN_LINK_NODE:
				setGenLinkNode((GenLink)newValue);
				return;
			case GenExtensionPackage.ALTERNATE_GEN_LINK__TYPE_MODEL_FACET:
				setTypeModelFacet((TypeModelFacet)newValue);
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
			case GenExtensionPackage.ALTERNATE_GEN_LINK__GEN_LINK_NODE:
				setGenLinkNode((GenLink)null);
				return;
			case GenExtensionPackage.ALTERNATE_GEN_LINK__TYPE_MODEL_FACET:
				setTypeModelFacet((TypeModelFacet)null);
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
			case GenExtensionPackage.ALTERNATE_GEN_LINK__GEN_LINK_NODE:
				return genLinkNode != null;
			case GenExtensionPackage.ALTERNATE_GEN_LINK__TYPE_MODEL_FACET:
				return typeModelFacet != null;
		}
		return super.eIsSet(featureID);
	}

} //AlternateGenLinkImpl
