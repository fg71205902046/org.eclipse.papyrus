/*****************************************************************************
 * Copyright (c) 2020 Christian W. Damus, CEA LIST, and others.
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.container.ModuleContainer;
import org.eclipse.papyrus.infra.emf.Activator;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.namespace.IdentityNamespace;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.FrameworkWiring;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

/**
 * Optional support for a PDE Target platform, to discover the plug-ins against
 * which workspace projects (if any) are overlaid.
 */
abstract class PlatformHelper {

	private static final String ECORE_URI_MAPPING_EXTENSION_POINT = "org.eclipse.emf.ecore.uri_mapping"; //$NON-NLS-1$
	private static final String E_EXTENSION = "extension"; //$NON-NLS-1$
	private static final String A_POINT = "point"; //$NON-NLS-1$
	private static final String E_MAPPING = "mapping"; //$NON-NLS-1$
	private static final String A_SOURCE = "source"; //$NON-NLS-1$
	private static final String A_TARGET = "target"; //$NON-NLS-1$

	static final PlatformHelper INSTANCE;

	static {
		PlatformHelper instance;

		try {
			instance = new PDEHelper();
		} catch (Exception e) {
			// PDE is not available
			instance = new InstallHelper();
		}

		INSTANCE = instance;
	}


	/**
	 * Get the IDs of all bundles available in the target platform, whether that be the
	 * PDE Target Platform (in case PDE is installed) or else the host installation.
	 *
	 * @return the platform bundle IDs
	 */
	abstract Collection<String> getPlatformBundleIDs();

	/**
	 * Return the {@code org.eclipse.emf.ecore.uri_mapping} extension declarations in the given {@code project}.
	 *
	 * @param project
	 *            a project in the workspace
	 * @return its declared URI mappings
	 */
	abstract Map<String, String> getLocalUriMappings(IProject project);

	//
	// Nested types
	//

	/**
	 * The install helper instance gets all resolved (ready/available) bundles in the current installation.
	 */
	private static final class InstallHelper extends PlatformHelper {

		private static final int AVAILABLE = Bundle.ACTIVE | Bundle.RESOLVED | Bundle.STARTING;

		@Override
		Collection<String> getPlatformBundleIDs() {
			Collection<String> result = new HashSet<>();

			FrameworkWiring wiring = Platform.getBundle(Constants.SYSTEM_BUNDLE_SYMBOLICNAME).adapt(FrameworkWiring.class);
			Collection<BundleCapability> bundleIdentities = wiring.findProviders(ModuleContainer
					.createRequirement(IdentityNamespace.IDENTITY_NAMESPACE, Collections.emptyMap(), Collections.emptyMap()));
			for (BundleCapability next : bundleIdentities) {
				Bundle bundle = next.getRevision().getBundle();
				if ((bundle.getState() & AVAILABLE) != 0) {
					result.add(bundle.getSymbolicName());
				}
			}

			return result;
		}

		@Override
		Map<String, String> getLocalUriMappings(IProject project) {
			IFile pluginXML = project.getFile("plugin.xml"); //$NON-NLS-1$
			if (!pluginXML.isAccessible()) {
				return Map.of();
			}

			Map<String, String> result = new HashMap<>();

			try (InputStream input = pluginXML.getContents()) {
				SAXParserFactory.newInstance().newSAXParser().parse(input, new DefaultHandler2() {
					private boolean inMappings;

					@Override
					public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
						// plugin.xml does not use namespaces, so the parser provides empty local names and the local names in qName
						if (E_EXTENSION.equals(qName) && ECORE_URI_MAPPING_EXTENSION_POINT.equals(attributes.getValue(A_POINT))) {
							inMappings = true;
						} else if (inMappings && E_MAPPING.equals(qName)) {
							String source = attributes.getValue(A_SOURCE);
							String target = attributes.getValue(A_TARGET);
							if (source != null && !source.isBlank() && target != null && !target.isBlank()) {
								result.put(source, target);
							}
						}
					}

					@Override
					public void endElement(String uri, String localName, String qName) throws SAXException {
						if (E_EXTENSION.equals(qName)) {
							inMappings = false;
						}
					}
				});
			} catch (SAXException | IOException | ParserConfigurationException | CoreException e) {
				Activator.log.error("Failed to parse plugin.xml in project" + project.getName(), e); //$NON-NLS-1$
			}

			return result;
		}

	}

	/**
	 * The PDE helper instance gets all active bundles in the current PDE target platform.
	 */
	private static final class PDEHelper extends PlatformHelper {

		@Override
		Collection<String> getPlatformBundleIDs() {
			IPluginModelBase[] pluginModels = PluginRegistry.getActiveModels();
			Collection<String> result = new HashSet<>();

			for (IPluginModelBase next : pluginModels) {
				if (next.getBundleDescription() != null) {
					result.add(next.getBundleDescription().getSymbolicName());
				}
			}

			return result;
		}

		@Override
		Map<String, String> getLocalUriMappings(IProject project) {
			HashMap<String, String> localMappings = new HashMap<>();
			final IPluginModelBase model = PluginRegistry.findModel(project.getName());
			if (model == null) {
				// No mappings if no plugin model
				return localMappings;
			}

			for (IPluginExtension extension : model.getExtensions().getExtensions()) {
				if (!Objects.equals(extension.getPoint(), ECORE_URI_MAPPING_EXTENSION_POINT)) {
					continue;
				}
				Arrays.stream(extension.getChildren())
						.filter(IPluginElement.class::isInstance).map(IPluginElement.class::cast)
						.filter(element -> Objects.equals(E_MAPPING, element.getName()))
						.forEach(element -> localMappings.put(element.getAttribute(A_SOURCE).getValue(), element.getAttribute(A_TARGET).getValue()));
			}

			return localMappings;
		}

	}

}
