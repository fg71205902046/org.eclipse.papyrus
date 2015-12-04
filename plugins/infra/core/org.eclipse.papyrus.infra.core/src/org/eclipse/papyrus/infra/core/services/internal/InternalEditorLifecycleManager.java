/*****************************************************************************
 * Copyright (c) 2013, 2015 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *  Christian W. Damus - bug 469188
 *****************************************************************************/
package org.eclipse.papyrus.infra.core.services.internal;

import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.services.IService;


public interface InternalEditorLifecycleManager extends IService {

	/**
	 * Sends the postInit notification for this editor
	 *
	 * @param editor
	 */
	void firePostInit(IMultiDiagramEditor editor);

	/**
	 * Sets the preDisplay notification for this editor
	 * 
	 * @param editor
	 */
	void firePreDisplay(IMultiDiagramEditor editor);

	/**
	 * Sends the postDisplay notification for this editor
	 *
	 * @param editor
	 */
	void firePostDisplay(IMultiDiagramEditor editor);

	/**
	 * Sends the beforeClose notification for this Editor
	 *
	 * @param editor
	 */
	void fireBeforeClose(IMultiDiagramEditor editor);
}
