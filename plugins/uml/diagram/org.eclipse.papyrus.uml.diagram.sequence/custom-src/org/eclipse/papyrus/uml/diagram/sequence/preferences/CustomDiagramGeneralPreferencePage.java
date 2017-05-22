/**
 * Copyright (c) 2017 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
  *  CEA LIST - Initial API and implementation
 */
package org.eclipse.papyrus.uml.diagram.sequence.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.papyrus.infra.gmfdiag.preferences.pages.DiagramPreferencePage;
import org.eclipse.papyrus.uml.diagram.sequence.messages.Messages;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.SequenceDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLDiagramEditorPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;


public class CustomDiagramGeneralPreferencePage extends DiagramPreferencePage {
	
	/**
	 * preference page editor control for choosing if and which execution specifications should be automatically created with synchronous messages
	 */
	private RadioGroupFieldEditor executionSpecificationWithSyncMsg = null;

	/**
	 * preference page editor control for choosing if and which execution specifications should be automatically created with asynchronous messages
	 */
	private RadioGroupFieldEditor executionSpecificationWithAsyncMsg = null;

	/**
	 * preference key for asynchronous messages
	 */
	public static String PREF_EXECUTION_SPECIFICATION_ASYNC_MSG = "PREF_EXECUTION_SPECIFICATION_ASYNC_MSG";  //$NON-NLS-1$	

	/**
	 * preference key for synchronous messages
	 */
	public static String PREF_EXECUTION_SPECIFICATION_SYNC_MSG = "PREF_EXECUTION_SPECIFICATION_SYNC_MSG";  //$NON-NLS-1$
	
	/**
	 * possible preference values
	 */
	public static final String CHOICE_BEHAVIOR_AND_REPLY= "CHOICE_BEHAVIOR_AND_REPLY"; //$NON-NLS-1$
	public static final String CHOICE_ACTION_AND_REPLY= "CHOICE_ACTION_AND_REPLY"; //$NON-NLS-1$
	public static final String CHOICE_BEHAVIOR= "CHOICE_BEHAVIOR"; //$NON-NLS-1$
	public static final String CHOICE_ACTION= "CHOICE_ACTION"; //$NON-NLS-1$
	public static final String CHOICE_NONE= "CHOICE_NONE"; //$NON-NLS-1$
	
	public CustomDiagramGeneralPreferencePage() {
		setPreferenceStore(UMLDiagramEditorPlugin.getInstance().getPreferenceStore());
		setPreferenceKey(SequenceDiagramEditPart.MODEL_ID);
	}
	
	/**
	 * Create new composite to contain the field editors 
	 *
	 * @param parent
	 *            the parent Composite that the field editors will be added to
	 */
	@Override
	protected void addFields(Composite parent) {
		super.addFields(parent);
		Group notificationsGroup = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		notificationsGroup.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		notificationsGroup.setLayoutData(gridData);
		notificationsGroup.setText(Messages.DiagramsPreferencePage_notificationGroup_label);
		Composite composite = new Composite(notificationsGroup, SWT.NONE);
		createFieldEditors(composite);
		addField(executionSpecificationWithSyncMsg);
		addField(executionSpecificationWithAsyncMsg);
	}

	/**
	 * 
	 * @param composite
	 */
	protected void createFieldEditors(Composite composite) {
		// preference for choosing if and which execution specifications should be automatically created with synchronous message
		// choice between behavior execution specification, action execution specification or nothing
		executionSpecificationWithSyncMsg = new RadioGroupFieldEditor(PREF_EXECUTION_SPECIFICATION_SYNC_MSG, 
				Messages.DiagramsPreferencePage_executionSpecificationWithSyncMsg_label, 1,
				new String[][] {
						{ Messages.DiagramsPreferencePage_createBehaviorExecutionSpecificationAndReply, CHOICE_BEHAVIOR_AND_REPLY },
						{ Messages.DiagramsPreferencePage_createActionExecutionSpecificationAndReply, CHOICE_ACTION_AND_REPLY }, 
						{ Messages.DiagramsPreferencePage_createBehaviorExecutionSpecification, CHOICE_BEHAVIOR }, 
						{ Messages.DiagramsPreferencePage_createActionExecutionSpecification, CHOICE_ACTION }, 
						{ Messages.DiagramsPreferencePage_createNoExecutionSpecification, CHOICE_NONE }
				}, composite);

		// preference for choosing if and which execution specifications should be automatically created with asynchronous message
		// choice between behavior execution specification, action execution specification or nothing
		executionSpecificationWithAsyncMsg = new RadioGroupFieldEditor(PREF_EXECUTION_SPECIFICATION_ASYNC_MSG, 
				Messages.DiagramsPreferencePage_executionSpecificationWithAsyncMsg_label, 1,
				new String[][] {
						{ Messages.DiagramsPreferencePage_createBehaviorExecutionSpecification, CHOICE_BEHAVIOR }, 
						{ Messages.DiagramsPreferencePage_createActionExecutionSpecification, CHOICE_ACTION }, 
						{ Messages.DiagramsPreferencePage_createNoExecutionSpecification, CHOICE_NONE }
				}, composite);
	}
	
	/**
	 * Initializes the default preference values for this preference store.
	 *
	 * @param IPreferenceStore
	 *            preferenceStore
	 */
	public static void initSpecificDefaults(IPreferenceStore preferenceStore) {
		preferenceStore.setDefault(PREF_EXECUTION_SPECIFICATION_SYNC_MSG, 
				CHOICE_BEHAVIOR_AND_REPLY);
		preferenceStore.setDefault(PREF_EXECUTION_SPECIFICATION_ASYNC_MSG, 
				CHOICE_NONE);		

	}	
}
