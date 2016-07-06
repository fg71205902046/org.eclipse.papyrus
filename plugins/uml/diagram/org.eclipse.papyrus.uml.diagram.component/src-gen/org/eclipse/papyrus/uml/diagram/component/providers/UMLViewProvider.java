/**
 * Copyright (c) 2014 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 */
package org.eclipse.papyrus.uml.diagram.component.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateEdgeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewForKindOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.TitleStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper;
import org.eclipse.papyrus.infra.gmfdiag.common.reconciler.DiagramVersioningUtils;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter;
import org.eclipse.papyrus.uml.diagram.common.helper.PreferenceInitializerForElementHelper;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.AbstractionAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.AbstractionEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.AbstractionNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.CommentAnnotatedElementEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.CommentBodyEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.CommentBodyEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.CommentEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.CommentEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentCompositeCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentCompositeCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentCompositeCompartmentEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentFloatingLabelEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentFloatingLabelEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentFloatingLabelEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentNameEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentRealizationAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentRealizationEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentRealizationNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConnectorAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConnectorEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConnectorNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintConstrainedElementEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintNameEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintSpecificationEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.DefaultNamedElementEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.DefaultNamedElementNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.DependencyAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.DependencyBranchEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.DependencyEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.DependencyNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.DependencyNodeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.DependencyNodeFloatingLabelEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.GeneralizationAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.GeneralizationEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceAttributeCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceAttributeCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceFloatingLabelEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceFloatingLabelEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceNameEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceOperationCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceOperationCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfacePortLinkEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceRealizationAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceRealizationEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceRealizationNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.LinkDescriptorEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ManifestationAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ManifestationEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ManifestationNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ModelEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ModelEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ModelNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ModelNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ModelPackageableElementCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ModelPackageableElementCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.MultiDependencyLabelEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.OperationForInterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PackageEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PackageEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PackageNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PackageNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PackagePackageableElementCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PackagePackageableElementCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PortAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PortEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PortNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PropertyForInterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PropertyPartEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PropertyPartNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ReceptionInInterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.RectangleInterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.RectangleInterfaceEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.RectangleInterfaceFloatingLabelEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.RectangleInterfaceFloatingLabelEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.RectangleInterfaceNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.RectangleInterfaceNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.SubstitutionAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.SubstitutionEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.SubstitutionNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.UsageAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.UsageEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.UsageNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.part.UMLVisualIDRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;

/**
 * @generated
 */
public class UMLViewProvider extends AbstractProvider implements IViewProvider {

	/**
	 * @generated
	 */
	@Override
	public final boolean provides(IOperation operation) {
		if (operation instanceof CreateViewForKindOperation) {
			return provides((CreateViewForKindOperation) operation);
		}
		assert operation instanceof CreateViewOperation;
		if (operation instanceof CreateDiagramViewOperation) {
			return provides((CreateDiagramViewOperation) operation);
		} else if (operation instanceof CreateEdgeViewOperation) {
			return provides((CreateEdgeViewOperation) operation);
		} else if (operation instanceof CreateNodeViewOperation) {
			return provides((CreateNodeViewOperation) operation);
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateViewForKindOperation op) {
		/*
		 * if (op.getViewKind() == Node.class)
		 * return getNodeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
		 * if (op.getViewKind() == Edge.class)
		 * return getEdgeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
		 */

		// check Diagram Type should be the class diagram
		String modelID = UMLVisualIDRegistry.getModelID(op.getContainerView());
		if (!getDiagramProvidedId().equals(modelID)) {
			return false;
		}

		String visualID = UMLVisualIDRegistry.getVisualID(op.getSemanticHint());
		if (Node.class.isAssignableFrom(op.getViewKind())) {
			return UMLVisualIDRegistry.canCreateNode(op.getContainerView(), visualID);
		}

		return true;
	}

	/**
	 * @generated
	 */
	protected String getDiagramProvidedId() {
		/*
		 * Indicates for which diagram this provider works for.
		 * <p>
		 * This method can be overloaded when diagram editor inherits from another one, but should never be <code>null</code>
		 * </p>
		 * 
		 * @return the unique identifier of the diagram for which views are provided.
		 */
		return ComponentDiagramEditPart.MODEL_ID;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateDiagramViewOperation op) {
		return ComponentDiagramEditPart.MODEL_ID.equals(op.getSemanticHint())
				&& UMLVisualIDRegistry.getDiagramVisualID(getSemanticElement(op.getSemanticAdapter())) != null;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateNodeViewOperation op) {
		if (op.getContainerView() == null) {
			return false;
		}
		IElementType elementType = getSemanticElementType(op.getSemanticAdapter());
		EObject domainElement = getSemanticElement(op.getSemanticAdapter());
		String visualID;
		if (op.getSemanticHint() == null) {
			// Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
			// In this situation there should be NO elementType, visualID will be determined
			// by VisualIDRegistry.getNodeVisualID() for domainElement.
			if (elementType != null || domainElement == null) {
				return false;
			}
			visualID = UMLVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement);
		} else {
			visualID = UMLVisualIDRegistry.getVisualID(op.getSemanticHint());
			if (elementType != null) {

				if (!UMLElementTypes.isKnownElementType(elementType) || (!(elementType instanceof IHintedType))) {
					return false; // foreign element type
				}

				String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
				if (!op.getSemanticHint().equals(elementTypeHint)) {
					return false; // if semantic hint is specified it should be the same as in element type
				}
				// if (domainElement != null && !visualID.equals(org.eclipse.papyrus.uml.diagram.component.part.UMLVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement))) {
				// return false; // visual id for node EClass should match visual id from element type
				// }
			} else {
				if (!ComponentDiagramEditPart.MODEL_ID.equals(UMLVisualIDRegistry.getModelID(op.getContainerView()))) {
					return false; // foreign diagram
				}
				if (visualID != null) {
					switch (visualID) {
					case DependencyNodeEditPart.VISUAL_ID:
					case ComponentEditPart.VISUAL_ID:
					case ModelEditPart.VISUAL_ID:
					case PackageEditPart.VISUAL_ID:
					case RectangleInterfaceEditPart.VISUAL_ID:
					case CommentEditPart.VISUAL_ID:
					case ConstraintEditPart.VISUAL_ID:
					case DefaultNamedElementEditPart.VISUAL_ID:
					case InterfaceEditPart.VISUAL_ID:
					case PortEditPart.VISUAL_ID:
					case ModelEditPartCN.VISUAL_ID:
					case PackageEditPartCN.VISUAL_ID:
					case RectangleInterfaceEditPartCN.VISUAL_ID:
					case PropertyForInterfaceEditPart.VISUAL_ID:
					case OperationForInterfaceEditPart.VISUAL_ID:
					case ReceptionInInterfaceEditPart.VISUAL_ID:
					case PropertyPartEditPartCN.VISUAL_ID:
					case ComponentEditPartCN.VISUAL_ID:
					case ComponentEditPartPCN.VISUAL_ID:
					case CommentEditPartPCN.VISUAL_ID:
					case ConstraintEditPartPCN.VISUAL_ID:
					case InterfaceEditPartPCN.VISUAL_ID:
						if (domainElement == null || !visualID
								.equals(UMLVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement))) {
							return false; // visual id in semantic hint should match visual id for domain element
						}
						break;
					default:
						return false;
					}
				}
			}
		}

