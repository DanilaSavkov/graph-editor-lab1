package view.root;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;

public class MyAppRootGeneration {

    public static GridPane setRoot(MenuBar menuBar, Pane startPane) {
        GridPane root = new GridPane();
        root.setGridLinesVisible(true);

        RowConstraints row1 = new RowConstraints(menuBar.getMaxHeight(), menuBar.getMaxHeight(), Double.MAX_VALUE);
        row1.setVgrow(Priority.NEVER);
        RowConstraints row2 = new RowConstraints(startPane.getMaxHeight(), startPane.getMaxHeight(), Double.MAX_VALUE);
        row2.setVgrow(Priority.ALWAYS);
        ColumnConstraints col1 = new ColumnConstraints(startPane.getMaxWidth(), startPane.getMaxWidth(), Double.MAX_VALUE);
        col1.setHgrow(Priority.ALWAYS);

        root.getRowConstraints().add(row1);
        root.getRowConstraints().add(row2);
        root.getColumnConstraints().add(col1);

        root.add(menuBar, 0, 0);
        root.add(startPane, 0, 1);
        
        return root;
    }
}
