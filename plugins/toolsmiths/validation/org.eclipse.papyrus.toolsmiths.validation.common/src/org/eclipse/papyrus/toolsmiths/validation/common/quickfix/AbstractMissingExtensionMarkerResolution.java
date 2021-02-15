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

package org.eclipse.papyrus.toolsmiths.validation.common.quickfix;

import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.papyrus.toolsmiths.validation.common.Activator;
import org.eclipse.pde.core.IBaseModel;
import org.eclipse.pde.core.plugin.IExtensions;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginParent;
import org.eclipse.pde.internal.ui.util.ModelModification;
import org.eclipse.pde.internal.ui.util.PDEModelUtility;

/**
 * Abstract framework for a marker resolution that adds an extension element to the <tt>plugin.xml</tt>.
 */
@SuppressWarnings("restriction")
public abstract class AbstractMissingExtensionMarkerResolution extends AbstractPapyrusWorkbenchMarkerResolution {

	public AbstractMissingExtensionMarkerResolution(int problemID) {
		super(problemID);
	}

	@Override
	public void run(IMarker marker) {
		if (!(marker.getResource() instanceof IFile)) {
			return;
		}
		ModelModification modification = new ModelModification((IFile) marker.getResource()) {
			@Override
			protected void modifyModel(IBaseModel model, IProgressMonitor monitor) throws CoreException {
				if (model instanceof IPluginModelBase) {
					addExtension((IPluginModelBase) model, marker);
				}
			}
		};
		PDEModelUtility.modifyModel(modification, null);
	}

	private void addExtension(IPluginModelBase model, IMarker marker) {
		try {
			String extensionPoint = getExtensionPoint(marker);
			IExtensions extensions = model.getExtensions(true); // We will need the extensions one way or another
			IPluginExtension extension = Stream.of(extensions.getExtensions())
					.filter(ext -> Objects.equals(extensionPoint, ext.getPoint()))
					.filter(ext -> canAddToExtension(ext, marker))
					.findAny()
					.orElseGet(() -> createNewExtension(extensions, extensionPoint));

			configureExtension(extension, marker);
		} catch (WrappedException e) {
			Activator.log.error(e.exception());
		} catch (CoreException e) {
			Activator.log.error(e);
		}
	}

	private IPluginExtension createNewExtension(IExtensions extensions, String point) {
		IPluginExtension result = extensions.getModel().getFactory().createExtension();

		try {
			result.setPoint(point);
			extensions.add(result);
		} catch (CoreException e) {
			throw new WrappedException(e);
		}

		return result;
	}

	protected boolean canAddToExtension(IPluginExtension existingExtension, IMarker marker) {
		return true;
	}

	protected abstract String getExtensionPoint(IMarker marker);

	protected abstract void configureExtension(IPluginExtension extension, IMarker marker) throws CoreException;

	protected final IPluginElement createElement(IPluginParent parent, String name) {
		IPluginElement result = parent.getModel().getFactory().createElement(parent);

		try {
			result.setName(name);
			parent.add(result);
		} catch (CoreException e) {
			throw new WrappedException(e);
		}

		return result;
	}

	protected final IPluginElement setAttribute(IPluginElement element, String name, String value) {
		try {
			element.setAttribute(name, value);
		} catch (CoreException e) {
			throw new WrappedException(e);
		}

		return element;
	}

}
