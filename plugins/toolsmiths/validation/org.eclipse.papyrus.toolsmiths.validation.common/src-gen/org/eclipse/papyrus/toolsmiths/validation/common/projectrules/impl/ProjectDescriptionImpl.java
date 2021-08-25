/**
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
 */
package org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Dependency;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectRulesPackage;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.util.ProjectRulesCache;
import org.eclipse.uml2.common.util.SubsetSupersetEObjectContainmentEList;
import org.eclipse.uml2.common.util.SubsetSupersetEObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Project Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectDescriptionImpl#getDependencies <em>Dependency</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectDescriptionImpl#getRequiredDependencies <em>Required Dependency</em>}</li>
 * <li>{@link org.eclipse.papyrus.toolsmiths.validation.common.projectrules.impl.ProjectDescriptionImpl#getExtensions <em>Extension</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProjectDescriptionImpl extends MinimalEObjectImpl.Container implements ProjectDescription {
	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependency</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<Dependency> dependencies;

	/**
	 * The cached value of the '{@link #getRequiredDependencies() <em>Required Dependency</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getRequiredDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<Dependency> requiredDependencies;

	/**
	 * The cached value of the '{@link #getExtensions() <em>Extension</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> extensions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ProjectDescriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProjectRulesPackage.Literals.PROJECT_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EList<Dependency> getDependencies() {
		if (dependencies == null) {
			dependencies = new SubsetSupersetEObjectContainmentEList<>(Dependency.class, this, ProjectRulesPackage.PROJECT_DESCRIPTION__DEPENDENCY, null, DEPENDENCY_ESUBSETS);
		}
		return dependencies;
	}

	/**
	 * The array of subset feature identifiers for the '{@link #getDependencies() <em>Dependency</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected static final int[] DEPENDENCY_ESUBSETS = new int[] { ProjectRulesPackage.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY };

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Dependency createDependency(String name) {
		Dependency newDependency = (Dependency) create(ProjectRulesPackage.Literals.DEPENDENCY);
		getDependencies().add(newDependency);
		if (name != null) {
			newDependency.setName(name);
		}
		return newDependency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Dependency getDependency(String name) {
		return getDependency(name, false, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Dependency getDependency(String name, boolean ignoreCase, boolean createOnDemand) {
		dependencyLoop: for (Dependency dependency : getDependencies()) {
			if (name != null && !(ignoreCase ? name.equalsIgnoreCase(dependency.getName()) : name.equals(dependency.getName()))) {
				continue dependencyLoop;
			}
			return dependency;
		}
		return createOnDemand ? createDependency(name) : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EList<Dependency> getRequiredDependencies() {
		if (requiredDependencies == null) {
			requiredDependencies = new SubsetSupersetEObjectResolvingEList<>(Dependency.class, this, ProjectRulesPackage.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY, REQUIRED_DEPENDENCY_ESUPERSETS, null);
		}
		return requiredDependencies;
	}

	/**
	 * The array of superset feature identifiers for the '{@link #getRequiredDependencies() <em>Required Dependency</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getRequiredDependencies()
	 * @generated
	 * @ordered
	 */
	protected static final int[] REQUIRED_DEPENDENCY_ESUPERSETS = new int[] { ProjectRulesPackage.PROJECT_DESCRIPTION__DEPENDENCY };

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Dependency createRequiredDependency(String name) {
		Dependency newRequiredDependency = (Dependency) create(ProjectRulesPackage.Literals.DEPENDENCY);
		getRequiredDependencies().add(newRequiredDependency);
		if (name != null) {
			newRequiredDependency.setName(name);
		}
		return newRequiredDependency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Dependency getRequiredDependency(String name) {
		return getRequiredDependency(name, false, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Dependency getRequiredDependency(String name, boolean ignoreCase, boolean createOnDemand) {
		requiredDependencyLoop: for (Dependency requiredDependency : getRequiredDependencies()) {
			if (name != null && !(ignoreCase ? name.equalsIgnoreCase(requiredDependency.getName()) : name.equals(requiredDependency.getName()))) {
				continue requiredDependencyLoop;
			}
			return requiredDependency;
		}
		return createOnDemand ? createRequiredDependency(name) : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EList<Extension> getExtensions() {
		if (extensions == null) {
			extensions = new EObjectContainmentWithInverseEList<>(Extension.class, this, ProjectRulesPackage.PROJECT_DESCRIPTION__EXTENSION, ProjectRulesPackage.EXTENSION__PROJECT);
		}
		return extensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Extension createExtension(String extensionPoint) {
		Extension newExtension = (Extension) create(ProjectRulesPackage.Literals.EXTENSION);
		getExtensions().add(newExtension);
		if (extensionPoint != null) {
			newExtension.setExtensionPoint(extensionPoint);
		}
		return newExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Extension getExtension(String extensionPoint) {
		return getExtension(extensionPoint, false, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Extension getExtension(String extensionPoint, boolean ignoreCase, boolean createOnDemand) {
		extensionLoop: for (Extension extension : getExtensions()) {
			if (extensionPoint != null && !(ignoreCase ? extensionPoint.equalsIgnoreCase(extension.getExtensionPoint()) : extensionPoint.equals(extension.getExtensionPoint()))) {
				continue extensionLoop;
			}
			return extension;
		}
		return createOnDemand ? createExtension(extensionPoint) : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ProjectRulesPackage.PROJECT_DESCRIPTION__EXTENSION:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getExtensions()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ProjectRulesPackage.PROJECT_DESCRIPTION__DEPENDENCY:
			return ((InternalEList<?>) getDependencies()).basicRemove(otherEnd, msgs);
		case ProjectRulesPackage.PROJECT_DESCRIPTION__EXTENSION:
			return ((InternalEList<?>) getExtensions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ProjectRulesPackage.PROJECT_DESCRIPTION__DEPENDENCY:
			return getDependencies();
		case ProjectRulesPackage.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY:
			return getRequiredDependencies();
		case ProjectRulesPackage.PROJECT_DESCRIPTION__EXTENSION:
			return getExtensions();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ProjectRulesPackage.PROJECT_DESCRIPTION__DEPENDENCY:
			getDependencies().clear();
			getDependencies().addAll((Collection<? extends Dependency>) newValue);
			return;
		case ProjectRulesPackage.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY:
			getRequiredDependencies().clear();
			getRequiredDependencies().addAll((Collection<? extends Dependency>) newValue);
			return;
		case ProjectRulesPackage.PROJECT_DESCRIPTION__EXTENSION:
			getExtensions().clear();
			getExtensions().addAll((Collection<? extends Extension>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ProjectRulesPackage.PROJECT_DESCRIPTION__DEPENDENCY:
			getDependencies().clear();
			return;
		case ProjectRulesPackage.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY:
			getRequiredDependencies().clear();
			return;
		case ProjectRulesPackage.PROJECT_DESCRIPTION__EXTENSION:
			getExtensions().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ProjectRulesPackage.PROJECT_DESCRIPTION__DEPENDENCY:
			return dependencies != null && !dependencies.isEmpty();
		case ProjectRulesPackage.PROJECT_DESCRIPTION__REQUIRED_DEPENDENCY:
			return requiredDependencies != null && !requiredDependencies.isEmpty();
		case ProjectRulesPackage.PROJECT_DESCRIPTION__EXTENSION:
			return extensions != null && !extensions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * Creates a new instance of the specified Ecore class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param eClass
	 *                   The Ecore class of the instance to create.
	 * @return The new instance.
	 * @generated
	 */
	protected EObject create(EClass eClass) {
		return EcoreUtil.create(eClass);
	}

	/**
	 * Retrieves the cache adapter for this '<em><b>Project Description</b></em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return The cache adapter for this '<em><b>Project Description</b></em>'.
	 * @generated
	 */
	protected ProjectRulesCache getCacheAdapter() {
		return ProjectRulesCache.getCacheAdapter(this);
	}

} // ProjectDescriptionImpl
