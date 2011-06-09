/*
* generated by Xtext
*/
package org.eclipse.papyrus.message.editor.xtext.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.papyrus.message.editor.xtext.services.UmlMessageGrammarAccess;

public class UmlMessageParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private UmlMessageGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.eclipse.papyrus.message.editor.xtext.parser.antlr.internal.InternalUmlMessageParser createParser(XtextTokenStream stream) {
		return new org.eclipse.papyrus.message.editor.xtext.parser.antlr.internal.InternalUmlMessageParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "MessageRule";
	}
	
	public UmlMessageGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(UmlMessageGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
