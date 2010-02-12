/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
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
package org.eclipse.papyrus.diagram.profile.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.diagram.common.service.AspectUnspecifiedTypeConnectionTool;
import org.eclipse.papyrus.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.eclipse.papyrus.diagram.profile.providers.UMLElementTypes;

/**
 * @generated
 */
public class UMLPaletteFactory extends PaletteFactory.Adapter {





	/**
	 * @generated
	 */
	private final static String CREATEPACKAGE1CREATIONTOOL = "createPackage1CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEMODEL2CREATIONTOOL = "createModel2CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEPROFILE3CREATIONTOOL = "createProfile3CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECOMMENT4CREATIONTOOL = "createComment4CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECONSTRAINT5CREATIONTOOL = "createConstraint5CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATESTEREOTYPE2CREATIONTOOL = "createStereotype2CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEMETACLASS3CREATIONTOOL = "createMetaclass3CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEENUMERATION4CREATIONTOOL = "createEnumeration4CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEDATATYPE5CREATIONTOOL = "createDataType5CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEEXTENSION1CREATIONTOOL = "createExtension1CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEGENERALIZATION2CREATIONTOOL = "createGeneralization2CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECOMMENTLINK3CREATIONTOOL = "createCommentlink3CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECONSTRAINTLINK4CREATIONTOOL = "createConstraintLink4CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEPROFILEAPPLICATION5CREATIONTOOL = "createProfileApplication5CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEELEMENTIMPORT6CREATIONTOOL = "createElementImport6CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEPACKAGEIMPORT7CREATIONTOOL = "createPackageImport7CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEDEPENDENCY8CREATIONTOOL = "createDependency8CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEDEPENDENCYBRANCH9CREATIONTOOL = "createDependencyBranch9CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEASSOCIATION10CREATIONTOOL = "createAssociation10CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEASSOCIATIONBRANCH11CREATIONTOOL = "createAssociationBranch11CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEPROPERTY1CREATIONTOOL = "createProperty1CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEOPERATION2CREATIONTOOL = "createOperation2CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEENUMERATIONLITERAL3CREATIONTOOL = "createEnumerationLiteral3CreationTool"; //$NON-NLS-1$



	/**
	 * @generated
	 */
	public UMLPaletteFactory() {

	}



	/**
	 * @generated
	 */
	public Tool createTool(String toolId) {
		if(toolId.equals(CREATEPACKAGE1CREATIONTOOL)) {
			return createPackage1CreationTool();
		}
		if(toolId.equals(CREATEMODEL2CREATIONTOOL)) {
			return createModel2CreationTool();
		}
		if(toolId.equals(CREATEPROFILE3CREATIONTOOL)) {
			return createProfile3CreationTool();
		}
		if(toolId.equals(CREATECOMMENT4CREATIONTOOL)) {
			return createComment4CreationTool();
		}
		if(toolId.equals(CREATECONSTRAINT5CREATIONTOOL)) {
			return createConstraint5CreationTool();
		}
		if(toolId.equals(CREATESTEREOTYPE2CREATIONTOOL)) {
			return createStereotype2CreationTool();
		}
		if(toolId.equals(CREATEMETACLASS3CREATIONTOOL)) {
			return createMetaclass3CreationTool();
		}
		if(toolId.equals(CREATEENUMERATION4CREATIONTOOL)) {
			return createEnumeration4CreationTool();
		}
		if(toolId.equals(CREATEDATATYPE5CREATIONTOOL)) {
			return createDataType5CreationTool();
		}
		if(toolId.equals(CREATEEXTENSION1CREATIONTOOL)) {
			return createExtension1CreationTool();
		}
		if(toolId.equals(CREATEGENERALIZATION2CREATIONTOOL)) {
			return createGeneralization2CreationTool();
		}
		if(toolId.equals(CREATECOMMENTLINK3CREATIONTOOL)) {
			return createCommentlink3CreationTool();
		}
		if(toolId.equals(CREATECONSTRAINTLINK4CREATIONTOOL)) {
			return createConstraintLink4CreationTool();
		}
		if(toolId.equals(CREATEPROFILEAPPLICATION5CREATIONTOOL)) {
			return createProfileApplication5CreationTool();
		}
		if(toolId.equals(CREATEELEMENTIMPORT6CREATIONTOOL)) {
			return createElementImport6CreationTool();
		}
		if(toolId.equals(CREATEPACKAGEIMPORT7CREATIONTOOL)) {
			return createPackageImport7CreationTool();
		}
		if(toolId.equals(CREATEDEPENDENCY8CREATIONTOOL)) {
			return createDependency8CreationTool();
		}
		if(toolId.equals(CREATEDEPENDENCYBRANCH9CREATIONTOOL)) {
			return createDependencyBranch9CreationTool();
		}
		if(toolId.equals(CREATEASSOCIATION10CREATIONTOOL)) {
			return createAssociation10CreationTool();
		}
		if(toolId.equals(CREATEASSOCIATIONBRANCH11CREATIONTOOL)) {
			return createAssociationBranch11CreationTool();
		}
		if(toolId.equals(CREATEPROPERTY1CREATIONTOOL)) {
			return createProperty1CreationTool();
		}
		if(toolId.equals(CREATEOPERATION2CREATIONTOOL)) {
			return createOperation2CreationTool();
		}
		if(toolId.equals(CREATEENUMERATIONLITERAL3CREATIONTOOL)) {
			return createEnumerationLiteral3CreationTool();
		}

		// default return: null
		return null;
	}




	public Object getTemplate(String templateId) {

		// default return: null
		return null;
	}



	/**
	 * @generated
	 */
	private Tool createPackage1CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Package_2007);
		types.add(UMLElementTypes.Package_1012);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createModel2CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Model_2005);
		types.add(UMLElementTypes.Model_1027);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createProfile3CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Profile_1030);
		types.add(UMLElementTypes.Profile_1024);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createComment4CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Comment_1002);
		types.add(UMLElementTypes.Comment_1007);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createConstraint5CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Constraint_1014);
		types.add(UMLElementTypes.Constraint_1028);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createStereotype2CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Stereotype_1023);
		types.add(UMLElementTypes.Stereotype_1026);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createMetaclass3CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Class_1031);
		types.add(UMLElementTypes.Class_3028);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createEnumeration4CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Enumeration_2006);
		types.add(UMLElementTypes.Enumeration_3025);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createDataType5CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.DataType_2010);
		types.add(UMLElementTypes.DataType_3027);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createExtension1CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Extension_1013);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createGeneralization2CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Generalization_4002);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createCommentlink3CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.CommentAnnotatedElement_1022);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createConstraintLink4CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ConstraintConstrainedElement_4014);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createProfileApplication5CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ProfileApplication_1045);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createElementImport6CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ElementImport_1064);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createPackageImport7CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.PackageImport_1065);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createDependency8CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Dependency_4008);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createDependencyBranch9CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Dependency_4018);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createAssociation10CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Association_4001);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createAssociationBranch11CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Association_4019);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createProperty1CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Property_3002);
		types.add(UMLElementTypes.Property_3018);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createOperation2CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Operation_3019);
		types.add(UMLElementTypes.Operation_3020);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createEnumerationLiteral3CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.EnumerationLiteral_1037);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}


}
