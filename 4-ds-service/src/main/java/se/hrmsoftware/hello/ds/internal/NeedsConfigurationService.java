package se.hrmsoftware.hello.ds.internal;


import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.ConfigurationPolicy;
import aQute.bnd.annotation.component.Deactivate;
import aQute.bnd.annotation.metatype.Configurable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.hrmsoftware.hello.service.HelloService;

import java.util.Map;

@Component(configurationPolicy = ConfigurationPolicy.require, designate = Configuration.class)
public class NeedsConfigurationService implements HelloService {

	private final static Logger LOG = LoggerFactory.getLogger(NeedsConfigurationService.class);
	private Configuration myConfiguration = null;

	@Override
	public void sayHello(String caller) {
		String text = myConfiguration.formatString();
		String textToShow = String.format(text, caller);
		if (myConfiguration.useUpperCase()) {
			textToShow = textToShow.toUpperCase();
		}
		System.out.println(textToShow);
	}

	@Activate
	void onActivate(Map<String, Object> configurationProps) {
		LOG.info("Activating component with configuration: {}", configurationProps);
		myConfiguration = Configurable.createConfigurable(Configuration.class, configurationProps);
		LOG.info("Configuration -> formatString: '{}', useUpperCase: '{}'",
				myConfiguration.formatString(), myConfiguration.useUpperCase());
	}

	@Deactivate
	void onDeactivate() {
		LOG.info("Deactivating component.");
	}
}
