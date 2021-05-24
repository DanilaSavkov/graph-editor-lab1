package model.edges;

import javafx.scene.shape.Shape;
import model.vertecies.GraphicalVertex;

import java.util.Arrays;
import java.util.HashSet;

public class GraphicalArrow extends GraphicalEdge {
    private Arrow arrow;

    public GraphicalArrow(GraphicalVertex source, GraphicalVertex destination) {
        super(source, destination);
        arrow = new Arrow(this.getShape());
    }

    public GraphicalArrow(Edge edge) {
        super(edge);
    }

    @Override
    public void setFocused() {
        super.setFocused();
        arrow.setFocused();
    }

    @Override
    public void setUnfocused() {
        super.setUnfocused();
        arrow.setUnfocused();
    }

    @Override
    public void setSelected() {
        super.setSelected();
        arrow.setSelected();
    }

    @Override
    public void setUnselected() {
        super.setUnselected();
        arrow.setUnselected();
    }

    @Override
    public void setWeight(double weight) {
        super.setWeight(weight);
    }

    @Override
    public Shape[] getGraphics() {
        HashSet<Shape> resultList = new HashSet<>(Arrays.asList(super.getGraphics()));
        resultList.addAll(Arrays.asList(arrow.getGraphics()));
        Shape[] result = resultList.toArray(new Shape[0]);
        return result;
    }
}