/*****************************************************************************
 * Copyright (c) 2007, 2009, 2013, 2021 Borland Software Corporation, CEA LIST, Artal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Dmitry Stadnik (Borland) - initial API and implementation
 * Michael Golubev (Montages) - #386838 - migrate to Xtend2
 * Ansgar Radermacher (CEA LIST) - added support for EMF validation
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : 1.4 Merge papyrus extension templates into codegen.xtend
 *****************************************************************************/
package xpt.providers

import com.google.inject.Inject
import xpt.Common
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram
import xpt.editor.VisualIDRegistry
import xpt.editor.ValidationMarker
import plugin.Activator
import xpt.editor.Editor

/**
 * FIXME: [MG] monolithic template with most of the code "same-generated".
 * Unfortunately, a lot of the logic is based around «IF editorGen.application == null» and we don't have a good ways to deal with taht in GMFT-runtimw
 */
@com.google.inject.Singleton class ValidationDecoratorProvider {
	@Inject extension Common;
	
	@Inject Activator xptActivator;
	@Inject Editor xptEditor;
	@Inject VisualIDRegistry xptVisualIDRegistry;
	@Inject ValidationMarker xptValidationMarker; 
	
	def className(GenDiagram it) '''«it.validationDecoratorProviderClassName»'''

	def packageName(GenDiagram it) '''«it.providersPackageName»'''

	def qualifiedClassName(GenDiagram it) '''«packageName(it)».«className(it)»'''
	
	def fullPath(GenDiagram it) '''«qualifiedClassName(it)»'''
def ValidationDecoratorProvider(GenDiagram it) '''
«copyright(editorGen)»
package «packageName(it)»;

«generatedClassComment»
public class «className(it)»
		extends org.eclipse.papyrus.uml.diagram.common.providers.ValidationDecoratorProvider
		implements org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider {

	«generatedMemberComment»
	public void createDecorators(org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget decoratorTarget) {
		org.eclipse.gef.EditPart editPart = (org.eclipse.gef.EditPart) decoratorTarget.getAdapter(org.eclipse.gef.EditPart.class);
		if (editPart instanceof org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart ||
				editPart instanceof org.eclipse.gef.editparts.AbstractConnectionEditPart) {
			Object model = editPart.getModel();
			if ((model instanceof org.eclipse.gmf.runtime.notation.View)) {
				org.eclipse.gmf.runtime.notation.View view = (org.eclipse.gmf.runtime.notation.View) model;
				if (!(view instanceof org.eclipse.gmf.runtime.notation.Edge) && !view.isSetElement()) {
					return;
				}
			}
			org.eclipse.gef.EditDomain ed = editPart.getViewer().getEditDomain();
			if (!(ed instanceof org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain)) {
				return;
			}
			if (((org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain) ed).getEditorPart() instanceof
					«xptEditor.qualifiedClassName(editorGen.editor)») {
				decoratorTarget.installDecorator(KEY, new StatusDecorator(decoratorTarget));
			}
		}
	}

	«generatedMemberComment»
	public boolean provides(org.eclipse.gmf.runtime.common.core.service.IOperation operation) {
		if (!(operation instanceof org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation)) {
			return false;
		}
		org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget decoratorTarget =
				((org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation) operation).getDecoratorTarget();
		org.eclipse.gmf.runtime.notation.View view = (org.eclipse.gmf.runtime.notation.View) decoratorTarget.getAdapter(
				org.eclipse.gmf.runtime.notation.View.class);
		return view != null && «VisualIDRegistry::modelID(it)».equals(«xptVisualIDRegistry.getModelIDMethodCall(it)»(view));
	}


	«additions(it)»
}
'''

	def additions(GenDiagram it) ''''''

}
