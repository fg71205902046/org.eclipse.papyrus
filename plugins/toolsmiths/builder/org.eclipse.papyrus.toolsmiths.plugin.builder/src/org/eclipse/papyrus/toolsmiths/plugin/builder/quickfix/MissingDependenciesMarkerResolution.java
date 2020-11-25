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

package org.eclipse.papyrus.toolsmiths.plugin.builder.quickfix;

import java.io.IOException;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.papyrus.eclipse.project.editors.file.ManifestEditor;
import org.eclipse.papyrus.emf.validation.DependencyValidationUtils;
import org.eclipse.papyrus.toolsmiths.plugin.builder.Activator;
import org.eclipse.papyrus.toolsmiths.plugin.builder.Messages;
import org.eclipse.papyrus.toolsmiths.plugin.builder.helper.BundleVersionHelper;

/**
 * This class add the missing dependencies detected in EMF models into the manifest of the project
 */
public class MissingDependenciesMarkerResolution extends AbstractPapyrusMarkerResolution {

	/**
	 * @see org.eclipse.ui.IMarkerResolution#getLabel()
	 *
	 * @return
	 */
	@Override
	public String getLabel() {
		return Messages.MissingDependenciesMarkerResolution_label;
	}

	/**
	 * @see org.eclipse.ui.IMarkerResolution#run(org.eclipse.core.resources.IMarker)
	 *
	 * @param marker
	 */
	@Override
	public void run(IMarker marker) {
		String dependencies = null;
		try {
			dependencies = (String) marker.getAttribute(DependencyValidationUtils.MISSING_DEPENDENCIES);
		} catch (CoreException e) {
			// nothing to do
		}
		if (dependencies != null && !dependencies.isEmpty()) {
			String[] result = dependencies.split(DependencyValidationUtils.DEPENDENCY_SEPARATOR);
			final IResource res = marker.getResource();
			final IProject project = res.getProject();
			try {
				ManifestEditor editor = new ManifestEditor(project);
				editor.init();
				for (String current : result) {
					final BundleVersionHelper versionHelper = new BundleVersionHelper(current);
					final String newVersion = versionHelper.getVersionRangeForManifest();
					editor.addDependency(current, newVersion);

				}
				editor.save();
			} catch (IOException e) {
				Activator.log.error(e);
			} catch (CoreException e) {
				Activator.log.error(e);
			}
		}
	}

	/**
	 * @see org.eclipse.ui.IMarkerResolution2#getDescription()
	 *
	 * @return
	 */
	@Override
	public String getDescription() {
		return Messages.MissingDependenciesMarkerResolution_description;
	}

}
