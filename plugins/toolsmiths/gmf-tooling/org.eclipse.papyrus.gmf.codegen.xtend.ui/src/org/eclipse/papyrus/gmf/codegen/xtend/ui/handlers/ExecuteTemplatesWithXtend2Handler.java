/*******************************************************************************
* Copyright (c) 2013, 2020 Montages A.G., CEA LIST, Artal
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License 2.0
* which accompanies this distribution, and is available at
* https://www.eclipse.org/legal/epl-2.0/
*
* Contributors:
*  	Guillaume Hillairet (Montages A.G.) : initial implementation
*    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
*****************************************************************************/
package org.eclipse.papyrus.gmf.codegen.xtend.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;


public class ExecuteTemplatesWithXtend2Handler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ExecuteTemplatesOperationWithXtend2 op = createOperation(event);
		op.run();
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	private ExecuteTemplatesOperationWithXtend2 createOperation(ExecutionEvent event) throws ExecutionException {
		ExecuteTemplatesOperationWithXtend2 op = new ExecuteTemplatesOperationWithXtend2();
		try{
			op.setName(event.getCommand().getName());
		} catch(NotDefinedException e) {
			throw new RuntimeException("Undefined command",e);
		}
		Shell shell = HandlerUtil.getActiveShell(event);
		op.setShell(shell);
		if (HandlerUtil.getCurrentSelectionChecked(event) instanceof TreeSelection) {
			TreeSelection selection = (TreeSelection)HandlerUtil.getCurrentSelectionChecked(event);
			if (selection.size() == 1) {
				if (selection.getFirstElement() instanceof GenEditorGenerator) {
					op.setGenModelURI(((XMIResource)selection.getPaths()[0].getFirstSegment()).getURI());
				} else {
					op.setGenModelURI(URI.createPlatformPluginURI(((IFile)selection.getFirstElement()).getFullPath().toString(), true));
				}
			} else {
				throw new ExecutionException("Size of selection more than one.");
			}
		}
		return op;
	}
}
	
