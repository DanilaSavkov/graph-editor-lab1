package model;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmSolver {
    private Graph<Vertex, Edge> graph;
    private AdjacencyMatrix matrix;

    public AlgorithmSolver(Graph graph) {
        this.graph = graph;
        for (Edge edge : this.graph.getEdges()) this.graph.remove(edge);
        matrix = new AdjacencyMatrix(graph);
    }

    private void removeAllEdges() {
        for (Edge edge : this.graph.getEdges()) this.graph.remove(edge);
    }

    private void DijkstraAlgorithm(Vertex start, Vertex end) {
        Map<Vertex, Boolean> checked = new HashMap<>(); // used
        for (Vertex vertex : graph.getVertices()) checked.put(vertex, false);
        for (Vertex vertex : graph.getVertices()) {
        }
    }

    private static double distance(Vertex vertex1, Vertex vertex2) {
        return Math.sqrt(Math.pow(vertex1.getX() - vertex2.getX(), 2) + Math.pow(vertex1.getY() - vertex2.getY(), 2));
    }
}
