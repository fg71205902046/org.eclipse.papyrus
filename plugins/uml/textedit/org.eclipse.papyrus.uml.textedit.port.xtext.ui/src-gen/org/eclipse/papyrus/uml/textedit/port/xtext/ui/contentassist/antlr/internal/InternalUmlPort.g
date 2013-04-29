/*
* generated by Xtext
*/
grammar InternalUmlPort;

options {
	superClass=AbstractInternalContentAssistParser;
	
}

@lexer::header {
package org.eclipse.papyrus.uml.textedit.port.xtext.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package org.eclipse.papyrus.uml.textedit.port.xtext.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.eclipse.papyrus.uml.textedit.port.xtext.services.UmlPortGrammarAccess;

}

@parser::members {
 
 	private UmlPortGrammarAccess grammarAccess;
 	
    public void setGrammarAccess(UmlPortGrammarAccess grammarAccess) {
    	this.grammarAccess = grammarAccess;
    }
    
    @Override
    protected Grammar getGrammar() {
    	return grammarAccess.getGrammar();
    }
    
    @Override
    protected String getValueForTokenName(String tokenName) {
    	return tokenName;
    }

}




// Entry rule entryRulePortRule
entryRulePortRule 
:
{ before(grammarAccess.getPortRuleRule()); }
	 rulePortRule
{ after(grammarAccess.getPortRuleRule()); } 
	 EOF 
;

