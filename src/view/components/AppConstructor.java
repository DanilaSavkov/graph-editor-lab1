package view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppConstructor {
    private Scene scene;

    private GridPane root; // menu bar + task pane
    private MenuBar menuBar;
    private GridPane taskPane; // tool bar + tab pane
    private ToolBar toolBar;
    private TabPane tabPane;
    private List<Sheet> sheets;

    private ToolBarConstructor toolBarConstructor;

    public AppConstructor() {
        sheets = new ArrayList<>();
        tabPane = new TabPane();
        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.FIXED);

        tabPane.getSelectionModel().selectedItemProperty().addListener(listener -> {
            toolBarConstructor.setSheet(sheets.get(tabPane.getSelectionModel().getSelectedIndex()));
        });

        toolBarConstructor = new ToolBarConstructor(null);
        toolBar =  toolBarConstructor.getToolBar();

        taskPane = new TaskPaneConstructor(toolBar, tabPane).getTaskPane();
        menuBar = new MenuBarConstructor().getMenuBar();

        menuBar.getMenus().get(0).getItems().get(0).setOnAction(newFileHandler());

        root = new GridPane();
        configureRoot();
        scene = new Scene(root, 1000, 600);
    }

    public Scene getScene() {
        return scene;
    }

    public void addSheet(String name) {
        Sheet sheet = new Sheet(name);
        sheets.add(sheet);
        tabPane.getTabs().add(new Tab(sheet.getGraph().getName(), sheet));

        toolBarConstructor.setSheet(sheet);
    }

    private void configureRoot() {
        root.setGridLinesVisible(false);

        RowConstraints row1 = new RowConstraints(menuBar.getMaxHeight(), menuBar.getMaxHeight(), Double.MAX_VALUE);
        row1.setVgrow(Priority.NEVER);
        RowConstraints row3 = new RowConstraints(taskPane.getMaxHeight(), taskPane.getMaxHeight(), Double.MAX_VALUE);
        row3.setVgrow(Priority.ALWAYS);
        ColumnConstraints col1 = new ColumnConstraints(taskPane.getMaxWidth(), taskPane.getMaxWidth(), Double.MAX_VALUE);
        col1.setHgrow(Priority.ALWAYS);

        root.getRowConstraints().add(row1);
        root.getRowConstraints().add(row3);
        root.getColumnConstraints().add(col1);

        root.add(menuBar, 0, 0);
        root.add(taskPane, 0, 1);
    }

    private EventHandler<ActionEvent> newFileHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addSheet(getNewGraphName());
            }
        };
    }

    private static String getNewGraphName() {
        TextInputDialog dialog = new TextInputDialog("(Безымянный)");
        dialog.setTitle("New Graph");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Graph name:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
