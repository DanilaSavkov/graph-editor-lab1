package controller;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonPropertiesController {
    private Button button;

    public ButtonPropertiesController(Button button) {
        this.button = button;
    }

    private void setEnterProperties(double scale, Cursor cursor) {
        EventHandler<MouseEvent> mouseEnterHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Вы навели курсор на кнопку.");
                button.setScaleX(scale);
                button.setScaleY(scale);
                button.setCursor(cursor);
            }
        };
        button.setOnMouseEntered(mouseEnterHandler);
    }

    private void setExitedProperties(double scale) {
        EventHandler<MouseEvent> mouseExitedHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Вы убрали курсор с кнопки.");
                button.setScaleX(scale);
                button.setScaleY(scale);
            }
        };
        button.setOnMouseExited(mouseExitedHandler);
    }

    public void setProperties(double enterScale, double exitedScale, Cursor cursor) {
        setEnterProperties(enterScale, cursor);
        setExitedProperties(exitedScale);
    }

}
