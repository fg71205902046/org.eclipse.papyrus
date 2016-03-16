/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Mickael ADAM (ALL4TEC) mickael.adam@all4tec.net - Initial API and Implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.uml.diagram.paletteconfiguration.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.papyrus.uml.diagram.paletteconfiguration.IconDescriptor;
import org.eclipse.papyrus.uml.diagram.paletteconfiguration.PaletteconfigurationPackage;

/**
 * Custom item provider for IconDescriptor. Used to notify changed on eContainer.
 *
 */
public class CustomIconDescriptorItemProvider extends IconDescriptorItemProvider {

	/**
	 * the constructor.
	 */
	public CustomIconDescriptorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(IconDescriptor.class)) {
		case PaletteconfigurationPackage.ICON_DESCRIPTOR__PLUGIN_ID:
		case PaletteconfigurationPackage.ICON_DESCRIPTOR__ICON_PATH:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			fireNotifyChanged(new ViewerNotification(notification, ((EObject) notification.getNotifier()).eContainer(), false, true));
			return;
		}
	}

}
