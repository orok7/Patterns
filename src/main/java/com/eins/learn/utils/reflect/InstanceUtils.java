package com.eins.learn.utils.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;

public class InstanceUtils {

    public static <T> T newInstance(Class<T> type) {
        try {
            Constructor<T> constructor = getMinArgsConstructor(type);
            if (constructor != null) {
                constructor.setAccessible(true);
                if (constructor.getParameterCount() == 0) {
                    return constructor.newInstance();
                }
                return constructor.newInstance(Arrays.stream(constructor.getParameterTypes())
                        .map(InstanceUtils::getObjectDefaultValue)
                        .toArray());
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static Object getObjectDefaultValue(Class<?> parameter) {
        Object parameterInstance = null;
        if (parameter.isPrimitive()) {
            switch (parameter.getSimpleName()) {
            case "char":
            case "byte":
            case "short":
            case "int":
            case "long":
            case "float":
            case "double":
                parameterInstance = 0;
                break;
            case "boolean":
                parameterInstance = false;
            }
        }
        return parameterInstance;
    }

    @SuppressWarnings("unchecked")
    private static <T> Constructor<T> getMinArgsConstructor(Class<T> type) {
        Constructor<T>[] declaredConstructors = (Constructor<T>[]) type.getDeclaredConstructors();
        return Arrays.stream(declaredConstructors)
                .min(Comparator.comparingInt(Constructor::getParameterCount)).orElse(null);
    }
}
