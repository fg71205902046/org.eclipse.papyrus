/*****************************************************************************
 * Copyright (c) 2015 Anatolyi Tischenko and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Anatolyi Tischenko - Initial API and implementation
 * 
 *****************************************************************************/
package aspects.xpt.diagram.commands

import org.eclipse.papyrus.gmf.codegen.gmfgen.LinkModelFacet
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLink
import xpt.Common
import com.google.inject.Inject
import com.google.inject.Singleton

@Singleton class ReorientLinkUtils extends xpt.diagram.commands.ReorientLinkUtils {
	
	@Inject extension Common
	
	override canReorient(LinkModelFacet it, GenLink link) '''
		«generatedMemberComment()»
		public boolean canExecute() {
			«checkLinkValidity(it)»
			if (reorientDirection == org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRequest.REORIENT_SOURCE) {
				return canReorientSource();
			}
			if (reorientDirection == org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRequest.REORIENT_TARGET) {
				return canReorientTarget();
			}
			return false;
		}
		
		«generatedMemberComment()»
		protected boolean canReorientSource() {
			«checkSourceRequestValidity(it, link)»
		}
		
		«generatedMemberComment()»
		protected boolean canReorientTarget() {
			«checkTargetRequestValidity(it, link)»
		}
	'''
	
	override reorient(LinkModelFacet it) '''
		
		«generatedMemberComment()»
		protected org.eclipse.gmf.runtime.common.core.command.CommandResult doExecuteWithResult(
				org.eclipse.core.runtime.IProgressMonitor monitor, org.eclipse.core.runtime.IAdaptable info)
				throws org.eclipse.core.commands.ExecutionException {
			if (!canExecute()) {
			throw new org.eclipse.core.commands.ExecutionException("Invalid arguments in reorient link command"); «nonNLS()»
			}
			if (reorientDirection == org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRequest.REORIENT_SOURCE) {
			return reorientSource();
			}
			if (reorientDirection == org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRequest.REORIENT_TARGET) {
			return reorientTarget();
			}
			throw new IllegalStateException();
		}
		
		«generatedMemberComment()»
		protected org.eclipse.gmf.runtime.common.core.command.CommandResult reorientSource() throws org.eclipse.core.commands.ExecutionException {
			«reorientSource(it)»
		}
		
		«generatedMemberComment()»
		protected org.eclipse.gmf.runtime.common.core.command.CommandResult reorientTarget() throws org.eclipse.core.commands.ExecutionException {
			«reorientTarget(it)»
		}
	'''
}