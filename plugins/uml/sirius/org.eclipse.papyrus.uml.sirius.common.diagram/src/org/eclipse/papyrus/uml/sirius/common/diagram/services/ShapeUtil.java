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
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Initial API and others
 *****************************************************************************/
package org.eclipse.papyrus.uml.sirius.common.diagram.services;

import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.color.RGBValuesProvider;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IEditorPart;

/**
 * Utility class to handle style, size and location
 */
public class ShapeUtil {

  /*************************** DRAW2D BASED ********************************/
  /*************************** GMF BASED ********************************/

  /**
   * Retrieve the GraphicalEditPart of a Node
   * 
   * @param currentNode
   * @return
   */
  public static GraphicalEditPart getEditPart(View currentNode) {
    GraphicalEditPart ret = null;

    final View sourceNode = currentNode;
    final IEditorPart editor = EclipseUIUtil.getActiveEditor();

    if ((sourceNode != null) && (editor instanceof DiagramEditor)) {

      final Map<?, ?> editPartRegistry = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
      // TODO : The edit part of edge => return the parent
      final GraphicalEditPart targetEditPart = (GraphicalEditPart) editPartRegistry.get(sourceNode);
      if (targetEditPart != null) {
        ret = targetEditPart;
      }
    }

    return ret;
  }

  /**
   * Get the Location of the currentNode (GMF Way)
   * 
   * @param currentNode
   * @return the point Location width of the currentNode
   */
  public static Point getLocation(Node currentNode) {
    return new Point(GMFNotationHelper.getX(currentNode), GMFNotationHelper.getY(currentNode));
  }

  /**
   * Absolute Location of Node in diagram
   * 
   * @param currentNode
   * @return
   */
  public static Point getLocationDRAW2D(Node currentNode) {
    int iX = -1;
    int iY = -1;
    Point location = null;
    GraphicalEditPart gep = getEditPart(currentNode);
    if (gep != null) {
      iX = gep.getFigure().getBounds().x;
      iY = gep.getFigure().getBounds().y;
      location = new Point(iX, iY);
    }
    return location;
  }

  /**
   * get the color of the node
   * 
   * @param node
   * @return get the color of the node
   */
  public static RGBValues getNodeColorStyle(DNode node) {

    NodeStyle shape = node.getOwnedStyle();
    if (shape instanceof Ellipse) {
      return ((Ellipse) shape).getColor();
    } else if (shape instanceof Square) {
      return ((Square) shape).getColor();
    }
    return null;
  }

  /**
   * Get the height of the currentNode (GMF Way)
   * 
   * @param currentNode
   * @return Get the height of the currentNode
   */
  public static int getNodeHeight(Node currentNode) {

    int height = GMFNotationHelper.getHeight(currentNode);

    // If the Node is AutoSized
    if (height == -1) {
      // Get the height from Draw2D base
      height = getNodeHeightDRAW2D(currentNode);
    }

    return height;
  }

  /**
   * Get the height of the currentNode (Draw2D Way)
   * 
   * @param currentNode
   * @return Get the height of the currentNode
   */
  public static int getNodeHeightDRAW2D(Node currentNode) {
    int iHeight = -1;
    GraphicalEditPart gep = getEditPart(currentNode);
    if (gep != null) {
      iHeight = gep.getFigure().getBounds().height;
    }
    return iHeight;
  }

  /**
   * Get the width of the currentNode (GMF Way)
   * 
   * @param currentNode
   * @return Get the width of the currentNode
   */
  public static int getNodeWidth(Node currentNode) {

    int width = GMFNotationHelper.getWidth(currentNode);

    // If the Node is AutoSized
    if (width == -1) {
      // Get the width from Draw2D base
      width = getNodeWidthDRAW2D(currentNode);
    }
    return width;
  }

  /**
   * Get the width of the currentNode (Draw2D Way)
   * 
   * @param currentNode
   * @return Get the height of the currentNode
   */
  public static int getNodeWidthDRAW2D(Node currentNode) {
    int iWidth = -1;

    GraphicalEditPart gep = getEditPart(currentNode);
    if (gep != null) {
      iWidth = gep.getFigure().getBounds().width;
    }

    return iWidth;
  }

  /**
   * Indicate if the icon label is shown on currentNode
   * 
   * @param currentNode
   * @return true if he icon label is shown on currentNode, false otherwise
   */
  @SuppressWarnings("deprecation")
  public static boolean isLabelShowIcon(Node currentNode) {
    boolean isShown = true;
    DNodeContainer container = (DNodeContainer) currentNode.getElement();
    if (container != null) {
      isShown = container.getOwnedStyle().isShowIcon();
    }
    return isShown;
  }

