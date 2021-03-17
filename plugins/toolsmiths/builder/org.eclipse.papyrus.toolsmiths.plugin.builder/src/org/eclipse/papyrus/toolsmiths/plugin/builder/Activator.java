/*****************************************************************************
 * Copyright (c) 2020 CEA LIST, EclipseSource, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Initial API and implementation
 *   Remi Schnekenburger (EclipseSource) - Bug 568495
 *   Christian W. Damus - bug 569357
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder;

import static org.eclipse.papyrus.toolsmiths.validation.common.utils.ModelResourceMapper.byExtension;
import static org.eclipse.papyrus.toolsmiths.validation.common.utils.ModelResourceMapper.resourceSets;
import static org.eclipse.papyrus.toolsmiths.validation.common.utils.ModelResourceMapper.rootsOfType;
import static org.eclipse.papyrus.toolsmiths.validation.elementtypes.constants.ElementTypesPluginValidationConstants.ELEMENTTYPES_PLUGIN_VALIDATION_MARKER_TYPE;
import static org.eclipse.papyrus.toolsmiths.validation.elementtypes.internal.checkers.ElementTypesPluginChecker.ELEMENT_TYPES_CONFIGURATION_EXTENSION;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.papyrus.infra.types.ElementTypeSetConfiguration;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.ModelResourceMapper;
import org.eclipse.papyrus.toolsmiths.validation.elementtypes.internal.checkers.ElementTypesPluginChecker;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.google.common.collect.ListMultimap;

public class Activator extends AbstractUIPlugin {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.eclipse.papyrus.toolsmiths.plugin.builder"; //$NON-NLS-1$

	/**
	 * The path of the papyrus icon in the project
	 */
	public static final String PAPYRUS_ICON_PATH = "/icons/papyrus.png"; //$NON-NLS-1$

	/**
	 * The log
	 */
	public static LogHelper log;

	/**
	 * The shared instance
	 */
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		log = new LogHelper(this);

		// model builder
		PapyrusPluginBuilder.addModelBuilder(new GenericEMFModelBuilder());
		PapyrusPluginBuilder.addModelBuilder(new ArchitectureModelBuilder());
		PapyrusPluginBuilder.addModelBuilder(new XWTModelBuilder());
		PapyrusPluginBuilder.addModelBuilder(new PluginCheckerBuilder(ELEMENTTYPES_PLUGIN_VALIDATION_MARKER_TYPE, this::mapElementTypesResources)
				.withChecker(ElementTypesPluginChecker.modelValidationCheckerFactory())
				.withChecker(ElementTypesPluginChecker.customModelCheckerFactory()));

		// manifest builder
		PapyrusPluginBuilder.addManifestBuilder(new ManifestBuilder());
		PapyrusPluginBuilder.addManifestBuilder(new StaticProfileManifestBuilder());
		PapyrusPluginBuilder.addManifestBuilder(new PluginCheckerBuilder(ELEMENTTYPES_PLUGIN_VALIDATION_MARKER_TYPE, this::mapElementTypesResources)
				.withChecker(ElementTypesPluginChecker.modelDependenciesCheckerFactory())
				.withChecker(ElementTypesPluginChecker.buildPropertiesCheckerFactory()));

		// extension builder
		PapyrusPluginBuilder.addPluginBuilder(new StaticProfileExtensionsBuilder());
		PapyrusPluginBuilder.addPluginBuilder(new PluginCheckerBuilder(ELEMENTTYPES_PLUGIN_VALIDATION_MARKER_TYPE, this::mapElementTypesResources)
				.withChecker(ElementTypesPluginChecker.extensionsCheckerFactory()));
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	private ListMultimap<IFile, ElementTypeSetConfiguration> mapElementTypesResources(IProject project) {
		ModelResourceMapper<ElementTypeSetConfiguration> mapper = new ModelResourceMapper<>(project);
		return mapper.map(byExtension(ELEMENT_TYPES_CONFIGURATION_EXTENSION), resourceSets(), rootsOfType(ElementTypeSetConfiguration.class));
	}

}
