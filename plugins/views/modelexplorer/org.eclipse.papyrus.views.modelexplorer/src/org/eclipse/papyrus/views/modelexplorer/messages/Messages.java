/*****************************************************************************
 * Copyright (c) 2017 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Thanh Liem PHAN (ALL4TEC) thanhliem.phan@all4tec.net - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.views.modelexplorer.messages;

import org.eclipse.osgi.util.NLS;

/**
 * Messages class for the plugin org.eclipse.papyrus.views.modelexplorer.
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.papyrus.views.modelexplorer.messages.messages"; //$NON-NLS-1$

	public static String RenameLabelCommand_DialogMessage;

	static {
		// Initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
