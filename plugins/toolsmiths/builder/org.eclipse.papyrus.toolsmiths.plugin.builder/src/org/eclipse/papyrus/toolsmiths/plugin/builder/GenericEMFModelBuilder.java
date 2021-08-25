/*****************************************************************************
 * Copyright (c) 2020, 2021 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http: //www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Initial API and implementation
 *   Christian W. Damus - bugs 569357, 570097, 572644, 573408, 575376, 575122
 *
 *****************************************************************************/

package org.eclipse.papyrus.toolsmiths.plugin.builder;

import static com.google.common.base.Functions.constant;
import static java.util.function.Function.identity;
import static org.eclipse.papyrus.toolsmiths.validation.common.checkers.ModelValidationChecker.createSubstitutionLabelProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.emf.helpers.BundleResourceURIHelper;
import org.eclipse.papyrus.emf.validation.DependencyValidationUtils;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.papyrus.infra.emf.utils.ResourceUtils;
import org.eclipse.papyrus.toolsmiths.plugin.builder.helper.ExtensionValidator;
import org.eclipse.papyrus.toolsmiths.plugin.builder.preferences.PluginBuilderPreferencesConstants;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.CommonProblemConstants;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.ExtensionsChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.checkers.IPluginChecker2;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.PluginErrorReporter;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.PluginErrorReporter.ExtensionChecker;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.PluginErrorReporter.ExtensionFixProvider;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.PluginErrorReporter.ExtensionMatcher;
import org.eclipse.papyrus.toolsmiths.validation.common.internal.utils.ProjectManagementUtils;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.DependencyKind;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.Extension;
import org.eclipse.papyrus.toolsmiths.validation.common.projectrules.ProjectDescription;
import org.eclipse.papyrus.toolsmiths.validation.common.spi.ProjectRulesModelProvider;
import org.eclipse.papyrus.toolsmiths.validation.common.utils.MarkersService;
import org.eclipse.pde.internal.core.builders.IncrementalErrorReporter.VirtualMarker;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Generic builder for all EMF model/metamodel
 * This builder checks that all required metamodel are declared as dependencies of the Plugin
 * This builder launch the EMF validation on the model
 *
 * Result of the build process is the creation of IMarker (blocking the launch of the Eclipse runtime)
 */
@SuppressWarnings("restriction")
public class GenericEMFModelBuilder extends AbstractPapyrusBuilder {

	/**
	 * helper used to find bundle associated to a resource or a URI
	 */
	private BundleResourceURIHelper RESOURCE_HELPER = BundleResourceURIHelper.INSTANCE;

	/**
	 * the list of excluded folder by name
	 */
	private static final Collection<String> EXCLUDED_FOLDER_NAME = new ArrayList<>();

	/**
	 * the list of excluded file name
	 */
	private static final Collection<String> EXCLUDED_FILE_NAME = new ArrayList<>();

	/**
	 * the list of excluded file extension
	 */
	private static final Collection<String> EXCLUDED_FILE_EXTENSION = new ArrayList<>();

	/**
	 * list of ignored nsURI
	 */
	private static final Collection<String> IGNORED_NS_URI = new ArrayList<>();

