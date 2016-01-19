/**
 *  Copyright (c) 2011 Mia-Software.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 * 	Gregoire Dupe (Mia-Software) - Design
 * 	Nicolas Guyomar (Mia-Software) - Implementation
 * 	Emmanuelle Rouill� (Mia-Software) - Bug 352618 - To be able to use non derived facet structural features and save them values.
 * 	Nicolas Bros (Mia-Software) - Bug 361823 - [Restructuring] eFacet2 meta-model
 *       Gregoire Dupe (Mia-Software) - Bug 366055 - NavigationQuery
 *       Gregoire Dupe (Mia-Software) - Bug 369673 - [Facet] IsOneOfQuery
 *       Olivier Remaud (Soft-Maint) - Bug 369824 - Add a simple way to return string literal constants from a customization query
 *       Gregoire Dupe (Mia-Software) - Bug 373078 - API Cleaning
 *       Gregoire Dupe (Mia-Software) - Bug 376576 - [EFacet] Change the multiplicity of Facet::extendedFacet
 */
package org.eclipse.papyrus.emf.facet.efacet.metamodel.v0_2_0.efacet.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.provider.EClassifierItemProvider;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.papyrus.emf.facet.efacet.metamodel.v0_2_0.efacet.EFacetFactory;
import org.eclipse.papyrus.emf.facet.efacet.metamodel.v0_2_0.efacet.EFacetPackage;
import org.eclipse.papyrus.emf.facet.efacet.metamodel.v0_2_0.efacet.Facet;

/**
 * This is the item provider adapter for a {@link org.eclipse.papyrus.emf.facet.efacet.metamodel.v0_2_0.efacet.Facet} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class FacetItemProvider
		extends EClassifierItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public FacetItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addDocumentationPropertyDescriptor(object);
			addExtendedMetaclassPropertyDescriptor(object);
			addConformanceTypedElementPropertyDescriptor(object);
			addExtendedFacetsPropertyDescriptor(object);
			addAllTypedElementsPropertyDescriptor(object);
			addAllFacetOperationsPropertyDescriptor(object);
			addAllFacetElementsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Documentation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void addDocumentationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_DocumentedElement_documentation_feature"), //$NON-NLS-1$
				getString("_UI_PropertyDescriptor_description", "_UI_DocumentedElement_documentation_feature", "_UI_DocumentedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				EFacetPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION,
				true,
				false,
				false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				null,
				null));
	}

	/**
	 * This adds a property descriptor for the Extended Metaclass feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void addExtendedMetaclassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_Facet_extendedMetaclass_feature"), //$NON-NLS-1$
				getString("_UI_PropertyDescriptor_description", "_UI_Facet_extendedMetaclass_feature", "_UI_Facet_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				EFacetPackage.Literals.FACET__EXTENDED_METACLASS,
				true,
				false,
				true,
				null,
				null,
				null));
	}

	/**
	 * This adds a property descriptor for the Conformance Typed Element feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void addConformanceTypedElementPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_Facet_conformanceTypedElement_feature"), //$NON-NLS-1$
				getString("_UI_PropertyDescriptor_description", "_UI_Facet_conformanceTypedElement_feature", "_UI_Facet_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				EFacetPackage.Literals.FACET__CONFORMANCE_TYPED_ELEMENT,
				true,
				false,
				true,
				null,
				null,
				null));
	}

	/**
	 * This adds a property descriptor for the Extended Facets feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void addExtendedFacetsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_Facet_extendedFacets_feature"), //$NON-NLS-1$
				getString("_UI_PropertyDescriptor_description", "_UI_Facet_extendedFacets_feature", "_UI_Facet_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				EFacetPackage.Literals.FACET__EXTENDED_FACETS,
				true,
				false,
				true,
				null,
				null,
				null));
	}

	/**
	 * This adds a property descriptor for the All Typed Elements feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void addAllTypedElementsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_Facet_allTypedElements_feature"), //$NON-NLS-1$
				getString("_UI_PropertyDescriptor_description", "_UI_Facet_allTypedElements_feature", "_UI_Facet_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				EFacetPackage.Literals.FACET__ALL_TYPED_ELEMENTS,
				false,
				false,
				false,
				null,
				null,
				null));
	}

	/**
	 * This adds a property descriptor for the All Facet Operations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void addAllFacetOperationsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_Facet_allFacetOperations_feature"), //$NON-NLS-1$
				getString("_UI_PropertyDescriptor_description", "_UI_Facet_allFacetOperations_feature", "_UI_Facet_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				EFacetPackage.Literals.FACET__ALL_FACET_OPERATIONS,
				false,
				false,
				false,
				null,
				null,
				null));
	}

	/**
	 * This adds a property descriptor for the All Facet Elements feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void addAllFacetElementsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_Facet_allFacetElements_feature"), //$NON-NLS-1$
				getString("_UI_PropertyDescriptor_description", "_UI_Facet_allFacetElements_feature", "_UI_Facet_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				EFacetPackage.Literals.FACET__ALL_FACET_ELEMENTS,
				false,
				false,
				false,
				null,
				null,
				null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(EFacetPackage.Literals.FACET__FACET_ELEMENTS);
			childrenFeatures.add(EFacetPackage.Literals.FACET__FACET_OPERATIONS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Facet.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Facet")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Facet) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Facet_type") //$NON-NLS-1$
				:
				getString("_UI_Facet_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Facet.class)) {
		case EFacetPackage.FACET__DOCUMENTATION:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case EFacetPackage.FACET__FACET_ELEMENTS:
		case EFacetPackage.FACET__FACET_OPERATIONS:
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
	 *
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(EFacetPackage.Literals.FACET__FACET_ELEMENTS,
				EFacetFactory.eINSTANCE.createFacetAttribute()));

		newChildDescriptors.add(createChildParameter(EFacetPackage.Literals.FACET__FACET_ELEMENTS,
				EFacetFactory.eINSTANCE.createFacetReference()));

		newChildDescriptors.add(createChildParameter(EFacetPackage.Literals.FACET__FACET_ELEMENTS,
				EcoreFactory.eINSTANCE.createEAttribute()));

		newChildDescriptors.add(createChildParameter(EFacetPackage.Literals.FACET__FACET_ELEMENTS,
				EcoreFactory.eINSTANCE.createEReference()));

		newChildDescriptors.add(createChildParameter(EFacetPackage.Literals.FACET__FACET_OPERATIONS,
				EFacetFactory.eINSTANCE.createFacetOperation()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return Efacet2EditPlugin.INSTANCE;
	}

}
