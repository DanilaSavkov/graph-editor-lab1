package model.representations;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

import java.util.HashMap;
import java.util.Map;

public abstract class Matrix<Type> {
    private final Graph<Vertex, Edge> graph;
    private final Map<Vertex, Map<Vertex, Type>> matrix;
    private final Type defaultValue;

    public Matrix(Graph graph, Type defaultValue) {
        this.graph = (Graph<Vertex, Edge>) graph;
        this.matrix = new HashMap<>();
        this.defaultValue = defaultValue;
        buildMatrix();
    }

    public Map<Vertex, Map<Vertex, Type>> getMatrix() {
        return matrix;
    }

    public Graph<Vertex, Edge> getGraph() {
        return graph;
    }

    private void buildMatrix() {
        buildEmptyMatrix();
        completeTheMatrix();
    }

    private void buildEmptyMatrix() {
        for (Vertex i : graph.getVertices()) {
            Map<Vertex, Type> matrixRow = new HashMap<>();
            for (Vertex j : graph.getVertices()) {
                matrixRow.put(j, defaultValue);
            }
            matrix.put(i, matrixRow);
        }
    }

    protected void completeTheMatrix() {
        for (Edge edge : graph.getEdges()) {
            addEdgeToMatrix(edge);
        }
    }

    protected abstract void addEdgeToMatrix(Edge edge);

    protected abstract String matrixValue(Type type);

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map<Vertex, Type> value : matrix.values()) {
            for (Type type : value.values()) {
                result.append(matrixValue(type)).append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
