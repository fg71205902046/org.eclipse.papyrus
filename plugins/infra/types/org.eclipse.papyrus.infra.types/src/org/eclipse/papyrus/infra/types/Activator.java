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
package org.eclipse.papyrus.infra.types;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.papyrus.infra.types.validator.ElementTypesConfigurationsValidator;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.eclipse.papyrus.infra.types"; //$NON-NLS-1$

	/**
	 * The log
	 */
	public static LogHelper log;

	/**
	 * The shared instance
	 */
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		log = new LogHelper(this);
		// add a validator for stereotypeConfiguration
		Object previouslValidator = EValidator.Registry.INSTANCE.get(ElementTypesConfigurationsPackage.eINSTANCE);
		if (previouslValidator != null) {
			throw new UnsupportedOperationException(NLS.bind("There is already a validator registered for {0}.", ElementTypesConfigurationsPackage.eINSTANCE));
		}

		EValidator.Registry.INSTANCE.put(ElementTypesConfigurationsPackage.eINSTANCE,
				new EValidator.Descriptor() {
					@Override
					public EValidator getEValidator() {
						return ElementTypesConfigurationsValidator.eINSTANCE;
					}
				});


	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
