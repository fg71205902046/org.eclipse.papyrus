/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.common.edit.policy;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.gmf.diagram.common.edit.policy.DefaultSemanticEditPolicy;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

/**
 * Semantic edit policy for Property owned by a Block (Part or Reference especially).
 */
public class BlockPropertyCompositeSemanticEditPolicy extends DefaultSemanticEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getCreateCommand(CreateElementRequest req) {

		// Port - FlowPort creation is allowed if the semantic element is 
		// a Property typed by a Block, the new Port - FlowPort is created on this Block.
		EObject eObject = req.getContainer();
		if ((eObject != null) && (eObject instanceof Property)) {
			Type type = ((Property) eObject).getType();
			if ((type != null) && (((ISpecializationType)SysMLElementTypes.BLOCK).getMatcher().matches(type))) {
				
				if((SysMLElementTypes.FLOW_PORT == req.getElementType())
					|| (SysMLElementTypes.FLOW_PORT_IN == req.getElementType())
					|| (SysMLElementTypes.FLOW_PORT_OUT == req.getElementType())
					|| (SysMLElementTypes.FLOW_PORT_IN_OUT == req.getElementType())
					|| (SysMLElementTypes.FLOW_PORT_NA == req.getElementType())
					|| (UMLElementTypes.PORT == req.getElementType())) {
					
					req.setContainer(type);
				}			
			}
		}
		
		return super.getCreateCommand(req);
	}
}
