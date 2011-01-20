/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Tatiana Fesenko (CEA LIST) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.compare.diff.extension;

import java.util.Iterator;

import org.eclipse.emf.compare.diff.metamodel.AttributeChange;
import org.eclipse.emf.compare.diff.metamodel.AttributeChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.AttributeChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffFactory;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.ReferenceChange;
import org.eclipse.emf.compare.diff.metamodel.UpdateAttribute;
import org.eclipse.emf.compare.diff.metamodel.impl.ModelElementChangeLeftTargetImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.compare.diff.metamodel.uml_diff_extension.TaggedValueChange;
import org.eclipse.papyrus.compare.diff.metamodel.uml_diff_extension.TaggedValueChangeLeftTarget;
import org.eclipse.papyrus.compare.diff.metamodel.uml_diff_extension.TaggedValueChangeRightTarget;
import org.eclipse.papyrus.compare.diff.metamodel.uml_diff_extension.TaggedValueReferenceChange;
import org.eclipse.papyrus.compare.diff.metamodel.uml_diff_extension.UMLDiffFactory;
import org.eclipse.papyrus.compare.diff.metamodel.uml_diff_extension.UpdateTaggedValue;
import org.eclipse.papyrus.compare.diff.metamodel.uml_diff_extension.impl.UMLDiffExtensionImpl;
import org.eclipse.uml2.uml.util.UMLUtil;


public class ChangeTaggedValueExtension extends UMLDiffExtensionImpl {

	@Override
	public void visit(DiffModel diffModel) {
		final Iterator<EObject> it = diffModel.eAllContents();
		while(it.hasNext()) {
			final DiffElement element = (DiffElement)it.next();
			if(element instanceof AttributeChange && (false == element instanceof TaggedValueChange)) {
				AttributeChange attributeChange = (AttributeChange)element;
				EObject stereotypeApplication = null;
				if(isStereotypeApplication(attributeChange.getLeftElement())) {
					stereotypeApplication = attributeChange.getLeftElement();
				} else if(isStereotypeApplication(attributeChange.getRightElement())) {
					stereotypeApplication = attributeChange.getRightElement();
				}
				if(stereotypeApplication != null) {
					getHideElements().add(element);
					DiffElement group = findOrCreateDiffElementFor(diffModel, UMLUtil.getBaseElement(stereotypeApplication));
					TaggedValueChange newElement = buildTaggedValueDiff(attributeChange);
					group.getSubDiffElements().add(newElement);
				}
			} else if(element instanceof ReferenceChange && (false == element instanceof TaggedValueReferenceChange)) {
				ReferenceChange referenceChange = (ReferenceChange)element;
				EObject stereotypeApplication = null;
				if(isStereotypeApplication(referenceChange.getLeftElement())) {
					stereotypeApplication = referenceChange.getLeftElement();
				} else if(isStereotypeApplication(referenceChange.getRightElement())) {
					stereotypeApplication = referenceChange.getRightElement();
				}
				if(stereotypeApplication != null) {
					getHideElements().add(element);
					DiffElement group = findOrCreateDiffElementFor(diffModel, UMLUtil.getBaseElement(stereotypeApplication));
					TaggedValueReferenceChange newElement = buildTaggedValueReferenceDiff(referenceChange);
					group.getSubDiffElements().add(newElement);
				}
			}
		}
	}

	protected TaggedValueChange buildTaggedValueDiff(AttributeChangeLeftTarget attributeChange) {
		TaggedValueChangeLeftTarget newElement = UMLDiffFactory.eINSTANCE.createTaggedValueChangeLeftTarget();
		initTaggedValueDiff(newElement, attributeChange);
		newElement.setLeftTarget(attributeChange.getLeftTarget());
		return newElement;
	}

	protected TaggedValueChange buildTaggedValueDiff(AttributeChangeRightTarget attributeChange) {
		TaggedValueChangeRightTarget newElement = UMLDiffFactory.eINSTANCE.createTaggedValueChangeRightTarget();
		initTaggedValueDiff(newElement, attributeChange);
		newElement.setRightTarget(attributeChange.getRightTarget());
		return newElement;
	}


