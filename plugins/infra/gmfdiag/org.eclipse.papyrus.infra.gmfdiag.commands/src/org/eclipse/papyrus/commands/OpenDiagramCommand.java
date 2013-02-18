/*****************************************************************************
 * Copyright (c) 2010 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Mathieu Velten (Atos Origin) mathieu.velten@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageManager;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForEObject;

/**
 * The Class OpenDiagramCommand.
 */
public class OpenDiagramCommand extends AbstractCommand {

	/** The diagram to open. */
	private EObject diagramToOpen = null;

	private ICommand previousCreateDiagramCommand = null;

	private boolean didOpen;

	/**
	 * Instantiates a new open diagram command.
	 * 
	 * @param domain
	 *        the domain
	 * @param diagram
	 *        the diagram
	 */
	public OpenDiagramCommand(EObject diagram) {
		super("Open diagram", null);
		diagramToOpen = diagram;
	}

	public OpenDiagramCommand(ICommand previousCreateDiagramCommand) {
		super("Open diagram", null);
		this.previousCreateDiagramCommand = previousCreateDiagramCommand;
	}

	/**
	 * {@inheritedDoc}
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		try {
			if(diagramToOpen == null && previousCreateDiagramCommand != null) {
				Object possibleDiagramToOpen = previousCreateDiagramCommand.getCommandResult().getReturnValue();
				if(possibleDiagramToOpen instanceof EObject) {
					diagramToOpen = (EObject)possibleDiagramToOpen;
				}
			}

			if(diagramToOpen != null) {
				IPageManager pageManager;

				// bug 358799
				// The command is also called during the initialiation phase. In this case, the
				// EditorsUtils.getServiceRegistry() method return the wrong ServiceREgistry.
				// Disable this call, and use the more costly way to get the IPageMngr
				// TODO : provide a better way of getting the IPageMngr.
				/*
				 * final ServicesRegistry serviceRegistry = EditorUtils.getServiceRegistry();
				 * if(serviceRegistry != null) {
				 * pageMngr =serviceRegistry.getService(IPageMngr.class);
				 * } else
				 */
				pageManager = ServiceUtilsForEObject.getInstance().getIPageManager(diagramToOpen);

				if(pageManager.isOpen(diagramToOpen)) {
					pageManager.selectPage(diagramToOpen);
					didOpen = false;
				} else {
					pageManager.openPage(diagramToOpen);
					didOpen = true;
				}
			}

			return CommandResult.newOKCommandResult();
		} catch (Exception e) {
			throw new ExecutionException("Can't open diagram", e);
		}
	}

	@Override
	protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
		return doExecuteWithResult(progressMonitor, info);
	}

	@Override
	protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
		try {
			IPageManager pageManager = ServiceUtilsForEObject.getInstance().getIPageManager(diagramToOpen);

			if(didOpen) {
				pageManager.closePage(diagramToOpen);
			}

			return CommandResult.newOKCommandResult();
		} catch (ServiceException ex) {
			return CommandResult.newErrorCommandResult(ex);
		}
	}
}
