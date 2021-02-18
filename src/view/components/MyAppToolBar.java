package view.components;

import handlers.ToolBarHandler;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;

import static view.components.MyAppMenuBar.getImageBy;

public class MyAppToolBar {
    public static ToolBar generate() {
        double imageSide = 30;

        ImageView handImage = getImageBy("https://pngimg.com/uploads/cursor/cursor_PNG87.png", imageSide);
        ImageView vertexImage = getImageBy("https://static.thenounproject.com/png/80650-200.png", imageSide);
        ImageView edgeImage = getImageBy("https://img.icons8.com/ios/452/line--v1.png", imageSide);

        Button hand = getImagedButton(handImage);
        Button vertex = getImagedButton(vertexImage);
        Button edge = getImagedButton(edgeImage);

        ToolBar toolBar = new ToolBar(hand, new Separator(), vertex, new Separator(), edge);
        toolBar.setOrientation(Orientation.VERTICAL);

        return toolBar;
    }

    public static Button getImagedButton(ImageView imageView) {
        Button button = new Button();
        button.setGraphic(imageView);
        ToolBarHandler.setProperties(button, Cursor.HAND, 1.15);
        return button;
    }
}
