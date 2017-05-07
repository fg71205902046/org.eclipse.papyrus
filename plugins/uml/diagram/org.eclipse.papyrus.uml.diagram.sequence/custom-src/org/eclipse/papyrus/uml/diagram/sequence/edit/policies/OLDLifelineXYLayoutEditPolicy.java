/*****************************************************************************
 * Copyright (c) 2009 CEA
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.commands.PreserveAnchorsPositionCommand;
import org.eclipse.papyrus.uml.diagram.common.draw2d.LifelineDotLineFigure;
import org.eclipse.papyrus.uml.diagram.sequence.command.CustomZOrderCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.BehaviorExecutionSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CCombinedCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomDurationConstraintEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.DestructionOccurrenceSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.OLDLifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.OLDLifelineEditPart.PreserveAnchorsPositionCommandEx;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.StateInvariantEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.sequence.util.LifelineEditPartUtil;
import org.eclipse.papyrus.uml.diagram.sequence.util.OccurrenceSpecificationMoveHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;

/**
 * The custom LayoutEditPolicy for LifelineEditPart.
 * 
 * @deprecated will be removed in Oxygen
 */
public class OLDLifelineXYLayoutEditPolicy extends XYLayoutEditPolicy {

	/** Initialization width of Execution Specification. */
	public final static int EXECUTION_INIT_WIDTH = 16;

	/** Initialization width of CoRegion. */
	public final static int COREGION_INIT_WIDTH = 30;

	/** Width of Destruction Occurrence Specification. */
	public final static int DESTRUCTION_INIT_SIZE = 20;

	/** Spacing below Lifeline. */
	public final static int LIFELINE_SOUTH_SPACING = 14;

	/** Initialization height of Execution Specification. */
	public final static int EXECUTION_INIT_HEIGHT = 50;

	/** Initialization height of a time bar figure. */
	private static final int TIME_BAR_HEIGHT = 1;

	/** The default spacing used between Execution Specification */
	public final static int SPACING_HEIGHT = 5;

	// private final static int MAX_CHILD_EXECUTION_DEPTH = 4;
	// force location of time/duration elements and ES
	private static final String TIME_CONSTRAINT_HINT = ((IHintedType) UMLElementTypes.TimeConstraint_Shape).getSemanticHint();

	private static final String TIME_OBSERVATION_HINT = ((IHintedType) UMLElementTypes.TimeObservation_Shape).getSemanticHint();

	private static final String DURATION_CONSTRAINT_ON_LIFELINE_HINT = ((IHintedType) UMLElementTypes.DurationConstraint_Shape).getSemanticHint();

	private static final String ACTION_EXECUTION_SPECIFICATION_HINT = ((IHintedType) UMLElementTypes.ActionExecutionSpecification_Shape).getSemanticHint();

	private static final String BEHAVIOR_EXECUTION_SPECIFICATION_HINT = ((IHintedType) UMLElementTypes.BehaviorExecutionSpecification_Shape).getSemanticHint();


	private static final String DESTRUCTION_OCCURANCE_SPECIFICATION_HINT = ((IHintedType) UMLElementTypes.DestructionOccurrenceSpecification_Shape).getSemanticHint();

	@Override
	public EditPart getTargetEditPart(Request request) {
		EditPart targetEditPart = super.getTargetEditPart(request);
		if (targetEditPart instanceof OLDLifelineEditPart) {
			OLDLifelineEditPart lifelineEditPart = (OLDLifelineEditPart) targetEditPart;
			if (lifelineEditPart.isInlineMode()) {
				Point location = null;
				if (request instanceof DropRequest) {
					location = ((DropRequest) request).getLocation();
				}
				if (location != null) {
					List children = lifelineEditPart.getChildren();
					for (Object object : children) {
						if (!(object instanceof OLDLifelineEditPart)) {
							continue;
						}
						OLDLifelineEditPart child = (OLDLifelineEditPart) object;
						IFigure figure = child.getFigure();
						Point pt = location.getCopy();
						figure.translateToRelative(pt);
						if (figure.containsPoint(pt)) {
							return child;
						}
					}
					return null;
				}
			}
		}
		return targetEditPart;
	}

	@Override
	protected Command getOrphanChildrenCommand(Request request) {
		// Don't support, this will disable the createAddCommand(), too.
		return UnexecutableCommand.INSTANCE;
	}

	@Override
	protected Command createAddCommand(EditPart child, Object constraint) {
		// Don't supported.
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		EditPart targetEditPart = getTargetEditPart(request);
		if (targetEditPart instanceof OLDLifelineEditPart && targetEditPart.getParent() instanceof OLDLifelineEditPart) {
			Point location = request.getLocation();
			IFigure figure = ((OLDLifelineEditPart) targetEditPart).getPrimaryShape();
			Point pt = location.getCopy();
			figure.translateToRelative(pt);
			if (!figure.containsPoint(pt)) {
				return UnexecutableCommand.INSTANCE;
			}
		}
		if (request instanceof CreateViewRequest) {
			CreateViewRequest cvr = (CreateViewRequest) request;
			if (cvr.getViewDescriptors().size() > 0) {
				ViewDescriptor viewDescriptor = cvr.getViewDescriptors().iterator().next();
				String semanticHint = viewDescriptor.getSemanticHint();
				if (TIME_CONSTRAINT_HINT.equals(semanticHint) || TIME_OBSERVATION_HINT.equals(semanticHint)) {
					Command cmd = getCommandForTimeObservationOrConstraint(cvr, viewDescriptor);
					if (cmd != null) {
						return cmd;
					}
				}
				if (DURATION_CONSTRAINT_ON_LIFELINE_HINT.equals(semanticHint)) {
					Command cmd = getCommandForDurationConstraint(cvr, viewDescriptor);
					if (cmd != null) {
						return cmd;
					}
				}
				if (ACTION_EXECUTION_SPECIFICATION_HINT.equals(semanticHint) || BEHAVIOR_EXECUTION_SPECIFICATION_HINT.equals(semanticHint)) {
					Command cmd = getCommandForExecutionSpecificationCreation(cvr, viewDescriptor);
					if (cmd != null) {
						return cmd;
					}
				}
				if (DESTRUCTION_OCCURANCE_SPECIFICATION_HINT.equals(semanticHint)) {
					Command cmd = getCommandForDestructionOccuranceCreation(cvr, viewDescriptor);
					if (cmd != null) {
						return cmd;
					}
				}
				if (UMLVisualIDRegistry.getType(StateInvariantEditPart.VISUAL_ID).equals(semanticHint)) {
					Command cmd = getCommandForStateInvariant(cvr, viewDescriptor);
					if (cmd != null) {
						return cmd;
					}
				}
			}
		}
		return super.getCreateCommand(request);
	}

