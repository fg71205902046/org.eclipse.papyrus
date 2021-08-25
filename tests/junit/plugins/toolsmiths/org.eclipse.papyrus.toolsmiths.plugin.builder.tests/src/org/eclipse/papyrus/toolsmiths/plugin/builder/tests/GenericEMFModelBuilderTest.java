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

import static org.eclipse.papyrus.junit.matchers.WorkspaceMatchers.hasMarkerAttribute;
import static org.eclipse.papyrus.junit.matchers.WorkspaceMatchers.isMarkerMessage;
import static org.eclipse.papyrus.junit.matchers.WorkspaceMatchers.isMarkerSeverity;
import static org.eclipse.papyrus.toolsmiths.validation.common.checkers.CommonProblemConstants.MISSING_DEPENDENCIES;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.papyrus.toolsmiths.plugin.builder.AbstractPapyrusBuilder;
import org.eclipse.papyrus.toolsmiths.plugin.builder.GenericEMFModelBuilder;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.Build;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.MarkerType;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.OverlayFile;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProject;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProjectFixture;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * Specific test cases for the {@link GenericEMFModelBuilder} class.
 */
@TestProject("org.eclipse.papyrus.toolsmiths.plugin.builder.example")
@MarkerType(GenericEMFModelBuilderTest.GENERIC_EMF_MODEL_BUILDER_PROBLEM)
@Build
public class GenericEMFModelBuilderTest {

	public static final String GENERIC_EMF_MODEL_BUILDER_PROBLEM = AbstractPapyrusBuilder.MODEL_PROBLEM;

	static final String MANIFEST_MF = "META-INF/MANIFEST.MF"; //$NON-NLS-1$
	static final String PLUGIN_XML = "plugin.xml"; //$NON-NLS-1$

	@ClassRule
	public static final FakeToolingModel fakeToolingModel = new FakeToolingModel();

	@Rule
	public final TestProjectFixture fixture = new TestProjectFixture();

	@Test
	public void missingRequiredDependency() {
		final List<IMarker> modelMarkers = fixture.getMarkers(MANIFEST_MF);

		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(
				isMarkerMessage(containsString("Missing Dependencies"))).and( //$NON-NLS-1$
						hasMarkerAttribute(MISSING_DEPENDENCIES, containsString("org.eclipse.papyrus.test.bogus.required"))))); //$NON-NLS-1$
	}

	@Test
	public void missingDependency() {
		final List<IMarker> modelMarkers = fixture.getMarkers(MANIFEST_MF);

		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_WARNING)).and(
				isMarkerMessage(containsString("Missing Dependencies"))).and( //$NON-NLS-1$
						hasMarkerAttribute(MISSING_DEPENDENCIES, containsString("org.eclipse.papyrus.test.bogus.expected"))))); //$NON-NLS-1$
	}

	@Test
	public void missingExtension() {
		final List<IMarker> modelMarkers = fixture.getMarkers(PLUGIN_XML);

		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(
				isMarkerMessage(both(containsString("Missing extension")).and(containsString("org.eclipse.papyrus.toolsmiths.plugin.builder.test.models")))))); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	@OverlayFile(value = "plugin-missingElement.xml", path = PLUGIN_XML)
	public void missingElement() {
		final List<IMarker> modelMarkers = fixture.getMarkers(PLUGIN_XML);

		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(
				isMarkerMessage(both(containsString("Missing element")).and(containsString("priority")))))); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	@OverlayFile(value = "plugin-missingAttribute.xml", path = PLUGIN_XML)
	public void missingAttribute() {
		final List<IMarker> modelMarkers = fixture.getMarkers(PLUGIN_XML);

		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(
				isMarkerMessage(both(containsString("Missing attribute")).and(containsString("target")))))); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	@OverlayFile(value = "plugin-invalidAttribute.xml", path = PLUGIN_XML)
	public void invalidAttribute() {
		final List<IMarker> modelMarkers = fixture.getMarkers(PLUGIN_XML);

		assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(
				isMarkerMessage(both(containsString("Attribute 'target' should have value ")).and(containsString("http://www.eclipse.org/uml2/5.0.0/UML")))))); //$NON-NLS-1$//$NON-NLS-2$
	}

}
