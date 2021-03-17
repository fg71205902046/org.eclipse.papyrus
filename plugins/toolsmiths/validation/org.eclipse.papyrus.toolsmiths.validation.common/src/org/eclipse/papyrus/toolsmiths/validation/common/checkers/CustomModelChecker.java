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

package org.eclipse.papyrus.toolsmiths.validation.common.checkers;

import static org.eclipse.papyrus.toolsmiths.validation.common.checkers.ModelValidationChecker.createSubstitutionLabelProvider;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EValidatorRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.toolsmiths.validation.common.Activator;

/**
 * <p>
 * A configurable model checker that recursively walks the model to evaluate custom
 * checks that are either not available in or not appropriate to be defined in the
 * model's intrinsic {@code EValidator}.
 * </p>
 * <p>
 * <strong><em>Important!</em></strong> Do not configure this checker with registered
 * validators. The {@link ModelValidationChecker} class covers those automatically.
 * </p>
 */
public class CustomModelChecker extends AbstractPluginChecker {

	/**
	 * The EMF model resource to validate.
	 */
	private final Resource resource;

	private final Map<String, Function<? super String, ? extends EValidator>> validatorFactories = new HashMap<>();

	private final EValidator nullValidator = new NullValidator();

	/**
	 * Constructor.
	 *
	 * @param modelFile
	 *            the model file being validated
	 * @param resource
	 *            the EMF resource loaded from the model file
	 * @param markerType
	 *            the marker type to create for reported problems
	 */
	public CustomModelChecker(final IFile modelFile, final Resource resource, String markerType) {
		super(modelFile.getProject(), modelFile, markerType);

		this.resource = resource;
	}

	/**
	 * Add a validator factory for a package. The input to the factory is the package namespace URI.
	 * This allows the same factory to be used for multiple packages.
	 *
	 * @param packageNSURI
	 *            the package for which to add a validator factory
	 * @param validatorFactory
	 *            the validator factory to add
	 */
	public CustomModelChecker withValidator(String packageNSURI, Function<? super String, ? extends EValidator> validatorFactory) {
		validatorFactories.put(packageNSURI, validatorFactory);
		return this;
	}

	@Override
	public void check(final DiagnosticChain diagnostics, final IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Check file '" + getModelFile().getName() + "'.", 1); //$NON-NLS-1$ //$NON-NLS-2$

		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		final EValidator.SubstitutionLabelProvider labels = createSubstitutionLabelProvider(adapterFactory);

		Map<Object, Object> context = new HashMap<>();
		context.put(EValidator.SubstitutionLabelProvider.class, labels);

		// Get the resource and validate it
		try {
			BasicDiagnostic validationResults = new BasicDiagnostic();
			Diagnostician diagnostician = new Diagnostician(new ValidatorRegistry());

			for (EObject next : resource.getContents()) {
				diagnostician.validate(next, validationResults, context);
			}

			if (validationResults.getSeverity() > Diagnostic.OK) {
				diagnostics.merge(wrap(validationResults));
			}
		} finally {
			adapterFactory.dispose();
		}

		subMonitor.worked(1);
		SubMonitor.done(monitor);
	}

	//
	// Nested types
	//

	@SuppressWarnings("serial") // Never serialized
	private final class ValidatorRegistry extends EValidatorRegistryImpl {

		private final Function<String, EValidator> nullFactory = __ -> nullValidator;

		@Override
		protected Object delegatedGet(Object key) {
			EValidator result = nullValidator;

			if (key instanceof EPackage) {
				EPackage ePackage = (EPackage) key;
				String nsURI = ePackage.getNsURI();

				result = validatorFactories.getOrDefault(nsURI, nullFactory).apply(nsURI);
				if (result instanceof SwitchValidator) {
					((SwitchValidator) result).setOwner(CustomModelChecker.this);
				}
				put(ePackage, result);
			}

			return result;
		}

	}

	private static final class NullValidator implements EValidator {

		@Override
		public boolean validate(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			return true;
		}

		@Override
		public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			return true;
		}

