/*****************************************************************************
 * Copyright (c) 2010 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.common.groups.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.diagram.common.command.wrappers.GEFtoEMFCommandWrapper;
import org.eclipse.papyrus.diagram.common.groups.core.groupcontainment.GroupContainmentRegistry;
import org.eclipse.papyrus.diagram.common.groups.core.ui.ChooseContainedElementsCreator.ChildSelection;
import org.eclipse.papyrus.diagram.common.groups.groupcontainment.AbstractContainerNodeDescriptor;
import org.eclipse.papyrus.diagram.common.groups.utils.GraphicalAndModelElementComparator;
import org.eclipse.papyrus.diagram.common.groups.utils.GraphicalAndModelElementComparator.Mode;


/**
 * This class provides utility methods useful for the group framework.
 * 
 * @author vhemery and adaussy
 */
public class Utils {

	/**
	 * Find the containers which may contain an edit part in the given bounds.
	 * 
	 * @param bounds
	 *        the new or old bounds of the item
	 * @param diagramPart
	 *        the diagram edit part
	 * @return the list of edit parts that are registered through the group framework and that can contain an element within the given bounds
	 */
	public static List<IGraphicalEditPart> findPossibleParents(Rectangle bounds, DiagramEditPart diagramPart) {
		if(diagramPart == null) {
			return Collections.emptyList();
		}
		Set<IGraphicalEditPart> groupParts = new HashSet<IGraphicalEditPart>();
		//For all object in diagram, find edit parts
		for(Object view : diagramPart.getViewer().getEditPartRegistry().keySet()) {
			if(view instanceof View) {
				Object editpart = diagramPart.getViewer().getEditPartRegistry().get(view);
				if(editpart instanceof IGraphicalEditPart) {
					IGraphicalEditPart part = (IGraphicalEditPart)editpart;
					//If this group is handled by the group framework
					if(GroupContainmentRegistry.isContainerConcerned(part)) {
						//recover group descriptor of this part
						AbstractContainerNodeDescriptor desc = GroupContainmentRegistry.getContainerDescriptor(part);
						//Look if I can get the Eclass from the IdoneContainer
						//And I look if its contain the element we want it to be compared with
						if(desc.getContentArea(part).contains(bounds)) {
							groupParts.add(part);
						}
					}
				}
			}
		}
		return new ArrayList<IGraphicalEditPart>(groupParts);
	}

	/**
	 * Find containers which may be chosen as graphical and as model parent of the element which has already been created
	 * 
	 * @param graphicalParentsToComplete
	 *        an empty list that will be filled with edits part of available graphical parents (e.g. new ArrayList())
	 * @param modelParentsToComplete
	 *        an empty list that will be filled with edits part of available graphical parents (e.g. new ArrayList())
	 * @param childPart
	 *        Edit part of the element we want to find out which may be its containers
	 * @return true if succeed
	 */
	@SuppressWarnings("unchecked")
	public static boolean createComputedListsOfParents(List<IGraphicalEditPart> graphicalParentsToComplete, List<IGraphicalEditPart> modelParentsToComplete, IGraphicalEditPart childPart) {
		Collection<View> diagramViews = new ArrayList<View>(childPart.getViewer().getEditPartRegistry().keySet());
		diagramViews.remove(childPart.getModel());
		Rectangle bounds = null;
		EClass childType = null;
		if(childPart != null) {
			bounds = childPart.getFigure().getBounds();
			childType = ((View)childPart.getModel()).eClass();
		}

		return createComputedListsOfParents(graphicalParentsToComplete, modelParentsToComplete, bounds, childType, diagramViews, childPart);
	}

