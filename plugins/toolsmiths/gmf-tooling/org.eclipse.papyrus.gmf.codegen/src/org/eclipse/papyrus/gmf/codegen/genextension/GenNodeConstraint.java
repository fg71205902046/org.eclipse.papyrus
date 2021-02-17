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
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenConstraint;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Node Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint#getGenNode <em>Gen Node</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint#getGenConstraint <em>Gen Constraint</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getGenNodeConstraint()
 * @model
 * @generated
 */
public interface GenNodeConstraint extends CommentedElement {
	/**
	 * Returns the value of the '<em><b>Gen Node</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.gmf.codegen.gmfgen.GenNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gen Node</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gen Node</em>' reference list.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getGenNodeConstraint_GenNode()
	 * @model
	 * @generated
	 */
	EList<GenNode> getGenNode();

	/**
	 * Returns the value of the '<em><b>Gen Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gen Constraint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gen Constraint</em>' reference.
	 * @see #setGenConstraint(GenConstraint)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getGenNodeConstraint_GenConstraint()
	 * @model
	 * @generated
	 */
	GenConstraint getGenConstraint();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint#getGenConstraint <em>Gen Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gen Constraint</em>' reference.
	 * @see #getGenConstraint()
	 * @generated
	 */
	void setGenConstraint(GenConstraint value);

} // GenNodeConstraint
