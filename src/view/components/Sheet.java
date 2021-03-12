package view.components;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Graphical;
import model.Selectable;
import model.edges.GraphicalEdge;
import model.graphs.Graph;
import model.vertecies.GraphicalVertex;

import java.util.ArrayList;
import java.util.List;

public class Sheet extends Pane {
    private final Graph<GraphicalVertex, GraphicalEdge> graph;
    private List<Selectable> selected;

    /*
     *      constructors
     */

    public Sheet() {
        graph = new Graph<>();
        selected = new ArrayList<>();
        setHandlers(mouseClickedHandler());
    }

    /*
     *      getter's and setter's
     */

    public List<Selectable> getSelected() {
        return selected;
    }

    public List<GraphicalVertex> getSelectedVertices() {
        ArrayList<GraphicalVertex> vertices = new ArrayList<>();
        for (Selectable item : selected) {
            if (item instanceof GraphicalVertex) {
                vertices.add((GraphicalVertex) item);
            }
        }
        return vertices;
    }

    public Graph<GraphicalVertex, GraphicalEdge> getGraph() {
        return graph;
    }

    public void setHandlers(EventHandler<MouseEvent> mouseClickedHandler) {
        setOnMouseClicked(mouseClickedHandler);
    }

    /*
     *      methods
     */

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
    
    public boolean contains(double x, double y) {
        return find(x, y) != null;
    }

    public Graphical find(double x, double y) {
        List<Graphical> graphItems = new ArrayList<>(graph.getEdges());
        graphItems.addAll(graph.getVertices());
        for (Graphical item : graphItems) {
            if (item.getShape().contains(x, y)) return item;
        }
        return null;
    }

    public void select(Selectable item) {
        if (!selected.contains(item)) {
            selected.add(item);
            item.setSelected();
        }
    }

    public void selectAll() {
        List<Selectable> graphItems = new ArrayList<>(graph.getEdges());
        graphItems.addAll(graph.getVertices());
        for (Selectable item : graphItems) {
            select(item);
        }
    }

    public void unselectAll() {
        for (Selectable item : selected) {
            item.setUnselected();
        }
        selected = new ArrayList<>();
    }

    /*
     *      handlers
     */

    public EventHandler<MouseEvent> mouseClickedHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {

                    if (contains(mouseEvent.getX(), mouseEvent.getY()))
                        select(find(mouseEvent.getX(), mouseEvent.getY()));
                    else unselectAll();

                    if (mouseEvent.getClickCount() == 2 && !contains(mouseEvent.getX(), mouseEvent.getY()))
                        add(new GraphicalVertex(mouseEvent.getX(), mouseEvent.getY()));
                }
            }
        };
    }
}