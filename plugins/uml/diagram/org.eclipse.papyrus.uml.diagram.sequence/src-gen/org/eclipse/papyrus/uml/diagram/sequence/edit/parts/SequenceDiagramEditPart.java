/**
 * Copyright (c) 2016 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
  *  CEA LIST - Initial API and implementation
 */
package org.eclipse.papyrus.uml.diagram.sequence.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusDiagramEditPart;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.DefaultCompartmentSemanticEditPolicy;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.DefaultCreationEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.PasteEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.CustomDiagramDragDropEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.referencialgrilling.GrillingManagementEditPolicy;

/**
 * @generated
 */
public class SequenceDiagramEditPart extends PapyrusDiagramEditPart {

	/**
	 * @generated
	 */
	public final static String MODEL_ID = "PapyrusUMLSequenceDiagram"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final String VISUAL_ID = "Package_SequenceDiagram";

	/**
	 * @generated
	 */
	public SequenceDiagramEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();

		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new DefaultCreationEditPolicy());
		installEditPolicy(PasteEditPolicy.PASTE_ROLE, new PasteEditPolicy());

		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DefaultCompartmentSemanticEditPolicy());
		// in Papyrus diagrams are not strongly synchronised
		// installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CANONICAL_ROLE, new org.eclipse.papyrus.uml.diagram.sequence.edit.policies.PackageCanonicalEditPolicy());

		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new CustomDiagramDragDropEditPolicy());
		installEditPolicy(GrillingManagementEditPolicy.GRILLING_MANAGEMENT, new GrillingManagementEditPolicy());
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}
}
