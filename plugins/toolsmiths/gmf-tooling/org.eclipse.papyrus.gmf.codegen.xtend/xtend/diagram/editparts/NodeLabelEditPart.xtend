/*****************************************************************************
 * Copyright (c) 2006, 2009, 2013, 2021 Borland Software Corporation, CEA LIST, Artal and others
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
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenNodeLabel
import xpt.Common
import xpt.diagram.editparts.Utils_qvto

@com.google.inject.Singleton class NodeLabelEditPart {
	@Inject extension Common;
	@Inject extension Utils_qvto;

	@Inject impl.diagram.editparts.NodeLabelEditPart xptNodeLabelEditPart;
	@Inject TextAware xptTextAware;
	@Inject xpt.diagram.editparts.Common xptEditpartsCommon;

	def qualifiedClassName(GenNodeLabel it) '''«xptNodeLabelEditPart.packageName(it)».«xptNodeLabelEditPart.className(it)»'''

	def fullPath(GenNodeLabel it) '''«qualifiedClassName(it)»'''

	def Main(GenNodeLabel it) '''
		«copyright(getDiagram().editorGen)»
		package «xptNodeLabelEditPart.packageName(it)»;
		
		«generatedClassComment»
		public class «xptNodeLabelEditPart.className(it)» «extendsList(it)» «implementsList(it)» {
		
			«attributes(it)»
			
			«xptNodeLabelEditPart.constructor(it)»
			
			«createDefaultEditPolicies(it)»
			
			«xptTextAware.methods(it, isStoringChildPositions(node), readOnly, elementIcon, viewmap, modelFacet, node,
			getDiagram())»
		
			«xptEditpartsCommon.notationalListeners(it)»
			
			«xptNodeLabelEditPart.refreshBounds(it)»
			
			«handleNotificationEvent(it)»
			
			«xptEditpartsCommon.labelFigure(it.viewmap)»
			
			«additions(it)»
		}
	'''

	def extendsList(GenNodeLabel it) '''extends org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusCompartmentEditPart'''

	def implementsList(GenNodeLabel it) '''implements org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart, org.eclipse.papyrus.infra.gmfdiag.common.editpart.IControlParserForDirectEdit'''

	def attributes(GenNodeLabel it) '''
		«xptEditpartsCommon.visualIDConstant(it)»
		
		«xptTextAware.fields(it)»
	'''

	def createDefaultEditPolicies(GenNodeLabel it) '''
		«generatedMemberComment»
		protected void createDefaultEditPolicies() {
			«xptNodeLabelEditPart.createDefaultEditPoliciesBody(it)»
		}
	'''

	def handleNotificationEvent(GenNodeLabel it) '''
		«generatedMemberComment»
		protected void handleNotificationEvent(org.eclipse.emf.common.notify.Notification event) {
			refreshLabel();
			«xptNodeLabelEditPart.handleNotificationEventBody(it)»
		}
	'''


	def additions(GenNodeLabel it) '''
	«««	Code to refresh icon
	
	«generatedMemberComment»
	private static final String ADD_PARENT_MODEL = "AddParentModel";
	
		
	«generatedMemberComment»
		public void activate() {
			super.activate();
			addOwnerElementListeners();
		}
	
		«generatedMemberComment»
		protected void addOwnerElementListeners() {
			addListenerFilter(ADD_PARENT_MODEL, this, ((org.eclipse.gmf.runtime.notation.View) getParent().getModel()));
	
		}
	
		«generatedMemberComment»
		public void deactivate() {
			removeOwnerElementListeners();
			super.deactivate();
	
		}
	
	
		«generatedMemberComment»
		protected void removeOwnerElementListeners() {
			removeListenerFilter(ADD_PARENT_MODEL);
	
		}
	
	«««END: PapyrusGenCode
	'''
}
