package view.components;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.representations.AdjacencyMatrix;
import model.representations.DistanceMatrix;
import model.representations.WeightMatrix;
import view.components.tabpane.AppTabPane;

import java.io.File;
import java.util.Optional;

public class Dialogs {
    public static String getUserGraphName() {
        TextInputDialog dialog = new TextInputDialog("Graph");
        dialog.setTitle("New Graph");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Graph name:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    public static File getFileToOpenChoose() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select graph file");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("GRAPH", "*.graph")
        );
        return chooser.showOpenDialog(new Stage());
    }

    public static File getPathToSaveFile(String initialName) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select directory");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.setInitialFileName(initialName);
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("DIR", "*/*")
        );
        return chooser.showSaveDialog(new Stage());
    }

    public static void showAlreadyExistAlert(String name) {
        alertBuilder(Alert.AlertType.CONFIRMATION, "Ooops...", "Graph named " + "'" + name + "'" + " already exist.").showAndWait();
    }

    public static void showFileError(String message) {
        alertBuilder(Alert.AlertType.ERROR, "Ooops...", message).showAndWait();
    }

    public static void showAdjacencyMatrix(AppTabPane tabPane) {
        AdjacencyMatrix matrix = new AdjacencyMatrix(tabPane.getActiveSheet().getGraph());
        alertBuilder(Alert.AlertType.INFORMATION, "Adjacency matrix", matrix.toString()).showAndWait();
    }

    public static void showWeightMatrix(AppTabPane tabPane) {
        WeightMatrix matrix = new WeightMatrix(tabPane.getActiveSheet().getGraph());
        alertBuilder(Alert.AlertType.INFORMATION, "Weight matrix", matrix.toString()).showAndWait();
    }

    public static void showDistanceMatrix(AppTabPane tabPane) {
        DistanceMatrix matrix = new DistanceMatrix(tabPane.getActiveSheet().getGraph());
        alertBuilder(Alert.AlertType.INFORMATION, "Distance matrix", matrix.toString()).showAndWait();
    }

    private static Alert alertBuilder(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }
}
