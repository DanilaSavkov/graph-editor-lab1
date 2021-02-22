package model;

import handlers.VertexHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GraphicalVertex extends Vertex {
    private static final double RADIUS = 10;
    private final Circle circle;
    private String id;

    public GraphicalVertex(double x, double y) {
        super(x, y);
        this.circle = new Circle(x, y, RADIUS);
        this.circle.setFill(Color.STEELBLUE);
    }

    public static double getRadius() {
        return RADIUS;
    }

    public Circle getCircle() {
        return circle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        circle.setCenterX(x);
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        circle.setCenterY(y);
    }
}
