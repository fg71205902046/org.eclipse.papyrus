/*****************************************************************************
 * Copyright (c) 2006, 2009, 2013, 2021 Borland Software Corporation, CEA LIST, Artal and others
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
package diagram.editparts

import com.google.inject.Inject
import impl.diagram.editparts.TextAware
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLinkLabel
import xpt.Common
import org.eclipse.papyrus.gmf.codegen.gmfgen.CustomBehaviour

@com.google.inject.Singleton class LinkLabelEditPart {
	@Inject extension Common;

	@Inject impl.diagram.editparts.LinkLabelEditPart xptLinkLabelEditPart;
	@Inject TextAware xptTextAware;
	@Inject xpt.diagram.editparts.Common xptEditpartsCommon;

	def qualifiedClassName(GenLinkLabel it) '''«xptLinkLabelEditPart.packageName(it)».«xptLinkLabelEditPart.className(it)»'''

	def fullPath(GenLinkLabel it) '''«qualifiedClassName(it)»'''

	def Main(GenLinkLabel it) '''
		«copyright(getDiagram().editorGen)»
		package «xptLinkLabelEditPart.packageName(it)»;
		
		«generatedClassComment»
		public class «xptLinkLabelEditPart.className(it)» «extendsList(it)» «implementsList(it)» {
		
			«attributes(it)»
			
			«xptLinkLabelEditPart.initializer(it)»
			
			«xptLinkLabelEditPart.constructor(it)»
			
			«createDefaultEditPolicies(it)»
			
			«xptLinkLabelEditPart.getKeyPoint(it)»
			
			«xptTextAware.methods(it, false, readOnly, elementIcon, viewmap, modelFacet, link, getDiagram())»
			
			«handleNotificationEvent(it)»
			
			«xptEditpartsCommon.labelFigure(it.viewmap)»
		
			«additions(it)»
		}
	'''

	def extendsList(GenLinkLabel it) '''extends org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusLabelEditPart'''

	def implementsList(GenLinkLabel it) '''
	implements org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart, org.eclipse.papyrus.infra.gmfdiag.common.editpart.IControlParserForDirectEdit
	«««	BEGIN: PapyrusGenCode
	«IF labelVisibilityPreference !== null»
	, org.eclipse.papyrus.uml.diagram.common.editparts.ILabelRoleProvider
	«ENDIF»
	«««	END: PapyrusGenCode
	'''

	def attributes(GenLinkLabel it) '''
		«xptEditpartsCommon.visualIDConstant(it)»
		
		«xptTextAware.fields(it)»
	'''

	def createDefaultEditPolicies(GenLinkLabel it) '''
	/**
	 * @generated Papyrus Generation
	 */
	@Override
	protected void createDefaultEditPolicies() {	
		super.createDefaultEditPolicies();
		installEditPolicy(org.eclipse.gef.EditPolicy.DIRECT_EDIT_ROLE, new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy());
		installEditPolicy(org.eclipse.gef.EditPolicy.SELECTION_FEEDBACK_ROLE, new «diagram.getTextSelectionEditPolicyQualifiedClassName()»());
		«««	BEGIN: PapyrusGenCode
		installEditPolicy(org.eclipse.gef.EditPolicy.PRIMARY_DRAG_ROLE, new org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.PapyrusLinkLabelDragPolicy());
		«««	END: PapyrusGenCode
		«««	Get the added custom behavoir
		«FOR CustomBehaviour:it.behaviour.filter(typeof (CustomBehaviour))»
		installEditPolicy(«CustomBehaviour.key», new «CustomBehaviour.editPolicyQualifiedClassName»());
		«ENDFOR»
	}
	'''

	def handleNotificationEvent(GenLinkLabel it) '''
		«generatedMemberComment»
		protected void handleNotificationEvent(org.eclipse.emf.common.notify.Notification event) {
			«xptLinkLabelEditPart.handleNotificationEventBody(it)»
		}
	'''

	def additions(GenLinkLabel it) '''
	«««	BEGIN: PapyrusGenCode
	«IF labelVisibilityPreference !== null»
		«generatedClassComment»
		public String getLabelRole(){
		return "«labelVisibilityPreference.role»";//$NON-NLS-1$
		}
		
		«generatedClassComment»
		public String getIconPathRole(){
		return "«labelVisibilityPreference.iconPathRole»";//$NON-NLS-1$
		}
	«ENDIF»
	«««	END: PapyrusGenCode
	'''
}
