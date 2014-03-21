package se.hrmsoftware.hello.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ManagedService;

import java.util.Dictionary;
import java.util.Hashtable;

public class Activator implements BundleActivator {


	public static final String SERVICE_PID = "se.hrmsoftware.configured";

	@Override
	public void start(BundleContext context) throws Exception {
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(Constants.SERVICE_PID, SERVICE_PID); // A managed service needs a PID.
		context.registerService(ManagedService.class, new Configuration(context), props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// There's actually no need to explicitly unregister the service
		// here - it's done by the framework when the bundle shuts down.
	}
}
