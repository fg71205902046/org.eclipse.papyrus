package org.eclipse.papyrus.example.diagram.simplediagram.part;

import org.eclipse.papyrus.infra.gmfdiag.tooling.runtime.part.DefaultModelElementSelectionPage;

/**
 * Wizard page that allows to select element from model.
 *
 * @generated
 */
public class ModelElementSelectionPage extends DefaultModelElementSelectionPage {

	/**
	 * @generated
	 */
	public ModelElementSelectionPage(String pageName) {
		super(UMLDiagramEditorPlugin.getInstance()
				.getItemProvidersAdapterFactory(), pageName);
	}

	/**
	 * Override to provide custom model element description.
	 *
	 * @generated
	 */
	@Override
	protected String getSelectionTitle() {
		return Messages.ModelElementSelectionPageMessage;
	}

}
