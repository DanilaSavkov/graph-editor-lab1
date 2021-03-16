package view;

import javafx.application.Application;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import view.components.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
//        Scene scene = new Scene(root.getGridPane(), 1000, 600);
        AppConstructor constructor = new AppConstructor();

        stage.setScene(constructor.getScene());
        stage.setResizable(true);
        stage.setTitle("KBE Premium");
//        stage.addEventFilter(KeyEvent.KEY_RELEASED, root.getSheet().keyReleasedHandler());
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}