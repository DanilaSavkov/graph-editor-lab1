package handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.GraphicalVertex;

public class VertexHandler {
    public static EventHandler<MouseEvent> vertexClickEvent(GraphicalVertex vertex) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectVertex(vertex);
            }
        };
    }

    public static void selectVertex(GraphicalVertex vertex) {
        vertex.setSelected();
    }

    public static EventHandler<MouseEvent> moveVertex(GraphicalVertex vertex) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                vertex.setSelected();
                vertex.setX(mouseEvent.getX());
                vertex.setY(mouseEvent.getY());
            }
        };
    }
}
