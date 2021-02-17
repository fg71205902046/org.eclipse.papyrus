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

import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLink;
import org.eclipse.papyrus.gmf.codegen.gmfgen.TypeModelFacet;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Alternate Gen Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink#getGenLinkNode <em>Gen Link Node</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink#getTypeModelFacet <em>Type Model Facet</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateGenLink()
 * @model
 * @generated
 */
public interface AlternateGenLink extends CommentedElement {
	/**
	 * Returns the value of the '<em><b>Gen Link Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gen Link Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gen Link Node</em>' reference.
	 * @see #setGenLinkNode(GenLink)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateGenLink_GenLinkNode()
	 * @model required="true"
	 * @generated
	 */
	GenLink getGenLinkNode();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink#getGenLinkNode <em>Gen Link Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gen Link Node</em>' reference.
	 * @see #getGenLinkNode()
	 * @generated
	 */
	void setGenLinkNode(GenLink value);

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
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateGenLink_TypeModelFacet()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TypeModelFacet getTypeModelFacet();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink#getTypeModelFacet <em>Type Model Facet</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Model Facet</em>' containment reference.
	 * @see #getTypeModelFacet()
	 * @generated
	 */
	void setTypeModelFacet(TypeModelFacet value);

} // AlternateGenLink
