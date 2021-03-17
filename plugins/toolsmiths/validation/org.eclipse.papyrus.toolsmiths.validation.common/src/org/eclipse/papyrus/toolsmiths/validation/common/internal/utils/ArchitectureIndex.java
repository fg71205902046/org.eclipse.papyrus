/*****************************************************************************
 * Copyright (c) 2020 Christian W. Damus, CEA LIST, and others.
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

package org.eclipse.papyrus.toolsmiths.validation.common.internal.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.infra.architecture.ArchitectureDomainManager;
import org.eclipse.papyrus.infra.core.architecture.ADElement;
import org.eclipse.papyrus.infra.core.architecture.ArchitectureDomain;
import org.eclipse.papyrus.infra.core.architecture.merged.MergedArchitectureDomain;
import org.eclipse.papyrus.infra.core.utils.JobExecutorService;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimap;

/**
 * An index of references to tooling models from the {@link ArchitectureDomain} models available
 * in the current context (installation, target, and workspace).
 */
public class ArchitectureIndex {

	private static final ArchitectureIndex INSTANCE = new ArchitectureIndex();

	private final ArchitectureDomainManager domainManager;

	private final Executor executor = new JobExecutorService();

	private AtomicReference<CompletableFuture<Multimap<EObject, EStructuralFeature.Setting>>> externalCrossReferences = new AtomicReference<>();

	/**
	 * Not instantiable by clients.
	 */
	private ArchitectureIndex() {
		super();

		domainManager = ArchitectureDomainManager.getInstance();
		domainManager.addListener(this::domainManagerChanged);
	}

	/**
	 * Get the architecture index.
	 *
	 * @return the architecture index
	 */
	public static ArchitectureIndex getInstance() {
		return INSTANCE;
	}

	/**
	 * Obtain a calculation of all cross-references from registered architecture models to tooling models
	 * (and anything else) not contained within one of those architecture models.
	 *
	 * @return the external cross-references of the current architecture domain models
	 */
	public Future<Multimap<EObject, EStructuralFeature.Setting>> getExternalCrossReferences() {
		CompletableFuture<Multimap<EObject, EStructuralFeature.Setting>> newResult = new CompletableFuture<>();
		CompletableFuture<Multimap<EObject, EStructuralFeature.Setting>> result = externalCrossReferences.compareAndExchange(null, newResult);

		if (result == null) {
			// We made the exchange, so should initiate the computation
			result = newResult;
			result.completeAsync(this::computeExternalCrossReferences, executor);
		}

		return result;
	}

	private Multimap<EObject, EStructuralFeature.Setting> computeExternalCrossReferences() {
		Set<? extends ADElement> architectureDomains = ArchitectureDomainManager.getInstance().getMerger().getDomains().stream()
				.map(MergedArchitectureDomain::getMergedElements)
				.flatMap(Collection::stream)
				.collect(Collectors.toSet());

		ImmutableListMultimap.Builder<EObject, EStructuralFeature.Setting> result = ImmutableListMultimap.builder();
		for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> next : EcoreUtil.ExternalCrossReferencer.find(architectureDomains).entrySet()) {
			result.putAll(next.getKey(), next.getValue());
		}

		return result.build();
	}

	/**
	 * Cancel any pending index calculations; forget all index calculations.
	 */
	private void domainManagerChanged() {
		Optional.ofNullable(externalCrossReferences.getAndSet(null)).ifPresent(future -> future.cancel(false));
	}

}
