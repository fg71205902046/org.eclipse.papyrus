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

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory;
import org.junit.Assume;

import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#isRequired() <em>Required</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DependencyTest extends TestCase {

	/**
	 * The fixture for this Dependency test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected Dependency fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DependencyTest.class);
	}

	/**
	 * Constructs a new Dependency test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public DependencyTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Dependency test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void setFixture(Dependency fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Dependency test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected Dependency getFixture() {
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
		setFixture(ProjectRulesFactory.eINSTANCE.createDependency());
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
	 * Tests the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#setRequired(boolean) <em>Required</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#setRequired(boolean)
	 * @generated NOT
	 */
	public void testSetRequired() {
		getFixture().setRequired(true);
		assertThat("Unowned dependency should not be set required", getFixture().isRequired(), is(false));
		ProjectDescription desc = ProjectRulesFactory.eINSTANCE.createProjectDescription();
		desc.getDependencies().add(getFixture());
		Assume.assumeThat("Dependency should not be required", getFixture().isRequired(), is(false));
		getFixture().setRequired(true);
		assertThat("Dependency should be required", getFixture().isRequired(), is(true));
		getFixture().setRequired(false);
		assertThat("Dependency should not be required", getFixture().isRequired(), is(false));
	}

	/**
	 * Tests the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#isRequired() <em>Is Required</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency#isRequired()
	 * @generated NOT
	 */
	public void testIsRequired() {
		assertThat("Unowned dependency should not be required", getFixture().isRequired(), is(false));
		ProjectDescription desc = ProjectRulesFactory.eINSTANCE.createProjectDescription();
		desc.getDependencies().add(getFixture());
		assertThat("Dependency should not be required", getFixture().isRequired(), is(false));
		desc.getRequiredDependencies().add(getFixture());
		assertThat("Dependency should be required", getFixture().isRequired(), is(true));
	}

} // DependencyTest
