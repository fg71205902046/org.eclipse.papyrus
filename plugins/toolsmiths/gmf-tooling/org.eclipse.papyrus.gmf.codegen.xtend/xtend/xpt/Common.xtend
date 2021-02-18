/*******************************************************************************
 * Copyright (c) 2006-2020 Borland Software Corporation, CEA LIST, Artal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *    Dmitry Stadnik (Borland) - initial API and implementation
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *****************************************************************************/
package xpt

import org.eclipse.papyrus.gmf.codegen.gmfgen.GenEditorGenerator


import org.eclipse.papyrus.gmf.codegen.gmfgen.GenEditorGenerator
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenEditorGenerator
import xpt.editor.VisualIDRegistry
import org.eclipse.papyrus.gmf.internal.common.codegen.Conversions

/**
 * XXX: [MG] I don't like dependency from Common -> VisualIdRegistry 
 */
@com.google.inject.Singleton class Common {
	def copyright(GenEditorGenerator it) 
	'''
	«IF copyrightText != null»
	/*
	 * «copyrightText.replaceAll('\n', '\n * ')»
	 */
 	«ENDIF»
	'''
	
	def xcopyright(GenEditorGenerator it) 
	'''
	«IF copyrightText != null»
	<!--
	«escapeXML(it.copyrightText)»
	-->
	«ENDIF»
	'''
	
	def escapeXML(CharSequence forXML) {
		Conversions::escapeXML(forXML.toString) 
	}
	
	def generatedClassComment(){
		generatedClassComment('')
	}
	
	def generatedClassComment(String comment) {
		doGeneratedComment(comment, '')
	} 

	def generatedMemberComment() {
		doGeneratedComment('', '')	
	}

	def generatedMemberComment(String comment) {
		doGeneratedComment(comment, '')
	} 

	def generatedMemberComment(String comment, String comment2) {
		doGeneratedComment(comment, comment2)
	}

	/**
	 * XXX: FIXME: merge all generatedXXXcomment to go here
	 */ 
	def doGeneratedComment(String comment, String comment2) 
	'''
	/**
	«IF comment.length > 0» * «comment.replaceAll('\n', '\n * ')»«ENDIF»
	 * @generated
	«IF comment2.length > 0» * «comment2.replaceAll('\n', '\n * ')»«ENDIF»
	 */
	'''

	def xmlGeneratedTag() '''<?gmfgen generated="true"?>'''
	
	def nonNLS_All(Iterable<?> list) '''«IF !list.empty»«FOR i : 1..list.size SEPARATOR ' '»«nonNLS(i)»«ENDFOR»«ENDIF»'''
	
	def nonNLS() '''«nonNLS(1)»'''
	
	def nonNLS(Object xptSelf, int i) '''«nonNLS(i)»'''

	def nonNLS(int xptSelf) '''//$NON-NLS-«xptSelf»$'''
	
	/**
	 * XXX:[MG] move this to VIDRegistry(?)
	 */
	
	
	/**
	 * Provides handy single point to override generation of assert statements
	 * TODO refactor this Common.xpt into different flavours - like CommonCodeStyle (nls, assert), CommonSnippets(pkgStmt, setCharset, getSaveOptions) and so on
	 * TODO condition.xpandToCharList()->count('"') / 2 gives better guess about number of nonNLS to generate
	 */ 
	def _assert(String condition) //
	'''assert «condition»;«IF condition.indexOf('\"') > 0» «nonNLS»«ENDIF»'''
	
	def addShortcutAnnotation(GenDiagram it, String viewVar) '''
		org.eclipse.emf.ecore.EAnnotation shortcutAnnotation = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEAnnotation();
		shortcutAnnotation.setSource("Shortcut"); «nonNLS()»
		shortcutAnnotation.getDetails().put("modelID", «VisualIDRegistry::modelID(it)»); «nonNLS()»
		«viewVar».getEAnnotations().add(shortcutAnnotation);
	'''
	
	/**
	 * FIXME: [MG] in some cases old xpand template generated artificial extra line break
	 * For now we want to preserve evrything including new line, to simplify checking the diff's against old generated code 
	 * In future this extra lines should be removed, this is single point of removal 
	 */
	def extraLineBreak() '''
«/*FIXME: artificially inserting new line break to reduce diff against xpand templates */»
	'''
	
	def hackTripleSpace() '''   '''
	
	def tripleSpace(int amount) {
		var b = new StringBuilder;
		var counter = 0;
		while(counter < amount) {
			b.append('   ');
			counter = counter + 1;
		}
		return b.toString;
	}
}

