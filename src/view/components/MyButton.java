package view.components;

import handlers.ToolBarHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MyButton extends Button {
    public MyButton(MyToolBar toolBar, ImageView imageView, Cursor cursor) {
        this.setGraphic(imageView);
        ToolBarHandler.setProperties(this, Cursor.HAND, 1.15);
        this.setOnAction(ToolBarHandler.setButtonCursorHandler(toolBar.getWorkingPlace(), cursor));
    }
}
