package model.representations.matrices;

import model.edges.Edge;
import model.graphs.Graph;
import model.representations.GraphRepresentation;
import model.vertecies.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Matrix<T> extends GraphRepresentation {
    private final Map<Vertex, List<T>> matrix;

    public Matrix(Graph<Vertex, Edge> graph) {
        super(graph);
        matrix = new HashMap<>();
        configure();
    }

    public Map<Vertex, List<T>> getMatrix() {
        return matrix;
    }

    @Override
    public void configure() {

    }
}
