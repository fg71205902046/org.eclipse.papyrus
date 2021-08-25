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

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>projectrules</b></em>' package.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ProjectRulesTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new ProjectRulesTests("projectrules Tests");
		suite.addTestSuite(DependencyTest.class);
		suite.addTestSuite(ExtensionTest.class);
		suite.addTestSuite(ExtensionElementTest.class);
		suite.addTestSuite(ExtensionAttributeTest.class);
		suite.addTestSuite(LiteralStringTest.class);
		suite.addTestSuite(VariableTest.class);
		suite.addTestSuite(ExpressionTest.class);
		suite.addTestSuite(OpaqueValueTest.class);
		suite.addTestSuite(FeaturePathTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public ProjectRulesTests(String name) {
		super(name);
	}

} // ProjectRulesTests
