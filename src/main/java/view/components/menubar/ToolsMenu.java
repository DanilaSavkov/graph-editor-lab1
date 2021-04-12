package view.components.menubar;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class ToolsMenu extends Menu {
    private static final MenuItem VERTEX_MODE_MENU_ITEM = new MenuItem("Vertex Mode");
    private static final MenuItem EDGE_MODE_MENU_ITEM = new MenuItem("Edge Mode");
    private static final MenuItem ALGORITHM_MENU_ITEM = new MenuItem("Do algorithm 5");

    public ToolsMenu() {
        super();
        this.getItems().addAll(VERTEX_MODE_MENU_ITEM, EDGE_MODE_MENU_ITEM, ALGORITHM_MENU_ITEM);
    }

    public ToolsMenu(String s) {
        super(s);
        this.getItems().addAll(VERTEX_MODE_MENU_ITEM, EDGE_MODE_MENU_ITEM, ALGORITHM_MENU_ITEM);
    }

    public ToolsMenu(String s, Node node) {
        super(s, node);
        this.getItems().addAll(VERTEX_MODE_MENU_ITEM, EDGE_MODE_MENU_ITEM, ALGORITHM_MENU_ITEM);
    }

    public ToolsMenu(String s, Node node, MenuItem... menuItems) {
        super(s, node, menuItems);
        this.getItems().add(0, VERTEX_MODE_MENU_ITEM);
        this.getItems().add(1, EDGE_MODE_MENU_ITEM);
        this.getItems().add(2, ALGORITHM_MENU_ITEM);
    }

    public MenuItem getVertexModeMenuItem() {
        return VERTEX_MODE_MENU_ITEM;
    }

    public MenuItem getEdgeModeMenuItem() {
        return EDGE_MODE_MENU_ITEM;
    }

    public MenuItem getAlgorithmMenuItem() {
        return ALGORITHM_MENU_ITEM;
    }
}