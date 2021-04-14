/**
 */
package org.eclipse.papyrus.infra.properties.viewmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.infra.properties.viewmodel.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ViewmodelFactoryImpl extends EFactoryImpl implements ViewmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ViewmodelFactory init() {
		try {
			ViewmodelFactory theViewmodelFactory = (ViewmodelFactory)EPackage.Registry.INSTANCE.getEFactory(ViewmodelPackage.eNS_URI);
			if (theViewmodelFactory != null) {
				return theViewmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ViewmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewmodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ViewmodelPackage.VIEW_MODEL: return createViewModel();
			case ViewmodelPackage.UI_ELEMENT: return createUIElement();
			case ViewmodelPackage.PROPERTY: return createProperty();
			case ViewmodelPackage.CONTROL: return createControl();
			case ViewmodelPackage.VERTICAL_LAYOUT: return createVerticalLayout();
			case ViewmodelPackage.GRID_LAYOUT: return createGridLayout();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewModel createViewModel() {
		ViewModelImpl viewModel = new ViewModelImpl();
		return viewModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIElement createUIElement() {
		UIElementImpl uiElement = new UIElementImpl();
		return uiElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property createProperty() {
		PropertyImpl property = new PropertyImpl();
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Control createControl() {
		ControlImpl control = new ControlImpl();
		return control;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VerticalLayout createVerticalLayout() {
		VerticalLayoutImpl verticalLayout = new VerticalLayoutImpl();
		return verticalLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GridLayout createGridLayout() {
		GridLayoutImpl gridLayout = new GridLayoutImpl();
		return gridLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewmodelPackage getViewmodelPackage() {
		return (ViewmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ViewmodelPackage getPackage() {
		return ViewmodelPackage.eINSTANCE;
	}

} //ViewmodelFactoryImpl
