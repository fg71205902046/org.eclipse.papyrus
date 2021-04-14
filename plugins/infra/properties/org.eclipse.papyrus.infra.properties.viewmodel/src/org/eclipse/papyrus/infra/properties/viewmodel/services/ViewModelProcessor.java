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
import org.eclipse.papyrus.infra.properties.viewmodel.ViewModel;

/**
 * Will be invoked on each view model before it is rendered.
 */
public interface ViewModelProcessor {

	/**
	 * Process the given viewModel before rendering, and return the modified view
	 * model (Or the original one if it hasn't been modified).
	 * 
	 * @param viewModel The original view model. This model shouldn't be modified by
	 *                  the {@link ViewModelProcessor}, as it may be cached or
	 *                  shared with other rendering contexts. Instead, if
	 *                  modifications are required, the processor should work on a
	 *                  copy of the ViewModel.
	 * @param section
	 * @param view
	 * @return The modified view model, or the original one if no modification was
	 *         required
	 */
	ViewModel process(ViewModel viewModel, Section section, View view);

	/**
	 * The order in which this view model processor should be invoked. Higher values
	 * are called later than lower values.
	 * 
	 * @return The order for this processor
	 */
	double getOrder();
}
