/*****************************************************************************
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
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder.helper;

import static java.util.function.Predicate.not;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.infra.tools.util.XMLTreeIterator;
import org.eclipse.papyrus.toolsmiths.plugin.internal.builder.messages.Messages;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CommonProblemConstants;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.PluginErrorReporter;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.util.Evaluator;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.base.Strings;

/**
 * A helper for validation of plug-in extensions described by a {@link ProjectDescription} model.
 * It is design to provide implementations of the various delegate interfaces of the
 * {@link PluginErrorReporter} API.
 */
public class ExtensionValidator<T extends EObject> {
	private static final String CATEGORY = "model_extension"; //$NON-NLS-1$

	private final IProject project;
	private final IFile modelFile;
	private final Extension extensionModel;

	public ExtensionValidator(IProject project, IFile modelFile, Extension extensionModel) {
		super();

		this.project = project;
		this.modelFile = modelFile;
		this.extensionModel = extensionModel;
	}

	/**
	 * Match an extension on the given {@code point} as a registration of the given {@code model}.
	 *
	 * @param {@code <extension>} element an element in the <tt>plugin.xml</tt>
	 * @param point
	 *            the extension point referenced by the {@code element}
	 * @param model
	 *            the tooling model being validated
	 *
	 * @return the XML element within the given extension {@code element} that matches the {@code model}
	 */
	public Optional<Element> matchExtension(Element element, String point, T model) {
		if (Objects.equals(point, extensionModel.getExtensionPoint())) {
			ExtensionAttribute resourceAttribute = extensionModel.getResourceAttribute();
			for (Iterator<Element> iter = new XMLTreeIterator(element); iter.hasNext();) {
				Element next = iter.next();
				if (matchElement(resourceAttribute.getElement(), next, element)) {
					return matchResourceAttribute(resourceAttribute, next, createEvaluator(model));
				}
			}
		}

		return Optional.empty();
	}

	protected boolean matchElement(ExtensionElement description, Element element, Element extension) {
		boolean result = Objects.equals(description.getName(), element.getTagName())
				&& (description.getParent() != null) == (element.getParentNode() != extension);

		if (result && description.getParent() != null) {
			// Check parents
			result = matchElement(description.getParent(), (Element) element.getParentNode(), extension);
		}

		return result;
	}

	protected Optional<Element> matchResourceAttribute(ExtensionAttribute resourceAttribute, Element element, Evaluator evaluator) {
		String attributeValue = element.getAttribute(resourceAttribute.getName());

		boolean evaluationResult = validate(resourceAttribute, attributeValue, evaluator);
		return evaluationResult ? Optional.of(element) : Optional.empty();
	}

	protected final String evaluate(ExtensionAttribute attribute, Evaluator evaluator) {
		Value value = attribute.getParsedValue();
		String result = value == null ? null : evaluator.evaluate(value);
		return Strings.nullToEmpty(result);
	}

	protected final boolean validate(ExtensionAttribute attribute, String actualValue, Evaluator evaluator) {
		Value value = attribute.getParsedValue();
		return value != null && evaluator.validate(actualValue, value);
	}

	protected Evaluator createEvaluator(T model) {
		return new Evaluator(project, modelFile, model);
	}

	public void checkExtension(Element element, String point, T model, PluginErrorReporter.ProblemReport problems) {
		ExtensionElement description = extensionModel.getElement(element.getTagName());
		if (description != null) {
			Evaluator evaluator = createEvaluator(model);
			checkExtensionElement(description, element, point, model, evaluator, problems);
		}
	}

	protected void checkExtensionElement(ExtensionElement description, Element element, String point, T model, Evaluator evaluator, PluginErrorReporter.ProblemReport problems) {
		Set<ExtensionAttribute> missingAttributes = new HashSet<>(description.getAttributes());
		Set<ExtensionElement> missingElements = new HashSet<>(description.getElements());

		NamedNodeMap attributes = element.getAttributes();

		for (int i = 0, size = attributes.getLength(); i < size; i++) {
			Attr attr = (Attr) attributes.item(i);
			ExtensionAttribute attrDescription = description.getAttribute(attr.getName());
			missingAttributes.remove(attrDescription);

			if (attrDescription != null && attrDescription.isValidatable()
			// The resource attribute is only used for matching
					&& attrDescription != attrDescription.containingExtension().getResourceAttribute()) {

				String expectedValue = evaluate(attrDescription, evaluator);
				if (!expectedValue.equals(attr.getValue())) {
					problems.reportProblem(Diagnostic.ERROR, element, attr.getName(),
							NLS.bind(Messages.ExtensionValidator_0, attr.getName(), expectedValue),
							CommonProblemConstants.INVALID_EXTENSION_ATTRIBUTE, CATEGORY, null);
				}
			}
		}

		// Report missing attributes
		missingAttributes.removeIf(not(ExtensionAttribute::isValidatable));
		if (!missingAttributes.isEmpty()) {
			missingAttributes.stream().map(ExtensionAttribute::getName).forEach(name -> {
				problems.reportProblem(Diagnostic.ERROR, element, NLS.bind(Messages.ExtensionValidator_1, name, point),
						CommonProblemConstants.MISSING_EXTENSION_ATTRIBUTE, CATEGORY,
						Map.of(CommonProblemConstants.ATTRIBUTE_NAME, name));
			});
		}

		// Recursively check child elements
		NodeList children = element.getChildNodes();
		for (int i = 0, size = children.getLength(); i < size; i++) {
			Node next = children.item(i);
			if (next.getNodeType() == Node.ELEMENT_NODE) {
				Element child = (Element) next;
				ExtensionElement childDescription = description.getElement(child.getTagName());
				missingElements.remove(childDescription);

				if (childDescription != null) {
					checkExtensionElement(childDescription, child, point, model, evaluator, problems);
				}
			}
		}

		// Report missing elements
		missingElements.removeIf(not(ExtensionElement::isValidatable));
		if (!missingElements.isEmpty()) {
			missingElements.stream().map(ExtensionElement::getName).forEach(name -> {
				problems.reportProblem(Diagnostic.ERROR, element, NLS.bind(Messages.ExtensionValidator_2, name, point),
						CommonProblemConstants.MISSING_EXTENSION_ELEMENT, CATEGORY,
						Map.of(CommonProblemConstants.ELEMENT_NAME, name));
			});
		}
	}

	/**
	 * Query the problem ID for missing extension on the given {@code point}.
	 *
	 * @param point
	 *            the extension point on which an extension is expected for the {@code model} but is absent
	 * @param model
	 *            a model being validated for which an extension on the given {@code point} is required
	 *
	 * @return the problem ID for quick fix
	 */
	public int problemID(String point, T model) {
		return CommonProblemConstants.MISSING_EXTENSION;
	}

}
