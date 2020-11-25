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

import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.papyrus.toolsmiths.plugin.builder.Activator;
import org.eclipse.pde.core.IBaseModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.builders.PDEMarkerFactory;
import org.eclipse.pde.internal.core.builders.XMLErrorReporter;
import org.eclipse.pde.internal.core.text.IDocumentElementNode;
import org.eclipse.pde.internal.ui.util.ModelModification;
import org.eclipse.pde.internal.ui.util.PDEModelUtility;

/**
 * Marker Resolution that adds a missing attribute to the {@link IDocumentElementNode} referenced by the marker.
 */
@SuppressWarnings("restriction")
public abstract class AbstractMissingAttributeMarkerResolution extends AbstractPapyrusMarkerResolution {
	private IMarker marker;
	private String attribute;

	/**
	 * Marker Resolution for the missing attribute with the given name.
	 *
	 * @param attribute
	 *            the name of the missing attribute
	 */
	public AbstractMissingAttributeMarkerResolution(String attribute) {
		this.attribute = attribute;
	}

	protected IMarker getMarker() {
		return marker;
	}

	@Override
	public void run(IMarker marker) {
		this.marker = marker;
		if (!(marker.getResource() instanceof IFile)) {
			return;
		}
		ModelModification modification = new ModelModification((IFile) marker.getResource()) {
			@Override
			protected void modifyModel(IBaseModel model, IProgressMonitor monitor) throws CoreException {
				if (model instanceof IPluginModelBase) {
					addMissingAttribute((IPluginModelBase) model);
				}
			}
		};
		PDEModelUtility.modifyModel(modification, null);
	}

	protected abstract String getAttributeValue();

	protected void addMissingAttribute(IPluginModelBase model) {
		try {
			String locationPath = (String) marker.getAttribute(PDEMarkerFactory.MPK_LOCATION_PATH);
			IDocumentElementNode node = getNodeWithMarker(model, locationPath);

			String value = getAttributeValue();
			if (value != null) {
				node.setXMLAttribute(attribute, value);
			}
		} catch (CoreException e) {
			Activator.log.error(e);
		}
	}

	private IDocumentElementNode getNodeWithMarker(IPluginModelBase model, String locationPath) {
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
				node = (IDocumentElementNode) model.getPluginBase();
			}

			int attr = token.indexOf(XMLErrorReporter.F_ATT_PREFIX);
			if (attr != -1) {
				return node;
			}
		}
		return node;
	}

}
