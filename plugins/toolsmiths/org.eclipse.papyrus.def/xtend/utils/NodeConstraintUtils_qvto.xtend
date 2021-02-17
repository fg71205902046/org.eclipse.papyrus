/*****************************************************************************
 * Copyright (c) 2010 CEA LIST
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Yann Tanguy (CEA LIST) - initial API and implementation
 * 
 *****************************************************************************/
package utils

import com.google.inject.Singleton
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenNode
import org.eclipse.papyrus.gmf.codegen.gmfgen.TypeModelFacet
import org.eclipse.papyrus.gmf.codegen.genextension.GenNodeConstraint

@Singleton class NodeConstraintUtils_qvto {

	def boolean hasNodeCreationConstraint(GenNode it) {
		return it.eResource.allContents.filter(typeof(GenNodeConstraint)).filter[v|
			v.genNode.contains(it) && v.genConstraint != null].size != 0
	}

	def GenNodeConstraint getNodeCreationConstraint(GenNode it) {

		if (hasNodeCreationConstraint(it)) {
			return it.eResource.allContents.filter(typeof(GenNodeConstraint)).filter[v|
				v.genNode.contains(it) && v.genConstraint != null].head
		} else {
			return null;
		}
	}

	def GenNode getOwningGenNode(TypeModelFacet it) {

		return it.eResource.allContents.filter(typeof(GenNode)).filter[v|v.modelFacet == it].head
	}

	def String getNodeCreationConstraintBody(GenNode it) {
		var nodeConstraint = getNodeCreationConstraint(it);

		if (hasNodeCreationConstraint(it)) {
			if ((nodeConstraint.genConstraint != null) && (nodeConstraint.genConstraint.body != null)) {
				return nodeConstraint.genConstraint.body;
			}
		}
		return "No GenNodeConstraint found.";
	}

}
