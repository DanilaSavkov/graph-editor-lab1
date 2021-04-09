package model.graphs;

import model.edges.Edge;
import model.vertecies.Vertex;

import java.util.*;

public class Graph<V extends Vertex, E extends Edge> {
    private String name;
    private final List<V> vertices;
    private final List<E> edges;

    public Graph(String name) {
        this.name = name;
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<V> getVertices() {
        return vertices;
    }

    public List<E> getEdges() {
        return edges;
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    public int getEdgesCount() {
        return edges.size();
    }

    public void add(V vertex) {
        vertices.add(vertex);
    }

    public void add(E edge) {
        if (vertices.contains(edge.getSource()) && vertices.contains(edge.getDestination())) {
            edges.add(edge);
        }
    }

    public void remove(V vertex) {
        vertices.remove(vertex);
    }

    public void remove(E edge) {
        edges.remove(edge);
    }

    public Vertex find(V vertex) {
        for (Vertex graphVertex : getVertices()) {
            if (vertex.getX() == graphVertex.getX() && vertex.getY() == graphVertex.getY()) return graphVertex;
        }
        return null;
    }

    public int getVertexIndex(V vertex) {
        return  vertices.indexOf(find(vertex));
    }
}