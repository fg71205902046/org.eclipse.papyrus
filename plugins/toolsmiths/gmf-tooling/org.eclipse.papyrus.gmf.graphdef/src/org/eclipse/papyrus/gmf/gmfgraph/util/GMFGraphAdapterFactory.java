/*******************************************************************************
 * Copyright (c) 2006, 2020 Borland Software Corporation, CEA LIST, ARTAL
 * 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 * 
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Borland - initial API and implementation
 *     Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 ******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.papyrus.gmf.gmfgraph.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.gmf.gmfgraph.AbstractFigure;
import org.eclipse.papyrus.gmf.gmfgraph.AbstractNode;
import org.eclipse.papyrus.gmf.gmfgraph.AlignmentFacet;
import org.eclipse.papyrus.gmf.gmfgraph.BasicFont;
import org.eclipse.papyrus.gmf.gmfgraph.Border;
import org.eclipse.papyrus.gmf.gmfgraph.BorderLayout;
import org.eclipse.papyrus.gmf.gmfgraph.BorderLayoutData;
import org.eclipse.papyrus.gmf.gmfgraph.BorderRef;
import org.eclipse.papyrus.gmf.gmfgraph.Canvas;
import org.eclipse.papyrus.gmf.gmfgraph.CenterLayout;
import org.eclipse.papyrus.gmf.gmfgraph.ChildAccess;
import org.eclipse.papyrus.gmf.gmfgraph.Color;
import org.eclipse.papyrus.gmf.gmfgraph.ColorPin;
import org.eclipse.papyrus.gmf.gmfgraph.Compartment;
import org.eclipse.papyrus.gmf.gmfgraph.CompoundBorder;
import org.eclipse.papyrus.gmf.gmfgraph.Connection;
import org.eclipse.papyrus.gmf.gmfgraph.ConnectionFigure;
import org.eclipse.papyrus.gmf.gmfgraph.ConstantColor;
import org.eclipse.papyrus.gmf.gmfgraph.CustomAttribute;
import org.eclipse.papyrus.gmf.gmfgraph.CustomAttributeOwner;
import org.eclipse.papyrus.gmf.gmfgraph.CustomBorder;
import org.eclipse.papyrus.gmf.gmfgraph.CustomClass;
import org.eclipse.papyrus.gmf.gmfgraph.CustomConnection;
import org.eclipse.papyrus.gmf.gmfgraph.CustomDecoration;
import org.eclipse.papyrus.gmf.gmfgraph.CustomFigure;
import org.eclipse.papyrus.gmf.gmfgraph.CustomLayout;
import org.eclipse.papyrus.gmf.gmfgraph.CustomLayoutData;
import org.eclipse.papyrus.gmf.gmfgraph.CustomPin;
import org.eclipse.papyrus.gmf.gmfgraph.DecorationFigure;
import org.eclipse.papyrus.gmf.gmfgraph.DefaultSizeFacet;
import org.eclipse.papyrus.gmf.gmfgraph.DiagramElement;
import org.eclipse.papyrus.gmf.gmfgraph.DiagramLabel;
import org.eclipse.papyrus.gmf.gmfgraph.Dimension;
import org.eclipse.papyrus.gmf.gmfgraph.Ellipse;
import org.eclipse.papyrus.gmf.gmfgraph.Figure;
import org.eclipse.papyrus.gmf.gmfgraph.FigureAccessor;
import org.eclipse.papyrus.gmf.gmfgraph.FigureDescriptor;
import org.eclipse.papyrus.gmf.gmfgraph.FigureGallery;
import org.eclipse.papyrus.gmf.gmfgraph.FigureRef;
import org.eclipse.papyrus.gmf.gmfgraph.FlowLayout;
import org.eclipse.papyrus.gmf.gmfgraph.Font;
import org.eclipse.papyrus.gmf.gmfgraph.GMFGraphPackage;
import org.eclipse.papyrus.gmf.gmfgraph.GeneralFacet;
import org.eclipse.papyrus.gmf.gmfgraph.GradientFacet;
import org.eclipse.papyrus.gmf.gmfgraph.GridLayout;
import org.eclipse.papyrus.gmf.gmfgraph.GridLayoutData;
import org.eclipse.papyrus.gmf.gmfgraph.Identity;
import org.eclipse.papyrus.gmf.gmfgraph.Insets;
import org.eclipse.papyrus.gmf.gmfgraph.InvisibleRectangle;
import org.eclipse.papyrus.gmf.gmfgraph.Label;
import org.eclipse.papyrus.gmf.gmfgraph.LabelOffsetFacet;
import org.eclipse.papyrus.gmf.gmfgraph.LabeledContainer;
import org.eclipse.papyrus.gmf.gmfgraph.Layout;
import org.eclipse.papyrus.gmf.gmfgraph.LayoutData;
import org.eclipse.papyrus.gmf.gmfgraph.LayoutRef;
import org.eclipse.papyrus.gmf.gmfgraph.Layoutable;
import org.eclipse.papyrus.gmf.gmfgraph.LineBorder;
import org.eclipse.papyrus.gmf.gmfgraph.MarginBorder;
import org.eclipse.papyrus.gmf.gmfgraph.Node;
import org.eclipse.papyrus.gmf.gmfgraph.Pin;
import org.eclipse.papyrus.gmf.gmfgraph.PinOwner;
import org.eclipse.papyrus.gmf.gmfgraph.Point;
import org.eclipse.papyrus.gmf.gmfgraph.Polygon;
import org.eclipse.papyrus.gmf.gmfgraph.PolygonDecoration;
import org.eclipse.papyrus.gmf.gmfgraph.Polyline;
import org.eclipse.papyrus.gmf.gmfgraph.PolylineConnection;
import org.eclipse.papyrus.gmf.gmfgraph.PolylineDecoration;
import org.eclipse.papyrus.gmf.gmfgraph.RGBColor;
import org.eclipse.papyrus.gmf.gmfgraph.RealFigure;
import org.eclipse.papyrus.gmf.gmfgraph.Rectangle;
import org.eclipse.papyrus.gmf.gmfgraph.Rectangle2D;
import org.eclipse.papyrus.gmf.gmfgraph.RoundedRectangle;
import org.eclipse.papyrus.gmf.gmfgraph.SVGFigure;
import org.eclipse.papyrus.gmf.gmfgraph.SVGProperty;
import org.eclipse.papyrus.gmf.gmfgraph.ScalablePolygon;
import org.eclipse.papyrus.gmf.gmfgraph.Shape;
import org.eclipse.papyrus.gmf.gmfgraph.StackLayout;
import org.eclipse.papyrus.gmf.gmfgraph.VerticalLabel;
import org.eclipse.papyrus.gmf.gmfgraph.VisiblePin;
import org.eclipse.papyrus.gmf.gmfgraph.VisualFacet;
import org.eclipse.papyrus.gmf.gmfgraph.XYLayout;
import org.eclipse.papyrus.gmf.gmfgraph.XYLayoutData;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.gmf.gmfgraph.GMFGraphPackage
 * @generated
 */
