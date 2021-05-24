package model.representations;

import model.edges.Edge;
import model.graphs.Graph;

public class WeightMatrix extends Matrix<Double> {
    private static final double INFINITY = Double.MAX_VALUE;

    public WeightMatrix(Graph graph) {
        super(graph, INFINITY);
    }

    @Override
    protected void addEdgeToMatrix(Edge edge) {
        getMatrix().get(edge.getSource()).put(edge.getDestination(), edge.getWeight());
//        getMatrix().get(edge.getDestination()).put(edge.getSource(), edge.getWeight());
    }

    @Override
    protected String matrixValue(Double d) {
        if (d == INFINITY) return "-";
        return String.valueOf(d);
    }
}