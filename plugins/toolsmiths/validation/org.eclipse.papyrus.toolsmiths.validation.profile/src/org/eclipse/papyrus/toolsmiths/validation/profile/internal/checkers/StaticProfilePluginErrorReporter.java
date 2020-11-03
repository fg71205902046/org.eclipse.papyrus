/*****************************************************************************
 * Copyright (c) 2020 CEA LIST, EclipseSource and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Remi SChnekenburger (EclipseSource) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.toolsmiths.validation.profile.internal.checkers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.toolsmiths.validation.profile.Activator;
import org.eclipse.papyrus.toolsmiths.validation.profile.internal.messages.Messages;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.pde.internal.core.builders.CompilerFlags;
import org.eclipse.pde.internal.core.builders.IncrementalErrorReporter.VirtualMarker;
import org.eclipse.pde.internal.core.builders.PluginBaseErrorReporter;
import org.eclipse.pde.internal.core.builders.XMLErrorReporter;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Error reporter for specific extensions for static profiles.
 */
@SuppressWarnings("restriction")
public class StaticProfilePluginErrorReporter extends PluginBaseErrorReporter {

	private static final String NAME = "name";

	private static final String PLATFORM_PLUGIN = "platform:/plugin/";

	private static final String LOCATION = "location";

	private static final String ORG_ECLIPSE_UML2_UML_GENERATED_PACKAGE = "org.eclipse.uml2.uml.generated_package";//$NON-NLS-1$

	private static final String ORG_ECLIPSE_EMF_ECORE_GENERATED_PACKAGE = "org.eclipse.emf.ecore.generated_package";//$NON-NLS-1$
	private static final String NS_URI = "nsURI"; //$NON-NLS-1$
	private static final String ECORE_EPACKAGE_STEREOTYPE = "Ecore::EPackage"; //$NON-NLS-1$

	private static final String PAPYRUS_UML_PROFILES_EXTENSION_POINT_FULL_ID = "org.eclipse.papyrus.uml.extensionpoints.UMLProfile"; //$NON-NLS-1$
	private static final String STATIC_PROFILE_CATEGORY = "Papyrus-staticProfile";//$NON-NLS-1$

	public static final String STATIC_PROFILE_MARKER_ATTRIBUTE = "staticProfile"; //$NON-NLS-1$

	private static final String POINT = "point"; //$NON-NLS-1$

	private static final int FIX_ID = 0;

	private static final String SEPARATOR = "_";

	private final List<String> foundPoints = new ArrayList<>();

	private final Map<String, String> localURIMappings = new HashMap<>();

	private Profile profile;
	private IFile profileFile;

	private String sourceID;

	private List<Element> papyrusProfileExtensions = new ArrayList<>();

	/**
	 * Constructor.
	 *
	 * @param file
	 *            the plugin.xml file
	 * @param profile
	 *            the profile model element
	 * @param profileFile
	 *            the profile containing file.
	 */
	public StaticProfilePluginErrorReporter(IFile file, Profile profile, IFile profileFile) {
		super(file);
		this.profile = profile;
		this.fFile = file;
		this.profileFile = profileFile;
		sourceID = sourceID(profileFile, profile);
		replaceReporter(this, file);

	}

	/**
	 * Replace the reporter created by default on abstract class, to implement our specific one.
	 *
	 * @see SelectiveDeleteErrorReporter.
	 */
	private void replaceReporter(StaticProfilePluginErrorReporter reporter, IFile file) {
		Field errorReporterField;
		try {
			errorReporterField = XMLErrorReporter.class.getDeclaredField("fErrorReporter"); //$NON-NLS-1$
			errorReporterField.setAccessible(true);
			Field modifiersField = Field.class.getDeclaredField("modifiers");//$NON-NLS-1$
			modifiersField.setAccessible(true);
			modifiersField.setInt(modifiersField, modifiersField.getModifiers() & ~Modifier.FINAL);
			errorReporterField.set(reporter, new SelectiveDeleteErrorReporter(file, sourceID));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			Activator.log.error(e);
		}

	}