// Rule PortRule
rulePortRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getPortRuleAccess().getGroup()); }
(rule__PortRule__Group__0)
{ after(grammarAccess.getPortRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleTypeRule
entryRuleTypeRule 
:
{ before(grammarAccess.getTypeRuleRule()); }
	 ruleTypeRule
{ after(grammarAccess.getTypeRuleRule()); } 
	 EOF 
;

// Rule TypeRule
ruleTypeRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getTypeRuleAccess().getGroup()); }
(rule__TypeRule__Group__0)
{ after(grammarAccess.getTypeRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleQualifiedName
entryRuleQualifiedName 
:
{ before(grammarAccess.getQualifiedNameRule()); }
	 ruleQualifiedName
{ after(grammarAccess.getQualifiedNameRule()); } 
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getQualifiedNameAccess().getGroup()); }
(rule__QualifiedName__Group__0)
{ after(grammarAccess.getQualifiedNameAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleMultiplicityRule
entryRuleMultiplicityRule 
:
{ before(grammarAccess.getMultiplicityRuleRule()); }
	 ruleMultiplicityRule
{ after(grammarAccess.getMultiplicityRuleRule()); } 
	 EOF 
;

// Rule MultiplicityRule
ruleMultiplicityRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getGroup()); }
(rule__MultiplicityRule__Group__0)
{ after(grammarAccess.getMultiplicityRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleBoundSpecification
entryRuleBoundSpecification 
:
{ before(grammarAccess.getBoundSpecificationRule()); }
	 ruleBoundSpecification
{ after(grammarAccess.getBoundSpecificationRule()); } 
	 EOF 
;

// Rule BoundSpecification
ruleBoundSpecification
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getBoundSpecificationAccess().getValueAssignment()); }
(rule__BoundSpecification__ValueAssignment)
{ after(grammarAccess.getBoundSpecificationAccess().getValueAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleUnlimitedLiteral
entryRuleUnlimitedLiteral 
:
{ before(grammarAccess.getUnlimitedLiteralRule()); }
	 ruleUnlimitedLiteral
{ after(grammarAccess.getUnlimitedLiteralRule()); } 
	 EOF 
;

// Rule UnlimitedLiteral
ruleUnlimitedLiteral
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getUnlimitedLiteralAccess().getAlternatives()); }
(rule__UnlimitedLiteral__Alternatives)
{ after(grammarAccess.getUnlimitedLiteralAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleModifiersRule
entryRuleModifiersRule 
:
{ before(grammarAccess.getModifiersRuleRule()); }
	 ruleModifiersRule
{ after(grammarAccess.getModifiersRuleRule()); } 
	 EOF 
;

// Rule ModifiersRule
ruleModifiersRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getModifiersRuleAccess().getGroup()); }
(rule__ModifiersRule__Group__0)
{ after(grammarAccess.getModifiersRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleModifierSpecification
entryRuleModifierSpecification 
:
{ before(grammarAccess.getModifierSpecificationRule()); }
	 ruleModifierSpecification
{ after(grammarAccess.getModifierSpecificationRule()); } 
	 EOF 
;

// Rule ModifierSpecification
ruleModifierSpecification
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getModifierSpecificationAccess().getAlternatives()); }
(rule__ModifierSpecification__Alternatives)
{ after(grammarAccess.getModifierSpecificationAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleRedefinesRule
entryRuleRedefinesRule 
:
{ before(grammarAccess.getRedefinesRuleRule()); }
	 ruleRedefinesRule
{ after(grammarAccess.getRedefinesRuleRule()); } 
	 EOF 
;

// Rule RedefinesRule
ruleRedefinesRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getRedefinesRuleAccess().getGroup()); }
(rule__RedefinesRule__Group__0)
{ after(grammarAccess.getRedefinesRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleSubsetsRule
entryRuleSubsetsRule 
:
{ before(grammarAccess.getSubsetsRuleRule()); }
	 ruleSubsetsRule
{ after(grammarAccess.getSubsetsRuleRule()); } 
	 EOF 
;

// Rule SubsetsRule
ruleSubsetsRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getSubsetsRuleAccess().getGroup()); }
(rule__SubsetsRule__Group__0)
{ after(grammarAccess.getSubsetsRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleDefaultValueRule
entryRuleDefaultValueRule 
:
{ before(grammarAccess.getDefaultValueRuleRule()); }
	 ruleDefaultValueRule
{ after(grammarAccess.getDefaultValueRuleRule()); } 
	 EOF 
;

// Rule DefaultValueRule
ruleDefaultValueRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getDefaultValueRuleAccess().getGroup()); }
(rule__DefaultValueRule__Group__0)
{ after(grammarAccess.getDefaultValueRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}




// Rule VisibilityKind
ruleVisibilityKind
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVisibilityKindAccess().getAlternatives()); }
(rule__VisibilityKind__Alternatives)
{ after(grammarAccess.getVisibilityKindAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Rule ModifierKind
ruleModifierKind
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifierKindAccess().getAlternatives()); }
(rule__ModifierKind__Alternatives)
{ after(grammarAccess.getModifierKindAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



rule__PortRule__Alternatives_5
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getTypeAssignment_5_0()); }
(rule__PortRule__TypeAssignment_5_0)
{ after(grammarAccess.getPortRuleAccess().getTypeAssignment_5_0()); }
)

    |(
{ before(grammarAccess.getPortRuleAccess().getUndefinedKeyword_5_1()); }

	'<Undefined>' 

{ after(grammarAccess.getPortRuleAccess().getUndefinedKeyword_5_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__UnlimitedLiteral__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getUnlimitedLiteralAccess().getINTTerminalRuleCall_0()); }
	RULE_INT
{ after(grammarAccess.getUnlimitedLiteralAccess().getINTTerminalRuleCall_0()); }
)

    |(
{ before(grammarAccess.getUnlimitedLiteralAccess().getAsteriskKeyword_1()); }

	'*' 

{ after(grammarAccess.getUnlimitedLiteralAccess().getAsteriskKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifierSpecification__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifierSpecificationAccess().getValueAssignment_0()); }
(rule__ModifierSpecification__ValueAssignment_0)
{ after(grammarAccess.getModifierSpecificationAccess().getValueAssignment_0()); }
)

    |(
{ before(grammarAccess.getModifierSpecificationAccess().getRedefinesAssignment_1()); }
(rule__ModifierSpecification__RedefinesAssignment_1)
{ after(grammarAccess.getModifierSpecificationAccess().getRedefinesAssignment_1()); }
)

    |(
{ before(grammarAccess.getModifierSpecificationAccess().getSubsetsAssignment_2()); }
(rule__ModifierSpecification__SubsetsAssignment_2)
{ after(grammarAccess.getModifierSpecificationAccess().getSubsetsAssignment_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__VisibilityKind__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVisibilityKindAccess().getPublicEnumLiteralDeclaration_0()); }
(	'+' 
)
{ after(grammarAccess.getVisibilityKindAccess().getPublicEnumLiteralDeclaration_0()); }
)

    |(
{ before(grammarAccess.getVisibilityKindAccess().getPrivateEnumLiteralDeclaration_1()); }
(	'-' 
)
{ after(grammarAccess.getVisibilityKindAccess().getPrivateEnumLiteralDeclaration_1()); }
)

    |(
{ before(grammarAccess.getVisibilityKindAccess().getProtectedEnumLiteralDeclaration_2()); }
(	'#' 
)
{ after(grammarAccess.getVisibilityKindAccess().getProtectedEnumLiteralDeclaration_2()); }
)

    |(
{ before(grammarAccess.getVisibilityKindAccess().getPackageEnumLiteralDeclaration_3()); }
(	'~' 
)
{ after(grammarAccess.getVisibilityKindAccess().getPackageEnumLiteralDeclaration_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifierKind__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifierKindAccess().getReadOnlyEnumLiteralDeclaration_0()); }
(	'readOnly' 
)
{ after(grammarAccess.getModifierKindAccess().getReadOnlyEnumLiteralDeclaration_0()); }
)

    |(
{ before(grammarAccess.getModifierKindAccess().getUnionEnumLiteralDeclaration_1()); }
(	'union' 
)
{ after(grammarAccess.getModifierKindAccess().getUnionEnumLiteralDeclaration_1()); }
)

    |(
{ before(grammarAccess.getModifierKindAccess().getOrderedEnumLiteralDeclaration_2()); }
(	'ordered' 
)
{ after(grammarAccess.getModifierKindAccess().getOrderedEnumLiteralDeclaration_2()); }
)

    |(
{ before(grammarAccess.getModifierKindAccess().getUniqueEnumLiteralDeclaration_3()); }
(	'unique' 
)
{ after(grammarAccess.getModifierKindAccess().getUniqueEnumLiteralDeclaration_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}



rule__PortRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PortRule__Group__0__Impl
	rule__PortRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getVisibilityAssignment_0()); }
