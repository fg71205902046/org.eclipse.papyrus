/*****************************************************************************
 * Copyright (c) 2007, 2009 Borland Software Corporation
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
 * Artem Tikhomirov (Borland) - [257119] Create views directly, not through ViewFactories
 * Michael Golubev (Montages) - #386838 - migrate to Xtend2
 * 
 *****************************************************************************/
package aspects.xpt.diagram.views

import aspects.xpt.Common
import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenExternalNodeLabel
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLabel
import xpt.diagram.Utils_qvto
import xpt.diagram.ViewmapAttributesUtils_qvto

/**
 * Renamed from xpt::diagram::views::Utils.xpt 
 * in order to have consistent naming between Xtend files migrated from _qvto helpers and xpt templates
 */
@Singleton class ViewStyles extends xpt.diagram.views.ViewStyles{
	@Inject extension Common;
	@Inject extension ViewmapAttributesUtils_qvto;
	@Inject extension Utils_qvto;



	override dispatch offset(GenExternalNodeLabel it, String viewVar) '''
		«IF labelOffsetX(viewmap, 0) != 0 || labelOffsetY(viewmap, 0) != 0»
			«offset(it,viewVar, labelOffsetX(viewmap, 0), labelOffsetY(viewmap, 0))»
		«ELSE»
			«offset(it,viewVar, 0, 15)»
		«ENDIF»
	'''

	override def offset(GenLabel it, String viewVar, int x, int y) '''
		«val location = stringUniqueIdentifier.toFirstLower+'_Location'»
		org.eclipse.gmf.runtime.notation.Location «location» = (org.eclipse.gmf.runtime.notation.Location) «viewVar».getLayoutConstraint();
		«IF it.getDiagram().isPixelMapMode()»
			«location».setX(«x»);
			«location».setY(«y»);
		«ELSE»
			«location».setX(org.eclipse.gmf.runtime.diagram.ui.util.MeasurementUnitHelper.getMapMode(«viewVar».getDiagram().getMeasurementUnit()).DPtoLP(«x»));
			«location».setY(org.eclipse.gmf.runtime.diagram.ui.util.MeasurementUnitHelper.getMapMode(«viewVar».getDiagram().getMeasurementUnit()).DPtoLP(«y»));
		«ENDIF»
	'''
}
