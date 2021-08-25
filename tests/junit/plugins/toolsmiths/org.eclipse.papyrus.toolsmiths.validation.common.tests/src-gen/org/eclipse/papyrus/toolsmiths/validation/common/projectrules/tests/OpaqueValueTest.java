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

import static org.eclipse.papyrus.junit.matchers.MoreMatchers.isEmpty;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Expression;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.LiteralString;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Opaque Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#parse() <em>Parse</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#stringValue() <em>String Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OpaqueValueTest extends ValueTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(OpaqueValueTest.class);
	}

	/**
	 * Constructs a new Opaque Value test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public OpaqueValueTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Opaque Value test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected OpaqueValue getFixture() {
		return (OpaqueValue) fixture;
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
		setFixture(ProjectRulesFactory.eINSTANCE.createOpaqueValue());
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
	 * Tests the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#parse() <em>Parse</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#parse()
	 * @generated NOT
	 */
	public void testParse() {
		Value value = getFixture().parse();
		assertThat("Null opaque value should parse to empty literal", value.stringValue(), is(""));
	}

	public void testParse_literal() {
		getFixture().setText("Hello world");
		Value value = getFixture().parse();
		assertThat(value, instanceOf(LiteralString.class));
		assertThat(value.stringValue(), is("Hello world"));
	}

	public void testParse_variable() {
		getFixture().setText("{resource}");
		Value value = getFixture().parse();
		assertThat(value, instanceOf(Variable.class));
		Variable variable = (Variable) value;
		assertThat(variable.getName(), is("resource"));
		assertThat(variable.getOptions(), isEmpty());
	}

	public void testParse_variableWithOptions() {
		getFixture().setText("{resource|uri|normalized}");
		Value value = getFixture().parse();
		assertThat(value, instanceOf(Variable.class));
		Variable variable = (Variable) value;
		assertThat(variable.getName(), is("resource"));
		assertThat(variable.getOptions(), hasItems("uri", "normalized"));
	}

	public void testParse_featurePath() {
		getFixture().setText("{=description.en . en}");
		Value value = getFixture().parse();
		assertThat(value, instanceOf(FeaturePath.class));
		FeaturePath featurePath = (FeaturePath) value;
		// The feature path is ordered and non-unique, so assert the list
		assertThat(featurePath.getPath(), is(List.of("description", "en", "en")));
	}

	public void testParse_expression() {
		getFixture().setText("=model={resource|uri} ({=description.en.en})");
		Value value = getFixture().parse();
		assertThat(value, instanceOf(Expression.class));
		Expression expression = (Expression) value;
		assertThat("Wrong number of expression parts", expression.getValues().size(), is(5));
		Value part1 = expression.getValues().get(0);
		assertThat("First part should be a literal", part1, instanceOf(LiteralString.class));
		assertThat(part1.stringValue(), is("model="));
		Value part2 = expression.getValues().get(1);
		assertThat("Second part should be a variable", part2, instanceOf(Variable.class));
		Variable variable = (Variable) part2;
		assertThat(variable.getName(), is("resource"));
		assertThat(variable.getOptions(), hasItem("uri"));
		Value part3 = expression.getValues().get(2);
		assertThat("Third part should be a literal", part3, instanceOf(LiteralString.class));
		assertThat(part3.stringValue(), is(" ("));
		Value part4 = expression.getValues().get(3);
		assertThat("Fourth part should be a feature path", part4, instanceOf(FeaturePath.class));
		FeaturePath featurePath = (FeaturePath) part4;
		assertThat(featurePath.getPath(), is(List.of("description", "en", "en")));
		Value part5 = expression.getValues().get(4);
		assertThat("Third part should be a literal", part5, instanceOf(LiteralString.class));
		assertThat(part5.stringValue(), is(")"));
	}

	/**
	 * Tests the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#stringValue() <em>String Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue#stringValue()
	 * @generated NOT
	 */
	@Override
	public void testStringValue() {
		assertThat("Null opaque value should have empty string value", getFixture().stringValue(), is(""));
		getFixture().setText("");
		assertThat("Empty opaque value should have its own string value", getFixture().stringValue(), is(""));
		getFixture().setText("=model:{resource|uri}");
		assertThat("Complex opaque value should have its own string value", getFixture().stringValue(), is("=model:{resource|uri}"));
	}

} // OpaqueValueTest
