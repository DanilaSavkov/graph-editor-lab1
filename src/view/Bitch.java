package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Bitch extends Application {
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #f8ecc2;");
        pane.setMaxSize(10000, 10000);
        pane.setPrefSize(10000, 10000);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pane);
        scrollPane.setPannable(true);
        scrollPane.setMaxSize(1000, 1000);

        BorderPane root = new BorderPane();
        root.setCenter(scrollPane);

        stage.setScene(new Scene(root, 800, 600));
        stage.setResizable(true);
        stage.setTitle("KBE Premium");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
