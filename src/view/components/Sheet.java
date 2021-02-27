package view.components;

import handlers.SheetHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class Sheet extends Pane {
    private final WeightedGraph<GraphicalVertex> weightedGraph;
    List<GraphicalVertex> selectedVertices;

    public Sheet() {
        weightedGraph = new WeightedGraph<>(true);
        selectedVertices = new ArrayList<>();
        setVertexToolProperties();
    }

    public List<GraphicalVertex> getSelectedVertices() {
        return selectedVertices;
    }

    public void add(GraphicalVertex vertex) {
        weightedGraph.add(vertex);
        this.getChildren().addAll(vertex.getCircle(), vertex.getText());
    }

    public void remove(GraphicalVertex vertex) {
        weightedGraph.remove(vertex);
        this.getChildren().removeAll(vertex.getCircle(), vertex.getText());
    }

    public boolean contains(GraphicalVertex requiredVertex) {
        return find(requiredVertex) != null;
    }

    public GraphicalVertex find(GraphicalVertex requiredVertex) {
        GraphicalVertex result = null;
        for (Vertex vertex : weightedGraph.getVertices()) {
            if ((Math.pow(requiredVertex.getX() - vertex.getX(), 2) + Math.pow(requiredVertex.getY() - vertex.getY(), 2)) <= Math.pow(GraphicalVertex.getRadius(), 2)) {
                result = (GraphicalVertex) vertex;
                break;
            }
        }
        return result;
    }

    public void select(GraphicalVertex vertex) {
        vertex.select();
        selectedVertices.add(vertex);
    }

    public void unselectAll() {
        selectedVertices = new ArrayList<>();
        for (Vertex vertex : weightedGraph.getVertices()) {
            ((GraphicalVertex) vertex).unselect();
        }
    }

    public void setVertexToolProperties() {
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, SheetHandler.mouseClick(this));
    }
}