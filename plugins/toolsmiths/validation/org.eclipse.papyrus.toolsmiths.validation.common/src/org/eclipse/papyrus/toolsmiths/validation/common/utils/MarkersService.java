/*****************************************************************************
 * Copyright (c) 2019 CEA LIST and others.
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
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.common.utils;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.MarkersManagementUtils;

/**
 * This allows to provide services about markers.
 * In this case, we can provide methods to create and delete markers.
 */
public class MarkersService {

	/**
	 * This allows to create a marker for a resource.
	 *
	 * @param resource
	 *            The resource where create the marker.
	 * @param type
	 *            The type of the marker validation.
	 * @param message
	 *            The message of the marker.
	 * @param severity
	 *            The severity of the marker.
	 * @return The created marker or <code>null</code> if there is an error.
	 */
	public static IMarker createMarker(final IResource resource, final String type, final String message, final int severity) {
		return MarkersManagementUtils.createMarker(resource, type, message, severity);
	}

	/**
	 * This allows to create a marker for a resource.
	 *
	 * @param resource
	 *            The resource where create the marker.
	 * @param type
	 *            The type of the marker validation.
	 * @return The created marker or <code>null</code> if there is an error.
	 */
	public static IMarker createMarker(final IResource resource, final String type) {
		return MarkersManagementUtils.createMarker(resource, type);
	}

	/**
	 * This allows to delete markers of a resource.
	 *
	 * @param resource
	 *            The resource.
	 * @param type
	 *            The type of markers to delete.
	 */
	public static void deleteMarkers(final IResource resource, final String type) {
		MarkersManagementUtils.deleteMarkers(resource, type);
	}

}
