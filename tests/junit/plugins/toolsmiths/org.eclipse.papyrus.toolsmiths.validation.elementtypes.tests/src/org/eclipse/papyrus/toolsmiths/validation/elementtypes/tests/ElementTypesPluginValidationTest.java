/*****************************************************************************
 * Copyright (c) 2019 CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Nicolas FAUVERGUE (CEA LIST) nicolas.fauvergue@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.toolsmiths.validation.elementtypes.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.papyrus.junit.framework.classification.tests.AbstractPapyrusTest;
import org.eclipse.papyrus.junit.utils.rules.ProjectFixture;
import org.eclipse.papyrus.toolsmiths.validation.elementtypes.checkers.ElementTypesPluginCheckerService;
import org.eclipse.papyrus.toolsmiths.validation.elementtypes.constants.ElementTypesPluginValidationConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * This class allows to test the profile plug-in validation.
 * This will check the markers resulting from a failing profile plug-in definition.
 */
public class ElementTypesPluginValidationTest extends AbstractPapyrusTest {

	/**
	 * The plug-in path to copy into the project fixture
	 */
	private static final String PLUGIN_PATH = "resources/org.eclipse.papyrus.toolsmiths.validation.elementtypes.example";//$NON-NLS-1$

	/**
	 * The severity attribute identifier.
	 */
	private static final String SEVERITY_ID = "severity"; //$NON-NLS-1$

	/**
	 * The project fixture to manage easily the project.
	 */
	@Rule
	public final ProjectFixture fixture = new ProjectFixture();

	/**
	 * This allows to copy the project into the fixture project.
	 */
	@Before
	public void init() {
		try {
			fixture.copyFolder(ElementTypesPluginValidationTest.class, PLUGIN_PATH);
		} catch (IOException e) {
			Assert.fail("Error while copying project"); //$NON-NLS-1$
		}
	}

	/**
	 * This allows to test the profile plug-in validation.
	 */
	@Test
	public void testProfilePluginValidation() {
		// First, run the validation
		ElementTypesPluginCheckerService.checkElementTypesPlugin(fixture.getProject(), new NullProgressMonitor());

		// Get the markers
		List<IMarker> markers = null;
		try {
			markers = Arrays.asList(fixture.getProject().findMarkers(ElementTypesPluginValidationConstants.ELEMENTTYPES_PLUGIN_VALIDATION_TYPE, true, IResource.DEPTH_INFINITE));
		} catch (CoreException e) {
			Assert.fail("Error with resource"); //$NON-NLS-1$
		}

		// Now check the markers
		Assert.assertNotNull("The markers have to be found", markers); //$NON-NLS-1$
		Assert.assertEquals("The number of markers is not correct", 10, markers.size()); //$NON-NLS-1$

		// Check the elementtypesconfigurations file markers
		final List<IMarker> elementtypesFileMarkers = markers.stream().filter(marker -> marker.getResource().getFullPath().toString().endsWith("BookStore.elementtypesconfigurations")).collect(Collectors.toList()); //$NON-NLS-1$
		Assert.assertNotNull("Elementtypesconfigurations file markers are not found", elementtypesFileMarkers); //$NON-NLS-1$
		Assert.assertEquals("The number of markers for elementtypesconfigurations file is not correct", 1, elementtypesFileMarkers.size()); //$NON-NLS-1$
		Assert.assertTrue("The severity of elementtypesconfigurations marker is not correct", isMarkerSeverity(elementtypesFileMarkers.get(0), IMarker.SEVERITY_ERROR)); //$NON-NLS-1$

		// Check the dependencies markers
		final List<IMarker> dependenciesMarkers = markers.stream().filter(marker -> marker.getResource().getFullPath().toString().endsWith("MANIFEST.MF")).collect(Collectors.toList()); //$NON-NLS-1$
		Assert.assertNotNull("Dependencies markers are not found", dependenciesMarkers); //$NON-NLS-1$
		Assert.assertEquals("The number of markers for dependencies is not correct", 8, dependenciesMarkers.size()); //$NON-NLS-1$
		final List<IMarker> warningExtensionsMarkers = dependenciesMarkers.stream().filter(marker -> isMarkerSeverity(marker, IMarker.SEVERITY_WARNING)).collect(Collectors.toList());
		Assert.assertEquals("The number of warning markers for dependencies is not correct", 6, warningExtensionsMarkers.size()); //$NON-NLS-1$
		final List<IMarker> errorExtensionsMarkers = dependenciesMarkers.stream().filter(marker -> isMarkerSeverity(marker, IMarker.SEVERITY_ERROR)).collect(Collectors.toList());
		Assert.assertEquals("The number of error markers for dependencies is not correct", 2, errorExtensionsMarkers.size()); //$NON-NLS-1$

		// Check the extensions markers
		final List<IMarker> extensionsMarkers = markers.stream().filter(marker -> marker.getResource().getFullPath().toString().endsWith("plugin.xml")).collect(Collectors.toList()); //$NON-NLS-1$
		Assert.assertNotNull("Extensions markers are not found", extensionsMarkers); //$NON-NLS-1$
		Assert.assertEquals("The number of markers for extensions is not correct", 1, extensionsMarkers.size()); //$NON-NLS-1$
		Assert.assertTrue("The severity of extensions marker is not correct", isMarkerSeverity(elementtypesFileMarkers.get(0), IMarker.SEVERITY_ERROR)); //$NON-NLS-1$
	}

	/**
	 * This allows to determinate if a marker got the correct severity.
	 *
	 * @param marker
	 *            the marker.
	 * @param severity
	 *            The severity to get.
	 * @return <code>true</code> if the marker got the correct severity, <code>false</code> otherwise.
	 */
	private boolean isMarkerSeverity(final IMarker marker, final int severity) {
		try {
			return severity == (int) marker.getAttribute(SEVERITY_ID);
		} catch (CoreException e) {
			Assert.fail("Error while getting " + SEVERITY_ID); //$NON-NLS-1$
			return false;
		}
	}

}