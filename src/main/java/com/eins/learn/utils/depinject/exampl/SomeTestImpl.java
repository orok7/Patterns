package com.eins.learn.utils.depinject.exampl;

import com.eins.learn.utils.depinject.annotations.AutoInject;
import com.eins.learn.utils.depinject.annotations.Component;

@Component
public class SomeTestImpl implements SomeTest{
	
	@AutoInject(qualifier = SomeTestImpl2.class)
	private SomeTest2 someTest2;
		
	public void doThis() {
		System.out.println("Hello from SomeTest");
	}

}
