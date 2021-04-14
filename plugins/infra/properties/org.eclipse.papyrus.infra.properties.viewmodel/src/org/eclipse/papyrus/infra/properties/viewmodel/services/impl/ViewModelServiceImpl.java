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
 *   EclipseSource - Bug 572794
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.properties.viewmodel.services.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.infra.properties.contexts.Section;
import org.eclipse.papyrus.infra.properties.contexts.View;
import org.eclipse.papyrus.infra.properties.viewmodel.Control;
import org.eclipse.papyrus.infra.properties.viewmodel.ViewModel;
import org.eclipse.papyrus.infra.properties.viewmodel.services.ControlFilter;
import org.eclipse.papyrus.infra.properties.viewmodel.services.ViewModelProcessor;
import org.eclipse.papyrus.infra.properties.viewmodel.services.ViewModelProvider;
import org.eclipse.papyrus.infra.properties.viewmodel.services.ViewModelService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class ViewModelServiceImpl implements ViewModelService {

	private final Set<ViewModelProvider> viewModelProviders = new HashSet<>();

	private final Set<ControlFilter> controlFilters = new HashSet<>();

	private final Set<ViewModelProcessor> viewModelProcessors = new TreeSet<>((c1, c2) -> Double.compare(c1.getOrder(), c2.getOrder()));

	@Override
	public ViewModel getViewModel(Section section, View view) {
		ViewModel baseVM = getBaseViewModel(section, view);
		if (baseVM == null) {
			return null;
		}

		ViewModel filteredVM = filterControls(baseVM, section, view);
		if (filteredVM == null) {
			return null;
		}

		ViewModel processedVM = processViewModel(filteredVM, section, view);
		return processedVM;
	}

	protected ViewModel getBaseViewModel(Section section, View view) {
		double highestPriority = Double.MIN_VALUE;
		ViewModelProvider currentModelProvider = null;
		for (ViewModelProvider provider : viewModelProviders) {
			double priority = provider.getPriority(section, view);
			if (Double.isNaN(priority)) {
				continue;
			}
			if (currentModelProvider == null || priority > highestPriority) {
				highestPriority = priority;
				currentModelProvider = provider;
			}
		}
		return currentModelProvider.getViewModel(section, view);
	}

	protected ViewModel filterControls(final ViewModel baseVM, Section section, View view) {
		// We'll heavily rely on filters to decide what needs to be displayed; so we'll
		// need
		// a copy in most cases (The baseVM with rarely remain unchanged). We may
		// improve this
		// in the future if this impacts performances, but VMs are usually simple enough
		// that
		// a copy shouldn't have a noticeable impact.
		ViewModel result = EcoreUtil.copy(baseVM);

		TreeIterator<EObject> eAllContents = result.eAllContents();
		while (eAllContents.hasNext()) {
			EObject next = eAllContents.next();
			if (next instanceof Control) {
				Control control = (Control) next;

				// XXX If we have many filters, this loop might be expensive.
				// Filtering should be relatively stable over time, so maybe we'll have 
				// to cache this (Dynamic filtering will be handled separately)
				for (ControlFilter filter : controlFilters) {
					if (!filter.shouldDisplay(control, section, view)) {
						eAllContents.remove();
						break;
					}
				}
			}
		}
		return baseVM;
	}

	protected ViewModel processViewModel(final ViewModel viewModel, Section section, View view) {
		ViewModel result = viewModel;
		
		for (ViewModelProcessor processor : viewModelProcessors) {
			result = processor.process(viewModel, section, view);
		}
		
		return result;
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void registerViewModelProvider(ViewModelProvider provider) {
		this.viewModelProviders.add(provider);
	}

	public void unregisterViewModelProvider(ViewModelProvider provider) {
		this.viewModelProviders.remove(provider);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void registerControlFilter(ControlFilter filter) {
		this.controlFilters.add(filter);
	}

	public void unregisterControlFilter(ControlFilter filter) {
		this.controlFilters.remove(filter);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void registerViewModelProcessor(ViewModelProcessor processor) {
		this.viewModelProcessors.add(processor);
	}

	public void unregisterViewModelProcessor(ViewModelProcessor processor) {
		this.viewModelProcessors.remove(processor);
	}

}
