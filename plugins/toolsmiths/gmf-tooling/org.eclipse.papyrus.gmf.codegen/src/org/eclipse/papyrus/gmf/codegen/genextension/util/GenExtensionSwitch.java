/**
 * Copyright (c) 2015 CEA LIST and others.
 * 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 * 
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *   CEA LIST - Initial API and implementation
 */
package org.eclipse.papyrus.gmf.codegen.genextension.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.papyrus.gmf.codegen.genextension.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage
 * @generated
 */
public class GenExtensionSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GenExtensionPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenExtensionSwitch() {
		if (modelPackage == null) {
			modelPackage = GenExtensionPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case GenExtensionPackage.EXTENDED_GEN_VIEW: {
				ExtendedGenView extendedGenView = (ExtendedGenView)theEObject;
				T result = caseExtendedGenView(extendedGenView);
				if (result == null) result = caseCommentedElement(extendedGenView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.COMMENTED_ELEMENT: {
				CommentedElement commentedElement = (CommentedElement)theEObject;
				T result = caseCommentedElement(commentedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.PROPERTY_REFRESH_HOOK: {
				PropertyRefreshHook propertyRefreshHook = (PropertyRefreshHook)theEObject;
				T result = casePropertyRefreshHook(propertyRefreshHook);
				if (result == null) result = caseExternalHook(propertyRefreshHook);
				if (result == null) result = caseCommentedElement(propertyRefreshHook);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.EXTERNAL_HOOK: {
				ExternalHook externalHook = (ExternalHook)theEObject;
				T result = caseExternalHook(externalHook);
				if (result == null) result = caseCommentedElement(externalHook);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.SPECIFIC_LOCATOR: {
				SpecificLocator specificLocator = (SpecificLocator)theEObject;
				T result = caseSpecificLocator(specificLocator);
				if (result == null) result = caseExternalHook(specificLocator);
				if (result == null) result = caseCommentedElement(specificLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.PAPYRUS_EXTENSION_ROOT_NODE: {
				PapyrusExtensionRootNode papyrusExtensionRootNode = (PapyrusExtensionRootNode)theEObject;
				T result = casePapyrusExtensionRootNode(papyrusExtensionRootNode);
				if (result == null) result = caseCommentedElement(papyrusExtensionRootNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.ALTERNATE_CANVAS: {
				AlternateCanvas alternateCanvas = (AlternateCanvas)theEObject;
				T result = caseAlternateCanvas(alternateCanvas);
				if (result == null) result = caseCommentedElement(alternateCanvas);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.ALTERNATE_GEN_TOP_LEVEL_NODE: {
				AlternateGenTopLevelNode alternateGenTopLevelNode = (AlternateGenTopLevelNode)theEObject;
				T result = caseAlternateGenTopLevelNode(alternateGenTopLevelNode);
				if (result == null) result = caseCommentedElement(alternateGenTopLevelNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.ALTERNATE_GEN_LINK: {
				AlternateGenLink alternateGenLink = (AlternateGenLink)theEObject;
				T result = caseAlternateGenLink(alternateGenLink);
				if (result == null) result = caseCommentedElement(alternateGenLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.MUTATING_CANVAS: {
				MutatingCanvas mutatingCanvas = (MutatingCanvas)theEObject;
				T result = caseMutatingCanvas(mutatingCanvas);
				if (result == null) result = caseCommentedElement(mutatingCanvas);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.OWNED_EDITPART: {
				OwnedEditpart ownedEditpart = (OwnedEditpart)theEObject;
				T result = caseOwnedEditpart(ownedEditpart);
				if (result == null) result = caseExternalHook(ownedEditpart);
				if (result == null) result = caseCommentedElement(ownedEditpart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.SPECIFIC_DIAGRAM_UPDATER: {
				SpecificDiagramUpdater specificDiagramUpdater = (SpecificDiagramUpdater)theEObject;
				T result = caseSpecificDiagramUpdater(specificDiagramUpdater);
				if (result == null) result = caseExternalHook(specificDiagramUpdater);
				if (result == null) result = caseCommentedElement(specificDiagramUpdater);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.GEN_NODE_CONSTRAINT: {
				GenNodeConstraint genNodeConstraint = (GenNodeConstraint)theEObject;
				T result = caseGenNodeConstraint(genNodeConstraint);
				if (result == null) result = caseCommentedElement(genNodeConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.SPECIFIC_LOCATOR_EXTERNAL_LABEL: {
				SpecificLocatorExternalLabel specificLocatorExternalLabel = (SpecificLocatorExternalLabel)theEObject;
				T result = caseSpecificLocatorExternalLabel(specificLocatorExternalLabel);
				if (result == null) result = caseExternalHook(specificLocatorExternalLabel);
				if (result == null) result = caseCommentedElement(specificLocatorExternalLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.ADDITIONAL_EDIT_PART_CANDIES: {
				AdditionalEditPartCandies additionalEditPartCandies = (AdditionalEditPartCandies)theEObject;
				T result = caseAdditionalEditPartCandies(additionalEditPartCandies);
				if (result == null) result = caseCommentedElement(additionalEditPartCandies);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.EDIT_PART_USING_DELETE_SERVICE: {
				EditPartUsingDeleteService editPartUsingDeleteService = (EditPartUsingDeleteService)theEObject;
				T result = caseEditPartUsingDeleteService(editPartUsingDeleteService);
				if (result == null) result = caseCommentedElement(editPartUsingDeleteService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.EDIT_PART_USING_REORIENT_SERVICE: {
				EditPartUsingReorientService editPartUsingReorientService = (EditPartUsingReorientService)theEObject;
				T result = caseEditPartUsingReorientService(editPartUsingReorientService);
				if (result == null) result = caseCommentedElement(editPartUsingReorientService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.LABEL_VISIBILITY_PREFERENCE: {
				LabelVisibilityPreference labelVisibilityPreference = (LabelVisibilityPreference)theEObject;
				T result = caseLabelVisibilityPreference(labelVisibilityPreference);
				if (result == null) result = caseCommentedElement(labelVisibilityPreference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.COMPARTMENT_VISIBILITY_PREFERENCE: {
				CompartmentVisibilityPreference compartmentVisibilityPreference = (CompartmentVisibilityPreference)theEObject;
				T result = caseCompartmentVisibilityPreference(compartmentVisibilityPreference);
				if (result == null) result = caseCommentedElement(compartmentVisibilityPreference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.COMPARTMENT_TITLE_VISIBILITY_PREFERENCE: {
				CompartmentTitleVisibilityPreference compartmentTitleVisibilityPreference = (CompartmentTitleVisibilityPreference)theEObject;
				T result = caseCompartmentTitleVisibilityPreference(compartmentTitleVisibilityPreference);
				if (result == null) result = caseCommentedElement(compartmentTitleVisibilityPreference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.CONSTRAINED_BY_REFERENCE_COMPARTMENT_ITEM_SEMANTIC_EDIT_POLICY: {
				ConstrainedByReferenceCompartmentItemSemanticEditPolicy constrainedByReferenceCompartmentItemSemanticEditPolicy = (ConstrainedByReferenceCompartmentItemSemanticEditPolicy)theEObject;
				T result = caseConstrainedByReferenceCompartmentItemSemanticEditPolicy(constrainedByReferenceCompartmentItemSemanticEditPolicy);
				if (result == null) result = caseCommentedElement(constrainedByReferenceCompartmentItemSemanticEditPolicy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.GENERATE_USING_ELEMENT_TYPE_CREATION_COMMAND: {
				GenerateUsingElementTypeCreationCommand generateUsingElementTypeCreationCommand = (GenerateUsingElementTypeCreationCommand)theEObject;
				T result = caseGenerateUsingElementTypeCreationCommand(generateUsingElementTypeCreationCommand);
				if (result == null) result = caseCommentedElement(generateUsingElementTypeCreationCommand);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.CUSTOM_DIAGRAM_UPDATER_SINGLETON: {
				CustomDiagramUpdaterSingleton customDiagramUpdaterSingleton = (CustomDiagramUpdaterSingleton)theEObject;
				T result = caseCustomDiagramUpdaterSingleton(customDiagramUpdaterSingleton);
				if (result == null) result = caseCommentedElement(customDiagramUpdaterSingleton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.SPECIFIC_NODE_PLATE: {
				SpecificNodePlate specificNodePlate = (SpecificNodePlate)theEObject;
				T result = caseSpecificNodePlate(specificNodePlate);
				if (result == null) result = caseCommentedElement(specificNodePlate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.GEN_VISUAL_TYPE_PROVIDER: {
				GenVisualTypeProvider genVisualTypeProvider = (GenVisualTypeProvider)theEObject;
				T result = caseGenVisualTypeProvider(genVisualTypeProvider);
				if (result == null) result = caseCommentedElement(genVisualTypeProvider);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case GenExtensionPackage.VISUAL_ID_OVERRIDE: {
				VisualIDOverride visualIDOverride = (VisualIDOverride)theEObject;
				T result = caseVisualIDOverride(visualIDOverride);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extended Gen View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extended Gen View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtendedGenView(ExtendedGenView object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Commented Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Commented Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommentedElement(CommentedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Refresh Hook</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Refresh Hook</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyRefreshHook(PropertyRefreshHook object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Hook</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Hook</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalHook(ExternalHook object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Specific Locator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specific Locator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecificLocator(SpecificLocator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Papyrus Extension Root Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Papyrus Extension Root Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePapyrusExtensionRootNode(PapyrusExtensionRootNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Alternate Canvas</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Alternate Canvas</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlternateCanvas(AlternateCanvas object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Alternate Gen Top Level Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Alternate Gen Top Level Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlternateGenTopLevelNode(AlternateGenTopLevelNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Alternate Gen Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Alternate Gen Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlternateGenLink(AlternateGenLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mutating Canvas</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mutating Canvas</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMutatingCanvas(MutatingCanvas object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Owned Editpart</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Owned Editpart</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOwnedEditpart(OwnedEditpart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Specific Diagram Updater</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specific Diagram Updater</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecificDiagramUpdater(SpecificDiagramUpdater object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gen Node Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gen Node Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenNodeConstraint(GenNodeConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Specific Locator External Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specific Locator External Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecificLocatorExternalLabel(SpecificLocatorExternalLabel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Additional Edit Part Candies</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Additional Edit Part Candies</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdditionalEditPartCandies(AdditionalEditPartCandies object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Edit Part Using Delete Service</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Edit Part Using Delete Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditPartUsingDeleteService(EditPartUsingDeleteService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Edit Part Using Reorient Service</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Edit Part Using Reorient Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditPartUsingReorientService(EditPartUsingReorientService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label Visibility Preference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label Visibility Preference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabelVisibilityPreference(LabelVisibilityPreference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Compartment Visibility Preference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Compartment Visibility Preference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompartmentVisibilityPreference(CompartmentVisibilityPreference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Compartment Title Visibility Preference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Compartment Title Visibility Preference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompartmentTitleVisibilityPreference(CompartmentTitleVisibilityPreference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constrained By Reference Compartment Item Semantic Edit Policy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constrained By Reference Compartment Item Semantic Edit Policy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstrainedByReferenceCompartmentItemSemanticEditPolicy(ConstrainedByReferenceCompartmentItemSemanticEditPolicy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generate Using Element Type Creation Command</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generate Using Element Type Creation Command</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenerateUsingElementTypeCreationCommand(GenerateUsingElementTypeCreationCommand object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Diagram Updater Singleton</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Diagram Updater Singleton</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomDiagramUpdaterSingleton(CustomDiagramUpdaterSingleton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Specific Node Plate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specific Node Plate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecificNodePlate(SpecificNodePlate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gen Visual Type Provider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gen Visual Type Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenVisualTypeProvider(GenVisualTypeProvider object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Visual ID Override</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Visual ID Override</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVisualIDOverride(VisualIDOverride object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //GenExtensionSwitch
