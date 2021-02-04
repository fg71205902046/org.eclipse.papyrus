/*****************************************************************************
 * Copyright (c) 2011 - 2013 Montages AG
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Svyatoslav Kovalsky (Montages) - initial API and implementation
 * Michael Golubev (Montages) - #386838 - migrate to Xtend2
 * 
 *****************************************************************************/
package aspects.impl.diagram.editparts

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.LabelModelFacet
import org.eclipse.papyrus.gmf.codegen.gmfgen.OclChoiceParser
import org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedEnumParser
import xpt.Common_qvto

@Singleton class ChoiceUtils_qvto extends impl.diagram.editparts.ChoiceUtils_qvto {
	@Inject extension Common_qvto

	override boolean isChoiceLabel(LabelModelFacet modelFacet) {
		var parser = modelFacet.parser;
		if (parser != null) {
			return parser.oclIsKindOf(typeof(PredefinedEnumParser)) || parser.oclIsKindOf(typeof(OclChoiceParser));
		} else {
			return false;
		}
	}
}
