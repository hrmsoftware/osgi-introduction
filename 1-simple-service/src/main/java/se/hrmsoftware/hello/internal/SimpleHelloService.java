package se.hrmsoftware.hello.internal;

import se.hrmsoftware.hello.service.HelloService;

import java.io.PrintStream;

import static java.lang.String.format;

/**
 * Really simple service that just prints out a greeting to
 * stdout.
 */
public class SimpleHelloService implements HelloService {
	static final String TEMPLATE = "Hello there, %s!\n";

	protected PrintStream getOut() {
		return System.out;
	}

	@Override
	public void sayHello(String caller) {
		getOut().print(format(TEMPLATE, caller));
	}
}
