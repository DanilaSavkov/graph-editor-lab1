package model.vertecies;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import model.Graphical;

public class GraphicalVertex extends Vertex implements Graphical {
    private final Circle circle;
    private final Text text;
    private boolean selected;
    private boolean focused;

    /*
     *      constructors
     */

    public GraphicalVertex(double x, double y) {
        super(x, y);

        circle = new Circle(x, y, CIRCLE_RADIUS);
        configureCircle();

        text = new Text();
        configureText();

        selected = false;
        focused = false;
    }

    /*
     *      getter's and setter's
     */

    public static double getCircleRadius() {
        return CIRCLE_RADIUS;
    }

    @Override
    public Circle getShape() {
        return circle;
    }

    @Override
    public Text getText() {
        return text;
    }

    @Override
    public Shape[] getGraphics() {
        return new Shape[]{circle, text};
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected() {
        selected = true;
        circle.setStroke(SELECTED_COLOR);
    }

    @Override
    public void setUnselected() {
        selected = false;
        circle.setStroke(DEFAULT_COLOR);
    }

    @Override
    public boolean isFocused() {
        return focused;
    }

    @Override
    public void setFocused() {
        focused = true;
        circle.setStroke(FOCUSED_COLOR);
    }

    @Override
    public void setUnfocused() {
        focused = false;
        circle.setStroke(DEFAULT_COLOR);
    }

    @Override
    public void setIdentifier(String identifier) {
        super.setIdentifier(identifier);
        text.setText(identifier);
    }

    public void setCircleHandlers(EventHandler<MouseEvent> mouseClickedHandler, EventHandler<MouseEvent> mouseDraggedHandler,
                                  EventHandler<MouseEvent> mouseEnteredHandler, EventHandler<MouseEvent> mouseExitedHandler) {
        circle.setOnMouseClicked(mouseClickedHandler);
        circle.setOnMouseDragged(mouseDraggedHandler);
        circle.setOnMouseEntered(mouseEnteredHandler);
        circle.setOnMouseExited(mouseExitedHandler);
    }

    /*
     *      configurations
     */

    private void configureCircle() {
        circle.setFill(CIRCLE_FILL);
        circle.setOpacity(CIRCLE_FILL_OPACITY);

        circle.setStroke(DEFAULT_COLOR);
        circle.setStrokeWidth(CIRCLE_STROKE_WIDTH);
        circle.setStrokeType(CIRCLE_STROKE_TYPE);

        setCircleHandlers(mouseClickedHandler(), mouseDraggedHandler(), mouseEnteredHandler(), mouseExitedHandler());
    }

    private void configureText() {
        text.setFont(FONT);
        text.setFill(TEXT_FILL);
        text.setText(getIdentifier());

        text.xProperty().bind(circle.centerXProperty().add(CIRCLE_RADIUS));
        text.yProperty().bind(circle.centerYProperty().add(CIRCLE_RADIUS));
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

    public EventHandler<MouseEvent> mouseDraggedHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSelected();
                circle.setCenterX(mouseEvent.getX());
                circle.setCenterY(mouseEvent.getY());
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

    public EventHandler<MouseEvent> edgeHandler(int count) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    setSelected();
                }
            }
        };
    }
}