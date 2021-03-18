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

package org.eclipse.papyrus.toolsmiths.validation.architecture.internal.checkers;

import static java.util.function.Predicate.not;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.papyrus.infra.core.architecture.ADElement;
import org.eclipse.papyrus.infra.core.architecture.ArchitectureContext;
import org.eclipse.papyrus.infra.core.architecture.ArchitecturePackage;
import org.eclipse.papyrus.infra.core.architecture.util.ArchitectureCommandUtils;
import org.eclipse.papyrus.infra.core.architecture.util.ArchitectureSwitch;
import org.eclipse.papyrus.infra.gmfdiag.representation.PapyrusDiagram;
import org.eclipse.papyrus.infra.gmfdiag.representation.RepresentationPackage;
import org.eclipse.papyrus.infra.gmfdiag.representation.util.RepresentationSwitch;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * Inference of bundle dependencies from the command classes and icon resources referenced by
 * an <em>Architecture Model</em>.
 */
class ArchitectureDependencies {

	private final String hostBundle;

	ArchitectureDependencies(IProject project) {
		super();

		this.hostBundle = PluginRegistry.findModel(project).getBundleDescription().getSymbolicName();
	}

	Set<String> computeDependencies(Resource resource) {
		return new DependenciesSwitch().doSwitch(resource);
	}

	//
	// Nested types
	//

	final class DependenciesSwitch extends ComposedSwitch<Set<String>> {
		private static final String BUNDLECLASS_SCHEME = "bundleclass"; //$NON-NLS-1$

		private final Set<String> result = new HashSet<>();

		DependenciesSwitch() {
			super();

			addSwitch(createArchitectureSwitch());
			addSwitch(createDiagramSwitch());
		}

		void collectIconDependency(EObject owner, String iconURI) {
			if (iconURI == null) {
				// Okay
				return;
			}

			try {
				ResourceSet rset = owner.eResource().getResourceSet();
				URI uri = rset.getURIConverter().normalize(URI.createURI(iconURI));
				getBundleName(uri).filter(not(hostBundle::equals)).ifPresent(result::add);
			} catch (Exception e) {
				// This will be reported by model validation
			}
		}

		private Optional<String> getBundleName(URI uri) {
			Optional<String> result;

			if (uri.isPlatformPlugin() || uri.isPlatformResource()) {
				result = Optional.of(uri.segment(1));
			} else if (BUNDLECLASS_SCHEME.equals(uri.scheme()) && uri.hasAuthority()) {
				result = Optional.of(uri.authority());
			} else {
				result = Optional.empty();
			}

			return result;
		}

		void collectCommandDependency(EObject owner, EStructuralFeature feature) {
			Optional<String> bundleName;

			Object commandClass = ArchitectureCommandUtils.getCommandClass(owner, feature);
			if (commandClass instanceof Class) {
				Bundle bundle = FrameworkUtil.getBundle((Class<?>) commandClass);
				bundleName = Optional.ofNullable(bundle).map(Bundle::getSymbolicName);
			} else if (commandClass instanceof IType) {
				Optional<IPluginModelBase> plugin = Optional.empty();

				IType commandType = (IType) commandClass;
				if (commandType.isBinary()) {
					IPackageFragmentRoot jar = (IPackageFragmentRoot) commandType.getAncestor(IJavaModel.PACKAGE_FRAGMENT_ROOT);
					if (jar != null) {
						IPath jarPath = jar.getPath();

						// It could be a bundle JAR or a library in a project
						IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(jarPath);
						if (file != null && file.isAccessible()) {
							plugin = Optional.ofNullable(PluginRegistry.findModel(file.getProject()));
						} else {
							for (IPluginModelBase next : PluginRegistry.getExternalModels()) {
								IPath path = new Path(next.getInstallLocation());
								if (jarPath.equals(path) || path.isPrefixOf(jarPath)) {
									plugin = Optional.of(next);
									break;
								}
							}
						}
					}
				} else {
					plugin = Optional.of(((IType) commandClass).getJavaProject())
							.map(IJavaProject::getProject)
							.map(PluginRegistry::findModel);
				}

				bundleName = plugin.map(IPluginModelBase::getBundleDescription)
						.map(BundleDescription::getSymbolicName);
			} else {
				bundleName = Optional.empty();
			}

			bundleName.filter(not(hostBundle::equals)).ifPresent(result::add);
		}

		@Override
		public Set<String> defaultCase(EObject object) {
			object.eContents().forEach(this::doSwitch);
			return result;
		}

		Set<String> doSwitch(Resource resource) {
			resource.getContents().forEach(this::doSwitch);
			return result;
		}

		private Switch<Set<String>> createArchitectureSwitch() {
			return new ArchitectureSwitch<>() {

				public Set<String> caseADElement(ADElement object) {
					collectIconDependency(object, object.getIcon());
					return null;
				}

				@Override
				public Set<String> caseArchitectureContext(ArchitectureContext object) {
					collectCommandDependency(object, ArchitecturePackage.Literals.ARCHITECTURE_CONTEXT__CONVERSION_COMMAND_CLASS);
					collectCommandDependency(object, ArchitecturePackage.Literals.ARCHITECTURE_CONTEXT__CREATION_COMMAND_CLASS);

					return null;
				}

			};
		}

		private Switch<Set<String>> createDiagramSwitch() {
			return new RepresentationSwitch<>() {

				public Set<String> casePapyrusDiagram(PapyrusDiagram object) {
					collectIconDependency(object, object.getGrayedIcon());
					collectCommandDependency(object, RepresentationPackage.Literals.PAPYRUS_DIAGRAM__CREATION_COMMAND_CLASS);
					return null;
				}

			};
		}

	}

}
