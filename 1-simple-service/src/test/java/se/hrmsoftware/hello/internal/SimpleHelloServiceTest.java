package se.hrmsoftware.hello.internal;

import org.junit.Assert;
import org.junit.Test;
import se.hrmsoftware.hello.service.HelloService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SimpleHelloServiceTest {
	@Test
	public void testGreeting() {
		String caller = "caller";
		String expected = String.format(SimpleHelloService.TEMPLATE, caller);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream stream = new PrintStream(baos);
		HelloService service = new SimpleHelloService() {
			@Override
			protected PrintStream getOut() {
				return stream;
			}
		};
		service.sayHello(caller);

		// Assert.
		String rendered = baos.toString();
		Assert.assertEquals(expected, rendered);
	}
}
