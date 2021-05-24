package view.components;

import file.GraphFileReader;
import file.GraphFileWriter;
import file.XMLGenerationException;
import file.XMLReadingException;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import model.algorithm.AlgorithmSolver;
import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;
import view.components.menubar.AppMenuBar;
import view.components.tabpane.AppTabPane;
import view.components.tabpane.Sheet;
import view.components.toolbar.AppToolBar;

import java.io.File;

public class AppConstructor {
    private static final AppTabPane TAB_PANE = new AppTabPane();
    private static final AppToolBar TOOL_BAR = new AppToolBar(null);
    private static final AppMenuBar MENU_BAR = new AppMenuBar();

    public AppConstructor() {
        TOOL_BAR.setDisable(true);
        configureTabPane();
        configureMenuBar();
    }

    public Scene getScene() {
        return new Scene(getRoot(), 1000, 600);
    }

    private Pane getRoot() {
        GridPane root = new GridPane();
        root.setGridLinesVisible(false);
        GridPane taskPane = new TaskPaneConstructor(TOOL_BAR, TAB_PANE).getTaskPane();

        RowConstraints row1 = new RowConstraints(MENU_BAR.getMaxHeight(), MENU_BAR.getMaxHeight(), Double.MAX_VALUE);
        row1.setVgrow(Priority.NEVER);
        RowConstraints row3 = new RowConstraints(taskPane.getMaxHeight(), taskPane.getMaxHeight(), Double.MAX_VALUE);
        row3.setVgrow(Priority.ALWAYS);
        ColumnConstraints col1 = new ColumnConstraints(taskPane.getMaxWidth(), taskPane.getMaxWidth(), Double.MAX_VALUE);
        col1.setHgrow(Priority.ALWAYS);

        root.getRowConstraints().add(row1);
        root.getRowConstraints().add(row3);
        root.getColumnConstraints().add(col1);

        root.add(MENU_BAR, 0, 0);
        root.add(taskPane, 0, 1);
        return root;
    }

    private void configureTabPane() {
        TAB_PANE.getSelectionModel().selectedItemProperty().addListener(listener -> {
            if (!TAB_PANE.getSelectionModel().isEmpty()) {
                TOOL_BAR.setSheet(TAB_PANE.getActiveSheet());
                TOOL_BAR.setDisable();
                TOOL_BAR.getSheet().setHandlers(null);
            }
            TOOL_BAR.setDisable(TAB_PANE.getTabs().size() <= 0);
            TOOL_BAR.setDisable();
        });
    }

    private void configureMenuBar() {
        MENU_BAR.getFileMenu().getNewFileMenuItem().setOnAction(actionEvent -> {
            TAB_PANE.addSheet(Dialogs.getUserGraphName());
        });
        MENU_BAR.getFileMenu().getOpenFileMenuItem().setOnAction(actionEvent -> {
            try {
                Graph<Vertex, Edge> graph = GraphFileReader.read(Dialogs.getFileToOpenChoose());
                if (TAB_PANE.contains(graph.getName())) return;
                Sheet sheet = new Sheet(graph);
                TAB_PANE.addSheet(sheet);
            } catch (XMLReadingException e) {
                Dialogs.showFileError(e.getMessage());
            } catch (IllegalArgumentException ignored) {
            }
        });
        MENU_BAR.getFileMenu().getSaveAsFileMenuItem().setOnAction(actionEvent -> {
            try {
                File path = Dialogs.getPathToSaveFile(TAB_PANE.getActiveSheet().getGraph().getName());
                TAB_PANE.getActiveSheet().getGraph().setName(path.getName());
                TAB_PANE.getSelectionModel().getSelectedItem().setText(path.getName());
                GraphFileWriter writer = new GraphFileWriter(TAB_PANE.getActiveSheet().getGraph());
                writer.writeXML(path);
            } catch (XMLGenerationException e) {
                Dialogs.showFileError(e.getMessage());
            } catch (NullPointerException ignored) {

            }
        });

        MENU_BAR.getToolsMenu().getVertexModeMenuItem().setOnAction(TOOL_BAR::vertexButtonOnAction);
        MENU_BAR.getToolsMenu().getEdgeModeMenuItem().setOnAction(TOOL_BAR::edgeButtonOnAction);
        MENU_BAR.getToolsMenu().getAlgorithmMenuItem().setOnAction(actionEvent -> {
            AlgorithmSolver solver = new AlgorithmSolver(TAB_PANE.getActiveSheet().getGraph());
            solver.algorithm();
            Sheet sheet = new Sheet(solver.getGraph());
            TAB_PANE.setActiveSheet(sheet);
            TOOL_BAR.setSheet(sheet);
        });

        MENU_BAR.getRepresentationMenu().getAdjacencyMatrixMenuItem().setOnAction(actionEvent -> Dialogs.showAdjacencyMatrix(TAB_PANE));
        MENU_BAR.getRepresentationMenu().getDistanceMatrixMenuItem().setOnAction(actionEvent -> Dialogs.showDistanceMatrix(TAB_PANE));
        MENU_BAR.getRepresentationMenu().getWeightMatrixMenuItem().setOnAction(actionEvent -> Dialogs.showWeightMatrix(TAB_PANE));
    }

    public EventHandler<KeyEvent> keyReleasedHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (TAB_PANE.getTabs().size() > 0) {
                    Sheet sheet = TAB_PANE.getActiveSheet();
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
                            if (keyEvent.isControlDown()) Dialogs.showAdjacencyMatrix(TAB_PANE);
                            break;
                    }
                }
            }
        };
    }
}
