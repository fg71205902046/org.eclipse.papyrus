package org.eclipse.papyrus.infra.properties.viewmodel.services.impl;

import org.eclipse.papyrus.infra.properties.contexts.Section;
import org.eclipse.papyrus.infra.properties.contexts.View;
import org.eclipse.papyrus.infra.properties.viewmodel.ViewModel;
import org.eclipse.papyrus.infra.properties.viewmodel.services.ViewModelProvider;
import org.osgi.service.component.annotations.Component;

/**
 * ViewModelProvider that generates a ViewModel from the selection. This
 * provider ignores XWTSections (that already provide their own View Model)
 */
@Component
public class DynamicViewModelProvider implements ViewModelProvider {

	@Override
	public ViewModel getViewModel(Section section, View view) {
		// TODO
		return null;
	}

	@Override
	public double getPriority(Section section, View view) {
		return section.getSectionFile() == null ? 1 : Double.NaN;
	}

}
