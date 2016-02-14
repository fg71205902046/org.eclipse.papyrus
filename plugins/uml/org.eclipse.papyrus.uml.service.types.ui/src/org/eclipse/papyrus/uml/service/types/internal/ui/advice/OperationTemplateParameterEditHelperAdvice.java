/*****************************************************************************
 * Copyright (c) 2014 CEA LIST.
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
package org.eclipse.papyrus.uml.service.types.internal.ui.advice;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.uml.service.types.internal.ui.dialogs.TemplateParameterConfigurationDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.OperationTemplateParameter;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

public class OperationTemplateParameterEditHelperAdvice extends AbstractEditHelperAdvice {

	/**
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getAfterCreateCommand(org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest)
	 *
	 * @param request
	 * @return
	 */
	@Override
	protected ICommand getAfterCreateCommand(CreateElementRequest request) {
		return new CreateElementCommand(request) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				OperationTemplateParameter newElement = UMLFactory.eINSTANCE.createOperationTemplateParameter();
				TemplateSignature owner = (TemplateSignature)getElementToEdit();
				owner.getOwnedParameters().add(newElement);
				TemplateSignature childHolder = (TemplateSignature)getElementToEdit();
				childHolder.getParameters().add(newElement);
				TemplateParameterConfigurationDialog configurationDialog = new TemplateParameterConfigurationDialog(new Shell(), SWT.APPLICATION_MODAL, UMLPackage.eINSTANCE.getOperation());
				configurationDialog.setOwner(childHolder);
				configurationDialog.open();
				newElement.setParameteredElement(configurationDialog.getParameterableElement());
				newElement.setDefault(configurationDialog.getDefaultparameterableElement());
				return CommandResult.newOKCommandResult(newElement);
			}
		};
	}
}