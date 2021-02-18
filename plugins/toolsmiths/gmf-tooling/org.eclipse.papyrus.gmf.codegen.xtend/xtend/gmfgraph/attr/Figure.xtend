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
package gmfgraph.attr

import com.google.inject.Inject
import xpt.Common
import org.eclipse.papyrus.gmf.gmfgraph.Color
import org.eclipse.papyrus.gmf.gmfgraph.Dimension
import gmfgraph.MapMode
import gmfgraph.Border
import org.eclipse.papyrus.gmf.gmfgraph.Insets
import org.eclipse.papyrus.gmf.gmfgraph.Point
import org.eclipse.papyrus.gmf.gmfgraph.Font
import org.eclipse.papyrus.gmf.gmfgraph.RGBColor
import xpt.Common_qvto
import org.eclipse.papyrus.gmf.gmfgraph.ConstantColor
import gmfgraph.Utils_qvto
import org.eclipse.papyrus.gmf.gmfgraph.BasicFont
import org.eclipse.papyrus.gmf.gmfgraph.RealFigure
import gmfgraph.Utils_Statefull_qvto

@com.google.inject.Singleton class Figure {
	@Inject extension Common_qvto
	@Inject extension Common
	@Inject extension Utils_qvto
	@Inject extension Utils_Statefull_qvto
	
	@Inject MapMode xptMapMode;
	@Inject Border xptBorder;
	@Inject CustomAttributeOwner xptCustomAttributeOwner;
	
	def figureAttrs(org.eclipse.papyrus.gmf.gmfgraph.Figure it, String figureVarName) '''
		«foregroundColor(it.foregroundColor, figureVarName)»
		«backgroundColor(it.backgroundColor, figureVarName)»
		«IF font != null»«font(font, figureVarName)»«ENDIF»
		«preferredSize(preferredSize, figureVarName)»
		«maximumSize(maximumSize, figureVarName)»
		«minimumSize(minimumSize, figureVarName)»
		«position(location, figureVarName)»
		«size(size, figureVarName)»
		«insets(insets, figureVarName)»
		«xptBorder.Init(it.border, figureVarName)»
		«dispatch_customAttributes(it, figureVarName)»
	'''

	def foregroundColor(Color color, String figureVarName) '''
		«IF color != null»
		«figureVarName».setForegroundColor(«color(color, foregroundColorVariableName(color, figureVarName))»);
		«ENDIF»
	'''
	
	def backgroundColor(Color color, String figureVarName) '''
		«IF color != null»
		«figureVarName».setBackgroundColor(«color(color, backgroundColorVariableName(color, figureVarName))»);
		«ENDIF»
	'''
	
	def preferredSize(Dimension dim, String figureVarName) '''
		«IF dim != null»
		«figureVarName».setPreferredSize(new org.eclipse.draw2d.geometry.Dimension(«xptMapMode.mapMode(dim)»));
		«ENDIF»
	'''
	
	def maximumSize(Dimension dim, String figureVarName) '''
		«IF dim != null»
		«figureVarName».setMaximumSize(new org.eclipse.draw2d.geometry.Dimension(«xptMapMode.mapMode(dim)»));
		«ENDIF»
	'''
	
	def minimumSize(Dimension dim, String figureVarName) '''
		«IF dim != null»
		«figureVarName».setMinimumSize(new org.eclipse.draw2d.geometry.Dimension(«xptMapMode.mapMode(dim)»));
		«ENDIF»
	'''
	
	def position(Point point, String figureVarName) '''
		«IF point != null»
		«figureVarName».setLocation(new org.eclipse.draw2d.geometry.Point(«xptMapMode.mapMode(point)»));
		«ENDIF»
	'''
	
	def size(Point point, String figureVarName) '''
		«IF point != null»
		«figureVarName».setSize(«xptMapMode.mapMode(point)»);
		«ENDIF»
	'''

	/**
	 * using MarginBorder for insets is not good idea (?)
	 */
	def insets(Insets insets, String figureVarName) '''
		«IF insets != null»
		«extraLineBreak»
		«figureVarName».setBorder(new org.eclipse.draw2d.MarginBorder(«xptMapMode.mapMode(insets)»));
		«ENDIF»
	'''
	
	def dispatch color(Color it, String variableName) '''
		«ERROR("This is abstract definition, missing concrete def for: " + it)»
	'''
	
	def dispatch color(RGBColor it, String variableName) '''
		«variableName»
		«addToStaticStream('''«color_staticFields(it, variableName)»''')»
	'''
	
	def dispatch color(ConstantColor it, String variableName) '''
		org.eclipse.draw2d.ColorConstants.«value»
	'''
	
	def dispatch color_staticFields(Color it, String variableName) ''''''
	
	def dispatch color_staticFields(RGBColor it, String variableName) '''
		«generatedMemberComment»
		static final org.eclipse.swt.graphics.Color «variableName» = new org.eclipse.swt.graphics.Color(null, «red», «green», «blue»);
		«extraLineBreak»
	'''
	
	def dispatch font(Font it, String figureVarName) '''
		«ERROR("This is abstract definition, missing concrete def for: " + it)»
	'''
	
	def dispatch font(BasicFont it, String figureVarName) '''
		«extraLineBreak»
		«figureVarName».setFont(«fontVariableName(it, figureVarName)»);
		«addToStaticStream('''«font_staticFields(it, figureVarName)»''')»
		«extraLineBreak»
	'''
	
	def dispatch font_staticFields(Font it, String figureVarName) ''''''
	
	def dispatch font_staticFields(BasicFont it, String figureVarName) '''
		«generatedMemberComment()»
		static final org.eclipse.swt.graphics.Font «fontVariableName(it, figureVarName)» = new org.eclipse.swt.graphics.Font(org.eclipse.swt.widgets.Display.getCurrent(), «
			IF it.faceName.nullOrSpaces()»org.eclipse.swt.widgets.Display.getDefault().getSystemFont().getFontData()[0].getName()«ELSE»"«faceName»"«ENDIF», «height», org.eclipse.swt.SWT.«style»);
	'''
	
	def dispatch dispatch_customAttributes(Figure it, String figureVarName) ''''''
	
	def dispatch dispatch_customAttributes(RealFigure it, String figureVarName) '''
		«xptCustomAttributeOwner.customAttributes(it, figureVarName)»
	'''
}