	/**
	 * Returns a unique id for the specified profile model element in the given profile file.
	 *
	 * @param file
	 *            the profile file
	 * @param profile
	 *            the profile model element
	 * @return the unique identifier for this profile in the plugin, which will allow to identify markers associated to this profile on the plugin.xml
	 */
	private static String sourceID(IFile file, Profile profile) {
		StringBuilder builder = new StringBuilder();
		builder.append("staticprofile");
		builder.append(SEPARATOR);
		builder.append(file.getProjectRelativePath().toString());
		builder.append(SEPARATOR);
		String name = profile.getQualifiedName();
		builder.append(name);
		return builder.toString();
	}

	@Override
	protected String getRootElementName() {
		return "plugin";
	}

	@Override
	public void validate(IProgressMonitor monitor) {
		super.validate(monitor);

		postValidatePapyrusExtensions();

		// now check the found extensions and throw error for inexisting ones
		if (!foundPoints.contains(ORG_ECLIPSE_EMF_ECORE_GENERATED_PACKAGE)) {
			reportNoEcoreGeneratedPackage();
		}
		if (!foundPoints.contains(ORG_ECLIPSE_UML2_UML_GENERATED_PACKAGE)) {
			reportNoUML2GeneratedPackage();
		}
		if (!foundPoints.contains(PAPYRUS_UML_PROFILES_EXTENSION_POINT_FULL_ID)) {
			if (profile.getNestingPackage() == null) {
				reportNoPapyrusProfile();
			}
		}
	}

	/**
	 * Validate the specific static profile extensions once the validation has visited all extensions in the plugin.xml file.
	 */
	private void postValidatePapyrusExtensions() {
		if (profile.getNestingPackage() != null) {
			return;
		}

		String profilePath = profileFile.getProjectRelativePath().removeFileExtension().addFileExtension(UmlModel.UML_FILE_EXTENSION).toString();

		for (Element papyrusProfileExtension : papyrusProfileExtensions) {
			String name = papyrusProfileExtension.getAttribute(NAME);
			String path = decodePath(papyrusProfileExtension.getAttribute("path"));
			// check path
			if (profilePath.equals(path)) {
				foundPoints.add(PAPYRUS_UML_PROFILES_EXTENSION_POINT_FULL_ID);
				// UI name shall not be null
				if (name == null || name.isEmpty()) {
					VirtualMarker marker = report(NLS.bind(Messages.StaticProfilePluginErrorReporter_uiLabelIsNull, profile.getLabel()), getLine(papyrusProfileExtension, NAME), CompilerFlags.WARNING, FIX_ID, papyrusProfileExtension, NAME,
							STATIC_PROFILE_CATEGORY);
					addMarkerAttribute(marker, STATIC_PROFILE_MARKER_ATTRIBUTE, profile.getLabel());
				}
			}
		}
	}

	/**
	 * Reports that no ECore generated package was found for the current profile.
	 */
	private void reportNoEcoreGeneratedPackage() {
		VirtualMarker marker = reportForProfile(NLS.bind(Messages.StaticProfilePluginErrorReporter_noEcoreGeneratedPackageFound, profile.getLabel()), 1, CompilerFlags.ERROR, STATIC_PROFILE_CATEGORY);
		addMarkerAttribute(marker, STATIC_PROFILE_MARKER_ATTRIBUTE, profile.getLabel());
	}

	private VirtualMarker reportForProfile(String message, int line, int severity, String category) {
		VirtualMarker marker = report(message, line, severity, category);
		addMarkerID(marker);
		return marker;
	}

	private void addMarkerID(VirtualMarker marker) {
		if (marker == null) {
			return;
		}
		addMarkerAttribute(marker, SelectiveDeleteErrorReporter.SOURCE_ID, sourceID);
	}

	/**
	 * Reports that no UML2 generated package was found for the current profile.
	 */
	private void reportNoUML2GeneratedPackage() {
		VirtualMarker marker = reportForProfile(NLS.bind(Messages.StaticProfilePluginErrorReporter_noUML2GeneratedPackage, profile.getLabel()), 1, CompilerFlags.ERROR, STATIC_PROFILE_CATEGORY);
		addMarkerAttribute(marker, STATIC_PROFILE_MARKER_ATTRIBUTE, profile.getLabel());
	}

	/**
	 * Reports that no Papyrus profile extension was found for the current profile.
	 */
	private void reportNoPapyrusProfile() {
		VirtualMarker marker = reportForProfile(NLS.bind(Messages.StaticProfilePluginErrorReporter_NoPapyrusProfileExtensionFound, profile.getLabel()), 1, CompilerFlags.WARNING, STATIC_PROFILE_CATEGORY);
		addMarkerAttribute(marker, STATIC_PROFILE_MARKER_ATTRIBUTE, profile.getLabel());
	}

