package com.eins.learn.collections.table;

public interface KeyBuilder<R, C> {
    Key<R, C> getKey(R row, C col);

    String getRowTitle(R row);

    String getColTitle(C col);
}