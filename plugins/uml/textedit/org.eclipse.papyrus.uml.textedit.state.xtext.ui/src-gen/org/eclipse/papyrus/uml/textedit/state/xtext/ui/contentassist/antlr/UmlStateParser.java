/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.textedit.state.xtext.ui.contentassist.antlr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.RecognitionException;
import org.eclipse.papyrus.uml.textedit.state.xtext.services.UmlStateGrammarAccess;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

public class UmlStateParser extends AbstractContentAssistParser {
	
	@Inject
	private UmlStateGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.eclipse.papyrus.uml.textedit.state.xtext.ui.contentassist.antlr.internal.InternalUmlStateParser createParser() {
		org.eclipse.papyrus.uml.textedit.state.xtext.ui.contentassist.antlr.internal.InternalUmlStateParser result = new org.eclipse.papyrus.uml.textedit.state.xtext.ui.contentassist.antlr.internal.InternalUmlStateParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getBehaviorKindAccess().getAlternatives(), "rule__BehaviorKind__Alternatives");
					put(grammarAccess.getStateRuleAccess().getGroup(), "rule__StateRule__Group__0");
					put(grammarAccess.getStateRuleAccess().getGroup_1(), "rule__StateRule__Group_1__0");
					put(grammarAccess.getSubmachineRuleAccess().getGroup(), "rule__SubmachineRule__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup(), "rule__QualifiedName__Group__0");
					put(grammarAccess.getEntryRuleAccess().getGroup(), "rule__EntryRule__Group__0");
					put(grammarAccess.getDoRuleAccess().getGroup(), "rule__DoRule__Group__0");
					put(grammarAccess.getExitRuleAccess().getGroup(), "rule__ExitRule__Group__0");
					put(grammarAccess.getStateRuleAccess().getNameAssignment_0(), "rule__StateRule__NameAssignment_0");
					put(grammarAccess.getStateRuleAccess().getSubmachineAssignment_1_1(), "rule__StateRule__SubmachineAssignment_1_1");
					put(grammarAccess.getStateRuleAccess().getEntryAssignment_2_0(), "rule__StateRule__EntryAssignment_2_0");
					put(grammarAccess.getStateRuleAccess().getDoAssignment_2_1(), "rule__StateRule__DoAssignment_2_1");
					put(grammarAccess.getStateRuleAccess().getExitAssignment_2_2(), "rule__StateRule__ExitAssignment_2_2");
					put(grammarAccess.getSubmachineRuleAccess().getPathAssignment_0(), "rule__SubmachineRule__PathAssignment_0");
					put(grammarAccess.getSubmachineRuleAccess().getSubmachineAssignment_1(), "rule__SubmachineRule__SubmachineAssignment_1");
					put(grammarAccess.getQualifiedNameAccess().getPathAssignment_0(), "rule__QualifiedName__PathAssignment_0");
					put(grammarAccess.getQualifiedNameAccess().getRemainingAssignment_2(), "rule__QualifiedName__RemainingAssignment_2");
					put(grammarAccess.getEntryRuleAccess().getKindAssignment_1(), "rule__EntryRule__KindAssignment_1");
					put(grammarAccess.getEntryRuleAccess().getBehaviorNameAssignment_2(), "rule__EntryRule__BehaviorNameAssignment_2");
					put(grammarAccess.getDoRuleAccess().getKindAssignment_1(), "rule__DoRule__KindAssignment_1");
					put(grammarAccess.getDoRuleAccess().getBehaviorNameAssignment_2(), "rule__DoRule__BehaviorNameAssignment_2");
					put(grammarAccess.getExitRuleAccess().getKindAssignment_1(), "rule__ExitRule__KindAssignment_1");
					put(grammarAccess.getExitRuleAccess().getBehaviorNameAssignment_2(), "rule__ExitRule__BehaviorNameAssignment_2");
					put(grammarAccess.getStateRuleAccess().getUnorderedGroup_2(), "rule__StateRule__UnorderedGroup_2");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.eclipse.papyrus.uml.textedit.state.xtext.ui.contentassist.antlr.internal.InternalUmlStateParser typedParser = (org.eclipse.papyrus.uml.textedit.state.xtext.ui.contentassist.antlr.internal.InternalUmlStateParser) parser;
			typedParser.entryRuleStateRule();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public UmlStateGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(UmlStateGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
