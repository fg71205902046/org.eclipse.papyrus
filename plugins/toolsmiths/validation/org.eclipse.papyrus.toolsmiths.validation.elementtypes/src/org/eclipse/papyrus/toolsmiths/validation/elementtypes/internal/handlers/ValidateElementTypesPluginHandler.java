/*****************************************************************************
 * Copyright (c) 2019, 2020 CEA LIST, Christian W. Damus, and others.
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
 *   Christian W. Damus - bug 569357
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.elementtypes.internal.handlers;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.toolsmiths.validation.elementtypes.Activator;
import org.eclipse.papyrus.toolsmiths.validation.elementtypes.checkers.ElementTypesPluginCheckerService;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * This handler allows to validate papyrus element types plug-ins.
 */
public class ValidateElementTypesPluginHandler extends AbstractHandler {

	/**
	 * This allows to validate a plugin which contains papyrus element types definition.
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection == null || !(selection instanceof StructuredSelection) || selection.isEmpty()) {
			return null;
		}

		// Get the shell to manage the validation in an UI
		final Shell shell = HandlerUtil.getActiveShell(event);

		try {
			new ProgressMonitorDialog(shell).run(true, true, monitor -> {
				final StructuredSelection structuredSelection = (StructuredSelection) selection;
				monitor.beginTask("Validate Element Types", structuredSelection.size());
				for (final Object selectedElement : structuredSelection.toList()) {
					if (monitor.isCanceled()) {
						return;
					}
					if (selectedElement instanceof IProject) {
						final IProject project = (IProject) selectedElement;
						ElementTypesPluginCheckerService.checkElementTypesPlugin(project, SubMonitor.convert(monitor));
					}
					SubMonitor.done(monitor);
				}
			});
		} catch (InvocationTargetException e) {
			Activator.log.error(e);
		} catch (InterruptedException e) {
			// Do nothing, just cancelled by user
		}

		return null;
	}

}
