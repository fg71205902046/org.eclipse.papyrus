/*******************************************************************************
 * Copyright (c) 2006, 2020 Borland Software Corporation, CEA LIST, Artal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *    Dmitry Stadnik (Borland) - initial API and implementation
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 */
package xpt.providers

import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase
import org.eclipse.papyrus.gmf.codegen.xtend.annotations.MetaDef
import com.google.inject.Inject
import xpt.diagram.Utils_qvto
import xpt.Common
import xpt.Common_qvto
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram
import xpt.CodeStyle
import metamodel.MetaModel
import org.eclipse.emf.codegen.ecore.genmodel.GenClass
import org.eclipse.papyrus.gmf.codegen.gmfgen.ModelFacet
import org.eclipse.papyrus.gmf.codegen.gmfgen.TypeModelFacet
import org.eclipse.papyrus.gmf.codegen.gmfgen.FeatureLinkModelFacet
import plugin.Activator
import xpt.editor.VisualIDRegistry

@com.google.inject.Singleton class ElementTypes {

	@Inject extension Common;
	@Inject extension Common_qvto;
	@Inject extension Utils_qvto;
	
	@Inject Activator xptActivator;
	@Inject CodeStyle xptCodeStyle;
	@Inject MetaModel xptMetaModel;
	@Inject VisualIDRegistry xptVisualIDRegistry;

	@MetaDef def accessElementType(GenCommonBase it) '''«it.diagram.elementTypesQualifiedClassName».«it.uniqueIdentifier»'''

	def className(GenDiagram it) '''«it.elementTypesClassName»'''

	def packageName(GenDiagram it) '''«it.providersPackageName»'''

	def qualifiedClassName(GenDiagram it) '''«packageName(it)».«className(it)»'''

	def fullPath(GenDiagram it) '''«qualifiedClassName(it)»'''

	def ElementTypes(GenDiagram it) '''
		«copyright(editorGen)»
		package «packageName(it)»;
		
		«generatedClassComment»
		public class «className(it)» {
		
			«constructor(it)»
			
			«attributes(it)»
			
			«FOR e : it.getAllTypedElements()»
				«elementTypeField(e)»
			«ENDFOR»
			
			«getNamedElementImageDescriptor(it)»
		
			«getNamedElementImage(it)»
		
			«getAdaptableImageDescriptor(it)»
		
			«getAdaptableImage(it)»
		
			«getElement(it)»
		
			«getElementType(it)»
		
			«isKnownElementType(it)»
			
			«getElementTypeByVisualID(it)»
			
			«typedInstance(it)»
		
			«additions(it)»
		}
	'''

	def constructor(GenDiagram it) '''
		«generatedMemberComment»
		private «className(it)»() {
		}
	'''

	def attributes(GenDiagram it) '''
		«generatedMemberComment»
		private static java.util.Map<org.eclipse.gmf.runtime.emf.type.core.IElementType, org.eclipse.emf.ecore.ENamedElement> elements;
		
		«generatedMemberComment»
		private static org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages elementTypeImages = new org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages(« //
		xptActivator.qualifiedClassName(editorGen.plugin)».getInstance().getItemProvidersAdapterFactory());
		
		«generatedMemberComment»
		private static java.util.Set<org.eclipse.gmf.runtime.emf.type.core.IElementType> KNOWN_ELEMENT_TYPES;
	'''

	/**
	 * Since 3.1, GMFT delegates ImageRegistry-related code to non-generated DiagramElementTypeImages, so methods below are not generated anymore.  
	 * If you still need one, you always can get it from 'elementTypeImages.getXXX()' call. 
	 * The only exception is 'DEFINE loadProvidedImageDescriptor FOR gmfgen::GenDiagram', which is not compatible with the delegation and removed completely.
	 * 
	 * def getImageRegistry(GenDiagram it) ''''''
	 * def getImageRegistryKey(GenDiagram it) ''''''
	 * def getProvidedImageDescriptor(GenDiagram it) ''''''
	 */
	def getNamedElementImageDescriptor(GenDiagram it) '''
		«generatedMemberComment»
		public static org.eclipse.jface.resource.ImageDescriptor getImageDescriptor(org.eclipse.emf.ecore.ENamedElement element) {
			return elementTypeImages.getImageDescriptor(element);
		}
	'''

	def getNamedElementImage(GenDiagram it) '''
		«generatedMemberComment»
		public static org.eclipse.swt.graphics.Image getImage(org.eclipse.emf.ecore.ENamedElement element) {
			return elementTypeImages.getImage(element);
		}
	'''

	def getAdaptableImageDescriptor(GenDiagram it) '''
		«generatedMemberComment»
		public static org.eclipse.jface.resource.ImageDescriptor getImageDescriptor(org.eclipse.core.runtime.IAdaptable hint) {
			return getImageDescriptor(getElement(hint));
		}
	'''

	def getAdaptableImage(GenDiagram it) '''
		«generatedMemberComment»
		public static org.eclipse.swt.graphics.Image getImage(org.eclipse.core.runtime.IAdaptable hint) {
			return getImage(getElement(hint));
		}
	'''

	def getElement(GenDiagram it) '''
		«generatedMemberComment('Returns \'type\' of the ecore object associated with the hint.\n')»
		public static org.eclipse.emf.ecore.ENamedElement getElement(org.eclipse.core.runtime.IAdaptable hint) {
			Object type = hint.getAdapter(org.eclipse.gmf.runtime.emf.type.core.IElementType.class);
			if (elements == null) {
				elements = new java.util.IdentityHashMap<org.eclipse.gmf.runtime.emf.type.core.IElementType, org.eclipse.emf.ecore.ENamedElement>();
				«IF domainDiagramElement != null»«bindUniqueIdentifierToNamedElement(domainDiagramElement, getUniqueIdentifier())»«ENDIF»
				«FOR node : getAllNodes()»
					«IF node.modelFacet != null»«bindUniqueIdentifierToNamedElement(node.modelFacet, node.getUniqueIdentifier())»«ENDIF»
				«ENDFOR»
				«FOR link : it.links»
					«IF link.modelFacet != null»«bindUniqueIdentifierToNamedElement(link.modelFacet, link.getUniqueIdentifier())»«ENDIF»
				«ENDFOR»
			}
			return (org.eclipse.emf.ecore.ENamedElement) elements.get(type);
		}
	'''

	def dispatch CharSequence bindUniqueIdentifierToNamedElement(GenClass it, String id) '''
		«extraLineBreak»
		elements.put(«id», «xptMetaModel.MetaClass(it)»);
	'''

	def dispatch CharSequence bindUniqueIdentifierToNamedElement(ModelFacet it, String id) '''
		«ERROR('Unsupported model facet: ' + it + " for: " + id)»
	'''

	def dispatch CharSequence bindUniqueIdentifierToNamedElement(TypeModelFacet it, String id) '''
		«extraLineBreak»
		«bindUniqueIdentifierToNamedElement(it.metaClass, id)»
	'''

	def dispatch CharSequence bindUniqueIdentifierToNamedElement(FeatureLinkModelFacet it, String id) '''
		«extraLineBreak»
		elements.put(«id», «xptMetaModel.MetaFeature(metaFeature)»);
	'''

	def elementTypeField(GenCommonBase it) '''
		«IF null != elementType»
			«generatedMemberComment»
			public static final org.eclipse.gmf.runtime.emf.type.core.IElementType «getUniqueIdentifier()» = getElementType("«elementType.
			uniqueIdentifier»"); «nonNLS(1)»
		«ENDIF»
	'''

	def getElementType(GenDiagram it) '''
		«generatedMemberComment»
		private static org.eclipse.gmf.runtime.emf.type.core.IElementType getElementType(String id) {
			return org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry.getInstance().getType(id);
		}
	'''

	def isKnownElementType(GenDiagram it) '''
		«generatedMemberComment»
		public static boolean isKnownElementType(org.eclipse.gmf.runtime.emf.type.core.IElementType elementType) {
			if (KNOWN_ELEMENT_TYPES == null) {
				KNOWN_ELEMENT_TYPES = new java.util.HashSet<org.eclipse.gmf.runtime.emf.type.core.IElementType>();
				«FOR e : it.getAllTypedElements()»
					«addKnownElementType(e)»
				«ENDFOR»
			}
			return KNOWN_ELEMENT_TYPES.contains(elementType);
		}
	'''

	def addKnownElementType(GenCommonBase it) '''
		«IF null != elementType»
			KNOWN_ELEMENT_TYPES.add(«getUniqueIdentifier()»);
		«ENDIF»
	'''

	def getElementTypeByVisualID(GenDiagram it) '''
		«generatedMemberComment»
		public static org.eclipse.gmf.runtime.emf.type.core.IElementType getElementType(int visualID) {
			switch (visualID) {
				«FOR e : it.getAllTypedElements().filter[el|el.elementType != null]»
					«caseElementType(e)»
				«ENDFOR»
			}
			return null;
		}
	'''

	def caseElementType(GenCommonBase it) '''
		«xptVisualIDRegistry.caseVisualID(it)»
			return «getUniqueIdentifier()»;
	'''

	@MetaDef def typedInstanceName(GenDiagram it) '''TYPED_INSTANCE'''

	@MetaDef def typedInstanceCall(GenDiagram it) '''«qualifiedClassName(it)».«typedInstanceName(it)»'''

	def typedInstance(GenDiagram it) '''
		«generatedClassComment»
		public static final org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes TYPED_INSTANCE 
			= new org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes(elementTypeImages) {
			
			«generatedMemberComment»
			«xptCodeStyle.overrideC(it)»
			public boolean isKnownElementType(org.eclipse.gmf.runtime.emf.type.core.IElementType elementType) {
				return «qualifiedClassName(it)».isKnownElementType(elementType);
			}
			
			«generatedMemberComment»
			«xptCodeStyle.overrideC(it)»
			public org.eclipse.gmf.runtime.emf.type.core.IElementType getElementTypeForVisualId(int visualID) {
				return «qualifiedClassName(it)».getElementType(visualID);
			}
			
			«generatedMemberComment»
			«xptCodeStyle.overrideC(it)»
			public org.eclipse.emf.ecore.ENamedElement getDefiningNamedElement(org.eclipse.core.runtime.IAdaptable elementTypeAdapter) {
				return «qualifiedClassName(it)».getElement(elementTypeAdapter);
			}
		}; 
	'''

	def additions(GenDiagram it) ''''''

}
