package com.eins.learn.collections.table;

import java.util.List;
import java.util.Map;

public class TableBuilder<R, C, V> {
    private List<R> rows;
    private List<C> cols;
    private TableDataMap<R, C, V> dataMap;
    private KeyBuilder<R, C> keyBuilder;

    public TableBuilder(List<R> rows, List<C> cols, TableDataMap<R, C, V> dataMap,
            KeyBuilder<R, C> keyBuilder) {
        this.rows = rows;
        this.cols = cols;
        this.dataMap = dataMap;
        this.keyBuilder = keyBuilder;
    }

    public Table<R, C, V> getTable() {
        Table<R, C, V> table = new Table<>();
        List<Line<R, C, V>> tableRows = table.getRows();
        List<Line<R, C, V>> tableCols = table.getCols();
        buildRows(tableRows, dataMap.getDataMap());
        buildCols(tableRows, tableCols);
        return table;
    }

    private void buildRows(List<Line<R, C, V>> tableRows, Map<Key<R, C>, V> data) {
        int rowIndex = 0;
        for (R row : rows) {
            int colIndexTmp = 0;
            Line<R, C, V> rowLine = new Line<>();
            List<Cell<R, C, V>> dataLine = rowLine.getDataLine();
            rowLine.setTitle(keyBuilder.getRowTitle(row));
            rowLine.setIndex(rowIndex);
            for (C col : cols) {
                Key<R, C> key = keyBuilder.getKey(row, col);
                Cell<R, C, V> cell = new Cell<>(key, rowIndex, colIndexTmp++, data.get(key));
                dataLine.add(cell);
            }
            tableRows.add(rowLine);
            rowIndex++;
        }
    }

    private void buildCols(List<Line<R, C, V>> tableRows, List<Line<R, C, V>> tableCols) {
        int colIndex = 0;
        for (C col : cols) {
            int rowIndexTmp = 0;
            Line<R, C, V> colLine = new Line<>();
            List<Cell<R, C, V>> dataLine = colLine.getDataLine();
            colLine.setTitle(keyBuilder.getColTitle(col));
            colLine.setIndex(colIndex);
            for (R row : rows) {
                Key<R, C> key = keyBuilder.getKey(row, col);
                Cell<R, C, V> cell = null;
                Line<R, C, V> rowByIndex = Line.findByIndex(tableRows, rowIndexTmp);
                if (rowByIndex != null) {
                    cell = Cell.findByIndexes(rowByIndex.getDataLine(), rowIndexTmp, colIndex);
                }
                dataLine.add(cell);
                rowIndexTmp++;
            }
            tableCols.add(colLine);
            colIndex++;
        }
    }
}
