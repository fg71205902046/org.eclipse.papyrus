/*****************************************************************************
 * Copyright (c) 2006, 2010, 2013, 2021 Borland Software Corporation, CEA LIST, Artal and others
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
 * Remi Schnekenburger (CEA LIST) - modification for Papyrus MDT
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : 1.4 Merge papyrus extension templates into codegen.xtend
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : Remove reference to gmfgraph and ModelViewMap
 *****************************************************************************/
package impl.diagram.editparts

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.FigureViewmap
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLink
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLinkLabel
import org.eclipse.papyrus.gmf.codegen.gmfgen.InnerClassViewmap
import org.eclipse.papyrus.gmf.codegen.gmfgen.ModeledViewmap
import org.eclipse.papyrus.gmf.codegen.gmfgen.ParentAssignedViewmap
import org.eclipse.papyrus.gmf.codegen.gmfgen.SnippetViewmap
import org.eclipse.papyrus.gmf.codegen.gmfgen.Viewmap
import xpt.Common
import xpt.Common_qvto

/**
 * Revisit: [MG]: @Inject extension same-named-api-class -> template extends api-class?
 */
@Singleton class LinkEditPart {
	@Inject extension Common;
	@Inject extension Common_qvto;
	
	@Inject xpt.diagram.editparts.Common xptEditpartsCommon;
	@Inject TextAware xptTextAware;

	def className(GenLink it) '''«editPartClassName»'''

	def packageName(GenLink it) '''«getDiagram().editPartsPackageName»'''

	def constructor(GenLink it) '''
		«generatedMemberComment»
		public «className(it)»(org.eclipse.gmf.runtime.notation.View view) {
			super(view);
		}
	'''

	def createDefaultEditPoliciesBody(GenLink it) '''
		super.createDefaultEditPolicies();
		«IF null == modelFacet»
			installEditPolicy(org.eclipse.gef.EditPolicy.COMPONENT_ROLE, new org.eclipse.gmf.runtime.diagram.ui.editpolicies.ViewComponentEditPolicy());
		«ENDIF»
		«xptEditpartsCommon.installSemanticEditPolicy(it)»
		«installGraphicalNodeEditPolicy(it)»
		«xptEditpartsCommon.behaviour(it)»
		«additionalEditPolicies(it)»
	'''
	
	def installGraphicalNodeEditPolicy(GenLink it) ''''''

	def additionalEditPolicies(GenLink it) ''''''

	/**
	 * FIXME: [MG] check counterpart for ModeledViewmap, 
	 */
	def addFixedChild(GenLink it) '''
	«IF labels.size > 0»
		«generatedMemberComment»
		protected boolean addFixedChild(org.eclipse.gef.EditPart childEditPart) {
			«FOR label : labels»
				«addLabel(label.viewmap,label)»
			«ENDFOR»
			return false;
		}
	«ENDIF»
'''

	// Note, condition in addFixedChild template above should be changed if addLabel support added for Viewmaps other than ParentAssignedViewmap
	def dispatch addLabel(Viewmap it, GenLinkLabel label) ''''''

	def dispatch addLabel(ParentAssignedViewmap it, GenLinkLabel label) '''
		«it.commonAddLabel(getterName,label)»
	'''

	def commonAddLabel(Viewmap it, String getterName, GenLinkLabel label) '''
		if (childEditPart instanceof «label.getEditPartQualifiedClassName()») {
			((«label.getEditPartQualifiedClassName()») childEditPart).«xptTextAware.labelSetterName(it)»(
					getPrimaryShape().«getterName»());
		}
	'''

	def removeFixedChild(GenLink it) '''
	«IF ! labels.empty»
		«generatedMemberComment»
		protected boolean removeFixedChild(org.eclipse.gef.EditPart childEditPart) {
			«FOR label : labels»
				«removeLabel(label.viewmap, label)»
			«ENDFOR»
			return false;
		}
	«ENDIF»
	'''

	// Note, condition in removeFixedChild template above should be changed if removeLabel support added for Viewmaps other than ParentAssignedViewmap
	def dispatch removeLabel(Viewmap it, GenLinkLabel label) ''''''

