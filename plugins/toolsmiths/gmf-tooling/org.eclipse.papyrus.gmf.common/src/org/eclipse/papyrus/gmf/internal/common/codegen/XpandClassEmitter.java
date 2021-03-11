/******************************************************************************
 * Copyright (c) 2013, 2020, 2021 Borland Software Corporation, CEA LIST, Artal
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *    Svyatoslav Kovalsky (Montages) - initial API and implementation
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *    Etienne ALLOGO (ARTAL) - etienne.allogo@artal.fr - Bug 569174 - newline characters preference api consistency
 *****************************************************************************/
package org.eclipse.papyrus.gmf.internal.common.codegen;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.papyrus.gmf.common.UnexpectedBehaviourException;
import org.eclipse.papyrus.gmf.internal.xpand.ResourceManager;


public class XpandClassEmitter extends XpandTextEmitter implements JavaClassEmitter {

	public XpandClassEmitter(ResourceManager manager, String templateFQN, String methodToInvoke) {
		this(manager, templateFQN, methodToInvoke, null);
	}

	public XpandClassEmitter(ResourceManager manager, String templateFQN, String methodToInvoke, Map<String, Object> globals) {
		super(manager, templateFQN, methodToInvoke, globals);
	}

	public String getQualifiedClassName(String lineSeparator, Object... input) throws UnexpectedBehaviourException {
		return getQualifiedClassName("qualifiedClassName", lineSeparator, input);
	}

	public String getQualifiedClassName(String fqnMethodName, String lineSeparator, Object... input) throws UnexpectedBehaviourException {
		return getText(fqnMethodName, lineSeparator, input);
	}
	
	private String getText(String method,Object... input) throws UnexpectedBehaviourException {
		try {
			return this.generate(new NullProgressMonitor(),method, input).trim();
		} catch (InvocationTargetException e) {
			throw new UnexpectedBehaviourException("Invocation method exception "+ method + " on template " + getTemplateFQN(), e);
		} catch (InterruptedException e) {
			throw new UnexpectedBehaviourException("Execute method exception "+ method + " on template " + getTemplateFQN(), e);
		}
	}

}
