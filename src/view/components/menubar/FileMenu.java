package view.components.menubar;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileMenu extends Menu {
    private static final MenuItem NEW_FILE_MENU_ITEM = new MenuItem("New");
    private static final MenuItem OPEN_FILE_MENU_ITEM = new MenuItem("Open");
    private static final MenuItem SAVE_AS_FILE_MENU_ITEM = new MenuItem("Save as");

    public FileMenu() {
        super();
        this.getItems().addAll(NEW_FILE_MENU_ITEM, OPEN_FILE_MENU_ITEM, SAVE_AS_FILE_MENU_ITEM);
    }

    public FileMenu(String s) {
        super(s);
        this.getItems().addAll(NEW_FILE_MENU_ITEM, OPEN_FILE_MENU_ITEM, SAVE_AS_FILE_MENU_ITEM);
    }

    public FileMenu(String s, Node node) {
        super(s, node);
        this.getItems().addAll(NEW_FILE_MENU_ITEM, OPEN_FILE_MENU_ITEM, SAVE_AS_FILE_MENU_ITEM);
    }

    public FileMenu(String s, Node node, MenuItem... menuItems) {
        super(s, node, menuItems);
        this.getItems().add(0, NEW_FILE_MENU_ITEM);
        this.getItems().add(1, OPEN_FILE_MENU_ITEM);
        this.getItems().add(2, SAVE_AS_FILE_MENU_ITEM);
    }

    public MenuItem getNewFileMenuItem() {
        return NEW_FILE_MENU_ITEM;
    }

    public MenuItem getOpenFileMenuItem() {
        return OPEN_FILE_MENU_ITEM;
    }

    public MenuItem getSaveAsFileMenuItem() {
        return SAVE_AS_FILE_MENU_ITEM;
    }
}
