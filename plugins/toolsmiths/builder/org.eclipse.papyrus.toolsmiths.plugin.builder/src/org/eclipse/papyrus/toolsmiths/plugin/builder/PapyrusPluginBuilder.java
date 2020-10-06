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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.papyrus.toolsmiths.plugin.builder.preferences.PluginBuilderPreferencesConstants;

/**
 * The main Papyrus builder in charge to delegate build to sub-builder
 */
public class PapyrusPluginBuilder extends IncrementalProjectBuilder {

	/**
	 * the id of the builder
	 */
	public static final String BUILDER_ID = "org.eclipse.papyrus.plugin.builder"; //$NON-NLS-1$

	private static final List<AbstractPapyrusBuilder> modelBuilders = new ArrayList<>();

	private static final List<AbstractPapyrusBuilder> pluginBuilders = new ArrayList<>();

	private static final List<AbstractPapyrusBuilder> manifestBuilders = new ArrayList<>();

	public static final void addModelBuilder(final AbstractPapyrusBuilder modelBuilder) {
		modelBuilders.add(modelBuilder);
	}

	public static final void addPluginBuilder(final AbstractPapyrusBuilder pluginBuilder) {
		pluginBuilders.add(pluginBuilder);
	}

	public static final void addManifestBuilder(final AbstractPapyrusBuilder pluginBuilder) {
		manifestBuilders.add(pluginBuilder);
	}

	/**
	 *
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder#build(int, java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 *
	 * @param kind
	 * @param args
	 * @param monitor
	 * @return
	 * @throws CoreException
	 */
	@Override
	protected IProject[] build(final int kind, final Map<String, String> args, final IProgressMonitor monitor)
			throws CoreException {
		if (getProject() == null) {
			return null;
		}
		// remove all previously created marker
		clean(monitor);

		if (!isPapyrusPluginBuilderActivated()) {
			return null;
		}

		// TODO : we also remove all java marker
		// getProject().deleteMarkers(IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER, true, -1);

		final List<IProject> wantedDeltaProjects = new ArrayList<>();

		if (isPapyrusModelBuilderActivated()) {
			for (final AbstractPapyrusBuilder builder : PapyrusPluginBuilder.modelBuilders) {
				IProject[] projects = builder.build(getProject(), this, kind, args, monitor);
				if (projects != null && projects.length != 0) {
					wantedDeltaProjects.addAll(Arrays.asList(projects));
				}
			}
		}

		if (isPapyrusManifestBuilderActivated()) {
			for (final AbstractPapyrusBuilder builder : PapyrusPluginBuilder.manifestBuilders) {
				IProject[] projects = builder.build(getProject(), this, kind, args, monitor);
				if (projects != null && projects.length != 0) {
					wantedDeltaProjects.addAll(Arrays.asList(projects));
				}
			}
		}

		for (final AbstractPapyrusBuilder builder : PapyrusPluginBuilder.pluginBuilders) {
			IProject[] projects = builder.build(getProject(), this, kind, args, monitor);
			if (projects != null && projects.length != 0) {
				wantedDeltaProjects.addAll(Arrays.asList(projects));
			}
		}

		return wantedDeltaProjects.toArray(new IProject[wantedDeltaProjects.size()]);
	}

	/**
	 *
	 * @return
	 *         <code>true</code> if the Papyrus Plugin Builder is activated
	 */
	protected boolean isPapyrusPluginBuilderActivated() {
		Boolean result = Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.ACTIVATE_MAIN_PAPYRUS_BUILDER);
		return result.booleanValue();
	}

	/**
	 *
	 * @return
	 *         <code>true</code> if the Papyrus Model Builder is activated
	 */
	protected boolean isPapyrusModelBuilderActivated() {
		boolean result = Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.ACTIVATE_PAPYRUS_MODEL_BUILDER);
		if (result) {
			// we get sub preference too, to avoid to cross and call builder to do nothing
			result = Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.PAPYRUS_MODEL_BUILDER_CHECK_MODEL_DEPENDENCIES)
					|| Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.PAPYRUS_MODEL_BUILDER_VALIDATE_MODEL);
		}
		return result;
	}

	/**
	 *
	 * @return
	 *         <code>true</code> if the Papyrus Manifest Builder is activated
	 */
	protected boolean isPapyrusManifestBuilderActivated() {
		boolean result = Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.ACTIVATE_PAPYRUS_MANIFEST_BUILDER);

		if (result) {
			// we get sub preference too, to avoid to cross and call builder to do nothing
			result = Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.PAPYRUS_MANIFEST_BUILDER_CHECK_DEPENDENCY_RANGE)
					|| Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.PAPYRUS_MANIFEST_BUILDER_CHECK_NO_REEXPORT);
		}
		return result;
	}

}