		@Override
		public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
			return true;
		}

	}

	/**
	 * <p>
	 * A convenient superclass for switch-style validators. Subclasses need simply declare, for each {@code EClass} in their
	 * model that requires validation, a method of the form
	 * </p>
	 * <blockquote>
	 * <tt>public void validate(<em>&lt;eclass&gt;</em> object, DiagnosticChain diagnostics, Map&lt;Object, Object&gt; context)</tt>
	 * </blockquote>
	 * <p>
	 * where <em>&lt;eclass&gt;</em> is the generated Java interface type of the model class.
	 * Methods matching the type (or any supertype) of an object in the model resource being validated will be invoked
	 * to collect diagnostics. Note that methods must be publicly accessible by this framework, so declared with public visibility
	 * on a public class in a package that is exported by its module.
	 * </p>
	 * <p>
	 * Additionally, if the subclass declares exactly the following method, then it will be invoked on every instance of a
	 * class from its package:
	 * </p>
	 * <blockquote>
	 * <tt>public void validateDefault(EObject object, DiagnosticChain diagnostics, Map&lt;Object, Object&gt; context)</tt>
	 * </blockquote>
	 */
	public static abstract class SwitchValidator implements EValidator {

		private final String nsURI;
		private final String source;

		private final Map<EClass, MethodHandle> validationMethods = new HashMap<>();

		private CustomModelChecker owner;

		/**
		 * Initializes me with the package that I validate.
		 *
		 * @param ePackage
		 *            my package
		 */
		public SwitchValidator(EPackage ePackage) {
			this(ePackage.getNsURI());
		}

		/**
		 * Initializes me with the namespace URI of the package that I validate.
		 *
		 * @param ePackage
		 *            my package namespace URI
		 */
		public SwitchValidator(String nsURI) {
			super();

			this.nsURI = nsURI;
			this.source = getClass().getSimpleName();
		}

		void setOwner(CustomModelChecker owner) {
			this.owner = owner;
		}

		/**
		 * Get a nice label for the given {@code object} via the {@linkplain EValidator.SubstitutionLabelProvider label provider}
		 * in the validation {@code context}.
		 *
		 * @param object
		 *            an object for which to get a label
		 * @param context
		 *            the validation context
		 * @return the label
		 */
		protected String getObjectLabel(EObject object, Map<Object, Object> context) {
			return EObjectValidator.getObjectLabel(object, context);
		}

		/**
		 * Get a nice label for the given {@code feature} via the {@linkplain EValidator.SubstitutionLabelProvider label provider}
		 * in the validation {@code context}.
		 *
		 * @param feature
		 *            feature of an object for which to get a label
		 * @param context
		 *            the validation context
		 * @return the label
		 */
		protected String getFeatureLabel(EStructuralFeature feature, Map<Object, Object> context) {
			return EObjectValidator.getFeatureLabel(feature, context);
		}

		/**
		 * Get a nice label for the given {@code value} of an attribute via the
		 * {@linkplain EValidator.SubstitutionLabelProvider label provider} in the validation {@code context}.
		 *
		 * @param dataType
		 *            the data type of the {@code value}
		 * @param value
		 *            the value of some attribute of an object for which to get a label
		 * @param context
		 *            the validation context
		 * @return the label
		 */
		protected String getValueLabel(EDataType dataType, Object value, Map<Object, Object> context) {
			return EObjectValidator.getValueLabel(dataType, value, context);
		}

		/**
		 * Format a string based on a standard message {@code pattern} syntax with arguments
		 * pretty-printed as well as possible by the label provider in the validation {@code context}.
		 *
		 * @param pattern
		 *            the message pattern
		 * @param context
		 *            the validation context
		 * @param argument
		 *            arguments to the message pattern
		 *
		 * @return the formatted string
		 */
		protected final String format(String pattern, Map<Object, Object> context, Object... argument) {
			Object[] bindings = Stream.of(argument).map(arg -> formatArgument(arg, context)).toArray();
			return NLS.bind(pattern, bindings);
		}

		/**
		 * Create a token for an argument to the {@link #format(String, Map, Object...)} API that
		 * pretty-prints the value of an attribute of the object that is validated.
		 *
		 * @param attribute
		 *            an attribute of the object that is validated
		 * @param value
		 *            the value of the attribute
		 * @return the argument token for the format pattern
		 */
		protected final Object value(EAttribute attribute, Object value) {
			return value(attribute.getEAttributeType(), value);
		}

		/**
		 * Create a token for an argument to the {@link #format(String, Map, Object...)} API that
		 * pretty-prints the value of an attribute of the object that is validated.
		 *
		 * @param dataType
		 *            data type of the value of an attribute of the object that is validated
		 * @param value
		 *            the value of the attribute
		 * @return the argument token for the format pattern
		 */
		protected final Object value(EDataType dataType, Object value) {
			return new Value(dataType, value);
		}

		private Object formatArgument(Object argument, Map<Object, Object> context) {
			Object result = argument;
			if (argument instanceof EStructuralFeature) {
				result = getFeatureLabel((EStructuralFeature) argument, context);
			} else if (argument instanceof EObject) {
				result = getObjectLabel((EObject) argument, context);
			} else if (argument instanceof Value) {
				Value value = (Value) argument;
				result = getValueLabel(value.dataType, value.value, context);
			}
			return result;
		}

		protected Diagnostic createDiagnostic(int severity, EObject eObject, EStructuralFeature feature, int code, String message) {
			List<Object> data = diagnosticData(eObject, feature);

			return new BasicDiagnostic(severity, source, code, message, data.toArray());
		}

		protected Diagnostic createDiagnostic(int severity, EObject eObject, int code, String message) {
			List<Object> data = diagnosticData(eObject, null);

			return new BasicDiagnostic(severity, source, code, message, data.toArray());
		}

		protected Diagnostic createDiagnostic(int severity, EObject eObject, EStructuralFeature feature, String message) {
			return createDiagnostic(severity, eObject, feature, 0, message);
		}

		protected Diagnostic createDiagnostic(int severity, EObject eObject, String message) {
			return createDiagnostic(severity, eObject, 0, message);
		}

		private List<Object> diagnosticData(EObject eObject, EStructuralFeature feature) {
			List<Object> result = new ArrayList<>();
			result.add(eObject);
			if (feature != null) {
				result.add(feature);
			}

			if (owner != null) {
				result.add(owner.getProject());
				result.add(owner.getModelFile());
				result.add(IPluginChecker2.markerType(owner.getMarkerType()));
			}

			return result;
		}

		protected boolean isValidatorFor(EPackage ePackage) {
			return nsURI.equals(ePackage.getNsURI());
		}

		@Override
		public final boolean validate(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			return validate(eObject.eClass(), eObject, diagnostics, context);
		}

		@Override
		public final boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			EPackage ePackage = eClass.getEPackage();
			if (isValidatorFor(ePackage)) {
				doValidate(eClass, eObject, diagnostics, context);

				if (!eClass.getESuperTypes().isEmpty()) {
					for (EClass superClass : eClass.getESuperTypes()) {
						validate(superClass, eObject, diagnostics, context);
					}
				}

				if (!isValidatorFor(EcorePackage.eINSTANCE)) {
					// Try the default EObject case, too
					doValidate(EcorePackage.Literals.EOBJECT, eObject, diagnostics, context);
				}
			}

			return true; // We always collect diagnostics
		}

		// Not used
		@Override
		public final boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
			return true;
		}

		private void doValidate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			MethodHandle validationMethod = getValidationMethod(eClass);
			try {
				validationMethod.invoke(eObject, diagnostics, context);
			} catch (Error e) {
				throw e;
			} catch (Throwable e) {
				Activator.log.error("Uncaught exception in validation method.", e); //$NON-NLS-1$
			}
		}

		private MethodHandle getValidationMethod(EClass eClass) {
			MethodHandle result = validationMethods.get(eClass);
			if (result == null) {
				result = lookupValidationMethod(eClass);
				validationMethods.put(eClass, result);
			}

			return result;
		}

		private MethodHandle lookupValidationMethod(EClass eClass) {
			MethodHandle result;
			MethodType methodType = validationMethodType(eClass);

			try {
				String methodName = eClass == EcorePackage.Literals.EOBJECT ? "validateDefault" : "validate"; //$NON-NLS-1$//$NON-NLS-2$
				result = MethodHandles.lookup().findVirtual(getClass(), methodName, methodType).bindTo(this);
			} catch (Exception e) {
				// It's normal for a validation case not to be defined
				result = MethodHandles.empty(methodType);
			}

			return result;
		}

		private MethodType validationMethodType(EClass eClass) {
			return MethodType.methodType(void.class, eClass.getInstanceClass(), DiagnosticChain.class, Map.class);
		}
	}

	/**
	 * Opaque wrapper for an attribute value that provides its data type for pretty-printing.
	 */
	private static final class Value {
		final EDataType dataType;
		final Object value;

		Value(EDataType dataType, Object value) {
			this.dataType = dataType;
			this.value = value;
		}
	}

}
