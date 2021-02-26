package tools;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;

public class Tool <T extends Event> {
    private final Node destination;
    private final EventType<T> influence;
    private final EventHandler<T> feedback;

    public Tool(Node destination, EventType<T> influence, EventHandler<T> feedback) {
        this.destination = destination;
        this.influence = influence;
        this.feedback = feedback;
        this.destination.addEventFilter(this.influence, this.feedback);
    }

    public Node getDestination() {
        return destination;
    }

    public EventType<T> getInfluence() {
        return influence;
    }

    public EventHandler<T> getFeedback() {
        return feedback;
    }
}
