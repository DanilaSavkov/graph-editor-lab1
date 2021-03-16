package model.representations.matrices;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

public class DistanceMatrix extends Matrix {
    public DistanceMatrix(Graph<Vertex, Edge> graph) {
        super(graph);
    }
}
