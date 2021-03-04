/*****************************************************************************
 * Copyright (c) 2006-2013, 2021 Borland Software Corporation, CEA LIST, Artal and others
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
 * Michael Golubev (Montages) - #386838 - migrate to Xtend2
 * Etienne ALLOGO (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : PapyrusGmfExtension epackage merge into gmfgen
 * 
 *****************************************************************************/
package aspects.xpt;

import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenEditorGenerator

@Singleton class Common extends xpt.Common {
	override copyright(GenEditorGenerator it) 
	'''
	«IF copyrightText !== null»
	/**
	 * «copyrightText.replaceAll('\n', '\n * ')»
	 */
 	«ENDIF»
	'''

	def String stringVisualID(GenCommonBase it) {
		if (visualIDOverride !== null)
			visualIDOverride
		else
			visualID.toString
	}

	def String stringUniqueIdentifier(GenCommonBase it) {
		if (visualIDOverride !== null)
			visualIDOverride
		else
			it.uniqueIdentifier
	}
}

