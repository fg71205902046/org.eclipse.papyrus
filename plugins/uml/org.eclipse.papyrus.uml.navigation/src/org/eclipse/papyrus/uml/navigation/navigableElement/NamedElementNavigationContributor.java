/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *  Shuai Li (CEA LIST) shuai.li@cea.fr - Creation
 *****************************************************************************/
package org.eclipse.papyrus.uml.navigation.navigableElement;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.papyrus.infra.services.navigation.service.NavigableElement;
import org.eclipse.papyrus.infra.services.navigation.service.NavigationContributor;
import org.eclipse.papyrus.uml.tools.utils.UMLUtil;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * NavigationContributor to navigate from TypedElement to their Type
 *
 * @author Shuai Li
 *
 */
public class NamedElementNavigationContributor implements NavigationContributor {

	public List<NavigableElement> getNavigableElements(Object fromElement) {
		List<NavigableElement> result = new LinkedList<NavigableElement>();

		Element element = UMLUtil.resolveUMLElement(fromElement);
		if (element instanceof NamedElement) {
			result.add(new NamedNavigableElement(((NamedElement) element)));
		}

		return result;
	}
}