package view.components.toolbar;

import model.vertecies.GraphicalVertex;

public class VertexButton extends ToolButton {
    private static final GraphicalVertex GRAPHICS = new GraphicalVertex(0, 0);

    public VertexButton() {
        super(GRAPHICS);
        GRAPHICS.setCircleHandlers(null, null, null, null);
    }

    @Override
    public GraphicalVertex getGraphics() {
        return GRAPHICS;
    }
}