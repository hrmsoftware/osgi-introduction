package se.hrmsoftware.hello.internal;

import se.hrmsoftware.hello.service.HelloService;

public class ConfigurableHelloService implements HelloService {
	public static final String DEFAULT_FORMAT = "Hello, %s, from default configuration";
	private final String formatString;

	public ConfigurableHelloService(String formatString) {
		if (formatString != null) {
			this.formatString = formatString;
		} else {
			this.formatString = DEFAULT_FORMAT;
		}
	}


	@Override
	public void sayHello(String caller) {
		System.out.println(String.format(formatString, caller));
	}
}
