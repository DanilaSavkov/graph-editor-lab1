package model.edges;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import model.Graphical;
import model.vertecies.GraphicalVertex;

public class GraphicalEdge extends Edge implements Graphical {
    private final Line frame;
    private final Line line;
    private final Text text;
    private boolean selected;
    private boolean focused;

    /*
     *      constructors
     */

    public GraphicalEdge(GraphicalVertex source, GraphicalVertex destination) {
        super(source, destination);

        frame = new Line();
        configureFrame();

        line = new Line();
        configureLine();

        text = new Text();
        configureText();

        selected = false;
        focused = false;

    }

    /*
     *      getter's and setter's
     */

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

    /*
     *      configurations
     */

    private void configureLine() {
        line.setFill(DEFAULT_COLOR);
        line.setStroke(DEFAULT_COLOR);
        line.setStrokeWidth(LINE_STROKE_WIDTH);
        line.setStrokeType(LINE_STROKE_TYPE);

        listen();
        getSource().getShape().centerXProperty().addListener(event -> listen());
        getSource().getShape().centerYProperty().addListener(event -> listen());
        getDestination().getShape().centerXProperty().addListener(event -> listen());
        getDestination().getShape().centerYProperty().addListener(event -> listen());

        line.setOnMouseClicked(mouseClickedHandler());
        line.setOnMouseEntered(mouseEnteredHandler());
        line.setOnMouseExited(mouseExitedHandler());
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
        text.setText("");

        text.xProperty().bind(line.startXProperty().add(15));
        text.yProperty().bind(line.startYProperty().add(15));
    }

    private void listen() {
        double sourceX = getSource().getShape().getCenterX();
        double sourceY = getSource().getShape().getCenterY();
        double destinationX = getDestination().getShape().getCenterX();
        double destinationY = getDestination().getShape().getCenterY();

        double deltaX = getDeltaX(sourceX, destinationX, sourceY, destinationY);
        double deltaY = getDeltaY(sourceX, destinationX, sourceY, destinationY);
        double deltaXSign = (destinationX - sourceX) / Math.abs(destinationX - sourceX);
        double deltaYSign = (destinationY - sourceY) / Math.abs(destinationY - sourceY);

        line.startXProperty().bind(frame.startXProperty().add(deltaXSign * deltaX));
        line.startYProperty().bind(frame.startYProperty().add(deltaYSign * deltaY));
        line.endXProperty().bind(frame.endXProperty().add(-deltaXSign * deltaX));
        line.endYProperty().bind(frame.endYProperty().add(-deltaYSign * deltaY));
    }

    private double getDeltaX(double x1, double x2, double y1, double y2) {
        return getDeltaY(x1, x2, y1, y2) * (Math.abs(x2 - x1) / Math.abs(y2 - y1));
    }

    private double getDeltaY(double x1, double x2, double y1, double y2) {
        return Math.sqrt((Math.pow(CIRCLE_RADIUS + 1, 2)) / (Math.pow((x2 - x1) / (y2 - y1), 2) + 1));
    }

    /*
     *      handlers
     */

    public EventHandler<MouseEvent> mouseClickedHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    setSelected();
                }
            }
        };
    }

    public EventHandler<MouseEvent> mouseEnteredHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!selected) {
                    setFocused();
                }
            }
        };
    }

    public EventHandler<MouseEvent> mouseExitedHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setUnfocused();
                if (selected) {
                    setSelected();
                }
            }
        };
    }

}
