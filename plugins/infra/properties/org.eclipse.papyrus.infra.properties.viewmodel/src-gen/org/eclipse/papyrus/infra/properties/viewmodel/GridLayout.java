/**
 */
package org.eclipse.papyrus.infra.properties.viewmodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grid Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.properties.viewmodel.GridLayout#getColumns <em>Columns</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage#getGridLayout()
 * @model
 * @generated
 */
public interface GridLayout extends Parent {
	/**
	 * Returns the value of the '<em><b>Columns</b></em>' attribute.
	 * The default value is <code>"2"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Columns</em>' attribute.
	 * @see #setColumns(int)
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage#getGridLayout_Columns()
	 * @model default="2" required="true"
	 * @generated
	 */
	int getColumns();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.properties.viewmodel.GridLayout#getColumns <em>Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Columns</em>' attribute.
	 * @see #getColumns()
	 * @generated
	 */
	void setColumns(int value);

} // GridLayout
