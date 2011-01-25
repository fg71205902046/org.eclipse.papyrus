/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.widgets;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.papyrus.log.LogHelper;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.eclipse.papyrus.widgets"; //$NON-NLS-1$

	/**
	 * The shared instance
	 */
	private static Activator plugin;

	/**
	 * The logger for this plugin
	 */
	public static LogHelper log;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		log = new LogHelper(plugin);
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/**
	 * Returns the image from the given path
	 * 
	 * @param path
	 *        the path of the image to be displayed
	 * @return the image found
	 */
	public static Image getImage(String path) {
		return getExternalImage(PLUGIN_ID, path);
	}

	/**
	 * Search the specified plugin and returns the image from the given path
	 * 
	 * @param pluginId
	 *        The plugin in which the image is located
	 * @param path
	 *        the path of the image to be displayed
	 * @return
	 *         The image from the given path in the given plugin
	 */
	public static Image getExternalImage(String pluginId, String path) {
		final ImageRegistry registry = getDefault().getImageRegistry();
		Image image = registry.get(path);
		if(image == null) {
			registry.put(path, AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, path));
			image = registry.get(path);
		}
		return image;
	}

}
