/*******************************************************************************
 * Copyright (c) 2008 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *     Tatiana Fesenko(CEA) - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.wizards.pages;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.wizards.Activator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * This WizardPage can create an empty .uml2 file for the PapyrusEditor.
 * 
 * @author <a href="mailto:jerome.benois@obeo.fr">Jerome Benois</a>
 */
public class NewModelFilePage extends WizardNewFileCreationPage {

	/** The Constant DEFAULT_NAME. */
	public static final String DEFAULT_NAME = "model";

	/** The Constant DIAGRAM_EXTENSION. */
	public static final String DEFAULT_DIAGRAM_EXTENSION = "di";

	/** The Constant PAGE_ID. */
	public static final String PAGE_ID = "NewPapyrusModel";

	/**
	 * Instantiates a new new model file page.
	 * 
	 * @param selection
	 *        the selection
	 */
	public NewModelFilePage(IStructuredSelection selection) {
		super(PAGE_ID, selection);
		setTitle("Create a new Papyrus model");
		setDescription("Create a new empty Papyrus model");
		setFileExtension(DEFAULT_DIAGRAM_EXTENSION);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		setFileName(getUniqueFileName(getContainerFullPath(), getFileName(), getFileExtension()));
		setPageComplete(validatePage());
	}

	public IStatus diagramExtensionChanged(String newExtension) {
		String currentExtension = getFileExtension();
		if(!currentExtension.equals(newExtension)) {

			String oldFileName = getFileName();
			String newFileName = NewModelFilePage.getUniqueFileName(getContainerFullPath(), getFileName(), newExtension);

			setFileName(newFileName);
			setFileExtension(newExtension);

			String message = String.format("The new diagram category requires a specific diagram file extension. " + "Thus, the diagram file has been renamed from %s to %s ", oldFileName, newFileName);
			Status resultStatus = new Status(Status.INFO, Activator.PLUGIN_ID, message);

			String errorMessage = getErrorMessage();
			if(errorMessage != null) {
				resultStatus = new Status(Status.ERROR, Activator.PLUGIN_ID, errorMessage);
			}
			return resultStatus;
		}
		return Status.OK_STATUS;
	}


	/**
	 * Gets the unique file name.
	 * 
	 * @param containerFullPath
	 *        the container full path
	 * @param fileName
	 *        the file name
	 * @param extension
	 *        the extension
	 * @return the unique file name
	 */
	protected static String getUniqueFileName(IPath containerFullPath, String fileName, String extension) {
		if(extension == null) {
			extension = "";
		}

		if(containerFullPath == null) {
			containerFullPath = new Path(""); //$NON-NLS-1$
		}
		if(fileName == null || fileName.trim().length() == 0) {
			fileName = DEFAULT_NAME;
		}

		if(fileName.contains(".")) {
			fileName = fileName.substring(0, fileName.indexOf("."));
		}

		IPath filePath = containerFullPath.append(fileName);
		filePath = containerFullPath.append(fileName);
		filePath = filePath.addFileExtension(extension);

		int i = 1;
		while(ResourcesPlugin.getWorkspace().getRoot().exists(filePath)) {
			i++;
			filePath = containerFullPath.append(fileName + i);
			if(extension != null) {
				filePath = filePath.addFileExtension(extension);
			}
		}
		return filePath.lastSegment();
	}

}
