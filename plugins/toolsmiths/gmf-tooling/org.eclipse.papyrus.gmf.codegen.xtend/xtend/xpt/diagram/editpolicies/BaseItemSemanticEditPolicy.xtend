/*****************************************************************************
 * Copyright (c) 2007, 2014, 2021 Borland Software Corporation, Christian W. Damus, CEA LIST, Artal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * Alexander Shatalin (Borland) - initial API and implementation
 * Michael Golubev (Montages) - #386838 - migrate to Xtend2
 * Christian W. Damus - bug 451230
 * Etienne Allogo (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : 1.4 Merge papyrus extension templates into codegen.xtend
 *****************************************************************************/
package xpt.diagram.editpolicies

import com.google.inject.Inject
import org.eclipse.papyrus.gmf.codegen.gmfgen.FeatureLinkModelFacet
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLink
import org.eclipse.papyrus.gmf.codegen.gmfgen.TypeLinkModelFacet
import xpt.Common
import xpt.Common_qvto
import xpt.editor.VisualIDRegistry
import xpt.diagram.edithelpers.BaseEditHelper
import plugin.Activator
import org.eclipse.papyrus.gmf.codegen.gmfgen.LinkModelFacet
import metamodel.MetaModel
import xpt.OclMigrationProblems_qvto
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature
import org.eclipse.emf.codegen.ecore.genmodel.GenClass
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenExpressionProviderBase
import org.eclipse.papyrus.gmf.codegen.gmfgen.ValueExpression
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenExpressionInterpreter
import xpt.expressions.getExpression
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenJavaExpressionProvider
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase
import xpt.providers.ElementTypes
import org.eclipse.papyrus.gmf.codegen.xtend.annotations.MetaDef
import xpt.QualifiedClassNameProvider

