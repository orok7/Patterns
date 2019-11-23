package com.eins.learn.patterns.iterator;

public interface MyIterable<T> {
    MyIterator<T> getIterator();
}
