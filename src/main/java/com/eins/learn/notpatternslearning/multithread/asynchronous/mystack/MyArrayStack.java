package com.eins.learn.notpatternslearning.multithread.asynchronous.mystack;

import java.lang.reflect.Array;

public class MyArrayStack<T> implements MyStack<T> {

    private T[] content;
    private int size = 0;
    private final int capacity;
    private int currentTop = -1;

    @SuppressWarnings("unchecked")
    public MyArrayStack(int capacity) {
        this.capacity = capacity;
        content = (T[]) new Object[capacity];//Array.newInstance(Object.class, capacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(T t) {
        if (currentTop == capacity - 1) {
            throw new StackOverflowError("");
        }
        content[++currentTop] = t;
    }

    @Override
    public T pop() {
        if (currentTop < 0) {
            return null;
        }
        return content[currentTop--];
    }
}
