package model.algorithm;

import model.edges.Edge;
import model.graphs.Graph;
import model.representations.DistanceMatrix;
import model.vertecies.Vertex;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.fill;

public class AlgorithmSolver {
    private final Graph<Vertex, Edge> graph;
    private final List<Edge> workingEdges;
    private final DistanceMatrix matrix;
    private final boolean[] tagged;
    private final int[] nearest;

    public AlgorithmSolver(Graph graph) {
        this.graph = graph;
        this.workingEdges = new ArrayList<>();
        this.matrix = new DistanceMatrix(graph);
        tagged = new boolean[graph.getVerticesCount()];
        fill(tagged, false);
        nearest = new int[graph.getVerticesCount()];
        fill(nearest, 0);
    }

    public Graph<Vertex, Edge> getGraph() {
        return graph;
    }

    public void algorithm() {
        setTagged(0);
        for (int k = 0; k < graph.getVerticesCount() - 1; k++) {
            double minDistance = Double.MAX_VALUE;
            int j = 0;
            for (int i = 1; i < graph.getVerticesCount(); i++) {
                if (!tagged[i] && minDistance > matrix.getValue(nearest[i], i)) {
                    minDistance = matrix.getMatrix().get(graph.getVertices().get(nearest[i])).get(graph.getVertices().get(i));
                    j = i;
                }
            }
            mainAction(nearest[j], j);
            setTagged(j);
            for (int i = 1; i < graph.getVerticesCount(); i++) {
                if (!tagged[i] && matrix.getValue(nearest[i], i) > matrix.getValue(i, j)) {
                    nearest[i] = j;
                }
            }
        }
        graph.getEdges().removeIf(edge -> !workingEdges.contains(edge));
    }

    private Edge buildEdge(Vertex nearest, Vertex current) {
        Edge edge = new Edge(nearest, current);
        edge.setWeight(matrix.getMatrix().get(nearest).get(current).intValue());
        workingEdges.add(edge);
        return edge;
    }

    private void mainAction(int nearestIndex, int currentIndex) {
        Edge edge = buildEdge(graph.getVertices().get(nearestIndex), graph.getVertices().get(currentIndex));
        graph.add(edge);
    }

    private void setTagged(int index) {
        tagged[index] = true;
    }
}