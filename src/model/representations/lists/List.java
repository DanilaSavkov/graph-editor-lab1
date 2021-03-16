package model.representations.lists;

import model.edges.Edge;
import model.graphs.Graph;
import model.representations.GraphRepresentation;
import model.vertecies.Vertex;

import java.util.HashMap;
import java.util.Map;

public abstract class List extends GraphRepresentation {
    private final Map<Vertex, java.util.List<Vertex>> list;

    public List(Graph<Vertex, Edge> graph) {
        super(graph);
        list = new HashMap<>();
        configure();
    }

    public Map<Vertex, java.util.List<Vertex>> getList() {
        return list;
    }

    @Override
    public void configure() {

    }
}
