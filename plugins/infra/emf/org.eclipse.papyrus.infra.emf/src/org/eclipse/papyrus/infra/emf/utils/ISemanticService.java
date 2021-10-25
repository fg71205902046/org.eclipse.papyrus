/*****************************************************************************
 * Copyright (c) 2021 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.infra.emf.utils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.services.IService;

/**
 * Interface to use to get the semantic object
 */
public interface ISemanticService extends IService {

	/**
	 * The ID of the service. This ID is also used in the plugin.xml to define the service
	 */
	public static final String SERVICE_ID = "org.eclipse.papyrus.infra.siriusdiag.sirius.ISiriusSessionService"; //$NON-NLS-1$

	/**
	 *
	 * @return
	 *         the semantic object associated to a graphical or other element
	 */
	public EObject getSemanticObject(EObject obj);

}
