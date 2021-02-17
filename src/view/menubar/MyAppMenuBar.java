package view.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;

import static view.menubar.MenuGeneration.*;

public class MyAppMenuBar {
    public static MenuBar getMenuBar() {
        double imageSide = 25;

        ImageView handImage = getImageByProperties("https://pngimg.com/uploads/cursor/cursor_PNG87.png", imageSide);
        ImageView vertexImage = getImageByProperties("https://static.thenounproject.com/png/80650-200.png", imageSide);
        ImageView edgeImage = getImageByProperties("https://img.icons8.com/ios/452/line--v1.png", imageSide);
        ImageView dirImage = getImageByProperties("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Folder_open_alt_font_awesome.svg/512px-Folder_open_alt_font_awesome.svg.png", imageSide);
        ImageView newImage = getImageByProperties("https://image.flaticon.com/icons/png/512/14/14862.png", imageSide);
        ImageView saveImage = getImageByProperties("https://img.icons8.com/ios/452/save-all.png", imageSide);
        ImageView saveAsImage = getImageByProperties("https://img.icons8.com/ios/452/save-as.png", imageSide);

        MenuItem newButton = getMenuItemByProperties("New", newImage);
        MenuItem openButton = getMenuItemByProperties("Open", dirImage);
        MenuItem saveButton = getMenuItemByProperties("Save", saveImage);
        MenuItem saveAsButton = getMenuItemByProperties("Save as...", saveAsImage);
        MenuItem handButton = getMenuItemByProperties("Hand", handImage);
        MenuItem vertexButton = getMenuItemByProperties("Vertex", vertexImage);
        MenuItem edgeButton = getMenuItemByProperties("Edge", edgeImage);

        Menu fileMenu = getMenuByMenuItems("File", newButton, openButton, new SeparatorMenuItem(), saveButton, saveAsButton);
        Menu toolsMenu = getMenuByMenuItems("Tools", handButton, vertexButton, edgeButton);

        return getMenuBarByMenus(fileMenu, toolsMenu);
    }
}


