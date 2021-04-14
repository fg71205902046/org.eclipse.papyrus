/**
 */
package org.eclipse.papyrus.infra.properties.viewmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UI Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.properties.viewmodel.UIElement#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.properties.viewmodel.UIElement#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage#getUIElement()
 * @model
 * @generated
 */
public interface UIElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.infra.properties.viewmodel.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage#getUIElement_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<Property> getProperties();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage#getUIElement_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.properties.viewmodel.UIElement#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // UIElement
