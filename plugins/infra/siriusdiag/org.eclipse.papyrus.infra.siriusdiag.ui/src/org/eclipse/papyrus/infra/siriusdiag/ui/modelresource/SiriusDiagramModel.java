/******************************************************************************
 * Copyright (c) 2021 CEA LIST, Artal Technologies
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Initial API and implementation
 *****************************************************************************/

package org.eclipse.papyrus.infra.siriusdiag.ui.modelresource;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.infra.architecture.ArchitectureDescriptionUtils;
import org.eclipse.papyrus.infra.core.architecture.RepresentationKind;
import org.eclipse.papyrus.infra.core.architecture.merged.MergedArchitectureViewpoint;
import org.eclipse.papyrus.infra.core.resource.AbstractDynamicModel;
import org.eclipse.papyrus.infra.core.resource.BadArgumentExcetion;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForEObject;
import org.eclipse.papyrus.infra.siriusdiag.representation.SiriusDiagramPrototype;
import org.eclipse.papyrus.infra.siriusdiag.sirius.PapyrusLocalSessionCreationOperation;
import org.eclipse.papyrus.infra.siriusdiag.sirius.PapyrusSessionManager;
import org.eclipse.papyrus.infra.siriusdiag.ui.Activator;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.SessionTransientAttachment;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * This class manages PapyrusDocument in aird model resource.
 *
 *
 */
public class SiriusDiagramModel extends AbstractDynamicModel<DSemanticDiagram> {

	/**
	 * Document Model ID.
	 */
	public static final String SIRIUS_DIAGRAM_MODEL_ID = "org.eclipse.papyrus.infra.siriusdiag.ui.DSemanticDiagram"; //$NON-NLS-1$

	/**
	 * the file extension where document are stored.
	 */
	public static final String SIRIUS_DIAGRAM_MODEL_FILE_EXTENSION = "aird"; // $NON-NLS-1$

	/**
	 *
	 * Constructor.
	 *
	 */
	public SiriusDiagramModel() {
		super();
	}

	/**
	 * @see org.eclipse.papyrus.infra.core.resource.AbstractBaseModel#getModelFileExtension()
	 *
	 * @return
	 */
	@Override
	protected String getModelFileExtension() {
		return SIRIUS_DIAGRAM_MODEL_FILE_EXTENSION;
	}

	/**
	 * @see org.eclipse.papyrus.infra.core.resource.AbstractModel#getIdentifier()
	 *
	 * @return
	 */
	@Override
	public String getIdentifier() {
		return SIRIUS_DIAGRAM_MODEL_ID;
	}

	/**
	 * @see org.eclipse.papyrus.infra.core.resource.AbstractBaseModel#loadModel(org.eclipse.emf.common.util.URI)
	 *
	 * @param uriWithoutExtension
	 */
	@Override
	public void loadModel(URI uriWithoutExtension) {
		// It is a common use case that this resource does not (and will not)
		// exist
		if (exists(uriWithoutExtension)) {
			try {
				super.loadModel(uriWithoutExtension);
			} catch (Exception ex) {
				createModel(uriWithoutExtension);
			}
		}

		if (resource == null) {
			createModel(uriWithoutExtension);
		}
	}

	/**
	 * Add a new initialized document to the aird model.
	 *
	 * @param document
	 * @param context
	 *            we need the context to be able to calculate the resource name were the DSemanticDiagram will be saved.
	 *            because this value is maybe not yet set to {@link DSemanticDiagram#setSemanticContext(EObject)}
	 */
	public void addDiagram(final DiagramDescription document, final EObject context) {
		if (context != null) { // we check the resource for control mode feature

			Resource targetResource;
			Resource contextResource = context.eResource();
			final URI semanticUri = contextResource.getURI();
			if (!contextResource.getURI().trimFileExtension().equals(getResource().getURI().trimFileExtension())) {
				ResourceSet set = contextResource.getResourceSet();
				targetResource = set.getResource(contextResource.getURI(), true);
			} else {
				targetResource = getResource();
			}
			if (targetResource != null) {

				URI uri = contextResource.getURI();
				uri = uri.trimFileExtension();
				uri = uri.appendFileExtension(getModelFileExtension());

				TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(context);
				ResourceSet set = contextResource.getResourceSet();
				// domain.getResourceSet();
				PapyrusLocalSessionCreationOperation operation = new PapyrusLocalSessionCreationOperation(uri, new NullProgressMonitor());
				operation.setTransactionalEditingDomain(domain);
				try {
					operation.execute();
				} catch (CoreException e) {
					e.printStackTrace();
				}
				Session session = operation.getCreatedSession();
				// ResourceSet set = contextResource.getResourceSet();
				// TransactionUtil.getEditingDomain(session.getSessionResource()).dispose();
				// TransactionUtil.disconnectFromEditingDomain(session.getSessionResource());
				// TransactionUtil.disconnectFromEditingDomain(session);
				context.eAdapters().add(new SessionTransientAttachment(session));
				set.getResources().add(session.getSessionResource());

				ArchitectureDescriptionUtils utils = new ArchitectureDescriptionUtils((ModelSet) context.eResource().getResourceSet());

				RecordingCommand command = (new RecordingCommand(domain) {
					@Override
					protected void doExecute() {
						session.addSemanticResource(semanticUri, new NullProgressMonitor());

						for (MergedArchitectureViewpoint current : utils.getArchitectureViewpoints()) {
							for (RepresentationKind rep : current.getRepresentationKinds()) {
								if (rep instanceof SiriusDiagramPrototype) {
									// get the sirius viewpoint or the diagram description representing the class diagram
									DiagramDescription dd = ((SiriusDiagramPrototype) rep).getDiagramDescription();
									Viewpoint vp = (Viewpoint) dd.eContainer();
									// TODO : think about the papyrus architecture switch! The sirius session AND/OR registered sirius viewpoint must also be updated I think.
									final ViewpointSelectionCallback selected = new ViewpointSelectionCallback();
									// for (final Viewpoint previouslySelected : session.getSelectedViewpoints(false)) {
									// selected.deselectViewpoint(previouslySelected, session, new NullProgressMonitor());
									// }
									if (!session.getSelectedViewpoints(false).contains(vp)) {
										selected.selectViewpoint(
												vp,
												session, new NullProgressMonitor());
									}
								}

							}
						}
						PapyrusSessionManager.INSTANCE.add(session);
						SessionUIManager.INSTANCE.getOrCreateUISession(session);// fait dans SessionEditroInput.openSession
						session.save(new NullProgressMonitor());
					}
				});

				domain.getCommandStack().execute(command);
				// command.execute();

			}

		}
	}

