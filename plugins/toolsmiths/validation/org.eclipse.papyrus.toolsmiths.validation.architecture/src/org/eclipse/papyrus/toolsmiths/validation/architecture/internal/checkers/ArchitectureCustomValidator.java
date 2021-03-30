/*****************************************************************************
 * Copyright (c) 2021 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.architecture.internal.checkers;

import static org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.ProjectManagementUtils.getExtensionElements;
import static org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.ProjectManagementUtils.hasAttribute;
import static org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.ProjectManagementUtils.resourcePathIs;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.papyrus.infra.core.architecture.ArchitectureContext;
import org.eclipse.papyrus.infra.core.architecture.ArchitecturePackage;
import org.eclipse.papyrus.infra.core.architecture.ArchitectureViewpoint;
import org.eclipse.papyrus.infra.core.architecture.RepresentationKind;
import org.eclipse.papyrus.infra.types.ElementTypeSetConfiguration;
import org.eclipse.papyrus.infra.types.core.extensionpoints.IElementTypeSetExtensionPoint;
import org.eclipse.papyrus.toolsmiths.validation.architecture.internal.messages.Messages;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CustomModelChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.ArchitectureIndex;
import org.eclipse.pde.core.plugin.IPluginElement;

/**
 * Custom validation rules for <em>Architecture Domain</em> models.
 */
public class ArchitectureCustomValidator extends CustomModelChecker.SwitchValidator {

	private static final String ELEM_ELEMENT_TYPE_SET = "elementTypeSet"; //$NON-NLS-1$

	public ArchitectureCustomValidator(String nsURI) {
		super(nsURI);
	}

	public void validate(RepresentationKind representation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		validateRepresentationKindUsed(representation, diagnostics, context);
	}

	public void validate(ArchitectureContext architecture, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (architecture.getId() != null) { // Missing ID is reported separately
			architecture.getElementTypes().forEach(typeSet -> validateElementTypesContextID(architecture, typeSet, diagnostics, context));
		}
	}

	private void validateElementTypesContextID(ArchitectureContext architecture, ElementTypeSetConfiguration typeSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		Collection<IPluginElement> registrations = getExtensionElements(IElementTypeSetExtensionPoint.EXTENSION_POINT_ID, ELEM_ELEMENT_TYPE_SET)
				.filter(resourcePathIs(IElementTypeSetExtensionPoint.PATH, typeSet.eResource().getURI()))
				.collect(Collectors.toList());

		if (!registrations.isEmpty() && registrations.stream().noneMatch(hasAttribute(IElementTypeSetExtensionPoint.CLIENT_CONTEXT_ID, architecture.getId()::equals))) {
			diagnostics.add(createDiagnostic(Diagnostic.WARNING, architecture, format(Messages.ArchitectureCustomValidator_2, context, typeSet)));
		}
	}

	private void validateRepresentationKindUsed(RepresentationKind representation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!findArchitectureContextReference(representation)) {
			diagnostics.add(createDiagnostic(Diagnostic.WARNING, representation, format("No viewpoint includes ''{0}'', so it is not accessible.", context, representation)));
		}
	}

	/**
	 * Search the registered architecture context models to find any {@link ArchitectureViewpoint viewpoint} that
	 * references the given {@code representation}.
	 *
	 * @param representation
	 *            a representation kind
	 * @return whether any viewpoint references it
	 */
	protected boolean findArchitectureContextReference(RepresentationKind representation) {
		// The simplest case is a reference within the same architecture model
		boolean result = !requireCrossReferenceAdapter(representation)
				.getInverseReferences(representation, ArchitecturePackage.Literals.ARCHITECTURE_VIEWPOINT__REPRESENTATION_KINDS, false).isEmpty();

		if (!result) {
			// Look for references from other registered architecture models (including from the workspace)
			result = ArchitectureIndex.getInstance().isReferenced(representation, ArchitecturePackage.Literals.ARCHITECTURE_VIEWPOINT__REPRESENTATION_KINDS);
		}

		return result;
	}

	private ECrossReferenceAdapter requireCrossReferenceAdapter(EObject object) {
		ECrossReferenceAdapter result = ECrossReferenceAdapter.getCrossReferenceAdapter(object);

		if (result == null) {
			result = new ECrossReferenceAdapter();
			Resource resource = object.eResource();
			ResourceSet rset = (resource != null) ? resource.getResourceSet() : null;
			if (rset != null) {
				rset.eAdapters().add(result);
			} else if (resource != null) {
				resource.eAdapters().add(result);
			} else {
				object.eAdapters().add(result);
			}
		}

		return result;
	}
}
