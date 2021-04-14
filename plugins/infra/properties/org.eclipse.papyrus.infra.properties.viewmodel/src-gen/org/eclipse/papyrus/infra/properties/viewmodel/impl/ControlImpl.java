/**
 */
package org.eclipse.papyrus.infra.properties.viewmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.papyrus.infra.properties.viewmodel.Control;
import org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Control</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.ControlImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.ControlImpl#getRendererHint <em>Renderer Hint</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControlImpl extends UIElementImpl implements Control {
	/**
	 * The default value of the '{@link #getProperty() <em>Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected static final String PROPERTY_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected String property = PROPERTY_EDEFAULT;
	/**
	 * The default value of the '{@link #getRendererHint() <em>Renderer Hint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRendererHint()
	 * @generated
	 * @ordered
	 */
	protected static final String RENDERER_HINT_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getRendererHint() <em>Renderer Hint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRendererHint()
	 * @generated
	 * @ordered
	 */
	protected String rendererHint = RENDERER_HINT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViewmodelPackage.Literals.CONTROL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(String newProperty) {
		String oldProperty = property;
		property = newProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewmodelPackage.CONTROL__PROPERTY, oldProperty, property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRendererHint() {
		return rendererHint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRendererHint(String newRendererHint) {
		String oldRendererHint = rendererHint;
		rendererHint = newRendererHint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViewmodelPackage.CONTROL__RENDERER_HINT, oldRendererHint, rendererHint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViewmodelPackage.CONTROL__PROPERTY:
				return getProperty();
			case ViewmodelPackage.CONTROL__RENDERER_HINT:
				return getRendererHint();
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
			case ViewmodelPackage.CONTROL__PROPERTY:
				setProperty((String)newValue);
				return;
			case ViewmodelPackage.CONTROL__RENDERER_HINT:
				setRendererHint((String)newValue);
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
			case ViewmodelPackage.CONTROL__PROPERTY:
				setProperty(PROPERTY_EDEFAULT);
				return;
			case ViewmodelPackage.CONTROL__RENDERER_HINT:
				setRendererHint(RENDERER_HINT_EDEFAULT);
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
			case ViewmodelPackage.CONTROL__PROPERTY:
				return PROPERTY_EDEFAULT == null ? property != null : !PROPERTY_EDEFAULT.equals(property);
			case ViewmodelPackage.CONTROL__RENDERER_HINT:
				return RENDERER_HINT_EDEFAULT == null ? rendererHint != null : !RENDERER_HINT_EDEFAULT.equals(rendererHint);
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
		result.append(" (property: ");
		result.append(property);
		result.append(", rendererHint: ");
		result.append(rendererHint);
		result.append(')');
		return result.toString();
	}

} //ControlImpl
