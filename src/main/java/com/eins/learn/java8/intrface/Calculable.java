package com.eins.learn.java8.intrface;

public interface Calculable {

    int getA();

    int getB();

    default int sum() {
        return getA() + getB();
    }

    default int mul() {
        return getA() * getB();
    }

    default int div() {
        return getA() / getB();
    }

    default int sub() {
        return getA() - getB();
    }

}