	static {
		EXCLUDED_FOLDER_NAME.add(".settings"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("META-INF"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("OSGI-INF"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("icons"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("images"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("bin"); //$NON-NLS-1$
		EXCLUDED_FOLDER_NAME.add("target"); //$NON-NLS-1$

		EXCLUDED_FILE_NAME.add(".classpath"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add(".project"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add(".gitignore"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add("about.html"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add("build.properties"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add("plugin.xml"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add("pom.xml"); //$NON-NLS-1$
		EXCLUDED_FILE_NAME.add("README"); //$NON-NLS-1$

		EXCLUDED_FILE_EXTENSION.add("png"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("jpg"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("jpeg"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("gif"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("xml"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("md"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("exsd"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("svg"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("gmfgen"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("genmodel"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("html"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("mediawiki"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("css"); //$NON-NLS-1$

		// we exclude these files coming from Papyrus models from the check
		EXCLUDED_FILE_EXTENSION.add("notation"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("di"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("properties"); //$NON-NLS-1$
		EXCLUDED_FILE_EXTENSION.add("qvto"); //$NON-NLS-1$

		IGNORED_NS_URI.add("http://www.w3.org/2001/XMLSchema-instance"); //$NON-NLS-1$
		IGNORED_NS_URI.add("http://www.omg.org/XMI"); //$NON-NLS-1$
		IGNORED_NS_URI.add("http://www.omg.org/spec/XMI/20131001"); //$NON-NLS-1$
		// IGNORED_NS_URI.add("http://www.eclipse.org/uml2/schemas/Ecore/5");
	}

	private final ProjectRulesModelProvider projectRulesModelProvider;

	public GenericEMFModelBuilder() {
		this(null);
	}

	public GenericEMFModelBuilder(ProjectRulesModelProvider projectRulesModelProvider) {
		super(MODEL_PROBLEM);

		this.projectRulesModelProvider = (projectRulesModelProvider == null) ? ProjectRulesModelProvider.NULL : projectRulesModelProvider;
	}

	@Override
	public IProject[] build(IProject builtProject, PapyrusPluginBuilder papyrusBuilder, int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		if (true) {
			// return null;
		}
		if (builtProject != null) {

			final IJavaProject javaProject = JavaCore.create(builtProject);

			Map<Resource, IFile> resources = null;

			try {
				// 1. get all ecore resource file owned by the project
				resources = getEcoreResources(javaProject);

				// 2. launch validation on each resource
				if (isModelValidationActivated()) {
					validateModel(resources);
				}

				// 3. check the required dependencies
				if (isCheckModelDependencyActivated()) {
					checkModelDependencies(resources, builtProject);
				}

				// 4. check plug-in extensions etc.
				checkPluginManifest(resources, builtProject);
			} finally {
				// Make sure to unload resource sets because if any of them load UML content either directly
				// or by cross-document reference, then the static CacheAdapter may be retaining them
				if (resources != null) {
					Exception thrown = null;

					for (Resource res : resources.keySet()) {
						try {
							EMFHelper.unload(res.getResourceSet());
						} catch (Exception e) {
							if (thrown == null) {
								thrown = e;
							} else {
								thrown.addSuppressed(e);
							}
						}
					}

					if (thrown != null) {
						throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Uncaught exception in unloading validated EMF resources.", thrown)); //$NON-NLS-1$
					}
				}
			}
		}
		return null;
	}

	/**
	 *
	 * @param resources
	 *            the resource to validate
	 */
	protected void validateModel(final Map<Resource, IFile> resources) {
		for (final Entry<Resource, IFile> entry : resources.entrySet()) {
			Collection<Diagnostic> diagnostics = validateResource(entry.getKey());
			createMarkerErrorFromDiagnostics(entry.getValue(), diagnostics);
		}
	}

	/**
	 * This method checks that all required dependencies are included in the manifest file of the project.
	 *
	 * @param resources
	 *            the resource for which we check the dependencies
	 * @param builtProject
	 *            the current built project
	 * @throws CoreException
	 */
	protected void checkModelDependencies(final Map<Resource, IFile> resources, final IProject builtProject) throws CoreException {
		// 3. get all current declared dependencies in the project
		final Collection<String> currentDeclaredDependencies = getAllAvailableDependencies(builtProject);
		final Map<Resource, ProjectDescription> projectDescriptions = getProjectDescriptions(resources.keySet());

		Optional<IFile> manifest = Optional.ofNullable(ProjectManagementUtils.getManifestFile(builtProject));

		// 4. explore resources dependencies
		for (final Entry<Resource, IFile> current : resources.entrySet()) {
			final Map<String, Boolean> dependencies = getDependencies(current.getKey(), projectDescriptions.get(current.getKey()), builtProject);
			dependencies.keySet().removeAll(currentDeclaredDependencies);
			dependencies.remove(builtProject.getName());

			if (!dependencies.isEmpty()) {
				final String requiredDependenciesToAdd = dependencies.entrySet().stream().filter(e -> e.getValue())
						.map(Map.Entry::getKey)
						.collect(Collectors.joining(DependencyValidationUtils.DEPENDENCY_SEPARATOR));
				final String expectedDependenciesToAdd = dependencies.entrySet().stream().filter(e -> !e.getValue())
						.map(Map.Entry::getKey)
						.collect(Collectors.joining(DependencyValidationUtils.DEPENDENCY_SEPARATOR));

				if (!requiredDependenciesToAdd.isBlank()) {
					final IMarker marker = createErrorMarker(manifest.orElse(current.getValue()), "Missing Dependencies in model"); //$NON-NLS-1$
					IPluginChecker2.problem(CommonProblemConstants.MISSING_DEPENDENCIES_MARKER_ID).applyTo(marker);
					marker.setAttribute(DependencyValidationUtils.MISSING_DEPENDENCIES, requiredDependenciesToAdd);
				}
				if (!expectedDependenciesToAdd.isBlank()) {
					final IMarker marker = createWarningMarker(manifest.orElse(current.getValue()), "Missing Dependencies in model"); //$NON-NLS-1$
					IPluginChecker2.problem(CommonProblemConstants.MISSING_DEPENDENCIES_MARKER_ID).applyTo(marker);
					marker.setAttribute(DependencyValidationUtils.MISSING_DEPENDENCIES, expectedDependenciesToAdd);
				}
			}
		}
	}

	/**
	 * This method checks that all required plug-in extensions are present and correct in the <tt>plugin.xml</tt>.
	 *
	 * @param resources
	 *            the resource for which we check the plug-in extensions
	 * @param builtProject
	 *            the current built project
	 * @throws CoreException
	 */
	protected void checkPluginManifest(final Map<Resource, IFile> resources, final IProject builtProject) throws CoreException {
		final Map<Resource, ProjectDescription> projectDescriptions = getProjectDescriptions(resources.keySet());

		if (projectDescriptions.isEmpty()) {
			// Nothing to do
			return;
		}

		IFile pluginXML = ProjectManagementUtils.getPluginXMLFile(builtProject);
		if (pluginXML == null) {
			// Nothing to do
			return;
		}

		for (final Resource current : resources.keySet()) {
			ProjectDescription projectDescription = projectDescriptions.get(current);
			EObject model = current.getContents().isEmpty() ? null : current.getContents().get(0);

			if (projectDescription != null && model != null) {
				ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
				ExtensionsChecker<EObject, PluginErrorReporter<EObject>> checker = new ExtensionsChecker<>(builtProject, resources.get(current), Set.of(model), getMarkerType(),
						(project, file, object) -> createPluginErrorReporter(project, file, object, projectDescription, adapterFactory));

				try {
					checker.check(new NullProgressMonitor());
				} finally {
					adapterFactory.dispose();
				}
			}
		}
	}

	private PluginErrorReporter<EObject> createPluginErrorReporter(IFile pluginXML, IFile modelFile, EObject model, ProjectDescription description, AdapterFactory adapterFactory) {
		PluginErrorReporter<EObject> result = new PluginErrorReporter<>(pluginXML, modelFile, model, getMarkerType(), object -> getLabel(object, adapterFactory)) {
			private String currentMissingExtensionPoint;

			protected void reportMissingExtension(String point) {
				currentMissingExtensionPoint = point;
				try {
					super.reportMissingExtension(point);
				} finally {
					currentMissingExtensionPoint = null;
				}
			}

			protected VirtualMarker reportProblem(String message, int line, int severity, int fixId, Element element, String attrName, String category) {
				VirtualMarker result = super.reportProblem(message, line, severity, fixId, element, attrName, category);

				result.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(model).toString());

				if (currentMissingExtensionPoint != null) {
					result.setAttribute(CommonProblemConstants.EXTENSION_POINT, currentMissingExtensionPoint);
				}

				return result;
			}
		};

		for (Extension extension : description.getExtensions()) {
			if (extension.getResourceAttribute() != null) {
				ExtensionValidator<EObject> validator = new ExtensionValidator<>(pluginXML.getProject(), modelFile, extension);

				String point = extension.getExtensionPoint();
				ExtensionMatcher<EObject> matcher = validator::matchExtension;
				ExtensionChecker<EObject> checker = validator::checkExtension;
				ExtensionFixProvider<EObject> fixProvider = validator::problemID;

				if (extension.getArchitectureReference() != null) {
					// This extension point is optional if the model can be registered via an Architecture Context
					result.softRequireExtensionPoint(point, matcher, checker, fixProvider);
				} else {
					result.requireExtensionPoint(point, matcher, checker, fixProvider);
				}
			}
		}

		return result;
	}

	protected String getLabel(EObject object, AdapterFactory adapterFactory) {
		String result = null;

		IItemLabelProvider provider = (IItemLabelProvider) adapterFactory.adapt(object, IItemLabelProvider.class);
		if (provider != null) {
			result = provider.getText(object);
		}

		if (result == null) {
			result = EcoreUtil.getIdentification(object);
		}

		return result;
	}

	/**
	 *
	 * @param javaProject
	 *            a java project
	 * @return
	 *         the list of file which should be ecore models
	 */
	protected Map<Resource, IFile> getEcoreResources(final IJavaProject javaProject) {
		Map<Resource, IFile> resources = new HashMap<>();
		try {
			Object[] nonJavaResource = javaProject.getNonJavaResources();
			for (Object current : nonJavaResource) {
				resources.putAll(getEcoreResource(current));
			}

		} catch (JavaModelException e) {
			Activator.log.error(e);
		}

		return resources;
	}

	/**
	 *
	 * @param container
	 *            a container, that is an IFile or an IFolder
	 * @return
	 *         the map of EMF Resource/IFile owned by the container
	 */
	protected Map<Resource, IFile> getEcoreResource(final Object container) {
		Map<Resource, IFile> resources = new HashMap<>();
		if (container instanceof IFile) {
			final IFile f = (IFile) container;
			if ((managedFileExtension(f.getFileExtension()) && !hasSpecificBuilder(f))
					&& !EXCLUDED_FILE_NAME.contains(f.getName())) {
				final Resource res = loadIfPossibleAsEcoreResource(f);
				if (res != null) {
					resources.put(res, f);
				}
			}

		} else if (container instanceof IFolder) {
			final IFolder folder = (IFolder) container;
			if (!EXCLUDED_FOLDER_NAME.contains(folder.getName())) {
				try {
					for (IResource current : folder.members()) {
						resources.putAll(getEcoreResource(current));
					}
				} catch (CoreException e) {
					Activator.log.error(e);
				}
			}
		}
		return resources;
	}

	protected Map<Resource, ProjectDescription> getProjectDescriptions(final Collection<? extends Resource> resources) {
		Map<Resource, ProjectDescription> result = new HashMap<>();

		for (Resource resource : resources) {
			ResourceSet rset = resource.getResourceSet();

			for (EObject next : resource.getContents()) {
				EPackage ePackage = next.eClass().getEPackage();
				ProjectDescription description = projectRulesModelProvider.getProjectDescription(ePackage, rset);
				if (description != null) {
					result.put(resource, description);
					break;
				}
			}
		}

		return result;
	}

	/**
	 *
	 * @param f
	 *            a file
	 * @return
	 *         a Resource if the EMF framework is able to load it
	 */
	protected Resource loadIfPossibleAsEcoreResource(final IFile f) {
		final ResourceSet set = new ResourceSetImpl();

		// Ensure that cross-doc references saved with the platform-scheme-aware URI handler can resolve
		// platform:/resource URIs to bundles in the target platform.
		set.setURIConverter(ResourceUtils.createWorkspaceAwareURIConverter());

		final URI uri = URI.createPlatformResourceURI(f.getFullPath().toOSString(), true);
		try {
			final Resource res = set.getResource(uri, true);
			return res;
		} catch (Exception exp) {
			// nothing to do, the file is not an EMF Resource
		}
		return null;
	}

	/**
	 *
	 * @param resource
	 *            a resource
	 * @param builtProject
	 *            the current build project
	 * @return
	 *         the collection of dependencies as String loaded for this resource
	 */
	protected Set<String> getDependencies(final Resource resource, final IProject builtProject) {
		EcoreUtil.resolveAll(resource);
		final Set<String> dependencies = new TreeSet<>();
		for (final Resource current : resource.getResourceSet().getResources()) {
			if (!isIgnoredNS_URI(current.getURI().toString()) && managedFileExtension(current.getURI().fileExtension())
					&& !hasSpecificBuilder(current.getURI())) {
				try {
					String bundleName = getBundleNameFromResource(current);
					if (bundleName != null) {
						dependencies.add(bundleName);
					}
					Set<String> modelDependencies = getModelBundleDependenciesFromXML(resource);
					if (modelDependencies != null && !modelDependencies.isEmpty()) {
						dependencies.addAll(modelDependencies);
					}
				} catch (Exception e) {
				}
			}
		}
		return dependencies;
	}

	/**
	 *
	 * @param resource
	 *            a resource
	 * @param description
	 *            a description of the project dependencies, or {@code null} if none is available
	 * @param builtProject
	 *            the current build project
	 * @return
	 *         a mapping of bundle dependencies to whether they are required ({@code true}) or just expected ({@code false})
	 */
	protected Map<String, Boolean> getDependencies(final Resource resource, final ProjectDescription description, final IProject builtProject) {
		Map<String, Boolean> result = getDependencies(resource, builtProject).stream()
				.collect(Collectors.toMap(identity(), constant(true), (a, b) -> a, TreeMap::new));

		if (description != null) {
			// Import-Package dependencies are not (yet) supported
			description.getDependencies().stream()
					.filter(dep -> dep.getKind() == DependencyKind.REQUIRE_BUNDLE)
					.forEach(dep -> result.put(dep.getName(), dep.isRequired()));
		}

		return result;
	}

	/**
	 *
	 * @param resource
	 *            a resource
	 * @return
	 *         the bundle providing this resource
	 */
	protected String getBundleNameFromResource(final Resource resource) {
		return RESOURCE_HELPER.getBundleNameFromResource(resource);
	}

	/**
	 *
	 * @param resource
	 *            an EMF Resource
	 * @return
	 *         the set of bundle's name required by this resource
	 */
	protected Set<String> getModelBundleDependenciesFromXML(final Resource resource) {
		final Set<String> importedMetamodels_NS_URI = getXMLImportedMetamodelNsURI(resource);
		return getBundleNameFromNS_URI(importedMetamodels_NS_URI);
	}

	/**
	 * This method allows to obtain metamodels URI reading the Resource as XML file
	 *
	 * @param resource
	 *            a resource
	 * @return
	 *         the list of imported metamodel in this resource, identified by their nsURI
	 */
	protected Set<String> getXMLImportedMetamodelNsURI(final Resource resource) {
		if (!(resource instanceof XMLResource)) {
			// There will be no XML namespaces
			return Set.of();
		}

		Set<String> result = new HashSet<>();

		try (InputStream input = URIConverter.INSTANCE.createInputStream(resource.getURI())) {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			SAXParser parser = factory.newSAXParser();

			parser.parse(input, new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					// We only want namespace declarations from the root element
					throw new OperationCanceledException();
				}

				@Override
				public void startPrefixMapping(String prefix, String uri) throws SAXException {
					result.add(uri);
				}
			});
		} catch (OperationCanceledException e) {
			// This is normal
		} catch (SAXException | IOException | ParserConfigurationException e) {
			if (e.getCause() instanceof OperationCanceledException) {
				// This also is normal, if the run-time exception is wrapped by the parser in a SAXException
			} else {
				Activator.log.error("Failed to parse XML resource for namespace URIs.", e); //$NON-NLS-1$
			}
		}

		return result;
	}

	/**
	 *
	 * @param nsURIs
	 *            a collection of URI
	 * @return
	 *         a set with the bundle name providing the nsURI
	 */
	protected Set<String> getBundleNameFromNS_URI(final Collection<String> nsURIs) {
		final Set<String> result = new HashSet<>();
		for (final String currentNS_URI : nsURIs) {
			if (!isIgnoredNS_URI(currentNS_URI)) {
				final String bundleName = getBundleNameFromNS_URI(currentNS_URI);
				if (bundleName != null) {
					result.add(bundleName);
				} else {
					Activator.log.warn(NLS.bind(Messages.GenericEMFModelBuilder_noBundleFoundForNsUri, currentNS_URI));
				}
			}
		}
		return result;
	}

	/**
	 *
	 * @param nsURI
	 *            the nsURI from a metamodel
	 * @return
	 *         the bundle provided the EPackage corresponding to this nsURI, or <code>null</code> if not found
	 */
	protected String getBundleNameFromNS_URI(final String nsURI) {
		// TODO : find a generic way for that!
		if (nsURI.equals(UMLResource.UML2_PROFILE_NS_URI) || nsURI.equals(UMLResource.ECORE_PROFILE_NS_URI)) {
			return "org.eclipse.uml2.uml.resources"; //$NON-NLS-1$
		}
		return RESOURCE_HELPER.getBundleNameFromNS_URI(nsURI);
	}

	/**
	 *
	 * @param resource
	 *            a resource
	 * @return
	 *         launch the validation on this resource
	 */
	protected Collection<Diagnostic> validateResource(final Resource resource) {
		final BasicDiagnostic result = new BasicDiagnostic();
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		final EValidator.SubstitutionLabelProvider labels = createSubstitutionLabelProvider(adapterFactory);

		Map<Object, Object> context = new HashMap<>();
		context.put(EValidator.SubstitutionLabelProvider.class, labels);

		try {
			for (final EObject current : resource.getContents()) {
				Diagnostician.INSTANCE.validate(current, result, context);
			}
		} finally {
			adapterFactory.dispose();
		}

		// the root diagnotic is useless for us
		return result.getChildren();
	}

	/**
	 * This method create java error marker from the Diagnotics
	 *
	 * @param iResource
	 *            an {@link IResource}
	 * @param diagnostics
	 *            the {@link Diagnostic} build for this resource
	 */
	protected void createMarkerErrorFromDiagnostics(final IResource iResource, final Collection<Diagnostic> diagnostics) {
		if (diagnostics != null && !diagnostics.isEmpty()) {
			for (Diagnostic diagnostic : diagnostics) {
				createMarkerErrorFromDiagnostic(iResource, diagnostic);
			}
		}
	}

	/**
	 * This method create java error marker from the Diagnotic
	 *
	 * @param iResource
	 *            an {@link IResource}
	 * @param diagnostics
	 *            the {@link Diagnostic} build for this resource
	 */
	protected void createMarkerErrorFromDiagnostic(final IResource iResource, final Diagnostic diagnostic) {
		try {
			final IMarker marker = MarkersService.createMarker(iResource, getMarkerType(), diagnostic);
			marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI((EObject) diagnostic.getData().get(0)).toString());

			int index = diagnostic.getData().indexOf(DependencyValidationUtils.MISSING_DEPENDENCIES);
			if (index > 0) {
				final String missingDependencies = (String) diagnostic.getData().get(index);
				marker.setAttribute(DependencyValidationUtils.MISSING_DEPENDENCIES, missingDependencies);
			}
		} catch (CoreException e) {
			Activator.log.error(e);
		}

		createMarkerErrorFromDiagnostics(iResource, diagnostic.getChildren());
	}

	protected String getMarkerType() {
		return getDefaultMarkerType();
	}

	/**
	 *
	 * @return
	 *         <code>true</code> if the validation of model is activated
	 */
	protected final boolean isModelValidationActivated() {
		return Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.PAPYRUS_MODEL_BUILDER_VALIDATE_MODEL);
	}

	/**
	 *
	 * @return
	 *         <code>true</code> if the check of model dependencies
	 */
	protected final boolean isCheckModelDependencyActivated() {
		return Activator.getDefault().getPreferenceStore().getBoolean(PluginBuilderPreferencesConstants.PAPYRUS_MODEL_BUILDER_CHECK_MODEL_DEPENDENCIES);
	}

	protected boolean managedFileExtension(final String fileExtension) {
		return !EXCLUDED_FILE_EXTENSION.contains(fileExtension);
	}

	protected boolean isIgnoredNS_URI(final String ns_URI) {
		return IGNORED_NS_URI.contains(ns_URI);
	}

	/**
	 * Query whether any registered Papyrus builder provider covers this model resource.
	 *
	 * @param resourceURI
	 *            the model resource URI
	 * @return whether it has a specific builder that means I can ignore it
	 */
	private boolean hasSpecificBuilder(URI resourceURI) {
		return Activator.getDefault().getBuilderProviders().stream()
				.anyMatch(provider -> provider.providesBuilder(PapyrusBuilderKind.MODEL_RESOURCE, resourceURI));
	}

	/**
	 * Query whether any registered Papyrus builder provider covers this model resource.
	 *
	 * @param resource
	 *            the model resource
	 * @return whether it has a specific builder that means I can ignore it
	 */
	private boolean hasSpecificBuilder(IResource resource) {
		return Activator.getDefault().getBuilderProviders().stream()
				.anyMatch(provider -> provider.providesBuilder(PapyrusBuilderKind.MODEL_RESOURCE, resource));
	}

}