public class GMFGraphAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GMFGraphPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GMFGraphAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = GMFGraphPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GMFGraphSwitch<Adapter> modelSwitch =
		new GMFGraphSwitch<Adapter>() {
			@Override
			public Adapter caseCanvas(Canvas object) {
				return createCanvasAdapter();
			}
			@Override
			public Adapter caseFigureGallery(FigureGallery object) {
				return createFigureGalleryAdapter();
			}
			@Override
			public Adapter caseIdentity(Identity object) {
				return createIdentityAdapter();
			}
			@Override
			public Adapter caseDiagramElement(DiagramElement object) {
				return createDiagramElementAdapter();
			}
			@Override
			public Adapter caseAbstractNode(AbstractNode object) {
				return createAbstractNodeAdapter();
			}
			@Override
			public Adapter caseNode(Node object) {
				return createNodeAdapter();
			}
			@Override
			public Adapter caseConnection(Connection object) {
				return createConnectionAdapter();
			}
			@Override
			public Adapter caseCompartment(Compartment object) {
				return createCompartmentAdapter();
			}
			@Override
			public Adapter caseDiagramLabel(DiagramLabel object) {
				return createDiagramLabelAdapter();
			}
			@Override
			public Adapter caseVisualFacet(VisualFacet object) {
				return createVisualFacetAdapter();
			}
			@Override
			public Adapter caseGeneralFacet(GeneralFacet object) {
				return createGeneralFacetAdapter();
			}
			@Override
			public Adapter caseAlignmentFacet(AlignmentFacet object) {
				return createAlignmentFacetAdapter();
			}
			@Override
			public Adapter caseGradientFacet(GradientFacet object) {
				return createGradientFacetAdapter();
			}
			@Override
			public Adapter caseLabelOffsetFacet(LabelOffsetFacet object) {
				return createLabelOffsetFacetAdapter();
			}
			@Override
			public Adapter caseDefaultSizeFacet(DefaultSizeFacet object) {
				return createDefaultSizeFacetAdapter();
			}
			@Override
			public Adapter caseFigure(Figure object) {
				return createFigureAdapter();
			}
			@Override
			public Adapter caseAbstractFigure(AbstractFigure object) {
				return createAbstractFigureAdapter();
			}
			@Override
			public Adapter caseFigureDescriptor(FigureDescriptor object) {
				return createFigureDescriptorAdapter();
			}
			@Override
			public Adapter caseChildAccess(ChildAccess object) {
				return createChildAccessAdapter();
			}
			@Override
			public Adapter caseRealFigure(RealFigure object) {
				return createRealFigureAdapter();
			}
			@Override
			public Adapter caseFigureRef(FigureRef object) {
				return createFigureRefAdapter();
			}
			@Override
			public Adapter caseConnectionFigure(ConnectionFigure object) {
				return createConnectionFigureAdapter();
			}
			@Override
			public Adapter caseDecorationFigure(DecorationFigure object) {
				return createDecorationFigureAdapter();
			}
			@Override
			public Adapter caseShape(Shape object) {
				return createShapeAdapter();
			}
			@Override
			public Adapter caseLabel(Label object) {
				return createLabelAdapter();
			}
			@Override
			public Adapter caseLabeledContainer(LabeledContainer object) {
				return createLabeledContainerAdapter();
			}
			@Override
			public Adapter caseVerticalLabel(VerticalLabel object) {
				return createVerticalLabelAdapter();
			}
			@Override
			public Adapter caseRectangle(Rectangle object) {
				return createRectangleAdapter();
			}
			@Override
			public Adapter caseInvisibleRectangle(InvisibleRectangle object) {
				return createInvisibleRectangleAdapter();
			}
			@Override
			public Adapter caseRoundedRectangle(RoundedRectangle object) {
				return createRoundedRectangleAdapter();
			}
			@Override
			public Adapter caseEllipse(Ellipse object) {
				return createEllipseAdapter();
			}
			@Override
			public Adapter casePolyline(Polyline object) {
				return createPolylineAdapter();
			}
			@Override
			public Adapter casePolygon(Polygon object) {
				return createPolygonAdapter();
			}
			@Override
			public Adapter caseScalablePolygon(ScalablePolygon object) {
				return createScalablePolygonAdapter();
			}
			@Override
			public Adapter casePolylineConnection(PolylineConnection object) {
				return createPolylineConnectionAdapter();
			}
			@Override
			public Adapter casePolylineDecoration(PolylineDecoration object) {
				return createPolylineDecorationAdapter();
			}
			@Override
			public Adapter casePolygonDecoration(PolygonDecoration object) {
				return createPolygonDecorationAdapter();
			}
			@Override
			public Adapter caseCustomAttributeOwner(CustomAttributeOwner object) {
				return createCustomAttributeOwnerAdapter();
			}
			@Override
			public Adapter caseCustomClass(CustomClass object) {
				return createCustomClassAdapter();
			}
			@Override
			public Adapter caseCustomAttribute(CustomAttribute object) {
				return createCustomAttributeAdapter();
			}
			@Override
			public Adapter caseFigureAccessor(FigureAccessor object) {
				return createFigureAccessorAdapter();
			}
			@Override
			public Adapter caseCustomFigure(CustomFigure object) {
				return createCustomFigureAdapter();
			}
			@Override
			public Adapter caseCustomDecoration(CustomDecoration object) {
				return createCustomDecorationAdapter();
			}
			@Override
			public Adapter caseCustomConnection(CustomConnection object) {
				return createCustomConnectionAdapter();
			}
			@Override
			public Adapter caseColor(Color object) {
				return createColorAdapter();
			}
			@Override
			public Adapter caseRGBColor(RGBColor object) {
				return createRGBColorAdapter();
			}
			@Override
			public Adapter caseConstantColor(ConstantColor object) {
				return createConstantColorAdapter();
			}
			@Override
			public Adapter caseFont(Font object) {
				return createFontAdapter();
			}
			@Override
			public Adapter caseBasicFont(BasicFont object) {
				return createBasicFontAdapter();
			}
			@Override
			public Adapter casePoint(Point object) {
				return createPointAdapter();
			}
			@Override
			public Adapter caseDimension(Dimension object) {
				return createDimensionAdapter();
			}
			@Override
			public Adapter caseInsets(Insets object) {
				return createInsetsAdapter();
			}
			@Override
			public Adapter caseBorder(Border object) {
				return createBorderAdapter();
			}
			@Override
			public Adapter caseBorderRef(BorderRef object) {
				return createBorderRefAdapter();
			}
			@Override
			public Adapter caseLineBorder(LineBorder object) {
				return createLineBorderAdapter();
			}
			@Override
			public Adapter caseMarginBorder(MarginBorder object) {
				return createMarginBorderAdapter();
			}
			@Override
			public Adapter caseCompoundBorder(CompoundBorder object) {
				return createCompoundBorderAdapter();
			}
			@Override
			public Adapter caseCustomBorder(CustomBorder object) {
				return createCustomBorderAdapter();
			}
			@Override
			public Adapter caseLayoutData(LayoutData object) {
				return createLayoutDataAdapter();
			}
			@Override
			public Adapter caseCustomLayoutData(CustomLayoutData object) {
				return createCustomLayoutDataAdapter();
			}
			@Override
			public Adapter caseGridLayoutData(GridLayoutData object) {
				return createGridLayoutDataAdapter();
			}
			@Override
			public Adapter caseBorderLayoutData(BorderLayoutData object) {
				return createBorderLayoutDataAdapter();
			}
			@Override
			public Adapter caseLayoutable(Layoutable object) {
				return createLayoutableAdapter();
			}
			@Override
			public Adapter caseLayout(Layout object) {
				return createLayoutAdapter();
			}
			@Override
			public Adapter caseLayoutRef(LayoutRef object) {
				return createLayoutRefAdapter();
			}
			@Override
			public Adapter caseCustomLayout(CustomLayout object) {
				return createCustomLayoutAdapter();
			}
			@Override
			public Adapter caseGridLayout(GridLayout object) {
				return createGridLayoutAdapter();
			}
			@Override
			public Adapter caseBorderLayout(BorderLayout object) {
				return createBorderLayoutAdapter();
			}
			@Override
			public Adapter caseFlowLayout(FlowLayout object) {
				return createFlowLayoutAdapter();
			}
			@Override
			public Adapter caseXYLayout(XYLayout object) {
				return createXYLayoutAdapter();
			}
			@Override
			public Adapter caseXYLayoutData(XYLayoutData object) {
				return createXYLayoutDataAdapter();
			}
			@Override
			public Adapter caseStackLayout(StackLayout object) {
				return createStackLayoutAdapter();
			}
			@Override
			public Adapter caseCenterLayout(CenterLayout object) {
				return createCenterLayoutAdapter();
			}
			@Override
			public Adapter caseSVGFigure(SVGFigure object) {
				return createSVGFigureAdapter();
			}
			@Override
			public Adapter caseSVGProperty(SVGProperty object) {
				return createSVGPropertyAdapter();
			}
			@Override
			public Adapter caseRectangle2D(Rectangle2D object) {
				return createRectangle2DAdapter();
			}
			@Override
			public Adapter casePin(Pin object) {
				return createPinAdapter();
			}
			@Override
			public Adapter caseCustomPin(CustomPin object) {
				return createCustomPinAdapter();
			}
			@Override
			public Adapter caseColorPin(ColorPin object) {
				return createColorPinAdapter();
			}
			@Override
			public Adapter caseVisiblePin(VisiblePin object) {
				return createVisiblePinAdapter();
			}
			@Override
			public Adapter casePinOwner(PinOwner object) {
				return createPinOwnerAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Canvas <em>Canvas</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Canvas
	 * @generated
	 */
	public Adapter createCanvasAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.FigureGallery <em>Figure Gallery</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.FigureGallery
	 * @generated
	 */
	public Adapter createFigureGalleryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Identity <em>Identity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Identity
	 * @generated
	 */
	public Adapter createIdentityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.DiagramElement <em>Diagram Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.DiagramElement
	 * @generated
	 */
	public Adapter createDiagramElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.AbstractNode <em>Abstract Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.AbstractNode
	 * @generated
	 */
	public Adapter createAbstractNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Node
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.ChildAccess <em>Child Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.ChildAccess
	 * @generated
	 */
	public Adapter createChildAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.RealFigure <em>Real Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.RealFigure
	 * @generated
	 */
	public Adapter createRealFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Connection
	 * @generated
	 */
	public Adapter createConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Compartment <em>Compartment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Compartment
	 * @generated
	 */
	public Adapter createCompartmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.DiagramLabel <em>Diagram Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.DiagramLabel
	 * @generated
	 */
	public Adapter createDiagramLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.VisualFacet <em>Visual Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.VisualFacet
	 * @generated
	 */
	public Adapter createVisualFacetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.GeneralFacet <em>General Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.GeneralFacet
	 * @generated
	 */
	public Adapter createGeneralFacetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.AlignmentFacet <em>Alignment Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.AlignmentFacet
	 * @generated
	 */
	public Adapter createAlignmentFacetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.GradientFacet <em>Gradient Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.GradientFacet
	 * @generated
	 */
	public Adapter createGradientFacetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.LabelOffsetFacet <em>Label Offset Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.LabelOffsetFacet
	 * @generated
	 */
	public Adapter createLabelOffsetFacetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.DefaultSizeFacet <em>Default Size Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.DefaultSizeFacet
	 * @generated
	 */
	public Adapter createDefaultSizeFacetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Figure <em>Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Figure
	 * @generated
	 */
	public Adapter createFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.FigureDescriptor <em>Figure Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.FigureDescriptor
	 * @generated
	 */
	public Adapter createFigureDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.AbstractFigure <em>Abstract Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.AbstractFigure
	 * @generated
	 */
	public Adapter createAbstractFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.FigureRef <em>Figure Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.FigureRef
	 * @generated
	 */
	public Adapter createFigureRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.ConnectionFigure <em>Connection Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.ConnectionFigure
	 * @generated
	 */
	public Adapter createConnectionFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.DecorationFigure <em>Decoration Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.DecorationFigure
	 * @generated
	 */
	public Adapter createDecorationFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Shape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Shape
	 * @generated
	 */
	public Adapter createShapeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Label
	 * @generated
	 */
	public Adapter createLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.LabeledContainer <em>Labeled Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.LabeledContainer
	 * @generated
	 */
	public Adapter createLabeledContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.VerticalLabel <em>Vertical Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.VerticalLabel
	 * @generated
	 */
	public Adapter createVerticalLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Rectangle <em>Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Rectangle
	 * @generated
	 */
	public Adapter createRectangleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.InvisibleRectangle <em>Invisible Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.InvisibleRectangle
	 * @generated
	 */
	public Adapter createInvisibleRectangleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.RoundedRectangle <em>Rounded Rectangle</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.RoundedRectangle
	 * @generated
	 */
	public Adapter createRoundedRectangleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Ellipse <em>Ellipse</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Ellipse
	 * @generated
	 */
	public Adapter createEllipseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Polyline <em>Polyline</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Polyline
	 * @generated
	 */
	public Adapter createPolylineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Polygon <em>Polygon</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Polygon
	 * @generated
	 */
	public Adapter createPolygonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.ScalablePolygon <em>Scalable Polygon</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.ScalablePolygon
	 * @generated
	 */
	public Adapter createScalablePolygonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.PolylineConnection <em>Polyline Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.PolylineConnection
	 * @generated
	 */
	public Adapter createPolylineConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.PolylineDecoration <em>Polyline Decoration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.PolylineDecoration
	 * @generated
	 */
	public Adapter createPolylineDecorationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.PolygonDecoration <em>Polygon Decoration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.PolygonDecoration
	 * @generated
	 */
	public Adapter createPolygonDecorationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CustomAttributeOwner <em>Custom Attribute Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CustomAttributeOwner
	 * @generated
	 */
	public Adapter createCustomAttributeOwnerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CustomClass <em>Custom Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CustomClass
	 * @generated
	 */
	public Adapter createCustomClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CustomFigure <em>Custom Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CustomFigure
	 * @generated
	 */
	public Adapter createCustomFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CustomDecoration <em>Custom Decoration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CustomDecoration
	 * @generated
	 */
	public Adapter createCustomDecorationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CustomConnection <em>Custom Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CustomConnection
	 * @generated
	 */
	public Adapter createCustomConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Color <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Color
	 * @generated
	 */
	public Adapter createColorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.RGBColor <em>RGB Color</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.RGBColor
	 * @generated
	 */
	public Adapter createRGBColorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.ConstantColor <em>Constant Color</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.ConstantColor
	 * @generated
	 */
	public Adapter createConstantColorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Font <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Font
	 * @generated
	 */
	public Adapter createFontAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.BasicFont <em>Basic Font</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.BasicFont
	 * @generated
	 */
	public Adapter createBasicFontAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Point
	 * @generated
	 */
	public Adapter createPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Dimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Dimension
	 * @generated
	 */
	public Adapter createDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Insets <em>Insets</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Insets
	 * @generated
	 */
	public Adapter createInsetsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Border <em>Border</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Border
	 * @generated
	 */
	public Adapter createBorderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.BorderRef <em>Border Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.BorderRef
	 * @generated
	 */
	public Adapter createBorderRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.LineBorder <em>Line Border</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.LineBorder
	 * @generated
	 */
	public Adapter createLineBorderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.MarginBorder <em>Margin Border</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.MarginBorder
	 * @generated
	 */
	public Adapter createMarginBorderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CompoundBorder <em>Compound Border</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CompoundBorder
	 * @generated
	 */
	public Adapter createCompoundBorderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CustomBorder <em>Custom Border</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CustomBorder
	 * @generated
	 */
	public Adapter createCustomBorderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.LayoutData <em>Layout Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.LayoutData
	 * @generated
	 */
	public Adapter createLayoutDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CustomLayoutData <em>Custom Layout Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CustomLayoutData
	 * @generated
	 */
	public Adapter createCustomLayoutDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.GridLayoutData <em>Grid Layout Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.GridLayoutData
	 * @generated
	 */
	public Adapter createGridLayoutDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.BorderLayoutData <em>Border Layout Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.BorderLayoutData
	 * @generated
	 */
	public Adapter createBorderLayoutDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Layoutable <em>Layoutable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Layoutable
	 * @generated
	 */
	public Adapter createLayoutableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Layout <em>Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Layout
	 * @generated
	 */
	public Adapter createLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.LayoutRef <em>Layout Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.LayoutRef
	 * @generated
	 */
	public Adapter createLayoutRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CustomLayout <em>Custom Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CustomLayout
	 * @generated
	 */
	public Adapter createCustomLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.GridLayout <em>Grid Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.GridLayout
	 * @generated
	 */
	public Adapter createGridLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.BorderLayout <em>Border Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.BorderLayout
	 * @generated
	 */
	public Adapter createBorderLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CustomAttribute <em>Custom Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CustomAttribute
	 * @generated
	 */
	public Adapter createCustomAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.FigureAccessor <em>Figure Accessor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.FigureAccessor
	 * @generated
	 */
	public Adapter createFigureAccessorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.FlowLayout <em>Flow Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.FlowLayout
	 * @generated
	 */
	public Adapter createFlowLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.XYLayout <em>XY Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.XYLayout
	 * @generated
	 */
	public Adapter createXYLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.XYLayoutData <em>XY Layout Data</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.XYLayoutData
	 * @generated
	 */
	public Adapter createXYLayoutDataAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.StackLayout <em>Stack Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.StackLayout
	 * @generated
	 */
	public Adapter createStackLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CenterLayout <em>Center Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CenterLayout
	 * @generated
	 */
	public Adapter createCenterLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.SVGFigure <em>SVG Figure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.SVGFigure
	 * @generated
	 */
	public Adapter createSVGFigureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.SVGProperty <em>SVG Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.SVGProperty
	 * @generated
	 */
	public Adapter createSVGPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Rectangle2D <em>Rectangle2 D</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Rectangle2D
	 * @generated
	 */
	public Adapter createRectangle2DAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.Pin <em>Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.Pin
	 * @generated
	 */
	public Adapter createPinAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.CustomPin <em>Custom Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.CustomPin
	 * @generated
	 */
	public Adapter createCustomPinAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.ColorPin <em>Color Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.ColorPin
	 * @generated
	 */
	public Adapter createColorPinAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.VisiblePin <em>Visible Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.VisiblePin
	 * @generated
	 */
	public Adapter createVisiblePinAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.gmf.gmfgraph.PinOwner <em>Pin Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.gmf.gmfgraph.PinOwner
	 * @generated
	 */
	public Adapter createPinOwnerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //GMFGraphAdapterFactory
