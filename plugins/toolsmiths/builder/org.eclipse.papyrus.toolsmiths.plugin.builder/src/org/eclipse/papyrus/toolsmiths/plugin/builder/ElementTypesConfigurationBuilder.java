/*****************************************************************************
 * Copyright (c) 2020 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.emf.helpers.BundleResourceURIHelper;

/**
 *
 */
public class ElementTypesConfigurationBuilder extends GenericEMFModelBuilder {

	static final String ELEMENT_TYPES_CONFIGURATION_EXTENSION = "elementtypesconfigurations";//$NON-NLS-1$

	/**
	 * @see org.eclipse.papyrus.toolsmiths.plugin.builder.GenericEMFModelBuilder#validateResource(org.eclipse.emf.ecore.resource.Resource)
	 *
	 * @param resource
	 * @return
	 */
	@Override
	protected Collection<Diagnostic> validateResource(Resource resource) {
		if (resource.getURI().fileExtension().equals(ELEMENT_TYPES_CONFIGURATION_EXTENSION)) {
			final String bundleName = BundleResourceURIHelper.INSTANCE.getBundleNameFromResource(resource);
			final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(bundleName);
			if (project != null && project.exists() && project.isOpen()) {
				// TODO this call will create Diagnotic but we won't convert them into Papyrus build error
				// TODO we should transform this call into a real buidler

				// FIXME we can't call it, because all is done using a shell, then a ProgressMonitorDialog
				// ElementTypesPluginCheckerService.checkElementTypesPlugin(project);
			}
		}
		return super.validateResource(resource);
	}

	/**
	 * @see org.eclipse.papyrus.toolsmiths.plugin.builder.GenericEMFModelBuilder#isIgnoredFileExtension(java.lang.String)
	 *
	 * @param fileExtension
	 * @return
	 */
	@Override
	protected boolean managedFileExtension(String fileExtension) {
		return ELEMENT_TYPES_CONFIGURATION_EXTENSION.equals(fileExtension);
	}

}
