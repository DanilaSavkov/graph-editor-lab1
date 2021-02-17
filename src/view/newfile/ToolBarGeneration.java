package view.newfile;

import controller.ButtonPropertiesController;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;

import static view.menubar.MenuGeneration.getImageByProperties;

public class ToolBarGeneration {
    public static ToolBar getToolBar() {
        double imageSide = 30;

        ImageView handImage = getImageByProperties("https://pngimg.com/uploads/cursor/cursor_PNG87.png", imageSide);
        ImageView vertexImage = getImageByProperties("https://static.thenounproject.com/png/80650-200.png", imageSide);
        ImageView edgeImage = getImageByProperties("https://img.icons8.com/ios/452/line--v1.png", imageSide);

        Button hand = getButtonWithImage(handImage);
        Button vertex = getButtonWithImage(vertexImage);
        Button edge = getButtonWithImage(edgeImage);

        ToolBar toolBar = new ToolBar(hand, new Separator(), vertex, new Separator(), edge);
        toolBar.setOrientation(Orientation.VERTICAL);

        return toolBar;
    }

    private static Button getButtonWithImage(ImageView imageView) {
        Button button = new Button();
        button.setGraphic(imageView);
        ButtonPropertiesController.setProperties(button, Cursor.HAND, 1.15, 1);
        return button;
    }
}
