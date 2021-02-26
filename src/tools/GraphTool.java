package tools;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.shape.Shape;

public class GraphTool<T extends Event> extends Tool<T> {
    public GraphTool(Shape destination, EventType<T> influence, EventHandler<T> feedback) {
        super(destination, influence, feedback);
    }
}
