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


import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
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
import org.eclipse.papyrus.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.papyrus.gmf.codegen.gmfgen.impl.GMFGenPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GenExtensionPackageImpl extends EPackageImpl implements GenExtensionPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedGenViewEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyRefreshHookEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalHookEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specificLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass papyrusExtensionRootNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alternateCanvasEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alternateGenTopLevelNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alternateGenLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mutatingCanvasEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ownedEditpartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specificDiagramUpdaterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genNodeConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specificLocatorExternalLabelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass additionalEditPartCandiesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editPartUsingDeleteServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editPartUsingReorientServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelVisibilityPreferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compartmentVisibilityPreferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compartmentTitleVisibilityPreferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constrainedByReferenceCompartmentItemSemanticEditPolicyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generateUsingElementTypeCreationCommandEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customDiagramUpdaterSingletonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specificNodePlateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genVisualTypeProviderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass visualIDOverrideEClass = null;

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
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GenExtensionPackageImpl() {
		super(eNS_URI, GenExtensionFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link GenExtensionPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @generated
	 */
	public static GenExtensionPackage init() {
		if (isInited) return (GenExtensionPackage)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredGenExtensionPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		GenExtensionPackageImpl theGenExtensionPackage = registeredGenExtensionPackage instanceof GenExtensionPackageImpl ? (GenExtensionPackageImpl)registeredGenExtensionPackage : new GenExtensionPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		GenModelPackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(GMFGenPackage.eNS_URI);
		GMFGenPackageImpl theGMFGenPackage = (GMFGenPackageImpl)(registeredPackage instanceof GMFGenPackageImpl ? registeredPackage : GMFGenPackage.eINSTANCE);

		// Load packages
		theGMFGenPackage.loadPackage();

		// Fix loaded packages
		theGenExtensionPackage.fixPackageContents();
		theGMFGenPackage.fixPackageContents();

		// Mark meta-data to indicate it can't be changed
		theGenExtensionPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GenExtensionPackage.eNS_URI, theGenExtensionPackage);
		return theGenExtensionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExtendedGenView() {
		if (extendedGenViewEClass == null) {
			extendedGenViewEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(0);
		}
		return extendedGenViewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExtendedGenView_GenView() {
        return (EReference)getExtendedGenView().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExtendedGenView_IsAbstract() {
        return (EAttribute)getExtendedGenView().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExtendedGenView_SuperGenViews() {
        return (EReference)getExtendedGenView().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExtendedGenView_PropRefreshHook() {
        return (EReference)getExtendedGenView().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExtendedGenView_Name() {
        return (EAttribute)getExtendedGenView().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExtendedGenView_SuperOwnedEditPart() {
        return (EAttribute)getExtendedGenView().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCommentedElement() {
		if (commentedElementEClass == null) {
			commentedElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(1);
		}
		return commentedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCommentedElement_Comment() {
        return (EAttribute)getCommentedElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPropertyRefreshHook() {
		if (propertyRefreshHookEClass == null) {
			propertyRefreshHookEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(2);
		}
		return propertyRefreshHookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPropertyRefreshHook_TriggeringCondition() {
        return (EAttribute)getPropertyRefreshHook().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPropertyRefreshHook_Action() {
        return (EAttribute)getPropertyRefreshHook().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExternalHook() {
		if (externalHookEClass == null) {
			externalHookEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(3);
		}
		return externalHookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExternalHook_Classpath() {
        return (EAttribute)getExternalHook().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpecificLocator() {
		if (specificLocatorEClass == null) {
			specificLocatorEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(4);
		}
		return specificLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpecificLocator_GenChildSideAffixedNode() {
        return (EReference)getSpecificLocator().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPapyrusExtensionRootNode() {
		if (papyrusExtensionRootNodeEClass == null) {
			papyrusExtensionRootNodeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(5);
		}
		return papyrusExtensionRootNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPapyrusExtensionRootNode_ExtensionNodes() {
        return (EReference)getPapyrusExtensionRootNode().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAlternateCanvas() {
		if (alternateCanvasEClass == null) {
			alternateCanvasEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(6);
		}
		return alternateCanvasEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAlternateCanvas_DomainDiagramElement() {
        return (EReference)getAlternateCanvas().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAlternateCanvas_AlternateTopLevelNodes() {
        return (EReference)getAlternateCanvas().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAlternateCanvas_AlternateLinkNodes() {
        return (EReference)getAlternateCanvas().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAlternateCanvas_Diagram() {
        return (EReference)getAlternateCanvas().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAlternateGenTopLevelNode() {
		if (alternateGenTopLevelNodeEClass == null) {
			alternateGenTopLevelNodeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(7);
		}
		return alternateGenTopLevelNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAlternateGenTopLevelNode_GenTopLevelNode() {
        return (EReference)getAlternateGenTopLevelNode().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAlternateGenTopLevelNode_TypeModelFacet() {
        return (EReference)getAlternateGenTopLevelNode().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAlternateGenLink() {
		if (alternateGenLinkEClass == null) {
			alternateGenLinkEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(8);
		}
		return alternateGenLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAlternateGenLink_GenLinkNode() {
        return (EReference)getAlternateGenLink().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAlternateGenLink_TypeModelFacet() {
        return (EReference)getAlternateGenLink().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMutatingCanvas() {
		if (mutatingCanvasEClass == null) {
			mutatingCanvasEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(9);
		}
		return mutatingCanvasEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMutatingCanvas_AlternateCanvases() {
        return (EReference)getMutatingCanvas().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOwnedEditpart() {
		if (ownedEditpartEClass == null) {
			ownedEditpartEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(10);
		}
		return ownedEditpartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOwnedEditpart_Name() {
        return (EAttribute)getOwnedEditpart().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpecificDiagramUpdater() {
		if (specificDiagramUpdaterEClass == null) {
			specificDiagramUpdaterEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(11);
		}
		return specificDiagramUpdaterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpecificDiagramUpdater_GenNode() {
        return (EReference)getSpecificDiagramUpdater().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGenNodeConstraint() {
		if (genNodeConstraintEClass == null) {
			genNodeConstraintEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(12);
		}
		return genNodeConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGenNodeConstraint_GenNode() {
        return (EReference)getGenNodeConstraint().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGenNodeConstraint_GenConstraint() {
        return (EReference)getGenNodeConstraint().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpecificLocatorExternalLabel() {
		if (specificLocatorExternalLabelEClass == null) {
			specificLocatorExternalLabelEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(13);
		}
		return specificLocatorExternalLabelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpecificLocatorExternalLabel_GenExternalNodeLabel() {
        return (EReference)getSpecificLocatorExternalLabel().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdditionalEditPartCandies() {
		if (additionalEditPartCandiesEClass == null) {
			additionalEditPartCandiesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(14);
		}
		return additionalEditPartCandiesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAdditionalEditPartCandies_BaseEditHelperPackage() {
        return (EAttribute)getAdditionalEditPartCandies().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEditPartUsingDeleteService() {
		if (editPartUsingDeleteServiceEClass == null) {
			editPartUsingDeleteServiceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(15);
		}
		return editPartUsingDeleteServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEditPartUsingDeleteService_Name() {
        return (EAttribute)getEditPartUsingDeleteService().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEditPartUsingDeleteService_GenView() {
        return (EReference)getEditPartUsingDeleteService().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEditPartUsingReorientService() {
		if (editPartUsingReorientServiceEClass == null) {
			editPartUsingReorientServiceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(16);
		}
		return editPartUsingReorientServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEditPartUsingReorientService_Name() {
        return (EAttribute)getEditPartUsingReorientService().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEditPartUsingReorientService_GenView() {
        return (EReference)getEditPartUsingReorientService().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLabelVisibilityPreference() {
		if (labelVisibilityPreferenceEClass == null) {
			labelVisibilityPreferenceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(17);
		}
		return labelVisibilityPreferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabelVisibilityPreference_Role() {
        return (EAttribute)getLabelVisibilityPreference().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabelVisibilityPreference_IconPathRole() {
        return (EAttribute)getLabelVisibilityPreference().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLabelVisibilityPreference_LinkLabels() {
        return (EReference)getLabelVisibilityPreference().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLabelVisibilityPreference_ExternalNodeLabels() {
        return (EReference)getLabelVisibilityPreference().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabelVisibilityPreference_VisibleByDefault() {
        return (EAttribute)getLabelVisibilityPreference().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompartmentVisibilityPreference() {
		if (compartmentVisibilityPreferenceEClass == null) {
			compartmentVisibilityPreferenceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(18);
		}
		return compartmentVisibilityPreferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompartmentVisibilityPreference_VisibleByDefault() {
        return (EAttribute)getCompartmentVisibilityPreference().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartmentVisibilityPreference_Compartments() {
        return (EReference)getCompartmentVisibilityPreference().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompartmentTitleVisibilityPreference() {
		if (compartmentTitleVisibilityPreferenceEClass == null) {
			compartmentTitleVisibilityPreferenceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(19);
		}
		return compartmentTitleVisibilityPreferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompartmentTitleVisibilityPreference_VisibleByDefault() {
        return (EAttribute)getCompartmentTitleVisibilityPreference().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompartmentTitleVisibilityPreference_Compartments() {
        return (EReference)getCompartmentTitleVisibilityPreference().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConstrainedByReferenceCompartmentItemSemanticEditPolicy() {
		if (constrainedByReferenceCompartmentItemSemanticEditPolicyEClass == null) {
			constrainedByReferenceCompartmentItemSemanticEditPolicyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(20);
		}
		return constrainedByReferenceCompartmentItemSemanticEditPolicyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstrainedByReferenceCompartmentItemSemanticEditPolicy_GenView() {
        return (EReference)getConstrainedByReferenceCompartmentItemSemanticEditPolicy().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGenerateUsingElementTypeCreationCommand() {
		if (generateUsingElementTypeCreationCommandEClass == null) {
			generateUsingElementTypeCreationCommandEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(21);
		}
		return generateUsingElementTypeCreationCommandEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomDiagramUpdaterSingleton() {
		if (customDiagramUpdaterSingletonEClass == null) {
			customDiagramUpdaterSingletonEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(22);
		}
		return customDiagramUpdaterSingletonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomDiagramUpdaterSingleton_SingletonPath() {
        return (EAttribute)getCustomDiagramUpdaterSingleton().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpecificNodePlate() {
		if (specificNodePlateEClass == null) {
			specificNodePlateEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(23);
		}
		return specificNodePlateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpecificNodePlate_EditParts() {
        return (EReference)getSpecificNodePlate().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpecificNodePlate_Name() {
        return (EAttribute)getSpecificNodePlate().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpecificNodePlate_NodePlateQualifiedName() {
        return (EAttribute)getSpecificNodePlate().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGenVisualTypeProvider() {
		if (genVisualTypeProviderEClass == null) {
			genVisualTypeProviderEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(24);
		}
		return genVisualTypeProviderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGenVisualTypeProvider_ClassName() {
        return (EAttribute)getGenVisualTypeProvider().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVisualIDOverride() {
		if (visualIDOverrideEClass == null) {
			visualIDOverrideEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(GenExtensionPackage.eNS_URI).getEClassifiers().get(25);
		}
		return visualIDOverrideEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVisualIDOverride_GenView() {
        return (EReference)getVisualIDOverride().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVisualIDOverride_VisualID() {
        return (EAttribute)getVisualIDOverride().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVisualIDOverride_Child() {
        return (EReference)getVisualIDOverride().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GenExtensionFactory getGenExtensionFactory() {
		return (GenExtensionFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isFixed = false;

	/**
	 * Fixes up the loaded package, to make it appear as if it had been programmatically built.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fixPackageContents() {
		if (isFixed) return;
		isFixed = true;
		fixEClassifiers();
	}

	/**
	 * Sets the instance class on the given classifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void fixInstanceClass(EClassifier eClassifier) {
		if (eClassifier.getInstanceClassName() == null) {
			eClassifier.setInstanceClassName("org.eclipse.papyrus.gmf.codegen.genextension." + eClassifier.getName());
			setGeneratedClassName(eClassifier);
		}
	}

} //GenExtensionPackageImpl
