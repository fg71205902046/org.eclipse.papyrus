/*****************************************************************************
 * Copyright (c) 2019, 2020 CEA LIST, EclipseSource and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Nicolas FAUVERGUE (CEA LIST) nicolas.fauvergue@cea.fr - Initial API and implementation
 *   Remi Schnekenburger (EclipseSource) - Bug 568495
 *
 *****************************************************************************/
package org.eclipse.papyrus.toolsmiths.validation.profile.internal.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.internal.runtime.MetaDataKeeper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.MarkersService;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.ProjectManagementService;
import org.eclipse.papyrus.toolsmiths.validation.profile.Activator;
import org.eclipse.pde.internal.core.builders.PDEMarkerFactory;
import org.eclipse.pde.internal.core.ibundle.IManifestHeader;
import org.eclipse.pde.internal.core.text.bundle.BundleModel;
import org.eclipse.pde.internal.core.text.bundle.ManifestHeader;
import org.osgi.framework.Constants;

/**
 * This class allows to check that the plug-in has the correct dependencies depending to the external profile references.
 */
public class ProfileDependenciesChecker implements IPluginChecker {

	/**
	 * The plug-ins to detect as warning instead of errors.
	 */
	private static Set<String> WARNING_PLUGINS_EXCEPTION = Set.of("org.eclipse.uml2.uml.resources"); //$NON-NLS-1$

	/**
	 * The current project resource.
	 */
	private final IProject project;

	/**
	 * The file of the UML profile.
	 */
	private final IFile profileFile;

	/**
	 * The EMF resource.
	 */
	private final Resource resource;

	/**
	 * Constructor.
	 *
	 * @param project
	 *            The current project resource.
	 * @param profileFile
	 *            The file of the UML profile.
	 * @param resource
	 *            The EMF resource.
	 */
	public ProfileDependenciesChecker(final IProject project, final IFile profileFile, final Resource resource) {
		this.project = project;
		this.profileFile = profileFile;
		this.resource = resource;
	}

	/**
	 * This allows to check that the plug-in has the correct dependencies depending to the external profile references.
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker#check(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings("restriction")
	@Override
	public void check(final IProgressMonitor monitor) {

		if (null != monitor) {
			monitor.subTask("Validate dependencies for profile '" + profileFile.getName() + "'."); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// Get the external reference paths
		final Collection<URI> externalReferencesPaths = getExternalReferencesPaths(project, profileFile, resource);

		// Calculate plug-ins names from URI
		final Collection<String> requiredPlugins = new HashSet<>();
		externalReferencesPaths.stream().forEach(externalReferencePath -> requiredPlugins.add(getPluginNameFromURI(externalReferencePath)));

		// For each external reference, get its plug-in name and search its dependency in the plug-in
		final Collection<String> existingRequiredPlugins = new HashSet<>();
		final List<BundleSpecification> dependencies = ProjectManagementService.getPluginDependencies(project);
		if (null != dependencies && !dependencies.isEmpty()) {
			ProjectManagementService.getPluginDependencies(project).stream().forEach(dependency -> existingRequiredPlugins.add(dependency.getName()));
			requiredPlugins.removeIf(requiredPlugin -> existingRequiredPlugins.contains(requiredPlugin));
		}

		List<ManifestError> errors = new ArrayList<>();
		// If requiredPlugins is not empty, that means, the dependency is not available in the profile plug-in
		// So, create the warning markers
		if (!requiredPlugins.isEmpty()) {
			requiredPlugins.stream().forEach(requiredPlugin -> {
				int severity = IMarker.SEVERITY_ERROR;
				if (WARNING_PLUGINS_EXCEPTION.contains(requiredPlugin)) {
					severity = IMarker.SEVERITY_WARNING;
				}
				errors.add(new ManifestError(PDEMarkerFactory.MARKER_ID, "The plug-in '" + requiredPlugin + "' must be defined as required plug-in (for profile '" + profileFile.getName() + "').", severity, Constants.REQUIRE_BUNDLE));
			});
		}

		reportErrors(errors);

		if (null != monitor) {
			monitor.worked(1);
		}
	}

	/**
	 * generate markers for the specified list of errors.
	 *
	 * @param errors
	 *            the list of errors for which markers will be created.
	 */
	@SuppressWarnings("restriction")
	private void reportErrors(List<ManifestError> errors) {
		if (!errors.isEmpty()) {
			final IFile manifestFile = ProjectManagementService.getManifestFile(project);
			BundleModel textBundleModel = prepareTextBundleModel(manifestFile);
			errors.stream().forEach(error -> {
				reportBundleError(manifestFile, textBundleModel, error.type, error.message, error.severity, error.header);
			});
		}
	}

