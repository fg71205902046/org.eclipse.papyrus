/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) Vincent.Lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.compare.diff.tests.standalone.options;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.papyrus.infra.core.resource.ModelMultiException;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.junit.BeforeClass;


public class AttributeChangeRightTargetMergeOptionsEnablementTest_1_LeftToRight extends AbstractMergeOptionsEnablementTests {

	private static final String MODEL_PATH = "attributeChangeRightTarget_1/"; //$NON-NLS-1$

	@BeforeClass
	public static void init() throws CoreException, IOException, ModelMultiException, ServiceException {
		AbstractMergeOptionsEnablementTests.init_standalone(MODEL_PATH, true);
	}


}
