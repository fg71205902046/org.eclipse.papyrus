/*****************************************************************************
 * Copyright (c) 2019 CEA LIST and others.
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
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.elementtypes.internal.checkers;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.MarkersService;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.PluginValidationService;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.ProjectManagementService;
import org.eclipse.papyrus.toolsmiths.validation.elementtypes.constants.ElementTypesPluginValidationConstants;

/**
 * This allows to check an element types plug-in (extensions, builds, dependencies, ...).
 */
public class ElementTypesPluginChecker {

	/**
	 * This allows to check the element types plug-in.
	 *
	 * @param project
	 *            The current project to check.
	 * @param A
	 *            monitor to report progress
	 */
	public static void checkElementTypesPlugin(final IProject project, IProgressMonitor monitor) {
		// Open the progress monitor dialog
		final Collection<IFile> elementTypesFiles = ProjectManagementService.getFilesFromProject(project, "elementtypesconfigurations", true); //$NON-NLS-1$
		monitor.beginTask("Validate Element Types plug-in", 1 + (elementTypesFiles.size() * 3)); // $NON-NLS-1$

		monitor.subTask("Prepare plug-in validation"); //$NON-NLS-1$
		// First of all, delete the existing markers for project
		MarkersService.deleteMarkers(project, ElementTypesPluginValidationConstants.ELEMENTTYPES_PLUGIN_VALIDATION_TYPE);

		// Create the plug-in validation service
		final PluginValidationService pluginValidationService = new PluginValidationService();

		// First, check the dependencies needed
		pluginValidationService.addPluginChecker(new ElementTypesDependenciesChecker(project));

		// For all element types files in the plug-in
		for (final IFile elementTypesFile : elementTypesFiles) {
			if (monitor.isCanceled()) {
				return;
			}

			// Get the resource
			final URI elementTypesFileURI = URI.createPlatformResourceURI(elementTypesFile.getFullPath().toOSString(), true);
			final Resource resource = new ResourceSetImpl().getResource(elementTypesFileURI, true);

			// Check the validation of the element types file
			pluginValidationService.addPluginChecker(new ElementTypesFileChecker(elementTypesFile, resource));

			// Check the extension point
			pluginValidationService.addPluginChecker(new ElementTypesExtensionsChecker(project, elementTypesFile));

			// Check the external dependencies needed
			pluginValidationService.addPluginChecker(new ElementTypesExternalDependenciesChecker(project, elementTypesFile, resource));
		}

		monitor.worked(1);

		// Call the validate
		pluginValidationService.validate(monitor);
	}

}
