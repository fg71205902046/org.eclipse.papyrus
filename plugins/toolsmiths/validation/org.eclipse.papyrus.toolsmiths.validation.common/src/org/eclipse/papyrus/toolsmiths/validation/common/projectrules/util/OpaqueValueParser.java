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
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Expression;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.FeaturePath;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.LiteralString;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.OpaqueValue;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesFactory;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Variable;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * A parser of {@link OpaqueValue} expressions.
 */
public class OpaqueValueParser {

	// TODO(cwd): Pluggable parser providers for tooling extensions to define custom value types

	private final OpaqueValue opaqueValue;

	private final List<ParseRule> rules = List.of(
			ParseRule.topLevelRule("=", this::parseExpression),
			ParseRule.rule("{=", this::isFeaturePath, this::parseFeaturePath),
			ParseRule.rule("{", this::isVariable, this::parseVariable),
			ParseRule.rule(this::parseLiteral));

	private final Pattern variablePattern = Pattern.compile("\\{[^}]*\\}");

	public OpaqueValueParser(OpaqueValue opaqueValue) {
		super();

		this.opaqueValue = opaqueValue;
	}

	public Value parse() {
		Value result;

		String text = opaqueValue.getText();
		if (text != null) {
			result = parse(text.trim(), false);
		} else {
			result = nothing();
		}

		return result;
	}

	private Value parse(String text, boolean nested) {
		return Iterables.tryFind(rules(nested), rule -> rule.match(text))
				.transform(rule -> rule.apply(text))
				.or(this::nothing);
	}

	private Iterable<ParseRule> rules(boolean nested) {
		return nested ? Iterables.filter(rules, Predicates.not(ParseRule::isTopLevel)) : rules;
	}

	private boolean isVariable(String text) {
		return text.length() > 1 && text.charAt(0) == '{' && text.charAt(text.length() - 1) == '}';
	}

	private boolean isFeaturePath(String text) {
		return text.length() > 2 && text.charAt(0) == '{' && text.charAt(1) == '=' && text.charAt(text.length() - 1) == '}';
	}

	private Value nothing() {
		return parseLiteral(""); //$NON-NLS-1$
	}

	private LiteralString parseLiteral(String text) {
		LiteralString result = ProjectRulesFactory.eINSTANCE.createLiteralString();
		result.setValue(text);
		return result;
	}

	private Variable parseVariable(String text) {
		Variable result;

		try (Scanner scanner = new Scanner(text.substring(1, text.length() - 1)).useDelimiter("\\s*\\|\\s*")) { //$NON-NLS-1$
			if (scanner.hasNext()) {
				result = ProjectRulesFactory.eINSTANCE.createVariable();

				// Set the name
				result.setName(scanner.next());

				// Accumulate the options
				scanner.forEachRemaining(result.getOptions()::add);
			} else {
				// Oops! Not a valid variable
				result = null;
			}
		}

		return result;
	}

	private FeaturePath parseFeaturePath(String text) {
		FeaturePath result;

		try (Scanner scanner = new Scanner(text.substring(2, text.length() - 1)).useDelimiter("\\s*\\.\\s*")) { //$NON-NLS-1$
			if (scanner.hasNext()) {
				result = ProjectRulesFactory.eINSTANCE.createFeaturePath();

				// Add the path segments
				scanner.forEachRemaining(result.getPath()::add);
			} else {
				// Oops! Not a valid feature path
				result = null;
			}
		}

		return result;
	}

	private Expression parseExpression(String text) {
		Expression result = ProjectRulesFactory.eINSTANCE.createExpression();
		Matcher variableMatcher = variablePattern.matcher(text);

		int literalStart = 1; // Skip over the initial '='
		while (literalStart < text.length() && variableMatcher.find(literalStart)) {
			if (variableMatcher.start() > literalStart) {
				result.getValues().add(parse(text.substring(literalStart, variableMatcher.start()), true));
			}

			// The next literal segment maybe starts after this variable
			literalStart = variableMatcher.end();
			result.getValues().add(parse(variableMatcher.group(), true));
		}

		if (literalStart < text.length()) {
			// Get the rest of the expression
			result.getValues().add(parse(text.substring(literalStart), true));
		}

		return result;
	}

	//
	// Nested types
	//

	private static final class ParseRule implements com.google.common.base.Function<String, Value> {
		private final boolean topLevel;
		private final String prefixMatch;
		private final Predicate<? super String> guard;
		private final Function<String, ? extends Value> rule;

		ParseRule(boolean topLevel, String prefixMatch, Predicate<? super String> guard, Function<String, ? extends Value> rule) {
			super();

			this.topLevel = topLevel;
			this.prefixMatch = prefixMatch;
			this.guard = guard;
			this.rule = rule;
		}

		static ParseRule topLevelRule(String prefixMatch, Predicate<? super String> guard, Function<String, ? extends Value> rule) {
			return new ParseRule(true, prefixMatch, guard, rule);
		}

		static ParseRule topLevelRule(String prefixMatch, Function<String, ? extends Value> rule) {
			return topLevelRule(prefixMatch, Predicates.alwaysTrue(), rule);
		}

		static ParseRule rule(String prefixMatch, Predicate<? super String> guard, Function<String, ? extends Value> rule) {
			return new ParseRule(false, prefixMatch, guard, rule);
		}

		static ParseRule rule(String prefixMatch, Function<String, ? extends Value> rule) {
			return rule(prefixMatch, Predicates.alwaysTrue(), rule);
		}

		static ParseRule rule(Function<String, ? extends Value> rule) {
			return rule("", rule); //$NON-NLS-1$
		}

		boolean isTopLevel() {
			return topLevel;
		}

		boolean match(String input) {
			return (prefixMatch.isEmpty() || input.startsWith(prefixMatch)) && guard.test(input);
		}

		@Override
		public Value apply(String input) {
			return rule.apply(input);
		}

	}

}
