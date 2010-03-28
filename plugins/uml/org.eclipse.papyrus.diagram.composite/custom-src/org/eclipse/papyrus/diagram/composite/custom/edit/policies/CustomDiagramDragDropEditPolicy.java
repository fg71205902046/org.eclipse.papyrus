/*****************************************************************************
 * Copyright (c) 2009 - 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Yann Tanguy (CEA LIST) yann.tanguy@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.composite.custom.edit.policies;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.diagram.common.editpolicies.CommonDiagramDragDropEditPolicy;
import org.eclipse.papyrus.diagram.composite.custom.edit.command.CreateViewCommand;
import org.eclipse.papyrus.diagram.composite.custom.helper.CollaborationHelper;
import org.eclipse.papyrus.diagram.composite.custom.helper.CompositeLinkMappingHelper;
import org.eclipse.papyrus.diagram.composite.custom.helper.ConnectorHelper;
import org.eclipse.papyrus.diagram.composite.custom.helper.DurationObservationHelper;
import org.eclipse.papyrus.diagram.composite.custom.helper.TimeObservationHelper;
import org.eclipse.papyrus.diagram.composite.custom.helper.TypeHelper;
import org.eclipse.papyrus.diagram.composite.custom.locators.PortPositionLocator;
import org.eclipse.papyrus.diagram.composite.custom.log.Log;
import org.eclipse.papyrus.diagram.composite.edit.parts.ActivityCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ActivityCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.ActorEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.AnyReceiveEventEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ArtifactEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.CallEventEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ChangeEventEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ClassClassifierEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ClassCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ClassCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.CollaborationCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.CollaborationCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.CommentEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ComponentCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ComponentCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.ConnectorEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ConstraintEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.CreationEventEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.DataTypeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.DependencyEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.DeploymentSpecificationEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.DeviceCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.DeviceCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.DurationConstraintEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.DurationEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.DurationObservationEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.EnumerationEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ExecutionEnvironmentCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ExecutionEnvironmentCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.ExecutionEventEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ExpressionEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.FunctionBehaviorCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.FunctionBehaviorCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.InformationItemEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.InstanceValueEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.InteractionCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.InteractionCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.InteractionConstraintEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.InterfaceEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.IntervalConstraintEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.IntervalEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.LiteralBooleanEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.LiteralIntegerEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.LiteralNullEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.LiteralStringEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.LiteralUnlimitedNaturalEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.NodeCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.NodeCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.OpaqueBehaviorCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.OpaqueBehaviorCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.OpaqueExpressionEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ParameterEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.PortEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.PrimitiveTypeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ProtocolStateMachineCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.ProtocolStateMachineCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.RoleBindingEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.SendOperationEventEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.SendSignalEventEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.SignalEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.SignalEventEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.StateMachineCompositeEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.StateMachineCompositeEditPartCN;
import org.eclipse.papyrus.diagram.composite.edit.parts.StringExpressionEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.TimeConstraintEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.TimeEventEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.TimeExpressionEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.TimeObservationEditPart;
import org.eclipse.papyrus.diagram.composite.edit.parts.UseCaseEditPart;
import org.eclipse.papyrus.diagram.composite.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.diagram.composite.providers.UMLElementTypes;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;

/**
 * This class provides an implementation for specific behavior of Drag and Drop in the Composite
 * Diagram.
 */
public class CustomDiagramDragDropEditPolicy extends CommonDiagramDragDropEditPolicy {

