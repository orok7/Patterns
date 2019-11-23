package com.eins.learn.patterns.bridge.shape;

import java.util.List;

public class Layer extends ShapeHolder {
    protected Layer(List<Shape> shapes) {
        super(shapes);
    }

    protected Layer(Shape shape) {
        super(shape);
    }

    public void drawShapeAt(int index) {
        if (shapes.size() > index) {
            shapes.get(index).draw();
        }
    }
}