	/**
	 * Find containers which may be chosen as graphical and as model parent of the element
	 * 
	 * @param graphicalParentsToComplete
	 *        an empty list that will be filled with edits part of available graphical parents (e.g. new ArrayList())
	 * @param modelParentsToComplete
	 *        an empty list that will be filled with edits part of available graphical parents (e.g. new ArrayList())
	 * @param creationRequest
	 *        request of creation
	 * @param anyPart
	 *        An edit part to get the viewer
	 * @param child
	 *        The EClass of the element to create
	 * @param elementName
	 *        Name of the element to be created (name used to look for default size FIXME)
	 * @return true if succeed
	 */
	@SuppressWarnings("unchecked")
	public static boolean createComputedListsOfParents(List<IGraphicalEditPart> graphicalParentsToComplete, List<IGraphicalEditPart> modelParentsToComplete, CreateViewAndElementRequest creationRequest, IGraphicalEditPart anyPart, EClass child, String ElementTypeName) {
		Collection<View> diagramViews = new ArrayList<View>(anyPart.getViewer().getEditPartRegistry().keySet());
		Dimension size = creationRequest.getSize();
		//FIXME : Add a correct default size
		// If size == null then a default size is used to create the bounds of the new elements
		if(size == null || size.isEmpty()) {
			//			IPreferenceStore store = Activator.getDefault().getPreferenceStore();
			//			String prefWidthName = PreferenceConstantHelper.getElementConstant(ElementTypeName, PreferenceConstantHelper.WIDTH);
			//			String prefHeightName = PreferenceConstantHelper.getElementConstant(ElementTypeName, PreferenceConstantHelper.HEIGHT);
			//			int width = store.getInt(prefWidthName);
			//			int height = store.getInt(prefHeightName);
			//			size = new Dimension(width, height);
			size = new Dimension(0, 0);
		}
		Rectangle bounds = new Rectangle(creationRequest.getLocation(), size);
		return createComputedListsOfParents(graphicalParentsToComplete, modelParentsToComplete, bounds, child, diagramViews, anyPart);
	}

	/**
	 * Find containers which may be chosen as graphical and as model parent of the element to create
	 * 
	 * @param graphicalParentsToComplete
	 *        an empty list that will be filled with edits part of available graphical parents (e.g. new ArrayList())
	 * @param modelParentsToComplete
	 *        an empty list that will be filled with edits part of available model parents e.g. new ArrayList())
	 * @param bounds
	 *        Bounds of the element
	 * @param request
	 *        createElementRequest of the current request
	 * @param views
	 *        Collection of view to iteration on
	 * @param anyPart
	 *        an edit part of the diagram to get the viewer
	 * @return true if successful
	 */
	private static boolean createComputedListsOfParents(List<IGraphicalEditPart> graphicalParentsToComplete, List<IGraphicalEditPart> modelParentsToComplete, Rectangle bounds, EClass child, Collection<View> views, IGraphicalEditPart anyPart) {
		if(views.isEmpty()) {
			return false;
		}
		for(Object view : views) {
			if(view instanceof View) {
				Object editpart = anyPart.getViewer().getEditPartRegistry().get(view);
				if(editpart instanceof IGraphicalEditPart) {
					IGraphicalEditPart part = (IGraphicalEditPart)editpart;
					if(GroupContainmentRegistry.isContainerConcerned(part)) {
						AbstractContainerNodeDescriptor desc = GroupContainmentRegistry.getContainerDescriptor(part);
						//Check if the current part contains the element
						//FIXME replace this piece of code by isItVisualyContained
						if(desc.getContentArea(part).contains(bounds)) {
							if(child instanceof EClass) {
								if(desc.canIBeModelParentOf(child)) {
									// If an edit part can be a model parent then is also a possible graphical parent
									graphicalParentsToComplete.add(part);
									modelParentsToComplete.add(part);
								} else {
									if(desc.canIBeGraphicalParentOf(child)) {
										graphicalParentsToComplete.add(part);
									}
								}
							}
						}
					}
				}
			}
		}
		//Try to reduce the number of available parents by removing
		if(graphicalParentsToComplete.size() > 1)
			withdrawUselessAncestorsElements(graphicalParentsToComplete, Mode.GRAPHICAL_AND_MODEL);
		if(modelParentsToComplete.size() > 1)
			withdrawUselessAncestorsElements(modelParentsToComplete, Mode.MODEL);
		//FIXME Sort the two list in order to have the most relevant parent first (lowest surface )
		return true;
	}

