package org.eclipse.papyrus.infra.properties.ui.fx;

import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.viewmodel.UIElement;

import javafx.scene.Node;

public interface FXRenderer {
	Node render(UIElement element, DataSource source);
	
	double getPriority(UIElement element, DataSource source);
}
