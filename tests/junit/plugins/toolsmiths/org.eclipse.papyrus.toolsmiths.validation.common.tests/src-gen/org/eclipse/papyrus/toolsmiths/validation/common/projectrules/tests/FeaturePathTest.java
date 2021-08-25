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
import static org.hamcrest.MatcherAssert.assertThat;

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Feature Path</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath#stringValue() <em>String Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeaturePathTest extends ValueTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(FeaturePathTest.class);
	}

	/**
	 * Constructs a new Feature Path test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public FeaturePathTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Feature Path test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected FeaturePath getFixture() {
		return (FeaturePath) fixture;
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
		setFixture(ProjectRulesFactory.eINSTANCE.createFeaturePath());
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
	 * Tests the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath#stringValue() <em>String Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath#stringValue()
	 * @generated NOT
	 */
	@Override
	public void testStringValue() {
		assertThat("Empty path should have empty value", getFixture().stringValue(), is("{=}"));
		getFixture().getPath().add("description");
		assertThat(getFixture().stringValue(), is("{=description}"));
		getFixture().getPath().add("en");
		assertThat(getFixture().stringValue(), is("{=description.en}"));
		getFixture().getPath().add("en");
		assertThat(getFixture().stringValue(), is("{=description.en.en}"));
	}

} // FeaturePathTest
