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
 *   Christian W. Damus - bugs 570097, 572677, 575122
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.common.quickfix;

import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.papyrus.toolsmiths.validation.common.Activator;
import org.eclipse.pde.core.plugin.IExtensions;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;

/**
 * Abstract framework for a marker resolution that adds an element to an extension in the <tt>plugin.xml</tt>.
 */
public abstract class AbstractMissingElementMarkerResolution extends AbstractEditExtensionMarkerResolution {

	public AbstractMissingElementMarkerResolution(int problemID) {
		super(problemID);
	}

	@Override
	protected void addExtension(IMarker marker, IPluginModelBase model) throws CoreException {
		try {
			String extensionPoint = getExtensionPoint(marker);
			IExtensions extensions = model.getExtensions(true); // We will need the extensions one way or another
			IPluginExtension extension = Stream.of(extensions.getExtensions())
					.filter(ext -> Objects.equals(extensionPoint, ext.getPoint()))
					.filter(ext -> canAddToExtension(ext, marker))
					.findAny()
					.orElseGet(() -> createNewExtension(extensions, extensionPoint));

			editExtension(marker, extension);

			// If we created a new extension, add it to the model now. If we had added it immediately
			// upon creation, the plugin.xml text would have accumulated all the partial states of
			// its configuration (bug 572677)
			if (extension.getParent() == null) {
				extensions.add(extension);
			}
		} catch (WrappedException e) {
			if (e.exception() instanceof CoreException) {
				throw (CoreException) e.exception();
			}
			IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID,
					"Uncaught exception in marker resolution.", e.exception()); //$NON-NLS-1$
			throw new CoreException(status);
		}
	}

	private IPluginExtension createNewExtension(IExtensions extensions, String point) {
		IPluginExtension result = extensions.getModel().getFactory().createExtension();

		try {
			result.setPoint(point);
		} catch (CoreException e) {
			throw new WrappedException(e);
		}

		return result;
	}

	protected boolean canAddToExtension(IPluginExtension existingExtension, IMarker marker) {
		return true;
	}

	protected abstract String getExtensionPoint(IMarker marker) throws CoreException;

	@Override
	protected void editExtension(IMarker marker, IPluginExtension extension) throws CoreException {
		// For backwards compatibility
		configureExtension(extension, marker);
	}

	protected abstract void configureExtension(IPluginExtension extension, IMarker marker) throws CoreException;

}
