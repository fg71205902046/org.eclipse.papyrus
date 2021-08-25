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
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage;

import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getResourceAttribute() <em>Resource Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtensionTest extends TestCase {

	/**
	 * The fixture for this Extension test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected Extension fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExtensionTest.class);
	}

	/**
	 * Constructs a new Extension test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public ExtensionTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Extension test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void setFixture(Extension fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Extension test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected Extension getFixture() {
		return fixture;
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
		setFixture(ProjectRulesFactory.eINSTANCE.createExtension());
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
	 * Tests the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getResourceAttribute() <em>Resource Attribute</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension#getResourceAttribute()
	 * @generated NOT
	 */
	public void testGetResourceAttribute() {
		assertThat("Empty extension cannot have a resource attribute", getFixture().getResourceAttribute(), nullValue());

		// Tuck the attribute away in some levels of nesting
		ExtensionAttribute attr = getFixture().createElement("elem").createElement("nested").createAttribute("model");
		OpaqueValue value = (OpaqueValue) attr.createValue(ProjectRulesPackage.Literals.OPAQUE_VALUE);

		value.setText("foo");
		assertThat("Attribute is not a resource attribute", getFixture().getResourceAttribute(), not(attr));

		value.setText("{resource|path}");
		assertThat("Attribute should be inferred as a resource attribute", getFixture().getResourceAttribute(), is(attr));
	}

} // ExtensionTest
