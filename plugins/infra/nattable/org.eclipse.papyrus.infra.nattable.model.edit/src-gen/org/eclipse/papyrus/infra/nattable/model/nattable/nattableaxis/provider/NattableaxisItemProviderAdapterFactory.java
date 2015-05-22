/**
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 */
package org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.provider;

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
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.util.NattableaxisAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class NattableaxisItemProviderAdapterFactory extends NattableaxisAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
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
	public NattableaxisItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.IdTreeItemAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdTreeItemAxisItemProvider idTreeItemAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.IdTreeItemAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIdTreeItemAxisAdapter() {
		if (idTreeItemAxisItemProvider == null) {
			idTreeItemAxisItemProvider = new IdTreeItemAxisItemProvider(this);
		}

		return idTreeItemAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.EObjectAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EObjectAxisItemProvider eObjectAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.EObjectAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEObjectAxisAdapter() {
		if (eObjectAxisItemProvider == null) {
			eObjectAxisItemProvider = new EObjectAxisItemProvider(this);
		}

		return eObjectAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.EObjectTreeItemAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EObjectTreeItemAxisItemProvider eObjectTreeItemAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.EObjectTreeItemAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEObjectTreeItemAxisAdapter() {
		if (eObjectTreeItemAxisItemProvider == null) {
			eObjectTreeItemAxisItemProvider = new EObjectTreeItemAxisItemProvider(this);
		}

		return eObjectTreeItemAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.FeatureTreeItemAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureTreeItemAxisItemProvider featureTreeItemAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.FeatureTreeItemAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFeatureTreeItemAxisAdapter() {
		if (featureTreeItemAxisItemProvider == null) {
			featureTreeItemAxisItemProvider = new FeatureTreeItemAxisItemProvider(this);
		}

		return featureTreeItemAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.ObjectTreeItemAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectTreeItemAxisItemProvider objectTreeItemAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.ObjectTreeItemAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createObjectTreeItemAxisAdapter() {
		if (objectTreeItemAxisItemProvider == null) {
			objectTreeItemAxisItemProvider = new ObjectTreeItemAxisItemProvider(this);
		}

		return objectTreeItemAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.FeatureIdAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureIdAxisItemProvider featureIdAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.FeatureIdAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFeatureIdAxisAdapter() {
		if (featureIdAxisItemProvider == null) {
			featureIdAxisItemProvider = new FeatureIdAxisItemProvider(this);
		}

		return featureIdAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.FeatureIdTreeItemAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureIdTreeItemAxisItemProvider featureIdTreeItemAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.FeatureIdTreeItemAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFeatureIdTreeItemAxisAdapter() {
		if (featureIdTreeItemAxisItemProvider == null) {
			featureIdTreeItemAxisItemProvider = new FeatureIdTreeItemAxisItemProvider(this);
		}

		return featureIdTreeItemAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.EStructuralFeatureAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeatureAxisItemProvider eStructuralFeatureAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.EStructuralFeatureAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEStructuralFeatureAxisAdapter() {
		if (eStructuralFeatureAxisItemProvider == null) {
			eStructuralFeatureAxisItemProvider = new EStructuralFeatureAxisItemProvider(this);
		}

		return eStructuralFeatureAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.EStructuralFeatureTreeItemAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeatureTreeItemAxisItemProvider eStructuralFeatureTreeItemAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.EStructuralFeatureTreeItemAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEStructuralFeatureTreeItemAxisAdapter() {
		if (eStructuralFeatureTreeItemAxisItemProvider == null) {
			eStructuralFeatureTreeItemAxisItemProvider = new EStructuralFeatureTreeItemAxisItemProvider(this);
		}

		return eStructuralFeatureTreeItemAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.ObjectIdAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectIdAxisItemProvider objectIdAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.ObjectIdAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createObjectIdAxisAdapter() {
		if (objectIdAxisItemProvider == null) {
			objectIdAxisItemProvider = new ObjectIdAxisItemProvider(this);
		}

		return objectIdAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.ObjectIdTreeItemAxis} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectIdTreeItemAxisItemProvider objectIdTreeItemAxisItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.ObjectIdTreeItemAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createObjectIdTreeItemAxisAdapter() {
		if (objectIdTreeItemAxisItemProvider == null) {
			objectIdTreeItemAxisItemProvider = new ObjectIdTreeItemAxisItemProvider(this);
		}

		return objectIdTreeItemAxisItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.AxisGroup} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AxisGroupItemProvider axisGroupItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.AxisGroup}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAxisGroupAdapter() {
		if (axisGroupItemProvider == null) {
			axisGroupItemProvider = new AxisGroupItemProvider(this);
		}

		return axisGroupItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public void dispose() {
		if (idTreeItemAxisItemProvider != null) idTreeItemAxisItemProvider.dispose();
		if (eObjectAxisItemProvider != null) eObjectAxisItemProvider.dispose();
		if (eObjectTreeItemAxisItemProvider != null) eObjectTreeItemAxisItemProvider.dispose();
		if (featureTreeItemAxisItemProvider != null) featureTreeItemAxisItemProvider.dispose();
		if (objectTreeItemAxisItemProvider != null) objectTreeItemAxisItemProvider.dispose();
		if (featureIdAxisItemProvider != null) featureIdAxisItemProvider.dispose();
		if (featureIdTreeItemAxisItemProvider != null) featureIdTreeItemAxisItemProvider.dispose();
		if (eStructuralFeatureAxisItemProvider != null) eStructuralFeatureAxisItemProvider.dispose();
		if (eStructuralFeatureTreeItemAxisItemProvider != null) eStructuralFeatureTreeItemAxisItemProvider.dispose();
		if (objectIdAxisItemProvider != null) objectIdAxisItemProvider.dispose();
		if (objectIdTreeItemAxisItemProvider != null) objectIdTreeItemAxisItemProvider.dispose();
		if (axisGroupItemProvider != null) axisGroupItemProvider.dispose();
	}

}
