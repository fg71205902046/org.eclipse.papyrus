/**
 * Copyright (c) 2021 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 */
package org.eclipse.papyrus.toolsmiths.validation.common.projectrules.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Extension Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ExtensionElementTest extends ExtensionModelNodeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExtensionElementTest.class);
	}

	/**
	 * Constructs a new Extension Element test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public ExtensionElementTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Extension Element test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected ExtensionElement getFixture() {
		return (ExtensionElement) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ProjectRulesFactory.eINSTANCE.createExtensionElement());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#containingExtension() <em>Containing Extension</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement#containingExtension()
	 * @generated NOT
	 */
	@Override
	public void testContainingExtension() {
		assertThat("Unowned element cannot have a containing extension", getFixture().containingExtension(), nullValue());

		Extension ext = ProjectRulesFactory.eINSTANCE.createExtension();
		ext.getElements().add(getFixture());
		assertThat("Element container should be its containing extension", getFixture().containingExtension(), is(ext));

		ExtensionElement nested = ext.createElement("elem");
		nested.getElements().add(getFixture());
		assertThat("Element container's container should be its containing extension", getFixture().containingExtension(), is(ext));
	}

} // ExtensionElementTest
