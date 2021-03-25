package model.representations.matrices;

import model.edges.Edge;
import model.edges.GraphicalEdge;
import model.graphs.Graph;
import model.vertecies.GraphicalVertex;
import model.vertecies.Vertex;

import java.util.HashMap;

public class AdjacencyMatrix<V extends Vertex, E extends Edge> extends Matrix<V, E, Boolean> {
    public AdjacencyMatrix(Graph<V, E> graph) {
        super(graph);
    }

    @Override
    public void configure() {
        for (V vertex : getGraph().getVertices()) {
            getMatrix().put(vertex, new HashMap<>());
            for (V vertex1 : getGraph().getVertices()) {
                getMatrix().get(vertex).put(vertex1, false);
            }
        }
//        fill();
    }

    public void fill() {
        for (E edge : getGraph().getEdges()) {
            getMatrix().get(edge.getSource()).put((V) edge.getDestination(), true);
            getMatrix().get(edge.getDestination()).put((V) edge.getSource(), true);
        }
    }
}
