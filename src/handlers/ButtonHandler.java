package handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import view.components.Sheet;

public class ButtonHandler {
    public static EventHandler<MouseEvent> mouseEntered(Button button, Cursor cursor, double scale) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setScaleX(scale);
                button.setScaleY(scale);
                button.setCursor(cursor);
            }
        };
    }

//    public static EventHandler<ActionEvent> vertexButtonAction(Sheet sheet) {
//        return new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                sheet.setVertexToolProperties();
//            }
//        };
//    }
}
