/*****************************************************************************
 * Copyright (c) 2020 Christian W. Damus, CEA LIST, and others.
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
 *****************************************************************************/

package org.eclipse.papyrus.uml.service.types.tests.registry;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.services.edit.context.TypeContext;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;
import org.eclipse.papyrus.junit.utils.rules.ModelSetFixture;
import org.eclipse.papyrus.junit.utils.rules.PluginResource;
import org.eclipse.papyrus.junit.utils.rules.ServiceRegistryModelSetFixture;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Type;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test cases for the unified {@code StereotypeMatcherAdviceConfiguration}.
 *
 * @see <a href="http://eclip.se/568853">bug 568853</a>
 */
@PluginResource("resource/bug568853/bug568853.di")
public class StereotypeMatcherAdviceTest {

	@Rule
	public final ModelSetFixture model = new ServiceRegistryModelSetFixture();

	@Test
	public void matchExistingElement() {
		Type suite = model.getModel().getOwnedType("MainTests");
		IElementType[] types = getTypesOf(suite);

		assertThat(Arrays.asList(types), hasItem(elementTypeIDThat(qualified("TestSuite"))));
	}

	@Test
	public void createNewElement() {
		org.eclipse.uml2.uml.Class suite = (org.eclipse.uml2.uml.Class) model.getModel().getOwnedType("MainTests");

		IElementType testSuiteType = getType("TestSuite");
		IElementType testCaseType = getType("TestCase");

		CreateElementRequest request = new CreateElementRequest(suite, testCaseType);
		ICommand command = edit(testSuiteType).getEditCommand(request);

		model.execute(command);
		Operation operation = (Operation) command.getCommandResult().getReturnValue();

		assertThat(suite.getOwnedOperations().size(), is(2));
		assertThat(suite.getOwnedOperations(), hasItem(operation));

		assertThat("Stereotype not applied to new element", operation.isStereotypeApplied(operation.getApplicableStereotype("UnitTest::TestCase")));
	}

	//
	// Test framework
	//

	IElementType[] getTypesOf(EObject object) {
		try {
			return ElementTypeRegistry.getInstance().getAllTypesMatching(object, TypeContext.getContext(model.getResourceSet()));
		} catch (ServiceException e) {
			throw new AssertionError(e);
		}
	}

	IElementType getType(String name) {
		try {
			Matcher<IElementType> matcher = elementTypeIDThat(qualified(name));
			return Stream.of(ElementTypeRegistry.getInstance().getSpecializationTypes(TypeContext.getContext(model.getResourceSet())))
					.filter(matcher::matches)
					.findAny()
					.orElseThrow(() -> new AssertionError("No such element type: " + name));
		} catch (ServiceException e) {
			throw new AssertionError(e);
		}
	}

	IElementEditService edit(IElementType type) {
		try {
			return ElementEditServiceUtils.getCommandProvider(type, TypeContext.getContext(model.getResourceSet()));
		} catch (ServiceException e) {
			throw new AssertionError(e);
		}
	}

	private Matcher<IElementType> elementTypeIDThat(Matcher<String> idMatcher) {
		return new FeatureMatcher<>(idMatcher, "id", "id") {

			@Override
			protected String featureValueOf(IElementType actual) {
				return actual.getId();
			}
		};
	}

	static Matcher<String> qualified(String name) {
		return is("org.eclipse.papyrus.uml.service.types.tests.UnitTest." + name); //$NON-NLS-1$
	}

}
