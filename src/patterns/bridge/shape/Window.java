package patterns.bridge.shape;

import java.util.List;

public class Window extends ShapeHolder {
    protected Window(List<Shape> shapes) {
        super(shapes);
    }

    public Window(Shape shape) {
        super(shape);
    }

    public void drawAllShapes() {
        shapes.forEach(Shape::draw);
    }
}
