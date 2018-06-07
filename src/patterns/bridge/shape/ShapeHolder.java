package patterns.bridge.shape;

import java.util.ArrayList;
import java.util.List;

public abstract class ShapeHolder {
    protected List<Shape> shapes;

    protected ShapeHolder(List<Shape> shapes) {
        this.shapes = new ArrayList<>();
        this.shapes.addAll(shapes);
    }

    protected ShapeHolder(Shape shape) {
        this.shapes = new ArrayList<>();
        this.shapes.add(shape);
    }

    protected void addShape(Shape shape) {
        shapes.add(shape);
    }

    protected void removeShape(Shape shape) {
        shapes.remove(shape);
    }

}
