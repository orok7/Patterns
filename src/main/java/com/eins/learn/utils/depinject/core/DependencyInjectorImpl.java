package com.eins.learn.utils.depinject.core;

import com.eins.learn.utils.reflect.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.eins.learn.utils.depinject.DependencyInjector;
import com.eins.learn.utils.depinject.annotations.AutoInject;
import com.eins.learn.utils.depinject.annotations.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class DependencyInjectorImpl implements DependencyInjector {

    private Map<Class<?>, Object> componentsContainer;
    private List<TargetField> targetFields;
    private final String packageName;

    public DependencyInjectorImpl(String packageName) {
        this.packageName = packageName;
        componentsContainer = new HashMap<>();
        targetFields = new ArrayList<>();
        try {
            componentScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<T> type) {
        return (T) componentsContainer.get(type);
    }

    private void componentScan() throws Exception {
        Class<?>[] classes;
        try {
            classes = ClassUtils.getClasses(packageName);
        } catch (IOException e) {
            throw new ClassNotFoundException("ClassUtil Exception\n", e);
        }
        scanAutoInjectAnnotations(classes);
        scanComponentAnnotations(classes);
        injection();
    }

    private void scanAutoInjectAnnotations(Class<?>[] classes) {
        Stream.of(classes)
                .flatMap(clazz -> Arrays.stream(clazz.getDeclaredFields())
                        .filter(this::isAutoInject)
                        .map(field -> new PairClassField(clazz, field))
                ).forEach(this::addTargetField);
    }

    private void scanComponentAnnotations(Class<?>[] classes) {
        Stream.of(classes)
                .filter(this::isComponent)
                .forEach(this::putComponentToContainer);
    }

    private void injection() throws Exception {
        for (TargetField targetField : targetFields) {
            Object injected;
            Class<?> qualifier = targetField.getQualifier();
            Field field = targetField.getField();
            Class<?> mainClass = targetField.getMainClass();
            if (qualifier == void.class) {
                List<Class<?>> assignableClasses = findAssignableClasses(field.getType());
                if (assignableClasses.size() == 1) {
                    injected = componentsContainer.get(assignableClasses.get(0));
                } else {
                    log.error("Cannot inject to field {} because of present more than one injection.\n" +
                            "Use @AutoInject(qualifier = ...) to set which class must be injected.", field);
                    continue;
                }
            } else {
                injected = componentsContainer.get(qualifier);
                if (injected == null) {
                    log.error("Cannot inject to field {} because of no any injection present.", field);
                    continue;
                }
            }
            log.info("component: {} injected to: {}", injected.getClass(), field.getType());
            field.setAccessible(true);
            field.set(componentsContainer.get(mainClass), injected);
        }
    }

    private boolean isComponent(Class<?> clazz) {
        return clazz.isAnnotationPresent(Component.class);
    }

    private boolean isAutoInject(Field field) {
        return field.isAnnotationPresent(AutoInject.class);
    }

    private void addTargetField(PairClassField pair) {
        Class<?> qualifier = pair.getField().getAnnotation(AutoInject.class).qualifier();
        targetFields.add(new TargetField(pair.getClazz(), pair.getField(), qualifier));
    }

    private void putComponentToContainer(Class<?> clazz) {
        log.info("Component added: {}", clazz);
        Object instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Can't create instance\n{}", e);
        }
        componentsContainer.put(clazz, instance);
    }

    private List<Class<?>> findAssignableClasses(Class<?> clazz) {
        return componentsContainer.entrySet()
                .stream()
                .filter(map -> map.getValue() != null)
                .filter(map -> clazz.isAssignableFrom(map.getKey()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class TargetField {
        private Class<?> mainClass;
        private Field field;
        private Class<?> qualifier;
    }

    @Getter
    @AllArgsConstructor
    private class PairClassField {
        private final Class<?> clazz;
        private final Field field;
    }

}
