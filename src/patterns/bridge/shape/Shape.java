package patterns.bridge.shape;

public abstract class Shape {
    protected Point currentCenterPosition;
    protected String borderColor = "black";
    protected String fillColor = "transparent";

    protected Shape(Point position) {
        this.currentCenterPosition = position;
    }

    protected abstract void draw();

    protected abstract void moveTo(Point point);
}