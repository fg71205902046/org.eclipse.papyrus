/*****************************************************************************
 * Copyright (c) 2006, 2014, 2021 Borland Software Corporation, CEA LIST, Artal and others
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
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : 1.4 Merge papyrus extension templates into codegen.xtend
 *****************************************************************************/
package impl.diagram.editparts

import com.google.inject.Inject
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenExternalNodeLabel
import xpt.diagram.editparts.Common
import xpt.editor.VisualIDRegistry
import xpt.diagram.editpolicies.TextSelectionEditPolicy

@com.google.inject.Singleton class ExternalNodeLabelEditPart {
	@Inject extension xpt.Common;

	@Inject Common xptEditpartsCommon;
	@Inject VisualIDRegistry xptVisualIDRegistry;
	@Inject TextSelectionEditPolicy textSelestion;

	def className(GenExternalNodeLabel it) '''«editPartClassName»'''

	def packageName(GenExternalNodeLabel it) '''«getDiagram().editPartsPackageName»'''

	def initializer(GenExternalNodeLabel it) '''
		«generatedMemberComment»
		static {
			registerSnapBackPosition(«xptVisualIDRegistry.typeMethodCall(it)», new org.eclipse.draw2d.geometry.Point(0, 0));
		}
	'''

	def constructor(GenExternalNodeLabel it) '''
		«generatedMemberComment»
		public «className(it)»(org.eclipse.gmf.runtime.notation.View view) {
			super(view);
		}
	'''

	def createDefaultEditPoliciesBody(GenExternalNodeLabel it) '''
		super.createDefaultEditPolicies();
		installEditPolicy(org.eclipse.gef.EditPolicy.DIRECT_EDIT_ROLE, new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy());
		installEditPolicy(org.eclipse.gef.EditPolicy.SELECTION_FEEDBACK_ROLE, new «textSelestion.qualifiedClassName(getDiagram())»());
		«xptEditpartsCommon.behaviour(it)»
		«additionalEditPolicies(it)»
	'''

	def additionalEditPolicies(GenExternalNodeLabel it) 
	'''
		installEditPolicy(org.eclipse.gef.EditPolicy.PRIMARY_DRAG_ROLE, new org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.ExternalLabelPrimaryDragRoleEditPolicy());
	'''

	def getBorderItemLocator(GenExternalNodeLabel it) '''
		«generatedMemberComment»
		public org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator getBorderItemLocator() {
			org.eclipse.draw2d.IFigure parentFigure = getFigure().getParent();
			if (parentFigure != null && parentFigure.getLayoutManager() != null) {
				Object constraint = parentFigure.getLayoutManager().getConstraint(getFigure());
				return (org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator) constraint;
			}
			return null;
		}
	'''

	def refreshBounds(GenExternalNodeLabel it) '''
		«generatedMemberComment»
		public void refreshBounds() {
			int x = ((Integer) getStructuralFeatureValue(org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getLocation_X())).intValue();
			int y = ((Integer) getStructuralFeatureValue(org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getLocation_Y())).intValue();
			int width = ((Integer) getStructuralFeatureValue(org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getSize_Width())).intValue();
			int height = ((Integer) getStructuralFeatureValue(org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getSize_Height())).intValue();
			getBorderItemLocator().setConstraint(new org.eclipse.draw2d.geometry.Rectangle(x, y, width, height));
		}
	'''

	def handleNotificationEventBody(GenExternalNodeLabel it) '''
		Object feature = event.getFeature();
		«xptEditpartsCommon.handleText(it)»
		super.handleNotificationEvent(event);
	'''

	def createFigure(GenExternalNodeLabel it) '''
		«xptEditpartsCommon.labelFigure(viewmap)»
	'''	
}