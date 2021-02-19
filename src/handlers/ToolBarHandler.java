package handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

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

    public static EventHandler<ActionEvent> setButtonCursorHandler(Pane pane, Cursor cursor) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.setCursor(cursor);
            }
        };
    }

    public static void whenButtonIsOnAction(Pane pane) {
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double x = mouseEvent.getX();
                double y = mouseEvent.getY();
                double radius = 10;
//                if (check(x, y, radius, pane.getChildren()))
                    pane.getChildren().add(new Circle(x, y, radius));
            }
        };
        pane.setOnMouseClicked(event);
    }

    private static boolean check(double pointX, double pointY, double radius, Circle... circles) {
        boolean result = true;
        for (Circle circle : circles) {
            if (Math.pow(pointX - circle.getCenterX(), 2) + Math.pow(pointY - circle.getCenterY(), 2) > 4 * Math.pow(radius, 2)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
