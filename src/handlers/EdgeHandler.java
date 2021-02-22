package handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.GraphicalVertex;
import view.components.MyPane;

public class EdgeHandler {
    private MyPane pane;

    public EdgeHandler(MyPane myPane) {
        this.pane = myPane;
    }

    // алгоритм добавления вершины на панель
    public EventHandler<MouseEvent> addEdge() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("edge event");
            }
        };
    }
}
