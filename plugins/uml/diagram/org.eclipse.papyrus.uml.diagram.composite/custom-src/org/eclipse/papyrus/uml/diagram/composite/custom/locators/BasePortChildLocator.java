/*****************************************************************************
 * Copyright (c) 2015 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.uml.diagram.composite.custom.locators;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.papyrus.infra.gmfdiag.common.figure.node.SelectableBorderedNodeFigure;
import org.eclipse.papyrus.uml.diagram.composite.custom.figures.PortFigure;

public abstract class BasePortChildLocator implements IBorderItemLocator {

	protected final PortFigure myPort;

	public BasePortChildLocator(PortFigure port) {
		myPort = port;
	}

	protected int getPortSide() {
		Rectangle parent = getParentBounds();
		Rectangle port = myPort.getBounds();

		int yPortLocation = port.y + port.height / 2;
		int xPortLocation = port.x + port.width / 2;

		int side = 0;
		;
		if (yPortLocation == parent.y) {
			side = side | PositionConstants.NORTH;
		}
		if (yPortLocation == parent.y + parent.height) {
			side = side | PositionConstants.SOUTH;
		}
		if (xPortLocation == parent.x) {
			side = side | PositionConstants.WEST;
		}
		if (xPortLocation == parent.x + parent.width) {
			side = side | PositionConstants.EAST;
		}

		//side == 0 the port located not on the board of the parent
		return side == 0 ? PositionConstants.NORTH : side;
	}

	private Rectangle getParentBounds() {
		IFigure parent = myPort.getParent();
		while (false == parent instanceof SelectableBorderedNodeFigure && parent != null) {
			parent = parent.getParent();
		}
		return ((SelectableBorderedNodeFigure)parent).getMainFigure().getBounds();
	}

	@Override
	public void setConstraint(Rectangle constraint) {
	}

	@Override
	public Rectangle getValidLocation(Rectangle proposedLocation, IFigure borderItem) {
		return null;
	}

	@Override
	public int getCurrentSideOfParent() {
		return 0;
	}
}