	/** List of VISUAL_ID for which a specific Drop behavior is provided */
	public int[] specificDropNode = {
		// TopLevelNodes
	ActivityCompositeEditPart.VISUAL_ID, InteractionCompositeEditPart.VISUAL_ID, ProtocolStateMachineCompositeEditPart.VISUAL_ID, StateMachineCompositeEditPart.VISUAL_ID, FunctionBehaviorCompositeEditPart.VISUAL_ID, OpaqueBehaviorCompositeEditPart.VISUAL_ID, ComponentCompositeEditPart.VISUAL_ID, DeviceCompositeEditPart.VISUAL_ID, ExecutionEnvironmentCompositeEditPart.VISUAL_ID, NodeCompositeEditPart.VISUAL_ID, ClassCompositeEditPart.VISUAL_ID, ClassClassifierEditPart.VISUAL_ID, CollaborationCompositeEditPart.VISUAL_ID, InterfaceEditPart.VISUAL_ID, PrimitiveTypeEditPart.VISUAL_ID, EnumerationEditPart.VISUAL_ID, DataTypeEditPart.VISUAL_ID, ActorEditPart.VISUAL_ID, DeploymentSpecificationEditPart.VISUAL_ID, ArtifactEditPart.VISUAL_ID, InformationItemEditPart.VISUAL_ID, SignalEditPart.VISUAL_ID, UseCaseEditPart.VISUAL_ID, SignalEventEditPart.VISUAL_ID, CallEventEditPart.VISUAL_ID, AnyReceiveEventEditPart.VISUAL_ID, SendSignalEventEditPart.VISUAL_ID, SendOperationEventEditPart.VISUAL_ID, ChangeEventEditPart.VISUAL_ID, TimeEventEditPart.VISUAL_ID, CreationEventEditPart.VISUAL_ID, ExecutionEventEditPart.VISUAL_ID, LiteralBooleanEditPart.VISUAL_ID, LiteralIntegerEditPart.VISUAL_ID, LiteralNullEditPart.VISUAL_ID, LiteralStringEditPart.VISUAL_ID, LiteralUnlimitedNaturalEditPart.VISUAL_ID, StringExpressionEditPart.VISUAL_ID, OpaqueExpressionEditPart.VISUAL_ID, TimeExpressionEditPart.VISUAL_ID, ExpressionEditPart.VISUAL_ID, DurationEditPart.VISUAL_ID, IntervalEditPart.VISUAL_ID, InstanceValueEditPart.VISUAL_ID, CommentEditPart.VISUAL_ID, DurationConstraintEditPart.VISUAL_ID, TimeConstraintEditPart.VISUAL_ID, IntervalConstraintEditPart.VISUAL_ID, InteractionConstraintEditPart.VISUAL_ID, ConstraintEditPart.VISUAL_ID,
		// TopLevelNodes

		// Class CN
	ActivityCompositeEditPartCN.VISUAL_ID, InteractionCompositeEditPartCN.VISUAL_ID, ProtocolStateMachineCompositeEditPartCN.VISUAL_ID, StateMachineCompositeEditPartCN.VISUAL_ID, FunctionBehaviorCompositeEditPartCN.VISUAL_ID, OpaqueBehaviorCompositeEditPartCN.VISUAL_ID, ComponentCompositeEditPartCN.VISUAL_ID, DeviceCompositeEditPartCN.VISUAL_ID, ExecutionEnvironmentCompositeEditPartCN.VISUAL_ID, NodeCompositeEditPartCN.VISUAL_ID, ClassCompositeEditPartCN.VISUAL_ID, CollaborationCompositeEditPartCN.VISUAL_ID,
		// Class CN
	CollaborationCompositeEditPartCN.VISUAL_ID, DependencyEditPart.VISUAL_ID, RoleBindingEditPart.VISUAL_ID, ConnectorEditPart.VISUAL_ID, PortEditPart.VISUAL_ID, ParameterEditPart.VISUAL_ID, org.eclipse.papyrus.diagram.composite.edit.parts.PropertyPartEditPartCN.VISUAL_ID, TimeObservationEditPart.VISUAL_ID, DurationObservationEditPart.VISUAL_ID };




