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
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *****************************************************************************/
package xpt.diagram.editparts

import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase
import com.google.inject.Inject
import org.eclipse.papyrus.gmf.codegen.gmfgen.Behaviour
import org.eclipse.papyrus.gmf.codegen.gmfgen.CustomBehaviour
import xpt.Common_qvto
import org.eclipse.papyrus.gmf.codegen.gmfgen.OpenDiagramBehaviour
import org.eclipse.papyrus.gmf.codegen.gmfgen.ParentAssignedViewmap
import impl.diagram.editparts.TextAware
import org.eclipse.papyrus.gmf.codegen.gmfgen.ModeledViewmap
import org.eclipse.papyrus.gmf.gmfgraph.DiagramLabel
import org.eclipse.papyrus.gmf.codegen.gmfgen.Viewmap
import org.eclipse.papyrus.gmf.codegen.gmfgen.FigureViewmap
import org.eclipse.papyrus.gmf.codegen.gmfgen.SnippetViewmap
import org.eclipse.papyrus.gmf.codegen.gmfgen.InnerClassViewmap
import impl.diagram.editparts.viewmaps.modeledViewmapProducer
import org.eclipse.emf.ecore.EObject
import org.eclipse.papyrus.gmf.gmfgraph.Label
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenContainerBase
import xpt.editor.VisualIDRegistry

