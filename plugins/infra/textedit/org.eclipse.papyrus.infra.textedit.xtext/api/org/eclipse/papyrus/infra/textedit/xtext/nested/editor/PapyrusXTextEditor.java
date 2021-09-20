/*****************************************************************************
 * Copyright (c) 2021 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.infra.textedit.xtext.nested.editor;

import java.io.IOException;
import java.util.EventObject;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.sasheditor.editor.ISashWindowsContainer;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors.configuration.ICustomDirectEditorConfiguration;
import org.eclipse.papyrus.infra.textedit.textdocument.TextDocument;
import org.eclipse.papyrus.infra.textedit.textdocument.TextDocumentPackage;
import org.eclipse.papyrus.infra.textedit.xtext.Activator;
import org.eclipse.papyrus.infra.textedit.xtext.internal.listeners.SaveTextOnFocusLostPageLifeCycleEventsListener;
import org.eclipse.papyrus.infra.textedit.xtext.internal.listeners.SaveTextOnFocusLostPartListener;
import org.eclipse.papyrus.infra.ui.lifecycleevents.ISaveAndDirtyService;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.xtext.ui.editor.XtextEditor;

/**
 * This Customization of the {@link XtextEditor} allows us to open an XtextEditor in the Papyrus sash editor
 */
public class PapyrusXTextEditor extends XtextEditor {

	/**
	 * the listener on the command stack
	 */
	private final CommandStackListener listener = new CommandStackListener() {

		@Override
		public void commandStackChanged(EventObject event) {
			resetInput();
		}
	};

	/**
	 * a listener on the semanticContext of the {@link TextDocument}
	 */
	private final Adapter textDocumentListener = new AdapterImpl() {

		@Override
		public void notifyChanged(org.eclipse.emf.common.notify.Notification msg) {
			if (TextDocumentPackage.eINSTANCE.getTextDocument_SemanticContext().equals(msg.getFeature())) {
				resetInput();
			}
		};

	};

	/**
	 * the part listener. This listener is used to be able to save the editor contents on the focus lost
	 */
	private IPartListener2 partListener;

	/** the service registry */
	protected ServicesRegistry registry;

	/** the Papyrus TextDocument */
	protected TextDocument textDocument;

	/** the editing domain */
	protected TransactionalEditingDomain domain;

	/**
	 * the ModelSet
	 */
	protected ModelSet modelSet;

	/**
	 * the Papyrus save and dirty service
	 */
	protected ISaveAndDirtyService saveAndDirtyService;

	/**
	 * the Xtext editor configuration
	 */
	protected ICustomDirectEditorConfiguration editorConfiguration;

	/**
	 * the file extension of the Xtext file for the current grammar
	 */
	private String fileExtension;

	/**
	 * the storage used by this editor
	 */
	private NestedXTextEditorStorage storage = null;

	/**
	 * the editor input used for this editor
	 */
	private NestedXTextEditorInput input = null;

	/**
	 * The Papyrus {@link ISashWindowsContainer}
	 */
	private ISashWindowsContainer sashWindowsContainer = null;

	/**
	 * The listener used on the {@link ISashWindowsContainer}
	 */
	private SaveTextOnFocusLostPageLifeCycleEventsListener sashWindowsContainerListener = null;

	/**
	 *
	 * Constructor.
	 *
	 */
	public PapyrusXTextEditor() {
		super();
	}

	/**
	 *
	 * @return
	 *         the created part listener
	 */
	protected IPartListener2 createPartListener() {
		return new SaveTextOnFocusLostPartListener(this);
	}

	/**
	 *
	 * @param servicesRegistry
	 *            the Papyrus service registry
	 * @param textDocument
	 *            the edited text document
	 * @param editorConfiguration
	 *            the editor configuration
	 * @param fileExtension
	 *            the file extension to use for the current grammar
	 */
	public void configureXTextEditor(final ServicesRegistry servicesRegistry, final TextDocument textDocument, final ICustomDirectEditorConfiguration editorConfiguration, final String fileExtension) {
		this.registry = servicesRegistry;
		this.textDocument = textDocument;
		this.editorConfiguration = editorConfiguration;
		this.fileExtension = fileExtension;
		try {
			this.modelSet = this.registry.getService(ModelSet.class);
		} catch (ServiceException e1) {
			Activator.log.error("We can't find the ModelSet", e1); //$NON-NLS-1$
		}

		try {
			this.saveAndDirtyService = registry.getService(ISaveAndDirtyService.class);
		} catch (ServiceException e) {
			Activator.log.error("We can find the ISaveAndDirtyService", e); //$NON-NLS-1$
		}
		this.partListener = createPartListener();
		this.storage = new NestedXTextEditorStorage(this.editorConfiguration, this.textDocument, this.fileExtension);
		this.input = new NestedXTextEditorInput(this.storage, this.textDocument, this.editorConfiguration);
	}

