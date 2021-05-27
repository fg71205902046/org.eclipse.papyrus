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

import java.io.IOException;
import java.util.Optional;
import java.util.function.BiFunction;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.papyrus.toolsmiths.validation.common.Activator;

/**
 * A marker resolution that resolves a problem by means of an EMF {@link Command}
 * that edits the model.
 *
 * @param <T>
 *            the model object class on which I resolve the problem
 */
public class SimpleModelEditMarkerResolution<T extends EObject> extends AbstractPapyrusWorkbenchMarkerResolution {

	private final String label;
	private final String description;

	private final Class<T> type;
	private final BiFunction<? super EditingDomain, ? super T, ? extends Command> commandFunction;

	protected SimpleModelEditMarkerResolution(int problemID, String label, String description, Class<T> type, BiFunction<? super EditingDomain, ? super T, ? extends Command> commandFunction) {
		super(problemID);

		this.label = label;
		this.description = description;
		this.type = type;
		this.commandFunction = commandFunction;
	}

	/**
	 * Create a new marker resolution that edits the model.
	 *
	 * @param <T>
	 *            the kind of object to edit
	 * @param problemID
	 *            my marker resolution problem ID
	 * @param label
	 *            a label to present to the user for the marker resolution
	 * @param description
	 *            a description of the marker resolution to present to the user
	 * @param type
	 *            the type of object to edit to resolve the marker
	 * @param commandFunction
	 *            a function that creates a command to edit the object. It will receive an editing domain either from
	 *            an existing open editor or created on-the-fly for an off-line edit, plus the object loaded in that domain to edit
	 *
	 * @return the marker resolution
	 */
	public static <T extends EObject> SimpleModelEditMarkerResolution<T> create(int problemID, String label, String description, Class<T> type, BiFunction<? super EditingDomain, ? super T, ? extends Command> commandFunction) {
		return new SimpleModelEditMarkerResolution<>(problemID, label, description, type, commandFunction);
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
	public void run(IMarker marker) {
		Optional<EditingDomain> openDomain = CommonMarkerResolutionUtils.getOpenEditingDomain(marker);
		EditingDomain domain = openDomain.orElseGet(() -> new AdapterFactoryEditingDomain(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE), new BasicCommandStack()));

		try {
			CommonMarkerResolutionUtils.getModelObject(marker, type, domain).ifPresent(object -> {
				Command command = commandFunction.apply(domain, object);
				if (command != null) {
					domain.getCommandStack().execute(command);
				}
			});
		} finally {
			if (openDomain.isEmpty()) {
				// We created a domain. Save the changes and unload it
				ResourceSet rset = domain.getResourceSet();
				if (!rset.getResources().isEmpty()) {
					try {
						rset.getResources().get(0).save(null);
					} catch (IOException e) {
						Activator.log.error("Failed to save marker resolution.", e); //$NON-NLS-1$
					}
				}

				EMFHelper.unload(domain.getResourceSet());
				((ComposedAdapterFactory) ((AdapterFactoryEditingDomain) domain).getAdapterFactory()).dispose();
			}
		}
	}

}
