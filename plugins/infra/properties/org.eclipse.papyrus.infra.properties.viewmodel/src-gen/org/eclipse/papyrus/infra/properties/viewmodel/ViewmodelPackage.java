/**
 */
package org.eclipse.papyrus.infra.properties.viewmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelFactory
 * @model kind="package"
 * @generated
 */
public interface ViewmodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "viewmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/papyrus/properties/viewmodel/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "pvm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ViewmodelPackage eINSTANCE = org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.UIElementImpl <em>UI Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.UIElementImpl
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getUIElement()
	 * @generated
	 */
	int UI_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_ELEMENT__PROPERTIES = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_ELEMENT__ID = 1;

	/**
	 * The number of structural features of the '<em>UI Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>UI Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UI_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.ParentImpl <em>Parent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ParentImpl
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getParent()
	 * @generated
	 */
	int PARENT = 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT__PROPERTIES = UI_ELEMENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT__ID = UI_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT__CHILDREN = UI_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Parent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_FEATURE_COUNT = UI_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Parent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_OPERATION_COUNT = UI_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewModelImpl <em>View Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewModelImpl
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getViewModel()
	 * @generated
	 */
	int VIEW_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_MODEL__PROPERTIES = PARENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_MODEL__ID = PARENT__ID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_MODEL__CHILDREN = PARENT__CHILDREN;

	/**
	 * The number of structural features of the '<em>View Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_MODEL_FEATURE_COUNT = PARENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>View Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_MODEL_OPERATION_COUNT = PARENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.PropertyImpl
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.ControlImpl <em>Control</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ControlImpl
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getControl()
	 * @generated
	 */
	int CONTROL = 4;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__PROPERTIES = UI_ELEMENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__ID = UI_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__PROPERTY = UI_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Renderer Hint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__RENDERER_HINT = UI_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Control</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FEATURE_COUNT = UI_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Control</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_OPERATION_COUNT = UI_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.VerticalLayoutImpl <em>Vertical Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.VerticalLayoutImpl
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getVerticalLayout()
	 * @generated
	 */
	int VERTICAL_LAYOUT = 5;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_LAYOUT__PROPERTIES = PARENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_LAYOUT__ID = PARENT__ID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_LAYOUT__CHILDREN = PARENT__CHILDREN;

	/**
	 * The number of structural features of the '<em>Vertical Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_LAYOUT_FEATURE_COUNT = PARENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Vertical Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTICAL_LAYOUT_OPERATION_COUNT = PARENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.GridLayoutImpl <em>Grid Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.GridLayoutImpl
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getGridLayout()
	 * @generated
	 */
	int GRID_LAYOUT = 6;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_LAYOUT__PROPERTIES = PARENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_LAYOUT__ID = PARENT__ID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_LAYOUT__CHILDREN = PARENT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_LAYOUT__COLUMNS = PARENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Grid Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_LAYOUT_FEATURE_COUNT = PARENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Grid Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRID_LAYOUT_OPERATION_COUNT = PARENT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.infra.properties.viewmodel.ViewModel <em>View Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Model</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.ViewModel
	 * @generated
	 */
	EClass getViewModel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.infra.properties.viewmodel.Parent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parent</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.Parent
	 * @generated
	 */
	EClass getParent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.infra.properties.viewmodel.Parent#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.Parent#getChildren()
	 * @see #getParent()
	 * @generated
	 */
	EReference getParent_Children();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.infra.properties.viewmodel.UIElement <em>UI Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UI Element</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.UIElement
	 * @generated
	 */
	EClass getUIElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.infra.properties.viewmodel.UIElement#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.UIElement#getProperties()
	 * @see #getUIElement()
	 * @generated
	 */
	EReference getUIElement_Properties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.infra.properties.viewmodel.UIElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.UIElement#getId()
	 * @see #getUIElement()
	 * @generated
	 */
	EAttribute getUIElement_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.infra.properties.viewmodel.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.infra.properties.viewmodel.Property#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.Property#getKey()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Key();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.infra.properties.viewmodel.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.infra.properties.viewmodel.Control <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.Control
	 * @generated
	 */
	EClass getControl();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.infra.properties.viewmodel.Control#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Property</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.Control#getProperty()
	 * @see #getControl()
	 * @generated
	 */
	EAttribute getControl_Property();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.infra.properties.viewmodel.Control#getRendererHint <em>Renderer Hint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Renderer Hint</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.Control#getRendererHint()
	 * @see #getControl()
	 * @generated
	 */
	EAttribute getControl_RendererHint();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.infra.properties.viewmodel.VerticalLayout <em>Vertical Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertical Layout</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.VerticalLayout
	 * @generated
	 */
	EClass getVerticalLayout();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.infra.properties.viewmodel.GridLayout <em>Grid Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grid Layout</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.GridLayout
	 * @generated
	 */
	EClass getGridLayout();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.infra.properties.viewmodel.GridLayout#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Columns</em>'.
	 * @see org.eclipse.papyrus.infra.properties.viewmodel.GridLayout#getColumns()
	 * @see #getGridLayout()
	 * @generated
	 */
	EAttribute getGridLayout_Columns();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ViewmodelFactory getViewmodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewModelImpl <em>View Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewModelImpl
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getViewModel()
		 * @generated
		 */
		EClass VIEW_MODEL = eINSTANCE.getViewModel();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.ParentImpl <em>Parent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ParentImpl
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getParent()
		 * @generated
		 */
		EClass PARENT = eINSTANCE.getParent();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARENT__CHILDREN = eINSTANCE.getParent_Children();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.UIElementImpl <em>UI Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.UIElementImpl
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getUIElement()
		 * @generated
		 */
		EClass UI_ELEMENT = eINSTANCE.getUIElement();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UI_ELEMENT__PROPERTIES = eINSTANCE.getUIElement_Properties();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UI_ELEMENT__ID = eINSTANCE.getUIElement_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.PropertyImpl
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__KEY = eINSTANCE.getProperty_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.ControlImpl <em>Control</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ControlImpl
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getControl()
		 * @generated
		 */
		EClass CONTROL = eINSTANCE.getControl();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL__PROPERTY = eINSTANCE.getControl_Property();

		/**
		 * The meta object literal for the '<em><b>Renderer Hint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL__RENDERER_HINT = eINSTANCE.getControl_RendererHint();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.VerticalLayoutImpl <em>Vertical Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.VerticalLayoutImpl
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getVerticalLayout()
		 * @generated
		 */
		EClass VERTICAL_LAYOUT = eINSTANCE.getVerticalLayout();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.infra.properties.viewmodel.impl.GridLayoutImpl <em>Grid Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.GridLayoutImpl
		 * @see org.eclipse.papyrus.infra.properties.viewmodel.impl.ViewmodelPackageImpl#getGridLayout()
		 * @generated
		 */
		EClass GRID_LAYOUT = eINSTANCE.getGridLayout();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRID_LAYOUT__COLUMNS = eINSTANCE.getGridLayout_Columns();

	}

} //ViewmodelPackage
