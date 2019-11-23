package com.eins.learn.java8.intrface;

public class CalcDemo {

    public static void main(String[] args) {
        Calculator c = new Calculator(8, 4);
        System.out.println("Unsafe div implementation (no errors)");
        invokeAllCalcMethods(c);

        System.out.println("\nSafe div implementation");
        SafeDivCalculator sc = new SafeDivCalculator(8, 0);
        invokeAllCalcMethods(sc);

        c = new Calculator(8, 0);
        System.out.println("\nUnsafe div implementation (with errors)");
        invokeAllCalcMethods(c);
    }

    private static void invokeAllCalcMethods(Calculable c) {
        System.out.println(c.getA() + " + " + c.getB() + " = " + c.sum());
        System.out.println(c.getA() + " - " + c.getB() + " = " + c.sub());
        System.out.println(c.getA() + " * " + c.getB() + " = " + c.mul());
        System.out.println(c.getA() + " / " + c.getB() + " = " + c.div());
    }

}
