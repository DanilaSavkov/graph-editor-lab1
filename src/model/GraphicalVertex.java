package model;

import handlers.VertexHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class GraphicalVertex extends Vertex {
    private static final double RADIUS = 10;
    private boolean selected;
    private final Circle circle;
    private Label label;

    public GraphicalVertex(double x, double y) {
        super(x, y);
        circle = new Circle(x, y, RADIUS);
        circle.setFill(Color.WHITESMOKE);
        circle.setOpacity(0.8);
        setUnselected();
        circle.addEventFilter(MouseEvent.MOUSE_CLICKED, VertexHandler.vertexClickEvent(this));
        circle.addEventFilter(MouseEvent.MOUSE_DRAGGED, VertexHandler.moveVertex(this));
    }

    public static double getRadius() {
        return RADIUS;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setSelected() {
        selected = true;
        setCircleStrokeColor(Color.GREEN);
    }

    public void setUnselected() {
        selected = false;
        setCircleStrokeColor(Color.BLACK);
    }

    private void setCircleStrokeColor(Color color) {
        circle.setStroke(color);
        circle.setStrokeWidth(RADIUS/3);
        circle.setStrokeType(StrokeType.INSIDE);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
        this.label = new Label(id);
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
