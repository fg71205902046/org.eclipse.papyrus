/*****************************************************************************
 * Copyright (c) 2013, 2017, 2021 Borland Software Corporation, CEA LIST, Artal and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Svyatoslav Kovalsky (Montages) - initial API and implementation
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : 1.4 Merge papyrus extension templates into codegen.xtend
 *****************************************************************************/
package xpt.diagram.editpolicies

import com.google.inject.Inject
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram
import xpt.Common
import xpt.CodeStyle

/** 
 * FIXME: [MG] - reverse the calls? call common code from here and TextelectionEP and not vice versa 
*/
@com.google.inject.Singleton class TextSelectionEditPolicy {
	
	@Inject extension Common;

	@Inject extension CodeStyle xptCodeStyle;
	
	def className(GenDiagram it) '''«it.textSelectionEditPolicyClassName»'''

	def packageName(GenDiagram it) '''«it.editPoliciesPackageName»'''

	def qualifiedClassName(GenDiagram it) '''«packageName(it)».«className(it)»'''
	
	def fullPath(GenDiagram it) '''«qualifiedClassName(it)»'''
	
	def TextSelectionEditPolicy_extendsClause(GenDiagram it) '''extends org.eclipse.gef.editpolicies.SelectionEditPolicy'''

	def TextSelectionEditPolicy_implementsClause(GenDiagram it) '''implements org.eclipse.papyrus.infra.gmfdiag.tooling.runtime.edit.policies.labels.IRefreshableFeedbackEditPolicy'''

	def TextSelectionEditPolicy_additions(GenDiagram it) ''''''
	
	def TextSelectionEditPolicyInvocation(GenDiagram it) '''«TextSelectionEditPolicy(it)»'''

	def TextSelectionEditPolicy(GenDiagram it) '''
		«copyright(editorGen)»
		package «packageName(it)»;
		
		«generatedClassComment»
		public class «className(it)» «TextSelectionEditPolicy_extendsClause(it)» «TextSelectionEditPolicy_implementsClause(
			it)» {
		
			«textFeedback(it)»
			
			«TextSelectionEditPolicy_additions(it)»
		}
	'''
	
	def textFeedback(GenDiagram it) '''
		«textFeedback_fields(it)» 
		
		«textFeedback_showPrimarySelection(it)»
		
		«textFeedback_showSelection(it)»
		
		«textFeedback_hideSelection(it)»
		
		«textFeedback_showFocus(it)»
		
		«textFeedback_hideFocus(it)»
		
		«textFeedback_getFeedbackBounds(it)»
		
		«textFeedback_createSelectionFeedbackFigure(it)»
		
		«textFeedback_createFocusFeedbackFigure(it)»
		
		«textFeedback_updateLabel(it)»
		
		«textFeedback_refreshSelectionFeedback(it)»
		
		«textFeedback_refreshFocusFeedback(it)»
		
		«textFeedback_refreshFeedback(it)»
		
		«textFeedback_getHostPositionListener(it)»
	'''

	def textFeedback_fields(GenDiagram it) '''
		«generatedMemberComment»
		private org.eclipse.draw2d.IFigure selectionFeedbackFigure;
		
		«generatedMemberComment»
		private org.eclipse.draw2d.IFigure focusFeedbackFigure;
		
		«generatedMemberComment»
		private org.eclipse.draw2d.FigureListener hostPositionListener;
	'''

	def textFeedback_showPrimarySelection(GenDiagram it) '''
		«generatedMemberComment»
		protected void showPrimarySelection() {
			if (getHostFigure() instanceof org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) {
				((org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) getHostFigure()).setSelected(true);
				((org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) getHostFigure()).setFocus(true);
			} else {
				showSelection();
				showFocus();
			}
		}
	'''

	def textFeedback_showSelection(GenDiagram it) '''
		«generatedMemberComment»
		protected void showSelection() {
			if (getHostFigure() instanceof org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) {
				((org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) getHostFigure()).setSelected(true);
				((org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) getHostFigure()).setFocus(false);
			} else {
				hideSelection();
				addFeedback(selectionFeedbackFigure = createSelectionFeedbackFigure());
				getHostFigure().addFigureListener(getHostPositionListener());
				refreshSelectionFeedback();
				hideFocus();
			}
		}
	'''

	def textFeedback_hideSelection(GenDiagram it) '''
		«generatedMemberComment»
		protected void hideSelection() {
			if (getHostFigure() instanceof org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) {
				((org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) getHostFigure()).setSelected(false);
				((org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) getHostFigure()).setFocus(false);
			} else {
				if (selectionFeedbackFigure != null) {
					removeFeedback(selectionFeedbackFigure);
					getHostFigure().removeFigureListener(getHostPositionListener());
					selectionFeedbackFigure = null;
				}
				hideFocus();
			}
		}
	'''

	def textFeedback_showFocus(GenDiagram it) '''
		«generatedMemberComment»
		protected void showFocus() {
			if (getHostFigure() instanceof org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) {
				((org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) getHostFigure()).setFocus(true);
			} else {
				hideFocus();
				addFeedback(focusFeedbackFigure = createFocusFeedbackFigure());
				refreshFocusFeedback();
			}
		}
	'''

	def textFeedback_hideFocus(GenDiagram it) '''
		«generatedMemberComment»
		protected void hideFocus() {
			if (getHostFigure() instanceof org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) {
				((org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel) getHostFigure()).setFocus(false);
			} else {
				if (focusFeedbackFigure != null) {
					removeFeedback(focusFeedbackFigure);
					focusFeedbackFigure = null;
			}
			}
		}
	'''

	def textFeedback_getFeedbackBounds(GenDiagram it) '''
		«generatedMemberComment»
		protected org.eclipse.draw2d.geometry.Rectangle getFeedbackBounds() {
			org.eclipse.draw2d.geometry.Rectangle bounds;
			if (getHostFigure() instanceof org.eclipse.draw2d.Label) {
				bounds = ((org.eclipse.draw2d.Label) getHostFigure()).getTextBounds();
				bounds.intersect(getHostFigure().getBounds());
			} else {
				bounds = getHostFigure().getBounds().getCopy();
			}
			getHostFigure().getParent().translateToAbsolute(bounds);
			getFeedbackLayer().translateToRelative(bounds);
			return bounds;
		}
	'''

	def textFeedback_createSelectionFeedbackFigure(GenDiagram it) '''
		«generatedMemberComment»
		protected org.eclipse.draw2d.IFigure createSelectionFeedbackFigure() {
			if (getHostFigure() instanceof org.eclipse.draw2d.Label) {
				org.eclipse.draw2d.Label feedbackFigure = new org.eclipse.draw2d.Label();
				feedbackFigure.setOpaque(true);
				feedbackFigure.setBackgroundColor(org.eclipse.draw2d.ColorConstants.menuBackgroundSelected);
				feedbackFigure.setForegroundColor(org.eclipse.draw2d.ColorConstants.menuForegroundSelected);
				return feedbackFigure;
			} else {
				org.eclipse.draw2d.RectangleFigure feedbackFigure = new org.eclipse.draw2d.RectangleFigure();
				feedbackFigure.setFill(false);
				return feedbackFigure;
			}
		}
	'''

	def textFeedback_createFocusFeedbackFigure(GenDiagram it) '''
		«generatedMemberComment»
		protected org.eclipse.draw2d.IFigure createFocusFeedbackFigure() {
			return new org.eclipse.draw2d.Figure() {
		
			«overrideC»
			protected void paintFigure(org.eclipse.draw2d.Graphics graphics) {
				graphics.drawFocus(getBounds().getResized(-1, -1));
			}
			};
		}
	'''

	def textFeedback_updateLabel(GenDiagram it) '''
		«generatedMemberComment»
		protected void updateLabel(org.eclipse.draw2d.Label target) {
			org.eclipse.draw2d.Label source = (org.eclipse.draw2d.Label) getHostFigure();
			target.setText(source.getText());
			target.setTextAlignment(source.getTextAlignment());
			target.setFont(source.getFont());
		}
	'''

	def textFeedback_refreshSelectionFeedback(GenDiagram it) '''
		«generatedMemberComment»
		protected void refreshSelectionFeedback() {
			if (selectionFeedbackFigure != null) {
				if (selectionFeedbackFigure instanceof org.eclipse.draw2d.Label) {
					updateLabel((org.eclipse.draw2d.Label) selectionFeedbackFigure);
					selectionFeedbackFigure.setBounds(getFeedbackBounds());
				} else {
					selectionFeedbackFigure.setBounds(getFeedbackBounds().expand(5, 5));
				}
			}
		}
	'''

	def textFeedback_refreshFocusFeedback(GenDiagram it) '''
		«generatedMemberComment»
		protected void refreshFocusFeedback() {
			if (focusFeedbackFigure != null) {
				focusFeedbackFigure.setBounds(getFeedbackBounds());
			}
		}
	'''

	def textFeedback_refreshFeedback(GenDiagram it) '''
		«generatedMemberComment»
		«xptCodeStyle.overrideI(it)»
		public void refreshFeedback() {
			refreshSelectionFeedback();
			refreshFocusFeedback();
		}
	'''

	def textFeedback_getHostPositionListener(GenDiagram it) '''
		«generatedMemberComment»
		private org.eclipse.draw2d.FigureListener getHostPositionListener() {
			if (hostPositionListener == null) {
				hostPositionListener = new org.eclipse.draw2d.FigureListener() {
					«overrideI»
					public void figureMoved(org.eclipse.draw2d.IFigure source) {
						refreshFeedback();
					}
				};
			}
			return hostPositionListener;
		}
	'''

}