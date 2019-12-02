package com.eins.learn.collections.table;

import java.util.ArrayList;
import java.util.List;

public class TableDemo {

    private static final String HOMER               = "Homer    ";
    private static final String MARGE               = "Marge    ";
    private static final String BART                = "Bart     ";
    private static final String LISA                = "Lisa     ";
    private static final String MAGGIE              = "Maggie   ";
    private static final String NONE                = "*********";
    private static final String WIFE                = "wife     ";
    private static final String SON                 = "son      ";
    private static final String OLDER_DAUGHTER      = "odaughter";
    private static final String YOUNGER_DAUGHTER    = "ydaughter";
    private static final String HUSBAND             = "husband  ";
    private static final String DADDY               = "daddy    ";
    private static final String MAMIE               = "mamie    ";
    private static final String OLDER_SISTER        = "osister  ";
    private static final String YOUNGER_SISTER      = "ysister  ";
    private static final String BROTHER             = "brother  ";
    private static final String SISTER              = "sister   ";

    public static void main(String[] args) {
        List<String> rows = getRows();

        List<String> cols = getCols();

        KeyBuilder<String, String> keyBuilder = getKeyBuilder();

        TableDataMap<String, String, String> dataMap = getTableDataMap(keyBuilder);

        TableBuilder<String, String, String> tableBuilder = new TableBuilder<>(rows, cols, dataMap, keyBuilder);

        Table<String, String, String> table = tableBuilder.getTable();

        List<Line<String, String, String>> tableCols = table.getCols();
        tableCols.forEach(c -> System.out.print("\t\t\t|\t" + c.getTitle()));
        System.out.println("\t\t\t|\t");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

        List<Line<String, String, String>> tableRows = table.getRows();

        tableRows.forEach(r -> {
            System.out.print(r.getTitle() + "\t|\t");
            r.getDataLine().forEach(dl -> System.out.print(dl.getValue() + "\t\t\t|\t"));
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        });

        System.out.println();
        System.out.println();
        System.out.println();

        Cell<String, String, String> cell = table.getCell(keyBuilder.getKey(HOMER, MARGE));
        System.out.println("Marge is Homer's " + cell.getValue());
        cell = table.getCell(keyBuilder.getKey(MARGE, HOMER));
        System.out.println("Homer is Marge's " + cell.getValue());


    }

    private static KeyBuilder<String, String> getKeyBuilder() {
        return new KeyBuilder<String, String>() {
                @Override
                public Key<String, String> getKey(String row, String col) {
                    return new Key<>(row, col);
                }

                @Override
                public String getRowTitle(String row) {
                    return row;
                }

                @Override
                public String getColTitle(String col) {
                    return col;
                }
            };
    }

    private static TableDataMap<String, String, String> getTableDataMap(KeyBuilder<String, String> keyBuilder) {
        TableDataMap<String, String, String> dataMap = new TableDataMap<>(keyBuilder);
        dataMap.put(HOMER, HOMER, NONE);
        dataMap.put(HOMER, MARGE, WIFE);
        dataMap.put(HOMER, BART, SON);
        dataMap.put(HOMER, LISA, OLDER_DAUGHTER);
        dataMap.put(HOMER, MAGGIE, YOUNGER_DAUGHTER);

        dataMap.put(MARGE, HOMER, HUSBAND);
        dataMap.put(MARGE, MARGE, NONE);
        dataMap.put(MARGE, BART, SON);
        dataMap.put(MARGE, LISA, OLDER_DAUGHTER);
        dataMap.put(MARGE, MAGGIE, YOUNGER_DAUGHTER);

        dataMap.put(BART, HOMER, DADDY);
        dataMap.put(BART, MARGE, MAMIE);
        dataMap.put(BART, BART, NONE);
        dataMap.put(BART, LISA, OLDER_SISTER);
        dataMap.put(BART, MAGGIE, YOUNGER_SISTER);

        dataMap.put(LISA, HOMER, DADDY);
        dataMap.put(LISA, MARGE, MAMIE);
        dataMap.put(LISA, BART, BROTHER);
        dataMap.put(LISA, LISA, NONE);
        dataMap.put(LISA, MAGGIE, SISTER);

        dataMap.put(MAGGIE, HOMER, DADDY);
        dataMap.put(MAGGIE, MARGE, MAMIE);
        dataMap.put(MAGGIE, BART, BROTHER);
        dataMap.put(MAGGIE, LISA, SISTER);
        dataMap.put(MAGGIE, MAGGIE, NONE);
        return dataMap;
    }

    private static List<String> getCols() {
        List<String> cols = new ArrayList<>();
        cols.add(HOMER);
        cols.add(MARGE);
        cols.add(BART);
        cols.add(LISA);
        cols.add(MAGGIE);
        return cols;
    }

    private static List<String> getRows() {
        List<String> rows = new ArrayList<>();
        rows.add(HOMER);
        rows.add(MARGE);
        rows.add(BART);
        rows.add(LISA);
        rows.add(MAGGIE);
        return rows;
    }

}
