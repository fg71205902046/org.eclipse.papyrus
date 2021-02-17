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
package org.eclipse.papyrus.gmf.codegen.genextension.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.papyrus.gmf.codegen.genextension.*;
import org.eclipse.papyrus.gmf.codegen.genextension.AdditionalEditPartCandies;
import org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas;
import org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink;
import org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode;
import org.eclipse.papyrus.gmf.codegen.genextension.CommentedElement;
import org.eclipse.papyrus.gmf.codegen.genextension.CompartmentTitleVisibilityPreference;
import org.eclipse.papyrus.gmf.codegen.genextension.CompartmentVisibilityPreference;
import org.eclipse.papyrus.gmf.codegen.genextension.ConstrainedByReferenceCompartmentItemSemanticEditPolicy;
import org.eclipse.papyrus.gmf.codegen.genextension.CustomDiagramUpdaterSingleton;
import org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingDeleteService;
import org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingReorientService;
import org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView;
import org.eclipse.papyrus.gmf.codegen.genextension.ExternalHook;
import org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint;
import org.eclipse.papyrus.gmf.codegen.genextension.GenVisualTypeProvider;
import org.eclipse.papyrus.gmf.codegen.genextension.GenerateUsingElementTypeCreationCommand;
import org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference;
import org.eclipse.papyrus.gmf.codegen.genextension.MutatingCanvas;
import org.eclipse.papyrus.gmf.codegen.genextension.OwnedEditpart;
import org.eclipse.papyrus.gmf.codegen.genextension.PapyrusExtensionRootNode;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionFactory;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;
import org.eclipse.papyrus.gmf.codegen.genextension.PropertyRefreshHook;
import org.eclipse.papyrus.gmf.codegen.genextension.SpecificDiagramUpdater;
import org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocator;
import org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocatorExternalLabel;
import org.eclipse.papyrus.gmf.codegen.genextension.SpecificNodePlate;
import org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GenExtensionFactoryImpl extends EFactoryImpl implements GenExtensionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GenExtensionFactory init() {
		try {
			GenExtensionFactory theGenExtensionFactory = (GenExtensionFactory)EPackage.Registry.INSTANCE.getEFactory(GenExtensionPackage.eNS_URI);
			if (theGenExtensionFactory != null) {
				return theGenExtensionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GenExtensionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenExtensionFactoryImpl() {
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
			case GenExtensionPackage.EXTENDED_GEN_VIEW: return createExtendedGenView();
			case GenExtensionPackage.COMMENTED_ELEMENT: return createCommentedElement();
			case GenExtensionPackage.PROPERTY_REFRESH_HOOK: return createPropertyRefreshHook();
			case GenExtensionPackage.EXTERNAL_HOOK: return createExternalHook();
			case GenExtensionPackage.SPECIFIC_LOCATOR: return createSpecificLocator();
			case GenExtensionPackage.PAPYRUS_EXTENSION_ROOT_NODE: return createPapyrusExtensionRootNode();
			case GenExtensionPackage.ALTERNATE_CANVAS: return createAlternateCanvas();
			case GenExtensionPackage.ALTERNATE_GEN_TOP_LEVEL_NODE: return createAlternateGenTopLevelNode();
			case GenExtensionPackage.ALTERNATE_GEN_LINK: return createAlternateGenLink();
			case GenExtensionPackage.MUTATING_CANVAS: return createMutatingCanvas();
			case GenExtensionPackage.OWNED_EDITPART: return createOwnedEditpart();
			case GenExtensionPackage.SPECIFIC_DIAGRAM_UPDATER: return createSpecificDiagramUpdater();
			case GenExtensionPackage.GEN_NODE_CONSTRAINT: return createGenNodeConstraint();
			case GenExtensionPackage.SPECIFIC_LOCATOR_EXTERNAL_LABEL: return createSpecificLocatorExternalLabel();
			case GenExtensionPackage.ADDITIONAL_EDIT_PART_CANDIES: return createAdditionalEditPartCandies();
			case GenExtensionPackage.EDIT_PART_USING_DELETE_SERVICE: return createEditPartUsingDeleteService();
			case GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE: return createEditPartUsingReorientService();
			case GenExtensionPackage.LABEL_VISIBILITY_PREFERENCE: return createLabelVisibilityPreference();
			case GenExtensionPackage.COMPARTMENT_VISIBILITY_PREFERENCE: return createCompartmentVisibilityPreference();
			case GenExtensionPackage.COMPARTMENT_TITLE_VISIBILITY_PREFERENCE: return createCompartmentTitleVisibilityPreference();
			case GenExtensionPackage.CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY: return createConstrainedByReferenceCompartmentItemSemanticEditPolicy();
			case GenExtensionPackage.GENERATE_USING_ELEMENT_TYPE_CREATION_COMMAND: return createGenerateUsingElementTypeCreationCommand();
			case GenExtensionPackage.CUSTOM_DIAGRAM_UPDATER_SINGLETON: return createCustomDiagramUpdaterSingleton();
			case GenExtensionPackage.SPECIFIC_NODE_PLATE: return createSpecificNodePlate();
			case GenExtensionPackage.GEN_VISUAL_TYPE_PROVIDER: return createGenVisualTypeProvider();
			case GenExtensionPackage.VISUAL_ID_OVERRIDE: return createVisualIDOverride();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExtendedGenView createExtendedGenView() {
		ExtendedGenViewImpl extendedGenView = new ExtendedGenViewImpl();
		return extendedGenView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CommentedElement createCommentedElement() {
		CommentedElementImpl commentedElement = new CommentedElementImpl();
		return commentedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PropertyRefreshHook createPropertyRefreshHook() {
		PropertyRefreshHookImpl propertyRefreshHook = new PropertyRefreshHookImpl();
		return propertyRefreshHook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExternalHook createExternalHook() {
		ExternalHookImpl externalHook = new ExternalHookImpl();
		return externalHook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpecificLocator createSpecificLocator() {
		SpecificLocatorImpl specificLocator = new SpecificLocatorImpl();
		return specificLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PapyrusExtensionRootNode createPapyrusExtensionRootNode() {
		PapyrusExtensionRootNodeImpl papyrusExtensionRootNode = new PapyrusExtensionRootNodeImpl();
		return papyrusExtensionRootNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AlternateCanvas createAlternateCanvas() {
		AlternateCanvasImpl alternateCanvas = new AlternateCanvasImpl();
		return alternateCanvas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AlternateGenTopLevelNode createAlternateGenTopLevelNode() {
		AlternateGenTopLevelNodeImpl alternateGenTopLevelNode = new AlternateGenTopLevelNodeImpl();
		return alternateGenTopLevelNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AlternateGenLink createAlternateGenLink() {
		AlternateGenLinkImpl alternateGenLink = new AlternateGenLinkImpl();
		return alternateGenLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MutatingCanvas createMutatingCanvas() {
		MutatingCanvasImpl mutatingCanvas = new MutatingCanvasImpl();
		return mutatingCanvas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OwnedEditpart createOwnedEditpart() {
		OwnedEditpartImpl ownedEditpart = new OwnedEditpartImpl();
		return ownedEditpart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpecificDiagramUpdater createSpecificDiagramUpdater() {
		SpecificDiagramUpdaterImpl specificDiagramUpdater = new SpecificDiagramUpdaterImpl();
		return specificDiagramUpdater;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GenNodeConstraint createGenNodeConstraint() {
		GenNodeConstraintImpl genNodeConstraint = new GenNodeConstraintImpl();
		return genNodeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpecificLocatorExternalLabel createSpecificLocatorExternalLabel() {
		SpecificLocatorExternalLabelImpl specificLocatorExternalLabel = new SpecificLocatorExternalLabelImpl();
		return specificLocatorExternalLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdditionalEditPartCandies createAdditionalEditPartCandies() {
		AdditionalEditPartCandiesImpl additionalEditPartCandies = new AdditionalEditPartCandiesImpl();
		return additionalEditPartCandies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EditPartUsingDeleteService createEditPartUsingDeleteService() {
		EditPartUsingDeleteServiceImpl editPartUsingDeleteService = new EditPartUsingDeleteServiceImpl();
		return editPartUsingDeleteService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EditPartUsingReorientService createEditPartUsingReorientService() {
		EditPartUsingReorientServiceImpl editPartUsingReorientService = new EditPartUsingReorientServiceImpl();
		return editPartUsingReorientService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LabelVisibilityPreference createLabelVisibilityPreference() {
		LabelVisibilityPreferenceImpl labelVisibilityPreference = new LabelVisibilityPreferenceImpl();
		return labelVisibilityPreference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompartmentVisibilityPreference createCompartmentVisibilityPreference() {
		CompartmentVisibilityPreferenceImpl compartmentVisibilityPreference = new CompartmentVisibilityPreferenceImpl();
		return compartmentVisibilityPreference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompartmentTitleVisibilityPreference createCompartmentTitleVisibilityPreference() {
		CompartmentTitleVisibilityPreferenceImpl compartmentTitleVisibilityPreference = new CompartmentTitleVisibilityPreferenceImpl();
		return compartmentTitleVisibilityPreference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConstrainedByReferenceCompartmentItemSemanticEditPolicy createConstrainedByReferenceCompartmentItemSemanticEditPolicy() {
		ConstrainedByReferenceCompartmentItemSemanticEditPolicyImpl constrainedByReferenceCompartmentItemSemanticEditPolicy = new ConstrainedByReferenceCompartmentItemSemanticEditPolicyImpl();
		return constrainedByReferenceCompartmentItemSemanticEditPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GenerateUsingElementTypeCreationCommand createGenerateUsingElementTypeCreationCommand() {
		GenerateUsingElementTypeCreationCommandImpl generateUsingElementTypeCreationCommand = new GenerateUsingElementTypeCreationCommandImpl();
		return generateUsingElementTypeCreationCommand;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomDiagramUpdaterSingleton createCustomDiagramUpdaterSingleton() {
		CustomDiagramUpdaterSingletonImpl customDiagramUpdaterSingleton = new CustomDiagramUpdaterSingletonImpl();
		return customDiagramUpdaterSingleton;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpecificNodePlate createSpecificNodePlate() {
		SpecificNodePlateImpl specificNodePlate = new SpecificNodePlateImpl();
		return specificNodePlate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GenVisualTypeProvider createGenVisualTypeProvider() {
		GenVisualTypeProviderImpl genVisualTypeProvider = new GenVisualTypeProviderImpl();
		return genVisualTypeProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VisualIDOverride createVisualIDOverride() {
		VisualIDOverrideImpl visualIDOverride = new VisualIDOverrideImpl();
		return visualIDOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GenExtensionPackage getGenExtensionPackage() {
		return (GenExtensionPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GenExtensionPackage getPackage() {
		return GenExtensionPackage.eINSTANCE;
	}

} //GenExtensionFactoryImpl
