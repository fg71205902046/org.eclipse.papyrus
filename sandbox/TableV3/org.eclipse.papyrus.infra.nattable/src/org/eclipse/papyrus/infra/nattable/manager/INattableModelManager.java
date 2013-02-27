/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
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
package org.eclipse.papyrus.infra.nattable.manager;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.papyrus.infra.nattable.model.nattable.IAxis;
import org.eclipse.papyrus.infra.nattable.model.nattable.Table;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablecontentprovider.IAxisContentsProvider;
import org.eclipse.papyrus.infra.nattable.utils.LocationValue;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.services.IDisposable;


public interface INattableModelManager extends ILimitedNattableModelManager, ITableAxisElementProvider, IDisposable, IDataProvider {


	public int getColumnCount();

	public int getRowCount();

	public void addRows(final Collection<Object> objectsToAdd);

	public void addColumns(final Collection<Object> objectsToAdd);

	public boolean canInsertRow(final Collection<Object> objectsToAdd, int index);

	public boolean canInsertColumns(final Collection<Object> objectsToAdd, int index);

	public boolean canDropColumnsElement(final Collection<Object> objectsToAdd);

	public boolean canDropRowElement(final Collection<Object> objectsToAdd);

	public void insertRows(final Collection<Object> objectsToAdd, int index);

	public void insertColumns(final Collection<Object> objectsToAdd, int index);

	public IDataProvider getBodyDataProvider();

	public IAxisManager getColumnDataProvider();

	public IAxisManager getLineDataProvider();

	public boolean canReorderColumns();

	public boolean canReoderRows();

	public void reorderColumnsElements(final IAxis axisToMove, final int newIndex);

	public void reorderRowElements(final IAxis axisToMove, final int newIndex);

	public void invertAxis();

	public boolean canInvertAxis();

	public boolean declareEditorsOnColumns();

	public boolean declareEditorsOnRows();

	public Table getTable();

	public ITableAxisElementProvider getTableAxisElementProvider();

	public NatTable createNattable(final Composite parent, int style, IWorkbenchPartSite site);

	public LocationValue getLocationInTheTable(final Point location);

	public List<Object> getElementsList(final IAxisContentsProvider axisProvider);

	public boolean canCreateRowElement(String elementType);

	public boolean canCreateColumnElement(String elementType);

	public Command getAddRowElementCommand(Collection<Object> objectsToAdd);

	public Command getAddColumnElementCommand(Collection<Object> objectsToAdd);
	
	public void print();
	
	public void selectAll();
}
