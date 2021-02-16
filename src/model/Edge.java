package model;

public class Edge {
    private Vertex beginVertex;
    private Vertex endVertex;
    private int weight;

    public Edge(Vertex beginVertex, Vertex endVertex) {
        this.beginVertex = beginVertex;
        this.endVertex = endVertex;
    }

    public Vertex[] getEnds() {
        return new Vertex[]{beginVertex, endVertex};
    }

    public Vertex getBeginVertex() {
        return beginVertex;
    }

    public void setBeginVertex(Vertex beginVertex) {
        this.beginVertex = beginVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
