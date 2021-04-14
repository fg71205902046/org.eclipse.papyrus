package org.eclipse.papyrus.infra.properties.ui.fx.renderers;

import org.eclipse.papyrus.infra.properties.environment.Type;
import org.eclipse.papyrus.infra.properties.ui.fx.FXRenderer;
import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.viewmodel.Control;
import org.eclipse.papyrus.infra.properties.viewmodel.UIElement;
import org.osgi.service.component.annotations.Component;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

@Component
public class EnumRenderer extends AbstractControlRenderer implements FXRenderer {

	@Override
	protected Node render(Control control, DataSource source) {
		ComboBox<Object> combo = new ComboBox<>();
		
		Label label = createLabel(control, source);
		
		HBox enumEditor = new HBox(label, combo);
		enumEditor.setAlignment(Pos.CENTER_LEFT);
		enumEditor.setSpacing(15);
		
		return enumEditor;
	}

	@Override
	public double getPriority(UIElement element, DataSource source) {
		if (element instanceof Control) {
			Control control = (Control) element;
			String hint = control.getRendererHint();
			try {
				if (Type.getByName(hint) == Type.ENUMERATION || Type.getByName(hint) == Type.REFERENCE) {
					return 1;
				}
			} catch (IllegalArgumentException ex) {
				// Ignore; we simply don't support it
			}
		}
		return Double.NaN;
	}

}
