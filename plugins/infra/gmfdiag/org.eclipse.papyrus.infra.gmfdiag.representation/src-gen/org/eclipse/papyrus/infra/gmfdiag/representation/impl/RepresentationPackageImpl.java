/**
 * Copyright (c) 2017 CEA LIST.
 * 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *  Maged Elaasar - Initial API and implementation
 *  
 * 
 */
package org.eclipse.papyrus.infra.gmfdiag.representation.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.papyrus.infra.constraints.ConstraintsPackage;
import org.eclipse.papyrus.infra.core.architecture.ArchitecturePackage;
import org.eclipse.papyrus.infra.gmfdiag.representation.AssistantRule;
import org.eclipse.papyrus.infra.gmfdiag.representation.ChildRule;
import org.eclipse.papyrus.infra.gmfdiag.representation.PaletteRule;
import org.eclipse.papyrus.infra.gmfdiag.representation.PapyrusDiagram;
import org.eclipse.papyrus.infra.gmfdiag.representation.PathElement;
import org.eclipse.papyrus.infra.gmfdiag.representation.RepresentationFactory;
import org.eclipse.papyrus.infra.gmfdiag.representation.RepresentationPackage;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RepresentationPackageImpl extends EPackageImpl implements RepresentationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass papyrusDiagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass childRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paletteRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pathElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assistantRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType elementTypeEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.papyrus.infra.gmfdiag.representation.RepresentationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RepresentationPackageImpl() {
		super(eNS_URI, RepresentationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link RepresentationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RepresentationPackage init() {
		if (isInited) return (RepresentationPackage)EPackage.Registry.INSTANCE.getEPackage(RepresentationPackage.eNS_URI);

		// Obtain or create and register package
		RepresentationPackageImpl theRepresentationPackage = (RepresentationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RepresentationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RepresentationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ArchitecturePackage.eINSTANCE.eClass();
		ConstraintsPackage.eINSTANCE.eClass();
		ElementTypesConfigurationsPackage.eINSTANCE.eClass();
		org.eclipse.papyrus.infra.architecture.representation.RepresentationPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theRepresentationPackage.createPackageContents();

		// Initialize created meta-data
		theRepresentationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRepresentationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RepresentationPackage.eNS_URI, theRepresentationPackage);
		return theRepresentationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPapyrusDiagram() {
		return papyrusDiagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPapyrusDiagram_CustomPalette() {
		return (EAttribute)papyrusDiagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPapyrusDiagram_CustomStyle() {
		return (EAttribute)papyrusDiagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPapyrusDiagram_ChildRules() {
		return (EReference)papyrusDiagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPapyrusDiagram_PaletteRules() {
		return (EReference)papyrusDiagramEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPapyrusDiagram_AssistantRules() {
		return (EReference)papyrusDiagramEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPapyrusDiagram_CreationCommandClass() {
		return (EAttribute)papyrusDiagramEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChildRule() {
		return childRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChildRule_Element() {
		return (EReference)childRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChildRule_Stereotypes() {
		return (EReference)childRuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChildRule_Origin() {
		return (EReference)childRuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChildRule_InsertionPath() {
		return (EReference)childRuleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPaletteRule() {
		return paletteRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaletteRule_Element() {
		return (EAttribute)paletteRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPathElement() {
		return pathElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPathElement_Feature() {
		return (EReference)pathElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPathElement_Origin() {
		return (EReference)pathElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPathElement_Target() {
		return (EReference)pathElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssistantRule() {
		return assistantRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssistantRule_ElementTypeID() {
		return (EAttribute)assistantRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAssistantRule__Matches__IElementType() {
		return assistantRuleEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getElementType() {
		return elementTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepresentationFactory getRepresentationFactory() {
		return (RepresentationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		papyrusDiagramEClass = createEClass(PAPYRUS_DIAGRAM);
		createEAttribute(papyrusDiagramEClass, PAPYRUS_DIAGRAM__CUSTOM_PALETTE);
		createEAttribute(papyrusDiagramEClass, PAPYRUS_DIAGRAM__CUSTOM_STYLE);
		createEReference(papyrusDiagramEClass, PAPYRUS_DIAGRAM__CHILD_RULES);
		createEReference(papyrusDiagramEClass, PAPYRUS_DIAGRAM__PALETTE_RULES);
		createEReference(papyrusDiagramEClass, PAPYRUS_DIAGRAM__ASSISTANT_RULES);
		createEAttribute(papyrusDiagramEClass, PAPYRUS_DIAGRAM__CREATION_COMMAND_CLASS);

		childRuleEClass = createEClass(CHILD_RULE);
		createEReference(childRuleEClass, CHILD_RULE__ELEMENT);
		createEReference(childRuleEClass, CHILD_RULE__STEREOTYPES);
		createEReference(childRuleEClass, CHILD_RULE__ORIGIN);
		createEReference(childRuleEClass, CHILD_RULE__INSERTION_PATH);

		paletteRuleEClass = createEClass(PALETTE_RULE);
		createEAttribute(paletteRuleEClass, PALETTE_RULE__ELEMENT);

		pathElementEClass = createEClass(PATH_ELEMENT);
		createEReference(pathElementEClass, PATH_ELEMENT__FEATURE);
		createEReference(pathElementEClass, PATH_ELEMENT__ORIGIN);
		createEReference(pathElementEClass, PATH_ELEMENT__TARGET);

		assistantRuleEClass = createEClass(ASSISTANT_RULE);
		createEAttribute(assistantRuleEClass, ASSISTANT_RULE__ELEMENT_TYPE_ID);
		createEOperation(assistantRuleEClass, ASSISTANT_RULE___MATCHES__IELEMENTTYPE);

		// Create data types
		elementTypeEDataType = createEDataType(ELEMENT_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		org.eclipse.papyrus.infra.architecture.representation.RepresentationPackage theRepresentationPackage_1 = (org.eclipse.papyrus.infra.architecture.representation.RepresentationPackage)EPackage.Registry.INSTANCE.getEPackage(org.eclipse.papyrus.infra.architecture.representation.RepresentationPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		papyrusDiagramEClass.getESuperTypes().add(theRepresentationPackage_1.getPapyrusRepresentationKind());
		childRuleEClass.getESuperTypes().add(theRepresentationPackage_1.getRule());
		paletteRuleEClass.getESuperTypes().add(theRepresentationPackage_1.getRule());
		assistantRuleEClass.getESuperTypes().add(theRepresentationPackage_1.getRule());

		// Initialize classes, features, and operations; add parameters
		initEClass(papyrusDiagramEClass, PapyrusDiagram.class, "PapyrusDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPapyrusDiagram_CustomPalette(), ecorePackage.getEString(), "customPalette", null, 0, 1, PapyrusDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPapyrusDiagram_CustomStyle(), ecorePackage.getEString(), "customStyle", null, 0, 1, PapyrusDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPapyrusDiagram_ChildRules(), this.getChildRule(), null, "childRules", null, 0, -1, PapyrusDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPapyrusDiagram_PaletteRules(), this.getPaletteRule(), null, "paletteRules", null, 0, -1, PapyrusDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPapyrusDiagram_AssistantRules(), this.getAssistantRule(), null, "assistantRules", null, 0, -1, PapyrusDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEJavaClass());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEAttribute(getPapyrusDiagram_CreationCommandClass(), g1, "creationCommandClass", null, 1, 1, PapyrusDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(childRuleEClass, ChildRule.class, "ChildRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChildRule_Element(), ecorePackage.getEClass(), null, "element", null, 0, 1, ChildRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getChildRule_Stereotypes(), ecorePackage.getEClass(), null, "stereotypes", null, 0, -1, ChildRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getChildRule_Origin(), ecorePackage.getEClass(), null, "origin", null, 0, 1, ChildRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getChildRule_InsertionPath(), this.getPathElement(), null, "insertionPath", null, 0, -1, ChildRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(paletteRuleEClass, PaletteRule.class, "PaletteRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPaletteRule_Element(), ecorePackage.getEString(), "element", null, 0, 1, PaletteRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pathElementEClass, PathElement.class, "PathElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPathElement_Feature(), ecorePackage.getEReference(), null, "feature", null, 1, 1, PathElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPathElement_Origin(), ecorePackage.getEClass(), null, "origin", null, 1, 1, PathElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getPathElement_Target(), ecorePackage.getEClass(), null, "target", null, 1, 1, PathElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(assistantRuleEClass, AssistantRule.class, "AssistantRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssistantRule_ElementTypeID(), ecorePackage.getEString(), "elementTypeID", null, 0, 1, AssistantRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getAssistantRule__Matches__IElementType(), ecorePackage.getEBoolean(), "matches", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getElementType(), "elementType", 1, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize data types
		initEDataType(elementTypeEDataType, IElementType.class, "ElementType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //RepresentationPackageImpl
