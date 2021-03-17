/*****************************************************************************
 * Copyright (c) 2019, 2020 CEA LIST, Christian W. Damus, and others.
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
 *   Christian W. Damus - bug 569357
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.profile.internal.checkers;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.BuildPropertiesChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.ModelDependenciesChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.MarkersService;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.PluginValidationService;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.ProjectManagementService;
import org.eclipse.papyrus.toolsmiths.validation.profile.Activator;
import org.eclipse.papyrus.toolsmiths.validation.profile.constants.ProfilePluginValidationConstants;
import org.eclipse.papyrus.toolsmiths.validation.profile.internal.messages.Messages;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Profile;

/**
 * This allows to check a profile plug-in (extensions, builds, dependencies).
 */
public class ProfilePluginChecker {

	/**
	 * This allows to check the profile plug-in.
	 *
	 * @param project
	 *            The current project to check.
	 */
	public static void checkProfilePlugin(final IProject project) {

		// Get the shell to manage the validation in an UI
		final Shell shell = Display.getCurrent().getActiveShell();

		try {
			// Open the progress monitor dialog
			new ProgressMonitorDialog(shell).run(true, true, monitor -> {
				final Collection<IFile> profileFiles = ProjectManagementService.getFilesFromProject(project, "profile.uml", true); //$NON-NLS-1$
				monitor.beginTask(Messages.ProfilePluginChecker_validateProfilePluginTask, 1 + (profileFiles.size() * 4)); // $NON-NLS-1$

				monitor.subTask("Prepare validation."); //$NON-NLS-1$
				// First of all, delete the existing markers for project
				MarkersService.deleteMarkers(project, ProfilePluginValidationConstants.PROFILE_PLUGIN_VALIDATION_TYPE);
				monitor.worked(1);

				// Create the plug-in validation service
				final PluginValidationService pluginValidationService = new PluginValidationService();

				// For all profiles files in the plug-in
				for (final IFile profileFile : profileFiles) {

					// get the existing profiles
					final URI profileFileURI = URI.createPlatformResourceURI(profileFile.getFullPath().toOSString(), true);
					final Collection<Profile> profiles = loadProfiles(profileFileURI);

					if (!profiles.isEmpty()) {
						// First, create the extensions checker
						pluginValidationService.addPluginChecker(new ProfileExtensionsChecker(project, profileFile, profiles));

						// Create the profile definition checker (no definition must be done for static profiles)
						pluginValidationService.addPluginChecker(new ProfileDefinitionChecker(profileFile, profiles));

						// Create the dependencies checker (depending to the external profile references)
						Resource resource = profiles.iterator().next().eResource();
						pluginValidationService.addPluginChecker(createProfileDependenciesChecker(project, profileFile, resource));
					}

					// Create the build checker
					pluginValidationService.addPluginChecker(new BuildPropertiesChecker(project, profileFile).withEMFGeneratorModels());
				}

				monitor.worked(1);

				// Call the validate
				pluginValidationService.validate(monitor);
			});
		} catch (InvocationTargetException e) {
			Activator.log.error(e);
		} catch (InterruptedException e) {
			// Do nothing, just cancelled by user
		}
	}

	/**
	 * Loads the EObject from the given URI.
	 *
	 * @param uri
	 *            The URI from which the EObject is loaded.
	 * @return
	 *         The profiles available in the model (can be empty if no profile or if an error occurred).
	 */
	private static Collection<Profile> loadProfiles(final URI uri) {
		final Collection<Profile> profiles = new HashSet<>();

		final ResourceSet resourceSet = new ResourceSetImpl();
		try {
			final Resource resource = resourceSet.getResource(uri, true);
			if (null != resource) {
				if (!resource.getContents().isEmpty()) {
					final Iterator<EObject> contentIt = resource.getAllContents();
					while (contentIt.hasNext()) {
						final EObject content = contentIt.next();
						if (content instanceof Profile) {
							profiles.add((Profile) content);
						}
					}
				}
			}
		} catch (final Exception ex) {
			Activator.log.error("Cannot load file: " + uri.toString(), ex); //$NON-NLS-1$
		}

		return profiles;
	}

	public static ModelDependenciesChecker createProfileDependenciesChecker(IProject project, IFile profileFile, Resource profileResource) {
		// TODO(569357): For now, continue using the Java Model Marker for compatibility until this is all refactored
		return new ModelDependenciesChecker(project, profileFile, profileResource, IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER)
				.withSeverityFunction(ModelDependenciesChecker.warningsFor("org.eclipse.uml2.uml.resources")); //$NON-NLS-1$
	}

}
