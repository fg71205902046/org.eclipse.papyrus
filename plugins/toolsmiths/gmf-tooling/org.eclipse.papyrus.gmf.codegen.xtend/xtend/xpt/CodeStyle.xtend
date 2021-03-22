/*****************************************************************************
 * Copyright (c) 2015, 2013, 2021 Borland Software Corporation, CEA LIST, Artal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Michael Golubev (Montages) - initial API and implementation
 * Anatoliy Tischenko - Initial API and implementation
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : 1.4 Merge papyrus extension templates into codegen.xtend
 *****************************************************************************/
package xpt

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenEditorGenerator

@Singleton class CodeStyle {
	@Inject extension GenEditorGenerator_qvto
	@Inject extension Common;
	/**
	 * FIXME: [MG] inline this, we now safely assume everywhere that it is > 4
	 */
	def overrideC(GenCommonBase xptSelf) ''' 
		«IF xptSelf.jdkComplianceLevel() > 4»
			@Override
		«ENDIF»
	'''

	def overrideI(GenCommonBase xptSelf)'''
		@Override
	'''

	/**
	 * FIXME: [MG] inline this, we now safely assume everywhere that it is > 4
	 */
	def SuppressWarnings(GenEditorGenerator xptSelf, String warnToken) '''«SuppressWarnings(xptSelf.diagram, warnToken)»'''

	/**
	 * FIXME: [MG] inline this, we now safely assume everywhere that it is > 4
	 */
	def SuppressWarnings(GenCommonBase xptSelf, String warnToken) '''
		«IF xptSelf.jdkComplianceLevel() > 4»
			@SuppressWarnings(«warnToken»)
		«ENDIF»
	'''
}