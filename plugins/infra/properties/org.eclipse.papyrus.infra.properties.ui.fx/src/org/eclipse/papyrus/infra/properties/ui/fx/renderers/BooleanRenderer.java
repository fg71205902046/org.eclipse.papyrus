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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

@Component
public class BooleanRenderer extends AbstractControlRenderer implements FXRenderer {

	@Override
	protected Node render(Control element, DataSource source) {
		Control control = (Control) element;
		IObservable observable = source.getObservable(control.getProperty());

		Label label = createLabel(control, source);
		RadioButton trueButton = new RadioButton("true");
		RadioButton falseButton = new RadioButton("false");
		ToggleGroup toggleGroup = new ToggleGroup();
		trueButton.setToggleGroup(toggleGroup);
		falseButton.setToggleGroup(toggleGroup);

		if (observable instanceof IObservableValue) {
			IObservableValue<?> obsValue = (IObservableValue<?>) observable;
			obsValue.addValueChangeListener(event -> {
				toggleGroup.selectToggle(Boolean.TRUE.equals(obsValue.getValue()) ? trueButton : falseButton);
			});
			toggleGroup.selectToggle(Boolean.TRUE.equals(obsValue.getValue()) ? trueButton : falseButton);
		}

		HBox booleanEditor = new HBox(label, trueButton, falseButton);
		booleanEditor.setAlignment(Pos.CENTER_LEFT);
		booleanEditor.setSpacing(15);

		return booleanEditor;
	}

	@Override
	public double getPriority(UIElement element, DataSource source) {
		if (element instanceof Control) {
			Control control = (Control) element;
			String hint = control.getRendererHint();
			try {
				if (Type.getByName(hint) == Type.BOOLEAN) {
					return 1;
				}
			} catch (IllegalArgumentException ex) {
				// Ignore; we simply don't support it
			}
		}
		return Double.NaN;
	}

}
