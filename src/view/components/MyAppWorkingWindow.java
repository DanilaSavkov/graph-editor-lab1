package view.components;

import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;

public class MyAppWorkingWindow {
    public static GridPane generate(ToolBar toolBar, Pane pane) {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(false);

        ColumnConstraints col1 = new ColumnConstraints(toolBar.getMaxWidth());
        col1.setHgrow(Priority.NEVER);
        ColumnConstraints col2 = new ColumnConstraints(pane.getMaxWidth(), pane.getMaxWidth(), Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true);
        RowConstraints row1 = new RowConstraints(pane.getMaxHeight(), pane.getMaxHeight(), Double.MAX_VALUE);
        row1.setVgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(col1, col2);
        gridPane.getRowConstraints().add(row1);

        gridPane.add(pane, 1, 0);
        gridPane.add(toolBar, 0, 0);

        return gridPane;
    }
}