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

import org.eclipse.papyrus.toolsmiths.plugin.builder.Messages;
import org.eclipse.uml2.uml.Profile;

/**
 * Resolution for markers created for missing profile name attribute in the extension point.
 */
public class PapyrusProfileExtensionMissingNameMarkerResolution extends AbstractMissingAttributeMarkerResolution {

	public PapyrusProfileExtensionMissingNameMarkerResolution() {
		super("name"); //$NON-NLS-1$
	}

	@Override
	public String getDescription() {
		return Messages.PapyrusProfileExtensionMissingNameMarkerResolution_description;
	}

	@Override
	public String getLabel() {
		return Messages.PapyrusProfileExtensionMissingNameMarkerResolution_label;
	}

	@Override
	protected String getAttributeValue() {
		Optional<Profile> profileOptional = MarkerResolutionUtils.getProfile(getMarker());
		if (profileOptional.isEmpty()) {
			return null;
		}
		Profile profile = profileOptional.get();
		return profile.getName();
	}

}
