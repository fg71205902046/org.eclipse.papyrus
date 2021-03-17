/**
 * Copyright (c) 2017, 2020 CEA LIST, Christian W. Damus, and others.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *  Maged Elaasar - Initial API and implementation
 *  Christian W. Damus - bug 569357
 *
 *
 */
package org.eclipse.papyrus.infra.architecture;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.papyrus.infra.core.architecture.ArchitectureDomain;
import org.eclipse.papyrus.infra.core.architecture.RepresentationKind;
import org.eclipse.papyrus.infra.core.architecture.merged.MergedArchitectureContext;
import org.eclipse.papyrus.infra.core.architecture.merged.MergedArchitectureDescriptionLanguage;
import org.eclipse.papyrus.infra.core.architecture.merged.MergedArchitectureDomain;
import org.eclipse.papyrus.infra.core.architecture.merged.MergedArchitectureViewpoint;
import org.eclipse.papyrus.infra.emf.utils.ResourceUtils;

/**
 * A merger for architecture domains read from extensions or preferences or contributed
 * dynamically (programmatically)
 *
 * It produces a collection of {@link org.eclipse.papyrus.infra.core.architecture.
 * merged.MergedDomain}s by merging a collection of {@link org.eclipse.papyrus.infra.
 * core.architecture.ArchitectureDomain}s.
 *
 * @since 1.0
 */
public class ArchitectureDomainMerger implements Cloneable {

	/**
	 * A resource set used to load architecture models
	 */
	private ResourceSet resourceSet;

	/**
	 * a collection of architecture models coming from extensions
	 */
	private Collection<URI> extensionModels;

	/**
	 * a collection of architecture models coming from preferences
	 */
	private Collection<URI> preferenceModels;

	/**
	 * a collection of architecture domains contributed dynamically (programmatically)
	 */
	private Collection<ArchitectureDomain> dynamicDomains;

	/**
	 * A collection of merged domains
	 */
	private Map<String, MergedArchitectureDomain> mergedDomains;

	/**
	 * a cached mapping from id to ADElement
	 */
	private Map<String, Object> idCache;

	/**
	 * Constructs a new instance of the class
	 */
	ArchitectureDomainMerger() {
	}

	/**
	 * Gets the extension model URIs
	 *
	 * @return the collection of extension model URIs
	 */
	Collection<URI> getExtensionModels() {
		return this.extensionModels;
	}

	/**
	 * Sets the collection of architecture models URIs read from extensions
	 *
	 * @param models
	 *            a collection of architecture model URIs
	 */
	public void setExtensionModels(Collection<URI> models) {
		this.extensionModels = models;
		reset();
	}

	/**
	 * Gets the preference model URIs
	 *
	 * @return the collection of preference model URIs
	 */
	Collection<URI> getPrefereceModels() {
		return this.preferenceModels;
	}

	/**
	 * Sets the collection of architecture model URIs read from preferences
	 *
	 * @param models
	 *            a collection of architecture model URIS
	 */
	public void setPreferenceModels(Collection<URI> models) {
		this.preferenceModels = models;
		reset();
	}

	/**
	 * Gets the dynamic model domains
	 *
	 * @return the collection of dynamic domains
	 */
	Collection<ArchitectureDomain> getDynamicDomains() {
		return this.dynamicDomains;
	}

	/**
	 * Sets the collection of architecture domains contributed dynamically
	 *
	 * @param domains
	 *            a collection of architecture domains
	 */
	public void setDynamicDomains(Collection<ArchitectureDomain> domains) {
		this.dynamicDomains = domains;
		reset();
	}

	/**
	 * Gets the collection of merged architecture domains
	 *
	 * @return the collection of merged architecture domains
	 */
	public Collection<MergedArchitectureDomain> getDomains() {
		if (mergedDomains == null) {
			init();
		}
		return mergedDomains.values();
	}

	/**
	 * Gets an architecture context given its id
	 *
	 * @param id
	 *            an id for an architecture context
	 * @return an architecture context
	 */
	public MergedArchitectureContext getArchitectureContextById(String id) {
		if (mergedDomains == null) {
			init();
		}
		Object found = idCache.get(id);
		return (found instanceof MergedArchitectureContext) ? (MergedArchitectureContext) found : null;
	}

