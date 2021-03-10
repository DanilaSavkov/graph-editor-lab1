package view;

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import view.components.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        RootConstructor root = new RootConstructor();
        Scene scene = new Scene(root.getGridPane(), 1000, 600);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("KBE Premium");
//        stage.addEventFilter(KeyEvent.KEY_RELEASED, SheetHandler.keyReleased(sheet));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}