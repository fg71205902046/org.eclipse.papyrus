/*****************************************************************************
 * Copyright (c) 2021 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.common.projectrules.util;

import org.eclipse.emf.common.notify.Notifier;

/**
 * Specialization of the UML2 <em>Cache Adapter</em> for this package.
 */
public class ProjectRulesCache extends org.eclipse.uml2.common.util.CacheAdapter {

	private static final ProjectRulesCache INSTANCE = new ProjectRulesCache();

	/**
	 * Get the cache adapter attached to the {@code notifier}, attaching one if necessary.
	 *
	 * @param notifier
	 *            a notifier
	 * @return its attached cache adapter
	 */
	public static ProjectRulesCache getCacheAdapter(Notifier notifier) {
		ProjectRulesCache result = getInstance();
		result.addAdapter(notifier);
		return result;
	}

	/**
	 * Get the shared cache adapter.
	 *
	 * @return the shared cache adapter
	 */
	public static ProjectRulesCache getInstance() {
		return INSTANCE;
	}

}
