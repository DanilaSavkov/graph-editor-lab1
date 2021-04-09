package model.edges;

import model.vertecies.Vertex;

public class Edge {
    private Vertex source;
    private Vertex destination;
    private int weight;

    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
        this.weight = 0;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public boolean contains(Vertex vertex) {
        return source.equals(vertex) || destination.equals(vertex);
    }
}