(rule__PortRule__VisibilityAssignment_0)
{ after(grammarAccess.getPortRuleAccess().getVisibilityAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__PortRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PortRule__Group__1__Impl
	rule__PortRule__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getIsDerivedAssignment_1()); }
(rule__PortRule__IsDerivedAssignment_1)?
{ after(grammarAccess.getPortRuleAccess().getIsDerivedAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__PortRule__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PortRule__Group__2__Impl
	rule__PortRule__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getNameAssignment_2()); }
(rule__PortRule__NameAssignment_2)
{ after(grammarAccess.getPortRuleAccess().getNameAssignment_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__PortRule__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PortRule__Group__3__Impl
	rule__PortRule__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getColonKeyword_3()); }

	':' 

{ after(grammarAccess.getPortRuleAccess().getColonKeyword_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__PortRule__Group__4
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PortRule__Group__4__Impl
	rule__PortRule__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__Group__4__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getIsConjugatedAssignment_4()); }
(rule__PortRule__IsConjugatedAssignment_4)?
{ after(grammarAccess.getPortRuleAccess().getIsConjugatedAssignment_4()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__PortRule__Group__5
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PortRule__Group__5__Impl
	rule__PortRule__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__Group__5__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getAlternatives_5()); }
(rule__PortRule__Alternatives_5)
{ after(grammarAccess.getPortRuleAccess().getAlternatives_5()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__PortRule__Group__6
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PortRule__Group__6__Impl
	rule__PortRule__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__Group__6__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getMultiplicityAssignment_6()); }
(rule__PortRule__MultiplicityAssignment_6)?
{ after(grammarAccess.getPortRuleAccess().getMultiplicityAssignment_6()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__PortRule__Group__7
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PortRule__Group__7__Impl
	rule__PortRule__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__Group__7__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getModifiersAssignment_7()); }
(rule__PortRule__ModifiersAssignment_7)?
{ after(grammarAccess.getPortRuleAccess().getModifiersAssignment_7()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__PortRule__Group__8
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__PortRule__Group__8__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__Group__8__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getDefaultAssignment_8()); }
(rule__PortRule__DefaultAssignment_8)?
{ after(grammarAccess.getPortRuleAccess().getDefaultAssignment_8()); }
)

