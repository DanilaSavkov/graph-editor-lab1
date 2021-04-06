package model.vertecies;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import model.interfaces.Graphical;

public class GraphicalVertex extends Vertex implements Graphical {
    private final Circle circle;
    private final Text text;
    private boolean selected;
    private boolean focused;

    public GraphicalVertex(double x, double y) {
        super(x, y);

        circle = new Circle(x, y, CIRCLE_RADIUS);
        configureCircle();

        text = new Text();
        configureText();

        selected = false;
        focused = false;
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        circle.setCenterX(x);
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        circle.setCenterY(y);
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

    public void setCircleHandlers(EventHandler<MouseEvent> mouseClickedHandler,
                                  EventHandler<MouseEvent> mouseDraggedHandler,
                                  EventHandler<MouseEvent> mouseEnteredHandler,
                                  EventHandler<MouseEvent> mouseExitedHandler) {
        circle.setOnMouseClicked(mouseClickedHandler);
        circle.setOnMouseDragged(mouseDraggedHandler);
        circle.setOnMouseEntered(mouseEnteredHandler);
        circle.setOnMouseExited(mouseExitedHandler);
    }

    private void configureCircle() {
        circle.setFill(DEFAULT_FILL);
        circle.setOpacity(CIRCLE_FILL_OPACITY);

        circle.setStroke(DEFAULT_COLOR);
        circle.setStrokeWidth(STROKE_WIDTH);
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

    public EventHandler<MouseEvent> mouseDraggedHandler() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSelected();
                setX(mouseEvent.getX());
                setY(mouseEvent.getY());
            }
        };
    }
}