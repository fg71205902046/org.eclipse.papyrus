/*****************************************************************************
 * Copyright (c) 2015 CEA LIST.
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
package org.eclipse.papyrus.moka.fuml.statemachines.Semantics.StateMachines;

import org.eclipse.papyrus.moka.fuml.Semantics.CommonBehaviors.Communications.EventOccurrence;

public class ForkPseudostateActivation extends PseudostateActivation {

	@Override
	public boolean isExitable(TransitionActivation exitingTransition) {
		// The Fork node activation can only be exited when all of its outgoing transitions
		// (expect the current "exitingTransition")previously fired.
		int i = 0;
		boolean isExitable = true;
		while(isExitable && i < this.outgoingTransitionActivations.size()){
			TransitionActivation transitionActivation = this.outgoingTransitionActivations.get(i);
			if(transitionActivation != exitingTransition){
				isExitable = transitionActivation.isTraversed();
			}
			i++;
		}
		return isExitable;
	}
	
	@Override
	public void enter(TransitionActivation enteringTransition, EventOccurrence eventOccurrence, RegionActivation leastCommonAncestor) {
		// Fires all outgoing transitions of the for **concurrently**
		// Transitions outgoing from a fork are not guarded nor triggered
		// If required parent state is entered first (the rule applies recursively)
		super.enter(enteringTransition, eventOccurrence, leastCommonAncestor);
		for(int i=0; i < this.outgoingTransitionActivations.size(); i++){
			this.outgoingTransitionActivations.get(i).fire(eventOccurrence);
		}
	}
	
}
