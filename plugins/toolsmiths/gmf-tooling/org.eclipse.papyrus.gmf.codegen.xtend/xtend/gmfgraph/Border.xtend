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
import gmfgraph.attr.CustomAttributeOwner
import org.eclipse.papyrus.gmf.gmfgraph.BorderRef
import org.eclipse.papyrus.gmf.gmfgraph.CompoundBorder
import org.eclipse.papyrus.gmf.gmfgraph.CustomBorder
import org.eclipse.papyrus.gmf.gmfgraph.Figure
import org.eclipse.papyrus.gmf.gmfgraph.LineBorder
import org.eclipse.papyrus.gmf.gmfgraph.MarginBorder
import xpt.Common
import xpt.Common_qvto

@com.google.inject.Singleton class Border {
	@Inject extension Common
	@Inject extension Common_qvto
	@Inject extension Utils_Statefull_qvto

	@Inject MapMode xptMapMode;
	@Inject gmfgraph.attr.Figure xptFigure;
	@Inject CustomAttributeOwner xptCustomAttributeOwner;
	@Inject Runtime xptRuntime;

	def Init(org.eclipse.papyrus.gmf.gmfgraph.Border border, String figureVarName) '''
		«IF border != null»
		«figureVarName».setBorder(«border(border, figureVarName)»);
		«ENDIF»
	'''

	def InitMethods(Figure it) '''
		«FOR b : getBordersInUse()»
			«initMethod(b, getBordersInUse().toList.indexOf(b))»
		«ENDFOR»
	'''

	def initMethod(CustomBorder it, Integer c) '''
		«generatedMemberComment()»
		private org.eclipse.draw2d.Border createBorder«c»() {
			«xptRuntime.newInstance(it, 'result')»
			«extraLineBreak»
			«xptCustomAttributeOwner.Init(it, 'result')»
			«extraLineBreak»
			return result;
		}
	'''

	def dispatch CharSequence border(Border it, String figureVarName) '''«ERROR(
		'This is an abstract definition border(String, Border) invoked for ' + it)»'''

	def dispatch CharSequence border(LineBorder it, String figureVarName) '''
		new org.eclipse.draw2d.LineBorder(«IF null == color»null«ELSE»«xptFigure.color(it.color,
			figureVarName.toUpperCase + '_BORDER')»«ENDIF», «xptMapMode.mapMode(width)»)
	'''

	def dispatch CharSequence border(MarginBorder it, String figureVarName) '''new org.eclipse.draw2d.MarginBorder(«xptMapMode.
		mapMode(insets)»)'''

	def dispatch CharSequence border(CompoundBorder it, String figureVarName) '''
		new org.eclipse.draw2d.CompoundBorder(«IF outer == null»null«ELSE»«border(outer, figureVarName)»«ENDIF», «IF inner ==
			null»null«ELSE»«border(inner, figureVarName)»«ENDIF»)
	'''

	def dispatch CharSequence border(CustomBorder it, String figureVarName) //
		'''createBorder«getBordersInUse().size»()«addBorder(it)»'''

	def dispatch CharSequence border(BorderRef it, String figureVarName) '''«border(actual, figureVarName)»'''
}
