/*
 * CollectionUtils
 *
 * Version 1.0-SNAPSHOT
 *
 * 26.05.18
 *
 * Written by Orok.Eins
 * */

package com.eins.learn.utils.collections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtils {

    public static <T, R> List<R> toList(List<T> list, Function<T, R> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> List<R> toList(Stream<T> stream, Function<T, R> mapper) {
        return stream.map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Set<R> toSortedSet(Stream<T> stream, Function<T, R> mapper, Comparator<R> comparator) {
        return stream.map(mapper).collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    public static <R, K, V> Set<R> toSortedSet(Map<K, V> map, Function<? super Map.Entry<K, V>, R> mapper,
            Comparator<R> comparator) {
        return map.entrySet().stream().map(mapper).collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    public static <T, R> Set<R> toSortedSet(T[] array, Function<T, R> mapper, Comparator<R> comparator) {
        return toSortedSet(Arrays.stream(array), mapper, comparator);
    }

    public static <RK, RV, K, V> Map<RK, RV> toMap(Map<K, V> map, Function<K, RK> keyMapper,
            Function<V, RV> valueMapper) {
        Map<RK, RV> resultMap = new HashMap<>();
        map.forEach((k, v) -> resultMap.put(keyMapper.apply(k), valueMapper.apply(v)));
        return resultMap;
    }

    public static <RK, RV, T> Map<RK, RV> toMap(Collection<T> collection, Function<T, RK> keyMapper,
            Function<T, RV> valueMapper) {
        Map<RK, RV> resultMap = new HashMap<>();
        collection.forEach(o -> resultMap.put(keyMapper.apply(o), valueMapper.apply(o)));
        return resultMap;
    }

    public static <RK, RV, T> Map<RK, RV> toMap(Stream<T> stream, Function<T, RK> keyMapper,
            Function<T, RV> valueMapper) {
        Map<RK, RV> resultMap = new HashMap<>();
        stream.forEach(o -> resultMap.put(keyMapper.apply(o), valueMapper.apply(o)));
        return resultMap;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] addToArray(Class<T> type, T[] src, T... newElements) {
        if (newElements == null || newElements.length == 0) {
            return src;
        }
        int oldLength = 0;
        int newLength = src != null ? src.length + newElements.length : newElements.length;
        T[] newArray = (T[]) Array.newInstance(type, newLength);
        if (src != null && src.length != 0) {
            System.arraycopy(src, 0, newArray, 0, src.length);
            oldLength = src.length;
        }
        System.arraycopy(newElements, 0, newArray, oldLength, newElements.length);
        return newArray;
    }

}