@com.google.inject.Singleton class BaseItemSemanticEditPolicy {
	@Inject extension Common;
	@Inject extension VisualIDRegistry
	
	@Inject extension Common_qvto;
	@Inject extension xpt.diagram.Utils_qvto;
	@Inject extension OclMigrationProblems_qvto;
	@Inject extension Utils_qvto;
	@Inject extension QualifiedClassNameProvider;

	@Inject BaseEditHelper xptBaseEditHelper;
	@Inject Activator xptPluginActivator;
	@Inject MetaModel xptMetaModel;
	@Inject getExpression xptGetExpression;
	@Inject ElementTypes xptElementTypes;

	def className(GenDiagram it) '''«it.baseItemSemanticEditPolicyClassName»'''

	def packageName(GenDiagram it) '''«it.getDiagram().editPoliciesPackageName»'''

	def qualifiedClassName(GenDiagram it) '''«packageName(it)».«className(it)»'''

	def fullPath(GenDiagram it) '''«qualifiedClassName(it)»'''

	def BaseItemSemanticEditPolicy(GenDiagram it) '''
«copyright(editorGen)»
package «packageName(it)»;

«generatedClassComment()»
public class «className(it)» extends org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy {

	«attributes(it)»
	
	«constructor(it)»

	«generatedMemberComment(
		'Extended request data key to hold editpart visual id.\n' + 'Add visual id of edited editpart to extended data of the request\n' + 'so command switch can decide what kind of diagram element is being edited.\n' + 'It is done in those cases when it\'s not possible to deduce diagram\n' + 'element kind from domain element.\n' + 'Add the reoriented view to the request extended data so that the view\n ' + 'currently edited can be distinguished from other views of the same element\n ' +
			'and these latter possibly removed if they become inconsistent after reconnect\n'
	)»
	@SuppressWarnings("unchecked")
	public org.eclipse.gef.commands.Command getCommand(org.eclipse.gef.Request request) {
		if (request instanceof org.eclipse.gef.requests.ReconnectRequest) {
			Object view = ((org.eclipse.gef.requests.ReconnectRequest) request).getConnectionEditPart().getModel();
			if (view instanceof org.eclipse.gmf.runtime.notation.View) {
				String id = «getVisualIDMethodCall(it)»((org.eclipse.gmf.runtime.notation.View) view);
				request.getExtendedData().put(VISUAL_ID_KEY, id);
				request.getExtendedData().put(GRAPHICAL_RECONNECTED_EDGE, view);
			}
		}
		return super.getCommand(request);
	}
	
	«generatedMemberComment('Returns visual id from request parameters.')»
	protected String getVisualID(org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest request) {
		return (String) request.getParameter(VISUAL_ID_KEY);
	}

	«semanticPart(it)»

	«generatedMemberComment('Returns editing domain from the host edit part.')»
	protected org.eclipse.emf.transaction.TransactionalEditingDomain getEditingDomain() {
		return ((org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart) getHost()).getEditingDomain();
	}

	«addDestroyShortcutsCommand(it)»

«IF links.exists(link|!link.sansDomain)»
	«linkConstraints(it)»
«ENDIF»

	«additions(it)»
}
'''

	def attributes(GenDiagram it) '''
		«generatedMemberComment('Extended request data key to hold editpart visual id.')»
		public static final String VISUAL_ID_KEY = "visual_id"; «nonNLS()»
		«generatedMemberComment('Extended request data key to hold the edge view during a reconnect request.')»
		public static final String GRAPHICAL_RECONNECTED_EDGE = "graphical_edge"; «nonNLS»
		«generatedMemberComment()»
		private final org.eclipse.gmf.runtime.emf.type.core.IElementType myElementType;
	'''

	def constructor(GenDiagram it) '''
		«generatedMemberComment()»
		protected «className(it)»(org.eclipse.gmf.runtime.emf.type.core.IElementType elementType) {
			myElementType = elementType;
		}
	'''

	def addDestroyShortcutsCommand(GenDiagram it) '''
		«generatedMemberComment('Clean all shortcuts to the host element from the same diagram')»
		protected void addDestroyShortcutsCommand(org.eclipse.gmf.runtime.common.core.command.ICompositeCommand cmd, org.eclipse.gmf.runtime.notation.View view) {
			«_assert('view.getEAnnotation(\"Shortcut\") == null')»
			for (java.util.Iterator<?> it = view.getDiagram().getChildren().iterator(); it.hasNext();) {
				org.eclipse.gmf.runtime.notation.View nextView = (org.eclipse.gmf.runtime.notation.View) it.next();
				if (nextView.getEAnnotation("Shortcut") == null || !nextView.isSetElement() || nextView.getElement() != view.getElement()) { «nonNLS()»
					continue;
				}
				cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), nextView));
			}
		}
	'''

	def semanticPart(GenDiagram it) '''
		«getSemanticCommand(it)»
		
		«addDeleteViewCommand(it)»
		
		«getEditHelperCommand(it)»
		
		«getContextElementType(it)»
		
		«getSemanticCommandSwitch(it)»
		
		«getConfigureCommand(it)»
		
		«getCreateRelationshipCommand(it)»
		
		«getCreateCommand(it)»
		
		«getCreateSemanticServiceEditCommand(it)»
		
		«getSetCommand(it)»
		
		«getEditContextCommand(it)»
		
		«getDestroyElementCommand(it)»
		
		«getDestroyReferenceCommand(it)»
		
		«getDuplicateCommand(it)»
		
		«getMoveCommand(it)»
		
		«getReorientReferenceRelationshipCommand(it)»
		
		«getReorientRelationshipCommand(it)»
		
		«getGEFWrapper(it)»
	'''

	def getEditHelperCommand(GenDiagram it) '''
		«generatedMemberComment()»
		private org.eclipse.gef.commands.Command getEditHelperCommand(org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest request, org.eclipse.gef.commands.Command editPolicyCommand) {
			if (editPolicyCommand != null) {
				org.eclipse.gmf.runtime.common.core.command.ICommand command = editPolicyCommand instanceof org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy ? ((org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy) editPolicyCommand).getICommand() : new org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy(editPolicyCommand);
				request.setParameter(«xptBaseEditHelper.editPolicyCommandConstant(it)», command);
			}
			org.eclipse.gmf.runtime.emf.type.core.IElementType requestContextElementType = getContextElementType(request);
			request.setParameter(«xptBaseEditHelper.contextElementTypeConstant(it)», requestContextElementType);
			org.eclipse.gmf.runtime.common.core.command.ICommand command = requestContextElementType.getEditCommand(request);
			request.setParameter(«xptBaseEditHelper.editPolicyCommandConstant(it)», null);
			request.setParameter(«xptBaseEditHelper.contextElementTypeConstant(it)», null);
			if (command != null) {
				if (!(command instanceof org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand)) {
					command = new org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand(getEditingDomain(), command.getLabel()).compose(command);
				}
				return new org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy(command);
			}
			return editPolicyCommand;
		}
	'''

	def getContextElementType(GenDiagram it) '''
		«generatedMemberComment()»
		protected org.eclipse.gmf.runtime.emf.type.core.IElementType getContextElementType(org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest request) {
			org.eclipse.gmf.runtime.emf.type.core.IElementType requestContextElementType = «xptElementTypes.qualifiedClassName(it)».getElementType(getVisualID(request));
			return requestContextElementType != null ? requestContextElementType : myElementType;
		}
	'''

	def getSemanticCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getSemanticCommand(org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest request) {
	org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest completedRequest = completeRequest(request);
	org.eclipse.gef.commands.Command semanticCommand = getSemanticCommandSwitch(completedRequest);
	semanticCommand = getEditHelperCommand(completedRequest, semanticCommand);
	if (completedRequest instanceof org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest) {
		org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest destroyRequest = (org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest) completedRequest;
		return shouldProceed(destroyRequest) ? addDeleteViewCommand(semanticCommand, destroyRequest) : null;
		}
		return semanticCommand;
	}
'''

	def addDeleteViewCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command addDeleteViewCommand(org.eclipse.gef.commands.Command mainCommand, org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest completedRequest){
		org.eclipse.gef.commands.Command deleteViewCommand = getGEFWrapper(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), (org.eclipse.gmf.runtime.notation.View) getHost().getModel()));
		return mainCommand == null ? deleteViewCommand : mainCommand.chain(deleteViewCommand);
}
'''

	def getSemanticCommandSwitch(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getSemanticCommandSwitch(org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest req) {
	if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest) {
		return getCreateRelationshipCommand((org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest) req);
	} else if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest) {
		return getCreateCommand((org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest) req);
	} else if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest) {
		return getConfigureCommand((org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest) req);
	} else if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest) {
		return getDestroyElementCommand((org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest) req);
	} else if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest) {
		return getDestroyReferenceCommand((org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest) req);
	} else if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest) {
		return getDuplicateCommand((org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest) req);
	} else if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest) {
		return getEditContextCommand((org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest) req);
	} else if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest) {
		return getMoveCommand((org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest) req);
	} else if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest) {
		return getReorientReferenceRelationshipCommand((org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest) req);
	} else if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest) {
		return getReorientRelationshipCommand((org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest) req);
	} else if (req instanceof org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest) {
		return getSetCommand((org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest) req);
	}
	return null;
}
'''

	def getConfigureCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getConfigureCommand(org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest req) {
	return null;
}
'''

	def getCreateRelationshipCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getCreateRelationshipCommand(org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest req) {
	«IF !usingElementTypeCreationCommand»
	return null;
	«ELSE»
	org.eclipse.papyrus.infra.services.edit.service.IElementEditService commandService = org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils.getCommandProvider(((org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart)getHost()).resolveSemanticElement());
	if(req.getElementType() != null) {
		commandService = org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils.getCommandProvider(req.getElementType(), req.getClientContext());
	}

	if(commandService == null) {
		return org.eclipse.gef.commands.UnexecutableCommand.INSTANCE;
	}

	org.eclipse.gmf.runtime.common.core.command.ICommand semanticCommand = commandService.getEditCommand(req);

	if((semanticCommand != null) && (semanticCommand.canExecute())) {
		return getGEFWrapper(semanticCommand);
	} 
	return org.eclipse.gef.commands.UnexecutableCommand.INSTANCE;
	«ENDIF»
}
'''

	def getCreateCommand(GenDiagram it) '''
