/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   Mickaël ADAM (ALL4TEC) mickael.adam@all4tec.net - Bug 525369
 *****************************************************************************/

package org.eclipse.papyrus.uml.diagram.sequence.referencialgrilling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.uml.diagram.sequence.util.LogOptions;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * This class is a basic class to compute owners.
 * 
 * @since 3.0
 */
public class ComputeOwnerHelper implements IComputeOwnerHelper {

	protected static void fillHorizontalMatch(ArrayList<DecorationNode> columns, HashMap<Lifeline, ArrayList<InteractionOperand>> HorizontalLifeLinetoOperand) {
		ArrayList<InteractionOperand> interactionOperandStack = new ArrayList<InteractionOperand>();
		for (DecorationNode column : columns) {
			if (column.getElement() instanceof InteractionOperand) {
				if (interactionOperandStack.contains(column.getElement())) {
					UMLDiagramEditorPlugin.log.trace(LogOptions.SEQUENCE_DEBUG_REFERENCEGRID, "update " + ((InteractionOperand) column.getElement()).getName());//$NON-NLS-1$
					interactionOperandStack.remove(column.getElement());
				} else {
					interactionOperandStack.add((InteractionOperand) column.getElement());
				}
			}
			if (column.getElement() instanceof Lifeline) {
				HorizontalLifeLinetoOperand.put((Lifeline) column.getElement(), (ArrayList<InteractionOperand>) interactionOperandStack.clone());
			}


		}
	}


	protected static void fillVerticalMatch(ArrayList<DecorationNode> rows, HashMap<Element, ArrayList<InteractionOperand>> verticalElementToOperand) {
		ArrayList<InteractionOperand> interactionOperandStack = new ArrayList<InteractionOperand>();
		for (DecorationNode row : rows) {
			if (row.getElement() instanceof InteractionOperand) {
				if (interactionOperandStack.contains(row.getElement())) {
					UMLDiagramEditorPlugin.log.trace(LogOptions.SEQUENCE_DEBUG_REFERENCEGRID, "update " + ((InteractionOperand) row.getElement()).getName());//$NON-NLS-1$
					interactionOperandStack.remove(row.getElement());
				} else {
					interactionOperandStack.add((InteractionOperand) row.getElement());
				}
			} else if (row.getElement() instanceof Element) {
				verticalElementToOperand.put((Element) row.getElement(), (ArrayList<InteractionOperand>) interactionOperandStack.clone());
			}
		}

	}

