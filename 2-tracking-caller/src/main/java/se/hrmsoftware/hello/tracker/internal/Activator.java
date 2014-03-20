package se.hrmsoftware.hello.tracker.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.hrmsoftware.hello.service.HelloService;

import java.util.Arrays;

public class Activator implements BundleActivator, ServiceTrackerCustomizer<HelloService, HelloService> {

	private static final Logger LOG = LoggerFactory.getLogger(Activator.class);
	private ServiceTracker<HelloService, HelloService> serviceTracker;
	private BundleContext myContext;

	@Override
	public void start(BundleContext context) throws Exception {
		myContext = context;
		serviceTracker = new ServiceTracker<HelloService, HelloService>(context, HelloService.class, this);
		serviceTracker.open();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (serviceTracker != null) {
			serviceTracker.close();
		}
		myContext = null;
	}

	@Override
	public HelloService addingService(ServiceReference<HelloService> reference) {
		LOG.info("Found HelloService published by {}: \n{}",
				reference.getBundle().getSymbolicName(), getServiceProperties(reference));
		HelloService service = myContext.getService(reference);
		if (service != null) {
			service.sayHello(myContext.getBundle().getSymbolicName());
		}
		return service; // returning something means we're tracking the service.
	}

	@Override
	public void modifiedService(ServiceReference<HelloService> reference, HelloService service) {
		// Any 'changes' to tracked services will end up in this callback.
	}

	@Override
	public void removedService(ServiceReference<HelloService> reference, HelloService service) {
		LOG.info("HelloService published by {} is being removed: \n{}",
				reference.getBundle().getSymbolicName(), getServiceProperties(reference));
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
}
