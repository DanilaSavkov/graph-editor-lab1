package view.components;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;

public class MyToolBar extends ToolBar {
    private final Sheet sheet;

    public MyToolBar(Sheet sheet) {
        this.sheet = sheet;
        double imageSide = 50;

        ImageView vertexImage = new MyImageView("https://pngimg.com/uploads/cursor/cursor_PNG78.png", imageSide);
        this.addAll(new Separator());
        this.setOrientation(Orientation.VERTICAL);
    }

    public void add(Node node) {
        this.getItems().add(node);
        if (node instanceof MyButton) ((MyButton) node).setToolBar(this);
    }

    public void addAll(Node... nodes) {
        for (Node node: nodes) {
            add(node);
        }
    }

    public Sheet getMyPane() {
        return sheet;
    }
}