	/**
	 * Fixed bugs about creating StateInvariant.
	 */
	private Command getCommandForStateInvariant(CreateViewRequest cvr, ViewDescriptor viewDescriptor) {
		Rectangle newBounds = new Rectangle();
		if (cvr.getLocation() != null) {
			newBounds.setLocation(cvr.getLocation());
		}
		if (cvr.getSize() != null) {
			newBounds.setSize(cvr.getSize());
		} else {
			newBounds.width = -1;
			newBounds.height = -1;
		}
		if (newBounds.x < 0 || newBounds.y < 0) {
			newBounds.x = newBounds.y = 0;
		}
		LifelineEditPart parent = (LifelineEditPart) getHost();
		IFigure mainFigure = parent.getMainFigure();
		mainFigure.translateToRelative(newBounds);
		// mainFigure.translateFromParent(newBounds);
		Rectangle bounds = mainFigure.getBounds().getCopy();
		Point t = mainFigure.getClientArea().getLocation().getNegated();
		newBounds.performTranslate(t.x, t.y);
		newBounds.translate(0, bounds.y);
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return new ICommandProxy(new SetBoundsCommand(editingDomain, DiagramUIMessages.SetLocationCommand_Label_Resize, viewDescriptor, newBounds));
	}

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		View childView = (View) child.getModel();
		switch (UMLVisualIDRegistry.getVisualID(childView)) {
		case StateInvariantEditPart.VISUAL_ID:
			return new StateInvariantResizableEditPolicy();
		// case DestructionOccurrenceSpecificationEditPart.VISUAL_ID:
		// return new BorderItemResizableEditPolicy();
		}
		EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if (result == null) {
			return super.createChildEditPolicy(child);
		}
		return result;
	}

	@Override
	protected Rectangle getCurrentConstraintFor(GraphicalEditPart child) {
		IFigure fig = child.getFigure();
		Object con = fig.getParent().getLayoutManager().getConstraint(fig);
		if (con instanceof Rectangle) {
			return (Rectangle) con;
		}
		return fig.getBounds();
	}

	@Override
	protected Object getConstraintFor(CreateRequest request) {
		Rectangle constraint = (Rectangle) super.getConstraintFor(request);
		if (request instanceof CreateViewAndElementRequest) {
			CreateViewAndElementRequest req = (CreateViewAndElementRequest) request;
			IHintedType type = (IHintedType) UMLElementTypes.Lifeline_Shape;
			if (type.getSemanticHint().equals(req.getViewAndElementDescriptor().getSemanticHint())) {
				constraint.y = 0; // fix layout offset
			}
			String destructionHint = ((IHintedType) UMLElementTypes.DestructionOccurrenceSpecification_Shape).getSemanticHint();
			if (destructionHint.equals(req.getViewAndElementDescriptor().getSemanticHint()) && constraint.width < 0 && constraint.height < 0) {
				constraint.width = constraint.height = DESTRUCTION_INIT_SIZE;// set initial size, same as DestructionOccurrenceSpecificationPreferencePage
			}
		}
		return constraint;
	}

	private static Rectangle getNewBoundsForChild(LifelineEditPart lifelineEP, Rectangle bounds, int figureWidth) {
		Rectangle newBounds = bounds.getCopy();
		// Get the dotline figure
		LifelineDotLineFigure figureLifelineDotLineFigure = lifelineEP.getPrimaryShape().getFigureLifelineDotLineFigure();
		// Translate the absolute location to relative
		figureLifelineDotLineFigure.translateToRelative(newBounds);
		newBounds.translate(figureLifelineDotLineFigure.getBounds().getLocation().getCopy().negate());
		Rectangle dotLineFigureBounds = figureLifelineDotLineFigure.getBounds();
		newBounds.x = dotLineFigureBounds.width / 2 - figureWidth / 2;
		newBounds.width = figureWidth;
		return newBounds;
	}

	private Command getCommandForCoRegionCreation(CreateViewRequest cvr, ViewDescriptor viewDescriptor) {
		Rectangle newBounds = new Rectangle();
		if (cvr.getLocation() != null) {
			newBounds.setLocation(cvr.getLocation());
		}
		if (cvr.getSize() != null) {
			newBounds.setSize(cvr.getSize());
		} else {
			newBounds.width = -1;
			newBounds.height = -1;
		}
		if (newBounds.x < 0 || newBounds.y < 0) {
			newBounds.x = newBounds.y = 0;
		}
		newBounds = getNewBoundsForChild((LifelineEditPart) getHost(), newBounds, COREGION_INIT_WIDTH);
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return new ICommandProxy(new SetBoundsCommand(editingDomain, DiagramUIMessages.SetLocationCommand_Label_Resize, viewDescriptor, newBounds));
	}

	private Command getCommandForDestructionOccuranceCreation(CreateViewRequest cvr, ViewDescriptor viewDescriptor) {
		if (cvr.getLocation() == null) {
			return UnexecutableCommand.INSTANCE;
		}
		LifelineEditPart lifelineEP = (LifelineEditPart) getHost();
		// Translate the absolute location to relative
		Point location = cvr.getLocation().getCopy();
		lifelineEP.getFigure().translateToRelative(location);
		location.translate(lifelineEP.getLocation().getCopy().negate());
		CompoundCommand command = new CompoundCommand();
		command = getSetLifelineHeightCommand(command, lifelineEP, location.y);
		return command;
	}

	private Rectangle getCreateExecuteSpecificationBounds(Point location, Dimension size, String semanticHint) {
		Point newLocation = location == null ? new Point() : location.getCopy();
		if (newLocation.x < 0 || newLocation.y < 0) {
			newLocation.x = newLocation.y = 0;
		}
		LifelineEditPart editPart = (LifelineEditPart) getHost();
		// Get the dotline figure
		LifelineDotLineFigure figureLifelineDotLineFigure = editPart.getPrimaryShape().getFigureLifelineDotLineFigure();
		List<ShapeNodeEditPart> executionSpecificationList = LifelineEditPartUtil.getChildShapeNodeEditPart(editPart);
		// Translate the absolute location to relative
		figureLifelineDotLineFigure.translateToRelative(newLocation);
		Rectangle dotLineFigureBounds = figureLifelineDotLineFigure.getBounds();
		// If we are creating an ES from the popup menu bar
		// We need to get a valid location to be able to create the ES figure
		if (newLocation.y < dotLineFigureBounds.y) {
			int max = dotLineFigureBounds.y;
			for (ShapeNodeEditPart sp : executionSpecificationList) {
				int figureBottom = sp.getFigure().getBounds().y + sp.getFigure().getBounds().height;
				if (figureBottom > max) {
					max = figureBottom;
				}
			}
			// Vertically, the new ES is located after all existing ES on the lifeline
			newLocation.y = max + SPACING_HEIGHT;
			// Horizontally, the figure is placed at the center of the lifeline
			newLocation.x = dotLineFigureBounds.x + dotLineFigureBounds.width / 2 - EXECUTION_INIT_WIDTH / 2;
		}
		// Get the height of the Execution specification
		int newHeight = getFigureHeight(semanticHint, size);
		// Define the bounds of the new Execution specification
		return new Rectangle(newLocation.x, newLocation.y, -1, newHeight);
	}

	private ShapeNodeEditPart getParentWhenCreationExecuteSpecification(Point location, Dimension size, String semanticHint) {
		LifelineEditPart lifelineEditPart = (LifelineEditPart) getHost();
		// Get the dotline figure
		LifelineDotLineFigure figureLifelineDotLineFigure = lifelineEditPart.getPrimaryShape().getFigureLifelineDotLineFigure();
		Point newLocation = location.getCopy();
		figureLifelineDotLineFigure.translateToRelative(newLocation);
		List<ShapeNodeEditPart> executionSpecificationList = LifelineEditPartUtil.getChildShapeNodeEditPart(lifelineEditPart);
		ShapeNodeEditPart parent = null;
		double distance = 0;
		for (ShapeNodeEditPart externalExecutionSpecificationEP : executionSpecificationList) {
			IFigure figure = externalExecutionSpecificationEP.getFigure();
			Rectangle bounds = figure.getBounds().getCopy();
			View view = externalExecutionSpecificationEP.getNotationView();
			// Handle the case of ExecutionSpecification being resized in the same compound command
			if (view instanceof Shape && ((Shape) view).getLayoutConstraint() instanceof Bounds) {
				Bounds newBounds = (Bounds) ((Shape) view).getLayoutConstraint();
				bounds.height = newBounds.getHeight();
			}
			if (!bounds.contains(newLocation) || bounds.x > newLocation.x) {
				continue;
			}
			double newDistance = bounds.getLocation().getDistance(newLocation);
			if (distance == 0 || newDistance < distance) {
				parent = externalExecutionSpecificationEP;
				distance = newDistance;
			}
		}
		return parent;
	}

	private Command getCommandForExecutionSpecificationCreation(CreateViewRequest cvr, ViewDescriptor viewDescriptor) {
		LifelineEditPart editPart = (LifelineEditPart) getHost();
		List<ShapeNodeEditPart> executionSpecificationList = LifelineEditPartUtil.getChildShapeNodeEditPart(editPart);
		Point location = cvr.getLocation();
		Dimension size = cvr.getSize();
		String semanticHint = viewDescriptor.getSemanticHint();
		// Define the bounds of the new Execution specification
		Rectangle newBounds = getCreateExecuteSpecificationBounds(location, size, semanticHint);
		// Get the dotline figure
		LifelineDotLineFigure figureLifelineDotLineFigure = editPart.getPrimaryShape().getFigureLifelineDotLineFigure();
		Rectangle dotLineBounds = figureLifelineDotLineFigure.getBounds();
		ShapeNodeEditPart parent = getParentWhenCreationExecuteSpecification(location, size, semanticHint);
		if (parent != null) {
			Rectangle parentBounds = parent.getFigure().getBounds();
			int width = parentBounds.width > 0 ? parentBounds.width : EXECUTION_INIT_WIDTH;
			newBounds.x = parentBounds.x + width / 2 + 1;
		} else {
			int width = newBounds.width > 0 ? newBounds.width : EXECUTION_INIT_WIDTH;
			newBounds.x = dotLineBounds.x + dotLineBounds.width / 2 - width / 2;
		}
		newBounds.x -= dotLineBounds.x;
		newBounds.y -= dotLineBounds.y;
		// newBounds = getExecutionSpecificationNewBounds(true, editPart, new Rectangle(), newBounds, new ArrayList<ShapeNodeEditPart>(0), true);
		// if(newBounds == null) {
		// return UnexecutableCommand.INSTANCE;
		// }
		Command p = new ICommandProxy(new SetBoundsCommand(editPart.getEditingDomain(), "Creation of an ExecutionSpecification", viewDescriptor, newBounds));
		// resize parent bar
		if (parent != null) {
			Rectangle newAdjustedBounds = newBounds.getCopy();
			newAdjustedBounds.height += OLDLifelineXYLayoutEditPolicy.SPACING_HEIGHT;
			p = p.chain(resizeParentExecutionSpecification((LifelineEditPart) getHost(), parent, newAdjustedBounds, executionSpecificationList));
		}
		return p;
	}

	private static Command resizeParentExecutionSpecification(LifelineEditPart lifelinePart, ShapeNodeEditPart part, Rectangle childBounds, List<ShapeNodeEditPart> list) {
		Rectangle bounds = getRelativeBounds(part.getFigure());
		childBounds.x = bounds.x;
		childBounds.width = bounds.width;
		Rectangle rect = bounds.getCopy();
		int spacingY = OLDLifelineXYLayoutEditPolicy.SPACING_HEIGHT;
		if (childBounds.y - spacingY < rect.y) {
			rect.height += rect.y - childBounds.y + spacingY;
			rect.y = childBounds.y - spacingY;
		} else if (childBounds.bottom() + spacingY > rect.bottom()) {
			rect.height = childBounds.bottom() - rect.y + spacingY;
		} else {
			return null;
		}
		Rectangle newBounds = rect.getCopy();
		CompoundCommand command = new CompoundCommand();
		Command c = new ICommandProxy(new SetBoundsCommand(part.getEditingDomain(), "Resize of Parent Bar", part, newBounds.getCopy()));
		command.add(c);
		Point moveDelta = new Point(newBounds.x - bounds.x, newBounds.y - bounds.y);
		Dimension sizeDelta = new Dimension(newBounds.width() - bounds.width(), newBounds.height() - bounds.height());
		if (moveDelta.y != 0 || sizeDelta.height() != 0) {
			ChangeBoundsRequest request = new ChangeBoundsRequest();
			request.setEditParts(part);
			request.setMoveDelta(moveDelta);
			request.setSizeDelta(sizeDelta);
			command = OccurrenceSpecificationMoveHelper.completeMoveExecutionSpecificationCommand(command, part, newBounds.getCopy(), request);
		}
		list.remove(part);
		ShapeNodeEditPart parent = getParent(lifelinePart, part.getFigure().getBounds(), list);
		if (parent == null) {
			return command.unwrap();
		}
		return command.unwrap().chain(resizeParentExecutionSpecification(lifelinePart, parent, newBounds.getCopy(), list));
	}

	/**
	 * Get the command for setting initial bounds of a Time Observation or Constraint representation
	 *
	 * @param cver
	 *            the request
	 * @return command or null if none is appropriate
	 */
	private Command getCommandForTimeObservationOrConstraint(CreateViewRequest cvr, ViewDescriptor viewDescriptor) {
		Object loc = cvr.getExtendedData().get(SequenceRequestConstant.OCCURRENCE_SPECIFICATION_LOCATION);
		if (loc instanceof Point) {
			IFigure parentFigure = ((IGraphicalEditPart) getHost()).getFigure();
			Point referencePoint = ((Point) loc).getCopy();
			parentFigure.translateToRelative(referencePoint);
			referencePoint.translate(parentFigure.getBounds().getLocation().getCopy().negate());
			// Get the height of the element
			int newHeight = getFigureHeight(viewDescriptor.getSemanticHint(), cvr.getSize());
			// Define the bounds of the new time element
			Rectangle newBounds = new Rectangle(referencePoint.x, referencePoint.y - newHeight / 2, -1, newHeight);
			TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
			return new ICommandProxy(new SetBoundsCommand(editingDomain, DiagramUIMessages.SetLocationCommand_Label_Resize, viewDescriptor, newBounds));
		}
		return null;
	}

	/**
	 * Get the command for setting initial bounds of a Duration Constraint representation
	 *
	 * @param cver
	 *            the request
	 * @return command or null if none is appropriate
	 */
	private Command getCommandForDurationConstraint(CreateViewRequest cvr, ViewDescriptor viewDescriptor) {
		Object locTop = cvr.getExtendedData().get(SequenceRequestConstant.OCCURRENCE_SPECIFICATION_LOCATION);
		Object locBottom = cvr.getExtendedData().get(SequenceRequestConstant.OCCURRENCE_SPECIFICATION_LOCATION_2);
		if (locTop instanceof Point && locBottom instanceof Point) {
			IFigure parentFigure = ((IGraphicalEditPart) getHost()).getFigure();
			Point referenceTop = ((Point) locTop).getCopy();
			Point referenceBottom = ((Point) locBottom).getCopy();
			// Get the height of the element
			int newHeight = referenceBottom.y - referenceTop.y;
			if (newHeight > 0) {
				parentFigure.translateToRelative(referenceTop);
				Point parentFigDelta = parentFigure.getBounds().getLocation().getCopy().negate();
				referenceTop.translate(parentFigDelta);
				// Define the bounds of the new time element
				Rectangle newBounds = new Rectangle(referenceTop.x, referenceTop.y, -1, newHeight);
				newBounds = CustomDurationConstraintEditPart.fixMessageBounds(newBounds, cvr, (LifelineEditPart) getHost());
				TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
				return new ICommandProxy(new SetBoundsCommand(editingDomain, DiagramUIMessages.SetLocationCommand_Label_Resize, viewDescriptor, newBounds));
			} else if (newHeight < 0) {
				parentFigure.translateToRelative(referenceBottom);
				Point parentFigDelta = parentFigure.getBounds().getLocation().getCopy().negate();
				referenceBottom.translate(parentFigDelta);
				// Define the bounds of the new time element
				Rectangle newBounds = new Rectangle(referenceBottom.x, referenceBottom.y, -1, -newHeight);
				newBounds = CustomDurationConstraintEditPart.fixMessageBounds(newBounds, cvr, (LifelineEditPart) getHost());
				TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
				return new ICommandProxy(new SetBoundsCommand(editingDomain, DiagramUIMessages.SetLocationCommand_Label_Resize, viewDescriptor, newBounds));
			}
		}
		return null;
	}

	/**
	 * Get the adapted height, taking in account the represented figure
	 *
	 * @param cr
	 *            the create request
	 * @return the height defined in the create request or a default value depending on the created figure
	 */
	private int getFigureHeight(String semanticHint, Dimension size) {
		int newHeight = 10;
		if (TIME_OBSERVATION_HINT.equals(semanticHint) || TIME_CONSTRAINT_HINT.equals(semanticHint)) {
			// height for a time bar (takes precedence on request's size)
			newHeight = TIME_BAR_HEIGHT;
		} else if (size != null) {
			// heigh from request
			newHeight = size.height;
		} else {
			newHeight = EXECUTION_INIT_HEIGHT;
		}
		return newHeight;
	}

	/**
	 * Useful operation to know where the figure of a ExecutionSpecification EditPart should be
	 * positioned within a Lifeline EditPart. The notToCheckList is needed to avoid checking those
	 * ExecutionSpecification EditParts. The returned bounds are relative to the Lifeline figure so
	 * they can be used, directly, within a SetBoundsCommand.
	 *
	 * @param lifelineEP
	 *            the lifeline ep
	 * @param oldBounds
	 *            The old bounds of the ES
	 * @param newBounds
	 *            The new initial bounds
	 * @param notToCheckExecutionSpecificationList
	 *            The ExecutionSpecification EditPart's List that won't be checked
	 *
	 * @return The new bounds of the executionSpecificationEP figure
	 */
	protected final static Rectangle getExecutionSpecificationNewBounds(boolean isMove, LifelineEditPart lifelineEP, Rectangle oldBounds, Rectangle newBounds, List<ShapeNodeEditPart> notToCheckExecutionSpecificationList, boolean useFixedXPos) {
		// Lifeline's figure where the child is drawn
		Rectangle dotLineBounds = lifelineEP.getPrimaryShape().getFigureLifelineDotLineFigure().getBounds();
		// if ExecutionSpecification is resize outside of the lifeline bounds
		if (newBounds.y <= dotLineBounds.y || newBounds.x < dotLineBounds.x || newBounds.x > dotLineBounds.right()) {
			return null;
		}
		List<ShapeNodeEditPart> toCheckExecutionSpecificationList = LifelineEditPartUtil.getChildShapeNodeEditPart(lifelineEP);
		toCheckExecutionSpecificationList.removeAll(notToCheckExecutionSpecificationList);
		if (isMove) {
			ShapeNodeEditPart parent = getParent(lifelineEP, newBounds, toCheckExecutionSpecificationList);
			if (useFixedXPos) {
				newBounds.x = oldBounds.x;
			} else if (parent == null) {
				// No mother, center position
				int width = newBounds.width > 0 ? newBounds.width : EXECUTION_INIT_WIDTH;
				newBounds.x = dotLineBounds.x + dotLineBounds.width / 2 - width / 2;
			} else {
				Rectangle parentBounds = parent.getFigure().getBounds();
				int width = parentBounds.width > 0 ? parentBounds.width : EXECUTION_INIT_WIDTH;
				newBounds.x = parentBounds.x + width / 2 + 1;
			}
		} else {
			ShapeNodeEditPart oldParent = getParent(lifelineEP, oldBounds, toCheckExecutionSpecificationList);
			// forbid resize if the new bounds exceed Y-wise the bounds of a non-parent ES
			for (ShapeNodeEditPart esPart : toCheckExecutionSpecificationList) {
				Rectangle esBounds = esPart.getFigure().getBounds();
				int esYBottom = esBounds.y + esBounds.height;
				if (esPart != oldParent) {
					if (((oldBounds.y + oldBounds.height) <= esBounds.y && (newBounds.y + newBounds.height) >= esBounds.y) || (oldBounds.y >= esYBottom && newBounds.y <= esYBottom)) {
						return null;
					}
				}
			}
		}
		// Change to relative bounds of the LifelineEP
		newBounds.x -= dotLineBounds.x;
		newBounds.y -= dotLineBounds.y;
		return newBounds;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
		// This policy is hosted in a LifelineEditPart
		LifelineEditPart lifelineEP = (LifelineEditPart) getHost();
		Command command = getResizeOrMoveChildrenCommand(lifelineEP, request, false, true, false);
		if (command == null) {
			command = super.getResizeChildrenCommand(request);
		}
		return command;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getMoveChildrenCommand(Request request) {
		// This policy is hosted in a LifelineEditPart
		LifelineEditPart lifelineEP = (LifelineEditPart) getHost();
		Command command = getResizeOrMoveChildrenCommand(lifelineEP, (ChangeBoundsRequest) request, true, true, false);
		if (command == null) {
			command = super.getMoveChildrenCommand(request);
		}
		return command;
	}

	@SuppressWarnings("unchecked")
	public static Command getResizeOrMoveChildrenCommand(LifelineEditPart lifelineEP, ChangeBoundsRequest request, boolean isMove, boolean updateEnclosingInteraction, boolean useFixedXPos) {
		List<EditPart> editParts = request.getEditParts();
		if (editParts != null) {
			CompoundCommand compoundCmd = new CompoundCommand();
			compoundCmd.setLabel("Move or resize");
			compoundCmd.setDebugLabel("Debug: Move or resize of a Lifeline's children");
			for (EditPart ep : editParts) {
				if (ep instanceof CCombinedCompartmentEditPart || ep instanceof BehaviorExecutionSpecificationEditPart) {
					// an execution specification have been moved or resized
					ShapeNodeEditPart executionSpecificationEP = (ShapeNodeEditPart) ep;
					// Check if height is within the limits of the figure
					Dimension newSizeDelta = adaptSizeDeltaToMaxHeight(executionSpecificationEP.getFigure(), request.getSizeDelta());
					// Current bounds of the ExecutionSpecification
					Rectangle oldBounds = executionSpecificationEP.getFigure().getBounds().getCopy();
					Rectangle newBounds = oldBounds.getCopy();
					// According to the parameters, the new bounds would be the following
					if (request instanceof AlignmentRequest) {
						AlignmentRequest alignmentRequest = (AlignmentRequest) request;
						// Horizontal-only alignment is not allowed
						switch (alignmentRequest.getAlignment()) {
						case PositionConstants.LEFT:
						case PositionConstants.CENTER:
						case PositionConstants.RIGHT:
						case PositionConstants.HORIZONTAL:
							return UnexecutableCommand.INSTANCE;
						}
						newBounds = alignmentRequest.getAlignmentRectangle().getCopy();
						executionSpecificationEP.getFigure().translateToRelative(newBounds);
						// Remove X component of the alignment
						newBounds.x = oldBounds.x;
					} else {
						Dimension unZoomedMoveDelta = new Dimension(request.getMoveDelta().x, request.getMoveDelta().y);
						executionSpecificationEP.getFigure().translateToRelative(unZoomedMoveDelta);
						newBounds.x += unZoomedMoveDelta.width;
						newBounds.y += unZoomedMoveDelta.height;

						Dimension unZoomedSizeDelta = newSizeDelta.getCopy();
						executionSpecificationEP.getFigure().translateToRelative(unZoomedSizeDelta);
						newBounds.height += unZoomedSizeDelta.height;
					}
					// Not to check list
					List<ShapeNodeEditPart> notToCheckExecutionSpecificationList = new BasicEList<ShapeNodeEditPart>();
					// Affixed ExecutionSpecification List
					notToCheckExecutionSpecificationList.addAll(getAffixedExecutionSpecificationEditParts(executionSpecificationEP));
					// Add also current ExecutionSpecification EditPart
					notToCheckExecutionSpecificationList.add(executionSpecificationEP);
					// find parent bar
					List<ShapeNodeEditPart> executionSpecificationList = LifelineEditPartUtil.getChildShapeNodeEditPart(lifelineEP);
					executionSpecificationList.remove(executionSpecificationEP);
					ShapeNodeEditPart parentBar = getParent(lifelineEP, newBounds, executionSpecificationList);
					// change bounds to relative
					newBounds = getExecutionSpecificationNewBounds(isMove, lifelineEP, oldBounds, newBounds, notToCheckExecutionSpecificationList, useFixedXPos);
					if (newBounds == null) {
						return UnexecutableCommand.INSTANCE;
					}
					if (parentBar != null) {
						compoundCmd.add(resizeParentExecutionSpecification(lifelineEP, parentBar, newBounds.getCopy(), executionSpecificationList));
					}
					// Create and add the command to the compound command
					SetBoundsCommand setBoundsCmd = new SetBoundsCommand(executionSpecificationEP.getEditingDomain(), "Resize of a ExecutionSpecification", executionSpecificationEP, newBounds);
					compoundCmd.add(new ICommandProxy(setBoundsCmd));
					Rectangle realMoveDelta = getRealMoveDelta(getRelativeBounds(executionSpecificationEP.getFigure()), newBounds);
					if (isMove) {
						// Move also children
						compoundCmd.add(createMovingAffixedExecutionSpecificationCommand(executionSpecificationEP, realMoveDelta, newBounds.getCopy()));
						compoundCmd.add(createZOrderCommand(lifelineEP, executionSpecificationEP, newBounds.getCopy(), notToCheckExecutionSpecificationList));
					}
					// Move also linked Time elements
					compoundCmd = OccurrenceSpecificationMoveHelper.completeMoveExecutionSpecificationCommand(compoundCmd, executionSpecificationEP, newBounds, request);
					IFigure parentFigure = executionSpecificationEP.getFigure().getParent();
					parentFigure.translateToAbsolute(newBounds);
					// translateToAbsolute only does half of the work, I don't know why
					newBounds.translate(parentFigure.getBounds().getLocation());
					if (updateEnclosingInteraction) {
						// update the enclosing interaction of a moved execution specification
						compoundCmd.add(SequenceUtil.createUpdateEnclosingInteractionCommand(executionSpecificationEP, request.getMoveDelta(), newSizeDelta));
					}
					// keep absolute position of anchors
					compoundCmd.add(new ICommandProxy(new OLDLifelineEditPart.PreserveAnchorsPositionCommandEx(executionSpecificationEP, new Dimension(realMoveDelta.width, realMoveDelta.height), PreserveAnchorsPositionCommand.PRESERVE_Y,
							executionSpecificationEP.getFigure(), request.getResizeDirection())));
				}
				// if (ep instanceof CombinedFragment2EditPart) {
				// CombinedFragment2EditPart cf2EP = (CombinedFragment2EditPart) ep;
				// IFigure cf2Figure = cf2EP.getFigure();
				// Rectangle bounds = cf2Figure.getBounds().getCopy();
				// cf2Figure.getParent().translateToAbsolute(bounds);
				// Dimension sizeDelta = request.getSizeDelta();
				// if (sizeDelta != null) {
				// if (sizeDelta.width != 0) {
				// return UnexecutableCommand.INSTANCE;
				// }
				// bounds.resize(sizeDelta);
				// }
				// Point moveDelta = request.getMoveDelta();
				// if (moveDelta != null) {
				// bounds.translate(moveDelta);
				// }
				// // Create and add the set bounds command to the compound command
				// SetBoundsCommand setBoundsCmd = new SetBoundsCommand(cf2EP.getEditingDomain(), "Resize of a CoRegion", cf2EP, getNewBoundsForChild(lifelineEP, bounds, COREGION_INIT_WIDTH));
				// compoundCmd.add(new ICommandProxy(setBoundsCmd));
				// // keep absolute position of anchors
				// if (sizeDelta != null && sizeDelta.height != 0) {
				// compoundCmd.add(new ICommandProxy(new OLDLifelineEditPart.PreserveAnchorsPositionCommandEx(cf2EP, new Dimension(0, sizeDelta.height), PreserveAnchorsPositionCommand.PRESERVE_Y, cf2EP.getPrimaryShape().getCentralVerticalLine(),
				// request.getResizeDirection())));
				// }
				// }
				if (ep instanceof DestructionOccurrenceSpecificationEditPart) {
					Rectangle rectLifeline = lifelineEP.getFigure().getBounds();
					compoundCmd = getSetLifelineHeightCommand(compoundCmd, lifelineEP, rectLifeline.height + request.getMoveDelta().y);
				}
			}
			if (!compoundCmd.isEmpty()) {
				return compoundCmd;
			}
		}
		return null;
	}

	/**
	 * Modifies Lifeline's height and keeps anchors' positions intact.
	 *
	 * @param compoundCmd
	 *            command to add to
	 * @param lifelineEP
	 *            Lifeline's edit part
	 * @param newHeight
	 *            new height of the Lifeline
	 *
	 * @return the compound command
	 */
	public static CompoundCommand getSetLifelineHeightCommand(CompoundCommand compoundCmd, LifelineEditPart lifelineEP, int newHeight) {
		Rectangle rectLifeline = lifelineEP.getFigure().getBounds().getCopy();
		Dimension zoomedAddon = new Dimension(0, DESTRUCTION_INIT_SIZE / 2);
		lifelineEP.getFigure().translateToRelative(zoomedAddon);
		int minimumHeight = ((OLDLifelineEditPart) lifelineEP).getMinimumHeight(-1, true) + zoomedAddon.height;
		newHeight = Math.max(newHeight, minimumHeight);
		int heightDelta = newHeight - rectLifeline.height;
		if (heightDelta == 0) {
			return compoundCmd;
		}
		rectLifeline.height += heightDelta;
		ChangeBoundsRequest lifelineResizeRequest = new ChangeBoundsRequest();
		lifelineResizeRequest.setSizeDelta(new Dimension(0, heightDelta));
		lifelineResizeRequest.setResizeDirection(PositionConstants.NORTH);
		lifelineResizeRequest.setEditParts(lifelineEP);
		compoundCmd.add(new ICommandProxy(new SetBoundsCommand(lifelineEP.getEditingDomain(), "Move of Lifeline/DOS", lifelineEP, rectLifeline)));
		PreserveAnchorsPositionCommandEx preserveAnchorsCommand = new PreserveAnchorsPositionCommandEx(lifelineEP, new Dimension(0, heightDelta), PreserveAnchorsPositionCommand.PRESERVE_Y, lifelineEP.getPrimaryShape()
				.getFigureLifelineDotLineFigure(), PositionConstants.SOUTH);
		compoundCmd.add(new ICommandProxy(preserveAnchorsCommand));
		return compoundCmd;
	}

	/**
	 * Command for change ZOrder of ExecutionSpecification ordered from parent to children.
	 *
	 * @param lifelineEP
	 *            the lifeline ep
	 * @param executionSpecificationEP
	 *            the execution specification ep
	 * @param newBounds
	 *            the new bounds
	 * @param notToCheckExecutionSpecificationList
	 *            the not to check bes list
	 *
	 * @return the command
	 */
	protected final static Command createZOrderCommand(LifelineEditPart lifelineEP, ShapeNodeEditPart executionSpecificationEP, Rectangle newBounds, List<ShapeNodeEditPart> notToCheckExecutionSpecificationList) {
		List<ShapeNodeEditPart> toCheckExecutionSpecificationList = LifelineEditPartUtil.getChildShapeNodeEditPart(lifelineEP);
		toCheckExecutionSpecificationList.removeAll(notToCheckExecutionSpecificationList);
		CompoundCommand cmd = new CompoundCommand();
		for (ShapeNodeEditPart externalExecutionSpecificationEP : toCheckExecutionSpecificationList) {
			Rectangle externalExecutionSpecificationBounds = getRelativeBounds(externalExecutionSpecificationEP.getFigure());
			// Check if there is any contact
			if (externalExecutionSpecificationBounds.touches(newBounds)) {
				View containerView = ViewUtil.getContainerView(executionSpecificationEP.getPrimaryView());
				if (containerView != null) {
					int i = 0;
					int parentIndex = -1;
					int childIndex = -1;
					for (Object child : containerView.getChildren()) {
						if (child == externalExecutionSpecificationEP.getPrimaryView()) {
							parentIndex = i;
						} else if (child == executionSpecificationEP.getPrimaryView()) {
							childIndex = i;
						}
						if (parentIndex != -1 && childIndex != -1) {
							if (childIndex > parentIndex) {
								cmd.add(new ICommandProxy(new CustomZOrderCommand(executionSpecificationEP.getEditingDomain(), executionSpecificationEP.getPrimaryView(), parentIndex)));
								cmd.add(new ICommandProxy(new CustomZOrderCommand(externalExecutionSpecificationEP.getEditingDomain(), externalExecutionSpecificationEP.getPrimaryView(), childIndex)));
							} else {
								break;
							}
						}
						i++;
					}
				}
			}
		}
		if (!cmd.isEmpty()) {
			return cmd;
		}
		return null;
	}

	/**
	 * Useful operation to know where the figure of a ExecutionSpecification EditPart should be
	 * positioned within a Lifeline EditPart. The notToCheckList is needed to avoid checking those
	 * ExecutionSpecification EditParts. The returned bounds are relative to the Lifeline figure so
	 * they can be used, directly, within a SetBoundsCommand.
	 *
	 * @param lifelineDotLineFigure
	 *            TODO
	 * @param newBounds
	 *            The new initial bounds
	 * @param executionSpecifactionEditPart
	 *            TODO
	 * @param notToCheckExecutionSpecificationList
	 *            The ExecutionSpecification EditPart's List that won't be checked
	 *
	 * @return The new bounds of the executionSpecificationEP figure
	 */
	/**
	 * Get the (futur) parent of a ExecutionSpecification
	 *
	 * @param lifelinePart
	 *
	 * @param childBounds
	 *            the child bounds
	 * @param toCheckExecutionSpecificationList
	 *            List of EditPart to check
	 * @return The parent
	 */
	public final static ShapeNodeEditPart getParent(LifelineEditPart lifelinePart, Rectangle childBounds, List<ShapeNodeEditPart> toCheckExecutionSpecificationList) {
		ShapeNodeEditPart parent = null;
		// Loop through the ExecutionSpecification list and try to find the most to the right
		// ExecutionSpecification within the executionSpecificationEP Y-axis bounds
		Rectangle externalBounds = childBounds.getCopy();
		for (ShapeNodeEditPart externalExecutionSpecificationEP : toCheckExecutionSpecificationList) {
			Rectangle externalExecutionSpecificationBounds = externalExecutionSpecificationEP.getFigure().getBounds();
			externalBounds.x = externalExecutionSpecificationBounds.x;
			externalBounds.width = externalExecutionSpecificationBounds.width;
			if (externalExecutionSpecificationBounds.touches(externalBounds) && externalExecutionSpecificationBounds.x <= childBounds.x) {
				if (parent == null || externalExecutionSpecificationBounds.x > parent.getFigure().getBounds().x) {
					parent = externalExecutionSpecificationEP;
				}
			}
		}
		return parent;
	}

	/**
	 * Used to modify the sizeDelta if the given value is higher/lower than the highest/lowest
	 * allowed values of the figure.
	 *
	 * @param figure
	 *            the figure
	 * @param sizeDelta
	 *            the size delta
	 *
	 * @return a corrected sizeDelta
	 */
	public final static Dimension adaptSizeDeltaToMaxHeight(IFigure figure, Dimension sizeDelta) {
		Dimension newSizeDelta = new Dimension(sizeDelta);
		int figureHeight = figure.getBounds().height;
		int maximunFigureHeight = figure.getMaximumSize().height;
		int minimunFigureHeight = figure.getMinimumSize().height;
		int height = figureHeight + newSizeDelta.height;
		if (height > maximunFigureHeight) {
			newSizeDelta.height = maximunFigureHeight - figureHeight;
		} else if (height < minimunFigureHeight) {
			newSizeDelta.height = minimunFigureHeight - figureHeight;
		}
		return newSizeDelta;
	}

	/**
	 * Returns all the ExecutionSpecification EditParts that are affixed to the right side of the
	 * given ExecutionSpecification EditPart. Not only the ones directly affixed to the
	 * executionSpecificationEP are returned, but the ones that are indirectly affixed as well (this
	 * is done recursively)
	 *
	 * @param executionSpecificationEP
	 *            the execution specification ep
	 *
	 * @return the list of affixed ExecutionSpecification. If there is no affixed
	 *         ExecutionSpecification, then an empty list will be returned
	 */
	public final static List<ShapeNodeEditPart> getAffixedExecutionSpecificationEditParts(ShapeNodeEditPart executionSpecificationEP) {
		List<ShapeNodeEditPart> notToCheckExecutionSpecificationList = new ArrayList<ShapeNodeEditPart>();
		return getAffixedExecutionSpecificationEditParts(executionSpecificationEP, notToCheckExecutionSpecificationList);
	}

	/**
	 * Operation used by the above operation. It's main goal is to obtain, recursively, all the
	 * affixed ExecutionSpecification. In order to do so, it is needed a ExecutionSpecification
	 * EditPart and the notToCheckList.
	 *
	 * @param executionSpecificationEP
	 *            the execution specification ep
	 * @param notToCheckExecutionSpecificationList
	 *            the not to check ExecutionSpecification list
	 *
	 * @return the list of affixed ExecutionSpecification. If there is no affixed
	 *         ExecutionSpecification, then an empty list will be returned
	 */
	protected final static List<ShapeNodeEditPart> getAffixedExecutionSpecificationEditParts(ShapeNodeEditPart executionSpecificationEP, List<ShapeNodeEditPart> notToCheckExecutionSpecificationList) {
		// Add itself to the notToCheck list
		List<ShapeNodeEditPart> newNotToCheckExecutionSpecificationList = new ArrayList<ShapeNodeEditPart>(notToCheckExecutionSpecificationList);
		newNotToCheckExecutionSpecificationList.add(executionSpecificationEP);
		// LifelineEditPart where the ExecutionSpecification EditPart is contained
		LifelineEditPart lifelineEP = (LifelineEditPart) executionSpecificationEP.getParent();
		// ExecutionSpecification EditParts list
		List<ShapeNodeEditPart> executionSpecificationList = LifelineEditPartUtil.getChildShapeNodeEditPart(lifelineEP);
		executionSpecificationList.removeAll(newNotToCheckExecutionSpecificationList);
		// List to store the Affixed ExecutionSpecification
		List<ShapeNodeEditPart> affixedExecutionSpecificationList = new ArrayList<ShapeNodeEditPart>();
		// Loop ExecutionSpecificationough the ExecutionSpecification list
		for (ShapeNodeEditPart childExecutionSpecificationEP : executionSpecificationList) {
			if (isAffixedToRight(executionSpecificationEP.getFigure().getBounds(), childExecutionSpecificationEP.getFigure().getBounds())) {
				affixedExecutionSpecificationList.add(childExecutionSpecificationEP);
				// Add also it's affixed ExecutionSpecification
				affixedExecutionSpecificationList.addAll(getAffixedExecutionSpecificationEditParts(childExecutionSpecificationEP, newNotToCheckExecutionSpecificationList));
			}
		}
		// To the ExecutionSpecification list
		return affixedExecutionSpecificationList;
	}

	/**
	 * Checks whether the right EditPart is affixed to the left EditPart. In order to do so, the
	 * operation checks if the right figure is really on the right and, if so, it just returns true
	 * if figures touch each other.
	 *
	 * @param leftFigure
	 *            The left rectangle
	 * @param rightFigure
	 *            The right rectangle
	 *
	 * @return true if the rectangles of both figures touch and the right figure is really on the
	 *         right. False otherwise
	 */
	public final static boolean isAffixedToRight(Rectangle leftFigure, Rectangle rightFigure) {
		// return leftFigure.touches(rightFigure) && leftFigure.x < rightFigure.x;
		return leftFigure.contains(rightFigure.getLocation()) && leftFigure.x < rightFigure.x;
	}

	/**
	 * If a ExecutionSpecification EditPart is going to be moved according to a moveDelta, this
	 * operation returns a compoundCommand that also moves the affixed ExecutionSpecification
	 * according to that delta.
	 *
	 * @param executionSpecificationEP
	 *            The ExecutionSpecification EditPart that is going to be moved
	 * @param moveDelta
	 *            The moveDelta of the previous EditPart
	 * @param newBounds
	 *            the new bounds
	 *
	 * @return the compound command
	 */
	protected final static CompoundCommand createMovingAffixedExecutionSpecificationCommand(ShapeNodeEditPart executionSpecificationEP, Rectangle moveDelta, Rectangle newBounds) {
		if (moveDelta.y != 0 || moveDelta.height != 0) {
			CompoundCommand compoundCmd = new CompoundCommand();
			for (ShapeNodeEditPart childExecutionSpecificationEP : getAffixedExecutionSpecificationEditParts(executionSpecificationEP)) {
				// Get Relative Bounds
				Rectangle childBounds = getRelativeBounds(childExecutionSpecificationEP.getFigure());
				// Apply delta
				childBounds.y += moveDelta.y;
				childBounds.x += moveDelta.x;
				// Create the child's SetBoundsCommand
				SetBoundsCommand childSetBoundsCmd = new SetBoundsCommand(executionSpecificationEP.getEditingDomain(), "Movement of affixed ExecutionSpecification", childExecutionSpecificationEP, childBounds);
				compoundCmd.add(new ICommandProxy(childSetBoundsCmd));
				IFigure parentFigure = childExecutionSpecificationEP.getFigure().getParent();
				parentFigure.translateToAbsolute(newBounds);
				// translateToAbsolute only does half of the work, I don't know why
				newBounds.translate(parentFigure.getBounds().getLocation());
				// change the enclosing interaction of the moved affixed child if necessary
				compoundCmd.add(SequenceUtil.createUpdateEnclosingInteractionCommand(childExecutionSpecificationEP, moveDelta.getLocation(), moveDelta.getSize()));
				ChangeBoundsRequest request = new ChangeBoundsRequest();
				request.setMoveDelta(new Point(0, moveDelta.y));
				OccurrenceSpecificationMoveHelper.completeMoveExecutionSpecificationCommand(compoundCmd, childExecutionSpecificationEP, childBounds.getCopy(), request);
				// Move it's children as well
				if (!getAffixedExecutionSpecificationEditParts(childExecutionSpecificationEP).isEmpty()) {
					compoundCmd.add(createMovingAffixedExecutionSpecificationCommand(childExecutionSpecificationEP, moveDelta, childBounds));
				}
			}
			if (!compoundCmd.isEmpty()) {
				return compoundCmd;
			}
		}
		return null;
	}

	/**
	 * Given an AbstractGraphialEditPart and the new relative bounds that the EditPart will have, it
	 * returns the real delta applied to the movement.
	 *
	 * @param oldRelativeBounds
	 *            The old position of the mentioned EditPart
	 * @param newRelativeBounds
	 *            The new position of the mentioned EditPart
	 *
	 * @return The real MoveDelta applied
	 */
	public final static Rectangle getRealMoveDelta(Rectangle oldRelativeBounds, Rectangle newRelativeBounds) {
		Rectangle realMoveDelta = new Rectangle();
		realMoveDelta.x = newRelativeBounds.x - oldRelativeBounds.x;
		realMoveDelta.y = newRelativeBounds.y - oldRelativeBounds.y;
		realMoveDelta.height = newRelativeBounds.height - oldRelativeBounds.height;
		realMoveDelta.width = newRelativeBounds.width - oldRelativeBounds.width;
		return realMoveDelta;
	}

	/**
	 * It returns the relative bounds of an Figure.
	 *
	 * @param figure
	 *            The Figure
	 *
	 * @return The relative bounds regarding it's parent figure
	 */
	public final static Rectangle getRelativeBounds(IFigure figure) {
		Rectangle relBounds = figure.getBounds().getCopy();
		Rectangle parentRectangle = figure.getParent().getBounds();
		relBounds.x -= parentRectangle.x;
		relBounds.y -= parentRectangle.y;
		return relBounds;
	}
}
