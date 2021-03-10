package model;

import javafx.scene.paint.Color;

public interface Selectable {
    Color SELECTED_COLOR = Color.GREEN;

    boolean isSelected();

    void setSelected();

    void setUnselected();
}
