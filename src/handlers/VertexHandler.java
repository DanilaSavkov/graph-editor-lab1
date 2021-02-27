package handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import model.GraphicalEdge;
import model.GraphicalVertex;

public class VertexHandler {
    // обработчик событий нажатия на вершину
    public static EventHandler<MouseEvent> mouseClick(GraphicalVertex vertex) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectVertex(vertex);
            }
        };
    }

    private static void selectVertex(GraphicalVertex vertex) {
        vertex.select();
    }

    // обработчик событий перетягивания вершины мышью
    public static EventHandler<MouseEvent> vertexDragEvent(GraphicalVertex vertex) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                moveVertex(vertex, mouseEvent);
            }
        };
    }

    private static void moveVertex(GraphicalVertex vertex, MouseEvent mouseEvent) {
        vertex.select();
        vertex.setX(mouseEvent.getX());
        vertex.setY(mouseEvent.getY());
    }
}
