/*****************************************************************************
 * Copyright (c) 2015 Christian W. Damus and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.infra.core.internal.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.papyrus.infra.core.Activator;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The preference page for Papyrus Editor general preferences.
 */
public class EditorPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public EditorPreferencePage() {
		super(Messages.EditorPreferencePage_0, SWT.FLAT);

		setDescription(Messages.EditorPreferencePage_5);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		addField(new RadioGroupFieldEditor(EditorPreferences.PREF_CONVERT_SHARED_LAYOUT,
				Messages.EditorPreferencePage_1,
				1,
				new String[][] {
						{ Messages.EditorPreferencePage_2, YesNo.PROMPT.name() },
						{ Messages.EditorPreferencePage_3, YesNo.NO.name() },
						{ Messages.EditorPreferencePage_4, YesNo.YES.name() },
				},
				getFieldEditorParent()));
	}

}
