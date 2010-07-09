package org.eclipse.papyrus.diagram.communication.edit.parts;

import org.eclipse.draw2d.IClippingStrategy;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.diagram.common.editpolicies.DuplicatePasteEditPolicy;
import org.eclipse.papyrus.diagram.common.editpolicies.OrphanViewPolicy;
import org.eclipse.papyrus.diagram.common.providers.ViewInfo;
import org.eclipse.papyrus.diagram.common.util.MDTUtil;
import org.eclipse.papyrus.diagram.communication.custom.edit.policies.CustomDiagramDragDropEditPolicy;
import org.eclipse.papyrus.diagram.communication.edit.policies.PackageItemSemanticEditPolicy;
import org.eclipse.papyrus.diagram.communication.part.UMLVisualIDRegistry;

/**
 * @generated
 */
public class ModelEditPart extends DiagramEditPart {

	/**
	 * @generated
	 */
	public final static String MODEL_ID = "org.eclipse.papyrus.diagram.communication"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1000;

	/**
	 * @generated
	 */
	public ModelEditPart(View view) {
		super(view);
		getFigure().setClippingStrategy(new IClippingStrategy() {

			public Rectangle[] getClip(IFigure childFigure) {
				// very inefficient, since it implies several tree traversals. Bit handles modifications of the tree structure
				// It's a workaround instead of the better solution to fix BorderedNodeFigure (overload and let it return
				// getExtendedBounds)
				// See bug 313985 (https://bugs.eclipse.org/bugs/show_bug.cgi?id=313985) for more details
				applyClippingStrategy(childFigure);
				if(childFigure instanceof BorderedNodeFigure) {
					return new Rectangle[]{ ((BorderedNodeFigure)childFigure).getExtendedBounds() };

				} else {
					return new Rectangle[]{ childFigure.getBounds() };
				}
			}
		});
	}

	/**
	 * @generated
	 */
	public void applyClippingStrategy(IFigure fig) {
		boolean hasBorderedNodeChild = false;
		for(Object child : fig.getChildren()) {
			if(child instanceof IFigure) {
				applyClippingStrategy((IFigure)child);
				if(child instanceof BorderedNodeFigure) {
					hasBorderedNodeChild = true;
				}
			}
		}
		if(hasBorderedNodeChild && (fig.getClippingStrategy() == null)) {
			fig.setClippingStrategy(new IClippingStrategy() {

				public Rectangle[] getClip(IFigure childFigure) {
					if(childFigure instanceof BorderedNodeFigure) {
						return new Rectangle[]{ ((BorderedNodeFigure)childFigure).getExtendedBounds() };
					} else {
						return new Rectangle[]{ childFigure.getBounds() };
					}
				}
			});
		}

	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(DuplicatePasteEditPolicy.PASTE_ROLE, new DuplicatePasteEditPolicy());

		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new PackageItemSemanticEditPolicy());


		//in Papyrus diagrams are not strongly synchronised
		//installEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CANONICAL_ROLE, new org.eclipse.papyrus.diagram.communication.edit.policies.ModelCanonicalEditPolicy());


		installEditPolicy("RemoveOrphanView", new OrphanViewPolicy()); //$NON-NLS-1$
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new CustomDiagramDragDropEditPolicy());
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}

	/**
	 * @generated
	 */
	protected void handleNotificationEvent(Notification event) {

		super.handleNotificationEvent(event);
		if(event.getNotifier() instanceof EAnnotation) {
			EAnnotation eAnnotation = (EAnnotation)event.getNotifier();
			if(eAnnotation.getSource() != null && eAnnotation.getSource().equals(MDTUtil.FilterViewAndLabelsSource)) {
				//modification form MOSKitt approach, canonical policies are not called
				MDTUtil.filterDiagramViews(this.getDiagramView());
			}
		}
	}

	/**
	 * @generated
	 */
	public Object getAdapter(Class adapter) {

		if(adapter != null && adapter.equals(ViewInfo.class)) {
			return UMLVisualIDRegistry.getDiagramViewInfo();
		}
		return super.getAdapter(adapter);
	}

}
