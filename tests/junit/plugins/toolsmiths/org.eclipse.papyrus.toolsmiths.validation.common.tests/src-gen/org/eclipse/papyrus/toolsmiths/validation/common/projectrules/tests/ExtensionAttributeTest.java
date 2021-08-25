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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.LiteralString;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Extension Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getParsedValue() <em>Parsed Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtensionAttributeTest extends ExtensionModelNodeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExtensionAttributeTest.class);
	}

	/**
	 * Constructs a new Extension Attribute test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public ExtensionAttributeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Extension Attribute test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected ExtensionAttribute getFixture() {
		return (ExtensionAttribute) fixture;
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
		setFixture(ProjectRulesFactory.eINSTANCE.createExtensionAttribute());
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
	 * Tests the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getParsedValue() <em>Parsed Value</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#getParsedValue()
	 * @generated NOT
	 */
	public void testGetParsedValue() {
		assertThat("Valueless attribute must not have a parsed value", getFixture().getParsedValue(), nullValue());

		LiteralString literal = (LiteralString) getFixture().createValue(ProjectRulesPackage.Literals.LITERAL_STRING);
		literal.setValue("Hello, world");
		assertThat("Structured value must not be parsed", getFixture().getParsedValue(), sameInstance(literal));

		OpaqueValue opaqueValue = (OpaqueValue) getFixture().createValue(ProjectRulesPackage.Literals.OPAQUE_VALUE);
		opaqueValue.setText("=model={resource|uri} ({=description.en.en})");

		Value parsed = getFixture().getParsedValue();
		assertThat("No parsed value found", parsed, notNullValue());
		assertThat("Opaque value must be parsed", parsed, not(instanceOf(OpaqueValue.class)));
		assertThat("Opaque value not correctly parsed", parsed.stringValue(), equalTo(opaqueValue.getText()));

		assertThat("Parsed value is not cached", getFixture().getParsedValue(), sameInstance(parsed));
	}

	/**
	 * Tests the '{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#containingExtension() <em>Containing Extension</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute#containingExtension()
	 * @generated NOT
	 */
	@Override
	public void testContainingExtension() {
		assertThat("Unowned attribute cannot have a containing extension", getFixture().containingExtension(), nullValue());

		Extension ext = ProjectRulesFactory.eINSTANCE.createExtension();
		ExtensionElement element = ext.createElement("elem");
		element.getAttributes().add(getFixture());
		assertThat("Attribute container's container should be its containing extension", getFixture().containingExtension(), is(ext));

		ExtensionElement nested = element.createElement("nested");
		nested.getAttributes().add(getFixture());
		assertThat("Element container's container's container should be its containing extension", getFixture().containingExtension(), is(ext));
	}

} // ExtensionAttributeTest
