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

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Project Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ProjectDescriptionTest extends TestCase {

	/**
	 * The fixture for this Project Description test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ProjectDescription fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ProjectDescriptionTest.class);
	}

	/**
	 * Constructs a new Project Description test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public ProjectDescriptionTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Project Description test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void setFixture(ProjectDescription fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Project Description test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ProjectDescription getFixture() {
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
		setFixture(ProjectRulesFactory.eINSTANCE.createProjectDescription());
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

} // ProjectDescriptionTest
