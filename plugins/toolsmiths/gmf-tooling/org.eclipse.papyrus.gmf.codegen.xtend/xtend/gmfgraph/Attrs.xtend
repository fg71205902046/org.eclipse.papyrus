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
import org.eclipse.papyrus.gmf.gmfgraph.Shape
import org.eclipse.papyrus.gmf.gmfgraph.Label
import org.eclipse.papyrus.gmf.gmfgraph.VerticalLabel
import org.eclipse.papyrus.gmf.gmfgraph.RoundedRectangle
import org.eclipse.papyrus.gmf.gmfgraph.InvisibleRectangle
import org.eclipse.papyrus.gmf.gmfgraph.CustomFigure
import org.eclipse.papyrus.gmf.gmfgraph.Polyline
import org.eclipse.papyrus.gmf.gmfgraph.SVGFigure
import org.eclipse.papyrus.gmf.gmfgraph.Rectangle2D
import xpt.Common

/**
 * Defines attributes for Figures (for DecorationFigure subclasses see Decoration.xpt)
 */
@com.google.inject.Singleton class Attrs {
	@Inject gmfgraph.attr.Shape xptShape;
	@Inject gmfgraph.attr.Figure xptFigure;
	@Inject MapMode xptMapMode;
	@Inject extension Common;
	/**
	 * Polymorphic entry point for any Figure's subclass attributes 
	 */
	def dispatch Init(Figure it, String figureVarName) '''
		«xptFigure.figureAttrs(it, figureVarName)»
		«extraLineBreak»
	'''

	def dispatch Init(Shape it, String figureVarName) '''
		«xptShape.shapeAttrs(it, figureVarName)»
		«xptFigure.figureAttrs(it, figureVarName)»
	'''

	def dispatch Init(Label it, String figureVarName) '''
		«extraLineBreak»
		«figureVarName».setText("«text»");
		«xptFigure.figureAttrs(it, figureVarName)»
	'''

	def dispatch Init(VerticalLabel it, String figureVarName) '''
		«extraLineBreak»
		«figureVarName».setText("«text»");
		«xptFigure.figureAttrs(it, figureVarName)»
	'''

	def dispatch Init(RoundedRectangle it, String figureVarName) '''
		«figureVarName».setCornerDimensions(new org.eclipse.draw2d.geometry.Dimension(«xptMapMode.mapMode(cornerWidth)», «xptMapMode.
			mapMode(cornerHeight)»));
		«xptShape.shapeAttrs(it, figureVarName)»
		«xptFigure.figureAttrs(it, figureVarName)»
	'''

	def dispatch Init(InvisibleRectangle it, String figureVarName) '''
		«figureVarName».setOutline(false);
		«figureVarName».setFill(false);
		«figureVarName».setOpaque(false);
		«xptFigure.figureAttrs(it, figureVarName)»
	'''

	def dispatch Init(CustomFigure it, String figureVarName) '''
		«xptFigure.figureAttrs(it, figureVarName)»
	'''

	def dispatch Init(Polyline it, String figureVarName) '''
		«FOR pt : it.template»
			«figureVarName».addPoint(new org.eclipse.draw2d.geometry.Point(«xptMapMode.mapMode(pt)»));
		«ENDFOR»
		«xptShape.shapeAttrs(it, figureVarName)»
		«xptFigure.figureAttrs(it, figureVarName)»
	'''

	def dispatch Init(SVGFigure it, String figureVarName) '''
		«figureVarName».setURI("«documentURI»");
		«IF noCanvasWidth»
			«figureVarName».setSpecifyCanvasWidth(false);
		«ENDIF»
		«IF noCanvasHeight»
			«figureVarName».setSpecifyCanvasHeight(false);
		«ENDIF»
		«IF areaOfInterest != null»
			«figureVarName».setAreaOfInterest(«Instance(areaOfInterest)»);
		«ENDIF»
		«xptFigure.figureAttrs(it, figureVarName)»
	'''

	def Instance(Rectangle2D it) '''
		new java.awt.geom.Rectangle2D.Float(«x»f, «y»f, «width»f, «height»f)
	'''
}
