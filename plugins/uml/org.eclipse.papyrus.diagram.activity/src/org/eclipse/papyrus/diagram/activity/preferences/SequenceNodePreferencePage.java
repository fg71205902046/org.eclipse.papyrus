/*****************************************************************************
 * Copyright (c) 2010 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.activity.preferences;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.diagram.activity.edit.parts.ActivityDiagramEditPart;
import org.eclipse.papyrus.diagram.activity.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.diagram.common.groups.preferences.OpacityFactoryHelper;
import org.eclipse.papyrus.preferences.pages.AbstractPapyrusNodePreferencePage;
import org.eclipse.papyrus.preferences.ui.DecorationGroup;
import org.eclipse.papyrus.preferences.ui.FontGroup;
import org.eclipse.papyrus.preferences.ui.LabelGroup;
import org.eclipse.papyrus.preferences.ui.NodeColorGroup;
import org.eclipse.papyrus.preferences.ui.NodeCompartmentGroup;
import org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper;
import org.eclipse.swt.widgets.Composite;

/**
 * @generated
 */
public class SequenceNodePreferencePage extends AbstractPapyrusNodePreferencePage {

	/**
	 * @generated
	 */
	public static final String compartments[] = { "StructuredActivityNodeContentCompartment" };

	/**
	 * @generated
	 */
	public SequenceNodePreferencePage() {
		super();
		setPreferenceKey(ActivityDiagramEditPart.MODEL_ID + "_SequenceNode");
	}

	/**
	 * @generated
	 */
	@Override
	protected String getBundleId() {
		return UMLDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	public static void initDefaults(IPreferenceStore store) {

		String key = ActivityDiagramEditPart.MODEL_ID + "_SequenceNode";
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.WIDTH), 40);
		store.setDefault(PreferenceConstantHelper.getElementConstant(key, PreferenceConstantHelper.HEIGHT), 40);

		Map<String, Boolean> map = getStaticCompartmentVisibilityPreferences();
		for(String name : map.keySet()) {
			String preferenceName = PreferenceConstantHelper.getLabelElementConstant(key, name, PreferenceConstantHelper.COMPARTMENT_VISIBILITY);
			store.setDefault(preferenceName, map.get(name));
		}

		map = getStaticCompartmentTitleVisibilityPreferences();
		for(String name : map.keySet()) {
			String preferenceName = PreferenceConstantHelper.getLabelElementConstant(key, name, PreferenceConstantHelper.COMPARTMENT_NAME_VISIBILITY);
			store.setDefault(preferenceName, map.get(name));
		}

		//org.eclipse.jface.preference.PreferenceConverter.setDefault(store, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.COLOR_FILL), new org.eclipse.swt.graphics.RGB(255, 255, 255));
		//org.eclipse.jface.preference.PreferenceConverter.setDefault(store, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.COLOR_LINE), new org.eclipse.swt.graphics.RGB(0, 0, 0));

		// Set the default for the gradient
		//store.setDefault(org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.GRADIENT_POLICY),false);
		//org.eclipse.papyrus.preferences.utils.GradientPreferenceConverter gradientPreferenceConverter = new  org.eclipse.papyrus.preferences.utils.GradientPreferenceConverter(
		//		new org.eclipse.swt.graphics.RGB(255, 255, 255),
		//		new org.eclipse.swt.graphics.RGB(0, 0, 0), 0, 0);
		//store.setDefault(org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.getElementConstant(elementName, org.eclipse.papyrus.preferences.utils.PreferenceConstantHelper.COLOR_GRADIENT), gradientPreferenceConverter.getPreferenceValue());

	}

	/**
	 * @generated
	 */
	@Override
	protected void initializeCompartmentsList() {
		for(String name : compartments) {
			this.compartmentsList.add(name);
		}
	}

	/**
	 * @generated
	 */
	private static TreeMap<String, Boolean> getStaticCompartmentVisibilityPreferences() {
		TreeMap<String, Boolean> map = new TreeMap<String, Boolean>();
		map.put("StructuredActivityNodeContentCompartment", Boolean.TRUE);
		return map;
	}

	/**
	 * @generated
	 */
	private static TreeMap<String, Boolean> getStaticCompartmentTitleVisibilityPreferences() {
		TreeMap<String, Boolean> map = new TreeMap<String, Boolean>();
		return map;
	}

	/**
	 * @generated
	 */
	protected TreeMap<String, Boolean> getCompartmentTitleVisibilityPreferences() {
		return getStaticCompartmentTitleVisibilityPreferences();
	}

	/**
	 * Add the SpecificKeywordStructuredActivityNodeVisibility preference Add
	 * the opacity group preference Remove the background color group
	 * 
	 * @see org.eclipse.papyrus.preferences.pages.AbstractPapyrusNodePreferencePage#createPageContents(org.eclipse.swt.widgets.Composite)
	 * 
	 * @param parent
	 * @generated NOT
	 */
	protected void createPageContents(Composite parent) {
		FontGroup fontGroupComposite = new FontGroup(parent, getPreferenceKey(), this);
		addAbstractGroup(fontGroupComposite);
		NodeColorGroup colorGroupForNodeComposite = new NodeColorGroup(parent, getPreferenceKey(), this);
		addAbstractGroup(colorGroupForNodeComposite);
		// Add opacity Group
		addAbstractGroup(OpacityFactoryHelper.getOpacityGroup(parent, getPreferenceKey(), this, IActivityPreferenceConstants.PREF_SEQUENCE_NODE_ALPHA));
		DecorationGroup decorationGroup = new DecorationGroup(parent, getPreferenceKey(), this);
		addAbstractGroup(decorationGroup);
		if(!compartmentsList.isEmpty()) {
			NodeCompartmentGroup compartmentGroup = new NodeCompartmentGroup(parent, getPreferenceKey(), this, compartmentsList, getCompartmentTitleVisibilityPreferences().keySet(), getPreferenceStore());
			addAbstractGroup(compartmentGroup);
		}

		// Label role group
		if(!getLabelRole().isEmpty()) {
			LabelGroup compartmentGroup = new LabelGroup(parent, getPreferenceKey(), this, getLabelRole());
			addAbstractGroup(compartmentGroup);
		}
		SpecificKeywordStructuredActivityNodeVisibility specificKeywordDisplay = new SpecificKeywordStructuredActivityNodeVisibility(parent, getTitle(), this, SpecificKeywordStructuredActivityNodeVisibility.ElementType.SEQUENCE_NODE);
		addAbstractGroup(specificKeywordDisplay);
	}
}
