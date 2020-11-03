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
 *   Remi Schnekenburger - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.toolsmiths.plugin.builder.helper.StaticProfileHelper;
import org.eclipse.papyrus.toolsmiths.validation.profile.internal.checkers.ProfileBuildChecker;
import org.eclipse.papyrus.toolsmiths.validation.profile.internal.checkers.ProfileDependenciesChecker;
import org.eclipse.uml2.uml.Profile;

/**
 * Builder that checks manifest for static profiles within a Papyrus plugin.
 */
public class StaticProfileManifestBuilder extends AbstractPapyrusBuilder {

	@Override
	public IProject[] build(IProject builtProject, PapyrusPluginBuilder papyrusBuilder, int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		if (papyrusBuilder.isInterrupted() || monitor.isCanceled()) {
			return null;
		}

		// check static profile presence?
		Map<IFile, List<Profile>> profiles = StaticProfileHelper.findStaticProfiles(builtProject, false);

		if (profiles.entrySet().isEmpty()) {
			return null;
		}

		// retrieve information from plugin extensions, warning, this iterates several time on the same parser with different file. Check that clean does not happen between various profiles
		for (Entry<IFile, List<Profile>> entry : profiles.entrySet()) {
			Resource resource = entry.getValue().size() > 0 ? entry.getValue().get(0).eResource() : null;
			if (resource == null) {
				return null;
			}
			ProfileDependenciesChecker profileDependenciesChecker = new ProfileDependenciesChecker(builtProject, entry.getKey(), resource);
			profileDependenciesChecker.check(monitor);

			ProfileBuildChecker profileBuildChecker = new ProfileBuildChecker(builtProject, entry.getKey());
			profileBuildChecker.check(monitor);
		}

		return new IProject[] { builtProject };
	}

}
