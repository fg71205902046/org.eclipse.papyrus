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
package org.eclipse.papyrus.toolsmiths.plugin.builder;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.papyrus.emf.helpers.ProjectDependencyHelper;

/**
 * Abstract Builder used in Papyrus
 */
public abstract class AbstractPapyrusBuilder {

	private ProjectDependencyHelper DEPENDENCY_HELPER = ProjectDependencyHelper.INSTANCE;

	/**
	 *
	 * @param builtProject
	 *            the current build project
	 * @param papyrusBuilder
	 *            the main papyrus builder
	 * @param kind
	 *            the kind of build (see {@link IncrementalProjectBuilder#build} method for more details)
	 * @param args
	 *            the args of the build (see {@link IncrementalProjectBuilder#build} method for more details)
	 * @param monitor
	 *            the progress monitor (see {@link IncrementalProjectBuilder#build} method for more details)
	 * @return
	 *         the projects for which we want delta informations for the next build (see {@link IncrementalProjectBuilder#build} method for more details)
	 * @throws CoreException
	 */
	public abstract IProject[] build(IProject builtProject, final PapyrusPluginBuilder papyrusBuilder, int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException;

	/**
	 *
	 * @param res
	 *            the resource to mark with an error
	 * @param message
	 *            the error message
	 * @return
	 *         the created marker
	 */
	protected IMarker createErrorMarker(final IResource res, final String message) {
		// use this type to appears as java error
		// later, we can create our own type, with a specific handler to open our own error dialog appearing during the launching of a new Eclipse runtime
		IMarker marker = null;
		try {
			marker = res.createMarker(IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER);

			marker.setAttribute(IMarker.MESSAGE, "Papyrus Builder: " + message);
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			marker.setAttribute("code", 10000);
			marker.setAttribute(IMarker.SOURCE_ID, Activator.PLUGIN_ID);
			marker.setAttribute(IJavaModelMarker.ID, 7500);
		} catch (CoreException e) {
			Activator.log.error(e);
		}
		return marker;
	}

	/**
	 *
	 * @param project
	 *            a project
	 * @return
	 *         the list of current declared dependencies in the project
	 */
	protected Collection<String> getAllAvailableDependencies(final IProject project) {
		return DEPENDENCY_HELPER.getAllAvailableDependencies(project.getName());
	}
}
