package view.components;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;

public class AppConstructor {
    private Scene scene;
    private Sheet sheet;
    private ToolBar toolBar;
    private MenuBar menuBar;

    public AppConstructor() {
        menuBar = new MenuBarConstructor().getMenuBar();
        toolBar = new ToolBarConstructor().getToolBar();
        sheet = new Sheet();
    }
}
