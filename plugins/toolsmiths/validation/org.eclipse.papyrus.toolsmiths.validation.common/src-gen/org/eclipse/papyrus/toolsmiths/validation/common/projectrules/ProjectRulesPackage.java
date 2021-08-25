/**
 * Copyright (c) 2021 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 */
package org.eclipse.papyrus.toolsmiths.validation.common.projectrules;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/uml2/2.0.0/UML originalName='ProjectRules'"
 * @generated
 */
public interface ProjectRulesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	String eNAME = "projectrules";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/Papyrus/toolsmiths/validation/ProjectRules";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	String eNS_PREFIX = "proj";

	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	String eCONTENT_TYPE = "org.eclipse.papyrus.toolsmiths.validation.common.projectrules";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	ProjectRulesPackage eINSTANCE = org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectDescriptionImpl <em>Project Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectDescriptionImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getProjectDescription()
	 * @generated
	 */
	int PROJECT_DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Dependency</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int PROJECT_DESCRIPTION__DEPENDENCY = 0;

	/**
	 * The feature id for the '<em><b>Required Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY = 1;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int PROJECT_DESCRIPTION__EXTENSION = 2;

	/**
	 * The number of structural features of the '<em>Project Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int PROJECT_DESCRIPTION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Project Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int PROJECT_DESCRIPTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.DependencyImpl <em>Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.DependencyImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getDependency()
	 * @generated
	 */
	int DEPENDENCY = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__KIND = 1;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__REQUIRED = 2;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__LOWER_BOUND = 3;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__UPPER_BOUND = 4;

	/**
	 * The number of structural features of the '<em>Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY_FEATURE_COUNT = 5;

	/**
	 * The operation id for the '<em>Is Required</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY___IS_REQUIRED = 0;

	/**
	 * The operation id for the '<em>Set Required</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY___SET_REQUIRED__BOOLEAN = 1;

	/**
	 * The number of operations of the '<em>Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionImpl <em>Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getExtension()
	 * @generated
	 */
	int EXTENSION = 2;

	/**
	 * The feature id for the '<em><b>Extension Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION__EXTENSION_POINT = 0;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION__ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Resource Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION__RESOURCE_ATTRIBUTE = 2;

	/**
	 * The feature id for the '<em><b>Architecture Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION__ARCHITECTURE_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Project</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION__PROJECT = 4;

	/**
	 * The number of structural features of the '<em>Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_FEATURE_COUNT = 5;

	/**
	 * The operation id for the '<em>Get Resource Attribute</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION___GET_RESOURCE_ATTRIBUTE = 0;

	/**
	 * The number of operations of the '<em>Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionModelNodeImpl <em>Extension Model Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionModelNodeImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getExtensionModelNode()
	 * @generated
	 */
	int EXTENSION_MODEL_NODE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_MODEL_NODE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Validatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_MODEL_NODE__VALIDATABLE = 1;

	/**
	 * The number of structural features of the '<em>Extension Model Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_MODEL_NODE_FEATURE_COUNT = 2;

	/**
	 * The operation id for the '<em>Containing Extension</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_MODEL_NODE___CONTAINING_EXTENSION = 0;

	/**
	 * The number of operations of the '<em>Extension Model Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_MODEL_NODE_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionElementImpl <em>Extension Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionElementImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getExtensionElement()
	 * @generated
	 */
	int EXTENSION_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ELEMENT__NAME = EXTENSION_MODEL_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Validatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ELEMENT__VALIDATABLE = EXTENSION_MODEL_NODE__VALIDATABLE;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ELEMENT__PARENT = EXTENSION_MODEL_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ELEMENT__ELEMENT = EXTENSION_MODEL_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ELEMENT__ATTRIBUTE = EXTENSION_MODEL_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ELEMENT__EXTENSION = EXTENSION_MODEL_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Extension Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ELEMENT_FEATURE_COUNT = EXTENSION_MODEL_NODE_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Containing Extension</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ELEMENT___CONTAINING_EXTENSION = EXTENSION_MODEL_NODE___CONTAINING_EXTENSION;

	/**
	 * The number of operations of the '<em>Extension Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ELEMENT_OPERATION_COUNT = EXTENSION_MODEL_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionAttributeImpl <em>Extension Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionAttributeImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getExtensionAttribute()
	 * @generated
	 */
	int EXTENSION_ATTRIBUTE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ATTRIBUTE__NAME = EXTENSION_MODEL_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Validatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ATTRIBUTE__VALIDATABLE = EXTENSION_MODEL_NODE__VALIDATABLE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ATTRIBUTE__VALUE = EXTENSION_MODEL_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parsed Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ATTRIBUTE__PARSED_VALUE = EXTENSION_MODEL_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ATTRIBUTE__ELEMENT = EXTENSION_MODEL_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Extension Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ATTRIBUTE_FEATURE_COUNT = EXTENSION_MODEL_NODE_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Containing Extension</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ATTRIBUTE___CONTAINING_EXTENSION = EXTENSION_MODEL_NODE___CONTAINING_EXTENSION;

	/**
	 * The operation id for the '<em>Get Parsed Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ATTRIBUTE___GET_PARSED_VALUE = EXTENSION_MODEL_NODE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Extension Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXTENSION_ATTRIBUTE_OPERATION_COUNT = EXTENSION_MODEL_NODE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ValueImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 6;

	/**
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>String Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int VALUE___STRING_VALUE = 0;

	/**
	 * The number of operations of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.LiteralStringImpl <em>Literal String</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.LiteralStringImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getLiteralString()
	 * @generated
	 */
	int LITERAL_STRING = 7;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int LITERAL_STRING__VALUE = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Literal String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int LITERAL_STRING_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>String Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int LITERAL_STRING___STRING_VALUE = VALUE___STRING_VALUE;

	/**
	 * The number of operations of the '<em>Literal String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int LITERAL_STRING_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.VariableImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Option</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int VARIABLE__OPTION = VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>String Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int VARIABLE___STRING_VALUE = VALUE___STRING_VALUE;

	/**
	 * The number of operations of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int VARIABLE_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExpressionImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 9;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__VALUE = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>String Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXPRESSION___STRING_VALUE = VALUE___STRING_VALUE;

	/**
	 * The number of operations of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.OpaqueValueImpl <em>Opaque Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.OpaqueValueImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getOpaqueValue()
	 * @generated
	 */
	int OPAQUE_VALUE = 10;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int OPAQUE_VALUE__TEXT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Opaque Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int OPAQUE_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>String Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int OPAQUE_VALUE___STRING_VALUE = VALUE___STRING_VALUE;

	/**
	 * The operation id for the '<em>Parse</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int OPAQUE_VALUE___PARSE = VALUE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Opaque Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int OPAQUE_VALUE_OPERATION_COUNT = VALUE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.FeaturePathImpl <em>Feature Path</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.FeaturePathImpl
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getFeaturePath()
	 * @generated
	 */
	int FEATURE_PATH = 11;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int FEATURE_PATH__PATH = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Feature Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int FEATURE_PATH_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>String Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int FEATURE_PATH___STRING_VALUE = VALUE___STRING_VALUE;

	/**
	 * The number of operations of the '<em>Feature Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int FEATURE_PATH_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.DependencyKind <em>Dependency Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.DependencyKind
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getDependencyKind()
	 * @generated
	 */
	int DEPENDENCY_KIND = 12;

	/**
	 * The meta object id for the '<em>Version</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.osgi.framework.Version
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getVersion()
	 * @generated
	 */
	int VERSION = 13;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription <em>Project Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Project Description</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription
	 * @generated
	 */
	EClass getProjectDescription();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getDependencies <em>Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Dependency</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getDependencies()
	 * @see #getProjectDescription()
	 * @generated
	 */
	EReference getProjectDescription_Dependency();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getRequiredDependencies <em>Required Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference list '<em>Required Dependency</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getRequiredDependencies()
	 * @see #getProjectDescription()
	 * @generated
	 */
	EReference getProjectDescription_RequiredDependency();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getExtensions <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Extension</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription#getExtensions()
	 * @see #getProjectDescription()
	 * @generated
	 */
	EReference getProjectDescription_Extension();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency <em>Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Dependency</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency
	 * @generated
	 */
	EClass getDependency();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getName()
	 * @see #getDependency()
	 * @generated
	 */
	EAttribute getDependency_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getKind()
	 * @see #getDependency()
	 * @generated
	 */
	EAttribute getDependency_Kind();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#isRequired()
	 * @see #getDependency()
	 * @generated
	 */
	EAttribute getDependency_Required();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getLowerBound()
	 * @see #getDependency()
	 * @generated
	 */
	EAttribute getDependency_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#getUpperBound()
	 * @see #getDependency()
	 * @generated
	 */
	EAttribute getDependency_UpperBound();

	/**
	 * Returns the meta object for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#isRequired() <em>Is Required</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the '<em>Is Required</em>' operation.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#isRequired()
	 * @generated
	 */
	EOperation getDependency__IsRequired();

	/**
	 * Returns the meta object for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#setRequired(boolean) <em>Set Required</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the '<em>Set Required</em>' operation.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#setRequired(boolean)
	 * @generated
	 */
	EOperation getDependency__SetRequired__boolean();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Extension</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension
	 * @generated
	 */
	EClass getExtension();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getExtensionPoint <em>Extension Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Extension Point</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getExtensionPoint()
	 * @see #getExtension()
	 * @generated
	 */
	EAttribute getExtension_ExtensionPoint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getElements <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Element</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getElements()
	 * @see #getExtension()
	 * @generated
	 */
	EReference getExtension_Element();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getResourceAttribute <em>Resource Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference '<em>Resource Attribute</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getResourceAttribute()
	 * @see #getExtension()
	 * @generated
	 */
	EReference getExtension_ResourceAttribute();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getArchitectureReference <em>Architecture Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference '<em>Architecture Reference</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getArchitectureReference()
	 * @see #getExtension()
	 * @generated
	 */
	EReference getExtension_ArchitectureReference();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Project</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getProject()
	 * @see #getExtension()
	 * @generated
	 */
	EReference getExtension_Project();

	/**
	 * Returns the meta object for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getResourceAttribute() <em>Get Resource Attribute</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the '<em>Get Resource Attribute</em>' operation.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getResourceAttribute()
	 * @generated
	 */
	EOperation getExtension__GetResourceAttribute();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement <em>Extension Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Extension Element</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement
	 * @generated
	 */
	EClass getExtensionElement();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getParent()
	 * @see #getExtensionElement()
	 * @generated
	 */
	EReference getExtensionElement_Parent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getElements <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Element</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getElements()
	 * @see #getExtensionElement()
	 * @generated
	 */
	EReference getExtensionElement_Element();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getAttributes <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Attribute</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getAttributes()
	 * @see #getExtensionElement()
	 * @generated
	 */
	EReference getExtensionElement_Attribute();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Extension</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#getExtension()
	 * @see #getExtensionElement()
	 * @generated
	 */
	EReference getExtensionElement_Extension();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionModelNode <em>Extension Model Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Extension Model Node</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionModelNode
	 * @generated
	 */
	EClass getExtensionModelNode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionModelNode#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionModelNode#getName()
	 * @see #getExtensionModelNode()
	 * @generated
	 */
	EAttribute getExtensionModelNode_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionModelNode#isValidatable <em>Validatable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Validatable</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionModelNode#isValidatable()
	 * @see #getExtensionModelNode()
	 * @generated
	 */
	EAttribute getExtensionModelNode_Validatable();

	/**
	 * Returns the meta object for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionModelNode#containingExtension() <em>Containing Extension</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the '<em>Containing Extension</em>' operation.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionModelNode#containingExtension()
	 * @generated
	 */
	EOperation getExtensionModelNode__ContainingExtension();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute <em>Extension Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Extension Attribute</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute
	 * @generated
	 */
	EClass getExtensionAttribute();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getValue()
	 * @see #getExtensionAttribute()
	 * @generated
	 */
	EReference getExtensionAttribute_Value();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getParsedValue <em>Parsed Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the reference '<em>Parsed Value</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getParsedValue()
	 * @see #getExtensionAttribute()
	 * @generated
	 */
	EReference getExtensionAttribute_ParsedValue();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the container reference '<em>Element</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getElement()
	 * @see #getExtensionAttribute()
	 * @generated
	 */
	EReference getExtensionAttribute_Element();

	/**
	 * Returns the meta object for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getParsedValue() <em>Get Parsed Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the '<em>Get Parsed Value</em>' operation.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getParsedValue()
	 * @generated
	 */
	EOperation getExtensionAttribute__GetParsedValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Value</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value#stringValue() <em>String Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the '<em>String Value</em>' operation.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value#stringValue()
	 * @generated
	 */
	EOperation getValue__StringValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.LiteralString <em>Literal String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Literal String</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.LiteralString
	 * @generated
	 */
	EClass getLiteralString();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.LiteralString#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.LiteralString#getValue()
	 * @see #getLiteralString()
	 * @generated
	 */
	EAttribute getLiteralString_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable#getName()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Name();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable#getOptions <em>Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute list '<em>Option</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable#getOptions()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Option();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Expression#getValues <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the containment reference list '<em>Value</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Expression#getValues()
	 * @see #getExpression()
	 * @generated
	 */
	EReference getExpression_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue <em>Opaque Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Opaque Value</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue
	 * @generated
	 */
	EClass getOpaqueValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#getText()
	 * @see #getOpaqueValue()
	 * @generated
	 */
	EAttribute getOpaqueValue_Text();

	/**
	 * Returns the meta object for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#parse() <em>Parse</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the '<em>Parse</em>' operation.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#parse()
	 * @generated
	 */
	EOperation getOpaqueValue__Parse();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath <em>Feature Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Feature Path</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath
	 * @generated
	 */
	EClass getFeaturePath();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute list '<em>Path</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath#getPath()
	 * @see #getFeaturePath()
	 * @generated
	 */
	EAttribute getFeaturePath_Path();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.DependencyKind <em>Dependency Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for enum '<em>Dependency Kind</em>'.
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.DependencyKind
	 * @generated
	 */
	EEnum getDependencyKind();

	/**
	 * Returns the meta object for data type '{@link org.osgi.framework.Version <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for data type '<em>Version</em>'.
	 * @see org.osgi.framework.Version
	 * @model instanceClass="org.osgi.framework.Version"
	 * @generated
	 */
	EDataType getVersion();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ProjectRulesFactory getProjectRulesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectDescriptionImpl <em>Project Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectDescriptionImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getProjectDescription()
		 * @generated
		 */
		EClass PROJECT_DESCRIPTION = eINSTANCE.getProjectDescription();

		/**
		 * The meta object literal for the '<em><b>Dependency</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference PROJECT_DESCRIPTION__DEPENDENCY = eINSTANCE.getProjectDescription_Dependency();

		/**
		 * The meta object literal for the '<em><b>Required Dependency</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY = eINSTANCE.getProjectDescription_RequiredDependency();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference PROJECT_DESCRIPTION__EXTENSION = eINSTANCE.getProjectDescription_Extension();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.DependencyImpl <em>Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.DependencyImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getDependency()
		 * @generated
		 */
		EClass DEPENDENCY = eINSTANCE.getDependency();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute DEPENDENCY__NAME = eINSTANCE.getDependency_Name();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute DEPENDENCY__KIND = eINSTANCE.getDependency_Kind();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute DEPENDENCY__REQUIRED = eINSTANCE.getDependency_Required();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute DEPENDENCY__LOWER_BOUND = eINSTANCE.getDependency_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute DEPENDENCY__UPPER_BOUND = eINSTANCE.getDependency_UpperBound();

		/**
		 * The meta object literal for the '<em><b>Is Required</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EOperation DEPENDENCY___IS_REQUIRED = eINSTANCE.getDependency__IsRequired();

		/**
		 * The meta object literal for the '<em><b>Set Required</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EOperation DEPENDENCY___SET_REQUIRED__BOOLEAN = eINSTANCE.getDependency__SetRequired__boolean();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionImpl <em>Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getExtension()
		 * @generated
		 */
		EClass EXTENSION = eINSTANCE.getExtension();

		/**
		 * The meta object literal for the '<em><b>Extension Point</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute EXTENSION__EXTENSION_POINT = eINSTANCE.getExtension_ExtensionPoint();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION__ELEMENT = eINSTANCE.getExtension_Element();

		/**
		 * The meta object literal for the '<em><b>Resource Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION__RESOURCE_ATTRIBUTE = eINSTANCE.getExtension_ResourceAttribute();

		/**
		 * The meta object literal for the '<em><b>Architecture Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION__ARCHITECTURE_REFERENCE = eINSTANCE.getExtension_ArchitectureReference();

		/**
		 * The meta object literal for the '<em><b>Project</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION__PROJECT = eINSTANCE.getExtension_Project();

		/**
		 * The meta object literal for the '<em><b>Get Resource Attribute</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EOperation EXTENSION___GET_RESOURCE_ATTRIBUTE = eINSTANCE.getExtension__GetResourceAttribute();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionElementImpl <em>Extension Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionElementImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getExtensionElement()
		 * @generated
		 */
		EClass EXTENSION_ELEMENT = eINSTANCE.getExtensionElement();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION_ELEMENT__PARENT = eINSTANCE.getExtensionElement_Parent();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION_ELEMENT__ELEMENT = eINSTANCE.getExtensionElement_Element();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION_ELEMENT__ATTRIBUTE = eINSTANCE.getExtensionElement_Attribute();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION_ELEMENT__EXTENSION = eINSTANCE.getExtensionElement_Extension();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionModelNodeImpl <em>Extension Model Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionModelNodeImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getExtensionModelNode()
		 * @generated
		 */
		EClass EXTENSION_MODEL_NODE = eINSTANCE.getExtensionModelNode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute EXTENSION_MODEL_NODE__NAME = eINSTANCE.getExtensionModelNode_Name();

		/**
		 * The meta object literal for the '<em><b>Validatable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute EXTENSION_MODEL_NODE__VALIDATABLE = eINSTANCE.getExtensionModelNode_Validatable();

		/**
		 * The meta object literal for the '<em><b>Containing Extension</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EOperation EXTENSION_MODEL_NODE___CONTAINING_EXTENSION = eINSTANCE.getExtensionModelNode__ContainingExtension();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionAttributeImpl <em>Extension Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExtensionAttributeImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getExtensionAttribute()
		 * @generated
		 */
		EClass EXTENSION_ATTRIBUTE = eINSTANCE.getExtensionAttribute();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION_ATTRIBUTE__VALUE = eINSTANCE.getExtensionAttribute_Value();

		/**
		 * The meta object literal for the '<em><b>Parsed Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION_ATTRIBUTE__PARSED_VALUE = eINSTANCE.getExtensionAttribute_ParsedValue();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXTENSION_ATTRIBUTE__ELEMENT = eINSTANCE.getExtensionAttribute_Element();

		/**
		 * The meta object literal for the '<em><b>Get Parsed Value</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EOperation EXTENSION_ATTRIBUTE___GET_PARSED_VALUE = eINSTANCE.getExtensionAttribute__GetParsedValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ValueImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '<em><b>String Value</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EOperation VALUE___STRING_VALUE = eINSTANCE.getValue__StringValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.LiteralStringImpl <em>Literal String</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.LiteralStringImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getLiteralString()
		 * @generated
		 */
		EClass LITERAL_STRING = eINSTANCE.getLiteralString();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute LITERAL_STRING__VALUE = eINSTANCE.getLiteralString_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.VariableImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute VARIABLE__NAME = eINSTANCE.getVariable_Name();

		/**
		 * The meta object literal for the '<em><b>Option</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute VARIABLE__OPTION = eINSTANCE.getVariable_Option();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ExpressionImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EReference EXPRESSION__VALUE = eINSTANCE.getExpression_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.OpaqueValueImpl <em>Opaque Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.OpaqueValueImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getOpaqueValue()
		 * @generated
		 */
		EClass OPAQUE_VALUE = eINSTANCE.getOpaqueValue();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute OPAQUE_VALUE__TEXT = eINSTANCE.getOpaqueValue_Text();

		/**
		 * The meta object literal for the '<em><b>Parse</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EOperation OPAQUE_VALUE___PARSE = eINSTANCE.getOpaqueValue__Parse();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.FeaturePathImpl <em>Feature Path</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.FeaturePathImpl
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getFeaturePath()
		 * @generated
		 */
		EClass FEATURE_PATH = eINSTANCE.getFeaturePath();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute FEATURE_PATH__PATH = eINSTANCE.getFeaturePath_Path();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.DependencyKind <em>Dependency Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.DependencyKind
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getDependencyKind()
		 * @generated
		 */
		EEnum DEPENDENCY_KIND = eINSTANCE.getDependencyKind();

		/**
		 * The meta object literal for the '<em>Version</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.osgi.framework.Version
		 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectRulesPackageImpl#getVersion()
		 * @generated
		 */
		EDataType VERSION = eINSTANCE.getVersion();

	}

} // ProjectRulesPackage
