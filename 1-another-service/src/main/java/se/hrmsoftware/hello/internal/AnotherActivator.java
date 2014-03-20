package se.hrmsoftware.hello.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import se.hrmsoftware.hello.service.HelloService;

import java.util.Dictionary;
import java.util.Hashtable;

public class AnotherActivator implements BundleActivator {
	private ServiceRegistration<HelloService> serviceRegistration = null;
	@Override
	public void start(BundleContext context) throws Exception {
		Dictionary<String, Object> props =
				new Hashtable<String, Object>();
		props.put(Constants.SERVICE_DESCRIPTION, "Swedish Hello Service");
		serviceRegistration = context.registerService(HelloService.class, new AnotherHelloService(), props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}
}
