package org.eclipse.papyrus.navigator.internal.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.core.editor.BackboneContext;
import org.eclipse.papyrus.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.core.utils.DiResourceSet;
import org.eclipse.papyrus.core.utils.EditorUtils;
import org.eclipse.papyrus.sasheditor.contentprovider.di.IPageMngr;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Utility method for Model Navigator.
 * 
 * @author <a href="mailto:jerome.benois@obeo.fr">Jerome Benois</a>
 * @author <a href="mailto:fjcano@prodevelop.es">Francisco Javier Cano Muñoz</a>
 **/
public class NavigatorUtils {

	/**
	 * @return Get the current {@link IMultiDiagramEditor}
	 */
	public static IMultiDiagramEditor getMultiDiagramEditor() {
		IEditorPart editor = getActiveEditor();
		if (editor instanceof IMultiDiagramEditor) {
			IMultiDiagramEditor multiDiagramEditor = (IMultiDiagramEditor) editor;
			return multiDiagramEditor;
		}
		return null;
	}

	/**
	 * @return Get the current {@link BackboneContext}
	 */
	public static BackboneContext getBackboneContext() {
		IMultiDiagramEditor multiDiagramEditor = getMultiDiagramEditor();
		if (multiDiagramEditor != null) {
			BackboneContext backboneContext = multiDiagramEditor
					.getDefaultContext();
			return backboneContext;
		}
		return null;
	}

	/**
	 * @return Get the current {@link DiResourceSet}
	 */
	public static DiResourceSet getDiResourceSet() {
		BackboneContext backboneContext = getBackboneContext();
		if (backboneContext != null) {
			return backboneContext.getResourceSet();
		}
		return null;
	}

	/**
	 * @return Get the current {@link TransactionalEditingDomain}
	 */
	public static TransactionalEditingDomain getTransactionalEditingDomain() {
		BackboneContext backboneContext = getBackboneContext();
		if (backboneContext != null) {
			return backboneContext.getTransactionalEditingDomain();
		}
		return null;
	}

	/**
	 * Gets the active <IEditorPart>.
	 * 
	 * @return the active editor
	 */
	public static IEditorPart getActiveEditor() {
		IWorkbench wb = PlatformUI.getWorkbench();
		if (wb != null) {
			IWorkbenchWindow ww = wb.getActiveWorkbenchWindow();
			if (ww != null) {
				IWorkbenchPage wp = ww.getActivePage();
				if (wp != null) {
					return wp.getActiveEditor();
				}
			}
		}
		return null;
	}

	/**
	 * Find a <IViewPart> by it's id string.
	 * 
	 * @param viewID
	 *            the view id
	 * 
	 * @return the i view part
	 */
	public static IViewPart findViewPart(String viewID) {
		try {
			IViewReference reference = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.findViewReference(viewID);
			IWorkbenchPart part = reference.getPart(false);
			if (part instanceof IViewPart) {
				return (IViewPart) part;
			} else {
				return null;
			}
		} catch (NullPointerException ex) {
			return null;
		}
	}

	/**
	 * Unwraps selection. Gets <EObject>s from <EditPart>s, from <View>s or from
	 * <EObject>s
	 * 
	 * @param selection
	 *            the selection
	 * 
	 * @return the i selection
	 */
	public static ISelection unwrapSelection(ISelection selection) {
		if (selection instanceof StructuredSelection && !selection.isEmpty()) {
			List<EObject> selectionList = new ArrayList<EObject>();
			StructuredSelection structuredSelection = (StructuredSelection) selection;
			for (Iterator<?> iterator = structuredSelection.iterator(); iterator
					.hasNext();) {
				Object next = iterator.next();
				if (next instanceof EditPart) {
					Object model = ((EditPart) next).getModel();
					EObject element = null;
					if (model instanceof View) {
						element = ((View) model).getElement();
					} else if (model instanceof EObject) {
						element = (EObject) model;
					}
					if (element != null) {
						selectionList.add(element);
					}
				} else if (next instanceof View) {
					EObject element = ((View) next).getElement();
					if (element != null) {
						selectionList.add(element);
					}
				} else if (next instanceof EObject) {
					selectionList.add((EObject) next);
				}
			}
			return new StructuredSelection(selectionList);
		} else
			return selection;
	}

