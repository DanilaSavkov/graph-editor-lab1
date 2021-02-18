package handlers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ToolBarHandler {
    private static EventHandler<MouseEvent> getButtonMovingHandlerBy(Button button, Cursor cursor, double scale) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setScaleX(scale);
                button.setScaleY(scale);
                button.setCursor(cursor);
            }
        };
    }

    public static void setProperties(Button button, Cursor enteredCursor, double enterScale) {
        button.setOnMouseEntered(getButtonMovingHandlerBy(button, enteredCursor, enterScale));
        button.setOnMouseExited(getButtonMovingHandlerBy(button, Cursor.DEFAULT, 1));
    }
}
