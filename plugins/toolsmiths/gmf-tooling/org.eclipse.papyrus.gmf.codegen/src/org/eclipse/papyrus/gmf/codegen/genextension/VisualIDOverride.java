/**
 * Copyright (c) 2015 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   CEA LIST - Initial API and implementation
 * 
 */
package org.eclipse.papyrus.gmf.codegen.genextension;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Visual ID Override</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getGenView <em>Gen View</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getVisualID <em>Visual ID</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getChild <em>Child</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getVisualIDOverride()
 * @model
 * @generated
 */
public interface VisualIDOverride extends EObject {
	/**
	 * Returns the value of the '<em><b>Gen View</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gen View</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gen View</em>' reference.
	 * @see #setGenView(GenCommonBase)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getVisualIDOverride_GenView()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	GenCommonBase getGenView();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getGenView <em>Gen View</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gen View</em>' reference.
	 * @see #getGenView()
	 * @generated
	 */
	void setGenView(GenCommonBase value);

	/**
	 * Returns the value of the '<em><b>Visual ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visual ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visual ID</em>' attribute.
	 * @see #setVisualID(String)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getVisualIDOverride_VisualID()
	 * @model required="true"
	 * @generated
	 */
	String getVisualID();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride#getVisualID <em>Visual ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visual ID</em>' attribute.
	 * @see #getVisualID()
	 * @generated
	 */
	void setVisualID(String value);

	/**
	 * Returns the value of the '<em><b>Child</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child</em>' containment reference list.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getVisualIDOverride_Child()
	 * @model containment="true"
	 * @generated
	 */
	EList<VisualIDOverride> getChild();

} // VisualIDOverride
