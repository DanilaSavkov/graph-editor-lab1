package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public interface Graphical extends Selectable, Focusable, Textual {
    Color DEFAULT_COLOR = Color.BLACK;

    double CIRCLE_RADIUS = 10;
    double CIRCLE_STROKE_WIDTH = CIRCLE_RADIUS / 3;
    StrokeType CIRCLE_STROKE_TYPE = StrokeType.INSIDE;
    Color CIRCLE_FILL = Color.WHITESMOKE;
    double CIRCLE_FILL_OPACITY = 0.8;

    double LINE_STROKE_WIDTH = 3;
    StrokeType LINE_STROKE_TYPE = StrokeType.CENTERED;

    Shape getShape();

    Shape[] getGraphics();
}
