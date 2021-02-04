/*******************************************************************************
 * Copyright (c) 2005, 2020 Borland Software Corporation, CEA LIST, ARTAL
 * 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 * 
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Borland - initial API and implementation
 *     Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 ******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.papyrus.gmf.mappings.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.papyrus.gmf.mappings.FeatureLabelMapping;
import org.eclipse.papyrus.gmf.mappings.GMFMapPackage;
import org.eclipse.papyrus.gmf.mappings.presentation.FilterUtil;

/**
 * This is the item provider adapter for a {@link org.eclipse.papyrus.gmf.mappings.FeatureLabelMapping} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FeatureLabelMappingItemProvider
	extends LabelMappingItemProvider
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
	 * @generated
	 */
	public FeatureLabelMappingItemProvider(AdapterFactory adapterFactory) {
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

			addFeaturesPropertyDescriptor(object);
			addEditableFeaturesPropertyDescriptor(object);
			addViewPatternPropertyDescriptor(object);
			addEditorPatternPropertyDescriptor(object);
			addEditPatternPropertyDescriptor(object);
			addViewMethodPropertyDescriptor(object);
			addEditMethodPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Features feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addFeaturesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FeatureLabelMapping_features_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FeatureLabelMapping_features_feature", "_UI_FeatureLabelMapping_type"),
				 GMFMapPackage.eINSTANCE.getFeatureLabelMapping_Features(),
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_DomainmetainformationPropertyCategory"),
				 null) {
						protected Collection<?> getComboBoxObjects(Object object) {
							@SuppressWarnings("unchecked")
							Collection<EStructuralFeature> original = (Collection<EStructuralFeature>) super.getComboBoxObjects(object);
							return FilterUtil.filterByContainerMetaclass(original, ((FeatureLabelMapping) object).getMapEntry());
						}
			});
	}

	/**
	 * This adds a property descriptor for the Editable Features feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditableFeaturesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FeatureLabelMapping_editableFeatures_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FeatureLabelMapping_editableFeatures_feature", "_UI_FeatureLabelMapping_type"),
				 GMFMapPackage.eINSTANCE.getFeatureLabelMapping_EditableFeatures(),
				 true,
				 false,
				 true,
				 null,
				 getString("_UI_DomainmetainformationPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the View Pattern feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addViewPatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FeatureLabelMapping_viewPattern_feature"),
				 getString("_UI_FeatureLabelMapping_viewPattern_description"),
				 GMFMapPackage.eINSTANCE.getFeatureLabelMapping_ViewPattern(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_VisualrepresentationPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Editor Pattern feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditorPatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FeatureLabelMapping_editorPattern_feature"),
				 getString("_UI_FeatureLabelMapping_editorPattern_description"),
				 GMFMapPackage.eINSTANCE.getFeatureLabelMapping_EditorPattern(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_VisualrepresentationPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the View Method feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addViewMethodPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FeatureLabelMapping_viewMethod_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FeatureLabelMapping_viewMethod_feature", "_UI_FeatureLabelMapping_type"),
				 GMFMapPackage.eINSTANCE.getFeatureLabelMapping_ViewMethod(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_VisualrepresentationPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Edit Pattern feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditPatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FeatureLabelMapping_editPattern_feature"),
				 getString("_UI_FeatureLabelMapping_editPattern_description"),
				 GMFMapPackage.eINSTANCE.getFeatureLabelMapping_EditPattern(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_VisualrepresentationPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Edit Method feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEditMethodPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FeatureLabelMapping_editMethod_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FeatureLabelMapping_editMethod_feature", "_UI_FeatureLabelMapping_type"),
				 GMFMapPackage.eINSTANCE.getFeatureLabelMapping_EditMethod(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_VisualrepresentationPropertyCategory"),
				 null));
	}

	/**
	 * This returns FeatureLabelMapping.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FeatureLabelMapping"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		StringBuilder result = new StringBuilder(getString("_UI_FeatureLabelMapping_type"));
		FeatureLabelMapping featureLabelMapping = (FeatureLabelMapping)object;
		EList<EAttribute> features = featureLabelMapping.getFeatures();
		result.append(':');
		result.append('[');
		if (!features.isEmpty()) {
			appendFeatureName(result, features.get(0));
		}
		for (int i = 1; i < features.size(); i++) {
			result.append(',');
			appendFeatureName(result, features.get(i));
		}
		result.append(']');
		appendReadOnly(result, featureLabelMapping);
		return result.toString();
	}

	private void appendReadOnly(StringBuilder labelText, FeatureLabelMapping feature) {
		if (feature.isReadOnly()) {
			labelText.append(" Read Only");
		}
	}
	
	private void appendFeatureName(StringBuilder labelText, EAttribute attr) {
		labelText.append(attr.getEContainingClass().getName());
		labelText.append("." + attr.getName());
		labelText.append(":" + attr.getEType().getName());
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

		switch (notification.getFeatureID(FeatureLabelMapping.class)) {
			case GMFMapPackage.FEATURE_LABEL_MAPPING__VIEW_PATTERN:
			case GMFMapPackage.FEATURE_LABEL_MAPPING__EDITOR_PATTERN:
			case GMFMapPackage.FEATURE_LABEL_MAPPING__EDIT_PATTERN:
			case GMFMapPackage.FEATURE_LABEL_MAPPING__VIEW_METHOD:
			case GMFMapPackage.FEATURE_LABEL_MAPPING__EDIT_METHOD:
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
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
