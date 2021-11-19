/******************************************************************************
 * Copyright (c) 2021 CEA LIST, Artal Technologies
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Rengin Battal (ARTAL) - rengin.battal@artal.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.siriusdiag.clazz.tests.delete.semantic.nodes;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.junit.framework.classification.ClassificationRunner;
import org.eclipse.papyrus.junit.framework.classification.tests.AbstractPapyrusTest;
import org.eclipse.papyrus.junit.utils.rules.ActiveDiagram;
import org.eclipse.papyrus.junit.utils.rules.PluginResource;
import org.eclipse.papyrus.sirusdiag.junit.utils.rules.SiriusDiagramEditorFixture;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeContainerSpec;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Delete Class from model test
 */
@PluginResource("resources/delete/nodes/durationObservation/TopNode_DurationObservation_DeleteSemanticTest.di")
@SuppressWarnings("nls")
@RunWith(ClassificationRunner.class)
public class TopNode_DurationObservation_DeleteSemanticTest extends AbstractPapyrusTest {

	private static final String ELEMENT_TO_DESTROY_NAME = "DurationObservationToDelete";

	private static final String CLASS_DIAGRAM_NAME = "TopNode_DurationObservation_ClassDiagram";

	@Rule
	public final SiriusDiagramEditorFixture fixture = new SiriusDiagramEditorFixture(/* Collections.singletonList("aird") */);

	@SuppressWarnings("restriction")
	@Test
	@ActiveDiagram(CLASS_DIAGRAM_NAME)
	public void Delete_DurationObservation_Semantic() {
		// setup
		Assert.assertTrue(fixture.getModel() instanceof Model);
		Model rootModel = (Model) fixture.getModel();
		NamedElement element = rootModel.getMember(ELEMENT_TO_DESTROY_NAME);
		Assert.assertNotNull("We can't find the DurationObservation element to destroy", element);

		DiagramEditPart diagramEditpart = fixture.getActiveDiagram();
		Diagram classDiagram = diagramEditpart.getDiagramView();
		Assert.assertNotNull("We can't find the class diagram", classDiagram);

		Assert.assertEquals("The diagram must contains one DurationObservation element", 1, classDiagram.getChildren().size());
		Object elementToBeDeleted = classDiagram.getChildren().get(0);
		Assert.assertTrue(elementToBeDeleted instanceof View);
		EObject siriusNewRepresentation = ((View) elementToBeDeleted).getElement();
		Assert.assertTrue(siriusNewRepresentation instanceof DNodeContainerSpec);
		Assert.assertEquals("The found view doesn't represent the DurationObservation element to destroy", element, ((DNodeContainerSpec) siriusNewRepresentation).getTarget());

		fixture.applySemanticDeletionTool((DNodeContainerSpec) siriusNewRepresentation);
		fixture.flushDisplayEvents();

		// the semantic element has been destroyed
		Assert.assertNull("The UML model does not contain the DurationObservation element after the destruction", rootModel.getMember(ELEMENT_TO_DESTROY_NAME));

		// the view has been destroyed too in the diagram
		Assert.assertEquals("The class diagram does not contain the view of the DurationObservation element aftrer the destruction", 0, classDiagram.getChildren().size());

		// undo
		fixture.getEditingDomain().getCommandStack().undo();
		fixture.flushDisplayEvents();
		// the semantic and the view elements have been reset
		Assert.assertNotNull("The UML model must contain the destroyed the DurationObservation element aftrer undoing the destruction", rootModel.getMember(ELEMENT_TO_DESTROY_NAME));
		Assert.assertEquals("The class diagram must contain the view of the DurationObservation element aftrer undoing the destruction", 1, classDiagram.getChildren().size());

		// redo
		fixture.getEditingDomain().getCommandStack().redo();
		fixture.flushDisplayEvents();
		Assert.assertNull("The UML model does not contain the DurationObservation element after redoing the destruction", rootModel.getMember(ELEMENT_TO_DESTROY_NAME));
		Assert.assertEquals("The class diagram does not contain the view of the DurationObservation element aftrer redoing the destruction", 0, classDiagram.getChildren().size());
	}
}
