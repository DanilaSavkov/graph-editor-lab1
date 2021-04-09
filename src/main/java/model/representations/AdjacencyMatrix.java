package model.representations;

import model.edges.Edge;
import model.graphs.Graph;

public class AdjacencyMatrix extends Matrix<Boolean> {
    public AdjacencyMatrix(Graph graph) {
        super(graph, false);
//        this.graph = (Graph<Vertex, Edge>) graph;
//        this.matrix = new HashMap<>();
//        buildMatrix();
    }

//    public Map<Vertex, Map<Vertex, Boolean>> getMatrix() {
//        return matrix;
//    }
//
//    public Graph<Vertex, Edge> getGraph() {
//        return graph;
//    }
//
//    private void buildMatrix() {
//        buildEmptyMatrix();
//        completeTheMatrix();
//    }
//
//    private void buildEmptyMatrix() {
//        for (Vertex i : graph.getVertices()) {
//            Map<Vertex, Boolean> matrixRow = new HashMap<>();
//            for (Vertex j : graph.getVertices()) {
//                matrixRow.put(j, false);
//            }
//            matrix.put(i, matrixRow);
//        }
//    }
//
//    private void completeTheMatrix() {
//        for (Edge edge : graph.getEdges()) {
//            addEdgeToMatrix(edge);
//        }
//    }

    protected void addEdgeToMatrix(Edge edge) {
        getMatrix().get(edge.getSource()).put(edge.getDestination(), true);
        getMatrix().get(edge.getDestination()).put(edge.getSource(), true);
    }

    @Override
    protected String matrixValue(Boolean aBoolean) {
        return aBoolean ? "1" : "0";
    }

//    @Override
//    public String toString() {
//        StringBuilder result = new StringBuilder();
//        for (Map<Vertex, Boolean> value : matrix.values()) {
//            for (Boolean bool : value.values()) {
//                result.append(bool ? "1" : "0").append("\t");
//            }
//            result.append("\n");
//        }
//        return result.toString();
//    }
}
