/*
* generated by Xtext
*/
package org.eclipse.papyrus.uml.textedit.common.xtext.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.papyrus.uml.textedit.common.xtext.services.UmlCommonGrammarAccess;

public class UmlCommonParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private UmlCommonGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.eclipse.papyrus.uml.textedit.common.xtext.parser.antlr.internal.InternalUmlCommonParser createParser(XtextTokenStream stream) {
		return new org.eclipse.papyrus.uml.textedit.common.xtext.parser.antlr.internal.InternalUmlCommonParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "QualifiedName";
	}
	
	public UmlCommonGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(UmlCommonGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
