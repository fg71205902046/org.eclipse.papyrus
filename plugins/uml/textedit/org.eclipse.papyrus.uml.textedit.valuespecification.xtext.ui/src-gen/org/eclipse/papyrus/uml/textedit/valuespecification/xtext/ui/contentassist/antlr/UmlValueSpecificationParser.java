/*
 * generated by Xtext
 */
package org.eclipse.papyrus.uml.textedit.valuespecification.xtext.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.eclipse.papyrus.uml.textedit.valuespecification.xtext.services.UmlValueSpecificationGrammarAccess;

public class UmlValueSpecificationParser extends AbstractContentAssistParser {
	
	@Inject
	private UmlValueSpecificationGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.eclipse.papyrus.uml.textedit.valuespecification.xtext.ui.contentassist.antlr.internal.InternalUmlValueSpecificationParser createParser() {
		org.eclipse.papyrus.uml.textedit.valuespecification.xtext.ui.contentassist.antlr.internal.InternalUmlValueSpecificationParser result = new org.eclipse.papyrus.uml.textedit.valuespecification.xtext.ui.contentassist.antlr.internal.InternalUmlValueSpecificationParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getAbstractRuleAccess().getAlternatives(), "rule__AbstractRule__Alternatives");
					put(grammarAccess.getAbstractRuleAccess().getAlternatives_0_2(), "rule__AbstractRule__Alternatives_0_2");
					put(grammarAccess.getAbstractRuleAccess().getValueAlternatives_0_2_0_0(), "rule__AbstractRule__ValueAlternatives_0_2_0_0");
					put(grammarAccess.getLiteralBooleanRuleAccess().getValueAlternatives_0(), "rule__LiteralBooleanRule__ValueAlternatives_0");
					put(grammarAccess.getVisibilityKindAccess().getAlternatives(), "rule__VisibilityKind__Alternatives");
					put(grammarAccess.getAbstractRuleAccess().getGroup_0(), "rule__AbstractRule__Group_0__0");
					put(grammarAccess.getAbstractRuleAccess().getGroup_0_1(), "rule__AbstractRule__Group_0_1__0");
					put(grammarAccess.getAbstractRuleAccess().getVisibilityAssignment_0_0(), "rule__AbstractRule__VisibilityAssignment_0_0");
					put(grammarAccess.getAbstractRuleAccess().getNameAssignment_0_1_0(), "rule__AbstractRule__NameAssignment_0_1_0");
					put(grammarAccess.getAbstractRuleAccess().getValueAssignment_0_2_0(), "rule__AbstractRule__ValueAssignment_0_2_0");
					put(grammarAccess.getAbstractRuleAccess().getInstanceSpecificationAssignment_0_2_1(), "rule__AbstractRule__InstanceSpecificationAssignment_0_2_1");
					put(grammarAccess.getAbstractRuleAccess().getUndefinedAssignment_1(), "rule__AbstractRule__UndefinedAssignment_1");
					put(grammarAccess.getLiteralBooleanRuleAccess().getValueAssignment(), "rule__LiteralBooleanRule__ValueAssignment");
					put(grammarAccess.getLiteralIntegerOrUnlimitedNaturalRuleAccess().getValueAssignment(), "rule__LiteralIntegerOrUnlimitedNaturalRule__ValueAssignment");
					put(grammarAccess.getLiteralRealRuleAccess().getValueAssignment(), "rule__LiteralRealRule__ValueAssignment");
					put(grammarAccess.getLiteralNullRuleAccess().getValueAssignment(), "rule__LiteralNullRule__ValueAssignment");
					put(grammarAccess.getLiteralStringRuleAccess().getValueAssignment(), "rule__LiteralStringRule__ValueAssignment");
					put(grammarAccess.getUndefinedRuleAccess().getValueAssignment(), "rule__UndefinedRule__ValueAssignment");
					put(grammarAccess.getVisibilityKindAccess().getPublicAssignment_0(), "rule__VisibilityKind__PublicAssignment_0");
					put(grammarAccess.getVisibilityKindAccess().getPrivateAssignment_1(), "rule__VisibilityKind__PrivateAssignment_1");
					put(grammarAccess.getVisibilityKindAccess().getProtectedAssignment_2(), "rule__VisibilityKind__ProtectedAssignment_2");
					put(grammarAccess.getVisibilityKindAccess().getPackageAssignment_3(), "rule__VisibilityKind__PackageAssignment_3");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.eclipse.papyrus.uml.textedit.valuespecification.xtext.ui.contentassist.antlr.internal.InternalUmlValueSpecificationParser typedParser = (org.eclipse.papyrus.uml.textedit.valuespecification.xtext.ui.contentassist.antlr.internal.InternalUmlValueSpecificationParser) parser;
			typedParser.entryRuleAbstractRule();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public UmlValueSpecificationGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(UmlValueSpecificationGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
