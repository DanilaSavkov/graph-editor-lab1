package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public interface Graphical extends Selectable, Focusable, Textual {
    Color DEFAULT_COLOR = Color.BLACK;

    Shape[] getGraphics();
}
