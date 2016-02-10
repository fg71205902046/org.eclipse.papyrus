/*****************************************************************************
 * Copyright (c) 2014 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.emf.types.internal.ui.advice;

import org.eclipse.gmf.runtime.emf.type.core.edithelper.IEditHelperAdvice;
import org.eclipse.papyrus.infra.emf.types.ui.advices.values.RuntimeValuesAdviceConfiguration;
import org.eclipse.papyrus.infra.types.AbstractAdviceBindingConfiguration;
import org.eclipse.papyrus.infra.types.core.factories.impl.AbstractAdviceBindingFactory;
import org.eclipse.papyrus.infra.types.core.impl.NullEditHelperAdvice;

public class RuntimeValuesAdviceEditHelperAdviceFactory extends AbstractAdviceBindingFactory<AbstractAdviceBindingConfiguration> {

	@Override
	protected IEditHelperAdvice getEditHelperAdvice(AbstractAdviceBindingConfiguration adviceConfiguration) {
		if (adviceConfiguration instanceof RuntimeValuesAdviceConfiguration) {
			IEditHelperAdvice editHelperAdvice = new RuntimeValuesAdviceEditHelperAdvice((RuntimeValuesAdviceConfiguration) adviceConfiguration);
			return editHelperAdvice;
		}
		return NullEditHelperAdvice.getInstance();
	}
}
