/**
 *
 * $Id$
 */
package SoaML.validation;

import org.eclipse.uml2.uml.Dependency;

/**
 * A sample validator interface for {@link SoaML.Expose}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ExposeValidator {
	boolean validate();

	boolean validateBase_Dependency(Dependency value);
}