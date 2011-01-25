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
package org.eclipse.papyrus.properties.extensions;

import java.io.IOException;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.papyrus.properties.Activator;
import org.eclipse.papyrus.properties.runtime.ConfigurationManager;

public class EnvironmentExtensionPoint {

	private final String EXTENSION_ID = "org.eclipse.papyrus.properties.environment"; //$NON-NLS-1$

	public EnvironmentExtensionPoint() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID);

		for(IConfigurationElement e : config) {
			final String environmentResource = e.getAttribute("environmentModel"); //$NON-NLS-1$
			URI uri = URI.createPlatformPluginURI(e.getContributor().getName() + "/" + environmentResource, true); //$NON-NLS-1$
			try {
				ConfigurationManager.instance.addEnvironment(uri);
			} catch (IOException ex) {
				Activator.log.error("The plugin " + e.getContributor() + " contributed an invalid " + "extension for " + EXTENSION_ID, ex); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
			}
		}
	}
}
