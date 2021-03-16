package model.representations;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

public abstract class GraphRepresentation implements Configurable {
    private final Graph<Vertex, Edge> graph;

    public GraphRepresentation(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }

    public Graph<Vertex, Edge> getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return graph.getName() + "\n";
    }
}
