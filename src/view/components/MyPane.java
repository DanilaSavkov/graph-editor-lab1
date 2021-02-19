package view.components;

import handlers.ToolBarHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.Graph;
import model.Vertex;

public class MyPane extends Pane {
    private Graph graph;

    public MyPane() {
        graph = new Graph();
    }

    public Graph getGraph() {
        return graph;
    }

    public boolean already(double x, double y, double radius) {
        boolean result = false;
        for (Vertex vertex : graph.getVertices()) {
            if ((Math.pow(x - vertex.getX(), 2) + Math.pow(y - vertex.getY(), 2)) <= Math.pow(3 * radius, 2)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void addVertex(double x, double y, double radius) {
        graph.addVertex(x, y);
        this.getChildren().add(new Circle(x, y, radius));
    }
}