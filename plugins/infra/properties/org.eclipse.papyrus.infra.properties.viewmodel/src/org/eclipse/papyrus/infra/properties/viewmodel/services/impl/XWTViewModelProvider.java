package org.eclipse.papyrus.infra.properties.viewmodel.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.properties.contexts.DataContextElement;
import org.eclipse.papyrus.infra.properties.contexts.Property;
import org.eclipse.papyrus.infra.properties.contexts.Section;
import org.eclipse.papyrus.infra.properties.contexts.View;
import org.eclipse.papyrus.infra.properties.environment.LayoutType;
import org.eclipse.papyrus.infra.properties.ui.CompositeWidget;
import org.eclipse.papyrus.infra.properties.ui.Layout;
import org.eclipse.papyrus.infra.properties.ui.PropertyEditor;
import org.eclipse.papyrus.infra.properties.ui.ValueAttribute;
import org.eclipse.papyrus.infra.properties.ui.WidgetAttribute;
import org.eclipse.papyrus.infra.properties.viewmodel.Control;
import org.eclipse.papyrus.infra.properties.viewmodel.GridLayout;
import org.eclipse.papyrus.infra.properties.viewmodel.Parent;
import org.eclipse.papyrus.infra.properties.viewmodel.VerticalLayout;
import org.eclipse.papyrus.infra.properties.viewmodel.ViewModel;
import org.eclipse.papyrus.infra.properties.viewmodel.ViewmodelFactory;
import org.eclipse.papyrus.infra.properties.viewmodel.services.ViewModelProvider;
import org.osgi.service.component.annotations.Component;

/**
 * ViewModelProvider for legacy sections (XWT Sections). Converts the *.xwt
 * Model to a ViewModel. Used for migration purposes (e.g. rendering an existing
 * XWT Section with a non-XWT Renderer)
 */
@Component
public class XWTViewModelProvider implements ViewModelProvider {

	@Override
	public ViewModel getViewModel(Section section, View view) {
		CompositeWidget widget = section.getWidget();
		if (widget == null) {
			return null;
		}

		ViewModel vm = ViewmodelFactory.eINSTANCE.createViewModel();
		VerticalLayout vmRoot = ViewmodelFactory.eINSTANCE.createVerticalLayout();
		vm.getChildren().add(vmRoot);

		TreeIterator<EObject> eAllContents = widget.eAllContents();
		
		Map<CompositeWidget, Parent> xwtToVMContainer = new HashMap<>();
		xwtToVMContainer.put(widget, vmRoot);
		
		
		// Simple transformation that maps CompositeWidget to Parent and PropertyEditor to Control
		// Custom widgets used by PropertyEditors are ignored (TODO: Convert them to rendererHint?)
		// Standard Widgets (e.g. Label) are ignored
		while (eAllContents.hasNext()) {
			EObject next = eAllContents.next();
			if (next instanceof CompositeWidget) {
				CompositeWidget composite = (CompositeWidget) next;
				Layout layout = composite.getLayout();
				LayoutType layoutType = layout.getLayoutType();
				switch (layoutType.getWidgetClass()) {
				case "PropertiesLayout":
					int numColumns = layout.getAttributes().stream()
						.filter(ValueAttribute.class::isInstance)
						.map(ValueAttribute.class::cast)
						.filter(attr -> attr.getName().equals("numColumns"))
						.map(ValueAttribute::getValue)
						.map(Integer::parseInt)
						.findFirst().orElse(-1);
					
					Parent vmLayout;
					if (numColumns > 1) {
						GridLayout gridLayout = ViewmodelFactory.eINSTANCE.createGridLayout();
						gridLayout.setColumns(numColumns);
						vmLayout = gridLayout;
					} else {
						vmLayout = ViewmodelFactory.eINSTANCE.createVerticalLayout();
					}
					
					xwtToVMContainer.put(composite, vmLayout);
					Parent vmParent = xwtToVMContainer.get((CompositeWidget)composite.eContainer());
					vmParent.getChildren().add(vmLayout);
					break;
				default:
					System.err.println("Unsupported layout: " + layoutType.getNamespace().getValue() + ":"
							+ layoutType.getWidgetClass() + ". Falling back to default VerticalLayout");
					break;
				}
			} else if (next instanceof PropertyEditor) {
				PropertyEditor propertyEditor = (PropertyEditor)next;
				Control control = ViewmodelFactory.eINSTANCE.createControl();
				Property property = propertyEditor.getProperty();
				control.setProperty(getPropertyName(property));
				control.setRendererHint(property.getType().getLiteral());
				
				Parent controlParent = xwtToVMContainer.get(propertyEditor.eContainer());
				controlParent.getChildren().add(control);
			} else if (next instanceof Layout || next instanceof WidgetAttribute) {
				// Silently ignore
			} else {
				System.err.println("Ignoring unsupported XWT Element: " + next);
				eAllContents.prune();
			}
		}


		return vm;
	}

	private String getPropertyName(Property property) {
		StringBuilder builder = new StringBuilder(property.getName());
		DataContextElement element = property.getContextElement();
		while (element != null) {
			builder.insert(0, element.getName()+":");
			element = element.getPackage();
		}
		return builder.toString();
	}

	@Override
	public double getPriority(Section section, View view) {
		return section.getWidget() == null ? Double.NaN : Double.MIN_VALUE;
	}

}
