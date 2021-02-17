package view.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuGeneration {
    public static ImageView getImageByProperties(String url, double side) {
        Image image = new Image(url);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(side);
        imageView.setFitWidth(side);
        return imageView;
    }

    public static MenuItem getMenuItemByProperties(String name, ImageView imageView) {
        MenuItem menuItem = new MenuItem(name);
        menuItem.setGraphic(imageView);
        return menuItem;
    }

    public static Menu getMenuByMenuItems(String name, MenuItem... menuItems) {
        Menu menu = new Menu(name);
        menu.getItems().addAll(menuItems);
        return menu;
    }

    public static MenuBar getMenuBarByMenus(Menu... menus) {
        return new MenuBar(menus);
    }
}