/*****************************************************************************
 * Copyright (c) 2008 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.umlutils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Image;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;

// TODO: Auto-generated Javadoc
/**
 * Utility class for Stereotypes.
 */
public class StereotypeUtil {

	/**
	 * Parse the stereotype image and select those that have an "icon" kind (EAnnotation).
	 * 
	 * @param stereotype
	 *            to parse
	 * 
	 * @return a EList of {@link Image}
	 */
	public static EList<Image> getIcons(Stereotype stereotype) {

		EList<Image> icons = new BasicEList<Image>();

		Iterator<Image> it = stereotype.getIcons().iterator();
		while (it.hasNext()) {
			Image image = it.next();
			if ("icon".equals(ImageUtil.getKind(image))) {
				icons.add(image);
			}
		}

		return icons;
	}

	/**
	 * Returns the list of names (not qualified) of properties to display.
	 * 
	 * @param stereotype
	 * @param stPropList
	 * 
	 * @return
	 */
	private static ArrayList<String> getStereoPropertiesToDisplay(org.eclipse.uml2.uml.Stereotype stereotype, ArrayList<String> stPropList) {
		ArrayList<String> result = new ArrayList<String>();

		Iterator<String> propIter = stPropList.iterator();
		while (propIter.hasNext()) {
			String currentProp = propIter.next();
			if (currentProp.substring(0, currentProp.lastIndexOf(".")).equals(stereotype.getQualifiedName())) {
				result.add(currentProp.substring(currentProp.lastIndexOf(".") + 1, currentProp.length()));
			}
		}
		return result;
	}

	/**
	 * return string that contains value of properties of applied stereotype
	 * 
	 * @param stereotypesPropertiesToDisplay
	 *            list of properties of stereotype to display grammar= {<B>stereotypequalifiedName</B>'.'<B>propertyName</B>','}*
	 * 
	 * @return a string withe the following grammar grammar= {'\u00AB'<B>StereotypeName</B>'\u00BB''#'{<B>propertyName</B>'='<B>propertyValue</B>'|'}*';'}*
	 */
	public static String getPropertiesValues(String stereotypesPropertiesToDisplay, Element umlElement) {
		final String SPACE_SEPARATOR = "#";
		final String EQUAL_SEPARATOR = "=";
		final String PROPERTY_VALUE_SEPARATOR = "|";
		final String SETREOTYPE_WITH_VALUE_SEPARATOR = ";";
		final String ST_LEFT = String.valueOf("\u00AB");
		final String ST_RIGHT = String.valueOf("\u00BB");

		HashSet<org.eclipse.uml2.uml.Stereotype> stereoSet = new HashSet<org.eclipse.uml2.uml.Stereotype>();
		ArrayList<String> stPropList = new ArrayList<String>();

		String propValues = "";

		// fill our data structure in order to generate the string
		StringTokenizer propStringTokenizer = new StringTokenizer(stereotypesPropertiesToDisplay, ",");
		while (propStringTokenizer.hasMoreElements()) {
			// extract property to display
			String propertyQN = propStringTokenizer.nextToken();
			// stereotype
			String stereotypeQN = propertyQN.substring(0, propertyQN.indexOf("."));
			stereoSet.add(umlElement.getAppliedStereotype(stereotypeQN));
			stPropList.add(propertyQN);
		}

		// Display each stereotype
		Iterator<org.eclipse.uml2.uml.Stereotype> stereoIter = stereoSet.iterator();
		while (stereoIter.hasNext()) {
			org.eclipse.uml2.uml.Stereotype currentSt = stereoIter.next();
			// display the stereotype
			propValues = propValues + ST_LEFT + currentSt.getName() + ST_RIGHT + SPACE_SEPARATOR;
			// get the set of property to display
			Iterator<String> stPropIter = getStereoPropertiesToDisplay(currentSt, stPropList).iterator();

			// display each property
			while (stPropIter.hasNext()) {
				String stProp = stPropIter.next();
				// get the property
				org.eclipse.uml2.uml.Property currentProp = null;
				Iterator iterPro = currentSt.getAllAttributes().iterator();
				// from a string look for the property
				while (iterPro.hasNext()) {
					org.eclipse.uml2.uml.Property tmpProperty = (org.eclipse.uml2.uml.Property) iterPro.next();
					if (tmpProperty.getName().equals(stProp)) {
						currentProp = tmpProperty;
					}
				}

				if (currentProp == null) {
					return "No value";
				}
				org.eclipse.uml2.uml.Type propType = currentProp.getType();

				// property type is an enumeration

				if (propType instanceof org.eclipse.uml2.uml.Enumeration) {
					propValues = propValues + getPropertyValueForEnumerationType(currentProp, currentSt, umlElement, EQUAL_SEPARATOR, PROPERTY_VALUE_SEPARATOR);
				}

				// property type is a metaclass
				else if ((propType instanceof org.eclipse.uml2.uml.Class) && (propType.getAppliedStereotypes() != null) && (propType.getAppliedStereotypes().size() > 0)
						&& ((org.eclipse.uml2.uml.Stereotype) propType.getAppliedStereotypes().get(0)).getName().equals("Metaclass")) {
					propValues = propValues + getPropertyValueForMetaclassType(currentProp, currentSt, umlElement, EQUAL_SEPARATOR, PROPERTY_VALUE_SEPARATOR);
				}

				// property type is a stereotype
				else if (propType instanceof org.eclipse.uml2.uml.Stereotype) {
					propValues = propValues + getPropertyValueForStereotypeType(currentProp, currentSt, umlElement, EQUAL_SEPARATOR, PROPERTY_VALUE_SEPARATOR);
				}

				// property is a composite class
				else if ((propType instanceof org.eclipse.uml2.uml.Class) && !(propType instanceof org.eclipse.uml2.uml.Stereotype) && currentProp.isComposite()) {
					propValues = propValues + stProp + EQUAL_SEPARATOR + currentProp.getName() + PROPERTY_VALUE_SEPARATOR;
				}

				// otherwise
				else {
					propValues = propValues + getPropertyValue(currentProp, currentSt, umlElement, EQUAL_SEPARATOR, PROPERTY_VALUE_SEPARATOR);
				}
			}// display each property
			if (propValues.endsWith(PROPERTY_VALUE_SEPARATOR)) {
				propValues = propValues.substring(0, propValues.lastIndexOf(PROPERTY_VALUE_SEPARATOR));
			}
			propValues = propValues + SETREOTYPE_WITH_VALUE_SEPARATOR;
		}// end display each property

		return propValues;
	}