	def dispatch removeLabel(ParentAssignedViewmap it, GenLinkLabel label) '''
		«it.commonRemoveLabel(label)»
	'''

	def dispatch removeLabel(ModeledViewmap it, GenLinkLabel label) '''
		«it.commonRemoveLabel(label)»
	'''

	def commonRemoveLabel(Viewmap it, GenLinkLabel label) '''
		if (childEditPart instanceof «label.getEditPartQualifiedClassName()») {
			return true;
		}
	'''

	def addChildVisual(GenLink it) '''
	«IF ! labels.empty»
		«generatedMemberComment»
		protected void addChildVisual(org.eclipse.gef.EditPart childEditPart, int index) {
			if (addFixedChild(childEditPart)) {
				return;
			}
			super.addChildVisual(childEditPart, -1);
		}
	«ENDIF»
	'''

	def removeChildVisual(GenLink it) '''
	«IF ! labels.empty»
		«generatedMemberComment»
		protected void removeChildVisual(org.eclipse.gef.EditPart childEditPart) {
			if (removeFixedChild(childEditPart)) {
				return;
			}
			super.removeChildVisual(childEditPart);
		}
	«ENDIF»
	'''

	def createLinkFigure(GenLink it) '''
		«generatedMemberComment(
			'Creates figure for this edit part.\n' + '\n' +
				'Body of this method does not depend on settings in generation model\n' +
				'so you may safely remove <i>generated</i> tag and modify it.\n'
		)»
		«createLinkFigure(it.viewmap, it)»
	'''

	def dispatch createLinkFigure(Viewmap it, GenLink link) '''«ERROR('Unknown viewmap: ' + it + ", for link: " + link)»'''

	def dispatch createLinkFigure(ModeledViewmap it, GenLink link) '''
«««		«generatedMemberComment»
«««		protected org.eclipse.draw2d.Connection createConnectionFigure() {
«««			return new «modeledViewmapFigureFQN(it)»();
«««		}
«««		
«««		«generatedMemberComment»
«««		public «modeledViewmapFigureFQN(it)» getPrimaryShape() {
«««			return («modeledViewmapFigureFQN(it)») getFigure();
«««		}
«««		
«««		«xptModeledViewmapProducer.viewmapClassBody(it)»
	'''

	def modeledViewmapFigureFQN(ModeledViewmap it) '''
«««	«xptModeledViewmapProducer.viewmapFigureFQN(it)»
	'''

	def dispatch createLinkFigure(FigureViewmap it, GenLink link) {
		var fqn = if(figureQualifiedClassName == null) 'org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx' else figureQualifiedClassName
		'''
			protected org.eclipse.draw2d.Connection createConnectionFigure() {
				return new «fqn»();
			}
			
			«generatedMemberComment»
			public «fqn» getPrimaryShape() {
				return («fqn») getFigure();
			}
		'''
	}

	def dispatch createLinkFigure(SnippetViewmap it, GenLink link) '''
		protected org.eclipse.draw2d.Connection createConnectionFigure() {
			return «body»;
		}
	'''

	def dispatch createLinkFigure(InnerClassViewmap it, GenLink link) '''
		protected org.eclipse.draw2d.Connection createConnectionFigure() {
			return new «className»();
		}
		
		«generatedMemberComment»
		public «className» getPrimaryShape() {
			return («className») getFigure();
		}
		
		«classBody»
	'''
	
	/**
	 * FIXME: [MG] it looks like the ModeledViewmap is fixed, check that
	 * FIXME: [MG] and add the dispatch for modeled viewmaps then 
	 */
	def boolean hasFixedLabels(GenLink it){
		labels.notEmpty && (labels.filter(l | l.viewmap.oclIsKindOf(typeof(ParentAssignedViewmap))).notEmpty || labels.filter(l | l.viewmap.oclIsKindOf(typeof(ModeledViewmap))).notEmpty)
	}


	
	/**
	 * computes super type of the link edit part in case the edit part manages a representation of a UML element
	 */
	def extendsListContents(GenLink it)'''
	«IF superEditPart !== null»
	«superEditPart»
	«ELSE»
	org.eclipse.papyrus.infra.gmfdiag.common.editpart.ConnectionEditPart
	«ENDIF»
	'''
}



