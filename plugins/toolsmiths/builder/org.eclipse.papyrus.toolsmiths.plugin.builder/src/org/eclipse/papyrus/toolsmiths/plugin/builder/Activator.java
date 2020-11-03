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
 *   Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Initial API and implementation
 *   Remi Schnekenburger (EclipseSource) - Bug 568495
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder;

import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.eclipse.papyrus.toolsmiths.plugin.builder"; //$NON-NLS-1$

	/**
	 * The path of the papyrus icon in the project
	 */
	public static final String PAPYRUS_ICON_PATH = "/icons/papyrus.png"; //$NON-NLS-1$

	/**
	 * The log
	 */
	public static LogHelper log;

	/**
	 * The shared instance
	 */
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		log = new LogHelper(this);

		// model builder
		PapyrusPluginBuilder.addModelBuilder(new GenericEMFModelBuilder());
		PapyrusPluginBuilder.addModelBuilder(new ArchitectureModelBuilder());
		PapyrusPluginBuilder.addModelBuilder(new ElementTypesConfigurationBuilder());
		PapyrusPluginBuilder.addModelBuilder(new XWTModelBuilder());

		// manifest builder
		PapyrusPluginBuilder.addManifestBuilder(new ManifestBuilder());
		PapyrusPluginBuilder.addManifestBuilder(new StaticProfileManifestBuilder());

		// extension builder
		PapyrusPluginBuilder.addPluginBuilder(new StaticProfileExtensionsBuilder());
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
