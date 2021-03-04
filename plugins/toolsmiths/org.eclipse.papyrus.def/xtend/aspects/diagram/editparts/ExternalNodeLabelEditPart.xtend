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
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenExternalNodeLabel
import xpt.Common

@Singleton class ExternalNodeLabelEditPart extends diagram.editparts.ExternalNodeLabelEditPart {
	@Inject extension Common;

	override extendsList(GenExternalNodeLabel it) '''
		«««BEGIN: PapyrusGenCode
	«««specify a java super class for external nodes
	«IF superEditPart !== null»
			extends «superEditPart»
		«««END: PapyrusGenCode
	«ELSE»
			extends org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusLabelEditPart
	«ENDIF»
	'''

	//we add the interface ILabelRoleProvider
	override implementsList(GenExternalNodeLabel it) '''
	implements org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart, org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart
	«««	BEGIN: PapyrusGenCode
	«IF labelVisibilityPreference !== null»
		, org.eclipse.papyrus.uml.diagram.common.editparts.ILabelRoleProvider
	«ENDIF»
	«««	END: PapyrusGenCode
	'''

	override additions(GenExternalNodeLabel it) '''
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
