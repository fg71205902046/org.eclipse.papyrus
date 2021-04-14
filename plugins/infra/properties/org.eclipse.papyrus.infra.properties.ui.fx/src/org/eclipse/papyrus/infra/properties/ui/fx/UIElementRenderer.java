package org.eclipse.papyrus.infra.properties.ui.fx;

import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.viewmodel.UIElement;

import javafx.scene.Node;

public interface UIElementRenderer {
	Node render(UIElement element, DataSource source);
}
