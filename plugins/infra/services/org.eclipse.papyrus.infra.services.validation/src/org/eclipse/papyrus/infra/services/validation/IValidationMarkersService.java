/*****************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   Christian W. Damus - bug 485220
 *   
 *****************************************************************************/

package org.eclipse.papyrus.infra.services.validation;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.services.markerlistener.IPapyrusMarker;


/**
 * @author damus
 *
 */
public interface IValidationMarkersService {

	Collection<IPapyrusMarker> getMarkers();

	Collection<IPapyrusMarker> getMarkers(EObject object);

	ModelSet getModelSet();

	void addValidationMarkerListener(IValidationMarkerListener listener);

	void removeValidationMarkerListener(
			IValidationMarkerListener listener);

}