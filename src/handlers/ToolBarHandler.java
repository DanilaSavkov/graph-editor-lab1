package handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import view.components.MyPane;

public class ToolBarHandler {
    public static EventHandler<MouseEvent> getMouseEventHandler(Button button, Cursor cursor, double scale) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setScaleX(scale);
                button.setScaleY(scale);
                button.setCursor(cursor);
            }
        };
    }

    public static EventHandler<ActionEvent> getOnActionHandler(MyPane pane, Cursor cursor, EventHandler<MouseEvent> paneClick) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.setCursor(cursor);
                pane.setOnMouseClicked(paneClick);
            }
        };
    }

    public static EventHandler<MouseEvent> addVertex(MyPane pane) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double x = mouseEvent.getX();
                double y = mouseEvent.getY();
                double radius = 10;
                if (!pane.already(x,y,radius)) {
                    pane.addVertex(x, y, radius);
                }
            }
        };
    }
}
