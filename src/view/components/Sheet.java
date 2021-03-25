package view.components;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.edges.Edge;
import model.edges.GraphicalEdge;
import model.graphs.Graph;
import model.interfaces.Graphical;
import model.interfaces.Selectable;
import model.interfaces.Textual;
import model.vertecies.GraphicalVertex;
import model.vertecies.Vertex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Sheet extends Pane {
    private final Graph<GraphicalVertex, GraphicalEdge> graph;
    private List<Selectable> selected;

    /*
     *      constructors
     */

    public Sheet(String name) {
        graph = new Graph<>(name);
        selected = new ArrayList<>();
        setHandlers(null);
    }

    public Sheet(Graph<Vertex, Edge> graph) {
        this.graph = new Graph<>(graph.getName());
        for (Vertex vertex : graph.getVertices()) {
            GraphicalVertex graphicalVertex = new GraphicalVertex(vertex.getX(), vertex.getY());
            graphicalVertex.setIdentifier(vertex.getIdentifier());
            graphicalVertex.setCircleHandlers(null, null, graphicalVertex.mouseEnteredHandler(), graphicalVertex.mouseExitedHandler());
            add(graphicalVertex);
        }
        for (Edge edge : graph.getEdges()) {
            GraphicalVertex source = (GraphicalVertex) this.graph.find(new GraphicalVertex(edge.getSource().getX(), edge.getSource().getY()));
            GraphicalVertex destination = (GraphicalVertex) this.graph.find(new GraphicalVertex(edge.getDestination().getX(), edge.getDestination().getY()));
            GraphicalEdge graphicalEdge = new GraphicalEdge(source, destination);
            graphicalEdge.setIdentifier(edge.getIdentifier());
            graphicalEdge.setLineHandlers(null, graphicalEdge.mouseEnteredHandler(), graphicalEdge.mouseExitedHandler());
            add(graphicalEdge);
        }
        selected = new ArrayList<>();
        setHandlers(null);
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

    public List<GraphicalEdge> getSelectedEdges() {
        ArrayList<GraphicalEdge> edges = new ArrayList<>();
        for (Selectable item : selected) {
            if (item instanceof GraphicalEdge) {
                edges.add((GraphicalEdge) item);
            }
        }
        return edges;
    }

    public Graph<GraphicalVertex, GraphicalEdge> getGraph() {
        return graph;
    }

    public void setHandlers(EventHandler<MouseEvent> mouseClickedHandler) {
        setOnMouseClicked(mouseClickedHandler);
    }

    /*
     *      main methods
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
        Iterator<GraphicalEdge> iterator = graph.getEdges().iterator();
        while (iterator.hasNext()) {
            GraphicalEdge edge = iterator.next();
            if (edge.contains(vertex)) {
                iterator.remove();
                remove(edge);
            }
        }
        unselect(vertex);
        graph.remove(vertex);
        removeGraphics(vertex);
    }

    public void remove(GraphicalEdge edge) {
        unselect(edge);
        graph.remove(edge);
        removeGraphics(edge);
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

    public void unselect(Selectable item) {
        selected.remove(item);
        item.setUnselected();
    }

    public void unselectAll() {
        for (Selectable item : selected) {
            item.setUnselected();
        }
        selected = new ArrayList<>();
    }

    /*
     *      other method's
     */

    protected void removeSelected() {
        for (GraphicalEdge edge : getSelectedEdges()) remove(edge);
        for (GraphicalVertex vertex : getSelectedVertices()) remove(vertex);
    }

    protected void setLastSelectedId() {
        if (!selected.isEmpty()) {
            Selectable item = selected.get(selected.size() - 1);
            unselectAll();
            select(item);
            ((Textual) item).setIdentifier(getUserIdentifier((Textual) item));
        }
    }

    private void removeGraphics(Graphical item) {
        this.getChildren().removeAll(item.getGraphics());
    }

    private static String getUserIdentifier(Textual item) {
        TextInputDialog dialog = new TextInputDialog(item.getText().getText());
        dialog.setTitle("Change identifier");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("New identifier:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
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