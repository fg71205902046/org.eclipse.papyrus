/*****************************************************************************
 * Copyright (c) 2006, 2009, 2013 Borland Software Corporation and others
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
 * 
 *****************************************************************************/
package aspects.diagram.editparts

import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenChildLabelNode

@Singleton class ChildNodeLabelEditPart extends diagram.editparts.ChildNodeLabelEditPart {



	override extendsList(GenChildLabelNode it) '''
«««BEGIN: PapyrusGenCode
«««Add own extension
«IF superEditPart !== null»
  extends «superEditPart»
«««END: PapyrusGenCode
«ELSE»
  extends org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart
«ENDIF»
'''

override implementsList(GenChildLabelNode it) '''implements org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart, org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart, org.eclipse.papyrus.infra.gmfdiag.common.editpart.IControlParserForDirectEdit'''
}