	/**
	 * Gets an architecture viewpoint given its id
	 *
	 * @param id
	 *            an id for an architecture viewpoint
	 * @return an architecture viewpoint
	 */
	public MergedArchitectureViewpoint getArchitectureViewpointById(String id) {
		if (mergedDomains == null) {
			init();
		}
		Object found = idCache.get(id);
		return (found instanceof MergedArchitectureViewpoint) ? (MergedArchitectureViewpoint) found : null;
	}

	/**
	 * Gets a representation kind given its id
	 *
	 * @param id
	 *            an id for an representation kind
	 * @return a representation kind
	 */
	public RepresentationKind getRepresentationKindById(String id) {
		if (mergedDomains == null) {
			init();
		}
		Object found = idCache.get(id);
		return (found instanceof RepresentationKind) ? (RepresentationKind) found : null;
	}

	@Override
	public ArchitectureDomainMerger clone() {
		ArchitectureDomainMerger clone = new ArchitectureDomainMerger();
		clone.setExtensionModels(extensionModels);
		clone.setPreferenceModels(preferenceModels);
		return clone;
	}

	/*
	 * Resets the merger's state
	 */
	private void reset() {
		resourceSet = null;
		mergedDomains = null;
		idCache = null;
	}

	/**
	 * Initializes this instance of the merger
	 */
	void init() {
		resourceSet = new ResourceSetImpl();
		resourceSet.setURIConverter(ResourceUtils.createWorkspaceAwareURIConverter());
		mergedDomains = new HashMap<>();

		Stream<URI> modelURIs = Stream.empty();
		if (extensionModels != null) {
			modelURIs = Stream.concat(modelURIs, extensionModels.stream());
		}
		if (preferenceModels != null) {
			modelURIs = Stream.concat(modelURIs, preferenceModels.stream());
		}

		// In case of preference registrations that are redundant, don't repeat any models
		modelURIs.map(resourceSet.getURIConverter()::normalize).distinct().forEach(model -> {
			ArchitectureDomain domain = loadDomain(resourceSet.createResource(model));
			if (domain != null) {
				merge(domain);
			}
		});

		// And then there's the ones that we don't know where they came from because we didn't load them
		if (dynamicDomains != null) {
			for (ArchitectureDomain domain : dynamicDomains) {
				if (domain != null) {
					merge(domain);
				}
			}
		}

		buildCache();
	}

	/*
	 * Read an architecture domain from the given architecture model resource
	 */
	private ArchitectureDomain loadDomain(Resource resource) {
		try {
			resource.load(null);
		} catch (IOException e) {
			// Don't log the error yet; we're trying several options
			return null;
		}
		EObject content = resource.getContents().get(0);
		if (content instanceof ArchitectureDomain) {
			return (ArchitectureDomain) content;
		} else {
			Activator.log.warn("file " + resource.getURI() + " is not an architecture model");
		}
		return null;
	}

	/*
	 * Merges the given architecture domain with the others
	 */
	private void merge(ArchitectureDomain domain) {
		MergedArchitectureDomain merged = mergedDomains.get(domain.getName());
		if (merged == null) {
			mergedDomains.put(domain.getName(), merged = new MergedArchitectureDomain());
		}
		merged.merge(domain);
	}

	/*
	 * builds a id to element cache for faster lookup
	 */
	private void buildCache() {
		idCache = new HashMap<>();
		for (MergedArchitectureDomain domain : mergedDomains.values()) {
			for (MergedArchitectureContext context : domain.getContexts()) {
				idCache.put(context.getId(), context);
				for (MergedArchitectureViewpoint viewpoint : context.getViewpoints()) {
					idCache.put(viewpoint.getId(), viewpoint);
				}
				if (context instanceof MergedArchitectureDescriptionLanguage) {
					MergedArchitectureDescriptionLanguage language = (MergedArchitectureDescriptionLanguage) context;
					for (RepresentationKind representationKind : language.getRepresentationKinds()) {
						idCache.put(representationKind.getId(), representationKind);
					}
				}
			}
		}
	}

}