	/**
	 * This method complete the list childsToComplete to add element which are visually contained in the element in creation "parent" and which can
	 * be graphical children of this new elements
	 * 
	 * @param childsToComplete
	 *        Empty list which will contain in the newly created element "parent" and which can be graphical children of this new elements
	 * @param creationRequest
	 *        request of creation
	 * @param anyPart
	 *        An edit part to get the viewer
	 * @param descriptor
	 *        Descriptor of the element in creation
	 * @param ElementTypeName
	 *        Name of the element to be created (name used to look for default size FIXME)
	 * @return true if succeed
	 */
	public static boolean createComputedListsOfVisualYRelatedElements(List<IGraphicalEditPart> childsToComplete, CreateViewAndElementRequest creationRequest, IGraphicalEditPart anyPart, AbstractContainerNodeDescriptor descriptor, String ElementTypeName) {
		Collection<View> diagramViews = new ArrayList<View>(anyPart.getViewer().getEditPartRegistry().keySet());
		Dimension size = creationRequest.getSize();
		//FIXME : Add a correct default size
		// If size == null then a default size is used to create the bounds of the new elements
		if(size == null || size.isEmpty()) {
			//			IPreferenceStore store = Activator.getDefault().getPreferenceStore();
			//			String prefWidthName = PreferenceConstantHelper.getElementConstant(ElementTypeName, PreferenceConstantHelper.WIDTH);
			//			String prefHeightName = PreferenceConstantHelper.getElementConstant(ElementTypeName, PreferenceConstantHelper.HEIGHT);
			//			int width = store.getInt(prefWidthName);
			//			int height = store.getInt(prefHeightName);
			//			size = new Dimension(width, height);
			size = new Dimension(0, 0);
		}
		Rectangle bounds = new Rectangle(creationRequest.getLocation(), size);
		createComputedListsOfVisualyRelatedElements(childsToComplete, bounds, descriptor, diagramViews, anyPart);
		return true;
	}

	/**
	 * Find containers which may be chosen as graphical and as model parent of the element
	 * 
	 * @param childsToComplete
	 *        Empty list which will contain in the newly created element "parent" and which can be graphical children of this new elements
	 * @param parentsBounds
	 *        Bounds of the element
	 * @param descriptors
	 *        Descriptor of the element in creation
	 * @param views
	 *        Collection of view to iteration on
	 * @param anyPart
	 *        an edit part of the diagram to get the viewer
	 * @return true if succeed
	 */
	private static boolean createComputedListsOfVisualyRelatedElements(List<IGraphicalEditPart> childsToComplete, Rectangle parentsBounds, AbstractContainerNodeDescriptor descriptor, Collection<View> views, IGraphicalEditPart anyPart) {
		/*
		 * 1 - Compute the EClass(s) which the element can be parent of : listEclass
		 * 2 - Iterate on views : v
		 * 2.1 - If v correspond to a main editPart : part
		 * 2.1.1 - If v correspond to a a EClass which belong to listEclass
		 * 2.1.1.1 - If part is visually contained is the parent then add to the list
		 * 3 - Withdraw useless elements
		 */
		//Set<AbstractContainerNodeDescriptor> descriptors = GroupContainmentRegistry.getDescriptorsWithContainerEClass(parentEclass);
		List<EClass> possibleChildrenEClass = new ArrayList<EClass>(descriptor.getPossibleGraphicalChildren());
		if(!possibleChildrenEClass.isEmpty()) {
			for(Object view : views) {
				if(view instanceof View) {
					View childView = (View)view;
					EClass childEclass = (childView.getElement().eClass());
					Object editpart = anyPart.getViewer().getEditPartRegistry().get(childView);
					if(editpart instanceof IGraphicalEditPart) {
						IGraphicalEditPart part = (IGraphicalEditPart)editpart;
						if(isMainEditPart(part)) {
							for(EClass possibleChild : possibleChildrenEClass) {
								if(possibleChild.isSuperTypeOf(childEclass)) {
									if(isItVisualyContained(parentsBounds, part)) {
										childsToComplete.add(part);
										break;
									}
								}
							}
						}
					}
				}
			}
			if(childsToComplete.size() > 1)
				withdrawUselessDescendantElements(childsToComplete, Mode.GRAPHICAL_AND_MODEL);
		}

		return true;
	}

