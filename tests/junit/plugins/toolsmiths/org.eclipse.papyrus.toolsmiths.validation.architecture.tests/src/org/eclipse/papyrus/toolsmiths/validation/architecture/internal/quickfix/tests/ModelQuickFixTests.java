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

package org.eclipse.papyrus.toolsmiths.validation.architecture.internal.quickfix.tests;

import static org.eclipse.papyrus.junit.matchers.MoreMatchers.greaterThanOrEqual;
import static org.eclipse.papyrus.junit.matchers.MoreMatchers.isEmpty;
import static org.eclipse.papyrus.toolsmiths.validation.architecture.constants.ArchitecturePluginValidationConstants.ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.papyrus.toolsmiths.validation.architecture.constants.ArchitecturePluginValidationConstants;
import org.eclipse.papyrus.toolsmiths.validation.architecture.internal.quickfix.ArchitectureMarkerResolutionGenerator;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker2;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.Build;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.MarkerType;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.OverlayFile;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProject;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProjectFixture;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Specific tests for the <em>Architecture Model</em> quick fixes.
 */
@TestProject("org.eclipse.papyrus.toolsmiths.validation.architecture.example")
@MarkerType(ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE)
@Build
public class ModelQuickFixTests {

	private static final String MODEL_PATH = "resources/BookStore.architecture"; //$NON-NLS-1$

	private List<IMarker> modelMarkers;
	private List<Map<String, Object>> fixedMarkers = new ArrayList<>();

	/**
	 * The project fixture to manage easily the project.
	 */
	@Rule
	public final TestProjectFixture fixture = new TestProjectFixture();

	public ModelQuickFixTests() {
		super();
	}

	/**
	 * Test the marker resolution for the Representations Advice warning.
	 *
	 * @see <a href="https://eclip.se/573788">bug 573788</a>
	 */
	@OverlayFile(value = "bug573788-models/BookStore-noRepresentationsAdvice.architecture", path = MODEL_PATH)
	@Test
	public void representationsAdviceFix() {
		fix(ArchitecturePluginValidationConstants.MISSING_REPRESENTATIONS_ADVICE_ID);
	}

	//
	// Test framework
	//

	@Before
	public void initMarkers() {
		modelMarkers = fixture.getMarkers(MODEL_PATH);
	}

	@After
	public void verifyFixes() {
		if (!fixedMarkers.isEmpty()) {
			fixture.build();

			Set<Map<String, Object>> newMarkerAttributes = getMarkerAttributes(fixture.getMarkers(MODEL_PATH));
			Set<Map<String, Object>> unfixed = new HashSet<>(fixedMarkers);
			unfixed.retainAll(newMarkerAttributes);

			assertThat("Some problem(s) not fixed", unfixed, isEmpty());
		}
	}

	Map<String, Object> getAttributes(IMarker marker) {
		try {
			return marker.getAttributes();
		} catch (CoreException e) {
			throw new AssertionError("Failed to extract marker attributes.", e);
		}
	}

	Set<Map<String, Object>> getMarkerAttributes(Collection<? extends IMarker> markers) {
		return markers.stream()
				.map(this::getAttributes)
				.collect(Collectors.toSet());
	}

	void fix(int problemID) {
		IMarker marker = getFixableMarker(problemID);
		Map<String, Object> attributes = getAttributes(marker);

		IMarkerResolutionGenerator2 generator = new ArchitectureMarkerResolutionGenerator();
		assertThat("No quick fix available for problem " + problemID, generator.hasResolutions(marker), is(true));
		IMarkerResolution[] fixes = generator.getResolutions(marker);
		assertThat("No quick fix provided by generator", fixes.length, greaterThanOrEqual(1));
		fixes[0].run(marker);

		fixedMarkers.add(attributes);
	}

	IMarker getFixableMarker(int problemID) {
		IPluginChecker2.MarkerAttribute attr = IPluginChecker2.problem(problemID);
		IMarker result = modelMarkers.stream().filter(m -> m.getAttribute(attr.getName(), -1) == problemID)
				.findAny().orElse(null);
		assertThat("Fixable problem marker not found: " + problemID, result, notNullValue());
		return result;
	}

}
