/*****************************************************************************
 * Copyright (c) 2019 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Nicolas FAUVERGUE (CEA LIST) nicolas.fauvergue@cea.fr - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.uml.properties.databinding.tests;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.papyrus.infra.properties.ui.modelelement.ModelElement;
import org.eclipse.papyrus.junit.utils.rules.PluginResource;
import org.eclipse.papyrus.uml.properties.modelelement.UMLModelElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.junit.Assert;

/**
 * This allows to test the basic observable value (PapyrusObservableValue).
 */
@SuppressWarnings({ "nls" })
@PluginResource("model/BasicObservableValue.di")
public class BasicObservableValueTest extends AbstractUMLObservableValueTest {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.uml.properties.databinding.tests.AbstractObservableTest#initializeContextObject()
	 */
	@Override
	protected Object initializeContextObject() {
		final Package pckg = (Package) model.getOwnedMember("Package1");
		Assert.assertNotNull("The package 'Package1' must be available in the model", pckg);

		return pckg;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.uml.properties.databinding.tests.AbstractObservableTest#createModelElement(org.eclipse.emf.edit.domain.EditingDomain, java.lang.Object)
	 */
	@Override
	protected ModelElement createModelElement(final EditingDomain domain, final Object source) {
		return new UMLModelElement((EObject) source, domain);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.uml.properties.databinding.tests.AbstractObservableTest#getPropertyPath()
	 */
	@Override
	protected String getPropertyPath() {
		return "URI";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.uml.properties.databinding.tests.AbstractObservableTest#expectedBeforeValue()
	 */
	@Override
	protected Object expectedBeforeValue() {
		return "";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.uml.properties.databinding.tests.AbstractObservableValueTest#expectedValueType()
	 */
	@Override
	protected Object expectedValueType() {
		return UMLPackage.eINSTANCE.getPackage_URI();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.uml.properties.databinding.tests.AbstractObservableTest#expectedAfterValue()
	 */
	@Override
	protected Object expectedAfterValue() {
		return "http://www.eclipse.org/testURI";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.uml.properties.databinding.tests.AbstractObservableTest#expectedValueToSet()
	 */
	@Override
	protected Object expectedValueToSet() {
		return "http://www.eclipse.org/testURI";
	}

}
