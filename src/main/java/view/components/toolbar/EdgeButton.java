package view.components.toolbar;

import model.edges.GraphicalEdge;
import model.vertecies.GraphicalVertex;

public class EdgeButton extends ToolButton {
    private static final GraphicalEdge GRAPHICS = new GraphicalEdge(new GraphicalVertex(0, 0), new GraphicalVertex(1, 1));

    public EdgeButton() {
        super(GRAPHICS);
        GRAPHICS.setLineHandlers(null, null, null);
    }

    @Override
    public GraphicalEdge getGraphics() {
        return GRAPHICS;
    }
}