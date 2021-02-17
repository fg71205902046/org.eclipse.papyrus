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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Hook</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The purpose of this class is point to an external class (for example java classes).
 * the classpath is the path to the this extenal class.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.ExternalHook#getClasspath <em>Classpath</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getExternalHook()
 * @model
 * @generated
 */
public interface ExternalHook extends CommentedElement {
	/**
	 * Returns the value of the '<em><b>Classpath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classpath</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classpath</em>' attribute.
	 * @see #setClasspath(String)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getExternalHook_Classpath()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getClasspath();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.ExternalHook#getClasspath <em>Classpath</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classpath</em>' attribute.
	 * @see #getClasspath()
	 * @generated
	 */
	void setClasspath(String value);

} // ExternalHook
