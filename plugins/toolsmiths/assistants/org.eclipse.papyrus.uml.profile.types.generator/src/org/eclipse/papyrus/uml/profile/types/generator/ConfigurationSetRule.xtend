/*****************************************************************************
 * Copyright (c) 2014, 2015, 2017, 2020 Christian W. Damus and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * Christian W. Damus - Initial API and implementation
 * Benoit Maggi       - Bug 474408: order by identifier the generated file
 * Ansgar Radermacher - Bug 526155: set element type name from profile
 * Camille Letavernier - Bug 569354: remove StereotypeAdvice; use StereotypeMatcherAdvice instead
 * 
 *****************************************************************************/
package org.eclipse.papyrus.uml.profile.types.generator

import java.util.List
import javax.inject.Inject
import javax.inject.Singleton
import org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration
import org.eclipse.papyrus.infra.types.ElementTypeConfiguration
import org.eclipse.papyrus.infra.types.ElementTypeSetConfiguration
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsFactory
import org.eclipse.uml2.uml.Profile
import org.eclipse.uml2.uml.UMLPackage

/**
 * Transformation rule for generating an {@link ElementTypeSetConfiguration} from a UML {@link Profile}.
 */
@Singleton
class ConfigurationSetRule {
	static extension ElementTypesConfigurationsFactory elementtypesconfigurationsFactory = ElementTypesConfigurationsFactory.
		eINSTANCE

	@Inject extension Identifiers
	@Inject extension UML
	@Inject extension UMLElementTypes
	@Inject extension ElementTypeRule

	static var List<ElementTypeConfiguration> elementTypeConfigurationList
	var List<AbstractAdviceBindingConfiguration> adviceBindingConfigurationList

	static def addElementType(ElementTypeConfiguration elementtype) {
		var found = elementTypeConfigurationList.findFirst[el|el.identifier.equals(elementtype.identifier)]
		if (found == null) {
			elementTypeConfigurationList.add(elementtype);
			return elementtype
		} else {
			return found
		}

	}

	def create createElementTypeSetConfiguration toConfigurationSet(Profile umlProfile) {

		elementTypeConfigurationList = newArrayList()
		adviceBindingConfigurationList = newArrayList()

		// Initialize the generation of IDs
		umlProfile.setIdentifierBase

		identifier = "elementTypes".qualified
		metamodelNsURI = baseUMLElementTypeSet?.metamodelNsURI ?: UMLPackage.eNS_URI;

		for (ext : umlProfile.allExtensions) {
			for (element : ext.metaclass.diagramSpecificElementTypes) {
				val elementtype = ext.toElementType(element)
				elementTypeConfigurationList.add(elementtype);
			}
		}

		adviceBindingsConfigurations.addAll(adviceBindingConfigurationList.sortBy[identifier])
		elementTypeConfigurations.addAll(elementTypeConfigurationList.sortBy[identifier])

		// set name (otherwise, the element type set remains invalid)
		if (useDiPostfix()) {
			name = umlProfile.name + " DI"
		}
		else {
			name = umlProfile.name
		}			
	}
}
