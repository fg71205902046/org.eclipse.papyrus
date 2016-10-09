package org.eclipse.papyrus.moka.fuml.statemachines.Semantics.Actions.IntermediateActions;

import org.eclipse.papyrus.moka.composites.Semantics.Actions.IntermediateActions.CS_ReadSelfActionActivation;
import org.eclipse.papyrus.moka.fuml.Semantics.Classes.Kernel.Object_;
import org.eclipse.papyrus.moka.fuml.statemachines.Semantics.StateMachines.DoActivityContextObject;

public class SM_ReadSelfAction extends CS_ReadSelfActionActivation{
	
	@Override
	public Object_ getExecutionContext() {
		// The context object can be a DoActivityContextObject. A DoActivityContextObject
		// is the execution context of a doActivity behavior executed on its own thread of
		// execution. It references the context object from which the doActivity was invoked.
		// This context may contains features (e.g. operations, properties) that may be manipulated
		// by the doActivity. To enable this, in this situation the ReadSelfAction must resolve
		// to the context of the DoActivityContextObject.
		Object_ context = super.getExecutionContext();
		if(context instanceof DoActivityContextObject){
			context = ((DoActivityContextObject)context).context;
		}
		return context;
	}
}
