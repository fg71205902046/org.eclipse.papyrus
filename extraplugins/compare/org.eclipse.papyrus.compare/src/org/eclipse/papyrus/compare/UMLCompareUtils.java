/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Tatiana Fesenko (CEA LIST) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.compare;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.papyrus.compare.ui.PapyrusLabelProvider;
import org.eclipse.papyrus.compare.ui.PapyrusStyledLabelProvider;
import org.eclipse.uml2.uml.util.UMLUtil;


public class UMLCompareUtils {

	private static UMLCompareUtils ourInstance;

	private IBaseLabelProvider myPapyrusLabelProvider;
	
	private UMLCompareUtils() {}
	
	public static UMLCompareUtils getInstance() {
		if (ourInstance == null) {
			ourInstance = new UMLCompareUtils();
		}
		return ourInstance;
	}

	public static boolean isStereotypeApplication(EObject eObject) {
		return UMLUtil.getStereotype(eObject) != null;
	}

	public IBaseLabelProvider getPapyrusLabelProvider() {
		return new PapyrusStyledLabelProvider();
	}

	public IBaseLabelProvider getStyledPapyrusLabelProvider() {
		if (myPapyrusLabelProvider == null) {
			myPapyrusLabelProvider = new PapyrusStyledLabelProvider();
		}
		return myPapyrusLabelProvider;
	}

}
