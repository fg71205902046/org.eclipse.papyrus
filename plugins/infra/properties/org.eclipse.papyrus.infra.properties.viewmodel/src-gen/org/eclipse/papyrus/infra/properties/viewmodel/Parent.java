/**
 */
package org.eclipse.papyrus.infra.properties.viewmodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.properties.viewmodel.Parent#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage#getParent()
 * @model abstract="true"
 * @generated
 */
public interface Parent extends UIElement {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.infra.properties.viewmodel.UIElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage#getParent_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<UIElement> getChildren();

} // Parent
