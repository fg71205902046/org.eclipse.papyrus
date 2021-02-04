/*******************************************************************************
 * Copyright (c) 2006, 2020 Borland Software Corporation, CEA LIST, Artal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *    Dmitry Stadnik (Borland) - initial API and implementation
 *    Alexander Shatalin (Borland) - initial API and implementation
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *****************************************************************************/
package diagram.editparts

import com.google.inject.Inject
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCompartment
import xpt.Common
import xpt.diagram.editparts.Utils_qvto

@com.google.inject.Singleton class CompartmentEditPart {
	@Inject extension Common;
	@Inject extension Utils_qvto;

	@Inject impl.diagram.editparts.CompartmentEditPart xptCompartmentEditPartImpl;
	@Inject xpt.diagram.editparts.Common xptEditpartsCommon;

	def qualifiedClassName(GenCompartment it) '''«xptCompartmentEditPartImpl.packageName(it)».«xptCompartmentEditPartImpl.className(it)»'''

	def fullPath(GenCompartment it) '''«qualifiedClassName(it)»'''

	def Main(GenCompartment it) '''
«copyright(getDiagram().editorGen)»
package «xptCompartmentEditPartImpl.packageName(it)»;

«generatedClassComment»
public class «xptCompartmentEditPartImpl.className(it)» «extendsList(it)» «implementsList(it)» {

	«attributes(it)»
	
	«xptCompartmentEditPartImpl.constructor(it)»
	
	«xptCompartmentEditPartImpl.hasModelChildrenChanged(it)»
	
	«xptCompartmentEditPartImpl.getCompartmentName(it)»
	
	«xptCompartmentEditPartImpl.createFigure(it)»
	
	«createDefaultEditPolicies(it)»
	
	«xptCompartmentEditPartImpl.refreshVisuals(it)»
	
	«handleNotificationEvent(it)»
	
	«xptCompartmentEditPartImpl.refreshBounds(it)»
	
	«xptCompartmentEditPartImpl.setRatio(it)»
	
	«xptCompartmentEditPartImpl.getTargetEditPartMethod(it)»
	
	«additions(it)»
}
'''

	def extendsList(GenCompartment it) '''
	extends «IF listLayout»org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart«ELSE»org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart«ENDIF»
	'''

	def implementsList(GenCompartment it) ''''''

	def attributes(GenCompartment it) '''
		«xptEditpartsCommon.visualIDConstant(it)»
	'''

	def createDefaultEditPolicies(GenCompartment it) '''
		«generatedMemberComment»
		protected void createDefaultEditPolicies() {
			«xptCompartmentEditPartImpl.createDefaultEditPoliciesBody(it)»
		}
	'''

	def handleNotificationEvent(GenCompartment it) '''
		«IF isStoringChildPositions(node)»
			«generatedMemberComment»
			protected void handleNotificationEvent(org.eclipse.emf.common.notify.Notification notification) {
				«xptCompartmentEditPartImpl.handleNotificationEventBody(it)»
			}
		«ENDIF»
	'''

	def additions(GenCompartment it) ''''''

}
