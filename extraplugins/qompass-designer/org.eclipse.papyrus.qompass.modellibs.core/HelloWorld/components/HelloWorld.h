// --------------------------------------------------------
// Code generated by Papyrus C++
// --------------------------------------------------------

#ifndef HELLOWORLD_COMPONENTS_HELLOWORLD_H
#define HELLOWORLD_COMPONENTS_HELLOWORLD_H

/************************************************************
 HelloWorld class header
 ************************************************************/

/* Owner package header include                             */
#include <HelloWorld/components/Pkg_components.h>

/* Owner package header include                             */
#include <PrimitiveTypes/Pkg_PrimitiveTypes.h>

#include <sysinterfaces/IStart.h>

#include <core/StdPorts/Cstart.h>

namespace components {

/************************************************************/
/**
 * Configuration attribute within the Hello World component. Its value is configured on the instance level.
 */

class HelloWorld: public ::StdPorts::Cstart, public IStart {

public:

	/**
	 * 
	 */
	String message;

	/**
	 * 
	 * 
	 */
	void run();

};
/************************************************************/
/* External declarations (package visibility)               */

/************************************************************/

/* Inline functions                                         */

} // of namespace components

/************************************************************
 End of HelloWorld class header
 ************************************************************/

#endif