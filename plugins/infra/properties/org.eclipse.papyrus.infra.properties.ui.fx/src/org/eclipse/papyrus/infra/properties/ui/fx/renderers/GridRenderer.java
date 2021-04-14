package org.eclipse.papyrus.infra.properties.ui.fx.renderers;

import org.eclipse.papyrus.infra.properties.ui.fx.FXRenderer;
import org.eclipse.papyrus.infra.properties.ui.fx.UIElementRenderer;
import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.viewmodel.GridLayout;
import org.eclipse.papyrus.infra.properties.viewmodel.UIElement;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

@Component
public class GridRenderer implements FXRenderer {

	private UIElementRenderer renderer;

	@Override
	public Node render(UIElement element, DataSource source) {
		GridLayout gridLayout = (GridLayout) element;
		int columns = gridLayout.getColumns();

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		
		for (int i = 0; i < columns; i++) {
			ColumnConstraints constraint = new ColumnConstraints();
			constraint.setFillWidth(true);
			constraint.setHgrow(Priority.ALWAYS);
			grid.getColumnConstraints().add(constraint);
		}

		int row = 0, column = 0;
		for (UIElement childElement : gridLayout.getChildren()) {
			Node childNode = renderer.render(childElement, source);
			if (childNode != null) {
				grid.add(childNode, column, row);
			}
			column++;
			if (column >= columns) {
				column = 0;
				row++;
			}
			GridPane.setFillWidth(childNode, Boolean.TRUE);
			GridPane.setHalignment(childNode, HPos.LEFT);
		}

		return grid;
	}

	@Override
	public double getPriority(UIElement element, DataSource source) {
		return element instanceof GridLayout ? 1 : Double.NaN;
	}
	
	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	public void setUIElementRenderer(UIElementRenderer renderer) {
		this.renderer = renderer;
	}
	
	public void unsetUIElementRenderer(UIElementRenderer renderer){
		this.renderer = null;
	}

}
