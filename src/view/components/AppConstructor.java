package view.components;

import file.GraphFileReader;
import file.GraphFileWriter;
import file.XMLGenerationException;
import file.XMLReadingException;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.edges.Edge;
import model.graphs.Graph;
import model.AdjacencyMatrix;
import model.vertecies.Vertex;

import java.io.File;
import java.util.Optional;

public class AppConstructor {
    private final Scene scene;
    private final GridPane root; // menu bar + task pane
    private final MenuBar menuBar;
    private final GridPane taskPane; // tool bar + tab pane
    private final ToolBar toolBar;
    private final TabPane tabPane;
    private final ToolBarConstructor toolBarConstructor;
    private final MenuBarConstructor menuBarConstructor;

    public AppConstructor() {
        tabPane = new TabPane();
        configureTabPane();

        toolBarConstructor = new ToolBarConstructor(null);
        toolBar = toolBarConstructor.getToolBar();
        configureToolBar();

        taskPane = new TaskPaneConstructor(toolBar, tabPane).getTaskPane();

        menuBarConstructor = new MenuBarConstructor();
        menuBar = menuBarConstructor.getMenuBar();
        configureMenuBar();

        root = new GridPane();
        configureRoot();

        scene = new Scene(root, 1000, 600);
    }

    public Scene getScene() {
        return scene;
    }

    private Sheet getActiveSheet() {
        return (Sheet) tabPane.getSelectionModel().getSelectedItem().getContent();
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

    private void configureTabPane() {
        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.FIXED);
        tabPane.getSelectionModel().selectedItemProperty().addListener(listener -> {
            if (!tabPane.getSelectionModel().isEmpty()) {
                toolBarConstructor.setSheet(getActiveSheet());
                toolBarConstructor.setDisable();
                toolBarConstructor.getSheet().setHandlers(null);
            }

            toolBar.setDisable(tabPane.getTabs().size() <= 0);
        });
    }

    private void configureMenuBar() {
        menuBarConstructor.getFileMenu().getItems().get(0).setOnAction(actionEvent -> {
            createNewSheet(getUserGraphName());
        });
        menuBarConstructor.getFileMenu().getItems().get(1).setOnAction(actionEvent -> {
            GraphFileReader work = new GraphFileReader();
            try {
                Graph<Vertex, Edge> graph = GraphFileReader.read(getOpenedFile());
                for (Tab tab : tabPane.getTabs()) {
                    if (tab.getText().equals(graph.getName())) {
                        return;
                    }
                }
                Sheet sheet = new Sheet(graph);
                sheet.setHandlers(null);
                tabPane.getTabs().add(new Tab(sheet.getGraph().getName(), sheet));
            } catch (XMLReadingException e) {
                showFileAlert(e.getMessage());
            } catch (IllegalArgumentException ignored) {

            }
        });
        menuBarConstructor.getFileMenu().getItems().get(2).setOnAction(actionEvent -> {
            try {
                File path = getSavingDirectory(getActiveSheet().getGraph().getName());
                getActiveSheet().getGraph().setName(path.getName());
                tabPane.getSelectionModel().getSelectedItem().setText(path.getName());
                GraphFileWriter writer = new GraphFileWriter(getActiveSheet().getGraph());
                writer.writeXML(path);
            } catch (XMLGenerationException e) {
                showFileAlert(e.getMessage());
            }
        });
    }

    private void configureToolBar() {
        toolBar.setDisable(true);
    }

    private void createNewSheet(String name) {
        for (Tab tab : tabPane.getTabs()) {
            if (tab.getText().equals(name)) {
                showExistsGraphAlert(name);
                return;
            }
        }
        if (name != null) {
            Sheet sheet = new Sheet(name);
            tabPane.getTabs().add(new Tab(sheet.getGraph().getName(), sheet));
        }
    }

    private String getUserGraphName() {
        TextInputDialog dialog = new TextInputDialog("Graph");
        dialog.setTitle("New Graph");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Graph name:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private File getOpenedFile() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select graph file");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("GRAPH", "*.graph")
        );
        return chooser.showOpenDialog(new Stage());
    }

    private File getSavingDirectory(String initialName) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select directory");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.setInitialFileName(initialName);
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("DIR", "*/*")
        );
        return chooser.showSaveDialog(new Stage());
    }

    private void showExistsGraphAlert(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ooops...");
        alert.setHeaderText(null);
        alert.setContentText("Graph named " + "'" + name + "'" + " already exist.");
        alert.showAndWait();
    }

    private void showFileAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ooops...");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
                        case W:
                            sheet.setLastSelectedWeight();
                        case A:
                            if (keyEvent.isControlDown()) sheet.selectAll();
                            break;
                        case M:
                            if (keyEvent.isControlDown()) showMatrix();
                            break;
                    }
                }
            }
        };
    }

    private void showMatrix() {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(getActiveSheet().getGraph());
        System.out.println(adjacencyMatrix);
    }
}
