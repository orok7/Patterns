package com.eins.learn.utils.depinject;

public interface DependencyInjector {

	<T> T getComponent(Class<T> type);

}
