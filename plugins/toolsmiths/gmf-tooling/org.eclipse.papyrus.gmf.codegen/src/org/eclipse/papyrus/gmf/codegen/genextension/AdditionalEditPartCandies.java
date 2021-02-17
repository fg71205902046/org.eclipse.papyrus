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
 * A representation of the model object '<em><b>Additional Edit Part Candies</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.AdditionalEditPartCandies#getBaseEditHelperPackage <em>Base Edit Helper Package</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAdditionalEditPartCandies()
 * @model
 * @generated
 */
public interface AdditionalEditPartCandies extends CommentedElement {
	/**
	 * Returns the value of the '<em><b>Base Edit Helper Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Edit Helper Package</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Edit Helper Package</em>' attribute.
	 * @see #setBaseEditHelperPackage(String)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAdditionalEditPartCandies_BaseEditHelperPackage()
	 * @model
	 * @generated
	 */
	String getBaseEditHelperPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.AdditionalEditPartCandies#getBaseEditHelperPackage <em>Base Edit Helper Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Edit Helper Package</em>' attribute.
	 * @see #getBaseEditHelperPackage()
	 * @generated
	 */
	void setBaseEditHelperPackage(String value);

} // AdditionalEditPartCandies
