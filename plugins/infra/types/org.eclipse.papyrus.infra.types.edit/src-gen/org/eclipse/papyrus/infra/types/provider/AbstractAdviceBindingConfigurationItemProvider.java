/**
 * Copyright (c) 2014, 2020 CEA LIST, Christian W. Damus, and others.
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
 *  Christian W. Damus - bug 568782
 */
package org.eclipse.papyrus.infra.types.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypeSetConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsFactory;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage;
import org.eclipse.uml2.common.edit.command.SubsetSupersetSetCommand;

/**
 * This is the item provider adapter for a {@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractAdviceBindingConfigurationItemProvider extends AdviceConfigurationItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractAdviceBindingConfigurationItemProvider(AdapterFactory adapterFactory) {
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

			addIdentifierPropertyDescriptor(object);
			addTargetPropertyDescriptor(object);
			addInheritancePropertyDescriptor(object);
			addApplyToAllTypesPropertyDescriptor(object);
			addElementTypeSetPropertyDescriptor(object);
			addOwningTargetPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Identifier feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdentifierPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IdentifiedConfiguration_identifier_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IdentifiedConfiguration_identifier_feature", "_UI_IdentifiedConfiguration_type"),
				 ElementTypesConfigurationsPackage.Literals.IDENTIFIED_CONFIGURATION__IDENTIFIER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Target feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractAdviceBindingConfiguration_target_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractAdviceBindingConfiguration_target_feature", "_UI_AbstractAdviceBindingConfiguration_type"),
				 ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Inheritance feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInheritancePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractAdviceBindingConfiguration_inheritance_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractAdviceBindingConfiguration_inheritance_feature", "_UI_AbstractAdviceBindingConfiguration_type"),
				 ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__INHERITANCE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Apply To All Types feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addApplyToAllTypesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractAdviceBindingConfiguration_applyToAllTypes_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractAdviceBindingConfiguration_applyToAllTypes_feature", "_UI_AbstractAdviceBindingConfiguration_type"),
				 ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__APPLY_TO_ALL_TYPES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Element Type Set feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addElementTypeSetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractAdviceBindingConfiguration_elementTypeSet_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractAdviceBindingConfiguration_elementTypeSet_feature", "_UI_AbstractAdviceBindingConfiguration_type"),
				 ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__ELEMENT_TYPE_SET,
				 false,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Owning Target feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOwningTargetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractAdviceBindingConfiguration_owningTarget_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractAdviceBindingConfiguration_owningTarget_feature", "_UI_AbstractAdviceBindingConfiguration_type"),
				 ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__OWNING_TARGET,
				 true,
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
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION);
			childrenFeatures.add(ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION);
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AbstractAdviceBindingConfiguration)object).getIdentifier();
		return label == null || label.length() == 0 ?
			getString("_UI_AbstractAdviceBindingConfiguration_type") :
			getString("_UI_AbstractAdviceBindingConfiguration_type") + " " + label;
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

		switch (notification.getFeatureID(AbstractAdviceBindingConfiguration.class)) {
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__IDENTIFIER:
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__INHERITANCE:
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__APPLY_TO_ALL_TYPES:
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__OWNING_TARGET:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION:
			case ElementTypesConfigurationsPackage.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION:
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
				(ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__CONTAINER_CONFIGURATION,
				 ElementTypesConfigurationsFactory.eINSTANCE.createContainerConfiguration()));

		newChildDescriptors.add
			(createChildParameter
				(ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__MATCHER_CONFIGURATION,
				 ElementTypesConfigurationsFactory.eINSTANCE.createMatcherConfiguration()));
	}

	/**
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createSetCommand(org.eclipse.emf.edit.domain.EditingDomain, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Command createSetCommandGen(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value) {
		if (feature == ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__OWNING_TARGET) {
			return new SubsetSupersetSetCommand(domain, owner, feature, new EStructuralFeature[] {ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET}, null, value);
		}
		if (feature == ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET) {
			return new SubsetSupersetSetCommand(domain, owner, feature, null, new EStructuralFeature[] {ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__OWNING_TARGET}, value);
		}
		return super.createSetCommand(domain, owner, feature, value);
	}

	@Override
	protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value) {
		Command result = createSetCommandGen(domain, owner, feature, value);

		if (result != null && feature == ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__TARGET) {
			AbstractAdviceBindingConfiguration advice = (AbstractAdviceBindingConfiguration) owner;
			if (value != advice.getOwningTarget()) {
				// This command will unset the container. Follow up with placement of the advice binding configuration into the element types set
				ElementTypeSetConfiguration typeSet = advice.getElementTypeSet();
				if (typeSet != null) {
					Command add = AddCommand.create(domain, typeSet, ElementTypesConfigurationsPackage.Literals.ELEMENT_TYPE_SET_CONFIGURATION__ADVICE_BINDINGS_CONFIGURATIONS, advice);
					result = result.chain(add);
				}
			} // Otherwise, this command will not change the target, so don't worry about it
		} else if (result != null && feature == ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__OWNING_TARGET) {
			AbstractAdviceBindingConfiguration advice = (AbstractAdviceBindingConfiguration) owner;
			ElementTypeSetConfiguration typeSet = advice.getElementTypeSet();
			if (typeSet != null && value != advice.getOwningTarget()) {
				// User is changing the container. Remove from current container, first so that it's undoable
				result = RemoveCommand.create(domain, owner).chain(result);
				
				// If clearing the container, re-home the advice in the set. Note that if the user wants to
				// delete the advice from the model, then that is done by a RemoveCommand, not by this means
				if (value == null) {
					Command add = AddCommand.create(domain, typeSet, ElementTypesConfigurationsPackage.Literals.ELEMENT_TYPE_SET_CONFIGURATION__ADVICE_BINDINGS_CONFIGURATIONS, advice);
					result = result.chain(add);
				}
			}
		}

		return result;
	}
	
}
