/*****************************************************************************
 * Copyright (c) 2021 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *   Christian W. Damus - bugs 570097, 572677, 575122
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.common.quickfix;

import java.util.Optional;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.pde.core.IBaseModel;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginParent;
import org.eclipse.pde.internal.core.builders.PDEMarkerFactory;
import org.eclipse.pde.internal.core.builders.XMLErrorReporter;
import org.eclipse.pde.internal.core.text.IDocumentElementNode;
import org.eclipse.pde.internal.ui.util.ModelModification;
import org.eclipse.pde.internal.ui.util.PDEModelUtility;

/**
 * Abstract framework for marker resolutions that edit extension elements in the <tt>plugin.xml</tt>.
 */
@SuppressWarnings("restriction")
public abstract class AbstractEditExtensionMarkerResolution extends AbstractPapyrusWorkbenchMarkerResolution {

	private IPluginModelBase pluginModel;

	private final ThreadLocal<IAdaptable> state = new ThreadLocal<>();

	/**
	 * Initializes me with the problem that I fix.
	 *
	 * @param problemID
	 *            the problem that I fix
	 */
	public AbstractEditExtensionMarkerResolution(int problemID) {
		super(problemID);
	}

	@Override
	public final void run(IMarker marker) {
		if (!(marker.getResource() instanceof IFile)) {
			return;
		}

		ModelModification modification = new ModelModification((IFile) marker.getResource()) {
			@Override
			protected void modifyModel(IBaseModel model, IProgressMonitor monitor) throws CoreException {
				if (model instanceof IPluginModelBase) {
					pluginModel = (IPluginModelBase) model;
					try {
						state.set(initialize(marker));

						IPluginParent element = getElement(marker);
						if (element != null) {
							editExtensionContent(marker, element);
						} else {
							addExtension(marker, pluginModel);
						}
					} finally {
						pluginModel = null;
						IAdaptable dispose = state.get();
						state.remove();
						dispose(dispose);
					}
				}
			}
		};
		PDEModelUtility.modifyModel(modification, null);
	}

	protected void editExtensionContent(IMarker marker, IPluginParent element) throws CoreException {
		if (element instanceof IPluginExtension) {
			editExtension(marker, (IPluginExtension) element);
		} else if (element instanceof IPluginElement) {
			editExtensionElement(marker, (IPluginElement) element);
		} else {
			throw new CoreException(new Status(IStatus.ERROR, getClass(), "Unsupported plug-in element node: " + element)); //$NON-NLS-1$
		}
	}

	protected void editExtension(IMarker marker, IPluginExtension extension) throws CoreException {
		throw new CoreException(new Status(IStatus.ERROR, getClass(), "Plug-in extension not supported")); //$NON-NLS-1$
	}

	protected void editExtensionElement(IMarker marker, IPluginElement element) throws CoreException {
		throw new CoreException(new Status(IStatus.ERROR, getClass(), "Plug-in extension element not supported")); //$NON-NLS-1$
	}

	protected void addExtension(IMarker marker, IPluginModelBase pluginModel) throws CoreException {
		throw new CoreException(new Status(IStatus.ERROR, getClass(), "Adding a new plug-in extension not supported")); //$NON-NLS-1$
	}

	protected IAdaptable initialize(IMarker marker) throws CoreException {
		return null;
	}

	protected final <T> T getState(Class<T> stateType) {
		return Optional.ofNullable(state.get()).map(state -> state.getAdapter(stateType)).orElse(null);
	}

	protected void dispose(IAdaptable state) {
		if (state != null) {
			DisposableState disposable = state.getAdapter(DisposableState.class);
			if (disposable != null) {
				disposable.dispose();
			}
		}
	}

	protected final IPluginParent getElement(IMarker marker) throws CoreException {
		String locationPath = marker.getAttribute(PDEMarkerFactory.MPK_LOCATION_PATH, ""); //$NON-NLS-1$
		IDocumentElementNode node = null;
		StringTokenizer strtok = new StringTokenizer(locationPath, Character.toString(XMLErrorReporter.F_CHILD_SEP));
		while (strtok.hasMoreTokens()) {
			String token = strtok.nextToken();
			if (node != null) {
				IDocumentElementNode[] children = node.getChildNodes();
				int childIndex = Integer.parseInt(token.substring(1, token.indexOf(')')));
				if ((childIndex >= 0) || (childIndex < children.length)) {
					node = children[childIndex];
				}
			} else {
				node = (IDocumentElementNode) pluginModel.getPluginBase();
			}

			int attr = token.indexOf(XMLErrorReporter.F_ATT_PREFIX);
			if (attr != -1) {
				return (IPluginParent) node;
			}
		}
		return (IPluginParent) node;
	}

	protected String getAttributeName(IMarker marker) throws CoreException {
		String locationPath = (String) marker.getAttribute(PDEMarkerFactory.MPK_LOCATION_PATH);
		int index = locationPath.indexOf(XMLErrorReporter.F_ATT_PREFIX);
		return index >= 0 ? locationPath.substring(index + 1) : null;
	}

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

	/**
	 * Obtain a builder to add attributes and nested elements to the given {@code element}
	 * in the <tt>pluginx.xml</tt>.
	 *
	 * @param element
	 *            an element in which to build content
	 * @return an extension builder for the {@code element}
	 */
	protected final ExtensionBuilder build(final IPluginElement element) {
		return new ExtensionBuilder() {
			@Override
			public void setAttribute(String name, String value) {
				AbstractEditExtensionMarkerResolution.this.setAttribute(element, name, value);
			}

			@Override
			public ExtensionBuilder newElement(String name) {
				IPluginElement child = createElement(element, name);
				return build(child);
			}
		};
	}

	/**
	 * Obtain a builder to add elements to the given {@code extension}
	 * in the <tt>pluginx.xml</tt>.
	 *
	 * @param extension
	 *            an extension in which to build content
	 * @return an extension builder for the {@code extension}
	 */
	protected final ExtensionBuilder build(IPluginExtension extension) {
		return new ExtensionBuilder() {
			@Override
			public void setAttribute(String name, String value) {
				throw new UnsupportedOperationException("setAttribute"); //$NON-NLS-1$
			}

			@Override
			public ExtensionBuilder newElement(String name) {
				IPluginElement child = createElement(extension, name);
				return build(child);
			}
		};
	}

	//
	// Nested types
	//

	/**
	 * An optional adapter interface for the transient
	 * {@linkplain AbstractEditExtensionMarkerResolution#initialize(IMarker) resolution state}
	 * that the framework uses to dispose it.
	 */
	@FunctionalInterface
	protected static interface DisposableState {
		/** Disposes of any external resources held by the state. */
		void dispose();
	}

	/**
	 * Interface for construction of new <tt>plugin.xml</tt> content. Implementations typically
	 * delegate back to some PDE API.
	 */
	public static interface ExtensionBuilder {

		void setAttribute(String name, String value);

		ExtensionBuilder newElement(String name);

	}

}