;
finally {
	restoreStackSize(stackSize);
}




















rule__TypeRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__TypeRule__Group__0__Impl
	rule__TypeRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getTypeRuleAccess().getPathAssignment_0()); }
(rule__TypeRule__PathAssignment_0)?
{ after(grammarAccess.getTypeRuleAccess().getPathAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__TypeRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__TypeRule__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getTypeRuleAccess().getTypeAssignment_1()); }
(rule__TypeRule__TypeAssignment_1)
{ after(grammarAccess.getTypeRuleAccess().getTypeAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__QualifiedName__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__QualifiedName__Group__0__Impl
	rule__QualifiedName__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedNameAccess().getPathAssignment_0()); }
(rule__QualifiedName__PathAssignment_0)
{ after(grammarAccess.getQualifiedNameAccess().getPathAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedName__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__QualifiedName__Group__1__Impl
	rule__QualifiedName__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedNameAccess().getColonColonKeyword_1()); }

	'::' 

{ after(grammarAccess.getQualifiedNameAccess().getColonColonKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedName__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__QualifiedName__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedNameAccess().getRemainingAssignment_2()); }
(rule__QualifiedName__RemainingAssignment_2)?
{ after(grammarAccess.getQualifiedNameAccess().getRemainingAssignment_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__MultiplicityRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group__0__Impl
	rule__MultiplicityRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getLeftSquareBracketKeyword_0()); }

	'[' 

{ after(grammarAccess.getMultiplicityRuleAccess().getLeftSquareBracketKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MultiplicityRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group__1__Impl
	rule__MultiplicityRule__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_1()); }
(rule__MultiplicityRule__BoundsAssignment_1)
{ after(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MultiplicityRule__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group__2__Impl
	rule__MultiplicityRule__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getGroup_2()); }
(rule__MultiplicityRule__Group_2__0)?
{ after(grammarAccess.getMultiplicityRuleAccess().getGroup_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MultiplicityRule__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getRightSquareBracketKeyword_3()); }

	']' 

{ after(grammarAccess.getMultiplicityRuleAccess().getRightSquareBracketKeyword_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}










rule__MultiplicityRule__Group_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group_2__0__Impl
	rule__MultiplicityRule__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getFullStopFullStopKeyword_2_0()); }

	'..' 

{ after(grammarAccess.getMultiplicityRuleAccess().getFullStopFullStopKeyword_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MultiplicityRule__Group_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_2_1()); }
(rule__MultiplicityRule__BoundsAssignment_2_1)
{ after(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__ModifiersRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group__0__Impl
	rule__ModifiersRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getLeftCurlyBracketKeyword_0()); }

	'{' 

{ after(grammarAccess.getModifiersRuleAccess().getLeftCurlyBracketKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ModifiersRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group__1__Impl
	rule__ModifiersRule__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getValuesAssignment_1()); }
(rule__ModifiersRule__ValuesAssignment_1)
{ after(grammarAccess.getModifiersRuleAccess().getValuesAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ModifiersRule__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group__2__Impl
	rule__ModifiersRule__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getGroup_2()); }
(rule__ModifiersRule__Group_2__0)*
{ after(grammarAccess.getModifiersRuleAccess().getGroup_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ModifiersRule__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getRightCurlyBracketKeyword_3()); }

	'}' 

{ after(grammarAccess.getModifiersRuleAccess().getRightCurlyBracketKeyword_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}










rule__ModifiersRule__Group_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group_2__0__Impl
	rule__ModifiersRule__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getCommaKeyword_2_0()); }

	',' 

{ after(grammarAccess.getModifiersRuleAccess().getCommaKeyword_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ModifiersRule__Group_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getValuesAssignment_2_1()); }
(rule__ModifiersRule__ValuesAssignment_2_1)
{ after(grammarAccess.getModifiersRuleAccess().getValuesAssignment_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__RedefinesRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RedefinesRule__Group__0__Impl
	rule__RedefinesRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__RedefinesRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRedefinesRuleAccess().getRedefinesKeyword_0()); }

	'redefines' 

