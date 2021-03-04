/*****************************************************************************
* Copyright (c) 2006, 2020 Borland Software Corporation, CEA LIST, ARTAL
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License 2.0
* which accompanies this distribution, and is available at
* https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
 *   Etienne ALLOGO (ARTAL) - Initial API and implementation
 *   Etienne ALLOGO (ARTAL) - etienne.allogo@artal.fr - Bug 569174 : PapyrusGmfExtension epackage merge into gmfgen
*****************************************************************************/

package org.eclipse.papyrus.gmf.codegen.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.gmf.codegen.genextension.AdditionalEditPartCandies;
import org.eclipse.papyrus.gmf.codegen.genextension.CommentedElement;
import org.eclipse.papyrus.gmf.codegen.genextension.CustomDiagramUpdaterSingleton;
import org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingDeleteService;
import org.eclipse.papyrus.gmf.codegen.genextension.EditPartUsingReorientService;
import org.eclipse.papyrus.gmf.codegen.genextension.ExtendedGenView;
import org.eclipse.papyrus.gmf.codegen.genextension.GenExtensionPackage;
import org.eclipse.papyrus.gmf.codegen.genextension.GenerateUsingElementTypeCreationCommand;
import org.eclipse.papyrus.gmf.codegen.genextension.LabelVisibilityPreference;
import org.eclipse.papyrus.gmf.codegen.genextension.PapyrusExtensionRootNode;
import org.eclipse.papyrus.gmf.codegen.genextension.PropertyRefreshHook;
import org.eclipse.papyrus.gmf.codegen.genextension.SpecificDiagramUpdater;
import org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocator;
import org.eclipse.papyrus.gmf.codegen.genextension.SpecificLocatorExternalLabel;
import org.eclipse.papyrus.gmf.codegen.genextension.VisualIDOverride;
import org.eclipse.papyrus.gmf.codegen.genextension.util.GenExtensionSwitch;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GMFGenFactory;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenChildSideAffixedNode;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenCommonBase;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenContainerBase;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagram;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenDiagramUpdater;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenExternalNodeLabel;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenFloatingLabel;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenLinkLabel;
import org.eclipse.papyrus.gmf.codegen.gmfgen.GenNode;
import org.eclipse.papyrus.gmf.codegen.gmfgen.RefreshHook;

/**
 * Migrates the data formerly contained in the "PAPYRUSGMFEXTENSION" into the new single generation model (papyrus/gmfgen/2020)
 * <ul>
 * <li>Migration is based on a set of mappings (Addition of new attributes to the formerly extended class).</li>
 * <li>Once the gmfgen file is migrated, it is then possible to regenerate the code of the corresponding UML diagram.</li>
 * </ul>
 *
 *
 */
public class PapyrusGMFExtensionMigrator extends GenExtensionSwitch<Boolean> {


	/**
	 * 
	 * Migrates {@link AdditionalEditPartCandies} to {@link GenDiagram}.
	 * 
	 * @param object
	 * @return
	 */
	@Override
	public Boolean caseAdditionalEditPartCandies(AdditionalEditPartCandies object) {
		String baseEditHelperPackage = object.getBaseEditHelperPackage();
		if (baseEditHelperPackage != null && !baseEditHelperPackage.isBlank()) {
			GenDiagram genDiagram = resolveGenDiagram(object.eResource());
			genDiagram.setBaseEditHelperPackage(baseEditHelperPackage);
		}
		return true; // delete
	}


	/**
	 * 
	 * Migrates {@link CustomDiagramUpdaterSingleton} to {@link GenDiagramUpdater}.
	 * Resolve first from whole resource.
	 *
	 * @param object
	 * @return
	 */
	@Override
	public Boolean caseCustomDiagramUpdaterSingleton(CustomDiagramUpdaterSingleton object) {
		String path = object.getSingletonPath();
		if (path != null && !path.isBlank()) {
			TreeIterator<EObject> it = object.eResource().getAllContents();
			while (it.hasNext()) {
				EObject eObject = it.next();
				if (eObject instanceof GenDiagramUpdater) {
					((GenDiagramUpdater) eObject).setCustomDiagramUpdaterSingletonPath(path);
					break;
				}
			}
		}
		return true; // delete
	}

