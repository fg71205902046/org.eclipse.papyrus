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

import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specific Diagram Updater</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This class is used to precise itw own locator used for the border item, like port or template.
 * 
 * for example, in the case of templateSignature of ClassEditPart in the method protected boolean addFixedChild(EditPart childEditPart):
 * replacement of the general BorderItemLocator is needed in order to constrain the movement. 
 * 
 * if (childEditPart instanceof RedefinableTemplateSignatureEditPart) {
 *    // BorderItemLocator locator = new BorderItemLocator(getMainFigure(), PositionConstants.NORTH);
 *    BorderItemLocator locator = new TemplateClassifierBorderItemLocator(getMainFigure(), PositionConstants.NORTH);
 *    getBorderedFigure().getBorderItemContainer().add(((RedefinableTemplateSignatureEditPart) childEditPart).getFigure(), locator);
 *    return true;
 * }
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificDiagramUpdater#getGenNode <em>Gen Node</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getSpecificDiagramUpdater()
 * @model
 * @generated
 */
public interface SpecificDiagramUpdater extends ExternalHook {
	/**
	 * Returns the value of the '<em><b>Gen Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gen Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gen Node</em>' reference.
	 * @see #setGenNode(GenCommonBase)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getSpecificDiagramUpdater_GenNode()
	 * @model
	 * @generated
	 */
	GenCommonBase getGenNode();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.SpecificDiagramUpdater#getGenNode <em>Gen Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gen Node</em>' reference.
	 * @see #getGenNode()
	 * @generated
	 */
	void setGenNode(GenCommonBase value);

} // SpecificDiagramUpdater
