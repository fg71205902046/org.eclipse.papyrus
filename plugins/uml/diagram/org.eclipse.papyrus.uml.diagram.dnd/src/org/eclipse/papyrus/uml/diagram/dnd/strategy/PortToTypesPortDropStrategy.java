/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Benoit Maggi (CEA LIST) benoit.maggi@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.dnd.strategy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.TransactionalDropStrategy;
import org.eclipse.papyrus.uml.diagram.composite.custom.edit.parts.ResizablePortEditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Type;

/**
 * A DropStrategy to move a Port in a Port
 */
public class PortToTypesPortDropStrategy extends TransactionalDropStrategy {

	@Override
	public String getLabel() {
		return "Parameter drag and drop inside Parameter Property";
	}

	@Override
	public String getID() {
		return "org.eclipse.papyrus.sysml14.diagram.parametric.dnd.ParameterDropStrategy"; //$NON-NLS-1$
	}

	@Override
	public String getDescription() {
		return "This strategy is is a specialization in order to be able to drop a Port inside Port.";
	}


	@Override
	public Command doGetCommand(Request request, final EditPart targetEditPart) {
		CompositeCommand cc = new CompositeCommand(getLabel());
		if (targetEditPart instanceof ResizablePortEditPart) {
			ResizablePortEditPart graphicalEditPart = (ResizablePortEditPart) targetEditPart;
			List<EObject> sourceElements = getSourceEObjects(request);
			if (sourceElements.size() == 0) {
				return null;
			}
			final List<EObject> valuesToAdd = new ArrayList<EObject>(sourceElements.size());
			Element target = (Element) graphicalEditPart.resolveSemanticElement();
			if (target instanceof Port && ((Port) target).getType() != null) {
				Port targetPort = (Port) target;
				Type targetType = targetPort.getType();
				for (EObject sourceElement : sourceElements) {
					if ((sourceElement instanceof Port) && sourceElement.eContainer().equals(targetType)) {
						addCommandDrop(graphicalEditPart, cc, valuesToAdd, sourceElement);
					}
				}
			}
		}
		return cc.canExecute() ? new ICommandProxy(cc.reduce()) : null;
	}

	/**
	 * @see org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.DropStrategy#getImage()
	 *
	 * @return
	 */
	@Override
	public Image getImage() {
		return null;
	}

	/**
	 * @see org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.DropStrategy#getPriority()
	 *
	 * @return
	 * @deprecated
	 */
	@Override
	public int getPriority() {
		return 0;
	}

	
	protected Command addCommandDrop(final ResizablePortEditPart targetEditPart, CompositeCommand cc, final List<EObject> valuesToAdd, EObject sourceElement) {

		valuesToAdd.add(sourceElement);
		Command cmd = new Command() {
			@Override
			public void execute() {
					ViewService.createNode(targetEditPart.getNotationView(), valuesToAdd.get(0), "Port_Shape", targetEditPart.getDiagramPreferencesHint());
			}

		};
		cc.add(new CommandProxy(cmd));
		return cmd;
	}
}
