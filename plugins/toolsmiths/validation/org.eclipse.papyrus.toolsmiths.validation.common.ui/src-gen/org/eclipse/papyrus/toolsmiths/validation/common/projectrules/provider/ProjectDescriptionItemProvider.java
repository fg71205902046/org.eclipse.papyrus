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
package org.eclipse.papyrus.toolsmiths.validation.common.projectrules.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.ui.Activator;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage;
import org.eclipse.uml2.common.edit.command.SubsetAddCommand;
import org.eclipse.uml2.common.edit.command.SubsetSupersetReplaceCommand;
import org.eclipse.uml2.common.edit.command.SubsetSupersetSetCommand;
import org.eclipse.uml2.common.edit.command.SupersetRemoveCommand;

/**
 * This is the item provider adapter for a {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ProjectDescriptionItemProvider
		extends ItemProviderAdapter
		implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public ProjectDescriptionItemProvider(AdapterFactory adapterFactory) {
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

			addRequiredDependencyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Required Dependency feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void addRequiredDependencyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_ProjectDescription_requiredDependency_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ProjectDescription_requiredDependency_feature", "_UI_ProjectDescription_type"),
				ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY,
				true,
				false,
				true,
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
			childrenFeatures.add(ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__DEPENDENCY);
			childrenFeatures.add(ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__EXTENSION);
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
	 * This returns ProjectDescription.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ProjectDescription"));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
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
	 *
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_ProjectDescription_type");
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

		switch (notification.getFeatureID(ProjectDescription.class)) {
		case ProjectRulesPackage.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case ProjectRulesPackage.PROJECT_DESCRIPTION__DEPENDENCY:
		case ProjectRulesPackage.PROJECT_DESCRIPTION__EXTENSION:
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

		newChildDescriptors.add(createChildParameter(ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__DEPENDENCY,
				ProjectRulesFactory.eINSTANCE.createDependency()));

		newChildDescriptors.add(createChildParameter(ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY,
				ProjectRulesFactory.eINSTANCE.createDependency()));

		newChildDescriptors.add(createChildParameter(ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__EXTENSION,
				ProjectRulesFactory.eINSTANCE.createExtension()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__DEPENDENCY ||
				childFeature == ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
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
		return Activator.INSTANCE;
	}

	/**
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createAddCommand(org.eclipse.emf.edit.domain.EditingDomain, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.util.Collection, int)
	 *      <!-- begin-user-doc -->
	 *      <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection, int index) {
		if (feature == ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY) {
			return new SubsetAddCommand(domain, owner, feature, new EStructuralFeature[] { ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__DEPENDENCY }, collection, index);
		}
		return super.createAddCommand(domain, owner, feature, collection, index);
	}

	/**
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createRemoveCommand(org.eclipse.emf.edit.domain.EditingDomain, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.util.Collection)
	 *      <!-- begin-user-doc -->
	 *      <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection) {
		if (feature == ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__DEPENDENCY) {
			return new SupersetRemoveCommand(domain, owner, feature, new EStructuralFeature[] { ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY }, collection);
		}
		return super.createRemoveCommand(domain, owner, feature, collection);
	}

	/**
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createReplaceCommand(org.eclipse.emf.edit.domain.EditingDomain, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object, java.util.Collection)
	 *      <!-- begin-user-doc -->
	 *      <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Command createReplaceCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, Collection<?> collection) {
		if (feature == ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY) {
			return new SubsetSupersetReplaceCommand(domain, owner, feature, new EStructuralFeature[] { ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__DEPENDENCY }, null, value, collection);
		}
		if (feature == ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__DEPENDENCY) {
			return new SubsetSupersetReplaceCommand(domain, owner, feature, null, new EStructuralFeature[] { ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY }, value, collection);
		}
		return super.createReplaceCommand(domain, owner, feature, value, collection);
	}

	/**
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createSetCommand(org.eclipse.emf.edit.domain.EditingDomain, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 *      <!-- begin-user-doc -->
	 *      <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value) {
		if (feature == ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY) {
			return new SubsetSupersetSetCommand(domain, owner, feature, new EStructuralFeature[] { ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__DEPENDENCY }, null, value);
		}
		if (feature == ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__DEPENDENCY) {
			return new SubsetSupersetSetCommand(domain, owner, feature, null, new EStructuralFeature[] { ProjectRulesPackage.Literals.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY }, value);
		}
		return super.createSetCommand(domain, owner, feature, value);
	}

}