	/**
	 * Default constructor
	 */
	public CustomDiagramDragDropEditPolicy() {
		super(CompositeLinkMappingHelper.getInstance());
		init(specificDropNode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IElementType getUMLElementType(int elementID) {
		return UMLElementTypes.getElementType(elementID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNodeVisualID(View containerView, EObject domainElement) {
		return UMLVisualIDRegistry.getNodeVisualID(containerView, domainElement);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLinkWithClassVisualID(EObject domainElement) {
		return UMLVisualIDRegistry.getLinkWithClassVisualID(domainElement);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getSpecificDropCommand(DropObjectsRequest dropRequest, Element semanticElement, int nodeVISUALID, int linkVISUALID) {

		// Switch test over linkVisualID
		switch(linkVISUALID) {
		case DependencyEditPart.VISUAL_ID:
			return dropDependency(dropRequest, semanticElement, linkVISUALID);
		case RoleBindingEditPart.VISUAL_ID:
			return dropRoleBinding(dropRequest, semanticElement, linkVISUALID);
		case ConnectorEditPart.VISUAL_ID:
			return dropConnector(dropRequest, semanticElement, linkVISUALID);
		default:
			// Switch test over nodeVISUALID
			switch(nodeVISUALID) {
			// Test TopLevelNode... Start
			case ActivityCompositeEditPart.VISUAL_ID:
			case InteractionCompositeEditPart.VISUAL_ID:
			case ProtocolStateMachineCompositeEditPart.VISUAL_ID:
			case StateMachineCompositeEditPart.VISUAL_ID:
			case FunctionBehaviorCompositeEditPart.VISUAL_ID:
			case OpaqueBehaviorCompositeEditPart.VISUAL_ID:
			case ComponentCompositeEditPart.VISUAL_ID:
			case DeviceCompositeEditPart.VISUAL_ID:
			case ExecutionEnvironmentCompositeEditPart.VISUAL_ID:
			case NodeCompositeEditPart.VISUAL_ID:
			case ClassCompositeEditPart.VISUAL_ID:
			case ClassClassifierEditPart.VISUAL_ID:
			case CollaborationCompositeEditPart.VISUAL_ID:
			case InterfaceEditPart.VISUAL_ID:
			case PrimitiveTypeEditPart.VISUAL_ID:
			case EnumerationEditPart.VISUAL_ID:
			case DataTypeEditPart.VISUAL_ID:
			case ActorEditPart.VISUAL_ID:
			case DeploymentSpecificationEditPart.VISUAL_ID:
			case ArtifactEditPart.VISUAL_ID:
			case InformationItemEditPart.VISUAL_ID:
			case SignalEditPart.VISUAL_ID:
			case UseCaseEditPart.VISUAL_ID:
			case SignalEventEditPart.VISUAL_ID:
			case CallEventEditPart.VISUAL_ID:
			case AnyReceiveEventEditPart.VISUAL_ID:
			case SendSignalEventEditPart.VISUAL_ID:
			case SendOperationEventEditPart.VISUAL_ID:
			case ChangeEventEditPart.VISUAL_ID:
			case TimeEventEditPart.VISUAL_ID:
			case CreationEventEditPart.VISUAL_ID:
			case ExecutionEventEditPart.VISUAL_ID:
			case LiteralBooleanEditPart.VISUAL_ID:
			case LiteralIntegerEditPart.VISUAL_ID:
			case LiteralNullEditPart.VISUAL_ID:
			case LiteralStringEditPart.VISUAL_ID:
			case LiteralUnlimitedNaturalEditPart.VISUAL_ID:
			case StringExpressionEditPart.VISUAL_ID:
			case OpaqueExpressionEditPart.VISUAL_ID:
			case TimeExpressionEditPart.VISUAL_ID:
			case ExpressionEditPart.VISUAL_ID:
			case DurationEditPart.VISUAL_ID:
			case IntervalEditPart.VISUAL_ID:
			case InstanceValueEditPart.VISUAL_ID:
			case CommentEditPart.VISUAL_ID:
			case DurationConstraintEditPart.VISUAL_ID:
			case TimeConstraintEditPart.VISUAL_ID:
			case IntervalConstraintEditPart.VISUAL_ID:
			case InteractionConstraintEditPart.VISUAL_ID:
			case ConstraintEditPart.VISUAL_ID:
				return dropTopLevelNode(dropRequest, semanticElement, nodeVISUALID, linkVISUALID);
				// Test TopLevelNode... End
			case PortEditPart.VISUAL_ID:
				return dropAffixedNode(dropRequest, (Port)semanticElement, nodeVISUALID);
			case ParameterEditPart.VISUAL_ID:
				return dropAffixedNode(dropRequest, (Parameter)semanticElement, nodeVISUALID);
			case org.eclipse.papyrus.diagram.composite.edit.parts.PropertyPartEditPartCN.VISUAL_ID:
				return dropProperty(dropRequest, (Property)semanticElement, nodeVISUALID);
			case TimeObservationEditPart.VISUAL_ID:
				return dropTimeObservation(dropRequest, (TimeObservation)semanticElement, nodeVISUALID);
			case DurationObservationEditPart.VISUAL_ID:
				return dropDurationObservation(dropRequest, (DurationObservation)semanticElement, nodeVISUALID);
			default:
				return super.getSpecificDropCommand(dropRequest, semanticElement, nodeVISUALID, linkVISUALID);
			}
		}
	}

	/**
	 * Returns the drop command for Dependency links.
	 * 
	 * @param dropRequest
	 *        the drop request
	 * @param semanticLink
	 *        the element to drop
	 * @param linkVISUALID
	 *        the visual identifier of the EditPart of the dropped element
	 * @return the drop command
	 */
	protected Command dropDependency(DropObjectsRequest dropRequest, Element semanticLink, int linkVISUALID) {
		Collection<?> sourceEnds = CompositeLinkMappingHelper.getInstance().getSource(semanticLink);
		Collection<?> targetEnds = CompositeLinkMappingHelper.getInstance().getTarget(semanticLink);

		// Dependency with Unary ends
		if((sourceEnds != null) && (targetEnds != null) && (sourceEnds.size() == 1) && (targetEnds.size() == 1)) {

			Element source = (Element)sourceEnds.toArray()[0];
			Element target = (Element)targetEnds.toArray()[0];
			return new ICommandProxy(dropBinaryLink(new CompositeCommand("drop Dependency"), source, target, //$NON-NLS-1$
			linkVISUALID, dropRequest.getLocation(), semanticLink));
		} else {
			return UnexecutableCommand.INSTANCE;
		}
	}

	/**
	 * Returns the drop command for RoleBinding links.
	 * 
	 * @param dropRequest
	 *        the drop request
	 * @param semanticLink
	 *        the element to drop
	 * @param linkVISUALID
	 *        the visual identifier of the EditPart of the dropped element
	 * @return the drop command
	 */
	protected Command dropRoleBinding(DropObjectsRequest dropRequest, Element semanticLink, int linkVISUALID) {
		Collection<?> sourceEnds = CompositeLinkMappingHelper.getInstance().getSource(semanticLink);
		Collection<?> targetEnds = CompositeLinkMappingHelper.getInstance().getTarget(semanticLink);

		// Dependency with Unary ends
		if((sourceEnds != null) && (targetEnds != null) && (sourceEnds.size() == 1) && (targetEnds.size() == 1)) {

			Element source = (Element)semanticLink.getOwner();
			Element target = (Element)targetEnds.toArray()[0];
			return new ICommandProxy(dropBinaryLink(new CompositeCommand("drop RoleBinding"), source, target, //$NON-NLS-1$
			linkVISUALID, dropRequest.getLocation(), semanticLink));
		} else {
			return UnexecutableCommand.INSTANCE;
		}
	}

	/**
	 * Returns the drop command for Connector links.
	 * 
	 * @param dropRequest
	 *        the drop request
	 * @param semanticLink
	 *        the element to drop
	 * @param linkVISUALID
	 *        the visual identifier of the EditPart of the dropped element
	 * @return the drop command
	 */
	protected Command dropConnector(DropObjectsRequest dropRequest, Element semanticLink, int linkVISUALID) {
		Collection<?> connectorEnds = CompositeLinkMappingHelper.getInstance().getSource(semanticLink);

		if((connectorEnds != null) && (connectorEnds.size() == 2)) {

			ConnectorHelper helper = new ConnectorHelper(getEditingDomain());
			return new ICommandProxy(helper.dropConnector((Connector)semanticLink, linkVISUALID, getViewer(), getHost(), getDiagramPreferencesHint(), dropRequest.getLocation(), ((GraphicalEditPart)getHost()).getNotationView()));
		} else {
			return UnexecutableCommand.INSTANCE;
		}
	}

	/**
	 * Returns the drop command for Property nodes.
	 * 
	 * @param dropRequest
	 *        the drop request
	 * @param location
	 *        the location to drop the element
	 * @param droppedElement
	 *        the element to drop
	 * @param nodeVISUALID
	 *        the visual identifier of the EditPart of the dropped element
	 * @return the drop command
	 */
	protected Command dropProperty(DropObjectsRequest dropRequest, Property droppedElement, int nodeVISUALID) {

		GraphicalEditPart graphicalParentEditPart = (GraphicalEditPart)getHost();
		EObject graphicalParentObject = graphicalParentEditPart.resolveSemanticElement();

		// Default drop location
		Point location = dropRequest.getLocation().getCopy();

		// Port inherits from Property this case should be excluded and treated separately
		if(!(droppedElement instanceof Port)) {

			if((graphicalParentObject instanceof Classifier) && (((Classifier)graphicalParentObject).getAttributes().contains(droppedElement))) {
				// The graphical parent is the real owner of the dropped property.
				return new ICommandProxy(getDefaultDropNodeCommand(nodeVISUALID, location, droppedElement));

			} else if(graphicalParentObject instanceof ConnectableElement) {
				Type type = ((ConnectableElement)graphicalParentObject).getType();
				if((type != null) && (type instanceof Classifier) && (((Classifier)type).getAttributes().contains(droppedElement))) {
					// The graphical parent is a Property typed by a Classifier that owns of the
					// dropped property.
					return new ICommandProxy(getDefaultDropNodeCommand(nodeVISUALID, location, droppedElement));
				}
			}
		}

		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Returns the drop command for Affixed nodes (Parameter, Port).
	 * This method uses PortPositionLocator used by both Port and Parameter.
	 * 
	 * @param dropRequest
	 *        the drop request
	 * @param location
	 *        the location to drop the element
	 * @param droppedElement
	 *        the element to drop
	 * @param nodeVISUALID
	 *        the visual identifier of the EditPart of the dropped element
	 * @return the drop command
	 */
	protected Command dropAffixedNode(DropObjectsRequest dropRequest, Element droppedElement, int nodeVISUALID) {

		// The dropped element must be a Port or Parameter
		if(!((droppedElement instanceof Port) || (droppedElement instanceof Parameter))) {
			Log.getInstance().error(new Exception("Incorrect parameter type (droppedElement should be a Port or Parameter)"));
			return UnexecutableCommand.INSTANCE;
		}

		// Manage Element drop in compartment
		Boolean isCompartmentTarget = false; // True if the target is a ShapeCompartmentEditPart
		GraphicalEditPart graphicalParentEditPart = (GraphicalEditPart)getHost();

		// Default drop location
		Point dropLocation = dropRequest.getLocation().getCopy();

		// Detect if the drop target is a compartment
		if(graphicalParentEditPart instanceof ShapeCompartmentEditPart) {
			isCompartmentTarget = true;

			// Replace compartment edit part by its parent EditPart
			graphicalParentEditPart = (GraphicalEditPart)graphicalParentEditPart.getParent();

			// Translate Port expected location according to the compartment location
			Point targetLocation = graphicalParentEditPart.getContentPane().getBounds().getLocation();
			ShapeCompartmentFigure compartmentFigure = (ShapeCompartmentFigure)getHostFigure();

			// Retrieve ViewPort location = the area where compartment children are located
			// Retrieve ViewPort view location =  the relative location of the viewed compartment
			// depending on the current scroll bar state
			Viewport compartmentViewPort = compartmentFigure.getScrollPane().getViewport();
			Point compartmentViewPortLocation = compartmentViewPort.getLocation();
			Point compartmentViewPortViewLocation = compartmentViewPort.getViewLocation();

			// Calculate the delta between the targeted element position for drop (the Composite figure)
			// and the View location with eventual scroll bar.
			Point delta = compartmentViewPortLocation.translate(targetLocation.negate());
			delta = delta.translate(compartmentViewPortViewLocation.negate());

			// Translate the requested drop location (relative to parent)
			dropLocation = dropRequest.getLocation().getTranslated(delta);
		}
		// Manage Element drop in compartment

		// Create proposed creation bounds and use the locator to find the expected position
		Point parentLoc = graphicalParentEditPart.getFigure().getBounds().getLocation().getCopy();
		PortPositionLocator locator = new PortPositionLocator(graphicalParentEditPart.getFigure(), PositionConstants.NONE);

		Rectangle proposedBounds = new Rectangle(dropLocation, new Dimension(20, 20));
		proposedBounds = proposedBounds.getTranslated(parentLoc);
		Rectangle preferredBounds = locator.getPreferredLocation(proposedBounds);

		// Convert the calculated preferred bounds as relative to parent location
		Rectangle creationBounds = preferredBounds.getTranslated(parentLoc.getNegated());
		dropLocation = creationBounds.getLocation();

		EObject graphicalParentObject = graphicalParentEditPart.resolveSemanticElement();

		if((graphicalParentObject instanceof EncapsulatedClassifier) && (((EncapsulatedClassifier)graphicalParentObject).getOwnedPorts().contains(droppedElement))) {
			// Drop Port on StructuredClassifier
			if(isCompartmentTarget) {
				return getDropAffixedNodeInCompartmentCommand(nodeVISUALID, dropLocation, droppedElement);
			}
			return new ICommandProxy(getDefaultDropNodeCommand(nodeVISUALID, dropLocation, droppedElement));

		} else if(graphicalParentObject instanceof ConnectableElement) {
			// Drop Port on Part
			Type type = ((ConnectableElement)graphicalParentObject).getType();

			if((type != null) && (type instanceof EncapsulatedClassifier) && (((EncapsulatedClassifier)type).getOwnedPorts().contains(droppedElement))) {
				if(isCompartmentTarget) {
					return getDropAffixedNodeInCompartmentCommand(nodeVISUALID, dropLocation, droppedElement);
				}
				return new ICommandProxy(getDefaultDropNodeCommand(nodeVISUALID, dropLocation, droppedElement));
			}

		} else if((graphicalParentObject instanceof Behavior) && (((Behavior)graphicalParentObject).getOwnedParameters().contains(droppedElement))) {
			// Drop Parameter on Behavior
			if(isCompartmentTarget) {
				return getDropAffixedNodeInCompartmentCommand(nodeVISUALID, dropLocation, droppedElement);
			}
			return new ICommandProxy(getDefaultDropNodeCommand(nodeVISUALID, dropLocation, droppedElement));
		}

		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Returns the drop command for DurationObservation nodes.
	 * 
	 * @param dropRequest
	 *        the drop request
	 * @param location
	 *        the location to drop the element
	 * @param droppedElement
	 *        the element to drop
	 * @param nodeVISUALID
	 *        the visual identifier of the EditPart of the dropped element
	 * @return the drop command
	 * 
	 */
	protected Command dropDurationObservation(DropObjectsRequest dropRequest, DurationObservation droppedElement, int nodeVISUALID) {

		// Test canvas element
		GraphicalEditPart graphicalParentEditPart = (GraphicalEditPart)getHost();
		EObject graphicalParentObject = graphicalParentEditPart.resolveSemanticElement();
		if(!(graphicalParentObject instanceof org.eclipse.uml2.uml.Package)) {
			return UnexecutableCommand.INSTANCE;
		}

		DurationObservationHelper durationObservationHelper = new DurationObservationHelper(getEditingDomain());
		return durationObservationHelper.dropDurationObservation((DurationObservation)droppedElement, getViewer(), getDiagramPreferencesHint(), dropRequest.getLocation(), ((GraphicalEditPart)getHost()).getNotationView());

	}

	/**
	 * Returns the drop command for TimeObservation nodes.
	 * 
	 * @param dropRequest
	 *        the drop request
	 * @param location
	 *        the location to drop the element
	 * @param droppedElement
	 *        the element to drop
	 * @param nodeVISUALID
	 *        the visual identifier of the EditPart of the dropped element
	 * @return the drop command
	 * 
	 */
	protected Command dropTimeObservation(DropObjectsRequest dropRequest, TimeObservation droppedElement, int nodeVISUALID) {

		// Test canvas element
		GraphicalEditPart graphicalParentEditPart = (GraphicalEditPart)getHost();
		EObject graphicalParentObject = graphicalParentEditPart.resolveSemanticElement();
		if(!(graphicalParentObject instanceof org.eclipse.uml2.uml.Package)) {
			return UnexecutableCommand.INSTANCE;
		}

		TimeObservationHelper timeObservationHelper = new TimeObservationHelper(getEditingDomain());
		return timeObservationHelper.dropTimeObservation((TimeObservation)droppedElement, getViewer(), getDiagramPreferencesHint(), dropRequest.getLocation(), ((GraphicalEditPart)getHost()).getNotationView());
	}

	/**
	 * This method return a drop command for TopLevelNode. It returns an {@link org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand} in
	 * case the element is dropped on a canvas referencing a domain element that is not a Package.
	 * 
	 * @param dropRequest
	 *        the drop request
	 * @param semanticElement
	 *        the semantic element
	 * @param nodeVISUALID
	 *        the visual identifier of the EditPart of the dropped element
	 * @param linkVISUALID
	 *        the visual identifier of the EditPart of the dropped element
	 * @return the drop command
	 */
	protected Command dropTopLevelNode(DropObjectsRequest dropRequest, Element semanticElement, int nodeVISUALID, int linkVISUALID) {

		GraphicalEditPart graphicalParentEditPart = (GraphicalEditPart)getHost();
		EObject graphicalParentObject = graphicalParentEditPart.resolveSemanticElement();
		if(graphicalParentObject instanceof org.eclipse.uml2.uml.Package) {
			return new ICommandProxy(getDefaultDropNodeCommand(nodeVISUALID, dropRequest.getLocation(), semanticElement));
		}

		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * This method returns the drop command for AffixedNode (Port, Parameter) in case the node is dropped on a ShapeCompartmentEditPart.
	 * 
	 * @param nodeVISUALID
	 *        the node visual identifier
	 * @param location
	 *        the drop location
	 * @param droppedObject
	 *        the object to drop
	 * @return a CompositeCommand for Drop
	 */
	protected CompoundCommand getDropAffixedNodeInCompartmentCommand(int nodeVISUALID, Point location, EObject droppedObject) {
		CompoundCommand cc = new CompoundCommand("Drop");
		IAdaptable elementAdapter = new EObjectAdapter(droppedObject);

		ViewDescriptor descriptor = new ViewDescriptor(elementAdapter, Node.class, ((IHintedType)getUMLElementType(nodeVISUALID)).getSemanticHint(), ViewUtil.APPEND, false, getDiagramPreferencesHint());
		// Create the command targeting host parent (owner of the ShapeCompartmentEditPart) 
		CreateViewCommand createCommand = new CreateViewCommand(getEditingDomain(), descriptor, ((View)(getHost().getParent().getModel())));
		cc.add(new ICommandProxy(createCommand));

		SetBoundsCommand setBoundsCommand = new SetBoundsCommand(getEditingDomain(), "move", (IAdaptable)createCommand.getCommandResult().getReturnValue(), location);
		cc.add(new ICommandProxy(setBoundsCommand));

		return cc;
	}

	/**
	 * This method is overridden to provide DND support to element that are not
	 * expected relatively to GMF declarations (e.g. : Drop Class on Collaboration).
	 * In such a case, the VisualID of the dropped element cannot be retrieved, because
	 * not expected to be created on the underlying graphical node. Such behavior should be externalized (with extension point) in order to be easily modified in derived diagrams.
	 * 
	 * @see org.eclipse.papyrus.diagram.common.editpolicies.CommonDiagramDragDropEditPolicy#getDropObjectsCommand(org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest)
	 * 
	 * @param dropRequest
	 *        the drop request
	 * @return the drop command
	 */
	public Command getDropObjectsCommand(DropObjectsRequest dropRequest) {

		CompoundCommand cc = new CompoundCommand("Drop");

		Iterator<?> iter = dropRequest.getObjects().iterator();
		if((dropRequest.getObjects().size() > 0) && (dropRequest.getObjects().get(0) instanceof String)) {
			return getDropFileCommand(dropRequest);
		}

		Point location = dropRequest.getLocation().getCopy();
		((GraphicalEditPart)getHost()).getContentPane().translateToRelative(location);
		((GraphicalEditPart)getHost()).getContentPane().translateFromParent(location);
		location.translate(((GraphicalEditPart)getHost()).getContentPane().getClientArea().getLocation().getNegated());

		EObject graphicalParentObject = ((GraphicalEditPart)getHost()).resolveSemanticElement();

		while((graphicalParentObject != null) && (iter.hasNext())) {

			EObject droppedObject = (EObject)iter.next();
			if(graphicalParentObject instanceof Collaboration) {

				if(droppedObject instanceof Collaboration) {
					CollaborationHelper helper = new CollaborationHelper(getEditingDomain());
					cc.add(helper.dropCollaborationAsCollaborationUse((GraphicalEditPart)getHost(), (Collaboration)droppedObject, location));

				} else if(droppedObject instanceof Class) {
					TypeHelper helper = new TypeHelper(getEditingDomain());
					cc.add(helper.dropTypeAsTypedProperty((GraphicalEditPart)getHost(), (Class)droppedObject, location));

				}

			} else if(graphicalParentObject instanceof StructuredClassifier) {

				if(droppedObject instanceof Collaboration) {
					CollaborationHelper helper = new CollaborationHelper(getEditingDomain());
					cc.add(helper.dropCollaborationAsCollaborationUse((GraphicalEditPart)getHost(), (Collaboration)droppedObject, location));

				} else if(droppedObject instanceof Class) {
					TypeHelper helper = new TypeHelper(getEditingDomain());
					cc.add(helper.dropTypeAsTypedProperty((GraphicalEditPart)getHost(), (Class)droppedObject, location));

				}

			} else if(graphicalParentObject instanceof TypedElement) {

				if(droppedObject instanceof Type) {

					TypeHelper helper = new TypeHelper(getEditingDomain());
					cc.add(helper.dropTypeOnTypedElement((GraphicalEditPart)getHost(), (Type)droppedObject, location));
				}
			}
		}

		if(!cc.isEmpty()) {
			return cc;
		}

		return super.getDropObjectsCommand(dropRequest);
	}
}
