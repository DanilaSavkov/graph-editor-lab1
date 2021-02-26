package view.components;

import handlers.SheetHandler;
import handlers.VertexHandler;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Graph;
import model.GraphicalVertex;
import model.Vertex;

public class Sheet extends Pane {
    private final Graph graph;

    public Sheet() {
        graph = new Graph();
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, SheetHandler.clickEvent(this));
        this.setOnKeyPressed(SheetHandler.keyEvent(this));
    }

    public Graph getGraph() {
        return graph;
    }

    public boolean contains(GraphicalVertex requiredVertex) {
        boolean result = false;
        for (Vertex vertex : graph.getVertices()) {
            if ((Math.pow(requiredVertex.getX() - vertex.getX(), 2) + Math.pow(requiredVertex.getY() - vertex.getY(), 2)) <= Math.pow(GraphicalVertex.getRadius(), 2)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void addVertex(GraphicalVertex vertex) {
        if (graph.add(vertex))
            this.getChildren().add(vertex.getCircle());
    }

    public void removeVertex(GraphicalVertex vertex) {
        if (graph.remove(vertex))
            this.getChildren().remove(vertex.getCircle());
    }

}