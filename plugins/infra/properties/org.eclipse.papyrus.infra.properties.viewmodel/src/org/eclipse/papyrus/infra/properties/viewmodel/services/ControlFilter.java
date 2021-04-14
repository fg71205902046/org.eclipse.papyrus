/*****************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   EclipseSource - Bug 572794
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.properties.viewmodel.services;

import org.eclipse.papyrus.infra.properties.contexts.Section;
import org.eclipse.papyrus.infra.properties.contexts.View;
import org.eclipse.papyrus.infra.properties.viewmodel.Control;

/**
 * A service to filter controls (properties) that should be included or excluded
 * from the view model before rendering.
 */
public interface ControlFilter {

	/**
	 * Test if the specified Control (property) should be rendered. Note: if the
	 * Control should be rendered based on a dynamic condition, that may change over
	 * time for the same selection, ControlFilters should return
	 * {@link Boolean#TRUE} (To make sure the control is included in the view
	 * model). A different mechanism will be used to conditionally display the
	 * Control.
	 * 
	 * @param control
	 * @param section
	 * @param view
	 * @return
	 */
	boolean shouldDisplay(Control control, Section section, View view);
}
