/*****************************************************************************
 * Copyright (c) 2008 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Cedric Dumoulin  Cedric.dumoulin@lifl.fr - Initial API and implementation
 *
  *****************************************************************************/
package org.eclipse.papyrus.core.extension.diagrameditor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.papyrus.core.editor.BackboneException;
import org.eclipse.papyrus.core.extension.ExtensionException;
import org.eclipse.papyrus.core.extension.NotFoundException;
import org.eclipse.papyrus.core.extension.editorcontext.IEditorContext;
import org.eclipse.papyrus.core.extension.editorcontext.IEditorContextRegistry;
import org.eclipse.papyrus.core.utils.IDebugChannel;
import org.eclipse.papyrus.core.utils.PapyrusTrace;
import org.eclipse.papyrus.sasheditor.gef.EditorNotFoundException;
import org.eclipse.papyrus.sasheditor.gef.MultiDiagramException;
import org.eclipse.ui.IEditorPart;

/**
 * A registry recording possible editor descriptors. This class use the eclipse extension mechanism.
 */
public class EditorFactoryRegistry implements IEditorFactoryRegistry {

	/** ID of the editor extension (schema filename) */
	public static final String EDITOR_EXTENSION_ID = "papyrusDiagram";

	/** Namespace where to look for the extension points. */
	protected String extensionPointNamespace;

	/** list of editor descriptors */
	protected List<EditorDescriptor> editorDescriptors;

	/** indicates if extension is loaded or not */
	private boolean isExtensionLoaded = false;

	/**
	 * Create a new Registry reading extension from the specified namespace. The namespace is usually the name of the plugin owning the registry.
	 * 
	 * @param extensionPointNamespace
	 */
	public EditorFactoryRegistry(String extensionPointNamespace) {
		super();
		this.extensionPointNamespace = extensionPointNamespace;
		editorDescriptors = new ArrayList<EditorDescriptor>();
	}

	/**
	 * Create a new editor for the specified diagram root.
	 * 
	 * @throws EditorNotFoundException
	 *             No editor handling the model can be found.
	 */
	public IEditorPart createEditorFor(IEditorContextRegistry contextFactoryRegistry, Object model) throws MultiDiagramException {
		for (EditorDescriptor desc : getEditorDescriptors()) {
			if (desc.isEditorFor(model)) {
				{
					// Get requested context
					IEditorContext context;
					try {
						PapyrusTrace.trace(IDebugChannel.PAPYRUS_CORE, this, "creation of an editor");
						context = contextFactoryRegistry.getContext(desc.getRequestedContextId());
						return desc.createEditorFor(context, model);
					} catch (NotFoundException e) {
						throw new EditorNotFoundException(e);
					} catch (ExtensionException e) {
						throw new EditorNotFoundException(e);
					} catch (BackboneException e) {
						throw new MultiDiagramException(e);
					}
				}
			}
		}
		// no editor found !
		throw new EditorNotFoundException("No editor registered for '" + model + "'.");
	}

	/**
	 * Get the list of editor descriptor.
	 * 
	 * @return the list of editor descriptor.
	 */
	public List<EditorDescriptor> getEditorDescriptors() {
		if (!isExtensionLoaded) {
			isExtensionLoaded = true;
			initializeEditorDescriptors();
		}
		return editorDescriptors;
	}

	/**
	 * Read editor descriptors from extension points.
	 */
	private void initializeEditorDescriptors() {
		// Reading data from plugins
		IConfigurationElement[] configElements = Platform.getExtensionRegistry().getConfigurationElementsFor(extensionPointNamespace, EDITOR_EXTENSION_ID);

		for (IConfigurationElement ele : configElements) {
			EditorDescriptor desc;
			try {
				if (EditorDescriptorExtensionFactory.EDITOR_DIAGRAM_EXTENSIONPOINT.equals(ele.getName())) {
					desc = EditorDescriptorExtensionFactory.eINSTANCE.createNestedEditorDescriptor(ele);
					editorDescriptors.add(desc);
				}
			} catch (ExtensionException e) {
				PapyrusTrace.error(IDebugChannel.PAPYRUS_EXTENSIONPOINT_LOADING, this, "Initialization editor problem " + e);
			}
		}
		PapyrusTrace.trace(IDebugChannel.PAPYRUS_EXTENSIONPOINT_LOADING, this, "" + editorDescriptors.size() + " editorDescriptions loaded");

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "EditorFactoryRegistry: " + editorDescriptors.toString();
	}

}
