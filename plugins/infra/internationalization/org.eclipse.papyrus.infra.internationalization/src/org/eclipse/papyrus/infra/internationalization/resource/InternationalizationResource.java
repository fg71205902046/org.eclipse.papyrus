/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Nicolas FAUVERGUE (CEA LIST) nicolas.fauvergue@cea.fr - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.infra.internationalization.resource;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.emf.commands.AddToResourceCommand;
import org.eclipse.papyrus.infra.emf.gmf.command.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.infra.internationalization.Activator;
import org.eclipse.papyrus.infra.internationalization.InternationalizationEntry;
import org.eclipse.papyrus.infra.internationalization.InternationalizationFactory;
import org.eclipse.papyrus.infra.internationalization.InternationalizationLibrary;
import org.eclipse.papyrus.infra.internationalization.common.utils.LocaleNameResolver;
import org.eclipse.papyrus.infra.internationalization.utils.InternationalizationKeyResolver;
import org.eclipse.papyrus.infra.internationalization.utils.InternationalizationResourceOptionsConstants;
import org.eclipse.papyrus.infra.internationalization.utils.PropertiesFilesUtils;
import org.eclipse.papyrus.infra.internationalization.utils.ResourceBundleAndURI;

/**
 * The resource corresponding to the properties files.
 */
public class InternationalizationResource extends XMIResourceImpl {

