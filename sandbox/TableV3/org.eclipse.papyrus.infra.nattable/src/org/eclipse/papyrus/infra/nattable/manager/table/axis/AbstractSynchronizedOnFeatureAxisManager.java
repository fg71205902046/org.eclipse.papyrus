/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.nattable.manager.table.axis;

import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.papyrus.infra.nattable.manager.table.INattableModelManager;
import org.eclipse.papyrus.infra.nattable.model.nattable.Table;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisprovider.AbstractAxisProvider;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisprovider.EMFFeatureValueAxisProvider;

/**
 * 
 * @author Vincent Lorenzo
 * 
 */
public abstract class AbstractSynchronizedOnFeatureAxisManager extends AbstractAxisManager {

	/**
	 * the feature listener
	 */
	protected Adapter featureListener;

	@Override
	public void init(final INattableModelManager manager, final String managerId, final Table table, final AbstractAxisProvider provider, boolean mustRefreshOnAxisChanges) {
		super.init(manager, managerId, table, provider, mustRefreshOnAxisChanges);
		verifyValues();
	}

	/**
	 * this method verify that the fields and the parameters are correct for this synchronized table
	 * 
	 */
	protected void verifyValues() {
		assert getRepresentedContentProvider() instanceof EMFFeatureValueAxisProvider;
		verifyCoupleContextFeature();
	}

	/**
	 * verify that the context contains the feature
	 * 
	 */
	protected void verifyCoupleContextFeature() {
		final EReference ref = ((EMFFeatureValueAxisProvider)getRepresentedContentProvider()).getListenFeature();
		assert ref.isMany();
		final EObject context = getTable().getContext();
		assert context.eClass().getEAllReferences().contains(ref);
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.nattable.manager.table.axis.AbstractAxisManager#canDropAxisElement(java.util.Collection)
	 * 
	 * @param objectsToAdd
	 * @return
	 */
	@Override
	public boolean canDropAxisElement(Collection<Object> objectsToAdd) {
		return false;
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.nattable.manager.table.axis.AbstractAxisManager#canInsertAxis(java.util.Collection, int)
	 * 
	 * @param objectsToAdd
	 * @param index
	 * @return
	 */
	@Override
	public boolean canInsertAxis(Collection<Object> objectsToAdd, int index) {
		return false;
	}
}
