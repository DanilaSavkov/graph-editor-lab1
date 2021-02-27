package model;

import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphicalEdge extends Edge implements Subscribable {
    private final Line line;
    private final Text text;

    public GraphicalEdge(Vertex startVertex, Vertex endVertex) {
        super(startVertex, endVertex);
        line = new Line(startVertex.getX(), startVertex.getY(), endVertex.getX(), endVertex.getY());
        text = new Text(getLineCenterX(), getLineCenterX(), null);
        text.setFont(FONT);
        text.setFill(TEXT_FILL);
    }

    public Line getLine() {
        return line;
    }

    public Text getText() {
        return text;
    }

    @Override
    public void setStartVertex(Vertex startVertex) {
        super.setStartVertex(startVertex);
        line.setStartX(startVertex.getX());
        line.setStartY(startVertex.getY());
        text.setX(getLineCenterX());
        text.setY(getLineCenterY());
    }

    @Override
    public void setEndVertex(Vertex endVertex) {
        super.setEndVertex(endVertex);
        line.setStartX(endVertex.getX());
        line.setStartY(endVertex.getY());
        text.setX(getLineCenterX());
        text.setY(getLineCenterY());
    }

    @Override
    public void setWeight(String weight) {
        super.setWeight(weight);
        text.setText(weight);
    }

    private double getLineCenterX() {
        return Math.abs(line.getStartX() - line.getEndX());
    }

    private double getLineCenterY() {
        return Math.abs(line.getStartY() - line.getEndY());
    }
}
