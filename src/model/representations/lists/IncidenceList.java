package model.representations.lists;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;

public class IncidenceList extends List {
    public IncidenceList(Graph<Vertex, Edge> graph) {
        super(graph);
    }
}
