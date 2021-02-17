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
package org.eclipse.papyrus.gmf.codegen.genextension;

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
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionFactory
 * @model kind="package"
 * @generated
 */
public interface GenExtensionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "genextension";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/papyrus/gmf/2020/GenExtension";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "genextension";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GenExtensionPackage eINSTANCE = org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.CommentedElementImpl <em>Commented Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.CommentedElementImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getCommentedElement()
	 * @generated
	 */
	int COMMENTED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENTED_ELEMENT__COMMENT = 0;

	/**
	 * The number of structural features of the '<em>Commented Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENTED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.ExtendedGenViewImpl <em>Extended Gen View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.ExtendedGenViewImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getExtendedGenView()
	 * @generated
	 */
	int EXTENDED_GEN_VIEW = 0;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_GEN_VIEW__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Gen View</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_GEN_VIEW__GEN_VIEW = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_GEN_VIEW__IS_ABSTRACT = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Super Gen Views</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_GEN_VIEW__SUPER_GEN_VIEWS = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Prop Refresh Hook</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_GEN_VIEW__PROP_REFRESH_HOOK = COMMENTED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_GEN_VIEW__NAME = COMMENTED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Super Owned Edit Part</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_GEN_VIEW__SUPER_OWNED_EDIT_PART = COMMENTED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Extended Gen View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_GEN_VIEW_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.ExternalHookImpl <em>External Hook</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.ExternalHookImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getExternalHook()
	 * @generated
	 */
	int EXTERNAL_HOOK = 3;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_HOOK__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Classpath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_HOOK__CLASSPATH = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>External Hook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_HOOK_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.PropertyRefreshHookImpl <em>Property Refresh Hook</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.PropertyRefreshHookImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getPropertyRefreshHook()
	 * @generated
	 */
	int PROPERTY_REFRESH_HOOK = 2;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REFRESH_HOOK__COMMENT = EXTERNAL_HOOK__COMMENT;

	/**
	 * The feature id for the '<em><b>Classpath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REFRESH_HOOK__CLASSPATH = EXTERNAL_HOOK__CLASSPATH;

	/**
	 * The feature id for the '<em><b>Triggering Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REFRESH_HOOK__TRIGGERING_CONDITION = EXTERNAL_HOOK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REFRESH_HOOK__ACTION = EXTERNAL_HOOK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Property Refresh Hook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REFRESH_HOOK_FEATURE_COUNT = EXTERNAL_HOOK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificLocatorImpl <em>Specific Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificLocatorImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getSpecificLocator()
	 * @generated
	 */
	int SPECIFIC_LOCATOR = 4;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_LOCATOR__COMMENT = EXTERNAL_HOOK__COMMENT;

	/**
	 * The feature id for the '<em><b>Classpath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_LOCATOR__CLASSPATH = EXTERNAL_HOOK__CLASSPATH;

	/**
	 * The feature id for the '<em><b>Gen Child Side Affixed Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_LOCATOR__GEN_CHILD_SIDE_AFFIXED_NODE = EXTERNAL_HOOK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Specific Locator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_LOCATOR_FEATURE_COUNT = EXTERNAL_HOOK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.PapyrusExtensionRootNodeImpl <em>Papyrus Extension Root Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.PapyrusExtensionRootNodeImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getPapyrusExtensionRootNode()
	 * @generated
	 */
	int PAPYRUS_EXTENSION_ROOT_NODE = 5;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAPYRUS_EXTENSION_ROOT_NODE__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Extension Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Papyrus Extension Root Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAPYRUS_EXTENSION_ROOT_NODE_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateCanvasImpl <em>Alternate Canvas</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateCanvasImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getAlternateCanvas()
	 * @generated
	 */
	int ALTERNATE_CANVAS = 6;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_CANVAS__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Domain Diagram Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_CANVAS__DOMAIN_DIAGRAM_ELEMENT = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Alternate Top Level Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_CANVAS__ALTERNATE_TOP_LEVEL_NODES = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Alternate Link Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_CANVAS__ALTERNATE_LINK_NODES = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_CANVAS__DIAGRAM = COMMENTED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Alternate Canvas</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_CANVAS_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateGenTopLevelNodeImpl <em>Alternate Gen Top Level Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateGenTopLevelNodeImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getAlternateGenTopLevelNode()
	 * @generated
	 */
	int ALTERNATE_GEN_TOP_LEVEL_NODE = 7;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_GEN_TOP_LEVEL_NODE__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Gen Top Level Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_GEN_TOP_LEVEL_NODE__GEN_TOP_LEVEL_NODE = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type Model Facet</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_GEN_TOP_LEVEL_NODE__TYPE_MODEL_FACET = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Alternate Gen Top Level Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_GEN_TOP_LEVEL_NODE_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateGenLinkImpl <em>Alternate Gen Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateGenLinkImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getAlternateGenLink()
	 * @generated
	 */
	int ALTERNATE_GEN_LINK = 8;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_GEN_LINK__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Gen Link Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_GEN_LINK__GEN_LINK_NODE = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type Model Facet</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_GEN_LINK__TYPE_MODEL_FACET = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Alternate Gen Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATE_GEN_LINK_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.MutatingCanvasImpl <em>Mutating Canvas</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.MutatingCanvasImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getMutatingCanvas()
	 * @generated
	 */
	int MUTATING_CANVAS = 9;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MUTATING_CANVAS__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Alternate Canvases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MUTATING_CANVAS__ALTERNATE_CANVASES = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Mutating Canvas</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MUTATING_CANVAS_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.OwnedEditpartImpl <em>Owned Editpart</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.OwnedEditpartImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getOwnedEditpart()
	 * @generated
	 */
	int OWNED_EDITPART = 10;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OWNED_EDITPART__COMMENT = EXTERNAL_HOOK__COMMENT;

	/**
	 * The feature id for the '<em><b>Classpath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OWNED_EDITPART__CLASSPATH = EXTERNAL_HOOK__CLASSPATH;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OWNED_EDITPART__NAME = EXTERNAL_HOOK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Owned Editpart</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OWNED_EDITPART_FEATURE_COUNT = EXTERNAL_HOOK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificDiagramUpdaterImpl <em>Specific Diagram Updater</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificDiagramUpdaterImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getSpecificDiagramUpdater()
	 * @generated
	 */
	int SPECIFIC_DIAGRAM_UPDATER = 11;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_DIAGRAM_UPDATER__COMMENT = EXTERNAL_HOOK__COMMENT;

	/**
	 * The feature id for the '<em><b>Classpath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_DIAGRAM_UPDATER__CLASSPATH = EXTERNAL_HOOK__CLASSPATH;

	/**
	 * The feature id for the '<em><b>Gen Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_DIAGRAM_UPDATER__GEN_NODE = EXTERNAL_HOOK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Specific Diagram Updater</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_DIAGRAM_UPDATER_FEATURE_COUNT = EXTERNAL_HOOK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.GenNodeConstraintImpl <em>Gen Node Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenNodeConstraintImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getGenNodeConstraint()
	 * @generated
	 */
	int GEN_NODE_CONSTRAINT = 12;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEN_NODE_CONSTRAINT__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Gen Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEN_NODE_CONSTRAINT__GEN_NODE = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Gen Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEN_NODE_CONSTRAINT__GEN_CONSTRAINT = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Gen Node Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEN_NODE_CONSTRAINT_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificLocatorExternalLabelImpl <em>Specific Locator External Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificLocatorExternalLabelImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getSpecificLocatorExternalLabel()
	 * @generated
	 */
	int SPECIFIC_LOCATOR_EXTERNAL_LABEL = 13;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_LOCATOR_EXTERNAL_LABEL__COMMENT = EXTERNAL_HOOK__COMMENT;

	/**
	 * The feature id for the '<em><b>Classpath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_LOCATOR_EXTERNAL_LABEL__CLASSPATH = EXTERNAL_HOOK__CLASSPATH;

	/**
	 * The feature id for the '<em><b>Gen External Node Label</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_LOCATOR_EXTERNAL_LABEL__GEN_EXTERNAL_NODE_LABEL = EXTERNAL_HOOK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Specific Locator External Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_LOCATOR_EXTERNAL_LABEL_FEATURE_COUNT = EXTERNAL_HOOK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AdditionalEditPartCandiesImpl <em>Additional Edit Part Candies</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.AdditionalEditPartCandiesImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getAdditionalEditPartCandies()
	 * @generated
	 */
	int ADDITIONAL_EDIT_PART_CANDIES = 14;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_EDIT_PART_CANDIES__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Base Edit Helper Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_EDIT_PART_CANDIES__BASE_EDIT_HELPER_PACKAGE = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Additional Edit Part Candies</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITIONAL_EDIT_PART_CANDIES_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.EditPartUsingDeleteServiceImpl <em>Edit Part Using Delete Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.EditPartUsingDeleteServiceImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getEditPartUsingDeleteService()
	 * @generated
	 */
	int EDIT_PART_USING_DELETE_SERVICE = 15;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_PART_USING_DELETE_SERVICE__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_PART_USING_DELETE_SERVICE__NAME = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Gen View</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_PART_USING_DELETE_SERVICE__GEN_VIEW = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Edit Part Using Delete Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_PART_USING_DELETE_SERVICE_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.EditPartUsingReorientServiceImpl <em>Edit Part Using Reorient Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.EditPartUsingReorientServiceImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getEditPartUsingReorientService()
	 * @generated
	 */
	int EDIT_PART_USING_REORIENT_SERVICE = 16;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_PART_USING_REORIENT_SERVICE__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_PART_USING_REORIENT_SERVICE__NAME = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Gen View</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_PART_USING_REORIENT_SERVICE__GEN_VIEW = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Edit Part Using Reorient Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDIT_PART_USING_REORIENT_SERVICE_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.LabelVisibilityPreferenceImpl <em>Label Visibility Preference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.LabelVisibilityPreferenceImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getLabelVisibilityPreference()
	 * @generated
	 */
	int LABEL_VISIBILITY_PREFERENCE = 17;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_VISIBILITY_PREFERENCE__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_VISIBILITY_PREFERENCE__ROLE = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Icon Path Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_VISIBILITY_PREFERENCE__ICON_PATH_ROLE = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Link Labels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_VISIBILITY_PREFERENCE__LINK_LABELS = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>External Node Labels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_VISIBILITY_PREFERENCE__EXTERNAL_NODE_LABELS = COMMENTED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Visible By Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_VISIBILITY_PREFERENCE__VISIBLE_BY_DEFAULT = COMMENTED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Label Visibility Preference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_VISIBILITY_PREFERENCE_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.CompartmentVisibilityPreferenceImpl <em>Compartment Visibility Preference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.CompartmentVisibilityPreferenceImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getCompartmentVisibilityPreference()
	 * @generated
	 */
	int COMPARTMENT_VISIBILITY_PREFERENCE = 18;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_VISIBILITY_PREFERENCE__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Visible By Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_VISIBILITY_PREFERENCE__VISIBLE_BY_DEFAULT = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Compartments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_VISIBILITY_PREFERENCE__COMPARTMENTS = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Compartment Visibility Preference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_VISIBILITY_PREFERENCE_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.CompartmentTitleVisibilityPreferenceImpl <em>Compartment Title Visibility Preference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.CompartmentTitleVisibilityPreferenceImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getCompartmentTitleVisibilityPreference()
	 * @generated
	 */
	int COMPARTMENT_TITLE_VISIBILITY_PREFERENCE = 19;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_TITLE_VISIBILITY_PREFERENCE__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Visible By Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_TITLE_VISIBILITY_PREFERENCE__VISIBLE_BY_DEFAULT = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Compartments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_TITLE_VISIBILITY_PREFERENCE__COMPARTMENTS = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Compartment Title Visibility Preference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARTMENT_TITLE_VISIBILITY_PREFERENCE_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.ConstrainedByReferenceCompartmentItemSemanticEditPolicyImpl <em>Constrained By Reference Compartment Item Semantic Edit Policy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.ConstrainedByReferenceCompartmentItemSemanticEditPolicyImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getConstrainedByReferenceCompartmentItemSemanticEditPolicy()
	 * @generated
	 */
	int CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY = 20;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Gen View</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY__GEN_VIEW = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constrained By Reference Compartment Item Semantic Edit Policy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.GenerateUsingElementTypeCreationCommandImpl <em>Generate Using Element Type Creation Command</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenerateUsingElementTypeCreationCommandImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getGenerateUsingElementTypeCreationCommand()
	 * @generated
	 */
	int GENERATE_USING_ELEMENT_TYPE_CREATION_COMMAND = 21;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATE_USING_ELEMENT_TYPE_CREATION_COMMAND__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The number of structural features of the '<em>Generate Using Element Type Creation Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATE_USING_ELEMENT_TYPE_CREATION_COMMAND_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.CustomDiagramUpdaterSingletonImpl <em>Custom Diagram Updater Singleton</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.CustomDiagramUpdaterSingletonImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getCustomDiagramUpdaterSingleton()
	 * @generated
	 */
	int CUSTOM_DIAGRAM_UPDATER_SINGLETON = 22;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_DIAGRAM_UPDATER_SINGLETON__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Singleton Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_DIAGRAM_UPDATER_SINGLETON__SINGLETON_PATH = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Custom Diagram Updater Singleton</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_DIAGRAM_UPDATER_SINGLETON_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificNodePlateImpl <em>Specific Node Plate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificNodePlateImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getSpecificNodePlate()
	 * @generated
	 */
	int SPECIFIC_NODE_PLATE = 23;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_NODE_PLATE__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Edit Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_NODE_PLATE__EDIT_PARTS = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_NODE_PLATE__NAME = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Node Plate Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_NODE_PLATE__NODE_PLATE_QUALIFIED_NAME = COMMENTED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Specific Node Plate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_NODE_PLATE_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.GenVisualTypeProviderImpl <em>Gen Visual Type Provider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenVisualTypeProviderImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getGenVisualTypeProvider()
	 * @generated
	 */
	int GEN_VISUAL_TYPE_PROVIDER = 24;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEN_VISUAL_TYPE_PROVIDER__COMMENT = COMMENTED_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEN_VISUAL_TYPE_PROVIDER__CLASS_NAME = COMMENTED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Gen Visual Type Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEN_VISUAL_TYPE_PROVIDER_FEATURE_COUNT = COMMENTED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.VisualIDOverrideImpl <em>Visual ID Override</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.VisualIDOverrideImpl
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getVisualIDOverride()
	 * @generated
	 */
	int VISUAL_ID_OVERRIDE = 25;

	/**
	 * The feature id for the '<em><b>Gen View</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISUAL_ID_OVERRIDE__GEN_VIEW = 0;

	/**
	 * The feature id for the '<em><b>Visual ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISUAL_ID_OVERRIDE__VISUAL_ID = 1;

	/**
	 * The feature id for the '<em><b>Child</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISUAL_ID_OVERRIDE__CHILD = 2;

	/**
	 * The number of structural features of the '<em>Visual ID Override</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISUAL_ID_OVERRIDE_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView <em>Extended Gen View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Gen View</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView
	 * @generated
	 */
	EClass getExtendedGenView();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#getGenView <em>Gen View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gen View</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#getGenView()
	 * @see #getExtendedGenView()
	 * @generated
	 */
	EReference getExtendedGenView_GenView();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#isIsAbstract <em>Is Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#isIsAbstract()
	 * @see #getExtendedGenView()
	 * @generated
	 */
	EAttribute getExtendedGenView_IsAbstract();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#getSuperGenViews <em>Super Gen Views</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Super Gen Views</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#getSuperGenViews()
	 * @see #getExtendedGenView()
	 * @generated
	 */
	EReference getExtendedGenView_SuperGenViews();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#getPropRefreshHook <em>Prop Refresh Hook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Prop Refresh Hook</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#getPropRefreshHook()
	 * @see #getExtendedGenView()
	 * @generated
	 */
	EReference getExtendedGenView_PropRefreshHook();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#getName()
	 * @see #getExtendedGenView()
	 * @generated
	 */
	EAttribute getExtendedGenView_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#getSuperOwnedEditPart <em>Super Owned Edit Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Super Owned Edit Part</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView#getSuperOwnedEditPart()
	 * @see #getExtendedGenView()
	 * @generated
	 */
	EAttribute getExtendedGenView_SuperOwnedEditPart();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.CommentedElement <em>Commented Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Commented Element</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.CommentedElement
	 * @generated
	 */
	EClass getCommentedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.CommentedElement#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.CommentedElement#getComment()
	 * @see #getCommentedElement()
	 * @generated
	 */
	EAttribute getCommentedElement_Comment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.PropertyRefreshHook <em>Property Refresh Hook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Refresh Hook</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.PropertyRefreshHook
	 * @generated
	 */
	EClass getPropertyRefreshHook();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.PropertyRefreshHook#getTriggeringCondition <em>Triggering Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Triggering Condition</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.PropertyRefreshHook#getTriggeringCondition()
	 * @see #getPropertyRefreshHook()
	 * @generated
	 */
	EAttribute getPropertyRefreshHook_TriggeringCondition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.PropertyRefreshHook#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.PropertyRefreshHook#getAction()
	 * @see #getPropertyRefreshHook()
	 * @generated
	 */
	EAttribute getPropertyRefreshHook_Action();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.ExternalHook <em>External Hook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Hook</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ExternalHook
	 * @generated
	 */
	EClass getExternalHook();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.ExternalHook#getClasspath <em>Classpath</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Classpath</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ExternalHook#getClasspath()
	 * @see #getExternalHook()
	 * @generated
	 */
	EAttribute getExternalHook_Classpath();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocator <em>Specific Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific Locator</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocator
	 * @generated
	 */
	EClass getSpecificLocator();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocator#getGenChildSideAffixedNode <em>Gen Child Side Affixed Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gen Child Side Affixed Node</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocator#getGenChildSideAffixedNode()
	 * @see #getSpecificLocator()
	 * @generated
	 */
	EReference getSpecificLocator_GenChildSideAffixedNode();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.PapyrusExtensionRootNode <em>Papyrus Extension Root Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Papyrus Extension Root Node</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.PapyrusExtensionRootNode
	 * @generated
	 */
	EClass getPapyrusExtensionRootNode();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.PapyrusExtensionRootNode#getExtensionNodes <em>Extension Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extension Nodes</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.PapyrusExtensionRootNode#getExtensionNodes()
	 * @see #getPapyrusExtensionRootNode()
	 * @generated
	 */
	EReference getPapyrusExtensionRootNode_ExtensionNodes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas <em>Alternate Canvas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alternate Canvas</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas
	 * @generated
	 */
	EClass getAlternateCanvas();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getDomainDiagramElement <em>Domain Diagram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Domain Diagram Element</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getDomainDiagramElement()
	 * @see #getAlternateCanvas()
	 * @generated
	 */
	EReference getAlternateCanvas_DomainDiagramElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getAlternateTopLevelNodes <em>Alternate Top Level Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Alternate Top Level Nodes</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getAlternateTopLevelNodes()
	 * @see #getAlternateCanvas()
	 * @generated
	 */
	EReference getAlternateCanvas_AlternateTopLevelNodes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getAlternateLinkNodes <em>Alternate Link Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Alternate Link Nodes</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getAlternateLinkNodes()
	 * @see #getAlternateCanvas()
	 * @generated
	 */
	EReference getAlternateCanvas_AlternateLinkNodes();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Diagram</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getDiagram()
	 * @see #getAlternateCanvas()
	 * @generated
	 */
	EReference getAlternateCanvas_Diagram();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode <em>Alternate Gen Top Level Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alternate Gen Top Level Node</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode
	 * @generated
	 */
	EClass getAlternateGenTopLevelNode();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode#getGenTopLevelNode <em>Gen Top Level Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gen Top Level Node</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode#getGenTopLevelNode()
	 * @see #getAlternateGenTopLevelNode()
	 * @generated
	 */
	EReference getAlternateGenTopLevelNode_GenTopLevelNode();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode#getTypeModelFacet <em>Type Model Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type Model Facet</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode#getTypeModelFacet()
	 * @see #getAlternateGenTopLevelNode()
	 * @generated
	 */
	EReference getAlternateGenTopLevelNode_TypeModelFacet();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink <em>Alternate Gen Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alternate Gen Link</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink
	 * @generated
	 */
	EClass getAlternateGenLink();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink#getGenLinkNode <em>Gen Link Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gen Link Node</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink#getGenLinkNode()
	 * @see #getAlternateGenLink()
	 * @generated
	 */
	EReference getAlternateGenLink_GenLinkNode();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink#getTypeModelFacet <em>Type Model Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type Model Facet</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink#getTypeModelFacet()
	 * @see #getAlternateGenLink()
	 * @generated
	 */
	EReference getAlternateGenLink_TypeModelFacet();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.MutatingCanvas <em>Mutating Canvas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mutating Canvas</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.MutatingCanvas
	 * @generated
	 */
	EClass getMutatingCanvas();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.MutatingCanvas#getAlternateCanvases <em>Alternate Canvases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Alternate Canvases</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.MutatingCanvas#getAlternateCanvases()
	 * @see #getMutatingCanvas()
	 * @generated
	 */
	EReference getMutatingCanvas_AlternateCanvases();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.OwnedEditpart <em>Owned Editpart</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Owned Editpart</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.OwnedEditpart
	 * @generated
	 */
	EClass getOwnedEditpart();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.OwnedEditpart#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.OwnedEditpart#getName()
	 * @see #getOwnedEditpart()
	 * @generated
	 */
	EAttribute getOwnedEditpart_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificDiagramUpdater <em>Specific Diagram Updater</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific Diagram Updater</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.SpecificDiagramUpdater
	 * @generated
	 */
	EClass getSpecificDiagramUpdater();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificDiagramUpdater#getGenNode <em>Gen Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gen Node</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.SpecificDiagramUpdater#getGenNode()
	 * @see #getSpecificDiagramUpdater()
	 * @generated
	 */
	EReference getSpecificDiagramUpdater_GenNode();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint <em>Gen Node Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gen Node Constraint</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint
	 * @generated
	 */
	EClass getGenNodeConstraint();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint#getGenNode <em>Gen Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gen Node</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint#getGenNode()
	 * @see #getGenNodeConstraint()
	 * @generated
	 */
	EReference getGenNodeConstraint_GenNode();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint#getGenConstraint <em>Gen Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gen Constraint</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint#getGenConstraint()
	 * @see #getGenNodeConstraint()
	 * @generated
	 */
	EReference getGenNodeConstraint_GenConstraint();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocatorExternalLabel <em>Specific Locator External Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific Locator External Label</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocatorExternalLabel
	 * @generated
	 */
	EClass getSpecificLocatorExternalLabel();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocatorExternalLabel#getGenExternalNodeLabel <em>Gen External Node Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gen External Node Label</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocatorExternalLabel#getGenExternalNodeLabel()
	 * @see #getSpecificLocatorExternalLabel()
	 * @generated
	 */
	EReference getSpecificLocatorExternalLabel_GenExternalNodeLabel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.AdditionalEditPartCandies <em>Additional Edit Part Candies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Additional Edit Part Candies</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AdditionalEditPartCandies
	 * @generated
	 */
	EClass getAdditionalEditPartCandies();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.AdditionalEditPartCandies#getBaseEditHelperPackage <em>Base Edit Helper Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Edit Helper Package</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.AdditionalEditPartCandies#getBaseEditHelperPackage()
	 * @see #getAdditionalEditPartCandies()
	 * @generated
	 */
	EAttribute getAdditionalEditPartCandies_BaseEditHelperPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingDeleteService <em>Edit Part Using Delete Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edit Part Using Delete Service</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingDeleteService
	 * @generated
	 */
	EClass getEditPartUsingDeleteService();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingDeleteService#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingDeleteService#getName()
	 * @see #getEditPartUsingDeleteService()
	 * @generated
	 */
	EAttribute getEditPartUsingDeleteService_Name();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingDeleteService#getGenView <em>Gen View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gen View</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingDeleteService#getGenView()
	 * @see #getEditPartUsingDeleteService()
	 * @generated
	 */
	EReference getEditPartUsingDeleteService_GenView();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingReorientService <em>Edit Part Using Reorient Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edit Part Using Reorient Service</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingReorientService
	 * @generated
	 */
	EClass getEditPartUsingReorientService();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingReorientService#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingReorientService#getName()
	 * @see #getEditPartUsingReorientService()
	 * @generated
	 */
	EAttribute getEditPartUsingReorientService_Name();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingReorientService#getGenView <em>Gen View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gen View</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingReorientService#getGenView()
	 * @see #getEditPartUsingReorientService()
	 * @generated
	 */
	EReference getEditPartUsingReorientService_GenView();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference <em>Label Visibility Preference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label Visibility Preference</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference
	 * @generated
	 */
	EClass getLabelVisibilityPreference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Role</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference#getRole()
	 * @see #getLabelVisibilityPreference()
	 * @generated
	 */
	EAttribute getLabelVisibilityPreference_Role();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference#getIconPathRole <em>Icon Path Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon Path Role</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference#getIconPathRole()
	 * @see #getLabelVisibilityPreference()
	 * @generated
	 */
	EAttribute getLabelVisibilityPreference_IconPathRole();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference#getLinkLabels <em>Link Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Link Labels</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference#getLinkLabels()
	 * @see #getLabelVisibilityPreference()
	 * @generated
	 */
	EReference getLabelVisibilityPreference_LinkLabels();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference#getExternalNodeLabels <em>External Node Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>External Node Labels</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference#getExternalNodeLabels()
	 * @see #getLabelVisibilityPreference()
	 * @generated
	 */
	EReference getLabelVisibilityPreference_ExternalNodeLabels();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference#isVisibleByDefault <em>Visible By Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visible By Default</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference#isVisibleByDefault()
	 * @see #getLabelVisibilityPreference()
	 * @generated
	 */
	EAttribute getLabelVisibilityPreference_VisibleByDefault();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.CompartmentVisibilityPreference <em>Compartment Visibility Preference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compartment Visibility Preference</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.CompartmentVisibilityPreference
	 * @generated
	 */
	EClass getCompartmentVisibilityPreference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.CompartmentVisibilityPreference#isVisibleByDefault <em>Visible By Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visible By Default</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.CompartmentVisibilityPreference#isVisibleByDefault()
	 * @see #getCompartmentVisibilityPreference()
	 * @generated
	 */
	EAttribute getCompartmentVisibilityPreference_VisibleByDefault();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.CompartmentVisibilityPreference#getCompartments <em>Compartments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Compartments</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.CompartmentVisibilityPreference#getCompartments()
	 * @see #getCompartmentVisibilityPreference()
	 * @generated
	 */
	EReference getCompartmentVisibilityPreference_Compartments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.CompartmentTitleVisibilityPreference <em>Compartment Title Visibility Preference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compartment Title Visibility Preference</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.CompartmentTitleVisibilityPreference
	 * @generated
	 */
	EClass getCompartmentTitleVisibilityPreference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.CompartmentTitleVisibilityPreference#isVisibleByDefault <em>Visible By Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visible By Default</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.CompartmentTitleVisibilityPreference#isVisibleByDefault()
	 * @see #getCompartmentTitleVisibilityPreference()
	 * @generated
	 */
	EAttribute getCompartmentTitleVisibilityPreference_VisibleByDefault();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.CompartmentTitleVisibilityPreference#getCompartments <em>Compartments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Compartments</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.CompartmentTitleVisibilityPreference#getCompartments()
	 * @see #getCompartmentTitleVisibilityPreference()
	 * @generated
	 */
	EReference getCompartmentTitleVisibilityPreference_Compartments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.ConstrainedByReferenceCompartmentItemSemanticEditPolicy <em>Constrained By Reference Compartment Item Semantic Edit Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constrained By Reference Compartment Item Semantic Edit Policy</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ConstrainedByReferenceCompartmentItemSemanticEditPolicy
	 * @generated
	 */
	EClass getConstrainedByReferenceCompartmentItemSemanticEditPolicy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.ConstrainedByReferenceCompartmentItemSemanticEditPolicy#getGenView <em>Gen View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gen View</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.ConstrainedByReferenceCompartmentItemSemanticEditPolicy#getGenView()
	 * @see #getConstrainedByReferenceCompartmentItemSemanticEditPolicy()
	 * @generated
	 */
	EReference getConstrainedByReferenceCompartmentItemSemanticEditPolicy_GenView();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.GenerateUsingElementTypeCreationCommand <em>Generate Using Element Type Creation Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generate Using Element Type Creation Command</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenerateUsingElementTypeCreationCommand
	 * @generated
	 */
	EClass getGenerateUsingElementTypeCreationCommand();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.CustomDiagramUpdaterSingleton <em>Custom Diagram Updater Singleton</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Diagram Updater Singleton</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.CustomDiagramUpdaterSingleton
	 * @generated
	 */
	EClass getCustomDiagramUpdaterSingleton();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.CustomDiagramUpdaterSingleton#getSingletonPath <em>Singleton Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Singleton Path</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.CustomDiagramUpdaterSingleton#getSingletonPath()
	 * @see #getCustomDiagramUpdaterSingleton()
	 * @generated
	 */
	EAttribute getCustomDiagramUpdaterSingleton_SingletonPath();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificNodePlate <em>Specific Node Plate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific Node Plate</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.SpecificNodePlate
	 * @generated
	 */
	EClass getSpecificNodePlate();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificNodePlate#getEditParts <em>Edit Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Edit Parts</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.SpecificNodePlate#getEditParts()
	 * @see #getSpecificNodePlate()
	 * @generated
	 */
	EReference getSpecificNodePlate_EditParts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificNodePlate#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.SpecificNodePlate#getName()
	 * @see #getSpecificNodePlate()
	 * @generated
	 */
	EAttribute getSpecificNodePlate_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificNodePlate#getNodePlateQualifiedName <em>Node Plate Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Plate Qualified Name</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.SpecificNodePlate#getNodePlateQualifiedName()
	 * @see #getSpecificNodePlate()
	 * @generated
	 */
	EAttribute getSpecificNodePlate_NodePlateQualifiedName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.GenVisualTypeProvider <em>Gen Visual Type Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gen Visual Type Provider</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenVisualTypeProvider
	 * @generated
	 */
	EClass getGenVisualTypeProvider();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.GenVisualTypeProvider#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenVisualTypeProvider#getClassName()
	 * @see #getGenVisualTypeProvider()
	 * @generated
	 */
	EAttribute getGenVisualTypeProvider_ClassName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride <em>Visual ID Override</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Visual ID Override</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride
	 * @generated
	 */
	EClass getVisualIDOverride();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getGenView <em>Gen View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gen View</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getGenView()
	 * @see #getVisualIDOverride()
	 * @generated
	 */
	EReference getVisualIDOverride_GenView();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getVisualID <em>Visual ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visual ID</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getVisualID()
	 * @see #getVisualIDOverride()
	 * @generated
	 */
	EAttribute getVisualIDOverride_VisualID();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Child</em>'.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getChild()
	 * @see #getVisualIDOverride()
	 * @generated
	 */
	EReference getVisualIDOverride_Child();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GenExtensionFactory getGenExtensionFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.ExtendedGenViewImpl <em>Extended Gen View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.ExtendedGenViewImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getExtendedGenView()
		 * @generated
		 */
		EClass EXTENDED_GEN_VIEW = eINSTANCE.getExtendedGenView();

		/**
		 * The meta object literal for the '<em><b>Gen View</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTENDED_GEN_VIEW__GEN_VIEW = eINSTANCE.getExtendedGenView_GenView();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_GEN_VIEW__IS_ABSTRACT = eINSTANCE.getExtendedGenView_IsAbstract();

		/**
		 * The meta object literal for the '<em><b>Super Gen Views</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTENDED_GEN_VIEW__SUPER_GEN_VIEWS = eINSTANCE.getExtendedGenView_SuperGenViews();

		/**
		 * The meta object literal for the '<em><b>Prop Refresh Hook</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTENDED_GEN_VIEW__PROP_REFRESH_HOOK = eINSTANCE.getExtendedGenView_PropRefreshHook();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_GEN_VIEW__NAME = eINSTANCE.getExtendedGenView_Name();

		/**
		 * The meta object literal for the '<em><b>Super Owned Edit Part</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_GEN_VIEW__SUPER_OWNED_EDIT_PART = eINSTANCE.getExtendedGenView_SuperOwnedEditPart();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.CommentedElementImpl <em>Commented Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.CommentedElementImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getCommentedElement()
		 * @generated
		 */
		EClass COMMENTED_ELEMENT = eINSTANCE.getCommentedElement();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENTED_ELEMENT__COMMENT = eINSTANCE.getCommentedElement_Comment();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.PropertyRefreshHookImpl <em>Property Refresh Hook</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.PropertyRefreshHookImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getPropertyRefreshHook()
		 * @generated
		 */
		EClass PROPERTY_REFRESH_HOOK = eINSTANCE.getPropertyRefreshHook();

		/**
		 * The meta object literal for the '<em><b>Triggering Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY_REFRESH_HOOK__TRIGGERING_CONDITION = eINSTANCE.getPropertyRefreshHook_TriggeringCondition();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY_REFRESH_HOOK__ACTION = eINSTANCE.getPropertyRefreshHook_Action();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.ExternalHookImpl <em>External Hook</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.ExternalHookImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getExternalHook()
		 * @generated
		 */
		EClass EXTERNAL_HOOK = eINSTANCE.getExternalHook();

		/**
		 * The meta object literal for the '<em><b>Classpath</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_HOOK__CLASSPATH = eINSTANCE.getExternalHook_Classpath();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificLocatorImpl <em>Specific Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificLocatorImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getSpecificLocator()
		 * @generated
		 */
		EClass SPECIFIC_LOCATOR = eINSTANCE.getSpecificLocator();

		/**
		 * The meta object literal for the '<em><b>Gen Child Side Affixed Node</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFIC_LOCATOR__GEN_CHILD_SIDE_AFFIXED_NODE = eINSTANCE.getSpecificLocator_GenChildSideAffixedNode();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.PapyrusExtensionRootNodeImpl <em>Papyrus Extension Root Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.PapyrusExtensionRootNodeImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getPapyrusExtensionRootNode()
		 * @generated
		 */
		EClass PAPYRUS_EXTENSION_ROOT_NODE = eINSTANCE.getPapyrusExtensionRootNode();

		/**
		 * The meta object literal for the '<em><b>Extension Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES = eINSTANCE.getPapyrusExtensionRootNode_ExtensionNodes();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateCanvasImpl <em>Alternate Canvas</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateCanvasImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getAlternateCanvas()
		 * @generated
		 */
		EClass ALTERNATE_CANVAS = eINSTANCE.getAlternateCanvas();

		/**
		 * The meta object literal for the '<em><b>Domain Diagram Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATE_CANVAS__DOMAIN_DIAGRAM_ELEMENT = eINSTANCE.getAlternateCanvas_DomainDiagramElement();

		/**
		 * The meta object literal for the '<em><b>Alternate Top Level Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATE_CANVAS__ALTERNATE_TOP_LEVEL_NODES = eINSTANCE.getAlternateCanvas_AlternateTopLevelNodes();

		/**
		 * The meta object literal for the '<em><b>Alternate Link Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATE_CANVAS__ALTERNATE_LINK_NODES = eINSTANCE.getAlternateCanvas_AlternateLinkNodes();

		/**
		 * The meta object literal for the '<em><b>Diagram</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATE_CANVAS__DIAGRAM = eINSTANCE.getAlternateCanvas_Diagram();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateGenTopLevelNodeImpl <em>Alternate Gen Top Level Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateGenTopLevelNodeImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getAlternateGenTopLevelNode()
		 * @generated
		 */
		EClass ALTERNATE_GEN_TOP_LEVEL_NODE = eINSTANCE.getAlternateGenTopLevelNode();

		/**
		 * The meta object literal for the '<em><b>Gen Top Level Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATE_GEN_TOP_LEVEL_NODE__GEN_TOP_LEVEL_NODE = eINSTANCE.getAlternateGenTopLevelNode_GenTopLevelNode();

		/**
		 * The meta object literal for the '<em><b>Type Model Facet</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATE_GEN_TOP_LEVEL_NODE__TYPE_MODEL_FACET = eINSTANCE.getAlternateGenTopLevelNode_TypeModelFacet();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateGenLinkImpl <em>Alternate Gen Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.AlternateGenLinkImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getAlternateGenLink()
		 * @generated
		 */
		EClass ALTERNATE_GEN_LINK = eINSTANCE.getAlternateGenLink();

		/**
		 * The meta object literal for the '<em><b>Gen Link Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATE_GEN_LINK__GEN_LINK_NODE = eINSTANCE.getAlternateGenLink_GenLinkNode();

		/**
		 * The meta object literal for the '<em><b>Type Model Facet</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATE_GEN_LINK__TYPE_MODEL_FACET = eINSTANCE.getAlternateGenLink_TypeModelFacet();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.MutatingCanvasImpl <em>Mutating Canvas</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.MutatingCanvasImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getMutatingCanvas()
		 * @generated
		 */
		EClass MUTATING_CANVAS = eINSTANCE.getMutatingCanvas();

		/**
		 * The meta object literal for the '<em><b>Alternate Canvases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MUTATING_CANVAS__ALTERNATE_CANVASES = eINSTANCE.getMutatingCanvas_AlternateCanvases();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.OwnedEditpartImpl <em>Owned Editpart</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.OwnedEditpartImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getOwnedEditpart()
		 * @generated
		 */
		EClass OWNED_EDITPART = eINSTANCE.getOwnedEditpart();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OWNED_EDITPART__NAME = eINSTANCE.getOwnedEditpart_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificDiagramUpdaterImpl <em>Specific Diagram Updater</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificDiagramUpdaterImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getSpecificDiagramUpdater()
		 * @generated
		 */
		EClass SPECIFIC_DIAGRAM_UPDATER = eINSTANCE.getSpecificDiagramUpdater();

		/**
		 * The meta object literal for the '<em><b>Gen Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFIC_DIAGRAM_UPDATER__GEN_NODE = eINSTANCE.getSpecificDiagramUpdater_GenNode();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.GenNodeConstraintImpl <em>Gen Node Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenNodeConstraintImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getGenNodeConstraint()
		 * @generated
		 */
		EClass GEN_NODE_CONSTRAINT = eINSTANCE.getGenNodeConstraint();

		/**
		 * The meta object literal for the '<em><b>Gen Node</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEN_NODE_CONSTRAINT__GEN_NODE = eINSTANCE.getGenNodeConstraint_GenNode();

		/**
		 * The meta object literal for the '<em><b>Gen Constraint</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEN_NODE_CONSTRAINT__GEN_CONSTRAINT = eINSTANCE.getGenNodeConstraint_GenConstraint();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificLocatorExternalLabelImpl <em>Specific Locator External Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificLocatorExternalLabelImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getSpecificLocatorExternalLabel()
		 * @generated
		 */
		EClass SPECIFIC_LOCATOR_EXTERNAL_LABEL = eINSTANCE.getSpecificLocatorExternalLabel();

		/**
		 * The meta object literal for the '<em><b>Gen External Node Label</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFIC_LOCATOR_EXTERNAL_LABEL__GEN_EXTERNAL_NODE_LABEL = eINSTANCE.getSpecificLocatorExternalLabel_GenExternalNodeLabel();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.AdditionalEditPartCandiesImpl <em>Additional Edit Part Candies</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.AdditionalEditPartCandiesImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getAdditionalEditPartCandies()
		 * @generated
		 */
		EClass ADDITIONAL_EDIT_PART_CANDIES = eINSTANCE.getAdditionalEditPartCandies();

		/**
		 * The meta object literal for the '<em><b>Base Edit Helper Package</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADDITIONAL_EDIT_PART_CANDIES__BASE_EDIT_HELPER_PACKAGE = eINSTANCE.getAdditionalEditPartCandies_BaseEditHelperPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.EditPartUsingDeleteServiceImpl <em>Edit Part Using Delete Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.EditPartUsingDeleteServiceImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getEditPartUsingDeleteService()
		 * @generated
		 */
		EClass EDIT_PART_USING_DELETE_SERVICE = eINSTANCE.getEditPartUsingDeleteService();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDIT_PART_USING_DELETE_SERVICE__NAME = eINSTANCE.getEditPartUsingDeleteService_Name();

		/**
		 * The meta object literal for the '<em><b>Gen View</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDIT_PART_USING_DELETE_SERVICE__GEN_VIEW = eINSTANCE.getEditPartUsingDeleteService_GenView();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.EditPartUsingReorientServiceImpl <em>Edit Part Using Reorient Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.EditPartUsingReorientServiceImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getEditPartUsingReorientService()
		 * @generated
		 */
		EClass EDIT_PART_USING_REORIENT_SERVICE = eINSTANCE.getEditPartUsingReorientService();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDIT_PART_USING_REORIENT_SERVICE__NAME = eINSTANCE.getEditPartUsingReorientService_Name();

		/**
		 * The meta object literal for the '<em><b>Gen View</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDIT_PART_USING_REORIENT_SERVICE__GEN_VIEW = eINSTANCE.getEditPartUsingReorientService_GenView();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.LabelVisibilityPreferenceImpl <em>Label Visibility Preference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.LabelVisibilityPreferenceImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getLabelVisibilityPreference()
		 * @generated
		 */
		EClass LABEL_VISIBILITY_PREFERENCE = eINSTANCE.getLabelVisibilityPreference();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_VISIBILITY_PREFERENCE__ROLE = eINSTANCE.getLabelVisibilityPreference_Role();

		/**
		 * The meta object literal for the '<em><b>Icon Path Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_VISIBILITY_PREFERENCE__ICON_PATH_ROLE = eINSTANCE.getLabelVisibilityPreference_IconPathRole();

		/**
		 * The meta object literal for the '<em><b>Link Labels</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABEL_VISIBILITY_PREFERENCE__LINK_LABELS = eINSTANCE.getLabelVisibilityPreference_LinkLabels();

		/**
		 * The meta object literal for the '<em><b>External Node Labels</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABEL_VISIBILITY_PREFERENCE__EXTERNAL_NODE_LABELS = eINSTANCE.getLabelVisibilityPreference_ExternalNodeLabels();

		/**
		 * The meta object literal for the '<em><b>Visible By Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_VISIBILITY_PREFERENCE__VISIBLE_BY_DEFAULT = eINSTANCE.getLabelVisibilityPreference_VisibleByDefault();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.CompartmentVisibilityPreferenceImpl <em>Compartment Visibility Preference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.CompartmentVisibilityPreferenceImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getCompartmentVisibilityPreference()
		 * @generated
		 */
		EClass COMPARTMENT_VISIBILITY_PREFERENCE = eINSTANCE.getCompartmentVisibilityPreference();

		/**
		 * The meta object literal for the '<em><b>Visible By Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARTMENT_VISIBILITY_PREFERENCE__VISIBLE_BY_DEFAULT = eINSTANCE.getCompartmentVisibilityPreference_VisibleByDefault();

		/**
		 * The meta object literal for the '<em><b>Compartments</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENT_VISIBILITY_PREFERENCE__COMPARTMENTS = eINSTANCE.getCompartmentVisibilityPreference_Compartments();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.CompartmentTitleVisibilityPreferenceImpl <em>Compartment Title Visibility Preference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.CompartmentTitleVisibilityPreferenceImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getCompartmentTitleVisibilityPreference()
		 * @generated
		 */
		EClass COMPARTMENT_TITLE_VISIBILITY_PREFERENCE = eINSTANCE.getCompartmentTitleVisibilityPreference();

		/**
		 * The meta object literal for the '<em><b>Visible By Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARTMENT_TITLE_VISIBILITY_PREFERENCE__VISIBLE_BY_DEFAULT = eINSTANCE.getCompartmentTitleVisibilityPreference_VisibleByDefault();

		/**
		 * The meta object literal for the '<em><b>Compartments</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPARTMENT_TITLE_VISIBILITY_PREFERENCE__COMPARTMENTS = eINSTANCE.getCompartmentTitleVisibilityPreference_Compartments();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.ConstrainedByReferenceCompartmentItemSemanticEditPolicyImpl <em>Constrained By Reference Compartment Item Semantic Edit Policy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.ConstrainedByReferenceCompartmentItemSemanticEditPolicyImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getConstrainedByReferenceCompartmentItemSemanticEditPolicy()
		 * @generated
		 */
		EClass CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY = eINSTANCE.getConstrainedByReferenceCompartmentItemSemanticEditPolicy();

		/**
		 * The meta object literal for the '<em><b>Gen View</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY__GEN_VIEW = eINSTANCE.getConstrainedByReferenceCompartmentItemSemanticEditPolicy_GenView();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.GenerateUsingElementTypeCreationCommandImpl <em>Generate Using Element Type Creation Command</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenerateUsingElementTypeCreationCommandImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getGenerateUsingElementTypeCreationCommand()
		 * @generated
		 */
		EClass GENERATE_USING_ELEMENT_TYPE_CREATION_COMMAND = eINSTANCE.getGenerateUsingElementTypeCreationCommand();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.CustomDiagramUpdaterSingletonImpl <em>Custom Diagram Updater Singleton</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.CustomDiagramUpdaterSingletonImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getCustomDiagramUpdaterSingleton()
		 * @generated
		 */
		EClass CUSTOM_DIAGRAM_UPDATER_SINGLETON = eINSTANCE.getCustomDiagramUpdaterSingleton();

		/**
		 * The meta object literal for the '<em><b>Singleton Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOM_DIAGRAM_UPDATER_SINGLETON__SINGLETON_PATH = eINSTANCE.getCustomDiagramUpdaterSingleton_SingletonPath();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificNodePlateImpl <em>Specific Node Plate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.SpecificNodePlateImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getSpecificNodePlate()
		 * @generated
		 */
		EClass SPECIFIC_NODE_PLATE = eINSTANCE.getSpecificNodePlate();

		/**
		 * The meta object literal for the '<em><b>Edit Parts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFIC_NODE_PLATE__EDIT_PARTS = eINSTANCE.getSpecificNodePlate_EditParts();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFIC_NODE_PLATE__NAME = eINSTANCE.getSpecificNodePlate_Name();

		/**
		 * The meta object literal for the '<em><b>Node Plate Qualified Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFIC_NODE_PLATE__NODE_PLATE_QUALIFIED_NAME = eINSTANCE.getSpecificNodePlate_NodePlateQualifiedName();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.GenVisualTypeProviderImpl <em>Gen Visual Type Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenVisualTypeProviderImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getGenVisualTypeProvider()
		 * @generated
		 */
		EClass GEN_VISUAL_TYPE_PROVIDER = eINSTANCE.getGenVisualTypeProvider();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEN_VISUAL_TYPE_PROVIDER__CLASS_NAME = eINSTANCE.getGenVisualTypeProvider_ClassName();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.gmf.codegen.genextension.impl.VisualIDOverrideImpl <em>Visual ID Override</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.VisualIDOverrideImpl
		 * @see org.eclipse.papyrus.gmf.codegen.genextension.impl.GenExtensionPackageImpl#getVisualIDOverride()
		 * @generated
		 */
		EClass VISUAL_ID_OVERRIDE = eINSTANCE.getVisualIDOverride();

		/**
		 * The meta object literal for the '<em><b>Gen View</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VISUAL_ID_OVERRIDE__GEN_VIEW = eINSTANCE.getVisualIDOverride_GenView();

		/**
		 * The meta object literal for the '<em><b>Visual ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VISUAL_ID_OVERRIDE__VISUAL_ID = eINSTANCE.getVisualIDOverride_VisualID();

		/**
		 * The meta object literal for the '<em><b>Child</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VISUAL_ID_OVERRIDE__CHILD = eINSTANCE.getVisualIDOverride_Child();

	}

} //GenExtensionPackage
