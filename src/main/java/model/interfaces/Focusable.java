package model.interfaces;

import javafx.scene.paint.Color;

public interface Focusable {
    Color FOCUSED_COLOR = Color.ORANGE;

    boolean isFocused();

    void setFocused();

    void setUnfocused();
}
