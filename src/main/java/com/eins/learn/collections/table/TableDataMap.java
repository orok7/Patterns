package com.eins.learn.collections.table;

import java.util.HashMap;
import java.util.Map;

public class TableDataMap<R, C, V> {
    private Map<R, Map<C, V>> map = new HashMap<>();
    private KeyBuilder<R, C> keyBuilder;

    private TableDataMap() {
    }

    public TableDataMap(KeyBuilder<R, C> keyBuilder) {
        this.keyBuilder = keyBuilder;
    }

    public void put(R r, C c, V v) {
        Map<C, V> cvMap = map.get(r);
        if (cvMap == null) {
            cvMap = new HashMap<>();
        }
        cvMap.put(c, v);
        map.put(r, cvMap);
    }

    public V get(R r, C c) {
        Map<C, V> cvMap = map.get(r);
        if (cvMap == null) {
            return null;
        }
        return cvMap.get(c);
    }

    public Map<Key<R, C>, V> getDataMap() {
        if (keyBuilder == null) {
            return null;
        }
        Map<Key<R, C>, V> dataMap = new HashMap<>();
        for (Map.Entry<R, Map<C, V>> rEntry : map.entrySet()) {
            for (Map.Entry<C, V> cEntry : rEntry.getValue().entrySet()) {
                dataMap.put(keyBuilder.getKey(rEntry.getKey(), cEntry.getKey()), cEntry.getValue());
            }
        }
        return dataMap;
    }
}
