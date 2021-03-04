/*****************************************************************************
* Copyright (c) 2006, 2020 Borland Software Corporation, CEA LIST, ARTAL
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License 2.0
* which accompanies this distribution, and is available at
* https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
 *   Etienne ALLOGO (ARTAL) - Initial API and implementation
 *   Etienne ALLOGO (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : PapyrusGmfExtension epackage merge into gmfgen
*****************************************************************************/
package org.eclipse.papyrus.gmf.codegen.xtend.ui.handlers;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.gmf.codegen.util.PapyrusGMFExtensionMigrator;
import org.eclipse.papyrus.gmf.codegen.xtend.ui.Activator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Migrates the data formerly contained in the "papyrusgmfextension" using {@link PapyrusGMFExtensionMigrator}.
 * The migration of gmfgen files is performed via a new context menu "Papyrus Developer> Migrate GMF Gen Papyrus extensions" by selecting the gmfgen file in the "Project Explorer" view
 * 
 * @author allogo
 *
 */
public class MigrateGMFGenFileHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (!(currentSelection instanceof IStructuredSelection) || currentSelection.isEmpty()) {
			return null;
		}
		// resolve selected file -project explorer)
		final IStructuredSelection selection = (IStructuredSelection) currentSelection;
		Object selectedElement = selection.getFirstElement();

		if (selectedElement instanceof IFile) {

			// load emf respurce
			IFile selectedFile = (IFile) selectedElement;
			String selectedFilePath = selectedFile.getFullPath().toString();
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource inputResource = resourceSet.getResource(URI.createURI(selectedFilePath), true);
			// create migrator
			PapyrusGMFExtensionMigrator migrator = new PapyrusGMFExtensionMigrator();

			// iterate on all resource (from roots)
			Object[] roots = inputResource.getContents().toArray();
			for (Object root : roots) {
				// migrate recursivelly
				migrator.migrate((EObject) root);
			}
			try {
				inputResource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				// notify and log on error
				MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error on file conversion", e.getMessage()); //$NON-NLS-1$
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error on file conversion", e)); //$NON-NLS-1$
			}

		}
		return null;
	}
}

