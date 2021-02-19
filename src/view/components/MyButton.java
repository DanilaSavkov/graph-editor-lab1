package view.components;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import static handlers.ToolBarHandler.*;

public class MyButton extends Button {
    private static final Cursor enteredCursor = Cursor.HAND;
    private static final double IMAGE_SCALE = 1.15;

    public MyButton(MyToolBar toolBar, EventHandler<MouseEvent> paneClick, Cursor onActionCursor, ImageView imageView) {
        this.setGraphic(imageView);
        setMouseEvent(enteredCursor, IMAGE_SCALE);
        setOnActionEvent(toolBar.getWorkingPlace(), onActionCursor, paneClick);
    }

    private void setMouseEvent(Cursor cursor, double scale) {
        this.setOnMouseEntered(getMouseEventHandler(this, cursor, scale));
        this.setOnMouseExited(getMouseEventHandler(this, Cursor.DEFAULT, 1));
    }

    public void setOnActionEvent(MyPane pane, Cursor onAction, EventHandler<MouseEvent> paneClick) {
        this.setOnAction(getOnActionHandler(pane, onAction, paneClick));
    }
}
