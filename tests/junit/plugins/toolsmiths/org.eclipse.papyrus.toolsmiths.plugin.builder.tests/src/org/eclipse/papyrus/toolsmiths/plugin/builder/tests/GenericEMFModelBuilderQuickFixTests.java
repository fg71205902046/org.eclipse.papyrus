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

import static org.eclipse.papyrus.toolsmiths.plugin.builder.tests.GenericEMFModelBuilderTest.GENERIC_EMF_MODEL_BUILDER_PROBLEM;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CommonProblemConstants;
import org.eclipse.papyrus.toolsmiths.validation.common.quickfix.CommonMarkerResolutionGenerator;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.Build;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.MarkerType;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.OverlayFile;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.QuickFix;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.QuickFixWith;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProject;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProjectFixture;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * Specific tests for the <em>Generic EMF Model Builder</em> quick fixes.
 */
@TestProject("org.eclipse.papyrus.toolsmiths.plugin.builder.example")
@MarkerType(GENERIC_EMF_MODEL_BUILDER_PROBLEM)
@QuickFixWith(CommonMarkerResolutionGenerator.class)
@Build
public class GenericEMFModelBuilderQuickFixTests {

	static final String MANIFEST_MF = "META-INF/MANIFEST.MF"; //$NON-NLS-1$
	static final String PLUGIN_XML = "plugin.xml"; //$NON-NLS-1$

	@ClassRule
	public static final FakeToolingModel fakeToolingModel = new FakeToolingModel();

	@Rule
	public final TestProjectFixture fixture = new TestProjectFixture();

	public GenericEMFModelBuilderQuickFixTests() {
		super();
	}

	@QuickFix(value = CommonProblemConstants.MISSING_DEPENDENCIES_MARKER_ID, all = true)
	@Test
	public void missingDependenciesFix() {
		fixture.assertAfterFix(manifest -> {
			assertThat(manifest, containsString(("org.eclipse.papyrus.test.bogus.required")));
			assertThat(manifest, containsString(("org.eclipse.papyrus.test.bogus.expected")));
		});
	}

	@QuickFix(CommonProblemConstants.MISSING_EXTENSION)
	@Test
	public void missingExtensionFix() {
		fixture.assertAfterFix(pluginXML -> {
			assertThat(pluginXML, containsString(("path=\"resources/uml.toolingstuff\"")));
			assertThat(pluginXML, containsString(("point=\"org.eclipse.papyrus.toolsmiths.plugin.builder.test.models\"")));
			assertThat(pluginXML, containsString(("id=\"org.eclipse.papyrus.toolsmiths.plugin.builder.example.umltooling\"")));
			assertThat(pluginXML, containsString(("target=\"http://www.eclipse.org/uml2/5.0.0/UML\"")));
			assertThat(pluginXML, containsString(("<priority")));
			assertThat(pluginXML, containsString(("value=\"medium\"")));
		});
	}

	@OverlayFile(value = "plugin-missingElement.xml", path = PLUGIN_XML)
	@QuickFix(CommonProblemConstants.MISSING_EXTENSION_ELEMENT)
	@Test
	public void missingElementFix() {
		fixture.assertAfterFix(pluginXML -> {
			assertThat(pluginXML, containsString(("<priority")));
			assertThat(pluginXML, containsString(("value=\"medium\"")));
		});
	}

	@OverlayFile(value = "plugin-missingAttribute.xml", path = PLUGIN_XML)
	@QuickFix(CommonProblemConstants.MISSING_EXTENSION_ATTRIBUTE)
	@Test
	public void missingAttributeFix() {
		fixture.assertAfterFix(pluginXML -> {
			assertThat(pluginXML, containsString(("target=\"http://www.eclipse.org/uml2/5.0.0/UML\"")));
		});
	}

	@OverlayFile(value = "plugin-invalidAttribute.xml", path = PLUGIN_XML)
	@QuickFix(CommonProblemConstants.INVALID_EXTENSION_ATTRIBUTE)
	@Test
	public void invalidAttributeFix() {
		fixture.assertAfterFix(pluginXML -> {
			assertThat(pluginXML, containsString(("target=\"http://www.eclipse.org/uml2/5.0.0/UML\"")));
		});
	}

}
