/******************************************************************************
 * Copyright (c) 2005, 2020 Borland Software Corporation, CEA LIST, Artal
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *    Artem Tikhomirov (Borland) - initial API and implementation
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *****************************************************************************/
package org.eclipse.papyrus.gmf.internal.codegen;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.papyrus.gmf.codegen.util.CodegenEmitters;
import org.eclipse.papyrus.gmf.codegen.util.EmitterSource;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class CodeGenUIPlugin extends AbstractUIPlugin {

	private static CodeGenUIPlugin plugin;

	private EmitterSource<GenEditorGenerator, CodegenEmitters> emitterSource;

	public CodeGenUIPlugin() {
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		if (emitterSource != null) {
			emitterSource.dispose();
			emitterSource = null;
		}
		super.stop(context);
		plugin = null;
	}

	public static CodeGenUIPlugin getDefault() {
		return plugin;
	}

	public static String getBundleString(String key) {
		return Platform.getResourceBundle(getDefault().getBundle()).getString(key);
	}

	public static String getBundleString(String key, Object[] args) {
		String val = getBundleString(key);
		if (val == null) {
			return key;
		}
		return MessageFormat.format(val, args);
	}

	public static IStatus createStatus(int statusCode, String message, Exception ex) {
		return new Status(statusCode, getPluginID(), 0, message, ex);
	}

	public static IStatus createError(String message, Exception ex) {
		return createStatus(IStatus.ERROR, message, ex);
	}

	public static IStatus createWarning(String message) {
		return createStatus(IStatus.WARNING, message, null);
	}

	public static IStatus createInfo(String message) {
		return createStatus(IStatus.INFO, message, null);
	}

	public static String getPluginID() {
		return getDefault().getBundle().getSymbolicName();
	}

	public CodegenEmitters getEmitters(GenEditorGenerator genModel) {
		if (emitterSource == null) {
			emitterSource = new EmitterSource<GenEditorGenerator, CodegenEmitters>() {
				@Override
				protected CodegenEmitters newEmitters(GenEditorGenerator genModel) {
					return new CodegenEmitters(!genModel.isDynamicTemplates(), genModel.getTemplateDirectory(), genModel.getModelAccess() != null);
				}
			};
		}
		return emitterSource.getEmitters(genModel, genModel.isDynamicTemplates());
	}
}
