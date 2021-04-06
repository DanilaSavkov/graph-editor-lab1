package view.components.tabpane;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import view.components.Dialogs;

public class AppTabPane extends TabPane {
    public AppTabPane() {
        super();
    }

    public Sheet getActiveSheet() {
        return (Sheet) ((ScrollPane) getActiveTab().getContent()).getContent();
    }

    private Tab getActiveTab() {
        return this.getSelectionModel().getSelectedItem();
    }

    public void addSheet(String name) {
        if (name != null && !contains(name)) {
            Sheet sheet = new Sheet(name);
            this.getTabs().add(new Tab(sheet.getGraph().getName(), sheetToScrollPane(sheet)));
        }
    }

    public void addSheet(Sheet sheet) {
        this.getTabs().add(new Tab(sheet.getGraph().getName(), sheetToScrollPane(sheet)));
    }

    private ScrollPane sheetToScrollPane(Sheet sheet) {
        ScrollPane scrollPane = new ScrollPane(sheet);
        scrollPane.setMaxSize(6500, 6500);
        scrollPane.setContent(sheet);
        scrollPane.setHvalue(sheet.getGraphXPosition());
        scrollPane.setVvalue(sheet.getGraphYPosition());
        return scrollPane;
    }

    public boolean contains(String name) {
        for (Tab tab : this.getTabs()) {
            if (tab.getText().equals(name)) {
                Dialogs.showAlreadyExistAlert(name);
                return true;
            }
        }
        return false;
    }
}