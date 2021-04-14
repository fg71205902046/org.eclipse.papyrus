/**
 */
package org.eclipse.papyrus.infra.properties.viewmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelPackage
 * @generated
 */
public interface ViewmodelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ViewmodelFactory eINSTANCE = org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>View Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>View Model</em>'.
	 * @generated
	 */
	ViewModel createViewModel();

	/**
	 * Returns a new object of class '<em>UI Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>UI Element</em>'.
	 * @generated
	 */
	UIElement createUIElement();

	/**
	 * Returns a new object of class '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property</em>'.
	 * @generated
	 */
	Property createProperty();

	/**
	 * Returns a new object of class '<em>Control</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Control</em>'.
	 * @generated
	 */
	Control createControl();

	/**
	 * Returns a new object of class '<em>Vertical Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Vertical Layout</em>'.
	 * @generated
	 */
	VerticalLayout createVerticalLayout();

	/**
	 * Returns a new object of class '<em>Grid Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grid Layout</em>'.
	 * @generated
	 */
	GridLayout createGridLayout();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ViewmodelPackage getViewmodelPackage();

} //ViewmodelFactory
