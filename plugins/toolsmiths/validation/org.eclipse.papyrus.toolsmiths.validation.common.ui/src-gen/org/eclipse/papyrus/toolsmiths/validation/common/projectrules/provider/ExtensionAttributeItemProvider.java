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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;

import com.google.common.base.Strings;

/**
 * This is the item provider adapter for a {@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ExtensionAttributeItemProvider
		extends ExtensionModelNodeItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public ExtensionAttributeItemProvider(AdapterFactory adapterFactory) {
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

			addValuePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Value feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	protected void addValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_ExtensionAttribute_value_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ExtensionAttribute_value_feature", "_UI_ExtensionAttribute_type"),
				ProjectRulesPackage.Literals.EXTENSION_ATTRIBUTE__VALUE,
				true,
				false,
				false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				null,
				null) {

			@Override
			public Object getFeature(Object object) {
				// We surface the text of the opaque value
				return ProjectRulesPackage.Literals.OPAQUE_VALUE__TEXT;
			}

			@Override
			protected Collection<?> getComboBoxObjects(Object object) {
				return null;
			}

			@Override
			protected Object getValue(EObject object, EStructuralFeature feature) {
				ExtensionAttribute attribute = (ExtensionAttribute) object;
				return attribute.getValue() == null ? "" : attribute.getValue().stringValue(); //$NON-NLS-1$
			}

			@Override
			public void setPropertyValue(Object object, Object value) {
				ExtensionAttribute attribute = (ExtensionAttribute) object;
				EditingDomain editingDomain = getEditingDomain(attribute);

				Value existing = attribute.getValue();
				OpaqueValue opaqueValue = existing instanceof OpaqueValue ? (OpaqueValue) existing : null;

				Command command;

				if (opaqueValue == null) {
					// We must have an opaque value. So, create one to replace whatever we had already
					opaqueValue = ProjectRulesFactory.eINSTANCE.createOpaqueValue();
					opaqueValue.setText((String) value);
					command = SetCommand.create(editingDomain, attribute, ProjectRulesPackage.Literals.EXTENSION_ATTRIBUTE__VALUE, opaqueValue);
				} else {
					command = SetCommand.create(editingDomain, opaqueValue, ProjectRulesPackage.Literals.OPAQUE_VALUE__TEXT, value);
				}

				editingDomain.getCommandStack().execute(command);
			}
		});
	}

	/**
	 * This returns ExtensionAttribute.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ExtensionAttribute"));
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
	public String getTextGen(Object object) {
		String label = ((ExtensionAttribute) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_ExtensionAttribute_type") : getString("_UI_ExtensionAttribute_type") + " " + label;
	}

	@Override
	public String getText(Object object) {
		String result = getTextGen(object);
		ExtensionAttribute attribute = (ExtensionAttribute) object;

		String value = attribute.getValue() == null ? null : attribute.getValue().stringValue();
		result = Strings.isNullOrEmpty(value)
				? result
				: getString("_UI_ExtensionAttribute_label", new Object[] { result, value });
		return result;
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

		switch (notification.getFeatureID(ExtensionAttribute.class)) {
		case ProjectRulesPackage.EXTENSION_ATTRIBUTE__VALUE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

}
