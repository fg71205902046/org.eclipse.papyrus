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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.Messages;

/**
 * A common operation to create a session and open it.
 */
public class PapyrusLocalSessionCreationOperation extends DefaultLocalSessionCreationOperation {

	private IProgressMonitor monitor;

	private TransactionalEditingDomain transactionalEditingDomain;

	/**
	 * Constructor.
	 *
	 * @param sessionResourceURI
	 *            the {@link URI} of the Resource {@link Session} model
	 * @param monitor
	 *            {@link IProgressMonitor} to show progression of
	 *            {@link Session} creation
	 */
	public PapyrusLocalSessionCreationOperation(URI sessionResourceURI, IProgressMonitor monitor) {
		super(sessionResourceURI, monitor);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.sirius.business.api.session.SessionCreationOperation#execute()
	 */
	@Override
	public void execute() throws CoreException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		try {
			monitor.beginTask(Messages.DefaultLocalSessionCreationOperation_createResoureMsg, 3);
			monitor.subTask(Messages.DefaultLocalSessionCreationOperation_createSessionMsg);
			// TODO VL : replace SubProgressMonitor which is deprecated by monitor.
			session = PapyrusSessionFactory.INSTANCE.createSession(sessionResourceURI, new SubProgressMonitor(monitor, 1), transactionalEditingDomain);
			monitor.subTask(Messages.DefaultLocalSessionCreationOperation_sessionOpenMsg);
			session.open(new SubProgressMonitor(monitor, 1));// TODO remove call to this deprecated class
			monitor.subTask(Messages.DAnalysisSessionImpl_saveMsg);
			session.save(new SubProgressMonitor(monitor, 1));
		} finally {
			monitor.done();
		}
	}

	public void setTransactionalEditingDomain(TransactionalEditingDomain transactionalEditingDomain) {
		// TODO VL : can we fill this field directly in the constructor
		this.transactionalEditingDomain = transactionalEditingDomain;
	}

}
