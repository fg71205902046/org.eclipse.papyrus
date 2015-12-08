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

package org.eclipse.papyrus.dev.types.utils;

import java.util.Comparator;

import org.eclipse.gmf.runtime.emf.type.core.edithelper.IEditHelperAdvice;


public class AdvicesComparator implements Comparator<IEditHelperAdvice> {

	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 *
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	@Override
	public int compare(IEditHelperAdvice o1, IEditHelperAdvice o2) {
		return o1.toString().compareTo(o2.toString());
	}

}