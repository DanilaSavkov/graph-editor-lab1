package view;

import handlers.ToolBarHandler;
import javafx.application.Application;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import view.components.MyMenuBar;
import view.components.MyAppWorkingWindow;
import view.components.MyAppRoot;
import view.components.MyToolBar;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("KBE PREMIUM");
        label.setStyle("-fx-font-size: xx-large; -fx-font-weight: bold; -fx-opacity: .1");

        // панель меню
        MenuBar menuBar = new MyMenuBar();
        menuBar.setStyle("");

        // стартовая панель
        BorderPane startPane = new BorderPane();
        startPane.setCenter(label);
        startPane.setStyle("-fx-background-color: #E7E7DE;");

        // рабочая зона + панель инструментов
        Pane list = new Pane();
        ToolBarHandler.whenButtonIsOnAction(list);
        list.setStyle("-fx-background-color: white;");
        ToolBar toolBar = new MyToolBar(list);
        toolBar.setStyle("");
        GridPane taskPane = MyAppWorkingWindow.generate(toolBar, list);

        // корень и сцена
        GridPane root = MyAppRoot.generate(menuBar, taskPane);
        Scene scene = new Scene(root, 800, 450);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("KBE Premium");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}