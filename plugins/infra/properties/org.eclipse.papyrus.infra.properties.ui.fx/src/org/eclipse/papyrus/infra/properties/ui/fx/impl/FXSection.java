/*****************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   EclipseSource - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.properties.ui.fx.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.constraints.ConstraintDescriptor;
import org.eclipse.papyrus.infra.constraints.constraints.Constraint;
import org.eclipse.papyrus.infra.constraints.runtime.ConstraintFactory;
import org.eclipse.papyrus.infra.properties.contexts.Section;
import org.eclipse.papyrus.infra.properties.contexts.View;
import org.eclipse.papyrus.infra.properties.ui.fx.ViewModelRenderer;
import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSource;
import org.eclipse.papyrus.infra.properties.ui.modelelement.DataSourceFactory;
import org.eclipse.papyrus.infra.properties.ui.runtime.DefaultDisplayEngine;
import org.eclipse.papyrus.infra.properties.ui.runtime.DisplayEngine;
import org.eclipse.papyrus.infra.properties.viewmodel.ViewModel;
import org.eclipse.papyrus.infra.properties.viewmodel.services.ViewModelService;
import org.eclipse.papyrus.infra.widgets.editors.EditorParentComposite;
import org.eclipse.papyrus.javafx.FXWrapperService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FXSection extends AbstractPropertySection implements IChangeListener {

	private Section section;

	private DataSource source;

	private View view;

	private Composite self;

	private DisplayEngine display;

	private Set<Constraint> constraints;

	private BorderPane fxParent;

	private ViewModelService viewModelService;

	private final List<Runnable> toDispose = new ArrayList<>();

	private ViewModelRenderer viewModelRenderer;

	private FXWrapperService fxWrapper;

	/**
	 * Constructor.
	 *
	 * @param section The Section object containing the Metadata for the XWTSection
	 * @param view    The view this section belongs to
	 * @param display The display engine that will generate the SWT Controls
	 */
	public FXSection(Section section, View view, DisplayEngine display) {
		this.section = section;
		this.view = view;
		this.display = display;

		BundleContext bundleContext = FrameworkUtil.getBundle(DefaultDisplayEngine.class).getBundleContext();

		ServiceReference<ViewModelService> serviceReference = bundleContext.getServiceReference(ViewModelService.class);
		this.viewModelService = bundleContext.getService(serviceReference);
		toDispose.add(() -> bundleContext.ungetService(serviceReference));

		ServiceReference<ViewModelRenderer> rendererReference = bundleContext
				.getServiceReference(ViewModelRenderer.class);
		this.viewModelRenderer = bundleContext.getService(rendererReference);
		toDispose.add(() -> bundleContext.ungetService(rendererReference));
		
		
		ServiceReference<FXWrapperService> fxWrapperReference = bundleContext
				.getServiceReference(FXWrapperService.class);
		this.fxWrapper = bundleContext.getService(fxWrapperReference);
		if (this.fxWrapper == null) {
			throw new IllegalStateException("No JavaFX-to-SWT Wrapper Service registered. Unable to render JavaFX Controls");
		}
		toDispose.add(() -> bundleContext.ungetService(fxWrapperReference));
	}

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		System.err.println("Create Controls");
		self = new EditorParentComposite(parent, SWT.NONE);

		FillLayout layout = new FillLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		self.setLayout(layout);

		fxParent = new BorderPane();
		fxParent.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		fxWrapper.toSWTComposite(self, fxParent);
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (selection == getSelection()) {
			return;
		}

		// Sets the initial input, *or* changes the input for the same view : we need to
		// clean the cache
		DataSourceFactory.instance.removeFromCache(getSelection(), view);

		super.setInput(part, selection);

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;

			setSource(DataSourceFactory.instance.createDataSourceFromSelection(structuredSelection, view));
		}
	}

	private void setSource(DataSource source) {
		final DataSource oldSource = this.source;

		if (oldSource != source) {
			if (oldSource != null) {
				if (section.getConstraints().size() == 0) {
					oldSource.removeChangeListener(this);
					oldSource.autoRelease();
				}
			}

			this.source = source;

			if (source != null) {
				source.retain();
				if (section.getConstraints().size() > 0) {
					source.addChangeListener(this);
				}
			}
		}
	}

	/**
	 * Displays the section
	 *
	 * @param refresh If true, and the section has already been displayed, the
	 *                controls will be regenerated. If false, the section will only
	 *                be displayed if it hasn't been displayed yet, or if the
	 *                display engine allows duplication of sections
	 */
	public void display(boolean refresh) {

		long begin = System.currentTimeMillis();
		System.err.println("Generate view model..."+begin);
		ViewModel viewModel = viewModelService.getViewModel(section, view);

		VBox propertiesRoot = new VBox();
		propertiesRoot.setFillWidth(true);
		propertiesRoot.setPadding(new Insets(5));
		
		if (viewModel != null) {
			System.err.println("Render..."+System.currentTimeMillis());
			viewModelRenderer.render(viewModel, source, propertiesRoot);
		}

		System.err.println("Attach..."+System.currentTimeMillis());
		fxParent.setCenter(propertiesRoot);
		System.err.println("Done");
		long end = System.currentTimeMillis();
		System.err.println("Total time: "+(end - begin)+"ms");

		System.err.println(refresh ? "Dynamic Update" : "Update selection");
	}

	private void hide() {
		display.removeSection(self);
	}

	@Override
	public void refresh() {
		display(false);
	}

	/**
	 * Tests if this section is applied. A section is applied if it doesn't have any
	 * constraint, or if at least one of its constraints match the current selection
	 *
	 * @return True if the section should be displayed
	 */
	protected boolean isApplied() {
		if (getConstraints().isEmpty()) {
			return true;
		}

		ISelection selection = getSelection();
		List<?> selectionList = ((IStructuredSelection) selection).toList();

		// Return true only if at least one constraint matches the selection

		for (Constraint constraint : getConstraints()) {
			if (constraint.match(selectionList)) {
				return true;
			}
		}

		return false;
	}

	protected Set<Constraint> getConstraints() {
		if (constraints == null) {
			constraints = new HashSet<>();
			for (ConstraintDescriptor constraintDescriptor : section.getConstraints()) {
				Constraint constraint = ConstraintFactory.getInstance().createFromModel(constraintDescriptor);
				if (constraint != null) {
					constraints.add(constraint);
				}
			}
		}

		return constraints;
	}

	@Override
	public void dispose() {
		// Release the DataSource
		if (source != null) {
			source.removeChangeListener(this);
			source.release();
		}

		// Dispose the SWT Composite
		if (self != null) {
			self.dispose();
		}

		// Clean the DataSource cache
		DataSourceFactory.instance.removeFromCache(getSelection(), view);

		// Run other dispose operations
		toDispose.forEach(Runnable::run);
		toDispose.clear();

		super.dispose();
	}

	@Override
	public IStructuredSelection getSelection() {
		return (IStructuredSelection) super.getSelection();
	}

	@Override
	public String toString() {
		return "FXSection : " + section.getName(); //$NON-NLS-1$
	}

	@Override
	public void handleChange(ChangeEvent event) {
		display(true);
	}

	@Override
	public boolean shouldUseExtraSpace() {
		// Actual layout is handled by JavaFX, so use as much SWT space as available
		return true;
	}

}
