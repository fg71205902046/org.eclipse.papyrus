/*****************************************************************************
 * Copyright (c) 2019, 2021 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Nicolas FAUVERGUE (CEA LIST) nicolas.fauvergue@cea.fr - Initial API and implementation
 *   Christian W. Damus - bug 570097
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.architecture.internal.checkers;


import static org.eclipse.papyrus.toolsmiths.validation.architecture.constants.ArchitecturePluginValidationConstants.ARCHITECTURE_EXTENSION_POINT_IDENTIFIER;
import static org.eclipse.papyrus.toolsmiths.validation.architecture.constants.ArchitecturePluginValidationConstants.ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.papyrus.infra.core.architecture.ArchitectureDomain;
import org.eclipse.papyrus.infra.core.architecture.ArchitecturePackage;
import org.eclipse.papyrus.infra.gmfdiag.representation.RepresentationPackage;
import org.eclipse.papyrus.toolsmiths.validation.architecture.Activator;
import org.eclipse.papyrus.toolsmiths.validation.architecture.constants.ArchitecturePluginValidationConstants;
import org.eclipse.papyrus.toolsmiths.validation.architecture.internal.messages.Messages;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.BuildPropertiesChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CustomModelChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.ExtensionsChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker2;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.ModelDependenciesChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.ModelValidationChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.PluginErrorReporter;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.MarkersService;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.PluginValidationService;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.ProjectManagementService;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This allows to check an architecture plug-in (extensions, builds, dependencies, ...).
 */
public class ArchitecturePluginChecker {

	public static final String ARCHITECTURE_EXTENSION = "architecture";//$NON-NLS-1$

	private static final Set<String> ADDITIONAL_REQUIREMENTS = Set.of(
			"org.eclipse.papyrus.infra.architecture" //$NON-NLS-1$
	);

	/**
	 * This allows to check the architecture plug-in.
	 *
	 * @param project
	 *            The current project to check.
	 */
	public static void checkArchitecturePlugin(final IProject project) {

		// Get the shell to manage the validation in an UI
		final Shell shell = Display.getCurrent().getActiveShell();

		try {
			// Open the progress monitor dialog
			new ProgressMonitorDialog(shell).run(true, true, monitor -> {
				final Collection<IFile> architectureFiles = ProjectManagementService.getFilesFromProject(project, "architecture", true); //$NON-NLS-1$
				monitor.beginTask(Messages.ArchitecturePluginChecker_0, 1 + (architectureFiles.size() * 3));

				monitor.subTask(Messages.ArchitecturePluginChecker_1);
				// First of all, delete the existing markers for project
				MarkersService.deleteMarkers(project, ArchitecturePluginValidationConstants.ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE);

				// Create the plug-in validation service
				final PluginValidationService pluginValidationService = new PluginValidationService();

				// First, check the dependencies needed
				pluginValidationService.addPluginChecker(createModelDependenciesChecker(project));

				// For all architecture files in the plug-in
				for (final IFile architectureFile : architectureFiles) {

					// Get the resource
					final URI architectureFileURI = URI.createPlatformResourceURI(architectureFile.getFullPath().toOSString(), true);
					final Resource resource = new ResourceSetImpl().getResource(architectureFileURI, true);

					// Check the validation of the architecture file
					pluginValidationService.addPluginChecker(createModelValidationChecker(project, architectureFile, resource));

					// Check the extension point
					pluginValidationService.addPluginChecker(createExtensionsChecker(project, architectureFile, resource));

					// Check the external dependencies needed
					pluginValidationService.addPluginChecker(createModelDependenciesChecker(project, architectureFile, resource));

					// Create the build checker
					pluginValidationService.addPluginChecker(createBuildPropertiesChecker(project, architectureFile, resource));
				}

				monitor.worked(1);

				// Call the validate
				pluginValidationService.validate(monitor);

			});
		} catch (InvocationTargetException e) {
			Activator.log.error(e);
		} catch (InterruptedException e) {
			// Do nothing, just cancelled by user
		}
	}

	/**
	 * Obtain a model validation checker factory.
	 *
	 * @return the model validation checker factory
	 */
	public static IPluginChecker2.Factory modelValidationCheckerFactory() {
		return IPluginChecker2.Factory.forEMFResource(ArchitecturePluginChecker::createModelValidationChecker);
	}

