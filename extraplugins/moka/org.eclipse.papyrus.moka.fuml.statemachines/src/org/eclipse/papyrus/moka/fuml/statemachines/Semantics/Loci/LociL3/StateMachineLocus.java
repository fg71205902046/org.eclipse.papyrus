/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Jeremie Tatibouet (CEA LIST)
 *
 *****************************************************************************/
package org.eclipse.papyrus.moka.fuml.statemachines.Semantics.Loci.LociL3;

import org.eclipse.papyrus.moka.composites.Semantics.Loci.LociL3.CS_Locus;
import org.eclipse.papyrus.moka.fuml.Semantics.Classes.Kernel.Object_;
import org.eclipse.papyrus.moka.fuml.statemachines.Semantics.Classes.Kernel.StateMachineObject;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;

public class StateMachineLocus extends CS_Locus {
	
	public Object_ instantiate(Class type) {
		Object_ object = null;
		if (type instanceof Behavior) {
			object = super.instantiate(type);
		} else {
			object = new StateMachineObject();
			object.types.add(type);
			object.createFeatureValues();
			this.add(object);
		}
		return object;
	}

	
}