{ after(grammarAccess.getRedefinesRuleAccess().getRedefinesKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RedefinesRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RedefinesRule__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__RedefinesRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRedefinesRuleAccess().getPortAssignment_1()); }
(rule__RedefinesRule__PortAssignment_1)
{ after(grammarAccess.getRedefinesRuleAccess().getPortAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__SubsetsRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SubsetsRule__Group__0__Impl
	rule__SubsetsRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SubsetsRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSubsetsRuleAccess().getSubsetsKeyword_0()); }

	'subsets' 

{ after(grammarAccess.getSubsetsRuleAccess().getSubsetsKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__SubsetsRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SubsetsRule__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SubsetsRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSubsetsRuleAccess().getPortAssignment_1()); }
(rule__SubsetsRule__PortAssignment_1)
{ after(grammarAccess.getSubsetsRuleAccess().getPortAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__DefaultValueRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__DefaultValueRule__Group__0__Impl
	rule__DefaultValueRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DefaultValueRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getDefaultValueRuleAccess().getEqualsSignKeyword_0()); }

	'=' 

{ after(grammarAccess.getDefaultValueRuleAccess().getEqualsSignKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__DefaultValueRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__DefaultValueRule__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DefaultValueRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getDefaultValueRuleAccess().getDefaultAssignment_1()); }
(rule__DefaultValueRule__DefaultAssignment_1)
{ after(grammarAccess.getDefaultValueRuleAccess().getDefaultAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}







rule__PortRule__VisibilityAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getVisibilityVisibilityKindEnumRuleCall_0_0()); }
	ruleVisibilityKind{ after(grammarAccess.getPortRuleAccess().getVisibilityVisibilityKindEnumRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__IsDerivedAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getIsDerivedSolidusKeyword_1_0()); }
(
{ before(grammarAccess.getPortRuleAccess().getIsDerivedSolidusKeyword_1_0()); }

	'/' 

{ after(grammarAccess.getPortRuleAccess().getIsDerivedSolidusKeyword_1_0()); }
)

{ after(grammarAccess.getPortRuleAccess().getIsDerivedSolidusKeyword_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__NameAssignment_2
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getNameIDTerminalRuleCall_2_0()); }
	RULE_ID{ after(grammarAccess.getPortRuleAccess().getNameIDTerminalRuleCall_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__IsConjugatedAssignment_4
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getIsConjugatedTildeKeyword_4_0()); }
(
{ before(grammarAccess.getPortRuleAccess().getIsConjugatedTildeKeyword_4_0()); }

	'~' 

{ after(grammarAccess.getPortRuleAccess().getIsConjugatedTildeKeyword_4_0()); }
)

{ after(grammarAccess.getPortRuleAccess().getIsConjugatedTildeKeyword_4_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__TypeAssignment_5_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getTypeTypeRuleParserRuleCall_5_0_0()); }
	ruleTypeRule{ after(grammarAccess.getPortRuleAccess().getTypeTypeRuleParserRuleCall_5_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__MultiplicityAssignment_6
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getMultiplicityMultiplicityRuleParserRuleCall_6_0()); }
	ruleMultiplicityRule{ after(grammarAccess.getPortRuleAccess().getMultiplicityMultiplicityRuleParserRuleCall_6_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__ModifiersAssignment_7
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getModifiersModifiersRuleParserRuleCall_7_0()); }
	ruleModifiersRule{ after(grammarAccess.getPortRuleAccess().getModifiersModifiersRuleParserRuleCall_7_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__PortRule__DefaultAssignment_8
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPortRuleAccess().getDefaultDefaultValueRuleParserRuleCall_8_0()); }
	ruleDefaultValueRule{ after(grammarAccess.getPortRuleAccess().getDefaultDefaultValueRuleParserRuleCall_8_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__TypeRule__PathAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getTypeRuleAccess().getPathQualifiedNameParserRuleCall_0_0()); }
	ruleQualifiedName{ after(grammarAccess.getTypeRuleAccess().getPathQualifiedNameParserRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__TypeRule__TypeAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getTypeRuleAccess().getTypeClassifierCrossReference_1_0()); }
