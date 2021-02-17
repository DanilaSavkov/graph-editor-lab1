package view;

import javafx.application.Application;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import view.menubar.MyAppMenuBar;
import view.newfile.MyAppWorkingWindow;
import view.root.MyAppRootGeneration;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        Label label = new Label("KBE PREMIUM");         // водяная метка приложения
        label.setStyle("-fx-font-size: xx-large; -fx-font-weight: bold; -fx-opacity: .1");

        // панель меню
        MenuBar menuBar = MyAppMenuBar.getMenuBar();
        menuBar.setStyle("");

        // стартовая панель
        BorderPane startPane = new BorderPane();
        startPane.setCenter(label);
        startPane.setStyle("-fx-background-color: #E7E7DE;");

//        GridPane taskPane = MyAppWorkingWindow.getProjectPane();

        GridPane root = MyAppRootGeneration.setRoot(menuBar, startPane);
        Scene scene = new Scene(root, 800, 450);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("KBE Premium");
        stage.show();
    }
}