package org.eclipse.papyrus.infra.properties.ui.fx.impl;

import org.eclipse.papyrus.infra.properties.contexts.Section;
import org.eclipse.papyrus.infra.properties.contexts.View;
import org.eclipse.papyrus.infra.properties.ui.renderers.SectionRendererProvider;
import org.eclipse.papyrus.infra.properties.ui.runtime.DisplayEngine;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.osgi.service.component.annotations.Component;

@Component
public class FXSectionProvider implements SectionRendererProvider {

	@Override
	public double getPriority(Section section, View view, DisplayEngine display) {
		// TODO Temporary PoC; return true for all sections (XWT has a priority of -1)
		// At the moment, the sections contents are hardcoded, so it doesn't really matter
		// which input we really get
		return 1;
	}

	@Override
	public ISectionDescriptor getSectionDescriptor(Section section, View view, DisplayEngine display) {
		return new FXSectionDescriptor(section, view, display);
	}

}
