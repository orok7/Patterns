package patterns.bridge.shape;

public class Rectangle extends Shape {
    private final int height;
    private final int width;

    public Rectangle(Point position, int height, int width) {
        super(position);
        this.height = height;
        this.width = width;
    }

    @Override
    protected void draw() {
        System.out.println("Draw rectangle[ " + currentCenterPosition + " h:" + height + " w:" + width + ", col:" + borderColor + ", fill:" + fillColor + "]");
    }

    @Override
    protected void moveTo(Point newPosition) {
        System.out.println("Move rectangle from " + currentCenterPosition + " to " + newPosition);
        currentCenterPosition.changeTo(newPosition);
        draw();
    }
}
