package model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Edge> edges;
    private List<Vertex> vertices;

    public boolean addVertex(int x, int y) {
        Vertex toAdd = new Vertex(x, y);
        return vertices.add(toAdd);
    }

    public boolean addEdge(Vertex beginVertex, Vertex endVertex) {
        Edge toAdd = new Edge(beginVertex, endVertex);
        return edges.add(toAdd);
    }

    public boolean remove(Vertex vertex) {
        if (vertices.contains(vertex)) {
            for (Edge edge: findEdgesByVertex(vertex)) {
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

    public List<Edge> findEdgesByVertex(Vertex vertex) {
        List<Edge> result = new ArrayList<>();
        for (Edge edge: edges) {
            if (edge.getEnds()[0] == vertex || edge.getEnds()[1] == vertex) {
                result.add(edge);
            }
        }
        return result;
    }


}
