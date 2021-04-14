package org.eclipse.papyrus.infra.properties.ui.fx.impl;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.papyrus.infra.properties.ui.fx.FXRenderer;
import org.eclipse.papyrus.infra.properties.ui.fx.UIElementRenderer;
import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.viewmodel.UIElement;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import javafx.scene.Node;

@Component
public class UIElementRendererImpl implements UIElementRenderer {

	private final Set<FXRenderer> fxRenderers = new HashSet<>();

	@Override
	public Node render(UIElement childNode, DataSource source) {
		FXRenderer renderer = getRenderer(childNode, source);
		if (renderer == null) {
			System.err.println("Unable to find a renderer for " + childNode);
			return null;
		}
		return renderer.render(childNode, source);
	}

	private FXRenderer getRenderer(UIElement childNode, DataSource source) {
		double currentPriority = Double.MIN_VALUE;
		FXRenderer currentRenderer = null;
		for (FXRenderer renderer : fxRenderers) {
			double priority = renderer.getPriority(childNode, source);
			if (Double.isNaN(priority)) {
				continue;
			}
			if (currentRenderer == null || priority > currentPriority) {
				currentRenderer = renderer;
				currentPriority = priority;
			}
		}
		return currentRenderer;
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void registerRenderer(FXRenderer renderer) {
		fxRenderers.add(renderer);
	}

	public void unregisterRenderer(FXRenderer renderer) {
		fxRenderers.remove(renderer);
	}

}