	/**
	 * Create a Marker on the specified file, with the given parameters.
	 *
	 * @param manifestFile
	 *            the file on which the marker should be created.
	 * @param textBundleModel
	 *            the textual model representation of the Manifest.MF file
	 * @param type
	 *            the type of the marker to create
	 * @param message
	 *            the message of the marker to create
	 * @param severity
	 *            the severity of the marker to create
	 * @param header
	 *            the header entry of the manifest file on which marker is created.
	 */
	@SuppressWarnings("restriction")
	private void reportBundleError(IFile manifestFile, BundleModel textBundleModel, String type, String message, int severity, String header) {
		if (textBundleModel != null) {
			IMarker marker = MarkersService.createMarker(manifestFile, type, message, severity);
			try {
				marker.setAttribute(IMarker.LINE_NUMBER, getLineNumber(textBundleModel.getBundle().getManifestHeader(header)));
			} catch (CoreException e) {
				Activator.log.error(e);
			}
		} else {
			MarkersService.createMarker(manifestFile, type, message, severity);
		}
	}

	/**
	 * Read and parse the manifest file to create the abstract representation.
	 *
	 * @param manifestFile
	 *            the file to parse
	 * @return the model of the manifest file.
	 */
	@SuppressWarnings("restriction")
	private BundleModel prepareTextBundleModel(IFile manifestFile) {
		try {
			IDocument doc = createDocument(manifestFile);
			if (doc == null) {
				return null;
			}
			BundleModel bm = new BundleModel(doc, true);
			bm.load();
			if (!bm.isLoaded()) {
				return null;
			}
			return bm;
		} catch (CoreException e) {
			Activator.log.error(e);
			return null;
		}
	}

	/**
	 * Read the manifest file and provide a {@link IDocument}.
	 *
	 * @param file
	 *            the file to parse
	 * @return the document of the textual file.
	 */
	protected IDocument createDocument(IFile file) {
		if (!file.exists()) {
			return null;
		}
		ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();
		if (manager == null) {
			return null;
		}
		IDocument document = null;
		try {
			manager.connect(file.getFullPath(), LocationKind.NORMALIZE, null);
			ITextFileBuffer textBuf = manager.getTextFileBuffer(file.getFullPath(), LocationKind.NORMALIZE);
			document = textBuf.getDocument();
		} catch (CoreException e) {
			Activator.log.error(e);
		} finally {
			try {
				manager.disconnect(file.getFullPath(), LocationKind.NORMALIZE, null);
			} catch (CoreException e) {
				Activator.log.error(e);
			}
		}
		return document;
	}

	/**
	 * This allows to get the external references paths.
	 *
	 * @param project
	 *            The current project.
	 * @param profileFile
	 *            The file or the UML profile.
	 * @param resource
	 *            The resource to get external references paths.
	 * @return The external references paths.
	 */
	@SuppressWarnings("restriction")
	private Collection<URI> getExternalReferencesPaths(final IProject project, final IFile profileFile, final Resource resource) {
		final Collection<URI> externalReferencesPaths = new HashSet<>();
		String metadataPath = MetaDataKeeper.getMetaArea().getMetadataLocation().toString();

		// First step, resolve all references
		EcoreUtil.resolveAll(resource);

		for (final Resource currentResource : resource.getResourceSet().getResources()) {
			// Check that the resource is not the current one or is not available in the same plugin
			if (isExternalReferenceToManage(project, currentResource)) {
				final URI resourceURI = currentResource.getURI();

				// React differently if this is a pathmap
				if (resourceURI.toString().startsWith("pathmap://")) { //$NON-NLS-1$
					// Try to resolve the pathmap
					final URI correspondingURI = getCorrespondingURIFromPathmap(resourceURI);
					if (null == correspondingURI) {
						// If this case, the pathmap cannot be resolved, so create a marker
						MarkersService.createMarker(profileFile,
								PDEMarkerFactory.MARKER_ID,
								"The pathmap '" + resourceURI.toString() + "' cannot be resolved.", //$NON-NLS-1$ //$NON-NLS-2$
								IMarker.SEVERITY_ERROR);
					} else {
						externalReferencesPaths.add(correspondingURI);
					}
				} else if (resourceURI.toFileString().startsWith(metadataPath)) {
					// avoid adding dependencies towards local metadata (case of internationalization)
				} else {
					externalReferencesPaths.add(resourceURI);
				}
			}
		}

		return externalReferencesPaths;

	}