	/**
	 *
	 * @see org.eclipse.papyrus.infra.core.resource.IEMFModel#canPersist(org.eclipse.emf.ecore.EObject)
	 *
	 * @param object
	 * @return
	 */
	@Override
	public boolean canPersist(EObject object) {
		return (getResource() != null) && isSupportedRoot(object);
	}

	/**
	 *
	 * @param object
	 * @return
	 */
	protected boolean isSupportedRoot(EObject object) {
		return object instanceof DSemanticDiagram;
	}

	/**
	 *
	 * @see org.eclipse.papyrus.infra.core.resource.IEMFModel#persist(org.eclipse.emf.ecore.EObject)
	 *
	 * @param object
	 */
	@Override
	public void persist(EObject object) {
		if (!canPersist(object)) {
			throw new IllegalArgumentException("cannot persist " + object); //$NON-NLS-1$
		}

		getResource().getContents().add(object);
	}

	/**
	 * @see org.eclipse.papyrus.infra.core.resource.AbstractBaseModel#configureResource(org.eclipse.emf.ecore.resource.Resource)
	 *
	 * @param resource
	 */
	@Override
	protected void configureResource(Resource resource) {
		super.configureResource(resource);
	}

	/**
	 *
	 * @param modelElement
	 *            an element of the edited model
	 * @return
	 *         the editing domain or <code>null</code> if not found
	 */
	protected final TransactionalEditingDomain getEditingDomain(final EObject modelElement) {
		final ServicesRegistry servicesRegistry = getServiceRegistry(modelElement);
		if (null == servicesRegistry) {
			return null;
		}
		try {
			return ServiceUtils.getInstance().getTransactionalEditingDomain(servicesRegistry);
		} catch (ServiceException e) {
			Activator.log.error("EditingDomain not found", e); //$NON-NLS-1$
		}
		return null;
	}

	/**
	 *
	 * @param modelElement
	 *            an element of the edited model
	 * @return
	 *         the service registry or <code>null</code> if not found
	 */
	protected final ServicesRegistry getServiceRegistry(final EObject modelElement) {
		try {
			return ServiceUtilsForEObject.getInstance().getServiceRegistry(modelElement);
		} catch (ServiceException e) {
			Activator.log.error("ServicesRegistry not found", e); //$NON-NLS-1$
		}
		return null;
	}

	/**
	 * TODO VL : seems not used... remove me ?
	 * Get a diagram by its name.
	 *
	 * @param diagramName
	 *            Name of the diagram. This is the name set by the user.
	 * @return
	 * @throws NotFoundException
	 * @throws BadArgumentExcetion
	 */
	public DSemanticDiagram getDiagram(String diagramName) throws NotFoundException, BadArgumentExcetion {

		if (diagramName == null || diagramName.length() == 0) {
			throw new BadArgumentExcetion("Diagram name should not be null and size should be >0."); //$NON-NLS-1$
		}
		for (Resource current : modelSet.getResources()) {
			for (EObject element : current.getContents()) {
				if (element instanceof DSemanticDiagram) {
					DSemanticDiagram diagram = (DSemanticDiagram) element;

					if (diagramName.equals(diagram.getName())) {
						// Found
						return diagram;

					}
				}
			}
		}
		// not found
		throw new NotFoundException(NLS.bind("No Diagram named '{0}' can be found in Model.", diagramName)); //$NON-NLS-1$
	}


}