  /*************************** SIRIUS BASED ********************************/

  /**
   * Retrieve the GMF element of a Sirius Node from a GMF diagram
   * 
   * @param Diagram
   *          diagram
   * @param DDiagramElement
   *          siriusNode
   * @return the GMF element Node
   */
  public static View retrieveNode(Diagram diagram, DDiagramElement siriusNode) {
    View ret = null;

    if (siriusNode instanceof DEdge) {
      for (Object oIte : diagram.getPersistedEdges()) {
        if (oIte instanceof Edge) {
          Edge edgeIte = (Edge) oIte;
          if (edgeIte.getElement() == siriusNode) {
            ret = edgeIte;
            break;
          }
        }
      }
    } else if ((siriusNode instanceof DNodeContainer) || (siriusNode instanceof DNode)) {
      ret = retrieveNodeRec(diagram, siriusNode);
    }

    return ret;
  }

  /**
   * Retrieve (recursive way) the GMF Node of a Sirius Node from a GMF anchor
   * 
   * @param anchor
   * @param siriusNode
   * @return
   */
  private static Node retrieveNodeRec(View anchor, DDiagramElement siriusNode) {
    Node ret = null;

    for (Object oIte : anchor.getPersistedChildren()) {
      if (oIte instanceof Node) {
        Node nodeIte = (Node) oIte;
        if (nodeIte.getElement() == siriusNode) {
          ret = nodeIte;
          break;
        }
        Node node = retrieveNodeRec(nodeIte, siriusNode);
        if (node != null) {
          ret = node;
          break;
        }
      }
    }
    return ret;
  }

