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

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.viewpoint.DAnalysis;

/**
 * Papyrus extension to use session with .
 *
 */
@SuppressWarnings("restriction")
public class PapyrusSession extends DAnalysisSessionImpl {
	// TODO : static field is not very nice, please find another way
	static private TransactionalEditingDomain transactionalEditingDomain;


	/**
	 * Constructor.
	 *
	 * @param mainDAnalysis
	 */
	public PapyrusSession(DAnalysis mainDAnalysis, TransactionalEditingDomain transactionalEditingDomain) {
		super(mainDAnalysis);
		this.transactionalEditingDomain = transactionalEditingDomain;
	}

	@Override
	public TransactionalEditingDomain getTransactionalEditingDomain() {
		return transactionalEditingDomain;
	}

	public static void setTransactionalEditingDomain(TransactionalEditingDomain ted) {
		transactionalEditingDomain = ted;
	}

	@Override
	public void save(Map<?, ?> options, IProgressMonitor monitor) {
		// do nothing on save
	}

}
