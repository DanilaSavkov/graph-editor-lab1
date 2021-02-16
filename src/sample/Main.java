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

        Label label = new Label("KBE PREMIUM");         // водяная метка приложения
        label.setStyle("-fx-font-size: xx-large; -fx-font-weight: bold; -fx-opacity: .1");



        Image handImage = new Image("https://pngimg.com/uploads/cursor/cursor_PNG87.png");          // иконка курсора
        ImageView handImageView = new ImageView(handImage);
        handImageView.setFitHeight(30);
        handImageView.setFitWidth(30);

        Image vertexImage = new Image("https://static.thenounproject.com/png/80650-200.png");       // иконка вершины
        ImageView vertexImageView = new ImageView(vertexImage);
        vertexImageView.setFitHeight(30);
        vertexImageView.setFitWidth(30);

        Image edgeImage = new Image("https://img.icons8.com/ios/452/line--v1.png");         // иконка ребра
        ImageView edgeImageView = new ImageView(edgeImage);
        edgeImageView.setFitHeight(30);
        edgeImageView.setFitWidth(30);

        Image dirImage = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Folder_open_alt_font_awesome.svg/512px-Folder_open_alt_font_awesome.svg.png");            // иконка открыть
        ImageView dirImageView = new ImageView(dirImage);
        dirImageView.setFitHeight(30);
        dirImageView.setFitWidth(30);

        Image newImage = new Image("https://image.flaticon.com/icons/png/512/14/14862.png");            // иконка новый файл
        ImageView newImageView = new ImageView(newImage);
        newImageView.setFitHeight(30);
        newImageView.setFitWidth(30);

        Image saveImage = new Image("https://img.icons8.com/ios/452/save-all.png");         // иконка сохранить
        ImageView saveImageView = new ImageView(saveImage);
        saveImageView.setFitHeight(30);
        saveImageView.setFitWidth(30);

        Image saveAsImage = new Image("https://img.icons8.com/ios/452/save-as.png");            // иконка сохранить как
        ImageView saveAsImageView = new ImageView(saveAsImage);
        saveAsImageView.setFitHeight(30);
        saveAsImageView.setFitWidth(30);



        MenuButton fileButton = new MenuButton("File");         // кнопка файл на панели инструментов
        MenuItem newButton = new MenuItem("New");
        newButton.setGraphic(newImageView);
        MenuItem openButton = new MenuItem("Open");
        openButton.setGraphic(dirImageView);
        MenuItem saveButton = new MenuItem("Save");
        saveButton.setGraphic(saveImageView);
        MenuItem saveAsButton = new MenuItem("Save as...");
        saveAsButton.setGraphic(saveAsImageView);
        SeparatorMenuItem separator= new SeparatorMenuItem();
        fileButton.getItems().addAll(newButton, openButton, separator, saveButton, saveAsButton);

        MenuButton instrumentsButton = new MenuButton("Instruments");           // кнопка инструменты на панели инструментов
        MenuItem handButton = new MenuItem("Hand");
        handButton.setGraphic(handImageView);
        MenuItem vertexButton = new MenuItem("Vertex");
        vertexButton.setGraphic(vertexImageView);
        MenuItem edgeButton = new MenuItem("Edge");
        edgeButton.setGraphic(edgeImageView);
        instrumentsButton.getItems().addAll(handButton, vertexButton, edgeButton);



        ToolBar toolBar = new ToolBar(fileButton, instrumentsButton);           // панель инструментов
        toolBar.setOrientation(Orientation.HORIZONTAL);
        toolBar.setStyle("-fx-background-color: #CDCBA6;");
        ObservableList<Node> tools = toolBar.getItems();
        for (Node node : tools) {
            node.setStyle("-fx-text-color: white; -fx-font-weight: bold; -fx-background-color: #008891;");
        }



        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(label);
        root.setStyle("-fx-background-color: #E7E7DE;");

        Scene scene = new Scene(root, 600, 400);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("KBE Premium");
        stage.show();
    }
}