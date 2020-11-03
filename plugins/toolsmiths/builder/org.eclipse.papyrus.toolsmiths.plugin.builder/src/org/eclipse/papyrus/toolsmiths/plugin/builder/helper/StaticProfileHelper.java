/*****************************************************************************
 * Copyright (c) 2020 CEA LIST, EclipseSource and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Remi Schnekenburger - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.toolsmiths.plugin.builder.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.core.editor.ModelSetServiceFactory;
import org.eclipse.papyrus.infra.core.resource.EditingDomainServiceFactory;
import org.eclipse.papyrus.infra.core.resource.ModelMultiException;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.ModelsReader;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.sasheditor.DiModel;
import org.eclipse.papyrus.infra.core.services.ExtensionServicesRegistry;
import org.eclipse.papyrus.infra.core.services.ServiceDescriptor;
import org.eclipse.papyrus.infra.core.services.ServiceDescriptor.ServiceTypeKind;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServiceStartKind;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.toolsmiths.plugin.builder.Activator;
import org.eclipse.papyrus.uml.tools.model.UmlUtils;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.uml2.uml.Profile;

/**
 * Helper for Static profile builders.
 */
public class StaticProfileHelper {

	private StaticProfileHelper() {
		// no instance
	}

	/**
	 * Return the list of the static profile files (.di) for a given project.
	 *
	 * @param builtProject
	 *            the project in which static profiles are found.
	 * @param includeSubProfiles
	 *            <code>true</code> if all profiles within the files are to be returned, only the root profile if <code>false</code>
	 * @return the map of the found profile files, with the list of contained profile model elements as a value
	 */
	public final static Map<IFile, List<Profile>> findStaticProfiles(IProject builtProject, boolean includeSubProfiles) {
		StaticProfileResourceVisitor visitor = new StaticProfileResourceVisitor(includeSubProfiles);
		try {
			builtProject.accept(visitor, IResource.DEPTH_INFINITE, true);
			return visitor.foundProfiles;
		} catch (CoreException e) {
			Activator.log.error(e);
		}
		return null;
	}

	private static class StaticProfileResourceVisitor implements IResourceVisitor {

		private static final String GENMODEL_EXTENSION = "genmodel";
		private Map<IFile, List<Profile>> foundProfiles = new HashMap<>();

		private final boolean includeSubProfiles;

		public StaticProfileResourceVisitor(boolean includeSubProfiles) {
			this.includeSubProfiles = includeSubProfiles;
		}

		@Override
		public boolean visit(IResource resource) throws CoreException {

			if (resource.getType() == IResource.FILE && DiModel.DI_FILE_EXTENSION.equals(resource.getFileExtension())) {
				// check sibling gen model file
				// full path for gen model
				String fileName = resource.getFullPath().lastSegment();
				String genModelName = fileName.substring(0, fileName.length() - DiModel.DI_FILE_EXTENSION.length()).concat(GENMODEL_EXTENSION);
				IResource genModelResource = resource.getParent().findMember(genModelName);
				if (genModelResource == null) {
					return true;
				}

				// papyrus model found, now trying to load to see if this is a profile
				URI diFileUri = URI.createPlatformResourceURI(resource.getFullPath().toString(), true);
				ModelSet modelSet = initialiseModelSet(diFileUri);
				if (modelSet == null) {
					return true;
				}
				try {
					EObject root = UmlUtils.getUmlModel(modelSet).lookupRoot();
					if (root instanceof Profile) {
						Profile profile = (Profile) root;
						List<Profile> profiles = new ArrayList<>();
						profiles.add(profile);
						if (includeSubProfiles) {
							profiles.addAll(PackageUtil.getSubProfiles(profile));
						}
						foundProfiles.put((IFile) resource, profiles);
					}
				} catch (NotFoundException e) {
				}
			}

			return true;
		}

		private ModelSet initialiseModelSet(URI diFileUri) {
			ServicesRegistry service = null;

			try {
				service = new ExtensionServicesRegistry();
			} catch (ServiceException e) {
				Activator.log.error(e);
				return null;
			}

			// Override service factory for Model Set
			ServiceDescriptor descriptor = new ServiceDescriptor(ModelSet.class, ModelSetServiceFactory.class.getName(),
					ServiceStartKind.STARTUP, 10);
			descriptor.setServiceTypeKind(ServiceTypeKind.serviceFactory);
			service.add(descriptor);

			// Override factory for editing domain
			descriptor = new ServiceDescriptor(TransactionalEditingDomain.class,
					EditingDomainServiceFactory.class.getName(), ServiceStartKind.STARTUP, 10,
					Collections.singletonList(ModelSet.class.getName()));
			descriptor.setServiceTypeKind(ServiceTypeKind.serviceFactory);
			service.add(descriptor);

			try {
				service.startServicesByClassKeys(ModelSet.class, TransactionalEditingDomain.class);
			} catch (ServiceException e) {
				Activator.log.error(e);
			}

			ModelSet modelSet = null;
			try {
				modelSet = ServiceUtils.getInstance().getModelSet(service);
			} catch (ServiceException e) {
				// Ignore service exception
			}

			// Instantiate a Model set
			if (modelSet == null) {
				modelSet = new ModelSet();
				try {
					ModelSetServiceFactory.setServiceRegistry(modelSet, service);
				} catch (ServiceException e) {
					// Ignore service exception
				}
			}

			// Read all Model from selected file
			ModelsReader modelsReader = new ModelsReader();
			modelsReader.readModel(modelSet);
			try {
				modelSet.loadModels(diFileUri);
			} catch (ModelMultiException e) {
				Activator.log.error(e);
			}
			// Initialize an editing domain
			modelSet.getTransactionalEditingDomain();
			return modelSet;
		}
	}

}
