package view.components;

import handlers.SheetHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Selectable;
import model.edges.GraphicalEdge;
import model.graphs.Graph;
import model.vertecies.GraphicalVertex;
import model.vertecies.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Sheet extends Pane {
    private final Graph<GraphicalVertex, GraphicalEdge> graph;
    private List<Selectable> selected;

    public Sheet() {
        graph = new Graph<>();
        selected = new ArrayList<>();
        this.setOnMouseClicked(mouseClickedHandler());
    }

    public List<Selectable> getSelected() {
        return selected;
    }

    public void add(GraphicalVertex vertex) {
        graph.add(vertex);
        this.getChildren().addAll(vertex.getGraphics());
    }

    public void add(GraphicalEdge edge) {
        graph.add(edge);
        this.getChildren().addAll(edge.getGraphics());
    }

    public void remove(GraphicalVertex vertex) {
        graph.remove(vertex);
        this.getChildren().removeAll(vertex.getGraphics());
    }

    public void remove(GraphicalEdge edge) {
        graph.remove(edge);
        this.getChildren().removeAll(edge.getGraphics());
    }

    public void select(Selectable item) {
        item.setSelected();
        selected.add(item);
    }

    public void unselectAll() {
        for (Selectable item : selected) {
            item.setUnselected();
        }
        selected = new ArrayList<>();
    }


    public boolean contains(GraphicalVertex requiredVertex) {
        return find(requiredVertex) != null;
    }

    public GraphicalVertex find(GraphicalVertex requiredVertex) {
        GraphicalVertex result = null;
        for (GraphicalVertex vertex : graph.getVertices()) {
            if ((Math.pow(requiredVertex.getCircle().getCenterX() - vertex.getCircle().getCenterX(), 2)
                    + Math.pow(requiredVertex.getCircle().getCenterY() - vertex.getCircle().getCenterY(), 2))
                    <= Math.pow(GraphicalVertex.getCircleRadius(), 2)) {
                result = (GraphicalVertex) vertex;
                break;
            }
        }
        return result;
    }

    /*
     *      handlers
     */

    private EventHandler<MouseEvent> mouseClickedHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    GraphicalVertex vertex = new GraphicalVertex(mouseEvent.getX(), mouseEvent.getY());
                    if (contains(vertex)) {
                        select(find(vertex));
                    } else unselectAll();
                    if (mouseEvent.getClickCount() == 2) {
                        if (!contains(vertex)) {
                            add(vertex);
                        }
                    }
                }
            }
        };
    }
}