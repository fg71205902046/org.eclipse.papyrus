/*****************************************************************************
 * Copyright (c) 2020, 2021 CEA LIST, EclipseSource, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Alexandra Buzila (EclipseSource) - Initial API and implementation
 *   Christian W. Damus - bugs 570097, 572677, 575122
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.common.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.plugin.IPluginElement;

/**
 * Marker Resolution that sets an attribute in the {@link IPluginElement} referenced by the marker.
 */
public abstract class AbstractSetAttributeMarkerResolution extends AbstractEditExtensionMarkerResolution {

	/**
	 * Initializes me with the problem that I fix.
	 *
	 * @param problemID
	 *            the problem that I fix
	 */
	public AbstractSetAttributeMarkerResolution(int problemID) {
		super(problemID);
	}

	protected abstract String getAttributeValue(IMarker marker) throws CoreException;

	@Override
	protected void editExtensionElement(IMarker marker, IPluginElement element) throws CoreException {
		String name = getAttributeName(marker);
		String value = name == null ? null : getAttributeValue(marker);
		if (value != null) {
			this.setAttribute(element, name, value);
		}
	}

}
