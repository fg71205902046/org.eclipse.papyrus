package org.eclipse.papyrus.infra.properties.ui.fx.renderers;

import org.eclipse.papyrus.infra.properties.ui.fx.FXRenderer;
import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.viewmodel.Control;
import org.eclipse.papyrus.infra.properties.viewmodel.UIElement;
import org.osgi.service.component.annotations.Component;

import javafx.scene.Node;
import javafx.scene.control.Label;

@Component
public class FallbackRenderer extends AbstractControlRenderer implements FXRenderer{

	@Override
	protected Node render(Control control, DataSource source) {
		return new Label("Unsupported control. Property: "+getLabel(control, source));
	}

	@Override
	public double getPriority(UIElement element, DataSource source) {
		return element instanceof Control ? Double.MIN_VALUE : Double.NaN;
	}

}
