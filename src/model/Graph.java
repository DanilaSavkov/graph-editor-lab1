package model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Edge> edges;
    private final List<Vertex> vertices;

    public Graph() {
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public boolean add(Vertex vertex) {
        return vertices.add(vertex);
    }

    public boolean add(Edge edge) {
        return edges.add(edge);
    }

    public boolean remove(Vertex vertex) {
        if (vertices.contains(vertex)) {
            for (Edge edge: edgesFromVertex(vertex)) {
                remove(edge);
            }
            vertices.remove(vertex);
            return true;
        }
        return false;
    }

    public boolean remove(Edge edge) {
        return edges.remove(edge);
    }

    public List<Edge> edgesFromVertex(Vertex vertex) {
        List<Edge> result = new ArrayList<>();
        for (Edge edge: edges) {
            if (edge.getEnds()[0] == vertex || edge.getEnds()[1] == vertex) {
                result.add(edge);
            }
        }
        return result;
    }
}
