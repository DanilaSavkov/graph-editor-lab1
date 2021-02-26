package view;

import handlers.SheetHandler;
import javafx.application.Application;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import view.components.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // панель меню
        MenuBar menuBar = new MenuBar();
        MenuItem newButton = new MenuItem("New");
        MenuItem openButton = new MenuItem("Open");
        MenuItem saveButton = new MenuItem("Save");
        MenuItem saveAsButton = new MenuItem("Save as...");
        MenuItem handButton = new MenuItem("Hand");
        MenuItem vertexButton = new MenuItem("Vertex");
        MenuItem edgeButton = new MenuItem("Edge");
        Menu fileMenu = new Menu("File", null, newButton, openButton, new SeparatorMenuItem(), saveButton, saveAsButton);
        Menu toolsMenu = new Menu("Tools", null, handButton, vertexButton, edgeButton);
        menuBar.getMenus().addAll(fileMenu, toolsMenu);
        menuBar.setStyle("");

        // стартовая панель
        BorderPane startPane = new BorderPane();
        Label label = new Label("KBE PREMIUM");
        label.setStyle("-fx-font-size: xx-large; -fx-font-weight: bold; -fx-opacity: .1");
        startPane.setCenter(label);
        startPane.setStyle("-fx-background-color: #E7E7DE;");

        // рабочая зона + панель инструментов
        Sheet sheet = new Sheet();
        ToolBar toolBar = new MyToolBar(sheet);
        toolBar.setStyle("");
        GridPane taskPane = MyAppWorkingWindow.generate(toolBar, sheet);

        // корень и сцена
        GridPane root = MyAppRoot.generate(menuBar, taskPane);
        Scene scene = new Scene(root, 800, 450);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("KBE Premium");
        stage.addEventFilter(KeyEvent.KEY_RELEASED, SheetHandler.keyReleased(sheet));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}