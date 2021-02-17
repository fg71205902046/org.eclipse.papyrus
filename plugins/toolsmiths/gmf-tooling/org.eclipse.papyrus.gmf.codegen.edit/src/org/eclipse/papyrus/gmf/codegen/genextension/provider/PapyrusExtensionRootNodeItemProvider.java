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
package org.eclipse.papyrus.gmf.codegen.genextension.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionFactory;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;
import org.eclipse.papyrus.gmf.codegen.genextension.PapyrusExtensionRootNode;

/**
 * This is the item provider adapter for a {@link org.eclipse.papyrus.gmf.codegen.genextension.PapyrusExtensionRootNode} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PapyrusExtensionRootNodeItemProvider extends CommentedElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PapyrusExtensionRootNodeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns PapyrusExtensionRootNode.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/PapyrusExtensionRootNode"));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((PapyrusExtensionRootNode)object).getComment();
		return label == null || label.length() == 0 ?
			getString("_UI_PapyrusExtensionRootNode_type") :
			getString("_UI_PapyrusExtensionRootNode_type") + " " + label;
	}


	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(PapyrusExtensionRootNode.class)) {
			case GenExtensionPackage.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createCommentedElement()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createExtendedGenView()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createExternalHook()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createPropertyRefreshHook()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createSpecificLocator()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createPapyrusExtensionRootNode()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createAlternateCanvas()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createAlternateGenTopLevelNode()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createAlternateGenLink()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createMutatingCanvas()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createOwnedEditpart()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createSpecificDiagramUpdater()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createGenNodeConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createSpecificLocatorExternalLabel()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createAdditionalEditPartCandies()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createEditPartUsingDeleteService()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createEditPartUsingReorientService()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createLabelVisibilityPreference()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createCompartmentVisibilityPreference()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createCompartmentTitleVisibilityPreference()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createConstrainedByReferenceCompartmentItemSemanticEditPolicy()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createGenerateUsingElementTypeCreationCommand()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createCustomDiagramUpdaterSingleton()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createSpecificNodePlate()));

		newChildDescriptors.add
			(createChildParameter
				(GenExtensionPackage.Literals.PAPYRUS_EXTENSION_ROOT_NODE__EXTENSION_NODES,
				 GenExtensionFactory.eINSTANCE.createGenVisualTypeProvider()));
	}

}
