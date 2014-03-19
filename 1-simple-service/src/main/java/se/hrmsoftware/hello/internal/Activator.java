package se.hrmsoftware.hello.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.hrmsoftware.hello.service.HelloService;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Activator that publishes the HelloService implementation.
 */
public class Activator implements BundleActivator {

	private static final Logger LOG = LoggerFactory.getLogger(Activator.class);
	private ServiceRegistration<HelloService> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		LOG.info("Starting Bundle and Registering service");
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(Constants.SERVICE_DESCRIPTION, "Simple Hello World Service");
		serviceRegistration = context.registerService(HelloService.class, new SimpleHelloService(), props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		LOG.info("Stopping Bundle and Unregistering service");
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}
}
