/**
 * Copyright (c) 2018 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 */
package org.eclipse.papyrus.uml.diagram.activity.edit.parts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.parsers.ParserUtil;
import org.eclipse.papyrus.infra.gmfdiag.tooling.runtime.edit.policies.DefaultNodeLabelDragPolicy;
import org.eclipse.papyrus.uml.diagram.activity.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.common.editparts.AbstractNodeLabelEditPart;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.UMLTextSelectionEditPolicy;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class IntervalConstraintAsLocalPostcondBodyEditPart extends AbstractNodeLabelEditPart {

	/**
	 * @generated
	 */
	public static final String VISUAL_ID = "IntervalConstraint_LocalPostconditionBodyLabel"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public IntervalConstraintAsLocalPostcondBodyEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new UMLTextSelectionEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new DefaultNodeLabelDragPolicy());
	}

	/**
	 * @generated
	 */
	@Override
	protected Image getLabelIcon() {
		// not use element icon
		return null;
	}

	/**
	 * @generated
	 */
	@Override
	public IParser getParser() {
		if (parser == null) {
			parser = ParserUtil.getParser(UMLElementTypes.IntervalConstraint_LocalPostconditionShape, getParserElement(), this, VISUAL_ID);
		}
		return parser;
	}
}
