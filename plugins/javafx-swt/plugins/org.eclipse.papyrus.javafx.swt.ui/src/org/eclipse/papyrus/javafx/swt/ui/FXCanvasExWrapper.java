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
 *   EclipseSource - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.javafx.swt.ui;

import org.eclipse.gef.fx.swt.canvas.FXCanvasEx;
import org.eclipse.papyrus.javafx.FXWrapperService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.osgi.service.component.annotations.Component;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Helper class to integrate JavaFX in SWT, without introducing API-Dependency
 * to sensitive classes such as FXCanvas. This class has also been extracted to
 * its own plug-in, so it can be built separately if necessary.
 */
@Component
public class FXCanvasExWrapper implements FXWrapperService {

	/**
	 * Create an SWT Control that wraps the specified javaFXParent Node. The
	 * new Control will be added as a child node to the specified swtParent.
	 * 
	 * @param swtParent    The SWT Composite that will contain the SWT-to-JavaFX
	 *                     Composite
	 * @param javaFXParent The root JavaFX {@link Parent} {@link Node} to integrate
	 *                     in SWT
	 */
	public void toSWTComposite(Composite swtParent, Parent javaFXParent) {
		FXCanvasEx fxCanvas = new FXCanvasEx(swtParent, SWT.NONE);
		fxCanvas.setScene(new Scene(javaFXParent));
	}

}
