package view.components;

import handlers.VertexHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Graph;
import model.GraphicalEdge;
import model.GraphicalVertex;
import model.Vertex;

import java.nio.file.attribute.GroupPrincipal;

public class MyPane extends Pane {
    private final Graph graph;

    public MyPane() {
        graph = new Graph();
    }

    public Graph getGraph() {
        return graph;
    }

    // может куда-то переместить?
    public boolean already(GraphicalVertex vertexToFind) {
        boolean result = false;
        for (Vertex vertex : graph.getVertices()) {
            if ((Math.pow(vertexToFind.getX() - vertex.getX(), 2) + Math.pow(vertexToFind.getY() - vertex.getY(), 2)) <= Math.pow(3 * GraphicalVertex.getRadius(), 2)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void addVertex(GraphicalVertex vertex) {
        graph.addVertex(vertex);
        this.getChildren().add(vertex.getCircle());
    }

//    public void addEdge(GraphicalEdge edge) {
//        graph.addEdge(edge);
//        this.getChildren().add(edge.getLine());
//    }
}