/*****************************************************************************
 * Copyright (c) 2021 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.toolsmiths.validation.architecture.tests;

import static org.eclipse.papyrus.junit.matchers.WorkspaceMatchers.isMarkerMessage;
import static org.eclipse.papyrus.junit.matchers.WorkspaceMatchers.isMarkerSeverity;
import static org.eclipse.papyrus.toolsmiths.validation.architecture.constants.ArchitecturePluginValidationConstants.ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.papyrus.junit.framework.classification.tests.AbstractPapyrusTest;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.AuxProject;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.Build;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.MarkerType;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.OverlayFile;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProject;
import org.eclipse.papyrus.toolsmiths.validation.common.tests.rules.TestProjectFixture;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * Test cases for the <em>Architecture Domain Model</em> validation of the model resource
 * in the project builder.
 */
@RunWith(Enclosed.class)
public class ArchitectureModelBuilderTest extends AbstractPapyrusTest {

	@TestProject("org.eclipse.papyrus.toolsmiths.validation.architecture.example")
	@MarkerType(ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE)
	@Build
	public static class Intrinsic {
		/**
		 * The project fixture to manage easily the project.
		 */
		@Rule
		public final TestProjectFixture fixture = new TestProjectFixture();

		/**
		 * Test the reporting of an unresolved creation command class.
		 */
		@Test
		@OverlayFile(value = "bug570097-models/BookStore-missingCommandClass.architecture", path = "resources/BookStore.architecture")
		public void unresolvedCreationCommandClass() {
			final List<IMarker> modelMarkers = fixture.getMarkers("resources/BookStore.architecture"); //$NON-NLS-1$

			assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(isMarkerMessage(containsString("ceationCommandClassExists")/* sic */)))); //$NON-NLS-1$
		}

		/**
		 * Test the reporting of a creation command class that exists but does not conform to {@code IModelCreationCommand}.
		 */
		@Test
		@OverlayFile(value = "bug570097-models/BookStore-invalidCommandClass.architecture", path = "resources/BookStore.architecture")
		public void invalidCreationCommandClass() {
			final List<IMarker> modelMarkers = fixture.getMarkers("resources/BookStore.architecture"); //$NON-NLS-1$

			assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(isMarkerMessage(containsString("conversionCommandClassExists"))))); //$NON-NLS-1$
		}

		/**
		 * Test that validation finds a creation command class that is a binary JDT type (from the target platform).
		 * Other test cases cover source types.
		 */
		@Test
		@OverlayFile(value = "bug570097-models/BookStore-commandClassBinaryType.architecture", path = "resources/BookStore.architecture")
		// Create another project that brings Papyrus UML Class Diagram onto the classpath so that our
		// command class reference is resolvable by JDT as a binary type
		@AuxProject("org.eclipse.papyrus.toolsmiths.validation.architecture.classdiagram")
		public void creationCommandClassBinaryTypeResolved() {
			final List<IMarker> modelMarkers = fixture.getMarkers("META-INF/MANIFEST.MF"); //$NON-NLS-1$

			assertThat(modelMarkers, not(hasItem(isMarkerMessage(containsString("ceationCommandClassExists")/* sic */)))); //$NON-NLS-1$
		}
	}

	@TestProject("org.eclipse.papyrus.toolsmiths.validation.architecture.example")
	@MarkerType(ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE)
	@Build
	public static class Custom {
		/**
		 * The project fixture to manage easily the project.
		 */
		@Rule
		public final TestProjectFixture fixture = new TestProjectFixture();

		/**
		 * Test the reporting of an icon that doesn't exist.
		 */
		@Test
		@OverlayFile(value = "bug570097-models/BookStore-missingIcon.architecture", path = "resources/BookStore.architecture")
		public void unresolvedIconResourceURI() {
			final List<IMarker> modelMarkers = fixture.getMarkers("resources/BookStore.architecture"); //$NON-NLS-1$

			assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(isMarkerMessage(containsString("no-such-icon.png"))))); //$NON-NLS-1$
		}

		/**
		 * Test the reporting of an grayed representation kind icon that doesn't exist.
		 */
		@Test
		@OverlayFile(value = "bug570097-models/BookStore-missingGrayedIcon.architecture", path = "resources/BookStore.architecture")
		public void unresolvedGrayedIconResourceURI() {
			final List<IMarker> modelMarkers = fixture.getMarkers("resources/BookStore.architecture"); //$NON-NLS-1$

			assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(isMarkerMessage(containsString("no-such-icon_d.png"))))); //$NON-NLS-1$
		}

		/**
		 * Test the reporting of an icon URI that is unparsable as a URI.
		 */
		@Test
		@OverlayFile(value = "bug570097-models/BookStore-invalidIcon.architecture", path = "resources/BookStore.architecture")
		public void invalidIconResourceURI() {
			final List<IMarker> modelMarkers = fixture.getMarkers("resources/BookStore.architecture"); //$NON-NLS-1$

			assertThat(modelMarkers, hasItem(both(isMarkerSeverity(IMarker.SEVERITY_ERROR)).and(isMarkerMessage(containsString("Invalid icon URI"))))); //$NON-NLS-1$
		}
	}

}
