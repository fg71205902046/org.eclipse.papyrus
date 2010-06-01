package org.eclipse.papyrus.diagram.profile.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.diagram.profile.edit.commands.ClassOperationCreateCommand;
import org.eclipse.papyrus.diagram.profile.providers.UMLElementTypes;

/**
 * @generated
 */
public class StereotypeOperationCompartmentItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public StereotypeOperationCompartmentItemSemanticEditPolicy() {
		super(UMLElementTypes.Stereotype_1026);
	}


	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if(UMLElementTypes.Operation_3020 == req.getElementType()) {
			return getGEFWrapper(new ClassOperationCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
