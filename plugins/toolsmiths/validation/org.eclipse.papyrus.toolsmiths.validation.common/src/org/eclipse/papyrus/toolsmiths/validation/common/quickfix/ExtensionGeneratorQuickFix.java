/*****************************************************************************
 * Copyright (c) 2021 Christian W. Damus, CEA LIST, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.validation.common.quickfix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.papyrus.infra.emf.utils.ResourceUtils;
import org.eclipse.papyrus.toolsmiths.validation.common.Activator;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CommonProblemConstants;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.messages.Messages;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionAttribute;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ExtensionElement;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Value;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.util.Evaluator;
import org.eclipse.papyrus.toolsmiths.validation.common.quickfix.AbstractEditExtensionMarkerResolution.DisposableState;
import org.eclipse.papyrus.toolsmiths.validation.common.quickfix.AbstractEditExtensionMarkerResolution.ExtensionBuilder;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginObject;
import org.eclipse.pde.core.plugin.IPluginParent;
import org.eclipse.ui.IMarkerResolution;

import com.google.common.base.Strings;

/**
 * Quick-fix framework for marker resolutions that generate <tt>plugin.xml</tt> extension
 * elements from {@link ProjectDescription}s.
 */
class ExtensionGeneratorQuickFix {

	public ExtensionGeneratorQuickFix() {
		super();
	}

	public IMarkerResolution addExtension(int problemID) {
		return new AddExtension(problemID, Messages.ExtensionGeneratorQuickFix_0, Messages.ExtensionGeneratorQuickFix_1);
	}

	public IMarkerResolution addElement(int problemID) {
		return new AddElement(problemID, Messages.ExtensionGeneratorQuickFix_2, Messages.ExtensionGeneratorQuickFix_3);
	}

	public IMarkerResolution addAttribute(int problemID) {
		return new AddAttribute(problemID, Messages.ExtensionGeneratorQuickFix_4, Messages.ExtensionGeneratorQuickFix_5);
	}

	public IMarkerResolution setAttribute(int problemID) {
		return new SetAttribute(problemID, Messages.ExtensionGeneratorQuickFix_6, Messages.ExtensionGeneratorQuickFix_7);
	}

	protected ProjectDescription getProjectDescription(EObject object) {
		return Activator.getDefault().getProjectDescription(object);
	}

	protected String getAttributeValue(List<String> elementPath, IMarker marker, ResourceSet resourceSet, String name) {
		Optional<EObject> object = CommonMarkerResolutionUtils.getModelObject(marker, EObject.class, resourceSet);
		Optional<ProjectDescription> projectDescription = object.map(this::getProjectDescription);
		return projectDescription.map(desc -> getAttributeValue(desc, elementPath, marker, object.get(), name)).orElse(null);
	}

	protected String getAttributeValue(ProjectDescription projectDescription, List<String> elementPath, IMarker marker, EObject object, String name) {
		String result = null;

		ExtensionElement element = getElement(projectDescription, elementPath);
		if (element != null) {
			ExtensionAttribute attribute = element.getAttribute(name);
			if (attribute != null) {
				Value value = attribute.getParsedValue();
				result = (value == null) ? null : createEvaluator(marker, object).evaluate(value);
			}
		}

		return result;
	}

	protected ExtensionElement getElement(ProjectDescription projectDescription, List<String> elementPath) {
		ExtensionElement result = null;

		Extension extension = projectDescription.getExtension(elementPath.get(0));
		if (extension != null) {
			for (int i = 1; i < elementPath.size(); i++) {
				result = (result != null) ? result.getElement(elementPath.get(i)) : extension.getElement(elementPath.get(i));
				if (result == null) {
					break;
				}
			}
		}

		return result;
	}

	protected Evaluator createEvaluator(IMarker marker, EObject object) {
		IFile file = ResourceUtils.getFile(object.eResource());
		if (file == null) {
			file = (IFile) marker.getResource();
		}
		return new Evaluator(file.getProject(), file, object);
	}

	protected ResourceSet createResourceSet() {
		ResourceSet result = new ResourceSetImpl();
		result.setURIConverter(ResourceUtils.createWorkspaceAwareURIConverter());
		result.setPackageRegistry(ResourceUtils.createWorkspaceAwarePackageRegistry());
		return result;
	}

	protected List<String> getElementPath(IPluginParent element) {
		List<String> result = new ArrayList<>();
		for (IPluginObject node = element; node instanceof IPluginElement || node instanceof IPluginExtension; node = node.getParent()) {
			result.add(node instanceof IPluginElement ? node.getName() : ((IPluginExtension) node).getPoint());
		}
		Collections.reverse(result);
		return result;
	}

	protected void generateElement(IMarker marker, List<String> parentPath, String elementName, ResourceSet resourceSet, ExtensionBuilder extensionBuilder) {
		Optional<EObject> object = CommonMarkerResolutionUtils.getModelObject(marker, EObject.class, resourceSet);
		Optional<ProjectDescription> projectDescription = object.map(this::getProjectDescription);

		projectDescription.ifPresent(desc -> generateElement(desc, parentPath, elementName, createEvaluator(marker, object.get()), extensionBuilder));
	}

	protected void generateElement(ProjectDescription projectDescription, List<String> parentPath, String elementName, Evaluator evaluator, ExtensionBuilder extensionBuilder) {
		ExtensionElement parent = getElement(projectDescription, parentPath);
		ExtensionElement element = (parent == null) ? null : parent.getElement(elementName);
		if (element != null) {
			generateElement(element, evaluator, extensionBuilder.newElement(element.getName()));
		}
	}

