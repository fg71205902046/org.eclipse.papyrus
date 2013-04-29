/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.textedit.port.xtext.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.eclipse.papyrus.uml.textedit.port.xtext.services.UmlPortGrammarAccess;

public class UmlPortParser extends AbstractContentAssistParser {
	
	@Inject
	private UmlPortGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.eclipse.papyrus.uml.textedit.port.xtext.ui.contentassist.antlr.internal.InternalUmlPortParser createParser() {
		org.eclipse.papyrus.uml.textedit.port.xtext.ui.contentassist.antlr.internal.InternalUmlPortParser result = new org.eclipse.papyrus.uml.textedit.port.xtext.ui.contentassist.antlr.internal.InternalUmlPortParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getPortRuleAccess().getAlternatives_5(), "rule__PortRule__Alternatives_5");
					put(grammarAccess.getUnlimitedLiteralAccess().getAlternatives(), "rule__UnlimitedLiteral__Alternatives");
					put(grammarAccess.getModifierSpecificationAccess().getAlternatives(), "rule__ModifierSpecification__Alternatives");
					put(grammarAccess.getVisibilityKindAccess().getAlternatives(), "rule__VisibilityKind__Alternatives");
					put(grammarAccess.getModifierKindAccess().getAlternatives(), "rule__ModifierKind__Alternatives");
					put(grammarAccess.getPortRuleAccess().getGroup(), "rule__PortRule__Group__0");
					put(grammarAccess.getTypeRuleAccess().getGroup(), "rule__TypeRule__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup(), "rule__QualifiedName__Group__0");
					put(grammarAccess.getMultiplicityRuleAccess().getGroup(), "rule__MultiplicityRule__Group__0");
					put(grammarAccess.getMultiplicityRuleAccess().getGroup_2(), "rule__MultiplicityRule__Group_2__0");
					put(grammarAccess.getModifiersRuleAccess().getGroup(), "rule__ModifiersRule__Group__0");
					put(grammarAccess.getModifiersRuleAccess().getGroup_2(), "rule__ModifiersRule__Group_2__0");
					put(grammarAccess.getRedefinesRuleAccess().getGroup(), "rule__RedefinesRule__Group__0");
					put(grammarAccess.getSubsetsRuleAccess().getGroup(), "rule__SubsetsRule__Group__0");
					put(grammarAccess.getDefaultValueRuleAccess().getGroup(), "rule__DefaultValueRule__Group__0");
					put(grammarAccess.getPortRuleAccess().getVisibilityAssignment_0(), "rule__PortRule__VisibilityAssignment_0");
					put(grammarAccess.getPortRuleAccess().getIsDerivedAssignment_1(), "rule__PortRule__IsDerivedAssignment_1");
					put(grammarAccess.getPortRuleAccess().getNameAssignment_2(), "rule__PortRule__NameAssignment_2");
					put(grammarAccess.getPortRuleAccess().getIsConjugatedAssignment_4(), "rule__PortRule__IsConjugatedAssignment_4");
					put(grammarAccess.getPortRuleAccess().getTypeAssignment_5_0(), "rule__PortRule__TypeAssignment_5_0");
					put(grammarAccess.getPortRuleAccess().getMultiplicityAssignment_6(), "rule__PortRule__MultiplicityAssignment_6");
					put(grammarAccess.getPortRuleAccess().getModifiersAssignment_7(), "rule__PortRule__ModifiersAssignment_7");
					put(grammarAccess.getPortRuleAccess().getDefaultAssignment_8(), "rule__PortRule__DefaultAssignment_8");
					put(grammarAccess.getTypeRuleAccess().getPathAssignment_0(), "rule__TypeRule__PathAssignment_0");
					put(grammarAccess.getTypeRuleAccess().getTypeAssignment_1(), "rule__TypeRule__TypeAssignment_1");
					put(grammarAccess.getQualifiedNameAccess().getPathAssignment_0(), "rule__QualifiedName__PathAssignment_0");
					put(grammarAccess.getQualifiedNameAccess().getRemainingAssignment_2(), "rule__QualifiedName__RemainingAssignment_2");
					put(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_1(), "rule__MultiplicityRule__BoundsAssignment_1");
					put(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_2_1(), "rule__MultiplicityRule__BoundsAssignment_2_1");
					put(grammarAccess.getBoundSpecificationAccess().getValueAssignment(), "rule__BoundSpecification__ValueAssignment");
					put(grammarAccess.getModifiersRuleAccess().getValuesAssignment_1(), "rule__ModifiersRule__ValuesAssignment_1");
					put(grammarAccess.getModifiersRuleAccess().getValuesAssignment_2_1(), "rule__ModifiersRule__ValuesAssignment_2_1");
					put(grammarAccess.getModifierSpecificationAccess().getValueAssignment_0(), "rule__ModifierSpecification__ValueAssignment_0");
					put(grammarAccess.getModifierSpecificationAccess().getRedefinesAssignment_1(), "rule__ModifierSpecification__RedefinesAssignment_1");
					put(grammarAccess.getModifierSpecificationAccess().getSubsetsAssignment_2(), "rule__ModifierSpecification__SubsetsAssignment_2");
					put(grammarAccess.getRedefinesRuleAccess().getPortAssignment_1(), "rule__RedefinesRule__PortAssignment_1");
					put(grammarAccess.getSubsetsRuleAccess().getPortAssignment_1(), "rule__SubsetsRule__PortAssignment_1");
					put(grammarAccess.getDefaultValueRuleAccess().getDefaultAssignment_1(), "rule__DefaultValueRule__DefaultAssignment_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.eclipse.papyrus.uml.textedit.port.xtext.ui.contentassist.antlr.internal.InternalUmlPortParser typedParser = (org.eclipse.papyrus.uml.textedit.port.xtext.ui.contentassist.antlr.internal.InternalUmlPortParser) parser;
			typedParser.entryRulePortRule();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public UmlPortGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(UmlPortGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