	/**
	 * This allows to determinate if the external reference must be managed or not.
	 * For example, we don't have to manage references of files from the same plug-in.
	 * Moreover, some pathmaps don't need to be include in the dependencies.
	 *
	 * @param project
	 *            The current project.
	 * @param resource
	 *            The resource to check.
	 * @return <code>true</code> if we have to manage reference, <code>false</code> otherwise.
	 */
	private boolean isExternalReferenceToManage(final IProject project, final Resource resource) {
		final String resourceURI = resource.getURI().toString();

		// We don't have to manage references of files from the same plug-in
		if (resourceURI.startsWith("platform:/plugin/" + project.getName() + "/") || //$NON-NLS-1$ //$NON-NLS-2$
				resourceURI.startsWith("platform:/resource/" + project.getName() + "/")) { //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		}

		return true;
	}

	/**
	 * This allows to resolve pathmap. To do this, we trim last segments until we got the correct corresponding URI.
	 * It is possible that we don't find pathmap, in this case, just return null.
	 *
	 * @param uri
	 *            The pathmap URI to search.
	 * @return The corresponding URI to the pathmap.
	 */
	private URI getCorrespondingURIFromPathmap(final URI uri) {
		URI copiedURI = URI.createURI(uri.toString());
		URI foundCorrespondingURI = null;

		while (null == foundCorrespondingURI) {
			foundCorrespondingURI = URIMappingRegistryImpl.INSTANCE.get(copiedURI);
			if (null == foundCorrespondingURI) {
				if (copiedURI.segmentCount() <= 0) {
					break;
				}
				copiedURI = copiedURI.trimSegments(1);
			}
		}

		return foundCorrespondingURI;
	}

	/**
	 * This allows to get the plug-in name from the URI.
	 *
	 * @param uri
	 *            The initial URI.
	 * @return The plug-in name from URI or <code>null</code> if any problem occurred.
	 */
	private String getPluginNameFromURI(final URI uri) {
		String pluginName = null;

		// Take we correct segment (without authority)
		final int takenSegment = uri.hasAuthority() ? 0 : 1;
		if (uri.segmentCount() > takenSegment) {
			pluginName = uri.segment(takenSegment);
		}

		return pluginName;
	}

	/**
	 * Retrieve the line number for the specified header in the Manifest file.
	 *
	 * @param imh
	 *            the manifest header to be retrieved.
	 * @return the line number or <code>0</code> if none was found.
	 */
	@SuppressWarnings("restriction")
	private int getLineNumber(IManifestHeader imh) {
		if (!(imh instanceof ManifestHeader)) {
			return 0;
		}
		ManifestHeader mh = (ManifestHeader) imh;
		org.eclipse.jface.text.IDocument doc = ((BundleModel) mh.getModel()).getDocument();
		try {
			int bundleEntryLineNumber = doc.getLineOfOffset(mh.getOffset()) + 1;
			// we are interested in the build entry name
			// (getLineOfOffset is 0-indexed, need 1-indexed)
			return bundleEntryLineNumber;
		} catch (BadLocationException e) {
			Activator.log.error(e);
		}
		return 0;
	}

	/**
	 * Representation of an error on the Manifest file.
	 */
	private static class ManifestError {

		private final String type;
		private final String message;
		private final int severity;
		private final String header;

		public ManifestError(String type, String message, int severity, String header) {
			this.type = type;
			this.message = message;
			this.severity = severity;
			this.header = header;
		}
	}
}
