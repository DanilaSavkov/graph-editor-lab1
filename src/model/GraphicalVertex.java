package model;

import handlers.VertexHandler;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GraphicalVertex extends Vertex {
    private static final double RADIUS = 10;
    private boolean selected;
    private final Circle circle;
    private final Text text;

    public GraphicalVertex(double x, double y) {
        super(x, y);
        circle = new Circle(x, y, RADIUS);
        circle.setFill(Color.WHITESMOKE);
        circle.setOpacity(0.8);
        circle.addEventFilter(MouseEvent.MOUSE_CLICKED, VertexHandler.mouseClick(this));
        circle.addEventFilter(MouseEvent.MOUSE_DRAGGED, VertexHandler.vertexDragEvent(this));
        unselect();
        text = new Text(x + RADIUS + 1, y + RADIUS + 1, null);
        textProperties();
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
    public void setId(String id) {
        super.setId(id);
        text.setText(id);
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        circle.setCenterX(x);
        text.setX(x + RADIUS + 1);
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        circle.setCenterY(y);
        text.setY(y + RADIUS + 1);
    }

    public void select() {
        selected = true;
        circleStrokeProperties(Color.GREEN);
    }

    public void unselect() {
        selected = false;
        circleStrokeProperties(Color.BLACK);
    }

    private void circleStrokeProperties(Color color) {
        circle.setStroke(color);
        circle.setStrokeWidth(RADIUS / 3);
        circle.setStrokeType(StrokeType.INSIDE);
    }

    private void textProperties() {
        String fontFamily = "Arial";
        double fontSize = 13;
        FontWeight fontWeight = FontWeight.BOLD;
        FontPosture fontPosture = FontPosture.ITALIC;
        text.setFont(Font.font(fontFamily, fontWeight , fontPosture, fontSize));
        text.setFill(Color.BLUE);
    }
}
