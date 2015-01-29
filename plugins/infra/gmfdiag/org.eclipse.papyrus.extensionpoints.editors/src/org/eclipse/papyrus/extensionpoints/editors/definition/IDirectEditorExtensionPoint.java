/*****************************************************************************
 * Copyright (c) 2015 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Gabriel Pascual (ALL4TEC) gabriel.pascual@all4tec.net - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.extensionpoints.editors.definition;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.extensionpoints.editors.configuration.IDirectEditorConfiguration;
import org.eclipse.papyrus.extensionpoints.editors.configuration.IDirectEditorConstraint;
import org.eclipse.swt.graphics.Image;

/**
 * Interface to manipulate extension point of Direct Editor.
 * 
 * @author Gabriel Pascual
 *
 */
public interface IDirectEditorExtensionPoint {

	/**
	 * Returns the language edited by this direct editor
	 *
	 * @return the language edited by this direct editor
	 */
	public String getLanguage();

	/**
	 * Returns the type of object to edit
	 *
	 * @return the type of object to edit
	 */
	public String getObjectToEdit();

	/**
	 * Returns the class of object to edit
	 *
	 * @return the class of object to edit
	 */
	public Class<? extends EObject> getObjectClassToEdit();

	/**
	 * Gets the icon.
	 *
	 * @return the icon
	 */
	public Image getIcon();

	/**
	 * Returns the configuration for the dialog window
	 *
	 * @return the configuration for the dialog window
	 */
	public IDirectEditorConfiguration getDirectEditorConfiguration();

	/**
	 * Sets the configuration for the dialog window
	 *
	 * @param directEditorConfiguration
	 *            the configuration for the dialog window
	 */
	// @unused
	public void setDirectEditorConfiguration(IDirectEditorConfiguration directEditorConfiguration);

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public Integer getPriority();


	/**
	 * Sets the priority.
	 *
	 * @param priority
	 *            the new priority
	 */
	public void setPriority(Integer priority);

	/**
	 * Gets the additional constraint.
	 *
	 * @return the additional constraint
	 */
	public IDirectEditorConstraint getAdditionalConstraint();

}
