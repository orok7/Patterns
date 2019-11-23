package com.eins.learn.notpatternslearning.multithread.asynchronous.mystack;

public interface MyStack<T> {

    int size();

    void push(T t);

    T pop();

}
