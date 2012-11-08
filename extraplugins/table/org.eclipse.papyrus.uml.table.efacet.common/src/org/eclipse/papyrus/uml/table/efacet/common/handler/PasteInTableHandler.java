/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.table.efacet.common.handler;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.facet.widgets.table.ui.internal.exported.ITableWidget;
import org.eclipse.emf.facet.widgets.table.ui.internal.exported.ITableWidgetProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.infra.core.sasheditor.editor.ISashWindowsContainer;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.infra.emf.dialog.CommandCreationProgressMonitorDialog;
import org.eclipse.papyrus.infra.table.efacet.common.editor.AbstractTableEditor;
import org.eclipse.papyrus.infra.table.efacet.metamodel.papyrustable.PapyrusTable;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.builders.NotificationBuilder;
import org.eclipse.papyrus.uml.table.efacet.common.Activator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;

public class PasteInTableHandler extends AbstractHandler {

	/**
	 * this field is used to determine if we want open a dialog to prevent the user that the command creation and the command execution can take a
	 * long time
	 */
	private boolean useProgressMonitorDialog = true;

	private final IPasteInTableCommandProvider provider = new PasteInPapyrusTableCommandProvider();

	/**
	 *
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 *
	 * @param event
	 * @return
	 * @throws ExecutionException
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final PapyrusTable papyrusTable = getPapyrusTable();
		if(papyrusTable != null) {
			final String contents = getClipboardContents();
			Command createdCommand;
			this.useProgressMonitorDialog = false;
			int returnCode = Window.OK;
			if(this.useProgressMonitorDialog) {//we create the command using a progress monitor
				final CommandCreationProgressMonitorDialog commandCreationDialog = new CommandCreationProgressMonitorDialog(Display.getCurrent().getActiveShell());
				final ProgressMonitorDialog commandExecutionProgressMonitor = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
				commandCreationDialog.getProgressMonitor().setTaskName("Paste creation...");
				try {
					commandCreationDialog.run(true, true, new IRunnableWithProgress() {

						public void run(final IProgressMonitor cancelProvider) throws InvocationTargetException, InterruptedException {
							final Command cmd = PasteInTableHandler.this.provider.getPasteFromFromStringCommand(papyrusTable, cancelProvider, commandExecutionProgressMonitor, contents, getITableWidget());
							commandCreationDialog.setCreatedCommand(cmd);
						}
					});
				} catch (final InvocationTargetException e) {
					Activator.log.error(e);
				} catch (final InterruptedException e) {
					Activator.log.error(e);

				}
				returnCode = commandCreationDialog.getReturnCode();
				createdCommand = commandCreationDialog.getCreatedCommand();
			} else {
				createdCommand = this.provider.getPasteFromFromStringCommand(papyrusTable, null, null, contents, getITableWidget());
			}

			if(returnCode == Window.OK) {
				if((createdCommand != null) && createdCommand.canExecute()) {
					getEditingDomain().getCommandStack().execute(createdCommand);
				} else {
					final String errorMessage = this.provider.getPasteErrorMessage();
					if((errorMessage != null) && !errorMessage.equals("")) {
						NotificationBuilder.createErrorPopup(errorMessage).run();
					}
				}
				//we don't use dialogs to do the paste
			} else if(returnCode == Window.CANCEL) {
				NotificationBuilder.createInfoPopup("Paste Action Canceled").run();
			}
		}
		return null;
	}



	/**
	 *
	 * @return
	 *         the PapyrusTable for the current nested active editor or <code>null</code> if not found
	 */
	private PapyrusTable getPapyrusTable() {
		ISashWindowsContainer container = null;
		try {
			container = ServiceUtilsForActionHandlers.getInstance().getISashWindowsContainer();
		} catch (final ServiceException e) {
			Activator.log.error(e);
		}
		if(container != null) {
			final IEditorPart activeEditor = container.getActiveEditor();
			if(activeEditor instanceof AbstractTableEditor) {
				return (PapyrusTable)activeEditor.getAdapter(PapyrusTable.class);
			}
		}
		return null;
	}

	private ITableWidget getITableWidget() {
//IEditorPaPlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		ISashWindowsContainer container = null;
		try {
			container = ServiceUtilsForActionHandlers.getInstance().getISashWindowsContainer();
		} catch (final ServiceException e) {
			Activator.log.error(e);
		}
		if(container != null) {
			final IEditorPart activeEditor = container.getActiveEditor();
			if(activeEditor instanceof AbstractTableEditor) {
				return ((ITableWidgetProvider)activeEditor.getAdapter(ITableWidgetProvider.class)).getTableWidget();
			}
		}
		return null;
	}

	/**
	 *
	 * @return
	 *         the clipboard contents used to build the command
	 */
	private String getClipboardContents() {
		// examine system clipboard
		String bufferSystem = null;

		//using AWT
		final DataFlavor[] dataFlavors = Toolkit.getDefaultToolkit().getSystemClipboard().getAvailableDataFlavors();
		for(final DataFlavor dataFlavor : dataFlavors) {
			try {
				if(dataFlavor.isFlavorTextType() && dataFlavor.isMimeTypeEqual(DataFlavor.stringFlavor)) {
					bufferSystem = Toolkit.getDefaultToolkit().getSystemClipboard().getData(dataFlavor).toString();
				}
			} catch (final Exception e) {
				Activator.log.error(e);
			}
		}
		//using SWT
		//		final Display display = Display.getCurrent();
		//		final Clipboard cb = new Clipboard(display);
		//		//we use the text transfert
		//		final TextTransfer transfer = TextTransfer.getInstance();
		//
		//		final Clipboard cb = new Clipboard(clipboard);
		//		final String contents = (String)cb.getContents(transfer);


		return bufferSystem;
	}

	/**
	 *
	 * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
	 *
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return this.provider.isPasteEnabled(getPapyrusTable(), getClipboardContents(), getITableWidget());
	}

	/**
	 *
	 * @return
	 *         the editing domain to use to execute the command
	 */
	private TransactionalEditingDomain getEditingDomain() {
		TransactionalEditingDomain domain = null;
		ServicesRegistry serviceRegistry = null;
		try {
			serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
		} catch (final ServiceException e) {
			Activator.log.error("ServicesRegistry not found", e);
		}
		try {
			domain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
		} catch (final ServiceException e) {
			Activator.log.error("Editing Domain not found", e);
		}
		return domain;
	}

	/**
	 * allows to define if we want use dialog to prevent the user that the command creation and the command execution can take a long time
	 *
	 */
	public void setWithProgressMonitorDialog(final boolean useProgressMonitorDialog) {
		this.useProgressMonitorDialog = useProgressMonitorDialog;
	}
}
