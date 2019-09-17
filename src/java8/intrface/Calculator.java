package java8.intrface;

public class Calculator implements Calculable {

    private int a;

    private int b;

    public Calculator(int a, int b) {
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
