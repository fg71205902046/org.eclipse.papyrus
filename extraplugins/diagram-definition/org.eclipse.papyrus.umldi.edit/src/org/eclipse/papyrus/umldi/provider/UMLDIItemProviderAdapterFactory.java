/**
 * Copyright (c) 2014 CEA LIST.
 *  
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *  CEA LIST - Initial API and implementation
 */
package org.eclipse.papyrus.umldi.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.papyrus.umldi.util.UMLDIAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UMLDIItemProviderAdapterFactory extends UMLDIAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UMLDIItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
		supportedTypes.add(IItemColorProvider.class);
		supportedTypes.add(IItemFontProvider.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.umldi.UmlDiagram} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UmlDiagramItemProvider umlDiagramItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.umldi.UmlDiagram}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUmlDiagramAdapter() {
		if (umlDiagramItemProvider == null) {
			umlDiagramItemProvider = new UmlDiagramItemProvider(this);
		}

		return umlDiagramItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.umldi.UmlShape} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UmlShapeItemProvider umlShapeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.umldi.UmlShape}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUmlShapeAdapter() {
		if (umlShapeItemProvider == null) {
			umlShapeItemProvider = new UmlShapeItemProvider(this);
		}

		return umlShapeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.umldi.UmlStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UmlStyleItemProvider umlStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.umldi.UmlStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUmlStyleAdapter() {
		if (umlStyleItemProvider == null) {
			umlStyleItemProvider = new UmlStyleItemProvider(this);
		}

		return umlStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.umldi.UmlEdge} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UmlEdgeItemProvider umlEdgeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.umldi.UmlEdge}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUmlEdgeAdapter() {
		if (umlEdgeItemProvider == null) {
			umlEdgeItemProvider = new UmlEdgeItemProvider(this);
		}

		return umlEdgeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.umldi.UmlCompartment} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UmlCompartmentItemProvider umlCompartmentItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.umldi.UmlCompartment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUmlCompartmentAdapter() {
		if (umlCompartmentItemProvider == null) {
			umlCompartmentItemProvider = new UmlCompartmentItemProvider(this);
		}

		return umlCompartmentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.umldi.UmlLabel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UmlLabelItemProvider umlLabelItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.umldi.UmlLabel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUmlLabelAdapter() {
		if (umlLabelItemProvider == null) {
			umlLabelItemProvider = new UmlLabelItemProvider(this);
		}

		return umlLabelItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (umlDiagramItemProvider != null) umlDiagramItemProvider.dispose();
		if (umlShapeItemProvider != null) umlShapeItemProvider.dispose();
		if (umlStyleItemProvider != null) umlStyleItemProvider.dispose();
		if (umlEdgeItemProvider != null) umlEdgeItemProvider.dispose();
		if (umlCompartmentItemProvider != null) umlCompartmentItemProvider.dispose();
		if (umlLabelItemProvider != null) umlLabelItemProvider.dispose();
	}

}
