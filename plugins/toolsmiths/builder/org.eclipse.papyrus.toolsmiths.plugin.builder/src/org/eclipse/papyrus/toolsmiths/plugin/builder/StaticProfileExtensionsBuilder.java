/*****************************************************************************
 * Copyright (c) 2020 CEA LIST, EclipseSource and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Remi Schnekenburger (EclipseSource) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.toolsmiths.plugin.builder;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.papyrus.toolsmiths.plugin.builder.helper.StaticProfileHelper;
import org.eclipse.papyrus.toolsmiths.validation.profile.internal.checkers.ProfileExtensionsChecker;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.core.builders.PDEMarkerFactory;
import org.eclipse.uml2.uml.Profile;

/**
 * Builder that checks extensions for the plugin.xml file when project contains a static profile.
 */
@SuppressWarnings("restriction")
public class StaticProfileExtensionsBuilder extends AbstractPapyrusBuilder {

	@Override
	public IProject[] build(IProject builtProject, PapyrusPluginBuilder papyrusBuilder, int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {

		if (papyrusBuilder.isInterrupted() || monitor.isCanceled()) {
			return null;
		}
		// check static profile presence?
		Map<IFile, List<Profile>> profiles = StaticProfileHelper.findStaticProfiles(builtProject, true);

		if (profiles.entrySet().isEmpty()) {
			return null;
		}
		// retrieve information from plugin extensions, warning, this iterates several time on the same parser with different file. Check that clean does not happen between various profiles
		for (Entry<IFile, List<Profile>> entry : profiles.entrySet()) {
			ProfileExtensionsChecker profileExtensionsChecker = new ProfileExtensionsChecker(builtProject, entry.getKey(), entry.getValue());
			profileExtensionsChecker.check(monitor);
		}

		return null;

	}

	@Override
	public void clean(IProgressMonitor monitor, IProject project) throws CoreException {
		super.clean(monitor, project);

		final IPluginModelBase pluginModelBase = PluginRegistry.findModel(project);
		if (pluginModelBase == null) {
			return;
		}

		// clean the kind of markers created by the specific plugin error reporter.
		pluginModelBase.getUnderlyingResource().deleteMarkers(PDEMarkerFactory.MARKER_ID, true, IResource.DEPTH_INFINITE);
		for (IPluginExtension extenstion : pluginModelBase.getExtensions().getExtensions()) {
			extenstion.getModel().getUnderlyingResource().deleteMarkers(PDEMarkerFactory.MARKER_ID, true, IResource.DEPTH_INFINITE);

			// clean only once the plugin.xml file
			break;
		}
	}
}
