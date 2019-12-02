package com.eins.learn.utils.depinject.core;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Slf4j
public class ClassUtils {
	private static final String CLASS_EXTENTION = ".class";
	private static final int ZERO = 0;

	public static List<String> getNames(String packageName) throws IOException, ClassNotFoundException {
		List<String> list = new ArrayList<>();
		Class<?>[] classes = getClasses(packageName);
		for (Class<?> clazz : classes) {
			list.add(clazz.getSimpleName());
		}
		return list;
	}

	public static Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class<?>> classes = new ArrayList<>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	private static List<Class<?>> findClasses(File directory, String packageName) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(CLASS_EXTENTION)) {
                String className = null;
				try {
                    int endIndex = file.getName().length() - CLASS_EXTENTION.length();
                    String substring = file.getName().substring(ZERO, endIndex);
                    className = packageName + '.' + substring;
                    Class<?> clazz = Class.forName(className);
                    classes.add(clazz);
				} catch (Throwable e) {
                    log.error("Trouble with class {}. Message: {}", className, e);
				}
			}
		}
		return classes;
	}
}
