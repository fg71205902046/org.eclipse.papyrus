package org.eclipse.papyrus.example.diagram.simplediagram.providers;

import org.eclipse.papyrus.example.diagram.simplediagram.edit.parts.ModelEditPart;
import org.eclipse.papyrus.example.diagram.simplediagram.edit.parts.UMLEditPartFactory;
import org.eclipse.papyrus.example.diagram.simplediagram.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.infra.gmfdiag.tooling.runtime.providers.DefaultEditPartProvider;

/**
 * @generated
 */
public class UMLEditPartProvider extends DefaultEditPartProvider {

	/**
	 * @generated
	 */
	public UMLEditPartProvider() {
		super(new UMLEditPartFactory(), UMLVisualIDRegistry.TYPED_INSTANCE,
				ModelEditPart.MODEL_ID);
	}

}
