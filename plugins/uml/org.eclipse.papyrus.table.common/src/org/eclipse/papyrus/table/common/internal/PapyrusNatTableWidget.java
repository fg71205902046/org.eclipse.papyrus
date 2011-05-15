/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.table.common.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.facet.infra.browser.custom.MetamodelView;
import org.eclipse.emf.facet.infra.browser.custom.util.UicustomUtil;
import org.eclipse.emf.facet.widgets.celleditors.ICommandFactoriesRegistry;
import org.eclipse.emf.facet.widgets.celleditors.ICommandFactory;
import org.eclipse.emf.facet.widgets.nattable.instance.tableinstance.AttributeColumn;
import org.eclipse.emf.facet.widgets.nattable.instance.tableinstance.Column;
import org.eclipse.emf.facet.widgets.nattable.instance.tableinstance.FacetAttributeColumn;
import org.eclipse.emf.facet.widgets.nattable.instance.tableinstance.FacetReferenceColumn;
import org.eclipse.emf.facet.widgets.nattable.instance.tableinstance.ReferenceColumn;
import org.eclipse.emf.facet.widgets.nattable.instance.tableinstance.Row;
import org.eclipse.emf.facet.widgets.nattable.instance.tableinstance.TableInstance;
import org.eclipse.emf.facet.widgets.nattable.instance.tableinstance.TableinstanceFactory;
import org.eclipse.emf.facet.widgets.nattable.instance.tableinstance.TableinstancePackage;
import org.eclipse.emf.facet.widgets.nattable.instance.tableinstance2.TableInstance2;
import org.eclipse.emf.facet.widgets.nattable.internal.NatTableWidget;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 * The Papyrus Nattable Widget. This widget provides 2 interesting methods for the synchronized table : 
 * <ul>
 * <li> a method to add rows without execute the command in the command stack</li>
 * <li> a method to remove rows without execute the command in the command stack</li>
 * </ul>
 *
 *The synchronization of the table is done using listener on the context.
 * We can't use the commadStack to add/remove element in the table, because in this case we add a command in the CommandStack :
 * 		-> 2 Commands for the Undo
 * 		-> moreover we add a new command executing the undo (thanks to the listener...)
 * 
 * Another idea : maybe, we should use something like the ServiceEdit, which provides commands for each action,
 * to chain the commands to execute.
 * 
 */
public class PapyrusNatTableWidget extends NatTableWidget implements IPapyrusNatTableWidget{

	/**
	 * the editing domain
	 */
	private final EditingDomain editingDomain;

	/**
	 * the table instance
	 */
	private final TableInstance tableInstance;

