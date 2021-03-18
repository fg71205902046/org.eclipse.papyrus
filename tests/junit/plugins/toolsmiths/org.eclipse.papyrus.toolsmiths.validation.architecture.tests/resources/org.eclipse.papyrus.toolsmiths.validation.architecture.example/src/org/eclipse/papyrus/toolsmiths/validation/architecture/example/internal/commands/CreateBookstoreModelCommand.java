package org.eclipse.papyrus.toolsmiths.validation.architecture.example.internal.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.uml.diagram.common.commands.ModelCreationCommandBase;
import org.eclipse.uml2.uml.UMLFactory;

public class CreateBookstoreModelCommand extends ModelCreationCommandBase {

	@Override
	protected EObject createRootElement() {
		return UMLFactory.eINSTANCE.createModel();
	}

}
