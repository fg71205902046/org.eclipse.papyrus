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
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.property.editor.xtext.validation;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.property.editor.xtext.umlProperty.MultiplicityRule;
import org.eclipse.papyrus.property.editor.xtext.umlProperty.PropertyRule;
import org.eclipse.papyrus.property.editor.xtext.umlProperty.RedefinesRule;
import org.eclipse.papyrus.property.editor.xtext.umlProperty.SubsetsRule;
import org.eclipse.papyrus.property.editor.xtext.umlProperty.UmlPropertyPackage;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.xtext.validation.Check;
 

/**
 * Class for customized java validation
 *
 */
public class UmlPropertyJavaValidator extends AbstractUmlPropertyJavaValidator {

	private static Namespace model ;
	private static Element contextElement ;
	
	// private variables for semantic error management (See IXtextSemanticValidator from org.eclipse.xtext.gmf.glue)
	private static boolean valid_MultiplicityRule = true ;
	private static boolean valid_RedefinesRule = true ;
	private static boolean valid_SubsetsRule = true ;
	
	public static void init(Element _contextElement) {
		contextElement = _contextElement ;
		if (contextElement != null) {
			Element elem = contextElement.getOwner() ;
			while (elem.getOwner() != null) {
				elem = elem.getOwner() ;
			}
			model = (Namespace)elem ;
		}
	}

	public static Namespace getModel() {
		return model ;
	}
	
	public static Element getContextElement() {
		return contextElement ;
	}
	
	public static boolean validate() {
		return valid_MultiplicityRule && valid_RedefinesRule && valid_SubsetsRule ;
	}
	
	/**
	 * Custom validation for multiplicities. Raises an error in the case where the lower bound is upper than the upper bound.
	 * 
	 */
	@Check
	public void checkMultiplicityRule (MultiplicityRule rule) {
		int lowerValue = 0 ;
		int upperValue = 0 ;
		String errorMessage = "The upper bound of a multiplicity cannot be lower than the lower bound." ;
		try {
			if (rule.getBounds().size() == 2) {
				lowerValue = rule.getBounds().get(0).getValue().equals("*") ? -1 : Integer.valueOf(rule.getBounds().get(0).getValue()) ;
				upperValue = rule.getBounds().get(1).getValue().equals("*") ? -1 : Integer.valueOf(rule.getBounds().get(1).getValue()) ;
				if ((lowerValue == -1 && upperValue != -1) ||
					(lowerValue > upperValue && upperValue != -1)) {
					error(errorMessage, rule, UmlPropertyPackage.BOUND_SPECIFICATION__VALUE) ;
					valid_MultiplicityRule = false ;
				}
				else {
					valid_MultiplicityRule = true ;
				}
			}
		}
		catch (Exception e) {
			// An exception may be raised only in the case where the syntax for multiplicities is not respected.
			// No error needs to be generated (the syntax error is automatically handled by XText)
		}
	}
	
