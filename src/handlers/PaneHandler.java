package handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import view.components.MyPane;

public class PaneHandler {
    private Pane pane;

    public PaneHandler(Pane pane) {
        this.pane = pane;
    }

    // активация действия при нажатии на кнопку
    public EventHandler<ActionEvent> paneClickHandler(EventHandler<MouseEvent> paneClickEvent) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.addEventFilter(MouseEvent.MOUSE_CLICKED, paneClickEvent);
            }
        };
    }
}
