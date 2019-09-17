package solid.liskovsubstitution;

import java.util.ArrayList;
import java.util.List;

public class WrongExample {

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            if (i % 2 == 0) {
                shapes.add(new Square(anyDouble(), anyDouble(), anyDouble()));
            } else {
                shapes.add(new Sphere(anyDouble(), anyDouble(), anyDouble()));
            }
        }

        System.out.println("Perimeters:");
        for (Shape shape : shapes) {
            System.out.println(shape.getPerimeter());
        }
        System.out.println("Volumes:");
        for (Shape shape : shapes) {
            try {
                System.out.println(shape.getVolume());
            } catch (UnsupportedOperationException e) {
                System.out.println("\t-\tUnsupported operation");
            }
        }
    }

    private static double anyDouble() {
        return Math.random()*10;
    }

    abstract static class Shape {
        double x;
        double y;

        public Shape(double x, double y) {
            this.x = x;
            this.y = y;
        }

        abstract double getPerimeter();
        abstract double getVolume();
    }

    static class Sphere extends Shape {
        double r;

        public Sphere(double x, double y, double r) {
            super(x, y);
            this.r = r;
        }

        @Override
        double getPerimeter() {
            return 2*Math.PI*r;
        }

        @Override
        double getVolume() {
            return (4/3)*Math.PI*r*r*r;
        }
    }

    static class Square extends Shape {
        double side;

        public Square(double x, double y, double side) {
            super(x, y);
            this.side = side;
        }

        @Override
        double getPerimeter() {
            return side*4;
        }

        @Override
        double getVolume() {
            throw new UnsupportedOperationException();
        }

    }
}