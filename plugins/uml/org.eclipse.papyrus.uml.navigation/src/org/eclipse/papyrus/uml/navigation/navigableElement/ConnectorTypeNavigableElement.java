/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.navigation.navigableElement;

import org.eclipse.uml2.uml.Connector;

/**
 * Navigate from a Connector to its type
 *
 * @author Camille Letavernier
 *
 */
public class ConnectorTypeNavigableElement extends TypedNavigableElement {

	/**
	 * Constructor.
	 *
	 * @param type
	 */
	public ConnectorTypeNavigableElement(Connector connector) {
		super(connector.getType());
	}

	@Override
	public String getDescription() {
		return "Go to the type declaration of this Connector: " + getTypeLabel();
	}

}