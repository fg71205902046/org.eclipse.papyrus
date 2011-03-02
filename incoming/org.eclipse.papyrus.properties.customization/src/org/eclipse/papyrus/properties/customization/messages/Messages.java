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
package org.eclipse.papyrus.properties.customization.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.papyrus.properties.customization.messages.messages"; //$NON-NLS-1$

	public static String CustomizationDialog_cancel;

	public static String CustomizationDialog_cannotDeletePluginContext;

	public static String CustomizationDialog_configurationName;

	public static String CustomizationDialog_configurationNameNotEmpty;

	public static String CustomizationDialog_configurationWithSameNameExists;

	public static String CustomizationDialog_copy;

	public static String CustomizationDialog_copyOf;

	public static String CustomizationDialog_createNewCopyByCopy;

	public static String CustomizationDialog_customization;

	public static String CustomizationDialog_delete;

	public static String CustomizationDialog_deleteContext;

	public static String CustomizationDialog_deleteContextConfirmation1;

	public static String CustomizationDialog_deleteContextConfirmation2;

	public static String CustomizationDialog_edit;

	public static String CustomizationDialog_editSelectedContext;

	public static String CustomizationDialog_enterConfigurationName;

	public static String CustomizationDialog_no;

	public static String CustomizationDialog_plugin;

	public static String CustomizationDialog_removeSelectedContext;

	public static String CustomizationDialog_selectContextToEdit;

	public static String CustomizationDialog_yes;

	public static String InputDialog_enterConfigurationName;

	public static String Preview_disablePreview;

	public static String Preview_noSelectedView;

	public static String Preview_preview;

	public static String Preview_previewIsDisabled;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
