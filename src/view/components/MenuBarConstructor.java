package view.components;

import javafx.scene.control.*;

public class MenuBarConstructor {
    private static final Menu FILE_MENU = new Menu();
    private final MenuBar menuBar;

    public MenuBarConstructor() {
        menuBar = new MenuBar(FILE_MENU);
        configureMenuBar();
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public Menu getFileMenu() {
        return FILE_MENU;
    }

    private static void configureMenuBar() {
        configureFileMenu();
    }

    private static void configureFileMenu() {
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveAsMenuItem = new MenuItem("Save as...");

        FILE_MENU.setText("F I L E");
        FILE_MENU.getItems().addAll(newMenuItem, openMenuItem, saveAsMenuItem);
    }
}