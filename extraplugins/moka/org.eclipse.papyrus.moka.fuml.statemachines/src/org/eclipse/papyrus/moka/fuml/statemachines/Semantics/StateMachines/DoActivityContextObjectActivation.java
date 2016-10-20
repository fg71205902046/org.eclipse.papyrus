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
package org.eclipse.papyrus.moka.fuml.statemachines.Semantics.StateMachines;

import java.util.List;

import org.eclipse.papyrus.moka.fuml.Semantics.CommonBehaviors.BasicBehaviors.Execution;
import org.eclipse.papyrus.moka.fuml.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.eclipse.papyrus.moka.fuml.Semantics.CommonBehaviors.Communications.ArrivalSignal;
import org.eclipse.papyrus.moka.fuml.Semantics.CommonBehaviors.Communications.ClassifierBehaviorInvocationEventAccepter;
import org.eclipse.papyrus.moka.fuml.Semantics.CommonBehaviors.Communications.InvocationEventOccurrence;
import org.eclipse.papyrus.moka.fuml.Semantics.CommonBehaviors.Communications.ObjectActivation;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;

public class DoActivityContextObjectActivation extends ObjectActivation {

	@Override
	public void dispatchNextEvent() {
		// The dispatching behaves exactly the same at it was specified in fUML.
		// In addition to this behavior the dispatch sequence of an object activation
		// for a DoActivityContextObject has the capacity to notify the state having
		// triggered if the executed doActivity has completed. The completion of a
		// do activity is determined based on the absence of any registered event
		// accepter after the current step.
		super.dispatchNextEvent();
		if(this.waitingEventAccepters.size() == 0){
			DoActivityContextObject doActivityObject = (DoActivityContextObject)this.object;
			if(doActivityObject.owner!=null){
				doActivityObject.owner.isDoActivityCompleted = true;
				if(doActivityObject.owner.hasCompleted()){
					doActivityObject.owner.notifyCompletion();
				}
			}
		}
	}
	
	@Override
	public void startBehavior(Class classifier, List<ParameterValue> inputs) {
		// The expected classifier is the doActivity behavior. The doActivity
		// behavior is executed as if it was the classifier of a class typing
		// the doActivity context object. It only exists one doActivity executed
		// as a classifier behavior for a DoActivityContextObject.
		if(classifier != null 
			&& classifier instanceof Behavior
			&& this.classifierBehaviorInvocations.isEmpty()){
			ClassifierBehaviorInvocationEventAccepter newInvocation = new ClassifierBehaviorInvocationEventAccepter();
			newInvocation.objectActivation = this;
			newInvocation.classifier = classifier;
			Execution doActivityExecution = this.object.locus.factory.createExecution((Behavior)classifier, this.object); 
			if(inputs != null){
				for(int i = 0; i < inputs.size(); i++){
					doActivityExecution.setParameterValue(inputs.get(i));
				}
			}
			newInvocation.execution = doActivityExecution;
			this.classifierBehaviorInvocations.add(newInvocation);
			this.register(newInvocation);
			InvocationEventOccurrence invocationEventOccurrence = new InvocationEventOccurrence();
			invocationEventOccurrence.execution = newInvocation.execution;
			this.eventPool.add(invocationEventOccurrence);
			this._send(new ArrivalSignal());
		}
	}
}
