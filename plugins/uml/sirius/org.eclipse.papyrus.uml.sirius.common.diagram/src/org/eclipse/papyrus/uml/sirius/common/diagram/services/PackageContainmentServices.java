/******************************************************************************
 * Copyright (c) 2021 CEA LIST, Artal Technologies
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Aurelien Didier (ARTAL) - aurelien.didier51@gmail.com - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.sirius.common.diagram.services;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.uml.sirius.common.diagram.core.services.AbstractDiagramServices;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * A set of services to handle the Package containment diagram.
 *
 */
public class PackageContainmentServices extends AbstractDiagramServices {
	/**
	 * Get all the packageable elements available in the semantic resources.
	 *
	 * @param element
	 *            Semantic element
	 * @return All the packageable elements
	 */
	public List<EObject> getAllPackageableElements(Element element) {
		final List<EObject> result = Lists.newArrayList();
		final List<Package> rootPkgs = getAllAvailableRootPackages(element);
		result.addAll(rootPkgs);
		for (final Package pkg : rootPkgs) {
			Iterators.addAll(result,
					Iterators.filter(pkg.eAllContents(), Predicates.instanceOf(PackageableElement.class)));
		}
		if (element instanceof Package) {
			result.removeAll(((Package)element).getPackagedElements());
		}

		return result;
	}

	public boolean isValidSelection(Element selection, Element container) {
		// To avoid circular references, check if selection is not an ancestor of container
		if (EcoreUtil.isAncestor(selection, container)) {
			String selName = "Selection";
			String contName = "container";

			if (selection instanceof NamedElement) {
				selName = ((NamedElement)selection).getName();
			}
			if (container instanceof NamedElement) {
				contName = ((NamedElement)container).getName();
			}

			MessageDialog.openError(Workbench.getInstance().getDisplay().getActiveShell(), "Selection Error",
					selName + " is an ancestor of " + contName + ", it could not be moved in " + contName
							+ ".");
			return false;
		}
		return true;
	}
}
