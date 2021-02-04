/*******************************************************************************
 * Copyright (c) 2007, 2020 Borland Software Corporation, CEA LIST, Artal and others
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
import org.eclipse.papyrus.gmf.gmfgraph.BorderLayout
import org.eclipse.papyrus.gmf.gmfgraph.CenterLayout
import org.eclipse.papyrus.gmf.gmfgraph.CustomBorder
import org.eclipse.papyrus.gmf.gmfgraph.CustomFigure
import org.eclipse.papyrus.gmf.gmfgraph.CustomLayout
import org.eclipse.papyrus.gmf.gmfgraph.Ellipse
import org.eclipse.papyrus.gmf.gmfgraph.Figure
import org.eclipse.papyrus.gmf.gmfgraph.FigureRef
import org.eclipse.papyrus.gmf.gmfgraph.FlowLayout
import org.eclipse.papyrus.gmf.gmfgraph.GridLayout
import org.eclipse.papyrus.gmf.gmfgraph.GridLayoutData
import org.eclipse.papyrus.gmf.gmfgraph.InvisibleRectangle
import org.eclipse.papyrus.gmf.gmfgraph.Label
import org.eclipse.papyrus.gmf.gmfgraph.LabeledContainer
import org.eclipse.papyrus.gmf.gmfgraph.Layout
import org.eclipse.papyrus.gmf.gmfgraph.LayoutData
import org.eclipse.papyrus.gmf.gmfgraph.Polygon
import org.eclipse.papyrus.gmf.gmfgraph.PolygonDecoration
import org.eclipse.papyrus.gmf.gmfgraph.Polyline
import org.eclipse.papyrus.gmf.gmfgraph.PolylineConnection
import org.eclipse.papyrus.gmf.gmfgraph.PolylineDecoration
import org.eclipse.papyrus.gmf.gmfgraph.Rectangle
import org.eclipse.papyrus.gmf.gmfgraph.RoundedRectangle
import org.eclipse.papyrus.gmf.gmfgraph.SVGFigure
import org.eclipse.papyrus.gmf.gmfgraph.ScalablePolygon
import org.eclipse.papyrus.gmf.gmfgraph.StackLayout
import org.eclipse.papyrus.gmf.gmfgraph.VerticalLabel
import org.eclipse.papyrus.gmf.gmfgraph.XYLayout
import org.eclipse.papyrus.gmf.gmfgraph.XYLayoutData
import xpt.Common_qvto

@com.google.inject.Singleton class Runtime {
	@Inject extension Utils_qvto
	@Inject extension Common_qvto

	def dispatch newInstance(Figure it, String figureVarName) '''«fqn(it)» «figureVarName» = «newInstance(it)»;'''

	def dispatch newInstance(Figure it) '''new «fqn(it)»()'''

	def dispatch newInstance(Layout it, String layoutVarName) '''«fqn(it)» «layoutVarName» = «newInstance(it)»;'''

	def dispatch newInstance(Layout it) '''new «fqn(it)»()'''

	def dispatch newInstance(LayoutData it, String constraintVarName) '''«fqn(it)» «constraintVarName» = «newInstance(it)»;'''

	def dispatch newInstance(LayoutData it) '''new «fqn(it)»()'''

	def dispatch newInstance(org.eclipse.papyrus.gmf.gmfgraph.Border it, String borderVarName) '''«fqn(it)» «borderVarName» = «newInstance(
		it)»;'''

	def dispatch newInstance(org.eclipse.papyrus.gmf.gmfgraph.Border it) '''new «fqn(it)»()'''

	def dispatch CharSequence fqn(Figure it) '''«ERROR('Abstract definition fqn(Figure)')»'''

	def dispatch CharSequence fqn(Layout it) '''«ERROR('Abstract definition fqn(Layout)')»'''

	def dispatch CharSequence fqn(LayoutData it) '''«ERROR('Abstract definition fqn(LayoutData)')»'''

	def dispatch CharSequence fqn(org.eclipse.papyrus.gmf.gmfgraph.Border it) '''«ERROR('Abstract definition fqn(Border)')»'''

	////////////////////////
	def dispatch CharSequence fqn(CustomLayout it) '''«qualifiedClassName»'''

	def dispatch CharSequence fqn(CustomBorder it) '''«qualifiedClassName»'''

	def dispatch CharSequence fqn(CustomFigure it) '''«qualifiedClassName»'''

	////////////////////////
	def dispatch CharSequence fqn(FlowLayout it) '''«IF forceSingleLine»org.eclipse.draw2d.ToolbarLayout«ELSE»org.eclipse.draw2d.FlowLayout«ENDIF»'''

	def dispatch CharSequence fqn(XYLayout it) '''org.eclipse.draw2d.XYLayout'''

	def dispatch CharSequence fqn(XYLayoutData it) '''org.eclipse.draw2d.geometry.Rectangle'''

	def dispatch CharSequence fqn(GridLayout it) '''org.eclipse.draw2d.GridLayout'''

	def dispatch CharSequence fqn(GridLayoutData it) '''org.eclipse.draw2d.GridData'''

	def dispatch CharSequence fqn(StackLayout it) '''org.eclipse.draw2d.StackLayout'''

	def dispatch CharSequence fqn(BorderLayout it) '''org.eclipse.draw2d.BorderLayout'''

	/**
	 * CenterLayout requires dependency to tooling runtime if the stabdalone plugin is generated
	 */
	def dispatch CharSequence fqn(CenterLayout it) '''org.eclipse.gmf.tooling.runtime.draw2d.CenterLayout'''

	////////////////////////
	def dispatch CharSequence fqn(FigureRef it) '''«fqn(it.figure)»'''

	def dispatch CharSequence fqn(Label it) '''«IF isFullRuntime()»org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel«ELSE»org.eclipse.draw2d.Label«ENDIF»'''

	def dispatch CharSequence fqn(VerticalLabel it) '''org.eclipse.gmf.tooling.runtime.draw2d.labels.VerticalLabel'''

	def dispatch CharSequence fqn(LabeledContainer it) '''org.eclipse.draw2d.LabeledContainer'''

	def dispatch CharSequence fqn(Rectangle it) '''org.eclipse.draw2d.RectangleFigure'''

	def dispatch CharSequence fqn(RoundedRectangle it) '''org.eclipse.draw2d.RoundedRectangle'''

	def dispatch CharSequence fqn(InvisibleRectangle it) '''org.eclipse.draw2d.RectangleFigure'''

	def dispatch CharSequence fqn(Ellipse it) '''org.eclipse.draw2d.Ellipse'''

	def dispatch CharSequence fqn(Polygon it) '''org.eclipse.draw2d.PolygonShape'''

	def dispatch CharSequence fqn(ScalablePolygon it) '''org.eclipse.draw2d.ScalablePolygonShape'''

	def dispatch CharSequence fqn(PolygonDecoration it) '''org.eclipse.draw2d.PolygonDecoration'''

	def dispatch CharSequence fqn(Polyline it) '''org.eclipse.draw2d.PolylineShape'''

	def dispatch CharSequence fqn(PolylineDecoration it) '''org.eclipse.draw2d.PolylineDecoration'''

	def dispatch CharSequence fqn(PolylineConnection it) '''«IF isFullRuntime()»org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx«ELSE»org.eclipse.draw2d.PolylineConnection«ENDIF»'''

	def dispatch CharSequence fqn(SVGFigure it) '''org.eclipse.gmf.runtime.lite.svg.SVGFigure'''

}
