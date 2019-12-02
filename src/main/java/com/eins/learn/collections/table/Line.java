package com.eins.learn.collections.table;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Line<R, C, V> {
    private List<Cell<R, C, V>> dataLine = new ArrayList<>();
    private int index;
    private String title;

    public static <R, C, V> Line<R, C, V> findByIndex(List<Line<R, C, V>> lines, int index) {
        return lines.stream().filter(l -> l.getIndex() == index).findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    public Class<V> getValueType() {
        if (dataLine.isEmpty() || dataLine.get(0) == null) {
            return null;
        }
        return dataLine.get(0).getValueType();
    }

    public boolean containsRow(R row) {
        return dataLine.stream().anyMatch(cell -> cell.containsRow(row));
    }

    public boolean containsCol(C col) {
        return dataLine.stream().anyMatch(cell -> cell.containsCol(col));
    }
}
