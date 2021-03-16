package view.components;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import model.edges.GraphicalEdge;
import model.vertecies.GraphicalVertex;

import java.util.List;

public class ToolBarConstructor {
    private static final double ENTERED_SCALE = 1.15;
    private static final Cursor ENTERED_CURSOR = Cursor.HAND;

    private final ToolBar toolBar;
    private Sheet sheet;

    private final Button vertex;
    private final Button edge;

    /*
     *      constructors
     */

    public ToolBarConstructor(Sheet sheet) {
        this.sheet = sheet;
        vertex = new Button();
        edge = new Button();
        toolBar = new ToolBar(vertex, new Separator(), edge);
        configureToolBar();
    }

    /*
     *      getter's and setter's
     */

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    /*
     *      configurations
     */

    private void configureVertexButton() {
        vertex.setText("V\nE\nR\nT\nE\nX");
        vertex.setOnMouseEntered(mouseEnteredHandler(vertex));
        vertex.setOnMouseExited(mouseExitedHandler(vertex));
        vertex.setOnAction(actionEvent -> {
            for (GraphicalVertex vertex : sheet.getGraph().getVertices()) {
                vertex.setCircleHandlers(vertex.mouseClickedHandler(), vertex.mouseDraggedHandler(), vertex.mouseEnteredHandler(), vertex.mouseExitedHandler());
            }
            sheet.setHandlers(sheet.mouseClickedHandler());
        });
    }

    private void configureEdgeButton() {
        edge.setText("E\nD\nG\nE");
        edge.setOnMouseEntered(mouseEnteredHandler(edge));
        edge.setOnMouseExited(mouseExitedHandler(edge));
        edge.setOnMouseClicked(actionEvent -> {
            addEdgeHandler();
            for (GraphicalVertex vertex : sheet.getGraph().getVertices()) {
                vertex.setCircleHandlers(null, null, vertex.mouseEnteredHandler(), vertex.mouseExitedHandler());
            }
            for (GraphicalEdge edge : sheet.getGraph().getEdges()) {
                edge.setLineHandlers(null, edge.mouseEnteredHandler(), edge.mouseExitedHandler());
            }
            sheet.setHandlers(null);
        });
    }

    private void configureToolBar() {
        toolBar.setOrientation(Orientation.VERTICAL);
        configureVertexButton();
        configureEdgeButton();
    }

    /*
     *      handlers
     */

    private void addEdgeHandler() {
        List<GraphicalVertex> selectedVertices = sheet.getSelectedVertices();
        if (selectedVertices.size() > 1) {
            GraphicalVertex source = selectedVertices.get(selectedVertices.size() - 1);
            GraphicalVertex destination = selectedVertices.get(selectedVertices.size() - 2);
            GraphicalEdge edge = new GraphicalEdge(source, destination);
            sheet.add(edge);
        }
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
