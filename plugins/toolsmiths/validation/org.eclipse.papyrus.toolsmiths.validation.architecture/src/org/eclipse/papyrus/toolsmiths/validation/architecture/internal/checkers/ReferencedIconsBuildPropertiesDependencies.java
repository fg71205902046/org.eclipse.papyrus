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

package org.eclipse.papyrus.toolsmiths.validation.architecture.internal.checkers;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.papyrus.infra.core.architecture.ADElement;
import org.eclipse.papyrus.infra.core.architecture.RepresentationKind;
import org.eclipse.papyrus.infra.core.architecture.util.ArchitectureSwitch;

/**
 * Computation of referenced icons that need to be packaged in the <tt>build.properties</tt>
 * along with the <em>Architecture Domain</em> model being validated.
 */
class ReferencedIconsBuildPropertiesDependencies {

	private final Resource resource;

	/**
	 * Initialize me with the resource to scan for references to icons.
	 *
	 * @param resource
	 *            the element types model resource to scan
	 */
	ReferencedIconsBuildPropertiesDependencies(Resource resource) {
		super();

		this.resource = resource;
	}

	/**
	 * Scan my resource for referenced icons that are resolvable in the workspace.
	 *
	 * @return the workspace resources that are icons referenced by the architecture model
	 */
	Collection<IResource> getDependencies() {
		Set<IResource> result = new HashSet<>();
		Set<URI> iconURIs = collectIcons(resource.getContents());

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		for (URI next : iconURIs) {
			if (next.isPlatformPlugin() || next.isPlatformResource()) {
				String projectName = next.segment(1);
				IProject project = root.getProject(projectName);
				if (project != null && project.isAccessible()) {
					IResource icon = root.getFile(new Path(next.toPlatformString(false)));
					result.add(icon);
				}
			}
		}

		return result;
	}

	private Set<URI> collectIcons(List<EObject> objects) {
		Set<URI> result = new HashSet<>();

		Switch<Set<URI>> iconsSwitch = new ArchitectureSwitch<>() {
			public Set<URI> caseADElement(ADElement object) {
				collectURI(object.getIcon());
				return null;
			}

			public Set<URI> caseRepresentationKind(RepresentationKind object) {
				collectURI(object.getGrayedIcon());
				return null;
			}

			private void collectURI(String uriString) {
				if (uriString != null && !uriString.isBlank()) {
					try {
						result.add(URI.createURI(uriString));
					} catch (Exception e) {
						// Not a valid URI? Then it can't indicate a file that needs to be packaged
					}
				}
			}

			public Set<URI> defaultCase(EObject object) {
				object.eContents().forEach(this::doSwitch);
				return result;
			}

		};

		objects.forEach(iconsSwitch::doSwitch);

		return result;
	}

}
