/*****************************************************************************
 * Copyright (c) 2017 CEA LIST, ALL4TEC and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Mickaël ADAM (ALL4TEC) mickael.adam@all4tec.net - Initial API and implementation
 *****************************************************************************/

package org.eclipse.papyrus.infra.gmfdiag.common.decoration;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;

/**
 * The <i>solid_diamond_filled</i> connection decoration.
 * 
 * @author Mickaël ADAM
 * @since 3.1
 */
public class SolidDiamondFilledConnectionDecoration extends PolygonDecoration {

	/** Default X scale value. */
	private static final int DEFAULT_SCALE_X = 12;

	/** Default Y scale value. */
	private static final int DEFAULT_SCALE_Y = 6;

	/** The decoration list point */
	private static PointList decorationPointList = new PointList();

	static {
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(-1, 1);
		decorationPointList.addPoint(-2, 0);
		decorationPointList.addPoint(-1, -1);
	}

	/**
	 * Constructor.
	 */
	public SolidDiamondFilledConnectionDecoration() {
		init();
	}

	/**
	 * Initialize the decoration.
	 */
	protected void init() {
		setTemplate(decorationPointList);
		setScale(DEFAULT_SCALE_X, DEFAULT_SCALE_Y);
		setFill(true);
	}

	/**
	 * <pre>
	 * Overridden to take into account of the line with in the scale (better representation).
	 * </pre>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.draw2d.Polyline#setLineWidth(int)
	 */
	@Override
	public void setLineWidth(final int w) {
		setScale(DEFAULT_SCALE_X + w, DEFAULT_SCALE_Y + w);
		super.setLineWidth(w);
	}

}
