/*****************************************************************************
 * Copyright (c) 2021 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder.tests;

import org.eclipse.papyrus.toolsmiths.plugin.builder.tests.palette.PaletteConfigurationBuilderQuickFixTests;
import org.eclipse.papyrus.toolsmiths.plugin.builder.tests.palette.PaletteConfigurationBuilderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for all <em>Plug-in Builder</em> tests.
 */
@RunWith(Suite.class)
@SuiteClasses({
		GenericEMFModelBuilderTest.class,
		GenericEMFModelBuilderQuickFixTests.class,
		PaletteConfigurationBuilderTest.class,
		PaletteConfigurationBuilderQuickFixTests.class,
})
public class AllTests {
	// Suite is specified in annotations
}
