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
 *    Alexander Shatalin (Borland) - initial API and implementation
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *****************************************************************************/
package xpt.editor

import com.google.inject.Inject
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenEditorGenerator
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenNavigator
import xpt.Common

/**
 * FIXME: [MG] why here? move to .navigator?
 */
@com.google.inject.Singleton class UriEditorInputTester {
	@Inject extension Common;

	def className(GenNavigator it) '''«uriInputTesterClassName»'''

	def packageName(GenNavigator it) '''«it.editorGen.editor.packageName»'''

	def qualifiedClassName(GenNavigator it) '''«packageName(it)».«className(it)»'''

	def fullPath(GenNavigator it) '''«qualifiedClassName(it)»'''

	def UriEditorInputTester(GenNavigator it) '''
		«copyright(editorGen)»
		package «packageName(it)»;
		
		«generatedClassComment»
		public class «className(it)» extends org.eclipse.core.expressions.PropertyTester {
		
			«test(editorGen)»
		
			«additions(it)»
		}
	'''

	def test(GenEditorGenerator it) '''
		«generatedMemberComment»
		public boolean test(Object receiver, String method, Object[] args, Object expectedValue) {
			if (false == receiver instanceof org.eclipse.emf.common.ui.URIEditorInput) {
				return false;
			}
			org.eclipse.emf.common.ui.URIEditorInput editorInput = (org.eclipse.emf.common.ui.URIEditorInput) receiver;
			return "«diagramFileExtension»".equals(editorInput.getURI().fileExtension()); «nonNLS(1)»
		}
	'''

	def additions(GenNavigator it) ''''''

}
