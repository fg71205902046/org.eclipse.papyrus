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
package org.eclipse.papyrus.core.editor;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.core.extension.editorcontext.AbstractEditorContext;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.core.utils.DiResourceSet;
import org.eclipse.ui.IEditorInput;

/**
 * Context used to load the base models and associated ResourceSet.
 */
public class BackboneContext extends AbstractEditorContext {

	/**
	 * Object managing models lifeCycle.
	 */
	protected DiResourceSet resourceSet;

	/**
	 * Model resolver used to find model element from any graphical artefact.
	 */
	protected BusinessModelResolver modelResolver;

	/**
	 * Constructor.
	 */
	public BackboneContext() {
		modelResolver = BusinessModelResolver.getInstance();
		resourceSet = new DiResourceSet();
	}

	/**
     * 
     */
	public void setInput(IEditorInput input) {
		// TODO change resourceSet URIs ?
	}

	/**
	 * @return the resourceSet
	 */
	public DiResourceSet getResourceSet() {
		return resourceSet;
	}

	/**
	 * 
	 * @param file
	 */
	public void load(IFile file) {
		resourceSet.loadResources(file);
	}

	public void save(IProgressMonitor monitor) throws IOException {
		resourceSet.saveResources(monitor);
	}

	/**
	 * Save resources as specified path
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void saveAs(IPath path) throws IOException {
		resourceSet.saveAs(path);
	}

	/**
	 * @return Object
	 */
	public List<?> getModels() {
		return resourceSet.getVisibleDiagrams();
	}

	/**
     * 
     */
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * @return the modelResolver
	 */
	public BusinessModelResolver getModelResolver() {
		return modelResolver;
	}

	/**
	 * Get the {@link TransactionalEditingDomain}.
	 * 
	 * @return
	 */
	public TransactionalEditingDomain getTransactionalEditingDomain() {
		return resourceSet.getTransactionalEditingDomain();
	}

	/**
	 * Create a new {@link DiagramNotifier} listening to the graphical model containing the diagram roots.
	 * 
	 * @TODO move diagram roots related stuff to appropriate service (to be defined)
	 * @return
	 */
	public DiagramNotifier createDiagramNotifier() {
		return new DiagramNotifier(resourceSet.getDiResource());
	}

}
