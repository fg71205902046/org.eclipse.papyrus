/*****************************************************************************
 * Copyright (c) 2011, 2016, 2021 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Francois Le Fevre (CEA LIST) francois.le-fevre@cea.fr - Initial API and implementation
 *  Christian W. Damus = bug 485220
 *  Pauline DEVILLE (CEA LIST) pauline.deville@cea.fr - Bug 571948
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.ui;

import java.util.Hashtable;

import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.services.spi.IContextualServiceRegistryTracker;
import org.eclipse.papyrus.infra.tools.spi.IExecutorServiceFactory;
import org.eclipse.papyrus.infra.tools.spi.INotificationBuilderFactory;
import org.eclipse.papyrus.infra.ui.internal.services.status.IStatusService;
import org.eclipse.papyrus.infra.ui.internal.services.status.StatusService;
import org.eclipse.papyrus.infra.ui.util.UIUtil;
import org.eclipse.papyrus.infra.ui.util.WorkbenchPartHelper;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.builders.NotificationBuilder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.eclipse.papyrus.infra.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The plug-in's logger
	 */
	public static LogHelper log;

	private ServiceRegistration<IExecutorServiceFactory> executorFactoryReg;
	private ServiceRegistration<IContextualServiceRegistryTracker> serviceRegistryTrackerReg;

	// Glue the UI notification mechanism to the facade
	private ServiceRegistration<INotificationBuilderFactory> notificationBuilderReg;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		log = new LogHelper(this);

		IExecutorServiceFactory executorFactory = () -> UIUtil.createUIExecutor(Display.getDefault());
		executorFactoryReg = context.registerService(IExecutorServiceFactory.class, executorFactory, null);

		IContextualServiceRegistryTracker serviceRegistryTracker = () -> {
			ServicesRegistry result = null;
			IEditorPart editor = WorkbenchPartHelper.getCurrentActiveEditorPart();
			if (editor != null) {
				result = editor.getAdapter(ServicesRegistry.class);
			}
			return result;
		};
		serviceRegistryTrackerReg = context.registerService(IContextualServiceRegistryTracker.class, serviceRegistryTracker, null);

		notificationBuilderReg = context.registerService(INotificationBuilderFactory.class, NotificationBuilder::new, null);

		// register IStatusService OSGI service
		Hashtable<String, String> props = new Hashtable<>();
		props.put("description", "This service is used to display loading status."); //$NON-NLS-1$ //$NON-NLS-2$
		context.registerService(IStatusService.class, new StatusService(), props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (notificationBuilderReg != null) {
			notificationBuilderReg.unregister();
			notificationBuilderReg = null;
		}
		if (serviceRegistryTrackerReg != null) {
			serviceRegistryTrackerReg.unregister();
			serviceRegistryTrackerReg = null;
		}
		if (executorFactoryReg != null) {
			executorFactoryReg.unregister();
			executorFactoryReg = null;
		}

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
