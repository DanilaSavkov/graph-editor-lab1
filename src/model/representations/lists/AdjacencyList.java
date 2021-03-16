package model.representations.lists;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

public class AdjacencyList extends List {
    public AdjacencyList(Graph<Vertex, Edge> graph) {
        super(graph);
    }
}
