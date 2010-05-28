/**
 * 
 */
package org.eclipse.papyrus.core.services.internal;

import org.eclipse.papyrus.core.services.ServiceDescriptor;
import org.eclipse.papyrus.core.services.ServiceException;
import org.eclipse.papyrus.core.services.ServiceState;
import org.eclipse.papyrus.core.services.ServicesRegistry;


/**
 * An ServiceEntry managing {@link IService} registered as lazy start.
 * 
 * @author cedric dumoulin
 *
 */
public class LazyStartupEntry extends ServiceStartupEntry {

	/**
	 * The ServiceEntry, according to its type.
	 */
	private ServiceTypeEntry serviceEntry;
	
	protected ServicesRegistry registry;

	/**
	 * Constructor.
	 * 
	 * @param serviceDescriptor
	 * @param registry
	 */
	public LazyStartupEntry(ServiceTypeEntry serviceEntry, ServiceDescriptor serviceDescriptor, ServicesRegistry registry) {

		super( serviceEntry, serviceDescriptor);
		this.registry = registry;
	}

	/**
	 * @see org.eclipse.papyrus.core.services.AbstractServiceEntry#getServiceInstance()
	 *
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Object getServiceInstance() throws ServiceException {
		if( serviceEntry.getState() == ServiceState.registered)
		{
			// Start the service
			try {
				serviceEntry.createService();
				serviceEntry.initService(registry);
				serviceEntry.startService();
			} catch (Exception e) {
				// There was an error. The service is in error
				serviceEntry = new ErrorServiceTypeEntry(serviceEntry.getDescriptor());
			}
		}
		
		// Return the instance
		return serviceEntry.getServiceInstance();
	}

	/**
	 * Do nothing
	 *
	 * @throws ServiceException
	 */
	@Override
	public void createService() throws ServiceException {
	}

	/**
	 * Do nothing
	 * 
	 * @param servicesRegistry
	 * @throws ServiceException
	 */
	@Override
	public void initService(ServicesRegistry servicesRegistry) throws ServiceException {
	}

	/**
	 * Do nothing.
	 *
	 * @throws ServiceException
	 */
	@Override
	public void startService() throws ServiceException {
	}

	/**
	 * @see org.eclipse.papyrus.core.services.AbstractServiceEntry#disposeService()
	 *
	 * @throws ServiceException
	 */
	@Override
	public void disposeService() throws ServiceException {
		serviceEntry.disposeService();
	}

}
