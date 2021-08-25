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

package org.eclipse.papyrus.toolsmiths.validation.common.spi;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;

/**
 * Service interface for providers of <em>Project Description</em> models.
 */
public interface ProjectRulesModelProvider {

	/** A provider instance that provides nothing. */
	ProjectRulesModelProvider NULL = (package_, resourceSet) -> null;

	/**
	 * Load the project description model for a given tooling package in the given
	 * resource set.
	 *
	 * @param package_
	 *            a tooling package
	 * @param resourceSet
	 *            a resource set in which to load the project description model
	 *
	 * @return the project description, or {@code null} if there is none
	 */
	ProjectDescription getProjectDescription(EPackage package_, ResourceSet resourceSet);

	/**
	 * Combine me with an{@code other} provider that I delegate to for models that I cannot provide, myself.
	 *
	 * @param other
	 *            another provider to delegate to
	 * @return the combined provider
	 */
	default ProjectRulesModelProvider or(ProjectRulesModelProvider other) {
		return (package_, resourceSet) -> {
			ProjectDescription result = getProjectDescription(package_, resourceSet);
			if (result == null) {
				result = other.getProjectDescription(package_, resourceSet);
			}
			return result;
		};
	}

}
