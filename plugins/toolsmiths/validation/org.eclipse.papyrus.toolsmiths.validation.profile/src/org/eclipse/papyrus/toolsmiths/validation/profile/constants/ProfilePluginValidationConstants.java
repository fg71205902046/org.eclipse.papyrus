/*****************************************************************************
 * Copyright (c) 2019 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Nicolas FAUVERGUE (CEA LIST) nicolas.fauvergue@cea.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.profile.constants;

/**
 * The profile plug-in validation constants.
 */
public class ProfilePluginValidationConstants {

	/**
	 * The type for the validation of profile plugin.
	 */
	public static final String PROFILE_PLUGIN_VALIDATION_TYPE = "org.eclipse.papyrus.toolsmiths.validation.profile.diagnostic"; //$NON-NLS-1$

	/**
	 * The UMLProfile extension point identifier.
	 */
	public static final String UMLPROFILE_EXTENSION_POINT = "org.eclipse.papyrus.uml.extensionpoints.UMLProfile"; //$NON-NLS-1$

	/**
	 * The uml generated package extension point identifier.
	 */
	public static final String UML_GENERATED_PACKAGE_EXTENSION_POINT = "org.eclipse.uml2.uml.generated_package"; //$NON-NLS-1$

	/**
	 * The EMF Ecore generated package extension point identifier.
	 */
	public static final String ECORE_GENERATED_PACKAGE_EXTENSION_POINT = "org.eclipse.emf.ecore.generated_package"; //$NON-NLS-1$

	/**
	 * The EMF Ecore URI mapping extension point identifier.
	 */
	public static final String ECORE_URI_MAPPING_EXTENSION_POINT = "org.eclipse.emf.ecore.uri_mapping"; //$NON-NLS-1$
}
