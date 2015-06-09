/*****************************************************************************
 * Copyright (c) 2015 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Celine Janssens (ALL4TEC) celine.janssens@all4tec.net - Initial API and implementation
 *   Celine Janssens (ALL4TEC) celine.janssens@all4tec.net - Bug 455311 : Refactor Stereotype Display
 *   
 *****************************************************************************/

package org.eclipse.papyrus.uml.diagram.common.stereotype.display.helper;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.commands.SetNodeVisibilityCommand;
import org.eclipse.papyrus.infra.gmfdiag.common.databinding.custom.CustomStyleValueCommand;
import org.eclipse.papyrus.uml.diagram.common.stereotype.display.command.SetPersistentViewCommand;
import org.eclipse.papyrus.uml.diagram.common.util.CommandUtil;
import org.eclipse.uml2.uml.Stereotype;

/**
 * This Class regroups the Commands and their execution for the Stereotype Display
 * 
 * @author Céline JANSSENS
 *
 */
public final class StereotypeDisplayCommandExecution {
	/**
	 * singleton instance
	 */
	private static StereotypeDisplayCommandExecution labelHelper;
	private StereotypeDisplayUtil helper = StereotypeDisplayUtil.getInstance();

	/** Singleton contructor */
	private StereotypeDisplayCommandExecution() {
	}

	/**
	 * Returns the singleton instance of this class
	 *
	 * @return the singleton instance.
	 */
	public static StereotypeDisplayCommandExecution getInstance() {
		if (labelHelper == null) {
			labelHelper = new StereotypeDisplayCommandExecution();
		}
		return labelHelper;
	}

	/**
	 * Set the visibility of a view
	 *
	 * @param view
	 *            The view on which the visibility has to be set
	 * @param isVisible
	 *            True to make the Compartment visible
	 */
	public void setVisibility(final TransactionalEditingDomain domain, final View view, final boolean isVisible, final boolean inCommandStack) {


		if (!inCommandStack) {
			class SetVisibilityRunnable implements Runnable {

				private boolean visible;

				/**
				 * Constructor.
				 *
				 */
				public SetVisibilityRunnable(boolean visible) {
					this.visible = visible;
				}

				/**
				 * @see java.lang.Runnable#run()
				 *
				 */
				@Override
				public void run() {
					if (view.isVisible() != visible) {
						view.setVisible(visible);
					}
				}

			}
			CommandUtil.executeUnsafeCommand(new SetVisibilityRunnable(isVisible), domain);
		} else {
			SetNodeVisibilityCommand visibility = new SetNodeVisibilityCommand(domain, view, isVisible);
			CommandUtil.executeCommandInStack(visibility, domain);
		}
	}


	/**
	 * Set the visibility of a view
	 *
	 * @param view
	 *            The view on which the visibility has to be set
	 * @param isVisible
	 *            True to make the Compartment visible
	 */
	public void setPersistency(final TransactionalEditingDomain domain, final View view, boolean inCommandStack) {
		if (!inCommandStack) {

			class SetPersistencyRunnable implements Runnable {

				/**
				 * @see java.lang.Runnable#run()
				 *
				 */
				@Override
				public void run() {
					makeViewPersistant(view);
				}

			}
			CommandUtil.executeUnsafeCommand(new SetPersistencyRunnable(), domain);
		} else {
			SetPersistentViewCommand persitence = new SetPersistentViewCommand(domain, view);
			CommandUtil.executeCommandInStack(persitence, domain);
		}

	}

	/**
	 * @param eContainer
	 */
	public void makeViewPersistant(final View view) {
		if (view != null) {
			if (view.eContainer() != null && view.eContainer() instanceof View) {

				// Make the Parent Persistent
				makeViewPersistant((View) view.eContainer());
				// Move the view from the Transient List to the Persistent Children list
				if (!(view instanceof Edge)) {
					((View) view.eContainer()).getPersistedChildren().add(view);
					((View) view.eContainer()).getTransientChildren().remove(view);
				}
			}
		}
	}

	/**
	 * Set the depth Name of the Stereotype Label.
	 * It uses the NamedStyle to store the depth into a View.
	 * 
	 * @param stereotype
	 *            The Stereotype of the Label that should be modified.
	 * @param nodeView
	 *            The view of the element that needs to be updated (i.e. The Class)
	 * @param depth
	 *            The Depth value as a string (Can be "none", "full" or a negative number )
	 */
	public void setDepth(final TransactionalEditingDomain domain, final Stereotype stereotype, final View nodeView, final String depth, final boolean inCommandStack) {
		final View label = helper.getStereotypeLabel(nodeView, stereotype);

		Command command = new CustomStyleValueCommand(label, depth, NotationPackage.eINSTANCE.getStringValueStyle(), NotationPackage.eINSTANCE.getStringValueStyle_StringValue(), StereotypeDisplayConstant.STEREOTYPE_LABEL_DEPTH);

		if (inCommandStack) {
			CommandUtil.executeCommandInStack(command, domain);
		} else {
			CommandUtil.executeUnsafeCommand(command, domain);
		}
	}

	/**
	 * This Method is called when the user ask explicitly to display a View.
	 * Then the node is first set as Persistence and the command is put in the command Stack
	 * before to set the Visibility as wanted.
	 * 
	 * @param domain
	 *            The Transactional Domain
	 * @param view
	 *            The View to make visible
	 * @param visible
	 *            True if the View has to be visible, false if the Node should be hidden
	 *
	 */
	public void setUserVisibility(final TransactionalEditingDomain domain, final View view, final boolean visible) {
		if (view != null && domain != null) {
			final CompoundCommand compoundCommand = new CompoundCommand("Set Persistency");
			
			final SetPersistentViewCommand persitence = new SetPersistentViewCommand(domain, view);
			compoundCommand.append(persitence);
			final SetNodeVisibilityCommand visibility = new SetNodeVisibilityCommand(domain, view, visible);
			compoundCommand.append(visibility);
			CommandUtil.executeCommandInStack(compoundCommand, domain);
		}
	}
	
	/**
	 * This Method is called when the user ask explicitly to display a View.
	 * The command is put in the command Stack before to set the Visibility as wanted.
	 * 
	 * @param domain
	 *            The Transactional Domain
	 * @param view
	 *            The View to make visible
	 * @param visible
	 *            True if the View has to be visible, false if the Node should be hidden
	 *
	 */
	public void setUserVisibilityWithoutPersistence(final TransactionalEditingDomain domain, final View view, final boolean visible) {
		if (view != null && domain != null) {
			
			final SetNodeVisibilityCommand visibility = new SetNodeVisibilityCommand(domain, view, visible);
			CommandUtil.executeCommandInStack(visibility, domain);
		}
	}

	/**
	 * This Method is called when the user ask explicitly to display a View.
	 * Then the node is first set as Persistence and the command is put in the command Stack
	 * before to set the Visibility as wanted.
	 * 
	 * @param domain
	 *            The Transactional Domain
	 * @param view
	 *            The View to make visible
	 * @param visible
	 *            True if the View has to be visible, false if the Node should be hidden
	 *
	 */
	public void setUserDepth(final TransactionalEditingDomain domain, final Stereotype stereotype, final View view, final String depth) {
		if (view != null && depth != null && !depth.isEmpty()) {
			setPersistency(domain, view, true);
			setDepth(domain, stereotype, view, depth, true);
		}
	}
}
