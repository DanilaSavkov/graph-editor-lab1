package view;

import javafx.application.Application;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.components.AppConstructor;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        AppConstructor constructor = new AppConstructor();

        stage.setScene(constructor.getScene());
        stage.setResizable(true);
        stage.setTitle("KBE Premium");
        stage.addEventFilter(KeyEvent.KEY_RELEASED, constructor.keyReleasedHandler());
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}