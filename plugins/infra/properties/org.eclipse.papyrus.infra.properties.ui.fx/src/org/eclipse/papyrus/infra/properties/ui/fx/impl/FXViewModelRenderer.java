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
 *****************************************************************************/
package org.eclipse.papyrus.infra.properties.ui.fx.impl;

import org.eclipse.papyrus.infra.properties.ui.fx.UIElementRenderer;
import org.eclipse.papyrus.infra.properties.ui.fx.ViewModelRenderer;
import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.viewmodel.UIElement;
import org.eclipse.papyrus.infra.properties.viewmodel.ViewModel;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

@Component
public class FXViewModelRenderer implements ViewModelRenderer {

	private UIElementRenderer renderer;
	
	/**
	 * @see org.eclipse.papyrus.infra.properties.ui.fx.ViewModelRenderer#render(org.eclipse.papyrus.infra.properties.viewmodel.ViewModel,
	 *      java.lang.Object)
	 *
	 * @param viewModel
	 * @param parent
	 */
	@Override
	public void render(ViewModel viewModel, DataSource source, Object parent) {
		if (parent instanceof Pane) {
			Pane parentPane = (Pane) parent;
			for (UIElement childNode : viewModel.getChildren()) {
				Node render = renderer.render(childNode, source);
				if (render != null) {
					parentPane.getChildren().add(render);
				}
			}
		}
	}

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	public void setUIElementRenderer(UIElementRenderer renderer) {
		this.renderer = renderer;
	}
	
	public void unsetUIElementRenderer(UIElementRenderer renderer){
		this.renderer = null;
	}

}
