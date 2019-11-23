package com.eins.learn.patterns.bridge.shape;

public class DemoBridge {
    public static void main(String[] args) {
        Window window = new Window(new Circle(new Point(8, 8), 5));
        window.addShape(new Rectangle(new Point(18, 18), 10, 20));

        Layer layer = new Layer(new Circle(new Point(3, 3), 2));
        layer.addShape(new Rectangle(new Point(10, 10), 2, 5));

        window.drawAllShapes();

        layer.drawShapeAt(0);
        layer.drawShapeAt(1);
    }
}
