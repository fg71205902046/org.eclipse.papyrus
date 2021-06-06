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

package org.eclipse.papyrus.infra.siriusdiag.representation.architecture.commands;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.siriusdiag.representation.SiriusDiagramPrototype;
import org.eclipse.papyrus.infra.siriusdiag.sirius.PapyrusSessionManager;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * Create a DSemanticDiagram Editor view
 */
public class CreateSiriusDiagramEditorViewCommand extends AbstractCreatePapyrusEditorViewCommand<DSemanticDiagram> {

	/**
	 * the {@link SiriusDiagramPrototype} used to create the {@link diagram} model and its editor view
	 */
	private final SiriusDiagramPrototype prototype;
	private DSemanticDiagram diagram;
	// TODO : VL : use me or remove me?
	private String id;

	/**
	 * the main title of the created {@link diagram}
	 */
	// TODO : use me or remove me
	private final String mainTitle;

	/**
	 *
	 * Constructor.
	 *
	 * @param domain
	 * @param diagramTemplatePrototype
	 * @param diagramName
	 * @param diagramMainTitle
	 * @param semanticContext
	 * @param graphicalContext
	 * @param openAfterCreation
	 */
	public CreateSiriusDiagramEditorViewCommand(final TransactionalEditingDomain domain, final SiriusDiagramPrototype diagramTemplatePrototype, final String diagramName, final String diagramMainTitle, final EObject semanticContext,
			final EObject graphicalContext, final boolean openAfterCreation, final String diagramId) {
		super(domain, "Create new Sirius Diagram", diagramName, semanticContext, graphicalContext, openAfterCreation, diagramId); //$NON-NLS-1$
		this.prototype = diagramTemplatePrototype;
		this.mainTitle = diagramMainTitle;
		this.id = diagramId;
	}

	/**
	 *
	 * Constructor.
	 *
	 * @param domain
	 * @param diagramTemplatePrototype
	 * @param diagramName
	 * @param diagramMainTitle
	 * @param semanticContext
	 * @param openAfterCreation
	 */
	public CreateSiriusDiagramEditorViewCommand(final TransactionalEditingDomain domain, final SiriusDiagramPrototype diagramTemplatePrototype, final String diagramName, final String diagramMainTitle, final EObject semanticContext,
			final boolean openAfterCreation, final String diagramId) {
		this(domain, diagramTemplatePrototype, diagramName, diagramMainTitle, semanticContext, null, openAfterCreation, diagramId);
	}

	/**
	 *
	 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
	 *
	 */
	@Override
	protected void doExecute() {
		final SiriusDiagramPrototype proto = this.prototype;
		DiagramDescription description = this.prototype.getDiagramDescription();
		attachToResource(semanticContext, description);
		Session session = PapyrusSessionManager.INSTANCE.getSessions().iterator().next();

		// Get Representation
		EObject model = this.semanticContext;
		String diagramName = this.editorViewName;
		proto.setImplementationID(this.diagramId);
		// TODO : VL : check if the method proto#setSession must be preserved or not...
		proto.setSession(session);
		Collection<RepresentationDescription> descs = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), model);
		for (RepresentationDescription desc : descs) {
			if (DialectManager.INSTANCE.canCreate(model, desc)) {

				TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

				domain.getCommandStack().execute(new RecordingCommand(domain) {

					@Override
					protected void doExecute() {
						// Implement your write operations here,
						// for example: set a new name
						// TODO : VL check we there is another way that the name comparison?!
						if (desc.getName().equals(proto.getDiagramDescription().getName())) {
							diagram = (DSemanticDiagram) DialectManager.INSTANCE.createRepresentation(diagramName, model, desc, session, new NullProgressMonitor());
							session.save(new NullProgressMonitor());
						}
					}
				});
			}
		}
		if (proto != null) {
			if (this.openAfterCreation) {
				openEditor(diagram);
			}
			if (diagram.eResource() != null) {
				// we suppose all is ok
				this.createdEditorView = diagram;
			}
		}
	}

}
