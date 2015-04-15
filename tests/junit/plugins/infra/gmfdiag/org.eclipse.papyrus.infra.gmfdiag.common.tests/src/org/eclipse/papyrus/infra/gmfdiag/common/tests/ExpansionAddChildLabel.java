/*****************************************************************************
 * Copyright (c) 2015 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.infra.gmfdiag.common.tests;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.gmfdiag.common.SynchronizableGmfDiagramEditor;
import org.eclipse.papyrus.infra.gmfdiag.common.expansion.ChildrenListRepresentation;
import org.eclipse.papyrus.infra.gmfdiag.common.expansion.DiagramExpansionSingleton;
import org.eclipse.papyrus.infra.gmfdiag.common.expansion.DiagramExpansionsRegistry;
import org.eclipse.papyrus.infra.gmfdiag.common.expansion.InducedRepresentationCreationEditPolicy;
import org.eclipse.papyrus.infra.gmfdiag.common.model.NotationModel;
import org.eclipse.papyrus.junit.utils.tests.AbstractEditorTest;
import org.junit.Assert;
import org.junit.Test;
import org.osgi.framework.Bundle;

/**
 * this Test is used to test if it is possible to add compartment
 * see #Test T002-Add a compartment
 * 
 *
 */
public class ExpansionAddChildLabel extends AbstractEditorTest{

	/**
	 * 
	 */
	protected static final String NESTED_INTERFACE_LABEL = "Interface_Label";

	public void openDiagram(IMultiDiagramEditor editor, final String name) {

		try {
			ModelSet modelSet = ServiceUtils.getInstance().getModelSet(editor.getServicesRegistry());
			NotationModel notation = (NotationModel) modelSet.getModel(NotationModel.MODEL_ID);
			Diagram diagram = notation.getDiagram(name);
			ServiceUtils.getInstance().getIPageManager(editor.getServicesRegistry()).openPage(diagram);
			flushDisplayEvents();
		} catch (Exception e) {
			throw new IllegalStateException("Cannot initialize test", e);
		}

	}

	/**
	 * 
	 */
	protected static final String CLASS_DIAGRAM_TYPE = "Class Diagram";
	/**
	 * 
	 */
	protected static final String CLASS_VISUALID = "2008";
	/**
	 * 
	 */
	protected static final String IMPLEMENTED_INTERFACES_HINT = "Implemented Interfaces";

