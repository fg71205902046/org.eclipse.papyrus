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
 *    Artem Tikhomirov (Borland) - initial API and implementation
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *****************************************************************************/
package gmfgraph

import com.google.inject.Inject
import org.eclipse.papyrus.gmf.gmfgraph.Figure
import org.eclipse.papyrus.gmf.gmfgraph.FigureRef
import org.eclipse.papyrus.gmf.gmfgraph.RealFigure
import xpt.Common
import xpt.Common_qvto

@com.google.inject.Singleton class Children {
	@Inject extension Common
	@Inject extension Common_qvto
	@Inject extension Utils_qvto

	@Inject Attrs xptAttrs;
	@Inject LayoutData xptLayoutData;
	@Inject Layout xptLayout;
	@Inject Runtime xptRuntime;

	def CreateChildren(RealFigure it) '''
		«generatedMemberComment»
		private void createContents(){
			«FOR child : it.children»
				«instantiate(child, 0, it, 'this')»
			«ENDFOR»
			«extraLineBreak»
		}
	'''

	def dispatch CharSequence instantiate(Figure it, int count, RealFigure parentFigure, String parentFigureVariable) '''
		«ERROR('Abstract instantiate(int, RealFigure, String)')»
	'''

	def dispatch CharSequence instantiate(FigureRef it, int count, RealFigure parentFigure, String parentFigureVariable) '''
		«extraLineBreak»
		«instantiate(it.figure, count, parentFigure, parentFigureVariable)»
		// Process FigureRef details
		«populate(it, count, parentFigure, parentFigureVariable, figureVariableName(figure, count))»
		«extraLineBreak»
	'''

	def dispatch CharSequence instantiate(RealFigure it, int count, RealFigure parentFigure, String parentFigureVariable) '''
		«extraLineBreak»
		«newFigureInstance(it, figureVariableName(it, count))»
		«extraLineBreak»
		«populate(it, count, parentFigure, parentFigureVariable, figureVariableName(it, count))»
		«FOR c : it.children»
			«instantiate(c, count + 1, it, figureVariableName(it, count))» 
		«ENDFOR»
		«extraLineBreak»
	'''

	def populate(Figure it, int count, RealFigure parentFigure, String parentFigureVariable, String figureVarName) '''
		«extraLineBreak»
		«xptAttrs.Init(it, figureVarName)»
		«IF null == layoutData || null == parentFigure.layout /*Check for (parentLayout != null) to avoid generating data that won't be used*/»
			«extraLineBreak»
			«parentFigureVariable».add(«figureVarName»);
		«ELSE»
			«xptLayoutData.Init(layoutData, parentFigureVariable, figureVarName)»
		«ENDIF»
		«xptLayout.Init(layout, figureVarName)»
	'''

	def newFigureInstance(RealFigure it, String figureVarName) '''
		«IF needsField(it)»«figureVarName» = «xptRuntime.newInstance(it)»;«ELSE»«xptRuntime.newInstance(it, figureVarName)»«ENDIF»
	'''

}
