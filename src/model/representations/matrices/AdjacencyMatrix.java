package model.representations.matrices;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

public class AdjacencyMatrix extends Matrix {
    public AdjacencyMatrix(Graph<Vertex, Edge> graph) {
        super(graph);
    }
}