	protected TaggedValueChange buildTaggedValueDiff(UpdateAttribute attributeChange) {
		UpdateTaggedValue newElement = UMLDiffFactory.eINSTANCE.createUpdateTaggedValue();
		initTaggedValueDiff(newElement, attributeChange);
		return newElement;
	}

	protected TaggedValueChange buildTaggedValueDiff(AttributeChange attributeChange) {
		if(attributeChange instanceof UpdateAttribute) {
			return buildTaggedValueDiff((AttributeChangeRightTarget)attributeChange);
		} else if(attributeChange instanceof AttributeChangeLeftTarget) {
			return buildTaggedValueDiff((AttributeChangeLeftTarget)attributeChange);
		} else if(attributeChange instanceof AttributeChangeRightTarget) {
			return buildTaggedValueDiff((AttributeChangeRightTarget)attributeChange);
		}
		// make it not abstract
		TaggedValueChange newElement = UMLDiffFactory.eINSTANCE.createUpdateTaggedValue();
		initTaggedValueDiff(newElement, attributeChange);
		return newElement;
	}

	protected TaggedValueChange initTaggedValueDiff(TaggedValueChange newElement, AttributeChange attributeChange) {
		newElement.setLeftElement(attributeChange.getLeftElement());
		newElement.setRightElement(attributeChange.getRightElement());
		newElement.setAttribute(attributeChange.getAttribute());
		newElement.setRemote(attributeChange.isRemote());
		return newElement;
	}

	protected TaggedValueReferenceChange buildTaggedValueReferenceDiff(ReferenceChange referenceChange) {
		TaggedValueReferenceChange newElement = UMLDiffFactory.eINSTANCE.createTaggedValueReferenceChange();
		newElement.setLeftElement(referenceChange.getLeftElement());
		newElement.setRightElement(referenceChange.getRightElement());
		newElement.setReference(referenceChange.getReference());
		newElement.setRemote(referenceChange.isRemote());
		return newElement;
	}

	private DiffElement findOrCreateDiffElementFor(DiffModel root, EObject object) {
		if(object == null) {
			if(!root.getOwnedElements().isEmpty()) {
				return root.getOwnedElements().get(0);
			}
			DiffGroup diffRoot = DiffFactory.eINSTANCE.createDiffGroup();
			root.getOwnedElements().add(diffRoot);
			return diffRoot;
		}
		DiffElement element = findDiffElementFor(root, object);
		if(element != null) {
			return element;
		}

		DiffElement diffParent = findOrCreateDiffElementFor(root, getParent(object));

		DiffGroup diffElementForObject = DiffFactory.eINSTANCE.createDiffGroup();
		diffElementForObject.setRightParent(object);

		diffParent.getSubDiffElements().add(diffElementForObject);
		return diffElementForObject;
	}

	private EObject getParent(EObject modelElement) {
		return modelElement.eContainer();
	}

	private DiffElement findDiffElementFor(DiffModel root, EObject modelElement) {
		if(modelElement == null) {
			return null;
		}
		for(DiffElement diff : root.getOwnedElements()) {
			if(isPertinentDiff(diff, modelElement)) {
				return diff;
			}
			if(diff instanceof DiffGroup) {
				return getDiffElementFor((DiffGroup)diff, modelElement);
			}
		}
		return null;
	}

	private DiffElement getDiffElementFor(DiffGroup root, EObject modelElement) {
		for(DiffElement diff : root.getSubDiffElements()) {
			if(isPertinentDiff(diff, modelElement)) {
				return diff;
			}
			if(diff instanceof DiffGroup) {
				return getDiffElementFor((DiffGroup)diff, modelElement);
			}
		}
		return null;
	}

	private boolean isPertinentDiff(DiffElement diff, EObject modelElement) {
		boolean isPertinent = false;
		if(diff instanceof DiffGroup) {
			return modelElement.equals(((DiffGroup)diff).getRightParent());
		}
		if(diff instanceof ModelElementChangeLeftTargetImpl) {
			isPertinent = ((ModelElementChangeLeftTargetImpl)diff).getLeftElement().equals(modelElement);
		}
		return isPertinent;
	}



	private boolean isStereotypeApplication(EObject eObject) {
		return UMLUtil.getStereotype(eObject) != null;
	}


}
