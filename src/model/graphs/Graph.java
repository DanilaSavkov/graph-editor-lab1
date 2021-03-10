package model.graphs;

import model.edges.Edge;
import model.vertecies.Vertex;

import java.util.*;

public class Graph<V extends Vertex, E extends Edge> {
    private String name;
    private final List<V> vertices;
    private final List<E> edges;

    /*
     *      constructors
     */

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /*
     *      getter's and setter's
     */

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

    /*
     *      other methods
     */

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
        for (E edge: edges) {
            if (edge.getSource().equals(vertex) || edge.getDestination().equals(vertex)) {
                remove(edge);
            }
        }
    }

    public void remove(E edge) {
        edges.remove(edge);
    }
}