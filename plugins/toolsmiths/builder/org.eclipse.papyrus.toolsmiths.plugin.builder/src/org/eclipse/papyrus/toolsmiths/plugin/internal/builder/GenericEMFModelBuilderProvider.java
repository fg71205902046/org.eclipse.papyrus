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

package org.eclipse.papyrus.toolsmiths.plugin.internal.builder;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.papyrus.toolsmiths.plugin.builder.AbstractPapyrusBuilder;
import org.eclipse.papyrus.toolsmiths.plugin.builder.GenericEMFModelBuilder;
import org.eclipse.papyrus.toolsmiths.plugin.builder.IPapyrusBuilderProvider;
import org.eclipse.papyrus.toolsmiths.plugin.builder.PapyrusBuilderKind;
import org.eclipse.papyrus.toolsmiths.validation.common.spi.ProjectRulesModelProvider;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * Provider of generic builder covering model resources not specifically handled by other providers.
 */
@Component
public class GenericEMFModelBuilderProvider implements IPapyrusBuilderProvider {

	private final CopyOnWriteArrayList<ProjectRulesModelProvider> projectRulesProviders = new CopyOnWriteArrayList<>();

	private AtomicReference<ProjectRulesModelProvider> compositeProjectRulesProvider = new AtomicReference<>();

	private final ProjectRulesModelProvider projectRulesProvider = (package_, resourceSet) -> {
		ProjectRulesModelProvider delegate = compositeProjectRulesProvider.get();
		if (delegate == null) {
			delegate = createProjectRulesModelProvider();
			compositeProjectRulesProvider.compareAndSet(null, delegate);
		}
		return delegate.getProjectDescription(package_, resourceSet);
	};

	@Override
	public String getProblemMarkerType(PapyrusBuilderKind builderKind) {
		return IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER;
	}

	@Override
	public boolean providesBuilder(PapyrusBuilderKind builderKind, URI resourceURI) {
		// The GenericEMFModelBuilder filters model resources for itself
		return false;
	}

	@Override
	public AbstractPapyrusBuilder getBuilder(PapyrusBuilderKind builderKind, IProject project) {
		return builderKind == PapyrusBuilderKind.MODEL_RESOURCE ? new GenericEMFModelBuilder(projectRulesProvider) : null;
	}

	private ProjectRulesModelProvider createProjectRulesModelProvider() {
		return projectRulesProviders.stream().reduce(ProjectRulesModelProvider::or).orElse(ProjectRulesModelProvider.NULL);
	}

	//
	// Service dependencies
	//

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void addProjectRulesModelProvider(ProjectRulesModelProvider provider) {
		if (this.projectRulesProviders.addIfAbsent(provider)) {
			compositeProjectRulesProvider.set(null);
		}
	}

	public void removeProjectRulesModelProvider(ProjectRulesModelProvider provider) {
		if (this.projectRulesProviders.remove(provider)) {
			compositeProjectRulesProvider.set(null);
		}
	}

}
