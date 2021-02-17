package view;

import controller.ButtonPropertiesController;
import javafx.application.Application;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

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
        SeparatorMenuItem separator = new SeparatorMenuItem();
        fileButton.getItems().addAll(newButton, openButton, separator, saveButton, saveAsButton);

        MenuButton instrumentsButton = new MenuButton("Instruments");           // кнопка инструменты на панели инструментов
        MenuItem handButton = new MenuItem("Hand");
        handButton.setGraphic(handImageView);
        MenuItem vertexButton = new MenuItem("Vertex");
        vertexButton.setGraphic(vertexImageView);
        MenuItem edgeButton = new MenuItem("Edge");
        edgeButton.setGraphic(edgeImageView);
        instrumentsButton.getItems().addAll(handButton, vertexButton, edgeButton);

        MenuButton helpButton = new MenuButton("Help");         // кнопка помощь на панели инструментов
        MenuItem helpText = new MenuItem();
        helpText.setDisable(true);
        helpText.setText("Use vertex to add a vertex \n" +
                "Use edge to add an edge \n" +
                "Use hand to pick vertex or edge");
        helpButton.getItems().add(helpText);


        ToolBar toolBar = new ToolBar(fileButton, instrumentsButton, helpButton);           // панель инструментов
        toolBar.setOrientation(Orientation.HORIZONTAL);
        toolBar.setStyle("-fx-background-color: #CDCBA6;");
        ObservableList<Node> tools = toolBar.getItems();
        for (Node node : tools) {
            node.setStyle("-fx-text-color: white; -fx-font-weight: bold; -fx-background-color: #008891;");
        }


        Button handB = new Button();
        handB.setGraphic(handImageView);

        Button vertexB = new Button();
        vertexB.setGraphic(vertexImageView);
        Button edgeB = new Button();
        edgeB.setGraphic(edgeImageView);

        ButtonPropertiesController handBController = new ButtonPropertiesController(handB);
        handBController.setProperties(1.1, 1, Cursor.HAND);

        ButtonPropertiesController vertexBController = new ButtonPropertiesController(vertexB);
        vertexBController.setProperties(1.1, 1, Cursor.HAND);

        ButtonPropertiesController edgeBController = new ButtonPropertiesController(edgeB);
        edgeBController.setProperties(1.1, 1, Cursor.HAND);

        VBox instruments = new VBox(handB, vertexB, edgeB);         // панель меню слева окна
        instruments.setSpacing(10);


        BorderPane emptyList = new BorderPane();
        emptyList.setCenter(label);
        emptyList.setStyle("-fx-background-color: #E7E7DE;");


        GridPane root = new GridPane();
        root.setGridLinesVisible(true);

        RowConstraints row1 = new RowConstraints(toolBar.getMaxHeight(), toolBar.getMaxHeight(), Double.MAX_VALUE);
        row1.setVgrow(Priority.NEVER);

        RowConstraints row2 = new RowConstraints(emptyList.getMaxHeight(), emptyList.getMaxHeight(), Double.MAX_VALUE);
        row2.setVgrow(Priority.ALWAYS);

        ColumnConstraints col1 = new ColumnConstraints(emptyList.getMaxWidth(), emptyList.getMaxWidth(), Double.MAX_VALUE);
        col1.setHgrow(Priority.ALWAYS);

        root.getRowConstraints().add(row1);
        root.getRowConstraints().add(row2);
        root.getColumnConstraints().add(col1);

        root.add(toolBar, 0, 0);
        root.add(emptyList, 0, 1);


        Scene scene = new Scene(root, 800, 450);


        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("KBE Premium");
        stage.show();
    }
}