(
{ before(grammarAccess.getTypeRuleAccess().getTypeClassifierIDTerminalRuleCall_1_0_1()); }
	RULE_ID{ after(grammarAccess.getTypeRuleAccess().getTypeClassifierIDTerminalRuleCall_1_0_1()); }
)
{ after(grammarAccess.getTypeRuleAccess().getTypeClassifierCrossReference_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__PathAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedNameAccess().getPathNamespaceCrossReference_0_0()); }
(
{ before(grammarAccess.getQualifiedNameAccess().getPathNamespaceIDTerminalRuleCall_0_0_1()); }
	RULE_ID{ after(grammarAccess.getQualifiedNameAccess().getPathNamespaceIDTerminalRuleCall_0_0_1()); }
)
{ after(grammarAccess.getQualifiedNameAccess().getPathNamespaceCrossReference_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__RemainingAssignment_2
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedNameAccess().getRemainingQualifiedNameParserRuleCall_2_0()); }
	ruleQualifiedName{ after(grammarAccess.getQualifiedNameAccess().getRemainingQualifiedNameParserRuleCall_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__BoundsAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getBoundsBoundSpecificationParserRuleCall_1_0()); }
	ruleBoundSpecification{ after(grammarAccess.getMultiplicityRuleAccess().getBoundsBoundSpecificationParserRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__BoundsAssignment_2_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getBoundsBoundSpecificationParserRuleCall_2_1_0()); }
	ruleBoundSpecification{ after(grammarAccess.getMultiplicityRuleAccess().getBoundsBoundSpecificationParserRuleCall_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__BoundSpecification__ValueAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getBoundSpecificationAccess().getValueUnlimitedLiteralParserRuleCall_0()); }
	ruleUnlimitedLiteral{ after(grammarAccess.getBoundSpecificationAccess().getValueUnlimitedLiteralParserRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__ValuesAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getValuesModifierSpecificationParserRuleCall_1_0()); }
	ruleModifierSpecification{ after(grammarAccess.getModifiersRuleAccess().getValuesModifierSpecificationParserRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__ValuesAssignment_2_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getValuesModifierSpecificationParserRuleCall_2_1_0()); }
	ruleModifierSpecification{ after(grammarAccess.getModifiersRuleAccess().getValuesModifierSpecificationParserRuleCall_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifierSpecification__ValueAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifierSpecificationAccess().getValueModifierKindEnumRuleCall_0_0()); }
	ruleModifierKind{ after(grammarAccess.getModifierSpecificationAccess().getValueModifierKindEnumRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifierSpecification__RedefinesAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifierSpecificationAccess().getRedefinesRedefinesRuleParserRuleCall_1_0()); }
	ruleRedefinesRule{ after(grammarAccess.getModifierSpecificationAccess().getRedefinesRedefinesRuleParserRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifierSpecification__SubsetsAssignment_2
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifierSpecificationAccess().getSubsetsSubsetsRuleParserRuleCall_2_0()); }
	ruleSubsetsRule{ after(grammarAccess.getModifierSpecificationAccess().getSubsetsSubsetsRuleParserRuleCall_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__RedefinesRule__PortAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRedefinesRuleAccess().getPortPortCrossReference_1_0()); }
(
{ before(grammarAccess.getRedefinesRuleAccess().getPortPortIDTerminalRuleCall_1_0_1()); }
	RULE_ID{ after(grammarAccess.getRedefinesRuleAccess().getPortPortIDTerminalRuleCall_1_0_1()); }
)
{ after(grammarAccess.getRedefinesRuleAccess().getPortPortCrossReference_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__SubsetsRule__PortAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSubsetsRuleAccess().getPortPortCrossReference_1_0()); }
(
{ before(grammarAccess.getSubsetsRuleAccess().getPortPortIDTerminalRuleCall_1_0_1()); }
	RULE_ID{ after(grammarAccess.getSubsetsRuleAccess().getPortPortIDTerminalRuleCall_1_0_1()); }
)
{ after(grammarAccess.getSubsetsRuleAccess().getPortPortCrossReference_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__DefaultValueRule__DefaultAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getDefaultValueRuleAccess().getDefaultSTRINGTerminalRuleCall_1_0()); }
	RULE_STRING{ after(grammarAccess.getDefaultValueRuleAccess().getDefaultSTRINGTerminalRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


