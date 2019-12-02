package com.eins.learn.collections.table;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class Table<R, C, V> {
    private List<Line<R, C, V>> rows = new ArrayList<>();
    private List<Line<R, C, V>> cols = new ArrayList<>();

    Table() {
    }

    public Cell<R, C, V> getCell(int rowIndex, int colIndex) {
        Line<R, C, V> rowByIndex = Line.findByIndex(rows, rowIndex);
        if (rowByIndex != null) {
            return Cell.findByIndexes(rowByIndex.getDataLine(), rowIndex, colIndex);
        }
        return null;
    }

    public Cell<R, C, V> getCell(Key<R, C> key) {
        Cell<R, C, V> cell = null;
        for (Line<R, C, V> row : rows) {
            cell = row.getDataLine().stream().filter(o -> o.getKey().equals(key)).findFirst()
                    .orElse(null);
            if (cell != null) {
                return cell;
            }
        }
        return null;
    }

    public Line<R, C, V> getLineByRow(R row) {
        return rows.stream().filter(line -> line.containsRow(row)).findFirst().orElse(null);
    }

    public Line<R, C, V> getLineByCol(C col) {
        return cols.stream().filter(line -> line.containsCol(col)).findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    public Class<V> getValueType() {
        if (rows.isEmpty() || rows.get(0) == null) {
            return null;
        }
        return rows.get(0).getValueType();
    }
}
