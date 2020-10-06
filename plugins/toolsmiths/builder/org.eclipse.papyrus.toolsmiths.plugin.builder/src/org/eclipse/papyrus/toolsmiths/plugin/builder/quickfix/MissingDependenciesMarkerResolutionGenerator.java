/*****************************************************************************
 * Copyright (c) 2020 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.toolsmiths.plugin.builder.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.papyrus.emf.validation.DependencyValidationUtils;
import org.eclipse.papyrus.toolsmiths.plugin.builder.Activator;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;

/**
 * This class is in charge to provide the QuickFix for missing dependencies
 */
public class MissingDependenciesMarkerResolutionGenerator implements IMarkerResolutionGenerator2 {

	/**
	 * @see org.eclipse.ui.IMarkerResolutionGenerator#getResolutions(org.eclipse.core.resources.IMarker)
	 *
	 * @param marker
	 * @return
	 */
	@Override
	public IMarkerResolution[] getResolutions(IMarker marker) {
		String value;
		try {
			value = (String) marker.getAttribute(DependencyValidationUtils.MISSING_DEPENDENCIES);
			if (value != null && !value.isEmpty()) {
				return new IMarkerResolution[] { new MissingDependenciesMarkerResolution() };
			}
		} catch (CoreException e) {
			Activator.log.error(e);
		}

		return new IMarkerResolution[] {};
	}

	/**
	 * @see org.eclipse.ui.IMarkerResolutionGenerator2#hasResolutions(org.eclipse.core.resources.IMarker)
	 *
	 * @param marker
	 * @return
	 */
	@Override
	public boolean hasResolutions(IMarker marker) {
		try {
			final String value = (String) marker.getAttribute(DependencyValidationUtils.MISSING_DEPENDENCIES);
			return value != null && !value.isEmpty();
		} catch (CoreException e) {
			// nothing to do
		}
		return false;
	}

}
