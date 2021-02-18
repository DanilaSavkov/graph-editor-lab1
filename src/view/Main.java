package view;

import javafx.application.Application;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import view.components.MyAppMenuBar;
import view.components.MyAppWorkingWindow;
import view.components.MyAppRoot;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("KBE PREMIUM");
        label.setStyle("-fx-font-size: xx-large; -fx-font-weight: bold; -fx-opacity: .1");

        // панель меню
        MenuBar menuBar = MyAppMenuBar.generate();
        menuBar.setStyle("-fx-background-color: MediumAquamarine;");

        // стартовая панель
        BorderPane startPane = new BorderPane();
        startPane.setCenter(label);
        startPane.setStyle("-fx-background-color: #E7E7DE;");

        // рабочая зона + панель инструментов
        GridPane taskPane = MyAppWorkingWindow.generate();

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