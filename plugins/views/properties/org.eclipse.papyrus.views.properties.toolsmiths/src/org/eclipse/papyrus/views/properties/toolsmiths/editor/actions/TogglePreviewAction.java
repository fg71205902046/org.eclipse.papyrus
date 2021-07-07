/*****************************************************************************
 * Copyright (c) 2011, 2021 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *  Christian W. Damus - bug 573987
 *****************************************************************************/
package org.eclipse.papyrus.views.properties.toolsmiths.editor.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.papyrus.views.properties.toolsmiths.Activator;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;

/**
 * An action to toggle the UIEditor Preview
 *
 * @author Camille Letavernier
 */
public class TogglePreviewAction extends AbstractHandler {

	private static final String STATE_ID = "org.eclipse.ui.commands.toggleState"; //$NON-NLS-1$
	private static final String COMMAND_ID = "org.eclipse.papyrus.customization.properties.TogglePreview"; //$NON-NLS-1$

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage activePage = HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage();
		if (activePage == null) {
			return null;
		}

		IViewReference viewReference = activePage.findViewReference(Activator.PREVIEW_ID);

		try {
			if (viewReference == null) {
				activePage.showView(Activator.PREVIEW_ID);
			} else {
				activePage.hideView((ViewPart) viewReference.getPart(false));
			}
		} catch (PartInitException ex) {
			Activator.log.error(ex);
		}

		return null;
	}

	@Override
	public void setEnabled(Object evaluationContext) {
		super.setEnabled(evaluationContext);

		IWorkbenchWindow activeWindow = (IWorkbenchWindow) HandlerUtil.getVariable(evaluationContext, ISources.ACTIVE_WORKBENCH_WINDOW_NAME);
		IWorkbenchPage activePage = activeWindow == null ? null : activeWindow.getActivePage();
		if (activePage == null) {
			return;
		}

		ICommandService commandService = activeWindow.getService(ICommandService.class);
		Command command = commandService.getCommand(COMMAND_ID);

		State state = (command == null) ? null : command.getState(STATE_ID);
		if (state == null) {
			return;
		}

		IViewReference viewReference = activePage.findViewReference(Activator.PREVIEW_ID);
		state.setValue(viewReference != null);
	}

}
