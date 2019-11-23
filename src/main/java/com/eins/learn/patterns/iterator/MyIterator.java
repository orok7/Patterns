package com.eins.learn.patterns.iterator;

public interface MyIterator<T> {
    boolean hasNext();
    T next();
}