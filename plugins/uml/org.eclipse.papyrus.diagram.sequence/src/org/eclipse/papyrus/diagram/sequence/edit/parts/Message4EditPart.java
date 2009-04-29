/***************************************************************************
 * Copyright (c) 2007 Conselleria de Infraestructuras y Transporte,
 * Generalitat de la Comunitat Valenciana . All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Gabriel Merin Cubero (Prodevelop) – Sequence Diagram implementation
 *
 ******************************************************************************/
package org.eclipse.papyrus.diagram.sequence.edit.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.internal.editpolicies.ConnectionEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.UMLPackage;

import org.eclipse.papyrus.diagram.common.commands.RemoveEObjectReferencesFromDiagram;
import org.eclipse.papyrus.diagram.common.edit.policies.DeleteOnlyViewComponentEditPolicy;
import org.eclipse.papyrus.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.papyrus.diagram.sequence.edit.policies.Message4ItemSemanticEditPolicy;
import org.eclipse.papyrus.diagram.sequence.edit.policies.SequenceDeleteOnlyViewComponentEditPolicy;

/**
 * @generated
 */
public class Message4EditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3004;

	/**
	 * @generated
	 */
	public Message4EditPart(View view) {
		super(view);
	}

	/**
	 * @author gmerin
	 * @generated NOT
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new Message4ItemSemanticEditPolicy());
		// ** install new ComponentEditPolicy
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new SequenceDeleteOnlyViewComponentEditPolicy()); // changed by
		// gmerin
		// ** install new ConnectionEditPolicy
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new ConnectionEditPolicy() {
					@Override
					protected boolean shouldDeleteSemantic() {
						return false;
					}

					@Override
					protected Command createDeleteViewCommand(
							GroupRequest deleteRequest) {
						Command command = super
								.createDeleteViewCommand(deleteRequest);
						command = command
								.chain(new ICommandProxy(
										new RemoveEObjectReferencesFromDiagram(
												getEditingDomain(),
												Message4EditPart.this
														.getDiagramView(),
												Collections
														.singletonList(resolveSemanticElement()))));
						return command;
					}
				});
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof MessageName4EditPart) {
			((MessageName4EditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureMessageSignalLabelFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */

	@Override
	protected Connection createConnectionFigure() {
		return new Signal();
	}

	/**
	 * @generated
	 */
	public Signal getPrimaryShape() {
		return (Signal) getFigure();
	}

	/**
	 * @generated
	 */
	public class Signal extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureMessageSignalLabelFigure;

		/**
		 * @generated
		 */
		public Signal() {
			this.setForegroundColor(ColorConstants.black);

			createContents();
			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureMessageSignalLabelFigure = new WrappingLabel();
			fFigureMessageSignalLabelFigure.setText("");

			fFigureMessageSignalLabelFigure
					.setFont(FFIGUREMESSAGESIGNALLABELFIGURE_FONT);

			this.add(fFigureMessageSignalLabelFigure);

		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			df.setForegroundColor(ColorConstants.black);
			PointList pl = new PointList();
			pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(2));
			pl.addPoint(getMapMode().DPtoLP(0), getMapMode().DPtoLP(0));
			pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(-2));
			df.setTemplate(pl);
			df.setScale(getMapMode().DPtoLP(7), getMapMode().DPtoLP(3));
			return df;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureMessageSignalLabelFigure() {
			return fFigureMessageSignalLabelFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREMESSAGESIGNALLABELFIGURE_FONT = new Font(Display
			.getCurrent(), "SANS", 9, SWT.NORMAL);

	/**
	 * @author jmunoz
	 * @generated NOT
	 */
	protected void handleNotificationEvent(Notification notification) {
		super.handleNotificationEvent(notification);
		List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();

		features.add(UMLPackage.eINSTANCE.getElement_Owner());
		features.add(UMLPackage.eINSTANCE.getElement_OwnedElement());
		features.add(UMLPackage.eINSTANCE.getMessage_MessageSort());
		DiagramEditPartsUtil.handleNotificationForDiagram(this, notification,
				features);
	}

}
