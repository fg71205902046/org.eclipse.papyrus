/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.moka.fuml.Semantics.CommonBehaviors.Communications;

import org.eclipse.papyrus.moka.fuml.Semantics.Classes.Kernel.Value;
import org.eclipse.papyrus.moka.fuml.Semantics.CommonBehaviors.BasicBehaviors.Execution;

public class EventOccurrenceSendingExecution extends Execution{

	// Event occurrence that is required to be sent
	public EventOccurrence self;
	
	@Override
	public void execute() {
		// Send the event occurrence to the etarget object
		this.self.doSend();
	}

	@Override
	public Value new_() {
		EventOccurrenceSendingExecution sendingExecution = new EventOccurrenceSendingExecution();
		sendingExecution.self = this.self;
		return sendingExecution;
	}

	@Override
	public String toString() {
		return "EventOccurrenceSendingExecution("+this.self+")";
	}
}
