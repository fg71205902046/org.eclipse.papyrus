/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) patrick.tessier@cea.fr - Initial API and implementation
 *   
 *****************************************************************************/
package org.eclipse.papyrus.requirements.reqif.test;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.papyrus.junit.utils.EditorUtils;
import org.eclipse.papyrus.junit.utils.PapyrusProjectUtils;
import org.eclipse.papyrus.junit.utils.ProjectUtils;
import org.eclipse.papyrus.requirements.reqif.integration.transformation.ReqIFImporterServiceEdit;
import org.eclipse.papyrus.requirements.reqif.transformation.ReqIFImporter;
import org.eclipse.papyrus.requirements.reqif.user.SampleUser;
import org.eclipse.papyrus.requirements.reqif.user.UserRegistry;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.junit.Assert;
import org.junit.Test;
import org.osgi.framework.Bundle;

public class ReImportModifiedSpecObject extends AbstractReqIFTest {

	/**
	 * Inits this.editor
	 * Fails or throws an exception if an error occurs
	 *
	 * @param bundle
	 *        the source bundle where the model is store
	 * @param projectName
	 *        the project that will created at runtime to execute test
	 * @param modelName
	 *        the model that will be copied and test executed on.
	 */
	@Override
	protected void initModel(String projectName, String modelName, Bundle bundle) throws Exception {
		//	project = ProjectUtils.createProject(projectName);
		this.diModelFile = PapyrusProjectUtils.copyPapyrusModel(project, bundle, getSourcePath(), modelName);
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				try {
					editor = EditorUtils.openPapyrusEditor(diModelFile);
				} catch (Exception ex) {
					//Activator.log.error(ex);
					Assert.fail(ex.getMessage());
				}
			}
		});
		Assert.assertNotNull(editor);
	}

	@Test
	public void addRequirements() {
		//loading
		//now launch a class diagram
		try {
			UserRegistry.instance.setCurrentUser(new SampleUser());
			project = ProjectUtils.createProject("ImportReqIFProject");
			PapyrusProjectUtils.copyIFile(getSourcePath() + "MyRequirementProfile.profile.uml", getBundle(), project, "MyRequirementProfile.profile.uml");
			initModel("ImportReqIFProject", "importSpecObject", getBundle());
			getReqIFFile("specObject_2.reqif");
			openDiagram(editor, "ClassDiagram");
			final DiagramEditPart diagramEditPart = editor.getAdapter(DiagramEditPart.class);
			Assert.assertNotNull("The diagram must be opened", diagramEditPart);
			RecordingCommand cmd = new RecordingCommand(getTransactionalEditingDomain(), "importReqIF") {

				@Override
				protected void doExecute() {
					//launch import
					Package root = (Package)diagramEditPart.resolveSemanticElement();
					ReqIFImporter sysMLReqIFTransfomation = new ReqIFImporterServiceEdit(domain, importedReqIFModel, root);
					sysMLReqIFTransfomation.reImportReqIFModel(true, "id", true);
					Profile theprofile = sysMLReqIFTransfomation.getImpactedProfile();
					ArrayList<Element> deleted = sysMLReqIFTransfomation.getElementToDelete();
					Assert.assertEquals(" Number of deleted must be equals to 0", 0, deleted.size());
					ArrayList<Element> add = sysMLReqIFTransfomation.getAddedElements();
					Assert.assertEquals(" Number of deleted must be equals to 1", 1, add.size());
					HashSet<Element> modification = sysMLReqIFTransfomation.getModifiedElement();
					Assert.assertEquals(" Number of deleted must be equals to 4", 4, modification.size());
				}
			};
			getTransactionalEditingDomain().getCommandStack().execute(cmd);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue("The test is not valided because of an exception " + e, false);
		}
	}

	/**
	 * @see org.eclipse.papyrus.junit.utils.tests.AbstractEditorTest#getSourcePath()
	 * @return
	 */
	@Override
	protected String getSourcePath() {
		return "models/re-import/modifiedLines/";
	}
}