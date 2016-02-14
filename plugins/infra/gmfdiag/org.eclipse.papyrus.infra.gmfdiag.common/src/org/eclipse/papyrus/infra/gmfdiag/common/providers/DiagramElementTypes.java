/*****************************************************************************
 * Copyright (c) 2015 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.infra.gmfdiag.common.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;

/**
 * @author melaasar
 *
 */
public abstract class DiagramElementTypes {

	private DiagramElementTypeImages myImages;

	public DiagramElementTypes(AdapterFactory adapterFactory) {
		this(new DiagramElementTypeImages(adapterFactory));
	}

	public DiagramElementTypes(DiagramElementTypeImages images) {
		myImages = images;
	}

	public abstract IElementType getElementTypeForVisualId(String visualID);

	public abstract boolean isKnownElementType(IElementType elementType);

	public abstract ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter);

	public DiagramElementTypeImages getElementTypeImages() {
		return myImages;
	}

}