package handlers;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.GraphicalVertex;
import model.Vertex;
import view.components.Sheet;

import java.util.List;
import java.util.Optional;

public class SheetHandler {
    // обработчик событий нажатий мышью по панели
    public static EventHandler<MouseEvent> mouseClick(Sheet sheet) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    oneClickAction(sheet, mouseEvent);
                    if (doubleClick(mouseEvent)) doubleClickAction(sheet, mouseEvent);
                }
            }
        };
    }

    private static void oneClickAction(Sheet sheet, MouseEvent mouseEvent) {
        if (!vertexClicked(sheet, mouseEvent)) unselectAllVertices(sheet);
    }

    private static void unselectAllVertices(Sheet sheet) {
        for (Vertex vertex : sheet.getGraph().getVertices()) {
            ((GraphicalVertex) vertex).unselect();
        }
    }

    private static boolean doubleClick(MouseEvent mouseEvent) {
        return mouseEvent.getClickCount() == 2;
    }

    private static void doubleClickAction(Sheet sheet, MouseEvent mouseEvent) {
        GraphicalVertex graphicalVertex = getVertexByMouse(mouseEvent);
        if (!sheet.contains(graphicalVertex)) {
            sheet.add(graphicalVertex);
        }
    }

    private static GraphicalVertex getVertexByMouse(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        return new GraphicalVertex(x, y);
    }

    private static boolean vertexClicked(Sheet sheet, MouseEvent mouseEvent) {
        return sheet.contains(new GraphicalVertex(mouseEvent.getX(), mouseEvent.getY()));
    }

    // обработчик событий нажатий клавиш на клавиатуре
    public static EventHandler<KeyEvent> keyReleased(Sheet sheet) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case DELETE:
                        deleteAllVertices(sheet);
                        break;
                    case I:
                        setLastVertexIdentifier(sheet);
                        break;
                }
            }
        };
    }

    private static void deleteAllVertices(Sheet sheet) {
        for (GraphicalVertex vertex : sheet.getSelectedVertices()) {
            sheet.remove(vertex);
        }
    }

    private static void setLastVertexIdentifier(Sheet sheet) {
        List<GraphicalVertex> selectedVertices = sheet.getSelectedVertices();
        GraphicalVertex vertex = null;
        if (!selectedVertices.isEmpty()) vertex = selectedVertices.get(selectedVertices.size() - 1);
        if (vertex != null) {
            String id = getUserIdentifier();
            if (id != null) {
                vertex.setId(id);
            }
        }
    }

    private static String getUserIdentifier() {
        TextInputDialog dialog = new TextInputDialog(null);
        dialog.setTitle("Change identifier");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("New identifier:");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
