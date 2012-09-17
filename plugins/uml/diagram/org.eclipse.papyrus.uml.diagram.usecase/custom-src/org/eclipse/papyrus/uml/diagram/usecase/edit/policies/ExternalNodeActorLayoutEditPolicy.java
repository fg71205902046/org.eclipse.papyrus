/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
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
package org.eclipse.papyrus.uml.diagram.usecase.edit.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorAppliedStereotypeEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorInComponentAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorInComponentNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorInPackageAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorInPackageNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorQualifiedNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorQualifiedNameInCEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorQualifiedNameInPEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.part.UMLVisualIDRegistry;

/**
 * This edit policy replaces the GMF generated edit policy for Port.
 * In particular it installs {@link ExternalLabelPrimaryDragRoleEditPolicy} on
 * children that are external label.
 * The code generated by GMF can be found in {@link PortEditPart#createLayoutEditPolicy()}.
 */
public class ExternalNodeActorLayoutEditPolicy extends LayoutEditPolicy {

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		View childView = (View)child.getModel();
		switch(UMLVisualIDRegistry.getVisualID(childView)) {
		case ActorNameEditPartTN.VISUAL_ID:
		case ActorAppliedStereotypeEditPartTN.VISUAL_ID:
		case ActorQualifiedNameEditPartTN.VISUAL_ID:
		//actor in package	
		case ActorInPackageNameEditPart.VISUAL_ID:
		case ActorQualifiedNameInPEditPart.VISUAL_ID:
		case ActorInPackageAppliedStereotypeEditPart.VISUAL_ID:
		//actor in component
		case ActorInComponentNameEditPart.VISUAL_ID:
		case ActorQualifiedNameInCEditPart.VISUAL_ID:
		case ActorInComponentAppliedStereotypeEditPart.VISUAL_ID:
			return new ExternalLabelPrimaryDragRoleEditPolicy();
		}
		EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if(result == null) {
			result = new NonResizableEditPolicy();
		}
		return result;
	}

	@Override
	protected Command getMoveChildrenCommand(Request request) {
		return null;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		return null;
	}
}
