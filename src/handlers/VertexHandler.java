package handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.GraphicalVertex;
import view.components.MyPane;

public class VertexHandler {
    private MyPane pane;

    public VertexHandler(MyPane myPane) {
        this.pane = myPane;
    }

    // алгоритм добавления вершины на панель
    public EventHandler<MouseEvent> addVertex() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                if (mouseEvent.getClickCount() == 2) {
                    double x = mouseEvent.getX();
                    double y = mouseEvent.getY();
                    GraphicalVertex vertex = new GraphicalVertex(x, y);
                    if (!pane.already(vertex)) {
                        pane.addVertex(vertex);
                    }
//                }
            }
        };
    }

    public EventHandler<MouseEvent> selectVertex(GraphicalVertex vertex) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                vertex.getCircle().setFill(Color.RED);
            }
        };
    }
}
