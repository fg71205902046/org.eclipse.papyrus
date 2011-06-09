/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.properties.customization.modelelement;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.papyrus.properties.Activator;
import org.eclipse.papyrus.properties.contexts.ConfigProperty;
import org.eclipse.papyrus.properties.contexts.ContextsFactory;
import org.eclipse.papyrus.properties.contexts.ContextsPackage;
import org.eclipse.papyrus.properties.contexts.DataContextElement;
import org.eclipse.papyrus.properties.modelelement.ModelElement;
import org.eclipse.papyrus.properties.modelelement.ModelElementFactory;
import org.eclipse.papyrus.properties.util.EMFHelper;

/**
 * A ModelElementFactory for handling {@link ConfigProperty} properties
 * 
 * @author Camille Letavernier
 */
public class GenericPropertyModelElementFactory implements ModelElementFactory {

	//Source : Group
	//context : Custom:Attribute:Group
	public ModelElement createFromSource(Object sourceElement, DataContextElement context) {

		EObject source = EMFHelper.getEObject(sourceElement);
		if(source == null) {
			Activator.log.warn("Unable to resolve the source element to an EObject"); //$NON-NLS-1$
			return null;
		}

		EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(source);

		EClass valueProperty = ContextsPackage.eINSTANCE.getValueProperty();
		EClass referenceProperty = ContextsPackage.eINSTANCE.getReferenceProperty();
		EStructuralFeature attributes = ContextsPackage.eINSTANCE.getSimpleConstraint_Properties();

		return new GenericAttributeModelElement(source, domain, attributes, ContextsFactory.eINSTANCE, valueProperty, referenceProperty);
	}

	//	public List<ModelElement> createFromDataSource(ModelElement currentElement, DataSource source, String propertyPath, DataContextElement context) {
	//		throw new UnsupportedOperationException();
	//	}

}