	/**
	 * @see org.eclipse.xtext.ui.editor.XtextEditor#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 *
	 * @param progressMonitor
	 */
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		// called by ISaveAndDirtyService
		// allow to execute PapyrusXTextDocumentProvider#doSaveDocument
		super.doSave(progressMonitor);
		// so then we need to recall modelSet.save
		try {
			// required to be sure the edition done by the command run by PapyrusXTextDocumentProvider will be also saved
			this.modelSet.save(progressMonitor);
		} catch (IOException e) {
			Activator.log.error("We can't save the modelSet", e); //$NON-NLS-1$
		}
	}


	/**
	 * @see org.eclipse.xtext.ui.editor.XtextEditor#getAdapter(java.lang.Class)
	 *
	 * @param <T>
	 * @param adapter
	 * @return
	 */
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		if (adapter == EObject.class) {
			return adapter.cast(this.textDocument);
		}
		if (adapter == TextDocument.class) {
			return adapter.cast(this.textDocument);
		}
		return super.getAdapter(adapter);
	}


	/**
	 * This method is called to re-set the editor input
	 */
	private void resetInput() {
		int currentCaretOffset = -1;
		if (getSourceViewer() != null
				&& getSourceViewer().getTextWidget() != null
				&& !getSourceViewer().getTextWidget().isDisposed()) {
			currentCaretOffset = getSourceViewer().getTextWidget().getCaretOffset();
		}
		try {
			// TODO maybe better to call setInput directly!
			// setInput(null);
			doSetInput(null);
		} catch (CoreException e) {
			Activator.log.error(e);
		}
		if (currentCaretOffset != -1 && getSourceViewer() != null
				&& getSourceViewer().getTextWidget() != null
				&& !getSourceViewer().getTextWidget().isDisposed()) {
			getSourceViewer().getTextWidget().setCaretOffset(currentCaretOffset);
		}
	}


	/**
	 * This method allows to save the typed text in the edited model
	 */
	public void saveTextInEditedModel() {
		performSave(true, new NullProgressMonitor());
	}

	/**
	 * this method registers the listeners
	 */
	protected void registerListeners() {
		this.domain.getCommandStack().addCommandStackListener(this.listener);
		this.textDocument.eAdapters().add(this.textDocumentListener);
		if (this.saveAndDirtyService != null) {
			this.saveAndDirtyService.registerIsaveablePart(this);
		}
		final IWorkbenchPage page = getEditorSite().getPage();
		page.addPartListener(this.partListener);

	}

	/**
	 * This method unregisters the listeners
	 */
	protected void unregisterListeners() {
		unregisterSashWindowsContainerListener();
		final IWorkbenchPage page = getEditorSite().getPage();
		page.removePartListener(this.partListener);
		this.domain.getCommandStack().removeCommandStackListener(this.listener);
		this.textDocument.eAdapters().remove(this.textDocumentListener);
		if (this.saveAndDirtyService != null) {
			this.saveAndDirtyService.removeIsaveablePart(this);
		}
	}


	/**
	 * @see org.eclipse.xtext.ui.editor.XtextEditor#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 *
	 * @param site
	 * @param input
	 * @throws PartInitException
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		org.eclipse.core.runtime.Assert.isNotNull(this.registry, "The service registry must be set calling the method configureXTextEditor"); //$NON-NLS-1$

		try {
			domain = ServiceUtils.getInstance().getTransactionalEditingDomain(this.registry);
		} catch (ServiceException e) {
			Activator.log.error("We can't find the editing domain", e); //$NON-NLS-1$
		}
		super.init(site, input);
		registerListeners();
	}

	/**
	 * @see org.eclipse.xtext.ui.editor.XtextEditor#dispose()
	 *
	 */
	@Override
	public void dispose() {
		unregisterListeners();

		// the part listener is not notified on editor close
		saveTextInEditedModel();

		super.dispose();
	}

	/**
	 * @see org.eclipse.xtext.ui.editor.XtextEditor#doSetInput(org.eclipse.ui.IEditorInput)
	 *
	 * @param input
	 * @throws CoreException
	 */
	@Override
	protected void doSetInput(IEditorInput input) throws CoreException {
		if (this.textDocument != null
				&& this.textDocument.eResource() != null) {// resource can be null when we are destroying the TextDocument
			super.doSetInput(this.input);
		}
	}


	/**
	 * @see org.eclipse.ui.texteditor.StatusTextEditor#setFocus()
	 *      Set the focus and add a listener on {@link ISashWindowsContainer} on the first call
	 */
	@Override
	public void setFocus() {
		registerSashWindowsContainerListener();
		super.setFocus();
	}

	/**
	 * This method register a listener on the {@link ISashWindowsContainer}, in order to be notified
	 * when the current editor doesn't have the focus anymore, to be able to save its contents
	 * We can't register this listener earlier, because when we reopen a Papyrus model where the XtextEditor is open,
	 * the {@link ISashWindowsContainer} is registered in the {@link ServicesRegistry} after the init!!!
	 */
	protected final void registerSashWindowsContainerListener() {
		if (this.sashWindowsContainer == null) {
			try {
				this.sashWindowsContainer = this.registry.getService(ISashWindowsContainer.class);
				this.sashWindowsContainerListener = new SaveTextOnFocusLostPageLifeCycleEventsListener(this);
				this.sashWindowsContainer.addPageChangedListener(this.sashWindowsContainerListener);
			} catch (ServiceException e) {
				Activator.log.error(e);
			}
		}
	}

	/**
	 * Remove the listener on the {@link ISashWindowsContainer}
	 */
	protected final void unregisterSashWindowsContainerListener() {
		if (this.sashWindowsContainer == null && this.sashWindowsContainerListener != null) {
			this.sashWindowsContainer.removePageChangedListener(this.sashWindowsContainerListener);
		}
		this.sashWindowsContainer = null;
		this.sashWindowsContainerListener = null;
	}

}
