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

package org.eclipse.papyrus.toolsmiths.validation.common.projectrules.util;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Expression;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.LiteralString;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable;

import com.google.common.base.Strings;

/**
 * An evaluator of potentially complex {@link Value}s in project rules.
 */
public class Evaluator {

	private static final String BUNDLE_NAME = "bundleName";

	/** The name of a {@link Variable} that indicates the model resource. */
	public static final String RESOURCE = "resource"; //$NON-NLS-1$
	private static final String OPTION_NAME = "name"; //$NON-NLS-1$
	private static final String OPTION_PATH = "path"; //$NON-NLS-1$
	private static final String OPTION_URI = "uri"; //$NON-NLS-1$

	private static final String VAR_URI = "uri"; //$NON-NLS-1$
	private static final String OPTION_FRAGMENT = "fragment"; //$NON-NLS-1$

	private final IProject project;
	private final URI projectURI;
	private final IFile modelFile;
	private final EObject model;

	private final Switch<String> engine = new Engine();

	public Evaluator(IProject project, IFile modelFile, EObject model) {
		super();

		this.project = project;
		this.projectURI = URI.createPlatformResourceURI(project.getName() + "/", true); //$NON-NLS-1$
		this.modelFile = modelFile;
		this.model = model;
	}

	public String evaluate(Value value) {
		return Strings.nullToEmpty(engine.doSwitch(value));
	}

	public boolean validate(String actualValue, Value expectedValue) {
		String expected = evaluate(expectedValue);
		boolean result = Objects.equals(actualValue, expected);

		if (!result && isResource(expectedValue)) {
			result = resourceEquals(actualValue, expected);
		}

		return result;
	}

	protected final boolean isResource(Value value) {
		return value instanceof Variable && RESOURCE.equals(((Variable) value).getName());
	}

	protected boolean resourceEquals(String pathOrURI1, String pathOrURI2) {
		// Easy case
		boolean result = Objects.equals(pathOrURI1, pathOrURI2);

		if (!result && pathOrURI1 != null && pathOrURI2 != null) {
			// maybe one is a path and the other not
			URI uri1 = URI.createURI(pathOrURI1, true);
			URI uri2 = URI.createURI(pathOrURI2, true);

			if (uri1.isRelative()) {
				uri1 = uri1.resolve(projectURI);
			}
			if (uri2.isRelative()) {
				uri2 = uri2.resolve(projectURI);
			}

			result = uri1.equals(uri2);
		}

		return result;
	}

	Object evaluateVariable(String name, Set<String> options) {
		Object result = null;

		// TODO(cwd): Pluggable variable resolvers
		switch (name) {
		case BUNDLE_NAME:
			result = project.getName();
			break;
		case RESOURCE:
			if (options.contains(OPTION_NAME)) {
				result = modelFile.getName();
			} else if (options.contains(OPTION_PATH)) {
				result = modelFile.getProjectRelativePath();
			} else if (options.contains(OPTION_URI)) {
				result = model.eResource().getURI();
			}
			break;
		case VAR_URI:
			result = EcoreUtil.getURI(model);

			if (options.contains(OPTION_FRAGMENT)) {
				result = ((URI) result).fragment();
			}

			break;
		default:
			// Unresolved variable
			result = String.format("{%s}", name); //$NON-NLS-1$
			break;
		}

		return result;
	}

	Object evaluateFeaturePath(Iterable<? extends String> path) {
		Object result = model;

		for (String next : path) {
			if (!(result instanceof EObject)) {
				break; // Nothing to evaluate the feature on
			}

			EObject object = (EObject) result;
			EStructuralFeature feature = object.eClass().getEStructuralFeature(next);
			if (feature == null) {
				result = null;
				break; // No such feature to evaluate
			}

			Object value = object.eGet(feature);

			if (FeatureMapUtil.isMany(object, feature) && value instanceof List) {
				List<?> list = (List<?>) value;
				if (list.isEmpty()) {
					result = null;
					break;
				}
				result = list.get(0);
			} else {
				result = value;
			}
		}

		return result;
	}

	//
	// Nested types
	//

	final class Engine extends ProjectRulesSwitch<String> {

		@Override
		public String caseLiteralString(LiteralString object) {
			return object.getValue();
		}

		@Override
		public String caseVariable(Variable object) {
			Object result = evaluateVariable(object.getName(), Set.copyOf(object.getOptions()));
			return (result == null) ? null : String.valueOf(result);
		}

		@Override
		public String caseFeaturePath(FeaturePath object) {
			Object result = evaluateFeaturePath(object.getPath());
			return (result == null) ? null : String.valueOf(result);
		}

		@Override
		public String caseExpression(Expression object) {
			StringBuilder result = new StringBuilder();

			for (Value next : object.getValues()) {
				String part = doSwitch(next);
				if (part != null) {
					result.append(part);
				}
			}

			return result.toString();
		}

		@Override
		public String caseOpaqueValue(OpaqueValue object) {
			return doSwitch(object.parse());
		}

		@Override
		public String caseValue(Value object) {
			return object.toString();
		}

	}

}
