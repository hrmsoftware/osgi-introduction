package se.hrmsoftware.hello.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import se.hrmsoftware.hello.service.HelloService;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Activator that publishes the HelloService implementation.
 */
public class Activator implements BundleActivator {

	private ServiceRegistration<HelloService> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		Dictionary<String, Object> props = new Hashtable<>();
		props.put(Constants.SERVICE_DESCRIPTION, "Simple Hello World Service");
		serviceRegistration = context.registerService(HelloService.class, new SimpleHelloService(), props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}
}
