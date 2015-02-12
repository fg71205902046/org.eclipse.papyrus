/*******************************************************************************
 * Copyright (c) 2013, 2015 Atos, CEA List.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Arthur Daussy <a href="mailto:arthur.daussy@atos.net"> - Initial API and implementation
 *     Gabriel Pascual (ALL4TEC) gabriel.pascual@all4tec.net - Bug 459702
 ******************************************************************************/
package org.eclipse.papyrus.infra.services.controlmode.participants;

import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.sasheditor.DiModel;
import org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest;
import org.eclipse.papyrus.infra.services.controlmode.commands.CleanSashCommand;
import org.eclipse.papyrus.infra.services.controlmode.commands.CreateControlResource;
import org.eclipse.papyrus.infra.services.controlmode.commands.InitializeSashCommand;
import org.eclipse.papyrus.infra.services.controlmode.commands.RemoveControlResourceCommand;


/**
 * Control participant in charge of controling the di resources.
 * It move page ref to control resources
 *
 * @author adaussy
 *
 */
public class DiControlParticipant implements IControlCommandParticipant, IUncontrolCommandParticipant {

	/**
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IControlModeParticipant#getID()
	 *
	 * @return
	 */
	public String getID() {
		return "org.eclipse.papyrus.infra.services.controlmode.participants.SashControlParticipant"; //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IControlModeParticipant#getPriority()
	 *
	 * @return
	 */
	public int getPriority() {
		return 80;
	}

	/**
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IControlCommandParticipant#provideControlCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 *
	 * @param request
	 * @return
	 */
	public boolean provideControlCommand(ControlModeRequest request) {
		EObject objectBeingControl = request.getTargetObject();
		return objectBeingControl != null;
	}

	/**
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IControlCommandParticipant#getPreControlCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 *
	 * @param request
	 * @return
	 */
	public ICommand getPreControlCommand(ControlModeRequest request) {
		return new CreateControlResource(request, DiModel.DI_FILE_EXTENSION);
	}

	/**
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IControlCommandParticipant#getPostControlCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 *
	 * @param request
	 * @return
	 */
	public ICommand getPostControlCommand(ControlModeRequest request) {
		return new InitializeSashCommand(request);
	}

	/**
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IUncontrolCommandParticipant#provideUnControlCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 *
	 * @param request
	 * @return
	 */
	public boolean provideUnControlCommand(ControlModeRequest request) {
		EObject objectBeingControl = request.getTargetObject();
		return objectBeingControl != null;
	}

	/**
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IUncontrolCommandParticipant#getPreUncontrolCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 *
	 * @param request
	 * @return
	 */
	public ICommand getPreUncontrolCommand(ControlModeRequest request) {
		boolean result = setDiTargetRequest(request);
		if (result) {
			return getClearDiCommand(request);
		}
		return UnexecutableCommand.INSTANCE;
	}

	protected ICommand getClearDiCommand(final ControlModeRequest request) {
		ModelSet modelSet = request.getModelSet();
		IFile affectedFiles = WorkspaceSynchronizer.getFile(modelSet.getAssociatedResource(request.getTargetObject(), DiModel.DI_FILE_EXTENSION, true));
		return new CleanSashCommand(Collections.singletonList(affectedFiles), request);
	}

	/**
	 * Sets the Di target request.
	 *
	 * @param request
	 *            the request
	 * @return true, if successful
	 */
	protected boolean setDiTargetRequest(ControlModeRequest request) {
		boolean sucessful = false;

		// Retrieve target Di resource URI
		URI diURI = request.getNewURI().trimFileExtension().appendFileExtension(DiModel.DI_FILE_EXTENSION);
		ModelSet modelSet = request.getModelSet();

		if (modelSet != null) {

			// Try to load target Di resource with Model Set
			Resource diResource = null;
			try {
				diResource = modelSet.getResource(diURI, true);
			} catch (Exception e) {
				diResource = null;
			}

			sucessful = diResource != null;
			if (sucessful) {
				request.setTargetResource(diResource, DiModel.DI_FILE_EXTENSION);
			}
		}

		return sucessful;
	}

	public ICommand getPostUncontrolCommand(ControlModeRequest request) {
		return new RemoveControlResourceCommand(request, DiModel.DI_FILE_EXTENSION);
	}
}
