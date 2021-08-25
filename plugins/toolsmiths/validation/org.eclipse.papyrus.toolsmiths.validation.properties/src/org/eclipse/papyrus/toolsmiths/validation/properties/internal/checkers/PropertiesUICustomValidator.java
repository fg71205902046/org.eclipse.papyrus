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

package org.eclipse.papyrus.toolsmiths.validation.properties.internal.checkers;

import static org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker2.problem;
import static org.eclipse.papyrus.toolsmiths.validation.properties.internal.constants.PropertiesPluginValidationConstants.INCONSISTENT_WIDGET_TYPE;
import static org.eclipse.papyrus.toolsmiths.validation.properties.internal.constants.PropertiesPluginValidationConstants.objectToReference;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.papyrus.infra.properties.contexts.ContextsPackage;
import org.eclipse.papyrus.infra.properties.contexts.Property;
import org.eclipse.papyrus.infra.properties.environment.WidgetType;
import org.eclipse.papyrus.infra.properties.ui.PropertyEditor;
import org.eclipse.papyrus.infra.properties.ui.UiPackage;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CustomModelChecker;
import org.eclipse.papyrus.toolsmiths.validation.properties.internal.messages.Messages;
import org.eclipse.papyrus.toolsmiths.validation.properties.internal.util.WidgetTypeHelper;

/**
 * Custom validation rules for <em>Properties UI</em> models.
 */
public class PropertiesUICustomValidator extends CustomModelChecker.SwitchValidator {

	public PropertiesUICustomValidator(String nsURI) {
		super(nsURI);
	}

	public void validate(PropertyEditor propertyEditor, DiagnosticChain diagnostics, Map<Object, Object> context) {
		WidgetType widgetType = propertyEditor.getWidgetType();
		Property property = propertyEditor.getProperty();

		if (widgetType != null && !widgetType.eIsProxy() && property != null && !property.eIsProxy()) {
			// If null is a problem, it's a different problem to this
			WidgetTypeHelper helper = WidgetTypeHelper.getInstance(propertyEditor);
			WidgetType defaultWidgetType = helper.getDefaultWidgetType(property);
			if (helper.isCoreWidgetType(widgetType) && defaultWidgetType != widgetType) {
				// Warn if a non-custom widget type seems to disagree with the property type
				diagnostics.add(createDiagnostic(Diagnostic.WARNING, propertyEditor, UiPackage.Literals.PROPERTY_EDITOR__WIDGET_TYPE,
						format(Messages.PropertiesUICustomValidator_0, context,
								propertyEditor.getProperty(), widgetType, value(ContextsPackage.Literals.PROPERTY__TYPE, property.getType())),
						problem(INCONSISTENT_WIDGET_TYPE),
						objectToReference(defaultWidgetType)));
			}
		}
	}

}
