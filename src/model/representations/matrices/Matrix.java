package model.representations.matrices;

import model.edges.Edge;
import model.graphs.Graph;
import model.representations.GraphRepresentation;
import model.vertecies.Vertex;

import java.util.HashMap;
import java.util.Map;

public abstract class Matrix<V extends Vertex, E extends Edge, T> extends GraphRepresentation<V, E> {
    private Map<V, Map<V, T>> matrix  = new HashMap<>();

    public Matrix(Graph<V, E> graph) {
        super(graph);
    }

    public Map<V, Map<V, T>> getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (V vertex : matrix.keySet()) {
            for (Map.Entry<V, T> vertexTEntry : matrix.get(vertex).entrySet()) {
                result.append(vertexTEntry.getValue());
            }
            result.append("\n");
        }
        return result.toString();
    }
}
