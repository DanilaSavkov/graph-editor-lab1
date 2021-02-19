package view.components;

import handlers.ToolBarHandler;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MyToolBar extends ToolBar {
    private Pane workingPlace;

    public MyToolBar(Pane workingPlace) {
        this.workingPlace = workingPlace;
        double imageSide = 30;

        ImageView handImage = new MyImageView("https://pngimg.com/uploads/cursor/cursor_PNG87.png", imageSide);
        ImageView vertexImage = new MyImageView("https://static.thenounproject.com/png/80650-200.png", imageSide);
        ImageView edgeImage = new MyImageView("https://img.icons8.com/ios/452/line--v1.png", imageSide);

        Button hand = new MyButton(this, handImage, Cursor.HAND);
        Button vertex = new MyButton(this, vertexImage, Cursor.CROSSHAIR);
        Button edge = new MyButton(this, edgeImage, Cursor.DEFAULT);

        this.getItems().addAll(hand, new Separator(), vertex, new Separator(), edge);
        this.setOrientation(Orientation.VERTICAL);
    }

    public Pane getWorkingPlace() {
        return workingPlace;
    }
}
