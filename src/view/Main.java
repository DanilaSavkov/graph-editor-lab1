package view;

import handlers.ButtonHandler;
import handlers.SheetHandler;
import javafx.application.Application;

import javafx.geometry.Orientation;
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
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem saveAsMenuItem = new MenuItem("Save as...");
        MenuItem vertexMenuItem = new MenuItem("Vertex");
        MenuItem edgeMenuItem = new MenuItem("Edge");
        Menu fileMenu = new Menu("File", null, newMenuItem, openMenuItem, new SeparatorMenuItem(), saveMenuItem, saveAsMenuItem);
        Menu toolsMenu = new Menu("Tools", null, vertexMenuItem, edgeMenuItem);
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
        Button vertexButton = new Button("vertex");
        ToolBar toolBar = new ToolBar(vertexButton, new Separator());
        toolBar.setOrientation(Orientation.VERTICAL);
        MainWindow mainWindow = new MainWindow(toolBar, sheet);

        // корень и сцена
        GridPane root = MyAppRoot.generate(menuBar, mainWindow);
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