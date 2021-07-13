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
 *   Christian W. Damus - bugs 570097, 573788
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.common.quickfix;

import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CommonProblemConstants;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Helper class with utility methods used by {@linkplain IMarkerResolution marker resolutions}.
 */
public final class CommonMarkerResolutionUtils {

	/** Returns the model name stored in the marker attribute {@link CommonProblemConstants#MODEL_NAME}. */
	public static Optional<String> getModelName(IMarker marker) {
		return Optional.ofNullable(marker.getAttribute(CommonProblemConstants.MODEL_NAME, null));
	}

	/** Returns the model path stored in the marker attribute {@link CommonProblemConstants#MODEL_PATH}. */
	public static Optional<IPath> getModelPath(IMarker marker) {
		return Optional.ofNullable(marker.getAttribute(CommonProblemConstants.MODEL_PATH, null)).map(Path::new);
	}

	/** Get the object of the given {@code type} from an editing {@code domain}, identified by the URI in the {@code marker}. */
	public static <T extends EObject> Optional<T> getModelObject(IMarker marker, Class<T> type, EditingDomain domain) {
		String targetURI = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
		return Optional.ofNullable(targetURI).map(uri -> URI.createURI(uri, true))
				.map(uri -> domain.getResourceSet().getEObject(uri, true))
				.filter(type::isInstance)
				.map(type::cast);
	}

	/** Get the editing domain from an editor that is currently open on the resource of a {@code marker}, if any. */
	public static Optional<EditingDomain> getOpenEditingDomain(IMarker marker) {
		IResource resource = marker.getResource();
		if (!(resource instanceof IFile) || !PlatformUI.isWorkbenchRunning()) {
			return Optional.empty();
		}

		IEditorInput editorInput = new FileEditorInput((IFile) resource);

		IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
		return Stream.of(windows).map(IWorkbenchWindow::getActivePage)
				.flatMap(page -> Stream.of(page.findEditors(editorInput, null, IWorkbenchPage.MATCH_INPUT)))
				.map(CommonMarkerResolutionUtils::getEditingDomain)
				.findAny();
	}

	private static EditingDomain getEditingDomain(IEditorReference editorRef) {
		EditingDomain result = null;

		IEditorPart editor = editorRef.getEditor(true);
		if (editor instanceof IEditingDomainProvider) {
			result = ((IEditingDomainProvider) editor).getEditingDomain();
		}
		if (result == null && editor != null) {
			result = editor.getAdapter(EditingDomain.class);
		}

		return result;
	}

}
