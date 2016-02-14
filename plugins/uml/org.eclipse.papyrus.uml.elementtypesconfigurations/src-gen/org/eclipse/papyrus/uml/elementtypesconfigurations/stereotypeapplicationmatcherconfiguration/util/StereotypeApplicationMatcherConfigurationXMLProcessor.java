/**
 * Copyright (c) 2014 CEA LIST.
 * 
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  CEA LIST - Initial API and implementation
 */
package org.eclipse.papyrus.uml.elementtypesconfigurations.stereotypeapplicationmatcherconfiguration.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.papyrus.uml.elementtypesconfigurations.stereotypeapplicationmatcherconfiguration.StereotypeApplicationMatcherConfigurationPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StereotypeApplicationMatcherConfigurationXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StereotypeApplicationMatcherConfigurationXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		StereotypeApplicationMatcherConfigurationPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the StereotypeApplicationMatcherConfigurationResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new StereotypeApplicationMatcherConfigurationResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new StereotypeApplicationMatcherConfigurationResourceFactoryImpl());
		}
		return registrations;
	}

} //StereotypeApplicationMatcherConfigurationXMLProcessor