  /**
   * Set the Default Font color of label of the currentNode (GMF Way)
   * 
   * @param currentNode.
   * @param color.
   * @return
   */
  public static void setDefaultFont(View currentNode) {

    GraphicalEditPart gep = getEditPart(currentNode);

    Object preferredValue = gep.getPreferredValue(NotationPackage.eINSTANCE.getFontStyle_FontColor());

    RGB color = null;
    if (preferredValue instanceof Integer) {
      color = FigureUtilities.integerToRGB((Integer) preferredValue);
    }

    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class),
        NotationPackage.eINSTANCE.getFontStyle_FontColor(), FigureUtilities.RGBToInteger(color));

    preferredValue = gep.getPreferredValue(NotationPackage.eINSTANCE.getFontStyle_FontHeight());

    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class),
        NotationPackage.eINSTANCE.getFontStyle_FontHeight(), (Integer) preferredValue);

    preferredValue = gep.getPreferredValue(NotationPackage.eINSTANCE.getFontStyle_Bold());

    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class), NotationPackage.eINSTANCE.getFontStyle_Bold(),
        (Boolean) preferredValue);
  }


  /**
   * Set the thickness format Edge
   * 
   * @param DEdge
   *          currentNode
   * @param Color
   * @return
   */
  public static boolean setEdgeThickStyle(DEdge currentEdge, Integer pThick) {
    if ((currentEdge != null) && (pThick != null)) {
      EdgeStyle edgeStyle = currentEdge.getOwnedStyle();
      if (edgeStyle.getSize().intValue() != pThick.intValue()) {
        edgeStyle.setSize(pThick);
        ShapeUtil.addCustomisation(edgeStyle, new EStructuralFeature[] { DiagramPackage.Literals.EDGE_STYLE__SIZE });
        getStyleHelper(currentEdge).refreshStyle(edgeStyle);
        return true;
      }
    }
    return false;
  }

  /**
   * Set the thickness format Edge
   * 
   * @param DEdge
   *          currentNode
   * @param Color
   * @return
   */
  public static boolean resetEdgeThickStyle(DEdge currentEdge, Integer pThick) {
    if ((currentEdge != null) && (pThick != null)) {
      EdgeStyle edgeStyle = currentEdge.getOwnedStyle();
      if (edgeStyle.getSize().intValue() != pThick.intValue()) {
        edgeStyle.setSize(pThick);
        ShapeUtil.removeCustomisation(edgeStyle, new EStructuralFeature[] { DiagramPackage.Literals.EDGE_STYLE__SIZE });
        getStyleHelper(currentEdge).refreshStyle(edgeStyle);
        return true;
      }
    }
    return false;
  }

  /**
   * Set the Font color of label of the currentNode (GMF Way)
   * 
   * @param currentNode.
   * @param color.
   * @return
   */
  public static void setFontColor(View currentNode, RGB color) {

    GraphicalEditPart gep = getEditPart(currentNode);

    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class),
        NotationPackage.eINSTANCE.getFontStyle_FontColor(), FigureUtilities.RGBToInteger(color));

    gep.refresh();

  }

  /**
   * Set the abscissa and ordinate location of the currentNode (GMF Way)
   * 
   * @param currentNode
   * @param pX
   * @param pY
   * @return
   */
  public static void setLocation(Node currentNode, int pX, int pY) {

    GraphicalEditPart gep = getEditPart(currentNode);

    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class), NotationPackage.eINSTANCE.getLocation_X(),
        Integer.valueOf(pX));
    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class), NotationPackage.eINSTANCE.getLocation_Y(),
        Integer.valueOf(pY));

    gep.refresh();
  }

  /**
   * Set the point location of the currentNode (GMF Way)
   * 
   * @param currentNode
   * @param Point
   * @return
   */
  public static void setLocation(Node currentNode, Point pPoint) {
    GraphicalEditPart gep = getEditPart(currentNode);
    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class), NotationPackage.eINSTANCE.getLocation_X(),
        Integer.valueOf(pPoint.x));
    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class), NotationPackage.eINSTANCE.getLocation_Y(),
        Integer.valueOf(pPoint.y));
    gep.refresh();
  }



  /**
   * Set the border format Node
   * 
   * @param DNodeContainer
   *          currentNode
   * @param Color
   * @return
   */
  public static boolean setBorderStyle(AbstractDNode currentNode, Integer pThick) {
    if ((currentNode != null) && (pThick != null)) {
      Style style = ShapeUtil.getCurrentStyle(currentNode);
      if (style instanceof BorderedStyle) {
        BorderedStyle bStyle = (BorderedStyle) style;
        if (!bStyle.getBorderSize().equals(pThick)) {
          bStyle.setBorderSize(pThick);
          ShapeUtil.addCustomisation(bStyle,
              new EStructuralFeature[] { DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE });
          getStyleHelper(currentNode).refreshStyle(bStyle);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Set the border format Node
   * 
   * @param DNodeContainer
   *          currentNode
   * @param Color
   * @return
   */
  public static boolean resetBorderStyle(AbstractDNode currentNode, Integer pThick) {
    if ((currentNode != null) && (pThick != null)) {
      Style style = ShapeUtil.getCurrentStyle(currentNode);
      if (style instanceof BorderedStyle) {
        BorderedStyle bStyle = (BorderedStyle) style;
        if (!bStyle.getBorderSize().equals(pThick)) {
          bStyle.setBorderSize(pThick);
          ShapeUtil.removeCustomisation(bStyle,
              new EStructuralFeature[] { DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE });
          getStyleHelper(currentNode).refreshStyle(bStyle);
          return true;
        }
      }
    }
    return false;
  }


  public static Style getCurrentStyle(DDiagramElement element) {
    if (element instanceof DNodeContainer) {
      return ((DNodeContainer) element).getOwnedStyle();

    } else if (element instanceof DNodeList) {
      return ((DNodeList) element).getOwnedStyle();

    } else if (element instanceof DNode) {
      return ((DNode) element).getOwnedStyle();

    } else if (element instanceof DEdge) {
      return ((DEdge) element).getOwnedStyle();
    }
    return null;
  }

  /**
   * Set the height of the currentNode (GMF Way)
   * 
   * @param currentNode
   * @return
   */
  public static void setNodeHeight(Node currentNode, int pHeight) {
    GraphicalEditPart gep = getEditPart(currentNode);
    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class), NotationPackage.eINSTANCE.getSize_Height(),
        Integer.valueOf(pHeight));
    gep.refresh();
  }

  /**
   * Set the width of the currentNode (GMF Way)
   * 
   * @param currentNode
   * @return
   */
  public static void setNodeWidth(Node currentNode, int pWidth) {
    GraphicalEditPart gep = getEditPart(currentNode);
    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class), NotationPackage.eINSTANCE.getSize_Width(),
        Integer.valueOf(pWidth));
    gep.refresh();
  }

  /**
   * Set the color to the shape representation Node
   * 
   * @param Node
   *          currentNode
   * @param color
   */
  public static void setShapeColor(Node currentNode, RGB color) {
    GraphicalEditPart gep = getEditPart(currentNode);

    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class),
        NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(color));

    gep.refresh();
  }

  /**
   * Set the abscissa point location of the currentNode (GMF Way)
   * 
   * @param currentNode
   * @param pX
   * @return
   */
  public static void setXLocation(Node currentNode, int pX) {
    GraphicalEditPart gep = getEditPart(currentNode);
    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class), NotationPackage.eINSTANCE.getLocation_X(),
        Integer.valueOf(pX));
  }

  /**
   * Set the ordinate point location of the currentNode (GMF Way)
   * 
   * @param currentNode
   * @param pY
   * @return
   */
  public static void setYLocation(Node currentNode, int pY) {
    GraphicalEditPart gep = getEditPart(currentNode);
    ViewUtil.setStructuralFeatureValue((View) gep.getAdapter(View.class), NotationPackage.eINSTANCE.getLocation_X(),
        Integer.valueOf(pY));
    gep.refresh();
  }

  /**
   * @param aEdge
   * @param desc
   * @return
   */
  public static RGB getDefaultColor(DSemanticDecorator aEdge, EObject desc, ColorDescription descColor) {
    RGBValues color = null;
    if (descColor != null) {
      RGBValuesProvider colors = new RGBValuesProvider();
      if (descColor instanceof FixedColor) {
        color = colors.getRGBValues((FixedColor) descColor);

      } else if (descColor instanceof ComputedColor) {
        color = colors.getRGBValues((ComputedColor) descColor, aEdge.getTarget(),
            InterpreterUtil.getInterpreter(aEdge.getTarget()));

      } else if (descColor instanceof InterpolatedColor) {
        color = colors.getRGBValues((InterpolatedColor) descColor, aEdge.getTarget(),
            InterpreterUtil.getInterpreter(aEdge.getTarget()));
      }
    }

    if (color == null) {
      return new RGB(0, 0, 0);
    }
    return new RGB(color.getRed(), color.getGreen(), color.getBlue());

  }

  /**
   * @param element
   * @param targetView
   */
  public static void copyCustomStyle(DDiagramElement sourceElement, DDiagramElement targetElement) {
    EObject sourceStyle = null;
    // EObject targetStyle = null;

    if ((sourceElement instanceof DNodeContainer) && (targetElement instanceof DNodeContainer)) {
      DNodeContainer srcElement = (DNodeContainer) sourceElement;
      DNodeContainer tgtElement = (DNodeContainer) targetElement;
      sourceStyle = srcElement.getOwnedStyle();
      // targetStyle = tgtElement.getOwnedStyle();

      EObject style = EcoreUtil.copy(sourceStyle);
      tgtElement.setOwnedStyle((ContainerStyle) style);

    } else if ((sourceElement instanceof DNode) && (targetElement instanceof DNode)) {
      DNode srcElement = (DNode) sourceElement;
      DNode tgtElement = (DNode) targetElement;
      sourceStyle = srcElement.getOwnedStyle();
      // targetStyle = tgtElement.getOwnedStyle();

      EObject style = EcoreUtil.copy(sourceStyle);
      tgtElement.setOwnedStyle((NodeStyle) style);

    } else if ((sourceElement instanceof DEdge) && (targetElement instanceof DEdge)) {
      DEdge srcElement = (DEdge) sourceElement;
      DEdge tgtElement = (DEdge) targetElement;
      sourceStyle = srcElement.getOwnedStyle();
      // targetStyle = tgtElement.getOwnedStyle();

      EObject style = EcoreUtil.copy(sourceStyle);
      tgtElement.setOwnedStyle((EdgeStyle) style);
    }
  }

  private static StyleHelper getStyleHelper(DSemanticDecorator semanticDecorator) {
    return new StyleHelper(
        SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticDecorator.getTarget()));
  }

  /**
   * API changes
   */
  public static void removeCustomisation(Style style, EStructuralFeature[] features) {
    for (EStructuralFeature feature : features) {
      style.getCustomFeatures().remove(feature.getName());
    }
  }

  public static boolean isCustomisation(Style style, EStructuralFeature feature) {
    for (String name : style.getCustomFeatures()) {
      if (name.equals(feature.getName())) {
        return true;
      }
    }
    return false;
  }

  public static void addCustomisation(Style style, EStructuralFeature[] features) {
    for (EStructuralFeature feature : features) {
      style.getCustomFeatures().add(feature.getName());
    }
  }

  /**
   * API changes
   */
  public static void setCustom(Style style, boolean value) {
    if (!value) {
      style.getCustomFeatures().clear();
    }
  }

  /**
   * API changes
   */
  public static boolean isCustom(Style style) {
    return !style.getCustomFeatures().isEmpty();
  }

}