	/**
	 * return the string that represents the value of property when its type is an Enumeration
	 * 
	 * @param property
	 *            the property to display
	 * @param stereotype
	 *            the stereotype that contains the property
	 * @param umlElement
	 *            the umlelement on which the stereotype is applied
	 * @param EQUAL_SEPARATOR
	 *            the separator between property and property value
	 * @param PROPERTY_VALUE_SEPARATOR
	 *            the separator to end the exprestion
	 * @return String withe the following grammar propertyname EQUAL_SEPERATOR propertyValue PROPERTY_VALUE_SEPERATOR
	 */
	private static String getPropertyValueForEnumerationType(Property property, Stereotype stereotype, Element umlElement, final String EQUAL_SEPARATOR, final String PROPERTY_VALUE_SEPARATOR) {
		String out = "";
		if ((property.getUpper() == 1) && (umlElement.getValue(stereotype, property.getName()) != null)) {
			if ((property.getLower() != 0) || umlElement.getValue(stereotype, property.getName()) != null) {
				if (property.isSetDefault() || umlElement.getValue(stereotype, property.getName()) != null) {
					out = property.getName() + EQUAL_SEPARATOR + ((EnumerationLiteral) umlElement.getValue(stereotype, property.getName())).getLabel() + PROPERTY_VALUE_SEPARATOR;
					;
				} else {
					out = property.getName() + PROPERTY_VALUE_SEPARATOR;
				}
			} else {
				out = property.getName() + PROPERTY_VALUE_SEPARATOR;
			}
		}

		// multiplicity is greater than one
		else {
			out = property.getName() + EQUAL_SEPARATOR + umlElement.getValue(stereotype, property.getName()) + PROPERTY_VALUE_SEPARATOR;
		}
		return out;
	}

	/**
	 * return the string that represents the value of property when its type is a Metaclass
	 * 
	 * @param property
	 *            the property to display
	 * @param stereotype
	 *            the stereotype that contains the property
	 * @param umlElement
	 *            the umlelement on which the stereotype is applied
	 * @param EQUAL_SEPARATOR
	 *            the separator between property and property value
	 * @param PROPERTY_VALUE_SEPARATOR
	 *            the separator to end the exprestion
	 * @return String withe the following grammar propertyname EQUAL_SEPERATOR propertyValue PROPERTY_VALUE_SEPERATOR
	 */
	private static String getPropertyValueForMetaclassType(Property property, Stereotype stereotype, Element umlElement, final String EQUAL_SEPARATOR, final String PROPERTY_VALUE_SEPARATOR) {
		String out = "";

		if ((property.getUpper() == 1) && (umlElement.getValue(stereotype, property.getName()) != null)) {
			out = property.getName() + EQUAL_SEPARATOR + ((NamedElement) (umlElement.getValue(stereotype, property.getName()))).getName() + PROPERTY_VALUE_SEPARATOR;
		}

		// multiplicity greater than one
		else if (property.getUpper() != 1) {
			List values = (List) umlElement.getValue(stereotype, property.getName());
			ArrayList elementNames = new ArrayList();
			if (values != null) {
				for (int count = 0; count < values.size(); count++) {
					elementNames.add(((NamedElement) values.get(count)).getName());
				}
			}
			out = property.getName() + EQUAL_SEPARATOR + elementNames + PROPERTY_VALUE_SEPARATOR;
		}

		// multiplicity = 1 and property value null
		else {
			out = property.getName() + EQUAL_SEPARATOR + umlElement.getValue(stereotype, property.getName()) + PROPERTY_VALUE_SEPARATOR;
		}
		return out;
	}