	protected void generateElement(ExtensionElement element, Evaluator evaluator, ExtensionBuilder builder) {
		for (ExtensionAttribute attribute : element.getAttributes()) {
			String value = evaluate(attribute, evaluator);
			if (value != null) {
				builder.setAttribute(attribute.getName(), value);
			}
		}

		for (ExtensionElement nested : element.getElements()) {
			generateElement(nested, evaluator, builder.newElement(nested.getName()));
		}
	}

	protected final String evaluate(ExtensionAttribute attribute, Evaluator evaluator) {
		Value value = attribute.getParsedValue();
		String result = (value == null) ? null : evaluator.evaluate(value);
		return Strings.emptyToNull(result);
	}

	protected void generateExtension(IMarker marker, String extensionPoint, ResourceSet resourceSet, ExtensionBuilder extensionBuilder) {
		Optional<EObject> object = CommonMarkerResolutionUtils.getModelObject(marker, EObject.class, resourceSet);
		Optional<ProjectDescription> projectDescription = object.map(this::getProjectDescription);

		projectDescription.ifPresent(desc -> generateExtension(desc, extensionPoint, createEvaluator(marker, object.get()), extensionBuilder));
	}

	protected void generateExtension(ProjectDescription projectDescription, String extensionPoint, Evaluator evaluator, ExtensionBuilder extensionBuilder) {
		Extension extension = projectDescription.getExtension(extensionPoint);
		if (extension != null) {
			generateExtension(extension, evaluator, extensionBuilder);
		}
	}

	protected void generateExtension(Extension extension, Evaluator evaluator, ExtensionBuilder builder) {
		for (ExtensionElement element : extension.getElements()) {
			generateElement(element, evaluator, builder.newElement(element.getName()));
		}
	}

	//
	// Nested types
	//

	protected class SetAttribute extends AbstractSetAttributeMarkerResolution {

		private final String label;
		private final String description;

		protected SetAttribute(int problemID, String label, String description) {
			super(problemID);

			this.label = label;
			this.description = description;
		}

		@Override
		protected IAdaptable initialize(IMarker marker) throws CoreException {
			return new State(marker);
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public String getDescription() {
			return description;
		}

		@Override
		protected String getAttributeValue(IMarker marker) throws CoreException {
			List<String> elementPath = getElementPath(getElement(marker));
			ResourceSet resourceSet = getState(ResourceSet.class);
			return ExtensionGeneratorQuickFix.this.getAttributeValue(elementPath, marker, resourceSet, getAttributeName(marker));
		}

	}

	protected class AddAttribute extends SetAttribute {

		protected AddAttribute(int problemID, String label, String description) {
			super(problemID, label, description);
		}

		// The marker location doesn't have an attribute name because the attribute is missing.
		@Override
		protected String getAttributeName(IMarker marker) throws CoreException {
			return marker.getAttribute(CommonProblemConstants.ATTRIBUTE_NAME, null);
		}

	}

	protected class AddElement extends AbstractEditExtensionMarkerResolution {

		private final String label;
		private final String description;

		protected AddElement(int problemID, String label, String description) {
			super(problemID);

			this.label = label;
			this.description = description;
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public String getDescription() {
			return description;
		}

		@Override
		protected IAdaptable initialize(IMarker marker) throws CoreException {
			return new State(marker);
		}

		@Override
		protected void editExtensionElement(IMarker marker, IPluginElement element) throws CoreException {
			String elementName = marker.getAttribute(CommonProblemConstants.ELEMENT_NAME, null);
			List<String> parentPath = getElementPath(getElement(marker));
			ResourceSet resourceSet = getState(ResourceSet.class);

			ExtensionGeneratorQuickFix.this.generateElement(marker, parentPath, elementName, resourceSet, build(element));
		}

	}

	protected class AddExtension extends AbstractMissingElementMarkerResolution {

		private final String label;
		private final String description;

		protected AddExtension(int problemID, String label, String description) {
			super(problemID);

			this.label = label;
			this.description = description;
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public String getDescription() {
			return description;
		}

		@Override
		protected IAdaptable initialize(IMarker marker) throws CoreException {
			return new State(marker);
		}

		@Override
		protected String getExtensionPoint(IMarker marker) throws CoreException {
			return marker.getAttribute(CommonProblemConstants.EXTENSION_POINT, null);
		}

		@Override
		protected void configureExtension(IPluginExtension extension, IMarker marker) throws CoreException {
			ResourceSet resourceSet = getState(ResourceSet.class);
			String point = getExtensionPoint(marker);

			ExtensionGeneratorQuickFix.this.generateExtension(marker, point, resourceSet, build(extension));
		}

	}

	protected final class State implements IAdaptable {

		private final ResourceSet resourceSet;

		State(IMarker marker) throws CoreException {
			super();
			resourceSet = createResourceSet();
		}

		void dispose() {
			EMFHelper.unload(resourceSet);
		}

		@Override
		public <T> T getAdapter(Class<T> adapter) {
			if (adapter == DisposableState.class) {
				DisposableState disposable = this::dispose;
				return adapter.cast(disposable);
			}
			if (adapter == ResourceSet.class) {
				return adapter.cast(resourceSet);
			}
			return null;
		}
	}

}
