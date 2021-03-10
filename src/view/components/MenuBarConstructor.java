package view.components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class MenuBarConstructor {
    private final MenuBar menuBar;

    public MenuBarConstructor() {
        menuBar = new MenuBar(getFileMenu(), getToolsMenu());
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    private static Menu getFileMenu() {
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem saveAsMenuItem = new MenuItem("Save as...");

        return new Menu("File", null, newMenuItem, openMenuItem, new SeparatorMenuItem(), saveMenuItem, saveAsMenuItem);
    }

    private static Menu getToolsMenu() {
        MenuItem vertexMenuItem = new MenuItem("Vertex");
        MenuItem edgeMenuItem = new MenuItem("Edge");

        return new Menu("Tools", null, vertexMenuItem, edgeMenuItem);
    }
}