	@Override
	protected void validateExtension(Element element) {
		// do not let default validation be done, this will be done by standard plugin builder
		String pointID = element.getAttribute(POINT);
		// find the correct checker
		switch (pointID) {
		case ORG_ECLIPSE_EMF_ECORE_GENERATED_PACKAGE:
			if (foundPoints.contains(ORG_ECLIPSE_EMF_ECORE_GENERATED_PACKAGE)) {
				// already found the extension that should be checked, so avoid checking others.
				break;
			}
			// check if this is the correct profile to test
			checkEcoreGeneratedPackage(element);
			break;
		case ORG_ECLIPSE_UML2_UML_GENERATED_PACKAGE:
			if (foundPoints.contains(ORG_ECLIPSE_UML2_UML_GENERATED_PACKAGE)) {
				break;
			}
			checkUML2GeneratedPackage(element);
			break;
		case PAPYRUS_UML_PROFILES_EXTENSION_POINT_FULL_ID:
			if (foundPoints.contains(PAPYRUS_UML_PROFILES_EXTENSION_POINT_FULL_ID)) {
				break;
			}
			checkPapyrusProfile(element);
			break;
		case "org.eclipse.emf.ecore.uri_mapping":
			collectMapping(element);
			break;
		default:
			break;
		}

	}

