package view.components;

import javafx.geometry.HPos;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;

public class MainWindow extends GridPane {
    private final ToolBar toolBar;
    private final Pane pane;

    public MainWindow(ToolBar toolBar, Pane pane) {
        this.toolBar = toolBar;
        this.pane = pane;
        this.generateCustomWindow();
    }

    private void generateCustomWindow() {
        this.setGridLinesVisible(false);

        ColumnConstraints col1 = new ColumnConstraints(toolBar.getMaxWidth());
        col1.setHgrow(Priority.NEVER);
        ColumnConstraints col2 = new ColumnConstraints(pane.getMaxWidth(), pane.getMaxWidth(), Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true);
        RowConstraints row1 = new RowConstraints(pane.getMaxHeight(), pane.getMaxHeight(), Double.MAX_VALUE);
        row1.setVgrow(Priority.ALWAYS);

        this.getColumnConstraints().addAll(col1, col2);
        this.getRowConstraints().add(row1);

        this.add(pane, 1, 0);
        this.add(toolBar, 0, 0);
    }
}