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

package org.eclipse.papyrus.toolsmiths.validation.common.internal.services;

import static org.osgi.service.component.annotations.ReferenceCardinality.MULTIPLE;
import static org.osgi.service.component.annotations.ReferencePolicy.DYNAMIC;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.toolsmiths.validation.common.Activator;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage;
import org.eclipse.papyrus.toolsmiths.validation.common.spi.ProjectRulesModelProvider;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class DefaultProjectRulesModelProvider implements ProjectRulesModelProvider {

	static final String PACKAGE_PROPERTY = "package"; //$NON-NLS-1$

	private final Map<EPackage, URI> projectRules = new HashMap<>();

	@Override
	public ProjectDescription getProjectDescription(EPackage package_, ResourceSet resourceSet) {
		ProjectDescription result = null;
		URI uri = projectRules.get(package_);

		try {
			Resource resource = uri == null ? null : resourceSet.getResource(uri, true);
			if (resource != null) {
				result = (ProjectDescription) EcoreUtil.getObjectByType(resource.getContents(), ProjectRulesPackage.Literals.PROJECT_DESCRIPTION);
			}
		} catch (Exception e) {
			Activator.log.error("Failed to load project description resource " + uri, e); //$NON-NLS-1$
		}

		return result;
	}

	@Reference(cardinality = MULTIPLE, policy = DYNAMIC, target = "(type=org.eclipse.papyrus.toolsmiths.validation.common.projectrules)")
	public void addProjectRules(URI uri, Map<String, ?> properties) {
		Object ePackage = properties.get(PACKAGE_PROPERTY);
		if (ePackage instanceof EPackage) {
			projectRules.put((EPackage) ePackage, uri);
		}
	}

	public void removeProjectRules(URI uri, Map<String, ?> properties) {
		projectRules.remove(properties.get(PACKAGE_PROPERTY), uri);
	}

}
