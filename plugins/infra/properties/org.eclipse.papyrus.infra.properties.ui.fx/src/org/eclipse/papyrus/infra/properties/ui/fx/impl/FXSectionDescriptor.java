package org.eclipse.papyrus.infra.properties.ui.fx.impl;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.papyrus.infra.properties.contexts.Section;
import org.eclipse.papyrus.infra.properties.contexts.View;
import org.eclipse.papyrus.infra.properties.ui.runtime.DisplayEngine;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;

// FIXME Copied from XWTSectionDescriptor. At this point, it's unclear
// what we need to reuse from the XWT Integration; so we keep everything
// (Except for the actual rendering). Some parts of the XWT Integration
// are important, especially the section-caching mechanism that allows
// us to reuse the same Section class/Section controls (For JavaFX,
// it means we can reuse the FXCanvas in many cases, which is must faster/smoother
// than creating a new one on each selection change. However, we could push
// the cache even further for JavaFX, as we don't need to reuse or cache the FX Nodes)
public class FXSectionDescriptor extends AbstractSectionDescriptor {

	private Section section;

	private View view;

	private DisplayEngine display;

	public FXSectionDescriptor(Section section, View view, DisplayEngine display) {
		this.section = section;
		this.view = view;
		this.display = display;
	}

	public String getId() {
		return section.getName();
	}

	public ISection getSectionClass() {
		return new FXSection(section, view, display);
	}

	public String getTargetTab() {
		return section.getTab().getId();
	}

	@Override
	public String toString() {
		return "Section " + getId(); //$NON-NLS-1$
	}

	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + System.identityHashCode(display);
		result = prime * result + System.identityHashCode(section);
		result = prime * result + System.identityHashCode(view);
		return result;
	}

	/**
	 * FX section descriptors are equal if they have the same (identical) references to the section and view from the property-sheet model
	 * and are associated with the same display engine.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result;

		if (this == obj) {
			result = true;
		} else if ((obj == null) || (obj.getClass() != this.getClass())) {
			result = false;
		} else {
			FXSectionDescriptor other = (FXSectionDescriptor) obj;

			result = (other.section == this.section) && (other.view == this.view) && (other.display == this.display);
		}

		return result;
	}

}
