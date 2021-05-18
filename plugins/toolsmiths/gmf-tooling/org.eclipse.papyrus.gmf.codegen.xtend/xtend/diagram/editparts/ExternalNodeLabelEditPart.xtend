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
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenExternalNodeLabel
import xpt.Common
import xpt.CodeStyle

@com.google.inject.Singleton class ExternalNodeLabelEditPart {
	@Inject extension Common;
	@Inject extension CodeStyle;

	@Inject xpt.diagram.editparts.Common xptEditpartsCommon;
	@Inject impl.diagram.editparts.ExternalNodeLabelEditPart xptExternalNodeLabelEditPart;
	@Inject TextAware xptTextAware

	def qualifiedClassName(GenExternalNodeLabel it) '''«xptExternalNodeLabelEditPart.packageName(it)».«xptExternalNodeLabelEditPart.className(it)»'''

	def fullPath(GenExternalNodeLabel it) '''«qualifiedClassName(it)»'''

	def Main(GenExternalNodeLabel it) '''
«copyright(getDiagram().editorGen)»
package «xptExternalNodeLabelEditPart.packageName(it)»;

«generatedClassComment»
public class «xptExternalNodeLabelEditPart.className(it)» «extendsList(it)» «implementsList(it)» {

	«attributes(it)»
	
	«xptExternalNodeLabelEditPart.initializer(it)»
	
	«xptExternalNodeLabelEditPart.constructor(it)»
	
	«createDefaultEditPolicies(it)»
	
	«xptExternalNodeLabelEditPart.getBorderItemLocator(it)»
	
	«xptExternalNodeLabelEditPart.refreshBounds(it)»
	
	«xptTextAware.methods(it, false, readOnly, elementIcon, viewmap, modelFacet, node, getDiagram())»
	
	«handleNotificationEvent(it)»
	
	«xptExternalNodeLabelEditPart.createFigure(it)»
	
	«additions(it)»
}
'''

	def extendsList(GenExternalNodeLabel it) '''
		«««BEGIN: PapyrusGenCode
	«««specify a java super class for external nodes
	«IF superEditPart !== null»
			extends «superEditPart»
		«««END: PapyrusGenCode
	«ELSE»
			extends org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusLabelEditPart
	«ENDIF»
	'''

	def implementsList(GenExternalNodeLabel it)  '''
	implements org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart, org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart
	«««	BEGIN: PapyrusGenCode
	«IF labelVisibilityPreference !== null»
		, org.eclipse.papyrus.uml.diagram.common.editparts.ILabelRoleProvider
	«ENDIF»
	«««	END: PapyrusGenCode
	'''

	def attributes(GenExternalNodeLabel it) '''
		«xptEditpartsCommon.visualIDConstant(it)»
		
		«xptTextAware.fields(it)»
	'''

	def createDefaultEditPolicies(GenExternalNodeLabel it) '''
		«generatedMemberComment»
		protected void createDefaultEditPolicies() {
			«xptExternalNodeLabelEditPart.createDefaultEditPoliciesBody(it)»
		}
	'''

	def handleNotificationEvent(GenExternalNodeLabel it) '''
		«generatedMemberComment»
		protected void handleNotificationEvent(org.eclipse.emf.common.notify.Notification event) {
			«xptExternalNodeLabelEditPart.handleNotificationEventBody(it)»
		}
	'''

	def additions(GenExternalNodeLabel it) '''
	«««	BEGIN: PapyrusGenCode
	«IF labelVisibilityPreference !== null»
		«generatedClassComment»
		«overrideI»
		public String getLabelRole(){
		return "«labelVisibilityPreference.role»";//$NON-NLS-1$
		}
		
		«generatedClassComment»
		«overrideI»
		public String getIconPathRole(){
		return "«labelVisibilityPreference.iconPathRole»";//$NON-NLS-1$
		}
	«ENDIF»
	«««	END: PapyrusGenCode
	'''

	def refreshBounds(GenExternalNodeLabel it) '''
		«««	BEGIN: PapyrusGenCode
		public void refreshBounds() {
			int x = ((Integer) getStructuralFeatureValue(org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getLocation_X())).intValue();
			int y = ((Integer) getStructuralFeatureValue(org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getLocation_Y())).intValue();
			int width = ((Integer) getStructuralFeatureValue(org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getSize_Width())).intValue();
			int height = ((Integer) getStructuralFeatureValue(org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getSize_Height())).intValue();
			getBorderItemLocator().setConstraint(new org.eclipse.draw2d.geometry.Rectangle(x, y, width, height));
			getBorderItemLocator().relocate(getFigure());
		}
    «««	END: PapyrusGenCode
	'''

}
