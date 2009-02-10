/*****************************************************************************
 * Copyright (c) 2008 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.Tessier@cea.fr - Initial API and implementation
 *
  *****************************************************************************/
package org.eclipse.papyrus.di;

import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uml1 Semantic Model Bridge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.papyrus.di.Uml1SemanticModelBridge#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.papyrus.di.DiPackage#getUml1SemanticModelBridge()
 * @model
 * @generated
 */
public interface Uml1SemanticModelBridge extends SemanticModelBridge {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(Element)
	 * @see org.eclipse.papyrus.di.DiPackage#getUml1SemanticModelBridge_Element()
	 * @model
	 * @generated
	 */
	Element getElement();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.di.Uml1SemanticModelBridge#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(Element value);

} // Uml1SemanticModelBridge
