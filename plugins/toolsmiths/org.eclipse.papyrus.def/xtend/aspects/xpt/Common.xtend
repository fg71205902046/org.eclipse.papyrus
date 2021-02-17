/*****************************************************************************
 * Copyright (c) 2006-2013 Borland Software Corporation and others
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
 * 
 *****************************************************************************/
package aspects.xpt;

import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenEditorGenerator
import org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride

@Singleton class Common extends xpt.Common {
	override copyright(GenEditorGenerator it) 
	'''
	«IF copyrightText != null»
	/**
	 * «copyrightText.replaceAll('\n', '\n * ')»
	 */
 	«ENDIF»
	'''

	def String stringVisualID(GenCommonBase it) {
		if (it.eResource.allContents.filter(typeof (VisualIDOverride)).exists(v|v.genView == it))
			it.eResource.allContents.filter(typeof (VisualIDOverride)).findFirst(v|v.genView == it).visualID
		else
			it.visualID.toString
	}

	def String stringUniqueIdentifier(GenCommonBase it) {
		if (it.eResource.allContents.filter(typeof (VisualIDOverride)).exists(v|v.genView == it))
			it.eResource.allContents.filter(typeof (VisualIDOverride)).findFirst(v|v.genView == it).visualID
		else
			it.uniqueIdentifier
	}
}

