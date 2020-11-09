/*****************************************************************************
 * Copyright (c) 2015, 2018 Christian W. Damus and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *   Ansgar Radermacher - Bug 526156, add postfix, if generating DI element types
 *
 *****************************************************************************/

package org.eclipse.papyrus.uml.profile.types.generator.ui.internal.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.eclipse.project.editors.project.PluginEditor;
import org.eclipse.papyrus.infra.ui.util.UIUtil;
import org.eclipse.papyrus.uml.profile.types.generator.AbstractGenerator;
import org.eclipse.papyrus.uml.profile.types.generator.ElementTypesGenerator;
import org.eclipse.papyrus.uml.profile.types.generator.Identifiers;
import org.eclipse.papyrus.uml.profile.types.generator.ui.internal.Activator;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.uml2.uml.Profile;
import org.w3c.dom.Element;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.Futures;

/**
 * A wizard for generation of a new tooling model model for a UML Profile.
 */
public class GeneratorWizard extends Wizard {

	/**
	 *
	 */
	// FIXME : should be provided by an element type plugin
	private static final String ELEMENTTYPESCONFIGURATIONS = "elementtypesconfigurations"; //$NON-NLS-1$

	public static final String TYPES_CORE_PLUGIN = "org.eclipse.papyrus.infra.types.core";
	public static final String TYPES_CORE_PLUGIN_MIN = "5.0.0";
	public static final String TYPES_CORE_PLUGIN_MAX = "6.0.0";

	private final IWorkbenchPage page;
	private final GeneratorWizardModel model;

	public GeneratorWizard(IWorkbenchPage page, Profile profile) {
		super();

		setDialogSettings(DialogSettings.getOrCreateSection(Activator.getInstance().getDialogSettings(), GeneratorWizard.class.getName()));

		this.page = page;
		this.model = new GeneratorWizardModel(this, profile, getDialogSettings());

		setWindowTitle("Generate Element Types Model");
		setHelpAvailable(false);
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		super.addPages();

		addPage(createMainPage(model));
	}

	protected IGeneratorWizardPage createMainPage(GeneratorWizardModel model) {
		return new GeneratorMainPage(model, "Element Types Configuration Model", "Enter details of the element types model to generate.", ELEMENTTYPESCONFIGURATIONS);
	}

	private void save() {
		for (IGeneratorWizardPage next : Iterables.filter(Arrays.asList(getPages()), IGeneratorWizardPage.class)) {
			next.save();
		}
	}

