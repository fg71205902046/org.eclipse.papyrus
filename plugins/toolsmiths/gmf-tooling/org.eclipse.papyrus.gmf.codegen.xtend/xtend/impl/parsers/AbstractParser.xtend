/*******************************************************************************
 * Copyright (c) 2013, 2020 Montages AG, CEA LIST, Artal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *	  Michael Golubev (Montages) - [368169] extract not-generated shared code to GMF-T runtime
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *****************************************************************************/
package impl.parsers

import org.eclipse.papyrus.gmf.codegen.gmfgen.GenParsers

/**
 * Since GMFT 3.1 we don't generate class AbstractParser into every diagram, by extending the 
 * org.eclipse.gmf.tooling.runtime.parsers.AbstractAttributeParser.
 * If you want to extend other custom implementation you may change the className and quialifiedClassName DEFINE's 
 * which are still used in the extend's clauses for generated PredefinedParser's
 * <p> 
 * However, for 3.1 release we still will generate empty file (without any java content), 
 * to ensure that the old code, including calls to non existing i18n fields in Messages is cleaned up.
 */
@com.google.inject.Singleton class AbstractParser {

	def className(GenParsers it) '''AbstractAttributeParser'''

	def packageName(GenParsers it) '''org.eclipse.gmf.tooling.runtime.parsers'''

	def String qualifiedClassName(GenParsers it) '''«packageName(it)».«className(it)»'''

	def fullPath(GenParsers it) '''«qualifiedClassName(it)»'''

	def deprecatedQualifiedClassName(GenParsers it) '''«implPackageName».AbstractParser'''

	def Main(GenParsers it) '''
		//Since GMFT 3.1 we don't generate class AbstractParser into every diagram, instead extend org.eclipse.gmf.tooling.runtime.parsers.AbstractAttributeParser.
	'''
}
