package model.edges;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import model.interfaces.Graphical;
import model.vertecies.GraphicalVertex;

public class GraphicalEdge extends Edge implements Graphical {
    private final Line frame;
    private final Line line;
    private final Text text;
    private boolean selected;
    private boolean focused;

    public GraphicalEdge(GraphicalVertex source, GraphicalVertex destination) {
        super(source, destination);

        frame = new Line();
        configureFrame();

        line = new Line();
        configureLine();

        text = new Text(String.valueOf(this.getWeight()));
        configureText();

        selected = false;
        focused = false;
    }

    public GraphicalEdge(Edge edge) {
        super(edge);

        frame = new Line();
        configureFrame();

        line = new Line();
        configureLine();

        text = new Text(String.valueOf(edge.getWeight()));
        configureText();

        selected = false;
        focused = false;
    }

    @Override
    public Line getShape() {
        return line;
    }

    @Override
    public Text getText() {
        return text;
    }

    @Override
    public Shape[] getGraphics() {
        return new Shape[]{line, frame, text};
    }

    @Override
    public boolean isFocused() {
        return focused;
    }

    @Override
    public void setFocused() {
        focused = true;
        line.setStroke(FOCUSED_COLOR);
    }

    @Override
    public void setUnfocused() {
        focused = false;
        line.setStroke(DEFAULT_COLOR);
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected() {
        selected = true;
        line.setStroke(SELECTED_COLOR);
    }

    @Override
    public void setUnselected() {
        selected = false;
        line.setStroke(DEFAULT_COLOR);
    }

    @Override
    public GraphicalVertex getSource() {
        return (GraphicalVertex) super.getSource();
    }

    @Override
    public GraphicalVertex getDestination() {
        return (GraphicalVertex) super.getDestination();
    }

    @Override
    public void setWeight(int weight) {
        super.setWeight(weight);
        text.setText(String.valueOf(weight));
    }

    public void setLineHandlers(EventHandler<MouseEvent> mouseClickedHandler,
                                EventHandler<MouseEvent> mouseEnteredHandler,
                                EventHandler<MouseEvent> mouseExitedHandler) {
        line.setOnMouseClicked(mouseClickedHandler);
        line.setOnMouseEntered(mouseEnteredHandler);
        line.setOnMouseExited(mouseExitedHandler);
    }

    private double getXLength(double x1, double x2, double y1, double y2) {
        return getYLength(x1, x2, y1, y2) * (Math.abs(x2 - x1) / Math.abs(y2 - y1));
    }

    private double getYLength(double x1, double x2, double y1, double y2) {
        return Math.sqrt((Math.pow(CIRCLE_RADIUS + LINE_OFFSET, 2)) / (Math.pow((x2 - x1) / (y2 - y1), 2) + 1));
    }

    private void updateLineAngle() {
        double sourceX = getSource().getShape().getCenterX();
        double sourceY = getSource().getShape().getCenterY();
        double destinationX = getDestination().getShape().getCenterX();
        double destinationY = getDestination().getShape().getCenterY();

        double deltaX = getXLength(sourceX, destinationX, sourceY, destinationY);
        double deltaY = getYLength(sourceX, destinationX, sourceY, destinationY);
        double deltaXSign = (destinationX - sourceX) / Math.abs(destinationX - sourceX);
        double deltaYSign = (destinationY - sourceY) / Math.abs(destinationY - sourceY);

        line.startXProperty().bind(frame.startXProperty().add(deltaXSign * deltaX));
        line.startYProperty().bind(frame.startYProperty().add(deltaYSign * deltaY));
        line.endXProperty().bind(frame.endXProperty().add(-deltaXSign * deltaX));
        line.endYProperty().bind(frame.endYProperty().add(-deltaYSign * deltaY));
    }

    private void updateTextLocation() {
        double sourceX = getSource().getShape().getCenterX();
        double sourceY = getSource().getShape().getCenterY();
        double destinationX = getDestination().getShape().getCenterX();
        double destinationY = getDestination().getShape().getCenterY();

        double deltaXSign = (destinationX - sourceX) / Math.abs(destinationX - sourceX);
        double deltaYSign = (destinationY - sourceY) / Math.abs(destinationY - sourceY);

        text.xProperty().bind(frame.startXProperty().add(deltaXSign * Math.abs(frame.getStartX() - frame.getEndX()) / 2));
        text.yProperty().bind(frame.startYProperty().add(deltaYSign * Math.abs(frame.getStartY() - frame.getEndY()) / 2));
    }

    private void configureLine() {
        line.setStroke(DEFAULT_COLOR);
        line.setStrokeWidth(STROKE_WIDTH);
        line.setStrokeType(LINE_STROKE_TYPE);

        updateLineAngle();
        getSource().getShape().centerXProperty().addListener(event -> updateLineAngle());
        getSource().getShape().centerYProperty().addListener(event -> updateLineAngle());
        getDestination().getShape().centerXProperty().addListener(event -> updateLineAngle());
        getDestination().getShape().centerYProperty().addListener(event -> updateLineAngle());

        setLineHandlers(mouseClickedHandler(), mouseEnteredHandler(), mouseExitedHandler());
    }

    private void configureFrame() {
        frame.setFill(Color.TRANSPARENT);
        frame.setStroke(Color.TRANSPARENT);

        frame.startXProperty().bind(getSource().getShape().centerXProperty());
        frame.startYProperty().bind(getSource().getShape().centerYProperty());
        frame.endXProperty().bind(getDestination().getShape().centerXProperty());
        frame.endYProperty().bind(getDestination().getShape().centerYProperty());
    }

    private void configureText() {
        text.setFont(FONT);
        text.setFill(TEXT_FILL);

        updateTextLocation();
        getSource().getShape().centerXProperty().addListener(event -> updateTextLocation());
        getSource().getShape().centerYProperty().addListener(event -> updateTextLocation());
        getDestination().getShape().centerXProperty().addListener(event -> updateTextLocation());
        getDestination().getShape().centerYProperty().addListener(event -> updateTextLocation());
    }
}