		return UMLVisualIDRegistry.canCreateNode(op.getContainerView(), visualID);
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateEdgeViewOperation op) {
		IElementType elementType = getSemanticElementType(op.getSemanticAdapter());

		if (!UMLElementTypes.isKnownElementType(elementType) || (!(elementType instanceof IHintedType))) {
			return false; // foreign element type
		}

		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if (elementTypeHint == null
				|| (op.getSemanticHint() != null && !elementTypeHint.equals(op.getSemanticHint()))) {
			return false; // our hint is visual id and must be specified, and it should be the same as in element type
		}
		// String visualID = org.eclipse.papyrus.uml.diagram.component.part.UMLVisualIDRegistry.getVisualID(elementTypeHint);
		// org.eclipse.emf.ecore.EObject domainElement = getSemanticElement(op.getSemanticAdapter());
		// if (domainElement != null && !visualID.equals(org.eclipse.papyrus.uml.diagram.component.part.UMLVisualIDRegistry.getLinkWithClassVisualID(domainElement))) {
		// return false; // visual id for link EClass should match visual id from element type
		// }
		return true;
	}

	/**
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Diagram createDiagram(IAdaptable semanticAdapter, String diagramKind, PreferencesHint preferencesHint) {
		Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
		DiagramVersioningUtils.stampCurrentVersion(diagram);
		diagram.getStyles().add(NotationFactory.eINSTANCE.createDiagramStyle());
		diagram.setType(ComponentDiagramEditPart.MODEL_ID);
		diagram.setElement(getSemanticElement(semanticAdapter));
		diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
		return diagram;
	}

	/**
	 * @generated
	 */
	@Override
	public Node createNode(IAdaptable semanticAdapter, View containerView, String semanticHint, int index,
			boolean persisted, PreferencesHint preferencesHint) {
		final EObject domainElement = getSemanticElement(semanticAdapter);
		final String visualID;
		if (semanticHint == null) {
			visualID = UMLVisualIDRegistry.getNodeVisualID(containerView, domainElement);
		} else {
			visualID = UMLVisualIDRegistry.getVisualID(semanticHint);
		}
		if (visualID != null) {
			switch (visualID) {
			case DependencyNodeEditPart.VISUAL_ID:
				return createDependency_Shape(domainElement, containerView, index, persisted, preferencesHint);
			case ComponentEditPart.VISUAL_ID:
				return createComponent_PackagedElementShape(domainElement, containerView, index, persisted,
						preferencesHint);
			case ModelEditPart.VISUAL_ID:
				return createModel_Shape(domainElement, containerView, index, persisted, preferencesHint);
			case PackageEditPart.VISUAL_ID:
				return createPackage_Shape(domainElement, containerView, index, persisted, preferencesHint);
			case RectangleInterfaceEditPart.VISUAL_ID:
				return createInterface_ClassifierShape(domainElement, containerView, index, persisted, preferencesHint);
			case CommentEditPart.VISUAL_ID:
				return createComment_Shape(domainElement, containerView, index, persisted, preferencesHint);
			case ConstraintEditPart.VISUAL_ID:
				return createConstraint_Shape(domainElement, containerView, index, persisted, preferencesHint);
			case DefaultNamedElementEditPart.VISUAL_ID:
				return createNamedElement_DefaultShape(domainElement, containerView, index, persisted, preferencesHint);
			case InterfaceEditPart.VISUAL_ID:
				return createInterface_Shape(domainElement, containerView, index, persisted, preferencesHint);
			case PortEditPart.VISUAL_ID:
				return createPort_Shape(domainElement, containerView, index, persisted, preferencesHint);
			case ModelEditPartCN.VISUAL_ID:
				return createModel_Shape_CN(domainElement, containerView, index, persisted, preferencesHint);
			case PackageEditPartCN.VISUAL_ID:
				return createPackage_Shape_CN(domainElement, containerView, index, persisted, preferencesHint);
			case RectangleInterfaceEditPartCN.VISUAL_ID:
				return createInterface_ClassifierShape_CN(domainElement, containerView, index, persisted,
						preferencesHint);
			case ComponentEditPartCN.VISUAL_ID:
				return createComponent_PackagedElementShape_CCN(domainElement, containerView, index, persisted,
						preferencesHint);
			case ComponentEditPartPCN.VISUAL_ID:
				return createComponent_PackagedElementShape_CN(domainElement, containerView, index, persisted,
						preferencesHint);
			case CommentEditPartPCN.VISUAL_ID:
				return createComment_Shape_CN(domainElement, containerView, index, persisted, preferencesHint);
			case ConstraintEditPartPCN.VISUAL_ID:
				return createConstraint_Shape_CN(domainElement, containerView, index, persisted, preferencesHint);
			case PropertyForInterfaceEditPart.VISUAL_ID:
				return createProperty_InterfaceAttributeLabel(domainElement, containerView, index, persisted,
						preferencesHint);
			case OperationForInterfaceEditPart.VISUAL_ID:
				return createOperation_InterfaceOperationLabel(domainElement, containerView, index, persisted,
						preferencesHint);
			case ReceptionInInterfaceEditPart.VISUAL_ID:
				return createReception_InterfaceReceptionLabel(domainElement, containerView, index, persisted,
						preferencesHint);
			case InterfaceEditPartPCN.VISUAL_ID:
				return createInterface_Shape_CN(domainElement, containerView, index, persisted, preferencesHint);
			case PropertyPartEditPartCN.VISUAL_ID:
				return createProperty_Shape(domainElement, containerView, index, persisted, preferencesHint);
			}
		}
		// can't happen, provided #provides(CreateNodeViewOperation) is correct
		return null;
	}

	/**
	 * @generated
	 */
	@Override
	public Edge createEdge(IAdaptable semanticAdapter, View containerView, String semanticHint, int index,
			boolean persisted, PreferencesHint preferencesHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		String vid = UMLVisualIDRegistry.getVisualID(elementTypeHint);
		if (vid != null) {
			switch (vid) {
			case UsageEditPart.VISUAL_ID:
				return createUsage_Edge(getSemanticElement(semanticAdapter), containerView, index, persisted,
						preferencesHint);
			case InterfaceRealizationEditPart.VISUAL_ID:
				return createInterfaceRealization_Edge(getSemanticElement(semanticAdapter), containerView, index,
						persisted, preferencesHint);
			case GeneralizationEditPart.VISUAL_ID:
				return createGeneralization_Edge(getSemanticElement(semanticAdapter), containerView, index, persisted,
						preferencesHint);
			case SubstitutionEditPart.VISUAL_ID:
				return createSubstitution_Edge(getSemanticElement(semanticAdapter), containerView, index, persisted,
						preferencesHint);
			case ManifestationEditPart.VISUAL_ID:
				return createManifestation_Edge(getSemanticElement(semanticAdapter), containerView, index, persisted,
						preferencesHint);
			case ComponentRealizationEditPart.VISUAL_ID:
				return createComponentRealization_Edge(getSemanticElement(semanticAdapter), containerView, index,
						persisted, preferencesHint);
			case AbstractionEditPart.VISUAL_ID:
				return createAbstraction_Edge(getSemanticElement(semanticAdapter), containerView, index, persisted,
						preferencesHint);
			case LinkDescriptorEditPart.VISUAL_ID:
				return createLink_DescriptorEdge(containerView, index, persisted, preferencesHint);
			case CommentAnnotatedElementEditPart.VISUAL_ID:
				return createComment_AnnotatedElementEdge(containerView, index, persisted, preferencesHint);
			case ConstraintConstrainedElementEditPart.VISUAL_ID:
				return createConstraint_ConstrainedElementEdge(containerView, index, persisted, preferencesHint);
			case DependencyEditPart.VISUAL_ID:
				return createDependency_Edge(getSemanticElement(semanticAdapter), containerView, index, persisted,
						preferencesHint);
			case DependencyBranchEditPart.VISUAL_ID:
				return createDependency_BranchEdge(getSemanticElement(semanticAdapter), containerView, index, persisted,
						preferencesHint);
			case InterfacePortLinkEditPart.VISUAL_ID:
				return createLink_InterfacePortEdge(containerView, index, persisted, preferencesHint);
			case ConnectorEditPart.VISUAL_ID:
				return createConnector_Edge(getSemanticElement(semanticAdapter), containerView, index, persisted,
						preferencesHint);
			}
		}
		// can never happen, provided #provides(CreateEdgeViewOperation) is correct
		return null;
	}

	/**
	 * @generated
	 */
	public Node createDependency_Shape(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(DependencyNodeEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "DependencyNode");
		Node dependency_MultiNameLabel = createLabel(node,
				UMLVisualIDRegistry.getType(MultiDependencyLabelEditPart.VISUAL_ID));
		dependency_MultiNameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location dependency_MultiNameLabel_Location = (Location) dependency_MultiNameLabel.getLayoutConstraint();
		dependency_MultiNameLabel_Location.setX(0);
		dependency_MultiNameLabel_Location.setY(5);
		Node dependency_FloatingNameLabel = createLabel(node,
				UMLVisualIDRegistry.getType(DependencyNodeFloatingLabelEditPart.VISUAL_ID));
		dependency_FloatingNameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location dependency_FloatingNameLabel_Location = (Location) dependency_FloatingNameLabel.getLayoutConstraint();
		dependency_FloatingNameLabel_Location.setX(0);
		dependency_FloatingNameLabel_Location.setY(5);
		return node;
	}

	/**
	 * @generated
	 */
	public Node createComponent_PackagedElementShape(EObject domainElement, View containerView, int index,
			boolean persisted, PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(ComponentEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Component");
		Node component_NameLabel = createLabel(node, UMLVisualIDRegistry.getType(ComponentNameEditPart.VISUAL_ID));
		Node component_FloatingNameLabel = createLabel(node,
				UMLVisualIDRegistry.getType(ComponentFloatingLabelEditPart.VISUAL_ID));
		component_FloatingNameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location component_FloatingNameLabel_Location = (Location) component_FloatingNameLabel.getLayoutConstraint();
		component_FloatingNameLabel_Location.setX(0);
		component_FloatingNameLabel_Location.setY(5);
		createCompartment(node, UMLVisualIDRegistry.getType(ComponentCompositeCompartmentEditPart.VISUAL_ID), true,
				true, false, false);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Component");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createModel_Shape(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(ModelEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Model");
		Node model_NameLabel = createLabel(node, UMLVisualIDRegistry.getType(ModelNameEditPart.VISUAL_ID));
		createCompartment(node, UMLVisualIDRegistry.getType(ModelPackageableElementCompartmentEditPart.VISUAL_ID),
				false, true, false, false);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Model");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createPackage_Shape(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(PackageEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Package");
		Node package_NameLabel = createLabel(node, UMLVisualIDRegistry.getType(PackageNameEditPart.VISUAL_ID));
		createCompartment(node, UMLVisualIDRegistry.getType(PackagePackageableElementCompartmentEditPart.VISUAL_ID),
				false, true, false, false);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Package");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createInterface_ClassifierShape(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(RectangleInterfaceEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Interface");
		Node interface_ClassifierNameLabel = createLabel(node,
				UMLVisualIDRegistry.getType(RectangleInterfaceNameEditPart.VISUAL_ID));
		Node interface_ClassifierFloatingNameLabel = createLabel(node,
				UMLVisualIDRegistry.getType(RectangleInterfaceFloatingLabelEditPart.VISUAL_ID));
		interface_ClassifierFloatingNameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location interface_ClassifierFloatingNameLabel_Location = (Location) interface_ClassifierFloatingNameLabel
				.getLayoutConstraint();
		interface_ClassifierFloatingNameLabel_Location.setX(0);
		interface_ClassifierFloatingNameLabel_Location.setY(5);
		createCompartment(node, UMLVisualIDRegistry.getType(InterfaceAttributeCompartmentEditPart.VISUAL_ID), true,
				true, true, true);
		createCompartment(node, UMLVisualIDRegistry.getType(InterfaceOperationCompartmentEditPart.VISUAL_ID), true,
				true, true, true);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Interface");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createComment_Shape(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(CommentEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Comment");
		Node comment_BodyLabel = createLabel(node, UMLVisualIDRegistry.getType(CommentBodyEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Node createConstraint_Shape(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(ConstraintEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Constraint");
		Node constraint_NameLabel = createLabel(node, UMLVisualIDRegistry.getType(ConstraintNameEditPart.VISUAL_ID));
		Node constraint_BodyLabel = createLabel(node,
				UMLVisualIDRegistry.getType(ConstraintSpecificationEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Node createNamedElement_DefaultShape(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(DefaultNamedElementEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "DefaultNamedElement");
		Node namedElement_NameLabel = createLabel(node,
				UMLVisualIDRegistry.getType(DefaultNamedElementNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Node createInterface_Shape(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(InterfaceEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Interface");
		Node interface_NameLabel = createLabel(node, UMLVisualIDRegistry.getType(InterfaceNameEditPart.VISUAL_ID));
		interface_NameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location interface_NameLabel_Location = (Location) interface_NameLabel.getLayoutConstraint();
		interface_NameLabel_Location.setX(0);
		interface_NameLabel_Location.setY(5);
		Node interface_FloatingNameLabel = createLabel(node,
				UMLVisualIDRegistry.getType(InterfaceFloatingLabelEditPart.VISUAL_ID));
		interface_FloatingNameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location interface_FloatingNameLabel_Location = (Location) interface_FloatingNameLabel.getLayoutConstraint();
		interface_FloatingNameLabel_Location.setX(0);
		interface_FloatingNameLabel_Location.setY(5);
		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(node, prefStore, "Interface");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createPort_Shape(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Port");
		Node port_NameLabel = createLabel(node, UMLVisualIDRegistry.getType(PortNameEditPart.VISUAL_ID));
		port_NameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location port_NameLabel_Location = (Location) port_NameLabel.getLayoutConstraint();
		port_NameLabel_Location.setX(0);
		port_NameLabel_Location.setY(5);
		Node port_StereotypeLabel = createLabel(node,
				UMLVisualIDRegistry.getType(PortAppliedStereotypeEditPart.VISUAL_ID));
		port_StereotypeLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location port_StereotypeLabel_Location = (Location) port_StereotypeLabel.getLayoutConstraint();
		port_StereotypeLabel_Location.setX(25);
		port_StereotypeLabel_Location.setY(-10);
		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(node, prefStore, "Port");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createModel_Shape_CN(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(ModelEditPartCN.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Model");
		Node model_NameLabel_CN = createLabel(node, UMLVisualIDRegistry.getType(ModelNameEditPartCN.VISUAL_ID));
		createCompartment(node, UMLVisualIDRegistry.getType(ModelPackageableElementCompartmentEditPartCN.VISUAL_ID),
				false, true, false, false);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Model");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createPackage_Shape_CN(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(PackageEditPartCN.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Package");
		Node package_NameLabel_CN = createLabel(node, UMLVisualIDRegistry.getType(PackageNameEditPartCN.VISUAL_ID));
		createCompartment(node, UMLVisualIDRegistry.getType(PackagePackageableElementCompartmentEditPartCN.VISUAL_ID),
				false, true, false, false);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Package");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createInterface_ClassifierShape_CN(EObject domainElement, View containerView, int index,
			boolean persisted, PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(RectangleInterfaceEditPartCN.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Interface");
		Node interface_ClassifierNameLabel_CN = createLabel(node,
				UMLVisualIDRegistry.getType(RectangleInterfaceNameEditPartCN.VISUAL_ID));
		Node interface_ClassifierFloatingNameLabel_CN = createLabel(node,
				UMLVisualIDRegistry.getType(RectangleInterfaceFloatingLabelEditPartCN.VISUAL_ID));
		interface_ClassifierFloatingNameLabel_CN.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location interface_ClassifierFloatingNameLabel_CN_Location = (Location) interface_ClassifierFloatingNameLabel_CN
				.getLayoutConstraint();
		interface_ClassifierFloatingNameLabel_CN_Location.setX(0);
		interface_ClassifierFloatingNameLabel_CN_Location.setY(5);
		createCompartment(node, UMLVisualIDRegistry.getType(InterfaceAttributeCompartmentEditPartCN.VISUAL_ID), true,
				true, true, true);
		createCompartment(node, UMLVisualIDRegistry.getType(InterfaceOperationCompartmentEditPartCN.VISUAL_ID), true,
				true, true, true);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Interface");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createComponent_PackagedElementShape_CCN(EObject domainElement, View containerView, int index,
			boolean persisted, PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(ComponentEditPartCN.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Component");
		Node component_NameLabel_CCN = createLabel(node,
				UMLVisualIDRegistry.getType(ComponentNameEditPartCN.VISUAL_ID));
		Node component_FloatingNameLabel_CCN = createLabel(node,
				UMLVisualIDRegistry.getType(ComponentFloatingLabelEditPartCN.VISUAL_ID));
		component_FloatingNameLabel_CCN.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location component_FloatingNameLabel_CCN_Location = (Location) component_FloatingNameLabel_CCN
				.getLayoutConstraint();
		component_FloatingNameLabel_CCN_Location.setX(0);
		component_FloatingNameLabel_CCN_Location.setY(5);
		createCompartment(node, UMLVisualIDRegistry.getType(ComponentCompositeCompartmentEditPartCN.VISUAL_ID), true,
				true, false, false);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Component");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createComponent_PackagedElementShape_CN(EObject domainElement, View containerView, int index,
			boolean persisted, PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(ComponentEditPartPCN.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Component");
		Node component_NameLabel_CN = createLabel(node,
				UMLVisualIDRegistry.getType(ComponentNameEditPartPCN.VISUAL_ID));
		Node component_FloatingNameLabel_CN = createLabel(node,
				UMLVisualIDRegistry.getType(ComponentFloatingLabelEditPartPCN.VISUAL_ID));
		component_FloatingNameLabel_CN.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location component_FloatingNameLabel_CN_Location = (Location) component_FloatingNameLabel_CN
				.getLayoutConstraint();
		component_FloatingNameLabel_CN_Location.setX(0);
		component_FloatingNameLabel_CN_Location.setY(5);
		createCompartment(node, UMLVisualIDRegistry.getType(ComponentCompositeCompartmentEditPartPCN.VISUAL_ID), true,
				true, false, false);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Component");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createComment_Shape_CN(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(CommentEditPartPCN.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Comment");
		Node comment_BodyLabel_CN = createLabel(node, UMLVisualIDRegistry.getType(CommentBodyEditPartPCN.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Node createConstraint_Shape_CN(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(ConstraintEditPartPCN.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Constraint");
		Node constraint_NameLabel_CN = createLabel(node,
				UMLVisualIDRegistry.getType(ConstraintNameEditPartPCN.VISUAL_ID));
		Node constraint_BodyLabel_CN = createLabel(node,
				UMLVisualIDRegistry.getType(ConstraintSpecificationEditPartPCN.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Node createProperty_InterfaceAttributeLabel(EObject domainElement, View containerView, int index,
			boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		node.setType(UMLVisualIDRegistry.getType(PropertyForInterfaceEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);

		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Property");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createOperation_InterfaceOperationLabel(EObject domainElement, View containerView, int index,
			boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		node.setType(UMLVisualIDRegistry.getType(OperationForInterfaceEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);

		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Operation");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createReception_InterfaceReceptionLabel(EObject domainElement, View containerView, int index,
			boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		node.setType(UMLVisualIDRegistry.getType(ReceptionInInterfaceEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);

		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Reception");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createInterface_Shape_CN(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(InterfaceEditPartPCN.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Interface");
		Node interface_NameLabel_CN = createLabel(node,
				UMLVisualIDRegistry.getType(InterfaceNameEditPartPCN.VISUAL_ID));
		interface_NameLabel_CN.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location interface_NameLabel_CN_Location = (Location) interface_NameLabel_CN.getLayoutConstraint();
		interface_NameLabel_CN_Location.setX(0);
		interface_NameLabel_CN_Location.setY(5);
		Node interface_FloatingNameLabel_CN = createLabel(node,
				UMLVisualIDRegistry.getType(InterfaceFloatingLabelEditPartPCN.VISUAL_ID));
		interface_FloatingNameLabel_CN.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location interface_FloatingNameLabel_CN_Location = (Location) interface_FloatingNameLabel_CN
				.getLayoutConstraint();
		interface_FloatingNameLabel_CN_Location.setX(0);
		interface_FloatingNameLabel_CN_Location.setY(5);
		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(node, prefStore, "Interface");
		return node;
	}

	/**
	 * @generated
	 */
	public Node createProperty_Shape(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(PropertyPartEditPartCN.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Property");
		Node property_NameLabel = createLabel(node, UMLVisualIDRegistry.getType(PropertyPartNameEditPartCN.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Edge createUsage_Edge(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(UsageEditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "Usage");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }
		Node usage_NameLabel = createLabel(edge, UMLVisualIDRegistry.getType(UsageNameEditPart.VISUAL_ID));
		usage_NameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location usage_NameLabel_Location = (Location) usage_NameLabel.getLayoutConstraint();
		usage_NameLabel_Location.setX(0);
		usage_NameLabel_Location.setY(60);
		Node usage_StereotypeLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(UsageAppliedStereotypeEditPart.VISUAL_ID));
		usage_StereotypeLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location usage_StereotypeLabel_Location = (Location) usage_StereotypeLabel.getLayoutConstraint();
		usage_StereotypeLabel_Location.setX(0);
		usage_StereotypeLabel_Location.setY(30);

		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(edge, prefStore, "Usage");
		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createInterfaceRealization_Edge(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(InterfaceRealizationEditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "InterfaceRealization");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }
		Node interfaceRealization_NameLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(InterfaceRealizationNameEditPart.VISUAL_ID));
		interfaceRealization_NameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location interfaceRealization_NameLabel_Location = (Location) interfaceRealization_NameLabel
				.getLayoutConstraint();
		interfaceRealization_NameLabel_Location.setX(0);
		interfaceRealization_NameLabel_Location.setY(60);
		Node interfaceRealization_StereotypeLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(InterfaceRealizationAppliedStereotypeEditPart.VISUAL_ID));
		interfaceRealization_StereotypeLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location interfaceRealization_StereotypeLabel_Location = (Location) interfaceRealization_StereotypeLabel
				.getLayoutConstraint();
		interfaceRealization_StereotypeLabel_Location.setX(0);
		interfaceRealization_StereotypeLabel_Location.setY(30);

		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(edge, prefStore, "InterfaceRealization");
		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createGeneralization_Edge(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(GeneralizationEditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "Generalization");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }
		Node generalization_StereotypeLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(GeneralizationAppliedStereotypeEditPart.VISUAL_ID));
		generalization_StereotypeLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location generalization_StereotypeLabel_Location = (Location) generalization_StereotypeLabel
				.getLayoutConstraint();
		generalization_StereotypeLabel_Location.setX(0);
		generalization_StereotypeLabel_Location.setY(60);

		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(edge, prefStore, "Generalization");
		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createSubstitution_Edge(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(SubstitutionEditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "Substitution");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }
		Node substitution_NameLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(SubstitutionNameEditPart.VISUAL_ID));
		substitution_NameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location substitution_NameLabel_Location = (Location) substitution_NameLabel.getLayoutConstraint();
		substitution_NameLabel_Location.setX(0);
		substitution_NameLabel_Location.setY(60);
		Node substitution_StereotypeLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(SubstitutionAppliedStereotypeEditPart.VISUAL_ID));
		substitution_StereotypeLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location substitution_StereotypeLabel_Location = (Location) substitution_StereotypeLabel.getLayoutConstraint();
		substitution_StereotypeLabel_Location.setX(0);
		substitution_StereotypeLabel_Location.setY(30);

		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(edge, prefStore, "Substitution");
		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createManifestation_Edge(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(ManifestationEditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "Manifestation");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }
		Node manifestation_NameLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(ManifestationNameEditPart.VISUAL_ID));
		manifestation_NameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location manifestation_NameLabel_Location = (Location) manifestation_NameLabel.getLayoutConstraint();
		manifestation_NameLabel_Location.setX(0);
		manifestation_NameLabel_Location.setY(60);
		Node manifestation_StereotypeLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(ManifestationAppliedStereotypeEditPart.VISUAL_ID));
		manifestation_StereotypeLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location manifestation_StereotypeLabel_Location = (Location) manifestation_StereotypeLabel
				.getLayoutConstraint();
		manifestation_StereotypeLabel_Location.setX(0);
		manifestation_StereotypeLabel_Location.setY(30);

		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(edge, prefStore, "Manifestation");
		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createComponentRealization_Edge(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(ComponentRealizationEditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "ComponentRealization");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }
		Node componentRealization_NameLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(ComponentRealizationNameEditPart.VISUAL_ID));
		componentRealization_NameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location componentRealization_NameLabel_Location = (Location) componentRealization_NameLabel
				.getLayoutConstraint();
		componentRealization_NameLabel_Location.setX(0);
		componentRealization_NameLabel_Location.setY(60);
		Node componentRealization_StereotypeLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(ComponentRealizationAppliedStereotypeEditPart.VISUAL_ID));
		componentRealization_StereotypeLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location componentRealization_StereotypeLabel_Location = (Location) componentRealization_StereotypeLabel
				.getLayoutConstraint();
		componentRealization_StereotypeLabel_Location.setX(0);
		componentRealization_StereotypeLabel_Location.setY(30);

		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(edge, prefStore, "ComponentRealization");
		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createAbstraction_Edge(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(AbstractionEditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "Abstraction");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }
		Node abstraction_NameLabel = createLabel(edge, UMLVisualIDRegistry.getType(AbstractionNameEditPart.VISUAL_ID));
		abstraction_NameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location abstraction_NameLabel_Location = (Location) abstraction_NameLabel.getLayoutConstraint();
		abstraction_NameLabel_Location.setX(0);
		abstraction_NameLabel_Location.setY(60);
		Node abstraction_StereotypeLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(AbstractionAppliedStereotypeEditPart.VISUAL_ID));
		abstraction_StereotypeLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location abstraction_StereotypeLabel_Location = (Location) abstraction_StereotypeLabel.getLayoutConstraint();
		abstraction_StereotypeLabel_Location.setX(0);
		abstraction_StereotypeLabel_Location.setY(30);

		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(edge, prefStore, "Abstraction");
		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createLink_DescriptorEdge(View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(LinkDescriptorEditPart.VISUAL_ID));
		edge.setElement(null);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }

		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createComment_AnnotatedElementEdge(View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(CommentAnnotatedElementEditPart.VISUAL_ID));
		edge.setElement(null);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "CommentAnnotatedElement");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }

		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createConstraint_ConstrainedElementEdge(View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(ConstraintConstrainedElementEditPart.VISUAL_ID));
		edge.setElement(null);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "ConstraintConstrainedElement");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }

		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createDependency_Edge(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(DependencyEditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "DependencyLink");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }
		Node dependency_NameLabel = createLabel(edge, UMLVisualIDRegistry.getType(DependencyNameEditPart.VISUAL_ID));
		dependency_NameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location dependency_NameLabel_Location = (Location) dependency_NameLabel.getLayoutConstraint();
		dependency_NameLabel_Location.setX(0);
		dependency_NameLabel_Location.setY(60);
		Node dependency_StereotypeLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(DependencyAppliedStereotypeEditPart.VISUAL_ID));
		dependency_StereotypeLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location dependency_StereotypeLabel_Location = (Location) dependency_StereotypeLabel.getLayoutConstraint();
		dependency_StereotypeLabel_Location.setX(0);
		dependency_StereotypeLabel_Location.setY(60);

		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(edge, prefStore, "DependencyLink");
		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createDependency_BranchEdge(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(DependencyBranchEditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "Dependency");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }

		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createLink_InterfacePortEdge(View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(InterfacePortLinkEditPart.VISUAL_ID));
		edge.setElement(null);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "InterfacePortLink");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }

		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createConnector_Edge(EObject domainElement, View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Connector edge = NotationFactory.eINSTANCE.createConnector();
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		List<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(UMLVisualIDRegistry.getType(ConnectorEditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(edge, prefStore, "Connector");
		// org.eclipse.gmf.runtime.notation.Routing routing = org.eclipse.gmf.runtime.notation.Routing.get(prefStore.getInt(org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants.PREF_LINE_STYLE));
		// if (routing != null) {
		// org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.setStructuralFeatureValue(edge, org.eclipse.gmf.runtime.notation.NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
		// }
		Node connector_StereotypeLabel = createLabel(edge,
				UMLVisualIDRegistry.getType(ConnectorAppliedStereotypeEditPart.VISUAL_ID));
		connector_StereotypeLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location connector_StereotypeLabel_Location = (Location) connector_StereotypeLabel.getLayoutConstraint();
		connector_StereotypeLabel_Location.setX(0);
		connector_StereotypeLabel_Location.setY(60);
		Node connector_NameLabel = createLabel(edge, UMLVisualIDRegistry.getType(ConnectorNameEditPart.VISUAL_ID));
		connector_NameLabel.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
		Location connector_NameLabel_Location = (Location) connector_NameLabel.getLayoutConstraint();
		connector_NameLabel_Location.setX(0);
		connector_NameLabel_Location.setY(-20);

		PreferenceInitializerForElementHelper.initLabelVisibilityFromPrefs(edge, prefStore, "Connector");
		return edge;
	}

	/**
	 * @generated
	 */
	protected void stampShortcut(View containerView, Node target) {
		if (!ComponentDiagramEditPart.MODEL_ID.equals(UMLVisualIDRegistry.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put("modelID", ComponentDiagramEditPart.MODEL_ID); //$NON-NLS-1$
			target.getEAnnotations().add(shortcutAnnotation);
		}
	}

	/**
	 * @generated
	 */
	protected Node createLabel(View owner, String hint) {
		DecorationNode rv = NotationFactory.eINSTANCE.createDecorationNode();
		rv.setType(hint);
		ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
		return rv;
	}

	/**
	 * @generated
	 */
	protected Node createCompartment(View owner, String hint, boolean canCollapse, boolean hasTitle, boolean canSort,
			boolean canFilter) {
		// SemanticListCompartment rv = NotationFactory.eINSTANCE.createSemanticListCompartment();
		// rv.setShowTitle(showTitle);
		// rv.setCollapsed(isCollapsed);
		Node rv = NotationFactory.eINSTANCE.createBasicCompartment();

		rv.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());

		if (hasTitle) {
			TitleStyle ts = NotationFactory.eINSTANCE.createTitleStyle();
			rv.getStyles().add(ts);
		}
		if (canSort) {
			rv.getStyles().add(NotationFactory.eINSTANCE.createSortingStyle());
		}
		if (canFilter) {
			rv.getStyles().add(NotationFactory.eINSTANCE.createFilteringStyle());
		}
		rv.setType(hint);
		ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
		return rv;
	}

	/**
	 * @generated
	 */
	protected EObject getSemanticElement(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			return EMFCoreUtil.resolve(TransactionUtil.getEditingDomain(eObject), eObject);
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected IElementType getSemanticElementType(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}

	/**
	 * @generated
	 */
	private void initFontStyleFromPrefs(View view, final IPreferenceStore store, String elementName) {
		String fontConstant = PreferencesConstantsHelper.getElementConstant(elementName,
				PreferencesConstantsHelper.FONT);
		String fontColorConstant = PreferencesConstantsHelper.getElementConstant(elementName,
				PreferencesConstantsHelper.COLOR_FONT);

		FontStyle viewFontStyle = (FontStyle) view.getStyle(NotationPackage.Literals.FONT_STYLE);
		if (viewFontStyle != null) {
			FontData fontData = PreferenceConverter.getFontData(store, fontConstant);
			viewFontStyle.setFontName(fontData.getName());
			viewFontStyle.setFontHeight(fontData.getHeight());
			viewFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
			viewFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);

			org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(store, fontColorConstant);
			viewFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
		}
	}

	/**
	 * @generated
	 */
	private void initForegroundFromPrefs(View view, final IPreferenceStore store, String elementName) {
		String lineColorConstant = PreferencesConstantsHelper.getElementConstant(elementName,
				PreferencesConstantsHelper.COLOR_LINE);
		org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(store, lineColorConstant);
		ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLineStyle_LineColor(),
				FigureUtilities.RGBToInteger(lineRGB));
	}

	/**
	 * @generated
	 */
	private void initBackgroundFromPrefs(View view, final IPreferenceStore store, String elementName) {
		String fillColorConstant = PreferencesConstantsHelper.getElementConstant(elementName,
				PreferencesConstantsHelper.COLOR_FILL);
		String gradientColorConstant = PreferencesConstantsHelper.getElementConstant(elementName,
				PreferencesConstantsHelper.COLOR_GRADIENT);
		String gradientPolicyConstant = PreferencesConstantsHelper.getElementConstant(elementName,
				PreferencesConstantsHelper.GRADIENT_POLICY);

		org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(store, fillColorConstant);
		ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getFillStyle_FillColor(),
				FigureUtilities.RGBToInteger(fillRGB));

		FillStyle fillStyle = (FillStyle) view.getStyle(NotationPackage.Literals.FILL_STYLE);
		fillStyle.setFillColor(FigureUtilities.RGBToInteger(fillRGB).intValue());

		;
		if (store.getBoolean(gradientPolicyConstant)) {
			GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(
					store.getString(gradientColorConstant));
			fillStyle.setGradient(gradientPreferenceConverter.getGradientData());
			fillStyle.setTransparency(gradientPreferenceConverter.getTransparency());
		}
	}
}
