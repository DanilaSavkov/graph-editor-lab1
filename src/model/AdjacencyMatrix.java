package model;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

import java.util.HashMap;
import java.util.Map;

public class AdjacencyMatrix {
    private Graph<Vertex, Edge> graph;
    private Map<Vertex, Map<Vertex, Boolean>> matrix;

    public AdjacencyMatrix(Graph graph) {
        this.graph = (Graph<Vertex, Edge>) graph;
        this.matrix = new HashMap<>();
        buildEmptyMatrix();
        completeTheMatrix();
    }

    private void buildEmptyMatrix() {
        for (Vertex i : graph.getVertices()) {
            Map<Vertex, Boolean> matrixRow = new HashMap<>();
            for (Vertex j : graph.getVertices()) {
                matrixRow.put(j, false);
            }
            matrix.put(i, matrixRow);
        }
    }

    private void completeTheMatrix() {
        for (Edge edge : graph.getEdges()) {
            addEdgeToMatrix(edge.getSource(), edge.getDestination());
        }
    }

    private void addEdgeToMatrix(Vertex v1, Vertex v2) {
        matrix.get(v1).put(v2, true);
        matrix.get(v2).put(v1, true);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map<Vertex, Boolean> value : matrix.values()) {
            for (Boolean bool : value.values()) {
                result.append(bool ? "1" : "0").append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
