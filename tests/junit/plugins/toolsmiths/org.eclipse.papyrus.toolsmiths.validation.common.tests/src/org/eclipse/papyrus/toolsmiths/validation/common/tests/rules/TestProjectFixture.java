/*****************************************************************************
 * Copyright (c) 2020 Christian W. Damus, CEA LIST, and others.
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

package org.eclipse.papyrus.toolsmiths.validation.common.tests.rules;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.junit.utils.JUnitUtils;
import org.eclipse.papyrus.junit.utils.rules.ProjectFixture;
import org.eclipse.papyrus.toolsmiths.plugin.builder.preferences.PluginBuilderPreferencesConstants;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker2;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.ModelResourceMapper;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * A project fixture that copies initial content into the project and ensures that the project name
 * matches the bundle symbolic name if the project is a bundle project.
 */
public class TestProjectFixture extends ProjectFixture {

	static final String JAVA_PROBLEM = "org.eclipse.jdt.core.problem"; //$NON-NLS-1$
	static final Pattern BUNDLE_SYMBOLIC_NAME = Pattern.compile("Bundle-SymbolicName:\\s*([^;]+)"); //$NON-NLS-1$

	private String markerType = JAVA_PROBLEM;
	private String projectNameOverride;
	private final Set<String> filteredDiagnosticSources = new HashSet<>();

	/**
	 * Initializes me.
	 */
	public TestProjectFixture() {
		super();
	}

	/**
	 * Configure the fixture with a {@link Diagnostic} source to ignore. This allows, for example,
	 * model validation tests to ignore problems reported by validators of other languages, such as
	 * on UML concepts that are inappropriately reused in the models being validated.
	 *
	 * @param source
	 *            a diagnostic source to filter out
	 * @return myself, for convenience of call chaining
	 */
	public TestProjectFixture filterDiagnosticSource(String source) {
		filteredDiagnosticSources.add(source);
		return this;
	}

	@Override
	public Statement apply(Statement base, Description description) {
		Build build = JUnitUtils.getAnnotation(description, Build.class);
		if (build != null && build.value()) {
			base = new BuildProject(base);
		}

		List<OverlayFile> overlayFiles = JUnitUtils.getAnnotationsByType(description, OverlayFile.class);
		if (!overlayFiles.isEmpty()) {
			base = new OverlayFilesInProject(overlayFiles, base, description);
		}

		TestProject testProject = JUnitUtils.getAnnotation(description, TestProject.class);
		Statement initProject = new Preferences(new InitializeProject(testProject, base, description));

		Statement createProject = super.apply(initProject, description);

		Statement projectName = new InitProjectName(testProject, createProject, description);

		MarkerType markerTypeAnnotation = JUnitUtils.getAnnotation(description, MarkerType.class);
		Statement markerType = new SetMarkerType(markerTypeAnnotation, projectName, description);

		return markerType;
	}

