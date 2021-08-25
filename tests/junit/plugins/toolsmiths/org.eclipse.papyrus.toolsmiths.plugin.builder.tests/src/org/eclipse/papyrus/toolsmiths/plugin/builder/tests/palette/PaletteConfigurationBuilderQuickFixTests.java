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

package org.eclipse.papyrus.toolsmiths.plugin.builder.tests.palette;

import static org.eclipse.papyrus.toolsmiths.plugin.builder.tests.GenericEMFModelBuilderTest.GENERIC_EMF_MODEL_BUILDER_PROBLEM;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CommonProblemConstants;
import org.eclipse.papyrus.toolsmiths.validation.common.quickfix.CommonMarkerResolutionGenerator;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.Build;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.MarkerType;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.QuickFix;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.QuickFixWith;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProject;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProjectFixture;
import org.junit.Rule;
import org.junit.Test;

/**
 * Specific tests for the <em>Palette Configuration Model Project Rules Description</em> quick fixes.
 */
@TestProject("org.eclipse.papyrus.toolsmiths.plugin.builder.palette.example")
@MarkerType(GENERIC_EMF_MODEL_BUILDER_PROBLEM)
@QuickFixWith(CommonMarkerResolutionGenerator.class)
@Build
public class PaletteConfigurationBuilderQuickFixTests {

	static final String MANIFEST_MF = "META-INF/MANIFEST.MF"; //$NON-NLS-1$
	static final String PLUGIN_XML = "plugin.xml"; //$NON-NLS-1$

	@Rule
	public final TestProjectFixture fixture = new TestProjectFixture();

	public PaletteConfigurationBuilderQuickFixTests() {
		super();
	}

	@QuickFix(value = CommonProblemConstants.MISSING_DEPENDENCIES_MARKER_ID, all = true)
	@Test
	public void missingDependenciesFix() {
		fixture.assertAfterFix(manifest -> {
			assertThat(manifest, containsString(("org.eclipse.papyrus.infra.gmfdiag.common")));
			assertThat(manifest, containsString(("org.eclipse.papyrus.infra.gmfdiag.paletteconfiguration")));
		});
	}

	@QuickFix(CommonProblemConstants.MISSING_EXTENSION)
	@Test
	public void missingExtensionFix() {
		fixture.assertAfterFix(pluginXML -> {
			assertThat(pluginXML, containsString(("point=\"org.eclipse.papyrus.infra.gmfdiag.common.paletteDefinition\"")));
			assertThat(pluginXML, containsString(("name=\"Test Palette Configuration\"")));
			assertThat(pluginXML, containsString(("ID=\"TestPaletteConfiguration\"")));
			assertThat(pluginXML, containsString(("path=\"resources/TestPalette.paletteconfiguration\"")));
			assertThat(pluginXML, containsString(("class=\"org.eclipse.papyrus.infra.gmfdiag.common.service.palette.ExtendedPluginPaletteProvider\"")));
			assertThat(pluginXML, containsString(("<Priority")));
			assertThat(pluginXML, containsString(("name=\"Medium\"")));
		});
	}

}