	/**
	 * Migrates {@link EditPartUsingDeleteService} to {@link GenCommonBase}.
	 *
	 * @param object
	 * @return
	 */
	@Override
	public Boolean caseEditPartUsingDeleteService(EditPartUsingDeleteService object) {
		object.getGenView().forEach(v -> v.setUsingDeleteService(true));
		return true; // delete
	}


	/**
	 * Migrates {@link EditPartUsingReorientService} to {@link GenCommonBase}.
	 *
	 * @param object
	 * @return
	 */
	@Override
	public Boolean caseEditPartUsingReorientService(EditPartUsingReorientService object) {
		object.getGenView().forEach(v -> v.setUsingDeleteService(true));
		return true; // delete
	}

	/**
	 * Migrates {@link ExtendedGenView} to {@link GenCommonBase}.
	 *
	 * @param extendedGenView
	 * @return
	 */
	@Override
	public Boolean caseExtendedGenView(ExtendedGenView extendedGenView) {
		EList<GenCommonBase> extendedElements = extendedGenView.getGenView();
		for (GenCommonBase extendedElement : extendedElements) {

			if (extendedElement.getSuperEditPart() == null && extendedGenView.getSuperOwnedEditPart() != null && !extendedGenView.getSuperOwnedEditPart().isBlank()) {
				// first oon null extension win (old behavior)
				// migrate extended attributes
				extendedElement.setSuperEditPart(extendedGenView.getSuperOwnedEditPart());
			}

			// migrate name property for GenExternalNodeLabel -> use by a template
			if (extendedElement instanceof GenExternalNodeLabel) {
				((GenExternalNodeLabel) extendedElement).setName(extendedGenView.getName());

			}

			if (extendedElement instanceof GenNode) {
				((GenNode) extendedElement).setSpecificNotificationEvent(true);
			}

		}

		// migrate PropertyRefreshHook
		PropertyRefreshHook refreshHook = extendedGenView.getPropRefreshHook();
		if (refreshHook != null) {
			migrate(refreshHook);
		}
		return true; // delete
	}


	/**
	 * Migrates {@link GenerateUsingElementTypeCreationCommand} to {@link GenDiagram}.
	 *
	 * @param object
	 * @return
	 */
	@Override
	public Boolean caseGenerateUsingElementTypeCreationCommand(GenerateUsingElementTypeCreationCommand object) {
		GenDiagram genDiagram = resolveGenDiagram(object.eResource());
		genDiagram.setUsingElementTypeCreationCommand(true);
		return true; // delete
	}

	/**
	 * Migrates {@link LabelVisibilityPreference} to {@link GenExternalNodeLabel} or {@link GenLinkLabel}.
	 *
	 * @param object
	 * @return
	 */
	@Override
	public Boolean caseLabelVisibilityPreference(LabelVisibilityPreference object) {
		object.getExternalNodeLabels().forEach(node -> {
			GenFloatingLabel preference = GMFGenFactory.eINSTANCE.createGenFloatingLabel();
			preference.setIconPathRole(object.getIconPathRole());
			preference.setRole(object.getRole());
			preference.setVisibleByDefault(object.isVisibleByDefault());
			node.setLabelVisibilityPreference(preference);
		});

		object.getLinkLabels().forEach(link -> {
			GenFloatingLabel preference = GMFGenFactory.eINSTANCE.createGenFloatingLabel();
			preference.setIconPathRole(object.getIconPathRole());
			preference.setRole(object.getRole());
			preference.setVisibleByDefault(object.isVisibleByDefault());
			link.setLabelVisibilityPreference(preference);
		});
		return true; // delete
	}

	/**
	 * Migrates all extension recursively starting by the root node.
	 *
	 * @param object
	 * @return
	 */
	@Override
	public Boolean casePapyrusExtensionRootNode(PapyrusExtensionRootNode object) {
		EList<CommentedElement> nodes = object.getExtensionNodes();
		for (Object commentedElement : nodes.toArray()) {
			migrate((EObject) commentedElement);
		}
		return object.eContents().isEmpty(); // will be deleted at the end of the whole migration process
	}


