/**
 * Copyright (c) 2015 Christian W. Damus and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 */
package org.eclipse.papyrus.aof.sync.emf.syncmapping;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.aof.sync.IMappingInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mapping Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.aof.sync.emf.syncmapping.MappingModel#getInstances <em>Instance</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.aof.sync.emf.syncmapping.SyncMappingPackage#getMappingModel()
 * @model
 * @generated
 */
public interface MappingModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Instance</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.aof.sync.IMappingInstance}&lt;?, ?>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapping</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance</em>' containment reference list.
	 * @see org.eclipse.papyrus.aof.sync.emf.syncmapping.SyncMappingPackage#getMappingModel_Instance()
	 * @model type="org.eclipse.papyrus.aof.sync.emf.syncmapping.IMappingInstance<?, ?>" containment="true" ordered="false"
	 * @generated
	 */
	EList<IMappingInstance<?, ?>> getInstances();

} // MappingModel
