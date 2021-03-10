package model.edges;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import model.Graphical;
import model.vertecies.GraphicalVertex;

public class GraphicalEdge extends Edge implements Graphical {
    private static final double LINE_STROKE_WIDTH = 3;
    private static final StrokeType LINE_STROKE_TYPE = StrokeType.CENTERED;

    private final Line line;
    private final Text text;
    private boolean selected;
    private boolean focused;

    /*
     *      constructors
     */

    public GraphicalEdge(GraphicalVertex source, GraphicalVertex destination) {
        super(source, destination);

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

    public Line getLine() {
        return line;
    }

    @Override
    public Text getText() {
        return text;
    }

    @Override
    public Shape[] getGraphics() {
        return new Shape[] {line, text};
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

    /*
     *      configurations
     */

    private void configureLine() {
        line.setFill(DEFAULT_COLOR);
        line.setStroke(DEFAULT_COLOR);
        line.setStrokeWidth(LINE_STROKE_WIDTH);
        line.setStrokeType(LINE_STROKE_TYPE);

        line.setOnDragEntered(event -> {

        });

        line.setOnMouseExited(event -> {

        });

        line.setOnMouseClicked(event -> {

        });
    }

    private void configureText() {
        text.setFont(FONT);
        text.setFill(TEXT_FILL);
        text.setText(String.valueOf(getWeight()));

        text.xProperty().bind(line.startXProperty().add(15));
        text.yProperty().bind(line.startXProperty().add(15));
    }
}