	/**
	 * 
	 * Constructor.
	 *
	 * @param parent
	 * @param editingDomainProvider
	 * @param tableInstanceParam
	 * @param menuMgr
	 */
	public PapyrusNatTableWidget(final Composite parent, final IEditingDomainProvider editingDomainProvider, final TableInstance tableInstanceParam, final MenuManager menuMgr) {
		super(parent, editingDomainProvider, tableInstanceParam, menuMgr);
		this.editingDomain = editingDomainProvider.getEditingDomain();
		this.tableInstance = tableInstanceParam;
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.table.common.internal.IPapyrusNatTableWidget#addRowsOutOfCommandStack(java.util.List)
	 *
	 *  {@inheritDoc}
	 */
	public void addRowsOutOfCommandStack(final List<EObject> newElements) {
		ICommandFactory commandFactory = ICommandFactoriesRegistry.INSTANCE
			.getCommandFactoryFor(this.editingDomain);
		CompoundCommand cmCommand = new CompoundCommand();
		// the EPackage for which the MetamodelView has already been created
		Set<EPackage> alreadyDone = new HashSet<EPackage>();
		for (EObject eObject : newElements) {
			if (!this.tableInstance.getElements().contains(eObject)) {
				Row row = TableinstanceFactory.eINSTANCE.createRow();
				Command cmd2 = commandFactory.createSetCommand(this.editingDomain, row,
					TableinstancePackage.eINSTANCE.getRow_Element(), eObject);
				cmCommand.append(cmd2);
				Command cmd1 = commandFactory.createAddCommand(this.editingDomain,
					this.tableInstance, TableinstancePackage.eINSTANCE.getTableInstance_Rows(),
					row);
				cmCommand.append(cmd1);
				for (EStructuralFeature eStructuralFeature : eObject.eClass()
					.getEAllStructuralFeatures()) {
					if (!isColumnAlreadyDeclared(eStructuralFeature)) {
						if (eStructuralFeature instanceof EReference) {
							ReferenceColumn referenceColumn = TableinstanceFactory.eINSTANCE
								.createReferenceColumn();
							referenceColumn.setReference((EReference) eStructuralFeature);
							Command cmd = commandFactory.createAddCommand(this.editingDomain,
								this.tableInstance,
								TableinstancePackage.eINSTANCE.getTableInstance_Columns(),
								referenceColumn);
							cmCommand.append(cmd);

						} else if (eStructuralFeature instanceof EAttribute) {
							AttributeColumn attributeColumn = TableinstanceFactory.eINSTANCE
								.createAttributeColumn();
							attributeColumn.setAttribute((EAttribute) eStructuralFeature);
							Command cmd = commandFactory.createAddCommand(this.editingDomain,
								this.tableInstance,
								TableinstancePackage.eINSTANCE.getTableInstance_Columns(),
								attributeColumn);
							cmCommand.append(cmd);
						}

						// we add the local customization file
						if (this.tableInstance instanceof TableInstance2) {
							List<MetamodelView> localCustoms = getLocalCustomizations();
							EObject container = eStructuralFeature.eContainer();
							if (container != null) {
								container = container.eContainer();
								if (container instanceof EPackage) {
									if (!alreadyDone.contains(container)) {
										if (UicustomUtil.getMetamodelViewByEPackage(localCustoms,
											(EPackage) container) == null) {
											Command cmd = getCreateMetamodelViewCommand(
												this.tableInstance,
												((EPackage) container).getNsURI());
											if (cmd.canExecute()) {
												cmCommand.append(cmd);
											}
											alreadyDone.add((EPackage) container);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		//		this.editingDomain.getCommandStack().execute(cmCommand);
		cmCommand.execute();
		if (this.tableInstance instanceof TableInstance2) {
			TableInstance2 tableInstance2 = (TableInstance2) this.tableInstance;
			try {
				setFacets(tableInstance2.getFacets2());
			} catch (CoreException e) {
				throw new RuntimeException(e);
			}
		}
		loadCustomizations(this.tableInstance.getCustomizations());
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.table.common.internal.IPapyrusNatTableWidget#removeRowsOutOfCommandStack(java.util.List)
	 *
	 *  {@inheritDoc}
	 */
	public void removeRowsOutOfCommandStack(final List<EObject> elementsToDelete) {
		//		this.natTable.setRedraw(false);
		try {
			ICommandFactory commandFactory = ICommandFactoriesRegistry.INSTANCE
				.getCommandFactoryFor(this.editingDomain);
			CompoundCommand compoundCommand = new CompoundCommand();
			for (int i = 0; i < this.tableInstance.getRows().size(); i++) {
				if (elementsToDelete.contains(this.tableInstance.getRows().get(i).getElement())) {
					Command removeRowCommand = commandFactory.createRemoveCommand(
						this.editingDomain, this.tableInstance,
						TableinstancePackage.eINSTANCE.getTableInstance_Rows(),
						this.tableInstance.getRows().get(i));
					compoundCommand.append(removeRowCommand);
				}
			}

			if (!compoundCommand.isEmpty() && compoundCommand.canExecute()) {
				//				this.editingDomain.getCommandStack().execute(compoundCommand);
				compoundCommand.execute();
			}
		} finally {
			//			this.natTable.setRedraw(true);
		}
	}


	//TODO this method is duplicated from the super class
	private List<MetamodelView> getLocalCustomizations() {
		List<MetamodelView> locals = new ArrayList<MetamodelView>();
		if (this.tableInstance.getLocalCustomization() != null) {
			locals.add(this.tableInstance.getLocalCustomization());
		}
		if (this.tableInstance instanceof TableInstance2) {
			locals.addAll(((TableInstance2) this.tableInstance).getLocalCustomizations());
		}
		return locals;
	}

	//TODO this method is duplicated from the super class
	private boolean isColumnAlreadyDeclared(final EStructuralFeature eStructuralFeature) {
		if (eStructuralFeature instanceof EReference) {
			for (Column c : this.tableInstance.getColumns()) {
				if (c instanceof ReferenceColumn) {
					if (((ReferenceColumn) c).getReference() == eStructuralFeature) {
						return true;
					}
				} else if (c instanceof FacetReferenceColumn) {
					if (((FacetReferenceColumn) c).getReference() == eStructuralFeature) {
						return true;
					}
				}
			}
		} else if (eStructuralFeature instanceof EAttribute) {
			for (Column c : this.tableInstance.getColumns()) {
				if (c instanceof AttributeColumn) {
					if (((AttributeColumn) c).getAttribute() == eStructuralFeature) {
						return true;
					}
				} else if (c instanceof FacetAttributeColumn) {
					if (((FacetAttributeColumn) c).getAttribute() == eStructuralFeature) {
						return true;
					}
				}
			}
		}

		return false;
	}


}