	private void collectMapping(Element element) {
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			Node node = element.getChildNodes().item(i);
			if (node instanceof Element && "mapping".equals(((Element) node).getNodeName())) {//$NON-NLS-1$
				Element profileElement = (Element) node;
				String sourceURI = profileElement.getAttribute("source");//$NON-NLS-1$
				String targetURI = profileElement.getAttribute("target");//$NON-NLS-1$
				localURIMappings.put(sourceURI, targetURI);
			}
		}
	}

	private void checkPapyrusProfile(Element element) {
		/*
		 * <extension point="org.eclipse.papyrus.uml.extensionpoints.UMLProfile">
		 * <profile
		 * description="UML profile for SysML (from OMG SysML V1.4)"
		 * iconpath="resources/icons/SysMLProfile.gif"
		 * name="SysML 1.4"
		 * path="pathmap://SysML14_PROFILES/SysML.profile.uml"
		 * provider="Eclipse Modeling Project">
		 * </profile>
		 * </extension>
		 */
		// should check only for root profiles
		if (profile.getNestingPackage() != null) {
			return;
		}

		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			Node node = element.getChildNodes().item(i);
			if (node instanceof Element && "profile".equals(((Element) node).getNodeName())) {//$NON-NLS-1$
				papyrusProfileExtensions.add((Element) node);
			}
		}

	}

	private void checkUML2GeneratedPackage(Element element) {
		/*
		 * <extension point="org.eclipse.uml2.uml.generated_package">
		 * <profile uri="http://www.eclipse.org/papyrus/sysml/1.4/SysML"
		 * location="pathmap://SysML14_PROFILES/SysML.profile.uml#SysML"/>
		 * <profile
		 * location="pathmap://SysML14_PROFILES/SysML.profile.uml#SysML.package_packagedElement_Activities"
		 * uri="http://www.eclipse.org/papyrus/sysml/1.4/SysML/Activities">
		 * </profile>
		 */
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			Node node = element.getChildNodes().item(i);
			if (node instanceof Element && "profile".equals(((Element) node).getNodeName())) { //$NON-NLS-1$
				Element profileElement = (Element) node;
				String extensionNsURI = profileElement.getAttribute("uri");//$NON-NLS-1$
				String extensionlocation = profileElement.getAttribute(LOCATION);

				// check this is the good extension point => genmodel should match first
				String stereotypeNsURI = profileURI(profile);
				if (Objects.equals(stereotypeNsURI, extensionNsURI)) {
					foundPoints.add(ORG_ECLIPSE_UML2_UML_GENERATED_PACKAGE);

					final String profileId = ((XMIResource) profile.eResource()).getID(profile);
					String uml2ProfileFile = profileFile.getProjectRelativePath().removeFileExtension().addFileExtension(UmlModel.UML_FILE_EXTENSION).lastSegment();

					if (!extensionlocation.endsWith(uml2ProfileFile + "#" + profileId)) { //$NON-NLS-1$
						VirtualMarker marker = reportForProfile(NLS.bind(Messages.StaticProfilePluginErrorReporter_wrongLocationForProfile, profile.getLabel()), getLine(profileElement, LOCATION), CompilerFlags.ERROR, FIX_ID, profileElement, LOCATION,
								STATIC_PROFILE_CATEGORY);
						addMarkerAttribute(marker, STATIC_PROFILE_MARKER_ATTRIBUTE, profile.getLabel());
					}
				}
			}
		}

	}

	private VirtualMarker reportForProfile(String message, int line, int severity, int fixId, Element element, String attrName,
			String category) {
		VirtualMarker marker = report(message, line, severity, fixId, element, attrName, category);
		addMarkerID(marker);
		return marker;
	}

	private void checkEcoreGeneratedPackage(Element element) {
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			Node node = element.getChildNodes().item(i);
			if (node instanceof Element && "package".equals(((Element) node).getNodeName())) {//$NON-NLS-1$
				Element packageElement = (Element) node;
				String extensionNsURI = packageElement.getAttribute("uri");//$NON-NLS-1$
				String extensionGenModel = packageElement.getAttribute("genModel");//$NON-NLS-1$

				// check this is the good extension point => genmodel should match first
				if (extensionGenModel == null) {
					return;
				}

				// retrieve profile file path and compare with given path in extension point
				IPath projectRelativePath = profileFile.getProjectRelativePath();
				String genModelFile = projectRelativePath.removeFileExtension().addFileExtension("genmodel").toString(); //$NON-NLS-1$
				// compare with genmodel
				if (!Objects.equals(genModelFile, extensionGenModel)) {
					// this one did not match, return;
					return;
				}

				// compare URI with profile uri in stereotype ecore::epackage
				String stereotypeNsURI = profileURI(profile);
				if (Objects.equals(stereotypeNsURI, extensionNsURI)) {
					foundPoints.add(ORG_ECLIPSE_EMF_ECORE_GENERATED_PACKAGE);
				}
			}
		}
	}

	private static String profileURI(Profile profile) {
		Stereotype ePackageSt = profile.getAppliedStereotype(ECORE_EPACKAGE_STEREOTYPE);
		if (ePackageSt != null) {
			Object value = profile.getValue(ePackageSt, NS_URI);
			return (String) value;

		}
		return null;
	}

	private String decodePath(String path) {
		if (path == null) {
			return null;
		}

		if (path.startsWith("pathmap://")) {
			// try to decode using uri mappers extensions
			return decodePathmapPath(path);
		} else if (path.startsWith(PLATFORM_PLUGIN)) {
			// check if path is valid within the plugin
			return decodePlatformPath(path);
		}

		// relative path?
		return path;
	}

	private String decodePlatformPath(String path) {
		return cutPluginPath(path);
	}

	private String decodePathmapPath(String path) {
		String decodePath = null;
		// check first local mappings
		for (Entry<String, String> entry : localURIMappings.entrySet()) {
			String sourceURI = entry.getKey().toString();
			if (path.startsWith(sourceURI)) {
				String targetURI = entry.getValue();
				decodePath = replaceString(path, sourceURI, targetURI);
				return cutPluginPath(decodePath);
			}
		}
		for (Entry<URI, URI> entry : URIMappingRegistryImpl.INSTANCE.entrySet()) {
			String sourceURI = entry.getKey().toString();
			if (path.startsWith(sourceURI)) {
				String targetURI = entry.getValue().toString();
				decodePath = replaceString(path, sourceURI, targetURI);
				return cutPluginPath(decodePath);
			}
		}

		// cut platform:/plugin/<profile-name> to get a project relative path
		return path;
	}

	private String cutPluginPath(String decodePath) {
		if (decodePath.startsWith(PLATFORM_PLUGIN)) {
			String cutPath = decodePath.substring(PLATFORM_PLUGIN.length());
			int index = cutPath.indexOf('/');
			cutPath = cutPath.substring(index + 1); // remove initial '/'
			return cutPath;
		}
		return decodePath;
	}

	private String replaceString(String path, String sourceURI, String targetURI) {
		String newPath = path.substring(sourceURI.length(), path.length());
		if (!targetURI.endsWith("/")) {
			newPath = "/".concat(newPath);
		}
		newPath = targetURI.concat(newPath);
		return newPath;
	}

}
