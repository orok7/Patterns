package com.eins.learn.utils.depinject.exampl;

import com.eins.learn.utils.depinject.annotations.AutoInject;
import com.eins.learn.utils.depinject.annotations.Component;

@Component
public class SomeTestImpl2 implements SomeTest2, SomeTest {
	
	@AutoInject(qualifier = SomeTestImpl.class)
	private SomeTest someTest;
	
	public void doThis() {
		someTest.doThis();
		System.out.println("Hello from SomeTest2");
	}

}
