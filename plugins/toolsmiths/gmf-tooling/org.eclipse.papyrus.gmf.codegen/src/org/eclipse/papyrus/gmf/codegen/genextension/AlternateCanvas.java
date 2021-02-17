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

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Alternate Canvas</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getDomainDiagramElement <em>Domain Diagram Element</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getAlternateTopLevelNodes <em>Alternate Top Level Nodes</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getAlternateLinkNodes <em>Alternate Link Nodes</em>}</li>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getDiagram <em>Diagram</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateCanvas()
 * @model
 * @generated
 */
public interface AlternateCanvas extends CommentedElement {
	/**
	 * Returns the value of the '<em><b>Domain Diagram Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Diagram Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Diagram Element</em>' reference.
	 * @see #setDomainDiagramElement(GenClass)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateCanvas_DomainDiagramElement()
	 * @model required="true"
	 * @generated
	 */
	GenClass getDomainDiagramElement();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getDomainDiagramElement <em>Domain Diagram Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Diagram Element</em>' reference.
	 * @see #getDomainDiagramElement()
	 * @generated
	 */
	void setDomainDiagramElement(GenClass value);

	/**
	 * Returns the value of the '<em><b>Alternate Top Level Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenTopLevelNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alternate Top Level Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alternate Top Level Nodes</em>' containment reference list.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateCanvas_AlternateTopLevelNodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<AlternateGenTopLevelNode> getAlternateTopLevelNodes();

	/**
	 * Returns the value of the '<em><b>Alternate Link Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateGenLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alternate Link Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alternate Link Nodes</em>' containment reference list.
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateCanvas_AlternateLinkNodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<AlternateGenLink> getAlternateLinkNodes();

	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' reference.
	 * @see #setDiagram(GenDiagram)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getAlternateCanvas_Diagram()
	 * @model required="true"
	 * @generated
	 */
	GenDiagram getDiagram();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.AlternateCanvas#getDiagram <em>Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' reference.
	 * @see #getDiagram()
	 * @generated
	 */
	void setDiagram(GenDiagram value);

} // AlternateCanvas
