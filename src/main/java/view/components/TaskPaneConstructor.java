package view.components;

import javafx.geometry.HPos;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;

public class TaskPaneConstructor {
    private final GridPane taskPane;
    private final ToolBar toolBar;
    private final TabPane tabPane;

    public TaskPaneConstructor(ToolBar toolBar, TabPane tabPane) {
        this.toolBar = toolBar;
        this.tabPane = tabPane;
        taskPane = new GridPane();
        configureGridPane();
    }

    public GridPane getTaskPane() {
        return taskPane;
    }

    private void configureGridPane() {
        taskPane.setGridLinesVisible(false);

        ColumnConstraints col1 = new ColumnConstraints(toolBar.getMaxWidth());
        col1.setHgrow(Priority.NEVER);
        ColumnConstraints col2 = new ColumnConstraints(tabPane.getMaxWidth(), tabPane.getMaxWidth(), Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true);
        RowConstraints row1 = new RowConstraints(tabPane.getMaxHeight(), tabPane.getMaxHeight(), Double.MAX_VALUE);
        row1.setVgrow(Priority.ALWAYS);

        taskPane.getColumnConstraints().addAll(col1, col2);
        taskPane.getRowConstraints().add(row1);

        taskPane.add(tabPane, 1, 0);
        taskPane.add(toolBar, 0, 0);
    }
}