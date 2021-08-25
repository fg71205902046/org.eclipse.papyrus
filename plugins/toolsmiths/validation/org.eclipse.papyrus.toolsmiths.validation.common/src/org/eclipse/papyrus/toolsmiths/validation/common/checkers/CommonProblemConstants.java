/*****************************************************************************
 * Copyright (c) 2021 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   Christian W. Damus - bugs 570097, 575122
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.common.checkers;

import org.eclipse.papyrus.emf.validation.DependencyValidationUtils;

/**
 * Problem and marker resolution constants for the common plug-in validation framework.
 */
public class CommonProblemConstants {

	/** For markers that report missing extensions for a model, the attribute recording the model name. */
	public static final String MODEL_NAME = "modelName"; //$NON-NLS-1$

	/** For markers that report missing extensions for a model, the attribute recording the model path within the project. */
	public static final String MODEL_PATH = "modelPath"; //$NON-NLS-1$

	/** The base problem ID for problem IDs defined in this common framework. */
	public static final int PROBLEM_ID_BASE = 0xff1000;

	/** A resource is missing from the binary build. */
	public static final int MISSING_FROM_BINARY_BUILD_MARKER_ID = PROBLEM_ID_BASE + 0x00;
	/** Marker attribute specifying the path that is missing from the binary build. */
	public static final String BINARY_BUILD_PATH = "binaryBuildPath"; //$NON-NLS-1$

	/** A dependency is missing from the manifest. */
	public static final int MISSING_DEPENDENCIES_MARKER_ID = PROBLEM_ID_BASE + 0x01;
	/** Marker attribute specifying the dependencies that are missing from the manifest. */
	public static final String MISSING_DEPENDENCIES = DependencyValidationUtils.MISSING_DEPENDENCIES;

	/** A plug-in extension is missing. */
	public static final int MISSING_EXTENSION = PROBLEM_ID_BASE + 0x02;
	/** Marker attribute specifying the extension point that is missing an extension in the plugin.xml. */
	public static final String EXTENSION_POINT = "extensionPoint"; //$NON-NLS-1$

	/** A plug-in extension element is missing. */
	public static final int MISSING_EXTENSION_ELEMENT = PROBLEM_ID_BASE + 0x03;
	/** Marker attribute specifying the element name that is missing from the plugin.xml. */
	public static final String ELEMENT_NAME = "elementName"; //$NON-NLS-1$

	/** A plug-in extension element's attribute is missing. */
	public static final int MISSING_EXTENSION_ATTRIBUTE = PROBLEM_ID_BASE + 0x04;
	/** Marker attribute specifying the attribute name that is missing from the plugin.xml. */
	public static final String ATTRIBUTE_NAME = "attributeName"; //$NON-NLS-1$

	/** A plug-in extension elemen's attribute is ill-specified. */
	public static final int INVALID_EXTENSION_ATTRIBUTE = PROBLEM_ID_BASE + 0x05;

	/**
	 * The highest problem ID defined in the common layer, possibly with some room for expansion.
	 * Tooling-specific problem IDs must not fall in the range from {@link #PROBLEM_ID_BASE} to
	 * {@code MAX_PROBLEM_ID}, inclusive.
	 */
	public static final int MAX_PROBLEM_ID = PROBLEM_ID_BASE + 0x3f;

}
