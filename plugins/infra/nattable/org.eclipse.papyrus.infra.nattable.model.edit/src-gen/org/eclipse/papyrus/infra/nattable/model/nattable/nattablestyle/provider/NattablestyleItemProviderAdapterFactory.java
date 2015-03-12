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
package org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.provider;

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
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.util.NattablestyleAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class NattablestyleItemProviderAdapterFactory extends NattablestyleAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NattablestyleItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.NamedStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected NamedStyleItemProvider namedStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.NamedStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createNamedStyleAdapter() {
		if (namedStyleItemProvider == null) {
			namedStyleItemProvider = new NamedStyleItemProvider(this);
		}

		return namedStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.FontStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected FontStyleItemProvider fontStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.FontStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createFontStyleAdapter() {
		if (fontStyleItemProvider == null) {
			fontStyleItemProvider = new FontStyleItemProvider(this);
		}

		return fontStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.CellTextStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CellTextStyleItemProvider cellTextStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.CellTextStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createCellTextStyleAdapter() {
		if (cellTextStyleItemProvider == null) {
			cellTextStyleItemProvider = new CellTextStyleItemProvider(this);
		}

		return cellTextStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.IntValueStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IntValueStyleItemProvider intValueStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.IntValueStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createIntValueStyleAdapter() {
		if (intValueStyleItemProvider == null) {
			intValueStyleItemProvider = new IntValueStyleItemProvider(this);
		}

		return intValueStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.IntListValueStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IntListValueStyleItemProvider intListValueStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.IntListValueStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createIntListValueStyleAdapter() {
		if (intListValueStyleItemProvider == null) {
			intListValueStyleItemProvider = new IntListValueStyleItemProvider(this);
		}

		return intListValueStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.BooleanValueStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected BooleanValueStyleItemProvider booleanValueStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.BooleanValueStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createBooleanValueStyleAdapter() {
		if (booleanValueStyleItemProvider == null) {
			booleanValueStyleItemProvider = new BooleanValueStyleItemProvider(this);
		}

		return booleanValueStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.BooleanListValueStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected BooleanListValueStyleItemProvider booleanListValueStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.BooleanListValueStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createBooleanListValueStyleAdapter() {
		if (booleanListValueStyleItemProvider == null) {
			booleanListValueStyleItemProvider = new BooleanListValueStyleItemProvider(this);
		}

		return booleanListValueStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.DoubleValueStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DoubleValueStyleItemProvider doubleValueStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.DoubleValueStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createDoubleValueStyleAdapter() {
		if (doubleValueStyleItemProvider == null) {
			doubleValueStyleItemProvider = new DoubleValueStyleItemProvider(this);
		}

		return doubleValueStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.DoubleListValueStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DoubleListValueStyleItemProvider doubleListValueStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.DoubleListValueStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createDoubleListValueStyleAdapter() {
		if (doubleListValueStyleItemProvider == null) {
			doubleListValueStyleItemProvider = new DoubleListValueStyleItemProvider(this);
		}

		return doubleListValueStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.StringValueStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StringValueStyleItemProvider stringValueStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.StringValueStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createStringValueStyleAdapter() {
		if (stringValueStyleItemProvider == null) {
			stringValueStyleItemProvider = new StringValueStyleItemProvider(this);
		}

		return stringValueStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.StringListValueStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StringListValueStyleItemProvider stringListValueStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.StringListValueStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createStringListValueStyleAdapter() {
		if (stringListValueStyleItemProvider == null) {
			stringListValueStyleItemProvider = new StringListValueStyleItemProvider(this);
		}

		return stringListValueStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.TableDisplayStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TableDisplayStyleItemProvider tableDisplayStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.TableDisplayStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createTableDisplayStyleAdapter() {
		if (tableDisplayStyleItemProvider == null) {
			tableDisplayStyleItemProvider = new TableDisplayStyleItemProvider(this);
		}

		return tableDisplayStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.EObjectValueStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EObjectValueStyleItemProvider eObjectValueStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.EObjectValueStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createEObjectValueStyleAdapter() {
		if (eObjectValueStyleItemProvider == null) {
			eObjectValueStyleItemProvider = new EObjectValueStyleItemProvider(this);
		}

		return eObjectValueStyleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.EObjectListValueStyle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EObjectListValueStyleItemProvider eObjectListValueStyleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattablestyle.EObjectListValueStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Adapter createEObjectListValueStyleAdapter() {
		if (eObjectListValueStyleItemProvider == null) {
			eObjectListValueStyleItemProvider = new EObjectListValueStyleItemProvider(this);
		}

		return eObjectListValueStyleItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
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
	 * 
	 * @generated
	 */
	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
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
	 * 
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>) type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * @generated
	 */
	@Override
	public void dispose() {
		if (namedStyleItemProvider != null)
			namedStyleItemProvider.dispose();
		if (fontStyleItemProvider != null)
			fontStyleItemProvider.dispose();
		if (cellTextStyleItemProvider != null)
			cellTextStyleItemProvider.dispose();
		if (intValueStyleItemProvider != null)
			intValueStyleItemProvider.dispose();
		if (intListValueStyleItemProvider != null)
			intListValueStyleItemProvider.dispose();
		if (booleanValueStyleItemProvider != null)
			booleanValueStyleItemProvider.dispose();
		if (booleanListValueStyleItemProvider != null)
			booleanListValueStyleItemProvider.dispose();
		if (doubleValueStyleItemProvider != null)
			doubleValueStyleItemProvider.dispose();
		if (doubleListValueStyleItemProvider != null)
			doubleListValueStyleItemProvider.dispose();
		if (stringValueStyleItemProvider != null)
			stringValueStyleItemProvider.dispose();
		if (stringListValueStyleItemProvider != null)
			stringListValueStyleItemProvider.dispose();
		if (tableDisplayStyleItemProvider != null)
			tableDisplayStyleItemProvider.dispose();
		if (eObjectValueStyleItemProvider != null)
			eObjectValueStyleItemProvider.dispose();
		if (eObjectListValueStyleItemProvider != null)
			eObjectListValueStyleItemProvider.dispose();
	}
}
