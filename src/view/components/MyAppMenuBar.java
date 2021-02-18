package view.components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MyAppMenuBar {
    public static MenuBar generate() {
        double imageSide = 25;

        ImageView handImage = getImageBy("https://pngimg.com/uploads/cursor/cursor_PNG87.png", imageSide);
        ImageView vertexImage = getImageBy("https://static.thenounproject.com/png/80650-200.png", imageSide);
        ImageView edgeImage = getImageBy("https://img.icons8.com/ios/452/line--v1.png", imageSide);
        ImageView dirImage = getImageBy("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Folder_open_alt_font_awesome.svg/512px-Folder_open_alt_font_awesome.svg.png", imageSide);
        ImageView newImage = getImageBy("https://image.flaticon.com/icons/png/512/14/14862.png", imageSide);
        ImageView saveImage = getImageBy("https://img.icons8.com/ios/452/save-all.png", imageSide);
        ImageView saveAsImage = getImageBy("https://img.icons8.com/ios/452/save-as.png", imageSide);

        MenuItem newButton = getMenuItemBy("New", newImage);
        MenuItem openButton = getMenuItemBy("Open", dirImage);
        MenuItem saveButton = getMenuItemBy("Save", saveImage);
        MenuItem saveAsButton = getMenuItemBy("Save as...", saveAsImage);
        MenuItem handButton = getMenuItemBy("Hand", handImage);
        MenuItem vertexButton = getMenuItemBy("Vertex", vertexImage);
        MenuItem edgeButton = getMenuItemBy("Edge", edgeImage);

        Menu fileMenu = getMenuByItems("File", newButton, openButton, new SeparatorMenuItem(), saveButton, saveAsButton);
        Menu toolsMenu = getMenuByItems("Tools", handButton, vertexButton, edgeButton);

        return getMenuBarByMenus(fileMenu, toolsMenu);
    }

    public static ImageView getImageBy(String url, double side) {
        Image image = new Image(url);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(side);
        imageView.setFitWidth(side);
        return imageView;
    }

    public static MenuItem getMenuItemBy(String name, ImageView imageView) {
        MenuItem menuItem = new MenuItem(name);
        menuItem.setGraphic(imageView);
        return menuItem;
    }

    public static Menu getMenuByItems(String name, MenuItem... menuItems) {
        Menu menu = new Menu(name);
        menu.getItems().addAll(menuItems);
        return menu;
    }

    public static MenuBar getMenuBarByMenus(Menu... menus) {
        return new MenuBar(menus);
    }
}