	private static ModelValidationChecker createModelValidationChecker(IProject project, IFile modelFile, Resource resource) {
		return new ModelValidationChecker(modelFile, resource, ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE);
	}

	/**
	 * Obtain a build properties checker factory.
	 *
	 * @return the build properties checker factory
	 */
	public static IPluginChecker2.Factory buildPropertiesCheckerFactory() {
		return IPluginChecker2.Factory.forEMFResource(ArchitecturePluginChecker::createBuildPropertiesChecker);
	}

	private static BuildPropertiesChecker createBuildPropertiesChecker(IProject project, IFile modelFile, Resource resource) {
		return new BuildPropertiesChecker(project, modelFile, resource, ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE)
				.withDependencies(file -> new ReferencedIconsBuildPropertiesDependencies(resource).getDependencies());
	}

	/**
	 * Obtain a dependencies checker factory for the specified bundle dependencies validation.
	 *
	 * @return the dependencies checker factory
	 */
	public static IPluginChecker2.Factory modelDependenciesCheckerFactory() {
		// When checking the project, we have some additional requirements that aren't model-specific
		return IPluginChecker2.Factory.forProject(ArchitecturePluginChecker::createModelDependenciesChecker)
				.or(IPluginChecker2.Factory.forEMFResource(ArchitecturePluginChecker::createModelDependenciesChecker));
	}

	private static ModelDependenciesChecker createModelDependenciesChecker(IProject project) {
		// When checking the project, we have some additional requirements that aren't model-specific
		return new ModelDependenciesChecker(project, null, null, ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE)
				.addRequirements(ADDITIONAL_REQUIREMENTS)
				.withSeverityFunction(bundle -> ADDITIONAL_REQUIREMENTS.contains(bundle) ? Diagnostic.WARNING : Diagnostic.ERROR);
	}

	private static ModelDependenciesChecker createModelDependenciesChecker(IProject project, IFile modelFile, Resource resource) {
		return new ModelDependenciesChecker(project, modelFile, resource, ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE)
				.withAdditionalRequirements(new ArchitectureDependencies(project)::computeDependencies);
	}

	/**
	 * Obtain a <tt>plugin.xml</tt> extensions checker factory.
	 *
	 * @return the extensions checker factory
	 */
	public static IPluginChecker2.Factory extensionsCheckerFactory() {
		return IPluginChecker2.Factory.forEMFResource(ArchitecturePluginChecker::createExtensionsChecker);
	}

	private static ExtensionsChecker<ArchitectureDomain, PluginErrorReporter<ArchitectureDomain>> createExtensionsChecker(
			IProject project, IFile modelFile, Resource resource) {

		Collection<ArchitectureDomain> domains = EcoreUtil.getObjectsByType(resource.getContents(), ArchitecturePackage.Literals.ARCHITECTURE_DOMAIN);
		return new ExtensionsChecker<>(project, modelFile, domains, ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE, ArchitecturePluginChecker::createPluginErrorReporter);
	}

	private static PluginErrorReporter<ArchitectureDomain> createPluginErrorReporter(IFile pluginXML, IFile modelFile, ArchitectureDomain model) {
		ArchitecturePluginXMLValidator validator = new ArchitecturePluginXMLValidator(modelFile);

		return new PluginErrorReporter<>(pluginXML, modelFile, model, ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE, domain -> domain.getId())
				.requireExtensionPoint(ARCHITECTURE_EXTENSION_POINT_IDENTIFIER, validator::matchExtension, null, validator::problemID);
	}

	/**
	 * Obtain a checker factory for custom model validation rules.
	 *
	 * @return the custom model checker factory
	 */
	public static IPluginChecker2.Factory customModelCheckerFactory() {
		return IPluginChecker2.Factory.forEMFResource(ArchitecturePluginChecker::createCustomModelChecker);
	}

	private static CustomModelChecker createCustomModelChecker(IProject project, IFile modelFile, Resource resource) {
		return new CustomModelChecker(modelFile, resource, ARCHITECTURE_PLUGIN_VALIDATION_MARKER_TYPE)
				.withValidator(ArchitecturePackage.eNS_URI, ArchitectureCustomValidator::new)
				.withValidator(RepresentationPackage.eNS_URI, ArchitectureCustomValidator::new); // No Papyrus-specific rules, yet
	}

}
