/*****************************************************************************
 * Copyright (c) 2006, 2009, 2013 Borland Software Corporation and others
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
package aspects.diagram.editparts

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenExternalNodeLabel
import org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView
import org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference
import xpt.Common

@Singleton class ExternalNodeLabelEditPart extends diagram.editparts.ExternalNodeLabelEditPart {
	@Inject extension Common;

	override extendsList(GenExternalNodeLabel it) '''
		«««BEGIN: PapyrusGenCode
	«««specify a java super class for external nodes
	«IF it.eResource.allContents.filter(typeof (ExtendedGenView)).filter[v | v.genView.contains(it) && v.superOwnedEditPart != null].size != 0»
			extends «FOR extendedObject : it.eResource.allContents.filter(typeof (ExtendedGenView)).filter[v|v.genView.contains(it) && v.superOwnedEditPart != null].toIterable»
				«specifyInheritance(extendedObject as ExtendedGenView)»
			«ENDFOR»
		«««END: PapyrusGenCode
	«ELSE»
			extends org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusLabelEditPart
		«ENDIF»
	'''

	//we add the interface ILabelRoleProvider
	override implementsList(GenExternalNodeLabel it) '''
	implements org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart, org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart
	«««	BEGIN: PapyrusGenCode
	«IF it.eResource.allContents.filter(typeof (LabelVisibilityPreference)).filter[v|v.externalNodeLabels.contains(it)].size != 0»
		, org.eclipse.papyrus.uml.diagram.common.editparts.ILabelRoleProvider
	«ENDIF»
	«««	END: PapyrusGenCode
	'''

	override additions(GenExternalNodeLabel it) '''
	«««	BEGIN: PapyrusGenCode
	«IF it.eResource.allContents.filter(typeof (LabelVisibilityPreference)).filter[v|v.externalNodeLabels.contains(it)].size != 0»
		«generatedClassComment»
		public String getLabelRole(){
		return "«it.eResource.allContents.filter(typeof (LabelVisibilityPreference)).filter[v|v.externalNodeLabels.contains(it)].head.role»";//$NON-NLS-1$
		}
		
		«generatedClassComment»
		public String getIconPathRole(){
		return "«it.eResource.allContents.filter(typeof (LabelVisibilityPreference)).filter[v|v.externalNodeLabels.contains(it)].head.iconPathRole»";//$NON-NLS-1$
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

	//BEGIN: PapyrusGenCode
	//definition of the inheritance 
	def specifyInheritance(ExtendedGenView it) '''«superOwnedEditPart»'''

//END: PapyrusGenCode
}
