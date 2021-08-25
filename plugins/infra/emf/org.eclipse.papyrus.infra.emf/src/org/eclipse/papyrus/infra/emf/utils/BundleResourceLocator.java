/*****************************************************************************
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
 *****************************************************************************/

package org.eclipse.papyrus.infra.emf.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.osgi.service.localization.BundleLocalization;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * A simple {@link ResourceLocator} that gets strings from an OSGi bundle.
 */
public class BundleResourceLocator implements ResourceLocator {

	private final Bundle bundle;
	private final URL baseURL;

	private ResourceBundle strings;

	public BundleResourceLocator(Bundle bundle) {
		super();

		this.bundle = bundle;

		try {
			this.baseURL = new URL(String.format("platform:/plugin/%s/", bundle.getSymbolicName())); //$NON-NLS-1$
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("bundle"); //$NON-NLS-1$
		}
	}

	@Override
	public URL getBaseURL() {
		return baseURL;
	}

	@Override
	public Object getImage(String key) {
		return null;
	}

	@Override
	public String getString(String key) {
		if (strings == null) {
			initStrings();
		}

		return strings.getString(key);
	}

	private void initStrings() {
		BundleContext ctx = bundle.getBundleContext();
		ServiceReference<BundleLocalization> ref = ctx.getServiceReference(BundleLocalization.class);

		if (ref != null) {
			BundleLocalization l10n = ctx.getService(ref);
			try {
				strings = l10n.getLocalization(bundle, null);
			} finally {
				ctx.ungetService(ref);
			}
		}

		if (strings == null) {
			strings = new ListResourceBundle() {

				@Override
				protected Object[][] getContents() {
					return new Object[0][0];
				}
			};
		}
	}

	@Override
	public String getString(String key, boolean translate) {
		// We don't support the untranslated case
		return getString(key);
	}

	@Override
	public String getString(String key, Object[] substitutions) {
		return NLS.bind(getString(key), substitutions);
	}

	@Override
	public String getString(String key, Object[] substitutions, boolean translate) {
		return NLS.bind(getString(key, translate), substitutions);
	}

}
