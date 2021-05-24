package model.representations;

import model.edges.Edge;
import model.graphs.Graph;

public class AdjacencyMatrix extends Matrix<Boolean> {
    public AdjacencyMatrix(Graph graph) {
        super(graph, false);
    }

    protected void addEdgeToMatrix(Edge edge) {
        getMatrix().get(edge.getSource()).put(edge.getDestination(), true);
//        getMatrix().get(edge.getDestination()).put(edge.getSource(), true);
    }

    @Override
    protected String matrixValue(Boolean aBoolean) {
        return aBoolean ? "1" : "0";
    }
}
