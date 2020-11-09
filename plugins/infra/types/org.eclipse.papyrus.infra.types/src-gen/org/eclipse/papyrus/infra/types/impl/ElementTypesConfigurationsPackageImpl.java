/**
 * Copyright (c) 2014 CEA LIST.
 * 
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *  CEA LIST - Initial API and implementation
 */
package org.eclipse.papyrus.infra.types.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration;
import org.eclipse.papyrus.infra.types.AbstractEditHelperAdviceConfiguration;
import org.eclipse.papyrus.infra.types.AbstractMatcherConfiguration;
import org.eclipse.papyrus.infra.types.AdviceBindingConfiguration;
import org.eclipse.papyrus.infra.types.AdviceConfiguration;
import org.eclipse.papyrus.infra.types.ConfigurationElement;
import org.eclipse.papyrus.infra.types.ContainerConfiguration;
import org.eclipse.papyrus.infra.types.EditHelperAdviceConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypeConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypeSetConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsFactory;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage;
import org.eclipse.papyrus.infra.types.ExternallyRegisteredAdvice;
import org.eclipse.papyrus.infra.types.ExternallyRegisteredType;
import org.eclipse.papyrus.infra.types.IconEntry;
import org.eclipse.papyrus.infra.types.IdentifiedConfiguration;
import org.eclipse.papyrus.infra.types.InheritanceKind;
import org.eclipse.papyrus.infra.types.MatcherConfiguration;
import org.eclipse.papyrus.infra.types.MetamodelTypeConfiguration;
import org.eclipse.papyrus.infra.types.NamedConfiguration;
import org.eclipse.papyrus.infra.types.SpecializationTypeConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ElementTypesConfigurationsPackageImpl extends EPackageImpl implements ElementTypesConfigurationsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementTypeSetConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurationElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementTypeConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iconEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass identifiedConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractAdviceBindingConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adviceConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractMatcherConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specializationTypeConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractEditHelperAdviceConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metamodelTypeConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editHelperAdviceConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adviceBindingConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass matcherConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externallyRegisteredTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externallyRegisteredAdviceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum inheritanceKindEEnum = null;

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
	 * @see org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ElementTypesConfigurationsPackageImpl() {
		super(eNS_URI, ElementTypesConfigurationsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ElementTypesConfigurationsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ElementTypesConfigurationsPackage init() {
		if (isInited) return (ElementTypesConfigurationsPackage)EPackage.Registry.INSTANCE.getEPackage(ElementTypesConfigurationsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredElementTypesConfigurationsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ElementTypesConfigurationsPackageImpl theElementTypesConfigurationsPackage = registeredElementTypesConfigurationsPackage instanceof ElementTypesConfigurationsPackageImpl ? (ElementTypesConfigurationsPackageImpl)registeredElementTypesConfigurationsPackage : new ElementTypesConfigurationsPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theElementTypesConfigurationsPackage.createPackageContents();

		// Initialize created meta-data
		theElementTypesConfigurationsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theElementTypesConfigurationsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ElementTypesConfigurationsPackage.eNS_URI, theElementTypesConfigurationsPackage);
		return theElementTypesConfigurationsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getElementTypeSetConfiguration() {
		return elementTypeSetConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementTypeSetConfiguration_ElementTypeConfigurations() {
		return (EReference)elementTypeSetConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementTypeSetConfiguration_AdviceBindingsConfigurations() {
		return (EReference)elementTypeSetConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getElementTypeSetConfiguration_MetamodelNsURI() {
		return (EAttribute)elementTypeSetConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConfigurationElement() {
		return configurationElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConfigurationElement_Description() {
		return (EAttribute)configurationElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getElementTypeConfiguration() {
		return elementTypeConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getElementTypeConfiguration_Hint() {
		return (EAttribute)elementTypeConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getElementTypeConfiguration_Kind() {
		return (EAttribute)elementTypeConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementTypeConfiguration_IconEntry() {
		return (EReference)elementTypeConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIconEntry() {
		return iconEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIconEntry_IconPath() {
		return (EAttribute)iconEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIconEntry_BundleId() {
		return (EAttribute)iconEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIdentifiedConfiguration() {
		return identifiedConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIdentifiedConfiguration_Identifier() {
		return (EAttribute)identifiedConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNamedConfiguration() {
		return namedConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNamedConfiguration_Name() {
		return (EAttribute)namedConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractAdviceBindingConfiguration() {
		return abstractAdviceBindingConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractAdviceBindingConfiguration_Target() {
		return (EReference)abstractAdviceBindingConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractAdviceBindingConfiguration_ContainerConfiguration() {
		return (EReference)abstractAdviceBindingConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractAdviceBindingConfiguration_MatcherConfiguration() {
		return (EReference)abstractAdviceBindingConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractAdviceBindingConfiguration_Inheritance() {
		return (EAttribute)abstractAdviceBindingConfigurationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractAdviceBindingConfiguration_ApplyToAllTypes() {
		return (EAttribute)abstractAdviceBindingConfigurationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdviceConfiguration() {
		return adviceConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdviceConfiguration_Before() {
		return (EReference)adviceConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdviceConfiguration_After() {
		return (EReference)adviceConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainerConfiguration() {
		return containerConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainerConfiguration_ContainerMatcherConfiguration() {
		return (EReference)containerConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainerConfiguration_EContainmentFeatures() {
		return (EReference)containerConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractMatcherConfiguration() {
		return abstractMatcherConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpecializationTypeConfiguration() {
		return specializationTypeConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpecializationTypeConfiguration_EditHelperAdviceConfiguration() {
		return (EReference)specializationTypeConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpecializationTypeConfiguration_ContainerConfiguration() {
		return (EReference)specializationTypeConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpecializationTypeConfiguration_MatcherConfiguration() {
		return (EReference)specializationTypeConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpecializationTypeConfiguration_SpecializedTypes() {
		return (EReference)specializationTypeConfigurationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractEditHelperAdviceConfiguration() {
		return abstractEditHelperAdviceConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractEditHelperAdviceConfiguration_Target() {
		return (EReference)abstractEditHelperAdviceConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMetamodelTypeConfiguration() {
		return metamodelTypeConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMetamodelTypeConfiguration_EClass() {
		return (EReference)metamodelTypeConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMetamodelTypeConfiguration_EditHelperClassName() {
		return (EAttribute)metamodelTypeConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEditHelperAdviceConfiguration() {
		return editHelperAdviceConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEditHelperAdviceConfiguration_EditHelperAdviceClassName() {
		return (EAttribute)editHelperAdviceConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdviceBindingConfiguration() {
		return adviceBindingConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdviceBindingConfiguration_EditHelperAdviceClassName() {
		return (EAttribute)adviceBindingConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMatcherConfiguration() {
		return matcherConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMatcherConfiguration_MatcherClassName() {
		return (EAttribute)matcherConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExternallyRegisteredType() {
		return externallyRegisteredTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExternallyRegisteredAdvice() {
		return externallyRegisteredAdviceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getInheritanceKind() {
		return inheritanceKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ElementTypesConfigurationsFactory getElementTypesConfigurationsFactory() {
		return (ElementTypesConfigurationsFactory)getEFactoryInstance();
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
		elementTypeSetConfigurationEClass = createEClass(ELEMENT_TYPE_SET_CONFIGURATION);
		createEReference(elementTypeSetConfigurationEClass, ELEMENT_TYPE_SET_CONFIGURATION__ELEMENT_TYPE_CONFIGURATIONS);
		createEReference(elementTypeSetConfigurationEClass, ELEMENT_TYPE_SET_CONFIGURATION__ADVICE_BINDINGS_CONFIGURATIONS);
		createEAttribute(elementTypeSetConfigurationEClass, ELEMENT_TYPE_SET_CONFIGURATION__METAMODEL_NS_URI);

		configurationElementEClass = createEClass(CONFIGURATION_ELEMENT);
		createEAttribute(configurationElementEClass, CONFIGURATION_ELEMENT__DESCRIPTION);

		elementTypeConfigurationEClass = createEClass(ELEMENT_TYPE_CONFIGURATION);
		createEAttribute(elementTypeConfigurationEClass, ELEMENT_TYPE_CONFIGURATION__HINT);
		createEAttribute(elementTypeConfigurationEClass, ELEMENT_TYPE_CONFIGURATION__KIND);
		createEReference(elementTypeConfigurationEClass, ELEMENT_TYPE_CONFIGURATION__ICON_ENTRY);

		iconEntryEClass = createEClass(ICON_ENTRY);
		createEAttribute(iconEntryEClass, ICON_ENTRY__ICON_PATH);
		createEAttribute(iconEntryEClass, ICON_ENTRY__BUNDLE_ID);

		identifiedConfigurationEClass = createEClass(IDENTIFIED_CONFIGURATION);
		createEAttribute(identifiedConfigurationEClass, IDENTIFIED_CONFIGURATION__IDENTIFIER);

		namedConfigurationEClass = createEClass(NAMED_CONFIGURATION);
		createEAttribute(namedConfigurationEClass, NAMED_CONFIGURATION__NAME);

		abstractAdviceBindingConfigurationEClass = createEClass(ABSTRACT_ADVICE_BINDING_CONFIGURATION);
		createEReference(abstractAdviceBindingConfigurationEClass, ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET);
		createEReference(abstractAdviceBindingConfigurationEClass, ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION);
		createEReference(abstractAdviceBindingConfigurationEClass, ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION);
		createEAttribute(abstractAdviceBindingConfigurationEClass, ABSTRACT_ADVICE_BINDING_CONFIGURATION__INHERITANCE);
		createEAttribute(abstractAdviceBindingConfigurationEClass, ABSTRACT_ADVICE_BINDING_CONFIGURATION__APPLY_TO_ALL_TYPES);

		adviceConfigurationEClass = createEClass(ADVICE_CONFIGURATION);
		createEReference(adviceConfigurationEClass, ADVICE_CONFIGURATION__BEFORE);
		createEReference(adviceConfigurationEClass, ADVICE_CONFIGURATION__AFTER);

		containerConfigurationEClass = createEClass(CONTAINER_CONFIGURATION);
		createEReference(containerConfigurationEClass, CONTAINER_CONFIGURATION__CONTAINER_MATCHER_CONFIGURATION);
		createEReference(containerConfigurationEClass, CONTAINER_CONFIGURATION__ECONTAINMENT_FEATURES);

		abstractMatcherConfigurationEClass = createEClass(ABSTRACT_MATCHER_CONFIGURATION);

		specializationTypeConfigurationEClass = createEClass(SPECIALIZATION_TYPE_CONFIGURATION);
		createEReference(specializationTypeConfigurationEClass, SPECIALIZATION_TYPE_CONFIGURATION__EDIT_HELPER_ADVICE_CONFIGURATION);
		createEReference(specializationTypeConfigurationEClass, SPECIALIZATION_TYPE_CONFIGURATION__CONTAINER_CONFIGURATION);
		createEReference(specializationTypeConfigurationEClass, SPECIALIZATION_TYPE_CONFIGURATION__MATCHER_CONFIGURATION);
		createEReference(specializationTypeConfigurationEClass, SPECIALIZATION_TYPE_CONFIGURATION__SPECIALIZED_TYPES);

		abstractEditHelperAdviceConfigurationEClass = createEClass(ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION);
		createEReference(abstractEditHelperAdviceConfigurationEClass, ABSTRACT_EDIT_HELPER_ADVICE_CONFIGURATION__TARGET);

		metamodelTypeConfigurationEClass = createEClass(METAMODEL_TYPE_CONFIGURATION);
		createEReference(metamodelTypeConfigurationEClass, METAMODEL_TYPE_CONFIGURATION__ECLASS);
		createEAttribute(metamodelTypeConfigurationEClass, METAMODEL_TYPE_CONFIGURATION__EDIT_HELPER_CLASS_NAME);

		editHelperAdviceConfigurationEClass = createEClass(EDIT_HELPER_ADVICE_CONFIGURATION);
		createEAttribute(editHelperAdviceConfigurationEClass, EDIT_HELPER_ADVICE_CONFIGURATION__EDIT_HELPER_ADVICE_CLASS_NAME);

		adviceBindingConfigurationEClass = createEClass(ADVICE_BINDING_CONFIGURATION);
		createEAttribute(adviceBindingConfigurationEClass, ADVICE_BINDING_CONFIGURATION__EDIT_HELPER_ADVICE_CLASS_NAME);

		matcherConfigurationEClass = createEClass(MATCHER_CONFIGURATION);
		createEAttribute(matcherConfigurationEClass, MATCHER_CONFIGURATION__MATCHER_CLASS_NAME);

		externallyRegisteredTypeEClass = createEClass(EXTERNALLY_REGISTERED_TYPE);

		externallyRegisteredAdviceEClass = createEClass(EXTERNALLY_REGISTERED_ADVICE);

		// Create enums
		inheritanceKindEEnum = createEEnum(INHERITANCE_KIND);
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		elementTypeSetConfigurationEClass.getESuperTypes().add(this.getConfigurationElement());
		elementTypeSetConfigurationEClass.getESuperTypes().add(this.getIdentifiedConfiguration());
		elementTypeSetConfigurationEClass.getESuperTypes().add(this.getNamedConfiguration());
		elementTypeConfigurationEClass.getESuperTypes().add(this.getConfigurationElement());
		elementTypeConfigurationEClass.getESuperTypes().add(this.getIdentifiedConfiguration());
		elementTypeConfigurationEClass.getESuperTypes().add(this.getNamedConfiguration());
		abstractAdviceBindingConfigurationEClass.getESuperTypes().add(this.getAdviceConfiguration());
		abstractAdviceBindingConfigurationEClass.getESuperTypes().add(this.getIdentifiedConfiguration());
		adviceConfigurationEClass.getESuperTypes().add(this.getConfigurationElement());
		specializationTypeConfigurationEClass.getESuperTypes().add(this.getElementTypeConfiguration());
		abstractEditHelperAdviceConfigurationEClass.getESuperTypes().add(this.getAdviceConfiguration());
		metamodelTypeConfigurationEClass.getESuperTypes().add(this.getElementTypeConfiguration());
		editHelperAdviceConfigurationEClass.getESuperTypes().add(this.getAbstractEditHelperAdviceConfiguration());
		adviceBindingConfigurationEClass.getESuperTypes().add(this.getAbstractAdviceBindingConfiguration());
		matcherConfigurationEClass.getESuperTypes().add(this.getAbstractMatcherConfiguration());
		externallyRegisteredTypeEClass.getESuperTypes().add(this.getElementTypeConfiguration());
		externallyRegisteredAdviceEClass.getESuperTypes().add(this.getAdviceBindingConfiguration());

		// Initialize classes, features, and operations; add parameters
		initEClass(elementTypeSetConfigurationEClass, ElementTypeSetConfiguration.class, "ElementTypeSetConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementTypeSetConfiguration_ElementTypeConfigurations(), this.getElementTypeConfiguration(), null, "elementTypeConfigurations", null, 0, -1, ElementTypeSetConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementTypeSetConfiguration_AdviceBindingsConfigurations(), this.getAbstractAdviceBindingConfiguration(), null, "adviceBindingsConfigurations", null, 0, -1, ElementTypeSetConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementTypeSetConfiguration_MetamodelNsURI(), ecorePackage.getEString(), "metamodelNsURI", null, 1, 1, ElementTypeSetConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configurationElementEClass, ConfigurationElement.class, "ConfigurationElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConfigurationElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, ConfigurationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementTypeConfigurationEClass, ElementTypeConfiguration.class, "ElementTypeConfiguration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getElementTypeConfiguration_Hint(), ecorePackage.getEString(), "hint", "", 0, 1, ElementTypeConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementTypeConfiguration_Kind(), ecorePackage.getEString(), "kind", "org.eclipse.gmf.runtime.emf.type.core.IHintedType", 0, 1, ElementTypeConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementTypeConfiguration_IconEntry(), this.getIconEntry(), null, "iconEntry", null, 0, 1, ElementTypeConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iconEntryEClass, IconEntry.class, "IconEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIconEntry_IconPath(), ecorePackage.getEString(), "iconPath", null, 0, 1, IconEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIconEntry_BundleId(), ecorePackage.getEString(), "bundleId", null, 0, 1, IconEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(identifiedConfigurationEClass, IdentifiedConfiguration.class, "IdentifiedConfiguration", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIdentifiedConfiguration_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, IdentifiedConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedConfigurationEClass, NamedConfiguration.class, "NamedConfiguration", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedConfiguration_Name(), ecorePackage.getEString(), "name", null, 1, 1, NamedConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractAdviceBindingConfigurationEClass, AbstractAdviceBindingConfiguration.class, "AbstractAdviceBindingConfiguration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractAdviceBindingConfiguration_Target(), this.getElementTypeConfiguration(), null, "target", null, 0, 1, AbstractAdviceBindingConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractAdviceBindingConfiguration_ContainerConfiguration(), this.getContainerConfiguration(), null, "containerConfiguration", null, 0, 1, AbstractAdviceBindingConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractAdviceBindingConfiguration_MatcherConfiguration(), this.getAbstractMatcherConfiguration(), null, "matcherConfiguration", null, 0, 1, AbstractAdviceBindingConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractAdviceBindingConfiguration_Inheritance(), this.getInheritanceKind(), "inheritance", null, 1, 1, AbstractAdviceBindingConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractAdviceBindingConfiguration_ApplyToAllTypes(), ecorePackage.getEBoolean(), "applyToAllTypes", null, 1, 1, AbstractAdviceBindingConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(adviceConfigurationEClass, AdviceConfiguration.class, "AdviceConfiguration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAdviceConfiguration_Before(), this.getAdviceConfiguration(), null, "before", null, 0, -1, AdviceConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdviceConfiguration_After(), this.getAdviceConfiguration(), null, "after", null, 0, -1, AdviceConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containerConfigurationEClass, ContainerConfiguration.class, "ContainerConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainerConfiguration_ContainerMatcherConfiguration(), this.getAbstractMatcherConfiguration(), null, "containerMatcherConfiguration", null, 0, 1, ContainerConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerConfiguration_EContainmentFeatures(), theEcorePackage.getEReference(), null, "eContainmentFeatures", null, 0, -1, ContainerConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractMatcherConfigurationEClass, AbstractMatcherConfiguration.class, "AbstractMatcherConfiguration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(specializationTypeConfigurationEClass, SpecializationTypeConfiguration.class, "SpecializationTypeConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpecializationTypeConfiguration_EditHelperAdviceConfiguration(), this.getAbstractEditHelperAdviceConfiguration(), this.getAbstractEditHelperAdviceConfiguration_Target(), "editHelperAdviceConfiguration", null, 0, 1, SpecializationTypeConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecializationTypeConfiguration_ContainerConfiguration(), this.getContainerConfiguration(), null, "containerConfiguration", null, 0, 1, SpecializationTypeConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecializationTypeConfiguration_MatcherConfiguration(), this.getAbstractMatcherConfiguration(), null, "matcherConfiguration", null, 0, 1, SpecializationTypeConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecializationTypeConfiguration_SpecializedTypes(), this.getElementTypeConfiguration(), null, "specializedTypes", null, 0, -1, SpecializationTypeConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractEditHelperAdviceConfigurationEClass, AbstractEditHelperAdviceConfiguration.class, "AbstractEditHelperAdviceConfiguration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractEditHelperAdviceConfiguration_Target(), this.getSpecializationTypeConfiguration(), this.getSpecializationTypeConfiguration_EditHelperAdviceConfiguration(), "target", null, 1, 1, AbstractEditHelperAdviceConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metamodelTypeConfigurationEClass, MetamodelTypeConfiguration.class, "MetamodelTypeConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetamodelTypeConfiguration_EClass(), theEcorePackage.getEClass(), null, "eClass", null, 0, 1, MetamodelTypeConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetamodelTypeConfiguration_EditHelperClassName(), ecorePackage.getEString(), "editHelperClassName", null, 0, 1, MetamodelTypeConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editHelperAdviceConfigurationEClass, EditHelperAdviceConfiguration.class, "EditHelperAdviceConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEditHelperAdviceConfiguration_EditHelperAdviceClassName(), ecorePackage.getEString(), "editHelperAdviceClassName", null, 1, 1, EditHelperAdviceConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adviceBindingConfigurationEClass, AdviceBindingConfiguration.class, "AdviceBindingConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdviceBindingConfiguration_EditHelperAdviceClassName(), ecorePackage.getEString(), "editHelperAdviceClassName", null, 1, 1, AdviceBindingConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(matcherConfigurationEClass, MatcherConfiguration.class, "MatcherConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMatcherConfiguration_MatcherClassName(), ecorePackage.getEString(), "matcherClassName", null, 1, 1, MatcherConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externallyRegisteredTypeEClass, ExternallyRegisteredType.class, "ExternallyRegisteredType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(externallyRegisteredAdviceEClass, ExternallyRegisteredAdvice.class, "ExternallyRegisteredAdvice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(inheritanceKindEEnum, InheritanceKind.class, "InheritanceKind");
		addEEnumLiteral(inheritanceKindEEnum, InheritanceKind.NONE);
		addEEnumLiteral(inheritanceKindEEnum, InheritanceKind.ALL);

		// Create resource
		createResource(eNS_URI);
	}

} //ElementTypesConfigurationsPackageImpl
