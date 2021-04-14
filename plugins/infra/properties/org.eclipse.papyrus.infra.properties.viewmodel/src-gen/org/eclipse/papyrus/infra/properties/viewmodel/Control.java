/**
 */
package org.eclipse.papyrus.infra.properties.viewmodel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Control</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.properties.viewmodel.Control#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.properties.viewmodel.Control#getRendererHint <em>Renderer Hint</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage#getControl()
 * @model
 * @generated
 */
public interface Control extends UIElement {

	/**
	 * Returns the value of the '<em><b>Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' attribute.
	 * @see #setProperty(String)
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage#getControl_Property()
	 * @model required="true"
	 * @generated
	 */
	String getProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.properties.viewmodel.Control#getProperty <em>Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' attribute.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(String value);

	/**
	 * Returns the value of the '<em><b>Renderer Hint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Renderer Hint</em>' attribute.
	 * @see #setRendererHint(String)
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage#getControl_RendererHint()
	 * @model
	 * @generated
	 */
	String getRendererHint();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.properties.viewmodel.Control#getRendererHint <em>Renderer Hint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Renderer Hint</em>' attribute.
	 * @see #getRendererHint()
	 * @generated
	 */
	void setRendererHint(String value);
} // Control
