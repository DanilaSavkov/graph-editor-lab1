package model;

import javafx.scene.shape.Line;

public class GraphicalEdge extends Edge {
    private Line line;

    public GraphicalEdge(GraphicalVertex beginVertex, GraphicalVertex endVertex) {
        super(beginVertex, endVertex);
        this.line = new Line(beginVertex.getX(), beginVertex.getY(), endVertex.getX(), endVertex.getY());
    }

    public Line getLine() {
        return line;
    }
}
