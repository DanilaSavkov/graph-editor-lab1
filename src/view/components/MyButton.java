package view.components;

import handlers.ButtonHandler;
import handlers.PaneHandler;
import handlers.VertexHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MyButton extends Button {
    private static final Cursor enteredCursor = Cursor.HAND;
    private static final double IMAGE_SCALE = 1.15;
    private MyToolBar toolBar;

    public MyButton(ImageView imageView) {
        this.setGraphic(imageView);
        ButtonHandler buttonHandler = new ButtonHandler(this);
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, buttonHandler.mouseEnteredEvent(enteredCursor, IMAGE_SCALE));
        this.addEventFilter(MouseEvent.MOUSE_EXITED, buttonHandler.mouseEnteredEvent(Cursor.DEFAULT, 1));
    }

    public void setPaneClickEvent(EventHandler<MouseEvent> paneClick) {
        PaneHandler paneHandler = new PaneHandler(this.toolBar.getMyPane());
        this.addEventFilter(ActionEvent.ACTION, paneHandler.paneClickHandler(paneClick));
    }

    public void setToolBar(MyToolBar toolBar) {
        this.toolBar = toolBar;
    }
}
