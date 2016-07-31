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

import java.util.Iterator;

import org.eclipse.papyrus.moka.fuml.Semantics.CommonBehaviors.Communications.EventOccurrence;

public class EntryPointPseudostateActivation extends ConnectionPointActivation {

	public boolean isExitable(TransitionActivation exitingTransition) {
		// An entry point can be exited as soon as every outgoing transition expect
		// the current "exitingTransition" have been traversed.
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
	public boolean canPropagateExecution(TransitionActivation enteringTransition, EventOccurrence eventOccurrence, RegionActivation leastCommonAncestor){
		// Static analysis is propagated to the parents. If the propagation is accepted, then all outgoing transitions
		// guards are evaluated. For the propagation to be accepted through this pseudo-state activation, all transitions
		// must be fireable and all of them must accept the propagation.
		boolean propagate = true;
		VertexActivation vertexActivation = this.getParentVertexActivation();
		if(vertexActivation != null){
			propagate = vertexActivation.canPropagateExecution(enteringTransition, eventOccurrence, leastCommonAncestor);
		}
		if(propagate && this.isEnterable(enteringTransition)){
			this.evaluateAllGuards(eventOccurrence);
			if(this.outgoingTransitionActivations.size() == this.fireableTransitions.size()){
				int i = 0;
				while(propagate && i < this.fireableTransitions.size()){
					propagate = this.fireableTransitions.get(i).canPropagateExecution(eventOccurrence);
					i++;
				}	
			}else{
				propagate = false;
			}
		}
		return propagate;
	}
	
	protected void _enter(TransitionActivation enteringTransition, EventOccurrence eventOccurrence, RegionActivation leastCommonAncestor){
		// When an exit point is entered its owning state is entered. That state maybe located
		// in a nested hierarchy which is not already active. Hence if the hierarchy is not
		// already active then it is entered.
		super.enter(enteringTransition, eventOccurrence, null);
		VertexActivation vertexActivation = this.getParentVertexActivation();
		if(vertexActivation!=null){
			vertexActivation.enter(enteringTransition, eventOccurrence, leastCommonAncestor);
		}
	}
	
	public void enter(TransitionActivation enteringTransition, EventOccurrence eventOccurrence, RegionActivation leastCommonAncestor) {
		// Enter a state through an entry point. The state on which the entry point is
		// placed can be a deeply nested state. Therefore parent state of that state must
		// be entered before if it is not already the case.
		this._enter(enteringTransition, eventOccurrence, leastCommonAncestor);
		// Fire all transitions originating from the entry point. These transitions
		// are fired under the condition that the guard is true. 
		for(Iterator<TransitionActivation> fireableTransitionsIterator = this.fireableTransitions.iterator(); fireableTransitionsIterator.hasNext();){
			fireableTransitionsIterator.next().fire(eventOccurrence);
		}
	}
	
	@Override
	public void exit(TransitionActivation exitingTransition, EventOccurrence eventOccurrence, RegionActivation leastCommonAncestor) {
		// When the entry point is exited it does not imply exiting parent state.
		// Only the entry point is exited and this has no effect on its parent state.
		super.exit(exitingTransition, eventOccurrence, null);
	}
}
