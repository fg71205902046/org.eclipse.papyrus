/*****************************************************************************
 * Copyright (c) 2019 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Nicolas FAUVERGUE (CEA LIST) nicolas.fauvergue@cea.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.elementtypes.checkers;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.papyrus.toolsmiths.validation.elementtypes.internal.checkers.ElementTypesPluginChecker;

/**
 * This allows to check an element types plug-in (extensions, builds, dependencies).
 */
public class ElementTypesPluginCheckerService {

	/**
	 * This allows to check the element types plug-in.
	 *
	 * @param project
	 *            The current project to check.
	 * @param monitor
	 *            An {@link IProgressMonitor}, or <code>null</code>
	 */
	public static void checkElementTypesPlugin(final IProject project, IProgressMonitor monitor) {
		ElementTypesPluginChecker.checkElementTypesPlugin(project, monitor == null ? new NullProgressMonitor() : monitor);
	}

}
