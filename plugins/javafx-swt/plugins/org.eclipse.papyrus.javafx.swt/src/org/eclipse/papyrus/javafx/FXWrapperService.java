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
package org.eclipse.papyrus.javafx;

import org.eclipse.swt.widgets.Composite;

import javafx.scene.Parent;

/**
 * <p>
 * A Service to integrate JavaFX Controls in SWT, without introducing a direct
 * dependency to the Canvas implementation (e.g. FXCanvas)
 * </p>
 */
public interface FXWrapperService {

	/**
	 * Embed the specified javaFX Control in the SWT Composite
	 * 
	 * @param swtParent
	 * @param javaFXParent
	 */
	void toSWTComposite(Composite swtParent, Parent javaFXParent);
}
