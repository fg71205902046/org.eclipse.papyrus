/*****************************************************************************
 * Copyright (c) 2007, 2013, 2015, 2021 Borland Software Corporation, Christian W. Damus, CEA LIST, Artal and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Michael Golubev (Montages) - initial API and implementation
 * Dmitry Stadnik (Borland) - initial API and implementation
 * Thibault Landre (Atos Origin) - add Papyrus dependencies to Papyrus GMF diagram
 * Vincent Lorenzo (CEA-LIST) vincent.lorenzo@cea.fr : add the dependency org.eclipse.papyrus.infra.services.edit
 * Christian W. Damus - bug 477384
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : 1.4 Merge papyrus extension templates into codegen.xtend
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : 1.2 cleanup dependency declarations in Require-Bundle section
 *****************************************************************************/
package xpt.plugin

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenPlugin
import xpt.GenEditorGenerator_qvto

@Singleton class manifest {

	@Inject extension GenEditorGenerator_qvto

	def qualifiedClassName(GenPlugin it) '''META-INF/MANIFEST.MF'''
	def fullPath(GenPlugin it) '''«qualifiedClassName(it)»'''

	def manifest (GenPlugin it)'''
		«manifestVersion(it)»
		«bundleManifestVersion(it)»
		«bundleName(it)»
		«symbolicName(it)»
		«bundleVersion(it)»
		«bundleClassPath(it)»
		«bundleActivator(it)»
		«bundleVendor(it)»
		«bundleLocalization(it)»
		«exportPackages()»
		«requireBundle()»
		«bundleActivatorPolicy(it)»
		«executionEnvironment()»
	'''

	def manifestVersion(GenPlugin it) '''Manifest-Version: 1.0'''
	def bundleManifestVersion(GenPlugin it) '''Bundle-ManifestVersion: 2'''
	def bundleName(GenPlugin it) '''Bundle-Name: %pluginName'''
	def symbolicName(GenPlugin it) '''Bundle-SymbolicName: «ID»; singleton:=true'''
	def bundleVersion(GenPlugin it) '''Bundle-Version: «version»'''
	def bundleClassPath(GenPlugin it) '''Bundle-ClassPath: .'''
	def bundleActivator(GenPlugin it) '''Bundle-Activator: «getActivatorQualifiedClassName()»'''
	def bundleVendor(GenPlugin it) '''Bundle-Vendor: %providerName'''
	def bundleLocalization(GenPlugin it) '''Bundle-Localization: plugin'''
	def bundleActivatorPolicy(GenPlugin it) '''Bundle-ActivationPolicy: lazy'''

	def exportPackages(GenPlugin it)'''
		Export-Package: «editorGen.diagram.editPartsPackageName»,
		 «editorGen.editor.packageName»,
		 «editorGen.diagram.providersPackageName»
	'''

	def requireBundle(GenPlugin it)'''
		Require-Bundle: com.google.guava;bundle-version="[30.0.0,31.0.0)",
		 org.eclipse.core.expressions;bundle-version="[3.6.0,4.0.0)",
		 org.eclipse.emf.databinding;bundle-version="[1.5.0,2.0.0)",
		 org.eclipse.gmf.runtime.diagram.ui.properties;bundle-version="[1.8.0,2.0.0)",
		 org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide;bundle-version="[1.7.0,2.0.0)",
		 org.eclipse.ocl.ecore;bundle-version="[3.15.0,4.0.0)",
		 org.eclipse.papyrus.infra.architecture;bundle-version="[3.0.0,4.0.0)",
		 org.eclipse.papyrus.infra.core.architecture;bundle-version="[3.0.0,4.0.0)",
		 org.eclipse.papyrus.infra.core.log;bundle-version="[2.0.0,3.0.0)",
		 org.eclipse.papyrus.infra.core.sasheditor;bundle-version="[3.0.0,4.0.0)",
		 org.eclipse.papyrus.infra.core.sashwindows.di;bundle-version="[2.0.0,3.0.0)",
		 org.eclipse.papyrus.infra.core;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.emf.appearance;bundle-version="[3.0.0,4.0.0)",
		 org.eclipse.papyrus.infra.emf.gmf;bundle-version="[2.0.0,3.0.0)",
		 org.eclipse.papyrus.infra.emf;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.gmfdiag.commands;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.gmfdiag.common;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.gmfdiag.extensionpoints.editors;bundle-version="[2.0.0,3.0.0)",
		 org.eclipse.papyrus.infra.gmfdiag.hyperlink;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.gmfdiag.preferences;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.gmfdiag.tooling.runtime;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.hyperlink;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.internationalization.common;bundle-version="[2.0.0,3.0.0)",
		 org.eclipse.papyrus.infra.internationalization.utils;bundle-version="[2.0.0,3.0.0)",
		 org.eclipse.papyrus.infra.services.edit;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.services.viewersearch;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.ui;bundle-version="[3.0.0,4.0.0)",
		 org.eclipse.papyrus.infra.viewpoints.policy;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.infra.widgets;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.uml.appearance;bundle-version="[3.0.0,4.0.0)",
		 org.eclipse.papyrus.uml.diagram.common;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.papyrus.uml.internationalization.utils;bundle-version="[2.0.0,3.0.0)",
		 org.eclipse.papyrus.uml.tools.utils;bundle-version="[4.0.0,5.0.0)",
		 org.eclipse.ui.navigator.resources;bundle-version="[3.7.0,4.0.0)",
		 org.eclipse.ui.navigator;bundle-version="[3.9.0,4.0.0)",
		 org.eclipse.uml2.uml.editor;bundle-version="[5.5.0,6.0.0)"
	'''

	def executionEnvironment(GenPlugin it) '''
		Bundle-RequiredExecutionEnvironment: «IF editorGen.jdkComplianceLevel() > 8»JavaSE-«editorGen.jdkComplianceLevel()»«ELSE»J2SE-1.«editorGen.jdkComplianceLevel()»«ENDIF»
	'''
}