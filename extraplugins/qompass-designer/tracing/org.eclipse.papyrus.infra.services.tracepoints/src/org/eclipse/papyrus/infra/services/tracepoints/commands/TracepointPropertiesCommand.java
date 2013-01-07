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
 *  Ansgar Radermacher (CEA LIST) - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.infra.services.tracepoints.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.papyrus.infra.services.tracepoints.TracepointConstants;
import org.eclipse.papyrus.infra.services.tracepoints.dialogs.TraceActionSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;


public class TracepointPropertiesCommand extends AbstractTracepointCommand {

	public TracepointPropertiesCommand(EObject selectedElement) {
		super("Tracepoint properties", TransactionUtil.getEditingDomain(selectedElement), selectedElement);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
	{
		updateResourceAndURI();
		selectTraceActions();
		return null;
	}

	protected void selectTraceActions() {
		IMarker marker = findMarker(TracepointConstants.tpOrbpMarker);
		if(marker == null) {
			marker = toggleMarker();
		}
		TraceActionSelection tad = new TraceActionSelection(new Shell(), marker, (Element)selectedElement);
		tad.open();
		if(tad.getReturnCode() == IDialogConstants.OK_ID) {
			Object[] result = tad.getResult();
			int traceAction = (Integer)result[0];
			String traceMechanism = (String)result[1];
			try {
				marker.setAttribute(TracepointConstants.traceAction, traceAction);
				marker.setAttribute(TracepointConstants.traceMechanism, traceMechanism);
			} catch (CoreException e) {
			}
		}
	}
}
