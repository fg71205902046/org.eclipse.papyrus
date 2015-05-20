/*****************************************************************************
 * Copyright (c) 2014 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Gabriel Pascual (ALL4TEC) gabriel.pascual@all4tec.fr - Initial API and implementation
 *   Patrik Nandorf (Ericsson AB) patrik.nandorf@ericsson.com - Bug 425565 
 *   
 *****************************************************************************/

package org.eclipse.papyrus.views.modelexplorer.newchild;

import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.newchild.CreationMenuFactory;
import org.eclipse.papyrus.infra.newchild.elementcreationmenumodel.CreationMenu;
import org.eclipse.papyrus.infra.widgets.util.RevealResultCommand;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.core.ui.pagebookview.MultiViewPageBookView;
import org.eclipse.papyrus.views.modelexplorer.newchild.preferences.NewChildPreferences;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

/**
 * Override of creation menu factory to select created element in Model Explorer view.
 * 
 * @author Gabriel Pascual
 *
 */
public class ModelExplorerMenuFactory extends CreationMenuFactory {

	private boolean defaultSelectionPreference;
	private IViewPart viewPart;


	/**
	 * Default constructor.
	 *
	 * @param editingDomain
	 */
	public ModelExplorerMenuFactory(TransactionalEditingDomain editingDomain) {
		super(editingDomain);
		defaultSelectionPreference = Activator.getDefault().getPreferenceStore().getBoolean(NewChildPreferences.DEFAULT_SELECTION);
		viewPart = getActiveViewPart();
	}

	/**
	 * @see org.eclipse.papyrus.infra.newchild.CreationMenuFactory#buildCommand(org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject, java.lang.String)
	 *
	 * @param reference
	 * @param container
	 * @param creationMenu 
	 * @return
	 */
	@Override
	protected Command buildCommand(EReference reference, EObject container, CreationMenu creationMenu, Map<?, ?> advice) {
		Command buildCommand = super.buildCommand(reference, container, creationMenu, advice);

		if (buildCommand == null || buildCommand == UnexecutableCommand.INSTANCE) {
			return buildCommand;
		}

		if (defaultSelectionPreference) {
			// Wrap command to select created element
			buildCommand = RevealResultCommand.wrap(buildCommand, viewPart, container);
		}

		return buildCommand;
	}


	/**
	 * Gets the active view part.
	 *
	 * @return the active view part
	 */
	private IViewPart getActiveViewPart() {
		IViewPart activeView = null;
		// Get Model Explorer view part
		IViewPart modelExplorerView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ModelExplorerPageBookView.VIEW_ID);

		if (modelExplorerView instanceof MultiViewPageBookView) {
			MultiViewPageBookView pageBook = (MultiViewPageBookView) modelExplorerView;
			activeView = pageBook.getActiveView();
		}

		return activeView;
	}
}
