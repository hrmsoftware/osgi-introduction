package se.hrmsoftware.hello.internal;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import se.hrmsoftware.hello.service.HelloService;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * This class publishes (and withdraws) a HelloService that is configured with the configured template string.
 */
public class Configuration implements ManagedService {

	public static final String FORMAT_PROP = "hello.format";
	private final BundleContext bundleContext;
	private final HelloService defaultService = new ConfigurableHelloService(ConfigurableHelloService.DEFAULT_FORMAT);
	private final Dictionary<String, Object> serviceProps = new Hashtable<String, Object>() {{
		put(Constants.SERVICE_DESCRIPTION, "Configurable Hello Service");
	}};
	private ServiceRegistration<HelloService> registration = null;

	public Configuration(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	@Override
	public void updated(Dictionary<String, ?> properties) throws ConfigurationException {
		if (properties == null) { // No configuration available
			republish(defaultService);
		} else {
			String format = properties.get(FORMAT_PROP) + "";
			republish(new ConfigurableHelloService(format));
		}
	}

	private void republish(HelloService service) {
		if (registration != null) {
			registration.unregister();
		}
		registration = bundleContext.registerService(HelloService.class, service, serviceProps);
	}


}
