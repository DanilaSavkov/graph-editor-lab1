package view.components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;


public class MyMenuBar extends MenuBar {
    public MyMenuBar() {
        double imageSide = 25;

        ImageView handImage = new MyImageView("https://pngimg.com/uploads/cursor/cursor_PNG87.png", imageSide);
        ImageView vertexImage = new MyImageView("https://static.thenounproject.com/png/80650-200.png", imageSide);
        ImageView edgeImage = new MyImageView("https://img.icons8.com/ios/452/line--v1.png", imageSide);
        ImageView dirImage = new MyImageView("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Folder_open_alt_font_awesome.svg/512px-Folder_open_alt_font_awesome.svg.png", imageSide);
        ImageView newImage = new MyImageView("https://image.flaticon.com/icons/png/512/14/14862.png", imageSide);
        ImageView saveImage = new MyImageView("https://img.icons8.com/ios/452/save-all.png", imageSide);
        ImageView saveAsImage = new MyImageView("https://img.icons8.com/ios/452/save-as.png", imageSide);

        MenuItem newButton = new MenuItem("New", newImage);
        MenuItem openButton = new MenuItem("Open", dirImage);
        MenuItem saveButton = new MenuItem("Save", saveImage);
        MenuItem saveAsButton = new MenuItem("Save as...", saveAsImage);
        MenuItem handButton = new MenuItem("Hand", handImage);
        MenuItem vertexButton = new MenuItem("Vertex", vertexImage);
        MenuItem edgeButton = new MenuItem("Edge", edgeImage);

        Menu fileMenu = new Menu("File", null, newButton, openButton, new SeparatorMenuItem(), saveButton, saveAsButton);
        Menu toolsMenu = new Menu("Tools", null, handButton, vertexButton, edgeButton);

        this.getMenus().addAll(fileMenu, toolsMenu);
    }
}


