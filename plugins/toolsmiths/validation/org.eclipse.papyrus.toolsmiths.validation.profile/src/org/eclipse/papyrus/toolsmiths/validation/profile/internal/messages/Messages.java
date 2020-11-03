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
 *   remi - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.profile.internal.messages;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.papyrus.toolsmiths.validation.profile.internal.messages.messages"; //$NON-NLS-1$
	public static String StaticProfileExtensionsBuilder_nsURI_differs;
	public static String StaticProfileExtensionsBuilder_subTask_checkingFile;
	public static String StaticProfilePluginErrorReporter_noEcoreGeneratedPackageFound;
	public static String StaticProfilePluginErrorReporter_NoPapyrusProfileExtensionFound;
	public static String StaticProfilePluginErrorReporter_noUML2GeneratedPackage;
	public static String StaticProfilePluginErrorReporter_uiLabelIsNull;
	public static String StaticProfilePluginErrorReporter_wrongLocationForProfile;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
