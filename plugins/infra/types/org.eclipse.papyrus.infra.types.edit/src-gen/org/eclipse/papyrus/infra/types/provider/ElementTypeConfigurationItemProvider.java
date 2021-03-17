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

import org.eclipse.emf.common.command.AbortExecutionException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.papyrus.infra.types.ElementTypeConfiguration;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsFactory;
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.papyrus.infra.types.ElementTypeConfiguration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ElementTypeConfigurationItemProvider extends ConfigurationElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementTypeConfigurationItemProvider(AdapterFactory adapterFactory) {
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
			addNamePropertyDescriptor(object);
			addHintPropertyDescriptor(object);
			addKindPropertyDescriptor(object);
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
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_NamedConfiguration_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_NamedConfiguration_name_feature", "_UI_NamedConfiguration_type"),
				 ElementTypesConfigurationsPackage.Literals.NAMED_CONFIGURATION__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Hint feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHintPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ElementTypeConfiguration_hint_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ElementTypeConfiguration_hint_feature", "_UI_ElementTypeConfiguration_type"),
				 ElementTypesConfigurationsPackage.Literals.ELEMENT_TYPE_CONFIGURATION__HINT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Kind feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addKindPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ElementTypeConfiguration_kind_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ElementTypeConfiguration_kind_feature", "_UI_ElementTypeConfiguration_type"),
				 ElementTypesConfigurationsPackage.Literals.ELEMENT_TYPE_CONFIGURATION__KIND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
			childrenFeatures.add(ElementTypesConfigurationsPackage.Literals.ELEMENT_TYPE_CONFIGURATION__ICON_ENTRY);
			childrenFeatures.add(ElementTypesConfigurationsPackage.Literals.ELEMENT_TYPE_CONFIGURATION__OWNED_ADVICE);
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
		String label = ((ElementTypeConfiguration)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ElementTypeConfiguration_type") :
			getString("_UI_ElementTypeConfiguration_type") + " " + label;
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

		switch (notification.getFeatureID(ElementTypeConfiguration.class)) {
			case ElementTypesConfigurationsPackage.ELEMENT_TYPE_CONFIGURATION__IDENTIFIER:
			case ElementTypesConfigurationsPackage.ELEMENT_TYPE_CONFIGURATION__NAME:
			case ElementTypesConfigurationsPackage.ELEMENT_TYPE_CONFIGURATION__HINT:
			case ElementTypesConfigurationsPackage.ELEMENT_TYPE_CONFIGURATION__KIND:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ElementTypesConfigurationsPackage.ELEMENT_TYPE_CONFIGURATION__ICON_ENTRY:
			case ElementTypesConfigurationsPackage.ELEMENT_TYPE_CONFIGURATION__OWNED_ADVICE:
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
				(ElementTypesConfigurationsPackage.Literals.ELEMENT_TYPE_CONFIGURATION__ICON_ENTRY,
				 ElementTypesConfigurationsFactory.eINSTANCE.createIconEntry()));

		newChildDescriptors.add
			(createChildParameter
				(ElementTypesConfigurationsPackage.Literals.ELEMENT_TYPE_CONFIGURATION__OWNED_ADVICE,
				 ElementTypesConfigurationsFactory.eINSTANCE.createAdviceBindingConfiguration()));

		newChildDescriptors.add
			(createChildParameter
				(ElementTypesConfigurationsPackage.Literals.ELEMENT_TYPE_CONFIGURATION__OWNED_ADVICE,
				 ElementTypesConfigurationsFactory.eINSTANCE.createExternallyRegisteredAdvice()));
	}

	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection, int index) {
		Command result = super.createAddCommand(domain, owner, feature, collection, index);

		if (feature == ElementTypesConfigurationsPackage.Literals.ELEMENT_TYPE_CONFIGURATION__OWNED_ADVICE) {
			// The opposite is a subset, so we need to take care of its superset feature(s)
			CompoundCommand compound = new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL, result.getLabel(), result.getDescription());

			int i = 0;
			for (Object next : collection) {
				IEditingDomainItemProvider provider = (IEditingDomainItemProvider) getRootAdapterFactory().adapt(next, IEditingDomainItemProvider.class);
				if (provider != null) {
					// Wrap the owner of the add to let the provider know that it's an inverse-add, which should not implicitly
					// remove from the existing container. That ensures that a drag-and-drop operation doesn't fail on attempt
					// to remove twice, and avoids an unnecessary removal step in the case of creating a new contained object
					Command inverse = provider.createCommand(next, domain, SetCommand.class, new CommandParameter(next, ElementTypesConfigurationsPackage.Literals.ABSTRACT_ADVICE_BINDING_CONFIGURATION__OWNING_TARGET,
							new InverseAddWrapper(owner)));
					if (inverse.canExecute()) {
						compound.append(inverse);

						if (index != CommandParameter.NO_INDEX) {
							// We cannot compute an executable move now because the object is not yet in the list.
							// So, defer the calculation to the future
							compound.append(new CommandWrapper(MoveCommand.create(domain, owner, feature, next, index + i)) {
								@Override
								protected boolean prepare() {
									return true;
								}

								@Override
								public void execute() {
									if (!super.prepare()) {
										throw new AbortExecutionException();
									}
									super.execute();
								}
							});
						}
					}
				}

				i++;
			}

			result = compound.unwrap();
		}

		return result;
	}

}
