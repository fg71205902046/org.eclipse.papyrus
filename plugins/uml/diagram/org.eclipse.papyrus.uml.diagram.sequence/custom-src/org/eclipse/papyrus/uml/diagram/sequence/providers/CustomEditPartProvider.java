/*****************************************************************************
 * Copyright (c) 2010 CEA
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Soyatec - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.providers;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DummyEditPart;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.*;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.sequence.referencialgrilling.GrillingEditpart;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;

/**
 * @author Jin Liu (jin.liu@soyatec.com)
 */
public class CustomEditPartProvider extends UMLEditPartProvider {

	@Override
	protected IGraphicalEditPart createEditPart(View view) {
		IGraphicalEditPart customEditPart = createCustomEditPart(view);
		if (customEditPart != null) {
			return customEditPart;
		}
		return super.createEditPart(view);
	}

	protected IGraphicalEditPart createCustomEditPart(View view) {
		if (GateEditPart.GATE_TYPE.equals(view.getType())) {
			return new GateEditPart(view);
		} else if (GateNameEditPart.GATE_NAME_TYPE.equals(view.getType())) {
			return new GateNameEditPart(view);
		} else if (InteractionOperandGuardEditPart.GUARD_TYPE.equals(view.getType())) {
			return new InteractionOperandGuardEditPart(view);
		} else if (BehaviorExecutionSpecificationBehaviorEditPart.BEHAVIOR_TYPE.equals(view.getType())) {
			return new BehaviorExecutionSpecificationBehaviorEditPart(view);
		}
		if (view instanceof Connector) {
			if (((Connector) view).getType().equals(SequenceUtil.OBSERVATION_LINK_TYPE)) {
				return new ObservationLinkEditPart(view);
			}
		}
		switch (UMLVisualIDRegistry.getVisualID(view)) {
//		case SequenceDiagramEditPart.VISUAL_ID:
//			return new OLDPackageEditPart(view);
		case InteractionEditPart.VISUAL_ID:
			return new CInteractionEditPart(view);
		case GrillingEditpart.VISUAL_ID:
			return new 	GrillingEditpart(view);
//		case InteractionNameEditPart.VISUAL_ID:
//			return new CustomInteractionNameEditPart(view);
//		case ConsiderIgnoreFragmentEditPart.VISUAL_ID:
//			return new CustomConsiderIgnoreFragmentEditPart(view);
		case CombinedFragmentEditPart.VISUAL_ID:
			return new CCombinedFragmentEditPart(view);
	//	case CombinedFragmentCombinedFragmentCompartmentEditPart.VISUAL_ID:
		//	return new CCombinedCompartmentEditPart(view);

//		case InteractionOperandEditPart.VISUAL_ID:
//			return new CustomInteractionOperandEditPart(view);
//		case InteractionUseEditPart.VISUAL_ID:
//			return new CustomInteractionUseEditPart(view);
//		case InteractionUseNameEditPart.VISUAL_ID:
//			return new CustomInteractionUseNameEditPart(view);
//		case InteractionUseName2EditPart.VISUAL_ID:
//			return new CustomInteractionUseName2EditPart(view);
//		case ContinuationEditPart.VISUAL_ID:
//			return new CustomContinuationEditPart(view);
		case LifelineEditPart.VISUAL_ID:
			return new CLifeLineEditPart(view);
//		case LifelineNameEditPart.VISUAL_ID:
//			return new CustomLifelineNameEditPart(view);
//		case ActionExecutionSpecificationEditPart.VISUAL_ID:
//			return new CustomActionExecutionSpecificationEditPart(view);
//		case BehaviorExecutionSpecificationEditPart.VISUAL_ID:
//			return new CustomBehaviorExecutionSpecificationEditPart(view);
//		case StateInvariantEditPart.VISUAL_ID:
//			return new CustomStateInvariantEditPart(view);
//		case CombinedFragment2EditPart.VISUAL_ID:
//			return new CustomCombinedFragment2EditPart(view);
//		case TimeConstraintEditPart.VISUAL_ID:
//			return new CustomTimeConstraintEditPart(view);
//		case TimeConstraintAppliedStereotypeEditPart.VISUAL_ID:
//			return new CustomTimeConstraintAppliedStereotypeEditPart(view);
//		case TimeConstraintLabelEditPart.VISUAL_ID:
//			return new CustomTimeConstraintLabelEditPart(view);
//		case TimeObservationEditPart.VISUAL_ID:
//			return new CustomTimeObservationEditPart(view);
//		case TimeObservationLabelEditPart.VISUAL_ID:
//			return new CustomTimeObservationLabelEditPart(view);
//		case TimeObservationAppliedStereotypeEditPart.VISUAL_ID:
//			return new CustomTimeObservationAppliedStereotypeEditPart(view);
//		case DurationConstraintEditPart.VISUAL_ID:
//			return new CustomDurationConstraintEditPart(view);
//		case DurationConstraintAppliedStereotypeEditPart.VISUAL_ID:
//			return new CustomDurationConstraintAppliedStereotypeEditPart(view);
//		case DestructionOccurrenceSpecificationEditPart.VISUAL_ID:
//			return new CustomDestructionOccurrenceSpecificationEditPart(view);
//		case ConstraintEditPart.VISUAL_ID:
//			return new CustomConstraintEditPart(view);
//		case Constraint2EditPart.VISUAL_ID:
//			return new CustomConstraint2EditPart(view);
//		case CommentEditPart.VISUAL_ID:
//			return new CustomCommentEditPart(view);
//		case CommentBodyEditPart.VISUAL_ID:
//			return new CustomCommentBodyEditPart(view);
//		case DurationConstraintInMessageEditPart.VISUAL_ID:
//			return new CustomDurationConstraintInMessageEditPart(view);
//		case DurationConstraintInMessageAppliedStereotypeEditPart.VISUAL_ID:
//			return new CustomDurationConstraintInMessageAppliedStereotypeEditPart(view);
//		case DurationObservationEditPart.VISUAL_ID:
//			return new CustomDurationObservationEditPart(view);
//		case DurationObservationAppliedStereotypeEditPart.VISUAL_ID:
//			return new CustomDurationObservationAppliedStereotypeEditPart(view);
//		case InteractionInteractionCompartmentEditPart.VISUAL_ID:
//			return new CustomInteractionInteractionCompartmentEditPart(view);
//		case CombinedFragmentCombinedFragmentCompartmentEditPart.VISUAL_ID:
//			return new CustomCombinedFragmentCombinedFragmentCompartmentEditPart(view);
//		case MessageSyncEditPart.VISUAL_ID:
//			return new CustomMessageSyncEditPart(view);
//		case MessageSyncNameEditPart.VISUAL_ID:
//			return new CustomMessageNameEditPart(view);
//		case MessageAsyncEditPart.VISUAL_ID:
//			return new CustomMessageAsyncEditPart(view);
//		case MessageAsyncNameEditPart.VISUAL_ID:
//			return new CustomMessageName2EditPart(view);
//		case MessageReplyEditPart.VISUAL_ID:
//			return new CustomMessageReplyEditPart(view);
//		case MessageReplyNameEditPart.VISUAL_ID:
//			return new CustomMessageName3EditPart(view);
//		case MessageCreateEditPart.VISUAL_ID:
//			return new CustomMessageCreateEditPart(view);
//		case MessageCreateNameEditPart.VISUAL_ID:
//			return new CustomMessageName4EditPart(view);
//		case MessageDeleteEditPart.VISUAL_ID:
//			return new CustomMessageDeleteEditPart(view);
//		case MessageDeleteNameEditPart.VISUAL_ID:
//			return new CustomMessageName5EditPart(view);
//		case MessageLostEditPart.VISUAL_ID:
//			return new CustomMessageLostEditPart(view);
//		case MessageLostNameEditPart.VISUAL_ID:
//			return new CustomMessageName6EditPart(view);
//		case MessageFoundEditPart.VISUAL_ID:
//			return new CustomMessageFoundEditPart(view);
//		case MessageFoundNameEditPart.VISUAL_ID:
//			return new CustomMessageName7EditPart(view);
//		case CommentAnnotatedElementEditPart.VISUAL_ID:
//			return new CustomCommentAnnotatedElementEditPart(view);
//		case GeneralOrderingEditPart.VISUAL_ID:
//			return new CustomGeneralOrderingEditPart(view);
//		case ExecutionSpecificationEndEditPart.VISUAL_ID:
//			return new ExecutionSpecificationEndEditPart(view);
//		case MessageEndEditPart.VISUAL_ID:
//			return new MessageEndEditPart(view);
//		case StateInvariantLabelEditPart.VISUAL_ID:
//			return new CustomStateInvariantLabelEditPart(view);
		}
		return null;
	}
}
