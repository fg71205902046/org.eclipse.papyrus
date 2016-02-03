/*
 * Copyright (c) 2014 CEA and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Christian W. Damus (CEA) - Initial API and implementation
 *
 */
package org.eclipse.papyrus.infra.core.editor.reload;



/**
 * Convenience superclass for selective implementation of editor reload call-backs.
 */
public class EditorReloadAdapter implements IEditorReloadListener {

	public EditorReloadAdapter() {
		super();
	}

	@Override
	public void editorAboutToReload(EditorReloadEvent event) {
		// Pass
	}

	@Override
	public void editorReloaded(EditorReloadEvent event) {
		// Pass
	}

}