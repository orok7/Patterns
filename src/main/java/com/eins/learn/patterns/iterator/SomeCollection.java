package com.eins.learn.patterns.iterator;

import java.util.ArrayList;
import java.util.List;

public class SomeCollection<T> implements MyIterable<T> {

    private List<T> data = new ArrayList<>();

    public void add(T obj) {
        data.add(obj);
    }

    public boolean remove(T obj) {
        return data.remove(obj);
    }

    @Override
    public MyIterator<T> getIterator() {
        return new SomeIterator();
    }

    private class SomeIterator implements MyIterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < data.size();
        }

        @Override
        public T next() {
            return data.get(index++);
        }
    }

}

