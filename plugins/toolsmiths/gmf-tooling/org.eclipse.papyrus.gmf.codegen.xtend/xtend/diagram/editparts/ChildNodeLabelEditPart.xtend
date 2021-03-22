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
 * Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : 1.4 Merge papyrus extension templates into codegen.xtend
 *****************************************************************************/
package diagram.editparts

import com.google.inject.Inject
import impl.diagram.editparts.TextAware
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenChildLabelNode
import xpt.Common

@com.google.inject.Singleton class ChildNodeLabelEditPart {
	@Inject extension Common;

	@Inject impl.diagram.editparts.ChildNodeLabelEditPart xptChildNodeLabelEditPart;
	@Inject xpt.diagram.editparts.Common xptEditpartsCommon;
	@Inject TextAware xptTextAware;

	def qualifiedClassName(GenChildLabelNode it) '''«xptChildNodeLabelEditPart.packageName(it)».«xptChildNodeLabelEditPart.className(it)»'''

	def fullPath(GenChildLabelNode it) '''«qualifiedClassName(it)»'''

	def Main(GenChildLabelNode it) '''
«copyright(getDiagram().editorGen)»
package «xptChildNodeLabelEditPart.packageName(it)»;

«generatedClassComment»
public class «xptChildNodeLabelEditPart.className(it)» «extendsList(it)» «implementsList(it)» {
	
	«attributes(it)»
	
	«xptChildNodeLabelEditPart.constructor(it)»

	«getDragTracker(it)»
	
	«createDefaultEditPolicies(it)»

	«xptTextAware.methods(it, false, labelReadOnly, labelElementIcon, viewmap, labelModelFacet, it, getDiagram())»

	«xptEditpartsCommon.notationalListeners(it)»

	«handleNotificationEvent(it)»

	«xptEditpartsCommon.labelFigure(it.viewmap)»
	
	«xptChildNodeLabelEditPart.isSelectable(it)»
	
	«additions(it)»
}
'''

	def extendsList(GenChildLabelNode it) '''
«««BEGIN: PapyrusGenCode
«««Add own extension
«IF superEditPart !== null»
  extends «superEditPart»
«««END: PapyrusGenCode
«ELSE»
  extends org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart
«ENDIF»
'''

	def implementsList(GenChildLabelNode it) '''implements org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart, org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart, org.eclipse.papyrus.infra.gmfdiag.common.editpart.IControlParserForDirectEdit'''

	def attributes(GenChildLabelNode it) '''
		«xptEditpartsCommon.visualIDConstant(it)»
		
		«xptTextAware.fields(it)»
	'''

	def getDragTracker(GenChildLabelNode it) '''
		«generatedMemberComment»
		public org.eclipse.gef.DragTracker getDragTracker(org.eclipse.gef.Request request) {
			«xptChildNodeLabelEditPart.getDragTrackerBody(it)»
		}
	'''

	def createDefaultEditPolicies(GenChildLabelNode it) '''
		«generatedMemberComment»
		protected void createDefaultEditPolicies() {
			«xptChildNodeLabelEditPart.createDefaultEditPoliciesBody(it)»
		}
	'''

	def handleNotificationEvent(GenChildLabelNode it) '''
		«generatedMemberComment»
		protected void handleNotificationEvent(org.eclipse.emf.common.notify.Notification event) {
			«xptChildNodeLabelEditPart.handleNotificationEventBody(it)»
		}
	'''

	def additions(GenChildLabelNode it) ''''''

}
