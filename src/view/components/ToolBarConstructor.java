package view.components;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import model.edges.GraphicalEdge;
import model.interfaces.Graphical;
import model.vertecies.GraphicalVertex;

import java.util.List;

public class ToolBarConstructor {
    private static final double ENTERED_SCALE = 1.15;
    private static final Cursor ENTERED_CURSOR = Cursor.HAND;
    private static final double BUTTON_SIDE_SIZE = Graphical.CIRCLE_RADIUS * 3;

    private static final Button VERTEX_BUTTON = new Button();
    private static final Button EDGE_BUTTON = new Button();
    private static final GraphicalVertex VERTEX_BUTTON_GRAPHICS = new GraphicalVertex(0, 0);
    private static final GraphicalEdge EDGE_BUTTON_GRAPHICS = new GraphicalEdge(new GraphicalVertex(0, 0), new GraphicalVertex(1, 1));

    private final ToolBar toolBar;
    private Sheet sheet;

    public ToolBarConstructor(Sheet sheet) {
        this.sheet = sheet;
        Separator separator = new Separator();
        separator.setMaxWidth(BUTTON_SIDE_SIZE);
        toolBar = new ToolBar(VERTEX_BUTTON, separator, EDGE_BUTTON);
        configureToolBar();
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    private void configureToolBar() {
        toolBar.setOrientation(Orientation.VERTICAL);
        configureVertexButton();
        configureEdgeButton();
    }

    private void configureVertexButton() {
        VERTEX_BUTTON_GRAPHICS.setCircleHandlers(null, null, null, null);
        VERTEX_BUTTON.setGraphic(VERTEX_BUTTON_GRAPHICS.getShape());
        VERTEX_BUTTON.setMinSize(BUTTON_SIDE_SIZE, BUTTON_SIDE_SIZE);
        VERTEX_BUTTON.setMaxSize(BUTTON_SIDE_SIZE, BUTTON_SIDE_SIZE);

        VERTEX_BUTTON.setOnMouseEntered(mouseEvent -> {
            VERTEX_BUTTON_GRAPHICS.mouseEnteredHandler().handle(mouseEvent);
            mouseEnteredHandler(VERTEX_BUTTON).handle(mouseEvent);
        });
        VERTEX_BUTTON.setOnMouseExited(mouseEvent -> {
            VERTEX_BUTTON_GRAPHICS.mouseExitedHandler().handle(mouseEvent);
            mouseExitedHandler(VERTEX_BUTTON).handle(mouseEvent);
        });
        VERTEX_BUTTON.setOnAction(actionEvent -> {
            VERTEX_BUTTON_GRAPHICS.setSelected();
            EDGE_BUTTON_GRAPHICS.setUnselected();
            vertexAction();
        });
    }

    private void configureEdgeButton() {
        EDGE_BUTTON_GRAPHICS.setLineHandlers(null, null, null);
        EDGE_BUTTON.setGraphic(EDGE_BUTTON_GRAPHICS.getShape());
        EDGE_BUTTON.setMinSize(BUTTON_SIDE_SIZE, BUTTON_SIDE_SIZE);
        EDGE_BUTTON.setMaxSize(BUTTON_SIDE_SIZE, BUTTON_SIDE_SIZE);

        EDGE_BUTTON.setOnMouseEntered(mouseEvent -> {
            EDGE_BUTTON_GRAPHICS.mouseEnteredHandler().handle(mouseEvent);
            mouseEnteredHandler(EDGE_BUTTON).handle(mouseEvent);
        });
        EDGE_BUTTON.setOnMouseExited(mouseEvent -> {
            EDGE_BUTTON_GRAPHICS.mouseExitedHandler().handle(mouseEvent);
            mouseExitedHandler(EDGE_BUTTON).handle(mouseEvent);
        });
        EDGE_BUTTON.setOnMouseClicked(actionEvent -> {
            VERTEX_BUTTON_GRAPHICS.setUnselected();
            EDGE_BUTTON_GRAPHICS.setSelected();
            edgeAction();
        });
    }

    protected void edgeAction() {
        createNewEdge();
        for (GraphicalVertex vertex : sheet.getGraph().getVertices()) {
            vertex.setCircleHandlers(null, null, vertex.mouseEnteredHandler(), vertex.mouseExitedHandler());
        }
        for (GraphicalEdge edge : sheet.getGraph().getEdges()) {
            edge.setLineHandlers(null, edge.mouseEnteredHandler(), edge.mouseExitedHandler());
        }
        sheet.unselectAll();
        sheet.setHandlers(null);
    }

    protected void vertexAction() {
        for (GraphicalVertex vertex : sheet.getGraph().getVertices()) {
            vertex.setCircleHandlers(vertex.mouseClickedHandler(), vertex.mouseDraggedHandler(), vertex.mouseEnteredHandler(), vertex.mouseExitedHandler());
        }
        sheet.setHandlers(sheet.mouseClickedHandler());
    }

    private void createNewEdge() {
        List<GraphicalVertex> selectedVertices = sheet.getSelectedVertices();
        if (selectedVertices.size() > 1) {
            GraphicalVertex source = selectedVertices.get(selectedVertices.size() - 1);
            GraphicalVertex destination = selectedVertices.get(selectedVertices.size() - 2);
            GraphicalEdge edge = new GraphicalEdge(source, destination);
            sheet.add(edge);
        }
    }

    public void setDisable() {
        EDGE_BUTTON_GRAPHICS.setUnselected();
        VERTEX_BUTTON_GRAPHICS.setUnselected();
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
