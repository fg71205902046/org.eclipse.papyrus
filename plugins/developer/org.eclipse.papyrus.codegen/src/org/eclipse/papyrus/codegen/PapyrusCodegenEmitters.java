/****************************************************************************
 * Copyright (c) 2008 Atos Origin.
 *  
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		Thibault Landre (Atos Origin) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.codegen;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.codegen.util.CodegenEmitters;
import org.eclipse.gmf.common.UnexpectedBehaviourException;
import org.eclipse.gmf.internal.common.codegen.TextEmitter;

/**
 * The Papyrus CodegenEmitters
 * 
 * @author tlandre
 */
public class PapyrusCodegenEmitters extends CodegenEmitters {

	public PapyrusCodegenEmitters(boolean useBaseTemplatesOnly, String templateDirectory, boolean includeDynamicModelTemplates) {
		super(useBaseTemplatesOnly, templateDirectory, includeDynamicModelTemplates);
	}

	public TextEmitter getNodePreferencePageEmitter() {
		return newXpandEmitter("xpt::preferences::NodePreferencePage::NodePreferencePage"); //$NON-NLS-1$
	}

	public TextEmitter getLinkPreferencePageEmitter() {
		return newXpandEmitter("xpt::preferences::LinkPreferencePage::LinkPreferencePage"); //$NON-NLS-1$
	}

	public TextEmitter getIDiagramPreferenceConstantEmitter() {
		return newXpandEmitter("xpt::preferences::IDiagramPreferenceConstant::IDiagramPreferenceConstant"); //$NON-NLS-1$
	}

	public TextEmitter getDiagramPreferencePageEmitter() {
		return newXpandEmitter("xpt::preferences::DiagramPreferencePage::DiagramPreferencePage"); //$NON-NLS-1$
	}

	/**
	 * Returns qualified class name defined in template.
	 */
	private String getQualifiedClassName(String templateName, Object... input) throws UnexpectedBehaviourException {
		TextEmitter emitter = newXpandEmitter(templateName);
		return getText(emitter, input);
	}

	/**
	 * copy from the private method in super class Returns text generated by emitter.
	 */
	private String getText(TextEmitter emitter, Object... input) throws UnexpectedBehaviourException {
		try {
			return emitter.generate(new NullProgressMonitor(), input).trim();
		} catch (InterruptedException ie) {
			return null;
		} catch (InvocationTargetException ite) {
			throw new UnexpectedBehaviourException(ite.getCause());
		}
	}

}
