package model;

import handlers.VertexHandler;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class GraphicalVertex extends Vertex implements Subscribable {
    private static final double RADIUS = 10;
    private static final double TEXT_OFFSET = RADIUS + 1;
    private static final double CIRCLE_STROKE_WIDTH = RADIUS / 3;
    private static final StrokeType STROKE_TYPE = StrokeType.INSIDE;
    private static final Color DEFAULT = Color.BLACK;
    private static final Color SELECTED = Color.GREEN;
    private static final Color CIRCLE_FILL = Color.WHITESMOKE;
    private static final double OPACITY = 0.8;

    private boolean selected;
    private final Circle circle;
    private final Text text;

    public GraphicalVertex(double x, double y) {
        super(x, y);
        circle = new Circle(x, y, RADIUS);
        circle.setStroke(DEFAULT);
        circle.setStrokeWidth(CIRCLE_STROKE_WIDTH);
        circle.setStrokeType(STROKE_TYPE);
        circle.setFill(CIRCLE_FILL);
        circle.setOpacity(OPACITY);
        text = new Text(x + TEXT_OFFSET, y + TEXT_OFFSET, null);
        text.setFont(FONT);
        text.setFill(TEXT_FILL);
        selected = false;
        setVertexToolProperties();
    }

    public static double getRadius() {
        return RADIUS;
    }

    public boolean isSelected() {
        return selected;
    }

    public Circle getCircle() {
        return circle;
    }

    public Text getText() {
        return text;
    }

    @Override
    public void setIdentifier(String identifier) {
        super.setIdentifier(identifier);
        text.setText(identifier);
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        circle.setCenterX(x);
        text.setX(x + TEXT_OFFSET);
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        circle.setCenterY(y);
        text.setY(y + TEXT_OFFSET);
    }

    public void select() {
        selected = true;
        circle.setStroke(SELECTED);
    }

    public void unselect() {
        selected = false;
        circle.setStroke(DEFAULT);
    }

    private void setVertexToolProperties() {
        circle.addEventFilter(MouseEvent.MOUSE_CLICKED, VertexHandler.mouseClick(this));
        circle.addEventFilter(MouseEvent.MOUSE_DRAGGED, VertexHandler.vertexDragEvent(this));
    }
}