package sample;

import javafx.application.Application;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label("лукашенко крутой");               // текстовая метка
        label.setStyle("-fx-font-size: xx-large; -fx-font-weight: bold;");

//        Button button = new Button("ЖЫВЕ БЕЛАРУСЬ");
//        button.setCursor(Cursor.HAND);
//        button.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-background-color: LightGreen;");

        MenuButton fileButton = new MenuButton("File");
        MenuItem newButton = new MenuItem("New");
        MenuItem openButton = new MenuItem("Open");
        MenuItem saveButton = new MenuItem("Save");
        MenuItem saveAsButton = new MenuItem("Save as...");
        fileButton.getItems().addAll(newButton, openButton, saveButton, saveAsButton);

        MenuButton instrumentsButton = new MenuButton("Instruments");
        MenuItem vertex = new MenuItem("Vertex");
        MenuItem edgeButton = new MenuItem("Edge");
        instrumentsButton.getItems().addAll(vertex, edgeButton);

        ToolBar toolBar = new ToolBar(fileButton, instrumentsButton);
        toolBar.setOrientation(Orientation.HORIZONTAL);
        toolBar.setStyle("-fx-background-color: #CDCBA6;");
        ObservableList<Node> tools = toolBar.getItems();
        for (Node node : tools) {
            node.setStyle("-fx-text-color: white; -fx-font-weight: bold; -fx-background-color: #008891;");
        }

        BorderPane root = new BorderPane();       // корневой узел
        root.setTop(toolBar);
        root.setCenter(label);
        root.setStyle("-fx-background-color: #E7E7DE;");

        Scene scene = new Scene(root, 600, 400);        // создание Scene

        stage.setScene(scene);                          // установка Scene для Stage
        stage.setResizable(true);
        stage.setTitle("KBE Premium");
        stage.show();
    }
}