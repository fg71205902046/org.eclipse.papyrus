/*
* generated by Xtext
*/
grammar InternalUmlMessage;

options {
	superClass=AbstractInternalContentAssistParser;
	
}

@lexer::header {
package org.eclipse.papyrus.message.editor.xtext.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package org.eclipse.papyrus.message.editor.xtext.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.eclipse.papyrus.message.editor.xtext.services.UmlMessageGrammarAccess;

}

@parser::members {
 
 	private UmlMessageGrammarAccess grammarAccess;
 	
    public void setGrammarAccess(UmlMessageGrammarAccess grammarAccess) {
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




// Entry rule entryRuleMessageRule
entryRuleMessageRule 
:
{ before(grammarAccess.getMessageRuleRule()); }
	 ruleMessageRule
{ after(grammarAccess.getMessageRuleRule()); } 
	 EOF 
;

// Rule MessageRule
ruleMessageRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getMessageRuleAccess().getGroup()); }
(rule__MessageRule__Group__0)
{ after(grammarAccess.getMessageRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleSequenceTermRule
entryRuleSequenceTermRule 
:
{ before(grammarAccess.getSequenceTermRuleRule()); }
	 ruleSequenceTermRule
{ after(grammarAccess.getSequenceTermRuleRule()); } 
	 EOF 
;

// Rule SequenceTermRule
ruleSequenceTermRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getSequenceTermRuleAccess().getGroup()); }
(rule__SequenceTermRule__Group__0)
{ after(grammarAccess.getSequenceTermRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleRecurrenceRule
entryRuleRecurrenceRule 
:
{ before(grammarAccess.getRecurrenceRuleRule()); }
	 ruleRecurrenceRule
{ after(grammarAccess.getRecurrenceRuleRule()); } 
	 EOF 
;

// Rule RecurrenceRule
ruleRecurrenceRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getRecurrenceRuleAccess().getAlternatives()); }
(rule__RecurrenceRule__Alternatives)
{ after(grammarAccess.getRecurrenceRuleAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}




rule__RecurrenceRule__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRecurrenceRuleAccess().getGroup_0()); }
(rule__RecurrenceRule__Group_0__0)
{ after(grammarAccess.getRecurrenceRuleAccess().getGroup_0()); }
)

    |(
{ before(grammarAccess.getRecurrenceRuleAccess().getGroup_1()); }
(rule__RecurrenceRule__Group_1__0)
{ after(grammarAccess.getRecurrenceRuleAccess().getGroup_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}



rule__MessageRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MessageRule__Group__0__Impl
	rule__MessageRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMessageRuleAccess().getSequenceTermAssignment_0()); }
(rule__MessageRule__SequenceTermAssignment_0)
{ after(grammarAccess.getMessageRuleAccess().getSequenceTermAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MessageRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MessageRule__Group__1__Impl
	rule__MessageRule__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMessageRuleAccess().getGroup_1()); }
(rule__MessageRule__Group_1__0)*
{ after(grammarAccess.getMessageRuleAccess().getGroup_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MessageRule__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MessageRule__Group__2__Impl
	rule__MessageRule__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageRule__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMessageRuleAccess().getColonKeyword_2()); }

	':' 

{ after(grammarAccess.getMessageRuleAccess().getColonKeyword_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MessageRule__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MessageRule__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageRule__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMessageRuleAccess().getNameAssignment_3()); }
(rule__MessageRule__NameAssignment_3)
{ after(grammarAccess.getMessageRuleAccess().getNameAssignment_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}










rule__MessageRule__Group_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MessageRule__Group_1__0__Impl
	rule__MessageRule__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageRule__Group_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMessageRuleAccess().getFullStopKeyword_1_0()); }

	'.' 

{ after(grammarAccess.getMessageRuleAccess().getFullStopKeyword_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MessageRule__Group_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MessageRule__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MessageRule__Group_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMessageRuleAccess().getSequenceTermAssignment_1_1()); }
(rule__MessageRule__SequenceTermAssignment_1_1)
{ after(grammarAccess.getMessageRuleAccess().getSequenceTermAssignment_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__SequenceTermRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SequenceTermRule__Group__0__Impl
	rule__SequenceTermRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SequenceTermRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSequenceTermRuleAccess().getSequencialOrderAssignment_0()); }
(rule__SequenceTermRule__SequencialOrderAssignment_0)
{ after(grammarAccess.getSequenceTermRuleAccess().getSequencialOrderAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__SequenceTermRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SequenceTermRule__Group__1__Impl
	rule__SequenceTermRule__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SequenceTermRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSequenceTermRuleAccess().getSequenceNameAssignment_1()); }