«generatedMemberComment()»
	protected org.eclipse.gef.commands.Command getCreateCommand(org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest req) {
		org.eclipse.gmf.runtime.emf.type.core.IElementType requestElementType = req.getElementType();
		if (requestElementType instanceof org.eclipse.gmf.runtime.emf.type.core.IElementType) {
						org.eclipse.papyrus.infra.services.edit.service.IElementEditService commandProvider = org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils.getCommandProvider(req.getContainer());
			if (commandProvider != null) {
				org.eclipse.gmf.runtime.common.core.command.ICommand command = commandProvider.getEditCommand(req);
				if (command != null && command.canExecute()) {
					return new org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy(command);
				}
			}
		}
		return null;
	}
'''

	def getSetCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getSetCommand(org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest req) {
	return null;
}
'''

	def getEditContextCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getEditContextCommand(org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest req) {
	return null;
}
'''

	def getDestroyElementCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getDestroyElementCommand(org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest req) {
	return null;
}
'''

	def getDestroyReferenceCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getDestroyReferenceCommand(org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest req) {
	return null;
}
'''

	def getDuplicateCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getDuplicateCommand(org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest req) {
	return null;
}
'''

	def getMoveCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getMoveCommand(org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest req) {
	«««	BEGIN: PapyrusGenCode
	«««	add move command
		org.eclipse.emf.ecore.EObject targetCEObject = req.getTargetContainer();
		if(targetCEObject != null) {
			org.eclipse.papyrus.infra.services.edit.service.IElementEditService provider = org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils.getCommandProvider(targetCEObject);
			if(provider != null) {
				org.eclipse.gmf.runtime.common.core.command.ICommand moveCommand = provider.getEditCommand(req);
				if(moveCommand != null) {
					return new org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy(moveCommand);
				}
			}
			return org.eclipse.gef.commands.UnexecutableCommand.INSTANCE;
		} else {
			return getGEFWrapper(new org.eclipse.gmf.runtime.emf.type.core.commands.MoveElementsCommand(req));
		}
	««« END: PapyrusGenCode
	
}
'''

	def getReorientReferenceRelationshipCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getReorientReferenceRelationshipCommand(org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest req) {
	return org.eclipse.gef.commands.UnexecutableCommand.INSTANCE;
}
'''

	def getReorientRelationshipCommand(GenDiagram it) '''
«generatedMemberComment()»
protected org.eclipse.gef.commands.Command getReorientRelationshipCommand(org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest req) {
	return org.eclipse.gef.commands.UnexecutableCommand.INSTANCE;
}
'''

	def getGEFWrapper(GenDiagram it) '''
		«generatedMemberComment()»
		protected final org.eclipse.gef.commands.Command getGEFWrapper(org.eclipse.gmf.runtime.common.core.command.ICommand cmd) {
		    return (cmd == null) ? org.eclipse.gef.commands.UnexecutableCommand.INSTANCE : new org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy(cmd);
		}
	'''

	/**
 *		FIXME need to check constraint's provider to ensure we don't generate a field
 *		for e.g. Java (or Literal, which is unlikely, though) expressions
 * 
 * 		[Papyrus Quick Fix] Do not generate field when the expression is provided
 * 		by a GenJavaExpressionProvider.
 */
	def linkConstraints(GenDiagram it) '''

	«generatedMemberComment()»
	public static «getLinkCreationConstraintsClassName()» getLinkConstraints() {
		«getLinkCreationConstraintsClassName()» cached = «xptPluginActivator.instanceAccess(it.editorGen)».getLinkConstraints();
		if (cached == null) {
			«xptPluginActivator.instanceAccess(it.editorGen)».setLinkConstraints(cached = new «getLinkCreationConstraintsClassName()»());
		}
		return cached;
	}

«generatedClassComment()»
public static class «getLinkCreationConstraintsClassName()» {

	«generatedMemberComment»
	public «getLinkCreationConstraintsClassName()»() {«««package-local for the BaseItemSemanticEditPolicy to instantiate. Perhaps, protected is better (i.e. if someone subclasses it?)
		// use static method #getLinkConstraints() to access instance
	}

	«FOR nextLink : it.links»
	«canCreate(nextLink)»
	«ENDFOR»

	«FOR nextLink : it.links»
	«canExist(nextLink)»
	«ENDFOR»
}
'''

	def canCreate(GenLink it) '''
«generatedMemberComment()»
public boolean canCreate«stringUniqueIdentifier()»(
«IF !it.sansDomain»
«canCreateParameters(it.modelFacet)»
«ENDIF»
) {
	«IF !it.sansDomain»
	«checkEMFConstraints(it.modelFacet)»
	«ENDIF»
	return canExist«stringUniqueIdentifier()»(
	«IF !it.sansDomain»
	«canCreateValues(it.modelFacet)»
	«ENDIF»
	);
}
'''

	/**
	 * 	XXX for now, both constraints are injected into single method
	 * 		which may not be suitable for modification especially when mixing
	 * 		java and ocl constraints (former requires manual code).
	 * 	Better approach would be:
	 * 		if either is non-null and providers are not the same - introduce two methods, 
	 * 		to check source and target separately. Otherwize, do it inplace.
	 */
	def canExist(GenLink it) '''
		«generatedMemberComment()»
			public boolean canExist«stringUniqueIdentifier()»(
		«IF !it.sansDomain»
			«canExistParameters(it.modelFacet)»
		«ENDIF»
		) {
		«IF creationConstraints != null && creationConstraints.isValid() && it.diagram.editorGen.expressionProviders != null»
			try {
			«IF creationConstraints.sourceEnd != null»
				«checkAdditionalConstraint(creationConstraints.sourceEnd.provider, creationConstraints.sourceEnd, 'source', 'target', creationConstraints.getSourceEndContextClass(), creationConstraints.getTargetEndContextClass())»
			«ENDIF»
			«IF creationConstraints.targetEnd != null»
				«checkAdditionalConstraint(creationConstraints.targetEnd.provider, creationConstraints.targetEnd, 'target', 'source', creationConstraints.getTargetEndContextClass(), creationConstraints.getSourceEndContextClass())»
			«ENDIF»
			return true;
			} catch(Exception e) {	
				«xptPluginActivator.instanceAccess(it.diagram.editorGen)».logError("Link constraint evaluation error", e); «nonNLS()»
				return false;
			}
		«ELSE»
			return true;
		«ENDIF»
		}
	'''

	/**
	 * FIXME XXX mark as private (_) and move to impl::<find proper place>::LinkConstraints.xpt
	 */
	def dispatch canCreateParameters(LinkModelFacet it) '''«sourceTargetParameters(it)»''' // source and target are reasonable defaults

	def dispatch canCreateParameters(
		TypeLinkModelFacet it) '''«IF hasContainerOtherThanSource(it)»«xptMetaModel.QualifiedClassName(it.containmentMetaFeature.genClass)» container, «ENDIF»«sourceTargetParameters(it)»'''

	def dispatch canExistParameters(LinkModelFacet it) '''«sourceTargetParameters(it)»''' // source and target are reasonable defaults

	def dispatch canExistParameters(
		TypeLinkModelFacet it) '''«IF hasContainerOtherThanSource(it)»«xptMetaModel.QualifiedClassName(it.containmentMetaFeature.genClass)» container, «ENDIF»«xptMetaModel.QualifiedClassName(metaClass)» linkInstance, «sourceTargetParameters(it)»'''

	def sourceTargetParameters(
		LinkModelFacet it) '''«xptMetaModel.QualifiedClassName(it.sourceType)» source, «xptMetaModel.QualifiedClassName(it.targetType)» target'''

