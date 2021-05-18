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

import com.google.inject.Singleton

@Singleton class CodeStyle {
	/**
	 * FIXME: [MG] inline this, we now safely assume everywhere that it is > 6
	 */
	def overrideC(Object xptSelf) ''' 
		@Override
	'''

	def overrideI(Object xptSelf)'''
		@Override
	'''

	/**
	 * FIXME: [MG] inline this, we now safely assume everywhere that it is > 6
	 */
	def SuppressWarnings(Object xptSelf, String warnToken) '''
		@SuppressWarnings(«warnToken»)
	'''
}