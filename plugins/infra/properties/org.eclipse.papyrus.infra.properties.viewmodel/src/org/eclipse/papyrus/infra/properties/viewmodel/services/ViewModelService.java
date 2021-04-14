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

public interface ViewModelService {

	ViewModel getViewModel(Section section, View view);
	
}
