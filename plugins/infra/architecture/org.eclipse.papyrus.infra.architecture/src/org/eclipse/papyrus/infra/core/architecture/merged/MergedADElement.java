/**
 * Copyright (c) 2017, 2020 CEA LIST, Christian W. Damus, and others.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *  Maged Elaasar - Initial API and implementation
 *  Nicolas FAUVERGUE (CEA LIST) nicolas.fauvergue@cea.fr - Bug 550359
 *  Christian W. Damus - bug 569357
 *
 */
package org.eclipse.papyrus.infra.core.architecture.merged;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.papyrus.infra.core.architecture.ADElement;

/**
 * An element that represents a merged collection of {@link org.eclipse.papyrus.infra.core.
 * architecture.ADElement}s that are instances of the same EClass. This allows the definition
 * of architecture elements to be split across several architectural models (*.architecture).
 *
 * All merged elements is assumed to have the same name and qualified name values. However, only
 * one of those elements (the main merge increment) has values for the single-valued properties.
 * On the other hand, all multi-valued properties of the elements are merged.
 *
 * @see org.eclipse.papyrus.infra.core.architecture.ADElement
 * @since 1.0
 */
public class MergedADElement {

	/**
	 * The merged parent of this element
	 */
	protected MergedADElement parent;

	/**
	 * the architecture elements that represent merge increments of this element
	 */
	protected Set<ADElement> elements;

	/**
	 * Create a new '<em><b>Merged AD Element</b></em>'.
	 *
	 * @param parent
	 *            the merged parent of this element
	 */
	public MergedADElement(MergedADElement parent) {
		this.parent = parent;
		this.elements = new LinkedHashSet<>();
	}

	/**
	 * Get the element's parent
	 *
	 * @return the parent element
	 */
	public MergedADElement getParent() {
		return parent;
	}

	/**
	 * Gets the context's id
	 *
	 * @return an id
	 */
	public String getId() {
		for (ADElement element : elements) {
			if (element.getId() != null) {
				return element.getId();
			}
		}
		return null;
	}

	/**
	 * Get the element's name
	 *
	 * @return a name
	 */
	public String getName() {
		for (ADElement element : elements) {
			if (element.getName() != null) {
				return element.getName();
			}
		}
		return null;
	}

	/**
	 * Get the element's qualified name
	 *
	 * @return a qualified name
	 */
	public String getQualifiedName() {
		for (ADElement element : elements) {
			if (element.getQualifiedName() != null) {
				return element.getQualifiedName();
			}
		}
		return null;
	}

	/**
	 * Get the element's description
	 *
	 * @return a description
	 */
	public String getDescription() {
		for (ADElement element : elements) {
			if (element.getDescription() != null) {
				return element.getDescription();
			}
		}
		return null;
	}

	/**
	 * Gets the context's icon path
	 *
	 * @return an icon path
	 */
	public String getIcon() {
		Object obj = getImageObject();
		if (obj instanceof ADElement) {
			return ((ADElement) obj).getIcon();
		}
		return null;
	}

	/**
	 * Get a merge increment whose image represents that of the merged element
	 *
	 * By default, any one of the merge increments will be returned. Subclasses may override.
	 *
	 * @return a merge increment
	 * @since 2.0
	 */
	public ADElement getImageObject() {
		for (ADElement element : elements) {
			if (element.getIcon() != null) {
				return element;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		for (ADElement element : elements) {
			hash += element.hashCode();
		}
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MergedADElement)) {
			return false;
		}
		MergedADElement other = (MergedADElement) obj;
		if (other.parent != this.parent) {
			return false;
		}
		Set<ADElement> copy = new HashSet<>(this.elements);
		copy.retainAll(other.elements);
		return copy.size() == this.elements.size();
	}

	@Override
	public String toString() {
		Iterator<ADElement> i = elements.iterator();
		if (i.hasNext()) {
			return i.next().toString();
		}
		return super.toString();
	}

	/*
	 * Adds the given element to the collection of merged elements
	 */
	void merge(ADElement element) {
		elements.add(element);
	}

	/**
	 * Get the merged elements number.
	 *
	 * @return The number of merged elements.
	 * @since 2.1
	 */
	public int getElementsNumber() {
		return elements.size();
	}

	/**
	 * Obtain an unmodifiable set of the original elements that I merge.
	 *
	 * @return my merged elements
	 * @since 3.0
	 */
	public Set<? extends ADElement> getMergedElements() {
		return Collections.unmodifiableSet(elements);
	}

}
