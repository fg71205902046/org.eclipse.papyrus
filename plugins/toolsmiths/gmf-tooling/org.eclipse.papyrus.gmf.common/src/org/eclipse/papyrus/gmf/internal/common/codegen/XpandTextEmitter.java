/******************************************************************************
 * Copyright (c) 2007, 2020, 2021 Borland Software Corporation, CEA LIST, Artal
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *    Artem Tikhomirov (Borland) - initial API and implementation
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *    Etienne ALLOGO (ARTAL) - etienne.allogo@artal.fr - Bug 569174 - newline characters preference api consistency
 *****************************************************************************/
package org.eclipse.papyrus.gmf.internal.common.codegen;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.gmf.common.UnexpectedBehaviourException;
import org.eclipse.papyrus.gmf.common.codegen.ImportAssistant;
import org.eclipse.papyrus.gmf.internal.xpand.BufferOutput;
import org.eclipse.papyrus.gmf.internal.xpand.ResourceManager;
import org.eclipse.papyrus.gmf.internal.xpand.XpandFacade;
import org.eclipse.papyrus.gmf.internal.xpand.model.AmbiguousDefinitionException;
import org.eclipse.papyrus.gmf.internal.xpand.model.EvaluationException;
import org.eclipse.papyrus.gmf.internal.xpand.model.Scope;
import org.eclipse.papyrus.gmf.internal.xpand.model.Variable;

/**
 * @author artem
 */
public class XpandTextEmitter implements TextEmitter {
	public final String PATH_SEPARATOR = "::";
	
	private final ResourceManager myResourceManager;
	private final String myTemplateFQN;
	private final String myMethod;
	private final List<Variable> myGlobals;

	public XpandTextEmitter(ResourceManager manager, String templateFQN, String method) {
		this(manager, templateFQN, method, null);
	}

	public XpandTextEmitter(ResourceManager manager, String templateFQN, String method,Map<String, Object> globals) {
		assert manager != null && templateFQN != null;
		myResourceManager = manager;
		myTemplateFQN = templateFQN;
		myMethod = method;
		if (globals != null && globals.size() > 0) {
			myGlobals = new ArrayList<Variable>(globals.size());
			for (Map.Entry<String, Object> e : globals.entrySet()) {
				assert e.getValue() instanceof EObject;
				myGlobals.add(new Variable(e.getKey(), ((EObject) e.getValue()).eClass(), e.getValue()));
			}
		} else {
			myGlobals = Collections.<Variable>emptyList();
		}
	}

	@Override
	public String generate(IProgressMonitor monitor, Object[] arguments, String lineSeparator) throws InterruptedException, InvocationTargetException, UnexpectedBehaviourException {
		return generate(monitor, myMethod, arguments);
	}
	
	protected String generate(IProgressMonitor monitor, String method, Object[] arguments) throws InterruptedException, InvocationTargetException {
		if (monitor != null && monitor.isCanceled()) {
			throw new InterruptedException();
		}
		try {
			StringBuilder result = new StringBuilder();
			new XpandFacade(createContext(result)).evaluate(myTemplateFQN + PATH_SEPARATOR + method, extractTarget(arguments), extractArguments(arguments));
			return result.toString();
		} catch (EvaluationException ex) {
			throw new InvocationTargetException(ex);
		} catch (AmbiguousDefinitionException e) {
			throw new InvocationTargetException(e);
		}
	}

	public ResourceManager getResourceManager() {
		return myResourceManager;
	}
	
	protected Object extractTarget(Object[] arguments) {
		assert arguments != null && arguments.length > 0;
		return arguments[0];
	}

	protected String getTemplateFQN() {
		return myTemplateFQN;
	}
	
	protected Object[] extractArguments(Object[] arguments) {
		assert arguments != null && arguments.length > 0;
		ArrayList<Object> res = new ArrayList<Object>(arguments.length);
		// strip first one off, assume it's target
		for (int i = 1; i < arguments.length; i++) {
			if (false == arguments[i] instanceof ImportAssistant) {
				// strip assistant off
				res.add(arguments[i]);
			}
		}
		return res.toArray();
	}

	private Scope createContext(StringBuilder result) {
		return new Scope(myResourceManager, myGlobals, new BufferOutput(result));
	}
	
}