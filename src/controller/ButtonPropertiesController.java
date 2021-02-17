package controller;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonPropertiesController {
    private static void setEnterProperties(Button button, Cursor cursor, double scale) {
        EventHandler<MouseEvent> mouseEnterHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println("Вы навели курсор на кнопку.");
                button.setScaleX(scale);
                button.setScaleY(scale);
                button.setCursor(cursor);
            }
        };
        button.setOnMouseEntered(mouseEnterHandler);
    }

    private static void setExitedProperties(Button button, double scale) {
        EventHandler<MouseEvent> mouseExitedHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println("Вы убрали курсор с кнопки.");
                button.setScaleX(scale);
                button.setScaleY(scale);
            }
        };
        button.setOnMouseExited(mouseExitedHandler);
    }

    public static void setProperties(Button button, Cursor cursor, double enterScale, double exitedScale) {
        setEnterProperties(button, cursor, enterScale);
        setExitedProperties(button, exitedScale);
    }
}
