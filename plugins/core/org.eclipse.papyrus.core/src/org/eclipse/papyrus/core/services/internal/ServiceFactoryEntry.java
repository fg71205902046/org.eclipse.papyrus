/**
 * 
 */
package org.eclipse.papyrus.core.services.internal;

import org.eclipse.papyrus.core.services.BadStateException;
import org.eclipse.papyrus.core.services.IService;
import org.eclipse.papyrus.core.services.IServiceFactory;
import org.eclipse.papyrus.core.services.ServiceDescriptor;
import org.eclipse.papyrus.core.services.ServiceException;
import org.eclipse.papyrus.core.services.ServiceState;
import org.eclipse.papyrus.core.services.ServicesRegistry;




/**
 * Entry of a Service implementing {@link IServiceFactory}.
 * This class provide methods to manage the Service life cycle.
 * 
 * @author cedric dumoulin
 * 
 */
public class ServiceFactoryEntry extends ServiceTypeEntry {

	/** Instance of the service, if started. */
	private Object serviceInstance;

	/** Instance of the factory, if created. */
	private IServiceFactory factoryInstance;

	/**
	 * Constructor.
	 * 
	 * @param serviceDescriptor
	 * @param registry
	 */
	public ServiceFactoryEntry(ServiceDescriptor serviceDescriptor, ServicesRegistry registry) {
		this.serviceDescriptor = serviceDescriptor;
		this.registry = registry;
		setState(ServiceState.registered);

	}


	/**
	 * Create an entry for an already created service.
	 * Constructor.
	 * 
	 * @param descriptor
	 *        Descriptor of the service. Key and priority should be set.
	 * @param serviceInstance
	 *        The service Instance
	 */
	public ServiceFactoryEntry(ServiceDescriptor descriptor, IServiceFactory factoryInstance) {
		this.serviceDescriptor = descriptor;
		this.factoryInstance = factoryInstance;
		setState(ServiceState.registered);
	}

	/**
	 * Get the service instance, even if it is not started.
	 * The service should be created.
	 * 
	 * @return
	 * @throws ServiceException
	 *         If service can't be started.
	 */
	public Object getServiceInstance() throws ServiceException {
		
		if( serviceInstance == null)
			throw new BadStateException("Service is not created.", state, serviceDescriptor);
		
		return serviceInstance;
			
	}


	/**
	 * @see java.lang.Object#toString()
	 * @return
	 * 
	 */
	@Override
	public String toString() {
		return "ServiceEntry [serviceDescriptor=" + serviceDescriptor.toString() + ", serviceInstance=" + serviceInstance + "]";
	}


	/**
	 * Create the associated service if not a Lazy Service.
	 * 
	 * @throws ServiceException
	 */
	public void createService() throws ServiceException {
		checkState(ServiceState.registered);
		// Exit if already  created.
		if( factoryInstance == null)
		{
//			factoryInstance = instanciateService();
			return;
		}
		
		// Create the service
		try {
			// Create the instance
			serviceInstance = factoryInstance.createService();
		} catch (Exception e) {
			setState(ServiceState.error);
			throw new ServiceException(e);
		}
		setState(ServiceState.created);
	}

	/**
	 * Start the associated service if not a Lazy Service.
	 * 
	 * @param servicesRegistry
	 *        The servicesRegistry containing this service.
	 * 
	 * @throws ServiceException
	 */
	public void initService(ServicesRegistry servicesRegistry) throws ServiceException {
		checkState(ServiceState.created);
		try {
			factoryInstance.init(servicesRegistry);
		} catch (ServiceException e) {
			setState(ServiceState.error);
			throw e;
		} catch (Exception e) {
			setState(ServiceState.error);
			throw new ServiceException(e);
		}

		setState(ServiceState.initialized);
	}

	/**
	 * Start the associated service if not a Lazy Service.
	 * 
	 * @throws ServiceException
	 */
	public void startService() throws ServiceException {
		
		checkState(ServiceState.initialized);
		setState(ServiceState.starting);
		
		try {
			factoryInstance.startService();
		} catch (ServiceException e) {
			setState(ServiceState.error);
			throw e;
		} catch (Exception e) {
			setState(ServiceState.error);
			throw new ServiceException(e);
		}
		
		setState(ServiceState.started);
	}

	/**
	 * Dispose the service.
	 */
	public void disposeService() throws ServiceException {
		if(factoryInstance == null)
			return;

		factoryInstance.disposeService();
		factoryInstance = null;
		setState(ServiceState.disposed);
	}


}
