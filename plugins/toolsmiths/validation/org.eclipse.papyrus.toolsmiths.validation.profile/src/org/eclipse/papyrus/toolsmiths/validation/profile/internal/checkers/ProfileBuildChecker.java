/*****************************************************************************
 * Copyright (c) 2019 CEA LIST, EclipseSource and others.
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
import java.util.List;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.MarkersService;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.ProjectManagementService;
import org.eclipse.papyrus.toolsmiths.validation.profile.Activator;
import org.eclipse.pde.core.build.IBuild;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.build.IBuildModel;
import org.eclipse.pde.internal.core.builders.PDEMarkerFactory;
import org.eclipse.pde.internal.core.text.build.BuildEntry;
import org.eclipse.pde.internal.core.text.build.BuildModel;

/**
 * This class allows to check the 'build.properties' needed for the profile file.
 */
public class ProfileBuildChecker implements IPluginChecker {

	/**
	 * The current project resource.
	 */
	private final IProject project;

	/**
	 * The file of the UML profile.
	 */
	private final IFile profileFile;

	/**
	 * Constructor.
	 *
	 * @param project
	 *            The current project resource.
	 * @param profileFile
	 *            The file of the UML profile.
	 */
	public ProfileBuildChecker(final IProject project, final IFile profileFile) {
		this.project = project;
		this.profileFile = profileFile;
	}


	/**
	 * This allows to check the build of the profile file.
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker#check(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings("restriction")
	@Override
	public void check(final IProgressMonitor monitor) {

		if (null != monitor) {
			if (monitor.isCanceled()) {
				return;
			}
			monitor.subTask("Validate 'build.properties' file for profile '" + profileFile.getName() + "'."); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// Get the build.properties entries from the project
		final IBuildModel buildModel = ProjectManagementService.getPluginBuild(project);
		if (null != buildModel) {

			// Create the conditions:
			// - Boolean to determinate if the build contains folder containing the profile
			boolean containsProfile = false;

			// Calculate the profile path
			final String profilePath = profileFile.getProjectRelativePath().toString();

			final IBuild build = buildModel.getBuild();
			final IBuildEntry buildEntry = build.getEntry(IBuildEntry.BIN_INCLUDES);

			// Iterate on existing tokens
			final String[] tokens = buildEntry.getTokens();
			for (int i = 0; i < tokens.length && !containsProfile; i++) {
				containsProfile = profilePath.startsWith(tokens[i]);
			}
			List<BuildError> errors = new ArrayList<>();

			// Create marker for UMLProfile extension point if needed
			if (!containsProfile) {
				errors.add(new BuildError(PDEMarkerFactory.MARKER_ID, "The build does not contains entry for file '" + profilePath + "'", IMarker.SEVERITY_ERROR, IBuildEntry.BIN_INCLUDES));
			}
			reportErrors(errors);
		}

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
	private void reportErrors(List<BuildError> errors) {
		final IFile buildPropertiesFile = ProjectManagementService.getBuildFile(project);
		BuildModel textBuildModel = prepareTextBuildModel(buildPropertiesFile);

		errors.stream().forEach(error -> {
			reportBuildError(buildPropertiesFile, textBuildModel, error.type, error.message, error.severity, error.entry);
		});
	}

	/**
	 * Create a Marker on the specified file, with the given parameters.
	 *
	 * @param buildFile
	 *            the file on which the marker should be created.
	 * @param textBuildModel
	 *            the textual model representation of the build.properties file
	 * @param type
	 *            the type of the marker to create
	 * @param message
	 *            the message of the marker to create
	 * @param severity
	 *            the severity of the marker to create
	 * @param header
	 *            the header entry of the build file on which marker is created.
	 */
	@SuppressWarnings("restriction")
	private void reportBuildError(IFile buildFile, BuildModel textBuildModel, String type, String message, int severity, String entry) {
		if (textBuildModel != null) {
			IMarker marker = MarkersService.createMarker(buildFile, type, message, severity);
			try {
				marker.setAttribute(IMarker.LINE_NUMBER, getLineNumber(textBuildModel.getBuild().getEntry(entry)));
			} catch (CoreException e) {
				Activator.log.error(e);
			}
		} else {
			MarkersService.createMarker(buildFile, type, message, severity);
		}
	}

	/**
	 * Read and parse the build.properties file to create the abstract representation.
	 *
	 * @param file
	 *            the file to parse
	 * @return the model of the build file.
	 */
	@SuppressWarnings("restriction")
	private BuildModel prepareTextBuildModel(IFile file) {
		try {
			IDocument doc = createDocument(file);
			if (doc == null) {
				return null;
			}
			BuildModel bm = new BuildModel(doc, true);
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
		try {
			manager.connect(file.getFullPath(), LocationKind.NORMALIZE, null);
			ITextFileBuffer textBuf = manager.getTextFileBuffer(file.getFullPath(), LocationKind.NORMALIZE);
			IDocument document = textBuf.getDocument();
			manager.disconnect(file.getFullPath(), LocationKind.NORMALIZE, null);
			return document;
		} catch (CoreException e) {
			Activator.log.error(e);
		}
		return null;
	}

	/**
	 * Retrieve the line number for the specified entry in the build.properties file.
	 *
	 * @param imh
	 *            the entry to be retrieved.
	 * @return the line number or <code>0</code> if none was found.
	 */
	@SuppressWarnings("restriction")
	private int getLineNumber(IBuildEntry ibe) {
		if (!(ibe instanceof BuildEntry)) {
			return 0;
		}
		BuildEntry be = (BuildEntry) ibe;
		org.eclipse.jface.text.IDocument doc = ((BuildModel) be.getModel()).getDocument();
		try {
			int buildEntryLineNumber = doc.getLineOfOffset(be.getOffset()) + 1;
			// we are interested in the build entry name
			// (getLineOfOffset is 0-indexed, need 1-indexed)
			return buildEntryLineNumber;
		} catch (BadLocationException e) {
			Activator.log.error(e);
		}
		return 0;
	}

	/**
	 * Representation of an error on the build.properties file.
	 */
	private static class BuildError {
		public final String entry;
		public final int severity;
		public final String message;
		public final String type;

		public BuildError(String type, String message, int severity, String entry) {
			this.type = type;
			this.message = message;
			this.severity = severity;
			this.entry = entry;
		}
	}
}
