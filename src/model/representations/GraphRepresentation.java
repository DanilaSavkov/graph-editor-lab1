package model.representations;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

public abstract class GraphRepresentation<V extends Vertex, E extends Edge> implements Configurable {
    private final Graph<V , E> graph;

    public GraphRepresentation(Graph<V, E> graph) {
        this.graph = graph;
        configure();
    }

    public Graph<V, E> getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return graph.getName() + "\n";
    }
}
