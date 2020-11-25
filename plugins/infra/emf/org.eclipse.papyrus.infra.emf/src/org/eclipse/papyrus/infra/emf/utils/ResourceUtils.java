/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
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
 *  Vincent Lorenzo (CEA LIST) Vincent.Lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.emf.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginObject;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.core.text.plugin.PluginElementNode;

/**
 *
 * This class provides methods for EMF Resource
 *
 */
@SuppressWarnings("restriction")
public class ResourceUtils {

	private static final String ECORE_URI_MAPPING_EXTENSION_POINT = "org.eclipse.emf.ecore.uri_mapping"; //$NON-NLS-1$
	private static final String PATH_SEPARATOR = "/";


	private ResourceUtils() {
		// to prevent instanciation
	}

	/**
	 *
	 * @param resource
	 *            an EMF resource
	 * @return
	 *         the IFile corresponding to this resource, or <code>null</code> if not found
	 */
	public static IFile getFile(final Resource resource) {
		if (resource != null) {
			URI uri = resource.getURI();
			uri = resource.getResourceSet().getURIConverter().normalize(uri);
			if (uri.isPlatformResource()) {
				String uriPlatformString = uri.toPlatformString(true);
				return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uriPlatformString));
			}
		}
		return null;
	}

	/**
	 *
	 *
	 * @param resource
	 *            a resource
	 * @return
	 *         a collection with all existing id in the resource
	 *
	 */
	public static Collection<String> getAllResourceIds(final XMIResource resource) {
		final Set<String> ids = new HashSet<>();
		final Iterator<EObject> iterator = resource.getAllContents();
		while (iterator.hasNext()) {
			final EObject current = iterator.next();
			final String id = resource.getID(current);
			Assert.isNotNull(id);
			Assert.isTrue(!ids.contains(id));
			ids.add(id);
		}
		return ids;
	}


	/**
	 * Default options to save emf files used in Papyrus
	 *
	 * @return
	 */
	public static Map<String, Object> getSaveOptions() {
		Map<String, Object> saveOptions = new HashMap<>();

		// default save options.
		saveOptions.put(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE);
		saveOptions.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		saveOptions.put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);
		saveOptions.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, Boolean.TRUE);
		saveOptions.put(XMLResource.OPTION_SKIP_ESCAPE_URI, Boolean.FALSE);
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");

		// see bug 397987: [Core][Save] The referenced plugin models are saved using relative path
		saveOptions.put(XMLResource.OPTION_URI_HANDLER, new org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl.PlatformSchemeAware());

		return saveOptions;
	}

	/**
	 * Return an encoded path string that can be used as a path attribute in a an extension for the given file.
	 * If an Ecore URI mapping exists for the path, it will be used as a path segment.
	 * Otherwise, a project-relative encoded path will be returned.
	 */
	public static String mapAndEncodePath(IFile file) {
		// check if we have a mapping that we can use
		URI pluginURI = URI.createPlatformPluginURI(file.getProject().getName() + PATH_SEPARATOR + file.getProjectRelativePath().toString(), true);
		String path = pluginURI.toString();

		// check local mappings first
		for (Entry<String, String> entry : getLocalUriMappings(file.getProject()).entrySet()) {
			String targetURI = entry.getValue();
			if (targetURI != null && targetURI.endsWith(PATH_SEPARATOR) && path.startsWith(targetURI)) {
				return path.replaceFirst(targetURI, entry.getKey());
			}
		}
		// check registered mappings
		for (Entry<URI, URI> entry : URIMappingRegistryImpl.INSTANCE.entrySet()) {
			String targetURI = entry.getValue().toString();
			if (targetURI != null && targetURI.endsWith(PATH_SEPARATOR) && path.startsWith(targetURI)) {
				return path.replaceFirst(targetURI, entry.getKey().toString());
			}
		}
		// no mapping were found - fallback to project relative path
		IPath relativePath = file.getProjectRelativePath();
		return getStringURI(relativePath);
	}

	/** Return the `org.eclipse.emf.ecore.uri_mapping` extension declarations in the given project. */
	public static Map<String, String> getLocalUriMappings(IProject project) {
		HashMap<String, String> localMappings = new HashMap<>();
		final IPluginModelBase model = PluginRegistry.findModel(project.getName());
		for (IPluginExtension extension : model.getExtensions().getExtensions()) {
			if (!Objects.equals(extension.getPoint(), ECORE_URI_MAPPING_EXTENSION_POINT)) {
				continue;
			}
			List<IPluginObject> pluginObjects = Arrays.stream(extension.getChildren()).filter(child -> Objects.equals("mapping", child.getName())).collect(Collectors.toList()); //$NON-NLS-1$
			pluginObjects.forEach(pluginObject -> {
				if ((pluginObject instanceof PluginElementNode)) {
					PluginElementNode node = (PluginElementNode) pluginObject;
					localMappings.put(node.getAttribute("source").getValue(), node.getAttribute("target").getValue()); //$NON-NLS-1$ //$NON-NLS-2$
				}
			});
		}
		return localMappings;
	}

	/** Returns an encoded string representation of the path. */
	public static String getStringURI(IPath path) {
		return Arrays.stream(path.segments())
				.map(segment -> URI.encodeSegment(segment, false))
				.collect(Collectors.joining(PATH_SEPARATOR));
	}

}
