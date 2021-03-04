/*******************************************************************************
 * Copyright (c) 2007, 2020, 2021 Borland Software Corporation, CEA LIST, Artal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *    Alexander Shatalin (Borland) - initial API and implementation
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *    Etienne ALLOGO (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : PapyrusGmfExtension epackage merge into gmfgen
 *****************************************************************************/
package xpt.diagram.updater

import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagramUpdater

@Singleton class extensions {

	def extensions(GenDiagramUpdater it) '''
«««		!!! was overriden  by papyrus to remove this !!!
«««
«««		«extraLineBreak»
«««		«extraLineBreak»
«««		«tripleSpace(1)»<extension point="org.eclipse.ui.commands" id="update-cmd">
«««		«tripleSpace(2)»«xmlGeneratedTag»
«««		«tripleSpace(2)»<command
«««		«tripleSpace(3)»categoryId="org.eclipse.ui.category.edit"
«««		«tripleSpace(3)»defaultHandler="«getUpdateCommandQualifiedClassName()»"
«««		«tripleSpace(3)»description="%update.diagram.description"
«««		«tripleSpace(3)»id="«updateCommandID»"
«««		«tripleSpace(3)»name="%update.diagram.name"/>
«««		«tripleSpace(1)»</extension>
«««		«extraLineBreak»«outTab»
«««		«tripleSpace(1)»<extension point="org.eclipse.ui.bindings" id="update-cmd-binding">
«««		«tripleSpace(2)»«xmlGeneratedTag»
«««		«tripleSpace(2)»<key 
«««		«tripleSpace(3)»commandId="«updateCommandID»"
«««		«tripleSpace(3)»contextId="«editorGen.editor.contextID»"
«««		«tripleSpace(3)»schemeId="org.eclipse.ui.defaultAcceleratorConfiguration"
«««		«tripleSpace(3)»sequence="F5"/>
«««		«tripleSpace(1)»</extension>
	'''

}
