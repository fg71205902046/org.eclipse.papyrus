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
 * Etienne ALLOGO (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : PapyrusGmfExtension epackage merge into gmfgen
 * 
 *****************************************************************************/
package aspects.diagram.editparts

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.CustomBehaviour
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLinkLabel
import xpt.Common

@Singleton class LinkLabelEditPart extends diagram.editparts.LinkLabelEditPart{
	@Inject extension Common;


	override implementsList(GenLinkLabel it) '''
	implements org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart, org.eclipse.papyrus.infra.gmfdiag.common.editpart.IControlParserForDirectEdit
	«««	BEGIN: PapyrusGenCode
	«IF labelVisibilityPreference !== null»
	, org.eclipse.papyrus.uml.diagram.common.editparts.ILabelRoleProvider
	«ENDIF»
	«««	END: PapyrusGenCode
	'''


	override additions(GenLinkLabel it) '''
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
	
	override extendsList(GenLinkLabel it) '''extends org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusLabelEditPart'''
	
	override createDefaultEditPolicies(GenLinkLabel it) '''
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
}
