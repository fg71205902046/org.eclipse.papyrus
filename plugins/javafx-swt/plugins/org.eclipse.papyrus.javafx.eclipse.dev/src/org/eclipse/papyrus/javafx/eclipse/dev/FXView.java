package org.eclipse.papyrus.javafx.eclipse.dev;

import org.eclipse.papyrus.javafx.swt.ui.FXCanvasExWrapper;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * A simple JavaFX View to make sure we can render JavaFX
 * code in SWT.
 */
public class FXView extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		BorderPane pane = new BorderPane();
		new FXCanvasExWrapper().toSWTComposite(parent, pane);
		
		pane.setCenter(new Label("JavaFX View"));
	}

	@Override
	public void setFocus() {
	}

}
