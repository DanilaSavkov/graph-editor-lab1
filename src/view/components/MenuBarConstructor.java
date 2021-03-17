package view.components;

import javafx.scene.control.*;

public class MenuBarConstructor {
    private static final Menu FILE_MENU = new Menu();
    private static final Menu TOOLS_MENU = new Menu();

    private final MenuBar menuBar;

    public MenuBarConstructor() {
        menuBar = new MenuBar(FILE_MENU, TOOLS_MENU);
        configureMenuBar();
    }

    /*
     *      getter's and setter's
     */

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public Menu getFileMenu() {
        return FILE_MENU;
    }

    public Menu getToolsMenu() {
        return TOOLS_MENU;
    }

    /*
     *      configurations
     */

    private static void configureMenuBar() {
        configureFileMenu();
        configureToolsMenu();
    }

    private static void configureFileMenu() {
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem saveAsMenuItem = new MenuItem("Save as...");

        FILE_MENU.setText("F I L E");
        FILE_MENU.getItems().addAll(newMenuItem, openMenuItem, new SeparatorMenuItem(), saveMenuItem, saveAsMenuItem);
    }

    private static void configureToolsMenu() {
        MenuItem vertexMenuItem = new MenuItem("Vertex");
        MenuItem edgeMenuItem = new MenuItem("Edge");

        TOOLS_MENU.setText("T O O L S");
        TOOLS_MENU.getItems().addAll(vertexMenuItem, edgeMenuItem);
    }
}