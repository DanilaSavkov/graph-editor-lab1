package model.interfaces;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public interface Graphical extends Selectable, Focusable, Textual {
    Color DEFAULT_COLOR = Color.BLACK;
    Color DEFAULT_FILL = Color.TRANSPARENT;
    double CIRCLE_RADIUS = 20;
    double STROKE_WIDTH = CIRCLE_RADIUS / 3;
    StrokeType LINE_STROKE_TYPE = StrokeType.CENTERED;
    double LINE_OFFSET = STROKE_WIDTH;
    StrokeType CIRCLE_STROKE_TYPE = StrokeType.INSIDE;
    double CIRCLE_FILL_OPACITY = 0.8;

    Shape getShape();

    Shape[] getGraphics();

    default EventHandler<MouseEvent> mouseEnteredHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!isSelected()) {
                    setFocused();
                }
            }
        };
    }

    default EventHandler<MouseEvent> mouseExitedHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setUnfocused();
                if (isSelected()) {
                    setSelected();
                }
            }
        };
    }

    default EventHandler<MouseEvent> mouseClickedHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    setSelected();
                }
            }
        };
    }
}
