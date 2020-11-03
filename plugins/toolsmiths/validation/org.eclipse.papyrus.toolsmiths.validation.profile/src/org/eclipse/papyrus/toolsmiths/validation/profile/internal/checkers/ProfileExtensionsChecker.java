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

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.ProjectManagementService;
import org.eclipse.papyrus.toolsmiths.validation.profile.internal.messages.Messages;
import org.eclipse.pde.internal.core.builders.DefaultSAXParser;
import org.eclipse.uml2.uml.Profile;

/**
 * This class allows to check the extensions of the 'plugin.xml' needed for the profiles.
 */
@SuppressWarnings("restriction")
public class ProfileExtensionsChecker implements IPluginChecker {

	/**
	 * The current project resource.
	 */
	private final IProject project;

	/**
	 * The file of the UML profile.
	 */
	private final IFile profileFile;

	/**
	 * The existing profiles in the UML file.
	 */
	private final Collection<Profile> existingProfiles;

	/**
	 * Constructor.
	 *
	 * @param project
	 *            The current project to check.
	 * @param profileFile
	 *            The file of the UML profile.
	 * @param existingProfiles
	 *            The existing profiles in the UML file.
	 */
	public ProfileExtensionsChecker(final IProject project, final IFile profileFile, final Collection<Profile> existingProfiles) {
		this.project = project;
		this.profileFile = profileFile;
		this.existingProfiles = existingProfiles;
	}

	/**
	 * This allows to check the extensions of the profile.
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker#check(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void check(final IProgressMonitor monitor) {

		if (monitor != null && monitor.isCanceled()) {
			return;
		}

		final IFile pluginXML = ProjectManagementService.getPluginXMLFile(project);
		monitor.subTask(NLS.bind(Messages.StaticProfileExtensionsBuilder_subTask_checkingFile, profileFile));
		for (Profile profile : existingProfiles) {
			StaticProfilePluginErrorReporter reporter = new StaticProfilePluginErrorReporter(pluginXML, profile, profileFile);
			DefaultSAXParser.parse(pluginXML, reporter);
			reporter.validateContent(monitor);
		}
		if (null != monitor) {
			monitor.worked(1);
		}
	}

}
