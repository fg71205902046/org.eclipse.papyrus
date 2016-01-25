/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
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

package org.eclipse.papyrus.uml.diagram.composite.custom.edit.parts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper;
import org.eclipse.papyrus.uml.diagram.common.Activator;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AffixedNodeAlignmentEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.PortResizableEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.helper.PreferenceInitializerForElementHelper;
import org.eclipse.papyrus.uml.diagram.composite.edit.parts.PortEditPart;


/**
 * This class is used for 2 purposes. 
 * 1. Install new ResizablePolicy for port
 * 2. Override Affixed_child_alignment_role policy for resize commands
 * @author Trung-Truc Nguyen
 *
 */
public class CustomFullPortAffixedEditPart extends PortEditPart{

	/**
	 * Constructor.
	 *
	 * @param view
	 */
	public CustomFullPortAffixedEditPart(View view) {
		super(view);
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new PortResizableEditPolicy());
		installEditPolicy(AffixedNodeAlignmentEditPolicy.AFFIXED_CHILD_ALIGNMENT_ROLE, new PortResizableEditPolicy());
	}

	public void installEditPolicy(Object key, EditPolicy editPolicy){
		if(EditPolicy.PRIMARY_DRAG_ROLE.equals(key)) {
			//prevent its parents from overriding this policy 
			if(editPolicy instanceof PortResizableEditPolicy)
				super.installEditPolicy(key, editPolicy);
		} 
		else 
			super.installEditPolicy(key, editPolicy);
	}
	
	/**
	 * this override method serves to resize the DefaultSizeNodeFigure each time the diagram is opened.
	 * without this, the DefaultNodeFigure size is 20x20 by default although the size of port figure is different.
	 */
	protected NodeFigure createNodePlate() {
		String prefElementId = getNotationView().getType();
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String preferenceConstantWitdh = PreferenceInitializerForElementHelper.getpreferenceKey(getNotationView(), prefElementId, PreferencesConstantsHelper.WIDTH);
		String preferenceConstantHeight = PreferenceInitializerForElementHelper.getpreferenceKey(getNotationView(), prefElementId, PreferencesConstantsHelper.HEIGHT);
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(store.getInt(preferenceConstantWitdh), store.getInt(preferenceConstantHeight));
		
		int width = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
		int height = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();
		
//		Dimension size = new Dimension(width, height);
		// FIXME: workaround for #154536

		int w = width  > 20 ? width  : 20;
		int h = height > 20 ? height : 20;
		
		result.getBounds().setSize(w, h);
		result.setDefaultSize(w, h);
		
		
		return result;
	}
}
