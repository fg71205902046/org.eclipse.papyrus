/*****************************************************************************
 * Copyright (c) 2006, 2009 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Dmitry Stadnik (Borland) - initial API and implementation
 * Alexander Shatalin (Borland) - initial API and implementation
 * Michael Golubev (Montages) - #386838 - migrate to Xtend2
 * 
 *****************************************************************************/
package aspects.impl.diagram.editparts

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCompartment
import xpt.diagram.editparts.Common
import org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView

//DOCUMENTATION: PapyrusGenCode
//Overload only the creation of editPolicies in order to add the paste edit policy
@Singleton class CompartmentEditPart extends impl.diagram.editparts.CompartmentEditPart {
	@Inject extension xpt.Common;
	@Inject Common xptEditpartsCommon;



		override createDefaultEditPoliciesBody(GenCompartment it) '''
		super.createDefaultEditPolicies();
		«IF canCollapse»
			installEditPolicy(org.eclipse.gef.EditPolicy.PRIMARY_DRAG_ROLE, new org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy());
		«ENDIF»
		«xptEditpartsCommon.installSemanticEditPolicy(it)»
		«IF ! childNodes.empty»
			installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CREATION_ROLE, new org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.DefaultCreationEditPolicy());
			installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.DRAG_DROP_ROLE, new org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy());
			installEditPolicy(org.eclipse.papyrus.uml.diagram.common.editpolicies.PasteEditPolicy.PASTE_ROLE, new org.eclipse.papyrus.uml.diagram.common.editpolicies.PasteEditPolicy());
		«ENDIF»
		«xptEditpartsCommon.installCanonicalEditPolicy(it)»
		«xptEditpartsCommon.behaviour(it)»
	'''
	
		override getTargetEditPartMethod(GenCompartment it) '''
		«generatedMemberComment»
		public org.eclipse.gef.EditPart getTargetEditPart(org.eclipse.gef.Request request) {

			return super.getTargetEditPart(request);
		}
	'''
	
	override createFigure(GenCompartment it) {
		if (hasExternalSuperClass(it,'org.eclipse.papyrus.uml.diagram.activity.edit.part.ShapeCompartmentWithoutScrollbarsEditPart')) {
			'''
				@Override
				public org.eclipse.draw2d.IFigure createFigure() {
					return super.createFigure();
				}
			'''
		} else {
			super.createFigure(it);
		}
	}

	def boolean hasExternalSuperClass(GenCompartment it, String className) {
		return it.eResource.allContents.filter(typeof(ExtendedGenView)).filter [v |	(v.genView.contains(it) && v.superOwnedEditPart.equals(className))].size > 0;
	}
}
