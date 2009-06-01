/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Emilien Perico (Atos Origin) emilien.perico@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.usecase.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ActorName2EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ActorName3EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ActorNameEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.AssociationNameEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.CommentBody2EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.CommentBodyEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ComponentName2EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ComponentName3EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ComponentNameEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ConstraintName2EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ConstraintName3EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ConstraintNameEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.DependencyNameEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ExtensionPoint2EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.ExtensionPointEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.PackageName2EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.PackageNameEditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.UseCaseName2EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.UseCaseName3EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.UseCaseName4EditPart;
import org.eclipse.papyrus.diagram.usecase.edit.parts.UseCaseNameEditPart;
import org.eclipse.papyrus.diagram.usecase.parsers.MessageFormatParser;
import org.eclipse.papyrus.diagram.usecase.part.UMLVisualIDRegistry;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class UMLParserProvider extends AbstractProvider implements IParserProvider {

	/**
	 * @generated
	 */
	private IParser actorName_5014Parser;

	/**
	 * @generated
	 */
	private IParser getActorName_5014Parser() {
		if (actorName_5014Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			actorName_5014Parser = parser;
		}
		return actorName_5014Parser;
	}

	/**
	 * @generated
	 */
	private IParser actorName_5015Parser;

	/**
	 * @generated
	 */
	private IParser getActorName_5015Parser() {
		if (actorName_5015Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			actorName_5015Parser = parser;
		}
		return actorName_5015Parser;
	}

	/**
	 * @generated
	 */
	private IParser useCaseName_5016Parser;

	/**
	 * @generated
	 */
	private IParser getUseCaseName_5016Parser() {
		if (useCaseName_5016Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			useCaseName_5016Parser = parser;
		}
		return useCaseName_5016Parser;
	}

	/**
	 * @generated
	 */
	private IParser useCaseName_5017Parser;

	/**
	 * @generated
	 */
	private IParser getUseCaseName_5017Parser() {
		if (useCaseName_5017Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			useCaseName_5017Parser = parser;
		}
		return useCaseName_5017Parser;
	}

	/**
	 * @generated
	 */
	private IParser componentName_5019Parser;

	/**
	 * @generated
	 */
	private IParser getComponentName_5019Parser() {
		if (componentName_5019Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			componentName_5019Parser = parser;
		}
		return componentName_5019Parser;
	}

	/**
	 * @generated
	 */
	private IParser packageName_5025Parser;

	/**
	 * @generated
	 */
	private IParser getPackageName_5025Parser() {
		if (packageName_5025Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			packageName_5025Parser = parser;
		}
		return packageName_5025Parser;
	}

	/**
	 * @generated
	 */
	private IParser constraintName_5026Parser;

	/**
	 * @generated
	 */
	private IParser getConstraintName_5026Parser() {
		if (constraintName_5026Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			constraintName_5026Parser = parser;
		}
		return constraintName_5026Parser;
	}

	/**
	 * @generated
	 */
	private IParser commentBody_5027Parser;

	/**
	 * @generated
	 */
	private IParser getCommentBody_5027Parser() {
		if (commentBody_5027Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getComment_Body() };
			MessageFormatParser parser = new MessageFormatParser(features);
			commentBody_5027Parser = parser;
		}
		return commentBody_5027Parser;
	}

	/**
	 * @generated
	 */
	private IParser extensionPoint_3007Parser;

	/**
	 * @generated
	 */
	private IParser getExtensionPoint_3007Parser() {
		if (extensionPoint_3007Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			extensionPoint_3007Parser = parser;
		}
		return extensionPoint_3007Parser;
	}

	/**
	 * @generated
	 */
	private IParser extensionPoint_3008Parser;

	/**
	 * @generated
	 */
	private IParser getExtensionPoint_3008Parser() {
		if (extensionPoint_3008Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			extensionPoint_3008Parser = parser;
		}
		return extensionPoint_3008Parser;
	}

	/**
	 * @generated
	 */
	private IParser useCaseName_5018Parser;

	/**
	 * @generated
	 */
	private IParser getUseCaseName_5018Parser() {
		if (useCaseName_5018Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			useCaseName_5018Parser = parser;
		}
		return useCaseName_5018Parser;
	}

	/**
	 * @generated
	 */
	private IParser componentName_5030Parser;

	/**
	 * @generated
	 */
	private IParser getComponentName_5030Parser() {
		if (componentName_5030Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			componentName_5030Parser = parser;
		}
		return componentName_5030Parser;
	}

	/**
	 * @generated
	 */
	private IParser commentBody_5028Parser;

	/**
	 * @generated
	 */
	private IParser getCommentBody_5028Parser() {
		if (commentBody_5028Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getComment_Body() };
			MessageFormatParser parser = new MessageFormatParser(features);
			commentBody_5028Parser = parser;
		}
		return commentBody_5028Parser;
	}

	/**
	 * @generated
	 */
	private IParser constraintName_5029Parser;

	/**
	 * @generated
	 */
	private IParser getConstraintName_5029Parser() {
		if (constraintName_5029Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			constraintName_5029Parser = parser;
		}
		return constraintName_5029Parser;
	}

	/**
	 * @generated
	 */
	private IParser constraintName_5020Parser;

	/**
	 * @generated
	 */
	private IParser getConstraintName_5020Parser() {
		if (constraintName_5020Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			constraintName_5020Parser = parser;
		}
		return constraintName_5020Parser;
	}

	/**
	 * @generated
	 */
	private IParser actorName_5021Parser;

	/**
	 * @generated
	 */
	private IParser getActorName_5021Parser() {
		if (actorName_5021Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			actorName_5021Parser = parser;
		}
		return actorName_5021Parser;
	}

	/**
	 * @generated
	 */
	private IParser useCaseName_5022Parser;

	/**
	 * @generated
	 */
	private IParser getUseCaseName_5022Parser() {
		if (useCaseName_5022Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			useCaseName_5022Parser = parser;
		}
		return useCaseName_5022Parser;
	}

	/**
	 * @generated
	 */
	private IParser componentName_5023Parser;

	/**
	 * @generated
	 */
	private IParser getComponentName_5023Parser() {
		if (componentName_5023Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			componentName_5023Parser = parser;
		}
		return componentName_5023Parser;
	}

	/**
	 * @generated
	 */
	private IParser packageName_5024Parser;

	/**
	 * @generated
	 */
	private IParser getPackageName_5024Parser() {
		if (packageName_5024Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			packageName_5024Parser = parser;
		}
		return packageName_5024Parser;
	}

	/**
	 * @generated
	 */
	private IParser associationName_6008Parser;

	/**
	 * @generated
	 */
	private IParser getAssociationName_6008Parser() {
		if (associationName_6008Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			associationName_6008Parser = parser;
		}
		return associationName_6008Parser;
	}

	/**
	 * @generated
	 */
	private IParser dependencyName_6010Parser;

	/**
	 * @generated
	 */
	private IParser getDependencyName_6010Parser() {
		if (dependencyName_6010Parser == null) {
			EAttribute[] features = new EAttribute[] { UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dependencyName_6010Parser = parser;
		}
		return dependencyName_6010Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ActorNameEditPart.VISUAL_ID:
			return getActorName_5014Parser();
		case ActorName2EditPart.VISUAL_ID:
			return getActorName_5015Parser();
		case UseCaseNameEditPart.VISUAL_ID:
			return getUseCaseName_5016Parser();
		case UseCaseName2EditPart.VISUAL_ID:
			return getUseCaseName_5017Parser();
		case ComponentNameEditPart.VISUAL_ID:
			return getComponentName_5019Parser();
		case PackageNameEditPart.VISUAL_ID:
			return getPackageName_5025Parser();
		case ConstraintNameEditPart.VISUAL_ID:
			return getConstraintName_5026Parser();
		case CommentBodyEditPart.VISUAL_ID:
			return getCommentBody_5027Parser();
		case ExtensionPointEditPart.VISUAL_ID:
			return getExtensionPoint_3007Parser();
		case ExtensionPoint2EditPart.VISUAL_ID:
			return getExtensionPoint_3008Parser();
		case UseCaseName3EditPart.VISUAL_ID:
			return getUseCaseName_5018Parser();
		case ComponentName2EditPart.VISUAL_ID:
			return getComponentName_5030Parser();
		case CommentBody2EditPart.VISUAL_ID:
			return getCommentBody_5028Parser();
		case ConstraintName2EditPart.VISUAL_ID:
			return getConstraintName_5029Parser();
		case ConstraintName3EditPart.VISUAL_ID:
			return getConstraintName_5020Parser();
		case ActorName3EditPart.VISUAL_ID:
			return getActorName_5021Parser();
		case UseCaseName4EditPart.VISUAL_ID:
			return getUseCaseName_5022Parser();
		case ComponentName3EditPart.VISUAL_ID:
			return getComponentName_5023Parser();
		case PackageName2EditPart.VISUAL_ID:
			return getPackageName_5024Parser();
		case AssociationNameEditPart.VISUAL_ID:
			return getAssociationName_6008Parser();
		case DependencyNameEditPart.VISUAL_ID:
			return getDependencyName_6010Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * 
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(UMLVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(UMLVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (UMLElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
