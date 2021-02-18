/*******************************************************************************
 * Copyright (c) 2007, 2020 Borland Software Corporation, CEA LIST, Artal
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *    Dmitry Stadnik (Borland) - initial implementation
 *    Artem Tikhomirov (Borland) - [235113] alternative parser access
 *                                 [244419] custom parsers
 *                                 initial API
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *****************************************************************************/
package parsers

import com.google.inject.Inject
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenParsers
import org.eclipse.papyrus.gmf.codegen.gmfgen.LabelTextAccessMethod
import org.eclipse.papyrus.gmf.codegen.xtend.annotations.Localization
import xpt.Common
import xpt.Externalizer
import plugin.Activator

@com.google.inject.Singleton class PredefinedParser {
	@Inject extension Common;

	@Inject Externalizer xptExternalizer;
	@Inject Activator xptActivator;
	
	def className(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''«it.className»'''

	def packageName(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''«it.holder.implPackageName»'''

	def qualifiedClassName(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''«packageName(it)».«className(it)»'''
	
	def fullPath(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''«qualifiedClassName(it)»'''
	
	def extendsList(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) //
	'''extends org.eclipse.gmf.tooling.runtime.parsers.AbstractAttributeParser'''

	def Main(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''
	«copyright(it.holder.editorGen)»
	package «packageName(it)»;
	
	«generatedClassComment»
	public class «className(it)» «extendsList(it)» {
	
		«fields(it)»
		«constructor(it)»
		«aux_methods(it)»
		«parser_getEditStringMethod(it)»
		«parser_isValidEditStringMethod(it)»
		«parser_getParseCommandMethod(it)»
		«parser_getPrintStringMethod(it)»
		«parser_isAffectingEventMethod(it)»
		«parser_getCompletionProcessorMethod(it)»
		«extraLineBreak»
		«additions(it)»
	}
	'''

	def additions(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) ''''''

	def fields(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''
	«IF viewMethod == LabelTextAccessMethod::MESSAGE_FORMAT || viewMethod == LabelTextAccessMethod::PRINTF»
	«generatedMemberComment»
	private String defaultPattern;
	«ENDIF»
	«IF viewMethod == LabelTextAccessMethod::MESSAGE_FORMAT || viewMethod == LabelTextAccessMethod::PRINTF ||
		editMethod == LabelTextAccessMethod::MESSAGE_FORMAT || editMethod == LabelTextAccessMethod::PRINTF»
	«generatedMemberComment»
	private String defaultEditablePattern;
	«ENDIF»
	«IF viewMethod == LabelTextAccessMethod::MESSAGE_FORMAT»
	«extraLineBreak»
	«generatedMemberComment»
	private java.text.MessageFormat viewProcessor;

	«generatedMemberComment»
	private java.text.MessageFormat editorProcessor;
	«ENDIF»
	«IF editMethod == LabelTextAccessMethod::MESSAGE_FORMAT»
	«extraLineBreak»
	«generatedMemberComment»
	private java.text.MessageFormat editProcessor;
	«ENDIF»
	'''

	def constructor(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''
		«generatedMemberComment»
		public «className(it)»(org.eclipse.emf.ecore.EAttribute[] features) {
			super(features);
		«IF viewMethod == LabelTextAccessMethod::NATIVE || editMethod == LabelTextAccessMethod::NATIVE»
			if (features.length != 1) {
				throw new IllegalArgumentException(java.util.Arrays.toString(features));
			}
		«ENDIF»
		}
		
		«generatedMemberComment»
		public «className(it)»(org.eclipse.emf.ecore.EAttribute[] features, org.eclipse.emf.ecore.EAttribute[] editableFeatures) {
			super(features, editableFeatures);
		«IF viewMethod == LabelTextAccessMethod::NATIVE || editMethod == LabelTextAccessMethod::NATIVE»
			if (features.length != 1) {
				throw new IllegalArgumentException(java.util.Arrays.toString(features));
			}
			if (editableFeatures.length != 1) {
				throw new IllegalArgumentException(java.util.Arrays.toString(editableFeatures));
			}
		«ENDIF»
		}
	'''

	def aux_methods(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''
	«IF viewMethod == LabelTextAccessMethod::MESSAGE_FORMAT»
	«generatedMemberComment»
	protected String getDefaultPattern() {
		if (defaultPattern == null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < features.length; i++) {
				if (i > 0) {
					sb.append(' ');
				}
				sb.append('{');
				sb.append(i);
				sb.append('}');
			}
			defaultPattern = sb.toString();
		}
		return defaultPattern;
	}
	
	«generatedMemberComment»
	public void setViewPattern(String viewPattern) {
		super.setViewPattern(viewPattern);
		viewProcessor = null;
	}

	«generatedMemberComment»
	public void setEditorPattern(String editorPattern) {
		super.setEditorPattern(editorPattern);
		editorProcessor = null;
	}

	«generatedMemberComment»
	protected java.text.MessageFormat getViewProcessor() {
		if (viewProcessor == null) {
			viewProcessor = new java.text.MessageFormat(getViewPattern() == null ? getDefaultPattern() : getViewPattern());
		}
		return viewProcessor;
	}

	«generatedMemberComment»
	protected java.text.MessageFormat getEditorProcessor() {
		if (editorProcessor == null) {
			editorProcessor = new java.text.MessageFormat(getEditorPattern() == null ? getDefaultEditablePattern() : getEditorPattern());
		}
	return editorProcessor;
	}
	«ELSEIF viewMethod == LabelTextAccessMethod::PRINTF»
	«generatedMemberComment»
	protected String getDefaultPattern() {
		if (defaultPattern == null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < features.length; i++) {
				if (i > 0) {
					sb.append(' ');
				}
				sb.append('%');
				sb.append(i + 1);
				sb.append('$');
				sb.append('s');
			}
			defaultPattern = sb.toString();
		}
		return defaultPattern;
	}
	«ENDIF»
	«IF viewMethod == LabelTextAccessMethod::MESSAGE_FORMAT || editMethod == LabelTextAccessMethod::MESSAGE_FORMAT»
	«generatedMemberComment»
	protected String getDefaultEditablePattern() {
		if (defaultEditablePattern == null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < editableFeatures.length; i++) {
				if (i > 0) {
					sb.append(' ');
				}
				sb.append('{');
				sb.append(i);
				sb.append('}');
			}
			defaultEditablePattern = sb.toString();
		}
		return defaultEditablePattern;
	}
	«ENDIF»
	«IF viewMethod == LabelTextAccessMethod::PRINTF || editMethod == LabelTextAccessMethod::PRINTF»
	«generatedMemberComment»
	protected String getDefaultEditablePattern() {
		if (defaultEditablePattern == null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < editableFeatures.length; i++) {
				if (i > 0) {
					sb.append(' ');
				}
				sb.append('%');
				sb.append(i + 1);
				sb.append('$');
				sb.append('s');
			}
			defaultEditablePattern = sb.toString();
		}
		return defaultEditablePattern;
	}
	«ENDIF»
	«IF editMethod == LabelTextAccessMethod::MESSAGE_FORMAT»
	«generatedMemberComment»
	public void setEditPattern(String editPattern) {
		super.setEditPattern(editPattern);
		editProcessor = null;
	}

	«generatedMemberComment»
	protected java.text.MessageFormat getEditProcessor() {
		if (editProcessor == null) {
			editProcessor = new java.text.MessageFormat(getEditPattern() == null ? getDefaultEditablePattern() : getEditPattern());
		}
		return editProcessor;
	}
	«ELSEIF editMethod == LabelTextAccessMethod::REGEXP»
	«generatedMemberComment»
	public String getEditPattern() {
		String pattern = super.getEditPattern();
		return pattern != null ? pattern : " "; «nonNLS(1)»
	}
	«ENDIF»
	'''

	def parser_getEditStringMethod(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''
		«generatedMemberComment»
		public String getEditString(org.eclipse.core.runtime.IAdaptable adapter, int flags) {
		«IF viewMethod == LabelTextAccessMethod::MESSAGE_FORMAT»
			org.eclipse.emf.ecore.EObject element =	(org.eclipse.emf.ecore.EObject) adapter.getAdapter(org.eclipse.emf.ecore.EObject.class);
			return getEditorProcessor().format(getEditableValues(element), new StringBuffer(),	new java.text.FieldPosition(0)).toString();
		«ELSEIF viewMethod == LabelTextAccessMethod::PRINTF»
			org.eclipse.emf.ecore.EObject element = (org.eclipse.emf.ecore.EObject) adapter.getAdapter(org.eclipse.emf.ecore.EObject.class);
			String pattern = getEditorPattern() == null ? getDefaultEditablePattern() : getEditorPattern();
			return String.format(pattern, getEditableValues(element));
		«ELSEIF viewMethod == LabelTextAccessMethod::REGEXP»
			return ""; «nonNLS(1)»
		«ELSEIF viewMethod == LabelTextAccessMethod::NATIVE»
			org.eclipse.emf.ecore.EObject element = (org.eclipse.emf.ecore.EObject) adapter.getAdapter(org.eclipse.emf.ecore.EObject.class);
			String s = org.eclipse.emf.ecore.util.EcoreUtil.convertToString(editableFeatures[0].getEAttributeType(), element.eGet(editableFeatures[0]));
			return s != null ? s : ""; «nonNLS(1)»
		«ENDIF»
		}
	'''

	def parser_isValidEditStringMethod(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''
		«generatedMemberComment»
		public org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus isValidEditString(org.eclipse.core.runtime.IAdaptable adapter, String editString) {
		«IF editMethod == LabelTextAccessMethod::MESSAGE_FORMAT»
			java.text.ParsePosition pos = new java.text.ParsePosition(0);
			Object[] values = getEditProcessor().parse(editString, pos);
			if (values == null) {
				return new org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus(
						«xptActivator.qualifiedClassName(holder.editorGen.plugin)».ID,
						org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus.UNEDITABLE,
						org.eclipse.osgi.util.NLS.bind(
								«xptExternalizer.accessorCall(holder.editorGen, i18nKeyForMessageFormatParserInvalidInputError())»,
								new Integer(pos.getErrorIndex())));
			}
			return validateNewValues(values);
		«ELSEIF editMethod == LabelTextAccessMethod::PRINTF»
			return org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus.UNEDITABLE_STATUS;
		«ELSEIF editMethod == LabelTextAccessMethod::REGEXP»
			if (editString == null) {
				return org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus.UNEDITABLE_STATUS;
			}
			Object[] values = editString.split(getEditPattern());
			return validateNewValues(values);
		«ELSEIF editMethod == LabelTextAccessMethod::NATIVE»
			return org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus.EDITABLE_STATUS;
		«ENDIF»
		}
	'''

	def parser_getParseCommandMethod(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''
		«generatedMemberComment»
		public org.eclipse.gmf.runtime.common.core.command.ICommand getParseCommand(org.eclipse.core.runtime.IAdaptable adapter, String newString, int flags) {
		«IF editMethod == LabelTextAccessMethod::MESSAGE_FORMAT»
			Object[] values = getEditProcessor().parse(newString, new java.text.ParsePosition(0));
			return getParseCommand(adapter, values, flags);
		«ELSEIF editMethod == LabelTextAccessMethod::PRINTF»
			return org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE;
		«ELSEIF editMethod == LabelTextAccessMethod::REGEXP»
			if (newString == null) {
				return org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE;
			}
			Object[] values = newString.split(getEditPattern());
			return super.getParseCommand(adapter, values, flags);
		«ELSEIF editMethod == LabelTextAccessMethod::NATIVE»
			Object value = org.eclipse.emf.ecore.util.EcoreUtil.createFromString(editableFeatures[0].getEAttributeType(), newString);
			return getParseCommand(adapter, new Object[] { value }, flags);
		«ENDIF»
		}
	'''

	def parser_getPrintStringMethod(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''
		«generatedMemberComment»
		public String getPrintString(org.eclipse.core.runtime.IAdaptable adapter, int flags) {
		«IF viewMethod == LabelTextAccessMethod::MESSAGE_FORMAT»
			org.eclipse.emf.ecore.EObject element = (org.eclipse.emf.ecore.EObject) adapter.getAdapter(org.eclipse.emf.ecore.EObject.class);
			return getViewProcessor().format(getValues(element), new StringBuffer(), new java.text.FieldPosition(0)).toString();
		«ELSEIF viewMethod == LabelTextAccessMethod::PRINTF»
			org.eclipse.emf.ecore.EObject element = (org.eclipse.emf.ecore.EObject) adapter.getAdapter(org.eclipse.emf.ecore.EObject.class);
			return String.format(getViewPattern() == null ? getDefaultPattern() : getViewPattern(), getValues(element));
		«ELSEIF viewMethod == LabelTextAccessMethod::REGEXP»
			return ""; «nonNLS(1)»
		«ELSEIF viewMethod == LabelTextAccessMethod::NATIVE»
			org.eclipse.emf.ecore.EObject element = (org.eclipse.emf.ecore.EObject) adapter.getAdapter(org.eclipse.emf.ecore.EObject.class);
			String s = org.eclipse.emf.ecore.util.EcoreUtil.convertToString(features[0].getEAttributeType(), element.eGet(features[0]));
			return s != null ? s : ""; «nonNLS(1)»
		«ENDIF»
		}
	'''

	def parser_isAffectingEventMethod(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''«/* NO-OP, rely on superclass for now */»'''

	def parser_getCompletionProcessorMethod(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser it) '''«/* NO-OP, rely on superclass for now */»'''

	def i18nValues(GenParsers it) '''
		«IF implementations != null»
			«IF implementations.filter(typeof(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser)).exists(
				p|p.editMethod == LabelTextAccessMethod::MESSAGE_FORMAT)»
				«xptExternalizer.messageEntry(i18nKeyForMessageFormatParserInvalidInputError(), 'Invalid input at {0}')»
			«ENDIF»
		«ENDIF»
	'''

	def i18nAccessors(GenParsers it) '''
		«IF implementations != null»
			«IF implementations.filter(typeof(org.eclipse.papyrus.gmf.codegen.gmfgen.PredefinedParser)).exists(
				p|p.editMethod == LabelTextAccessMethod::MESSAGE_FORMAT)»
				«xptExternalizer.accessorField(i18nKeyForMessageFormatParserInvalidInputError())»
			«ENDIF»
		«ENDIF»
	'''

	@Localization protected def String i18nKeyForMessageFormatParserInvalidInputError() {
		return 'MessageFormatParser.InvalidInputError'
	}
}
