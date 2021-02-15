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
 *   Christian W. Damus - bug 570097
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.common.quickfix;

import java.util.Optional;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CommonProblemConstants;
import org.eclipse.ui.IMarkerResolution;

/**
 * Helper class with utility methods used by {@linkplain IMarkerResolution marker resolutions}.
 */
public final class CommonMarkerResolutionUtils {

	/** Returns the model name stored in the marker attribute {@link CommonProblemConstants#MODEL_NAME}. */
	public static Optional<String> getModelName(IMarker marker) {
		return Optional.ofNullable(marker.getAttribute(CommonProblemConstants.MODEL_NAME, null));
	}

	/** Returns the model path stored in the marker attribute {@link CommonProblemConstants#MODEL_PATH}. */
	public static Optional<IPath> getModelPath(IMarker marker) {
		return Optional.ofNullable(marker.getAttribute(CommonProblemConstants.MODEL_PATH, null)).map(Path::new);
	}

}
