package model.edges;

import model.vertecies.Vertex;

public class Edge {
    private Vertex source;
    private Vertex destination;
    private int weight;

    /*
     *      constructors
     */

    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
    }

    public Edge(Edge edge) {
        this.source = edge.getSource();
        this.destination = edge.getDestination();
    }

    /*
     *      getter's and setter's
     */

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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
