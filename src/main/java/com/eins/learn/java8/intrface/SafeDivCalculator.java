package com.eins.learn.java8.intrface;

public class SafeDivCalculator implements SafeDivCalculable {

    private int a;

    private int b;

    public SafeDivCalculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int getA() {
        return a;
    }

    @Override
    public int getB() {
        return b;
    }

}
