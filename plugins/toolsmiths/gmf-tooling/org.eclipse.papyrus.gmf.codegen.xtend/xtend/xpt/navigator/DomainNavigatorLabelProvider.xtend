/*******************************************************************************
 * Copyright (c) 2007, 2020 Borland Software Corporation, CEA LIST, Artal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/ 
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: 
 *    Alexander Shatalin (Borland) - initial API and implementation
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 *    Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Bug 569174
 *****************************************************************************/
package xpt.navigator

import com.google.inject.Inject
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenNavigator
import xpt.Common
import plugin.Activator

@com.google.inject.Singleton class DomainNavigatorLabelProvider {
	@Inject extension Common;

	@Inject	Activator xptActivator
	@Inject NavigatorContentProvider xptNavigatorContentProvider;
	@Inject DomainNavigatorItem xptDomainNavigatorItem;

	def className(GenNavigator it) '''«it.domainLabelProviderClassName»'''

	def packageName(GenNavigator it) '''«it.packageName»'''

	def qualifiedClassName(GenNavigator it) '''«packageName(it)».«className(it)»'''
	
	def fullPath(GenNavigator it) '''«qualifiedClassName(it)»'''
	
	def implementsList(GenNavigator it) '''implements org.eclipse.ui.navigator.ICommonLabelProvider'''

	def DomainNavigatorLabelProvider(GenNavigator it) '''
		«copyright(editorGen)»
		package «packageName(it)»;
		
		«generatedClassComment()»
		public class «className(it)» «implementsList(it)» {
			
			«attributes(it)»
			
			«iCommonLabelProvider(it)»
			
			«iLabelProvider(it)»
			
			«iBaseLabelProvider(it)»
			
			«xptNavigatorContentProvider.iMementoAware(it)»
			
			«iDescriptionProvider(it)»
			
			   «additions(it)»
		}
	'''

	def attributes(GenNavigator it) '''
		«generatedMemberComment()»
		private org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider myAdapterFactoryLabelProvider = new org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider(«
			xptActivator.qualifiedClassName(editorGen.plugin)».getInstance().getItemProvidersAdapterFactory());
	'''

	def iCommonLabelProvider(GenNavigator it) '''
		«generatedMemberComment()»
		public void init(org.eclipse.ui.navigator.ICommonContentExtensionSite aConfig) {
		}
	'''

	def iLabelProvider(GenNavigator it) '''
		«getImage(it)»
		
		«getText(it)»
	'''

	def iBaseLabelProvider(GenNavigator it) '''
		«addListener(it)»
		
		«dispose(it)»
		
		«isLabelProperty(it)»
		
		«removeListener(it)»
	'''

	def iDescriptionProvider(GenNavigator it) '''
		«generatedMemberComment()»
		public String getDescription(Object anElement) {
			return null;
		}
	'''

	def addListener(GenNavigator it) '''
		«generatedMemberComment()»
		public void addListener(org.eclipse.jface.viewers.ILabelProviderListener listener) {
			myAdapterFactoryLabelProvider.addListener(listener);
		}
	'''

	def dispose(GenNavigator it) '''
		«generatedMemberComment()»
		public void dispose() {
			myAdapterFactoryLabelProvider.dispose();
		}
	'''

	def isLabelProperty(GenNavigator it) '''
		«generatedMemberComment()»
		public boolean isLabelProperty(Object element, String property) {
			return myAdapterFactoryLabelProvider.isLabelProperty(element, property);
		}
	'''

	def removeListener(GenNavigator it) '''
		«generatedMemberComment()»
		public void removeListener(org.eclipse.jface.viewers.ILabelProviderListener listener) {
			myAdapterFactoryLabelProvider.removeListener(listener);
		}
	'''

	def getImage(GenNavigator it) '''
		«generatedMemberComment()»
		public org.eclipse.swt.graphics.Image getImage(Object element) {
			if (element instanceof «xptDomainNavigatorItem.qualifiedClassName(it)») {
				return myAdapterFactoryLabelProvider.getImage(«getEObject(it)»);
			}
			return null;
		}
	'''

	def getEObject(GenNavigator it) '''((«xptDomainNavigatorItem.qualifiedClassName(it)») element).getEObject()'''

	def getText(GenNavigator it) '''
		«generatedMemberComment()»
		public String getText(Object element) {
			if (element instanceof «xptDomainNavigatorItem.qualifiedClassName(it)») {
				return myAdapterFactoryLabelProvider.getText(«getEObject(it)»);
			}
			return null;
		}
	'''

	def additions(GenNavigator it) ''''''

}
