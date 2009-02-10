/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.papyrus.papyrusgmfgenextension.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.gmf.codegen.gmfgen.GenCommonBase;

import org.eclipse.papyrus.papyrusgmfgenextension.ExtendedGenNode;
import org.eclipse.papyrus.papyrusgmfgenextension.PapyrusgmfgenextensionPackage;
import org.eclipse.papyrus.papyrusgmfgenextension.PropertyRefreshHook;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extended Gen Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.papyrus.papyrusgmfgenextension.impl.ExtendedGenNodeImpl#getGenNode <em>Gen Node</em>}</li>
 *   <li>{@link org.eclipse.papyrus.papyrusgmfgenextension.impl.ExtendedGenNodeImpl#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.eclipse.papyrus.papyrusgmfgenextension.impl.ExtendedGenNodeImpl#getSuperGenNodes <em>Super Gen Nodes</em>}</li>
 *   <li>{@link org.eclipse.papyrus.papyrusgmfgenextension.impl.ExtendedGenNodeImpl#getPropRefreshHook <em>Prop Refresh Hook</em>}</li>
 *   <li>{@link org.eclipse.papyrus.papyrusgmfgenextension.impl.ExtendedGenNodeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtendedGenNodeImpl extends CommentedElementImpl implements ExtendedGenNode {
	/**
	 * The cached value of the '{@link #getGenNode() <em>Gen Node</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenNode()
	 * @generated
	 * @ordered
	 */
	protected EList<GenCommonBase> genNode;

	/**
	 * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSuperGenNodes() <em>Super Gen Nodes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperGenNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<GenCommonBase> superGenNodes;

	/**
	 * The cached value of the '{@link #getPropRefreshHook() <em>Prop Refresh Hook</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropRefreshHook()
	 * @generated
	 * @ordered
	 */
	protected PropertyRefreshHook propRefreshHook;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtendedGenNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PapyrusgmfgenextensionPackage.Literals.EXTENDED_GEN_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenCommonBase> getGenNode() {
		if (genNode == null) {
			genNode = new EObjectResolvingEList<GenCommonBase>(GenCommonBase.class, this, PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__GEN_NODE);
		}
		return genNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsAbstract() {
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAbstract(boolean newIsAbstract) {
		boolean oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__IS_ABSTRACT, oldIsAbstract, isAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenCommonBase> getSuperGenNodes() {
		if (superGenNodes == null) {
			superGenNodes = new EObjectResolvingEList<GenCommonBase>(GenCommonBase.class, this, PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__SUPER_GEN_NODES);
		}
		return superGenNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyRefreshHook getPropRefreshHook() {
		return propRefreshHook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPropRefreshHook(PropertyRefreshHook newPropRefreshHook, NotificationChain msgs) {
		PropertyRefreshHook oldPropRefreshHook = propRefreshHook;
		propRefreshHook = newPropRefreshHook;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__PROP_REFRESH_HOOK, oldPropRefreshHook, newPropRefreshHook);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropRefreshHook(PropertyRefreshHook newPropRefreshHook) {
		if (newPropRefreshHook != propRefreshHook) {
			NotificationChain msgs = null;
			if (propRefreshHook != null)
				msgs = ((InternalEObject)propRefreshHook).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__PROP_REFRESH_HOOK, null, msgs);
			if (newPropRefreshHook != null)
				msgs = ((InternalEObject)newPropRefreshHook).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__PROP_REFRESH_HOOK, null, msgs);
			msgs = basicSetPropRefreshHook(newPropRefreshHook, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__PROP_REFRESH_HOOK, newPropRefreshHook, newPropRefreshHook));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__PROP_REFRESH_HOOK:
				return basicSetPropRefreshHook(null, msgs);
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
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__GEN_NODE:
				return getGenNode();
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__IS_ABSTRACT:
				return isIsAbstract() ? Boolean.TRUE : Boolean.FALSE;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__SUPER_GEN_NODES:
				return getSuperGenNodes();
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__PROP_REFRESH_HOOK:
				return getPropRefreshHook();
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__NAME:
				return getName();
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
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__GEN_NODE:
				getGenNode().clear();
				getGenNode().addAll((Collection<? extends GenCommonBase>)newValue);
				return;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__IS_ABSTRACT:
				setIsAbstract(((Boolean)newValue).booleanValue());
				return;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__SUPER_GEN_NODES:
				getSuperGenNodes().clear();
				getSuperGenNodes().addAll((Collection<? extends GenCommonBase>)newValue);
				return;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__PROP_REFRESH_HOOK:
				setPropRefreshHook((PropertyRefreshHook)newValue);
				return;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__NAME:
				setName((String)newValue);
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
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__GEN_NODE:
				getGenNode().clear();
				return;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__SUPER_GEN_NODES:
				getSuperGenNodes().clear();
				return;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__PROP_REFRESH_HOOK:
				setPropRefreshHook((PropertyRefreshHook)null);
				return;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__NAME:
				setName(NAME_EDEFAULT);
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
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__GEN_NODE:
				return genNode != null && !genNode.isEmpty();
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__IS_ABSTRACT:
				return isAbstract != IS_ABSTRACT_EDEFAULT;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__SUPER_GEN_NODES:
				return superGenNodes != null && !superGenNodes.isEmpty();
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__PROP_REFRESH_HOOK:
				return propRefreshHook != null;
			case PapyrusgmfgenextensionPackage.EXTENDED_GEN_NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isAbstract: ");
		result.append(isAbstract);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ExtendedGenNodeImpl
