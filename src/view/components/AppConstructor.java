package view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.util.Optional;

public class AppConstructor {
    private final Scene scene;

    private final GridPane root; // menu bar + task pane
    private final MenuBar menuBar;
    private final GridPane taskPane; // tool bar + tab pane
    private final ToolBar toolBar;
    private final TabPane tabPane;

    private final ToolBarConstructor toolBarConstructor;
    private final TaskPaneConstructor taskPaneConstructor;
    private final MenuBarConstructor menuBarConstructor;

    /*
     *      constructors
     */

    public AppConstructor() {
        tabPane = new TabPane();
        configureTabPane();

        toolBarConstructor = new ToolBarConstructor(null);
        toolBar = toolBarConstructor.getToolBar();
        configureToolBar();

        taskPaneConstructor = new TaskPaneConstructor(toolBar, tabPane);
        taskPane = taskPaneConstructor.getTaskPane();

        menuBarConstructor = new MenuBarConstructor();
        menuBar = menuBarConstructor.getMenuBar();
        configureMenuBar();

        root = new GridPane();
        configureRoot();

        scene = new Scene(root, 1000, 600);
    }

    /*
     *      getter's and setter's
     */

    public Scene getScene() {
        return scene;
    }

    public Sheet getActiveSheet() {
        return (Sheet) tabPane.getSelectionModel().getSelectedItem().getContent();
    }

    /*
     *      configurations
     */

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

    private void configureTabPane() {
        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.FIXED);

        tabPane.getSelectionModel().selectedItemProperty().addListener(listener -> {
            if (!tabPane.getSelectionModel().isEmpty()) {
                toolBarConstructor.setSheet(getActiveSheet());
            }

            toolBar.setDisable(tabPane.getTabs().size() <= 0);
        });
    }

    private void configureMenuBar() {
        menuBarConstructor.getFileMenu().getItems().get(0).setOnAction(newFileHandler());
    }

    private void configureToolBar() {
        toolBar.setDisable(true);
    }

    /*
     *      methods
     */

    public void createNewFile(String name) {
        for (Tab tab : tabPane.getTabs()) {
            if (tab.getText().equals(name)) {
                existsGraphNameAlert(name);
                return;
            }
        }
        Sheet sheet = new Sheet(name);
        tabPane.getTabs().add(new Tab(sheet.getGraph().getName(), sheet));
    }

    private String getUserGraphName() {
        TextInputDialog dialog = new TextInputDialog("Unnamed");
        dialog.setTitle("New Graph");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Graph name:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void existsGraphNameAlert(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ooops...");
        alert.setHeaderText(null);
        alert.setContentText("Graph named " + "'" + name + "'" + " already exist.");
        alert.showAndWait();
    }

    /*
     *      handlers
     */

    private EventHandler<ActionEvent> newFileHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createNewFile(getUserGraphName());
            }
        };
    }

    public EventHandler<KeyEvent> keyReleasedHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (tabPane.getTabs().size() > 0) {
                    Sheet sheet = getActiveSheet();
                    switch (keyEvent.getCode()) {
                        case DELETE:
                            sheet.removeSelected();
                            break;
                        case I:
                            sheet.setLastSelectedId();
                            break;
                        case A:
                            if (keyEvent.isControlDown()) sheet.selectAll();
                            break;
//                    case E:
//                        if (keyEvent.isControlDown()) toolBarConstructor.edgeAction();
//                        break;
//                    case H:
//                        if (keyEvent.isControlDown()) sheet.horizontalAlignmentSelected();
//                        break;
                    }
                }
            }
        };
    }
}
