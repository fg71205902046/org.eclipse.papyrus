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
package org.eclipse.papyrus.infra.siriusdiag.sirius;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.internal.session.SessionFactoryImpl;
import org.eclipse.sirius.business.internal.session.SessionFactoryService;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryService;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetFactory;
import org.eclipse.sirius.tools.internal.resource.InMemoryResourceImpl;
import org.eclipse.sirius.tools.internal.resource.ResourceSetUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.util.DescriptionResourceImpl;

/**
 * Papyrus extension of sirius session factory.
 *
 */
public class PapyrusSessionFactory implements SessionFactory {

	private ServicesRegistry serviceRegistry;

	public static PapyrusSessionFactory INSTANCE = PapyrusSessionFactory.init();

	/**
	 * Default initialization of a {@link SessionFactoryImpl}.
	 *
	 * @return a new instance of {@link SessionFactory}.
	 */
	public static PapyrusSessionFactory init() {
		SessionFactory sessionFactory = SessionFactoryService.INSTANCE.getSessionFactory();
		if (sessionFactory == null || !(sessionFactory instanceof PapyrusSessionFactory)) {
			sessionFactory = new PapyrusSessionFactory();
		}
		return (PapyrusSessionFactory) sessionFactory;
	}

	/**
	 * Create a new empty {@link Session} instance from the sessionModelURI.
	 *
	 * @param sessionResourceURI
	 *            the location URI of the new {@link Session} to create
	 * @return the newly created {@link Session}
	 *
	 * @throws CoreException
	 *             exception when session resource creation failed
	 */
	public final PapyrusSession createSession(URI sessionResourceURI) throws CoreException {
		return createSession(sessionResourceURI, new NullProgressMonitor());
	}

	public PapyrusSession createSession(final URI sessionResourceURI, IProgressMonitor monitor, TransactionalEditingDomain ted) throws CoreException {

		boolean alreadyExistingResource = ted.getResourceSet().getURIConverter().exists(sessionResourceURI, null);
		PapyrusSession session = null;
		if (alreadyExistingResource) {
			session = loadSessionModelResource(sessionResourceURI, ted, monitor);
		} else {
			session = createSessionResource(sessionResourceURI, ted, true, monitor);
		}
		return session;
	}