	@Test
	public void load_DiagramExpansion() {
		//loading
		DiagramExpansionsRegistry diagramExpansionsRegistry=  loadXMIExpansionModel("AddChildLabel.xmi");
		Assert.assertEquals("Size ot the registry must be equals to 1",1,diagramExpansionsRegistry.getDiagramExpansions().size());
		Assert.assertEquals("Size ot the map childreen must be equals to 1",1,diagramExpansionsRegistry.mapChildreen.size());

		//test the data structure that is interpreted by the framework
		ChildrenListRepresentation childrenListRepresentation= diagramExpansionsRegistry.mapChildreen.get(CLASS_DIAGRAM_TYPE);
		System.out.println(childrenListRepresentation);
		Assert.assertNotNull("A usage contex has been defined for "+CLASS_DIAGRAM_TYPE , childrenListRepresentation);
		Assert.assertNotNull("The class has been redefined",childrenListRepresentation.IDMap.get(CLASS_VISUALID));
		Assert.assertNotNull("The compartment of class has been added",childrenListRepresentation.IDMap.get(IMPLEMENTED_INTERFACES_HINT));
		List<String> the_2008_Children=childrenListRepresentation.parentChildrenRelation.get(CLASS_VISUALID);
		Assert.assertEquals("2008 can have a new compartment",1, the_2008_Children.size());
		Assert.assertEquals("2008 has to contain "+IMPLEMENTED_INTERFACES_HINT,IMPLEMENTED_INTERFACES_HINT, the_2008_Children.get(0));
		
		Assert.assertNotNull("The Nested Interface has to be added",childrenListRepresentation.IDMap.get(NESTED_INTERFACE_LABEL));
		List<String> the_IMPLEMENTED_INTERFACES_Children=childrenListRepresentation.parentChildrenRelation.get(IMPLEMENTED_INTERFACES_HINT);
		Assert.assertEquals("Nested Interface can have a new compartment",1, the_IMPLEMENTED_INTERFACES_Children.size());
		Assert.assertEquals("Nested Interface has to contain "+NESTED_INTERFACE_LABEL,NESTED_INTERFACE_LABEL, the_IMPLEMENTED_INTERFACES_Children.get(0));
		// the model is valid 
		//now launch a class diagram

		try {
			initModel("ExpansionModelProject", "ExpansionModelTest", getBundle());
			openDiagram(editor, "NewDiagram");
			SynchronizableGmfDiagramEditor	diagramEditor = (SynchronizableGmfDiagramEditor)editor.getActiveEditor();
			DiagramEditPart diagramEditPart = (DiagramEditPart)editor.getAdapter(DiagramEditPart.class);
			Assert.assertNotNull("The diagram must be opened", diagramEditPart);
			IGraphicalEditPart classEditPart =(IGraphicalEditPart)diagramEditPart.getChildren().get(0);
			Assert.assertNotNull("A Class edit Part must exist", diagramEditPart);

			//verify editpolicy
			EditPolicy inducedRepresentationCreator=classEditPart.getEditPolicy(InducedRepresentationCreationEditPolicy.INDUCED_REPRESENTATION_CREATOR_EDITPOLICY);
			Assert.assertNotNull("A Class must have this editpolicy", inducedRepresentationCreator);


			//test in the notation
			View classNotationView=classEditPart.getNotationView();
			Assert.assertEquals("the Type of class editpart must be 2008", classNotationView.getType(), "2008");
			Assert.assertEquals("the Type of class editpart must be 2008 must contains 2 labels and 4 compartments",classNotationView.getPersistedChildren().size(), 6);
			View compartment=(View)classNotationView.getPersistedChildren().get(5);
			Assert.assertEquals("The last compartment must have the type "+IMPLEMENTED_INTERFACES_HINT,IMPLEMENTED_INTERFACES_HINT , compartment.getType());

			//test in the editpart is created for this notation
			Assert.assertEquals("the Type of class editpart must be 2008 must contains 2 labels and 4 compartments",classEditPart.getChildren().size(), 6);
			IGraphicalEditPart compartmentEdiPart=(IGraphicalEditPart)classEditPart.getChildren().get(5);
			if( compartmentEdiPart.getNotationView().equals(compartment)){
				Assert.assertEquals("The last compartment must have the type "+IMPLEMENTED_INTERFACES_HINT,IMPLEMENTED_INTERFACES_HINT , compartmentEdiPart.getNotationView().getType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	protected DiagramExpansionsRegistry loadXMIExpansionModel(String filename) {
		DiagramExpansionsRegistry diagramExpansionsRegistry= DiagramExpansionSingleton.getInstance().getDiagramExpansionRegistry();
		diagramExpansionsRegistry.clear();
		Assert.assertEquals("Size ot the registry must be equals to 0",0,diagramExpansionsRegistry.getDiagramExpansions().size());
		Assert.assertEquals("Size ot the map childreen must be equals to 0",0,diagramExpansionsRegistry.mapChildreen.size());
		URI badContextExpansion = URI.createPlatformPluginURI("org.eclipse.papyrus.infra.gmfdiag.common.tests", true);
		badContextExpansion=badContextExpansion.appendSegment("models");
		badContextExpansion=badContextExpansion.appendSegment(filename);

		diagramExpansionsRegistry.loadExpansion(badContextExpansion);

		return diagramExpansionsRegistry;
	}
	/**
	 * @see org.eclipse.papyrus.junit.utils.tests.AbstractEditorTest#getSourcePath()
	 *
	 * @return
	 */
	@Override
	protected String getSourcePath() {
		return "models/";
	}

	@Override
	protected Bundle getBundle() {
		return org.eclipse.papyrus.infra.gmfdiag.common.Activator.getInstance().getBundle();
	}
}
