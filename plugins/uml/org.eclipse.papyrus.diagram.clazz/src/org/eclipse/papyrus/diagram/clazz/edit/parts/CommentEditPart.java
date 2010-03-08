/*****************************************************************************
 * Copyright (c) 2009 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.clazz.edit.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.papyrus.diagram.clazz.custom.policies.CustomGraphicalNodeEditPolicy;
import org.eclipse.papyrus.diagram.clazz.edit.policies.CommentItemSemanticEditPolicy;
import org.eclipse.papyrus.diagram.clazz.edit.policies.OpenDiagramEditPolicy;
import org.eclipse.papyrus.diagram.clazz.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.diagram.clazz.providers.UMLElementTypes;
import org.eclipse.papyrus.diagram.common.editpolicies.AppliedStereotypeLabelDisplayEditPolicy;
import org.eclipse.papyrus.diagram.common.editpolicies.AppliedStereotypeNodeLabelDisplayEditPolicy;
import org.eclipse.papyrus.diagram.common.figure.node.CornerBentFigure;
import org.eclipse.papyrus.diagram.common.figure.node.HTMLCornerBentFigure;
import org.eclipse.papyrus.preferences.utils.GradientPreferenceConverter;
import org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * @generated
 */
public class CommentEditPart extends

ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2012;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public CommentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new CommentItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		installEditPolicy(EditPolicyRoles.OPEN_ROLE, new OpenDiagramEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new CustomGraphicalNodeEditPolicy());
		installEditPolicy(AppliedStereotypeLabelDisplayEditPolicy.STEREOTYPE_LABEL_POLICY, new AppliedStereotypeNodeLabelDisplayEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if(result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new HTMLCornerBentFigure();
	}

	/**
	 * @generated
	 */
	public HTMLCornerBentFigure getPrimaryShape() {
		return (HTMLCornerBentFigure)primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if(childEditPart instanceof CommentBodyEditPart) {
			((CommentBodyEditPart)childEditPart).setLabel(getPrimaryShape().getCornerBentFigure());
			return true;
		}

		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if(childEditPart instanceof CommentBodyEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if(addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if(removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so you may safely remove
	 * <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane. Respects layout one may have set
	 * for generated figure.
	 * 
	 * @param nodeShape
	 *        instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if(nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if(contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if(primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if(primaryShape instanceof Shape) {
			((Shape)primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if(primaryShape instanceof Shape) {
			((Shape)primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(UMLVisualIDRegistry.getType(CommentBodyEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */getMARelTypesOnSource() {
		List/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */types = new ArrayList/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */();
		types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		return types;
	}

	/**
	 * @generated
	 */
	public List/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */getMARelTypesOnSourceAndTarget(IGraphicalEditPart targetEditPart) {
		List/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */types = new ArrayList/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */();
		if(targetEditPart instanceof Dependency2EditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof AssociationClassEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof AssociationNodeEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof ComponentEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof SignalEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof InterfaceEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof ModelEditPartTN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof EnumerationEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof PackageEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof ClassEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof DataTypeEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof ConstraintEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof org.eclipse.papyrus.diagram.clazz.edit.parts.CommentEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof DurationObservationEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof TimeObservationEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof RedefinableTemplateSignatureEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPartCN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof ComponentEditPartCN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof SignalEditPartCN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof InterfaceEditPartCN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof ModelEditPartCN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof EnumerationEditPartCN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof PackageEditPartCN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof ClassEditPartCN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPartCN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof DataTypeEditPartCN) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof Comment2EditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof Constraint2EditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		if(targetEditPart instanceof ContainmentCircleEditPart) {
			types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */getMATypesForTarget(IElementType relationshipType) {
		List/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */types = new ArrayList/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */();
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Dependency_2014);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.AssociationClass_2013);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Association_2015);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.InstanceSpecification_2001);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Component_2002);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Signal_2003);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Interface_2004);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Model_2005);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Enumeration_2006);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Package_2007);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Class_2008);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.PrimitiveType_2009);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.DataType_2010);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Constraint_2011);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Comment_2012);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.DurationObservation_2095);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.TimeObservation_2096);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.InstanceSpecification_3020);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Component_3021);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Signal_3022);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Interface_3023);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Model_3024);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Enumeration_3025);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Package_3009);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Class_3010);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.PrimitiveType_3026);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.DataType_3027);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Comment_3028);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Constraint_3029);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Port_3032);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */getMARelTypesOnTarget() {
		List/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */types = new ArrayList/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */();
		types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		types.add(UMLElementTypes.TemplateBinding_4015);
		return types;
	}

	/**
	 * @generated
	 */
	public List/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */getMATypesForSource(IElementType relationshipType) {
		List/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */types = new ArrayList/* <org.eclipse.gmf.runtime.emf.type.core.IElementType> */();
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Comment_2012);
		}
		if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Comment_3028);
		}
		if(relationshipType == UMLElementTypes.ConstraintConstrainedElement_4014) {
			types.add(UMLElementTypes.Constraint_2011);
		}
		if(relationshipType == UMLElementTypes.ConstraintConstrainedElement_4014) {
			types.add(UMLElementTypes.Constraint_3029);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.AssociationClass_2013);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Association_2015);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Component_2002);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Signal_2003);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Interface_2004);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Model_2005);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Enumeration_2006);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Package_2007);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Class_2008);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.PrimitiveType_2009);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.DataType_2010);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Component_3021);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Signal_3022);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Interface_3023);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Model_3024);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Enumeration_3025);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Package_3009);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.Class_3010);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.PrimitiveType_3026);
		}
		if(relationshipType == UMLElementTypes.TemplateBinding_4015) {
			types.add(UMLElementTypes.DataType_3027);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public Object getPreferredValue(EStructuralFeature feature) {
		IPreferenceStore preferenceStore = (IPreferenceStore)getDiagramPreferencesHint().getPreferenceStore();
		Object result = null;

		if(feature == NotationPackage.eINSTANCE.getLineStyle_LineColor() || feature == NotationPackage.eINSTANCE.getFontStyle_FontColor() || feature == NotationPackage.eINSTANCE.getFillStyle_FillColor()) {
			String prefColor = null;
			if(feature == NotationPackage.eINSTANCE.getLineStyle_LineColor()) {
				prefColor = PreferenceConstantHelper.getElementConstant("Comment", PreferenceConstantHelper.COLOR_LINE);
			} else if(feature == NotationPackage.eINSTANCE.getFontStyle_FontColor()) {
				prefColor = PreferenceConstantHelper.getElementConstant("Comment", PreferenceConstantHelper.COLOR_FONT);
			} else if(feature == NotationPackage.eINSTANCE.getFillStyle_FillColor()) {
				prefColor = PreferenceConstantHelper.getElementConstant("Comment", PreferenceConstantHelper.COLOR_FILL);
			}
			result = FigureUtilities.RGBToInteger(PreferenceConverter.getColor((IPreferenceStore)preferenceStore, prefColor));
		} else if(feature == NotationPackage.eINSTANCE.getFillStyle_Transparency() || feature == NotationPackage.eINSTANCE.getFillStyle_Gradient()) {
			String prefGradient = PreferenceConstantHelper.getElementConstant("Comment", PreferenceConstantHelper.COLOR_GRADIENT);
			GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(preferenceStore.getString(prefGradient));
			if(feature == NotationPackage.eINSTANCE.getFillStyle_Transparency()) {
				result = new Integer(gradientPreferenceConverter.getTransparency());
			} else if(feature == NotationPackage.eINSTANCE.getFillStyle_Gradient()) {
				result = gradientPreferenceConverter.getGradientData();
			}
		}

		if(result == null) {
			result = getStructuralFeatureValue(feature);
		}
		return result;
	}

}