(rule__SequenceTermRule__SequenceNameAssignment_1)?
{ after(grammarAccess.getSequenceTermRuleAccess().getSequenceNameAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__SequenceTermRule__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__SequenceTermRule__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SequenceTermRule__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSequenceTermRuleAccess().getRecurrenceAssignment_2()); }
(rule__SequenceTermRule__RecurrenceAssignment_2)?
{ after(grammarAccess.getSequenceTermRuleAccess().getRecurrenceAssignment_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__RecurrenceRule__Group_0__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RecurrenceRule__Group_0__0__Impl
	rule__RecurrenceRule__Group_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__RecurrenceRule__Group_0__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRecurrenceRuleAccess().getAsteriskKeyword_0_0()); }

	'*' 

{ after(grammarAccess.getRecurrenceRuleAccess().getAsteriskKeyword_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RecurrenceRule__Group_0__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RecurrenceRule__Group_0__1__Impl
	rule__RecurrenceRule__Group_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__RecurrenceRule__Group_0__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRecurrenceRuleAccess().getLeftSquareBracketKeyword_0_1()); }

	'[' 

{ after(grammarAccess.getRecurrenceRuleAccess().getLeftSquareBracketKeyword_0_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RecurrenceRule__Group_0__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RecurrenceRule__Group_0__2__Impl
	rule__RecurrenceRule__Group_0__3
;
finally {
	restoreStackSize(stackSize);
}

rule__RecurrenceRule__Group_0__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRecurrenceRuleAccess().getSTRINGTerminalRuleCall_0_2()); }
	RULE_STRING
{ after(grammarAccess.getRecurrenceRuleAccess().getSTRINGTerminalRuleCall_0_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RecurrenceRule__Group_0__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RecurrenceRule__Group_0__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__RecurrenceRule__Group_0__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRecurrenceRuleAccess().getRightSquareBracketKeyword_0_3()); }

	']' 

{ after(grammarAccess.getRecurrenceRuleAccess().getRightSquareBracketKeyword_0_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}










rule__RecurrenceRule__Group_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RecurrenceRule__Group_1__0__Impl
	rule__RecurrenceRule__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__RecurrenceRule__Group_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRecurrenceRuleAccess().getLeftSquareBracketKeyword_1_0()); }

	'[' 

{ after(grammarAccess.getRecurrenceRuleAccess().getLeftSquareBracketKeyword_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RecurrenceRule__Group_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RecurrenceRule__Group_1__1__Impl
	rule__RecurrenceRule__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__RecurrenceRule__Group_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRecurrenceRuleAccess().getSTRINGTerminalRuleCall_1_1()); }
	RULE_STRING
{ after(grammarAccess.getRecurrenceRuleAccess().getSTRINGTerminalRuleCall_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__RecurrenceRule__Group_1__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__RecurrenceRule__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__RecurrenceRule__Group_1__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getRecurrenceRuleAccess().getRightSquareBracketKeyword_1_2()); }

	']' 

{ after(grammarAccess.getRecurrenceRuleAccess().getRightSquareBracketKeyword_1_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}









rule__MessageRule__SequenceTermAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMessageRuleAccess().getSequenceTermSequenceTermRuleParserRuleCall_0_0()); }
	ruleSequenceTermRule{ after(grammarAccess.getMessageRuleAccess().getSequenceTermSequenceTermRuleParserRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__MessageRule__SequenceTermAssignment_1_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMessageRuleAccess().getSequenceTermSequenceTermRuleParserRuleCall_1_1_0()); }
	ruleSequenceTermRule{ after(grammarAccess.getMessageRuleAccess().getSequenceTermSequenceTermRuleParserRuleCall_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__MessageRule__NameAssignment_3
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMessageRuleAccess().getNameNameRuleTerminalRuleCall_3_0()); }
	RULE_NAMERULE{ after(grammarAccess.getMessageRuleAccess().getNameNameRuleTerminalRuleCall_3_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__SequenceTermRule__SequencialOrderAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSequenceTermRuleAccess().getSequencialOrderINTTerminalRuleCall_0_0()); }
	RULE_INT{ after(grammarAccess.getSequenceTermRuleAccess().getSequencialOrderINTTerminalRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__SequenceTermRule__SequenceNameAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSequenceTermRuleAccess().getSequenceNameIDTerminalRuleCall_1_0()); }
	RULE_ID{ after(grammarAccess.getSequenceTermRuleAccess().getSequenceNameIDTerminalRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__SequenceTermRule__RecurrenceAssignment_2
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getSequenceTermRuleAccess().getRecurrenceRecurrenceRuleParserRuleCall_2_0()); }
	ruleRecurrenceRule{ after(grammarAccess.getSequenceTermRuleAccess().getRecurrenceRecurrenceRuleParserRuleCall_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


RULE_NAMERULE : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'_'|'-'|'0'..'9')*;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


