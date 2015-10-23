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
 *  Ansgar Radermacher (CEA LIST) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.services.tracebreakpoints.ui.preferences;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.papyrus.infra.services.tracebreakpoints.ITraceMechanism;
import org.eclipse.papyrus.infra.services.tracebreakpoints.Messages;
import org.eclipse.papyrus.infra.services.tracebreakpoints.TraceActionEnums.TAClass;
import org.eclipse.papyrus.infra.services.tracebreakpoints.TraceActionEnums.TAOperation;
import org.eclipse.papyrus.infra.services.tracebreakpoints.TraceActionEnums.TAState;
import org.eclipse.papyrus.infra.services.tracebreakpoints.ui.Activator;
import org.eclipse.papyrus.infra.services.tracebreakpoints.ui.TraceActions;
import org.eclipse.papyrus.infra.services.tracebreakpoints.ui.TraceMechanism;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * This class represents the TracePoint preference page
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that belongs to the main plug-in class. That way, preferences can be accessed directly via the preference store.
 */

public class TPPreferencePage
		extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	public TPPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(Messages.TPPreferencePage_TraceOptions);
	}

	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	@Override
	public void createFieldEditors() {
		EList<ITraceMechanism> mechanisms = TraceMechanism.getTraceMechanisms();
		int elements = 0;
		for (ITraceMechanism mechanism : mechanisms) {
			// TODO: function need to support null object
			EList<String> mechanismIDs = mechanism.getTraceMechanismIDs(null);
			elements += mechanismIDs.size();
		}
		String[][] mechList = new String[elements][2];
		elements = 0;
		for (ITraceMechanism mechanism : mechanisms) {
			// TODO: function need to support null object
			EList<String> mechanismIDs = mechanism.getTraceMechanismIDs(null);
			for (String id : mechanismIDs) {
				mechList[elements][1] = id;
				mechList[elements][0] = mechanism.getTraceMechanismDescription(null, id);
				elements++;
			}
		}

		String[][] taClassOptions = TraceActions.getStringFields(TAClass.values());
		String[][] taStateOptions = TraceActions.getStringFields(TAState.values());
		String[][] taOperationOptions = TraceActions.getStringFields(TAOperation.values());

		addField(new BinaryEncodedMChoiceFieldEditor(TPPreferenceConstants.P_TRACE_OPTION_CLASS, Messages.TPPreferencePage_ClassOptions, 3, taClassOptions, getFieldEditorParent(), true));

		addField(new BinaryEncodedMChoiceFieldEditor(TPPreferenceConstants.P_TRACE_OPTION_STATE, Messages.TPPreferencePage_StateOptions, 3, taStateOptions, getFieldEditorParent(), true));

		addField(new RadioGroupFieldEditor(
				TPPreferenceConstants.P_TRACE_OPTION_OP,
				Messages.TPPreferencePage_OperationOptions, 3, taOperationOptions, getFieldEditorParent(), true));

		addField(new ComboFieldEditor(
				TPPreferenceConstants.P_TRACE_IMPLEMENTATION_PORT,
				Messages.TPPreferencePage_TMforPorts, mechList, getFieldEditorParent()));

		addField(new ComboFieldEditor(
				TPPreferenceConstants.P_TRACE_IMPLEMENTATION_OP,
				Messages.TPPreferencePage_TMforOperations, mechList, getFieldEditorParent()));

		addField(new ComboFieldEditor(
				TPPreferenceConstants.P_TRACE_IMPLEMENTATION_SM,
				Messages.TPPreferencePage_TMforStateMachines, mechList, getFieldEditorParent()));

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
	}
}
