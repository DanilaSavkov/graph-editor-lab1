package view.components.toolbar;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.interfaces.Graphical;
import model.interfaces.Selectable;

public abstract class ToolButton extends Button implements Selectable {
    private static final double BUTTON_SIDE_SIZE = Graphical.CIRCLE_RADIUS * 3;
    private static final double ENTERED_SCALE = 1.15;
    private static final Cursor ENTERED_CURSOR = Cursor.HAND;
    private final Graphical graphics;

    public ToolButton(Graphical graphics) {
        this.graphics = graphics;
        this.setGraphic(graphics.getShape());
        this.setMinSize(BUTTON_SIDE_SIZE, BUTTON_SIDE_SIZE);
        this.setMaxSize(BUTTON_SIDE_SIZE, BUTTON_SIDE_SIZE);
        this.setOnMouseEntered(this::mouseEntered);
        this.setOnMouseExited(this::mouseExited);
    }

    private void mouseEntered(MouseEvent mouseEvent) {
        graphics.mouseEnteredHandler().handle(mouseEvent);
        this.setScaleX(ENTERED_SCALE);
        this.setScaleY(ENTERED_SCALE);
        this.setCursor(ENTERED_CURSOR);
    }

    private void mouseExited(MouseEvent mouseEvent) {
        graphics.mouseExitedHandler().handle(mouseEvent);
        this.setScaleX(1);
        this.setScaleY(1);
        this.setCursor(Cursor.DEFAULT);
    }

    public Graphical getGraphics() {
        return graphics;
    }

    public static double getButtonSideSize() {
        return BUTTON_SIDE_SIZE;
    }

    @Override
    public boolean isSelected() {
        return graphics.isSelected();
    }

    @Override
    public void setSelected() {
        graphics.setSelected();
    }

    @Override
    public void setUnselected() {
        graphics.setUnselected();
    }
}