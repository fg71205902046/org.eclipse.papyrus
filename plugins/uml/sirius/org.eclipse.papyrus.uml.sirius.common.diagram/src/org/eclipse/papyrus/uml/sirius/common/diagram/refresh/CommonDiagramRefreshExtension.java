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
package org.eclipse.papyrus.uml.sirius.common.diagram.refresh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.helper.SiriusDiagramHelper;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;

/**
 * 
 * TODO : really used ?
 *
 */
public class CommonDiagramRefreshExtension implements IRefreshExtension {

	/**
	 * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
	 */
	@Override
	public void beforeRefresh(DDiagram diagram) {
//	System.out.println(diagram.toString());
	}

	/**
	 * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
	 */
	@Override
	public void postRefresh(DDiagram diagram) {
//		System.out.println(diagram.toString());
		//		drawCommonbendpoints(diagram.description().getLayout().)
//		Sirius
	}

	/**
	 *
	 * @param g
	 *
	 */
	protected void drawCommonbendpoints(final Graphics g) {
		//		if (getPoints().size() > 2 && getDrawBendpointPreferenceValue()) {
//			final Collection<Point> commonPoints = getCommonBendpointsToDraw();
//			drawCommonBendpoints(g, commonPoints);
//		}
	}

	/**
	 *
	 * @return the common bendpoints to draw (see bug
	 *         https://bugs.eclipse.org/bugs/show_bug.cgi?id=424099)
	 */
	@SuppressWarnings("unchecked")
//	public Collection<Point> getCommonBendpointsToDraw() {
//		final Collection<Point> commonBendpointsToDraw = new HashSet<>();
//		final PointList bendPoints = getPoints();
//
//		final Map<?, ?> visualPartMap = getVisualPartMap(this);
//		if (visualPartMap != null) {
//			final EditPart currentEditPart = (EditPart) visualPartMap.get(this);
//			// 1. find the uml eClass of the current link
//			EClass eClass = null;
//			if (currentEditPart instanceof IGraphicalEditPart) {
//				final EObject el = ((IGraphicalEditPart) currentEditPart).resolveSemanticElement();
//				if (el != null) {
//					eClass = el.eClass();
//				}
//			}
//
//			// 2. find all connections editpart with same source AND/OR same target than the
//			// current one
//			if (currentEditPart instanceof ConnectionEditPart && eClass != null) {
//				final EditPart sourceEP = ((ConnectionEditPart) currentEditPart).getSource();
//				final EditPart targetEP = ((ConnectionEditPart) currentEditPart).getTarget();
//				final Set<Object> allConnectionsEP = new HashSet<>();
//				// 2.1. get all potential editpart connections
//				if (sourceEP instanceof AbstractGraphicalEditPart && targetEP instanceof AbstractGraphicalEditPart) {
//					allConnectionsEP.addAll(((AbstractGraphicalEditPart) sourceEP).getSourceConnections());
//					allConnectionsEP.addAll(((AbstractGraphicalEditPart) targetEP).getSourceConnections());
//					allConnectionsEP.addAll(((AbstractGraphicalEditPart) sourceEP).getTargetConnections());
//					allConnectionsEP.addAll(((AbstractGraphicalEditPart) targetEP).getTargetConnections());
//					allConnectionsEP.remove(currentEditPart);
//				}
//
//				// 2.2 get the figure for these connections and keep only figure when its
//				// editpart has the same kind than the current one
//				final Set<Connection> allConnections = new HashSet<>();
//				if (allConnectionsEP.size() > 0) {
//					for (final Object current : allConnectionsEP) {
//						// the editpart be instance of the same class
//						if (current.getClass().isInstance(currentEditPart)
//								&& currentEditPart.getClass().isInstance(current)
//								&& current instanceof IGraphicalEditPart) {
//							final EObject resolvedElement = ((IGraphicalEditPart) current).resolveSemanticElement();
//							if (resolvedElement != null && eClass != null && resolvedElement.eClass() != eClass) {
//								continue;// we draw ben point only for elements which have the same eClass, when this
//											// eClass is not null
//							}
//							final IFigure currentFig = ((IGraphicalEditPart) current).getFigure();
//							if (currentFig instanceof Connection) {
//								allConnections.add((Connection) currentFig);
//							}
//						}
//					}
//				}
//
//				// 3. Create the list of the LineSeg of the current figure
//				final List<LineSeg> refs = new ArrayList<>();
//				for (int i = 0; i < bendPoints.size() - 1; i++) {
//					LineSeg seg = new LineSeg(bendPoints.getPoint(i), bendPoints.getPoint(i + 1));
//					refs.add(seg);
//				}
//
//				// 4. find common segments between the current figure and each others link
//				// we need to associate each common segment to the concerned link
//				final Map<Connection, Map<LineSeg, List<LineSeg>>> segs = new HashMap<>();
//				for (Connection currentConn : allConnections) {
//					final PointList currentPoints = currentConn.getPoints();
//					final Map<LineSeg, List<LineSeg>> mapSegs = new HashMap<>();
//					segs.put(currentConn, mapSegs);
//					for (LineSeg refSeg : refs) {
//						final List<LineSeg> commonSubSegs = new ArrayList<>();
//						mapSegs.put(refSeg, commonSubSegs);
//						for (int i = 0; i < currentPoints.size() - 1; i++) {
//							LineSeg tmp = new LineSeg(currentPoints.getPoint(i), currentPoints.getPoint(i + 1));
//							PointList intersection = getCommonSegment(refSeg, tmp);
//							if (intersection.size() == 2) {
//								if (!intersection.getFirstPoint().equals(intersection.getLastPoint())) {
//									double distanceFromFirst = refSeg.getOrigin()
//											.getDistance(intersection.getFirstPoint());
//									double distanceFromSecond = refSeg.getOrigin()
//											.getDistance(intersection.getLastPoint());
//									final LineSeg commonSeg;
//									// we arrange the 2 points in order to have the first point nearest of the start
//									// of the current segment
//									if (distanceFromFirst < distanceFromSecond) {
//										commonSeg = new LineSeg(intersection.getFirstPoint(),
//												intersection.getLastPoint());
//									} else {
//										commonSeg = new LineSeg(intersection.getLastPoint(),
//												intersection.getFirstPoint());
//									}
//									commonSubSegs.add(commonSeg);
//								}
//							}
//						}
//					}
//				}
//
//				// 5. we look for the bendpoints crossing existing link on the model, then
//				// crossing their common segment with the current figure, to find bendpoints to
//				// draw
//				for (Entry<Connection, Map<LineSeg, List<LineSeg>>> entry : segs.entrySet()) {
//					final Map<LineSeg, List<LineSeg>> commonSegMap = entry.getValue();
//					for (int i = 0; i < refs.size(); i++) { // we iterate on the segments of the current figure
//
//						// 5.1 find required values to find bendpoints to draw
//						final LineSeg currentFigureSeg = refs.get(i);
//						// final List<LineSeg> currentCommonSegs = segs.get(currentFigureSeg);
//						final List<LineSeg> currentCommonSegs = commonSegMap.get(currentFigureSeg);
//
//						final LineSeg previousSeg;
//						final List<LineSeg> previousCommonSegs;
//
//						final LineSeg nextSeg;
//						final List<LineSeg> nextCommonSegs;
//
//						// obtain previous segs of the current figure
//						if (i != 0) {
//							previousSeg = refs.get(i - 1);
//							previousCommonSegs = commonSegMap.get(previousSeg);
//						} else {
//							previousSeg = null;
//							previousCommonSegs = null;
//						}
//
//						// obtain next segs of the current figure
//						if (i != refs.size() - 1) {
//							nextSeg = refs.get(i + 1);
//							nextCommonSegs = commonSegMap.get(nextSeg);
//						} else {
//							nextSeg = null;
//							nextCommonSegs = null;
//						}
//
//						LineSeg previousCommonSeg = null;
//						LineSeg nextCommonSeg = null;
//
//						// we iterate on the common subsegment shared with others link with the current
//						// figure
//						for (int a = 0; a < currentCommonSegs.size(); a++) {
//							final LineSeg curr = currentCommonSegs.get(a);
//							final Point first = curr.getOrigin();
//							final Point second = curr.getTerminus();
//							// obtain previous common seg
//							if (a == 0) {
//								if (previousCommonSegs != null && previousCommonSegs.size() > 0) {
//									previousCommonSeg = previousCommonSegs.get(previousCommonSegs.size() - 1);
//								} else {
//									previousCommonSeg = null;
//								}
//							} else {
//								previousCommonSeg = currentCommonSegs.get(a - 1);
//							}
//
//							// obtain next common seg
//							if (a == currentCommonSegs.size() - 1) {
//								if (nextCommonSegs != null && nextCommonSegs.size() > 0) {
//									nextCommonSeg = nextCommonSegs.get(0);
//								} else {
//									nextCommonSeg = null;
//								}
//							} else {
//								nextCommonSeg = currentCommonSegs.get(a + 1);
//							}
//
//							// 5.2 calculates bendpoints visibility
//
//							// determining if we draw first point :
//							if (previousCommonSeg == null) {
//								if (i == 0) {// first segment of the figure
//									if (!bendPoints.getFirstPoint().equals(first)) {
//										// we draw the point when it is not the first anchor of the figure
//										commonBendpointsToDraw.add(first);
//									}
//								} else {
//									commonBendpointsToDraw.add(first);
//								}
//							} else if (!previousCommonSeg.getTerminus().equals(first)) {
//								// the previous common seg doesn't share this point with the current segment
//								// we draw the first point
//								commonBendpointsToDraw.add(first);
//							}
//
//							// determining if we draw the second point
//							if (nextCommonSeg == null) {
//								if (i == refs.size() - 1) {
//									if (!bendPoints.getLastPoint().equals(second)) {
//										// we draw the point when it is not the first anchor of the figure
//										commonBendpointsToDraw.add(second);
//									}
//								} else {
//									commonBendpointsToDraw.add(second);
//								}
//							} else if (!nextCommonSeg.getOrigin().equals(second)) {
//								// the next common seg doesn't share this point with the current segment
//								// we draw the second point
//								commonBendpointsToDraw.add(second);
//							}
//						}
//					}
//				}
//			}
//		}
//
//		return commonBendpointsToDraw;
//	}

	/**
	 *
	 * @param seg1 the first segment
	 * @param seg2 the secong segment
	 * @return
	 */
	public static final PointList getCommonSegment(final LineSeg seg1, final LineSeg seg2) {
		final List<Point> list = new ArrayList<>();
		list.add(seg1.getOrigin());
		list.add(seg2.getOrigin());
		list.add(seg1.getTerminus());
		list.add(seg2.getTerminus());

		List<Point> commonPoints = new ArrayList<>();
		for (Point point : list) {
			if (!commonPoints.contains(point)) {
				if (seg1.containsPoint(point, 0) && seg2.containsPoint(point, 0)) {
					commonPoints.add(point);
				}
			}
		}

		final PointList result = new PointList();
		for (final Point point : commonPoints) {
			result.addPoint(point);
		}
		return result;
	}
}
