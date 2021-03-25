package model.representations.matrices;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

public class DistanceMatrix<V extends Vertex, E extends Edge> extends Matrix<V, E, Integer> {
    public DistanceMatrix(Graph<V, E> graph) {
        super(graph);
    }

    @Override
    public void configure() {

    }
}
