/**
 * Copyright (c) 2015 CEA LIST.
 *  
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 * 	CEA LIST - Initial API and implementation
 * 
 */
package org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Representation Kind</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel.RepresentationKind#getEditPartQualifiedName <em>Edit Part Qualified Name</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel.RepresentationKind#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel.RepresentationKind#getViewFactory <em>View Factory</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel.ExpansionmodelPackage#getRepresentationKind()
 * @model
 * @generated
 */
public interface RepresentationKind extends EObject {
	/**
	 * Returns the value of the '<em><b>Edit Part Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edit Part Qualified Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edit Part Qualified Name</em>' attribute.
	 * @see #setEditPartQualifiedName(String)
	 * @see org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel.ExpansionmodelPackage#getRepresentationKind_EditPartQualifiedName()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getEditPartQualifiedName();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel.RepresentationKind#getEditPartQualifiedName <em>Edit Part Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edit Part Qualified Name</em>' attribute.
	 * @see #getEditPartQualifiedName()
	 * @generated
	 */
	void setEditPartQualifiedName(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel.ExpansionmodelPackage#getRepresentationKind_Name()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel.RepresentationKind#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>View Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Factory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Factory</em>' attribute.
	 * @see #setViewFactory(String)
	 * @see org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel.ExpansionmodelPackage#getRepresentationKind_ViewFactory()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getViewFactory();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.gmfdiag.common.expansionmodel.RepresentationKind#getViewFactory <em>View Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Factory</em>' attribute.
	 * @see #getViewFactory()
	 * @generated
	 */
	void setViewFactory(String value);

} // RepresentationKind
