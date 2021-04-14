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
package org.eclipse.papyrus.infra.properties.ui.fx;

import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.viewmodel.ViewModel;

/**
 * A service to render a ViewModel in the UI.
 */
// FIXME: Proper integration needs to be defined. Multiple view model renderers
// may coexist (e.g. SWT Renderer vs FX Renderer), but only one of them can
// be invoked by a specific Section (e.g. FXSection needs an FXViewModelRenderer)
// Having this as a generic service maybe doesn't make sense
public interface ViewModelRenderer {

	/**
	 * Renders the ViewModel in the specified parent
	 *
	 * @param viewModel
	 *            The view model to render
	 * @param source
	 * @param parent
	 *            the UI Parent (e.g. SWT Composite, ...)
	 */
	void render(ViewModel viewModel, DataSource source, Object parent);

}
