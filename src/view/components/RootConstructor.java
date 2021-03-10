package view.components;

import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;

public class RootConstructor extends Parent {
    private final GridPane gridPane;
    private final MenuBar menuBar;
    private final Pane pane;

    public RootConstructor() {
        this.menuBar = new MenuBarConstructor().getMenuBar();
        this.pane = new TaskPaneConstructor().getTaskPane();
        this.gridPane = new GridPane();
        configureGridPane();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    private void configureGridPane() {
        gridPane.setGridLinesVisible(false);

        RowConstraints row1 = new RowConstraints(menuBar.getMaxHeight(), menuBar.getMaxHeight(), Double.MAX_VALUE);
        row1.setVgrow(Priority.NEVER);
        RowConstraints row3 = new RowConstraints(pane.getMaxHeight(), pane.getMaxHeight(), Double.MAX_VALUE);
        row3.setVgrow(Priority.ALWAYS);
        ColumnConstraints col1 = new ColumnConstraints(pane.getMaxWidth(), pane.getMaxWidth(), Double.MAX_VALUE);
        col1.setHgrow(Priority.ALWAYS);

        gridPane.getRowConstraints().add(row1);
        gridPane.getRowConstraints().add(row3);
        gridPane.getColumnConstraints().add(col1);

        gridPane.add(menuBar, 0, 0);
        gridPane.add(pane, 0, 1);
    }
}