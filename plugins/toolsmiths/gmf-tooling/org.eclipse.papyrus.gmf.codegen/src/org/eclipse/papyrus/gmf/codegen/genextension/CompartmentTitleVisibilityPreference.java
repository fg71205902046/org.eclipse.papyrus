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
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCompartment;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compartment Title Visibility Preference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This element allows to define the compartment's title which are hidden (or visible) by default
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.CompartmentTitleVisibilityPreference#isVisibleByDefault <em>Visible By Default</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.CompartmentTitleVisibilityPreference#getCompartments <em>Compartments</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getCompartmentTitleVisibilityPreference()
 * @model
 * @generated
 */
public interface CompartmentTitleVisibilityPreference extends CommentedElement {
	/**
	 * Returns the value of the '<em><b>Visible By Default</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visible By Default</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visible By Default</em>' attribute.
	 * @see #setVisibleByDefault(boolean)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getCompartmentTitleVisibilityPreference_VisibleByDefault()
	 * @model default="true"
	 * @generated
	 */
	boolean isVisibleByDefault();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.CompartmentTitleVisibilityPreference#isVisibleByDefault <em>Visible By Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visible By Default</em>' attribute.
	 * @see #isVisibleByDefault()
	 * @generated
	 */
	void setVisibleByDefault(boolean value);

	/**
	 * Returns the value of the '<em><b>Compartments</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.gmf.codegen.gmfgen.GenCompartment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compartments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compartments</em>' reference list.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getCompartmentTitleVisibilityPreference_Compartments()
	 * @model
	 * @generated
	 */
	EList<GenCompartment> getCompartments();

} // CompartmentTitleVisibilityPreference
