package handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.GraphicalVertex;
import view.components.MyPane;

public class ButtonHandler {
    private Button button;

    public ButtonHandler(Button button) {
        this.button = button;
    }

    // наведение мыши на кнопку
    public EventHandler<MouseEvent> mouseEnteredEvent(Cursor cursor, double scale) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setScaleX(scale);
                button.setScaleY(scale);
                button.setCursor(cursor);
            }
        };
    }
}
