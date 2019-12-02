package com.eins.learn.collections.table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public final class Key<R, C> {
    private final R row;
    private final C col;

    public boolean containsRow(R row) {
        return this.row != null && this.row.equals(row);
    }

    public boolean containsCol(C col) {
        return this.col != null && this.col.equals(col);
    }
}
