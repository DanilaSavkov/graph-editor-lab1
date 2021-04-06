package model.representations;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

import java.util.HashMap;
import java.util.Map;

public class DistanceMatrix extends Matrix<Double> {
    public DistanceMatrix(Graph graph) {
        super(graph, -1.0);
    }

    @Override
    protected void completeTheMatrix() {
        for (Vertex i : getGraph().getVertices()) {
            Map<Vertex, Double> matrixRow = new HashMap<>();
            for (Vertex j : getGraph().getVertices()) {
                double distance = Math.sqrt(Math.pow(i.getX() - j.getX(), 2) + Math.pow(i.getY() - j.getY(), 2));
                matrixRow.put(j, distance);
            }
            getMatrix().put(i, matrixRow);
        }
    }

    @Override
    protected void addEdgeToMatrix(Edge edge) {

    }

    @Override
    protected String matrixValue(Double value) {
        String result = String.valueOf(value);
        int index = result.indexOf(".");
        return result.substring(0, index);
    }
}
