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
 *   Remi Schnekenburger (EclipseSource) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.toolsmiths.validation.profile.internal.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.MarkersService;
import org.eclipse.papyrus.toolsmiths.validation.profile.Activator;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.builders.IncrementalErrorReporter;
import org.eclipse.pde.internal.core.builders.PDEMarkerFactory;

/**
 * Local copy of the {@link IncrementalErrorReporter} from PDE.
 * <p>
 * Overrides marker deletion to delete only some selected markers by id)
 * </p>
 */
@SuppressWarnings("restriction")
public class SelectiveDeleteErrorReporter extends IncrementalErrorReporter {

	public static final String SOURCE_ID = "source_id"; //$NON-NLS-1$
	private final IResource fResource;
	private final Collection<VirtualMarker> fReportedMarkers = new ArrayList<>();
	private int fErrorCount;
	private final String sourceID;

	public SelectiveDeleteErrorReporter(IResource file, String sourceID) {
		super(file);
		fResource = file;
		this.sourceID = sourceID;
	}

	@Override
	public VirtualMarker addMarker(String message, int lineNumber, int severity, int problemID, String category) {

		if (lineNumber == -1) {
			lineNumber = 1;
		}

		if (severity == IMarker.SEVERITY_ERROR) {
			fErrorCount++;
		}

		VirtualMarker marker = new VirtualMarker();
		marker.setAttribute(PDEMarkerFactory.PROBLEM_ID, problemID);
		marker.setAttribute(PDEMarkerFactory.CAT_ID, category);
		marker.setAttribute(IMarker.MESSAGE, message);
		marker.setAttribute(IMarker.SEVERITY, severity);
		marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);

		fReportedMarkers.add(marker);

		return marker;
	}

	@Override
	public void applyMarkers() {
		IMarker[] existingMarkers;
		try {
			// This seem to be for compatibility with some legacy code,
			// PDE builders don't create markers with this type anymore
			fResource.deleteMarkers(IMarker.PROBLEM, false, IResource.DEPTH_ZERO);
			existingMarkers = fResource.findMarkers(PDEMarkerFactory.MARKER_ID, false, IResource.DEPTH_ZERO);
		} catch (CoreException e) {
			PDECore.logException(e);
			// If we can't read existing, let delete them before we create new
			existingMarkers = new IMarker[0];
			try {
				fResource.deleteMarkers(PDEMarkerFactory.MARKER_ID, false, IResource.DEPTH_ZERO);
			} catch (CoreException e1) {
				PDECore.logException(e1);
			}
		}

		// iterate over existing markers to check which are resolved now
		for (IMarker marker : existingMarkers) {
			boolean resolved = true;
			Map<String, Object> existingAttributes = null;

			// Iterate over new markers to filter out all we already know
			for (Iterator<VirtualMarker> it = fReportedMarkers.iterator(); it.hasNext();) {
				VirtualMarker reportedMarker = it.next();
				if (existingAttributes == null) {
					try {
						existingAttributes = marker.getAttributes();
					} catch (Exception e) {
						PDECore.logException(e);
						// assume the marker is not accessible, can be deleted
						break;
					}
				}
				// Same marker is found, no need to create again
				if (reportedMarker.getAttributes().equals(existingAttributes)) {
					resolved = false;
					it.remove();
					break;
				}
			}

			// The marker was not reported again, the old one can be deleted
			// this matchSource was added to allow for multiple passes of the xml parser
			if (resolved && matchSource(marker)) {
				try {
					marker.delete();
				} catch (CoreException e) {
					PDECore.logException(e);
				}
			}
		}

		// Create only new markers
		for (VirtualMarker reportedMarker : fReportedMarkers) {
			try {
				IMarker marker = MarkersService.createMarker(fResource, PDEMarkerFactory.MARKER_ID);
				if (marker != null) {
					marker.setAttributes(reportedMarker.getAttributes());
				}
			} catch (CoreException e) {
				PDECore.logException(e);
			}
		}
	}

	private boolean matchSource(IMarker marker) {
		String markerID = null;
		try {
			markerID = (String) marker.getAttribute(SOURCE_ID);
		} catch (CoreException e) {
			Activator.log.error(e);
		}
		return this.sourceID.equals(markerID);
	}

	@Override
	public int getErrorCount() {
		return fErrorCount;
	}


}