	/**
	 * Finds the <EditPart>s for the <EObject>s in the selection.
	 * 
	 * @param selection
	 *            the selection
	 * @param viewer
	 *            the viewer
	 * 
	 * @return the edits the parts from selection
	 */
	public static List<EditPart> getEditPartsFromSelection(
			ISelection selection, IDiagramGraphicalViewer viewer) {
		if (selection instanceof StructuredSelection && !selection.isEmpty()) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;
			// look for Views of the EObjects in the selection
			List<View> views = new ArrayList<View>();
			for (Object o : structuredSelection.toList()) {
				if (o instanceof EObject) {
					List<Object> referencerViews = getEObjectViews((EObject) o);
					for (Object ro : referencerViews) {
						if (ro instanceof View) {
							views.add((View) ro);
						}
					}
				}
			}
			if (!views.isEmpty()) {
				List<EditPart> editParts = new ArrayList<EditPart>();
				for (View view : views) {
					Object ep = viewer.getEditPartRegistry().get(view);
					if (ep instanceof EditPart) {
						editParts.add((EditPart) ep);
					}
				}
				if (!editParts.isEmpty()) {
					return editParts;
				}
			}
		}
		return Collections.emptyList();
	}

	/**
	 * Gets the given <EObject> views.
	 * 
	 * @param element
	 *            the element
	 * 
	 * @return the e object views
	 */
	public static List<Object> getEObjectViews(EObject element) {
		List<Object> views = new ArrayList<Object>();
		if (element != null) {
			EReference[] features = { NotationPackage.eINSTANCE
					.getView_Element() };
			Collection<?> referencers = EMFCoreUtil.getReferencers(element,
					features);
			views.addAll(referencers);
		}
		return views;
	}

	// //
	// get an object name
	// //

	/**
	 * Gets the object name or empty string.
	 * 
	 * @param object
	 *            the object
	 * 
	 * @return the object name or empty string
	 */
	public static String getObjectNameOrEmptyString(Object object) {
		String name = getObjectName(object);
		return name == null ? "" : name;
	}

	/** The Constant getNameNames. */
	private static final String[] getNameNames = { "getName", "getname" };

	/**
	 * Gets the object name.
	 * 
	 * @param object
	 *            the object
	 * 
	 * @return the object name
	 */
	public static String getObjectName(Object object) {
		if (object == null) {
			return null;
		}
		Method method = null;
		Object o = null;
		for (String methodName : getNameNames) {
			try {
				method = object.getClass()
						.getMethod(methodName, (Class[]) null);
			} catch (NoSuchMethodException e) {
				method = null;
			}
			if (method != null) {
				break;
			}
		}
		if (method != null) {
			try {
				o = method.invoke(object, (Object[]) null);
			} catch (IllegalAccessException ex) {
				return null;
			} catch (InvocationTargetException ex) {
				return null;
			}
			if (o instanceof String) {
				return (String) o;
			}
		}
		return null;
	}

	/**
	 * Opens a {@link Diagram} in the sash editor.
	 * 
	 * @param diagram
	 */
	public static void openDiagram(Diagram diagram) {
		IPageMngr pageManager = EditorUtils.getIPageMngr();
		if (pageManager != null) {
			pageManager.openPage(diagram);
		}
	}

	/**
	 * Opens a view part in the workbench with the specified ID.
	 * 
	 * @param viewPartID
	 */
	public static void openViewPart(String viewPartID) {
		if (viewPartID == null) {
			return;
		}
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(viewPartID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens the property sheets view.
	 */
	public static void openPropertySheetsView() {
		openViewPart("org.eclipse.ui.views.PropertySheet");
	}
}