	@Override
	public PapyrusSession createSession(final URI sessionResourceURI, IProgressMonitor monitor) throws CoreException {
		if (serviceRegistry != null) {
			try {
				TransactionalEditingDomain ted = serviceRegistry.getService(TransactionalEditingDomain.class);
				return createSession(sessionResourceURI, monitor, ted);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public PapyrusSession createDefaultSession(URI sessionResourceURI) throws CoreException {
		return createSession(sessionResourceURI, new NullProgressMonitor());
	}

	/**
	 * Create and prepare the editing domain of the future session.
	 *
	 * It calls {@link ResourceSetFactory} to init the resource set and then the {@link EditingDomainFactoryService} to
	 * instantiate the editing domain.
	 *
	 * Note that {@link SessionFactoryImpl#configureDomain(TransactionalEditingDomain, URI)} allows to configure the
	 * created editing domain.
	 *
	 * @param sessionResourceURI
	 *            the location URI of the future {@link Session}
	 * @return a configured editing domain.
	 */
	protected final TransactionalEditingDomain setEditingDomain(final URI sessionResourceURI, TransactionalEditingDomain ted) {
		ResourceSet set = ted.getResourceSet();

		set.getLoadOptions().put(DescriptionResourceImpl.OPTION_USE_URI_FRAGMENT_AS_ID, true);

		configureDomain(ted, sessionResourceURI);

		return ted;
	}

	/**
	 * Configure the editing domain which will be used to create a PapyrusSession for the given session resource uri.
	 *
	 * @param transactionalEditingDomain
	 *            the editing domain to configure
	 * @param sessionResourceUri
	 *            the location URI of the future {@link Session}
	 */
	protected void configureDomain(TransactionalEditingDomain transactionalEditingDomain, URI sessionResourceUri) {
		ResourceSet set = transactionalEditingDomain.getResourceSet();
		if (set instanceof ResourceSetImpl) {
			ResourceSetImpl resourceSetImpl = (ResourceSetImpl) set;
			new ResourceSetImpl.MappedResourceLocator(resourceSetImpl);
		}
	}

	/**
	 * Load and existing session.
	 *
	 * @param sessionResourceURI
	 *            the location URI of the new {@link Session} to create
	 * @param transactionalEditingDomain
	 *            the editing domain of the new {@link Session} to create
	 * @param monitor
	 *            a {@link IProgressMonitor} to show progression of {@link Session} creation
	 * @return the newly created {@link Session}
	 * @throws CoreException
	 *             if some loading error occurs.
	 */
	protected PapyrusSession loadSessionModelResource(URI sessionResourceURI, final TransactionalEditingDomain transactionalEditingDomain, IProgressMonitor monitor) throws CoreException {
		ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
		// Make ResourceSet aware of resource loading with progress monitor
		ResourceSetUtil.setProgressMonitor(resourceSet, new SubProgressMonitor(monitor, 2));

		PapyrusSession session = null;
		try {
			monitor.beginTask(Messages.SessionFactoryImpl_sessionLoadingMsg, 4);
			// Get resource
			final Resource sessionModelResource = resourceSet.getResource(sessionResourceURI, true);
			checkResource(sessionResourceURI, resourceSet);
			if (sessionModelResource != null) {
				DAnalysis analysis = null;
				if (!sessionModelResource.getContents().isEmpty() && (sessionModelResource.getContents().get(0) instanceof DAnalysis)) {
					analysis = (DAnalysis) sessionModelResource.getContents().get(0);
					PapyrusSession.setTransactionalEditingDomain(transactionalEditingDomain);
					session = createSession(analysis, transactionalEditingDomain);
					monitor.worked(2);
				} else {
					session = createSessionResource(sessionResourceURI, transactionalEditingDomain, false, new SubProgressMonitor(monitor, 2));
				}
			}
		} catch (WrappedException e) {
			throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, Messages.SessionFactoryImpl_loadingError, e));
		} finally {
			monitor.done();
			ResourceSetUtil.resetProgressMonitor(resourceSet);
		}
		return session;
	}

	/**
	 * This method can be overridden to do additional checks during
	 * {@link SessionFactoryImpl#loadSessionModelResource(URI, TransactionalEditingDomain, IProgressMonitor)} just after
	 * the load of the session resource.
	 *
	 * It does nothing per default.
	 *
	 * @param sessionResourceURI
	 *            the current session resource uri.
	 *
	 * @param resourceSet
	 *            the current resource set.
	 */
	protected void checkResource(URI sessionResourceURI, ResourceSet resourceSet) {
		// Nothing to do per default.
	}

	/**
	 * Creates the resource that will store the root DAnalysis of the session to create.
	 *
	 * @param sessionResourceURI
	 *            the URI indicating the location
	 * @param transactionalEditingDomain
	 *            the editing domain to use for creating the resource
	 * @param additionalTasks
	 *            a boolean to configure the calls of
	 *            {@link SessionFactoryImpl#createAdditionalResources(Collection, TransactionalEditingDomain, URI, IProgressMonitor)}
	 *            and {@link SessionFactoryImpl#completeSessionCreation(Session, Collection, IProgressMonitor)}
	 * @param monitor
	 *            a {@link IProgressMonitor} to show progression of session creation
	 * @param forceDefaultSessionKind
	 * @return the created Session
	 * @throws CoreException
	 *             if resource cannot be created
	 */
	protected PapyrusSession createSessionResource(final URI sessionResourceURI, final TransactionalEditingDomain transactionalEditingDomain, boolean additionalTasks, IProgressMonitor monitor)
			throws CoreException {
		PapyrusSession session = null;
		try {
			monitor.beginTask(Messages.SessionFactoryImpl_sessionCreation, 2);
			Resource sessionModelResource = new ResourceSetImpl().createResource(sessionResourceURI);
			ResourceQuery resourceQuery = new ResourceQuery(sessionModelResource);
			if (!(resourceQuery.isRepresentationsResource() || sessionModelResource instanceof InMemoryResourceImpl)) {
				throw new IllegalArgumentException(Messages.SessionFactoryImpl_ResourceTypeErrorMsg);
			}

			Collection<Resource> additionalResources = new ArrayList<>();
			if (additionalTasks) {
				createAdditionalResources(additionalResources, transactionalEditingDomain, sessionResourceURI, monitor);
			}

			DAnalysis analysis = prepareDAnalysis(sessionResourceURI, transactionalEditingDomain, monitor, sessionModelResource, SiriusPlugin.ID);
			session = createSession(analysis, transactionalEditingDomain);

			if (additionalTasks) {
				completeSessionCreation(session, additionalResources, monitor);
			}

			monitor.worked(1);
		} finally {
			monitor.done();
		}
		return session;
	}

	/**
	 *
	 * Create additional resources for future {@link Session}.
	 *
	 * This method is called in {@link SessionFactoryImpl#createSessionResource()} if additionalTask == true before the
	 * {@link DAnalysis} and {@link Session} creation.
	 *
	 * @param additionalResources
	 *            accumulator to reference created resources, it will be later passed a paramater of
	 *            {@link SessionFactoryImpl#completeSessionCreation(Session, Collection, IProgressMonitor)}
	 * @param transactionalEditingDomain
	 *            the editing domain of the future {@link Session}
	 * @param sessionResourceURI
	 *            the location URI of the new {@link Session} to create
	 * @param monitor
	 *            a {@link IProgressMonitor} to show progression of {@link Session} creation
	 */
	protected void createAdditionalResources(Collection<Resource> additionalResources, TransactionalEditingDomain transactionalEditingDomain, URI sessionResourceURI, IProgressMonitor monitor) {
		// Nothing to do per default.
	}

	private DAnalysis prepareDAnalysis(final URI sessionResourceURI, final TransactionalEditingDomain transactionalEditingDomain, final IProgressMonitor monitor, Resource sessionModelResource,
			String pluginID) throws CoreException {

		Resource sessionResource = sessionModelResource;
		DAnalysis analysis = ViewpointFactory.eINSTANCE.createDAnalysis();
		sessionModelResource.getContents().add(analysis);
		try {
			sessionModelResource.save(Collections.emptyMap());
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, pluginID, Messages.SessionFactoryImpl_creationFailedErrorMsg, e));
		}
		monitor.worked(1);
		// Now load it from the TED
		sessionResource = transactionalEditingDomain.getResourceSet().getResource(sessionResourceURI, true);
		if (sessionResource.getContents().isEmpty()) {
			throw new CoreException(new Status(IStatus.ERROR, pluginID, Messages.SessionFactoryImpl_EmptyContentErrorMsg));
		}
		analysis = (DAnalysis) sessionResource.getContents().get(0);
		return analysis;
	}

	/**
	 * Create a {@link Session} for the given {@link DAnalysis}.
	 *
	 * @param analysis
	 *            the main DAnalysis of the {@link Session} to create.
	 * @param transactionalEditingDomain
	 *            the editing domain, might be usefull to help subclasses to create their sessions.
	 * @return a {@link Session}
	 */
	protected PapyrusSession createSession(DAnalysis analysis, TransactionalEditingDomain transactionalEditingDomain) {
		PapyrusSession.setTransactionalEditingDomain(transactionalEditingDomain);
		return new PapyrusSession(analysis, transactionalEditingDomain);
	}

	/**
	 * Complete the session creation.
	 *
	 * This method is called in {@link SessionFactoryImpl#createSessionResource()} if additionalTask == true after the
	 * {@link DAnalysis} and {@link Session} creation.
	 *
	 * @param session
	 *            the newly created {@link Session}
	 * @param additionalResources
	 *            accumulator to referencing additional resource created sooner in
	 *            {@link SessionFactoryImpl#createAdditionalResources(Collection, TransactionalEditingDomain, URI, IProgressMonitor)}
	 * @param monitor
	 *            a {@link IProgressMonitor} to show progression of {@link Session} creation
	 */
	protected void completeSessionCreation(Session session, Collection<Resource> additionalResources, IProgressMonitor monitor) {
		// Nothing to do per default.
	}

	/**
	 * @return the serviceRegistry
	 */
	public ServicesRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	/**
	 * @param serviceRegistry
	 *            the serviceRegistry to set
	 */
	public void setServiceRegistry(ServicesRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
}
