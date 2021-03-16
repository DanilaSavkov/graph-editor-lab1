package model.edges;

import model.interfaces.Identified;
import model.vertecies.Vertex;

public class Edge implements Identified {
    private Vertex source;
    private Vertex destination;
    private String identifier;

    /*
     *      constructors
     */

    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
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

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /*
     *      main method's
     */

    public boolean contains(Vertex vertex) {
        return source.equals(vertex) || destination.equals(vertex);
    }
}