	@Check
	public void checkRedefinesRule (RedefinesRule rule) {
		try {
			Property redefinedProperty = rule.getProperty() ;

			String typeErrorMessage = "The type of the redefining property must conform to the type of the redefined property" ;
			String multiplicityErrorMessage = "The multiplicity of the redefining property must be contained in the multiplicity of the redefined property" ;
			String isDerivedErrorMessage = "Since the redefined property is derived, the redefining property must be derived" ;

			EObject container = rule.eContainer() ;

			while (container != null && !(container instanceof PropertyRule)) {
				container = container.eContainer() ;
			}

			if (container == null)
				return ;

			PropertyRule propertyRule = (PropertyRule)container ;
			Classifier typeOfRedefiningProperty = propertyRule.getType().getType() ;
			boolean isRedefiningPropertyDerived = propertyRule.getIsDerived() != null && propertyRule.getIsDerived().equals("/") ;

			valid_RedefinesRule = typeOfRedefiningProperty.conformsTo(redefinedProperty.getType()) ;
			if (! valid_RedefinesRule) {
				error(typeErrorMessage, rule, UmlPropertyPackage.REDEFINES_RULE__PROPERTY) ;
				return ;
			}

			int lowerBoundOfRedefinedProperty = redefinedProperty.getLower() ;
			int upperBoundOfRedefinedProperty = redefinedProperty.getUpper() ;

			int lowerBound = 1 ;
			int upperBound = 1 ;

			if (propertyRule.getMultiplicity() != null) {
				if (propertyRule.getMultiplicity().getBounds().size() == 1) {
					lowerBound = propertyRule.getMultiplicity().getBounds().get(0).getValue().equals("*") ? -1 : Integer.valueOf(propertyRule.getMultiplicity().getBounds().get(0).getValue()) ;
					upperBound = lowerBound ;
				}
				else if (propertyRule.getMultiplicity().getBounds().size() == 2) {
					lowerBound = propertyRule.getMultiplicity().getBounds().get(0).getValue().equals("*") ? -1 : Integer.valueOf(propertyRule.getMultiplicity().getBounds().get(0).getValue()) ;
					upperBound = propertyRule.getMultiplicity().getBounds().get(1).getValue().equals("*") ? -1 : Integer.valueOf(propertyRule.getMultiplicity().getBounds().get(1).getValue()) ;
				}
			}

			valid_RedefinesRule = lowerBound >= lowerBoundOfRedefinedProperty ;
			switch (upperBoundOfRedefinedProperty) {
			case -1:
				break ;
			default:
				valid_RedefinesRule = valid_RedefinesRule && upperBound <= upperBoundOfRedefinedProperty;
				break;
			}

			if (! valid_RedefinesRule) {
				error(multiplicityErrorMessage, rule, UmlPropertyPackage.REDEFINES_RULE__PROPERTY) ;
				return ;
			}
			
			if (redefinedProperty.isDerived() && !isRedefiningPropertyDerived)
				valid_RedefinesRule = false ;
			if (! valid_RedefinesRule) {
				error(isDerivedErrorMessage, rule, UmlPropertyPackage.REDEFINES_RULE__PROPERTY) ;
				return ;
			}

		}
		catch (Exception e) {
			// An exception may be raised only in the case where the syntax for subsets is not respected.
			// No error needs to be generated (the syntax error is automatically handled by XText)
		}
	}
	
	@Check
	public void checkSubsetsRule (SubsetsRule rule) {
		try {
			Property subsettedProperty = rule.getProperty() ;

			String typeErrorMessage = "The type of the subsetting property must conform to the type of the subsetted property" ;
			String multiplicityErrorMessage = "The multiplicity of the subsetting property must be contained in the multiplicity of the subsetted property" ;

			EObject container = rule.eContainer() ;

			while (container != null && !(container instanceof PropertyRule)) {
				container = container.eContainer() ;
			}

			if (container == null)
				return ;

			PropertyRule propertyRule = (PropertyRule)container ;
			Classifier typeOfSubsettingProperty = propertyRule.getType().getType() ;

			valid_SubsetsRule = typeOfSubsettingProperty.conformsTo(subsettedProperty.getType()) ;
			if (! valid_SubsetsRule) {
				error(typeErrorMessage, rule, UmlPropertyPackage.SUBSETS_RULE__PROPERTY) ;
				return ;
			}

			int lowerBoundOfRedefinedProperty = subsettedProperty.getLower() ;
			int upperBoundOfRedefinedProperty = subsettedProperty.getUpper() ;

			int lowerBound = 1 ;
			int upperBound = 1 ;

			if (propertyRule.getMultiplicity() != null) {
				if (propertyRule.getMultiplicity().getBounds().size() == 1) {
					lowerBound = propertyRule.getMultiplicity().getBounds().get(0).getValue().equals("*") ? -1 : Integer.valueOf(propertyRule.getMultiplicity().getBounds().get(0).getValue()) ;
					upperBound = lowerBound ;
				}
				else if (propertyRule.getMultiplicity().getBounds().size() == 2) {
					lowerBound = propertyRule.getMultiplicity().getBounds().get(0).getValue().equals("*") ? -1 : Integer.valueOf(propertyRule.getMultiplicity().getBounds().get(0).getValue()) ;
					upperBound = propertyRule.getMultiplicity().getBounds().get(1).getValue().equals("*") ? -1 : Integer.valueOf(propertyRule.getMultiplicity().getBounds().get(1).getValue()) ;
				}
			}

			valid_SubsetsRule = lowerBound >= lowerBoundOfRedefinedProperty ;
			switch (upperBoundOfRedefinedProperty) {
			case -1:
				break ;
			default:
				valid_SubsetsRule = valid_SubsetsRule && upperBound <= upperBoundOfRedefinedProperty;
				break;
			}

			if (! valid_SubsetsRule) {
				error(multiplicityErrorMessage, rule, UmlPropertyPackage.SUBSETS_RULE__PROPERTY) ;
				return ;
			}
			

		}
		catch (Exception e) {
			// An exception may be raised only in the case where the syntax for subsets is not respected.
			// No error needs to be generated (the syntax error is automatically handled by XText)
		}
	}
}
