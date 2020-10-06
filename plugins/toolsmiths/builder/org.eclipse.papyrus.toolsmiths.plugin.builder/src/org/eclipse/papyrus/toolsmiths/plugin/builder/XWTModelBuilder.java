/*****************************************************************************
 * Copyright (c) 2020 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;

/**
 *
 */
public class XWTModelBuilder extends GenericEMFModelBuilder {

	static final String XWT_EXTENSION = "xwt";//$NON-NLS-1$

	static final String ENVIRONMENT_XMI_EXTENSION = "xmi";

	static final String CTX_EXTENSION = "ctx";

	private static final String XWT_URI = "http://www.eclipse.org/xwt";

	private static final String XWT_PRESENTATION_URI = "http://www.eclipse.org/xwt/presentation";

	/**
	 * @see org.eclipse.papyrus.toolsmiths.plugin.builder.GenericEMFModelBuilder#getModelBundleDependenciesFromXML(org.eclipse.emf.ecore.resource.Resource)
	 *
	 * @param resource
	 * @return
	 */
	@Override
	protected Set<String> getModelBundleDependenciesFromXML(Resource resource) {
		final Set<String> importedMetamodels_NS_URI = getXMLImportedMetamodelNsURI(resource);
		final Set<String> realURI = new HashSet<>();
		for (final String current : importedMetamodels_NS_URI) {
			String bundleFound = null;
			if (isIgnoredNS_URI(current)) {
				// we ignore this one
				continue;
			}
			if (current.startsWith("clr-namespace:")) {
				String substring = current.replaceAll("clr-namespace:", "");

				// assuming the path contains the bundle name
				IPluginModelBase pluginModelBase = PluginRegistry.findModel(substring);

				while (pluginModelBase == null && substring.contains(".")) {
					int lastIndex = substring.lastIndexOf('.');
					substring = substring.substring(0, lastIndex);
					pluginModelBase = PluginRegistry.findModel(substring);
				}

				if (pluginModelBase != null) {
					bundleFound = pluginModelBase.getBundleDescription().getName();
				}
			} else if (XWT_URI.equals(current) || XWT_PRESENTATION_URI.equals(current)) {
				bundleFound = "org.eclipse.xwt";
			} else {
				bundleFound = getBundleNameFromNS_URI(current);
			}
			if (bundleFound == null) {
				Activator.log.warn(NLS.bind("Papyrus Builder: We are not able to find the bundle providing the URI", current));
			}
		}

		return realURI;
	}

	/**
	 *
	 * @param resource
	 *            a resource
	 * @param builtProject
	 *            the current build project
	 * @return
	 *         the collection of dependencies as String loaded for this resource
	 */
	@Override
	protected Set<String> getDependencies(final Resource resource, final IProject builtProject) {
		EcoreUtil.resolveAll(resource);
		final Set<String> dependencies = new TreeSet<>();
		for (final Resource current : resource.getResourceSet().getResources()) {
			final String bundleName = getBundleNameFromResource(current);
			if (bundleName != null) {
				dependencies.add(bundleName);

			} else {
				Activator.log.warn(NLS.bind("We can't find the bundle providing the resource uri {0}", resource.getURI()));
			}
			dependencies.addAll(getModelBundleDependenciesFromXML(resource));

		}
		return dependencies;
	}

	/**
	 * @see org.eclipse.papyrus.toolsmiths.plugin.builder.GenericEMFModelBuilder#getBundleNameFromResource(org.eclipse.emf.ecore.resource.Resource)
	 *
	 * @param resource
	 * @return
	 */
	@Override
	protected String getBundleNameFromResource(Resource resource) {
		String str = super.getBundleNameFromResource(resource);
		if (str == null) {
			org.eclipse.emf.common.util.URI uri = resource.getURI();
			if (uri.scheme().equals("ppe") && uri.segments()[0].equals("environment")) {
				str = uri.segments()[1];
			}
		}
		return str;
	}

	/**
	 * @see org.eclipse.papyrus.toolsmiths.plugin.builder.GenericEMFModelBuilder#isIgnoredFileExtension(java.lang.String)
	 *
	 * @param fileExtension
	 * @return
	 */
	@Override
	protected boolean managedFileExtension(String fileExtension) {
		return XWT_EXTENSION.equals(fileExtension) || CTX_EXTENSION.equals(fileExtension) || ENVIRONMENT_XMI_EXTENSION.equals(fileExtension);
	}

	/**
	 * @see org.eclipse.papyrus.toolsmiths.plugin.builder.GenericEMFModelBuilder#isIgnoredNS_URI(java.lang.String)
	 *
	 * @param ns_URI
	 * @return
	 */
	@Override
	protected boolean isIgnoredNS_URI(String ns_URI) {
		return super.isIgnoredNS_URI(ns_URI) || "clr-namespace:java.lang".equals(ns_URI);
	}
}
