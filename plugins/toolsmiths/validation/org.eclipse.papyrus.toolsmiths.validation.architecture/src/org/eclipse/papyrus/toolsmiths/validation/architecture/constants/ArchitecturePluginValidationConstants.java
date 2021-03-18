/*****************************************************************************
 * Copyright (c) 2019, 2021 CEA LIST, Christian W. Damus, and others.
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
 *   Christian W. Damus - bug 570097
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.architecture.constants;

/**
 * The architecture plug-in validation constants.
 */
public class ArchitecturePluginValidationConstants {

	/**
	 * The type for the validation of architecture plugin.
	 */
	public static final String ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE = "org.eclipse.papyrus.toolsmiths.validation.architecture.diagnostic"; //$NON-NLS-1$

	/**
	 * The extension point identifier for architecture configurations files.
	 */
	public static final String ARCHITECTURE_EXTENSION_POINT_IDENTIFIER = "org.eclipse.papyrus.infra.architecture.models"; //$NON-NLS-1$
}
