/*****************************************************************************
 * Copyright (c) 2009 CEA LIST.
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
package org.eclipse.papyrus.diagram.composite.custom.helper;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest.ConnectionViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.diagram.common.commands.CommonDeferredCreateConnectionViewCommand;
import org.eclipse.papyrus.diagram.common.commands.SemanticAdapter;
import org.eclipse.papyrus.diagram.common.helper.ElementHelper;
import org.eclipse.papyrus.diagram.composite.providers.UMLElementTypes;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.TimeObservation;

/**
 * The Class TimeObservationHelper has in charge to drop a timeObservation
 * 
 * This class looks like AssociationClassHelper
 */
public class TimeObservationHelper extends ElementHelper {

	/**
	 * Instantiates a new TimeObservation helper.
	 * 
	 * @param editDomain
	 *            the edit domain
	 */
	public TimeObservationHelper(TransactionalEditingDomain editDomain) {
		this.editDomain = editDomain;
	}

	/**
	 * Drop TimeObservation.
	 * 
	 * @param durationobservation
	 *            the association class the semantic element
	 * @param viewer
	 *            the viewer
	 * @param diagramPreferencesHint
	 *            the diagram preferences hint
	 * @param location
	 *            the location of the drop
	 * @param containerView
	 *            the container view that will contain the associationClass views
	 * 
	 * @return the command
	 */
	public Command dropTimeObservation(TimeObservation timeObservation, EditPartViewer viewer,
			PreferencesHint diagramPreferencesHint, Point location, View containerView) {
		CompositeCommand cc = new CompositeCommand("dropTimeObservation");

		// 0. Obtain the events
		NamedElement endToConnect = timeObservation.getEvent();
		GraphicalEditPart endEditPart = null;

		// we have an event
		if (endToConnect != null) {
			// look for if an editpart exist for this element
			Collection<EditPart> editPartSet = viewer.getEditPartRegistry().values();
			Iterator<EditPart> editPartIterator = editPartSet.iterator();

			while (editPartIterator.hasNext() && endEditPart == null) {

				EditPart currentEditPart = editPartIterator.next();
				if (currentEditPart instanceof GraphicalEditPart
						&& endToConnect.equals(((GraphicalEditPart) currentEditPart).resolveSemanticElement())) {
					/**
					 * Warning : TimeObservationEditPart, TimeObservationStereotypeLabelEditPart and
					 * TimeObservationNameEditPart are equal : This is the object of this 2nd IF!!!
					 */
					if (!(currentEditPart instanceof CompartmentEditPart)
							&& !(currentEditPart instanceof LabelEditPart))
						endEditPart = (GraphicalEditPart) currentEditPart;
				}
			}
		}

		// 2. creation of the node TimeObservation
		IAdaptable elementAdapter = new EObjectAdapter(timeObservation);
		ViewDescriptor descriptor = new ViewDescriptor(elementAdapter, Node.class,
				((IHintedType) UMLElementTypes.TimeObservation_2094).getSemanticHint(), ViewUtil.APPEND, false,
				diagramPreferencesHint);
		CreateCommand nodeCreationCommand = new CreateCommand(getEditingDomain(), descriptor, ((View) containerView));
		cc.compose(nodeCreationCommand);

		SetBoundsCommand setBoundsCommand = new SetBoundsCommand(getEditingDomain(), "move",
				(IAdaptable) nodeCreationCommand.getCommandResult().getReturnValue(), location);
		cc.compose(setBoundsCommand);

		if (endEditPart != null) {
			IAdaptable sourceEventAdapter = null;
			IAdaptable targetEventAdapter = null;

			// obtain the node figure
			sourceEventAdapter = (IAdaptable) nodeCreationCommand.getCommandResult().getReturnValue();

			// used in the creation command of the event
			ConnectionViewDescriptor dashedLineViewDescriptor = new ConnectionViewDescriptor(
					UMLElementTypes.TimeObservationEvent_4018,
					((IHintedType) UMLElementTypes.TimeObservationEvent_4018).getSemanticHint(), diagramPreferencesHint);

			// 3. creation of the dashed line between the associationClass link

			targetEventAdapter = new SemanticAdapter(null, endEditPart.getModel());
			CommonDeferredCreateConnectionViewCommand dashedLineCmd = new CommonDeferredCreateConnectionViewCommand(
					getEditingDomain(), ((IHintedType) UMLElementTypes.TimeObservationEvent_4018).getSemanticHint(),
					((IAdaptable) sourceEventAdapter), ((IAdaptable) targetEventAdapter), viewer,
					diagramPreferencesHint, dashedLineViewDescriptor, null);
			dashedLineCmd.setElement(timeObservation);
			cc.compose(dashedLineCmd);

		}

		return new ICommandProxy(cc);
	}

}
