/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Atos Origin - Initial API and implementation
 * Saadia DHOUIB (CEA LIST) saadia.dhouib@cea.fr - adapted from sequence diagram
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.communication.custom.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.papyrus.diagram.communication.custom.util.CommandHelper;
import org.eclipse.papyrus.diagram.communication.edit.commands.MessageCreateCommand;
import org.eclipse.papyrus.diagram.communication.edit.policies.UMLBaseItemSemanticEditPolicy;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageSort;

/**
 * 
 * Class for creating messages
 * 
 */
public class CustomMessageCreateCommand extends MessageCreateCommand {

	/**
	 * Source of message
	 */
	private final EObject source;

	/**
	 * Target of message
	 */
	private final EObject target;

	/**
	 * container of message
	 */
	private final Interaction container;



	/**
	 * 
	 * Constructor of Message Custom Create Command
	 * 
	 * @param req
	 * @param source
	 * @param target
	 */
	public CustomMessageCreateCommand(CreateRelationshipRequest req, EObject source, EObject target) {
		super(req, source, target);
		this.source = source;
		this.target = target;
		container = deduceContainer(source, target);
	}

	/**
	 * Add a condition on the MOS container
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean canExecute() {
		if(source == null && target == null) {
			return false;
		}
		if(source != null && false == source instanceof Element) {
			return false;
		}
		if(target != null && false == target instanceof Element) {
			return false;
		}
		if(getSource() == null) {
			return true; // link creation is in progress; source is not defined
			// yet
		}
		// target may be null here but it's possible to check constraint
		if(getContainer() == null) {
			return false;
		}

		return UMLBaseItemSemanticEditPolicy.getLinkConstraints().canCreateMessage_8009(getContainer(), getSource(), getTarget());
	}

	/**
	 * Create a MessageOccurenceSpecification and the call event when a message
	 * is created
	 * 
	 * 
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if(!canExecute()) {
			throw new ExecutionException("Invalid arguments in create link command"); //$NON-NLS-1$
		}

		//InteractionFragment container = (InteractionFragment)getRequest().getParameters().get(CommunicationRequestConstant.SOURCE_MODEL_CONTAINER);
		//InteractionFragment targetContainer = (InteractionFragment)getRequest().getParameters().get(CommunicationRequestConstant.TARGET_MODEL_CONTAINER);
		//InteractionFragment targetContainer = (InteractionFragment)getRequest().getParameters().get(CommunicationRequestConstant.SOURCE_MODEL_CONTAINER);

		Message message = CommandHelper.doCreateMessage(container, MessageSort.CREATE_MESSAGE_LITERAL, getSource(), getTarget());
		//Message message = CommunicationCommandHelper.doCreateMessage(container, getSource(), getTarget());
		if(message != null) {
			doConfigure(message, monitor, info);
			((CreateElementRequest)getRequest()).setNewElement(message);
			return CommandResult.newOKCommandResult(message);
		}

		return CommandResult.newErrorCommandResult("There is no valid container for events"); //$NON-NLS-1$

	}


	@Override
	protected void doConfigure(Message newElement, IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		IElementType elementType = ((CreateElementRequest)getRequest()).getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest)getRequest()).getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		configureRequest.setParameter(CreateRelationshipRequest.SOURCE, getSource());
		configureRequest.setParameter(CreateRelationshipRequest.TARGET, getTarget());
		ICommand configureCommand = elementType.getEditCommand(configureRequest);
		if(configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

	/**
	 * Default approach is to traverse ancestors of the source to find instance
	 * of container. Modify with appropriate logic.
	 * 
	 * 
	 */
	private static Interaction deduceContainer(EObject source, EObject target) {
		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for(EObject element = source; element != null; element = element.eContainer()) {
			if(element instanceof Interaction) {
				return (Interaction)element;
			}
		}
		return null;
	}

}
