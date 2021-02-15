/*****************************************************************************
 * Copyright (c) 2020, 2021 CEA LIST, EclipseSource, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Alexandra Buzila (EclipseSource) - Initial API and implementation
 *   Christian W. Damus - bug 570097
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.profile.internal.quickfix;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.papyrus.infra.emf.utils.ResourceUtils;
import org.eclipse.papyrus.toolsmiths.validation.common.quickfix.AbstractMissingExtensionMarkerResolution;
import org.eclipse.papyrus.toolsmiths.validation.profile.constants.ProfilePluginValidationConstants;
import org.eclipse.papyrus.toolsmiths.validation.profile.internal.messages.Messages;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Resolution that adds an entry for the 'org.eclipse.emf.ecore.generated_package' extension and configures it.
 */
public class NoEcoreGenPackageMarkerResolution extends AbstractMissingExtensionMarkerResolution {

	public NoEcoreGenPackageMarkerResolution() {
		super(ProfilePluginValidationConstants.NO_ECORE_GEN_PACKAGE_MARKER_ID);
	}

	@Override
	public String getDescription() {
		return Messages.NoEcoreGenPackageMarkerResolution_description;
	}

	@Override
	public String getLabel() {
		return Messages.NoEcoreGenPackageMarkerResolution_label;
	}

	@Override
	protected String getExtensionPoint(IMarker marker) {
		return ProfilePluginValidationConstants.ECORE_GENERATED_PACKAGE_EXTENSION_POINT;
	}

	@Override
	protected void configureExtension(IPluginExtension extension, IMarker marker) throws CoreException {
		IPluginElement packageElement = createElement(extension, "package"); //$NON-NLS-1$

		IFile genModel = ProfileMarkerResolutionUtils.getGenModelFile(marker);
		if (genModel != null) {
			IPath relativePath = genModel.getProjectRelativePath();
			packageElement.setAttribute("genModel", ResourceUtils.getStringURI(relativePath)); //$NON-NLS-1$
		}
		String uri = ProfileMarkerResolutionUtils.getStereotypeUri(marker);
		if (uri != null) {
			packageElement.setAttribute("uri", uri); //$NON-NLS-1$
		}

		String packageClassName = genModel == null ? null : getPackageInterfaceName(genModel);
		if (packageClassName != null) {
			packageElement.setAttribute("class", packageClassName); //$NON-NLS-1$
		}
	}

	private String getPackageInterfaceName(IFile genModelFile) {
		String result = null;

		ResourceSet rset = new ResourceSetImpl();
		GenModel model = UMLUtil.load(rset, URI.createPlatformResourceURI(genModelFile.getFullPath().toPortableString(), true), GenModelPackage.Literals.GEN_MODEL);
		if (model != null && !model.getGenPackages().isEmpty()) {
			result = model.getGenPackages().get(0).getQualifiedPackageInterfaceName();
		}

		return result;
	}
}
