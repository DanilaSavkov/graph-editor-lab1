package view.components.menubar;

import javafx.scene.control.MenuBar;

public class AppMenuBar extends MenuBar {
    private static final FileMenu FILE_MENU = new FileMenu("File");
    private static final ToolsMenu TOOLS_MENU = new ToolsMenu("Tools");
    private static final RepresentationMenu REPRESENTATION_MENU = new RepresentationMenu("Representation");

    public AppMenuBar() {
        super(FILE_MENU, TOOLS_MENU, REPRESENTATION_MENU);
    }

    public FileMenu getFileMenu() {
        return FILE_MENU;
    }

    public ToolsMenu getToolsMenu() {
        return TOOLS_MENU;
    }

    public RepresentationMenu getRepresentationMenu() {
        return REPRESENTATION_MENU;
    }
}