	/**
	 * Migrates {@link PropertyRefreshHook} to {@link GenNode}.
	 *
	 * @param propertyRefreshHook
	 * @return
	 */
	@Override
	public Boolean casePropertyRefreshHook(PropertyRefreshHook propertyRefreshHook) {

		final ExtendedGenView extendedView = (ExtendedGenView) propertyRefreshHook.eContainer();
		extendedView.getGenView().forEach(node -> {
			if (node instanceof GenNode) {
				GenNode extenedNode = (GenNode) node;
				
				RefreshHook refreshHook = GMFGenFactory.eINSTANCE.createRefreshHook();
				// @deprecated
				// extenedNode.setRefreshComment(refreshHook.getComment());
				refreshHook.setRefreshCondition(propertyRefreshHook.getTriggeringCondition());
				refreshHook.setRefreshAction(propertyRefreshHook.getAction());
				extenedNode.setRefreshHook(refreshHook);
			}
		});
		return true; // delete // remove
	}

	/**
	 * Migrates {@link SpecificDiagramUpdater} to {@link GenContainerBase}.
	 *
	 * @param object
	 * @return
	 */
	@Override
	public Boolean caseSpecificDiagramUpdater(SpecificDiagramUpdater object) {
		GenCommonBase node = object.getGenNode();
		if (node instanceof GenContainerBase) {
			((GenContainerBase) node).setSpecificDiagramUpdaterClassName(object.getClasspath());
		}
		return true; // delete
	}

	/**
	 * Migrates {@link SpecificLocator} to {@link GenChildSideAffixedNode}.
	 *
	 * @param object
	 * @return
	 */
	@Override
	public Boolean caseSpecificLocator(SpecificLocator object) {
		object.getGenChildSideAffixedNode().forEach(node -> {
			node.setLocatorClassName(object.getClasspath());
		});
		return true; // delete
	}

	/**
	 * Migrates {@link SpecificLocatorExternalLabel} to {@link GenExternalNodeLabel}.
	 *
	 * @param object
	 * @return
	 */
	@Override
	public Boolean caseSpecificLocatorExternalLabel(SpecificLocatorExternalLabel object) {
		object.getGenExternalNodeLabel().forEach(node -> {
			node.setLocatorClassName(object.getClasspath());
		});
		return true; // delete
	}

	
	/**
	 * Migrates {@link VisualIDOverride} to {@link GenCommonBase}.
	 *
	 * @param visualIDOverride
	 * @return
	 */
	@Override
	public Boolean caseVisualIDOverride(VisualIDOverride visualIDOverride) {
		EList<VisualIDOverride> childs = visualIDOverride.getChild();
		// move visual id
		String visualID = visualIDOverride.getVisualID();
		GenCommonBase base = visualIDOverride.getGenView();
		if (base != null && visualID != null && !visualID.isBlank()) {
			// FIXME allogo +> should replace int visual id but too complicated to manage unicity as is, to be solveed later
			base.setVisualIDOverride(visualID);
		}

		// migrate children
		Object[] childsCopy = childs.toArray();
		for (Object child : childsCopy) {
			migrate((EObject) child);
		}

		return true; // delete
	}

	/**
	 * Delete element from resource if from package {@link GenExtensionPackage}.
	 *
	 * @param object
	 * @return true means to be deleted
	 */

	@Override
	public Boolean defaultCase(EObject object) {
		// migrate gen extension (don't return null for other package => detetion if call to migrate retur true)
		return object != null && object.eClass().getEPackage().getNsURI().equals(GenExtensionPackage.eNS_URI);
	}

	/**
	 * Migrate the extension attributes to corresponding genObject.
	 *
	 * @param root
	 *            the root
	 */
	public void migrate(EObject root) {
		if (doSwitch(root)) {
			// delete migrated item
			EcoreUtil.remove(root);
		}
	}


	/**
	 * Resolve gen diagram in whole resource
	 *
	 * @param eResource
	 *            the emf resource
	 * @return the gen diagram
	 */
	private GenDiagram resolveGenDiagram(Resource eResource) {
		TreeIterator<EObject> it = eResource.getAllContents();
		while (it.hasNext()) {
			EObject eObject = it.next();
			if (eObject instanceof GenDiagram) {
				return (GenDiagram) eObject;
			}
		}
		return null;
	}

}