	@Override
	public void updateOwnedByInteractionOperand(EditingDomain domain, ArrayList<DecorationNode> rows, ArrayList<DecorationNode> columns, Interaction interaction, GridManagementEditPolicy grid) {
		// update owner of interaction operand

		HashMap<Lifeline, ArrayList<InteractionOperand>> horizontalLifeLinetoOperand = new HashMap<Lifeline, ArrayList<InteractionOperand>>();
		HashMap<Element, ArrayList<InteractionOperand>> verticalElementToOperand = new HashMap<Element, ArrayList<InteractionOperand>>();
		fillHorizontalMatch(columns, horizontalLifeLinetoOperand);
		UMLDiagramEditorPlugin.log.trace(LogOptions.SEQUENCE_DEBUG_REFERENCEGRID, "horizontal parsing done " + horizontalLifeLinetoOperand);//$NON-NLS-1$
		fillVerticalMatch(rows, verticalElementToOperand);
		UMLDiagramEditorPlugin.log.trace(LogOptions.SEQUENCE_DEBUG_REFERENCEGRID, "vertical parsing done " + verticalElementToOperand);//$NON-NLS-1$


		// list of element for the interaction

		ArrayList<InteractionFragment> elementForInteraction = new ArrayList<InteractionFragment>();
		// list of element for the interactionOperand
		HashMap<InteractionOperand, ArrayList<InteractionFragment>> elementForIneractionOp = new HashMap<InteractionOperand, ArrayList<InteractionFragment>>();
		Iterator elementInteraction = interaction.eAllContents();
		while (elementInteraction.hasNext()) {
			Element element = (Element) elementInteraction.next();
			if (element instanceof InteractionFragment) {
				InteractionFragment aFragment = (InteractionFragment) element;
				if (verticalElementToOperand.containsKey(aFragment)) {
					Iterator<Lifeline> iterLifeline = horizontalLifeLinetoOperand.keySet().iterator();
					while (iterLifeline.hasNext()) {
						Lifeline currentLifeline = iterLifeline.next();
						if (currentLifeline.getCoveredBys().contains(aFragment)) {
							ArrayList<InteractionOperand> potentialoperand = intersection(verticalElementToOperand.get(element), horizontalLifeLinetoOperand.get(currentLifeline));
							if (potentialoperand.size() >= 1) {
								simplifyOwnerInteractionOperand(potentialoperand);
								if (potentialoperand.size() == 1) {
									if (elementForIneractionOp.get(potentialoperand.get(0)) == null) {
										elementForIneractionOp.put(potentialoperand.get(0), new ArrayList<InteractionFragment>());
									}
									elementForIneractionOp.get(potentialoperand.get(0)).add(aFragment);
									if (aFragment instanceof ExecutionOccurrenceSpecification) {
										elementForIneractionOp.get(potentialoperand.get(0)).add(((ExecutionOccurrenceSpecification) aFragment).getExecution());
									}
								}
							} else {
								if (!(aFragment instanceof InteractionOperand)) {
									elementForInteraction.add(aFragment);
									if (aFragment instanceof ExecutionOccurrenceSpecification) {
										elementForInteraction.add(((ExecutionOccurrenceSpecification) aFragment).getExecution());
									}
								}
							}
						}
					}
				}


			}

		}

		// update fragments of interaction operrands
		Iterator<InteractionOperand> iterator = elementForIneractionOp.keySet().iterator();
		while (iterator.hasNext()) {
			InteractionOperand interactionOperand = (InteractionOperand) iterator.next();
			ArrayList<InteractionFragment> elements = elementForIneractionOp.get(interactionOperand);
			if (elements.size() != 0) {
				// sort list bu taking
				ArrayList<InteractionFragment> existedFragments = new ArrayList<InteractionFragment>();
				ArrayList<InteractionFragment> sorted = sortSemanticFromRows(elements, rows);
				existedFragments.addAll(sorted);
				existedFragments.addAll(interactionOperand.getFragments());
				grid.execute(new SetCommand(domain, interactionOperand, UMLPackage.eINSTANCE.getInteractionOperand_Fragment(), existedFragments));
			}
		}

		// Update fragments of the interaction
		if (elementForInteraction.size() != 0) {
			ArrayList<InteractionFragment> existedFragments = new ArrayList<InteractionFragment>();
			ArrayList<InteractionFragment> sorted = sortSemanticFromRows(elementForInteraction, rows);
			existedFragments.addAll(sorted);
			// Add not sorted element existing into fragment
			for (InteractionFragment interactionFragment : interaction.getFragments()) {
				if (!existedFragments.contains(interactionFragment)) {
					if (interactionFragment instanceof ExecutionSpecification) {
						// if its an execution specification place it after the start
						int indexOfStartOS = existedFragments.indexOf(((ExecutionSpecification) interactionFragment).getStart());
						if (0 <= indexOfStartOS) {
							existedFragments.add(indexOfStartOS + 1, interactionFragment);
						} else {
							existedFragments.add(interactionFragment);
						}
					} else {
						// else add it to the end of the list
						existedFragments.add(interactionFragment);
					}
				}
			}
			grid.execute(new SetCommand(domain, interaction, UMLPackage.eINSTANCE.getInteraction_Fragment(), existedFragments));
		}
	}

	/**
	 * The goal is to create a new list of ordered fragment form a list of fragments by taking general order from rows
	 * 
	 * @param fragments
	 *            a list of fragments
	 * @param rows
	 *            the general order of event
	 * @return an ordered list
	 */
	protected ArrayList<InteractionFragment> sortSemanticFromRows(ArrayList<InteractionFragment> fragments, ArrayList<DecorationNode> rows) {
		ArrayList<InteractionFragment> sortedList = new ArrayList<InteractionFragment>();
		for (Iterator<DecorationNode> iteratorRow = rows.iterator(); iteratorRow.hasNext();) {
			DecorationNode row = iteratorRow.next();
			if (fragments.contains(row.getElement())) {
				if (!sortedList.contains((InteractionFragment) row.getElement())) {
					sortedList.add((InteractionFragment) row.getElement());
				}
			}

		}
		return sortedList;
	}

	/**
	 * simplify the list of interaction operand to find only one.
	 * all interaction operand in this list must have a relation owner-owned.
	 * 
	 * @param operandList
	 */
	protected static void simplifyOwnerInteractionOperand(ArrayList<InteractionOperand> operandList) {
/*		
  	while (operandList.size() > 1) {
 
			InteractionOperand last = operandList.get(operandList.size() - 1);
			EObject parent = last.eContainer();
			while (parent != null) {
				operandList.remove(parent);
				parent = parent.eContainer();
			}
		}
*/
	}

	/**
	 * make the intersection of 2 lists
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	protected static <T> ArrayList<T> intersection(List<T> list1, List<T> list2) {
		ArrayList<T> list = new ArrayList<T>();

		for (T t : list1) {
			if (list2.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}
}
