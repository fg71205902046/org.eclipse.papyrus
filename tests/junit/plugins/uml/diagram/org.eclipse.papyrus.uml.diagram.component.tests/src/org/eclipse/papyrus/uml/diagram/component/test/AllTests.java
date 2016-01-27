/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Nizar GUEDIDI (CEA LIST) - Initial API and implementation
 /*****************************************************************************/
package org.eclipse.papyrus.uml.diagram.component.test;

import org.eclipse.papyrus.uml.diagram.component.test.canonical.AllCanonicalTests;
import org.eclipse.papyrus.uml.diagram.component.test.dnd.AllDropTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All tests together.
 */
@RunWith(Suite.class)
@SuiteClasses({
		// canonical
		AllCanonicalTests.class,
		AllDropTests.class

		// load
		// LoadTests.class
})
public class AllTests {

}
