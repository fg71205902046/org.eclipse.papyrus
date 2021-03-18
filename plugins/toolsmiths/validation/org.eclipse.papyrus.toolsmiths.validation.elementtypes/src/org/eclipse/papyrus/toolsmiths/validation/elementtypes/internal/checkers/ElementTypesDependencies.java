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

package org.eclipse.papyrus.toolsmiths.validation.elementtypes.internal.checkers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.papyrus.emf.helpers.BundleResourceURIHelper;
import org.eclipse.papyrus.infra.types.ElementTypeSetConfiguration;
import org.eclipse.papyrus.infra.types.IconEntry;
import org.eclipse.papyrus.infra.types.util.ElementTypesConfigurationsSwitch;
import org.eclipse.pde.core.plugin.PluginRegistry;

/**
 * Inference of bundle dependencies from the icon resources referenced by
 * an <em>Element Types Configurations Model</em>.
 */
class ElementTypesDependencies {

	private final String hostBundle;

	ElementTypesDependencies(IProject project) {
		super();

		this.hostBundle = PluginRegistry.findModel(project).getBundleDescription().getSymbolicName();
	}

	Set<String> computeDependencies(Resource resource) {
		return new DependenciesSwitch().doSwitch(resource);
	}

	/**
	 * Obtain the URI that an icon entry references.
	 *
	 * @param iconEntry
	 *            an icon entry
	 * @return the referenced icon URI, or {@linkplain Optional#isEmpty() empty} if none
	 *
	 * @throws IllegalArgumentException
	 *             if the icon entry is invalid (does not generate a valid URI)
	 */
	static Optional<URI> getIconURI(IconEntry iconEntry) {
		if (iconEntry.getIconPath() == null) {
			// For whatever reason, the icon path is optional
			return Optional.empty();
		}

		String bundleID = iconEntry.getBundleId();
		if (bundleID == null) {
			bundleID = iconEntry.eResource().getURI().segment(1); // We know a priori that this is a platform:/resource URI
		}

		IPath path = new Path(bundleID).append(new Path(iconEntry.getIconPath()));

		return Optional.of(URI.createPlatformPluginURI(path.toString(), true));
	}

	//
	// Nested types
	//

	final class DependenciesSwitch extends ComposedSwitch<Set<String>> {

		private final Set<String> result = new HashSet<>();

		DependenciesSwitch() {
			super();

			addSwitch(createElementTypesSwitch());
		}

		@Override
		public Set<String> defaultCase(EObject object) {
			object.eContents().forEach(this::doSwitch);
			return result;
		}

		Set<String> doSwitch(Resource resource) {
			resource.getContents().forEach(this::doSwitch);
			return result;
		}

		private Switch<Set<String>> createElementTypesSwitch() {
			return new ElementTypesConfigurationsSwitch<>() {

				public Set<String> caseIconEntry(IconEntry object) {
					String bundleID = object.getBundleId();

					if (bundleID != null && !bundleID.equals(hostBundle)) {
						result.add(bundleID);
					}

					return null;
				}

				public Set<String> caseElementTypeSetConfiguration(ElementTypeSetConfiguration object) {
					Optional.ofNullable(object.getMetamodelNsURI())
							.map(BundleResourceURIHelper.INSTANCE::getBundleNameFromNS_URI)
							.ifPresent(result::add);

					return null;
				}

			};
		}

	}

}
