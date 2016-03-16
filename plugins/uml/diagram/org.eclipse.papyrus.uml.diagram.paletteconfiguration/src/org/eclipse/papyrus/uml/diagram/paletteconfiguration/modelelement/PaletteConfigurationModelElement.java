/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Mickael ADAM (ALL4TEC) mickael.adam@all4tec.net - Initial API and Implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.paletteconfiguration.modelelement;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.papyrus.infra.properties.ui.modelelement.EMFModelElement;
import org.eclipse.papyrus.infra.widgets.creation.ReferenceValueFactory;
import org.eclipse.papyrus.infra.widgets.creation.StringEditionFactory;
import org.eclipse.papyrus.uml.diagram.paletteconfiguration.PaletteConfiguration;
import org.eclipse.papyrus.uml.diagram.paletteconfiguration.provider.RequiredProfilesLabelProvider;

/**
 * {@link EMFModelElement} for {@link StereotypeToApply}. Add required profile needed when set stereotype qualify name.
 */
public class PaletteConfigurationModelElement extends EMFModelElement {

	/**
	 * Constructor.
	 */
	public PaletteConfigurationModelElement(final PaletteConfiguration sourceElement, final EditingDomain domain) {
		super(sourceElement, domain);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.infra.properties.ui.modelelement.EMFModelElement#getLabelProvider(java.lang.String)
	 */
	@Override
	public ILabelProvider getLabelProvider(final String propertyPath) {
		ILabelProvider labelProvider = null;
		if ("requiredProfiles".equals(propertyPath)) {//$NON-NLS-1$
			labelProvider = new RequiredProfilesLabelProvider();
		} else {
			labelProvider = super.getLabelProvider(propertyPath);
		}
		return labelProvider;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.infra.properties.ui.modelelement.EMFModelElement#getDirectCreation(java.lang.String)
	 */
	@Override
	public boolean getDirectCreation(final String propertyPath) {
		boolean directCreation;
		if ("requiredProfiles".equals(propertyPath)) {//$NON-NLS-1$
			directCreation = true;
		} else {
			directCreation = super.getDirectCreation(propertyPath);
		}
		return directCreation;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.infra.properties.ui.modelelement.EMFModelElement#getValueFactory(java.lang.String)
	 */
	@Override
	public ReferenceValueFactory getValueFactory(final String propertyPath) {
		ReferenceValueFactory valueFactory = null;
		if ("requiredProfiles".equals(propertyPath)) {//$NON-NLS-1$
			// Set the string edition factory for required profiles
			valueFactory = new StringEditionFactory();
		} else {
			valueFactory = super.getValueFactory(propertyPath);
		}

		return valueFactory;
	}

}
