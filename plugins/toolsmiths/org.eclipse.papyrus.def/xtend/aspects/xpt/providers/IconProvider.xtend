/*****************************************************************************
 * Copyright (c) 2007, 2014 Borland Software Corporation, CEA, and others
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
 * Artem Tikhomirov (Borland) - refactored javaInitilizers not to use methods from GMFGen model
 *                               [221347] Got rid of generated interfaces 
 *                               (IObjectInitializer, IFeatureInitializer) and implementation thereof
 * Michael Golubev (Montages) - #386838 - migrate to Xtend2
 * Christian W. Damus (CEA) - bug 440263
 * 
 *****************************************************************************/
package aspects.xpt.providers

import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram

@Singleton class IconProvider extends xpt.providers.IconProvider {

	override def extendsList(GenDiagram it) '''extends org.eclipse.papyrus.infra.gmfdiag.common.providers.DefaultElementTypeIconProvider'''
	
}
