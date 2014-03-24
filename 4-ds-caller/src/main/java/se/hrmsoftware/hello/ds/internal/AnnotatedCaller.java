package se.hrmsoftware.hello.ds.internal;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;
import aQute.bnd.annotation.component.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.hrmsoftware.hello.service.HelloService;

import java.util.Arrays;
import java.util.Map;

@Component()
public class AnnotatedCaller {

	private static final Logger LOG = LoggerFactory.getLogger(AnnotatedCaller.class);

	@Reference(multiple = true, dynamic = true, unbind = "removeService")
	void addService(HelloService service, Map<String, Object> props) {
		logProperties(props);
		service.sayHello("ANNOTATED_CALLER");
	}

	void removeService(HelloService service) {
		LOG.info("Hello Service removed");
	}
	@Activate
	void activated() {
		LOG.info("Activated component");
	}
	@Deactivate
	void deactivate() {
		LOG.info("Deactivated component");
	}

	private void logProperties(Map<String, Object> props) {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, Object> entry : props.entrySet()) {
			if (builder.length() > 0) { builder.append("\n"); }
			builder.append("\t|").append(entry.getKey()).append(" : ");
			if (entry.getValue() instanceof Object[]) {
				builder.append(Arrays.toString((Object[]) entry.getValue()));
			} else {
				builder.append(entry.getValue());
			}
		}
		LOG.info("Calling Service with properties: \n{}", builder);
	}
}
