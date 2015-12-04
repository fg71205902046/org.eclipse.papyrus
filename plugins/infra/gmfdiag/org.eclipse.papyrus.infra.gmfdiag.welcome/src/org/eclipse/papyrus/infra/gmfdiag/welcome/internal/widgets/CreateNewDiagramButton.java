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

package org.eclipse.papyrus.infra.gmfdiag.welcome.internal.widgets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.emf.providers.strategy.SemanticEMFContentProvider;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForEObject;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForResourceSet;
import org.eclipse.papyrus.infra.services.labelprovider.service.LabelProviderService;
import org.eclipse.papyrus.infra.viewpoints.configuration.PapyrusDiagram;
import org.eclipse.papyrus.infra.viewpoints.policy.PolicyChecker;
import org.eclipse.papyrus.infra.viewpoints.policy.ViewPrototype;
import org.eclipse.papyrus.infra.widgets.editors.TreeSelectorDialog;
import org.eclipse.papyrus.uml.tools.providers.SemanticUMLContentProvider;
import org.eclipse.papyrus.views.properties.modelelement.DataSource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * The properties widget that creates a new diagram.
 */
public class CreateNewDiagramButton extends Composite {

	private DataSource input;

	public CreateNewDiagramButton(Composite parent, int style) {
		super(parent, style);

		GridLayout layout = new GridLayout(1, false);
		setLayout(layout);

		Button button = new Button(this, SWT.PUSH | SWT.FLAT);
		button.setText("Create Diagram");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createDiagram();
			}
		});
	}

	@Override
	public void dispose() {
		this.input = null;

		super.dispose();
	}

	/**
	 * Sets the input DataSource for this Property editor.
	 *
	 * @param input
	 */
	public void setInput(DataSource input) {
		this.input = input;
	}

	/**
	 * @return the input DataSource for this Property editor
	 */
	public DataSource getInput() {
		return input;
	}

	protected EObject getModelElement() {
		return EMFHelper.getEObject(input.getSelection().getFirstElement());
	}

	protected void createDiagram() {
		List<ViewPrototype> availablePrototypes = new ArrayList<>();

		ModelSet modelSet = null;
		LabelProviderService labels = null;

		try {
			modelSet = ServiceUtilsForEObject.getInstance().getModelSet(getModelElement());
			labels = ServiceUtilsForResourceSet.getInstance().getService(LabelProviderService.class, modelSet);
		} catch (ServiceException e) {
			throw new IllegalStateException(e);
		}

		SemanticEMFContentProvider content = new SemanticUMLContentProvider(modelSet) {

			@Override
			public boolean isValidValue(Object element) {
				availablePrototypes.clear();

				if (element != null) {
					EObject object = EMFHelper.getEObject(element);
					if (object != null) {
						// build a list of all the available prototypes
						for (final ViewPrototype proto : PolicyChecker.getCurrent().getPrototypesFor(object)) {
							if (!(proto.getConfiguration() instanceof PapyrusDiagram)) {
								continue;
							}
							availablePrototypes.add(proto);
						}
					}
				}

				return !availablePrototypes.isEmpty();
			}
		};

		TreeSelectorDialog dlg = new TreeSelectorDialog(getShell());
		dlg.setLabelProvider(labels.getLabelProvider());
		dlg.setContentProvider(content);
		dlg.setMessage("Select an element for which to create a new diagram.");
		dlg.setTitle("Select Diagram Context");
		dlg.setInput(modelSet);

		if (dlg.open() == Window.OK) {
			Object[] result = dlg.getResult();
			if (result.length > 0) {
				EObject object = EMFHelper.getEObject(result[0]);
				if (object != null) {
					createDiagram(object, availablePrototypes);
				}
			}
		}
	}

	protected void createDiagram(EObject context, List<ViewPrototype> prototypes) {
		Collections.sort(prototypes, new ViewPrototype.Comp());

		ILabelProvider labels = new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((ViewPrototype) element).getLabel();
			}

			@Override
			public Image getImage(Object element) {
				return ((ViewPrototype) element).getIcon();
			}
		};

		ElementListSelectionDialog dlg = new ElementListSelectionDialog(getShell(), labels);
		dlg.setTitle("Create Diagram");
		dlg.setMessage("Select the diagram to create");
		dlg.setMultipleSelection(false);
		dlg.setElements(prototypes.toArray());

		if (dlg.open() == Window.OK) {
			Object[] result = dlg.getResult();
			if (result.length > 0) {
				ViewPrototype prototype = (ViewPrototype) result[0];
				prototype.instantiateOn(context);
			}
		}
	}
}
