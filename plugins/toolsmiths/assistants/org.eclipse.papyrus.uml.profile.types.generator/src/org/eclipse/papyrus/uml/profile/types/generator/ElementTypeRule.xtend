/*****************************************************************************
 * Copyright (c) 2014, 2015, 2018, 2020 Christian W. Damus and others.
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
 * Ansgar Radermacher - Bug 526155, enable re-generation from profile: copy existing advices
 * Ansgar Radermacher - Bug 526156, reference semantic base element type
 * Camille Letavernier - Bug 569354: remove StereotypeAdvice; use StereotypeMatcherAdvice instead
 * 
 *****************************************************************************/
package org.eclipse.papyrus.uml.profile.types.generator

import javax.inject.Inject
import javax.inject.Singleton
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.papyrus.infra.types.ElementTypeConfiguration
import org.eclipse.papyrus.infra.types.ElementTypesConfigurationsFactory
import org.eclipse.papyrus.infra.types.SpecializationTypeConfiguration
import org.eclipse.papyrus.uml.types.core.matchers.stereotype.StereotypeApplicationMatcherFactory
import org.eclipse.uml2.uml.Stereotype

import static extension org.eclipse.emf.common.util.URI.decode
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry
import org.eclipse.papyrus.infra.types.core.impl.ConfiguredHintedSpecializationElementType

/**
 * Transformation rule for generating a {@link SpecializationTypeConfiguration} from a UML metaclass {@link Extension}.
 */
@Singleton
class ElementTypeRule {
    static extension ElementTypesConfigurationsFactory elementtypesconfigurationsFactory = ElementTypesConfigurationsFactory.
        eINSTANCE
    static extension StereotypeApplicationMatcherFactory stereotypeApplicationMatcherConfigurationFactory = StereotypeApplicationMatcherFactory.
        eINSTANCE

    @Inject extension UMLElementTypes
    @Inject extension Identifiers

    def create createSpecializationTypeConfiguration toElementType(ImpliedExtension umlExtension,
		ElementTypeConfiguration supertype) {

		// Basics
		identifier = umlExtension.toElementTypeID(supertype)
		if (hasSemanticSupertype(supertype)) {
			// try to lookup base type in the registry first
			val baseTypeId = umlExtension.toSemanticElementTypeID(umlExtension.metaclass.elementTypeConfiguration);
			val baseTypeFromRegistry = ElementTypeRegistry.instance.getType(baseTypeId)
			if (baseTypeFromRegistry instanceof ConfiguredHintedSpecializationElementType) {
				// base type found, reference it instead of creating a new one
				val baseType = (baseTypeFromRegistry as ConfiguredHintedSpecializationElementType).configuration
				specializedTypes.add(baseType)
			}
			else {
				// Add the base semantic type in addition to the parent visual type
				val baseType = createSpecializationTypeConfiguration()
				baseType.identifier = umlExtension.toElementTypeID(umlExtension.metaclass.elementTypeConfiguration)
				baseType.specializedTypes.add(umlExtension.metaclass.elementTypeConfiguration)
				baseType.hint = umlExtension.metaclass.elementTypeConfiguration.hint
				baseType.name = umlExtension.toElementTypeName(umlExtension.metaclass.elementTypeConfiguration)
				// Icon
				var icon = umlExtension.stereotype.iconEntry
				baseType.iconEntry = if(icon != null) icon else umlExtension.metaclass.iconEntry
				val addedBaseType = ConfigurationSetRule.addElementType(baseType)
				specializedTypes.add(addedBaseType)
			}
		}
		specializedTypes.add(supertype)
		hint = supertype.hint
		name = umlExtension.toElementTypeName(supertype)

		// copy eventually already existing advices from registry
		val elemTypeFromRegistry = ElementTypeRegistry.instance.getType(identifier)
		if (elemTypeFromRegistry instanceof ConfiguredHintedSpecializationElementType) {
			// existing element type found, copy helper advice, if any
			val elemTypeConfigFromRegistry = elemTypeFromRegistry.configuration
			if (elemTypeConfigFromRegistry instanceof SpecializationTypeConfiguration) {
				val helperAdviceFromRegistry = (elemTypeConfigFromRegistry as SpecializationTypeConfiguration).editHelperAdviceConfiguration
				if (helperAdviceFromRegistry != null) {
					editHelperAdviceConfiguration = helperAdviceFromRegistry
				}
			}
		}

		// Icon
		var icon = umlExtension.stereotype.iconEntry
		iconEntry = if(icon != null) icon else umlExtension.metaclass.iconEntry

		// Add stereotype matcher, if it isn't inherited from a semantic supertype
		if (!hasSemanticSupertype(supertype)) {
			matcherConfiguration = umlExtension.toMatcherConfiguration(supertype)
		}
	}

    private def create createStereotypeMatcherAdviceConfiguration toMatcherConfiguration(ImpliedExtension umlExtension,
        ElementTypeConfiguration supertype) {
            
        val umlStereotype = umlExtension.stereotype
        identifier = umlStereotype.name.toFirstLower.qualified + supertype.hintSuffix;
        stereotypesQualifiedNames.add(umlStereotype.qualifiedName)
        description = "Apply Stereotype "+umlStereotype.name
    }

    private def getIconEntry(Stereotype stereotype) {
        val image = stereotype.icons.findFirst[!location.nullOrEmpty]
        if (image != null) {
            val uri = URI.createURI(image.location, true)

            if (uri != null) {
                createIconEntry => [
                    if (uri.platform) {

                        // Explicit platform-scheme URI
                        bundleId = uri.segment(1)
                        iconPath = "/" + uri.segmentsList.drop(2).join("/", [decode])
                    } else if (uri.relative) {

                        // Bundle-relative path.  Infer the bundle ID from the containing project
                        bundleId = stereotype.containingProject.name
                        iconPath = "/" + uri.toString.decode
                    } else {

                        // Absolute URI: use as is; don't decode it
                        iconPath = uri.toString
                    }
                ]
            }
        }
    }

    private def containingProject(EObject object) {
        ResourcesPlugin.workspace.root.getProject(object.eResource.URI.segment(2))
    }
}