	/**
	 * Build the test project.
	 */
	public void build() {
		try {
			getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, new NullProgressMonitor());
			getProject().build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
		} catch (CoreException e) {
			throw new AssertionError("Failed to build project.", e);
		}
	}

	/**
	 * Get all of the markers in the project.
	 *
	 * @return the markers
	 * @see #getMarkers(IResource)
	 */
	public List<IMarker> getMarkers() {
		return getMarkers(getProject());
	}

	/**
	 * Get the markers on the given {@code resource}. In the case that the resource is a container,
	 * the markers of all contained resources are aggregated within it.
	 *
	 * @param resource
	 *            the resource for which to get markers
	 * @return the markers
	 */
	public List<IMarker> getMarkers(IResource resource) {
		List<IMarker> result = null;

		try {
			Predicate<IMarker> markerPredicate = Predicate.not(marker -> filteredDiagnosticSources.contains(marker.getAttribute(IPluginChecker2.MARKER_ATTRIBUTE_DIAGNOSTIC_SOURCE, (String) null)));
			result = Stream.of(resource.findMarkers(markerType, true, IResource.DEPTH_INFINITE))
					.filter(markerPredicate)
					.collect(Collectors.toList());
		} catch (CoreException e) {
			throw new AssertionError("Failed to get project markers.", e); //$NON-NLS-1$
		}

		return result;
	}

	/**
	 * Get the markers on the named resource. In the case that the resource is a container,
	 * the markers of all contained resources are aggregated within it.
	 *
	 * @param path
	 *            the resource path for which to get markers
	 * @return the markers
	 * @see #getMarkers(IResource)
	 */
	public List<IMarker> getMarkers(String path) {
		IResource resource = getProject().findMember(path);
		assertThat("No such resource in the project: " + path, resource, notNullValue()); //$NON-NLS-1$

		return getMarkers(resource);
	}

	@Override
	protected void createProject(String name) throws CoreException {
		if (projectNameOverride != null) {
			super.createProject(projectNameOverride);
		} else {
			super.createProject(name);
		}
	}

	/**
	 * Load a model resource in a suitable resource set.
	 *
	 * @param modelFile
	 *            the model file to load
	 * @return the resource, loaded in a resource set
	 */
	public Resource loadModelResource(IFile modelFile) {
		Resource result;

		URI uri = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);
		URI rsetURI = uri;

		if ("uml".equals(uri.fileExtension()) || "notation".equals(uri.fileExtension())) { //$NON-NLS-1$//$NON-NLS-2$
			// Look for a DI file to initialize the resource set, instead
			URI di = uri.trimFileExtension().appendFileExtension("di"); //$NON-NLS-1$
			if (URIConverter.INSTANCE.exists(di, null)) {
				rsetURI = di;
			}
		}

		ResourceSet rset = ("di".equals(rsetURI.fileExtension())) //$NON-NLS-1$
				? ModelResourceMapper.modelSets().apply(rsetURI)
				: ModelResourceMapper.resourceSets().apply(rsetURI);

		try {
			result = rset.getResource(uri, true);
		} catch (Exception e) {
			throw new AssertionError("Failed to load test model resource: " + modelFile.getFullPath(), e); //$NON-NLS-1$
		}

		return result;
	}

	/**
	 * Load a model resource in a suitable resource set.
	 *
	 * @param modelPath
	 *            the project-relative path of the model file to load
	 * @return the resource, loaded in a resource set
	 */
	public Resource loadModelResource(String modelPath) {
		return loadModelResource(getFile(modelPath));
	}

	//
	// Nested types
	//

	/**
	 * A statement that peeks into the contents of the project before the project is created,
	 * to override the name of the project that will be created with the bundle symbolic name
	 * in the case that the project's content will be a bundle project.
	 */
	private final class InitProjectName extends Statement {
		private final TestProject testProject;
		private final Statement base;
		private final Description description;

		InitProjectName(TestProject testProject, Statement base, Description description) {
			super();

			this.testProject = testProject;
			this.base = base;
			this.description = description;
		}

		@Override
		public void evaluate() throws Throwable {
			initializeProjectNameOverride();

			base.evaluate();
		}

		private void initializeProjectNameOverride() throws IOException {
			String path = testProject.value();
			while (path.endsWith("/")) {
				path = path.substring(0, path.length() - 1);
			}

			// If it's a bundle project, it needs to have the same name as the bundle
			String resourcePath = "resources/" + path + "/META-INF/MANIFEST.MF";
			URL resource = JUnitUtils.getTestClass(description).getClassLoader().getResource(resourcePath);
			if (resource != null) {
				Matcher m = null;
				try (InputStream input = resource.openStream()) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
					for (String line = reader.readLine(); line != null; line = reader.readLine()) {
						if (m == null) {
							m = BUNDLE_SYMBOLIC_NAME.matcher(line);
						} else {
							m.reset(line);
						}
						if (m.find()) {
							projectNameOverride = m.group(1);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * A statement that initializes the contents of the project before the test.
	 */
	private final class InitializeProject extends Statement {
		private final TestProject testProject;
		private final Statement base;
		private final Description description;

		InitializeProject(TestProject testProject, Statement base, Description description) {
			super();

			this.testProject = testProject;
			this.base = base;
			this.description = description;
		}

		@Override
		public void evaluate() throws Throwable {
			if (testProject == null) {
				throw new IllegalStateException("No @TestProject annotation found."); //$NON-NLS-1$
			}

			try {
				copyFolder(JUnitUtils.getTestClass(description), "resources/" + testProject.value()); //$NON-NLS-1$
			} catch (IOException e) {
				throw new IOException("Failed to initialize project contents.", e); //$NON-NLS-1$
			}

			base.evaluate();
		}
	}

	/**
	 * A statement that overlays additional files onto the initialized project.
	 *
	 * @see InitializeProject
	 */
	private final class OverlayFilesInProject extends Statement {
		private final List<OverlayFile> overlayFiles;
		private final Statement base;
		private final Description description;

		OverlayFilesInProject(List<OverlayFile> overlayFiles, Statement base, Description description) {
			super();

			this.overlayFiles = List.copyOf(overlayFiles);
			this.base = base;
			this.description = description;
		}

		@Override
		public void evaluate() throws Throwable {
			Class<?> testClass = JUnitUtils.getTestClass(description);

			for (OverlayFile overlay : overlayFiles) {
				URL resource = testClass.getClassLoader().getResource("resources/" + overlay.value()); //$NON-NLS-1$
				try (InputStream input = resource.openStream()) {
					IFile file = getProject().getFile(getTargetPath(overlay));
					if (file.exists()) {
						file.setContents(input, true, false, null);
					} else {
						file.create(input, true, null);
					}
				}
			}

			base.evaluate();
		}

		private IPath getTargetPath(OverlayFile overlay) {
			IPath result;

			if (overlay.path().isBlank()) {
				result = new Path(overlay.value());
				if (result.segmentCount() > 1) {
					result = result.removeFirstSegments(1);
				}
			} else {
				result = new Path(overlay.path());
			}

			return result;
		}

	}

	/**
	 * A statement that builds the project before the test.
	 */
	private final class BuildProject extends Statement {
		private final Statement base;

		BuildProject(Statement base) {
			super();

			this.base = base;
		}

		@Override
		public void evaluate() throws Throwable {
			build();
			base.evaluate();
		}

	}

	/**
	 * A statement that ensures that Papyrus builders are enabled in the preferences
	 * and restores the preferences to the previous values when complete.
	 */
	private final class Preferences extends Statement {
		private final Set<String> prefNames = Set.of(
				PluginBuilderPreferencesConstants.ACTIVATE_MAIN_PAPYRUS_BUILDER,
				PluginBuilderPreferencesConstants.ACTIVATE_PAPYRUS_MODEL_BUILDER,
				PluginBuilderPreferencesConstants.PAPYRUS_MODEL_BUILDER_CHECK_MODEL_DEPENDENCIES,
				PluginBuilderPreferencesConstants.PAPYRUS_MODEL_BUILDER_VALIDATE_MODEL,
				PluginBuilderPreferencesConstants.ACTIVATE_PAPYRUS_MANIFEST_BUILDER,
				PluginBuilderPreferencesConstants.PAPYRUS_MANIFEST_BUILDER_CHECK_DEPENDENCY_RANGE,
				PluginBuilderPreferencesConstants.PAPYRUS_MANIFEST_BUILDER_CHECK_NO_REEXPORT);
		private final IPreferenceStore prefs = org.eclipse.papyrus.toolsmiths.plugin.builder.Activator.getDefault().getPreferenceStore();
		private final Statement base;

		private final Map<String, Boolean> restorePrefs = new HashMap<>();

		Preferences(Statement base) {
			super();

			this.base = base;
		}

		@Override
		public void evaluate() throws Throwable {
			prefNames.forEach(pref -> restorePrefs.put(pref, prefs.getBoolean(pref)));
			prefNames.forEach(pref -> prefs.setValue(pref, true));

			try {
				base.evaluate();
			} finally {
				restorePrefs.forEach((pref, restore) -> prefs.setValue(pref, restore));
			}
		}

	}

	/**
	 * A statement that sets the marker type to extract from resources for the duration of a test.
	 */
	private final class SetMarkerType extends Statement {
		private final String testMarkerType;
		private final Statement base;
		private String restoreMarkerType;

		SetMarkerType(MarkerType annotation, Statement base, Description description) {
			super();

			this.testMarkerType = Optional.ofNullable(annotation).map(MarkerType::value).orElse(JAVA_PROBLEM);
			this.base = base;
		}

		@Override
		public void evaluate() throws Throwable {
			restoreMarkerType = TestProjectFixture.this.markerType;

			try {
				TestProjectFixture.this.markerType = testMarkerType;
				base.evaluate();
			} finally {
				TestProjectFixture.this.markerType = restoreMarkerType;
			}
		}

	}

}
