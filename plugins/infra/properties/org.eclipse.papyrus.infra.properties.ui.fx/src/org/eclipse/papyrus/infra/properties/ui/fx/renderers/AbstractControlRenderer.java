package org.eclipse.papyrus.infra.properties.ui.fx.renderers;

import org.eclipse.papyrus.infra.properties.ui.fx.FXRenderer;
import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.ui.modelelement.ILabeledModelElement;
import org.eclipse.papyrus.infra.properties.ui.modelelement.ModelElement;
import org.eclipse.papyrus.infra.properties.viewmodel.Control;
import org.eclipse.papyrus.infra.properties.viewmodel.UIElement;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public abstract class AbstractControlRenderer implements FXRenderer {

	@Override
	public Node render(UIElement element, DataSource source) {
		return render((Control)element, source);
	}
	
	protected abstract Node render(Control control, DataSource source);
	
	protected String getLabel(Control control, DataSource source) {
		String propertyPath = control.getProperty();
		final ModelElement modelElement = source.getModelElement(propertyPath);
		if (modelElement instanceof ILabeledModelElement) {
			final String label = ((ILabeledModelElement) modelElement).getLabel(getLocalPropertyPath(propertyPath));
			if (null != label && !label.isEmpty()) {
				return label;
			}
		}
		return getLocalPropertyPath(propertyPath);
	}

	protected String getLocalPropertyPath(String propertyPath) {
		return propertyPath.substring(propertyPath.lastIndexOf(":") + 1); //$NON-NLS-1$
	}

	protected String getDescription(Control control, DataSource source) {
		return null; // TODO
	}

	protected Label createLabel(Control control, DataSource source) {
		Label label = new Label(getLabel(control, source));
		String description = getDescription(control, source);
		if (description != null) {
			label.setTooltip(new Tooltip(description));
		}
		return label;
	}

}
