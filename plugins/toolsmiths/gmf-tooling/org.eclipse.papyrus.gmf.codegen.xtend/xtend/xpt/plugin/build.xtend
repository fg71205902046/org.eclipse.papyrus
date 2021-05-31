/*******************************************************************************
 * Copyright (c) 2013, 2020 Borland Software Corporation, CEA LIST, Artal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *   Michael Golubev (Montages) - initial API and implementation
 *   Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *****************************************************************************/
package xpt.plugin

import org.eclipse.papyrus.gmf.codegen.gmfgen.GenPlugin

@com.google.inject.Singleton class build {

	def qualifiedClassName(GenPlugin it) '''build.properties'''
	def fullPath(GenPlugin it) '''«qualifiedClassName(it)»'''

	def build(GenPlugin it)'''
		«includes»
		«compileOrders»
	'''

	def includes(GenPlugin it)'''
		bin.includes = .,\
		               icons/,\
		               META-INF/,\
		               plugin.xml,\
		               plugin.properties,\
		               messages.properties,\
		               .options
	'''

	def compileOrders(GenPlugin it)'''
		jars.compile.order = .
		source.. = src/
		output.. = bin/
	'''
}