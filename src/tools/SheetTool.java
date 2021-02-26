package tools;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Parent;

public class SheetTool<T extends Event> extends Tool<T> {
    public SheetTool(Parent destination, EventType<T> influence, EventHandler<T> feedback) {
        super(destination, influence, feedback);
    }
}