// these are in fact 'canExist' values
	def dispatch canCreateValues(LinkModelFacet it) '''source, target''' // defaults

	def dispatch canCreateValues(
		TypeLinkModelFacet it) '''«IF hasContainerOtherThanSource(it)»container, «ENDIF»null, source, target'''

	def dispatch checkEMFConstraints(
		LinkModelFacet it) '''«ERROR('Unrecognized link model facet in checkEMFConstraints: ' + it)»'''

	/**
	 * [MG] extracted from LET statement, @see checkEMFConstraints(TypeLinkModelFacet)
	 */
	private def checkChildFeatureBounds(TypeLinkModelFacet it) {
		childMetaFeature != containmentMetaFeature && !isUnbounded(childMetaFeature.ecoreFeature)
	}
	

	def dispatch checkEMFConstraints(TypeLinkModelFacet it) '''
«IF containmentMetaFeature.ecoreFeature != null»
		«IF ! isUnbounded(containmentMetaFeature.ecoreFeature) || checkChildFeatureBounds(it)»
			if («getContainerVariable(it)» != null) {
						«checkEMFConstraints(containmentMetaFeature, it)»
						«IF checkChildFeatureBounds(it)»
							«checkEMFConstraints(childMetaFeature, it)»
						«ENDIF»
			}
		«ENDIF»
«ENDIF»
'''

	def checkEMFConstraints(GenFeature it, TypeLinkModelFacet modelFacet) '''
«IF modelFacet.containmentMetaFeature.ecoreFeature != null»
«IF ! isUnbounded(ecoreFeature)»
if («featureBoundComparator(it, getContainerVariable(modelFacet), modelFacet.getSourceType())») {
	return false;
}
«ENDIF»
«ENDIF»
'''

	def dispatch checkEMFConstraints(FeatureLinkModelFacet it) '''
if (source != null) {
	if («featureBoundsConditionClause(it.metaFeature, 'source', getSourceType())») {
		return false;
	}
	«IF it.metaFeature.contains»
	if (source == target) {
		return false;
	}
	«ENDIF»	
}
«IF metaFeature.reverse != null»
if (target != null && («featureBoundsConditionClause(metaFeature.reverse, 'target', getTargetType())»)) {
	return false;
}
«ENDIF»
«extraLineBreak»
'''

	def featureBoundsConditionClause(GenFeature it, String targetVar, GenClass targetType) '''
«««Checking upper bounds if was specified in MM
«IF ecoreFeature != null»
	«IF ! isUnbounded(ecoreFeature)»«featureBoundComparator(it, targetVar, targetType)»«ENDIF»
	«««	Checking uniqueness in addition if upper bounds != 1
	«IF ! isSingleValued(ecoreFeature) && ! isUnbounded(ecoreFeature)» || «ENDIF»
	«««	Checking uniqueness in if upper bounds !- 1
	«IF ! isSingleValued(ecoreFeature)»«featureUniquenessComparator(it, targetVar, targetType)»«ENDIF»
«ENDIF»
'''

	def featureBoundComparator(GenFeature it, String featureVar, GenClass featureVarGenClass) '''
		«xptMetaModel.getFeatureValue(it, featureVar, featureVarGenClass)»
		«IF ecoreFeature.upperBound == 1»
			!= null
		«ELSE»
			.size() >= «ecoreFeature.upperBound»
		«ENDIF»
	'''

	def featureUniquenessComparator(GenFeature it, String featureVar,
		GenClass featureVarGenClass) '''«xptMetaModel.getFeatureValue(it, featureVar, featureVarGenClass)».contains(target)'''

	def dispatch checkAdditionalConstraint(GenExpressionProviderBase it, ValueExpression valueExpr, String sourceEndVar,
		String targetEndVar, GenClass context, GenClass oppositeEndContext) '''
«ERROR('Have no idea what extra constraints to check for ' + it)»
'''

	def dispatch checkAdditionalConstraint(GenExpressionInterpreter it, ValueExpression valueExpr, String sourceEndVar,
		String targetEndVar, GenClass context, GenClass oppositeEndContext) '''
		if («sourceEndVar» == null) {
			return true;
		} else {«/** else is important here as it gives scope for the env variable */»
			java.util.Map<String, org.eclipse.emf.ecore.EClassifier> env = java.util.Collections.<String, org.eclipse.emf.ecore.EClassifier>singletonMap(«oppositeEndVariableNameValue(it)», «xptMetaModel.MetaClass(oppositeEndContext)»); «nonNLS()»
			Object «sourceEndVar»Val = «xptGetExpression.getExpression(it, valueExpr, context, 'env')».evaluate(«sourceEndVar», java.util.Collections.singletonMap(«oppositeEndVariableNameValue(it)», «targetEndVar»)); «nonNLS()»
			if (false == «sourceEndVar»Val instanceof Boolean || !((Boolean) «sourceEndVar»Val).booleanValue()) {
				return false;
			} // else fall-through
		}
	'''

	def dispatch checkAdditionalConstraint(GenJavaExpressionProvider it, ValueExpression valueExpr, String sourceEndVar,
		String targetEndVar, GenClass context, GenClass oppositeEndContext) '''
«IF injectExpressionBody && valueExpr.body != null && !valueExpr.body.empty»
	«valueExpr.body»
«ELSEIF throwException || (injectExpressionBody && (valueExpr.body == null || valueExpr.body.empty))»
	// TODO: implement this method, using «sourceEndVar» and «targetEndVar» 
	// to access link source and target, respectively
	// Ensure that you remove @generated or mark it @generated NOT
	if (Boolean.TRUE.booleanValue()) {
		throw new java.lang.UnsupportedOperationException("No java implementation provided"); «nonNLS()»
	}
«ELSE»
	if (Boolean.TRUE.booleanValue()) {«/*just in case there are two consecutive java expression with neither throw nor inject - avoid unreachable code.*/»
		return false;
	}
«ENDIF»
'''

	@MetaDef def canExistCall(FeatureLinkModelFacet xptSelf, GenLink link, String sourceVar, String targetVar) //
	'''«_accessLinkConstraints(link.diagram)».canExist«link.uniqueIdentifier»(«sourceVar», «targetVar»)'''

	/**
	 * NOTE, containerVar will be used only when link has other container than its source. It's safe to pass variable/method name that doesn't exist
	 */
	@MetaDef def canExistCall(TypeLinkModelFacet xptSelf, GenLink link, String containerVar, String linkVar,
		String sourceVar, String targetVar) //
	'''«_accessLinkConstraints(link.diagram)».canExist«link.uniqueIdentifier»(«IF hasContainerOtherThanSource(xptSelf)»«containerVar», «ENDIF»«linkVar», «sourceVar», «targetVar»)'''

	@MetaDef def canCreateCall(FeatureLinkModelFacet xptSelf, GenLink link, String sourceVar, String targetVar) //
	'''«_accessLinkConstraints(link.diagram)».canCreate«link.uniqueIdentifier»(«sourceVar», «targetVar»)'''

	/**
	 * NOTE, containerVar will be used only when link has other container than its source. It's safe to pass variable/method name that doesn't exist
	 * Yes, this is sorta hack, but no idea of better approach right now. Perhaps, CreateLinkUtils may always define getContainer() for TypeLinkModelFacet, and use getSource() by default?
	 */
	@MetaDef def canCreateCall(TypeLinkModelFacet xptSelf, GenLink link, String containerVar, String sourceVar,
		String targetVar) //
	'''«_accessLinkConstraints(link.diagram)».canCreate«link.uniqueIdentifier»(«IF hasContainerOtherThanSource(xptSelf)»«containerVar», «ENDIF»«sourceVar», «targetVar»)'''

	@MetaDef private def _accessLinkConstraints(
		GenDiagram xptSelf) '''«qualifiedClassName(xptSelf)».getLinkConstraints()'''

	def oppositeEndVariableNameValue(Object any) '''"oppositeEnd"'''

	def additions(GenDiagram it) ''''''

	def defaultConstructor(GenCommonBase it) '''
		«generatedMemberComment()»
		public «getItemSemanticEditPolicyClassName(it)»() {
			«defaultConstructorBody(it)»
		}
	'''

	/**
	 * @param genCommon diagram, node or link, which MUST have an element type (genCommon.elementType != null)
	 */
	def defaultConstructorBody(GenCommonBase genCommon) '''
		«IF genCommon.elementType == null»
			«ERROR("No element type in the passed node. Only diagram, node or link are supported in this template: " + genCommon)»
		«ENDIF»
		super(«xptElementTypes.accessElementType(genCommon)»);
	'''


	/**
	 * Generate generic method if using semantic creation command based on element types framework.
	 * 
	 */
	def getCreateSemanticServiceEditCommand(GenDiagram it) '''
«IF usingElementTypeCreationCommand»
	«generatedMemberComment»
	protected org.eclipse.gmf.runtime.common.core.command.ICommand getSemanticCreationCommand(org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest req) {
		org.eclipse.papyrus.infra.services.edit.service.IElementEditService commandService = org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils.getCommandProvider(req.getContainer());
		if(commandService == null) {
			return org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE;
		}
		return commandService.getEditCommand(req);
	}
«ENDIF»	
'''
}
