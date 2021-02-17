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

import java.util.Optional;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Visual Type Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Configuration of this diagram's provider to the Visual Type Service,
 * which enables canonical view synchronization, drag-and-drop, and
 * possibly other capabilities.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.gmf.codegen.genextension.GenVisualTypeProvider#getClassName <em>Class Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getGenVisualTypeProvider()
 * @model
 * @generated
 */
public interface GenVisualTypeProvider extends CommentedElement {
	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute.
	 * The default value is <code>"UMLVisualTypeProvider"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage#getGenVisualTypeProvider_ClassName()
	 * @model default="UMLVisualTypeProvider"
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.gmf.codegen.genextension.GenVisualTypeProvider#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

	/**
	 * Obtains the class name to generate for the Visual Type Provider
	 * extension of the given {@code diagram}, which defaults to
	 * {@code "UMLVisualTypeProvider"} in the case that either there
	 * is no visual type provider in the GMFGen model or it does not
	 * have a class name.
	 * 
	 * @param diagram
	 *            a diagram generator model element
	 * @return the provider class name
	 * @generated NOT
	 */
	static String getClassName(GenDiagram diagram) {
		return Optional.ofNullable(diagram.eResource())
				.map(Resource::getContents)
				.orElse(ECollections.<EObject> emptyEList())
				.stream()
				.filter(PapyrusExtensionRootNode.class::isInstance).map(PapyrusExtensionRootNode.class::cast)
				.findFirst()
				.map(PapyrusExtensionRootNode::getExtensionNodes)
				.filter(GenVisualTypeProvider.class::isInstance).map(GenVisualTypeProvider.class::cast)
				.map(GenVisualTypeProvider::getClassName)
				.orElse(GenExtensionPackage.Literals.GEN_VISUAL_TYPE_PROVIDER__CLASS_NAME.getDefaultValueLiteral());
	}

	/**
	 * Obtains the qualified class name to generate for the Visual Type Provider
	 * extension of the given {@code diagram}.
	 * 
	 * @param diagram
	 *            a diagram generator model element
	 * @return the qualified provider class name
	 * @generated NOT
	 */
	static String getQualifiedClassName(GenDiagram diagram) {
		String className = getClassName(diagram);

		return String.format("%s.%s", diagram.getProvidersPackageName(), className);
	}

} // GenVisualTypeProvider
