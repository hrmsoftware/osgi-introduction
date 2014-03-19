package se.hrmsoftware.hello.caller.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.hrmsoftware.hello.service.HelloService;

import java.util.Arrays;

/**
 * Naive activator that looks up the service reference on
 * activation.
 */
public class Activator implements BundleActivator {

	private static final Logger LOG = LoggerFactory.getLogger(Activator.class);

	@Override
	public void start(BundleContext context) throws Exception {
		LOG.info("Starting Bundle");

		// Get our name.
		String myName = context.getBundle().getSymbolicName();

		// Find reference.
		ServiceReference<HelloService> ref = context.getServiceReference(HelloService.class);
		if (ref != null) {
			try {
				// Print service properties.
				String props = getServiceProperties(ref);
				LOG.info("Calling HelloService#sayHello('{}') Published by {}. Props:\n{}",
						myName, ref.getBundle().getSymbolicName(), props);
				// Get the service
				HelloService service = context.getService(ref);
				if (service != null) {
					service.sayHello(myName);
				}
			} finally {
				// Release reference
				context.ungetService(ref);
			}
		} else {
			LOG.info("No HelloService available at this time :(");
		}
	}

	private String getServiceProperties(ServiceReference<HelloService> ref) {
		StringBuilder builder = new StringBuilder();
		for (String key : ref.getPropertyKeys()){
			Object value = ref.getProperty(key);
			String strValue;
			if (value instanceof Object[]) {
				strValue = Arrays.toString((Object[]) value);
			} else {
				strValue = value.toString();
			}
			if (builder.length() > 0) {
				builder.append("\n");
			}
			builder.append("\t|").append(key).append(" : ").append(strValue);
		}
		return builder.toString();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		LOG.info("Stopping Bundle");
	}
}
