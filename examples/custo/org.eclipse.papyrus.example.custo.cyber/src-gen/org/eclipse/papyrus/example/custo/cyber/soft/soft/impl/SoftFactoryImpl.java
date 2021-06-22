/**
 * 
 *   Copyright (c) 2021 CEA LIST.
 *    
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License 2.0
 *   which accompanies this distribution, and is available at
 *   https://www.eclipse.org/legal/epl-2.0/
 *  
 *   SPDX-License-Identifier: EPL-2.0
 *    
 *  Contributors:
 *  	Patrick Tessier 	(CEA LIST) - Initial API and implementation
 *  
 */
package org.eclipse.papyrus.example.custo.cyber.soft.soft.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.example.custo.cyber.soft.soft.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SoftFactoryImpl extends EFactoryImpl implements SoftFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "\n  Copyright (c) 2021 CEA LIST.\n   \n  All rights reserved. This program and the accompanying materials\n  are made available under the terms of the Eclipse Public License 2.0\n  which accompanies this distribution, and is available at\n  https://www.eclipse.org/legal/epl-2.0/\n \n  SPDX-License-Identifier: EPL-2.0\n   \n Contributors:\n \tPatrick Tessier \t(CEA LIST) - Initial API and implementation\n ";

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SoftFactory init() {
		try {
			SoftFactory theSoftFactory = (SoftFactory)EPackage.Registry.INSTANCE.getEFactory(SoftPackage.eNS_URI);
			if (theSoftFactory != null) {
				return theSoftFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SoftFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SoftPackage.SOFT_LAYER: return createSoftLayer();
			case SoftPackage.SOFT: return createSoft();
			case SoftPackage.COMMUNICATION: return createCommunication();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftLayer createSoftLayer() {
		SoftLayerImpl softLayer = new SoftLayerImpl();
		return softLayer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Soft createSoft() {
		SoftImpl soft = new SoftImpl();
		return soft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Communication createCommunication() {
		CommunicationImpl communication = new CommunicationImpl();
		return communication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftPackage getSoftPackage() {
		return (SoftPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SoftPackage getPackage() {
		return SoftPackage.eINSTANCE;
	}

} //SoftFactoryImpl
