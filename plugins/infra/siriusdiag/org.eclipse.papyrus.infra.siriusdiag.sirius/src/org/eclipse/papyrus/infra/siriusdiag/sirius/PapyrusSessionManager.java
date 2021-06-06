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

import java.text.MessageFormat;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForResourceSet;
import org.eclipse.sirius.business.api.logger.MarkerRuntimeLogger;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.SessionManagerImpl;
import org.eclipse.sirius.common.tools.api.util.MarkerUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.Messages;

/**
 * Default implementation for a session manager.
 *
 */
@SuppressWarnings("restriction")
public class PapyrusSessionManager extends SessionManagerImpl {

	public static PapyrusSessionManager INSTANCE = PapyrusSessionManager.init();

	/**
	 * Default initialization of a {@link SessionManagerImpl}.
	 *
	 * @return a new instance of {@link SessionManager}.
	 */
	public static PapyrusSessionManager init() {
		return new PapyrusSessionManager();
	}

	public Session getSession(URI sessionModelURI, IProgressMonitor monitor, TransactionalEditingDomain ted) {
		Option<IResource> optionalResource = new URIQuery(sessionModelURI).getCorrespondingResource();
		if (optionalResource.some()) {
			MarkerUtil.removeMarkerFor(optionalResource.get(), MarkerRuntimeLogger.MARKER_TYPE);
		}
		Session session = lookForAlreadyLoadedSession(sessionModelURI);
		if (session == null) {
			try {
				try {
					ServicesRegistry serviceRegistry = ServiceUtilsForResourceSet.getInstance().getServiceRegistry(ted.getResourceSet());
					PapyrusSessionFactory.INSTANCE.setServiceRegistry(serviceRegistry);
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session = PapyrusSessionFactory.INSTANCE.createSession(sessionModelURI, monitor, ted);
			} catch (CoreException e) {
				if (optionalResource.some()) {
					MarkerUtil.addMarkerFor(optionalResource.get(),
							MessageFormat.format(Messages.SessionManagerImpl_representationsFileLoadingSeeErrorLogMsg, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()),
							IMarker.SEVERITY_ERROR, MarkerRuntimeLogger.MARKER_TYPE);
				}
				throw new RuntimeException(e.getLocalizedMessage(), e);
			}
		}
		return session;
	}

	private Session lookForAlreadyLoadedSession(URI sessionModelURI) {
		Session alreadyLoadedSession = null;
		for (Session loadedSession : getSessions()) {
			if (loadedSession.getSessionResource().getURI().equals(sessionModelURI)) {
				alreadyLoadedSession = loadedSession;
			}
		}
		return alreadyLoadedSession;
	}
}