@com.google.inject.Singleton class Common {
	@Inject extension xpt.Common;
	@Inject extension Common_qvto;
	
	@Inject TextAware xptTextAware;
	@Inject modeledViewmapProducer xptModeledViewmapProducer;
	@Inject VisualIDRegistry xptVisualIDRegistry;
	
	def visualIDConstant(GenCommonBase it) '''
		«generatedMemberComment»
		public static final int VISUAL_ID = «visualID»;
	'''

	def behaviour(GenCommonBase it) '''
		«FOR b : it.behaviour»
			«dispatchBehaviour(b)»
		«ENDFOR»
	'''

	def dispatch dispatchBehaviour(Behaviour it) ''''''

	def dispatch dispatchBehaviour(CustomBehaviour it) '''
	«IF editPolicyQualifiedClassName.nullOrSpaces»
		removeEditPolicy(«key»); «IF key.startsWith('\"') && key.endsWith('\"')»«nonNLS(1)»«ENDIF»
	«ELSE»
		installEditPolicy(«key», new «getEditPolicyQualifiedClassName()»()); «IF key.startsWith('\"') && key.endsWith('\"')»«nonNLS(1)»«ENDIF»
	«ENDIF»
	'''
	
	def dispatch dispatchBehaviour(OpenDiagramBehaviour it) '''
		installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.OPEN_ROLE,
			new «it.getEditPolicyQualifiedClassName»());
	'''

	def dispatch labelFigure(ParentAssignedViewmap it) '''
		«generatedMemberComment»
		protected org.eclipse.draw2d.IFigure createFigure() {
			// Parent should assign one using «xptTextAware.labelSetterName(it)»() method
			return null;
		}
	'''

	def dispatch labelFigure(ModeledViewmap it) '''
		«IF figureModel.oclIsKindOf(typeof(DiagramLabel)) && (figureModel as DiagramLabel).accessor != null»
		«generatedMemberComment»
		protected org.eclipse.draw2d.IFigure createFigure() {
			// Parent should assign one using «xptTextAware.labelSetterName(it)»() method
			return null;
		}
		«ELSE»
			«labelFigureDelegateToPrim(it)»
		«ENDIF»
	'''

	def dispatch labelFigure(Viewmap it) '''
		«labelFigureDelegateToPrim(it)»
	'''

	def labelFigureDelegateToPrim(Viewmap it) '''
		«generatedMemberComment»
		protected org.eclipse.draw2d.IFigure createFigure() {
			org.eclipse.draw2d.IFigure label = createFigurePrim();
			defaultText = getLabelTextHelper(label);
			return label;
		}
	
		«generatedMemberComment»
		protected org.eclipse.draw2d.IFigure createFigurePrim() {
			«labelFigurePrim(it)»
	'''

	def dispatch labelFigurePrim(FigureViewmap it) '''
	«IF figureQualifiedClassName == null»
		return new org.eclipse.draw2d.Label();
	«ELSE»
		return new «figureQualifiedClassName»();
	«ENDIF»
		}
	'''

	def dispatch labelFigurePrim(SnippetViewmap it) '''
		return «body»;
		}
	'''

	def dispatch labelFigurePrim(InnerClassViewmap it) '''
		return new «className»();
		}

		«classBody»
	'''

	def dispatch labelFigurePrim(ModeledViewmap it) '''
		return new «xptModeledViewmapProducer.viewmapFigureFQN(it)»(«labelTextDefaultValue(figureModel)»);
		}
	'''

	def dispatch labelTextDefaultValue(EObject it) ''''''

	def dispatch labelTextDefaultValue(DiagramLabel it) '''
		«IF it.figure != null && it.figure.actualFigure.oclIsKindOf(typeof(Label)) && (it.figure.actualFigure as Label).text != null»
			"«(it.figure.actualFigure as Label).text»"
		«ENDIF»
	'''

	def dispatch labelFigurePrim(Viewmap it) '''
		«ERROR('Unknown viewmap: ' + it)»
	'''

	def notationalListeners(GenCommonBase it) '''
		«generatedMemberComment»
		protected void addNotationalListeners() {
			super.addNotationalListeners();
			addListenerFilter("PrimaryView", this, getPrimaryView()); «nonNLS(1)»
		}
	
		«generatedMemberComment»
		protected void removeNotationalListeners() {
			super.removeNotationalListeners();
			removeListenerFilter("PrimaryView"); «nonNLS(1)»
		}
	'''

	def handleBounds(GenCommonBase it) '''
		if (org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getSize_Width().equals(feature) ||
				org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getSize_Height().equals(feature) ||
				org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getLocation_X().equals(feature) ||
				org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getLocation_Y().equals(feature)) {
			refreshBounds();
		}
	'''

	def handleText(GenCommonBase it) '''
		if (org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
			Integer c = (Integer) event.getNewValue();
			setFontColor(org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry.getInstance().getColor(c));
		} else if (org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getFontStyle_Underline().equals(feature)) {
			refreshUnderline();
		} else if (org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getFontStyle_StrikeThrough().equals(feature)) {
			refreshStrikeThrough();
		} else if (org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(feature) ||
				org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getFontStyle_FontName().equals(feature) ||
				org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getFontStyle_Bold().equals(feature) ||
				org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getFontStyle_Italic().equals(feature)) {
			refreshFont();
		} else {
			if (getParser() != null && getParser().isAffectingEvent(event, getParserOptions().intValue())) {
				refreshLabel();
			}
			if (getParser() instanceof org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser) {
				org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser modelParser =
					(org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser) getParser();
				if (modelParser.areSemanticElementsAffected(null, event)) {
					removeSemanticListeners();
					if (resolveSemanticElement() != null) {
						addSemanticListeners();
					}
					refreshLabel();
				}
			}
		}
	'''

	def installSemanticEditPolicy(GenCommonBase it) '''
	«IF sansDomain»
	removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.SEMANTIC_ROLE);
	«ELSE»
	installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.SEMANTIC_ROLE, new «getItemSemanticEditPolicyQualifiedClassName()»());
	«ENDIF»
	'''

	def installCanonicalEditPolicy(GenContainerBase it) '''
	«IF it.needsCanonicalEditPolicy»
	installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CANONICAL_ROLE, new «getCanonicalEditPolicyQualifiedClassName()»());
	«ENDIF»
	'''

	def installCreationEditPolicy(GenCommonBase it) '''
	installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CREATION_ROLE, «creationEditPolicyNewInstance»);
	'''

	def creationEditPolicyNewInstance(GenCommonBase it) 
	'''new org.eclipse.gmf.tooling.runtime.edit.policies.reparent.CreationEditPolicyWithCustomReparent(«xptVisualIDRegistry.runtimeTypedInstanceCall(it.diagram)»)'''

}