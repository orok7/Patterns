package patterns.lazyinit;

public class DemoLazyChart {
    public static void main(String[] args) {
        // ToDo finish implementation
        LazyChart chart = new LazyChart(1, aDouble -> aDouble * aDouble, aDouble -> aDouble+=0.5);
        System.out.println(chart.getNextValues(10));
        System.out.println(chart.getPreviousValues(5));
        System.out.println(chart.getNextValues(10));
        System.out.println(chart.getPreviousValues(8));
    }
}
