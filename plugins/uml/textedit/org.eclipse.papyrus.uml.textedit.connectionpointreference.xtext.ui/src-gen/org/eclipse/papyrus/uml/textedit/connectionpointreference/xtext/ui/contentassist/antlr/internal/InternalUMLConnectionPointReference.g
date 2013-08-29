/*
* generated by Xtext
*/
grammar InternalUMLConnectionPointReference;

options {
	superClass=AbstractInternalContentAssistParser;
	
}

@lexer::header {
package org.eclipse.papyrus.uml.textedit.connectionpointreference.xtext.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package org.eclipse.papyrus.uml.textedit.connectionpointreference.xtext.ui.contentassist.antlr.internal; 

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
import org.eclipse.papyrus.uml.textedit.connectionpointreference.xtext.services.UMLConnectionPointReferenceGrammarAccess;

}

@parser::members {
 
 	private UMLConnectionPointReferenceGrammarAccess grammarAccess;
 	
    public void setGrammarAccess(UMLConnectionPointReferenceGrammarAccess grammarAccess) {
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




// Entry rule entryRuleConnectionPointReferenceRule
entryRuleConnectionPointReferenceRule 
:
{ before(grammarAccess.getConnectionPointReferenceRuleRule()); }
	 ruleConnectionPointReferenceRule
{ after(grammarAccess.getConnectionPointReferenceRuleRule()); } 
	 EOF 
;

// Rule ConnectionPointReferenceRule
ruleConnectionPointReferenceRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getAlternatives()); }
(rule__ConnectionPointReferenceRule__Alternatives)?
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}




rule__ConnectionPointReferenceRule__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getGroup_0()); }
(rule__ConnectionPointReferenceRule__Group_0__0)
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getGroup_0()); }
)

    |(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getGroup_1()); }
(rule__ConnectionPointReferenceRule__Group_1__0)
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getGroup_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}



rule__ConnectionPointReferenceRule__Group_0__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ConnectionPointReferenceRule__Group_0__0__Impl
	rule__ConnectionPointReferenceRule__Group_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__Group_0__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryKeyword_0_0()); }

	'entry' 

{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryKeyword_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ConnectionPointReferenceRule__Group_0__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ConnectionPointReferenceRule__Group_0__1__Impl
	rule__ConnectionPointReferenceRule__Group_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__Group_0__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryAssignment_0_1()); }
(rule__ConnectionPointReferenceRule__EntryAssignment_0_1)
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryAssignment_0_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ConnectionPointReferenceRule__Group_0__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ConnectionPointReferenceRule__Group_0__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__Group_0__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getGroup_0_2()); }
(rule__ConnectionPointReferenceRule__Group_0_2__0)*
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getGroup_0_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__ConnectionPointReferenceRule__Group_0_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ConnectionPointReferenceRule__Group_0_2__0__Impl
	rule__ConnectionPointReferenceRule__Group_0_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__Group_0_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getCommaKeyword_0_2_0()); }

	',' 

{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getCommaKeyword_0_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ConnectionPointReferenceRule__Group_0_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ConnectionPointReferenceRule__Group_0_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__Group_0_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryAssignment_0_2_1()); }
(rule__ConnectionPointReferenceRule__EntryAssignment_0_2_1)
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryAssignment_0_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__ConnectionPointReferenceRule__Group_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ConnectionPointReferenceRule__Group_1__0__Impl
	rule__ConnectionPointReferenceRule__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__Group_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getExitKeyword_1_0()); }

	'exit' 

{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getExitKeyword_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ConnectionPointReferenceRule__Group_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ConnectionPointReferenceRule__Group_1__1__Impl
	rule__ConnectionPointReferenceRule__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__Group_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getExitAssignment_1_1()); }
(rule__ConnectionPointReferenceRule__ExitAssignment_1_1)
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getExitAssignment_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ConnectionPointReferenceRule__Group_1__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ConnectionPointReferenceRule__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__Group_1__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getGroup_1_2()); }
(rule__ConnectionPointReferenceRule__Group_1_2__0)*
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getGroup_1_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__ConnectionPointReferenceRule__Group_1_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ConnectionPointReferenceRule__Group_1_2__0__Impl
	rule__ConnectionPointReferenceRule__Group_1_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__Group_1_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getCommaKeyword_1_2_0()); }

	',' 

{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getCommaKeyword_1_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ConnectionPointReferenceRule__Group_1_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ConnectionPointReferenceRule__Group_1_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__Group_1_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getExitAssignment_1_2_1()); }
(rule__ConnectionPointReferenceRule__ExitAssignment_1_2_1)
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getExitAssignment_1_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}







rule__ConnectionPointReferenceRule__EntryAssignment_0_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryPseudostateCrossReference_0_1_0()); }
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryPseudostateIDTerminalRuleCall_0_1_0_1()); }
	RULE_ID{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryPseudostateIDTerminalRuleCall_0_1_0_1()); }
)
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryPseudostateCrossReference_0_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__EntryAssignment_0_2_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryPseudostateCrossReference_0_2_1_0()); }
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryPseudostateIDTerminalRuleCall_0_2_1_0_1()); }
	RULE_ID{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryPseudostateIDTerminalRuleCall_0_2_1_0_1()); }
)
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getEntryPseudostateCrossReference_0_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__ExitAssignment_1_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getExitPseudostateCrossReference_1_1_0()); }
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getExitPseudostateIDTerminalRuleCall_1_1_0_1()); }
	RULE_ID{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getExitPseudostateIDTerminalRuleCall_1_1_0_1()); }
)
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getExitPseudostateCrossReference_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ConnectionPointReferenceRule__ExitAssignment_1_2_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getExitPseudostateCrossReference_1_2_1_0()); }
(
{ before(grammarAccess.getConnectionPointReferenceRuleAccess().getExitPseudostateIDTerminalRuleCall_1_2_1_0_1()); }
	RULE_ID{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getExitPseudostateIDTerminalRuleCall_1_2_1_0_1()); }
)
{ after(grammarAccess.getConnectionPointReferenceRuleAccess().getExitPseudostateCrossReference_1_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


RULE_INTEGER_VALUE : (('0'|'1'..'9' ('_'? '0'..'9')*)|('0b'|'0B') '0'..'1' ('_'? '0'..'1')*|('0x'|'0X') ('0'..'9'|'a'..'f'|'A'..'F') ('_'? ('0'..'9'|'a'..'f'|'A'..'F'))*|'0' '_'? '0'..'7' ('_'? '0'..'7')*);

RULE_ID : (('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*|'\'' ( options {greedy=false;} : . )*'\'');

RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

RULE_ML_COMMENT : '/*' ~('@') ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'|'@'))* ('\r'? '\n')?;

RULE_INT : ('0'..'9')+;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


