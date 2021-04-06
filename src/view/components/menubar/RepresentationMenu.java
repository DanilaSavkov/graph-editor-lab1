package view.components.menubar;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class RepresentationMenu extends Menu {
    private static final MenuItem ADJACENCY_MATRIX_MENU_ITEM = new MenuItem("Adjacency matrix");
    private static final MenuItem DISTANCE_MATRIX_MENU_ITEM = new MenuItem("Distance matrix");
    private static final MenuItem WEIGHT_MATRIX_MENU_ITEM = new MenuItem("Weight matrix");

    public RepresentationMenu() {
        super();
        this.getItems().addAll(ADJACENCY_MATRIX_MENU_ITEM, WEIGHT_MATRIX_MENU_ITEM, DISTANCE_MATRIX_MENU_ITEM);
    }

    public RepresentationMenu(String s) {
        super(s);
        this.getItems().addAll(ADJACENCY_MATRIX_MENU_ITEM, WEIGHT_MATRIX_MENU_ITEM, DISTANCE_MATRIX_MENU_ITEM);
    }

    public RepresentationMenu(String s, Node node) {
        super(s, node);
        this.getItems().addAll(ADJACENCY_MATRIX_MENU_ITEM, WEIGHT_MATRIX_MENU_ITEM, DISTANCE_MATRIX_MENU_ITEM);
    }

    public RepresentationMenu(String s, Node node, MenuItem... menuItems) {
        super(s, node, menuItems);
        this.getItems().add(0, ADJACENCY_MATRIX_MENU_ITEM);
        this.getItems().add(1, WEIGHT_MATRIX_MENU_ITEM);
        this.getItems().add(2, DISTANCE_MATRIX_MENU_ITEM);
    }

    public MenuItem getAdjacencyMatrixMenuItem() {
        return ADJACENCY_MATRIX_MENU_ITEM;
    }

    public MenuItem getDistanceMatrixMenuItem() {
        return DISTANCE_MATRIX_MENU_ITEM;
    }

    public MenuItem getWeightMatrixMenuItem() {
        return WEIGHT_MATRIX_MENU_ITEM;
    }
}