	/**
	 * return the string that represents the value of property when its type is a stereotype
	 * 
	 * @param property
	 *            the property to display
	 * @param stereotype
	 *            the stereotype that contains the property
	 * @param umlElement
	 *            the umlelement on which the stereotype is applied
	 * @param EQUAL_SEPARATOR
	 *            the separator between property and property value
	 * @param PROPERTY_VALUE_SEPARATOR
	 *            the separator to end the exprestion
	 * @return String withe the following grammar propertyname EQUAL_SEPERATOR propertyValue PROPERTY_VALUE_SEPERATOR
	 */
	private static String getPropertyValueForStereotypeType(Property property, Stereotype stereotype, Element umlElement, final String EQUAL_SEPARATOR, final String PROPERTY_VALUE_SEPARATOR) {
		String out = "";
		if ((property.getUpper() == 1) && (umlElement.getValue(stereotype, property.getName()) != null)) {

			// retrieve the base element from the stereotype application
			Object value = umlElement.getValue(stereotype, property.getName());
			Element baseElement = UMLUtil.getBaseElement((EObject) value);

			// display the base element's qualified name
			out = property.getName() + EQUAL_SEPARATOR + ((NamedElement) baseElement).getName() + PROPERTY_VALUE_SEPARATOR;
		}

		// multiplicity greater than one
		else if (property.getUpper() != 1) {
			// retrieve the base element from the stereotype application
			List values = (List) umlElement.getValue(stereotype, property.getName());
			ArrayList baseElements = new ArrayList();
			if (values != null) {
				for (int k = 0; k < values.size(); k++) {
					baseElements.add(((NamedElement) UMLUtil.getBaseElement((EObject) values.get(k))).getName());
				}
			}

			out = property.getName() + EQUAL_SEPARATOR + baseElements + PROPERTY_VALUE_SEPARATOR;
		}

		// multiplicity = 1 and property value null
		else {
			out = property.getName() + EQUAL_SEPARATOR + (umlElement.getValue(stereotype, property.getName())) + PROPERTY_VALUE_SEPARATOR;
		}
		return out;
	}

	/**
	 * return the string that represents the value of property
	 * 
	 * @param property
	 *            the property to display
	 * @param stereotype
	 *            the stereotype that contains the property
	 * @param umlElement
	 *            the umlelement on which the stereotype is applied
	 * @param EQUAL_SEPARATOR
	 *            the separator between property and property value
	 * @param PROPERTY_VALUE_SEPARATOR
	 *            the separator to end the exprestion
	 * @return String withe the following grammar propertyname EQUAL_SEPERATOR propertyValue PROPERTY_VALUE_SEPERATOR
	 */
	private static String getPropertyValue(Property property, Stereotype stereotype, Element umlElement, final String EQUAL_SEPARATOR, final String PROPERTY_VALUE_SEPARATOR) {
		String out = "";
		if ((property.getLower() != 0) || umlElement.getValue(stereotype, property.getName()) != null) {
			if (property.isSetDefault() || umlElement.getValue(stereotype, property.getName()) != null) {
				out = property.getName() + EQUAL_SEPARATOR + umlElement.getValue(stereotype, property.getName()) + PROPERTY_VALUE_SEPARATOR;
			} else {
				out = property.getName() + PROPERTY_VALUE_SEPARATOR;
			}
		} else {
			out = property.getName() + PROPERTY_VALUE_SEPARATOR;

		}
		return out;
	}

	/**
	 * Parse the stereotype image and select those that have an "shape" kind (EAnnotation).
	 * 
	 * @param stereotype
	 *            to parse
	 * 
	 * @return a EList of {@link Image}
	 */
	public static EList<Image> getShapes(Stereotype stereotype) {

		EList<Image> shapes = new BasicEList<Image>();

		Iterator<Image> it = stereotype.getIcons().iterator();
		while (it.hasNext()) {
			Image image = it.next();
			if ("shape".equals(ImageUtil.getKind(image))) {
				shapes.add(image);
			}
		}

		return shapes;
	}
}
