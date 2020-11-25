/*****************************************************************************
 * Copyright (c) 2020 CEA LIST, EclipseSource and others.
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
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder.quickfix;

import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.infra.emf.utils.ResourceUtils;
import org.eclipse.papyrus.toolsmiths.plugin.builder.Activator;
import org.eclipse.papyrus.toolsmiths.plugin.builder.Messages;
import org.eclipse.uml2.uml.Profile;

/**
 * Resolution for markers created for missing genModel attributes in the extension point.
 *
 */
public class NoLocationUMLGenPackageMarkerResolution
		extends AbstractMissingAttributeMarkerResolution {

	NoLocationUMLGenPackageMarkerResolution() {
		super("location"); //$NON-NLS-1$
	}

	@Override
	public String getLabel() {
		return Messages.NoLocationUMLGenPackageMarkerResolution_label;
	}

	@Override
	public String getDescription() {
		return Messages.NoLocationUMLGenPackageMarkerResolution_description;
	}

	@Override
	protected String getAttributeValue() {
		Optional<Profile> profileOptional = MarkerResolutionUtils.getProfile(getMarker());
		if (profileOptional.isEmpty()) {
			return null;
		}
		Profile profile = profileOptional.get();
		Resource resource = profile.eResource();
		String uriFragment = resource.getURIFragment(profile);

		try {
			IFile umlModelFile = MarkerResolutionUtils.getUMLModelFile(getMarker());
			if (umlModelFile == null) {
				return null;
			}

			return ResourceUtils.mapAndEncodePath(umlModelFile) + "#" + uriFragment; //$NON-NLS-1$
		} catch (CoreException e) {
			Activator.log.error(e);
		}
		return null;
	}
}