	@Override
	public boolean performFinish() {
		save();

		final IStatus[] status = { Status.CANCEL_STATUS };

		try {
			getContainer().run(true, false, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) {
					status[0] = doPerformFinish(monitor);
				}
			});
		} catch (InterruptedException e) {
			status[0] = Status.CANCEL_STATUS;
		} catch (InvocationTargetException e) {
			status[0] = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Model generation failed with an exception.", e.getTargetException());//$NON-NLS-1$
		}

		if (status[0].matches(IStatus.WARNING | IStatus.ERROR)) {
			StatusManager.getManager().handle(status[0], StatusManager.BLOCK | StatusManager.LOG);
		}

		return status[0].getSeverity() < IStatus.ERROR;
	}

	protected IStatus doPerformFinish(IProgressMonitor monitor) {
		IStatus result = Status.OK_STATUS;

		Identifiers identifiers = new Identifiers();
		if (model.isAddDiPostfixActive()) {
			identifiers.setPrefix(model.getIdentifier() + Identifiers.diPostfix());
			identifiers.setUseDiPostfix(true);
		} else {
			identifiers.setPrefix(model.getIdentifier());
		}
		identifiers.setBaseElementTypesSet(model.getSelectedElementTypeSet());
		identifiers.setSuppressSemanticSuperElementTypes(model.isSuppressSemanticSuperElementTypes());

		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		identifiers.setAdapterFactory(adapterFactory);

		try {
			List<AbstractGenerator<Profile, ?>> generators = Lists.newArrayListWithExpectedSize(1);
			addGenerators(generators, identifiers, model);

			int extraTasks = 2; // 1 to configure the plug-in, 1 to open the editor
			monitor.beginTask(NLS.bind("Generating {0}", generators.size() > 1 ? "models" : "model"), generators.size() + extraTasks);

			// Generate the output models
			for (AbstractGenerator<Profile, ?> next : generators) {
				monitor.subTask(next.getLabel());

				result = next.generate(model.getProfile(), getOutputURI(next, identifiers, model));

				if (result.getSeverity() >= IStatus.ERROR) {
					break;
				}

				monitor.worked(1);
			}

			// Configure the target plug-in (If the target is not a plug-in, this step will be skipped with a Warning)
			if (result.getSeverity() < IStatus.ERROR) {
				monitor.subTask("Configuring the target plug-in");
				URI outputModelURI = model.getOutputModelURI();
				result = configurePlugin(outputModelURI, identifiers);
				monitor.worked(1);
			}

			if (result.getSeverity() < IStatus.ERROR) {
				monitor.subTask("Opening editor");
				try {
					Futures.getChecked(UIUtil.syncCall(getShell().getDisplay(), new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							IDE.openEditor(page, model.getOutputModelFile());
							return null;
						}
					}), PartInitException.class);
				} catch (PartInitException e) {
					result = e.getStatus();
				}
			}
		} finally {
			adapterFactory.dispose();
			monitor.done();
		}

		return result;
	}

	/**
	 * <p>
	 * Configure the plug-in owning the target model (e.g. Add necessary dependencies
	 * to Papyrus ElementTypes framework, as well a required ElementType plug-in dependencies,
	 * ensure build.properties contains all the necessary resources...).
	 * </p>
	 *
	 * <p>
	 * If the target project is not an Eclipse plug-in project, this method does nothing and returns a warning.
	 * </p>
	 *
	 * @param outputModelURI
	 * @return
	 *         The status representing the result of this configuration operation.
	 */
	protected IStatus configurePlugin(URI outputModelURI, Identifiers identifiers) {
		// Should always be a workspace project; but let's make sure
		if (!outputModelURI.isPlatformResource()) {
			return new Status(IStatus.WARNING, getClass(), "The target model is not located in a workspace project; impossible to configure the plug-in.");
		}

		String projectName = outputModelURI.segment(1);
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		if (!project.exists()) {
			return new Status(IStatus.WARNING, getClass(), "The target model is not located in a workspace project; impossible to configure the plug-in.");
		}

		if (!project.isOpen()) {
			try {
				project.open(null);
			} catch (CoreException e) {
				Activator.log.error(e);
				return new Status(IStatus.WARNING, getClass(), "The target model is located in a workspace project that couldn't be opened; impossible to configure the plug-in.");
			}
		}

		try {
			PluginEditor editor = new PluginEditor(project);
			editor.init();

			if (!editor.exists()) {
				return new Status(IStatus.WARNING, getClass(), "The target model is not located in an Eclipse Plug-in Project; impossible to configure the plug-in.");
			}

			Set<String> files = Sets.newHashSet(PluginEditor.BUILD_PROPERTIES_FILE, PluginEditor.PLUGIN_XML_FILE);
			addDependencies(editor);
			addExtensions(editor, outputModelURI, identifiers);
			addBuildProperties(editor, outputModelURI);

			editor.save();
		} catch (Exception ex) {
			return new Status(IStatus.WARNING, getClass(), "The target model is not located in an Eclipse Plug-in Project; impossible to configure the plug-in.", ex);
		}

		return Status.OK_STATUS;
	}

	/**
	 * Add required plug-in dependencies to the target plug-in
	 *
	 * @param editor
	 *            The plug-in to configure
	 */
	protected void addDependencies(PluginEditor editor) {
		if (!editor.hasDependency(TYPES_CORE_PLUGIN)) {
			editor.addDependency(TYPES_CORE_PLUGIN, String.format("[%s, %s)", TYPES_CORE_PLUGIN_MIN, TYPES_CORE_PLUGIN_MAX));
		}
		// TODO Find and Add dependencies to plug-ins contributing the referenced models (e.g. UML, extended profiles)
	}

	/**
	 * Add required contributions to extension points in the target plug-in,
	 * for the given element types model.
	 *
	 * @param editor
	 *            The plug-in to configure
	 * @param outputModelURI
	 *            The generated model being contributed
	 */
	protected void addExtensions(PluginEditor editor, URI outputModelURI, Identifiers identifiers) {
		if (!editor.pluginManifestExists()) {
			editor.getPluginEditor().create();
		}
		editor.addToBuild("plugin.xml");

		Element ext = editor.addExtension("org.eclipse.papyrus.infra.types.core.elementTypeSetConfiguration");
		Element elementTypeSet = editor.addChild(ext, "elementTypeSet");
		elementTypeSet.setAttribute("path", toLocalProjectPath(outputModelURI).toString());
		elementTypeSet.setAttribute("clientContextID", identifiers.getContextId());
	}

	/**
	 * Add required build.properties entries in the target plug-in, for the
	 * model being generated.
	 *
	 * @param editor
	 *            The plug-in to configure
	 * @param outputModelURI
	 *            The generated model
	 */
	protected void addBuildProperties(PluginEditor editor, URI outputModelURI) {
		IPath path = toLocalProjectPath(outputModelURI);

		// Trim one segment to exclude the file name (we always export the parent folder),
		// and convert to a workspace-relative path
		path = path.removeLastSegments(1);

		// Add trailing / because we export a folder
		editor.addToBuild(path.toString() + "/");
	}

	/**
	 * <p>
	 * Converts a platform:/resource/ {@link URI} to a path relative
	 * to the current project.
	 * </p>
	 * <p>
	 * The <code>platform:/resource/projectName/folderName/fileName.fileExtension</code> {@link URI}
	 * becomes a <code>folderName/fileName.fileExtension</code> {@link IPath}.
	 * </p>
	 *
	 * @param outputModelURI
	 *            The URI to convert to a local project path
	 * @return
	 *         The {@link IPath} relative to the Project Root, corresponding to this URI
	 */
	protected IPath toLocalProjectPath(URI outputModelURI) {
		IPath path = new Path(outputModelURI.toPlatformString(true));

		// Remove the project name, as we export paths relative to the project folder
		path = path.removeFirstSegments(1);

		return path;
	}

	protected void addGenerators(List<? super AbstractGenerator<Profile, ?>> generators, Identifiers identifiers, GeneratorWizardModel wizardModel) {
		generators.add(new ElementTypesGenerator(identifiers));
	}

	protected URI getOutputURI(AbstractGenerator<Profile, ?> generator, Identifiers identifiers, GeneratorWizardModel wizardModel) {
		return wizardModel.getOutputModelURI();
	}
}
