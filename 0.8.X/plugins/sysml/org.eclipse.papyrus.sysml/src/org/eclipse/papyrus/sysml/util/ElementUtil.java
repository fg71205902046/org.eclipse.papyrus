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
 *  Yann Tanguy (CEA LIST) yann.tanguy@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.util;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;

public class ElementUtil {

	/**
	 * Check if the StereotypedElement has the given stereotype,
	 * or if one of its stereotype is inherits from the stereotype passed in parameter.
	 * This method currently exists in UMLUtils, this one is a copy created to avoid some dependencies
	 * propagated by UMLUtils (jface...).
	 * Current UMLUtils plug-in should probably be separated into two parts
	 * one depending on UML2 plug-in only, and the other bringing
	 * ui related features;
	 * 
	 * @param stereotypeName
	 *        The name of the stereotype to find.
	 * @return the Stereotype application EObject.
	 * @deprecated prefer using getStereotypeApplication()
	 */
	@Deprecated
	public static EObject hasStereotype(Element elt, EClass stereotypeClass) {
		EObject stereotypeApplication = null;

		// Stereotype parsing
		Iterator<EObject> stAppIt = elt.getStereotypeApplications().iterator();
		while(stAppIt.hasNext() && (stereotypeApplication == null)) {
			EObject stApp = stAppIt.next();
			if(stApp.eClass().getEAllSuperTypes().contains(stereotypeClass) || (stApp.eClass().equals(stereotypeClass))) {
				stereotypeApplication = stApp;
			}
		}

		return stereotypeApplication;
	}
	
	/**
	 * Convenient method to retrieve the StereotypeApplication by passing an element of the static profile.
	 * 
	 * @param <T>
	 *        the type of stereotype to look for
	 * @param element
	 *        an UML model element
	 * @param clazz
	 *        the class of an element of a static profile. Compatible sub-types will be returned as well
	 * @return the stereotype application or null if such stereotype is not applied
	 */
	@SuppressWarnings("unchecked")
	public static <T extends EObject> T getStereotypeApplication(Element element, java.lang.Class<T> clazz) {
		for(EObject stereoApplication : element.getStereotypeApplications()) {
			// check whether the stereotype is an instance of the passed parameter class
			if(clazz.isInstance(stereoApplication)) {
				return (T)stereoApplication;
			}
		}
		return null;
	}
}