	/**
	 * This methods is used to know if an edit part is contained in a rectangle
	 * 
	 * @param parentBounds
	 *        Rectangle of the parent
	 * @param child
	 *        IGraphicalEditPart of the element we want to test
	 * @return true if the bounds of child are contained in parentsBounds
	 */
	private static boolean isItVisualyContained(Rectangle parentBounds, IGraphicalEditPart child) {

		if(child.getParent() instanceof IGraphicalEditPart) {
			// an edit part is considered the "main" edit part of an element if it is not contained in an edit part of the same element (e.g. not a label nor a compartment)
			Rectangle bounds = child.getFigure().getBounds().getCopy();
			child.getFigure().translateToAbsolute(bounds);
			if(bounds != null) {
				if(parentBounds.contains(bounds)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Return true if the edit part is the main editPart of a model element (e.g the label is not the main edit part of an activityPartition
	 * 
	 * @param part
	 *        IGraphicalEditPart to test
	 * @return
	 */
	public static boolean isMainEditPart(IGraphicalEditPart part) {
		EObject currentElement = part.resolveSemanticElement();
		EditPart parentEditPart = part.getParent();
		if(parentEditPart instanceof IGraphicalEditPart) {
			return !currentElement.equals(((IGraphicalEditPart)parentEditPart).resolveSemanticElement());
		} else {
			return true;
		}
	}

	/**
	 * This method remove all elements from the list which have a parent in the (graphical model) or model in the list
	 * 
	 * @param listToModify
	 *        List of elements (IGraphicalEditPart
	 * @param mode
	 *        if GraphicalAndModelElementComparator.MODEL then it only take care of model parent if
	 *        GraphicalAndModelElementComparator.GRAPHICAL_AND_MODEL it take care of graphical and logical relationship
	 * @return true if succeed
	 */
	private static void withdrawUselessAncestorsElements(List<IGraphicalEditPart> listToModify, Mode mode) {
		if(!listToModify.isEmpty()) {

			GraphicalAndModelElementComparator comparator = new GraphicalAndModelElementComparator(listToModify.get(0));
			//Select the comparator mode

			comparator.setMode(mode);

			/**
			 * Keep in the list only elements which which have no smaller element ( with this comparator)
			 * 
			 */
			for(int element = 0; element < listToModify.size(); element++) {
				for(int elementToCompare = element + 1; elementToCompare < listToModify.size(); elementToCompare++) {
					int compare = comparator.compare((IGraphicalEditPart)listToModify.get(element), (IGraphicalEditPart)listToModify.get(elementToCompare));
					if(compare < 0) {
						listToModify.remove(element);
						element--;
						elementToCompare = listToModify.size();

					} else if(compare > 0) {
						listToModify.remove(elementToCompare);
						elementToCompare--;
					}
				}
			}
		}
	}

	/**
	 * This method will withdraw all EObject directly referenced by object which are also referenced by one of its parents (parent by reference and
	 * parent by containment)
	 * 
	 * @param object
	 */
	public static void withDrawRedundantElementReferenced(EObject object) {
		/*
		 * Algo :
		 * 1 - Select all references which are Group Framework concerned and which represent a reference to a parent
		 * 2 - Create a Set directlyReferencedByElement of EObject directly referenced by object
		 * 3 - Iterate thought parents to see if one of them also point at an element of directlyReferencedByElement
		 */
		/*
		 * 1 - Select all references which are Group Framework concerned and which represent a reference to a parent
		 */
		Set<EReference> groupFrameworkReferences = GroupContainmentRegistry.getAllERefencesFromNodeToGroup();
		HashMap<EObject, EReference> referencingGroupsAndTheirRelation = new HashMap<EObject, EReference>();
		Set<EObject> elementToVosit = new HashSet<EObject>();
		for(EReference ref : groupFrameworkReferences) {
			if(object.eClass().getEAllReferences().contains(ref)) {
				// list of groups containing the object
				List<EObject> groups;
				if(ref.isMany()) {
					groups = (List<EObject>)object.eGet(ref);
				} else {
					groups = Collections.singletonList((EObject)object.eGet(ref));
				}
				if(!ref.isContainment()) {
					/*
					 * 2 - Create a Set directlyReferencedByElement of EObject directly referenced by object
					 */
					for(EObject element : groups) {
						if(!referencingGroupsAndTheirRelation.containsKey(element) && !ref.isDerived()) {
							referencingGroupsAndTheirRelation.put(element, ref);
						}
					}
				}
				for(EObject group : groups) {
					if(group != null) {
						elementToVosit.add(group);
					}
				}
			}
		}
		Set<EObject> elementAlreadyVisited = new HashSet<EObject>();
		for(EObject visitingElement : elementToVosit) {
			withDrawRedundantElementReferenced(object, groupFrameworkReferences, referencingGroupsAndTheirRelation, elementAlreadyVisited, visitingElement);
		}
	}

	/**
	 * This method will withdraw all EObject directly referenced by object which are also referenced by one of its parents (parent by reference and
	 * parent by containment). This method is called recursively
	 * 
	 * @param originalEObject
	 *        The EObject on which you want to check the reference
	 * @param groupFrameworkReferences
	 *        All the EReference which are used on the groupFramework and which represent a relation from a element to its parent
	 * @param directlyReferencedByElement
	 *        Set of elements which are directly referenced by the original element
	 * @param elementAlreadyVisited
	 *        Set of element already visited. Used to avoid infinite loop
	 * @param visitingElement
	 *        The current element which is being visited
	 */
	private static void withDrawRedundantElementReferenced(EObject originalEObject, Set<EReference> groupFrameworkReferences, Map<EObject, EReference> directlyReferencedByElement, Set<EObject> elementAlreadyVisited, EObject visitingElement) {
		if(visitingElement != null) {
			elementAlreadyVisited.add(visitingElement);
			for(EReference ref : groupFrameworkReferences) {
				if(visitingElement != null) {
					if(visitingElement.eClass().getEAllReferences().contains(ref)) {
						List<EObject> groups;
						if(ref.isMany()) {
							groups = (List<EObject>)visitingElement.eGet(ref);
						} else {
							groups = Collections.singletonList((EObject)visitingElement.eGet(ref));
						}
						for(EObject currentElementParentGroup : groups) {
							//If it belong to the directly referenced element then
							if(directlyReferencedByElement.containsKey(currentElementParentGroup)) {
								withdrawEObjectFromReference(originalEObject, currentElementParentGroup, directlyReferencedByElement.get(currentElementParentGroup));
								// parents already handled in the first recursion (as direct parent group)
							} else if(elementAlreadyVisited.contains(currentElementParentGroup)) {
								// element already met, avoid infinite loop
								org.eclipse.papyrus.diagram.common.groups.Activator.getDefault().getLog().log(new Status(Status.WARNING, org.eclipse.papyrus.diagram.common.groups.Activator.PLUGIN_ID, "There is a circle element reference"));
							} else {
								//else iterate recursively also on this group's parents
								withDrawRedundantElementReferenced(originalEObject, groupFrameworkReferences, directlyReferencedByElement, elementAlreadyVisited, currentElementParentGroup);
								//elementToVosit.add(currentCompareElement);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * This method is used to with an element of a reference
	 * 
	 * @param source
	 *        EObject from which the reference start
	 * @param destination
	 *        EObject to which the reference start
	 * @param ref
	 *        EReference
	 */
	private static void withdrawEObjectFromReference(EObject source, EObject destination, EReference ref) {
		if(ref != null && source != null && destination != null) {
			if(ref != null && ref.isMany()) {
				Collection<EObject> collection = (Collection<EObject>)source.eGet(ref);
				if(collection.contains(destination)) {
					collection.remove(destination);
				}
			} else if(ref != null && !ref.isMany()) {
				source.eUnset(ref);
			}
		}
	}

	/**
	 * This method remove all elements from the list which have a descendant in the (graphical model) or model in the list
	 * 
	 * @param listToModify
	 *        List of elements (IGraphicalEditPart
	 * @param mode
	 *        if GraphicalAndModelElementComparator.MODEL then it only take care of model parent if
	 *        GraphicalAndModelElementComparator.GRAPHICAL_AND_MODEL it take care of graphical and logical relationship
	 * @return true if succeed
	 */
	private static boolean withdrawUselessDescendantElements(List<IGraphicalEditPart> listToModify, Mode mode) {

		if(!listToModify.isEmpty()) {

			GraphicalAndModelElementComparator comparator = new GraphicalAndModelElementComparator(listToModify.get(0));
			//Select the comparator mode
			comparator.setMode(mode);
			for(int element = 0; element < listToModify.size(); element++) {
				for(int elementToCompare = element + 1; elementToCompare < listToModify.size(); elementToCompare++) {
					int compare = comparator.compare((IGraphicalEditPart)listToModify.get(element), (IGraphicalEditPart)listToModify.get(elementToCompare));
					if(compare > 0) {
						listToModify.remove(element);
						element--;
						elementToCompare = listToModify.size();

					} else if(compare < 0) {
						listToModify.remove(elementToCompare);
						elementToCompare--;
					}
				}
			}
		}
		return true;
	}





	/**
	 * Find the children edit parts which may be contained by the group in the given bounds.
	 * 
	 * @param contentArea
	 *        the new or old content area the group
	 * @param groupEditPart
	 *        the group edit part
	 * @param diagramPart
	 *        the diagram edit part
	 * @return the list of edit parts that are within the given bounds and which element can be children according to the group framework
	 */
	public static List<IGraphicalEditPart> findPossibleChildren(Rectangle contentArea, IGraphicalEditPart groupEditPart, DiagramEditPart diagramPart) {
		AbstractContainerNodeDescriptor descriptor = GroupContainmentRegistry.getContainerDescriptor(groupEditPart);
		if(diagramPart == null || descriptor == null) {
			return Collections.emptyList();
		}
		Set<IGraphicalEditPart> groupParts = new HashSet<IGraphicalEditPart>();
		for(Object view : diagramPart.getViewer().getEditPartRegistry().keySet()) {
			if(view instanceof View) {
				Object editpart = diagramPart.getViewer().getEditPartRegistry().get(view);
				if(editpart instanceof IGraphicalEditPart && editpart instanceof IPrimaryEditPart && !groupEditPart.equals(editpart)) {
					IGraphicalEditPart part = (IGraphicalEditPart)editpart;
					// check bounds
					boolean boundsOK = false;
					if(groupEditPart.getChildren().contains(editpart)) {
						// graphically contained part will follow the move.
						boundsOK = true;
					} else {
						Rectangle figBounds = part.getFigure().getBounds().getCopy();
						part.getFigure().translateToAbsolute(figBounds);
						if(contentArea.contains(figBounds)) {
							boundsOK = true;
						}
					}
					if(boundsOK) {
						// check group can contain
						EObject child = part.resolveSemanticElement();
						for(EReference refToChildren : descriptor.getChildrenReferences()) {
							if(refToChildren.getEReferenceType().isInstance(child)) {
								groupParts.add(part);
								break;
							}
						}
					}
				}
			}
		}
		return new ArrayList<IGraphicalEditPart>(groupParts);
	}

	// Debug purpose
	public static void drawRect(IGraphicalEditPart editPart, Rectangle refContentArea) {
		RoundedRectangle rectFeedback = new RoundedRectangle();
		rectFeedback.setBounds(refContentArea);
		rectFeedback.setCornerDimensions(new Dimension(0, 0));
		rectFeedback.setLineWidth(2);
		rectFeedback.setLineStyle(Graphics.LINE_DASH);
		rectFeedback.setForegroundColor(editPart.getFigure().getForegroundColor());
		rectFeedback.setOpaque(true);
		rectFeedback.setFill(false);

		IFigure layer = LayerManager.Helper.find(editPart).getLayer(LayerConstants.FEEDBACK_LAYER);
		layer.add(rectFeedback);
	}

	/**
	 * Get the command to change the graphical parent of an element
	 * 
	 * @param childPart
	 *        the child edit part to change parent
	 * @param newParent
	 *        the new graphical parent
	 * @return the command or null if no effect
	 */
	public static Command getUpdateGraphicalParentCmd(IGraphicalEditPart childPart, IGraphicalEditPart newParent) {
		if(childPart.getParent().equals(newParent)) {
			return null;
		}
		ChangeBoundsRequest request = new ChangeBoundsRequest();
		request.setMoveDelta(new Point(0, 0));
		request.setSizeDelta(new Dimension(0, 0));
		request.setEditParts(childPart);
		Point loc = childPart.getFigure().getBounds().getLocation().getCopy();
		childPart.getFigure().translateToAbsolute(loc);
		request.setLocation(loc);
		request.setType(RequestConstants.REQ_DROP);
		org.eclipse.gef.commands.Command cmd = newParent.getCommand(request);
		if(cmd != null && cmd.canExecute()) {
			return new GEFtoEMFCommandWrapper(cmd);
		} else {
			return null;
		}
	}

	/**
	 * Get the command to add a reference from a parent group edit part to its new child
	 * 
	 * @param newParentpart
	 *        the new parent edit part which contains children by reference
	 * @param newChildPart
	 *        the child edit part
	 * @return the command or null
	 */
	public static Command getAddReferenceToChildCmd(IGraphicalEditPart newParentpart, IGraphicalEditPart newChildPart) {
		AbstractContainerNodeDescriptor desc = GroupContainmentRegistry.getContainerDescriptor(newParentpart);
		EObject parent = newParentpart.resolveSemanticElement();
		EObject child = newChildPart.resolveSemanticElement();
		// get the better child reference to use
		EReference usedReference = getBestReferenceAmongList(desc.getChildrenReferences(), child);
		if(usedReference != null) {
			return new AddCommand(newParentpart.getEditingDomain(), parent, usedReference, child);
		} else {
			// no possible child reference
			return null;
		}
	}

	/**
	 * Get the best reference (nearest to child's type) to a child among the list
	 * 
	 * @param childrenReferences
	 *        the possible children references
	 * @param child
	 *        the child eobject to choose a referencefor
	 * @return the most precise child reference or null
	 */
	public static EReference getBestReferenceAmongList(List<EReference> childrenReferences, EObject child) {
		EReference usedReference = null;
		for(EReference ref : childrenReferences) {
			if(ref.getEReferenceType().isInstance(child)) {
				if(usedReference == null || ref.getEReferenceType().getEAllSuperTypes().contains(usedReference.getEReferenceType())) {
					// the ref feature is more precise than the previously selected one. Use it instead.
					usedReference = ref;
				}
			}
		}
		return usedReference;
	}

	/**
	 * Get the command to remove a reference from a parent group edit part to its old child
	 * 
	 * @param oldParentpart
	 *        the old parent edit part which contains children by reference
	 * @param oldChildPart
	 *        the child edit part
	 * @return the command or null
	 */
	public static Command getRemoveReferenceToChildCmd(IGraphicalEditPart oldParentpart, IGraphicalEditPart oldChildPart) {
		AbstractContainerNodeDescriptor desc = GroupContainmentRegistry.getContainerDescriptor(oldParentpart);
		EObject parent = oldParentpart.resolveSemanticElement();
		EObject child = oldChildPart.resolveSemanticElement();
		CompoundCommand globalCmd = new CompoundCommand();
		// get the better child reference to use
		EReference usedReference = null;
		for(EReference ref : desc.getChildrenReferences()) {
			if(parent.eGet(ref) instanceof List<?>) {
				if(((List<?>)parent.eGet(ref)).contains(child)) {
					// remove the reference to the child
					Command cmd = new RemoveCommand(oldParentpart.getEditingDomain(), parent, usedReference, child);
					if(cmd.canExecute()) {
						globalCmd.append(cmd);
					}
				}
			}
		}
		if(!globalCmd.isEmpty()) {
			return globalCmd;
		} else {
			return null;
		}
	}

	/**
	 * Construct a map ready to use for ChooseContainedElementsCreator, which indicate children selection state for a group
	 * 
	 * @param group
	 *        the parent group
	 * @param children
	 *        the group children
	 * @return the selection map of children
	 */
	public static Map<IGraphicalEditPart, ChildSelection> contructSelectionMapForGroupChildren(IGraphicalEditPart group, List<IGraphicalEditPart> children) {
		Map<IGraphicalEditPart, ChildSelection> map = new HashMap<IGraphicalEditPart, ChildSelection>(children.size());
		for(IGraphicalEditPart child : children) {
			IGraphicalEditPart oldGraphicalContainer = (IGraphicalEditPart)child.getParent();
			if(!GroupContainmentRegistry.isContainerConcerned(oldGraphicalContainer)) {
				/*
				 * The child is not handled by any group yet :
				 * the group becomes the new parent if it is a model container,
				 * otherwise, the user can decide
				 */
				if(GroupContainmentRegistry.isContainerModel(group)) {
					map.put(child, ChildSelection.ALWAYS_SELECTED);
				} else {
					map.put(child, ChildSelection.SELECTED);
				}
			} else if(group.equals(oldGraphicalContainer)) {
				// Child is already in the group, keep it
				map.put(child, ChildSelection.ALWAYS_SELECTED);
			} else {
				// Child is in another group, let the user decide to take it
				map.put(child, ChildSelection.NOT_SELECTED);
			}
		}
		return map;
	}

	/**
	 * Get the bounds of an edit part
	 * 
	 * @param part
	 *        edit part to find bounds
	 * @return part's bounds in absolute coordinates
	 */
	public static Rectangle getAbsoluteBounds(IGraphicalEditPart part) {
		// take bounds from figure
		Rectangle bounds = part.getFigure().getBounds().getCopy();

		if(part.getNotationView() instanceof Node) {
			// rather update with up to date model bounds
			Node node = (Node)part.getNotationView();
			LayoutConstraint cst = node.getLayoutConstraint();
			if(cst instanceof Bounds) {
				Bounds b = (Bounds)cst;
				Point parentLoc = part.getFigure().getParent().getBounds().getLocation();
				if(b.getX() > 0) {
					bounds.x = b.getX() + parentLoc.x;
				}
				if(b.getY() > 0) {
					bounds.y = b.getY() + parentLoc.y;
				}
				if(b.getHeight() != -1) {
					bounds.height = b.getHeight();
				}
				if(b.getWidth() != -1) {
					bounds.width = b.getWidth();
				}
			}
		}

		part.getFigure().getParent().translateToAbsolute(bounds);
		
		return bounds;
	}
	/**
	 * This method compute the delta between to IGraphicalEditPart.
	 * @param oldParent Old IGraphicalEditPart
	 * @param newParent New IGraphicalEditPart
	 * @return Return a DDimention between the two bounds (often use to translate point or Rectangle) 
	 */
	public static Dimension computeDeltaToChangeParent(IGraphicalEditPart oldParent, IGraphicalEditPart newParent) {
		Rectangle hostBounds = Utils.getAbsoluteBounds(oldParent);
		Rectangle parentBounds = Utils.getAbsoluteBounds(newParent);
		Dimension delta = hostBounds.getLocation().getDifference(parentBounds.getLocation());
		return delta;
	}
	
	public static Dimension computeDeltaToChangeParent(IGraphicalEditPart oldParent, Rectangle newParent) {
		Rectangle hostBounds = Utils.getAbsoluteBounds(oldParent);
		Dimension delta = hostBounds.getLocation().getDifference(newParent.getLocation());
		return delta;
	}
	
	/**
	 * Give the reference object which can reference the child for the parent type part
	 * @param parentType
	 *        EClass of the parent OBject you want to know the EReference
	 * @param childType
	 *        EClass of the child you want to test
	 * @return null if no reference is found
	 */
	public static EReference getContainmentEReference(EClass parentType ,EClass childType) {
		List<EReference> result = new ArrayList<EReference>();
		EReference usedReference = null;
		for(EReference reference : parentType.getEAllContainments()) {
			if(reference.getEReferenceType().isSuperTypeOf(childType) && !reference.isDerived()) {
				result.add(reference);
			}
		}
		
		//Select the best containment relation
		for(EReference ref : result) {
			if(usedReference == null || ref.getEReferenceType().getEAllSuperTypes().contains(usedReference.getEReferenceType())) {
				// the ref feature is more precise than the previously selected one. Use it instead.
				usedReference = ref;
			}
		}
		return usedReference;
	}
}
