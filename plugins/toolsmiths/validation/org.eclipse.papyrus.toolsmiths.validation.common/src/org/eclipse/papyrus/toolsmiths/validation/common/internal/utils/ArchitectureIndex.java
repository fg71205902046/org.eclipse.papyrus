/*****************************************************************************
 * Copyright (c) 2020, 2021 Christian W. Damus, CEA LIST, and others.
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
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.infra.architecture.ArchitectureDomainManager;
import org.eclipse.papyrus.infra.core.architecture.ADElement;
import org.eclipse.papyrus.infra.core.architecture.ArchitectureDomain;
import org.eclipse.papyrus.infra.core.architecture.merged.MergedArchitectureDomain;
import org.eclipse.papyrus.infra.core.utils.JobExecutorService;
import org.eclipse.papyrus.infra.emf.utils.InternalCrossReferencer;
import org.eclipse.papyrus.toolsmiths.validation.common.Activator;

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

	private final Map<Mode, Computation<Multimap<EObject, EStructuralFeature.Setting>>> crossReferences = new EnumMap<>(Map.of(
			Mode.EXTERNAL_CROSS_REFERENCE, new Computation<>(this::computeExternalCrossReferences),
			Mode.INTERNAL_CROSS_REFERENCE, new Computation<>(this::computeInternalCrossReferences)));

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
	 * @param crossReferenceMode
	 *            the cross-reference mode to query
	 * @return the external cross-references of the current architecture domain models
	 */
	public CompletableFuture<Multimap<EObject, EStructuralFeature.Setting>> getCrossReferences(Mode crossReferenceMode) {
		return crossReferences.get(crossReferenceMode).get();
	}

	/**
	 * Obtain a calculation of all cross-references from registered architecture models to tooling models
	 * (and anything else) not contained within one of those architecture models.
	 *
	 * @return the external cross-references of the current architecture domain models
	 */
	public CompletableFuture<Multimap<EObject, EStructuralFeature.Setting>> getExternalCrossReferences() {
		return getCrossReferences(Mode.EXTERNAL_CROSS_REFERENCE);
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
	 * Obtain a calculation of all cross-references from objects in registered architecture models to other objects
	 * contained within one of those architecture models.
	 *
	 * @return the internal cross-references of the current architecture domain models
	 */
	public CompletableFuture<Multimap<EObject, EStructuralFeature.Setting>> getInternalCrossReferences() {
		return getCrossReferences(Mode.INTERNAL_CROSS_REFERENCE);
	}

	private Multimap<EObject, EStructuralFeature.Setting> computeInternalCrossReferences() {
		Set<? extends ADElement> architectureDomains = ArchitectureDomainManager.getInstance().getMerger().getDomains().stream()
				.map(MergedArchitectureDomain::getMergedElements)
				.flatMap(Collection::stream)
				.collect(Collectors.toSet());

		ImmutableListMultimap.Builder<EObject, EStructuralFeature.Setting> result = ImmutableListMultimap.builder();
		for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> next : InternalCrossReferencer.find(architectureDomains).entrySet()) {
			result.putAll(next.getKey(), next.getValue());
		}

		return result.build();
	}

	/**
	 * Cancel any pending index calculations; forget all index calculations.
	 */
	private void domainManagerChanged() {
		crossReferences.values().forEach(Computation::reset);
	}

	/**
	 * Query whether any object in the given {@code resource} is referenced by some registered <em>architecture model</em>.
	 *
	 * @param crossReferenceMode
	 *            the cross-reference mode to query
	 * @param resource
	 *            a resource
	 * @return whether it is referenced by a registered architecture model
	 */
	public boolean isReferenced(Mode crossReferenceMode, Resource resource) {
		return isReferenced(crossReferenceMode, resource, null);
	}

	/**
	 * Query whether any object in the given {@code resource} is referenced by some registered <em>architecture model</em>
	 * via the given {@code reference}. Objects cross-referenced by other references are not considered.
	 *
	 * @param crossReferenceMode
	 *            the cross-reference mode to query
	 * @param resource
	 *            a resource
	 * @param reference
	 *            the reference to consider by which objects in the {@code resource} may be referenced
	 * @return whether any object in the {@code resource} is referenced by a registered architecture model via the {@code reference}
	 */
	public boolean isReferenced(Mode crossReferenceMode, Resource resource, EReference reference) {
		return Optional.ofNullable(resource).map(Resource::getResourceSet)
				.map(context -> isReferenced(crossReferenceMode, resource.getURI(), reference, context))
				.orElse(false);
	}

	/**
	 * Query whether the given {@code object} is referenced by some registered <em>architecture model</em>.
	 * The cross-reference mode is inferred from the type of {@code object}, which either is an <em>architecture
	 * model</em> element, implying internal cross-reference search, or not, implying external cross-references.
	 *
	 * @param object
	 *            a model object
	 * @return whether it is referenced by a registered architecture model
	 */
	public boolean isReferenced(EObject object) {
		return isReferenced(object, null);
	}

	/**
	 * Query whether the given {@code object} is referenced by some registered <em>architecture model</em>
	 * via the given {@code reference}. Objects cross-referenced by other references are not considered.
	 * The cross-reference mode is inferred from the type of {@code object}, which either is an <em>architecture
	 * model</em> element, implying internal cross-reference search, or not, implying external cross-references.
	 *
	 * @param object
	 *            a model object
	 * @param reference
	 *            the reference to consider by which objects may be referenced
	 * @return whether the {@code object} is referenced by a registered architecture model via the {@code reference}
	 */
	public boolean isReferenced(EObject object, EReference reference) {
		return Optional.ofNullable(object).map(EObject::eResource).map(Resource::getResourceSet)
				.map(context -> isReferenced(inferCrossReferenceMode(object), EcoreUtil.getURI(object), reference, context))
				.orElse(false);
	}

	/**
	 * Infer the cross-reference mode to query for an {@code object}. The mode will be inferred as
	 * {@linkplain Mode#INTERNAL_CROSS_REFERENCE internal} if the {@code object} is in an architecture
	 * domain; {@linkplain Mode#EXTERNAL_CROSS_REFERENCE external}, otherwise.
	 *
	 * @param object
	 *            an object for which to query cross-references
	 * @return the inferred cross-reference query mode
	 */
	private static Mode inferCrossReferenceMode(EObject object) {
		return (object instanceof ADElement)
				? Mode.INTERNAL_CROSS_REFERENCE
				: object != null && object.eContainer() != null
						? inferCrossReferenceMode(object.eContainer())
						: Mode.EXTERNAL_CROSS_REFERENCE;
	}

	/**
	 * Query whether any registered <em>architecture model</em> has an HREF matching the given {@code uri}.
	 *
	 * @param crossReferenceMode
	 *            the cross-reference mode to query
	 * @param uri
	 *            an URI, which may be a resource URI or an object URI ({@linkplain URI#hasFragment() with fragment})
	 * @param context
	 *            the resource set in which to resolve/convert URIs for stable comparison
	 * @return whether any registered architecture has an HREF matching the given URI
	 */
	public boolean isReferenced(Mode crossReferenceMode, URI uri, ResourceSet context) {
		return isReferenced(crossReferenceMode, uri, null, context);
	}

	/**
	 * Query whether any registered <em>architecture model</em> has an HREF matching the given {@code uri}
	 * in the given {@code reference}. HREFs in other references are not considered.
	 *
	 * @param crossReferenceMode
	 *            the cross-reference mode to query
	 * @param uri
	 *            an URI, which may be a resource URI or an object URI ({@linkplain URI#hasFragment() with fragment})
	 * @param reference
	 *            the reference to consider in looking for HREFs
	 * @param context
	 *            the resource set in which to resolve/convert URIs for stable comparison
	 * @return whether any registered architecture has an HREF matching the given URI in the {@code reference}
	 */
	public boolean isReferenced(Mode crossReferenceMode, URI uri, EReference reference, ResourceSet context) {
		boolean result = false;

		try {
			CompletableFuture<Boolean> futureResult = isReferencedAsync(crossReferenceMode, uri, reference, context);
			result = Boolean.TRUE.equals(futureResult.get());
		} catch (ExecutionException | InterruptedException e) {
			// Cannot access the architecture index? Then we didn't find anything
			Activator.log.error("Error querying Architecture Context models.", e); //$NON-NLS-1$
		}

		return result;
	}

	/**
	 * Asynchronously query whether any registered <em>architecture model</em> has an HREF matching the given {@code uri}
	 * in the given {@code reference}. HREFs in other references are not considered.
	 *
	 * @param crossReferenceMode
	 *            the cross-reference mode to query
	 * @param uri
	 *            an URI, which may be a resource URI or an object URI ({@linkplain URI#hasFragment() with fragment})
	 * @param reference
	 *            the reference to consider in looking for HREFs
	 * @param context
	 *            the resource set in which to resolve/convert URIs for stable comparison
	 * @return whether any registered architecture has an HREF matching the given URI in the {@code reference}
	 */
	public CompletableFuture<Boolean> isReferencedAsync(Mode crossReferenceMode, URI uri, EReference reference, ResourceSet context) {
		URIConverter converter = context.getURIConverter();
		Function<URI, URI> uriTrimmer = uri.hasFragment() ? Function.identity() : URI::trimFragment;
		Predicate<Map.Entry<EObject, EStructuralFeature.Setting>> referenceFilter = (reference == null)
				? __ -> true
				: entry -> entry.getValue().getEStructuralFeature() == reference;

		// The architecture models are loaded in their own resource set, so look for our model by URI
		Predicate<Multimap<EObject, EStructuralFeature.Setting>> isReferenced = xrefs -> xrefs
				.entries().stream()
				.filter(referenceFilter)
				.map(Map.Entry::getKey)
				.map(EcoreUtil::getURI)
				.map(uriTrimmer)
				.map(converter::normalize)
				.anyMatch(uri::equals);

		return getCrossReferences(crossReferenceMode).thenApply(isReferenced::test);
	}

	//
	// Nested types
	//

	/**
	 * Cross-reference indexing/searching modes.
	 */
	public static enum Mode {
		/** Search cross-references from architecture models to other tooling models. */
		EXTERNAL_CROSS_REFERENCE,
		/** Search cross-references wihin and between architecture models only. */
		INTERNAL_CROSS_REFERENCE;
	}

	/**
	 * A potentially long-running computation that provides for thread-safe initiation
	 * and cancellation/reset to re-compute when inputs change.
	 *
	 * @param <T>
	 *            the type of the computation
	 */
	private final class Computation<T> implements Supplier<CompletableFuture<T>> {
		private final AtomicReference<CompletableFuture<T>> computation = new AtomicReference<>();
		private final Supplier<T> computer;

		Computation(Supplier<T> computer) {
			super();

			this.computer = computer;
		}

		@Override
		public CompletableFuture<T> get() {
			CompletableFuture<T> newResult = new CompletableFuture<>();
			CompletableFuture<T> result = computation.compareAndExchange(null, newResult);

			if (result == null) {
				// We made the exchange, so should initiate the computation
				result = newResult;
				result.completeAsync(computer, executor);
			}

			return result;
		}

		void reset() {
			Optional.ofNullable(computation.getAndSet(null)).ifPresent(future -> future.cancel(false));
		}

	}

}
