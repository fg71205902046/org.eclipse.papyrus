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

package org.eclipse.papyrus.infra.core.architecture.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.infra.core.architecture.ArchitecturePackage;
import org.eclipse.papyrus.infra.core.architecture.impl.ArchitecturePlugin;
import org.eclipse.papyrus.infra.tools.util.ClasspathHelper;
import org.osgi.framework.Bundle;

/**
 * Utilities for working with/resolving the command classes referenced by
 * <em>Architecture Models</em>.
 *
 * @since 3.1
 */
public class ArchitectureCommandUtils {

	private static final String CLASS_CONSTRAINT = "classConstraint"; //$NON-NLS-1$
	private static final String BUNDLECLASS = "bundleclass"; //$NON-NLS-1$

	private static final Map<EStructuralFeature, Class<?>> commandClassConstraints = new ConcurrentHashMap<>();

	/**
	 * Obtain the type constraining command classes referenced by the given feature.
	 *
	 * @param commandClassFeature
	 *            the model feature that names a command class
	 * @return the type to which command classes named by the feature must conform
	 */
	public static Optional<Class<?>> getCommandType(EStructuralFeature commandClassFeature) {
		return Optional.ofNullable(commandClassConstraints.computeIfAbsent(commandClassFeature, ArchitectureCommandUtils::loadClassAnnotation));
	}

	private static Class<?> loadClassAnnotation(EStructuralFeature feature) {
		Class<?> result = null;

		String classURI = EcoreUtil.getAnnotation(feature, ArchitecturePackage.eNS_URI, CLASS_CONSTRAINT);
		if (classURI != null) {
			URI uri = URI.createURI(classURI);
			if (!BUNDLECLASS.equals(uri.scheme())) {
				result = fail("Constraint class URI does not have bundleclass scheme: " + uri); //$NON-NLS-1$
			} else if (uri.authority() == null) {
				result = fail("Constraint class URI does not have an authority: " + uri); //$NON-NLS-1$
			} else if (uri.segmentCount() != 1) {
				result = fail("Constraint class URI must have exactly one segment: " + uri); //$NON-NLS-1$
			} else {
				Bundle bundle = Platform.getBundle(uri.authority());
				if (bundle == null) {
					result = fail("No such bundle in constraint class URI: " + uri); //$NON-NLS-1$
				} else {
					try {
						result = bundle.loadClass(uri.segment(0));
					} catch (Exception e) {
						result = fail(e);
					}
				}
			}
		}

		return result;
	}

	private static String getClassBundleAnnotation(EObject owner, EStructuralFeature feature) {
		String result = null;

		if (owner.eIsSet(feature)) {
			String classURI = EcoreUtil.getAnnotation(feature, ArchitecturePackage.eNS_URI, CLASS_CONSTRAINT);
			URI uri = classURI == null ? null : URI.createURI(classURI);

			if (uri != null && BUNDLECLASS.equals(uri.scheme())) {
				result = uri.authority();
			}
		}

		return result;
	}

	private static Class<?> fail(String message) {
		return fail(new IllegalArgumentException(message));
	}

	private static Class<?> fail(Throwable exception) {
		ArchitecturePlugin.INSTANCE.log(exception);
		return Void.class; // Bomb the usage of this class-name feature
	}

	/**
	 * Get the command class referenced by the given feature of an architecture model object, if it
	 * conforms to constraints (if any) declared in the Ecore model.
	 *
	 * @param modelObject
	 *            the architecture model object for which to load a command class
	 * @param commandClassFeature
	 *            the model feature that names the command class
	 * @return
	 *         the referenced class, or {@code null} if the class doesn't exist or is invalid
	 *         (such as not conforming to its constraining type). In such a case, the
	 *         exception is logged. The result may be a Java {@link Class} or a JDT
	 *         {@code IType}, depending whether JDT is available
	 */
	public static Object getCommandClass(EObject modelObject, EStructuralFeature commandClassFeature) {
		return getCommandClass(modelObject, commandClassFeature, getCommandType(commandClassFeature));
	}

	private static Object getCommandClass(EObject modelObject, EStructuralFeature commandClassFeature, Optional<Class<?>> registeredType) {
		if (commandClassFeature.getEType().getInstanceClass() == Class.class) {
			// Easy
			Class<?> result = (Class<?>) modelObject.eGet(commandClassFeature);
			if (result != null && registeredType.isPresent() && !registeredType.get().isAssignableFrom(result)) {
				result = null;
			}
			return result;
		}

		String className = Optional.ofNullable(modelObject.eGet(commandClassFeature)).map(String::valueOf).orElse(null);
		if (className == null) {
			// Easy
			return null;
		}

		URI context = EcoreUtil.getURI(modelObject).trimFragment();

		return ClasspathHelper.INSTANCE.findClass(className, context, registeredType.orElse(null));
	}

	/**
	 * Get the command class referenced by the given feature of an architecture model object, not considering
	 * any constraints imposed on it by the Ecore model.
	 *
	 * @param modelObject
	 *            the architecture model object for which to load a command class
	 * @param commandClassFeature
	 *            the model feature that names the command class
	 * @return
	 *         the referenced class, or {@code null} if the class doesn't exist. The result may be a Java
	 *         {@link Class} or a JDT {@code IType}, depending whether JDT is available
	 */
	public static Object getCommandClassUnconstrained(EObject modelObject, EStructuralFeature commandClassFeature) {
		return getCommandClass(modelObject, commandClassFeature, Optional.empty());
	}

	/**
	 * Search the content tree of a model object for bundle dependency requirements implied by features referencing
	 * command classes.
	 *
	 * @param modelObject
	 *            a model tree
	 * @return the bundle dependencies implied by command class features set in the content of the tree
	 */
	public static Set<String> getRequiredCommandBundleDependencies(EObject modelObject) {
		Set<String> result = new HashSet<>();

		Map<EClass, List<EStructuralFeature>> commandClassFeatures = new HashMap<>();

		for (Iterator<EObject> iter = EcoreUtil.getAllContents(Set.of(modelObject)); iter.hasNext();) {
			EObject next = iter.next();
			for (EStructuralFeature feature : getCommandClassFeatures(next.eClass(), commandClassFeatures)) {
				String bundleDependency = getClassBundleAnnotation(next, feature);
				if (bundleDependency != null) {
					result.add(bundleDependency);
				}
			}
		}

		return result;
	}

	/**
	 * Get the features of a model class that are constrained command class features.
	 *
	 * @param eClass
	 *            an EClass
	 * @param commandClassFeatures
	 *            a cache of previously computed constrained command class features
	 * @return the class's constrained command-class features
	 */
	private static Iterable<EStructuralFeature> getCommandClassFeatures(EClass eClass, Map<EClass, List<EStructuralFeature>> commandClassFeatures) {
		List<EStructuralFeature> result = commandClassFeatures.get(eClass);

		if (result == null) {
			result = eClass.getEAllStructuralFeatures().stream()
					.filter(f -> EcoreUtil.getAnnotation(f, ArchitecturePackage.eNS_URI, CLASS_CONSTRAINT) != null)
					.collect(Collectors.toList());
			commandClassFeatures.put(eClass, result);
		}

		return result;
	}

}
