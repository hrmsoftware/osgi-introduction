package se.hrmsoftware.hello.internal;

import se.hrmsoftware.hello.service.HelloService;

public class AnotherHelloService implements HelloService {
	@Override
	public void sayHello(String caller) {
		System.out.println("Hejsan svejsan, "+caller+"!");
	}
}
