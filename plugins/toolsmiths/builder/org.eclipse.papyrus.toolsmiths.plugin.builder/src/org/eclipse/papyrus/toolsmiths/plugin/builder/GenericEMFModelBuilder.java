/*****************************************************************************
 * Copyright (c) 2020 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http: //www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.emf.helpers.BundleResourceURIHelper;
import org.eclipse.papyrus.emf.validation.DependencyValidationUtils;
import org.eclipse.papyrus.toolsmiths.plugin.builder.preferences.PluginBuilderPreferencesConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Generic builder for all EMF model/metamodel
 * This builder checks that all required metamodel are declared as dependencies of the Plugin
 * This builder launch the EMF validation on the model
 *
 * Result of the build process is the creation of IMarker (blocking the launch of the Eclipse runtime)
 */
public class GenericEMFModelBuilder extends AbstractPapyrusBuilder {

	/**
	 * helper used to find bundle associated to a resource or a URI
	 */
	private BundleResourceURIHelper RESOURCE_HELPER = BundleResourceURIHelper.INSTANCE;

	/**
	 * xmlns field in an XML file
	 */
	private static final String XMLNS = "xmlns"; //$NON-NLS-1$

	/**
	 * the list of excluded folder by name
	 */
	private static final Collection<String> EXCLUDED_FOLDER_NAME = new ArrayList<>();

	/**
	 * the list of excluded file name
	 */
	private static final Collection<String> EXCLUDED_FILE_NAME = new ArrayList<>();

	/**
	 * the list of excluded file extension
	 */
	private static final Collection<String> EXCLUDED_FILE_EXTENSION = new ArrayList<>();

	/**
	 * list of ignored nsURI
	 */
	private static final Collection<String> IGNORED_NS_URI = new ArrayList<>();

