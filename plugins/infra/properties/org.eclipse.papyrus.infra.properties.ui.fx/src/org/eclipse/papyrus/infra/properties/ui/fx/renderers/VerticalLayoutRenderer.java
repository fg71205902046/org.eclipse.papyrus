package org.eclipse.papyrus.infra.properties.ui.fx.renderers;

import org.eclipse.papyrus.infra.properties.ui.fx.FXRenderer;
import org.eclipse.papyrus.infra.properties.ui.fx.UIElementRenderer;
import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.viewmodel.UIElement;
import org.eclipse.papyrus.infra.properties.viewmodel.VerticalLayout;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

@Component
public class VerticalLayoutRenderer implements FXRenderer {

	private UIElementRenderer renderer;

	@Override
	public Node render(UIElement element, DataSource source) {
		VBox vBox = new VBox();
		vBox.setFillWidth(true);

		VerticalLayout layout = (VerticalLayout) element;

		for (UIElement childElement : layout.getChildren()) {
			Node render = renderer.render(childElement, source);
			if (render != null) {
				vBox.getChildren().add(render);
			} else {
				System.err.println("Unable to render child node " + childElement);
			}
		}
		return vBox;
	}

	@Override
	public double getPriority(UIElement element, DataSource source) {
		return element instanceof VerticalLayout ? 1 : Double.NaN;
	}
	
	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	public void setUIElementRenderer(UIElementRenderer renderer) {
		this.renderer = renderer;
	}
	
	public void unsetUIElementRenderer(UIElementRenderer renderer){
		this.renderer = null;
	}

}
