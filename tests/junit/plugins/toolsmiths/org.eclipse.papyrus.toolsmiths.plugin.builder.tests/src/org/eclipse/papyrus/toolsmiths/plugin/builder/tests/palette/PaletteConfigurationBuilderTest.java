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

import static org.eclipse.papyrus.junit.matchers.MoreMatchers.emptyIterable;
import static org.eclipse.papyrus.junit.matchers.WorkspaceMatchers.hasMarkerAttribute;
import static org.eclipse.papyrus.junit.matchers.WorkspaceMatchers.isMarkerMessage;
import static org.eclipse.papyrus.junit.matchers.WorkspaceMatchers.isMarkerSeverity;
import static org.eclipse.papyrus.toolsmiths.plugin.builder.tests.GenericEMFModelBuilderTest.GENERIC_EMF_MODEL_BUILDER_PROBLEM;
import static org.eclipse.papyrus.toolsmiths.validation.common.checkers.CommonProblemConstants.MISSING_DEPENDENCIES;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.Build;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.MarkerType;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.OverlayFile;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProject;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProjectFixture;
import org.junit.Rule;
import org.junit.Test;

/**
 * Specific test cases for the <em>Palette Configuration Model Project Rules Description</em>.
 */
@TestProject("org.eclipse.papyrus.toolsmiths.plugin.builder.palette.example")
@MarkerType(GENERIC_EMF_MODEL_BUILDER_PROBLEM)
@Build
public class PaletteConfigurationBuilderTest {

	static final String MANIFEST_MF = "META-INF/MANIFEST.MF"; //$NON-NLS-1$
	static final String PLUGIN_XML = "plugin.xml"; //$NON-NLS-1$
	static final String PALETTE = "resources/TestPalette.paletteconfiguration"; //$NON-NLS-1$
	static final String EXT_PT = "org.eclipse.papyrus.infra.gmfdiag.common.paletteDefinition"; //$NON-NLS-1$

	@Rule
	public final TestProjectFixture fixture = new TestProjectFixture();

	@Test
	public void modelValidation() {
		final List<IMarker> modelMarkers = fixture.getMarkers(PALETTE);

		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(
				isMarkerMessage(containsString("unresolved proxy"))))); //$NON-NLS-1$
	}

	@Test
	public void missingRequiredDependencyFromRules() {
		final List<IMarker> modelMarkers = fixture.getMarkers(MANIFEST_MF);

		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(
				isMarkerMessage(containsString("Missing Dependencies"))).and( //$NON-NLS-1$
						hasMarkerAttribute(MISSING_DEPENDENCIES, containsString("org.eclipse.papyrus.infra.gmfdiag.common"))))); //$NON-NLS-1$
	}

	@Test
	public void missingRequiredDependencyImpliedBySchema() {
		final List<IMarker> modelMarkers = fixture.getMarkers(MANIFEST_MF);

		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(
				isMarkerMessage(containsString("Missing Dependencies"))).and( //$NON-NLS-1$
						hasMarkerAttribute(MISSING_DEPENDENCIES, containsString("org.eclipse.papyrus.infra.gmfdiag.paletteconfiguration"))))); //$NON-NLS-1$
	}

	@Test
	public void missingExtension() {
		final List<IMarker> modelMarkers = fixture.getMarkers(PLUGIN_XML);

		// It's just a warning because of the alternative Architecture Domain registration mechanism
		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_WARNING)).and(
				isMarkerMessage(both(containsString("Missing extension")).and(containsString(EXT_PT)))))); //$NON-NLS-1$
	}

	@Test
	@OverlayFile(value = "palette/plugin-wrongPath.xml", path = PLUGIN_XML)
	public void unmatchedExtension() {
		final List<IMarker> modelMarkers = fixture.getMarkers(PLUGIN_XML);

		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_WARNING)).and(
				isMarkerMessage(both(containsString("Missing extension")).and(containsString(EXT_PT)))))); //$NON-NLS-1$
	}

	@Test
	@OverlayFile(value = "palette/plugin-ok.xml", path = PLUGIN_XML)
	public void extensionOK() {
		final List<IMarker> modelMarkers = fixture.getMarkers(PLUGIN_XML);

		assertThat(modelMarkers, emptyIterable());
	}

	@Test
	@OverlayFile(value = "palette/plugin-architecture.xml", path = PLUGIN_XML)
	@OverlayFile(value = "palette/TestPalette.architecture", path = "resources/TestPalette.architecture")
	public void architectureRegistered() {
		final List<IMarker> modelMarkers = fixture.getMarkers(PLUGIN_XML);

		assertThat(modelMarkers, emptyIterable());
	}

}
