package com.eins.learn.utils.depinject.exampl;

import com.eins.learn.utils.depinject.DependencyInjector;
import com.eins.learn.utils.depinject.annotations.AutoInject;
import com.eins.learn.utils.depinject.annotations.Component;
import com.eins.learn.utils.depinject.core.DependencyInjectorImpl;

import java.util.function.Consumer;

@Component
public class Demo {

	private final static Consumer<?> sout = System.out::println;

	@AutoInject
	private SomeTest2 someTest2;

	public static void main(String[] args) {
		DependencyInjector depinject = new DependencyInjectorImpl("com.eins.learn.utils.depinject");
		Demo demo = depinject.getComponent(Demo.class);
		demo.someTest2.doThis();
	}

}
