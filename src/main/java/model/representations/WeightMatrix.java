package model.representations;

import model.edges.Edge;
import model.graphs.Graph;

public class WeightMatrix extends Matrix<Integer> {
    private static final int INFINITY = Integer.MAX_VALUE;

    public WeightMatrix(Graph graph) {
        super(graph, INFINITY);
    }

    public static int getINFINITY() {
        return INFINITY;
    }

    @Override
    protected void addEdgeToMatrix(Edge edge) {
        getMatrix().get(edge.getSource()).put(edge.getDestination(), edge.getWeight());
        getMatrix().get(edge.getDestination()).put(edge.getSource(), edge.getWeight());
    }

    @Override
    protected String matrixValue(Integer integer) {
        if (integer == INFINITY) return "-";
        return String.valueOf(integer);
    }
}