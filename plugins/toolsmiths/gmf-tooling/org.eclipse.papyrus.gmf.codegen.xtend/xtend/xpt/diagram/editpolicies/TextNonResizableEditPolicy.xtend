/*****************************************************************************
 * Copyright (c) 2013, 2017 , 2021 Borland Software Corporation, CEA LIST, Artal and others
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
 * Benoit Maggi (CEA LIST) benoit.maggi@cea.fr - #510281 change dependency to replace gmft-runtime
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : 1.4 Merge papyrus extension templates into codegen.xtend
 *****************************************************************************/
package xpt.diagram.editpolicies

import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram
import com.google.inject.Inject
import xpt.Common

/** 
 * FIXME: [MG] - reverse the calls? call common code from here and TextelectionEP and not vice versa 
*/
@com.google.inject.Singleton class TextNonResizableEditPolicy {
	
	@Inject extension Common;
	
	@Inject	TextSelectionEditPolicy xptTextSelection
	
	def className(GenDiagram it) '''«it.textNonResizableEditPolicyClassName»'''

	def packageName(GenDiagram it) '''«it.editPoliciesPackageName»'''

	def qualifiedClassName(GenDiagram it) '''«packageName(it)».«className(it)»'''
	
	def fullPath(GenDiagram it) '''«qualifiedClassName(it)»'''
	
	def TextNonResizableEditPolicyInvocation(GenDiagram it) '''«TextNonResizableEditPolicy(it)»'''

	def TextNonResizableEditPolicy(GenDiagram it) '''
		«copyright(editorGen)»
		package «packageName(it)»;
		
		«generatedClassComment»
		public class «className(it)» «TextNonResizableEditPolicy_extendsClause(it)» «TextNonResizableEditPolicy_implementsClause(
			it)» {
		
			«xptTextSelection.textFeedback(it)»
			
			«TextNonResizableEditPolicy_createSelectionHandles(it)»
			
			«TextNonResizableEditPolicy_additions(it)»
		}
	'''

	def TextNonResizableEditPolicy_extendsClause(GenDiagram it) '''extends org.eclipse.gmf.runtime.diagram.ui.editpolicies.NonResizableEditPolicyEx'''

	def TextNonResizableEditPolicy_implementsClause(GenDiagram it)  '''implements org.eclipse.papyrus.infra.gmfdiag.tooling.runtime.edit.policies.labels.IRefreshableFeedbackEditPolicy'''

	def TextNonResizableEditPolicy_createSelectionHandles(GenDiagram it) '''
		«generatedMemberComment»
		protected java.util.List<?> createSelectionHandles() {
			org.eclipse.gef.handles.MoveHandle moveHandle =
				new org.eclipse.gef.handles.MoveHandle((org.eclipse.gef.GraphicalEditPart) getHost());
			moveHandle.setBorder(null);
			moveHandle.setDragTracker(new org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx(getHost()));
			return java.util.Collections.singletonList(moveHandle);
		}
	'''
	def TextNonResizableEditPolicy_additions(GenDiagram it) ''''''

}