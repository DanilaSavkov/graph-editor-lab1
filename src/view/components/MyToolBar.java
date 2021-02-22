package view.components;

import handlers.EdgeHandler;
import handlers.VertexHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;

public class MyToolBar extends ToolBar {
    private final MyPane myPane;

    public MyToolBar(MyPane myPane) {
        this.myPane = myPane;
        double imageSide = 30;

        ImageView vertexImage = new MyImageView("https://pngimg.com/uploads/cursor/cursor_PNG11.png", imageSide);
        ImageView edgeImage = new MyImageView("https://img.icons8.com/ios/452/long-arrow-right.png", imageSide);

        MyButton vertex = new MyButton(vertexImage);
        VertexHandler vertexHandler = new VertexHandler(myPane);
        MyButton edge = new MyButton(edgeImage);
        EdgeHandler edgeHandler = new EdgeHandler(myPane);

        this.addAll(vertex, new Separator(), edge);
        this.setOrientation(Orientation.VERTICAL);

        vertex.setPaneClickEvent(vertexHandler.addVertex());
        edge.setPaneClickEvent(edgeHandler.addEdge());
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

    public MyPane getMyPane() {
        return myPane;
    }
}
