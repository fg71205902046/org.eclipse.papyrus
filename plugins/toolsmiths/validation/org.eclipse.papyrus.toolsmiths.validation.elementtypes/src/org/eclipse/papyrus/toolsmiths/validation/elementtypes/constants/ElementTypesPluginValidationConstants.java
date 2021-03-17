/*****************************************************************************
 * Copyright (c) 2019, 2020 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Nicolas FAUVERGUE (CEA LIST) nicolas.fauvergue@cea.fr - Initial API and implementation
 *   Christian W. Damus - bug 569357
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.elementtypes.constants;

/**
 * The element types plug-in validation constants.
 */
public class ElementTypesPluginValidationConstants {

	/**
	 * The marker type for the validation of element types plugin.
	 */
	public static final String ELEMENTTYPES_PLUGIN_VALIDATION_MARKER_TYPE = "org.eclipse.papyrus.toolsmiths.validation.elementtypes.diagnostic"; //$NON-NLS-1$

	/**
	 * The extension point identifier for element types configurations files.
	 */
	public static final String ELEMENTTYPES_EXTENSION_POINT_IDENTIFIER = "org.eclipse.papyrus.infra.types.core.elementTypeSetConfiguration"; //$NON-NLS-1$
}
