package model.interfaces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public interface Graphical extends Selectable, Focusable, Textual {
    Color DEFAULT_COLOR = Color.BLACK;
    Color DEFAULT_FILL = Color.WHITESMOKE;

    double CIRCLE_RADIUS = 10;
    double STROKE_WIDTH = CIRCLE_RADIUS / 3;

    StrokeType LINE_STROKE_TYPE = StrokeType.CENTERED;
    double LINE_OFFSET = 3;

    StrokeType CIRCLE_STROKE_TYPE = StrokeType.INSIDE;
    double CIRCLE_FILL_OPACITY = 0.8;

    Shape getShape();

    Shape[] getGraphics();
}
