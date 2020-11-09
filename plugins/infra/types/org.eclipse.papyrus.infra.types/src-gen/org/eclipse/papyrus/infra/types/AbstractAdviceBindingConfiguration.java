/**
 * Copyright (c) 2014 CEA LIST.
 * 
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *  CEA LIST - Initial API and implementation
 */
package org.eclipse.papyrus.infra.types;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Advice Binding Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration#getContainerConfiguration <em>Container Configuration</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration#getMatcherConfiguration <em>Matcher Configuration</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration#getInheritance <em>Inheritance</em>}</li>
 *   <li>{@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration#isApplyToAllTypes <em>Apply To All Types</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage#getAbstractAdviceBindingConfiguration()
 * @model abstract="true"
 * @generated
 */
public interface AbstractAdviceBindingConfiguration extends AdviceConfiguration, IdentifiedConfiguration {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(ElementTypeConfiguration)
	 * @see org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage#getAbstractAdviceBindingConfiguration_Target()
	 * @model
	 * @generated
	 */
	ElementTypeConfiguration getTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(ElementTypeConfiguration value);

	/**
	 * Returns the value of the '<em><b>Container Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container Configuration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container Configuration</em>' containment reference.
	 * @see #setContainerConfiguration(ContainerConfiguration)
	 * @see org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage#getAbstractAdviceBindingConfiguration_ContainerConfiguration()
	 * @model containment="true"
	 * @generated
	 */
	ContainerConfiguration getContainerConfiguration();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration#getContainerConfiguration <em>Container Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container Configuration</em>' containment reference.
	 * @see #getContainerConfiguration()
	 * @generated
	 */
	void setContainerConfiguration(ContainerConfiguration value);

	/**
	 * Returns the value of the '<em><b>Matcher Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Matcher Configuration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Matcher Configuration</em>' containment reference.
	 * @see #setMatcherConfiguration(AbstractMatcherConfiguration)
	 * @see org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage#getAbstractAdviceBindingConfiguration_MatcherConfiguration()
	 * @model containment="true"
	 * @generated
	 */
	AbstractMatcherConfiguration getMatcherConfiguration();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration#getMatcherConfiguration <em>Matcher Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Matcher Configuration</em>' containment reference.
	 * @see #getMatcherConfiguration()
	 * @generated
	 */
	void setMatcherConfiguration(AbstractMatcherConfiguration value);

	/**
	 * Returns the value of the '<em><b>Inheritance</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.papyrus.infra.types.InheritanceKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inheritance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inheritance</em>' attribute.
	 * @see org.eclipse.papyrus.infra.types.InheritanceKind
	 * @see #setInheritance(InheritanceKind)
	 * @see org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage#getAbstractAdviceBindingConfiguration_Inheritance()
	 * @model required="true"
	 * @generated
	 */
	InheritanceKind getInheritance();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration#getInheritance <em>Inheritance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inheritance</em>' attribute.
	 * @see org.eclipse.papyrus.infra.types.InheritanceKind
	 * @see #getInheritance()
	 * @generated
	 */
	void setInheritance(InheritanceKind value);

	/**
	 * Returns the value of the '<em><b>Apply To All Types</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Apply To All Types</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Apply To All Types</em>' attribute.
	 * @see #setApplyToAllTypes(boolean)
	 * @see org.eclipse.papyrus.infra.types.ElementTypesConfigurationsPackage#getAbstractAdviceBindingConfiguration_ApplyToAllTypes()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	boolean isApplyToAllTypes();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration#isApplyToAllTypes <em>Apply To All Types</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Apply To All Types</em>' attribute.
	 * @see #isApplyToAllTypes()
	 * @generated
	 */
	void setApplyToAllTypes(boolean value);

} // AbstractAdviceBindingConfiguration
