package view.components;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;

public class ToolBarConstructor {
    private static final double ENTERED_SCALE = 1.15;
    private static final Cursor ENTERED_CURSOR = Cursor.HAND;

    private final ToolBar toolBar;

    private final Button vertex;
    private final Button edge;

    public ToolBarConstructor() {
        vertex = new Button();
        configureVertexButton();
        edge = new Button();
        configureEdgeButton();
        toolBar = new ToolBar(vertex, new Separator(), edge);
        configureToolBar();
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    private void configureVertexButton() {
        vertex.setText("V");
        vertex.setOnMouseEntered(mouseEnteredHandler(vertex));
        vertex.setOnMouseExited(mouseExitedHandler(vertex));
    }

    private void configureEdgeButton() {
        edge.setText("E");
        edge.setOnMouseEntered(mouseEnteredHandler(edge));
        edge.setOnMouseExited(mouseExitedHandler(edge));
    }

    private void configureToolBar() {
        toolBar.setOrientation(Orientation.VERTICAL);
    }

    private EventHandler<MouseEvent> mouseEnteredHandler(Button button) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setScaleX(ENTERED_SCALE);
                button.setScaleY(ENTERED_SCALE);
                button.setCursor(ENTERED_CURSOR);
            }
        };
    }

    private EventHandler<MouseEvent> mouseExitedHandler(Button button) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setScaleX(1);
                button.setScaleY(1);
                button.setCursor(Cursor.DEFAULT);
            }
        };
    }
}
