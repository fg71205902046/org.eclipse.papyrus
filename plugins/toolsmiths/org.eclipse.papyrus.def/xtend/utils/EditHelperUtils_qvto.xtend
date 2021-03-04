/*****************************************************************************
 * Copyright (c) 2014, 2021 CEA LIST, Artal and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Florian Noyrit - Initial API and implementation
 * Etienne ALLOGO (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : PapyrusGmfExtension epackage merge into gmfgen
 * 
 *****************************************************************************/
package utils

import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram

@Singleton class EditHelperUtils_qvto {

	def String getBaseEditHelperFullName(GenDiagram diagram) {
		// Bug 569174 : from AdditionalEditPartCandies
		if(diagram.baseEditHelperPackage !== null) {
			return diagram.baseEditHelperPackage + "." + diagram.baseEditHelperClassName
		} else {
			return diagram.getBaseEditHelperQualifiedClassName();
		}

	}
}
