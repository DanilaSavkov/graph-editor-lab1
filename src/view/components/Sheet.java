package view.components;

import handlers.SheetHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Graph;
import model.GraphicalVertex;
import model.Vertex;

import java.util.ArrayList;
import java.util.List;

// идентификатор работает неправильно: установка идет на последню
// добавленную из выбранных вершин.
public class Sheet extends Pane {
    private final Graph graph;

    public Sheet() {
        graph = new Graph();
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, SheetHandler.mouseClick(this));
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

    public void add(GraphicalVertex vertex) {
        if (graph.add(vertex)) {
            this.getChildren().addAll(vertex.getCircle(), vertex.getText());
        }
    }

    public void remove(GraphicalVertex vertex) {
        if (graph.remove(vertex)) {
            this.getChildren().removeAll(vertex.getCircle(), vertex.getText());
        }
    }

    public List<GraphicalVertex> getSelectedVertices() {
        List<GraphicalVertex> vertices = new ArrayList<>();
        for (Vertex vertex : graph.getVertices()) {
            GraphicalVertex temp = (GraphicalVertex) vertex;
            if (temp.isSelected()) vertices.add(temp);
        }
        return vertices;
    }
}