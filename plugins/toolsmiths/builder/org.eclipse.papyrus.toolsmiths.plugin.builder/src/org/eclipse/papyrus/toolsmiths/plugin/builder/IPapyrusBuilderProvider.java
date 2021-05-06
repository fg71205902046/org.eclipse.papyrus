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

package org.eclipse.papyrus.toolsmiths.plugin.builder;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;

/**
 * A pluggable service that contributes {@link AbstractPapyrusBuilder}s for various
 * kinds of resources to the {@link PapyrusPluginBuilder}.
 *
 * @since 1.1
 */
public interface IPapyrusBuilderProvider {

	/**
	 * Queries the ID of the marker type under which problems that my builders generate should be recorded.
	 *
	 * @param builderKind
	 *            a builder kind
	 *
	 * @return the problem marker type that I generate for that builder kind
	 */
	String getProblemMarkerType(PapyrusBuilderKind builderKind);

	/**
	 * Query whether I provide a builder of the given kind that validates some particular resource.
	 * This is used by the framework to skip certain generic validation for that resource.
	 *
	 * @param builderKind
	 *            a builder kind
	 * @param resourceURI
	 *            a resource URI
	 * @return whether I provide a builder that covers the resource
	 */
	boolean providesBuilder(PapyrusBuilderKind builderKind, URI resourceURI);

	/**
	 * Query whether I provide a builder of the given kind that validates some particular {@code resource}.
	 * This is used by the framework to skip certain generic validation for that resource.
	 *
	 * @param builderKind
	 *            a builder kind
	 * @param resource
	 *            a resource
	 * @return whether I provide a builder that covers the {@code resource}
	 */
	default boolean providesBuilder(PapyrusBuilderKind builderKind, IResource resource) {
		boolean result = false;

		if (resource.getType() == IResource.FILE) {
			URI uri = URI.createPlatformResourceURI(resource.getFullPath().toPortableString(), true);
			result = providesBuilder(builderKind, uri);
		}

		return result;
	}

	/**
	 * Get a Papyrus builder delegate of the given kind for a {@code project}.
	 *
	 * @param builderKind
	 *            the kind of builder to obtain
	 * @param project
	 *            the project for which to obtain it
	 *
	 * @return the builder, or {@code null} if none
	 */
	AbstractPapyrusBuilder getBuilder(PapyrusBuilderKind builderKind, IProject project);

}
