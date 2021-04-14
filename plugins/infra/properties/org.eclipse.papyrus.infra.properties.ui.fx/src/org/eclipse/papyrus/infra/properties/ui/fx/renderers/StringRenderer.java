package org.eclipse.papyrus.infra.properties.ui.fx.renderers;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.papyrus.infra.properties.environment.Type;
import org.eclipse.papyrus.infra.properties.ui.fx.FXRenderer;
import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.viewmodel.Control;
import org.eclipse.papyrus.infra.properties.viewmodel.UIElement;
import org.osgi.service.component.annotations.Component;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

@Component
public class StringRenderer extends AbstractControlRenderer implements FXRenderer {

	@Override
	protected Node render(Control control, DataSource source) {
		IObservable observable = source.getObservable(control.getProperty());

		Label name = createLabel(control, source);

		TextField nameField = new TextField();
		if (observable instanceof IObservableValue) {
			Object value = ((IObservableValue<?>) observable).getValue();
			if (value instanceof String) {
				nameField.setText((String) value);
			}
		}

		HBox stringEditor = new HBox(name, nameField);
		stringEditor.setAlignment(Pos.CENTER_LEFT);
		stringEditor.setSpacing(15);
		HBox.setHgrow(nameField, Priority.ALWAYS);

		return stringEditor;
	}

	@Override
	public double getPriority(UIElement element, DataSource source) {
		if (element instanceof Control) {
			Control control = (Control) element;
			String hint = control.getRendererHint();
			try {
				if (Type.getByName(hint) == Type.STRING) {
					return 1;
				}
			} catch (IllegalArgumentException ex) {
				// Ignore; we simply don't support it
			}
		}
		return Double.NaN;
	}

}
