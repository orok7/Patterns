package com.eins.learn.collections.table;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Cell<R, C, V> {
    private Key<R, C> key;
    private int rowIndex;
    private int colIndex;
    private V value;

    public static <R, C, V> Cell<R, C, V> findByIndexes(List<Cell<R, C, V>> cells,int rowIndex, int colIndex) {
        return cells.stream().filter(c -> c.colIndex == colIndex && c.rowIndex == rowIndex).findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    public Class<V> getValueType() {
        return value != null ? (Class<V>) value.getClass() : null;
    }

    public boolean containsRow(R row) {
        return key != null && key.containsRow(row);
    }

    public boolean containsCol(C col) {
        return key != null && key.containsCol(col);
    }
}