	/**
	 * Constructor.
	 *
	 * @param uri
	 *            The uri of the resource.
	 */
	public InternationalizationResource(final URI uri) {
		super(uri);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#load(java.util.Map)
	 */
	@Override
	public void load(final Map<?, ?> options) throws IOException {

		// Load the key resolver save option if exist
		final InternationalizationKeyResolver keyResolver = loadKeyResolverOption(options, defaultLoadOptions);

		// Load the URI save option if exist
		final URI initialURI = loadURIOption(options, defaultLoadOptions);

		// Load the locale save option if exist
		final Object saveOptionLocale = options != null
				&& options.containsKey(InternationalizationResourceOptionsConstants.LOAD_OPTION_LOCALE)
						? options.get(InternationalizationResourceOptionsConstants.LOAD_OPTION_LOCALE)
						: defaultLoadOptions != null
								? defaultLoadOptions
										.get(InternationalizationResourceOptionsConstants.LOAD_OPTION_LOCALE)
								: null;
		Locale locale = null;
		if (null != saveOptionLocale) {
			if (saveOptionLocale instanceof Locale) {
				locale = (Locale) saveOptionLocale;
			} else if (saveOptionLocale instanceof String) {
				locale = LocaleNameResolver.getLocaleFromString((String) saveOptionLocale);
			}
		}

		// Load the resource from Properties files utils manager
		final ResourceBundleAndURI resourceBundleAndURI = PropertiesFilesUtils.getResourceBundle(
				initialURI.trimFileExtension().appendFileExtension(PropertiesFilesUtils.PROPERTIES_FILE_EXTENSION),
				locale);
		if (null != resourceBundleAndURI && null != resourceBundleAndURI.getResourceBundle()) {
			// Create the internationalization library to set to the
			// resource contents
			final InternationalizationLibrary library = InternationalizationFactory.eINSTANCE
					.createInternationalizationLibrary();
			final Enumeration<String> keys = resourceBundleAndURI.getResourceBundle().getKeys();

			// Loop on existing keys
			while (keys.hasMoreElements()) {
				// Create an entry for each key

				final String key = (String) keys.nextElement();

				final InternationalizationEntry entry = keyResolver.createInternationalizationEntryByKey(key,
						getResourceSet(), initialURI);
				entry.setValue(resourceBundleAndURI.getResourceBundle().getString(key));

				// Add the entry to the library
				library.getEntries().add(entry);
			}

			if (getResourceSet() instanceof ModelSet) {
				// Add the library to the resource
				final GMFtoEMFCommandWrapper command = new GMFtoEMFCommandWrapper(new AddToResourceCommand(
						((ModelSet) getResourceSet()).getTransactionalEditingDomain(), this, library));
				command.execute();
			} else {
				// Workaround for the non ModelSet resource set.
				final ResourceSet resourceSet = getResourceSet();
				resourceSet.getResources().remove(this);
				getContents().add(library);
				resourceSet.getResources().add(this);
			}

			setModified(false);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#save(java.util.Map)
	 */
	@Override
	public void save(final Map<?, ?> options) throws IOException {

		// Load the key resolver save option if exist
		final InternationalizationKeyResolver keyResolver = loadKeyResolverOption(options, defaultSaveOptions);

		// Load the deleted objects save option if exist
		final Object saveOptionDeletedObjects = options != null
				&& options.containsKey(InternationalizationResourceOptionsConstants.SAVE_OPTION_DELETED_OBJECTS)
						? options.get(InternationalizationResourceOptionsConstants.SAVE_OPTION_DELETED_OBJECTS)
						: defaultSaveOptions != null
								? defaultSaveOptions
										.get(InternationalizationResourceOptionsConstants.SAVE_OPTION_DELETED_OBJECTS)
								: null;
		Set<?> deletedObjects = null;
		if (null != saveOptionDeletedObjects && saveOptionDeletedObjects instanceof Set) {
			deletedObjects = (Set<?>) saveOptionDeletedObjects;
		}

		// Load the sort save option if exist
		final Object saveOptionSort = options != null
				&& options.containsKey(InternationalizationResourceOptionsConstants.SAVE_OPTION_SORT)
						? options.get(InternationalizationResourceOptionsConstants.SAVE_OPTION_SORT)
						: defaultSaveOptions != null
								? defaultSaveOptions.get(InternationalizationResourceOptionsConstants.SAVE_OPTION_SORT)
								: null;
		boolean sort = false;
		if (null != saveOptionSort && saveOptionSort instanceof Boolean) {
			sort = (Boolean) saveOptionSort;
		}

		// It is needed to manage the save of properties by a simple output
		// stream because the resource bundle cannot be modified
		final SortedProperties properties = new SortedProperties(sort);

		if (!getContents().isEmpty()) {
			for (final EObject content : getContents()) {
				if (content instanceof InternationalizationLibrary) {
					final InternationalizationLibrary library = (InternationalizationLibrary) content;

					// Add all the entries into properties (need this properties
					// to save
					// easier the properties file)
					for (final InternationalizationEntry entry : library.getEntries()) {
						if ((null == deletedObjects || !deletedObjects.contains(entry.getKey()))
								&& !entry.getValue().isEmpty()) {
							if (null != keyResolver) {
								properties.setProperty(keyResolver.getKey(entry), entry.getValue());
							} else {
								properties.setProperty(entry.getKey().toString(), entry.getValue());
							}
						}
					}

					// This allows to save the properties into the properties
					// file
					final URIConverter uriConverter = new ExtensibleURIConverterImpl();
					properties.store(uriConverter.createOutputStream(getURI()), null);
				}
			}
		}
		setModified(false);
	}

	/**
	 * This allows to load the key resolver option.
	 * 
	 * @param options
	 *            The initial options.
	 * @param defaultOptions
	 *            The default options.
	 * @return The key resolver.
	 */
	protected InternationalizationKeyResolver loadKeyResolverOption(final Map<?, ?> options,
			final Map<?, ?> defaultOptions) {
		// Load the key resolver save option if exist
		Object saveOptionKeyResolver = options != null
				&& options.containsKey(InternationalizationResourceOptionsConstants.LOAD_SAVE_OPTION_KEY_RESOLVER)
						? options.get(InternationalizationResourceOptionsConstants.LOAD_SAVE_OPTION_KEY_RESOLVER)
						: defaultOptions != null
								? defaultOptions
										.get(InternationalizationResourceOptionsConstants.LOAD_SAVE_OPTION_KEY_RESOLVER)
								: null;
		InternationalizationKeyResolver keyResolver = null;
		if (null != saveOptionKeyResolver && saveOptionKeyResolver instanceof InternationalizationKeyResolver) {
			keyResolver = (InternationalizationKeyResolver) saveOptionKeyResolver;
		} else {
			Activator.log.error("The 'keyResolver' option for the internationalization resource must be set.", null); //$NON-NLS-1$
		}

		return keyResolver;
	}

	/**
	 * This allows to load the URI option.
	 * 
	 * @param options
	 *            The initial options.
	 * @param defaultOptions
	 *            The default options.
	 * @return The URI option.
	 */
	protected URI loadURIOption(final Map<?, ?> options, final Map<?, ?> defaultOptions) {
		// Load the URI save option if exist
		Object saveOptionURI = options != null
				&& options.containsKey(InternationalizationResourceOptionsConstants.LOAD_OPTION_URI)
						? options.get(InternationalizationResourceOptionsConstants.LOAD_OPTION_URI)
						: defaultOptions != null
								? defaultOptions.get(InternationalizationResourceOptionsConstants.LOAD_OPTION_URI)
								: null;
		URI initialURI = null;
		if (null != saveOptionURI && saveOptionURI instanceof URI) {
			initialURI = (URI) saveOptionURI;
		} else {
			Activator.log.error("The initial 'URI' option for the internationalization resource must be set.", null); //$NON-NLS-1$
		}

		return initialURI;
	}

	/**
	 * Re-implement the properties because the sort is not enable for a
	 * HashTable
	 */
	private class SortedProperties extends Properties {

		/**
		 * Serial version.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Determinates if the sort must be applied.
		 */
		private boolean sort = false;

		/**
		 * Constructor.
		 *
		 * @param sort
		 *            Determinates if the sort must be applied.
		 */
		public SortedProperties(final boolean sort) {
			this.sort = sort;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see java.util.Hashtable#keys()
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Enumeration keys() {
			Enumeration keysEnum = super.keys();
			if (sort) {
				Vector<String> keyList = new Vector<String>();
				while (keysEnum.hasMoreElements()) {
					keyList.add((String) keysEnum.nextElement());
				}
				Collections.sort(keyList);
				keysEnum = keyList.elements();
			}
			return keysEnum;
		}

	}

}
