package patterns.bridge.shape;

public class Circle extends Shape {
    private final int radius;

    public Circle(Point position, int radius) {
        super(position);
        this.radius = radius;
    }

    @Override
    protected void draw() {
        System.out.println("Draw circle[ " + currentCenterPosition + " R:" + radius + ", col:" + borderColor + ", fill:" + fillColor + "]");
    }

    @Override
    protected void moveTo(Point newPosition) {
        System.out.println("Move circle from " + currentCenterPosition + " to " + newPosition);
        currentCenterPosition.changeTo(newPosition);
        draw();
    }
}