	static {
		EXCLUDED_FOLDER_NAME.add(".settings"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("META-INF"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("OSGI-INF"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("icons"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("images"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("bin"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("target"); //$NON-NLS-1$

		EXCLUDED_FILE_NAME.add(".classpath"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add(".project"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add(".gitignore"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add("about.html"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add("build.properties"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add("plugin.xml"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add("pom.xml"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add("README"); //$NON-NLS-1$

		EXCLUDED_FILE_EXTENSION.add("png"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("jpg"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("jpeg"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("gif"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("xml"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("md"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("exsd"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("svg"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("gmfgen"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("genmodel"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("html"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("mediawiki"); //$NON-NLS-1$

		// we exclude these files coming from Papyru model from the check
		EXCLUDED_FILE_EXTENSION.add("notation"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("di"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("properties"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("qvto"); //$NON-NLS-1$

		// we exclude this extension point because it requires a specific management
		EXCLUDED_FILE_EXTENSION.add(ArchitectureModelBuilder.ARCHITECTURE_EXTENSION);
		EXCLUDED_FILE_EXTENSION.add(ElementTypesConfigurationBuilder.ELEMENT_TYPES_CONFIGURATION_EXTENSION);
		EXCLUDED_FILE_EXTENSION.add(XWTModelBuilder.XWT_EXTENSION);
		EXCLUDED_FILE_EXTENSION.add(XWTModelBuilder.CTX_EXTENSION);
		EXCLUDED_FILE_EXTENSION.add(XWTModelBuilder.ENVIRONMENT_XMI_EXTENSION);

		IGNORED_NS_URI.add("http://www.w3.org/2001/XMLSchema-instance"); //$NON-NLS-1$
		IGNORED_NS_URI.add("http://www.omg.org/XMI"); //$NON-NLS-1$
		IGNORED_NS_URI.add("http://www.omg.org/spec/XMI/20131001"); //$NON-NLS-1$
		// IGNORED_NS_URI.add("http://www.eclipse.org/uml2/schemas/Ecore/5");
	}

	/**
	 * @see org.eclipse.papyrus.toolsmiths.plugin.builder.AbstractPapyrusBuilder#build(org.eclipse.core.resources.IProject, org.eclipse.papyrus.toolsmiths.plugin.builder.PapyrusPluginBuilder, int, java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 *
	 * @param builtProject
	 * @param papyrusBuilder
	 * @param kind
	 * @param args
	 * @param monitor
	 * @return
	 * @throws CoreException
	 */
	@Override
	public IProject[] build(IProject builtProject, PapyrusPluginBuilder papyrusBuilder, int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		if (true) {
			// return null;
		}
		if (builtProject != null) {

			final IJavaProject javaProject = JavaCore.create(builtProject);

			// 1. get all ecore resource file owned by the project
			final Map<Resource, IFile> resources = getEcoreResources(javaProject);

			// 2. launch validation on each resource
			if (isModelValidationActivated()) {
				validateModel(resources);
			}

			/*
			 * check the required dependencies
			 */
			if (isCheckModelDependencyActivated()) {
				checkModelDependencies(resources, builtProject);
			}
		}
		return null;
	}

	/**
	 *
	 * @param resources
	 *            the resource to validate
	 */
	protected void validateModel(final Map<Resource, IFile> resources) {
		for (final Entry<Resource, IFile> entry : resources.entrySet()) {
			Collection<Diagnostic> diagnostics = validateResource(entry.getKey());
			createMarkerErrorFromDiagnostics(entry.getValue(), diagnostics);
		}
	}

	/**
	 * This method checks that all required dependencies are included into the manifest file of the project
	 *
	 * @param resources
	 *            the resource for which we check the dependencies
	 * @param builtProject
	 *            the current built project
	 * @throws CoreException
	 */
	protected void checkModelDependencies(final Map<Resource, IFile> resources, final IProject builtProject) throws CoreException {
		// 3. get all current declared dependencies in the project
		final Collection<String> currentDeclaredDependencies = getAllAvailableDependencies(builtProject);

		// 4. explore resources dependencies
		for (final Entry<Resource, IFile> current : resources.entrySet()) {
			final Set<String> requiredDependencies = getDependencies(current.getKey(), builtProject);
			requiredDependencies.removeAll(currentDeclaredDependencies);
			requiredDependencies.remove(builtProject.getName());
			if (requiredDependencies.size() > 0) {
				final StringBuilder dependenciesToAdd = new StringBuilder();
				final Iterator<String> iter = requiredDependencies.iterator();
				while (iter.hasNext()) {
					dependenciesToAdd.append(iter.next());
					if (iter.hasNext()) {
						dependenciesToAdd.append(DependencyValidationUtils.DEPENDENCY_SEPARATOR);
					}
				}
				final IMarker marker = createErrorMarker(current.getValue(), "Missing Dependencies in model"); //$NON-NLS-1$
				marker.setAttribute(DependencyValidationUtils.MISSING_DEPENDENCIES, dependenciesToAdd.toString());
			}
		}
	}

	/**
	 *
	 * @param javaProject
	 *            a java project
	 * @return
	 *         the list of file which should be ecore models
	 */
	protected Map<Resource, IFile> getEcoreResources(final IJavaProject javaProject) {
		Map<Resource, IFile> resources = new HashMap<>();
		try {
			Object[] nonJavaResource = javaProject.getNonJavaResources();
			for (Object current : nonJavaResource) {
				resources.putAll(getEcoreResource(current));
			}

		} catch (JavaModelException e) {
			Activator.log.error(e);
		}

		return resources;
	}

	/**
	 *
	 * @param container
	 *            a container, that is an IFile or an IFolder
	 * @return
	 *         the map of EMF Resource/IFile owned by the container
	 */
	protected Map<Resource, IFile> getEcoreResource(final Object container) {
		Map<Resource, IFile> resources = new HashMap<>();
		if (container instanceof IFile) {
			final IFile f = (IFile) container;
			if ((managedFileExtension(f.getFileExtension()))
					&& !EXCLUDED_FILE_NAME.contains(f.getName())) {
				final Resource res = loadIfPossibleAsEcoreResource(f);
				if (res != null) {
					resources.put(res, f);
				}
			}

		} else if (container instanceof IFolder) {
			final IFolder folder = (IFolder) container;
			if (!EXCLUDED_FOLDER_NAME.contains(folder.getName())) {
				try {
					for (IResource current : folder.members()) {
						resources.putAll(getEcoreResource(current));
					}
				} catch (CoreException e) {
					Activator.log.error(e);
				}
			}
		}
		return resources;
	}

	/**
	 *
	 * @param f
	 *            a file
	 * @return
	 *         a Resource if the EMF framework is able to load it
	 */
	protected Resource loadIfPossibleAsEcoreResource(final IFile f) {
		final ResourceSet set = new ResourceSetImpl();
		final URI uri = URI.createPlatformResourceURI(f.getFullPath().toOSString(), true);
		try {
			final Resource res = set.getResource(uri, true);
			return res;
		} catch (Exception exp) {
			// nothing to do, the file is not an EMF Resource
		}
		return null;
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
	protected Set<String> getDependencies(final Resource resource, final IProject builtProject) {
		EcoreUtil.resolveAll(resource);
		final Set<String> dependencies = new TreeSet<>();
		for (final Resource current : resource.getResourceSet().getResources()) {
			if (!isIgnoredNS_URI(current.getURI().toString()) && managedFileExtension(current.getURI().fileExtension())) {
				try {
					dependencies.add(getBundleNameFromResource(current));
					dependencies.addAll(getModelBundleDependenciesFromXML(resource));
				} catch (Exception e) {
					int i = 0;
					i++;
				}
			}
		}
		return dependencies;
	}

	/**
	 *
	 * @param resource
	 *            a resource
	 * @return
	 *         the bundle providing this resource
	 */
	protected String getBundleNameFromResource(final Resource resource) {
		return RESOURCE_HELPER.getBundleNameFromResource(resource);
	}

	/**
	 *
	 * @param resource
	 *            an EMF Resource
	 * @return
	 *         the set of bundle's name required by this resource
	 */
	protected Set<String> getModelBundleDependenciesFromXML(final Resource resource) {
		final Set<String> importedMetamodels_NS_URI = getXMLImportedMetamodelNsURI(resource);
		return getBundleNameFromNS_URI(importedMetamodels_NS_URI);
	}

	/**
	 * This method allows to obtain metamodels URI reading the Resource as XML file
	 *
	 * @param resource
	 *            a resource
	 * @return
	 *         the list of imported metamodel in this resource, identified by their nsURI
	 */
	protected Set<String> getXMLImportedMetamodelNsURI(final Resource resource) {
		String libraryFile = null;
		if (resource.getURI().isPlatform()) {
			// TODO : improve me ?
			// works on linux or not ?
			libraryFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(resource.getURI().toPlatformString(true))).getRawLocation().toOSString();
		} else {
			// TODO
			// libraryFile = resource.resourceSet.URIConverter.normalize(theRelativeFile).toFileString
			throw new UnsupportedOperationException();
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		Set<String> uris = new HashSet<>();
		try {
			final DocumentBuilder dBuilder = factory.newDocumentBuilder();
			final Document doc = dBuilder.parse(libraryFile);
			final Element docElement = doc.getDocumentElement();
			final NamedNodeMap attr = docElement.getAttributes();
			for (int i = 0; i < attr.getLength(); i++) {
				Node item = attr.item(i);
				if (item.getNodeName().startsWith(XMLNS)) {
					uris.add(item.getNodeValue());
				}
			}
		} catch (ParserConfigurationException e) {
			Activator.log.error(e);
		} catch (SAXException e) {
			Activator.log.error(e);
		} catch (IOException e) {
			Activator.log.error(e);
		}
		return uris;
	}

	/**
	 *
	 * @param nsURIs
	 *            a collection of URI
	 * @return
	 *         a set with the bundle name providing the nsURI
	 */
	protected Set<String> getBundleNameFromNS_URI(final Collection<String> nsURIs) {
		final Set<String> result = new HashSet<>();
		for (final String currentNS_URI : nsURIs) {
			if (!isIgnoredNS_URI(currentNS_URI)) {
				final String bundleName = getBundleNameFromNS_URI(currentNS_URI);
				if (bundleName != null) {
					result.add(bundleName);
				} else {
					Activator.log.warn(NLS.bind("Papyrus Builder: We don't found bundle for nsURI {0}", currentNS_URI)); //$NON-NLS-1$
				}
			}
		}
		return result;
	}

	/**
	 *
	 * @param nsURI
	 *            the nsURI from a metamodel
	 * @return
	 *         the bundle provided the EPackage corresponding to this nsURI, or <code>null</code> if not found
	 */
	protected String getBundleNameFromNS_URI(final String nsURI) {
		// TODO : find a generic way for that!
		if (nsURI.equals("http://www.eclipse.org/uml2/schemas/UML2/2") || nsURI.equals("http://www.eclipse.org/uml2/schemas/Ecore/5")) {
			return "org.eclipse.uml2.uml.resources";
		}
		return RESOURCE_HELPER.getBundleNameFromNS_URI(nsURI);
	}

	/**
	 *
	 * @param resource
	 *            a resource
	 * @return
	 *         launch the validation on this resource
	 */
	protected Collection<Diagnostic> validateResource(final Resource resource) {
		final Collection<Diagnostic> diagnostics = new ArrayList<>();
		for (final EObject current : resource.getContents()) {
			org.eclipse.emf.common.util.Diagnostic diag = Diagnostician.INSTANCE.validate(current);
			// the root diagnotic is useless for us
			diagnostics.addAll(diag.getChildren());
		}
		return diagnostics;
	}

	/**
	 * This method create java error marker from the Diagnotics
	 *
	 * @param iResource
	 *            an {@link IResource}
	 * @param diagnostics
	 *            the {@link Diagnostic} build for this resource
	 */
	protected void createMarkerErrorFromDiagnostics(final IResource iResource, final Collection<Diagnostic> diagnostics) {
		if (diagnostics != null && !diagnostics.isEmpty()) {
			for (Diagnostic diagnostic : diagnostics) {
				createMarkerErrorFromDiagnostic(iResource, diagnostic);
			}
		}
	}

	/**
	 * This method create java error marker from the Diagnotic
	 *
	 * @param iResource
	 *            an {@link IResource}
	 * @param diagnostics
	 *            the {@link Diagnostic} build for this resource
	 */
	protected void createMarkerErrorFromDiagnostic(final IResource iResource, final Diagnostic diagnostic) {
		if (Diagnostic.ERROR == diagnostic.getSeverity()) {
			final IMarker marker = createErrorMarker(iResource, diagnostic.getMessage());
			int index = diagnostic.getData().indexOf(DependencyValidationUtils.MISSING_DEPENDENCIES);
			if (index > 0) {
				final String missingDependencies = (String) diagnostic.getData().get(index);
				try {
					marker.setAttribute(DependencyValidationUtils.MISSING_DEPENDENCIES, missingDependencies);
				} catch (CoreException e) {
					Activator.log.error(e);
				}
			}
		}
		createMarkerErrorFromDiagnostics(iResource, diagnostic.getChildren());
	}

	/**
	 *
	 * @return
	 *         <code>true</code> if the validation of model is activated
	 */
	protected final boolean isModelValidationActivated() {
		return Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.PAPYRUS_MODEL_BUILDER_VALIDATE_MODEL);
	}

	/**
	 *
	 * @return
	 *         <code>true</code> if the check of model dependencies
	 */
	protected final boolean isCheckModelDependencyActivated() {
		return Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.PAPYRUS_MODEL_BUILDER_CHECK_MODEL_DEPENDENCIES);
	}

	protected boolean managedFileExtension(final String fileExtension) {
		return !EXCLUDED_FILE_EXTENSION.contains(fileExtension);
	}

	protected boolean isIgnoredNS_URI(final String ns_URI) {
		return IGNORED_NS_URI.contains(ns_URI);
	}
}
