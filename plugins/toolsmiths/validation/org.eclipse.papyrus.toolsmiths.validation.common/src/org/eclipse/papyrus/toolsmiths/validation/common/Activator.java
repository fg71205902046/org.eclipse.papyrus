/*****************************************************************************
 * Copyright (c) 2019, 2021 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Nicolas FAUVERGUE (CEA LIST) nicolas.fauvergue@cea.fr - Initial API and implementation
 *   Christian W. Damus - bugs 570097, 575122
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.common;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.papyrus.infra.tools.util.CompositeServiceTracker;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;
import org.eclipse.papyrus.toolsmiths.validation.common.spi.ProjectRulesModelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {

	/**
	 * The plug-in ID.
	 */
	public static final String PLUGIN_ID = "org.eclipse.papyrus.toolsmiths.validation.common"; //$NON-NLS-1$

	/**
	 * The path of the papyrus icon in the bundle.
	 */
	public static final String PAPYRUS_ICON_PATH = "/icons/full/obj16/papyrus.png"; //$NON-NLS-1$

	/**
	 * The shared instance.
	 */
	private static Activator plugin;

	/**
	 * The log helper.
	 */
	public static LogHelper log;

	private ServiceTracker<ProjectRulesModelProvider, ProjectRulesModelProvider> projectRulesProvider;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		log = new LogHelper(this);

		projectRulesProvider = new CompositeServiceTracker<>(context,
				ProjectRulesModelProvider.class, ProjectRulesModelProvider.NULL, ProjectRulesModelProvider::or);
		projectRulesProvider.open();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		projectRulesProvider.close();
		projectRulesProvider = null;

		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 *
	 * @return the shared instance.
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Get the project description for a model object's package.
	 *
	 * @param modelObject
	 *            a model object
	 * @return its package's project description, or {@code null} if none is registered
	 *
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public final ProjectDescription getProjectDescription(EObject modelObject) {
		ResourceSet rset = (modelObject == null) ? null : EMFHelper.getResourceSet(modelObject);
		return rset == null ? null : projectRulesProvider.getService().getProjectDescription(modelObject.eClass().getEPackage(), rset);
	}

}
