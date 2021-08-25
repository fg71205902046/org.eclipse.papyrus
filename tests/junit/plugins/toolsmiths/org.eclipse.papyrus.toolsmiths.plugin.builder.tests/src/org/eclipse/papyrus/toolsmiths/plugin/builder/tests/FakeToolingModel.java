/*****************************************************************************
 * Copyright (c) 2021 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder.tests;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * JUnit rule that registers a fake tooling model and its project rules for the
 * duration of the test.
 */
public class FakeToolingModel extends TestWatcher {

	private static final URI PATHMAP = URI.createURI("pathmap://TOOLSMITHS_PLUGIN_BUILDER_TEST/"); //$NON-NLS-1$

	private EPackage toolingPackage;
	private ServiceRegistration<URI> projectRulesRegistration;

	@Override
	protected void starting(Description description) {
		// Register the fake tooling model for the duration of the test
		ResourceSet rset = new ResourceSetImpl();
		Resource res = rset.getResource(PATHMAP.appendSegment("BuilderTest.ecore"), true);
		toolingPackage = (EPackage) res.getContents().get(0);
		EPackage.Registry.INSTANCE.put(toolingPackage.getNsURI(), toolingPackage);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("toolingstuff", new XMIResourceFactoryImpl());

		// Register the fake tooling model's project rules for the duration of the test
		BundleContext context = FrameworkUtil.getBundle(GenericEMFModelBuilderTest.class).getBundleContext();
		Dictionary<String, Object> props = new Hashtable<>();
		props.put("type", "org.eclipse.papyrus.toolsmiths.validation.common.projectrules");
		props.put("package", toolingPackage);
		projectRulesRegistration = context.registerService(URI.class, PATHMAP.appendSegment("BuilderTest.projectrules"), props);
	}

	@Override
	protected void finished(Description description) {
		projectRulesRegistration.unregister();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().remove("toolingstuff");
		EPackage.Registry.INSTANCE.remove(toolingPackage.getNsURI());
	}

}
