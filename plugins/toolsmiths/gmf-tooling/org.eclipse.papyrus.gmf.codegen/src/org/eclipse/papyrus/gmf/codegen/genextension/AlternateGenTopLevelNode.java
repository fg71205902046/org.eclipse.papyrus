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

import org.eclipse.papyrus.gmf.codegen.gmfgen.GenTopLevelNode;
import org.eclipse.papyrus.gmf.codegen.gmfgen.TypeModelFacet;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Alternate Gen Top Level Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode#getGenTopLevelNode <em>Gen Top Level Node</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode#getTypeModelFacet <em>Type Model Facet</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateGenTopLevelNode()
 * @model
 * @generated
 */
public interface AlternateGenTopLevelNode extends CommentedElement {
	/**
	 * Returns the value of the '<em><b>Gen Top Level Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gen Top Level Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gen Top Level Node</em>' reference.
	 * @see #setGenTopLevelNode(GenTopLevelNode)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateGenTopLevelNode_GenTopLevelNode()
	 * @model required="true"
	 * @generated
	 */
	GenTopLevelNode getGenTopLevelNode();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode#getGenTopLevelNode <em>Gen Top Level Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gen Top Level Node</em>' reference.
	 * @see #getGenTopLevelNode()
	 * @generated
	 */
	void setGenTopLevelNode(GenTopLevelNode value);

	/**
	 * Returns the value of the '<em><b>Type Model Facet</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Model Facet</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Model Facet</em>' containment reference.
	 * @see #setTypeModelFacet(TypeModelFacet)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateGenTopLevelNode_TypeModelFacet()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TypeModelFacet getTypeModelFacet();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode#getTypeModelFacet <em>Type Model Facet</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Model Facet</em>' containment reference.
	 * @see #getTypeModelFacet()
	 * @generated
	 */
	void setTypeModelFacet(TypeModelFacet value);

} // AlternateGenTopLevelNode
