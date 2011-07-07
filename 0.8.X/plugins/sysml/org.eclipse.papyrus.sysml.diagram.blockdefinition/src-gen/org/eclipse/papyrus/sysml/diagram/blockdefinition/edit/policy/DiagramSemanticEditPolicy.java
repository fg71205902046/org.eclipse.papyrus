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
package org.eclipse.papyrus.sysml.diagram.blockdefinition.edit.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.papyrus.gmf.diagram.common.edit.policy.DefaultSemanticEditPolicy;

public class DiagramSemanticEditPolicy extends DefaultSemanticEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getReorientRelationshipSourceCommand(ReconnectRequest request) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getReorientRelationshipTargetCommand(ReconnectRequest request) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getReorientRefRelationshipSourceCommand(ReconnectRequest request) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getReorientRefRelationshipTargetCommand(ReconnectRequest request) {
		return UnexecutableCommand.INSTANCE;
	}
}
