/*****************************************************************************
 * Copyright (c) 2015 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Shuai Li (CEA LIST) <shuai.li@cea.fr> - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.infra.gmfdiag.navigation.menu.listener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.papyrus.infra.gmfdiag.navigation.menu.DefaultNavigationMenu;
import org.eclipse.papyrus.infra.gmfdiag.navigation.menu.button.HyperlinkButton;
import org.eclipse.papyrus.infra.gmfdiag.navigation.menu.button.MoreButton;
import org.eclipse.papyrus.infra.gmfdiag.navigation.menu.button.NavigationSubMenuButton;
import org.eclipse.papyrus.infra.services.navigation.service.NavigableElement;
import org.eclipse.papyrus.infra.widgets.editors.SelectionMenu;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Shuai Li
 *
 */
public class SelectionMenuSelectionChangedListener implements ISelectionChangedListener {
	protected DefaultNavigationMenu navigationMenu;
	protected List<Object> selectionMenuItems;
	protected SelectionMenu selectionMenu;
	protected List<SelectionMenu> subMenus;
	protected Object umlElement;
	protected boolean moreButton = false;

	public SelectionMenuSelectionChangedListener(DefaultNavigationMenu navigationMenu, SelectionMenu selectionMenu, List<Object> selectionMenuItems, Object umlElement, List<SelectionMenu> subMenus) {
		this.navigationMenu = navigationMenu;
		this.selectionMenu = selectionMenu;
		this.selectionMenuItems = selectionMenuItems;

		this.umlElement = umlElement;
		this.subMenus = subMenus;
	}

	public SelectionMenuSelectionChangedListener(DefaultNavigationMenu navigationMenu, SelectionMenu selectionMenu, List<Object> selectionMenuItems) {
		this.navigationMenu = navigationMenu;
		this.selectionMenu = selectionMenu;
		this.selectionMenuItems = selectionMenuItems;
	}

	/**
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 *
	 * @param event
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection().isEmpty()) {
			return;
		}

		Object selectedElement = ((IStructuredSelection) event.getSelection()).getFirstElement();

		// Close submenus of same level or higher
		if (!(selectedElement instanceof NavigationSubMenuButton)) {
			if (subMenus != null) {
				if (event.getSource() instanceof TableViewer) {
					Composite levelShell = ((TableViewer) event.getSource()).getTable().getShell();

					List<SelectionMenu> subMenusToDelete = new ArrayList<SelectionMenu>();
					for (SelectionMenu subMenu : subMenus) {
						if (subMenu.getParentShell().equals(levelShell)) {
							subMenusToDelete.add(subMenu);
							subMenu.dispose();
						}
					}

					subMenus.removeAll(subMenusToDelete);
				}
			}
		}

		if (selectedElement instanceof NavigableElement) {
			navigationMenu.showInModelExplorer((NavigableElement) selectedElement);
		} else if (selectedElement instanceof HyperlinkButton) {
			((HyperlinkButton) selectedElement).getAction().run();
			navigationMenu.exitItem();
		} else if (selectedElement instanceof MoreButton) {
			((LinkedList<Object>) selectionMenuItems).removeLast();
			navigationMenu.addContextualMenus(selectionMenuItems, umlElement);
			selectionMenu.refresh();
		} else {
			navigationMenu.revealObject(selectedElement);
		}
	}

}